package ge.tsu.library.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable Integer id) {
    userService.deleteUser(id);
  }

}
