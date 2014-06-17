package org.kuali.coeus.propdev.api.person.attachment;

import org.kuali.coeus.propdev.api.person.ProposalPersonLink;
import org.kuali.coeus.sys.api.model.KcFile;

public interface ProposalPersonBiographyAttachmentContract extends KcFile, ProposalPersonLink {

    Integer getBiographyNumber();
}
