package erp.dto;

import java.util.List;

public class Department {
	
	private int deptNo;
	private String deptName;
	private int floor;
//	private List<Employee> empList;
	
	
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Department(int deptNo) {
		this.deptNo = deptNo;
	}



	public Department(int deptno, String deptname, int floor) {
		this.deptNo = deptno;
		this.deptName = deptname;
		this.floor = floor;
	}
	
	public int getDeptno() {
		return deptNo;
	}

	public void setDeptno(int deptno) {
		this.deptNo = deptno;
	}

	public String getDeptname() {
		return deptName;
	}

	public void setDeptname(String deptname) {
		this.deptName = deptname;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return String.format("%s(%d)", deptName, deptNo);
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + deptNo;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (deptNo != other.deptNo)
			return false;
		return true;
	}
	
	
}
