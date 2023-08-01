package edu.uob.constructionmanagementsimulation.entity;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
public class Seminar {
    private Integer id;
    private String title;
    private Integer instructor_id;

    public Seminar(Integer id, String title, Integer instructor_id) {
        this.id = id;
        this.title = title;
        this.instructor_id = instructor_id;
    }
}


