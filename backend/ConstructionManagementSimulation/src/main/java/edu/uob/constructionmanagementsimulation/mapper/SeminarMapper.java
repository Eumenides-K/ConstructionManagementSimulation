package edu.uob.constructionmanagementsimulation.mapper;

import edu.uob.constructionmanagementsimulation.entity.Seminar;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SeminarMapper {
    @Select("SELECT * FROM Seminars WHERE id = #{id}")
    Seminar findById(@Param("id") Integer id);

    @Insert("INSERT INTO Seminars(title, instructor_id) VALUES(#{title}, #{instructorId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Seminar seminar);

    @Update("UPDATE Seminars SET title = #{title}, instructor_id = #{instructorId} WHERE id = #{id}")
    int update(Seminar seminar);

    @Delete("DELETE FROM Seminars WHERE id = #{id}")
    int delete(@Param("id") Integer id);

    @Select("SELECT * FROM Seminars WHERE instructorId = #{instructorId}")
    List<Seminar> findAllByInstructor(Integer instructorId);

    @Select("SELECT * FROM Seminars WHERE instructorId = #{instructorId}")
    List<Seminar> findAll(Integer instructorId);
}
