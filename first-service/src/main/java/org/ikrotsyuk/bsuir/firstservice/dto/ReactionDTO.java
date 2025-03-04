package org.ikrotsyuk.bsuir.firstservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactionDTO {
    @NotNull
    private Long articleId;
    @NotNull
    @Size(min = 2, max = 2048)
    private String content;
}
