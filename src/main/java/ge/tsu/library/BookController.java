package ge.tsu.library;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BookController {

  @Autowired
  private LibraryService bookService;

  @GetMapping("/books")
  public List<BookView> getBooks() throws SQLException {
    return bookService.getBooks();
  }
}
