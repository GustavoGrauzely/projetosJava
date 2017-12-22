package br.com.grauzely.acc.util;

import java.sql.*;
import javax.swing.*;

public class ConnectionUtil {
	Connection conn = null;

	public static Connection connectdb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/suite_accenture", "root", "alv696569");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}