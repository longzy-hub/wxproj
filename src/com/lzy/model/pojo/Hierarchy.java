package com.lzy.model.pojo;

public class Hierarchy {
	private Integer id;
	private Integer superior_id;//上级id
	private Integer subordinate_id;//下级id
	
	public Hierarchy() {
		super();
	}

	public Hierarchy(Integer id, Integer superior_id, Integer subordinate_id) {
		super();
		this.id = id;
		this.superior_id = superior_id;
		this.subordinate_id = subordinate_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSuperior_id() {
		return superior_id;
	}

	public void setSuperior_id(Integer superior_id) {
		this.superior_id = superior_id;
	}

	public Integer getSubordinate_id() {
		return subordinate_id;
	}

	public void setSubordinate_id(Integer subordinate_id) {
		this.subordinate_id = subordinate_id;
	}

	@Override
	public String toString() {
		return "Hierarchy [id=" + id + ", superior_id=" + superior_id + ", subordinate_id=" + subordinate_id + "]";
	}


}
