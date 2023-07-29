package edu.uob.constructionmanagementsimulation.mapper;

import edu.uob.constructionmanagementsimulation.entity.StudyGroup;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudyGroupMapper {
    @Select("SELECT * FROM StudyGroups WHERE id = #{id}")
    StudyGroup findById(@Param("id") Integer id);

    @Insert("INSERT INTO StudyGroups(name, password_hash, seminar_id) VALUES(#{name}, #{passwordHash}, #{seminarId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(StudyGroup studyGroup);

    @Update("UPDATE StudyGroups SET name = #{name}, password_hash = #{passwordHash}, seminar_id = #{seminarId} WHERE id = #{id}")
    int update(StudyGroup studyGroup);

    @Delete("DELETE FROM StudyGroups WHERE id = #{id}")
    int delete(@Param("id") Integer id);

    @Select("SELECT * FROM StudyGroup WHERE seminarId = #{seminarId}")
    List<StudyGroup> findAllBySeminar(Integer seminarId);

    @Select("SELECT * FROM StudyGroups")
    List<StudyGroup> findAll();
}
