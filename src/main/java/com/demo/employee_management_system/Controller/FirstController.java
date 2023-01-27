package com.demo.employee_management_system.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.employee_management_system.DataModel.emp;
import com.demo.employee_management_system.Services.EmpService;

// import com.demo.employee_management_system.DataModel.EmpRepository;
// import com.demo.employee_management_system.DataModel.emp;

@RestController
@RequestMapping("/api")
@EnableMethodSecurity
public class FirstController {

    private Logger logger = LoggerFactory.getLogger(FirstController.class);

    @Autowired
    private EmpService empservice;

    @GetMapping("/dummy")
    public String sri() {
        return "test";
    }

    @PreAuthorize("hasAuthority('manager')")
    @GetMapping("/Employs")
    public List<emp> getemploys() {
        return empservice.getAllEmployees();
    }

    @PreAuthorize("hasAuthority('manager')")
    @PostMapping("/addEmp")
    public emp addEmp(@RequestBody emp Newemploy) {
        logger.info(Newemploy.toString());
        return empservice.AddEmploy(Newemploy);

    }

    @PreAuthorize("hasAnyAuthority('developer','manager')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployee(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(empservice.get(id), HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("no such element found", HttpStatus.NOT_FOUND);

            // // TODO: handle exception
        }

    }

    @PreAuthorize("hasAnyAuthority('manger', 'developer')")
    @PatchMapping("/update")
    public emp change(@RequestBody emp updateEmp) {
        return empservice.modify(updateEmp);
    }

    @PreAuthorize("hasAutority('manager')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> reomoveEmp(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(empservice.delete(id), HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("Data not found, cannot delete", HttpStatus.NOT_IMPLEMENTED);

        }

    }

}