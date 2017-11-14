package hello;

import hello.data.domain.Track;
import hello.data.domain.User;
import hello.service.UserService;
import hello.storage.StorageFileNotFoundException;
import hello.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
//@RequestMapping(value = "/")
public class FileUploadController {

    @Autowired
    private UserService userService;

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/uploadForm")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/uploadForm")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,  @RequestParam("userName") String userName) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        //to read this file and save into database for particular user
        User u = userService.findByUsername(userName);
        List<Track> tracks = new ArrayList<Track>();
        try {

            File fXmlFile = new File("upload-dir/" + file.getOriginalFilename());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();
            String library_persistance_id = "";
            String nodeName = doc.getDocumentElement().getNodeName();
            NodeList nList = doc.getElementsByTagName("dict");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                //    System.out.println("NODE NAME:" + eElement.getNodeName());
                //    System.out.println("TAG NAME:" + eElement.getTagName());
                    // Iterate through sub-elements

                    // A map to store key => values
                    Map<String, String> mss = new HashMap<String, String>();

                    NodeList childs = eElement.getChildNodes();
                    {
                        for (int k = 0; k < childs.getLength(); k++) {
                            Node child = childs.item(k);

                            if (child.getNodeType() == Node.ELEMENT_NODE) {
                                Element child_element = (Element) child;
                                String tag = child_element.getTagName();
                                String content = child_element.getTextContent();

                                if (tag.equals("key") && (k + 1 < childs.getLength())) {
                                    // Next one
                                    Node next = childs.item(k + 1);

                                    if (next.getNodeType() == Node.ELEMENT_NODE) {
                                        Element next_element = (Element) next;
                                        // Should be integer or string
                                        String next_tag = next_element.getTagName();
                                        String next_content = next_element.getTextContent();

                                            // store
                                        mss.put(content, next_content);
                                        // skip
                                        k++;
                                    }
                                }
                            }
                        }
                    }
                    String trackId = "";
                    String name = "";
                    String artist = "";
                    String album = "";
                    String year = "";
                    String genre = "";
                    //find particular elements
                    if (mss.containsKey("Track ID")) trackId = mss.get("Track ID");
                    if (mss.containsKey("Name")) name = mss.get("Name");
                    if (mss.containsKey("Artist")) artist = mss.get("Artist");
                    if (mss.containsKey("Album")) album = mss.get("Album");
                    if (mss.containsKey("Year")) year = mss.get("Year");
                    if (mss.containsKey("Genre")) genre = mss.get("Genre");
                    if (mss.containsKey("Library Persistent ID")) library_persistance_id = mss.get("Library Persistent ID");
                    //store data into object
                      if(trackId != null && !trackId.isEmpty() && name != null && !name.isEmpty()
                              && artist != null && !artist.isEmpty() && album != null && !album.isEmpty()
                              && year != null && !year.isEmpty() && genre != null && !genre.isEmpty()) {
                          Long tId = Long.valueOf(trackId).longValue();
                          Track t = new Track(tId, library_persistance_id, name, artist, album, year, genre);
                          System.out.println("track size" + t.toString());
                          tracks.add(t);
                          System.out.println("track size" + tracks.size());
                          userService.createLibrary(u, tracks);
                      }
                }
            }
            System.out.println(tracks.size());
            userService.createLibrary(u, tracks);
            System.out.println("Library Persistent ID: " + library_persistance_id);
            System.out.println(userName);
            //attach library persistance id to user

            userService.applyPersistanceId(u, library_persistance_id);
            //save tracks for that user


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/hello";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
