package jp.co.sss.crud.bean;

/**
 * ログイン情報を管理するJavaBeanクラス。
 * ログイン判定、ログインユーザ情報、エラーメッセージを管理します。
 */
public class LoginResultBean {

	/**
	 * ログイン判定
	 */
	private boolean isLogin;
	/**
	 * ログインユーザー
	 */
	private EmployeeBean loginUser;
	/**
	 * ログイン失敗時のエラーメッセージ
	 */
	private String errorMsg;

	/**
	 * ログイン成功時のコンストラクタ
	 * 
	 * @param loginUser
	 */
	private LoginResultBean(EmployeeBean loginUser) {
		this.loginUser = loginUser;
		this.isLogin = true;

	}

	/**
	 * ログイン失敗時のコンストラクタ
	 * 
	 * @param errorMsg
	 */
	private LoginResultBean(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * ログイン成功時に呼び出すメソッド
	 * 
	 * @param loginUser
	 * @return LoginResultBean
	 */
	public static LoginResultBean succeedLogin(EmployeeBean loginUser) {
		return new LoginResultBean(loginUser);
	}

	/**
	 * ログイン失敗時に呼び出すメソッド
	 * 
	 * @param errorMsg
	 * @return LoginResultBean
	 */
	public static LoginResultBean failLogin(String errorMsg) {
		return new LoginResultBean(errorMsg);
	}

	/**
	 * ログイン判定を取得
	 * 
	 * @return isLogin
	 */
	public boolean isLogin() {
		return isLogin;
	}

	/**
	 * ログイン成功時のユーザ情報を取得
	 * 
	 * @return loginUser
	 */
	public EmployeeBean getLoginUser() {
		return loginUser;
	}

	/**
	 * ログイン失敗時エラーメッセージを返す
	 * 
	 * @return errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

}
