/*
 * Copyright 2006-2008 The Kuali Foundation
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

import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.LookupService;
import org.kuali.rice.kns.web.struts.form.KualiForm;

/**
 * 
 * Form of the ProtocolCorrespondenceTemplate.
 */
public class ProtocolCorrespondenceTemplateForm extends KualiForm {

    private static final long serialVersionUID = 6043169784839779473L;
    
    private String testName;
    private ProtocolCorrespondenceTemplate testTemplate;
    
    private List<ProtocolCorrespondenceType> correspondenceTypes;    
    private List<ProtocolCorrespondenceTemplate> defaultCorrespondenceTemplates;
    private List<ProtocolCorrespondenceTemplate> newCorrespondenceTemplates;
    
    public ProtocolCorrespondenceTemplateForm() {
        super();
        if (getCorrespondenceTypes() == null) {
            this.setCorrespondenceTypes(initCorrespondenceTypes());
        }
        if (getDefaultCorrespondenceTemplates() == null) {
            this.setDefaultCorrespondenceTemplates(initNewCorrespondenceTemplates());
        }
        if (getNewCorrespondenceTemplates() == null) {
            this.setNewCorrespondenceTemplates(initNewCorrespondenceTemplates());
        }
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestTemplate(ProtocolCorrespondenceTemplate testTemplate) {
        this.testTemplate = testTemplate;
    }

    public ProtocolCorrespondenceTemplate getTestTemplate() {
        return testTemplate;
    }

    public void setCorrespondenceTypes(List<ProtocolCorrespondenceType> correspondenceTypes) {
        this.correspondenceTypes = correspondenceTypes;
    }

    public List<ProtocolCorrespondenceType> getCorrespondenceTypes() {
        return correspondenceTypes;
    }

    public void setDefaultCorrespondenceTemplates(List<ProtocolCorrespondenceTemplate> defaultCorrespondenceTemplates) {
        this.defaultCorrespondenceTemplates = defaultCorrespondenceTemplates;
    }

    public List<ProtocolCorrespondenceTemplate> getDefaultCorrespondenceTemplates() {
        return defaultCorrespondenceTemplates;
    }

    public void setNewCorrespondenceTemplates(List<ProtocolCorrespondenceTemplate> newCorrespondenceTemplates) {
        this.newCorrespondenceTemplates = newCorrespondenceTemplates;
    }

    public List<ProtocolCorrespondenceTemplate> getNewCorrespondenceTemplates() {
        return newCorrespondenceTemplates;
    }

    /**
     * This method returns all existing correspondence types from the database
     * 
     * @return List<ProtocolCorrespondenceType> 
     */
    @SuppressWarnings("unchecked")
    private List<ProtocolCorrespondenceType> initCorrespondenceTypes() {
        LookupService lookupService = KNSServiceLocator.getLookupService();
        return (List<ProtocolCorrespondenceType>) lookupService.findCollectionBySearchUnbounded(ProtocolCorrespondenceType.class, new HashMap());
    }
    
    /**
     * 
     * This method returns an initialized list of ProtocolCorrespondenceTemplate with exactly the same elements
     * as there are ProtocolCorrespondenceType.
     * @return List<ProtocolCorrespondenceTemplate>
     */
    public List<ProtocolCorrespondenceTemplate> initNewCorrespondenceTemplates() {
        List<ProtocolCorrespondenceTemplate> newCorrespondenceTemplates = new ArrayList<ProtocolCorrespondenceTemplate>();
        for(ProtocolCorrespondenceType correspondenceType : getCorrespondenceTypes()) {
            newCorrespondenceTemplates.add(new ProtocolCorrespondenceTemplate());
        }
        return newCorrespondenceTemplates;
    }

}
