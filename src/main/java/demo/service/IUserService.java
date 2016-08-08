package demo.service;

import java.util.List;

import demo.domain.TUser;

public interface IUserService {
	public TUser getUserById(int id);
	public List<TUser> getAllUsers();
}
