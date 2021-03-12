package erp.database;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

public class JdbcConnTest {

	@Test
	public void testGetConnection() {
		Connection con = JdbcConn.getConnection(); // JdbcConn 연결 잘 되는지 검사
		System.out.println("con > " + con);
		Assert.assertNotNull(con);
	}

}
