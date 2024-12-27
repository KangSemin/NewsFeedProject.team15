package com.newsfeed.fakebook.dto.like;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class LikeRequestDto {

    private Long userid;
    private Long feedid;

}


