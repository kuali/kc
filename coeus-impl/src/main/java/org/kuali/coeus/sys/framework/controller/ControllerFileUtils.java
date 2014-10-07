package org.kuali.coeus.sys.framework.controller;

import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.rice.krad.util.KRADUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

public final class ControllerFileUtils {

    private ControllerFileUtils() {
        throw new UnsupportedOperationException("do not call");
    }

    public final static void streamToResponse(KcFile attachmentDataSource, HttpServletResponse response) throws Exception {
        streamToResponse(attachmentDataSource.getData(),attachmentDataSource.getName(),attachmentDataSource.getType(),response);
    }

    public final static void streamToResponse(byte[] fileContents, String fileName, String fileContentType, HttpServletResponse response) throws Exception {
        long size = fileContents.length;
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(fileContents)) {
            KRADUtils.addAttachmentToResponse(response, inputStream, fileContentType, fileName, size);
            response.flushBuffer();
        }
    }
}
