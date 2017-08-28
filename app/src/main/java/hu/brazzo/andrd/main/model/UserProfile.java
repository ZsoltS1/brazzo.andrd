package hu.brazzo.andrd.main.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@org.parceler.Parcel
public class UserProfile implements Parcelable {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("lastName")
    @Expose
    public String lastname;
    @SerializedName("firstName")
    @Expose
    public String firstname;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("userRoles")
    @Expose
    public List<Userroles> userroles;
    @SerializedName("expires")
    @Expose
    public String expires;
    @SerializedName("m2m")
    @Expose
    public String m2m;

    @org.parceler.Parcel
    public static class Userroles implements Parcelable {
        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("description")
        @Expose
        public String description;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.name);
            dest.writeString(this.description);
        }

        public Userroles() {
        }

        protected Userroles(Parcel in) {
            this.id = in.readInt();
            this.name = in.readString();
            this.description = in.readString();
        }

        public static final Creator<Userroles> CREATOR = new Creator<Userroles>() {
            @Override
            public Userroles createFromParcel(Parcel source) {
                return new Userroles(source);
            }

            @Override
            public Userroles[] newArray(int size) {
                return new Userroles[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.lastname);
        dest.writeString(this.firstname);
        dest.writeString(this.email);
        dest.writeList(this.userroles);
        dest.writeString(this.expires);
        dest.writeString(this.m2m);
    }

    public UserProfile() {
    }

    protected UserProfile(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.lastname = in.readString();
        this.firstname = in.readString();
        this.email = in.readString();
        this.userroles = new ArrayList<Userroles>();
        in.readList(this.userroles, Userroles.class.getClassLoader());
        this.expires = in.readString();
        this.m2m = in.readString();
    }

    public static final Creator<UserProfile> CREATOR = new Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel source) {
            return new UserProfile(source);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };

    // Decodes UserProfile json into business model object
    public static UserProfile fromJson(String jsonString) {
        final GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        final Gson gson = builder.create();
        return gson.fromJson(jsonString, UserProfile.class);
    }

    public static String toJson(UserProfile userProfile) {
        final GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        final Gson gson = builder.create();
        return gson.toJson(userProfile);
    }
}
