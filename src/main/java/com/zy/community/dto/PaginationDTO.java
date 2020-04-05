package com.zy.community.dto;

import com.zy.community.model.Question;
import lombok.Data;

import java.util.List;

/**
 * @author: Yu Zhang
 * @create: 2020-04-05 22:13
 */
@Data
public class PaginationDTO {
    private List<Question> questions;
}
