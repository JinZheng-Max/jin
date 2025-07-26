package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;

public interface CategoryService {
    /**
     * 新增分类
     *
     * @param categoryDTO
     * @return
     */
    void save(CategoryDTO categoryDTO);

    /**
     * 分类分页查询
     *
     * @param categoryPageDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageDTO);

    /**
     * 禁用/启用分类
     *
     * @param status,id
     * @return
     */
    void startOrStop(Integer status, Long id);
}
