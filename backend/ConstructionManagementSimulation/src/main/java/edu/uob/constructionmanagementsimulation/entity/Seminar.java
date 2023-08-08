package edu.uob.constructionmanagementsimulation.entity;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
public class Seminar {
    private Integer id;
    private String title;
    private Integer instructor_id;
    private Boolean start;
    // the number of week in which different unanticipated events happen
    private int scd, ls, sef, rc;

    public Seminar(Integer id, String title, Integer instructor_id) {
        this.id = id;
        this.title = title;
        this.instructor_id = instructor_id;
        this.start = false;
        this.scd = 0; this.ls = 0; this.sef =0; this.rc = 0;
    }
}


