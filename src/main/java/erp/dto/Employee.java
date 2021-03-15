package erp.dto;

public class Employee {

	private int empNo;
	private String empName;
	private Title title; // 참조
	private Employee manager; // 내부참조
	private int salary;
	private Department dept; // 참조
	// 참조하는걸 맞춰서 타입 설정하면 더 알기 쉬움

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int empNo) {
		this.empNo = empNo;
	}

	
	public Employee(String empName, int empNo) {
		this.empName = empName;
		this.empNo = empNo;
	}

	public Employee(int empNo, String empName, Title title, Employee manager, int salary, Department dept) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dept = dept;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

//	@Override
//	public String toString() {
//		return String.format("Employee (%s, %s, %s, %s, %s, %s)", empNo, empName, title.gettNo(), manager.getEmpNo() == 0 ? " " : manager.getEmpNo(),
//				salary, dept.getDeptno());
//	}
	
	@Override
	public String toString() {
		return String.format("%s (%d)", empName, empNo);
	}

	public String showEmpList() {
		return String.format("Employee (%s, %d)", empName, empNo);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + empNo;
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
		Employee other = (Employee) obj;
		if (empNo != other.empNo)
			return false;
		return true;
	}
	
}