package com.esfandsoft.sysc4806project.objects;

/**
 * Data sent to the server when a user tries to log in
 */
public class UserLoginDto {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
