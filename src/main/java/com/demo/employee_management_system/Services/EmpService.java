package com.demo.employee_management_system.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.employee_management_system.DataModel.EmpRepository;
import com.demo.employee_management_system.DataModel.emp;

@Service
public class EmpService {
    @Autowired
    private EmpRepository empRepository;

    public List<emp> getAllEmployees() {
        return (List<emp>) empRepository.findAll();
    }

    public emp AddEmploy(emp newemploy) {
        return empRepository.save(newemploy);
    }

    public emp get(Integer id) {
        return empRepository.findById(id).get();
    }

    public emp modify(emp newemploy) {
        return empRepository.save(newemploy);
    }

    public emp delete(Integer id) {
        empRepository.deleteById(id);
        return null;

    }

    public emp getuser(String username) {
        return empRepository.findByUserName(username);
    }

    // public boolean getname()

}
