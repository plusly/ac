package com.atguigu.ac.sc.entity;

public class Cert {
	private Integer id;

	private String name;

	@Override
	public String toString() {
		return "Cert [id=" + id + ", name=" + name + "]";
	}

	public Cert(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Cert() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
}