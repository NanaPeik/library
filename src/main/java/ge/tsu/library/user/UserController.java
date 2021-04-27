package ge.tsu.library.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PutMapping("/users/{userId}")
  public UserView updateUser(@RequestBody User updateUser, @PathVariable Integer userId) {
    return userService.updateUser(userId, updateUser);
  }

}
