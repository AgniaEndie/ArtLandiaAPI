package ru.endienasg.artlandia.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.endienasg.artlandia.models.entities.User;


public interface IUserRepository extends JpaRepository<User, String> {
    User save(User user);


    User findByUsername(String username);
}
