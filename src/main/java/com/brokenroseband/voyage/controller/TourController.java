package com.brokenroseband.voyage.controller;

import com.brokenroseband.voyage.entity.Tour;
import com.brokenroseband.voyage.entity.User;
import com.brokenroseband.voyage.service.TourService;
import com.brokenroseband.voyage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер, отвечающий за взаимодействие с турами
 */
@RestController
public class TourController {

    /**
     * Сервис работы с турами
     */
    @Autowired
    private TourService tourService;

    /**
     * Получение списка всех туров
     */
    @GetMapping("/api/tours")
    public List<Tour> getTours(){
        return tourService.getAll();
    }

    /**
     * Получение тура по идентификатору
     * @param tourId идентификатор тура
     * @return Тур
     */
    @GetMapping("/api/tours/{tourId}")
    public ResponseEntity<Tour> getTourById(@PathVariable Long tourId){
        if(tourService.findById(tourId)!=null){
            return new ResponseEntity(tourService.findById(tourId), HttpStatus.OK);
        }
        else{
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }
}
