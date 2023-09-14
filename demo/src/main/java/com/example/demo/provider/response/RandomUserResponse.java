package com.example.demo.provider.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RandomUserResponse {

    private ArrayList<User> results;

    public static class User {
        public String gender;
        public Name name;
        public String email;
        public Login login;
        public Picture picture;
    }

    public static class Login {
        public String username;
    }

    public static class Name {
        public String first;
        public String last;
    }

    public static class Picture {
        public String large;
    }

}




