package jp.co.sss.crud.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * ログイン情報の入力を受け取るフォーム。
 * 入力チェックも同時に行う。
 * 
 * @author k-hijikata
 */
public class LoginForm {
	/** 社員ID */
	@Max(value = 9999)
	@NotNull
	private Integer empId;

	/** パスワード */
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9]{0,16}$")
	private String empPass;

	/**
	 * 社員IDの取得
	 *
	 * @return 社員ID
	 */
	public Integer getEmpId() {
		return empId;
	}

	/**
	 * 社員IDのセット
	 *
	 * @param empId 社員ID
	 */
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	/**
	 * パスワードの取得
	 *
	 * @return パスワード
	 */
	public String getEmpPass() {
		return empPass;
	}

	/**
	 * パスワードのセット
	 *
	 * @param empPass パスワード
	 */
	public void setEmpPass(String empPass) {
		this.empPass = empPass;
	}
}
