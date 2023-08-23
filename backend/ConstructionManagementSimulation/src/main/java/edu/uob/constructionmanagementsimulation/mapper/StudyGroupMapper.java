package edu.uob.constructionmanagementsimulation.mapper;

import edu.uob.constructionmanagementsimulation.entity.StudyGroup;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudyGroupMapper {
    @Select("SELECT * FROM studygroups WHERE id = #{id}")
    StudyGroup findById(@Param("id") Integer id);

    @Insert("INSERT INTO studygroups(name, password_hash, seminar_id, height, expenditure) VALUES(#{name}, #{password_hash}, #{seminar_id}, #{height}, #{expenditure})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(StudyGroup studyGroup);

    @Update("UPDATE studygroups SET name = #{name}, password_hash = #{password_hash}, seminar_id = #{seminar_id} WHERE id = #{id}")
    int update(StudyGroup studyGroup);

    @Update("UPDATE studygroups SET height = #{height}, expenditure = #{expenditure}")
    int updateResult(@Param("height") Integer height, @Param("expenditure") Integer expenditure);

    @Delete("DELETE FROM studygroups WHERE id = #{id}")
    int delete(@Param("id") Integer id);

    @Select("SELECT * FROM studygroups WHERE seminar_id = #{seminar_id}")
    List<StudyGroup> findAllBySeminar(Integer seminar_id);

    @Select("SELECT * FROM studygroups")
    List<StudyGroup> findAll();
}
