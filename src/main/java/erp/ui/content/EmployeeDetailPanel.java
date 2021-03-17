package erp.ui.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import erp.dto.Employee;
import erp.dto.EmployeeDetail;
import erp.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class EmployeeDetailPanel extends AbstractContentPanel<EmployeeDetail> implements ActionListener {
	private JPasswordField pfPass1;
	private JPasswordField pfPass2;
	private String imgPath = System.getProperty("user.dir") + File.separator + "img" + File.separator;
	private JLabel lblPic;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnFemale;
	private JRadioButton rdbtnMale;
	private JButton btnAddPic;
	private JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
	private JLabel lblConfirm;
	private JDateChooser dateHire;
	private JTextField tfEmpNo;
	
	public EmployeeDetailPanel() {
		initialize(); 
		loadPic(null); // JLabel이 먼저 부착된 후 거기에 이미지를 달아야되니까 initialize 밑에!
	}
	private void loadPic(String imgFilePath) {
		Image changeImage = null;
		if(imgFilePath == null) {
			ImageIcon icon = new ImageIcon(imgPath + "noimage.jpg"); // 기본 사진은 noimage가 들어가있어야됨
			changeImage = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
		} else {
			ImageIcon icon = new ImageIcon(imgFilePath);
			changeImage = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
		}
		ImageIcon changeIcon = new ImageIcon(changeImage);
		lblPic.setIcon(changeIcon);
		
	}
	private void initialize() {
		setBorder(new TitledBorder(null, "사원 세부정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pTop = new JPanel();
		add(pTop, BorderLayout.NORTH);
		
		JPanel pPic = new JPanel();
		pTop.add(pPic);
		pPic.setLayout(new BorderLayout(0, 0));
		
		lblPic = new JLabel("");
		lblPic.setPreferredSize(new Dimension(100, 150));
		pPic.add(lblPic, BorderLayout.NORTH);
		
		btnAddPic = new JButton("사진추가");
		btnAddPic.addActionListener(this);
		pPic.add(btnAddPic, BorderLayout.SOUTH);
		
		JPanel pItem = new JPanel();
		add(pItem, BorderLayout.CENTER);
		pItem.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pContent = new JPanel();
		pItem.add(pContent);
		pContent.setLayout(new GridLayout(0, 2, 10, 0));
		
		JLabel lblEmpNo = new JLabel("사원번호");
		lblEmpNo.setHorizontalAlignment(SwingConstants.TRAILING);
		pContent.add(lblEmpNo);
		
		tfEmpNo = new JTextField();
		tfEmpNo.setEditable(false);
		pContent.add(tfEmpNo);
		tfEmpNo.setColumns(10);
		
		JLabel lblHiredate = new JLabel("입사일");
		lblHiredate.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblHiredate);
		
		dateHire = new JDateChooser();
		dateHire.setDate(new Date(System.currentTimeMillis()));
		pContent.add(dateHire);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblGender);
		
		JPanel pGender = new JPanel();
		pContent.add(pGender);
		
		rdbtnFemale = new JRadioButton("여");
		rdbtnFemale.setSelected(true);
		buttonGroup.add(rdbtnFemale);
		pGender.add(rdbtnFemale);
		
		rdbtnMale = new JRadioButton("남");
		buttonGroup.add(rdbtnMale);
		pGender.add(rdbtnMale);
		
		JLabel lblPass1 = new JLabel("비밀번호");
		lblPass1.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblPass1);
		
		pfPass1 = new JPasswordField();
		pfPass1.getDocument().addDocumentListener(listener);
		pContent.add(pfPass1);
		
		JLabel lblPass2 = new JLabel("비밀번호 확인");
		lblPass2.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblPass2);
		
		pfPass2 = new JPasswordField();
		pfPass2.getDocument().addDocumentListener(listener);
		pContent.add(pfPass2);
		
		JPanel pSpace = new JPanel();
		pContent.add(pSpace);
		
		lblConfirm = new JLabel("");
		lblConfirm.setFont(new Font("굴림", Font.BOLD, 20));
		lblConfirm.setForeground(Color.RED);
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		pContent.add(lblConfirm);
	}

	public void setTfEmpNo(Employee employee) {
		tfEmpNo.setText(String.valueOf(employee.getEmpNo()));
	}
	
	@Override
	public void setItem(EmployeeDetail item) {
		tfEmpNo.setText(String.valueOf(item.getEmpNo()));
		
		byte[] iconByte = item.getPic(); // 배열 일단 가져옴 >> 이제 이걸 label에 세팅해줘야함
		ImageIcon icon = new ImageIcon(iconByte); // iconByte를 icon으로 바꿔준다음
		lblPic.setIcon(icon); // Label에 icon 세팅함
		
		dateHire.setDate(item.getHireDate());
		if (item.isGender()) {
			rdbtnFemale.setSelected(true);
		} else {
			rdbtnMale.setSelected(true);
		}
		
		// password는 가져오면 안됨! << 똑같은 해시함수를 만들어 낼 수 없기 때문 
	}

	@Override
	public EmployeeDetail getItem() {
		validCheck();
	
		int empNo = Integer.parseInt(tfEmpNo.getText());
		boolean gender = rdbtnFemale.isSelected() ? true : false;
		Date hireDate = dateHire.getDate();
		// validCheck먼저 하고 나서 변수 설정하고 있기 때문에 아래 if문은 필요없음(이미 validCheck에서 다 한 작업)
//		if (!lblConfirm.getText().contentEquals("일치")) {
//			throw new InvalidCheckException("패스워드 불일치");
//		}
		String pass = String.valueOf(pfPass1.getPassword());
		
		// 크기 맞춰서 올라간 lblPic의 그 이미지를 가져오기
		byte[] pic = getImage(); // 내가 만든 method
		
		
		return new EmployeeDetail(empNo, gender, hireDate, pass, pic);
	} 

	private byte[] getImage() {
		
		
		// try-catch-resource로 bufferedimage 쓰기
		// 가로세로폭은 이미 icon에서 이미지 크기 맞추서 올라간걸 가져왔으므로 getIconWidth getIcobHeight로 가져옴
		// 밖으로 이미지 내보내는 거니까 output
		
		/* 레이블에 있는 이미지를 이미지로 만들어서 다시 바이트배열로 던져주기*/
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
			ImageIcon icon = (ImageIcon) lblPic.getIcon(); // icon을 가져와서 imageicon으로 형변환해줌
			BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			
			// 실제 이미지를 이제 그려야하므로 Graphics 사용
			Graphics2D g2 = bi.createGraphics();
			
			// bufferedImage에서 공간확보해서 가져온것을 Graphics2D g2에서 그림
			g2.drawImage(icon.getImage(), 0, 0, null); // observer는 null로 하면 됨
			g2.dispose();
			
			ImageIO.write(bi, "png", baos);
			
			return baos.toByteArray(); // byte 배열로 다시 return해주기
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void validCheck() {
		if(!lblConfirm.getText().contentEquals("일치")) {
			throw new InvalidCheckException("패스워드 불일치");
		} 
		
	}

	@Override
	public void clearTf() {
		lblPic.setIcon(new ImageIcon(imgPath + "noimage.jpg"));
		dateHire.setDate(new Date(System.currentTimeMillis()));
		rdbtnFemale.setSelected(true);
		pfPass1.setText("");
		pfPass2.setText("");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddPic) {
			actionPerformedBtnAddPic(e);
		}
	}
	protected void actionPerformedBtnAddPic(ActionEvent e) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG images", "jpg", "png", "gif");;
		chooser.setFileFilter(filter);
		int res = chooser.showOpenDialog(null);
		if (res != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택해주세요");
			return;
		}
		
		String chooseFilePath = chooser.getSelectedFile().getPath();
		loadPic(chooseFilePath);
	}
	
	DocumentListener listener = new DocumentListener() {
		
		@Override
		public void removeUpdate(DocumentEvent e) {
			getMessage();
		}
		
		@Override
		public void insertUpdate(DocumentEvent e) {
			getMessage();
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) {
			getMessage();
		}
		
		public void getMessage() {
			String pw1 = new String(pfPass1.getPassword());
			String pw2 = new String(pfPass2.getPassword());
			
			if (pw1.equals(pw2)) {
				lblConfirm.setText("일치");
			} else {
				lblConfirm.setText("불일치");
			}
		}
	};
	

	

	
	}









