package kr.or.ddit.android.repository.employee;

import kr.or.ddit.android.model.Employee;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

// class명에서 맨 첫글자를 소문자로 변경한 문자열이 스프링 빈 이름으로 기본 설정됨
// 다른 이름의 스프링 빈이름으로 등록을 하려면 속성 설정
// ==> ex) @Repository("userDaooooo") <- 일반적으로는 사용하지 않는편
@Repository
public class UserDao implements IUserDao {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	/**
	* Method : getUserList
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 리스트 조회
	*/
	@Override
	public Employee checkUser(Employee user) {
		return sqlSession.selectOne("user.checkUser", user);
	}


}
