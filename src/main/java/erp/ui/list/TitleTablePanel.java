package erp.ui.list;

import javax.swing.SwingConstants;

import erp.dto.Title;
import erp.service.TitleService;

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

}
