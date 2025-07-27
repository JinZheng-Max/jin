package com.sky.controller.admin;


import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
@Api(tags = "分类管理相关接口")
public class CatagoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     *
     * @param categoryDTO
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增分类方法")
    public Result save(@RequestBody CategoryDTO categoryDTO) {
        log.info("新增分类：{}", categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }
    /**
     * 分类分页查询
     *
     * @param categoryPageDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询分类方法")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageDTO) {
        log.info("分页查询：{}", categoryPageDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageDTO);
        return Result.success(pageResult);
    }
    /**
     * 根据类型分类查询
     *
     * @param type
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询类型分类方法")
    public Result<List<Category>> list(Integer type) {
        log.info("查询类型分类：{}", type);
        List<Category> list= categoryService.list(type);
        return Result.success(list);
    }
    /**
     * 禁用启用菜品、套餐
     *
     * @param id，status
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation(value = "启用禁用分类方法")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        log.info("启用禁用分类：{},{}", status,id);
        categoryService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 删除分类
     *
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation(value = "根据id删除分类方法")
    public Result delete(Long id) {
        log.info("删除分类：{}", id);
        categoryService.delete(id);
        return Result.success();
    }
    /**
     * 修改分类
     *
     * @param categoryDTO
     * @return
     */
    @PutMapping
    @ApiOperation(value = "修改分类方法")
    public Result update(@RequestBody CategoryDTO categoryDTO) {
        log.info("修改分类：{}", categoryDTO);
        categoryService.update(categoryDTO);
        return Result.success();
    }
}
