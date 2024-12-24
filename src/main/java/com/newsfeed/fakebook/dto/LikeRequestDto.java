package com.newsfeed.fakebook.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class LikeRequestDto {

    private Long likeid;
    private Long userid;
    private Long feedid;

    public LikeRequestDto (Long likeid) {
        this.likeid = likeid;
    }
}


