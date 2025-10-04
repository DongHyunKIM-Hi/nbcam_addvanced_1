package org.example.nbcam_addvanced_1.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.nbcam_addvanced_1.common.entity.Post;
import org.example.nbcam_addvanced_1.common.entity.User;
import org.example.nbcam_addvanced_1.post.model.dto.PostDto;
import org.example.nbcam_addvanced_1.post.repository.PostRepository;
import org.example.nbcam_addvanced_1.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostDto createPost(String username, String content) {

        User user = userRepository.findByUsername(username).orElseThrow(
            () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        Post post = new Post(content, user);

        postRepository.save(post);

        return PostDto.from(post);
    }

    public PostDto getPost(long id) {

        Post post = postRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("등록된 Post가 없습니다.")
        );

        return PostDto.from(post);
    }

    public List<PostDto> getPostListByUsername(String username) {

        User user = userRepository.findByUsername(username).orElseThrow(
            () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        List<Post> postList = new ArrayList<>();


        return postList.stream()
            .map(PostDto::from)
            .collect(Collectors.toList());
    }
}
