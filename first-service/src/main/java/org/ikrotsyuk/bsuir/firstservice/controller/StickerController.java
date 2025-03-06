package org.ikrotsyuk.bsuir.firstservice.controller;

import org.ikrotsyuk.bsuir.firstservice.service.StickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/stickers")
public class StickerController {
    private final StickerService stickerService;

    @Autowired
    public StickerController(StickerService stickerService){
        this.stickerService = stickerService;
    }
}
