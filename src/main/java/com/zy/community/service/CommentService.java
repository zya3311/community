package com.zy.community.service;

import com.zy.community.dto.CommentDTO;
import com.zy.community.enums.CommentTypeEnum;
import com.zy.community.exception.CustomizeErrorCode;
import com.zy.community.exception.CustomizeException;
import com.zy.community.mapper.CommentMapper;
import com.zy.community.mapper.QuestionExtMapper;
import com.zy.community.mapper.QuestionMapper;
import com.zy.community.mapper.UserMapper;
import com.zy.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private UserMapper userMapper;


    @Transactional(rollbackFor = RuntimeException.class)
    public void insert(Comment comment) {
        if(null == comment.getParentId() || 0 == comment.getParentId()) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            // 回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }

            // 回复问题
//            Question question = questionMapper.selectByPrimaryKey(dbComment.getParentId());
//            if (question == null) {
//                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
//            }
//
            commentMapper.insert(comment);

            // 增加评论数
//            Comment parentComment = new Comment();
//            parentComment.setId(comment.getParentId());
//            parentComment.setCommentCount(1);
            // commentExtMapper.incCommentCount(parentComment);

            // 创建通知
            // createNotify(comment, dbComment.getCommentator(), commentator.getName(), question.getTitle(), NotificationTypeEnum.REPLY_COMMENT, question.getId());
        } else {
            // 回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            comment.setCommentCount(0);
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);

            // 创建通知
            // createNotify(comment, question.getCreator(), commentator.getName(), question.getTitle(), NotificationTypeEnum.REPLY_QUESTION, question.getId());
        }
    }

    public List<CommentDTO> listByQuestionId(Long id) {
        // 使用question的ID查询其对应的评论
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id)
                .andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if(comments == null || comments.size() == 0) {
            return new ArrayList<>();
        }
        // 获取评论人的用户ID
        Set<Long> commentators = comments.stream().map(x -> x.getCommentator())
                .collect(Collectors.toSet());
        // 使用评论人用户ID查询用其用户信息
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(new ArrayList<>(commentators));
        List<User> users = userMapper.selectByExample(userExample);

        Map<Long, User> userMap = users.stream().
                collect(Collectors.toMap(user -> user.getId(), user -> user));

        // 使用comment和user组装commentDTO并返回
        List<CommentDTO> commentDTOS = comments.stream().map(x -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(x, commentDTO);
            commentDTO.setUser(userMap.get(x.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }
}
















