package erp.ui.list;

import javax.swing.SwingConstants;

import erp.dto.Title;
import erp.service.TitleService;
import erp.ui.exception.NotSelectedException;

@SuppressWarnings("serial")
public class TitleTablePanel extends AbstractCustomTablePanel<Title> {
	private TitleService service;

	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1);
		
		setTableCellWidth(100, 250); 
		
	}

	@Override
	public Object[] toArray(Title t) {
		return new Object[] {t.gettNo(), t.gettName()};
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"직책번호", "직책명"};
	}

	@Override
	public void initList() {
		list = service.showTitles(); // service에 있는 showTitles()를 호출해서 dao에서 구현한 selectedByAll한 것을 구현해온다.
		
	}

	public void setService(TitleService service) {
		this.service = service; // TitleManager (JFrame)의 service를 받아와야함
		
	}

	
	// sort 했을때 위치가 바껴서 그 행을 못 잡아내는 문제 발생 >> 추상 method 한 다음 여기서 해결할 것
	@Override
	public Title getItem() {
		int idx = table.getSelectedRow(); // 몇번째 행이 선택되었는가?
		int titleNo = (int) table.getValueAt(idx, 0); // 선택된 행의 첫 번째 열 값 >> 직책번호니까 기본키!
		
		// 예외처리
		if (idx == -1) {
			throw new NotSelectedException();
		}
		return list.get(list.indexOf(new Title(titleNo))); // 그 기본키로 생성한 객체가 list의 몇번째에 있는지 return함
	}

}
