package com.upc.api_examen_parcial_202120796.respositories;

import com.upc.api_examen_parcial_202120796.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByGcUsername(String gcUsername);
}
