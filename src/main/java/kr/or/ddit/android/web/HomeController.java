package kr.or.ddit.android.web;

import kr.or.ddit.android.model.Commute;
import kr.or.ddit.android.model.Employee;
import kr.or.ddit.android.service.commute.ICommuteService;
import kr.or.ddit.android.service.employee.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Resource(name = "userService")
    private IUserService userService;

    @Resource(name = "commuteService")
    private ICommuteService commuteService;

    @RequestMapping(path = "login", method = RequestMethod.POST)
    public String androidLoginWithRequestAndResponse(HttpServletRequest request, Model model) {
        Map result = new HashMap<String, String>();
        String nowTime = getCurrentTime("YYYY,M,d");

        String userId = request.getParameter("emp_id");
        String pass = request.getParameter("pass");

        Employee user = userService.checkUser(new Employee(userId, pass));

        if (user != null) {
            model.addAttribute("user", user);
        }

        return "jsonView";
    }

    private String getCurrentTime(String timeFormat) {
        // TODO Auto-generated method stub
        return new SimpleDateFormat(timeFormat).format(System.currentTimeMillis());
    }

    @RequestMapping(path = "checkCommute", method = RequestMethod.POST)
    public String checkCommute(Commute commute, Data nowDt, Model model) throws ParseException {

//        SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
//        Date nowDt = sf.parse(now_Dt);
//        sf = new SimpleDateFormat("YYYY-MM-dd");
//        commute.setDt(sf.format(nowDt));

        SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd");
        commute.setDt(sf.format(nowDt));

        Commute comData = commuteService.commuteInfo(commute);

        sf = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
        if ( comData != null ) {

            String nowTimestr = sf.format(nowDt);
            Date time = sf.parse("18:00:00");
            Date nowTime = sf.parse(nowTimestr);

            commute.setOw_tm(nowTimestr);
            if (time.getTime() - nowTime.getTime() > 0) {
                commute.setRes("비정상근무/조퇴");
            } else {
                commute.setRes(comData.getRes());
            }

            commuteService.commuteUpdate(commute);

        } else {
            String nowTimestr = sf.format(nowDt);
            Date time = sf.parse("09:00:00");
            Date nowTime = sf.parse(nowTimestr);

            commute.setGtw_tm(nowTimestr);
            if (time.getTime() - nowTime.getTime() > 0) {
                commute.setRes("정시출근");
            } else {
                commute.setRes("지각");
            }

            commuteService.commuteCheck(commute);
        }

        model.addAttribute("res", commute.getRes());

        return "jsonView";
    }

    @RequestMapping(path = "commuteList", method = RequestMethod.POST)
    public String commuteList(Commute commute, Model model) {

        if ( commute.getEmp_id() != null ) {
            List<Commute> commuteList = commuteService.getCommuteList(commute);
            model.addAttribute("commuteList", commuteList);
        }

        return "jsonView";
    }

    @RequestMapping(path = "setReas", method = RequestMethod.POST)
    public String setReas(Commute commute, Model model) {
        if (commute != null) {
            int result = commuteService.commuteReason(commute);
            model.addAttribute("result", result);
        }
        return "jsonView";
    }

    @RequestMapping(path = "commuteInfo", method = RequestMethod.POST)
    public String commuteInfo(Commute commute, Model model) {
        if ( commute != null ) {
            commute = commuteService.commuteInfo(commute);
            model.addAttribute("commute", commute);
        }
        return "jsonView";
    }

    @GetMapping(path = "test")
    public String test(String test, Model model) {
        model.addAttribute("test", test);
        return "jsonView";
    }

}
