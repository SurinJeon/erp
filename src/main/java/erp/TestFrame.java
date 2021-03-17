package erp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp.dto.Employee;
import erp.dto.EmployeeDetail;
import erp.service.EmployeeDetailService;
import erp.service.EmployeeService;
import erp.ui.content.EmployeeDetailPanel;
import erp.ui.list.EmployeeTablePanel;

@SuppressWarnings("serial")
public class TestFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private EmployeeTablePanel pList;
	private EmployeeService service;
	private EmployeeDetailPanel pEmpDetail;
	private JPanel pBtn;
	private JButton btnGet;
	private JButton btnSet;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestFrame() {
		initialize();
		service = new EmployeeService();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 702);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		
		EmployeeService service = new EmployeeService();
		
		pList = new EmployeeTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);
		
		pEmpDetail = new EmployeeDetailPanel();
		pEmpDetail.setTfEmpNo(new Employee(1003));
		contentPane.add(pEmpDetail);
		
		pBtn = new JPanel();
		contentPane.add(pBtn);
		
		btnGet = new JButton("가져오기");
		btnGet.addActionListener(this);
		pBtn.add(btnGet);
		
		btnSet = new JButton("불러오기");
		btnSet.addActionListener(this);
		pBtn.add(btnSet);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSet) {
			actionPerformedBtnSet(e);
		}
		if (e.getSource() == btnGet) {
			actionPerformedBtnGet(e);
		}
		
	}
	
	protected void actionPerformedBtnGet(ActionEvent e) {
		EmployeeDetail employeeDetail = pEmpDetail.getItem();
		
		JOptionPane.showMessageDialog(null, employeeDetail);
	}
	
	protected void actionPerformedBtnSet(ActionEvent e) {
		EmployeeDetailService service = new EmployeeDetailService();
		EmployeeDetail empDetail = service.selectEmployeeDetailByEmpNo(new Employee(1003));
		pEmpDetail.setItem(empDetail);
	}
}




