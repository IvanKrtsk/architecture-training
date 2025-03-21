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
public class WriterResponseDTO {
    @Min(0)
    private Long id;
    @NotBlank
    @Size(min = 2, max = 64)
    private String login;
    @NotBlank
    @Size(min = 8, max = 128)
    private String password;
    @NotBlank
    @Size(min = 2, max = 64)
    private String firstname;
    @NotBlank
    @Size(min = 2, max = 64)
    private String lastname;
}
