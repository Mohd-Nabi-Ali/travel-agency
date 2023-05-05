package com.brokenroseband.voyage.repository;

import com.brokenroseband.voyage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Поиск пользователя по имени
     * @param  username имя пользователя
     * @return пользователь
     */
    User findByUsername(String username);

    /**
     * Поиск пользователя по почте
     * @param email почта
     * @return пользователь
     */
    User findByEmail(String email);
}
