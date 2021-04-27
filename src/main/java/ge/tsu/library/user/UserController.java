package ge.tsu.library.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/users")
  public String updateUser(@RequestParam String name, @RequestParam String email,
    @RequestParam String id, @RequestParam String isAdmin) {
     userService.updateUser(id, name, email, isAdmin);
     return "redirect:library";
  }

}
