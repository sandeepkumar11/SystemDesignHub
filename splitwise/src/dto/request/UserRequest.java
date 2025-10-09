package dto.request;

public class UserRequest {
    private final String name;
    private final String email;
    private final String password;

    public UserRequest(String name, String email) {
        this.name = name;
        this.email = email;
        this.password = null;
    }

    public UserRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
