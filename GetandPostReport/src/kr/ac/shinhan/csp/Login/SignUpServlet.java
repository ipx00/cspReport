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
		
		for(UserAccount UA:UserList){	//아이디 중복체크
			if(id.equals(UA.getUserID()))
			{
				i=1; //중복체크 표시
			}
		}
			
		
		if(i==1)
		{
			resp.getWriter().println("<html>");
			resp.getWriter().println("<body>");
			resp.getWriter().println("<h1>" + "아이디가 중복 되었습니다 다시 입력하세요" + "</h1>");
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
		resp.getWriter().println("<h1>" + "회원가입이 완료되었습니다" + "</h1>");
		
		resp.getWriter().println("</html>");
		resp.getWriter().println("</body>");
		/*
		Query q = pm.newQuery("select 필드 from 테이블명);")
		List<String> resuolts = (List<string>)q.execute();
		*/
		}
	}
}