package ge.tsu.library;

import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
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

  //TODO add check cookies
  @GetMapping("/")
  public ModelAndView getBooks() throws SQLException {
    return new ModelAndView("sign_in");
  }

  @RequestMapping(method = RequestMethod.POST, value = "/signin")
  public String signIn(@RequestParam String username, @RequestParam String password,
      HttpServletResponse httpServletResponse) {

    UserView userView = userService.getUser(username, password);
    if (userView != null) {
      return "redirect:library";
    }
    return "redirect:signin";
  }
  @RequestMapping(method = RequestMethod.POST, value = "/signup")
  public String signUp(@RequestParam String user, @RequestParam String pass,
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

}
