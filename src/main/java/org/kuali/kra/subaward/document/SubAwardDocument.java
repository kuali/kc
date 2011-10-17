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
package org.kuali.kra.subaward.document;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.rice.kns.document.Copyable;
import org.kuali.rice.kns.document.SessionDocument;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

public class SubAwardDocument extends ResearchDocumentBase implements  Copyable, SessionDocument{
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5454534590787613256L;
    
    private List<SubAward> subAwardList;    
    public static final String DOCUMENT_TYPE_CODE = "SAWD";
    @Override
    public String getDocumentTypeCode() {
        // TODO Auto-generated method stub
        return DOCUMENT_TYPE_CODE;
    }
    /**
     * Constructs a subAwardDocument object
     */
    public SubAwardDocument(){        
        super();        
        init();
    }
    public SubAward getSubAward() {
        return getSubAwardList().size() > 0 ? getSubAwardList().get(0) : new SubAward();
    }    
    public void setSubAward(SubAward subAward){
        subAwardList.set(0, subAward);
    }
    public void setSubAwardList(List<SubAward> subAwardList) {
        this.subAwardList = subAwardList;
    }
    public List<SubAward> getSubAwardList() {
        return subAwardList;
    }    

   
    /**
     * This method specifies if this document may be edited; i.e. it's only initiated or saved
     * @return
     */
    public boolean isEditable() {
        KualiWorkflowDocument workflowDoc = getDocumentHeader().getWorkflowDocument();
        return workflowDoc.stateIsInitiated() || workflowDoc.stateIsSaved(); 
    }
    
    protected void init() {
        subAwardList = new ArrayList<SubAward>();
        subAwardList.add(new SubAward());       
    }
}
