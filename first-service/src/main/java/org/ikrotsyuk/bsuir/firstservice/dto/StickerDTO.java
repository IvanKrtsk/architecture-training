package org.ikrotsyuk.bsuir.firstservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StickerDTO {
    @NotNull
    @Size(min = 2, max = 32)
    private String name;
}
