package org.kuali.coeus.propdev.impl.attachment;


import org.springframework.web.multipart.MultipartFile;

public interface ProposalDevelopmentAttachment {
    public MultipartFile getMultipartFile();

    public void setMultipartFile(MultipartFile multipartFile);

    public String getUploadUserDisplay();

}
