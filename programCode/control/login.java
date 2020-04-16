package control;

public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String ac = request.getParameter("ac");
		if(ac==null)ac="";
		CommDAO dao = new CommDAO();
		String date = Info.getDateStr();
		String today = date.substring(0,10);
		String tomonth = date.substring(0,7);
		
		 
		if(ac.equals("login"))
		{
			String username = request.getParameter("username");
			String password = request.getParameter("pwd1");
			String utype = request.getParameter("cx");
			String pagerandom = request.getParameter("pagerandom")==null?"":request.getParameter("pagerandom");
			String random = (String)request.getSession().getAttribute("random");
			if(!pagerandom.equals(random)&&request.getParameter("a")!=null)
			{
				request.setAttribute("random", "");
				go("/index.jsp", request, response);
			}
			else{
				String sql1="";
				if (utype.equals("注册用户")){sql1 = "select * from yonghuzhuce where yonghuming='"+username+"' and mima='"+password+"' and issh='是'";}
				if (utype.equals("教师信息")){sql1="select * from jiaoshixinxi where jiaoshibianhao='"+username+"' and mima='"+password+"'and issh='是'";}
		if (utype.equals("学生信息")){sql1="select * from xueshengxinxi where xueshengbianhao='"+username+"' and mima='"+password+"'and issh='是'";}
		//quxanxiaxndexnglxu
			List<HashMap> userlist1 = dao.select(sql1);
			if(userlist1.size()==1)
			{
				request.getSession(). setAttribute("username", username);
				
				request.getSession(). setAttribute("cx", utype);  
				gor("index.jsp", request, response);
			}else{
				request.setAttribute("error", "");
				go("/index.jsp", request, response);
			}
			
			
			
			}
		}
		
		if(ac.equals("adminlogin"))
		{
			String username = request.getParameter("username");
			String password = request.getParameter("pwd");
			String utype = request.getParameter("cx");
			String pagerandom = request.getParameter("pagerandom")==null?"":request.getParameter("pagerandom");
			String random = (String)request.getSession().getAttribute("random");
			if(!pagerandom.equals(random)&&request.getParameter("a")!=null)
			{
				request.setAttribute("random", "");
				go("/login.jsp", request, response);
			}
			else{
				String sql1="";
				if (utype.equals("管理员")){sql1 = "select * from allusers where username='"+username+"' and pwd='"+password+"'"; }
				if (utype.equals("教师信息")){sql1="select * from jiaoshixinxi where jiaoshibianhao='"+username+"' and mima='"+password+"' and issh='是'";}
		if (utype.equals("学生信息")){sql1="select * from xueshengxinxi where xueshengbianhao='"+username+"' and mima='"+password+"' and issh='是'";}
		//quxanxiaxndexnglxu
			List<HashMap> userlist1 = dao.select(sql1);
			if(userlist1.size()==1)
			{
				request.getSession(). setAttribute("username", username);  
				if (utype.equals("管理员"))
				{
					request.getSession(). setAttribute("cx",  userlist1.get(0).get("cx")); 
				}
				else
				{
					request.getSession(). setAttribute("cx", utype); 
				}
				
				
				gor("main.jsp", request, response);
			}else{
				request.setAttribute("error", "");
				go("/login.jsp", request, response);
			}
			
			
			
			}
		}