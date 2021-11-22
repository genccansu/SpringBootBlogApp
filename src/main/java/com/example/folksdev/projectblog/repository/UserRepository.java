package com.example.folksdev.projectblog.repository;

import com.example.folksdev.projectblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

    @Query(value="select u.displayName from User u where u.id=?1")
    Optional<String> findDisplayNameByUserId(String id);
}
