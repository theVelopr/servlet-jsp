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
	
	ServletContext servletContext; // serlvetContext �ּҰ��� ���� ���� ���� ����
	
	@Override // ServletConfig ��ü�� config ������ ���� 
	public void init(ServletConfig config) throws ServletException {
		servletContext = config.getServletContext(); // servletContext �ּҰ��� ���� ��, serlvetContext ������ ����
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.print("Context : " + servletContext); // ������ serlvetContext �ּҰ��� ���
	}

}
