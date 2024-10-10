//Giúp tương tác với JDBC
package com.devteria.identity_service.repository;

import org.hibernate.sql.model.jdbc.OptionalTableUpdateOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import com.devteria.identity_service.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
