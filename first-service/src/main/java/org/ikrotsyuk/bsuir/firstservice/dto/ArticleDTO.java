package org.ikrotsyuk.bsuir.firstservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    @NotNull
    private Long writerId;
    @NotNull
    @Size(min = 2, max = 64)
    private String title;
    @NotNull
    @Size(min = 4, max = 2048)
    private String content;
}
