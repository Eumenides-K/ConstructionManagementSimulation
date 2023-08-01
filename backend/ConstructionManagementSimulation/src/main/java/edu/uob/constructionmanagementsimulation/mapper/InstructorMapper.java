package edu.uob.constructionmanagementsimulation.mapper;

import edu.uob.constructionmanagementsimulation.entity.Instructor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InstructorMapper {
    @Select("SELECT * FROM instructors WHERE id = #{id}")
    Instructor findById(@Param("id") Integer id);

    @Insert("INSERT INTO instructors(email, password_hash) VALUES(#{email}, #{password_hash})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Instructor instructor);

    @Update("UPDATE instructors SET email = #{email}, password_hash = #{password_hash} WHERE id = #{id}")
    int update(Instructor instructor);

    @Delete("DELETE FROM instructors WHERE id = #{id}")
    int delete(@Param("id") Integer id);

    @Select("SELECT * FROM instructors")
    List<Instructor> findAll();

    @Select("SELECT id, email, password_hash FROM instructors WHERE email = #{email}")
    Instructor findByEmail(String email);

    @Select("SELECT password_hash FROM instructors WHERE email = #{email}")
    String findPasswordByEmail(String email);

    @Select("SELECT id FROM instructors WHERE email = #{email}")
    Integer findInstructorIdByEmail(String email);
}
