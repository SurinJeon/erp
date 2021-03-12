package erp.ui.list;

import javax.swing.SwingConstants;

import erp.dto.Employee;

public class EmployeeTablePanel extends AbstractCustomTablePanel<Employee> {

	@Override
	public void initList() {

		
	}

	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5, 6);
		setTableCellWidth(100, 100, 100, 100, 100, 100, 100);
				
	}

	@Override
	public Object[] toArray(Employee t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getColumnNames() {
		// TODO Auto-generated method stub
		return null;
	}

}
