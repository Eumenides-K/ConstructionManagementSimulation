package edu.uob.constructionmanagementsimulation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class StudyGroup {
    private Integer id;
    private String name;
    private String password_hash;
    private Integer seminar_id;

    public StudyGroup(Integer id, String name, String password_hash, Integer seminar_id) {
        this.id = id;
        this.name = name;
        this.password_hash = password_hash;
        this.seminar_id = seminar_id;
    }
}


