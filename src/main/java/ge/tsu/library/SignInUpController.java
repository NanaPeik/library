package ge.tsu.library;

import ge.tsu.library.user.UserService;
import ge.tsu.library.user.UserView;
import java.sql.SQLException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignInUpController {

  @Autowired
  private UserService userService;

  @GetMapping("/")
  public ModelAndView getBooks() throws SQLException {
    return new ModelAndView("sign_in");
  }

  @RequestMapping(method = RequestMethod.POST, value = "/signin")
  public String signIn(@RequestParam String username, @RequestParam String password,
      HttpServletResponse httpServletResponse) {

    UserView userView = userService.getUser(username, password);
    if (userView != null) {
      Cookie cookie = new Cookie("user_is_admin", userView.isAdmin());
      cookie.setHttpOnly(true);
      httpServletResponse.addCookie(cookie);
      return "redirect:library";
    }
    return "redirect:signin";
  }
  @RequestMapping(method = RequestMethod.POST, value = "/signup")
  public String registerUser(@RequestParam String user, @RequestParam String pass,
      @RequestParam String email, @RequestParam String repeatPass) {
    if(!repeatPass.equals(pass)){
      return "redirect:signup";
    }
    userService.registerUser(user,pass,email);
    return "redirect:signin";
  }

  @RequestMapping(method = RequestMethod.GET, value = "/signup")
  public ModelAndView signUp() {
    return new ModelAndView("sign_up");
  }

  @RequestMapping(method = RequestMethod.GET, value = "/signin")
  public ModelAndView signIn() {
    return new ModelAndView("sign_in");
  }

  @RequestMapping(method = RequestMethod.POST, value = "/edituserinformation")
  public ModelAndView editUser(@RequestParam String userid) {
    ModelAndView modelAndView = new ModelAndView("edit_user_information");
    modelAndView.addObject("user",userService.getUserById(Integer.parseInt(userid)));
    return modelAndView;
  }

}
