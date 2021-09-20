package io.bootcamp.BootcampBackend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Integer>{
    @Query("SELECT p FROM User p WHERE p.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("DELETE FROM User s WHERE s.id = ?1")
    int deleteUserById(int id);
}
