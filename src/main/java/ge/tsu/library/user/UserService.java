package ge.tsu.library.user;

import java.util.List;

public interface UserService {

  UserView getUser(String userName, String password);

  UserView getUserById(int id);

  List<UserView> getUsers();

  void registerUser(String userName, String password, String email);

  void deleteUser(Integer userId);
  void updateUser(String userId, String name, String email, String isAdminUser);
}
