package edu.uob.constructionmanagementsimulation.controller;

import edu.uob.constructionmanagementsimulation.mapper.SeminarMapper;
import edu.uob.constructionmanagementsimulation.entity.Seminar;
import edu.uob.constructionmanagementsimulation.mapper.InstructorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seminars")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @PostMapping
    public void insert(@RequestBody Seminar seminar) {
        seminarMapper.insert(seminar);
    }

    @PutMapping
    public void update(@RequestBody Seminar seminar) {
        seminarMapper.update(seminar);
    }

    @DeleteMapping
    public void delete(@PathVariable("id") Integer id) {
        seminarMapper.delete(id);
    }

    @GetMapping
    public List<Seminar> findAllByInstructor(@PathVariable("instructorId") Integer instructorId) {
        return seminarMapper.findAllByInstructor(instructorId);
    }
}
