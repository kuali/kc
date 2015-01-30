/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
