package hello.controller;

import hello.data.domain.Album;
import hello.data.domain.User;
import hello.service.AlbumService;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AppController {
    @Autowired
    private UserService userService;
    @Autowired
    private AlbumService albumService;

    @RequestMapping(value = "/albums", method = RequestMethod.GET)
    public String displayAlbums(Model model, HttpServletRequest req) throws Exception {
        User u = userService.findByUsername(req.getRemoteUser());
        model.addAttribute("albums", albumService.displayAll(u));
        return "albums";
    }

    @RequestMapping(value = "/tracks", method = RequestMethod.GET)
    public String displayTracks(Model model, HttpServletRequest req, @RequestParam("albumName") String albumName) throws Exception {
        User u = userService.findByUsername(req.getRemoteUser());
        Album a = albumService.findAlbumByAlbumName(albumName);
        model.addAttribute("tracks", albumService.displayAllTracks(u, a));
        return "tracks";
    }

}