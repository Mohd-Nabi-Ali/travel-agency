package com.brokenroseband.voyage.controller;

import com.brokenroseband.voyage.entity.User;
import com.brokenroseband.voyage.model.LoginInput;
import com.brokenroseband.voyage.service.UserService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Контроллер регистрации и авторизации
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    /**
     * Сервис работы с пользователями
     */
    @Autowired
    private UserService userService;

    /**
     * Регистраци пользователя
     * @param userForm Новый пользователь
     * @return результат создания
     */
    @PostMapping("/registration")
    public ResponseEntity<String> addUser(@RequestBody User userForm) {
        if (userForm.getUsername().equals("")||
                userForm.getFirstname().equals("")||
                userForm.getLastname().equals("")||
                userForm.getPassword().equals("")||
                userForm.getEmail().equals("")||
                userForm.getMiddlename().equals("")||
                userForm.getPassportdata().equals("")||
                userForm.getAge()==0||
                userForm.getPhone().equals("")){
            return new ResponseEntity(userForm.toString(), HttpStatus.BAD_REQUEST);
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            return new ResponseEntity("неправильный пароль", HttpStatus.BAD_REQUEST);
        }
        String answer = userService.saveUser(userForm);
        if (!answer.equals("")){
            return new ResponseEntity("Пользователь с такой почтой уже существует", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("success", HttpStatus.OK);
    }

    /**
     * Вход в аккаунт
     * @param loginInput почта и пароль пользователя
     * @return результат авторизации
     */
    @PostMapping("/login")
    ResponseEntity<String> auth(@RequestBody LoginInput loginInput) {
        return userService.login(loginInput);
    }
}
