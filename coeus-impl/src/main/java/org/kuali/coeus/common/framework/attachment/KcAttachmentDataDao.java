package org.kuali.coeus.common.framework.attachment;

public interface KcAttachmentDataDao {
    public byte[] getData(String id);
    public String saveData(byte[] data, String previousId);
    public void removeData(String id);
}
