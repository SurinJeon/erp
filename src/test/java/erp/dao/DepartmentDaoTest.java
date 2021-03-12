package erp.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp.dao.impl.DepartmentDaoImpl;
import erp.dto.Department;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	
	private static DepartmentDao dao = DepartmentDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println(); // 공백
	}

	@Test
	public void test04SelectDepartmentByAll() {
		System.out.printf("%s() %n", "testSelectDepartmentByAll");
		List<Department> list = dao.selectDepartmentByAll();
		Assert.assertNotNull(list);
		
		for(Department d : list) {
			System.out.println(d);
		}
		
	}

	@Test
	public void test05SelectDepartmentByNo() {
		System.out.printf("%s() %n", "testSelectDepartmentByNo");
		Department dept = new Department(3);
		Department searchDept= dao.selectDepartmentByNo(dept);
		Assert.assertNotNull(searchDept);
		
		System.out.println(searchDept);
		
	}

	@Test
	public void test01InsertDepartment() {
		System.out.printf("%s() %n", "testInsertDepartment");
		Department newDept = new Department(5, "마케팅", 5);
		
		int res = dao.insertDepartment(newDept);
		Assert.assertEquals(1, res);
		System.out.println(res);
		
		System.out.println(dao.selectDepartmentByNo(newDept));
	}

	@Test
	public void test02UpdateDepartment() {
		System.out.printf("%s() %n", "testInsertDepartment");
		Department upDept = new Department(5, "재무", 5);
		
		int res = dao.updateDepartment(upDept);
		Assert.assertEquals(1, res);
		System.out.println(res);
		
		System.out.println(dao.selectDepartmentByNo(upDept));
	}

	@Test
	public void test03DeleteDepartment() {
		System.out.printf("%s() %n", "testDeleteDepartment");
		Department newDept = new Department(5, "마케팅", 5);
		
		int res = dao.deleteDepartment(newDept);
		Assert.assertEquals(1, res);
		System.out.println(res);
		
		dao.selectDepartmentByAll().stream().forEach(System.out::println);
		
	}

}
