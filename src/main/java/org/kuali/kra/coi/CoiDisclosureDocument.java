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
package org.kuali.kra.coi;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.document.Copyable;
import org.kuali.rice.kns.document.SessionDocument;
import org.kuali.rice.kns.service.ParameterConstants;
import org.kuali.rice.kns.service.ParameterConstants.COMPONENT;
import org.kuali.rice.kns.service.ParameterConstants.NAMESPACE;

/**
 * 
 * This class is Coi disclosure document class
 */
@NAMESPACE(namespace=Constants.MODULE_NAMESPACE_COIDISCLOSURE)
@COMPONENT(component=ParameterConstants.DOCUMENT_COMPONENT)
public class CoiDisclosureDocument extends ResearchDocumentBase implements Copyable, SessionDocument { 
    
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

        return isComplete;

    }

}
