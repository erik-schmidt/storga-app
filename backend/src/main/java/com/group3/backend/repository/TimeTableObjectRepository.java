package com.group3.backend.repository;

import com.group3.backend.model.TimeTableObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimeTableObjectRepository extends JpaRepository<TimeTableObject, String>{
    List<TimeTableObject> findAllByDate(LocalDate date);
    List<TimeTableObject> findAllByFieldOfStudySemester(String courseAndSemester);
    List<TimeTableObject> findAllByCourseNumber(String courseNumber);
}
