package com.book.common.base;

/**
 * @packageName: com.book.common.base
 * @className: PagePathConstant
 * @date: create in 2022/3/30 15:58
 * @author: wyh
 * @description: 页面模块地址
 */
public class PagePathConstant {

    private static final String SYSTEM_BASIC = "/system";

    private static final String BASIC = "/basic";

    private static final String BOOKRETURN = "/bookReturn";

    private static final String BOOKBOOROW = "/bookBoorow";

    private static final String SYSTEM_MENU = SYSTEM_BASIC.concat("/menu");

    private static final String SYSTEM_USER = SYSTEM_BASIC.concat("/user");

    private static final String SYSTEM_ROLE = SYSTEM_BASIC.concat("/role");

    private static final String BOOKINFO_BASIC = BASIC.concat("/bookInfo");

    private static final String BOOKTYPE_BASIC = BASIC.concat("/bookType");

    //系统用户模块
    public static final String USERLIST_PAGE = SYSTEM_USER.concat("/userList");

    public static final String USERADD_PAGE = SYSTEM_USER.concat("/userAdd");

    public static final String USEREDIT_PAGE = SYSTEM_USER.concat("/userEdit");

    public static final String EDITPWD_PAGE = SYSTEM_USER.concat("/editPwd");

    //系统角色模块
    public static final String ROLELIST_PAGE = SYSTEM_ROLE.concat("/roleList");

    public static final String ROLEADD_PAGE = SYSTEM_ROLE.concat("/roleAdd");

    public static final String ROLEEDIT_PAGE = SYSTEM_ROLE.concat("/roleEdit");

    //系统菜单模块
    public static final String MENULIST_PAGE = SYSTEM_MENU.concat("/menuList");

    public static final String MENUADD_PAGE = SYSTEM_MENU.concat("/menuAdd");

    public static final String MENUEDIT_PAGE = SYSTEM_MENU.concat("/menuEdit");

    //书本返还模块
    public static final String RETURNBOOKINFO_PAGE = BOOKRETURN.concat("/returnBookInfo");

    //书本租借模块
    public static final String BOOROWBOOKINFO_PAGE = BOOKBOOROW.concat("/boorowBookInfo");

    public static final String BOOROWBOOKADD_PAGE = BOOKBOOROW.concat("/boorowBookAdd");

    //书本类型模块
    public static final String BOOKTYPE_PAGE = BOOKTYPE_BASIC.concat("/bookType");

    public static final String BOOKTYPEADD_PAGE = BOOKTYPE_BASIC.concat("/bookTypeAdd");

    public static final String BOOKTYPEEDIT_PAGE = BOOKTYPE_BASIC.concat("/bookTypeEdit");

    //书本信息模块
    public static final String BOOKINFO_PAGE = BOOKINFO_BASIC.concat("/bookInfo");

    public static final String BOOKINFOADD_PAGE = BOOKINFO_BASIC.concat("/bookInfoAdd");

    public static final String BOOKINFOEDIT_PAGE = BOOKINFO_BASIC.concat("/bookInfoEdit");
}
