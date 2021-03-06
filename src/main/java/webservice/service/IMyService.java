package webservice.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService()
public interface IMyService {
	@WebResult(name="addResult")
	public int add(@WebParam(name="a")int a, @WebParam(name="b")int b);
	public int minus(int a, int b);
	
	@WebResult(name="user")
	public User addUser(@WebParam(name="user")User user);
	@WebResult(name="user")
	public User login(@WebParam(name="username")String username,@WebParam(name="password")String password);
	@WebResult(name="user")
	public List<User> list();
}
