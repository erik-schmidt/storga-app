package com.group3.backend.controller;

import com.group3.backend.model.Course;
import com.group3.backend.model.Link;
import com.group3.backend.service.LinkCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/link")
@CrossOrigin
public class LinkCollectionController {

    private LinkCollectionService linkCollectionService;
    private AccessChecker accessChecker;

    @Autowired
    public LinkCollectionController(LinkCollectionService linkCollectionService, AccessChecker accessChecker){
        this.linkCollectionService = linkCollectionService;
        this.accessChecker = accessChecker;
    }

    /**
     * ping()
     * return a String with a successful message if backend reachable
     * @return String "Test successful"
     */
    @GetMapping("/ping")
    public String ping(){
        return linkCollectionService.ping();
    }

    @GetMapping("/get/{matrNr}")
    public ResponseEntity<?> getLinkListByStdId(@PathVariable(value = "matrNr")String matrNr, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return linkCollectionService.getLinkListByMatrNr(matrNr);
    }

    @GetMapping("/get/{matrNr}/{linkNr}")
    public ResponseEntity<?> getLinkListByStdIdAndNr(@PathVariable(value = "matrNr")String matrNr, @PathVariable(value = "linkNr") int linkNr, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return linkCollectionService.getLinkListByMatrNrAndNr(matrNr, linkNr);
    }

    @PutMapping("/add/{matrNr}")
    public ResponseEntity<?> addLinkToStudent(@PathVariable(value = "matrNr") String matrNr, @RequestBody Link link, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return linkCollectionService.addLinkToStudent(matrNr, link);
    }

    @DeleteMapping("/delete/{matrNr}/{linkId}")
    public ResponseEntity<?> deleteLink(@PathVariable(value = "matrNr")String matrNr,@PathVariable(value = "linkId") int linkId, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return linkCollectionService.deleteLink(matrNr, linkId);
    }

    @PutMapping("/put/{matrNr}/{linkId}")
    public ResponseEntity<?> changeLink(@PathVariable(value = "matrNr")String matrNr, @PathVariable(value = "linkId") int linkId, @RequestBody Link link, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return linkCollectionService.changeLink(matrNr, linkId, link);
    }
}
