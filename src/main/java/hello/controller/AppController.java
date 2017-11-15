package hello.controller;

import hello.data.domain.User;
import hello.service.TrackService;
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
    private TrackService trackService;

    @RequestMapping(value = "/tracks", method = RequestMethod.GET)
    public String displayTracks(Model model, HttpServletRequest req) throws Exception {
        User u = userService.findByUsername(req.getRemoteUser());
        model.addAttribute("tracks", trackService.displayAll(u));
        return "tracks";
    }

}