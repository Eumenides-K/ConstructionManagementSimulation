package edu.uob.constructionmanagementsimulation.mapper;

import edu.uob.constructionmanagementsimulation.entity.Seminar;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SeminarMapper {
    @Select("SELECT * FROM seminars WHERE id = #{id}")
    Seminar findById(@Param("id") Integer id);

    @Insert("INSERT INTO seminars(title, instructor_id) VALUES(#{title}, #{instructor_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Seminar seminar);

    @Update("UPDATE seminars SET title = #{title}, instructor_id = #{instructor_id} WHERE id = #{id}")
    int update(Seminar seminar);

    @Delete("DELETE FROM seminars WHERE id = #{id}")
    int delete(@Param("id") Integer id);

    @Select("SELECT * FROM seminars WHERE instructor_id = #{instructor_id}")
    List<Seminar> findAllByInstructor(Integer instructorId);

    @Select("SELECT * FROM seminars WHERE instructorId = #{instructor_id}")
    List<Seminar> findAll(Integer instructorId);
}
