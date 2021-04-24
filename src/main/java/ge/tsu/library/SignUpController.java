package ge.tsu.library;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class SignUpController {

  @RequestMapping(method = RequestMethod.POST, value = "/sign_up")
  public String login(@RequestParam String username, @RequestParam String password,
      HttpServletResponse httpServletResponse) {
    if (username.equals(password)) {
      Cookie cookie = new Cookie("user_signed", "yes");
      cookie.setHttpOnly(true);
      httpServletResponse.addCookie(cookie);
      return "redirect:login";
    }
    Cookie cookie = new Cookie("user_signed", "no");
    cookie.setHttpOnly(true);
    httpServletResponse.addCookie(cookie);
    return "redirect:login";
  }
}
