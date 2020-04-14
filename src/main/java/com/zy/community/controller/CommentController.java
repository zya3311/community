package com.zy.community.controller;

import com.zy.community.dto.CommentDTO;
import com.zy.community.dto.ResultDTO;
import com.zy.community.exception.CustomizeErrorCode;
import com.zy.community.mapper.CommentMapper;
import com.zy.community.model.Comment;
import com.zy.community.model.User;
import com.zy.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping(value = "/comment")
    @ResponseBody
    public Object post(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

//        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())) {
//            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
//        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
