package nl.han.oose;

public class UserToken {
    private String token;
    private String user;

    public UserToken() {
    }

    public UserToken(String token, String user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
