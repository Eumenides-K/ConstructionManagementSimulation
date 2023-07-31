package edu.uob.constructionmanagementsimulation.entity;

import lombok.Data;

@Data
public class Instructor {
    private Integer id;
    private String email;
    private String password_hash;

    public Instructor(String email, String password_hash) {
        this.email = email;
        this.password_hash = password_hash;
    }

    public String toString() {
        return "email: " + getEmail() + "\npassword_hash: " + getPassword_hash();
    }
}


