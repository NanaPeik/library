package ge.tsu.library.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/users")
  public UserView updateUser(@RequestParam String name, @RequestParam String email,
    @RequestParam String id, @RequestParam String isAdmin) {
    return userService.updateUser(id, name, email, isAdmin);
  }

}
