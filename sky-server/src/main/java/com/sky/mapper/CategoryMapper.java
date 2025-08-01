package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 新增分类
     *
     * @param category
     * @return
     */
    @Insert("insert into category (type, name, sort, status, create_time, update_time, create_user, update_user) values (#{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Category category);

    /**
     * 分类分页查询
     *
     * @param categoryPageDTO
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageDTO);

    /**
     * 启用禁用分类
     *
     * @param category
     */
    void update(Category category);

    /**
     * 根据type查询分类
     *
     * @param type
     * @return
     */
    @Select("select * from category where type = #{type}")
    List<Category> list(Integer type);

    /**
     * 根据id删除分类
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);
}
