package com.DZ2.DAO;

import com.DZ2.model.Filter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Demo {

    @RequestMapping(method = RequestMethod.GET, value = "/hi", produces = "text/plain")
    public @ResponseBody
    String test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfigApplicationContext.class);
        PlaneDAO planeDAO = (PlaneDAO) context.getBean("planeDAO");
        return planeDAO.read((long) 7001).toString();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test", produces = "text/plain")
    public @ResponseBody
    String test2() {
        return "efsaags";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test1", produces = "text/plain")
    public @ResponseBody
    void test3() {
        Filter.filter();
    }
}
