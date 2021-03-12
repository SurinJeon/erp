package erp.ui.content;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import erp.dto.Title;
import erp.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class TitlePanel extends JPanel {
	private JTextField tfTitleNo;
	private JTextField tfTitleName;

	public TitlePanel() {

		initialize();
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "직책정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 10, 10));

		JLabel lblTitleNo = new JLabel("직책번호");
		lblTitleNo.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblTitleNo);

		tfTitleNo = new JTextField();
		add(tfTitleNo);
		tfTitleNo.setColumns(10);

		JLabel lblTitleName = new JLabel("직책명");
		lblTitleName.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblTitleName);

		tfTitleName = new JTextField();
		tfTitleName.setColumns(10);
		add(tfTitleName);
	}

	public void setTitle(Title title) {
		tfTitleNo.setText(title.gettNo() + "");
		tfTitleName.setText(title.gettName());

	}

	public Title getTitle() {
		validCheck(); // 유효성검사
		int tNo = Integer.parseInt(tfTitleNo.getText().trim());
		String tName = tfTitleName.getText().trim();
		
//		tfTitleNo.setEditable(false);
		return new Title(tNo, tName);
	}

	private void validCheck() {
		// tNom tName이 입력되어야만 가능하도록 유효성 검사 하는 것
		if (tfTitleNo.getText().contentEquals("") || tfTitleName.getText().equals("")) { // contentEquals나 equals 다 써도 됨
			throw new InvalidCheckException();
		}

	}

	public void clearTf() {
		tfTitleNo.setText("");
		tfTitleName.setText("");
	}

	public JTextField getTfTitleNo() {
		return tfTitleNo;
	}

	
	
}
