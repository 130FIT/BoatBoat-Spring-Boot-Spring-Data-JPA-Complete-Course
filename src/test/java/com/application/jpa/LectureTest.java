package com.application.jpa;

import com.application.jpa.entities.Lecture;
import com.application.jpa.repositories.LectureRepository;
import com.application.jpa.repositories.ResourceRepository;
import com.application.jpa.repositories.SectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class LectureTest {
    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @BeforeEach
    public void setUp() {
        lectureRepository.deleteAll();
        sectionRepository.deleteAll();
        resourceRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testCreateLecture() {
        Lecture lecture = Lecture.builder()
                .name("Lecture Title")
                .build();
        lectureRepository.save(lecture);
        assertEquals(1, lectureRepository.count());
    }
}
