package com.book.controller.api;


import com.book.common.units.PageInfo;
import com.book.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
    private IRoleService roleService;


    @PostMapping(value = "/getRoleList")
    public Object getRoleList(Integer page, Integer rows, String roleName) throws Exception {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<>();
        condition.put("roleName", roleName);
        pageInfo.setCondition(condition);
        roleService.getRoleList(pageInfo);
        return pageInfo;
    }
}

