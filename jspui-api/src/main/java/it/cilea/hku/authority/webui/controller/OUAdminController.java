/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 * 
 * http://www.dspace.org/license/
 * 
 * The document has moved 
 * <a href="https://svn.duraspace.org/dspace/licenses/LICENSE_HEADER">here</a>
 */
package it.cilea.hku.authority.webui.controller;

import it.cilea.hku.authority.service.ApplicationService;
import it.cilea.hku.authority.webui.dto.OrganizationUnitDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

/**
 * Concrete SpringMVC controller is used to list admin OU page
 * 
 * @author pascarelli
 * 
 */
public class OUAdminController extends ParameterizableViewController
{
    protected Log log = LogFactory.getLog(getClass());

    protected ApplicationService applicationService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {

        ModelAndView mav = super.handleRequest(request, response);     
        String errore = request.getParameter("error");
        OrganizationUnitDTO grantDTO = new OrganizationUnitDTO();
        if (errore != null && Boolean.parseBoolean(errore) == true)
        {
            // errore
            mav.getModel().put("error", "jsp.dspace-admin.hku.error.add-ou");
        }
        mav.getModel().put("dto", grantDTO);
        return mav;
    }

    public ApplicationService getApplicationService()
    {
        return applicationService;
    }

    public void setApplicationService(ApplicationService applicationService)
    {
        this.applicationService = applicationService;
    }

}