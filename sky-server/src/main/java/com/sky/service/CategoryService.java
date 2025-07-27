package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

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

    /**
     * 根据类型查询分类，目测前端有bug
     *
     * @return
     */
    List<Category> list(Integer type);

    /**
     * 删除分类
     *
     * @param id
     * @return
     */
    void delete(Long id);


    /**
     * 修改分类
     *
     * @param categoryDTO
     * @return
     */
    void update(CategoryDTO categoryDTO);
}
