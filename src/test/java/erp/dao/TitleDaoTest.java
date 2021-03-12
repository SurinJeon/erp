package erp.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp.dao.impl.TitleDaoImpl;
import erp.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoTest {


	private static TitleDao dao = TitleDaoImpl.getInstance(); 
	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	} 
	
	
	@Test
	public void test04SelectTitleByAll() {
		System.out.printf("%s() %n", "testSelectTitleByAll");
		List<Title> titleList = dao.selectTitleByAll();
		Assert.assertNotNull(titleList);
		
		titleList.stream().forEach(System.out::println);
	}

	@Test
	public void test05SelectTitleByNo() {
		System.out.printf("%s() %n", "ttestSelectTitleByNo");
		Title title = new Title(3);
		Title searchTitle = dao.selectTitleByNo(title);
		Assert.assertNotNull(searchTitle);
		System.out.println(searchTitle);
		
	}

	@Test
	public void test01InsertTitle() {
		System.out.printf("%s() %n", "testInsertTitle");
		Title newTitle = new Title(6, "인턴");
		int res = dao.insertTitle(newTitle);
		Assert.assertEquals(1, res);
		System.out.println(res);
		System.out.println(dao.selectTitleByNo(newTitle));
	}

	@Test
	public void test02UpdateTitle() {
		System.out.printf("%s() %n", "testUpdateTitle");
		Title newTitle = new Title(6, "계약직");
		int res = dao.updateTitle(newTitle); 
		Assert.assertEquals(1, res);
		System.out.println(res);
		System.out.println(dao.selectTitleByNo(newTitle));
	
	}

	@Test
	public void test03DeleteTitle() {
		System.out.printf("%s() %n", "testDeleteTitle");
		int res = dao.deleteTitle(6);
		Assert.assertEquals(1, res);
		System.out.println(res);
		dao.selectTitleByAll().stream().forEach(System.out::println);
	}

}
