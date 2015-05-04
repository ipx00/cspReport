package kr.ac.shinhan.csp.Login;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.shinhan.csp.MyPersistenceManager;
import kr.ac.shinhan.csp.TeamMember;

public class SignUpServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			 throws IOException {
		String id, name, password;
		int i=0;
		id = req.getParameter("id");
		name = req.getParameter("name");
		password = req.getParameter("password");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
		PersistenceManager pm = MyPersistenceManager.getManager();
		
		
		Query qry = pm.newQuery(UserAccount.class);
		List<UserAccount> UserList = (List<UserAccount>) qry.execute();
		
		for(UserAccount UA:UserList){	//���̵� �ߺ�üũ
			if(id.equals(UA.getUserID()))
			{
				i=1; //�ߺ�üũ ǥ��
			}
		}
			
		
		if(i==1)
		{
			resp.getWriter().println("<html>");
			resp.getWriter().println("<body>");
			resp.getWriter().println("<h1>" + "���̵� �ߺ� �Ǿ����ϴ� �ٽ� �Է��ϼ���" + "</h1>");
			resp.getWriter().println("</html>");
			resp.getWriter().println("</body>");
		}
		else
		{
		UserAccount UA = new UserAccount(id, password, name);
		pm.makePersistent(UA);
		

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
	
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		resp.getWriter().println("<h1>" + "ȸ�������� �Ϸ�Ǿ����ϴ�" + "</h1>");
		
		resp.getWriter().println("</html>");
		resp.getWriter().println("</body>");
		/*
		Query q = pm.newQuery("select �ʵ� from ���̺��);")
		List<String> resuolts = (List<string>)q.execute();
		*/
		}
	}
}