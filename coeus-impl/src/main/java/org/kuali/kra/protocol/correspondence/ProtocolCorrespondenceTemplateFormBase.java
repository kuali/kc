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
package org.kuali.kra.protocol.correspondence;

import org.kuali.rice.kns.util.ActionFormUtilMap;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.LookupService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * Form of the ProtocolCorrespondenceTemplateBase.
 */
public abstract class ProtocolCorrespondenceTemplateFormBase extends KualiForm {

    private static final long serialVersionUID = 6043169784839779473L;
    
    private List<ProtocolCorrespondenceTypeBase> correspondenceTypes;    
    private List<ProtocolCorrespondenceTemplateBase> newDefaultCorrespondenceTemplates;
    private List<ProtocolCorrespondenceTemplateBase> newCorrespondenceTemplates;
    private List<ProtocolCorrespondenceTemplateList> replaceCorrespondenceTemplates;
    private List<ProtocolCorrespondenceTemplateBase> deletedCorrespondenceTemplates;
    
    private boolean readOnly;
    
    public ProtocolCorrespondenceTemplateFormBase() {
        super();
        this.readOnly = true;
        this.setCorrespondenceTypes(initCorrespondenceTypes());
        this.resetForm();
        this.deletedCorrespondenceTemplates = new ArrayList<ProtocolCorrespondenceTemplateBase>();
    }

    public void setCorrespondenceTypes(List<ProtocolCorrespondenceTypeBase> correspondenceTypes) {
        this.correspondenceTypes = correspondenceTypes;
    }

    public List<ProtocolCorrespondenceTypeBase> getCorrespondenceTypes() {
        return correspondenceTypes;
    }

    public void setNewDefaultCorrespondenceTemplates(List<ProtocolCorrespondenceTemplateBase> newDefaultCorrespondenceTemplates) {
        this.newDefaultCorrespondenceTemplates = newDefaultCorrespondenceTemplates;
    }

    public List<ProtocolCorrespondenceTemplateBase> getNewDefaultCorrespondenceTemplates() {
        return newDefaultCorrespondenceTemplates;
    }

    public void setNewCorrespondenceTemplates(List<ProtocolCorrespondenceTemplateBase> newCorrespondenceTemplates) {
        this.newCorrespondenceTemplates = newCorrespondenceTemplates;
    }

    public List<ProtocolCorrespondenceTemplateBase> getNewCorrespondenceTemplates() {
        return newCorrespondenceTemplates;
    }

	public List<ProtocolCorrespondenceTemplateList> getReplaceCorrespondenceTemplates() {
		return replaceCorrespondenceTemplates;
	}

	public void setReplaceCorrespondenceTemplates(
			List<ProtocolCorrespondenceTemplateList> replaceCorrespondenceTemplates) {
		this.replaceCorrespondenceTemplates = replaceCorrespondenceTemplates;
	}

	public List<ProtocolCorrespondenceTemplateBase> getDeletedCorrespondenceTemplates() {
        return deletedCorrespondenceTemplates;
    }

    public void setDeletedCorrespondenceTemplates(List<ProtocolCorrespondenceTemplateBase> deletedCorrespondenceTemplates) {
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
     * @return List&lt;ProtocolCorrespondenceTypeBase&gt;
     */
    @SuppressWarnings("unchecked")
    private List<ProtocolCorrespondenceTypeBase> initCorrespondenceTypes() {
        LookupService lookupService = KRADServiceLocatorWeb.getLookupService();
        return (List<ProtocolCorrespondenceTypeBase>) lookupService.findCollectionBySearchUnbounded(getProtocolCorrespondenceTypeBOClassHook(), new HashMap());
    }

    protected abstract Class<? extends ProtocolCorrespondenceTypeBase> getProtocolCorrespondenceTypeBOClassHook();
    
    /**
     * 
     * This method resets the input fields for the default correspondence templates and the new committee correspondence templates.
     */
    @SuppressWarnings("unused")
    public void resetForm() {
        this.newDefaultCorrespondenceTemplates = new ArrayList<ProtocolCorrespondenceTemplateBase>();
        this.newCorrespondenceTemplates = new ArrayList<ProtocolCorrespondenceTemplateBase>();
        this.replaceCorrespondenceTemplates = new ArrayList<ProtocolCorrespondenceTemplateList>();
        for (ProtocolCorrespondenceTypeBase correspondenceType : this.getCorrespondenceTypes()) {
            this.newDefaultCorrespondenceTemplates.add(getNewProtocolCorrespondenceTemplateInstanceHook());
            this.newCorrespondenceTemplates.add(getNewProtocolCorrespondenceTemplateInstanceHook());
            this.replaceCorrespondenceTemplates.add(new ProtocolCorrespondenceTemplateList());
            int typeIndex = correspondenceTypes.indexOf(correspondenceType);
            for (ProtocolCorrespondenceTemplateBase correspondenceTemplate : correspondenceType.getProtocolCorrespondenceTemplates()) {
            	this.replaceCorrespondenceTemplates.get(typeIndex).getList().add(getNewProtocolCorrespondenceTemplateInstanceHook());
            }
        }
    }

    protected abstract ProtocolCorrespondenceTemplateBase getNewProtocolCorrespondenceTemplateInstanceHook();
    
    

    @Override
    public void populate(HttpServletRequest request) {
        super.populate(request);
        
        // Clear values finder cache so that new committees are recognized by the committee values finder (drop-down box). 
        if (getActionFormUtilMap() != null && getActionFormUtilMap() instanceof ActionFormUtilMap) {
            ((ActionFormUtilMap) getActionFormUtilMap()).setCacheValueFinderResults(false);
        }
    }
}
