package erp.ui;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import erp.dto.Employee;
import erp.dto.Title;
import erp.service.TitleService;
import erp.ui.content.AbstractContentPanel;
import erp.ui.content.TitlePanel;
import erp.ui.exception.NullListException;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.TitleTablePanel;

@SuppressWarnings("serial")
public class TitleManagerUI extends AbstractManagerUI<Title> {
	
	private TitleService service;
	
	@Override
	protected void setService() {
		service = new TitleService();
		
	}

	@Override
	protected void tableLoadData() {
		((TitleTablePanel)pList).setService(service);
		pList.loadData();
		
	}

	@Override
	protected AbstractContentPanel<Title> createContentPanel() {
		return new TitlePanel();
	}

	@Override
	protected AbstractCustomTablePanel<Title> createTablePanel() {
		return new TitleTablePanel();
	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		Title title = pContent.getItem();
		
		service.updateTitle(title);
		JOptionPane.showMessageDialog(null, title + " 수정했습니다.");
		
		pList.loadData();
		pContent.clearTf();		
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Title title = pContent.getItem();
//		JOptionPane.showMessageDialog(null, title);
		
		service.addTitle(title);
		JOptionPane.showMessageDialog(null, title + " 추가했습니다.");
		
		pList.loadData();
		pContent.clearTf();
	}

	@Override
	protected void actionPerformedMenuDelete() {
		Title delTitle = pList.getItem();
		service.removeTitle(delTitle);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delTitle + " 삭제되었습니다.");
		
	}

	@Override
	protected void actionPerformedMenuUpdate() {
		Title upTitle = pList.getItem();
		((TitlePanel) pContent).getTfTitleNo().setEditable(false);
		pContent.setItem(upTitle);
		btnAdd.setText("수정");
		
	}

	@Override
	public void actionPerformedMenuCategory() {
		Title searchTitle = pList.getItem();
		List<Employee> empList = service.searchEmployeeByTitle(searchTitle);
		if(empList != null) {
		JOptionPane.showMessageDialog(null, empList, "동일 직책 사원", JOptionPane.INFORMATION_MESSAGE);
		} else {
			throw new NullListException();
		}
		
	}

}
