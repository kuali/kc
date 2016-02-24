/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
        KcFile attachmentDataSource = (KcFile) fileLine;
        try {
            byte[] data = attachmentDataSource.getData();
            long size = data.length;
            ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
            KRADUtils.addAttachmentToResponse(response,inputStream,attachmentDataSource.getType(),attachmentDataSource.getName(),size);
            response.flushBuffer();
        } catch (Exception e) {
            throw new RuntimeException("error occurred while downloading attachment", e);
        }
    }
}
