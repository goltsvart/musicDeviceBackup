package hello.controller;

import hello.data.domain.Album;
import hello.data.domain.Track;
import hello.data.domain.TrackList;
import hello.data.domain.User;
import hello.service.AlbumService;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/lists", method = RequestMethod.GET)
    public String displayLists(Model model, HttpServletRequest req) throws Exception {
        User u = userService.findByUsername(req.getRemoteUser());
        model.addAttribute("trackLists", albumService.displayAllLists(u));
        return "lists";
    }

    @RequestMapping(value = "/tracks", method = RequestMethod.GET)
    public String displayTracks(Model model, HttpServletRequest req, @RequestParam("albumName") String albumName) throws Exception {
        User u = userService.findByUsername(req.getRemoteUser());
        Album a = albumService.findAlbumByAlbumName(albumName);
        model.addAttribute("tracks", albumService.displayAllTracks(u, a));
        return "tracks";
    }

    @RequestMapping(value = "/listtracks", method = RequestMethod.GET)
    public String displayListTracks(Model model, HttpServletRequest req, @RequestParam("playlistId") String playlistId) throws Exception {
        User u = userService.findByUsername(req.getRemoteUser());
        TrackList t = albumService.findTrackListById(Long.parseLong(playlistId));
        model.addAttribute("tracks", albumService.displayAllTracksForList(u, t));
        model.addAttribute("playlistId", playlistId);
        return "tracks";
    }

    @RequestMapping(value= "/tracks/edit/{id}", method = RequestMethod.GET)
    public String editTrack(@PathVariable("id") Integer trackId, ModelMap model) {
        model.put("track", albumService.findTrackById(trackId));
        return "edit";
    }

    @RequestMapping(value= "/tracks/delete/{id}", method = RequestMethod.GET)
    public String delTrack(@PathVariable("id") Integer trackId, ModelMap model) {
        albumService.deleteTrackById(trackId);
        return "hello";
    }

    @RequestMapping(value= "/tracks/{id}/move/{playlistId}", method = RequestMethod.GET)
    public String moveTrack(@PathVariable("id") Integer trackId, ModelMap model, @PathVariable("playlistId") String playlistId) {
        model.put("track", albumService.findTrackById(trackId));
        model.addAttribute("playlistId", playlistId);
        return "move";
    }

    @RequestMapping(value="/track",method=RequestMethod.POST)
    public String saveEditedTrack(@ModelAttribute("track") Track editedTrack, BindingResult result, ModelMap model)
    {
        albumService.saveTrack(editedTrack);
        return "hello";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerUser(Model model, HttpServletRequest req) throws Exception {
        return "register";
    }

    @RequestMapping(value="/register",method=RequestMethod.POST)
    public String register(@ModelAttribute("user") User user, BindingResult result, ModelMap model)
    {
        userService.save(user);
        return "login";
    }

    @RequestMapping(value = "/move", method = RequestMethod.POST)
    public String moveTrackTo(@ModelAttribute("track") Track track, @RequestParam(name = "trackList") String trackList,
                              BindingResult result, ModelMap model, @RequestParam(name = "playlistId") String playlistId) {
        TrackList oldtl = albumService.findTrackListById(Long.valueOf(playlistId));
        TrackList tl = albumService.findTrackListByPlaylistName(trackList);
        Track t = albumService.findTrackById(track.getId());
        albumService.moveTrackTo(oldtl, t, tl);
        return "hello";
    }
}