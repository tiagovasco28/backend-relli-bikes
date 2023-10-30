package com.backend.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.backend.products.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
    
    UserDetails findByLogin(String login);
}
