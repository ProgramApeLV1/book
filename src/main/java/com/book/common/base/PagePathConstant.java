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

    private static final String BOOK_RETURN = "/bookReturn";

    private static final String BOOK_BOOROW = "/bookBoorow";

    private static final String SYSTEM_MENU = SYSTEM_BASIC.concat("/menu");

    private static final String SYSTEM_USER = SYSTEM_BASIC.concat("/user");

    private static final String SYSTEM_ROLE = SYSTEM_BASIC.concat("/role");

    private static final String BOOK_INFO_BASIC = BASIC.concat("/bookInfo");

    private static final String BOOK_TYPE_BASIC = BASIC.concat("/bookType");

    //系统用户模块
    public static final String USER_LIST_PAGE = SYSTEM_USER.concat("/userList");

    public static final String USER_ADD_PAGE = SYSTEM_USER.concat("/userAdd");

    public static final String USER_EDIT_PAGE = SYSTEM_USER.concat("/userEdit");

    public static final String EDIT_PWD_PAGE = SYSTEM_USER.concat("/editPwd");

    //系统角色模块
    public static final String ROLE_LIST_PAGE = SYSTEM_ROLE.concat("/roleList");

    public static final String ROLE_ADD_PAGE = SYSTEM_ROLE.concat("/roleAdd");

    public static final String ROLE_EDIT_PAGE = SYSTEM_ROLE.concat("/roleEdit");

    public static final String ROLE_MENU_CONFIG_PAGE = SYSTEM_ROLE.concat("/roleMenuConfig");

    //系统菜单模块
    public static final String MENU_LIST_PAGE = SYSTEM_MENU.concat("/menuList");

    public static final String MENU_ADD_PAGE = SYSTEM_MENU.concat("/menuAdd");

    public static final String MENU_EDIT_PAGE = SYSTEM_MENU.concat("/menuEdit");

    //书本返还模块
    public static final String RETURN_BOOK_INFO_PAGE = BOOK_RETURN.concat("/returnBookInfo");

    //书本租借模块
    public static final String BOOROW_BOOK_INFO_PAGE = BOOK_BOOROW.concat("/boorowBookInfo");

    public static final String BOOROW_BOOK_ADD_PAGE = BOOK_BOOROW.concat("/boorowBookAdd");

    //书本类型模块
    public static final String BOOK_TYPE_PAGE = BOOK_TYPE_BASIC.concat("/bookType");

    public static final String BOOK_TYPE_ADD_PAGE = BOOK_TYPE_BASIC.concat("/bookTypeAdd");

    public static final String BOOK_TYPE_EDIT_PAGE = BOOK_TYPE_BASIC.concat("/bookTypeEdit");

    //书本信息模块
    public static final String BOOK_INFO_PAGE = BOOK_INFO_BASIC.concat("/bookInfo");

    public static final String BOOK_INFO_ADD_PAGE = BOOK_INFO_BASIC.concat("/bookInfoAdd");

    public static final String BOOK_INFO_EDIT_PAGE = BOOK_INFO_BASIC.concat("/bookInfoEdit");
}
