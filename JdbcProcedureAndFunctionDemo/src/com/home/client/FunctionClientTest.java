package com.home.client;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;
import java.util.Scanner;

import com.home.util.JdbcUtil;

public class FunctionClientTest {

	public static void main(String[] args) {
		
		String SQL="{?=call function_getAverage(?,?)}";
		try(Connection connection=JdbcUtil.getConnection();
				CallableStatement cs=connection.prepareCall(SQL);
				Scanner scanner=new Scanner(System.in)){
			while(true){
			System.out.println("Enter first number: ");
			int n1 = scanner.nextInt();
			System.out.println("Enter second number: ");
			int n2 = scanner.nextInt();
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, n1);
			cs.setInt(3, n2);
			cs.execute();
			System.out.println("Average of first and second number is: "+cs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
