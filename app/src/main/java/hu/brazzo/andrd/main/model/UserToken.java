package hu.brazzo.andrd.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class UserToken implements Parcelable {

    @Expose(serialize = true, deserialize = true)
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
    }

    public UserToken() {
    }

    protected UserToken(Parcel in) {
        this.token = in.readString();
    }

    public static final Creator<UserToken> CREATOR = new Creator<UserToken>() {
        @Override
        public UserToken createFromParcel(Parcel source) {
            return new UserToken(source);
        }

        @Override
        public UserToken[] newArray(int size) {
            return new UserToken[size];
        }
    };

    // Decodes UserToken json into business model object
    public static UserToken fromJson(String jsonString) {
        final GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        final Gson gson = builder.create();
        return gson.fromJson(jsonString, UserToken.class);
    }

    public static String toJson(UserToken userToken) {
        final GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        final Gson gson = builder.create();
        return gson.toJson(userToken);
    }
}
