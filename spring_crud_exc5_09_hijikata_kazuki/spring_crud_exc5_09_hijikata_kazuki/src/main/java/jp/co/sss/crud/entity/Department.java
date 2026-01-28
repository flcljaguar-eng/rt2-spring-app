package jp.co.sss.crud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * 従業員が所属する部署の情報を格納するエンティティ。
 * 部署IDと部署名を保持します。
 * 
 * @author k-hijikata
 */
@Entity
public class Department {
	/** 部署ID */
	@Id
	private Integer deptId;
	
	/** 部署名 */
	@Column
	private String deptName;

	/**
	 * 部署IDを取得します。
	 * 
	 * @return 部署ID
	 */
	public Integer getDeptId() {
		return deptId;
	}

	/**
	 * 部署IDを設定します。
	 * 
	 * @param deptId 部署ID
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	/**
	 * 部署名を取得します。
	 * 
	 * @return 部署名
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * 部署名を設定します。
	 * 
	 * @param deptId 部署名
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
