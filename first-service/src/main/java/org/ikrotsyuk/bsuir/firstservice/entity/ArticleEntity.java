package org.ikrotsyuk.bsuir.firstservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_article")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "writer_id")
    private WriterEntity writer;
    @NotBlank
    @Size(min = 2, max = 64)
    private String title;
    @NotBlank
    @Size(min = 4, max = 2048)
    private String content;
    @Column(name = "created")
    private LocalDateTime createdAt;
    @Column(name = "modified")
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<ReactionEntity> reactions = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tbl_articles_stickers",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "sticker_id")
    )
    private List<StickerEntity> stickers = new ArrayList<>();
}