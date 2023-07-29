package edu.uob.constructionmanagementsimulation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("edu.uob.constructionmanagementsimulation.mapper")
public class ConstructionManagementSimulationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConstructionManagementSimulationApplication.class, args);
    }

}
