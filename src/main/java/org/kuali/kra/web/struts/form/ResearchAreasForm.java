/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.web.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.ResearchAreasService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;

public class ResearchAreasForm extends KualiForm {

    private String researchAreas;
    private String searchResults;
    private String lookupResultsBOClassName;
    private String researchAreaCode;
    private String addRA;
    private String sqlScripts;
    private String deletedRas;

    private static final Log LOG = LogFactory.getLog(ResearchAreasForm.class);

    /**
     * Constructs a ResearchAreasForm.
     */
    public ResearchAreasForm() {
        super();
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // FIXME : just a temporary soln. it always get the methodtocall='refresh' after it started properly the first time.
        // need to investigate this.
        this.setMethodToCall("");
        addRA = "";
        deletedRas = "";
    }

    public String getResearchAreas() {
        if (StringUtils.isNotBlank(addRA) && addRA.equals("Y")) {
            if (KraServiceLocator.getService(ResearchAreasService.class).isResearchAreaExist(researchAreaCode, deletedRas)) {
                setResearchAreas("<h3>true</h3>");
            }else {
                setResearchAreas("<h3>false</h3>");
            }
        } else if (StringUtils.isNotBlank(addRA) && addRA.equals("S")) {
            KraServiceLocator.getService(ResearchAreasService.class).saveResearchAreas(sqlScripts);
            String error = (String) GlobalVariables.getUserSession().retrieveObject("raError");
            if (StringUtils.isNotBlank(error)) {
                setResearchAreas("<h3>" + error + "</h3>");
                GlobalVariables.getUserSession().addObject("raError", (Object) null);
            } else {
                setResearchAreas("<h3>Success</h3>");
            }
        } else {
            setResearchAreas(KraServiceLocator.getService(ResearchAreasService.class).getSubResearchAreasForTreeView(
                    researchAreaCode));
        }
        return researchAreas;
    }

    public void setResearchAreas(String researchAreas) {
        this.researchAreas = researchAreas;
    }

    @Override
    public void populate(HttpServletRequest request) {
        // TODO Auto-generated method stub
        super.populate(request);
        this.setResearchAreas("");
        this.getResearchAreaCode();
    }

    public String getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(String searchResults) {
        this.searchResults = searchResults;
    }

    public String getLookupResultsBOClassName() {
        return lookupResultsBOClassName;
    }

    public void setLookupResultsBOClassName(String lookupResultsBOClassName) {
        this.lookupResultsBOClassName = lookupResultsBOClassName;
    }

    public String getResearchAreaCode() {
        return researchAreaCode;
    }

    public void setResearchAreaCode(String researchAreaCode) {
        this.researchAreaCode = researchAreaCode;
    }

    public String getAddRA() {
        return addRA;
    }

    public void setAddRA(String addRA) {
        this.addRA = addRA;
    }

    public String getSqlScripts() {
        return sqlScripts;
    }

    public void setSqlScripts(String sqlScripts) {
        this.sqlScripts = sqlScripts;
    }

    public String getDeletedRas() {
        return deletedRas;
    }

    public void setDeletedRas(String deletedRas) {
        this.deletedRas = deletedRas;
    }


}
