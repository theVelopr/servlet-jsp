package com.edu.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/context1")
public class ServletContextTest1Servlet extends HttpServlet{
	
	ServletContext servletContext; // serlvetContext 주소값을 갖는 참조 변수 선언
	
	@Override // ServletConfig 객체를 config 변수로 받음 
	public void init(ServletConfig config) throws ServletException {
		servletContext = config.getServletContext(); // servletContext 주소값을 추출 후, serlvetContext 변수에 저장
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.print("Context : " + servletContext); // 추출한 serlvetContext 주소값을 출력
	}

}
