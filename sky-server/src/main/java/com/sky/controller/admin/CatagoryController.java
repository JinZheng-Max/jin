package com.sky.controller.admin;


import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
