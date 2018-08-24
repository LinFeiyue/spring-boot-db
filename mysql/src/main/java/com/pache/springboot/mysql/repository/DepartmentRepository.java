package com.pache.springboot.mysql.repository;

import com.pache.springboot.mysql.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2018-8-23.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long>{
}
