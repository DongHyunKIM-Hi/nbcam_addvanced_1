package org.example.nbcam_addvanced_1.domain.post.repository;

import java.util.List;
import org.example.nbcam_addvanced_1.common.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUser_Username(String username);
}
