package erp.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp.dto.Employee;
import erp.dto.EmployeeDetail;
import erp.service.EmployeeDetailService;
import erp.ui.content.EmployeeDetailPanel;

@SuppressWarnings("serial")
public class EmployeeDetailUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel pBtns;
	private EmployeeDetailPanel pItem;
	private JButton btnAdd;
	private EmployeeDetailService service;
	private JButton btnCancel;
	
	
	// 매개변수로 pBtns부분 보일지 안 보일지 결정하는 변수 넣기!
	// 왜냐하면 사진이 있으면 추가하면 안되기때문
	public EmployeeDetailUI(boolean isBtns, EmployeeDetailService service) {
		this.service = service; // EmployeeManagerUI에서 받아오기위해서 이렇게 함
		initialize(isBtns);
	}
	private void initialize(boolean isBtns) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 478, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pItem = new EmployeeDetailPanel();
		contentPane.add(pItem, BorderLayout.CENTER);
		
		pBtns = new JPanel();
			// isBtns가 true >> 보이게 함 
			// isBtns가 false >> 안 보이게 함
			contentPane.add(pBtns, BorderLayout.SOUTH);

			btnAdd = new JButton();
			btnAdd.addActionListener(this);
			pBtns.add(btnAdd);

			btnCancel = new JButton();
			btnCancel.addActionListener(this);
			pBtns.add(btnCancel);
			
			if (isBtns) {
				btnAdd.setText("추가");
				btnCancel.setText("삭제");
			} else {
				btnAdd.setText("수정");
				btnCancel.setText("삭제");
//
//				contentPane.add(pBtns, BorderLayout.SOUTH);
//
//				btnUpdate = new JButton("수정");
//				pBtns.add(btnUpdate);
//				btnDel.addActionListener(this);
//
//				btnDel = new JButton("삭제");
//				btnDel.addActionListener(this);
//				pBtns.add(btnDel);
		}
	}

	public void setEmpNo(Employee employee) {
		pItem.setTfEmpNo(employee);
	}
	
	// 여기서 수정까지 함 (불러와서 그대로 pItem 부분 채우겠단건 수정하겠단 뜻)
	public void setDetailItem(EmployeeDetail empDetail) {
//		btnAdd.setText("수정");
		pItem.setItem(empDetail);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contentEquals("삭제")) {
			actionPerformedBtnDel(e);
		}
		if (e.getActionCommand().contentEquals("취소")) {
			actionPerformedBtnCancel(e);
		}
		if (e.getActionCommand().contentEquals("추가")) {
			actionPerformedBtnAdd(e);
		}
		if (e.getActionCommand().contentEquals("수정")) {
			actionPerformedBtnUpdate(e);
		}
	}
	private void actionPerformedBtnUpdate(ActionEvent e) {

		EmployeeDetail upEmpDetail = pItem.getItem(); // 1.
		service.modifyEmployeeDetail(upEmpDetail); // 2.
		JOptionPane.showMessageDialog(null,"수정 완료"); // 확인용 message
		pItem.clearTf();
		dispose(); // 스스로 창이 꺼짐
		
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		/*
		 * 1. 입력된 empdetail 가져오기
		 * 2. service에 적용하기 
		 */
		
		EmployeeDetail newEmpDetail = pItem.getItem(); // 1.
		service.addEmployeeDetail(newEmpDetail); // 2.
		JOptionPane.showMessageDialog(null, "추가 완료"); // 확인용 message
		pItem.clearTf();
		dispose();
	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		pItem.clearTf();
		if (btnAdd.getText().contentEquals("수정")){
			btnAdd.setText("추가");
		}
	}	

	protected void actionPerformedBtnDel(ActionEvent e) {
		EmployeeDetail empDetail = pItem.getItem();
		service.removeEmployeeDetail(new Employee(empDetail.getEmpNo())); 
		JOptionPane.showMessageDialog(null, "삭제 완료");
		pItem.clearTf();
	}
	
	///////////////////////////// for main test //////////////////////////////
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeDetailService service = new EmployeeDetailService();
					EmployeeDetailUI frame = new EmployeeDetailUI(true, service);
					frame.setEmpNo(new Employee(2106)); // unvisible 해뒀기 때문에 따로 설정해줘야함 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
