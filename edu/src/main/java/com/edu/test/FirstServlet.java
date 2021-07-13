package com.edu.test;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

//@WebServlet("/hello2")
public class FirstServlet extends HttpServlet{
	
	/**
	 * init()
	 * - 서블릿이 최초로 호출되었을 때 서블릿 컨테이너가 자동으로 실행한다.
	 * - 서블릿 초기화 작업을 담당
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {

		System.out.println("init() executed!");
		
	}
	
	/**
	 * service()
	 * - 클라이언트의 요청이 있을 때마다 매번 서블릿 컨테이너가 자동으로 실행함.
	 */
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws
	ServletException, IOException {
	
		System.out.println("service() executed!");
		
	}	
}
