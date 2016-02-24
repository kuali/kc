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
package org.kuali.kra.coi;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.coeus.common.framework.krms.KrmsRulesContext;
import org.kuali.coeus.common.framework.krms.KcKrmsFactBuilderService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;
import org.kuali.rice.krad.document.Copyable;
import org.kuali.rice.krad.document.SessionDocument;
import org.kuali.rice.krms.api.engine.Facts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class is Coi disclosure document class
 */
@NAMESPACE(namespace=Constants.MODULE_NAMESPACE_COIDISCLOSURE)
@COMPONENT(component=ParameterConstants.DOCUMENT_COMPONENT)
public class CoiDisclosureDocument extends KcTransactionalDocumentBase implements Copyable, SessionDocument, KrmsRulesContext {
    
    public static final String DOCUMENT_TYPE_CODE = "COI";
    private List<CoiDisclosure> coiDisclosureList;
    

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
        KcKrmsFactBuilderService fbService = KcServiceLocator.getService("coiDisclosureFactBuilderService");
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
    
    public String getDocumentBoNumber() {
        return getCoiDisclosure().getCoiDisclosureNumber();
        
    }

}
