package org.kuali.coeus.propdev.api.attachment;


import org.kuali.coeus.sys.api.model.KcFile;

public interface NarrativeAttachmentContract extends KcFile {
    Integer getModuleNumber();
    String getProposalNumber();
}
