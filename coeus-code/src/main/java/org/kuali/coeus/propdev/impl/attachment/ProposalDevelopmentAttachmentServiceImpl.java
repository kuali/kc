package org.kuali.coeus.propdev.impl.attachment;

import org.apache.commons.io.FilenameUtils;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("proposalDevelopmentAttachmentService")
public class ProposalDevelopmentAttachmentServiceImpl implements ProposalDevelopmentAttachmentService {

    @Autowired
    @Qualifier("kcAttachmentService")
    private KcAttachmentService kcAttachmentService;

    public void standardizeAttachments(DevelopmentProposal developmentProposal) {
            for (Narrative narrative : developmentProposal.getInstituteAttachments()) {
                String extension = FilenameUtils.getExtension(narrative.getName());
                narrative.refreshReferenceObject("narrativeType");
                String newFileName = getKcAttachmentService().checkAndReplaceInvalidCharacters(getKcAttachmentService().checkAndReplaceSpecialCharacters((narrative.getNarrativeType().getDescription())));
                narrative.setName(newFileName + "." + extension);
            }
            for (Narrative narrative : developmentProposal.getNarratives()) {
                String extension = FilenameUtils.getExtension(narrative.getName());
                narrative.refreshReferenceObject("narrativeType");
                String newFileName = getKcAttachmentService().checkAndReplaceInvalidCharacters(getKcAttachmentService().checkAndReplaceSpecialCharacters((narrative.getNarrativeType().getDescription())));
                narrative.setName(newFileName + "." + extension);
            }
    }
    public KcAttachmentService getKcAttachmentService() {
        return kcAttachmentService;
    }

    public void setKcAttachmentService(KcAttachmentService kcAttachmentService) {
        this.kcAttachmentService = kcAttachmentService;
    }
}
