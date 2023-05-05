package com.brokenroseband.voyage.repository;

import com.brokenroseband.voyage.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TourRepository extends JpaRepository<Tour,Long> {
    public List<Tour> findByCountGreaterThan(int count);
}