package com.brokenroseband.voyage.controller;

import com.brokenroseband.voyage.entity.Tour;
import com.brokenroseband.voyage.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер администратора
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private TourService tourService;

    @PostMapping("/tours")
    Tour addTour(@RequestBody Tour tour) {
        return tourService.addTour(tour);
    }
    @PutMapping("/tours/{id}")
    Tour updateTour(@PathVariable long id, @RequestBody Tour tour) {
        return tourService.addTour(tour);
    }
    @DeleteMapping("/tours/{id}")
    ResponseEntity deleteTour(@PathVariable long id) {
        return tourService.deleteTour(id);
    }
}
