/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.web.struts.form;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.kuali.core.service.DataDictionaryService;
import org.kuali.core.web.struts.form.KualiTransactionalDocumentFormBase;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.PropLocation;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.PropSpecialReview;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * This class...
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentForm extends KualiTransactionalDocumentFormBase {
    private String primeSponsorName;
    private PropLocation newPropLocation;
    private PropSpecialReview newPropSpecialReview;

    public ProposalDevelopmentForm() {
        super();
        this.setDocument(new ProposalDevelopmentDocument());
        newPropLocation=new PropLocation();
        newPropSpecialReview=new PropSpecialReview();
        DataDictionaryService dataDictionaryService = (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
        this.setHeaderNavigationTabs((dataDictionaryService.getDataDictionary().getDocumentEntry(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument.class.getName())).getHeaderTabNavigation());
    }


    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return (ProposalDevelopmentDocument) this.getDocument();
    }

    @Override
    public void populate(HttpServletRequest request) {

        this.setMethodToCall(null);
        this.setRefreshCaller(null);
        this.setAnchor(null);
        this.setTabStates(new HashMap<String, String>());
        this.setCurrentTabIndex(0);

        super.populate(request);
        ProposalDevelopmentDocument proposalDevelopmentDocument=getProposalDevelopmentDocument();
        if (proposalDevelopmentDocument.getOrganizationId()!=null && proposalDevelopmentDocument.getPropLocations().size()==0) {
            // populate 1st location.  Not sure yet
            PropLocation propLocation=new PropLocation();
            propLocation.setLocation(proposalDevelopmentDocument.getOrganization().getOrganizationName());
            proposalDevelopmentDocument.getPropLocations().add(propLocation);
        }
        proposalDevelopmentDocument.refreshReferenceObject("sponsor");
    }


    public PropLocation getNewPropLocation() {
        return newPropLocation;
    }


    public void setNewPropLocation(PropLocation newPropLocation) {
        this.newPropLocation = newPropLocation;
    }


    public PropSpecialReview getNewPropSpecialReview() {
        return newPropSpecialReview;
    }


    public void setNewPropSpecialReview(PropSpecialReview newPropSpecialReview) {
        this.newPropSpecialReview = newPropSpecialReview;
    }
    
    /* Reset method  
     * @param mapping    
     * @param request    
     * reset check box values in keyword panel
     */ 
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        ProposalDevelopmentDocument proposalDevelopmentDocument = this.getProposalDevelopmentDocument();
        List<PropScienceKeyword> keywords = proposalDevelopmentDocument.getKeywords();
        for(int i=0; i<keywords.size(); i++) {
            PropScienceKeyword propScienceKeyword = (PropScienceKeyword)keywords.get(i);
            propScienceKeyword.setSelectKeyword(false);
        }
    }


    /**
     * Sets the primeSponsorName attribute value.
     * @param primeSponsorName The primeSponsorName to set.
     */
    public void setPrimeSponsorName(String primeSponsorName) {
        this.primeSponsorName = primeSponsorName;
    }


    /**
     * Gets the primeSponsorName attribute. 
     * @return Returns the primeSponsorName.
     */
    public String getPrimeSponsorName() {
        return primeSponsorName;
    }

}
