package erp.ui.content;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;
import erp.service.EmployeeService;
import erp.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class EmpPanel extends AbstractContentPanel<Employee> implements ItemListener{
	private JTextField tfNo;
	private JTextField tfName;
	private JComboBox<Title> cmbTitle; // generic을 쓰지 않으면 일반적으로 String이 들어가게 됨...
	// >> String이면 String 객체를 다시 생성해서 넣어야하는데, 제네릭 쓰면 그런 문제는 없음
	private JComboBox<Employee> cmbManager;
	private JSpinner spinSalary;
	private JComboBox<Department> cmbDept;
	private EmployeeService service; // TestFrame에서 쓸 수 있도록 여기 적어줌
	private JPanel pItem;
	
	public EmpPanel() {

		initialize();
	}

	public void setService(EmployeeService service) {
		this.service = service;
		
		// 부서부분 콤보박스 보이게 하기위한 작업(일단 데이터 가져오기)
		List<Department> deptList = service.showDeptList();
		// 콤보박스용 모델 만들기 (deptList 들어갈거니까 generic은 Department)
		DefaultComboBoxModel<Department> deptModel = new DefaultComboBoxModel<>(new Vector<>(deptList));
		// 이제 모델을 콤보박스에 달아주기
		cmbDept.setModel(deptModel);
		
		
		List<Title> titleList = service.showTitleList();
		DefaultComboBoxModel<Title> titleModel = new DefaultComboBoxModel<Title>(new Vector<>(titleList));
		cmbTitle.setModel(titleModel);

		System.out.println(cmbDept.getSelectedItem());
		
		List<Employee> managerList = service.showEmployeesByDept(new Department(cmbDept.getSelectedIndex()));
		DefaultComboBoxModel<Employee> managerModel = new DefaultComboBoxModel<Employee>(new Vector<>(managerList));
		cmbManager.setModel(managerModel);
		
		// 처음에 켰을 때 아무것도 선택되어있지 않게 만듦
//		cmbDept.getSelectedItem(0);
		cmbDept.setSelectedIndex(-1);
		cmbTitle.setSelectedIndex(-1);
		
		
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "사원 정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		pItem = new JPanel();
		add(pItem);
		pItem.setLayout(new GridLayout(0, 2, 10, 0));
		
		JLabel lblNo = new JLabel("사원번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblNo);
		
		tfNo = new JTextField();
		tfNo.setColumns(10);
		pItem.add(tfNo);
		
		JLabel lblName = new JLabel("사원명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		pItem.add(tfName);
		
		JLabel lblDept = new JLabel("부서");
		lblDept.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblDept);
		

		cmbDept = new JComboBox<Department>();
		cmbDept.addItemListener(this);
		pItem.add(cmbDept);
	
		
		JLabel lblManager = new JLabel("직속상사");
		lblManager.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblManager);
		
		cmbManager = new JComboBox<Employee>();
		pItem.add(cmbManager);
		
		
		JLabel lblTitle = new JLabel("직책");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblTitle);
		

		cmbTitle = new JComboBox<Title>();
		pItem.add(cmbTitle);
		
		JLabel lblSalary = new JLabel("급여");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblSalary);
		
		spinSalary = new JSpinner();
		spinSalary.setModel(new SpinnerNumberModel(2000000, 1500000, 5000000, 100000));
		pItem.add(spinSalary);
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cmbDept) {
			itemStateChangedCmbDept(e);
		}
	}

	protected void itemStateChangedCmbDept(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			Department dept = (Department) cmbDept.getSelectedItem();
			List<Employee> managerList = service.showEmployeesByDept(dept);

			if (managerList == null) {
				managerList = new ArrayList<Employee>();
//				JOptionPane.showMessageDialog(null, "해당 부서 사원이 없습니다");
			}

			DefaultComboBoxModel<Employee> managerModel = new DefaultComboBoxModel<Employee>(new Vector<>(managerList));
			cmbManager.setModel(managerModel);
			cmbManager.setSelectedIndex(-1);

		}
	}


	@Override
	public void validCheck() {
		if (tfNo.getText().trim().contentEquals("") || tfName.getText().contentEquals("")) {
			throw new InvalidCheckException();
		}

		if (cmbDept.getSelectedIndex() == -1 || cmbManager.getSelectedIndex() == -1
				|| cmbTitle.getSelectedIndex() == -1) {
			System.out.println("콤보박스찍힘");
			throw new InvalidCheckException();
		}

	}
	
	@Override
	public void clearTf() {
		tfNo.setText("");
		tfName.setText("");
		cmbDept.setSelectedIndex(-1);
		cmbManager.setSelectedIndex(-1);
		cmbTitle.setSelectedIndex(-1);
		spinSalary.setValue(2000000);

	}

	@Override
	public void setItem(Employee item) {
		tfNo.setText(item.getEmpNo() + "");
		tfName.setText(item.getEmpName());
		cmbDept.setSelectedItem(item.getDept()); // equals를 해줘야 일치하는걸 선택할 수 있음
		cmbManager.setSelectedItem(item.getManager());
		cmbTitle.setSelectedItem(item.getTitle());
		spinSalary.setValue(item.getSalary());		
	}

	@Override
	public Employee getItem() {
		validCheck();
		int empNo = Integer.parseInt(tfNo.getText().trim());
		String empName = tfName.getText().trim();

		// 위에서 제네릭 안했다면 여기서 좀 일이 많아짐
		Title title = (Title) cmbTitle.getSelectedItem();
		Employee manager = (Employee) cmbManager.getSelectedItem();
		int salary = (int) spinSalary.getValue();
		Department dept = (Department) cmbDept.getSelectedItem();

		return new Employee(empNo, empName, title, manager, salary, dept);
	}

	public JTextField getTfNo() {
		return tfNo;
	}
	
}









