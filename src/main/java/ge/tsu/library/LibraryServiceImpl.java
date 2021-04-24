package ge.tsu.library;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LibraryServiceImpl implements LibraryService {

  private JdbcTemplate jdbcTemplate;

  @Override
  public List<BookView> getBooks() throws SQLException {
    return jdbcTemplate
        .queryForList("select * from public.book")
        .stream()
        .map(this::map)
        .collect(Collectors.toList());
  }

  private BookView map(Map<String, Object> map) {
    BookView bookView = new BookView();
    for (Map.Entry<String, Object> entry : map.entrySet()) {
      switch (entry.getKey()) {
        case "name":
          bookView.setName(entry.getValue().toString());
          break;
        case "author":
          bookView.setAuthor(entry.getValue().toString());
          break;
        case "published_date":
          bookView.setPublishedDate(entry.getValue().toString());
          break;
        case "id":
          bookView.setId(entry.getValue().toString());
          break;
      }
    }
    return bookView;
  }
}
