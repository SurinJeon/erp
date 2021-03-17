package erp.ui;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import erp.dto.Department;
import erp.dto.Employee;
import erp.service.DepartmentService;
import erp.ui.content.AbstractContentPanel;
import erp.ui.content.DepartmentPanel;
import erp.ui.exception.NullListException;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.DepartmentTablePanel;

@SuppressWarnings("serial")
public class DepartmentManagerUI extends AbstractManagerUI<Department> {
	private DepartmentService service;
	
	public DepartmentManagerUI() {
		empListByTitleItem.setText(AbstractManagerUI.DEPT_MENU);
	}

	@Override
	protected void setService() {
		service = new DepartmentService();		
	}

	@Override
	protected void tableLoadData() {
		((DepartmentTablePanel) pList).setService(service);
		pList.loadData();		
	}

	@Override
	protected AbstractContentPanel<Department> createContentPanel() {
		return  new DepartmentPanel();
	}

	@Override
	protected AbstractCustomTablePanel<Department> createTablePanel() {
		return new DepartmentTablePanel();
	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		Department upDept = pContent.getItem();
		
		service.updateDepartment(upDept);
		JOptionPane.showMessageDialog(null, upDept + " 수정했습니다.");
		
		pList.loadData();
		pContent.clearTf();		
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department department = pContent.getItem();
		service.addDepartment(department);
		JOptionPane.showMessageDialog(null, department + " 추가했습니다.");
		
		pList.loadData();
		pContent.clearTf();
		
	}

	@Override
	protected void actionPerformedMenuDelete() {
		Department delDept = pList.getItem();
		service.removeDepartment(delDept);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delDept + "삭제되었습니다.");
		
	}

	@Override
	protected void actionPerformedMenuUpdate() {
		Department upDept = pList.getItem();
		((DepartmentPanel) pContent).getTfDeptNo().setEditable(false);
		pContent.setItem(upDept);
		btnAdd.setText("수정");
	}

	@Override
	public void actionPerformedMenuCategory() {
		Department searchDept = pList.getItem();
		List<Employee> empList = service.searchEmployeeByDept(searchDept);
		
		if (empList != null) {
			JOptionPane.showMessageDialog(null, empList, "동일 부서 사원", JOptionPane.INFORMATION_MESSAGE);
		} else {
			throw new NullListException();
		}		
	}

}
