package org.ikrotsyuk.bsuir.firstservice.dto.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponseDTO {
    @Min(0)
    private Long id;
    @Min(0)
    private Long writerId;
    @NotBlank
    @Size(min = 2, max = 64)
    private String title;
    @NotBlank
    @Size(min = 4, max = 2048)
    private String content;
}
