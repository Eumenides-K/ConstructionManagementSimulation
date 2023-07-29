package edu.uob.constructionmanagementsimulation.mapper;

import edu.uob.constructionmanagementsimulation.entity.Instructor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InstructorMapper {
    @Select("SELECT * FROM Instructors WHERE id = #{id}")
    Instructor findById(@Param("id") Integer id);

    @Insert("INSERT INTO Instructors(email, password_hash) VALUES(#{email}, #{password_hash})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Instructor instructor);

    @Update("UPDATE Instructors SET email = #{email}, password_hash = #{password_hash} WHERE id = #{id}")
    int update(Instructor instructor);

    @Delete("DELETE FROM Instructors WHERE id = #{id}")
    int delete(@Param("id") Integer id);

    @Select("SELECT * FROM Instructors")
    List<Instructor> findAll();

    @Select("SELECT * FROM instructors WHERE email = #{email}")
    Instructor findByEmail(String email);
}
