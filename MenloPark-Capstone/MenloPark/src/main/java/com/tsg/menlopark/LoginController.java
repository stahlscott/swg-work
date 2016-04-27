/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.menlopark;

import com.tsg.menlopark.dao.CmsDao;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Adam Coate <adamcoate1@gmail.com>
 */
@Controller
public class LoginController {
    
    private final CmsDao dao;
    
    @Inject
    public LoginController(CmsDao dao) {
        this.dao = dao;
    }
    
     @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String displayLoginPage() {
        return "loginPage";
    }
}
