package com.prakhar.blogapp.payloads;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private Integer id;
    @NotEmpty
    private String title;
    @NotEmpty
    @Size(min = 10)
    private String content;
    private Date createdAt;
    private String imageUrl;
    private UserDto user;
    private CategoryDto category;
}
