package com.example.springbootjpa;


import com.example.springbootjpa.bean.UserDO;
import com.example.springbootjpa.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class JpaApplicationTests {

	@Autowired
	private UserDao userDao;

	@Test
	public void contextLoads() {
	}

	public void before() {
		/*UserDO userDO = new UserDO();

		userDO.setId(1L);
		userDO.setName("风清扬");
		userDO.setAccount("fengqy");
		userDO.setPwd("123456");
		userDao.save(userDO);

		userDO = new UserDO();
		userDO.setId(3L);
		userDO.setName("东方不败");
		userDO.setAccount("bubai");
		userDO.setPwd("123456");
		userDao.save(userDO);

		userDO.setId(5L);
		userDO.setName("向问天");
		userDO.setAccount("wentian");
		userDO.setPwd("123456");
		userDao.save(userDO);*/
	}

	@Test
	public void testAdd() {

		UserDO userDO = new UserDO();
		userDO.setId(2L);
		userDO.setName("任我行");
		userDO.setAccount("renwox");
		userDO.setPwd("123456");
		userDao.save(userDO);

		userDO = new UserDO();
		userDO.setId(4L);
		userDO.setName("令狐冲");
		userDO.setAccount("linghuc");
		userDO.setPwd("123456");
		userDao.save(userDO);
	}

	public void after() {
		/*userDao.deleteById(1L);
		userDao.deleteById(3L);
		userDao.deleteById(5L);*/
	}

	/**
	 * 查询
	 */
	@Test
	public void testLocate() {
		Optional<UserDO> userDOOptional = userDao.findById(1L);
		if (userDOOptional.isPresent()) {
			UserDO userDO = userDOOptional.get();
			System.out.println("name = " + userDO.getName());
			System.out.println("account = " + userDO.getAccount());
		}
	}

	/**
	 * 排序
	 */
	@Test
	public void testFindAll() {
		List<UserDO> userDOList = userDao.findAll();
		for (UserDO userDO : userDOList) {
			System.out.println("name = " + userDO.getName());
			System.out.println("account = " + userDO.getAccount());
		}
	}

	/**
	 * 自定义查询
	 */
	@Test
	public void testFindByAccount() {
		UserDO userDO = userDao.findByAccount("wentian");
		if (userDO != null) {
			System.out.println("name = " + userDO.getName());
			System.out.println("account = " + userDO.getAccount());
		}
	}


}

