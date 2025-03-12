package org.ikrotsyuk.bsuir.firstservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReactionRequestDTO {
    @Min(0)
    private Long articleId;
    @NotBlank
    @Size(min = 2, max = 2048)
    private String content;
}
