package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 自定義的查詢方法
    List<User> findByUsername(String username);

    // 更進一步的自定義查詢方法
    List<User> findByUsernameAndEmail(String username, String email);

    // 通過使用者名稱模糊查詢
    List<User> findByUsernameLike(String usernamePattern);

    // 查詢使用者名稱開頭為指定前綴的使用者
    List<User> findByUsernameStartingWith(String prefix);

    // 自定義的更新操作
    // 注意：這不是查詢方法，但是可以在接口中定義更新操作
    int updateUsernameByEmail(String username, String email);

}
