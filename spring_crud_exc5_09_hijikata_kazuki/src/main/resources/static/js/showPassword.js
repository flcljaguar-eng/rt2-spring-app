/**
* チェックボックスにパスワードを表示させる機能を持たせるファイル
*/
function togglePasswordVisibility() {
	console.log("test");
	//	フォーム入力されたパスワード
	let passwordInput = document.getElementById("password");
	//	チェックボックスのチェック有無
	let showPasswordCheckbox = document.getElementById("showPassword");

	if (showPasswordCheckbox.checked) {
		//	チェックが入っていた場合	input type="text"へ変更
		passwordInput.type = "text";
	} else {
		passwordInput.type = "password";
	}
}