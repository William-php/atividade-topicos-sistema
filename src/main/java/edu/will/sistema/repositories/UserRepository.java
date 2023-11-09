package edu.will.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.will.sistema.models.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
