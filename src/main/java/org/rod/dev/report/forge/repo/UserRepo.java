package org.rod.dev.report.forge.repo;

import org.rod.dev.report.forge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
     @Query("""
        select distinct u
        from User u
        where u.email = :email
    """)
     Optional<User> findByEmail(@Param("email") String email);

     boolean existsByEmail(String email);
}
