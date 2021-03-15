package erp.service;

import java.util.List;

import erp.dao.DepartmentDao;
import erp.dao.EmployeeDao;
import erp.dao.TitleDao;
import erp.dao.impl.DepartmentDaoImpl;
import erp.dao.impl.EmployeeDaoImpl;
import erp.dao.impl.TitleDaoImpl;
import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;

public class EmployeeService {
	
	private DepartmentDao deptDao = DepartmentDaoImpl.getInstance();
	private TitleDao titleDao = TitleDaoImpl.getInstance();
	private EmployeeDao dao = EmployeeDaoImpl.getInstance();
	
	public List<Department> showDeptList(){
		return deptDao.selectDepartmentByAll();
	}
	
	public List<Title> showTitleList(){
		return titleDao.selectTitleByAll();
	}
	
	public List<Employee> showEmployees(){
		return dao.selectEmployeeByAll();
	}
	
	public List<Employee> searchEmployeeByTitle(Title title){
		return dao.selectEmployeeByTitle(title);
	}
	
	public List<Employee> showEmployeesByDept(Department department){
		return dao.selectEmployeeByDept(department);
	}
	
	public void addEmployee(Employee employee){
		dao.insertEmployee(employee);
	}

}
