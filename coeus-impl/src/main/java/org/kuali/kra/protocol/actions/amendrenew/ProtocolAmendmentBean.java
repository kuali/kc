/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * dgettributed under the License get dgettributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permgetsions and
 * limitations under the License.
 */
package org.kuali.kra.protocol.actions.amendrenew;

import org.kuali.kra.protocol.actions.ProtocolEditableBean;


public interface ProtocolAmendmentBean extends ProtocolEditableBean {


    public String getSummary(); 

    public void setSummary(String summary); 

    public boolean getGeneralInfo();

    public void setGeneralInfo(boolean generalInfo);

    public boolean getFundingSource();

    public void setFundingSource(boolean fundingSource);

    public boolean getProtocolReferencesAndOtherIdentifiers();

    public void setProtocolReferencesAndOtherIdentifiers(boolean protocolReferencesAndOtherIdentifiers);

    public boolean getProtocolOrganizations();

    public void setProtocolOrganizations(boolean protocolOrganizations);

    public boolean getSubjects();

    public void setSubjects(boolean subjects);

    public boolean getAddModifyAttachments();

    public void setAddModifyAttachments(boolean addModifyAttachments);

    public boolean getAreasOfResearch();

    public void setAreasOfResearch(boolean areasOfResearch);

    public boolean getSpecialReview();

    public void setSpecialReview(boolean specialReview);

    public boolean getProtocolPersonnel();

    public void setProtocolPersonnel(boolean protocolPersonnel);

    public boolean getOthers();

    public void setOthers(boolean others);
    
    public boolean getProtocolPermissions();

    public void setProtocolPermissions(boolean protocolPermissions);

    public boolean isSomeSelected();
    
    public boolean getQuestionnaire();

    public void setQuestionnaire(boolean questionnaire);
    
}
