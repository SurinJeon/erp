package erp.service;

import erp.dao.EmployeeDetailDao;
import erp.dao.impl.EmployeeDetailDaoImpl;
import erp.dto.Employee;
import erp.dto.EmployeeDetail;

public class EmployeeDetailService {
	private EmployeeDetailDao empDetailDao = EmployeeDetailDaoImpl.getInstance();

	public EmployeeDetail selectEmployeeDetailByEmpNo(Employee employee) {
		return empDetailDao.selectEmployeeDetailByNo(employee);
	}

	public void addEmployeeDetail(EmployeeDetail empDetail) {
		empDetailDao.insertEmployeeDetail(empDetail);
	}

	public void modifyEmployeeDetail(EmployeeDetail empDetail) {
		empDetailDao.updateEmployeeDetail(empDetail);
	}

	public void removeEmployeeDetail(Employee employee) {
		empDetailDao.deleteEmployeeDetail(employee);
	}
}
