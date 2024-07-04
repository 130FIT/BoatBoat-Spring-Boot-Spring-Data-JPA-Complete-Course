package com.application.jpa;

import com.application.jpa.entities.Lecture;
import com.application.jpa.entities.Resource;
import com.application.jpa.entities.Section;
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

    @Test
    @Transactional
    public void testCreateLectureWithSection() {
        Section section = Section.builder()
                .name("Section Title")
                .sectionOrder("1")
                .build();
        sectionRepository.save(section);
        Lecture lecture = Lecture.builder()
                .name("Lecture Title")
                .section(section)
                .build();
        lectureRepository.save(lecture);
        assertEquals(1, lectureRepository.count());
        assertEquals(section, lectureRepository.findById(lecture.getId()).get().getSection());
    }

    @Test
    @Transactional
    public void testCreateLectureWithResource() {
        Lecture lecture = Lecture.builder()
                .name("Lecture Title")
                .build();

        Resource resource = Resource.builder()
                .name("Resource Title")
                .lecture(lecture)
                .build();
        lecture.setResource(resource);
        lectureRepository.save(lecture);
        resourceRepository.save(resource);
        assertEquals(1, resourceRepository.count());
        assertEquals(lecture, resourceRepository.findById(resource.getId()).get().getLecture());
        assertEquals(resource, lectureRepository.findById(lecture.getId()).get().getResource()) ;
    }
}
