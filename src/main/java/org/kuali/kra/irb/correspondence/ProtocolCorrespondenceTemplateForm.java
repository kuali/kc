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
package org.kuali.kra.irb.correspondence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kuali.rice.kns.util.ActionFormUtilMap;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.LookupService;

/**
 * 
 * Form of the ProtocolCorrespondenceTemplate.
 */
public class ProtocolCorrespondenceTemplateForm extends KualiForm {

    private static final long serialVersionUID = 6043169784839779473L;
    
    private List<ProtocolCorrespondenceType> correspondenceTypes;    
    private List<ProtocolCorrespondenceTemplate> newDefaultCorrespondenceTemplates;
    private List<ProtocolCorrespondenceTemplate> newCorrespondenceTemplates;
    private List<ProtocolCorrespondenceTemplateList> replaceCorrespondenceTemplates;
    private List<ProtocolCorrespondenceTemplate> deletedCorrespondenceTemplates;
    
    private boolean readOnly;
    
    public ProtocolCorrespondenceTemplateForm() {
        super();
        this.readOnly = true;
        this.setCorrespondenceTypes(initCorrespondenceTypes());
        this.resetForm();
        this.deletedCorrespondenceTemplates = new ArrayList<ProtocolCorrespondenceTemplate>();
    }

    public void setCorrespondenceTypes(List<ProtocolCorrespondenceType> correspondenceTypes) {
        this.correspondenceTypes = correspondenceTypes;
    }

    public List<ProtocolCorrespondenceType> getCorrespondenceTypes() {
        return correspondenceTypes;
    }

    public void setNewDefaultCorrespondenceTemplates(List<ProtocolCorrespondenceTemplate> newDefaultCorrespondenceTemplates) {
        this.newDefaultCorrespondenceTemplates = newDefaultCorrespondenceTemplates;
    }

    public List<ProtocolCorrespondenceTemplate> getNewDefaultCorrespondenceTemplates() {
        return newDefaultCorrespondenceTemplates;
    }

    public void setNewCorrespondenceTemplates(List<ProtocolCorrespondenceTemplate> newCorrespondenceTemplates) {
        this.newCorrespondenceTemplates = newCorrespondenceTemplates;
    }

    public List<ProtocolCorrespondenceTemplate> getNewCorrespondenceTemplates() {
        return newCorrespondenceTemplates;
    }

	public List<ProtocolCorrespondenceTemplateList> getReplaceCorrespondenceTemplates() {
		return replaceCorrespondenceTemplates;
	}

	public void setReplaceCorrespondenceTemplates(
			List<ProtocolCorrespondenceTemplateList> replaceCorrespondenceTemplates) {
		this.replaceCorrespondenceTemplates = replaceCorrespondenceTemplates;
	}

	public List<ProtocolCorrespondenceTemplate> getDeletedCorrespondenceTemplates() {
        return deletedCorrespondenceTemplates;
    }

    public void setDeletedCorrespondenceTemplates(List<ProtocolCorrespondenceTemplate> deletedCorrespondenceTemplates) {
        this.deletedCorrespondenceTemplates = deletedCorrespondenceTemplates;
    }
    
    public boolean getReadOnly() {
    	return readOnly;
    }
    
    public void setReadOnly(boolean readOnly) {
    	this.readOnly = readOnly;
    }

    /**
     * This method returns all existing correspondence types from the database
     * 
     * @return List<ProtocolCorrespondenceType> 
     */
    @SuppressWarnings("unchecked")
    private List<ProtocolCorrespondenceType> initCorrespondenceTypes() {
        LookupService lookupService = KRADServiceLocatorWeb.getLookupService();
        return (List<ProtocolCorrespondenceType>) lookupService.findCollectionBySearchUnbounded(ProtocolCorrespondenceType.class, new HashMap());
    }

    /**
     * 
     * This method resets the input fields for the default correspondence templates and the new committee correspondence templates.
     */
    @SuppressWarnings("unused")
    public void resetForm() {
        this.newDefaultCorrespondenceTemplates = new ArrayList<ProtocolCorrespondenceTemplate>();
        this.newCorrespondenceTemplates = new ArrayList<ProtocolCorrespondenceTemplate>();
        this.replaceCorrespondenceTemplates = new ArrayList<ProtocolCorrespondenceTemplateList>();
        for (ProtocolCorrespondenceType correspondenceType : this.getCorrespondenceTypes()) {
            this.newDefaultCorrespondenceTemplates.add(new ProtocolCorrespondenceTemplate());
            this.newCorrespondenceTemplates.add(new ProtocolCorrespondenceTemplate());
            this.replaceCorrespondenceTemplates.add(new ProtocolCorrespondenceTemplateList());
            int typeIndex = correspondenceTypes.indexOf(correspondenceType);
            for (ProtocolCorrespondenceTemplate correspondenceTemplate : correspondenceType.getProtocolCorrespondenceTemplates()) {
            	this.replaceCorrespondenceTemplates.get(typeIndex).getList().add(new ProtocolCorrespondenceTemplate());
            }
        }
    }

    @Override
    public void populate(HttpServletRequest request) {
        super.populate(request);
        
        // Clear values finder cache so that new committees are recognized by the committee values finder (drop-down box). 
        if (getActionFormUtilMap() != null && getActionFormUtilMap() instanceof ActionFormUtilMap) {
            ((ActionFormUtilMap) getActionFormUtilMap()).setCacheValueFinderResults(false);
        }
    }
}
