package com.home.client;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

import com.home.util.JdbcUtil;

public class ProcedureClientTest {

	public static void main(String[] args) {
		
		String SQL="call proc_getEmployeeById(?)";
		try(Connection connection=JdbcUtil.getConnection();
				CallableStatement cs=connection.prepareCall(SQL);
				Scanner scanner=new Scanner(System.in)){
			while(true){
			System.out.println("Enter employee id: ");
			int empId = scanner.nextInt();
			cs.setInt(1, empId);
			ResultSet rs = cs.executeQuery();
			if(rs.next()) {
				int employee_id = rs.getInt("employee_id");
				String employee_name=rs.getString("employee_name");
				String email=rs.getString("email");
				Double salary=rs.getDouble("salary");
				Date joiningDate=rs.getDate("date_of_joining");
				BigDecimal bonus=rs.getBigDecimal("bonus");
				System.out.println("EmployeeId: "+employee_id);
				System.out.println("Employee name: "+employee_name);
				System.out.println("Email: "+email);
				System.out.println("Employee joining date: "+joiningDate);
				System.out.println("Bonus: "+bonus);
			}
			else
				System.out.println("Employee doesn't exist with provded Id");
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
