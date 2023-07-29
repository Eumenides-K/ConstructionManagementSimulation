package edu.uob.constructionmanagementsimulation.entity;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Seminar {
    private Integer id;
    private String title;
    private Integer instructorId;
}
