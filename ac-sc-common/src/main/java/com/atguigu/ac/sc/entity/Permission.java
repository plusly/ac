package com.atguigu.ac.sc.entity;

import java.util.ArrayList;
import java.util.List;

public class Permission {
    private Integer id;

    private Integer pid;

    //必须和zTree中使用的属性名一致
    private String name;

	//必须和zTree中使用的属性名一致
    private String icon;

    //必须和zTree中使用的属性名一致
    private String url;
    
    //必须和zTree中使用的属性名一致
    private Boolean open = true;
    
    //必须和zTree中使用的属性名一致
    private List<Permission> children = new ArrayList<>();
    
    //增加checked属性用来表示树形菜单中多选框是否勾选
    private Boolean checked;
    
    public Permission() {
		
	}

	public Permission(Integer id, Integer pid, String name, String icon, String url, Boolean open,
			List<Permission> children) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.icon = icon;
		this.url = url;
		this.open = open;
		this.children = children;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", pid=" + pid + ", name=" + name + ", icon=" + icon + ", url=" + url
				+ ", open=" + open + ", children=" + children + "]";
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public List<Permission> getChildren() {
		return children;
	}

	public void setChildren(List<Permission> children) {
		this.children = children;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

}