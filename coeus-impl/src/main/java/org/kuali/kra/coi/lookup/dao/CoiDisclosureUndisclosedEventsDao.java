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
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.coi.lookup.dao;

import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;

import java.util.List;
import java.util.Map;

public interface CoiDisclosureUndisclosedEventsDao {

    public List<ProposalPerson> getDevelopmentProposalPersons(Map<String,String> fieldValues);
    public List<InstitutionalProposalPerson> getInstituteProposalPersons(Map<String,String> fieldValues);
    public List<AwardPerson> getAwardPersons(Map<String,String> fieldValues);
    public List<IacucProtocolPerson> getIacucProtocolPersons(Map<String,String> fieldValues);
    public List<ProtocolPerson> getIrbProtocolPersons(Map<String,String> fieldValues);

}
