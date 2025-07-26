package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CategoryServicelmpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    /**
     * 新增分类
     *
     * @param categoryDTO
     * @return
     */
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        // 对象属性拷贝
        BeanUtils.copyProperties(categoryDTO, category);
        // 设置创建时间和更新时间
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        // 设置创建人，更新人
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());
        //设置状态，禁用
        category.setStatus(0);
        //保存数据
        categoryMapper.insert(category);

    }

    /**
     * 分类分页查询
     *
     * @param categoryPageDTO
     * @return
     */
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageDTO) {

        PageHelper.startPage(categoryPageDTO.getPage(), categoryPageDTO.getPageSize());
        Page<Category> page = categoryMapper.pageQuery(categoryPageDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }


    /**
     * 启用禁用分类
     *
     * @param status
     * @param id
     */
    public void startOrStop(Integer status, Long id) {
        Category category = Category.builder()
                .status(status)
                .id(id)
                .build();

        categoryMapper.update(category);
    }
}
