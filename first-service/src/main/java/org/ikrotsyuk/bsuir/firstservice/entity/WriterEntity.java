package org.ikrotsyuk.bsuir.firstservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "writer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WriterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private List<ArticleEntity> articles = new ArrayList<>();
}
