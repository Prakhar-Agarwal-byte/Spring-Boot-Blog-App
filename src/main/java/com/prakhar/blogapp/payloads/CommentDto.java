package com.prakhar.blogapp.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private Integer id;
    @NotEmpty
    @Size(min = 5)
    private String content;
    private PostDto post;
    private UserDto user;
}
