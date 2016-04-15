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
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
@Controller
public class RESTController {

    private final DvdLibraryDao dao;

    @Inject
    public RESTController(DvdLibraryDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/dvd/{dvdId}", method = RequestMethod.GET)
    @ResponseBody
    public Dvd getDvd(@PathVariable int dvdId) {
        return dao.getDvdById(dvdId);
    }

    @RequestMapping(value = "/dvd", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Dvd createDvd(@Valid @RequestBody Dvd dvd) {
        return dao.addDvd(dvd);
    }

    @RequestMapping(value = "/dvd/{dvdId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDvd(@PathVariable int dvdId) {
        dao.removeDvd(dvdId);
    }

    @RequestMapping(value = "/dvd/{dvdId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)      // @Valid           BindingResult result
    public void updateDvd(@PathVariable int dvdId, @Valid @RequestBody Dvd dvd) {
//        if (result.hasErrors()) {
//            
//        } else {
        dvd.setDvdId(dvdId);
        dao.updateDvd(dvd);
//        }
    }

    @RequestMapping(value = "/dvds", method = RequestMethod.GET)
    @ResponseBody
    public List<Dvd> getAllDvds() {
        return dao.getAllDvds();
    }
}
