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
    public void testTextCreatedShouldFoundOnDatabase(){
        var text = Text.builder()
                .name("Text 1")
                .size(100)
                .url("http://text.com")
                .build();
        textRepository.save(text);
        assertEquals(text, textRepository.findAll().get(0));

    }
}
