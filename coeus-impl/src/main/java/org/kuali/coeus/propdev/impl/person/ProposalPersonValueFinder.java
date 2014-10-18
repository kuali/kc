package org.kuali.coeus.propdev.impl.person;

import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.krad.uif.view.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProposalPersonValueFinder extends UifKeyValuesFinderBase{
    @Override
    public List<KeyValue> getKeyValues(ViewModel model) {
        ProposalDevelopmentDocumentForm form = (ProposalDevelopmentDocumentForm) model;
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        // if a person is in multiple child proposals, then personnel attachments for that
        // person can only be managed at the parent. If however, the person is not in multiple children,
        // then they can only be managed at the child.
        for (ProposalPerson person : form.getDevelopmentProposal().getProposalPersons()) {
            if (form.getDevelopmentProposal().isInHierarchy()) {
                if (renderEditForPersonnelAttachment(person.getPersonId(), form.getDevelopmentProposal())) {
                    keyValues.add(new ConcreteKeyValue(person.getProposalPersonNumber().toString(), person.getFullName()));
                }
            } else {
                keyValues.add(new ConcreteKeyValue(person.getProposalPersonNumber().toString(), person.getFullName()));
            }
        }
        return keyValues;
    }

    protected boolean renderEditForPersonnelAttachment(String personId, DevelopmentProposal proposal) {
        if (personId != null) {
            boolean inMultiple = getHierarchyService().personInMultipleProposals(personId, proposal);
            return (proposal.isParent()) ? inMultiple : !inMultiple;
        }
        return true;
    }

    protected ProposalHierarchyService getHierarchyService() {
        return KcServiceLocator.getService(ProposalHierarchyService.class);
    }

}
