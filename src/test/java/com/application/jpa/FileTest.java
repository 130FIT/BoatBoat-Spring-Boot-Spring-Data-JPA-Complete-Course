package com.application.jpa;

import com.application.jpa.repositories.FileRepository;
import com.application.jpa.repositories.LectureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import com.application.jpa.entities.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class FileTest {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private LectureRepository lectureRepository;

    @BeforeEach
    public void setUp() {
        fileRepository.deleteAll();
        lectureRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testCreateFile() {
        File file = File.builder()
            .name("file1")
            .type("pdf")
            .size(100)
            .url("http://localhost:8080/files/file1")
            .build();
        fileRepository.save(file);
        File fileInDb = fileRepository.findById(file.getId()).get();
        assertEquals(file, fileInDb);
    }
}
