package kr.ac.shinhan.csp.Login;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.shinhan.csp.MyPersistenceManager;
public class entryServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			 throws IOException {
		String id = null;
		int i=0;
		PersistenceManager pm = MyPersistenceManager.getManager();
		Query qry = pm.newQuery(UserLoginToken.class);
		Cookie[] cookies = req.getCookies();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		if(cookies != null) //NullPointerException 방지
		{
		for(Cookie c : cookies){
			if(c.getName().equals("log_in_id")) //쿠키가 없는데 getName()을 쓰니까 NULL포인트 익셉션이 난다...
			{									
				List<UserLoginToken> UL = (List<UserLoginToken>) qry.execute(c.getValue());
				for(UserLoginToken u:UL) //토큰은 절대 같은 값이 없으므로 다음과 같은 포문은 현재 쿠키가 가지고 있는
				{						 //토큰을 포함하는 하나의 UserLoginToken의 ID를 대입
					id = u.getUserAccount();
					u.setToken(UUID.randomUUID().toString()); //토큰 변경
					c.setValue(u.getToken());
				}
				//id세션에 저장하고 index.html로 이동
				HttpSession session = req.getSession(true);
				session.setMaxInactiveInterval(10000);
				session.setAttribute("userloginID", id);//id가 들어가야함.
				resp.sendRedirect("/index.html");
				i=1;
			}
			
		}
		}
		if(i==0) // 쿠키에 저장된 내용이 없으면 loing.html 을 연결.
			{
			resp.sendRedirect("/login.html");
			}
		resp.getWriter().println("</html>");
		resp.getWriter().println("</body>");
		
	}
}