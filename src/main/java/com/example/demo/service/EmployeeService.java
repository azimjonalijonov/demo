package com.example.demo.service;

import com.example.demo.Entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }
    public Employee save(Employee employee){
        return employeeRepository.save(employee);
        

    }
    public  Employee GetbyId(Long id ){
      Optional<Employee> employee = employeeRepository.findById(id);
      if(employee.isPresent()){
        return employee.get() ;}
      return  null;
    }
    public List<Employee> findAlll(String name,String email){
       List<Employee> employees = employeeRepository.findAllByNameAndEmail(name,email);
       return  employees;
    }
    public List<Employee> find(String name, String lastname){
        List<Employee> employees = employeeRepository.findAl(name,lastname);
        return employees;
    }
    public List<Employee> findAllByNameLike(String name){
        List <Employee> res = employeeRepository.findAllByNameStartingWithOrderByIdDesc(name);
         return res;
    }
public void delete (Long id){
        employeeRepository.deleteById(id);
}

}
