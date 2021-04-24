package ge.tsu.library;

import java.sql.SQLException;
import java.util.List;

public interface LibraryService {
  List<BookView> getBooks() throws SQLException;
}
