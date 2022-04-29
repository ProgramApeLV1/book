package com.book.controller.api;

import com.book.common.base.BaseController;
import com.book.common.units.PageInfo;
import com.book.common.units.ResponseJson;
import com.book.common.units.StringUtil;
import com.book.common.units.StringUtils;
import com.book.model.Menu;
import com.book.model.vo.MenuVo;
import com.book.service.IMenuService;
import org.springframework.beans.BeanUtils;
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

    @GetMapping(value = "/getMenuTree")
    public ResponseJson getMenuTree() throws Exception {
        return ResponseJson.success(menuService.getMenuTree());
    }

    @GetMapping(value = "/getMenuInfoList")
    public ResponseJson getMenuInfoList(Integer page, Integer rows, String menuName, String level) throws Exception {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<>();
        condition.put("menuName", menuName);
        condition.put("level", level);
        pageInfo.setCondition(condition);
        menuService.getMenuInfoList(pageInfo);
        return ResponseJson.success(pageInfo);
    }

    @GetMapping(value = "/getCurMenuInfo")
    public ResponseJson getCurMenuInfo(@RequestParam String unid) throws Exception {
        return ResponseJson.success(menuService.getCurMenuInfo(unid));
    }

    @GetMapping(value = "/getParentMenuList")
    public ResponseJson getParentMenuList() throws Exception {
        return ResponseJson.success(menuService.getParentMenuList());
    }

    @PostMapping(value = "/saveMenu")
    public ResponseJson saveMenu(@RequestBody MenuVo menuVo) throws Exception {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVo, menu);
        String menuCode = "ME" + System.currentTimeMillis();
        if (StringUtil.isEmpty(menuVo.getPCode())) {
            menu.setPCode("NONE");
            menu.setLevel(0);
        } else {
            Menu pMenu = menuService.getParentMenu(menuVo.getPCode());
            menu.setPCode(menuVo.getPCode());
            menu.setLevel(pMenu.getLevel() + 1);
        }
        menu.setResourceType(menuVo.getResourceType() == null ? 0 : menuVo.getResourceType());
        menu.setCode(menuCode);
        menu.setStatus(1);
        menu.setIcon("icon-taginfo");
        menu.setCreateTime(new Date());
        menu.setUrl("NONE");
        menuService.insert(menu);
        return ResponseJson.success();
    }

    @PostMapping(value = "/editMenu")
    public ResponseJson editMenu(@RequestBody MenuVo menuVo) throws Exception {
        Menu menu = menuService.selectById(menuVo.getUnid());
        if (StringUtils.isNotBlank(menuVo.getUrl())) {
            menu.setUrl(menuVo.getUrl());
        } else {
            menu.setUrl("NONE");
        }
        if (StringUtils.isEmpty(menuVo.getPCode()) || "NONE".equals(menuVo.getPCode())) {
            menu.setPCode("NONE");
            menu.setLevel(0);
        } else {
            Menu pMenu = menuService.getParentMenu(menuVo.getPCode());
            menu.setPCode(menuVo.getPCode());
            menu.setLevel(pMenu.getLevel() + 1);
        }
        menu.setResourceType(menuVo.getResourceType() == null ? 0 : menuVo.getResourceType());
        menu.setName(menuVo.getName());
        menu.setDescription(menuVo.getDescription());
        menu.setSeq(menuVo.getSeq());
        menuService.updateById(menu);
        return ResponseJson.success();
    }

    @PostMapping(value = "/deleteWorkById")
    public ResponseJson deleteWorkById(String unid) throws Exception {
        menuService.deleteById(unid);
        return ResponseJson.success();
    }
}
