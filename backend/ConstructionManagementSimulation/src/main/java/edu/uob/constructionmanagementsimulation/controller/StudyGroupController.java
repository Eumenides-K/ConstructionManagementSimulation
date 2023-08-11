package edu.uob.constructionmanagementsimulation.controller;

import edu.uob.constructionmanagementsimulation.entity.StudyGroup;
import edu.uob.constructionmanagementsimulation.mapper.StudyGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/groups")
public class StudyGroupController {
    @Autowired
    private final StudyGroupMapper studyGroupMapper;

    public StudyGroupController(StudyGroupMapper studyGroupMapper) {
        this.studyGroupMapper = studyGroupMapper;
    }

    @GetMapping("/{id}")
    public StudyGroup findById(@PathVariable("id") Integer id) {
        return studyGroupMapper.findById(id);
    }

    @PostMapping
    public void insert(@RequestBody StudyGroup studyGroup) {
        studyGroupMapper.insert(studyGroup);
    }

    @PutMapping
    public void update(@RequestBody StudyGroup studyGroup) {
        studyGroupMapper.update(studyGroup);
    }

    @DeleteMapping
    public void delete(@PathVariable("id") Integer id) {
        studyGroupMapper.delete(id);
    }

    @GetMapping("/seminar/{seminar_id}")
    public List<StudyGroup> findAllBySeminar(@PathVariable("seminar_id") Integer seminar_id) {
        return studyGroupMapper.findAllBySeminar(seminar_id);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody StudyGroup studyGroup) {
        StudyGroup foundStudyGroup = studyGroupMapper.findById(studyGroup.getId());

        if (foundStudyGroup != null && Objects.equals(foundStudyGroup.getPassword_hash(), studyGroup.getPassword_hash())) {
            return ResponseEntity.ok(foundStudyGroup.getId());
        } else {
            return ResponseEntity.status(400).body("Invalid email or password");
        }
    }
}
