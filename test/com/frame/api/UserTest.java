package com.frame.api;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.frame.model.bean.User;
import com.frame.model.bean.UserCriteria;
import com.frame.model.dao.UserMapper;
import com.frame.util.JsonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring.xml"})
public class UserTest {

	@Autowired
	private UserMapper userService;
	
	//@Test
	public void testAddUser() {
		User user = new User();
		user.setUserName("白虎神皇xdp");
		user.setPassword("nnnnnnnnnnnnnnnnnnn");
		user.setUserAccount("zhucz");
		user.setUserType("1");
		userService.insertSelective(user);
		System.out.println(JsonUtil.objToJson(user));
		
		User user1 = new User();
		user1.setUserName("白虎神皇xdp");
		user1.setPassword("nnnnnnnnnnnnnnnnnnn");
		user1.setUserAccount("zhucz");
		user1.setUserType("1");
		userService.insertSelective(user1);
		System.out.println(JsonUtil.objToJson(user1));
	}
	
	@Test
	public void testGetUsers(){
		UserCriteria user = new UserCriteria();
		user.or().andUserAccountEqualTo("hh");
		List<User> users = this.userService.selectByExample(user);
		System.out.println(users);
	}
	
}
