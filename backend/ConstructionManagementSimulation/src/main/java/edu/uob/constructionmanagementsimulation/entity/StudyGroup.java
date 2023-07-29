package edu.uob.constructionmanagementsimulation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudyGroup {
    private Integer id;
    private String name;
    private String passwordHash;
    private Integer seminarId;
}
