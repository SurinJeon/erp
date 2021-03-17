package erp.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import erp.dto.Employee;
import erp.dto.EmployeeDetail;
import erp.service.EmployeeDetailService;
import erp.service.EmployeeService;
import erp.ui.content.AbstractContentPanel;
import erp.ui.content.EmpPanel;
import erp.ui.content.EmployeeDetailPanel;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.EmployeeTablePanel;

@SuppressWarnings("serial")
public class EmployeeManagerUI extends AbstractManagerUI<Employee> {
	private EmployeeService service;
	private EmployeeDetailService detailService;

	public EmployeeManagerUI() {
		empListByTitleItem.setText(AbstractManagerUI.EMP_MENU);
	}

	@Override
	protected void setService() {
		service = new EmployeeService();
		detailService = new EmployeeDetailService();
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
		return new EmployeeTablePanel();
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
		EmployeeDetailUI frame;
		Employee emp = pList.getItem();
		EmployeeDetail empDetail = detailService.selectEmployeeDetailByEmpNo(emp);

		if (empDetail == null) {
			frame = new EmployeeDetailUI(true, detailService);			
		} else {
			frame = new EmployeeDetailUI(false, detailService); // 세부정보가 있는 사람은 삭제버튼만 보여야됨
			frame.setDetailItem(empDetail); 
		}

		frame.setEmpNo(emp); // empNo는 uneditable 해뒀기 때문에 따로 미리 입력값 들어가도록 설정해줘야함
		frame.setVisible(true);

		// 코드로 작성한 design부분
		/*
		 * JFrame subFrame = new JFrame("사원 세부정보"); subFrame.setVisible(true); // 사이즈
		 * 맞춰주기 subFrame.setBounds(this.getWidth(), this.getHeight(), 450, 500);
		 * 
		 * // service에서 찾아온 값을 panel에 넣어주기 EmployeeDetailPanel subDetailPanel = new
		 * EmployeeDetailPanel(); subDetailPanel.setItem(empDetail);
		 * 
		 * // frame에 panel 달아주기 subFrame.add(subDetailPanel, BorderLayout.CENTER);
		 */

//		throw new UnsupportedOperationException("제공되지 않음");
		// 이 기능은 구현하지 않을 것이라서 예외 던짐
	}

}
