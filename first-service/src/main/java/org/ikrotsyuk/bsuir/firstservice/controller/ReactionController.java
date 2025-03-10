package org.ikrotsyuk.bsuir.firstservice.controller;

import jakarta.validation.Valid;
import org.ikrotsyuk.bsuir.firstservice.dto.request.ReactionRequestDTO;
import org.ikrotsyuk.bsuir.firstservice.dto.response.ReactionResponseDTO;
import org.ikrotsyuk.bsuir.firstservice.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1.0/reactions")
public class ReactionController {
    private final ReactionService reactionService;

    @Autowired
    public ReactionController(ReactionService reactionService){
        this.reactionService = reactionService;
    }

    @GetMapping
    public ResponseEntity<List<ReactionResponseDTO>> getReactions(){
        return new ResponseEntity<>(reactionService.getReactions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReactionResponseDTO> getReaction(@PathVariable Long id){
        return new ResponseEntity<>(reactionService.getReactionById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReactionResponseDTO> addReaction(@Valid @RequestBody ReactionRequestDTO reactionRequestDTO){
        return new ResponseEntity<>(reactionService.addReaction(reactionRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReaction(@PathVariable Long id){
        return new ResponseEntity<>(reactionService.deleteReaction(id));
    }

    @PutMapping
    public ResponseEntity<?> updateReaction(@Valid @RequestBody ReactionResponseDTO reactionResponseDTO){
        HttpStatus status = reactionService.updateReaction(reactionResponseDTO);
        if(status.is2xxSuccessful()){
            return new ResponseEntity<>(reactionResponseDTO, status);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
