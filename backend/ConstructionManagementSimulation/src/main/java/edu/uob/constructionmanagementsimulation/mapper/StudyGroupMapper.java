package edu.uob.constructionmanagementsimulation.mapper;

import edu.uob.constructionmanagementsimulation.entity.StudyGroup;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudyGroupMapper {
    @Select("SELECT * FROM studygroups WHERE id = #{id}")
    StudyGroup findById(@Param("id") Integer id);

    @Insert("INSERT INTO studygroups(name, password_hash, seminar_id) VALUES(#{name}, #{password_hash}, #{seminar_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(StudyGroup studyGroup);

    @Update("UPDATE studygroups SET name = #{name}, password_hash = #{password_hash}, seminar_id = #{seminar_id} WHERE id = #{id}")
    int update(StudyGroup studyGroup);

    @Delete("DELETE FROM studygroups WHERE id = #{id}")
    int delete(@Param("id") Integer id);

    @Select("SELECT * FROM studygroups WHERE seminar_id = #{seminar_id}")
    List<StudyGroup> findAllBySeminar(Integer seminar_id);

    @Select("SELECT * FROM studygroups")
    List<StudyGroup> findAll();
}
