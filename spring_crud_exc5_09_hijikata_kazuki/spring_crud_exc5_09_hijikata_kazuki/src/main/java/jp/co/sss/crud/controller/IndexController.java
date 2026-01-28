package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.crud.bean.LoginResultBean;
import jp.co.sss.crud.form.LoginForm;
import jp.co.sss.crud.service.LoginService;

/**
 * ログイン、ログアウト処理を行うコントローラクラス。
 * ログイン判定を行い、結果をビューに渡します。
 * 
 * @author k-hijikata
 */
@Controller
public class IndexController {

	/**
	 * ログイン認証処理を行うサービスクラス。
	 * Spring DIによって自動注入されます。
	 */
	@Autowired
	LoginService loginService;

	/**
	 * トップページ
	 * ログインしていない場合必ずこのページを表示する
	 * 
	 * @param loginForm ログイン入力フォーム
	 * @return 遷移先のビュー
	 */
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(@ModelAttribute LoginForm loginForm, HttpSession session) {
		//既にログイン処理を行っていた場合全社員リストへ遷移する
		if (session.getAttribute("loginUser") != null) {
			return "redirect:/list";
		}

		return "index";
	}

	/**
	 * ログイン処理
	 * 成功した場合取得したユーザ情報をセッションに保存する
	 * 
	 * @param loginForm ログイン入力フォーム
	 * @param model モデル
	 * @param sesson セッション
	 * @return 遷移先のビュー
	 */
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult result, Model model,
			HttpSession sesson) {

		String path = "index";

		LoginResultBean loginResultBean = loginService.execute(loginForm);

		//入力内容が不正な形式の場合トップページに返す
		if (result.hasErrors()) {
			return path;
		}
		//loginResultBeanでログイン判定
		if (loginResultBean.isLogin()) {
			sesson.setAttribute("loginUser", loginResultBean.getLoginUser());
			path = "redirect:/list";
		} else {
			model.addAttribute("errMessage", loginResultBean.getErrorMsg());
		}

		return path;

	}

	/**
	 * ログアウト処理
	 * セッションスコープに保存されたユーザ情報を削除する
	 * 
	 * @param session セッション
	 * @return 遷移先のビュー
	 */
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:/";
	}

}
