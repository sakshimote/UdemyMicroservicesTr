package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.WikimediaData;

public interface WikimediaRepository extends JpaRepository<WikimediaData, Long> {

}
