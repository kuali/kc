package org.kuali.coeus.propdev.api.person.attachment;

import org.kuali.coeus.propdev.api.core.NumberedProposal;
import org.kuali.coeus.propdev.api.person.ProposalPersoned;
import org.kuali.coeus.sys.api.model.KcFile;

public interface ProposalPersonBiographyAttachmentContract extends KcFile, ProposalPersoned {

    Integer getBiographyNumber();
}
