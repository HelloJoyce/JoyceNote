package com.js.springbootstart.repository;

import com.js.springbootstart.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository //spring bean
public interface UserRepository extends CrudRepository<User, Integer> {


}
