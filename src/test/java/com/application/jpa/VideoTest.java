package com.application.jpa;

import com.application.jpa.entities.Video;
import com.application.jpa.repositories.LectureRepository;
import com.application.jpa.repositories.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class VideoTest {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private LectureRepository lectureRepository;

    @BeforeEach
    public void setUp() {
        videoRepository.deleteAll();
        lectureRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testCreateVideo() {
        Video video = Video.builder()
                .name("Video Title")
                .url("https://www.youtube.com/watch?v=12345")
                .size(1000)
                .length(60)
                .build();
        videoRepository.save(video);
        assertEquals(1, videoRepository.count());
    }
}
