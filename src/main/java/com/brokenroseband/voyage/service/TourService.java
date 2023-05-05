package com.brokenroseband.voyage.service;


import com.brokenroseband.voyage.entity.Tour;
import com.brokenroseband.voyage.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class TourService {

    /**
     * Репозиторий работы с турами
     */
    @Autowired
    TourRepository tourRepository;

    /**
     * Получение всех туров
     * @return список туров
     */
    public List<Tour> getAll(){
        return tourRepository.findByCountGreaterThan(0);
    }

    /**
     * Поиск тура по идентификатору
     * @param id идентификатор
     * @return тур
     */
    public Tour findById(Long id){
        Optional<Tour> tour = tourRepository.findById(id);
        try {
            return tour.get();
        } catch (Exception e) {
            return null;
        }
    }

    public Tour addTour(Tour tour){
        tourRepository.save(tour);
        return tour;
    }
    public ResponseEntity deleteTour(Long id){
        Optional<Tour> tour = tourRepository.findById(id);
        if(tour.isPresent()) {
            tourRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
