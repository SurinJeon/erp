package erp.ui.list;

import javax.swing.SwingConstants;

import erp.dto.Employee;
import erp.service.EmployeeService;

@SuppressWarnings("serial")
public class EmployeeTablePanel extends AbstractCustomTablePanel<Employee> {

	private EmployeeService service;

	@Override
	public void initList() {
		list = service.showEmployees();

	}

	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 5);
		setTableCellAlign(SwingConstants.RIGHT, 4);
		setTableCellWidth(100, 100, 100, 200, 150, 100);

	}

	@Override
	public Object[] toArray(Employee e) {
		return new Object[] { e.getEmpNo(), e.getEmpName(),
				String.format("%s(%d)", e.getTitle().gettName(), e.getTitle().gettNo()),
				(e.getManager().getEmpName() == null ? "" : e.getManager().getEmpName())
						+ (e.getManager().getEmpNo() == 0 ? "" : "(" + e.getManager().getEmpNo() + "" + ")"),
				e.getSalary(), String.format("%s(%d)", e.getDept().getDeptname(), e.getDept().getDeptno()) };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "사원번호", "사원명", "직책", "직속상사", "급여", "부서" };
	}

	public void setService(EmployeeService service) {
		this.service = service;
	}

}
