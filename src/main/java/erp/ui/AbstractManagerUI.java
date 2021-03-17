package erp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import erp.dto.Title;
import erp.ui.content.AbstractContentPanel;
import erp.ui.content.TitlePanel;
import erp.ui.exception.InvalidCheckException;
import erp.ui.exception.SqlConstraintException;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.TitleTablePanel;

@SuppressWarnings("serial")
public abstract class AbstractManagerUI<T> extends JFrame implements ActionListener {

	private JPanel contentPane;
	protected JButton btnAdd;
	private JButton btnCancel;
	
	protected AbstractContentPanel<T> pContent;
	protected AbstractCustomTablePanel<T> pList;
	protected JMenuItem empListByTitleItem; // 아래에서 접근 가능해야하므로 private >> protected로 바꿈 
	
	// 동일직책사원보기 동일부서사원보기 사원상세정보 이런식으로 보이게 상수 선언!
	protected static final String TITLE_MENU = "동일 직책 사원 보기";
	protected static final String DEPT_MENU = "동일 부서 사원 보기";
	protected static final String EMP_MENU = "사원 세부정보 보기";
	
	
//	private TitleService service; // 하위 subclass가 생성해야함
	
	public AbstractManagerUI() {
		setService(); // service 연결
		
		initialize();
		
		tableLoadData(); // table component load data 과정
	}

	/*
	 * 순서보기
	 * 1. initialize
	 * 2. 서비스 연결
	 * 3. table의 데이터 로드
	 * 4. contentpanel 생성
	 * 5. tablepanel 생성
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pContent = createContentPanel();
		contentPane.add(pContent);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		JButton btnCancel = new JButton("취소");
		pBtn.add(btnCancel);
		
		pList = createTablePanel();
	
		contentPane.add(pList);
		
		// 바로가기 메뉴 만들기
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(this);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(this);
		popMenu.add(deleteItem);
		
		empListByTitleItem = new JMenuItem();
		empListByTitleItem.addActionListener(this);
		popMenu.add(empListByTitleItem);
		
		return popMenu;
	}

	// popupmenu와 btn까지 모두 한번에 if문 다 넣어버림
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() instanceof JMenuItem) { // 수정은 popupmenu에도 있고 btn에도 있으므로 이런 식으로 구분해줘야함
				/*popup 부분*/
				if (e.getActionCommand().equals("삭제")) {
					actionPerformedMenuDelete();
				}

				if (e.getActionCommand().equals("수정")) {
					actionPerformedMenuUpdate();
				}

				if (e.getActionCommand().equals(AbstractManagerUI.TITLE_MENU) ||
						e.getActionCommand().equals(AbstractManagerUI.DEPT_MENU) ||
						e.getActionCommand().equals(AbstractManagerUI.EMP_MENU)
						) {
					actionPerformedMenuCategory();
				}
					
			} else {
				/*btn 부분*/
				if (e.getSource() == btnAdd) {
					if (btnAdd.getText().equals("추가")){
						actionPerformedBtnAdd(e);
					} else if(btnAdd.getText().equals("수정")) {
						actionPerformedBtnUpdate(e);
						((TitlePanel) pContent).getTfTitleNo().setEditable(true);
						btnAdd.setText("추가");
					}
				}

				if(e.getSource() == btnCancel) {
					actionPerformedBtnCancel(e);
				}
			}
		} catch (InvalidCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
//			pContent.clearTf();
//			e1.printStackTrace(); // 예외 원인 조사 
		} catch (SqlConstraintException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
			pContent.clearTf();
		} catch (UnsupportedOperationException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
		
	}
	

	protected abstract void setService(); // 하위에서 알아서 정의하도록 추상method화
	
	protected abstract void tableLoadData();
	
	// ContentPanel과 TablePanel은 하위에서 정의하도록 추상화
	protected abstract AbstractContentPanel<T> createContentPanel();

	protected abstract AbstractCustomTablePanel<T> createTablePanel();
	
	protected abstract void actionPerformedBtnUpdate(ActionEvent e);
	
	protected abstract void actionPerformedBtnAdd(ActionEvent e);
	
	protected abstract void actionPerformedMenuDelete();
	
	protected abstract void actionPerformedMenuUpdate();
	
	public abstract void actionPerformedMenuCategory();
	
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pContent.clearTf();
		if(btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}
}