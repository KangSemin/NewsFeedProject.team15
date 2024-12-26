package com.newsfeed.fakebook.controller;

import com.newsfeed.fakebook.dto.LikeRequestDto;
import com.newsfeed.fakebook.dto.LikeResponseDto;
import com.newsfeed.fakebook.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fakebook/feeds")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{id}/like")
    public ResponseEntity<Void> save(@PathVariable Long id,@RequestBody LikeRequestDto requestDto) {

                likeService.save(
                        requestDto.getLikeid(),
                        requestDto.getUserid(),
                        id
                );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

   @GetMapping("/{id}/like")
    public ResponseEntity<LikeResponseDto> findById(@PathVariable Long id) {
        LikeResponseDto likeResponseDto =
                likeService.findById(id);
        return new ResponseEntity<>(likeResponseDto, HttpStatus.OK);

//코드가 똑같음













}





}
