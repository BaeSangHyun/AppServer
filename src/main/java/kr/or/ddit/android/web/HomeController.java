package kr.or.ddit.android.web;

import kr.or.ddit.android.model.Commute;
import kr.or.ddit.android.model.Employee;
import kr.or.ddit.android.service.commute.ICommuteService;
import kr.or.ddit.android.service.employee.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

        if (user == null) {
        } else {
            model.addAttribute("user", user);
        }

        return "jsonView";
    }

    private String getCurrentTime(String timeFormat) {
        // TODO Auto-generated method stub
        return new SimpleDateFormat(timeFormat).format(System.currentTimeMillis());
    }

    @RequestMapping(path = "checkCommute", method = RequestMethod.POST)
    public String checkCommute(HttpServletRequest request, Model model) throws ParseException {
        Commute commute = new Commute();
        commute.setEmp_id(request.getParameter("emp_id"));

        Date nowDt = new Date();
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
                commute.setRes("정상근무");
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


}
