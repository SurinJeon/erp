package erp.service;

import java.util.List;

import erp.dao.EmployeeDao;
import erp.dao.TitleDao;
import erp.dao.impl.EmployeeDaoImpl;
import erp.dao.impl.TitleDaoImpl;
import erp.dto.Employee;
import erp.dto.Title;

public class TitleService {

	private TitleDao dao = TitleDaoImpl.getInstance();
	private EmployeeDao daoEmp = EmployeeDaoImpl.getInstance();
	
	// TitleDaoImpl에서 만들어 둔 것을 TitleService에서 methodO로 가져올 수 있게 만듦
	public List<Title> showTitles(){
		return dao.selectTitleByAll();
	}
	
	public void addTitle(Title title) {
		dao.insertTitle(title);
	}
	
	public void removeTitle(Title title) {
		dao.deleteTitle(title.gettNo());
	}
	
	public void updateTitle(Title title) {
		dao.updateTitle(title);
		
	}
	
	public List<Employee> searchEmployeeByTitle(Title title){
		return daoEmp.selectEmployeeByTitle(title);
	}
}
