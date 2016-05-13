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

package org.kuali.coeus.instprop.impl.api.service;

import org.kuali.coeus.instprop.impl.api.dto.InstitutionalProposalDto;
import org.kuali.coeus.instprop.impl.api.dto.IpPersonDto;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;

import java.util.*;

public interface InstitutionalProposalApiService {

    public void addCustomData(InstitutionalProposal institutionalProposal, InstitutionalProposalDto institutionalProposalDto);

    public InstitutionalProposalDocument saveInitialProposal(InstitutionalProposal proposal, String description) throws WorkflowException;

    public void updateProposalLog(String proposalLogNumber, InstitutionalProposalDocument ipDocument);

    public String createProposalLog(InstitutionalProposalDto ipDto, IpPersonDto pi);

    public void addPersons(InstitutionalProposalDocument proposalDocument, List<IpPersonDto> personDtos);

    public InstitutionalProposalPerson addPerson(InstitutionalProposalDocument proposalDocument, IpPersonDto personDto);

    public ArrayList<LinkedHashMap> getProposalPersons(ArrayList<LinkedHashMap> persons, String roleCode);

    public void addRequiredFields(InstitutionalProposal proposal, InstitutionalProposalDocument ipDocument, String proposalNumber);

    public void initializeCostTotals(InstitutionalProposal proposal);

    public void updateDataObjectFromDto(Object existingDataObject, Object input);

    public void initializeData(InstitutionalProposal proposal);

    public void initializeCollections(InstitutionalProposal proposal);

    }
