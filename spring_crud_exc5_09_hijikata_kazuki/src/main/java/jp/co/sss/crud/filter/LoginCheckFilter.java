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
public class LoginCheckFilter extends HttpFilter {
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

		//リクエストがログイン処理の場合チェックを実行せず、リクエスト対象のコントローラ処理に移る
		if (requestURL.endsWith("/spring_crud/") ||
				requestURL.endsWith("/spring_crud/login")) {

			chain.doFilter(request, response);
			return;
		}
		
		//セッション情報を取得
		HttpSession session = request.getSession(false);
		if (session == null) {
			//ログイン情報がnullの場合
			response.sendRedirect("/spring_crud/");
			return;
		}

		//indexControllerで生成したセッション情報からユーザのログイン情報を取得
		EmployeeBean loginUser = (EmployeeBean) session.getAttribute("loginUser");

		if (loginUser == null) {
			//ログイン情報がnullの場合
			response.sendRedirect("/spring_crud/");
			return;
		} else {
			chain.doFilter(request, response);
			return;
		}

	}
}
