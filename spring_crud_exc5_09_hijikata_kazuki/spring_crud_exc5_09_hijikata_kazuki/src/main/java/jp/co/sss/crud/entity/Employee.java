package jp.co.sss.crud.entity;



import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * 従業員の情報を格納するエンティティ。
 * 従業員の基本情報（ID、パスワード、名前、性別、住所、誕生日、権限、部署情報）を保持します。
 * 
 * @author k-hijikata
 */
@Entity
@Table(name = "employee")
public class Employee {
	/** 従業員ID */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_emp_gen")
	@SequenceGenerator(name = "seq_emp_gen", sequenceName = "seq_emp", allocationSize = 1)
	private Integer empId;
	
	/** 従業員パスワード */
	@Column
	private String empPass;
	
	/** 従業員名 */
	@Column
	private String empName;
	
	/** 性別（1:男性、2:女性） */
	@Column
	private Integer gender;

	/** 住所 */
	@Column
	private String address;
	
	/** 誕生日 */
	@Column
	private Date birthday;
	
	/** 権限レベル */
	@Column
	private Integer authority;
	
	/** 論理削除を行うフラグ */
	@Column
	private Integer deleteFlag;
	
	/** 外部参照する部署情報 */
	@ManyToOne
	@JoinColumn(name = "dept_id", referencedColumnName = "deptId")
	private Department department;
	
	/**
	 * デフォルトコンストラクタ。
	 * 全てのフィールドがnullまたはデフォルト値で初期化されます。
	 */
	public Employee() {
		super();
	}

	/**
	 * 従業員IDを取得します。
	 * 
	 * @return 従業員ID
	 */
	public Integer getEmpId() {
		return empId;
	}

	/**
	 * 従業員IDを設定します。
	 * 
	 * @param empId 従業員ID
	 */
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	/**
	 * 従業員パスワードを取得します。
	 * 
	 * @return 従業員パスワード
	 */
	public String getEmpPass() {
		return empPass;
	}

	/**
	 * 従業員パスワードを設定します。
	 * 
	 * @param empPass 従業員パスワード
	 */
	public void setEmpPass(String empPass) {
		this.empPass = empPass;
	}

	/**
	 * 従業員名を取得します。
	 * 
	 * @return 従業員名
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * 従業員名を設定します。
	 * 
	 * @param empName 従業員名
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * 性別を取得します。
	 * 
	 * @return 性別
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * 性別を設定します。
	 * 
	 * @param gender 性別
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * 住所を取得します。
	 * 
	 * @return 住所
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 住所を設定します。
	 * 
	 * @param address 住所
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 誕生日を取得します。
	 * 
	 * @return 誕生日
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * 誕生日を設定します。
	 * 
	 * @param birthday 誕生日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * 権限レベルを取得します。
	 * 
	 * @return 権限レベル
	 */
	public Integer getAuthority() {
		return authority;
	}

	/**
	 * 権限レベルを設定します。
	 * 
	 * @param authority 権限レベル
	 */
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	/**
	 * 削除フラグを取得します。
	 * 
	 * @return 削除フラグ
	 */
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * 削除フラグを取得します。
	 * 
	 * @param deleteFlag 削除フラグ
	 */
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * 部署情報を取得します。
	 * 
	 * @return department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * 部署情報を設定します。
	 * 
	 * @param department
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
}