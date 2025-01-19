package item.mapper;

import item.dto.CommentDto;
import item.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public class CommentMapper {
    public static CommentDto toCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto(comment.getId(),
                comment.getItem().getId(),
                comment.getText(),
                comment.getAuthor().getName(),
                comment.getCreated());
        return commentDto;
    }

    public static Comment toComment(CommentDto commentDto) {
        return Comment.builder()
                .id(commentDto.getId())
                .text(commentDto.getText())
                .created(commentDto.getCreated())
                .build();
    }
}
