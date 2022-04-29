package com.book.controller.api;


import com.book.common.units.PageInfo;
import com.book.common.units.ResponseJson;
import com.book.model.vo.RoleVo;
import com.book.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping(value = "/getRoleList")
    public ResponseJson getRoleList(Integer page, Integer rows, String roleName) throws Exception {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<>();
        condition.put("roleName", roleName);
        pageInfo.setCondition(condition);
        roleService.getRoleList(pageInfo);
        return ResponseJson.success(pageInfo);
    }

    @PostMapping(value = "/add")
    public ResponseJson add(@RequestBody RoleVo roleVo) throws Exception {

        return ResponseJson.success();
    }

    @PutMapping(value = "/put")
    public ResponseJson put(@RequestBody RoleVo roleVo) throws Exception {

        return ResponseJson.success();
    }

    @DeleteMapping(value = "/delete")
    public ResponseJson delete(@RequestBody RoleVo roleVo) throws Exception {
        roleService.deleteById(roleVo.getUnid());
        return ResponseJson.success();
    }
}

