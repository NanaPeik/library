package ge.tsu.library;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LibraryServiceImpl implements LibraryService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<BookView> getBooks() throws SQLException {
    return jdbcTemplate
        .queryForList("select * from book.book")
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
          try {
            bookView.setPublishedDate(new SimpleDateFormat("yyyy-MM-dd").parse(entry.getValue().toString()));
          } catch (ParseException e) {
            e.printStackTrace();
          }
          break;
        case "id":
          bookView.setId(Integer.parseInt(entry.getValue().toString()));
          break;
        case "quantity":
          bookView.setQuantity(Integer.parseInt(entry.getValue().toString()));
          break;
        case "genre":
          bookView.setGenre(entry.getValue().toString());
          break;
      }
    }
    return bookView;
  }
}
