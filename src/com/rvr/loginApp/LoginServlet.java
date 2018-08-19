package com.rvr.loginApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String regno = req.getParameter("regno");
		String password = req.getParameter("passwd");
		int sid = Integer.parseInt(regno);
		/*
		 * System.out.println(regno); System.out.println(password);
		 */

		// JDBC codes to login

		PrintWriter out = resp.getWriter();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbUrl = "jdbc:mysql://localhost:3306/capsV3_db?user=root&password=root";
		String qry = "select * from students_info where sid=? and password=?";
		try {

			con = DriverManager.getConnection(dbUrl);

			pstmt = con.prepareStatement(qry);
			pstmt.setInt(1, sid);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				out.println("<h1>Login Succesfull</h1>");

			} else {
				out.println("<h1>Login Failed!!!! </h1>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// return ls;

	}

}
