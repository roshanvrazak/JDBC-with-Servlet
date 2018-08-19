package com.rvr.loginApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/createProfile")
public class CreateProfileServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("sid");
		int sid = Integer.parseInt(id);
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String isad = req.getParameter("isad");
		String pass = req.getParameter("passwd");

		PrintWriter out = resp.getWriter();

		// System.out.println(sid+" "+fname+" "+lname+" "+isad+" "+pass);

		// JDBC Codes

		Connection con = null;
		PreparedStatement pstmt = null;
		String dbUrl = "jdbc:mysql://localhost:3306/capsV3_db?user=root&password=root";
		String qry = "insert into students_info values(?,?,?,?,?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(dbUrl);

			pstmt = con.prepareStatement(qry);
			pstmt.setInt(1, sid);
			pstmt.setString(2, fname);
			pstmt.setString(3, lname);
			pstmt.setString(4, isad);
			pstmt.setString(5, pass);

			int an = pstmt.executeUpdate();
			if (an > 0) {
				out.println("<h1> Profile created successfully</h1>");
			}

		} catch (Exception e) {
			out.println("<h1> Profile creation failed !!!</h1>");
		} finally {

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
		// return false;

	}

}
