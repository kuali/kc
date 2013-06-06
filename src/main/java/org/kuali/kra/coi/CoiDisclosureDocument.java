/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.coi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.bo.DocumentCustomData;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.kra.krms.KrmsRulesContext;
import org.kuali.kra.krms.service.KcKrmsFactBuilderService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;
import org.kuali.rice.krad.document.Copyable;
import org.kuali.rice.krad.document.SessionDocument;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krms.api.engine.Facts;
import org.kuali.rice.kim.api.identity.Person;

/**
 * 
 * This class is Coi disclosure document class
 */
@NAMESPACE(namespace=Constants.MODULE_NAMESPACE_COIDISCLOSURE)
@COMPONENT(component=ParameterConstants.DOCUMENT_COMPONENT)
public class CoiDisclosureDocument extends ResearchDocumentBase implements Copyable, SessionDocument, KrmsRulesContext { 
    
    public static final String DOCUMENT_TYPE_CODE = "COI";
    private List<CoiDisclosure> coiDisclosureList;
    
    /**
     * Constructs a CoiDisclosureDocument object.
     */
    public CoiDisclosureDocument() { 
        super();
        coiDisclosureList = new ArrayList<CoiDisclosure>();
        CoiDisclosure newCoiDisclosure = new CoiDisclosure();
        newCoiDisclosure.setCoiDisclosureDocument(this);
        coiDisclosureList.add(newCoiDisclosure);

    }

    public List<CoiDisclosure> getCoiDisclosureList() {
        return coiDisclosureList;
    }

    public void setCoiDisclosureList(List<CoiDisclosure> coiDisclosureList) {
        this.coiDisclosureList = coiDisclosureList;
    }

    @Override
    public String getDocumentTypeCode() {
        // TODO Auto-generated method stub
        return DOCUMENT_TYPE_CODE;
    } 
    
    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between CoiDisclosureDocument 
     * and CoiDisclosure to the outside world - aka a single CoiDisclosure field associated with CoiDocument
     * @return
     */
    public CoiDisclosure getCoiDisclosure() {
        if (coiDisclosureList.size() == 0) return null;
        return coiDisclosureList.get(0);
    }

    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between CoiDocument 
     * and CoiDisclosure to the outside world - aka a single CoiDisclosure field associated with CoiDocument
     * @param coiDisclosure
     */
    public void setCoiDisclosure(CoiDisclosure coiDisclosure) {
        coiDisclosureList.set(0, coiDisclosure);
    }

 
    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#buildListOfDeletionAwareLists()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        if (getCoiDisclosure() != null) {
            managedLists.addAll(getCoiDisclosure().buildListOfDeletionAwareLists());
        }
        managedLists.add(coiDisclosureList);
        return managedLists;
    }
    /*
     * This is just a holder, business logic needs to be added to this
     */
    public boolean isProcessComplete() {

        boolean isComplete = true;
        // if document has only been submitted for ceritification
        if (StringUtils.equalsIgnoreCase(getCoiDisclosure().getCoiDisclosureStatus().getCoiDisclosureStatusCode(), CoiDisclosureStatus.ROUTED_FOR_REVIEW)) {
            isComplete = true;
        } else {
            // approved/ disapproved
            if (!getDocumentHeader().getWorkflowDocument().isFinal() && !getDocumentHeader().getWorkflowDocument().isDisapproved()) {           
                isComplete = false;
            } 
        }
        return isComplete;

    }
    
    public void populateContextQualifiers(Map<String, String> qualifiers) {
        qualifiers.put("namespaceCode", Constants.MODULE_NAMESPACE_COIDISCLOSURE);
        qualifiers.put("name", KcKrmsConstants.CoiDisclosure.COI_DISCLOSURE_CONTEXT);
    }
    
    public void addFacts(Facts.Builder factsBuilder) {
        KcKrmsFactBuilderService fbService = KraServiceLocator.getService("coiDisclosureFactBuilderService");
        fbService.addFacts(factsBuilder, this);
    }
    
    @Override
    public void populateAgendaQualifiers(Map<String, String> qualifiers) {
        qualifiers.put(KcKrmsConstants.UNIT_NUMBER, getCoiDisclosure().getLeadUnitNumber());
    }

    @Override
    public List<? extends DocumentCustomData> getDocumentCustomData() {
        return new ArrayList();
    }
    
    @Override
    public String getCustomLockDescriptor(Person user) {
        String activeLockRegion = (String) GlobalVariables.getUserSession().retrieveObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION);
        String updatedTimestamp = "";
        if (this.getUpdateTimestamp() != null) {
            updatedTimestamp = (new SimpleDateFormat("MM/dd/yyyy KK:mm a").format(this.getUpdateTimestamp()));
        }
        if (StringUtils.isNotEmpty(activeLockRegion)) {
            return this.getCoiDisclosure().getCoiDisclosureNumber() + "-" + activeLockRegion + "-" + GlobalVariables.getUserSession().getPrincipalName() + "-" + updatedTimestamp;  
        }

        return null;
    }
    
    @Override
    public boolean useCustomLockDescriptors() {
        return true;
    }

}
