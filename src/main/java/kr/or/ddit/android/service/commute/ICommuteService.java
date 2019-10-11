package kr.or.ddit.android.service.commute;

import kr.or.ddit.android.model.Commute;

import java.util.List;

public interface ICommuteService {

    /**
     * 해당 유저의 출근, 퇴근을 판단하기 위한 데이터 참조
     * @param commute
     * @return
     */
    Commute commuteInfo (Commute commute );

    /**
     * 출근 데이터 저장 ( 날짜와 출근 시간, 유저아이디, 결과만 저장
     * @param commute
     * @return
     */
    int commuteCheck ( Commute commute );

    /**
     * 퇴근 데이터 저장
     * @param commute
     * @return
     */
    int commuteUpdate ( Commute commute );

    /**
     * 사유서 업데이트
     * @param commute
     * @return
     */
    int commuteReason ( Commute commute );

    /**
     * 근태 리스트
     * @param commute
     * @return
     */
    List<Commute> getCommuteList ( Commute commute );
}
