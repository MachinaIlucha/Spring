package com.lesson6.Dz;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ItemController {

    private ItemDAO itemDAO;

    @Autowired
    public ItemController(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test", produces = "text/plan")
    public @ResponseBody
    String test() {
        return "ok";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/save-item", produces = "text/plain")
    public @ResponseBody
    ResponseEntity<String> save(HttpServletRequest request) throws Exception {
        Item item = new ObjectMapper().readValue(request.toString(), Item.class);
        itemDAO.save(item);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/save-read", produces = "text/plain")
    public @ResponseBody
    ResponseEntity<String> read(HttpServletRequest request) {
        String prId = request.getParameter("id");
        long id = Long.parseLong(prId);

        itemDAO.read(id);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/save-update", produces = "text/plain")
    public @ResponseBody
    ResponseEntity<String> update(HttpServletRequest request) throws Exception {
        Item item = new ObjectMapper().readValue(request.toString(), Item.class);
        itemDAO.update(item);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/save-delete", produces = "text/plain")
    public @ResponseBody
    ResponseEntity<String> delete(HttpServletRequest request) throws Exception {
        Item item = new ObjectMapper().readValue(request.toString(), Item.class);
        itemDAO.delete(item);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

}
