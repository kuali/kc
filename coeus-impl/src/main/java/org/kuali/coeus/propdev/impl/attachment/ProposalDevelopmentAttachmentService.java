package org.kuali.coeus.propdev.impl.attachment;


import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;

public interface ProposalDevelopmentAttachmentService {
    public void prepareAttachmentsForSave(DevelopmentProposal developmentProposal);
    public void standardizeAttachment(DevelopmentProposal developmentProposal, ProposalPersonBiography biography);
    public void standardizeAttachment(DevelopmentProposal developmentProposal, Narrative narrative);
}
