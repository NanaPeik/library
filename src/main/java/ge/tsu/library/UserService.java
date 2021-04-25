package ge.tsu.library;

import java.util.List;

public interface UserService {

  List<UserView> getUsers();

  UserView getUser(String userName, String password);

  void registerUser(String userName, String password, String email);
}
