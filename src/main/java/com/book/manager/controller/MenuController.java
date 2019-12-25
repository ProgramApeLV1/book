package com.book.controller.controller;

import com.book.common.base.BaseController;
import com.book.common.units.PageInfo;
import com.book.common.units.StringUtils;
import com.book.model.Menu;
import com.book.model.MenuTree;
import com.book.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.book.common.base.Constant.ACTION_ERROR;
import static com.book.common.base.Constant.ACTION_SUCCESS;

@Controller
@RequestMapping("/menuCont")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @RequestMapping(value = "getMenuTree")
    @ResponseBody
    public List<MenuTree> getMenuTree() {
        return menuService.getMenuTree();
    }

    @RequestMapping(value = "gotoBookInfoPage")
    public ModelAndView gotoBookInfoPage(ModelAndView modelAndView) {
        modelAndView.setViewName("/basic/bookInfo");
        return modelAndView;
    }

    @RequestMapping(value = "gotoBookTypePage")
    public ModelAndView gotoBookTypePage(ModelAndView modelAndView) {
        modelAndView.setViewName("/basic/bookType");
        return modelAndView;
    }

    @RequestMapping(value = "gotoBoorowBookPage")
    public ModelAndView gotoBoorowBookPage(ModelAndView modelAndView) {
        modelAndView.setViewName("/bookBoorow/boorowBookInfo");
        return modelAndView;
    }

    @RequestMapping(value = "gotoReturnBookPage")
    public ModelAndView gotoReturnBookPage(ModelAndView modelAndView) {
        modelAndView.setViewName("/bookReturn/returnBookInfo");
        return modelAndView;
    }

    @RequestMapping(value = "gotoMenuInfoPage")
    public ModelAndView gotoMenuInfoPage(ModelAndView modelAndView) {
        modelAndView.setViewName("/system/menuList");
        return modelAndView;
    }

    @RequestMapping(value = "gotoMenuInfoAddPage")
    public ModelAndView gotoMenuInfoAddPage(ModelAndView modelAndView) {
        modelAndView.setViewName("/system/menuAdd");
        return modelAndView;
    }

    @RequestMapping(value = "gotoMenuInfoEditPage")
    public ModelAndView gotoMenuInfoAddPage(ModelAndView modelAndView, Integer id) {
        Menu menu = menuService.selectById(id);
        modelAndView.addObject("menu", menu);
        modelAndView.setViewName("/system/menuEdit");
        return modelAndView;
    }

    @RequestMapping(value = "/getMenuInfoList", method = RequestMethod.POST)
    @ResponseBody
    public Object getMenuInfoList(Integer page, Integer rows, String menuName) {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<>();
        condition.put("menuName", menuName);
        pageInfo.setCondition(condition);
        menuService.getMenuInfoList(pageInfo);
        return pageInfo;
    }

    @RequestMapping(value = "/getParentMenuList", method = RequestMethod.POST)
    @ResponseBody
    public List<Menu> getParentMenuList() {
        return menuService.getParentMenuList();
    }

    @RequestMapping(value = "/saveMenu", method = RequestMethod.POST)
    @ResponseBody
    public Object saveMenu(@RequestBody Menu menu) {
        String menuCode = "ME" + System.currentTimeMillis();
        if (StringUtils.isNotBlank(menu.getPCode())) {

        } else {
            menu.setPCode("NONE");
        }
        menu.setCode(menuCode);
        menu.setStatus(1);
        menu.setIcon("icon-taginfo");
        menu.setCreateTime(new Date());
        menu.setUrl("NONE");
        try {
            menuService.insert(menu);
            return responseSuccess(ACTION_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return responseError(ACTION_ERROR);
        }
    }

    @RequestMapping(value = "/editMenu", method = RequestMethod.POST)
    @ResponseBody
    public Object editMenu(@RequestBody Menu menu) {
        Menu menuVo = menuService.selectById(menu.getId());
        try {
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
            return responseSuccess(ACTION_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return responseError(ACTION_ERROR);
        }
    }

    @RequestMapping(value = "/deleteWorkById", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteWorkById(Integer id) {
        try {
            menuService.deleteById(id);
            return responseSuccess(ACTION_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return responseError(ACTION_ERROR);
        }
    }

    @RequestMapping(value = "gotoUserInfoPage")
    public ModelAndView gotoUserInfoPage(ModelAndView modelAndView) {
        modelAndView.setViewName("/system/userList");
        return modelAndView;
    }
}
