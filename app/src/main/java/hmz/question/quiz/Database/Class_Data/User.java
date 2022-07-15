package hmz.question.quiz.Database.Class_Data;

public class User {
    private Integer Id;
    private String Username;
    private String Type;
    private String Email;
    private String Password;

    public User(Integer id, String username, String type, String email, String password) {
        Id = id;
        Username = username;
        Type = type;
        Email = email;
        Password = password;
    }

    public User(String username, String type, String email, String password) {
        Username = username;
        Type = type;
        Email = email;
        Password = password;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return   "Username : " + Username +
                "\nEmail : " + Email;
    }
}
