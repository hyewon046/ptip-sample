package com.example.ptipver1.repository;

import com.example.ptipver1.dto.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Optional<Notice> findByOriginId(String originId);
}
