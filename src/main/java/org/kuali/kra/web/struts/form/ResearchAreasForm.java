/*
 * Copyright 2005-2010 The Kuali Foundation
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

import org.apache.struts.action.ActionMapping;
import org.kuali.rice.kns.web.struts.form.KualiForm;

public class ResearchAreasForm extends KualiForm {

    private static final long serialVersionUID = 5924974293486200804L;
    private String researchAreas;
    private String searchResults;
    private String lookupResultsBOClassName;
    private String researchAreaCode;
    private String addRA;
    private String sqlScripts;
    private String deletedRas;


    /**
     * Constructs a ResearchAreasForm.
     */
    public ResearchAreasForm() {
        super();
    }

    /**
     * 
     * This method is a hook to research area hierarchy ajax call.  Based on the data come with jquery
     * ajax call, it then check if research area is duplicate, save changes made to research area
     * hierarchy, or retrieve the child research areas.
     * The return data are wrapped in <h3> tag and saved in researchAreas property.  The form
     * data is returned to jquery ajax call, and it then process the data wrapped in <h3> tag.
     * 
     * @return
     */
    public String getResearchAreas() {
//        if (StringUtils.isNotBlank(addRA) && addRA.equals("Y")) {
//            if (KraServiceLocator.getService(ResearchAreasService.class).isResearchAreaExist(researchAreaCode, deletedRas)) {
//                setResearchAreas("<h3>true</h3>");
//            }else {
//                setResearchAreas("<h3>false</h3>");
//            }
//        } else if (StringUtils.isNotBlank(addRA) && addRA.equals("S")) {
//            try {
//                KraServiceLocator.getService(ResearchAreasService.class).saveResearchAreas(sqlScripts);
//                String error = (String) GlobalVariables.getUserSession().retrieveObject("raError");
//                if (StringUtils.isNotBlank(error)) {
//                    setResearchAreas("<h3>" + error + "</h3>");
//                    GlobalVariables.getUserSession().addObject("raError", (Object) null);
//                } else {
//                    setResearchAreas("<h3>Success</h3>");
//                }
//            }
//            catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else if (StringUtils.isNotBlank(addRA) && addRA.equals("A")) {
//            setResearchAreas(KraServiceLocator.getService(ResearchAreasService.class).getSubResearchAreasForTreeView(
//                    researchAreaCode, true));
//        } else {
//            setResearchAreas(KraServiceLocator.getService(ResearchAreasService.class).getSubResearchAreasForTreeView(
//                    researchAreaCode, false));
//        }
        return researchAreas;
    }

    public void setResearchAreas(String researchAreas) {
        this.researchAreas = researchAreas;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // FIXME : just a temporary soln. it always get the methodtocall='refresh' after it started properly the first time.
        // need to investigate this.
        this.setMethodToCall("");
        addRA = "";
        deletedRas = "";
    }


    @Override
    public void populate(HttpServletRequest request) {
        super.populate(request);
        this.setResearchAreas("");
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
