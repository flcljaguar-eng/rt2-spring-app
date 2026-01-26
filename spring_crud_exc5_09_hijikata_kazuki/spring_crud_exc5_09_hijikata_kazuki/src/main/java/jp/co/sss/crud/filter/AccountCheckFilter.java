package jp.co.sss.crud.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.bean.EmployeeBean;

@Component
public class AccountCheckFilter extends HttpFilter {
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		//リクエストURLを取得
		String requestURL = request.getRequestURI();

		//フィルタからCSSなどの処理を除外
		if (requestURL.indexOf("/html/") != -1 ||
				requestURL.indexOf("/css/") != -1 ||
				requestURL.indexOf("/img/") != -1 ||
				requestURL.indexOf("/js/") != -1) {
			chain.doFilter(request, response);

			return;
		}

		//セッション情報を取得して未ログインの状態を除外
		HttpSession session = request.getSession(false);
		if(session == null) {
			chain.doFilter(request, response);
			return;
		}
		
		
		
		//indexControllerで生成したセッション情報からユーザのログイン情報を取得
		EmployeeBean loginUser = (EmployeeBean) session.getAttribute("loginUser");

		if (requestURL.indexOf("/spring_crud/regist") != -1 ||
				requestURL.indexOf("/spring_crud/delete") != -1) {

			//リクエストが権限を要求する登録、削除の場合チェックを実行
			if (loginUser.getAuthority() >= 2) {
				chain.doFilter(request, response);
				return;
			} else {
				
				//権限がない場合ログアウト処理を実行
				response.sendRedirect("/spring_crud/logout");
				return;
			}

		} else {
			chain.doFilter(request, response);
			return;
		}

	}
}
