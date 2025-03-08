package org.ikrotsyuk.bsuir.firstservice.repository;

import org.ikrotsyuk.bsuir.firstservice.entity.WriterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterRepository extends JpaRepository<WriterEntity, Long> {
}
