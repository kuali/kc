package org.kuali.coeus.propdev.impl.attachment;

import org.apache.commons.io.FilenameUtils;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiographyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component("proposalDevelopmentAttachmentService")
public class ProposalDevelopmentAttachmentServiceImpl implements ProposalDevelopmentAttachmentService {

    @Autowired
    @Qualifier("kcAttachmentService")
    private KcAttachmentService kcAttachmentService;

    @Autowired
    @Qualifier("proposalPersonBiographyService")
    private ProposalPersonBiographyService proposalPersonBiographyService;

    @Override
    public void prepareAttachmentsForSave(DevelopmentProposal developmentProposal) {
        for (Narrative narrative : developmentProposal.getInstituteAttachments()) {
            standardizeAttachment(developmentProposal,narrative);
        }
        for (Narrative narrative : developmentProposal.getNarratives()) {
            standardizeAttachment(developmentProposal,narrative);
        }
        for (ProposalPersonBiography biography : developmentProposal.getPropPersonBios()) {
            standardizeAttachment(developmentProposal,biography);
            getProposalPersonBiographyService().prepareProposalPersonBiographyForSave(developmentProposal,biography);
        }
    }

    @Override
    public void standardizeAttachment(DevelopmentProposal developmentProposal, Narrative narrative){
        if (narrative.getNarrativeAttachment() != null) {
            narrative.refreshReferenceObject("narrativeType");
            String extension = FilenameUtils.getExtension(narrative.getName());
            String newFileName = getKcAttachmentService().checkAndReplaceInvalidCharacters(
                    getKcAttachmentService().checkAndReplaceSpecialCharacters(narrative.getNarrativeType().getDescription()));
            narrative.setName(newFileName + "." + extension);
        }
    }

    @Override
    public void standardizeAttachment(DevelopmentProposal developmentProposal, ProposalPersonBiography biography) {
            if (biography.getPersonnelAttachment() != null) {
                biography.refreshReferenceObject("propPerDocType");
                String extension = FilenameUtils.getExtension(biography.getName());
                String fullName = getPerson(developmentProposal,biography.getProposalPersonNumber()).getFullName();
                String newFileName = getKcAttachmentService().checkAndReplaceInvalidCharacters(
                        getKcAttachmentService().checkAndReplaceSpecialCharacters(fullName + "_" + biography.getPropPerDocType().getDescription()));
                biography.setName(newFileName + "." + extension);
            }
    }

    protected ProposalPerson getPerson(DevelopmentProposal developmentProposal, Integer proposalPersonNumber) {
        for (ProposalPerson person : developmentProposal.getProposalPersons()) {
            if (proposalPersonNumber.equals(person.getProposalPersonNumber())) {
                return person;
            }
        }
        return null;
    }

    public KcAttachmentService getKcAttachmentService() {
        return kcAttachmentService;
    }

    public void setKcAttachmentService(KcAttachmentService kcAttachmentService) {
        this.kcAttachmentService = kcAttachmentService;
    }

    public ProposalPersonBiographyService getProposalPersonBiographyService() {
        return proposalPersonBiographyService;
    }

    public void setProposalPersonBiographyService(ProposalPersonBiographyService proposalPersonBiographyService) {
        this.proposalPersonBiographyService = proposalPersonBiographyService;
    }
}
