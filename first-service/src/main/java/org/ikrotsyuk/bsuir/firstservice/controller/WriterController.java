package org.ikrotsyuk.bsuir.firstservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1.0/writers")
public class WriterController {
    @GetMapping
    public ResponseEntity<?> getWriters(){
        return ResponseEntity.ok(Collections.EMPTY_LIST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWriter(@PathVariable int id){
        return ResponseEntity.ok("ok");
    }


}
