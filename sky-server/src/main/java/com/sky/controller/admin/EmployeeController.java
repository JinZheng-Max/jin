package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工相关接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "员工登录方法")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "员工退出方法")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 新增员工
     *
     * @param employeeDTO
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增员工方法")
    public Result save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工：{}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }
    /**
     * 员工分页查询
     *
     * @param employeePageQueryDTO
     * @return PageResult
     */
    @GetMapping("/page")
    @ApiOperation(value = "员工分页查询方法")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("员工分页查询：{}", employeePageQueryDTO);
        PageResult pageResult =employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 启用禁用员工账号
     *
     * @return  Result
     */
    @PostMapping("/status/{status}")
    @ApiOperation(value = "启用禁用员工账号方法")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        log.info("启用禁用员工账号：{},{}", status,id);
        employeeService.startOrStop(status, id);
        return Result.success();
    }
    /**
     * 根据id查询员工
     *
     * @return  employee
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询员工方法")
    public Result<Employee> getById(@PathVariable Long id) {
        log.info("根据id查询员工：{}", id);
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }
    /**
     * 编辑员工信息
     *@param employeeDTO
     * @return  Result
     */
    @PutMapping
    @ApiOperation(value = "编辑员工信息方法")
    public Result update(@RequestBody EmployeeDTO employeeDTO) {
        log.info("编辑员工信息：{}", employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }
    /**
     * 修改密码
     *@param passwordEditDTO
     * @return  Result
     */
    @PutMapping("/editPassword")
    @ApiOperation(value = "修改密码方法")
    public Result editPassword(@RequestBody PasswordEditDTO passwordEditDTO) {
       log.info("修改密码：{}", passwordEditDTO.getEmpId());
       log.info("修改密码：{}", passwordEditDTO.getNewPassword());
       log.info("修改密码：{}", passwordEditDTO.getOldPassword());
       employeeService.editPassword(passwordEditDTO);
       return Result.success();
    }


}
