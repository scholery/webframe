package com.frame.api;

import java.util.List;

import com.frame.bean.GridBean;
import com.frame.model.bean.User;
import com.frame.model.bean.UserCriteria;

public interface IUser {

	void addUser(User user);

	void updateUser(User user);

	void deleteUser(List<Integer> ids);

	User getUserById(int id);

	List<User> findUsers(UserCriteria user);

	User getUserWithPass(String account);

	List<Integer> getUserModuleIds(int id);

	GridBean findUsersForGrid(UserCriteria user, int page, int count);
}
