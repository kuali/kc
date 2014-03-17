/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.struts.action.ActionMapping;
import org.kuali.rice.kns.web.struts.form.KualiForm;

import javax.servlet.http.HttpServletRequest;

public abstract class ResearchAreasFormBase extends KualiForm {

    private static final long serialVersionUID = 5924974293486200804L;
    private String researchAreas;
    private String searchResults;
    private String lookupResultsBOClassName;
    private String researchAreaCode;
    private String addRA;
    private String sqlScripts;
    private String deletedRas;
    private boolean authorizedToMaintainResearchAreas;



    public ResearchAreasFormBase() {
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

    public boolean isAuthorizedToMaintainResearchAreas() {
        return authorizedToMaintainResearchAreas;
    }

    public void setAuthorizedToMaintainResearchAreas(boolean authorizedToMaintainResearchAreas) {
        this.authorizedToMaintainResearchAreas = authorizedToMaintainResearchAreas;
    }
    
}
