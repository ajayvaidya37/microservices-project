package com.ajayvaidya.user.service.UserServiceMicroservice.repository;

import com.ajayvaidya.user.service.UserServiceMicroservice.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
}
