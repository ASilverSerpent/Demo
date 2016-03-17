package webservice.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

@WebService(endpointInterface = "webservice.service.IMyService")
public class MyServiceImpl implements IMyService {
	private static List<User> users = new ArrayList<User>();

	public MyServiceImpl() {
		users.add(new User(1, "admin", "管理员", "111111"));
	}

	public int add(int a, int b) {
		System.out.println(a + " + " + b + " = " + (a + b));
		return a + b;
	}

	public int minus(int a, int b) {
		System.out.println(a + " - " + b + " = " + (a - b));
		return a - b;
	}

	public User addUser(User user) {
		users.add(user);
		return user;
	}

	public User login(String username, String password) {
		for(User user : users) {
			if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
				return user;
			}
		}
		return null;
	}

	public List<User> list() {
		return users;
	}

}
