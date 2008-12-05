/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kuali.kra.irb.document;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.document.Copyable;
import org.kuali.core.document.SessionDocument;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.irb.bo.Protocol;

public class ProtocolDocument extends ResearchDocumentBase implements Copyable, SessionDocument { 
	
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 803158468103165087L;
    private List<Protocol> protocolList;
    
	/*
	private ProtocolVulnerableSub protocolVulnerableSub; 
	private ProtocolVoteAbstainees protocolVoteAbstainees; 
	private ProtocolSpecialReview protocolSpecialReview; 
	private ProtocolReviewers protocolReviewers; 
	private ProtocolResearchAreas protocolResearchAreas; 
	private ProtocolLocation protocolLocation; 
	private ProtocolKeyPersons protocolKeyPersons; 
	private ProtocolFundingSource protocolFundingSource; 
	private ProtocolCorrespondents protocolCorrespondents; 
	private ProtocolNotepad protocolNotepad; 
	private ProtocolCustomData protocolCustomData; 
	private ProtocolRelatedProjects protocolRelatedProjects; 
	private ProtocolReferences protocolReferences; 
	private ProtocolUserRoles protocolUserRoles; 
	private ProtoAmendRenewal protoAmendRenewal; 
	private ProtocolSubmission protocolSubmission; 
	private ProtocolInvestigators protocolInvestigators; 
	private ProtocolDocuments protocolDocuments; 
	private ProtocolActions protocolActions; 
	private ProtocolLinks protocolLinks; 
	*/
	
	public ProtocolDocument() { 
        super();
        protocolList = new ArrayList<Protocol>();
        Protocol newProtocol = new Protocol();
        protocolList.add(newProtocol);
	} 
	
    public void initialize() {
    }

    
    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between ProtocolDocument 
     * and Protocol to the outside world - aka a single Protocol field associated with ProtocolDocument
     * @return
     */
    public Protocol getProtocol() {
        return protocolList.get(0);
    }

    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between ProtocolDocument 
     * and Protocol to the outside world - aka a single Protocol field associated with ProtocolDocument
     * @param protocol
     */
    public void setProtocol(Protocol protocol) {
        protocolList.set(0, protocol);
    }


    /**
     *
     * @return
     */
    public List<Protocol> getProtocolList() {
        return protocolList;
    }

    /**
     *
     * @param protocolList
     */
    public void setProtocolList(List<Protocol> protocolList) {
        this.protocolList = protocolList;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(protocolList);
        return managedLists;

    }
}