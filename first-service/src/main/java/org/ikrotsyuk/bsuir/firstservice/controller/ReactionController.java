package org.ikrotsyuk.bsuir.firstservice.controller;

import org.ikrotsyuk.bsuir.firstservice.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/reactions")
public class ReactionController {
    private final ReactionService reactionService;

    @Autowired
    public ReactionController(ReactionService reactionService){
        this.reactionService = reactionService;
    }
}
