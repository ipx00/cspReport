package kr.ac.shinhan.csp.Login;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.shinhan.csp.MyPersistenceManager;
import kr.ac.shinhan.csp.TeamMember;

public class LogoutServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			 throws IOException {
		PersistenceManager pm = MyPersistenceManager.getManager();
		Query qry = pm.newQuery(UserLoginToken.class);
		HttpSession session = req.getSession(false);
		int i=0;
		session.invalidate();			// 세션 삭제
		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		Cookie[] cookieList = req.getCookies();
		if(cookieList != null)
		{
		for(Cookie c : cookieList)
		{
			if(c.getName().equals("log_in_id"))
			{
				List<UserLoginToken> UT = (List<UserLoginToken>) qry.execute(c.getValue());
				for(UserLoginToken u:UT) //토큰은 절대 같은 값이 없으므로 다음과 포문은 현재 쿠키가 가지고 있는
				{						 //토큰을 포함하는 하나의 UserLoginToken객체를 삭제
				pm.deletePersistent(u);
				}
				c.setValue(null);		 //쿠키 삭제
				c.setMaxAge(0);
				resp.addCookie(c);
				resp.sendRedirect("/login.html");
				i=1;
			}
		}
		}
		if(i==0)		//쿠키가 없을시 수행됨
		{
			resp.sendRedirect("login.html");
		}
		resp.getWriter().println("</html>");
		resp.getWriter().println("</body>");
		
	}
}