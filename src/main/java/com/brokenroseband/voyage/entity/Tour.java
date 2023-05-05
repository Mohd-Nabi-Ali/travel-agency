package com.brokenroseband.voyage.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Сущность Тур
 */
@Entity
@Table(name = "tours")
@Data
@ToString
public class Tour implements Serializable {
    /**
     * Идентификатор тура
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Место начала
     */
    private String start;

    /**
     * Место окончания
     */
    private String finish;

    /**
     * Цена тура
     */
    private int price;

    /**
     * Дата тура
     */
    private String date;
    /**
     * Количество туров в наличии
     */
    private int count;

    /**
     * Описание тура
     */
    @OneToOne(mappedBy = "tour",cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JsonIgnoreProperties("tour")
    private Description description;

    /**
     * Пользователи, обладающие путевкой
     */
    @ManyToMany(mappedBy = "tours",cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<User> users;
}

