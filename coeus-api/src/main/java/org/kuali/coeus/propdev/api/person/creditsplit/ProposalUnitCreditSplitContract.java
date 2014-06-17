package org.kuali.coeus.propdev.api.person.creditsplit;

import org.kuali.coeus.common.api.type.InvestigatorCreditTypeContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonLink;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public interface ProposalUnitCreditSplitContract extends ProposalPersonLink {

    String getUnitNumber();

    ScaleTwoDecimal getCredit();

    InvestigatorCreditTypeContract getInvestigatorCreditType();
}
