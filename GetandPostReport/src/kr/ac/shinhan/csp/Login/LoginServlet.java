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

import com.google.apphosting.utils.servlet.ParseBlobUploadFilter;

import kr.ac.shinhan.csp.MyPersistenceManager;

public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			 throws IOException {
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		boolean expirechecked = req.getParameter("checkedinfo") != null;
		boolean success = false;
		
		
		PersistenceManager pm = MyPersistenceManager.getManager();
		Query qry = pm.newQuery(UserAccount.class);
		List<UserAccount> UserList = (List<UserAccount>) qry.execute();
		
		if(UserList.size() ==0){ success = false;}
		
		
		for(UserAccount UL:UserList)
		{
			if(UL.getUserID().equals(id))	//���̵� �ִ��� Ȯ��
			{
				if(UL.getPassword().equals(password)) //��� ������ Ȯ��
				{
					success=true;
					break;
				}
			}
		}
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		if(success == true)//���̵� Ȯ�ο���
		{
			if(expirechecked == true)//���� Ȯ�ο���
			{
				UserLoginToken tm = new UserLoginToken(UUID.randomUUID().toString(),id,"30");
				int date = Integer.parseInt(tm.getExpireDate());
				pm.makePersistent(tm);
				Cookie cookie = new Cookie("log_in_id",tm.getToken());
				cookie.setMaxAge(date*24*60*60); //30�� ����!
				resp.addCookie(cookie);
			}
			//����
			HttpSession session = req.getSession(true);
			session.setMaxInactiveInterval(1000);
			session.setAttribute("userloginID", id);
			resp.sendRedirect("/index.html");
		}
		else
		{	
			resp.getWriter().println("�α��� ����!");
			resp.getWriter().println("<a href='login.html'>Login Again</a>");
			resp.getWriter().println("</html>");
			resp.getWriter().println("</body>");
		}
	}
	
	}