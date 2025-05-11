package com.niit.interact.repository;

import com.niit.interact.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLoginName(String loginName);
}
