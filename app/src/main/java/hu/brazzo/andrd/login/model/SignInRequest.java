package hu.brazzo.andrd.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SignInRequest implements Serializable {

    @SerializedName("username")
    @Expose(serialize = true, deserialize = true)
    private String username;

    @SerializedName("password")
    @Expose(serialize = true, deserialize = true)
    private String password;

    public SignInRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

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
