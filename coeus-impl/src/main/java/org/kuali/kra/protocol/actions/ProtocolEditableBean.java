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
