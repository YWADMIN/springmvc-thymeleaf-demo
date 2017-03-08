package cn.codergege.demo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.Map;

@Controller
public class IndexController {
    private final Logger log = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = { "/", "/index", "/home" }, method = RequestMethod.GET)
    public String index(Map map) {
        Date date = new Date();
        map.put("date", date);
        System.out.println("HomeController runs, date: " + date);
        log.info("HomeController runs, date: " + date);
        return "index";
    }

    @RequestMapping("/homepage")
    public String homePage() {
        return "redirect:/index";
    }
}
			