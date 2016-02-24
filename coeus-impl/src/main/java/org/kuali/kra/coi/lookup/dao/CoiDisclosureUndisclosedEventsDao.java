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
package org.kuali.kra.coi.lookup.dao;

import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.irb.personnel.ProtocolPerson;

import java.util.List;
import java.util.Map;

public interface CoiDisclosureUndisclosedEventsDao {

    public List<InstitutionalProposalPerson> getInstituteProposalPersons(Map<String,String> fieldValues);
    public List<AwardPerson> getAwardPersons(Map<String,String> fieldValues);
    public List<IacucProtocolPerson> getIacucProtocolPersons(Map<String,String> fieldValues);
    public List<ProtocolPerson> getIrbProtocolPersons(Map<String,String> fieldValues);

}
