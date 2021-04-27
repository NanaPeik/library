package ge.tsu.library.user;

public class UserView {

  private String id;
  private String userName;
  private String password;
  private String email;
  private String isAdmin;

  public String isAdmin() {
    return isAdmin;
  }

  public void setAdmin(String admin) {
    isAdmin = admin;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
