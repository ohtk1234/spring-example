package org.zerock.myapp.persistence;

import java.sql.Connection;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.myapp.mapper.TimeMapper;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

//Spring Framwork을 함께 구동시킴
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/**/root-")


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TimeMapperTests {
	
	@Setter(onMethod_= @Autowired)
	private SqlSessionFactory sqlSessionFactory;
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assert this.sqlSessionFactory != null;
		log.info("\t+ this.sqlSessionFactory: {}", this.sqlSessionFactory);
	}
	
	@Disabled
	@Test
	@Order(1)
	@DisplayName("Test1: To test getNow method.")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	
	void testGetNow() {
		log.trace("testGetNow() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		TimeMapper mapper = sqlSession.<TimeMapper>getMapper(TimeMapper.class);
		Objects.requireNonNull(mapper);
		log.info("\t+ mapper: {}, type: {}", mapper, mapper.getClass().getName());
		
		Date now = mapper.getNow();
		log.info("\t+ now: {}", now);
		
	}
	
	@Disabled
	@Test
	@Order(2)
	@DisplayName("Test2: To test getNow method.")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	
	void testGetNow2() {
		
		log.trace("testGetNow2() invoked.");
		
		//step1. 동적으로 TimeMapper 
		Configuration conf = this.sqlSessionFactory.getConfiguration();
		conf.addMapper(TimeMapper.class);
		
		
		
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		TimeMapper mapper = sqlSession = this.getMapper(TimeMapper.class);
		Objects.requireNonNull(mapper);
		log.info("\t+mapper: {}, type: {}", mapper, mapper.getClass().getName());
		Date now = mapper.getNow();
		log.info("\t+ now: {}", now);
	
	}
}
