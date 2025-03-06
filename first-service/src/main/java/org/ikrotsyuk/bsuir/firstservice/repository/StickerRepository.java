package org.ikrotsyuk.bsuir.firstservice.repository;

import org.ikrotsyuk.bsuir.firstservice.entity.StickerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StickerRepository extends JpaRepository<StickerEntity, Long> {
}
