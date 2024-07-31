package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    // 檢查用戶名是否存在
    boolean existsByUsername(String username);

    // 根據用戶名查找用戶
    User findByUsername(String username);
}
