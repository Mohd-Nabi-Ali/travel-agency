package com.brokenroseband.voyage.controller;

import com.brokenroseband.voyage.entity.Tour;
import com.brokenroseband.voyage.entity.User;
import com.brokenroseband.voyage.repository.UserRepository;
import com.brokenroseband.voyage.service.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер клиента
 */
@RestController
@RequestMapping("/api/user")
public class ClientController {

    /**
     * Сервис работы с пользователями
     */
    @Autowired
    UserService userService;

    /**
     * Получение информации о текущем пользователе
     * @return Пользователь
     */
    @GetMapping("/info")
    public User getUserInfo(){
        return userService.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }
    @PostMapping("/info")
    public User updateUserInfo(@RequestBody User user){
        return userService.updateUser(user);
    }
    /**
     * Бронирование тура
     * @param tour Бронируемый тур
     * @return пользователь
     */
    @PostMapping("/booking")
    public ResponseEntity<User> bookTour(@RequestBody  Tour tour){
        return userService.bookTour(tour);
    }
}
