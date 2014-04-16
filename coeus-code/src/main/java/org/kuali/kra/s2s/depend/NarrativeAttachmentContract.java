package org.kuali.kra.s2s.depend;


import org.kuali.coeus.sys.api.model.KcFile;

public interface NarrativeAttachmentContract extends KcFile {
    Integer getModuleNumber();
    String getProposalNumber();
}
