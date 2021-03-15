package erp.dao.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;
import erp.service.EmployeeService;
import erp.ui.content.EmpPanel;
import erp.ui.exception.InvalidCheckException;
import erp.ui.list.EmployeeTablePanel;

@SuppressWarnings("serial")
public class EmployeeManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private JButton btnCancel;
	private JButton btnSet;
	private EmpPanel pEmpItem;
	private EmployeeTablePanel pList;
	private EmployeeService service;
	
	public EmployeeManager() {
		initialize();
		service = new EmployeeService();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		
		EmployeeService service = new EmployeeService();
		
		pEmpItem = new EmpPanel(); // 여기까지하면 이미 constructor 호출까지 끝남
		pEmpItem.setService(service); // setService() 호출하면서 그 밑에 list도 세팅해주도록 코드 짬
		contentPane.add(pEmpItem);
		
		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		
		btnSet = new JButton("설정");
		btnSet.addActionListener(this);
		pBtns.add(btnSet);
		pBtns.add(btnCancel);
		
		pList = new EmployeeTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSet) {
			actionPerformedBtnNewButton(e);
		}
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
			pList.loadData();
			pEmpItem.clearTf();
		}
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Employee emp = null;
		try {
		emp = pEmpItem.getEmployee();

		String message = String.format("사원번호 : %d%n사원명 : %s%n부서 : %s%n직속상사 : %s%n직책 : %s%n급여 : %d%n",
				emp.getEmpNo(),
				emp.getEmpName(),
				emp.getDept(),
				emp.getManager(),
				emp.getTitle(),
				emp.getSalary()
				);

			JOptionPane.showMessageDialog(null, message, "안내", JOptionPane.INFORMATION_MESSAGE);
		} catch (InvalidCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

		service.addEmployee(emp);
		
	}
	
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pEmpItem.clearTf();
	}
	
	protected void actionPerformedBtnNewButton(ActionEvent e) {
		Employee emp = new Employee(1003, "조민희", new Title(3), new Employee(4377), 3000000, new Department(2));
		pEmpItem.setEmployee(emp);
		
		
	}
}




