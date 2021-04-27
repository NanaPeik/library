package ge.tsu.library.book;

import ge.tsu.library.book.BookView;
import java.sql.SQLException;
import java.util.List;

public interface BookService {

  List<BookView> getBooks() throws SQLException;
}
