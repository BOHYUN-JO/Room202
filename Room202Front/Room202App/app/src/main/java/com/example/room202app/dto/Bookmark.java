package com.example.room202app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//북마크

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bookmark {
    private Long bookmarkId;
    private Long userId;
    private Long articleId;
}
