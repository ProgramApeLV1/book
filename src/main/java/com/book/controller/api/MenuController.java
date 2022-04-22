package com.book.controller.api;

import com.book.common.base.BaseController;
import com.book.common.units.PageInfo;
import com.book.common.units.ResponseJson;
import com.book.common.units.StringUtils;
import com.book.model.Menu;
import com.book.model.MenuTree;
import com.book.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menuApi")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @GetMapping(value = "getMenuTree")
    public List<MenuTree> getMenuTree() throws Exception {
        return menuService.getMenuTree();
    }

    @PostMapping(value = "/getMenuInfoList")
    public Object getMenuInfoList(Integer page, Integer rows, String menuName) throws Exception {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<>();
        condition.put("menuName", menuName);
        pageInfo.setCondition(condition);
        menuService.getMenuInfoList(pageInfo);
        return pageInfo;
    }

    @PostMapping(value = "/getParentMenuList")
    public List<Menu> getParentMenuList() throws Exception {
        return menuService.getParentMenuList();
    }

    @PostMapping(value = "/saveMenu")
    public Object saveMenu(@RequestBody Menu menu) throws Exception {
        String menuCode = "ME" + System.currentTimeMillis();
        if (!StringUtils.isNotBlank(menu.getPCode())) {
            menu.setPCode("NONE");
        }
        menu.setCode(menuCode);
        menu.setStatus(1);
        menu.setIcon("icon-taginfo");
        menu.setCreateTime(new Date());
        menu.setUrl("NONE");
        menuService.insert(menu);
        return ResponseJson.success();
    }

    @PostMapping(value = "/editMenu")
    public Object editMenu(@RequestBody Menu menu) throws Exception {
        Menu menuVo = menuService.selectById(menu.getId());
        if (StringUtils.isNotBlank(menu.getUrl())) {
            menuVo.setUrl(menu.getUrl());
        } else {
            menuVo.setUrl("NONE");
        }
        if (StringUtils.isNotBlank(menu.getPCode())) {
            menuVo.setPCode(menu.getPCode());
        } else {
            menuVo.setPCode("NONE");
        }
        menuVo.setName(menu.getName());
        menuVo.setDescription(menu.getDescription());
        menuVo.setResourceType(menu.getResourceType());
        menuVo.setSeq(menu.getSeq());
        menuService.updateById(menuVo);
        return ResponseJson.success();
    }

    @PostMapping(value = "/deleteWorkById")
    public Object deleteWorkById(Integer id) throws Exception {
        menuService.deleteById(id);
        return ResponseJson.success();
    }
}
