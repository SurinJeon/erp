package erp.ui;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import erp.dto.Employee;
import erp.service.EmployeeService;
import erp.ui.content.AbstractContentPanel;
import erp.ui.content.EmpPanel;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.EmployeeTablePanel;

@SuppressWarnings("serial")
public class EmployeeManagerUI extends AbstractManagerUI<Employee> {
	private EmployeeService service;
	@Override
	protected void setService() {
		service = new EmployeeService();
	}

	@Override
	protected void tableLoadData() {
		((EmployeeTablePanel) pList).setService(service);
		pList.loadData();		
	}

	@Override
	protected AbstractContentPanel<Employee> createContentPanel() {
		EmpPanel empPanel = new EmpPanel();
		empPanel.setService(service);
		return empPanel; // 이부분 다시 생각해보기 (dept랑 title 연관되어있어서 이렇게 가져옴)
	}

	@Override
	protected AbstractCustomTablePanel<Employee> createTablePanel() {
		return  new EmployeeTablePanel();
	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		Employee upEmp = pContent.getItem();
		
		service.modifyEmployee(upEmp);
		JOptionPane.showMessageDialog(null, upEmp + " 수정했습니다.");
		
		pList.loadData();
		pContent.clearTf();			
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Employee newEmp = pContent.getItem();
		
		service.addEmployee(newEmp);
		JOptionPane.showMessageDialog(null, newEmp + " 추가했습니다.");
		
		pList.loadData();
		pContent.clearTf();
	}

	@Override
	protected void actionPerformedMenuDelete() {
		Employee delEmp = pList.getItem();
		service.removeEmployee(delEmp);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delEmp + " 삭제되었습니다.");		
	}

	@Override
	protected void actionPerformedMenuUpdate() {
		Employee upEmp = pList.getItem();
		((EmpPanel) pContent).getTfNo().setEditable(false);
		pContent.setItem(upEmp);
		btnAdd.setText("수정");		
	}

	@Override
	public void actionPerformedMenuCategory() {
		throw new UnsupportedOperationException("제공되지 않음");
		// 이 기능은 구현하지 않을 것이라서 예외 던짐 
	}

}
