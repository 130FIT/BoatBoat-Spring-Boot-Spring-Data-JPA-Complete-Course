package com.application.jpa;

import com.application.jpa.entities.Lecture;
import com.application.jpa.entities.Text;
import com.application.jpa.repositories.LectureRepository;
import com.application.jpa.repositories.TextRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class TextTest {
    @Autowired
    private TextRepository textRepository;
    @Autowired
    private LectureRepository lectureRepository;

    @BeforeEach
    public void setUp() {
        textRepository.deleteAll();
        lectureRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testTextCreatedShouldFoundOnDatabase() {
        Text text = Text.builder()
                .name("Text 1")
                .size(100)
                .content("Content")
                .url("http://text.com")
                .build();
        textRepository.save(text);
        assertEquals(text, textRepository.findAll().get(0));
    }
    @Test
    @Transactional
    public void testTextCreatedWithLectureShouldFoundOnDatabase() {
        Lecture lecture = Lecture.builder()
                .name("Lecture 1")
                .build();
        Text text = Text.builder()
                .name("Text 1")
                .size(100)
                .content("Content")
                .url("http://text.com")
                .lecture(lecture)
                .build();
        lecture.setResource(text);
        lectureRepository.save(lecture);
        textRepository.save(text);
        Text textInDb = textRepository.findAll().get(0);
        Lecture lectureInDb = lectureRepository.findAll().get(0);
        assertEquals(text, textInDb);
        assertEquals(lecture, lectureInDb);
        assertEquals(textInDb, lectureInDb.getResource());
        assertEquals(lectureInDb, textInDb.getLecture());
    }
}
