package com.tcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.domain.Media;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
