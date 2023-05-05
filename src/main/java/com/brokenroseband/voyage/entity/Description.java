package com.brokenroseband.voyage.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Сущность Описание тура
 */
@Entity
@Table(name = "descriptions")
@Getter
@Setter
@ToString
public class Description implements Serializable {

    /**
     * Идентификатор описания
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Ссылка на изображения описания
     */
    private String img;

    /**
     * Описание
     */
    private String text;

    /**
     * Тур, обладающий данным описанием
     */
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Tour tour;
}