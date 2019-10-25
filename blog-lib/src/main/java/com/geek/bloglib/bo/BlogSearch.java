package com.geek.bloglib.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogSearch {
    private String title;

    private String typeId;

    private boolean recommend;
}
