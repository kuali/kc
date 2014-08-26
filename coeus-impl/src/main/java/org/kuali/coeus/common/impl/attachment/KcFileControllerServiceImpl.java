package org.kuali.coeus.common.impl.attachment;


import org.apache.log4j.Logger;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.rice.krad.file.FileMeta;
import org.kuali.rice.krad.util.KRADUtils;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.rice.krad.web.service.impl.FileControllerServiceImpl;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.util.List;

@Component("kcFileControllerService")
public class KcFileControllerServiceImpl extends FileControllerServiceImpl {
    private static final Logger LOG = Logger.getLogger(KcFileControllerServiceImpl.class);

    @Override
    protected void sendFileFromLineResponse(UifFormBase form, HttpServletResponse response, List<FileMeta> collection,
                                            FileMeta fileLine) {
        KcFile attachmentDataSource = ((KcFile)fileLine);
        byte[] data = attachmentDataSource.getData();
        long size = data.length;
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
            KRADUtils.addAttachmentToResponse(response,inputStream,attachmentDataSource.getType(),attachmentDataSource.getName(),size);
            response.flushBuffer();
        } catch (Exception e) {
            LOG.error("Error while downloading attachment");
            throw new RuntimeException("IOException occurred while downloading attachment", e);
        }
    }
}
