package com.example.demo.repository;

import com.example.demo.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query( "select  e from Employee e where e.name=:name and e.last_name =:last_name")
    List<Employee> findAll(@Param("name") String name,
                           @Param("last_name")String last_name);

    List<Employee> findAllByNameAndEmail(String name,String email);
    @Query(value = "select * from   devstudent d where d.name = name and d.last_name =last_name ",nativeQuery = true)
    List<Employee> findAl(@Param("name") String name, @Param("last_name") String last_name);
    List<Employee> findAllByNameStartingWithOrderByIdDesc(String name);

    @Query("select e from  Employee e where upper( e.name) like upper(concat(:name,'%')) order by e.id desc ")
    List<Employee> findAllByNameL(@Param("name")String name);

    @Query(value = "select * from devstudent d where d.name like %:name% order by d.id desc",nativeQuery = true)
    List<Employee> findAllByNameLike(@Param("name") String name);
}
