package com.demo.employee_management_system.Controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.employee_management_system.DataModel.emp;
import com.demo.employee_management_system.Services.EmpService;
import com.demo.employee_management_system.Util.jwtTokenUtil;

@RestController
@RequestMapping("/public")
public class AuthenticationController {

    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private EmpService empservice;

    @Autowired
    private jwtTokenUtil jwtToken;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Map<String, String> body) {
        logger.info(body.toString());

        String username = body.get("username");
        String password = body.get("password");

        emp user = empservice.getuser(username);

        try {
            if (user.getPassword().equals(password)) {
                return new ResponseEntity<>(jwtToken.generateAccessToken(user), HttpStatus.ACCEPTED);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("User not found", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("Wrong password", HttpStatus.FORBIDDEN);

    }
}
