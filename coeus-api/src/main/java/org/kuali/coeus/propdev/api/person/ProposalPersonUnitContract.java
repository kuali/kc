package org.kuali.coeus.propdev.api.person;

import org.kuali.coeus.propdev.api.person.creditsplit.ProposalUnitCreditSplitContract;

import java.util.List;

public interface ProposalPersonUnitContract extends ProposalPersonLink {

    String getUnitNumber();

    boolean isLeadUnit();

    List<? extends ProposalUnitCreditSplitContract> getCreditSplits();
}
