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
package org.kuali.kra.protocol.actions;

import java.io.Serializable;

public interface ProtocolEditableBean extends ProtocolActionBean, Serializable {

    public boolean getGeneralInfoEnabled();
    
    public void setGeneralInfoEnabled(boolean generalInfoEnabled);

    public boolean getFundingSourceEnabled();

    public void setFundingSourceEnabled(boolean fundingSourceEnabled);

    public boolean getProtocolReferencesEnabled();

    public void setProtocolReferencesEnabled(boolean protocolReferencesEnabled);

    public boolean getProtocolOrganizationsEnabled();

    public void setProtocolOrganizationsEnabled(boolean protocolOrganizationsEnabled);

    public boolean getSubjectsEnabled();

    public void setSubjectsEnabled(boolean subjectsEnabled);

    public boolean getAddModifyAttachmentsEnabled();

    public void setAddModifyAttachmentsEnabled(boolean addModifyAttachmentsEnabled);

    public boolean getAreasOfResearchEnabled();

    public void setAreasOfResearchEnabled(boolean areasOfResearchEnabled);

    public boolean getSpecialReviewEnabled();

    public void setSpecialReviewEnabled(boolean specialReviewEnabled);

    public boolean getProtocolPersonnelEnabled();

    public void setProtocolPersonnelEnabled(boolean protocolPersonnelEnabled);

    public boolean getOthersEnabled();

    public void setOthersEnabled(boolean othersEnabled);
    
    public boolean getProtocolPermissionsEnabled();

    public void setProtocolPermissionsEnabled(boolean protocolPermissionsEnabled);

    public boolean getQuestionnaireEnabled();

    public void setQuestionnaireEnabled(boolean questionnaireEnabled);
    
}
