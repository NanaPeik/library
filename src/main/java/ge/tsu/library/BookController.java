package ge.tsu.library;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BookController {

  @Autowired
  private LibraryService bookService;

  @GetMapping("/library")
  public ModelAndView getBooks() throws SQLException {
    ModelAndView modelAndView=new ModelAndView("library");
    modelAndView.addObject("books", bookService.getBooks());
    return modelAndView;
  }
}
