package com.demo.employee_management_system.DataModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EmpRepository extends CrudRepository<emp, Integer> {

    emp findByUserName(String name);

}
