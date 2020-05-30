package in.nit.raghu.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nit.raghu.entity.User;
import in.nit.raghu.repository.UserRepository;
import in.nit.raghu.service.IUserService;

@Service
public class UserServiceImpl implements IUserService,UserDetailsService{
	@Autowired
	private UserRepository repository;
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	@Transactional
	public Integer saveUser(User user) {
		user.setPassword(
				pwdEncoder.encode(user.getPassword())
				);
		return repository.save(user).getId();
	}

	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		Optional<User> user=repository.findByUsername(username);
		if(user.isPresent()) 
			return user.get();
		return null;
	}

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException 
	{
		User user=findByUsername(username); 
		if(user==null) 
			throw new UsernameNotFoundException(
					new StringBuffer()
					.append("User name ")
					.append(username)
					.append(" not found!")
					.toString()
					);

		List<GrantedAuthority> authorities=
				user.getRoles()
				.stream()
				.map(
						role->new SimpleGrantedAuthority(role)
						)
				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(
				username, 
				user.getPassword(), 
				authorities);
	}

}
