package demo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import demo.dao.IUserDao;
import demo.domain.TUser;
import demo.service.IUserService;

@Service("userService")
public class IUserServiceImpl implements IUserService {
	
	@Resource
	private IUserDao userDao;

	public TUser getUserById(int id) {
		return userDao.findById(id);
	}

	@Override
	public List<TUser> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public void addUser(TUser user) throws Exception{
		userDao.save(user);
	}

}
