package org.kuali.coeus.propdev.api.person.attachment;

import org.kuali.coeus.propdev.api.core.NumberedProposal;
import org.kuali.coeus.sys.api.model.Describable;
import org.kuali.coeus.sys.api.model.Named;

public interface ProposalPersonBiographyContract extends Describable, Named, NumberedProposal {

    Integer getProposalPersonNumber();

    String getPersonId();

    Integer getBiographyNumber();

    Integer getRolodexId();

    String getType();

    PropPerDocTypeContract getPropPerDocType();

    ProposalPersonBiographyAttachmentContract getPersonnelAttachment();
}
