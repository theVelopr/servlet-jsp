package com.edu.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/queryTest2")
public class QeuryTest2Servlet extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html; charset=UTF-8");

		PrintWriter out = resp.getWriter();

		out.print("<html>");
		out.print("<head><title>Qeury String 테스트</title></head>");
		out.print("<body>");
		out.print("<h1>POST 방식 Query String 추출</h1>");
		ServletInputStream input = req.getInputStream();
		int len = req.getContentLength();
		byte[] buf = new byte[len];
		input.readLine(buf,  0,  len);
		String s = new String(buf);
		out.print("전체 문자열 : " + s);
		out.println("</body></html>");
		out.close();
		
	}

}
