package edu.uob.constructionmanagementsimulation.controller;

import edu.uob.constructionmanagementsimulation.entity.Instructor;
import edu.uob.constructionmanagementsimulation.mapper.InstructorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.server.ResponseStatusException;

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

        Instructor existingInstructor = instructorMapper.findByEmail(instructor.getEmail());
        if (existingInstructor != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already taken!");
        }

        instructor.setPassword_hash(BCrypt.hashpw(instructor.getPassword_hash(), BCrypt.gensalt()));
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

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody Instructor instructor) {
        String hashedPassword = instructorMapper.findPasswordByEmail(instructor.getEmail());

        if (hashedPassword != null && BCrypt.checkpw(instructor.getPassword_hash(), hashedPassword)) {
            return ResponseEntity.ok(instructorMapper.findInstructorIdByEmail(instructor.getEmail()));
        } else {
            return ResponseEntity.status(400).body("Invalid email or password");
        }

        /*
        * Unknown error here. An Instructor instance should have been found by the email in the database.
        * However, Mybatis failed to map the Instructor object. So only the id and password_hash is queried but not the whole Instructor object.
        */
    }
}
