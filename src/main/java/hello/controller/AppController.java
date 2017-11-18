package hello.controller;

import hello.data.domain.User;
import hello.service.AlbumService;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AppController {
    @Autowired
    private UserService userService;
    @Autowired
    private AlbumService albumService;

    @RequestMapping(value = "/albums", method = RequestMethod.GET)
    public String displayTracks(Model model, HttpServletRequest req) throws Exception {
        User u = userService.findByUsername(req.getRemoteUser());
        model.addAttribute("albums", albumService.displayAll(u));
        return "albums";
    }

}