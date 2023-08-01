package edu.uob.constructionmanagementsimulation.controller;

import edu.uob.constructionmanagementsimulation.mapper.SeminarMapper;
import edu.uob.constructionmanagementsimulation.entity.Seminar;
import edu.uob.constructionmanagementsimulation.mapper.InstructorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seminars")
public class SeminarController {
    @Autowired
    private final SeminarMapper seminarMapper;

    public SeminarController(SeminarMapper seminarMapper) {
        this.seminarMapper = seminarMapper;
    }

    @GetMapping("/{id}")
    public Seminar findById(@PathVariable("id") Integer id) {
        return seminarMapper.findById(id);
    }

//    @PostMapping
//    public void insert(@RequestBody Seminar seminar) {
//        seminarMapper.insert(seminar);
//    }

    @PostMapping
    public ResponseEntity<Integer> insert(@RequestBody Seminar seminar) {
        int id = seminarMapper.insert(seminar);
        return ResponseEntity.ok(id);
    }

    @PutMapping
    public void update(@RequestBody Seminar seminar) {
        seminarMapper.update(seminar);
    }

    @DeleteMapping
    public void delete(@PathVariable("id") Integer id) {
        seminarMapper.delete(id);
    }

    @GetMapping("/instructor/{instructor_id}")
    public List<Seminar> findAllByInstructor(@PathVariable("instructor_id") Integer instructor_id) {
        System.out.println("Finding seminars...");
        return seminarMapper.findAllByInstructor(instructor_id);
    }
}
