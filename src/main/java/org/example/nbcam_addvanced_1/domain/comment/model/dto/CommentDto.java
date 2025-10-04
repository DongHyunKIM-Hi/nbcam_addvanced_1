package org.example.nbcam_addvanced_1.domain.comment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.nbcam_addvanced_1.common.entity.Comment;
import org.example.nbcam_addvanced_1.domain.post.model.dto.PostDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private long commentId;
    private String content;
    private long postId;


    public static CommentDto from(Comment comment) {
        return new CommentDto(comment.getId(), comment.getContent(), comment.getPost().getId());
    }
}
