package erp.dao.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import erp.dto.Department;
import erp.dto.Employee;
import erp.service.DepartmentService;
import erp.ui.content.DepartmentPanel;
import erp.ui.exception.InvalidCheckException;
import erp.ui.exception.NotSelectedException;
import erp.ui.exception.NullListException;
import erp.ui.exception.SqlConstraintException;
import erp.ui.list.DepartmentTablePanel;

@SuppressWarnings("serial")
public class DepartmentManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private DepartmentPanel pContent;
	private DepartmentTablePanel pList;
	private DepartmentService service;
	
	public DepartmentManager() {
		service = new DepartmentService();
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pContent = new DepartmentPanel();
		contentPane.add(pContent);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
		
		pList = new DepartmentTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);
		
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(popupMenuListener);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(popupMenuListener);
		popMenu.add(deleteItem);
		
		JMenuItem empListByDeptItem = new JMenuItem("동일 부서 사원 보기");
		empListByDeptItem.addActionListener(popupMenuListener);
		popMenu.add(empListByDeptItem);
		
		return popMenu;
	}
	
	ActionListener popupMenuListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getActionCommand().equals("수정")) {
					Department upDept = pList.getItem();
					pContent.getTfDeptNo().setEditable(false);
					pContent.setDepartment(upDept);
					btnAdd.setText("수정");
				}
				
				if (e.getActionCommand().equals("삭제")) {
					Department delDept = pList.getItem();
					service.removeDepartment(delDept);
					pList.loadData();
					JOptionPane.showMessageDialog(null, delDept + "삭제되었습니다.");
				}
				
				if (e.getActionCommand().equals("동일 부서 사원 보기")) {
					Department searchDept = pList.getItem();
					List<Employee> empList = service.searchEmployeeByDept(searchDept);
					
					if (!empList.isEmpty()) {
						JOptionPane.showMessageDialog(null, empList, "동일 부서 사원", JOptionPane.INFORMATION_MESSAGE);
					} else {
						throw new NullListException();
					}
					 
				}
			} catch (NotSelectedException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (SqlConstraintException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			} catch (NullListException e3) {
				JOptionPane.showMessageDialog(null, e3.getMessage());
			}
			
		}
	};
	private JButton btnCancel;
	
	public void actionPerformed(ActionEvent e) {
		try {
		if (e.getSource() == btnAdd) {
			if (btnAdd.getText().equals("추가")) {
				actionPerformedBtnAdd(e);
			} else if(btnAdd.getText().equals("수정")) {
				actionPerformedBtnUpdate(e);
				pContent.getTfDeptNo().setEditable(true);
				btnAdd.setText("추가");
			}
		} 
		} catch (InvalidCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (SqlConstraintException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
			pContent.clearTf();
		}
		
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		
	}
	
	private void actionPerformedBtnCancel(ActionEvent e) {
		pContent.clearTf();
		
	}
	private void actionPerformedBtnUpdate(ActionEvent e) {
		Department upDept = pContent.getDepartment();
		
		service.updateDepartment(upDept);
		JOptionPane.showMessageDialog(null, upDept + " 수정했습니다.");
		
		pList.loadData();
		pContent.clearTf();
		
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department department = pContent.getDepartment();
		service.addDepartment(department);
		JOptionPane.showMessageDialog(null, department + " 추가했습니다.");
		
		pList.loadData();
		pContent.clearTf();
	}
}