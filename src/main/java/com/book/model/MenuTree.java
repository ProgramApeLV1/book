package com.book.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @description：TreeVO
 */
public class MenuTree implements java.io.Serializable {

    private static final long serialVersionUID = 980682543891282923L;
    
    private Long id;
 
    private String code;
    
    private String pCode;
    
    private String text;

    private String iconCls;
    
    private String state = "open";
    
    private boolean checked = false;
    
    private Object attributes;
    
    private List<MenuTree> children=new ArrayList<MenuTree>();
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public List<MenuTree> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTree> children) {
        this.children = children;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
