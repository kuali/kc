package org.kuali.coeus.propdev.impl.person;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinder;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.krad.uif.view.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProposalPersonValueFinder extends UifKeyValuesFinderBase{
    @Override
    public List<KeyValue> getKeyValues(ViewModel model) {
        ProposalDevelopmentDocumentForm form = (ProposalDevelopmentDocumentForm) model;
        List<KeyValue> keyValues = new ArrayList<KeyValue>();

        for (ProposalPerson person : form.getDevelopmentProposal().getProposalPersons()) {
            keyValues.add(new ConcreteKeyValue(person.getProposalPersonNumber().toString(), person.getFullName()));
        }
        return keyValues;
    }
}
