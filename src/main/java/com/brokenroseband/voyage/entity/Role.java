package com.brokenroseband.voyage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Сущность Роль
 */
@Entity
@Data
@Table(name = "roles_voyage")
public class Role implements GrantedAuthority {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название
     */
    private String name;
    /**
     * Пользователи, укоторых есть данная роль
     */
    @Transient
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
    public Role() {
    }
    /**
     * Создание роли
     * @param id идентификатор
     */
    public Role(Long id) {
        this.id = id;
    }

    /**
     * Создание роли
     * @param id идентификатор
     * @param name название
     */
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return getName();
    }
}
