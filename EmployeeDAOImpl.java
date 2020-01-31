package com.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.spring.model.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	
	 private DataSource dataSource;
	 
	 
	

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Employee employee) {
		String query = "insert into employee values(?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
		
		con = dataSource.getConnection();
		ps = con.prepareStatement(query);
		ps.setInt(1, employee.getId());
		ps.setString(2, employee.getName());
		ps.setString(3, employee.getRole());
		int x = ps.executeUpdate();
		if(x!=0)
		{
			System.out.println("Record inserted successfully with " + employee.getId());
			
		}
		else
		{
			System.out.println("Record not inserted with employee id" + employee.getId());
			
		}
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

	@Override
	public Employee getById(int id) {
		String query = "select name,role from Employee where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee emp =null;
		try{
		con = dataSource.getConnection();
		ps = con.prepareStatement(query);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		if(rs.next())
		{
			emp= new Employee();
			emp.setId(id);
			emp.setName(rs.getString("name"));
			emp.setRole(rs.getString("role"));
			System.out.println("Employee Found "+emp);
			
		}
		else
		{
			System.out.println("No Employee Found with id " +id);
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return emp;
	}

	@Override
	public void update(Employee employee) {
		String query = "update Employee set name=?, role=? where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getRole());
			ps.setInt(3, employee.getId());
			int out = ps.executeUpdate();
			if(out !=0){
				System.out.println("Employee updated with id="+employee.getId());
			}else System.out.println("No Employee found with id="+employee.getId());
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void deleteById(int id) {
		String query = "delete from Employee where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int out = ps.executeUpdate();
			if(out !=0){
				System.out.println("Employee deleted with id="+id);
			}else System.out.println("No Employee found with id="+id);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<Employee> getAll() {
		String query = "select id, name, role from Employee";
		List<Employee> empList = new ArrayList<Employee>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setRole(rs.getString("role"));
				empList.add(emp);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empList;
	}
	
	

}
