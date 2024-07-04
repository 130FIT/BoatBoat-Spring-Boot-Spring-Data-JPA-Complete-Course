package com.application.jpa;

import com.application.jpa.entities.Section;
import com.application.jpa.repositories.CourseRepository;
import com.application.jpa.repositories.SectionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class SectionTest {
    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private CourseRepository courseRepository;

    @BeforeEach
    public void setUp() {
        sectionRepository.deleteAll();
        courseRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testCreateSection() {
        Section section = Section.builder()
                .name("Section Title")
                .sectionOrder("1")
                .build();
        sectionRepository.save(section);
        assertEquals(1, sectionRepository.count());
    }
}
