package com.zy.community.dto;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: Yu Zhang
 * @create: 2020-04-05 22:13
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;

    private Boolean showPrevious;

    private Boolean showFirstPage;

    private Boolean showNext;

    private Boolean showEndPage;
    /**
     * 当前页
     */
    private Integer page;

    /**
     * 要显示出来的页码
     */
    private  List<Integer> pages = new LinkedList<>();

    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.page = page;
        pages.add(page);
        for(int i=1; i<=3; i++) {
            if(page - i > 0) {
                pages.add(0, page - i);
            }
            if(totalPage >= page + i) {
                pages.add(page + i);
            }
        }
        // 是否展示"上一页"按钮
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        // 是否展示"下一页"按钮
        if (page.equals(totalPage)) {
            showNext = false;
        } else {
            showNext = true;
        }

        // 是否展示"跳转到第一页"按钮
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        // 是否展示"跳转到最后一页"按钮
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }

    }
}



























