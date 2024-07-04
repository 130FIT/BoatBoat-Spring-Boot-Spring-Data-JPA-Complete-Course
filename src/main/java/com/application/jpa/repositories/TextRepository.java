package com.application.jpa.repositories;

import com.application.jpa.entities.Text;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextRepository extends JpaRepository<Text, Integer> {
}
