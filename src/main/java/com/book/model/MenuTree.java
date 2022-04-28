package com.book.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description：TreeVO
 */
@Data
public class MenuTree implements java.io.Serializable {

    private static final long serialVersionUID = 980682543891282923L;
    
    private String unid;
 
    private String code;
    
    private String pCode;
    
    private String text;

    private String iconCls;
    
    private String state = "open";
    
    private boolean checked = false;
    
    private Object attributes;
    
    private List<MenuTree> children=new ArrayList<MenuTree>();
}
