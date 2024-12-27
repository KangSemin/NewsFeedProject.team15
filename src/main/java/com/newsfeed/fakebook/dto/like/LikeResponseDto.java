package com.newsfeed.fakebook.dto.like;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class LikeResponseDto {

    private String username;

    private String profileImage;

    public LikeResponseDto (String username, String profileImage) {
            this.username = username;
            this.profileImage = profileImage;
    }

}
