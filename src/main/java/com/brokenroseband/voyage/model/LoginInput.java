package com.brokenroseband.voyage.model;


public class LoginInput {

    /**
     * Почта
     */
    private String email;

    /**
     * Пароль
     */
    private String password;

    /**
     * Получение почты
     * @return Почта
     */
    public String getEmail() {
        return email;
    }

    /**
     * Установка нового пароля
     * @param email Почта
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Получение пароля
     * @return Пароль
     */
    public String getPassword() {
        return password;
    }

    /**
     * Установка нового пароля
     * @param password Пароль
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Конструктор
     * @param email Почта
     * @param password Пароль
     */
    public LoginInput(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
