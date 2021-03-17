package erp.ui.list;

import javax.swing.SwingConstants;

import erp.dto.Department;
import erp.dto.Title;
import erp.service.DepartmentService;
import erp.ui.exception.NotSelectedException;

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

	@Override
	public Department getItem() {
		int idx = table.getSelectedRow(); // 몇번째 행이 선택되었는가?
		int deptNo = (int) table.getValueAt(idx, 0); // 선택된 행의 첫 번째 열 값 >> 직책번호니까 기본키!
		
		// 예외처리
		if (idx == -1) {
			throw new NotSelectedException();
		}
		return list.get(list.indexOf(new Department(deptNo))); // 그 기본키로 생성한 객체가 list의 몇번째에 있는지 return함
	}

}
