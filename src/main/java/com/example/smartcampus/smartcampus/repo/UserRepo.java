package com.example.smartcampus.smartcampus.repo;

import com.example.smartcampus.smartcampus.entity.Role;
import com.example.smartcampus.smartcampus.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
     boolean existsByEmail(String email);
     Optional<User> findByEmail(String email);
     Page<User> findAllByRole(Role role, Pageable pageable);
     List<User> findAllByRole(Role role);
}
