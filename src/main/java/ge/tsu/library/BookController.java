package ge.tsu.library;

import java.sql.SQLException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BookController {

  @Autowired
  private LibraryService bookService;
  @Autowired
  private UserService userService;

  @GetMapping("/library")
  public ModelAndView getBooks(HttpServletRequest request) throws SQLException {
    ModelAndView modelAndView = new ModelAndView("library");

    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length > 0) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("user_is_admin") && cookie.getValue().equals("true")) {
          modelAndView.addObject("users", userService.getUsers());
        }
      }
    }
    modelAndView.addObject("books", bookService.getBooks());
    return modelAndView;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/edituserinformation")
  public ModelAndView signUp(@RequestParam String userid) {
    ModelAndView modelAndView = new ModelAndView("edit_user_information");
    modelAndView.addObject("user",userService.getUserById(Integer.parseInt(userid)));
    return modelAndView;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/save")
  public String saveUserChanges() {
    //save changes in dataBase
    return "redirect:library";
  }
}
