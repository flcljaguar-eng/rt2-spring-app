package jp.co.sss.crud.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.service.SearchForEmployeesByEmpIdService;
import jp.co.sss.crud.service.UpdateEmployeeService;
import jp.co.sss.crud.util.BeanManager;

/**
 * 社員情報を更新するコントローラクラス
 * 対応する処理をサービスクラスで行い、結果をビューに渡します。
 */
@Controller
public class UpdateController {

	/**
	 * 社員情報を検索するサービスクラス。
	 * Spring DIによって自動注入されます。
	 */
	@Autowired
	SearchForEmployeesByEmpIdService searchForEmployeesByEmpIdService;

	/**
	 * 社員情報を更新するサービスクラス。
	 * Spring DIによって自動注入されます。
	 */
	@Autowired
	UpdateEmployeeService updateEmployeeService;

	/**
	 * 社員情報の変更内容入力画面を出力
	 *
	 * @param empId
	 *            社員ID
	 * @param model
	 *            モデル
	 * @return 遷移先のビュー
	 * @throws ParseException 
	 */
	@RequestMapping(path = "/update/input", method = RequestMethod.GET)
	public String inputUpdate(Integer empId, @ModelAttribute EmployeeForm employeeForm, Model model) {

		EmployeeBean employee = null;

		employee = searchForEmployeesByEmpIdService.execute(empId);

		employeeForm = BeanManager.copyBeanToForm(employee);
		model.addAttribute("employeeForm", employee);

		return "update/update_input";
	}

	/**
	 * 社員情報の変更確認画面を出力
	 *
	 * @param employeeForm
	 *            変更対象の社員情報
	 * @param model
	 *            モデル
	 * @return 遷移先のビュー
	 */
	@RequestMapping(path = "/update/check", method = RequestMethod.POST)
	public String checkUpdate(@Valid @ModelAttribute EmployeeForm employeeForm, BindingResult result) {
		//入力した内容が不正な場合入力画面に戻る
		if (result.hasErrors()) {
			return "update/update_input";
		}

		return "update/update_check";
	}

	/**
	 * 変更内容入力画面に戻る
	 *
	 * @param employeeForm 変更対象の社員情報
	 * @return 遷移先のビュー
	 */
	@RequestMapping(path = "/update/back", method = RequestMethod.POST)
	public String backInputUpdate(@ModelAttribute EmployeeForm employeeForm) {
		return "update/update_input";
	}

	/**
	 * 社員情報の変更
	 *
	 * @param employeeForm
	 *            変更対象の社員情報
	 * @return 遷移先のビュー
	 */
	@RequestMapping(path = "/update/complete", method = RequestMethod.POST)
	public String exeUpdate(EmployeeForm employeeForm, HttpSession session) {
		//フォームに入力した内容を更新
		updateEmployeeService.execute(employeeForm);

		//indexControllerで生成したセッション情報からユーザのログイン情報を取得
		EmployeeBean loginUser = (EmployeeBean) session.getAttribute("loginUser");

		//更新したユーザとログインユーザが同一の場合セッションスコープを更新
		if (loginUser.getEmpId() == employeeForm.getEmpId()) {
			//更新した内容をログインユーザを保存するセッションスコープに代入
			EmployeeBean empBean = BeanManager.copyFormToBean(employeeForm);
			session.setAttribute("loginUser", empBean);
		}

		return "redirect:/update/complete";
	}

	/**
	 * 完了画面の表示
	 * 
	 * @return 遷移先ビュー
	 */
	@RequestMapping(path = "/update/complete", method = RequestMethod.GET)
	public String completeUpdate() {
		return "update/update_complete";
	}

}
