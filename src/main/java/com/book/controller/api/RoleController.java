package com.book.controller.api;


import com.book.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色管理表 前端控制器
 * </p>
 *
 * @author wyh123
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/roleApi")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;



}

