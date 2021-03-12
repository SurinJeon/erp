package erp.service;

import java.util.List;

import erp.dao.EmployeeDao;
import erp.dao.impl.EmployeeDaoImpl;
import erp.dto.Employee;
import erp.dto.Title;

public class EmployeeService {
	
	private EmployeeDao dao = EmployeeDaoImpl.getInstance();
//	private TitleDao tDao = TitleDaoImpl.getInstance();
	
	public List<Employee> showEmployees(){
		return dao.selectEmployeeByAll();
	}
	
	public List<Employee> searchEmployeeByTitle(Title title){
		return dao.selectEmployeeByTitle(title);
	}

}
