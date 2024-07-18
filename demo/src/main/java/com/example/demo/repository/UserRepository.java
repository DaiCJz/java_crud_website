package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    
    // 添加這個方法來檢查用戶名是否存在
    boolean existsByUsername(String username);

    // 如果需要根據用戶名查找用戶，可以添加這個方法
    User findByUsername(String username);
}