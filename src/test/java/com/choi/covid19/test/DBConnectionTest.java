package com.choi.covid19.test;

import java.sql.Connection;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.choi.covid19.config.RootConfig;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class DBConnectionTest {
	
	@Setter(onMethod_ = @Autowired)
	private DataSource dataSource;
	
	@Setter(onMethod_ = @Autowired)
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void dbConnectionTest() {
		try(SqlSession session = sqlSessionFactory.openSession();
				Connection conn = session.getConnection();) {
			log.info(session);
			log.info(conn);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
