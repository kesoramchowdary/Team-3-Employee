package com.demo.employee_management_system.DataModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employ")
public class emp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "emp_id")
    private int EmpId;

    @Column(name = "name")
    private String userName;

    // @Column(name = "email")
    private String Email;

    // @Column(name = "designation")
    private String Role;

    @Column(name = "mobile")
    private String MobileNo;

    // @Column(name = "pass")
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String Password;

    public int getEmpId() {
        return EmpId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return Email;
    }

    public String getRole() {
        return Role;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    @JsonIgnore
    public String getPassword() {
        return Password;
    }

    public emp() {
    }

    public void setEmpId(int empId) {
        EmpId = empId;
    }

    public void setUserName(String name) {
        userName = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setRole(String role) {
        Role = role;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    @JsonProperty
    public void setPassword(String password) {
        Password = password;
    }

    public emp(int empId, String name, String email, String role, String mobileNo, String password) {
        EmpId = empId;
        userName = name;
        Email = email;
        Role = role;
        MobileNo = mobileNo;
        Password = password;
    }

    // ObjectMapper mapper = new ObjectMapper();
    // mapper.disable(MapperFeature.USE_GETTERS_AS_SETTERS);

}
