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

@Controller
public class IndexController {

	@Autowired
	LoginService loginService;

	/**
	 * トップページ
	 * 
	 * @param loginForm ログイン入力フォーム
	 * @return遷移先のビュー
	 */
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(@ModelAttribute LoginForm loginForm, HttpSession session) {
		
		return "index";
	}

	/**
	 * ログイン処理
	 * 
	 * @param loginForm ログイン入力フォーム
	 * @param model モデル
	 * @param sesson セッション
	 * @return 遷移先のビュー
	 */
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult result, Model model, HttpSession sesson) {

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
	 * ログアウト
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
