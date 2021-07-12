package com.edu.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/queryTest")
public class QueryTestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html; charset=UTF-8");

		PrintWriter out = resp.getWriter();
		


		out.print("<html>");
		out.print("<head><title>Qeury String �׽�Ʈ</title></head>");
		out.print("<body>");
		out.print("<h3>GET ������� ��û</h3>");
		
		String id = req.getParameter("id");
		String password = req.getParameter("pwd");
		String name = req.getParameter("name");
		String[] hobbies = req.getParameterValues("hobby");
		String gender = req.getParameter("gender");
		String religion = req.getParameter("religion");
		String intro = req.getParameter("Introduction");
		
		out.print("ID : " + id + "<br>");
		out.print("Password : " + password + "<br>");
		out.print("Name : " + name + "<br>");
		out.print("Hobby : ");
		for (int i = 0; i < hobbies.length; i++) {
			out.print(hobbies[i] + " ");
		}
		out.print("<br>");
		out.print("���� : " +  gender + "<br>");
		out.print("���� : " + religion + "<br>");
		out.print("�Ұ� : " + intro + "<br>");
		out.print("��ü ������ : " + req.getQueryString());
		
		
		out.print("</body></html>");
		out.close();
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html; charset=UTF-8");

		PrintWriter out = resp.getWriter();

		out.print("<html>");
		out.print("<head><title>Qeury String �׽�Ʈ</title></head>");
		out.print("<body>");
		out.print("<h3>POST ������� ��û</h3>");
		
		String id = req.getParameter("id");
		String password = req.getParameter("pwd");
		String name = req.getParameter("name");
		String[] hobbies = req.getParameterValues("hobby");
		String gender = req.getParameter("gender");
		String religion = req.getParameter("religion");
		String intro = req.getParameter("Introduction");
		
		out.print("ID : " + id + "<br>");
		out.print("Password : " + password + "<br>");
		out.print("Name : " + name + "<br>");
		out.print("Hobby : ");
		for (int i = 0; i < hobbies.length; i++) {
			out.print(hobbies[i] + " ");
		}
		out.print("<br>");
		out.print("���� : " +  gender + "<br>");
		out.print("���� : " + religion + "<br>");
		out.print("�Ұ� : " + intro + "<br>");
		out.print("��ü ������ : " + req.getQueryString());
		
		out.print("</body></html>");
		out.close();
	}

}
