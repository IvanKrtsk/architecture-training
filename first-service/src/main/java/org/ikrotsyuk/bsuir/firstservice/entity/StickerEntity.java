package org.ikrotsyuk.bsuir.firstservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sticker")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StickerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 2, max = 32)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "stickers", fetch = FetchType.LAZY)
    private List<ArticleEntity> articles = new ArrayList<>();
}
