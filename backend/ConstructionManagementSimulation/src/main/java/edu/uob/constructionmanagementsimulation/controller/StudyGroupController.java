package edu.uob.constructionmanagementsimulation.controller;

import edu.uob.constructionmanagementsimulation.entity.Seminar;
import edu.uob.constructionmanagementsimulation.entity.StudyGroup;
import edu.uob.constructionmanagementsimulation.mapper.InstructorMapper;
import edu.uob.constructionmanagementsimulation.mapper.StudyGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @GetMapping
    public List<StudyGroup> findAllBySeminar(@PathVariable("seminarId") Integer seminarId) {
        return studyGroupMapper.findAllBySeminar(seminarId);
    }
}
