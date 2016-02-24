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
