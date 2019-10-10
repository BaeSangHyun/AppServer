package kr.or.ddit.android.repository.employee;

import kr.or.ddit.android.model.Employee;
import kr.or.ddit.android.model.User;

public interface IUserDao {

	/**
	* Method : getUserList
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 리스트 조회
	*/
	Employee checkUser(Employee user);

}
