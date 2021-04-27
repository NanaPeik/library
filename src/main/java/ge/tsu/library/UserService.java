package ge.tsu.library;

import java.util.List;

public interface UserService {

  UserView getUser(String userName, String password);

  UserView getUserById(int id);

  List<UserView> getUsers();

  void registerUser(String userName, String password, String email);
}
