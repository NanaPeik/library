package ge.tsu.library;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService{

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<UserView> getUsers() {
    return jdbcTemplate
        .queryForList("select * from book.user")
        .stream()
        .map(this::map)
        .collect(Collectors.toList());
  }

  @Override
  public UserView getUser(String userName, String password) {
    return jdbcTemplate.queryForObject(String.format(""
        + "select * from users.users where user_name='%s' and password='%s';",userName,password),UserView.class);
  }

  @Override
  public void registerUser(String userName, String password, String email) {
    jdbcTemplate.execute("insert into user.user(id,user_name,password,email)"
        + "values (UUID.randomUUID().toString(),userName,password,email)");
  }

  private UserView map(Map<String, Object> map) {
    UserView userView = new UserView();
    for (Map.Entry<String, Object> entry : map.entrySet()) {
      switch (entry.getKey()) {
        case "id":
          userView.setId(entry.getValue().toString());
          break;
        case "user_name":
          userView.setUserName(entry.getValue().toString());
          break;
        case "email":
          userView.setEmail(entry.getValue().toString());
          break;
        case "password":
          userView.setPassword(entry.getValue().toString());
          break;
      }
    }
    return userView;
  }
}
