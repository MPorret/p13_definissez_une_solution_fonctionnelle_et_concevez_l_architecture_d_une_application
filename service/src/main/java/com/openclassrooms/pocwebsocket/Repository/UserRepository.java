package com.openclassrooms.pocwebsocket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.openclassrooms.pocwebsocket.Model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
