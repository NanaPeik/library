package ge.tsu.library;

public interface UserService {

  UserView getUser(String userName, String password);

  void registerUser(String userName, String password, String email);
}
