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

import erp.dto.Employee;
import erp.dto.Title;
import erp.service.TitleService;
import erp.ui.content.TitlePanel;
import erp.ui.exception.InvalidCheckException;
import erp.ui.exception.NotSelectedException;
import erp.ui.exception.NullListException;
import erp.ui.exception.SqlConstraintException;
import erp.ui.list.TitleTablePanel;

@SuppressWarnings("serial")
public class TitleManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private TitlePanel pContent;
	private TitleTablePanel pList;
	private TitleService service; // Frame 생성하고 아래로 계속 전달해주기
	
	public TitleManager() {
		service = new TitleService();
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pContent = new TitlePanel();
		contentPane.add(pContent);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		JButton btnCancel = new JButton("취소");
		pBtn.add(btnCancel);
		
		pList = new TitleTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);
		
		// 바로가기 메뉴 만들기
		JPopupMenu popupMenu = createPopupMenu(); // 메소드 만들어서 빼기
		pList.setPopupMenu(popupMenu); // 이 popupMenu는 title department employee 모두가 하는 것 >> 그래서 abstractcustom어쩌고로 넘김
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(popupMenuListener);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(popupMenuListener);
		popMenu.add(deleteItem);
		
		JMenuItem empListByTitleItem = new JMenuItem("동일 직책 사원 보기");
		empListByTitleItem.addActionListener(popupMenuListener);
		popMenu.add(empListByTitleItem);
		
		return popMenu;
	}
	
	// 팝업전용 actionlistener-(method : actionperformed)만들기
	ActionListener popupMenuListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getActionCommand().equals("삭제")) {
					Title delTitle = pList.getItem();
					service.removeTitle(delTitle);
					pList.loadData();
					JOptionPane.showMessageDialog(null, delTitle + " 삭제되었습니다.");
				}
				
				if (e.getActionCommand().equals("수정")) {
					Title upTitle = pList.getItem();
					pContent.getTfTitleNo().setEditable(false);
					pContent.setTitle(upTitle);
					btnAdd.setText("수정");
				}
				
				if (e.getActionCommand().equals("동일 직책 사원 보기")) {
					Title searchTitle = pList.getItem();
					List<Employee> empList = service.searchEmployeeByTitle(searchTitle);
					if(empList != null) {
					JOptionPane.showMessageDialog(null, empList, "동일 직책 사원", JOptionPane.INFORMATION_MESSAGE);
					} else {
						throw new NullListException();
					}
				}
			} catch (NotSelectedException  | SqlConstraintException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (NullListException e2) {
				JOptionPane.showMessageDialog(null, "해당 사원이 없습니다.");
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}; 
	
	public void actionPerformed(ActionEvent e) {
		try { // getTitle()의 예외가 여기까지 전파됨 << 그래서 여기서 try-catch
			if (e.getSource() == btnAdd) {
				if (btnAdd.getText().equals("추가")){
					actionPerformedBtnAdd(e);
				} else if(btnAdd.getText().equals("수정")) {
					actionPerformedBtnUpdate(e);
					pContent.getTfTitleNo().setEditable(true);
					btnAdd.setText("추가");
				}
			}
		} catch (InvalidCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
//			pContent.clearTf();
//			e1.printStackTrace(); // 예외 원인 조사 
		} catch (SqlConstraintException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
			pContent.clearTf();
		}
	}
	
	private void actionPerformedBtnUpdate(ActionEvent e) {
		
		Title title = pContent.getTitle();
		
		service.updateTitle(title);
		JOptionPane.showMessageDialog(null, title + " 수정했습니다.");
		
		pList.loadData();
		pContent.clearTf();
		
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Title title = pContent.getTitle();
//		JOptionPane.showMessageDialog(null, title); // textField에 넣은 내용 제대로 가져오는지 확인용
		
		// service를 통해서 addTitle! << 근데 service는 dao거를 또 가져옴
		service.addTitle(title);
		JOptionPane.showMessageDialog(null, title + " 추가했습니다.");
		
		// 아래 List 부분에도 추가되기 위해서 다시 리스트 -> 배열 -> 모델 -> 세팅 과정 거치는 loadData해줌
		pList.loadData();
		
		// 그리고 textField칸에 다시 적을 수 있도록 clear해줌
		pContent.clearTf();
	}
}