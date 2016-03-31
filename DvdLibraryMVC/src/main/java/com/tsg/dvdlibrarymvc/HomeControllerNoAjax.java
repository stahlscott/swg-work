/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.dvdlibrarymvc;

import com.tsg.dvdlibrarymvc.dao.DvdLibraryDao;
import com.tsg.dvdlibrarymvc.dto.Dvd;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
@Controller
public class HomeControllerNoAjax {

    private final DvdLibraryDao dao;

    @Inject
    public HomeControllerNoAjax(DvdLibraryDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displayDvdListNoAjax", method = RequestMethod.GET)
    public String displayDvdListNoAjax(Model model) {
        List<Dvd> dList = dao.getAllDvds();
        model.addAttribute("dvdList", dList);

        return "displayDvdListNoAjax";
    }

    @RequestMapping(value = "/displayNewDvdFormNoAjax", method = RequestMethod.GET)
    public String displayNewDvdFormNoAjax(Model model) {
        Dvd dvd = new Dvd();
        model.addAttribute("dvd", dvd);
        return "newDvdFormNoAjax";
    }

    @RequestMapping(value = "/addNewDvdNoAjax", method = RequestMethod.POST)
    public String addNewDvdNoAjax(@Valid @ModelAttribute("dvd") Dvd dvd, BindingResult result) {
        if (result.hasErrors()) {
            return "newDvdFormNoAjax";
        } else {
            dao.addDvd(dvd);
            return "redirect:displayDvdListNoAjax";
        }
    }

    @RequestMapping(value = "/deleteDvdNoAjax", method = RequestMethod.GET)
    public String deleteDvdNoAjax(HttpServletRequest req) {
        int dvdId = Integer.parseInt(req.getParameter("dvdId"));
        dao.removeDvd(dvdId);

        return "redirect:displayDvdListNoAjax";
    }

    @RequestMapping(value = "/displayEditDvdFormNoAjax", method = RequestMethod.GET)
    public String displayEditDvdFormNoAjax(HttpServletRequest req, Model model) {
        int dvdId = Integer.parseInt(req.getParameter("dvdId"));
        Dvd dvd = dao.getDvdById(dvdId);
        model.addAttribute("dvd", dvd);
        return "editDvdFormNoAjax";
    }

    @RequestMapping(value = "/editDvdFormNoAjax", method = RequestMethod.POST)
    public String editDvdFormNoAjax(@Valid @ModelAttribute("dvd") Dvd dvd, BindingResult result) {
        if (result.hasErrors()) {
            return "editDvdFormNoAjax";
        } else {
            dao.updateDvd(dvd);
            return "redirect:displayDvdListNoAjax";
        }
    }
}
