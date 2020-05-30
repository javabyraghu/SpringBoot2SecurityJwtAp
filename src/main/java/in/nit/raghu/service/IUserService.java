package in.nit.raghu.service;

import in.nit.raghu.entity.User;

public interface IUserService {

	public Integer saveUser(User user);
	public User findByUsername(String username);
}
