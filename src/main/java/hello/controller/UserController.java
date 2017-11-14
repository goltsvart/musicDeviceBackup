package hello.controller;

//@Controller
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(Model model, String error, String logout) {
//        if (error != null)
//            model.addAttribute("error", "Your username and password is invalid.");
//
//        if (logout != null)
//            model.addAttribute("message", "You have been logged out successfully.");
//
//        return "login";
//    }
//
//    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
//    public String welcome(Model model) {
//        return "home";
//    }
//}