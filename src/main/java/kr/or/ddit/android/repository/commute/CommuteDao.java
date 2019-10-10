package kr.or.ddit.android.repository.commute;

import kr.or.ddit.android.model.Commute;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CommuteDao implements ICommuteDao {

    @Resource(name="sqlSessionTemplate")
    private SqlSessionTemplate sqlSession;

    @Override
    public Commute commuteInfo(Commute commute) {
        return sqlSession.selectOne("commute.commuteInfo", commute);
    }

    @Override
    public int commuteCheck(Commute commute) {
        return sqlSession.insert("commute.commuteCheck", commute);
    }

    @Override
    public int commuteUpdate(Commute commute) {
        return sqlSession.update("commute.commuteUpdate", commute);
    }

    @Override
    public int commuteReason(Commute commute) {
        return sqlSession.update("commute.commuteReason", commute);
    }
}
