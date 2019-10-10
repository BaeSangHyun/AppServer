package kr.or.ddit.android.service.employee;

import kr.or.ddit.android.model.Employee;
import kr.or.ddit.android.repository.employee.IUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService implements IUserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	// UserDao클래스의 이름 첫글자를 소문자로 변경한 것이 자동으로 설정됨
	@Resource(name="userDao")
	private IUserDao userDao;

	/**
	* Method : getUserList
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@Override
	public Employee checkUser(Employee user) {
		return userDao.checkUser(user);
	}
}
