package org.ikrotsyuk.bsuir.firstservice.controller;

import jakarta.validation.Valid;
import org.ikrotsyuk.bsuir.firstservice.dto.request.WriterRequestDTO;
import org.ikrotsyuk.bsuir.firstservice.dto.response.WriterResponseDTO;
import org.ikrotsyuk.bsuir.firstservice.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1.0/writers")
public class WriterController {
    private final WriterService writerService;

    @Autowired
    public WriterController(WriterService writerService){
        this.writerService = writerService;
    }

    @GetMapping
    public ResponseEntity<List<WriterResponseDTO>> getWriters(){
        return new ResponseEntity<>(writerService.getWriters(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WriterResponseDTO> getWriter(@PathVariable long id){
        return new ResponseEntity<>(writerService.getWriterById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WriterResponseDTO> addWriter(@Valid @RequestBody WriterRequestDTO writerRequestDTO){
        return new ResponseEntity<>(writerService.addWriter(writerRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWriter(@PathVariable Long id){
        return new ResponseEntity<>(writerService.deleteWriter(id));
    }

    @PutMapping
    public ResponseEntity<WriterResponseDTO> updateWriter(@Valid @RequestBody WriterResponseDTO writerResponseDTO){
        System.err.println(writerResponseDTO.getLogin());
        HttpStatus status = writerService.updateWriter(writerResponseDTO);
        if(status == HttpStatus.OK)
            return new ResponseEntity<>(writerResponseDTO, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
