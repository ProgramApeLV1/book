package com.book.controller.api;

import com.book.common.base.ApiCode;
import com.book.common.base.BaseController;
import com.book.common.units.PageInfo;
import com.book.common.units.ResponseJson;
import com.book.common.units.StringUtil;
import com.book.common.units.StringUtils;
import com.book.model.Menu;
import com.book.model.MenuTree;
import com.book.model.User;
import com.book.model.vo.MenuVo;
import com.book.service.IMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/menuApi")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @GetMapping(value = "/getMenuTree")
    public ResponseJson getMenuTree() throws Exception {
        return ResponseJson.success(menuService.getMenuTree());
    }

    @GetMapping(value = "/getMenuTree/{userId}")
    public ResponseJson getMenuTreeByUser(@PathVariable("userId") String userId) throws Exception {
        User user = userService.getById(userId);
        if (Objects.isNull(user)) {
            return ResponseJson.error(ApiCode.REQUEST_ERROR.getCode(), "用户信息不存在");
        }
        List<MenuTree> menuTrees = menuService.getMenuTreeByUser(user);
        return ResponseJson.success(menuTrees);
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
        menu.setUrl(StringUtil.isEmpty(menuVo.getUrl()) ? "NONE" : menuVo.getUrl());
        menuService.save(menu);
        return ResponseJson.success();
    }

    @PostMapping(value = "/editMenu")
    public ResponseJson editMenu(@RequestBody MenuVo menuVo) throws Exception {
        Menu menu = menuService.getById(menuVo.getUnid());
        menu.setUrl(StringUtil.isEmpty(menuVo.getUrl()) ? "NONE" : menuVo.getUrl());
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
        menuService.removeById(unid);
        return ResponseJson.success();
    }
}
