package erp.ui.list;

import javax.swing.SwingConstants;

import erp.dto.Department;
import erp.service.DepartmentService;

@SuppressWarnings("serial")
public class DepartmentTablePanel extends AbstractCustomTablePanel<Department> {
	private DepartmentService service;

	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2);
		setTableCellWidth(100, 200, 200);

	}

	@Override
	public Object[] toArray(Department d) {
		return new Object[] { d.getDeptno(), d.getDeptname(), d.getFloor() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "부서번호", "부서명", "위치" };
	}

	@Override
	public void initList() {
		list = service.showDepartments();

	}

	public void setService(DepartmentService service) {
		this.service = service;
	}

}
