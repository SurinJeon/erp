package erp.ui.list;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import erp.dto.Title;
import erp.ui.exception.NotSelectedException;

@SuppressWarnings("serial")
public abstract class AbstractCustomTablePanel<T> extends JPanel {
	protected JTable table;
	protected List<T> list; // TitleTablePanel에 접근하기 위해서 protected로 지정함
	
	
	public AbstractCustomTablePanel() {
		initialize();
	}
	
	// 원래 생성자에서 넣었는데 안 되어서 method로 뺌
	public void loadData() {
		initList(); // List 내용물을 가져온다
		setList(); // 가져온 내용물을 2차원배열로 만들어서 model에 집어넣는다!
	}
	
	// title이든 department든 employee든 list에서 오른쪽 클릭하면 수정/삭제 되도록 JPopupMenu가 필요하기때문에 여기 만들어줌
	public void setPopupMenu(JPopupMenu popMenu) {
		table.setComponentPopupMenu(popMenu);
	}
	
	public abstract void initList(); // 하위에서 overriding해라 
	
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(getModel());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		scrollPane.setViewportView(table);
	}

	// popup창에서 수정/삭제 누를때 쓰이는 method
	public abstract T getItem();
		
		/*
		int idx = table.getSelectedRow(); // 몇번째 행이 선택되었는가?
		
		
		// 예외처리
		if (idx == -1) {
			throw new NotSelectedException();
		}
		return list.get(idx);
		*/
		
		
	
	
	protected JTable getTable() {
		return table;
	}
	
	public DefaultTableModel getModel() {
		CustomTableModel model = new CustomTableModel(getData(), getColumnNames());
		return model;
	}
	
	public Object[][] getData() {
		return new Object[][] { {null, null, null}, };
	}
	
	public void setList() {
		// setList는 다른 타입이 오는 toArray만 추상으로 해서 setList의 바디로 가져옴
		
		// 1. 2차원 배열에 값 다 집어넣기
		Object[][] data = new Object[list.size()][];

		for (int i = 0; i < data.length; i++) {
			data[i] = toArray(list.get(i));
		}
		
		// 2. model에 배열 값을 집어넣기
		CustomTableModel model = new CustomTableModel(data, getColumnNames());
		
		// 3. table에 model setting
		table.setModel(model);
		
		// 정렬 되게 하는 부분
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);

		setAlignAndWidth();
		
	}
	
	protected abstract void setAlignAndWidth();
	
	public void setTableCellAlign(int align, int...idx) {
		// 컬럼 내용 정렬
			TableColumnModel tcm = table.getColumnModel(); // table의 각 column을 갖고오기 위함
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
			dtcr.setHorizontalAlignment(align);
			
			for (int i = 0; i < idx.length; i++) {
				tcm.getColumn(idx[i]).setCellRenderer(dtcr);
			}
		}
	
	public void setTableCellWidth(int...width) {
		TableColumnModel tcm = table.getColumnModel();
		
		for (int i = 0; i < width.length; i++) {
			tcm.getColumn(i).setPreferredWidth(width[i]);
		}
	}
	
	public abstract Object[] toArray(T t);
	
	public abstract String[] getColumnNames(); // 각 부분마다 다르니까 하위에서 정의해라

	private class CustomTableModel extends DefaultTableModel{

		public CustomTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
		
	}
	
}
