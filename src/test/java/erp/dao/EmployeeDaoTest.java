package erp.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp.dao.impl.EmployeeDaoImpl;
import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {

	private static EmployeeDao dao = EmployeeDaoImpl.getInstance();
	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test01SelectEmployeeByAll() {
		System.out.printf("%s() %n", "testSelectEmployeeByAll");
		
		List<Employee> emplist = dao.selectEmployeeByAll();
		Assert.assertNotNull(emplist);
	
		for(Employee e : emplist) {
			System.out.println(e);
		}
	}

	@Test
	public void test02SelectEmployeeByNo() {
		System.out.printf("%s() %n", "testSelectEmployeeByNo");
		Employee emp = new Employee(4377);
		Employee searchEmp = dao.selectEmployeeByNo(emp);
		Assert.assertNotNull(emp);
		
		System.out.println(searchEmp);
		
	}

	@Test
	public void test03InsertEmployee() {
		System.out.printf("%s() %n", "testInsertEmployee");
		
		Employee newEmp = new Employee(1004, "전수린", new Title(3), new Employee(4377), 3000000, new Department(2));
		
		int res = dao.insertEmployee(newEmp);
		
		Assert.assertEquals(1, res);
		System.out.println(res);
		
		List<Employee> list = dao.selectEmployeeByAll();
		for(Employee e : list) {
			System.out.println(e);
		}
//		System.out.println(dao.selectEmployeeByNo(newEmp));
		
	}

	@Test
	public void test04UpdateEmployee() {
		System.out.printf("%s() %n", "testUpdateEmployee");
		
		Employee upEmp = new Employee(1004, "짱수린", new Title(5), new Employee(4377), 10000000, new Department(2));
		
		int res = dao.updateEmployee(upEmp);
		Assert.assertEquals(1, res);
		
		System.out.println(res);
		
		System.out.println(dao.selectEmployeeByNo(upEmp));
		
	}

	@Test
	public void test05DeleteEmployee() {
		System.out.printf("%s() %n", "testDeleteEmployee");
		Employee newEmp = new Employee(1004);
		
		int res = dao.deleteEmployee(newEmp);
		Assert.assertEquals(1, res);
		System.out.println(res);

		dao.selectEmployeeByAll().stream().forEach(System.out::println);
	}
	
}
