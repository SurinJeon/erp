package erp.ui.content;

import javax.swing.JPanel;

public abstract class InterfaceItem<T> extends JPanel{
	
	public InterfaceItem() {
		initialize();
	}
	public abstract void initialize();
	
	// interface니까 public 어쩌고 생략되어서 안 써도 됨
	public abstract void setItem(T item);
	public abstract T getItem();
	public abstract void validCheck();
	public abstract void clearTf();
}
