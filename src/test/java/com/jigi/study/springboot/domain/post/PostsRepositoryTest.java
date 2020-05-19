package com.jigi.study.springboot.domain.post;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {

        // given
        String title = "제목 테스트";
        String contnet = "내용 테스트";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(contnet)
                .author("gapjung")
                .build()
        );

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(contnet);
    }

    @Test
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.of(2020, 05, 19, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Posts> posts = postsRepository.findAll();

        // then
        Posts post = posts.get(0);

        System.out.println(">>>>>>>>>>> createDate=" + post.getCreateDate() + ", modifiedDate=" + post.getModifiedDate());

        assertThat(post.getCreateDate()).isAfter(now);
        assertThat(post.getModifiedDate()).isAfter(now);
    }
}