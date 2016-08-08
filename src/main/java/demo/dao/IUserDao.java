package demo.dao;

import java.util.List;

import demo.domain.TUser;

public interface IUserDao {
	public TUser findById(int id);
	public List<TUser> findAll();
	public void save(TUser user) throws Exception;
}
