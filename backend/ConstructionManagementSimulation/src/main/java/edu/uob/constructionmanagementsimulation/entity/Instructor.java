package edu.uob.constructionmanagementsimulation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Instructor {
    private Integer id;
    private String email;
    private String password_hash;

    public String toString() {
        return "email: " + getEmail() + "\npassword_hash: " + getPassword_hash();
    }
}


