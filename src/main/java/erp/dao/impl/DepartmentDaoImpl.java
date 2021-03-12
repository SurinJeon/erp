package erp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import erp.dao.DepartmentDao;
import erp.database.JdbcConn;
import erp.dto.Department;


public class DepartmentDaoImpl implements DepartmentDao {
	
	private static final DepartmentDaoImpl instance = new DepartmentDaoImpl();
	
	public static DepartmentDaoImpl getInstance() {
		return instance;
	}
	// singleton
	
	@Override
	public List<Department> selectDepartmentByAll() {
		String sql = "select deptNo, deptName, floor from department";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt= con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				){
			if(rs.next()) {
				List<Department> list = new ArrayList<Department>();
				do {
					list.add(getDepartment(rs));
				} while(rs.next());
				return list;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	} // end of selectDepartmentByAll()

	private Department getDepartment(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("deptno");
		String deptName = rs.getString("deptname");
		int floor = rs.getInt("floor");
		return new Department(deptNo, deptName, floor);
	}
	@Override
	public Department selectDepartmentByNo(Department department) {
		String sql = "select deptNo, deptName, floor from department where deptNo = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt= con.prepareStatement(sql);
				){
			pstmt.setInt(1, department.getDeptno());
			try (ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					return getDepartment(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	} // end of selectDepartmentByNo()

	@Override
	public int insertDepartment(Department department) {
		String sql = "insert into department values (?, ?, ?)";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt= con.prepareStatement(sql);
				){
			pstmt.setInt(1, department.getDeptno());
			pstmt.setString(2, department.getDeptname());
			pstmt.setInt(3, department.getFloor());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

		
	@Override
	public int updateDepartment(Department department) {
		String sql = "update department set deptname = ? where deptno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt= con.prepareStatement(sql);
				){
			pstmt.setString(1, department.getDeptname());
			pstmt.setInt(2, department.getDeptno());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteDepartment(Department department) {
		String sql = "delete from department where deptno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt= con.prepareStatement(sql);
				){
			pstmt.setInt(1, department.getDeptno());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
