package lesson6.DZ2.DAO;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
