package com.group3.backend.service;

import com.group3.backend.exceptions.CheckMatrNrClass;
import com.group3.backend.model.Student;
import com.group3.backend.model.TimeTableObject;
import com.group3.backend.repository.TimeTableObjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TimeTableObjectService extends CheckMatrNrClass {

    private TimeTableObjectRepository timeTableObjectRepository;
    private StudentService studentService;
    private Logger logger = LoggerFactory.getLogger(TimeTableObject.class);

    @Autowired
    public TimeTableObjectService(TimeTableObjectRepository timeTableObjectRepository, StudentService studentService){
        this.timeTableObjectRepository = timeTableObjectRepository;
        this.studentService = studentService;
    }

    /**
     * reachabilityTest()
     * return a String with a successful message if backend reachable
     * @return String "reachable"
     */
    public String ping(){
        return "reachable";
    }

    // TODO: 29.05.2020 doc and test   exceptions
    public ResponseEntity getAllTimeTableObjectsByFieldOfStudyAndSemester(String matrNr){
        try{
            checkMatriculationNumber(matrNr);
            Student student = (Student)studentService.getStudentByNumber(matrNr).getBody();
            String fieldOfStudyAndSemester = student.getFieldOfStudy() +""+ student.getCurrentSemester();
            List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByFieldOfStudySemester(fieldOfStudyAndSemester);
            return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // TODO: 29.05.2020 doc and test  exceptions
    public ResponseEntity getAllTimeTableObjectsByStartTime(LocalDate date){
        try{
            List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDate(date);
            return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // TODO: 29.05.2020 doc and test   exceptions
    public ResponseEntity getAllTimeTableObjectsByStartEndTime(LocalDateTime startDate, LocalDateTime endDate){
        try{
            //List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByStartTimeDateAndFinishTimeDateIsBetween(startDate, endDate);
            //return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            return null;
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // TODO: 29.05.2020 doc and test   exceptions
    public ResponseEntity getAllTimeTableObjectsByCourseNumber(String courseNumber){
        try{
            List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByCourseNumber(courseNumber);
            return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
