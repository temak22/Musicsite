package ru.mirea.musicsite.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.musicsite.security.entities.User;


public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findById(int id);
}
