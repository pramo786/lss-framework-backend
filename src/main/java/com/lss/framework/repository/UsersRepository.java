package com.lss.framework.repository;

import com.lss.framework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findByUserName(String userName);
}
