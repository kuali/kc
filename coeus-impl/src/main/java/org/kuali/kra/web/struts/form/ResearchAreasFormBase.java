/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
