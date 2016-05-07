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

    public InstitutionalProposalDocument saveDocument(InstitutionalProposalDocument proposalDocument) throws WorkflowException;

    public void routeDocument(InstitutionalProposalDocument proposalDocument) throws WorkflowException;

    public String getValidationErrors();

    public boolean isDocInModifiableState(WorkflowDocument workflowDocument);

    public void addCustomData(InstitutionalProposal institutionalProposal, InstitutionalProposalDto institutionalProposalDto);

    public InstitutionalProposalDocument saveInitialProposal(InstitutionalProposal proposal, String description) throws WorkflowException;

    public void updateProposalLog(boolean createProposalLog, String proposalLogNumber, InstitutionalProposalDocument ipDocument);

    public String createProposalLog(boolean createProposalLog, InstitutionalProposalDto ipDto);

    public void addPersons(InstitutionalProposalDocument proposalDocument, List<IpPersonDto> personDtos);

    public InstitutionalProposalPerson addPerson(InstitutionalProposalDocument proposalDocument, IpPersonDto personDto);

    public ArrayList<LinkedHashMap> getProposalPersons(ArrayList<LinkedHashMap> persons, String roleCode);

    public ProposalLog addProposalLog(InstitutionalProposalDto ipDto);

    public void addRequiredFields(InstitutionalProposal proposal, InstitutionalProposalDocument ipDocument, String proposalNumber);

    public void initializeCostTotals(InstitutionalProposal proposal);

    public Object convertObject(Object input, Class clazz);

    public void updateDataObjectFromDto(Object existingDataObject, Object input);

    public Object getNewDataObject(Class clazz);

    public void initializeData(InstitutionalProposal proposal);
}
