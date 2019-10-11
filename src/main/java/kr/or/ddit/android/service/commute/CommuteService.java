package kr.or.ddit.android.service.commute;

import kr.or.ddit.android.model.Commute;
import kr.or.ddit.android.repository.commute.ICommuteDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommuteService implements ICommuteService {

    @Resource(name = "commuteDao")
    private ICommuteDao commuteDao;

    @Override
    public Commute commuteInfo(Commute commute) {
        return commuteDao.commuteInfo(commute);
    }

    @Override
    public int commuteCheck(Commute commute) {
        return commuteDao.commuteCheck(commute);
    }

    @Override
    public int commuteUpdate(Commute commute) {
        return commuteDao.commuteUpdate(commute);
    }

    @Override
    public int commuteReason(Commute commute) {
        return commuteDao.commuteReason(commute);
    }

    @Override
    public List<Commute> getCommuteList(Commute commute) {
        return commuteDao.getCommuteList(commute);
    }
}
