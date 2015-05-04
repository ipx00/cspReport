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
		if(cookies != null) //NullPointerException ����
		{
		for(Cookie c : cookies){
			if(c.getName().equals("log_in_id")) //��Ű�� ���µ� getName()�� ���ϱ� NULL����Ʈ �ͼ����� ����...
			{									
				List<UserLoginToken> UL = (List<UserLoginToken>) qry.execute(c.getValue());
				for(UserLoginToken u:UL) //��ū�� ���� ���� ���� �����Ƿ� ������ ���� ������ ���� ��Ű�� ������ �ִ�
				{						 //��ū�� �����ϴ� �ϳ��� UserLoginToken�� ID�� ����
					id = u.getUserAccount();
					u.setToken(UUID.randomUUID().toString()); //��ū ����
					c.setValue(u.getToken());
				}
				//id���ǿ� �����ϰ� index.html�� �̵�
				HttpSession session = req.getSession(true);
				session.setMaxInactiveInterval(10000);
				session.setAttribute("userloginID", id);//id�� ������.
				resp.sendRedirect("/index.html");
				i=1;
			}
			
		}
		}
		if(i==0) // ��Ű�� ����� ������ ������ loing.html �� ����.
			{
			resp.sendRedirect("/login.html");
			}
		resp.getWriter().println("</html>");
		resp.getWriter().println("</body>");
		
	}
}