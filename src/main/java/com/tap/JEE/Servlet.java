package com.tap.JEE;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String method = req.getMethod();
		
		if(method.equalsIgnoreCase("get")) {
			doGet(req, resp);
		}else if(method.equalsIgnoreCase("post")) {
			doPost(req, resp);
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userName = req.getParameter("name");
		
		PrintWriter out = resp.getWriter();
		out.print("Hello " + userName);
		
		System.out.println("post");
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userName = req.getParameter("name");
		
		PrintWriter out = resp.getWriter();
		out.print("Hello " + userName);
		
		System.out.println("get");
	}
	
}
