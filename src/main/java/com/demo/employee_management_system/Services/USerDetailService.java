package com.demo.employee_management_system.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.employee_management_system.DataModel.EmpRepository;
import com.demo.employee_management_system.DataModel.emp;

@Service
public class USerDetailService implements UserDetailsService {
    @Autowired
    EmpRepository empRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        emp user = empRepo.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("user Not found");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new User(user.getUserName(), user.getPassword(), authorities);

    }
}
