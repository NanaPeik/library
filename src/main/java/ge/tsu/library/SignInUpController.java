package ge.tsu.library;

import java.sql.SQLException;
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

  @RequestMapping(method = RequestMethod.POST, value = "/sign_in")
  public String signIn(@RequestParam String username, @RequestParam String password,
      HttpServletResponse httpServletResponse) {

    UserView userView = userService.getUser(username,password);
    if(userView!=null){
      return "redirect:library";
    }
    return "redirect:login";

//    if (username.equals(password)) {
//      Cookie cookie = new Cookie("user_signed", "yes");
//      cookie.setHttpOnly(true);
//      httpServletResponse.addCookie(cookie);
//      return "redirect:login";
//    }
//    Cookie cookie = new Cookie("user_signed", "no");
//    cookie.setHttpOnly(true);
//    httpServletResponse.addCookie(cookie);
//    return "redirect:library";
  }

  @RequestMapping(method = RequestMethod.POST, value = "/sign_up")
  public void signUp(@RequestParam String username, @RequestParam String password,
      HttpServletResponse httpServletResponse) {

  }
}
