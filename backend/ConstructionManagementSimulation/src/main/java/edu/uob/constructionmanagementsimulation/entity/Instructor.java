package edu.uob.constructionmanagementsimulation.entity;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Instructor {
    private Integer id;
    private String email;
    private String password_hash;
}
