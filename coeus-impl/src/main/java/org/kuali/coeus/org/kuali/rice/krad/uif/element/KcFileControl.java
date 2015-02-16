package org.kuali.coeus.org.kuali.rice.krad.uif.element;


import org.kuali.coeus.sys.framework.controller.KcFileService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.uif.control.FileControl;

public class KcFileControl extends FileControl {

    @Override
    public void performInitialization(Object model) {
        long maxUploadSize = KcServiceLocator.getService(KcFileService.class).getMaxUploadSizeParameter();
        this.setOnChangeScript("Kc.Global.validateAttachmentFile(this," + maxUploadSize + ");");
        super.performInitialization(model);
    }
}
