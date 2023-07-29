package edu.uob.constructionmanagementsimulation.controller;

import edu.uob.constructionmanagementsimulation.entity.Instructor;
import edu.uob.constructionmanagementsimulation.mapper.InstructorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
    @Autowired
    private final InstructorMapper instructorMapper;

    public InstructorController(InstructorMapper instructorMapper) {
        this.instructorMapper = instructorMapper;
    }

    @GetMapping("/{id}")
    public Instructor findById(@PathVariable("id") Integer id) {
        return instructorMapper.findById(id);
    }

    @PostMapping
    public void insert(@RequestBody Instructor instructor) {
        System.out.println("Received a request to insert an instructor with email: " + instructor.getEmail());

        // TODO: password hash
        instructorMapper.insert(instructor);
    }

    @PutMapping
    public void update(@RequestBody Instructor instructor) {
        instructorMapper.update(instructor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        instructorMapper.delete(id);
    }

    @GetMapping
    public List<Instructor> findAll() {
        return instructorMapper.findAll();
    }

//    @PostMapping("/login")
//    public Instructor login(@RequestBody Instructor instructor) {
//        Instructor existingInstructor = instructorMapper.findByEmail(instructor.getEmail());
//
//        if (existingInstructor != null && existingInstructor.getPassword().equals(instructor.getPassword())) {
//            return new ResponseEntity<>(existingInstructor, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
//        }
//    }
}
