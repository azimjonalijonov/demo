package com.example.demo.rest;

import com.example.demo.Entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeResource {
    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping("/emp")
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        Employee res= employeeService.save(employee);
        return ResponseEntity.ok(res);
    }
    @PutMapping("/emp")
    public  ResponseEntity update(@RequestBody Employee employee){
        Employee res= employeeService.save(employee);
        return  ResponseEntity.ok(res);
    }

@GetMapping("/emp/{id}")
    public  ResponseEntity GetById(@PathVariable Long id){
        Employee e= employeeService.GetbyId(id);
        return ResponseEntity.ok(e);}
@GetMapping("/empl")
public ResponseEntity find(@RequestParam String name, @RequestParam String email){
        List<Employee> employees = employeeService.findAlll(name,email);
        return ResponseEntity.ok(employees);
    }
    @GetMapping("/e")
    public ResponseEntity find1(@RequestParam String name, @RequestParam String lastname){
        List<Employee>employees= employeeService.find(name,lastname);
        return ResponseEntity.ok(employees);
    }
@GetMapping("/elike")
public  ResponseEntity findLike(@RequestParam String name){
        List<Employee> fin = employeeService.findAllByNameLike(name);
        return ResponseEntity.ok(fin);
}



@DeleteMapping("/emp/{id}")
    public void delete(@PathVariable Long id){
        employeeService.delete(id);
}


}
