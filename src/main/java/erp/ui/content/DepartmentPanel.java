package erp.ui.content;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import erp.dto.Department;
import erp.ui.exception.InvalidCheckException;

// 와꾸짜기
@SuppressWarnings("serial")
public class DepartmentPanel extends AbstractContentPanel<Department> {
	private JTextField tfDeptNo;
	private JTextField tfDeptName;
	private JTextField tfFloor;

	public DepartmentPanel() {

		initialize();
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "부서정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 10, 10));

		JLabel lblDeptNo = new JLabel("부서번호");
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDeptNo);

		tfDeptNo = new JTextField();
		add(tfDeptNo);
		tfDeptNo.setColumns(10);

		JLabel lblDeptName = new JLabel("부서명");
		lblDeptName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDeptName);

		tfDeptName = new JTextField();
		tfDeptName.setColumns(10);
		add(tfDeptName);

		JLabel lblFloor = new JLabel("위치");
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblFloor);

		tfFloor = new JTextField();
		tfFloor.setColumns(10);
		add(tfFloor);
	}

	@Override
	public void clearTf() {
		tfDeptNo.setText("");
		tfDeptName.setText("");
		tfFloor.setText("");
		if(!tfDeptNo.isEditable()) {
			tfDeptNo.setEditable(true);
		}
	}

	public JTextField getTfDeptNo() {
		return tfDeptNo;
	}

	@Override
	public void setItem(Department item) {
		tfDeptNo.setText(item.getDeptno() + "");
		tfDeptName.setText(item.getDeptname());
		tfFloor.setText(item.getFloor() + "");		
	}

	@Override
	public Department getItem() {
		// 유효성 검사 추가 << 공백이 오면 추가되면 안됨
		validCheck();
		int deptno = Integer.parseInt(tfDeptNo.getText().trim());
		String deptname = tfDeptName.getText().trim();
		int floor = Integer.parseInt(tfFloor.getText().trim());

		return new Department(deptno, deptname, floor);
	}

	@Override
	public void validCheck() {
		if (tfDeptNo.getText().contentEquals("") || tfDeptName.getText().equals("")) { // contentEquals나 equals 다 써도 됨
			throw new InvalidCheckException();
		}
		
		
	}

}