package edu.cmpe283.prj2.controller;

import edu.cmpe283.prj2.dao.InventoryDao;
import edu.cmpe283.prj2.manager.InventoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: vplociennik
 * Date: 11/27/13
 * Time: 3:14 AM
 */
@Controller
public class HomeController {

    @Autowired
    private InventoryManager inventoryManager;

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        model.addAttribute("list", inventoryManager.getHostList());
        model.addAttribute("list2", inventoryManager.getVmList());
        System.out.println(inventoryManager.getHostList());
        return "home";
    }

}
