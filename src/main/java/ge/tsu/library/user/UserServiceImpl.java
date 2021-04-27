package ge.tsu.library.user;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public UserView getUser(String userName, String password) {
    List<UserView> userViewList = jdbcTemplate
      .queryForList(String.format(
        "select * from users.users where user_name='%s' and password='%s';", userName, password))
      .stream()
      .map(this::map)
      .collect(Collectors.toList());
    if (!userViewList.isEmpty()) {
      return userViewList.get(0);
    }
    return null;
  }

  @Override
  public UserView getUserById(int id) {
    List<UserView> userViewList = jdbcTemplate
      .queryForList(String.format(
        "select * from users.users where id='%s';", id))
      .stream()
      .map(this::map)
      .collect(Collectors.toList());
    if (!userViewList.isEmpty()) {
      return userViewList.get(0);
    }
    return null;
  }

  @Override
  public List<UserView> getUsers() {
    return jdbcTemplate
      .queryForList(String.format(
        "select * from users.users;"))
      .stream()
      .map(this::map)
      .collect(Collectors.toList());
  }

  @Override
  public void registerUser(String userName, String password, String email) {
    UserView userView = getUser(userName, password);
    if (userView == null) {
      jdbcTemplate.execute(String.format("insert into users.users(user_name,password,email)"
        + "values ('%s','%s','%s')", userName, password, email));
    }
  }

  @Override
  public void updateUser(String userId, String name, String email, String isAdminUser) {
    jdbcTemplate.execute(String.format("update users.users set user_name='%s',"
        + " email='%s', is_admin='%s' where id=%s;", name,
      email, Boolean.valueOf(isAdminUser),
      userId));
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
        case "is_admin":
          userView.setAdmin(entry.getValue().toString());
          break;
      }
    }
    return userView;
  }
}
