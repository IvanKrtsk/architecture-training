package org.ikrotsyuk.bsuir.firstservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WriterDTO {
    @NotNull
    @Size(min = 2, max = 64)
    private String login;
    @NotNull
    @Size(min = 8, max = 128)
    private String password;
    @NotNull
    @Size(min = 2, max = 64)
    private String firstname;
    @NotNull
    @Size(min = 2, max = 64)
    private String lastname;
}
