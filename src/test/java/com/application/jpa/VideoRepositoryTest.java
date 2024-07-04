package com.application.jpa;

import com.application.jpa.entities.Lecture;
import com.application.jpa.entities.Section;
import com.application.jpa.entities.Video;
import com.application.jpa.repositories.VideoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class VideoRepositoryTest {
    @Autowired
    private VideoRepository videoRepository;

    @Test
    @Transactional
    public void testCreateVideo() {
        Video video = Video.builder()
                .name("Video Title")
                .length(100)
                .build();
        videoRepository.save(video);
        assertEquals(1, videoRepository.count());
    }
}
