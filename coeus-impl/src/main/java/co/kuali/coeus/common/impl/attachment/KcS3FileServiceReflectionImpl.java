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
package co.kuali.coeus.common.impl.attachment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class KcS3FileServiceReflectionImpl implements KcS3FileService {

    private Object s3FileService;

    @Override
    public String createFile(Object s3File) {
        final Method createFile;
        try {
            createFile = s3FileService.getClass().getMethod(KcAttachmentDataS3Constants.CREATE_FILE_METHOD, s3File.getClass());
            return (String) createFile.invoke(s3FileService, s3File);
        } catch (NoSuchMethodException|InvocationTargetException|IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object retrieveFile(String id) {
        try {
            final Method retrieveFile = s3FileService.getClass().getMethod(KcAttachmentDataS3Constants.RETRIEVE_FILE_METHOD, String.class);
            return retrieveFile.invoke(s3FileService, id);
        } catch (NoSuchMethodException|InvocationTargetException|IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFile(String id) {
        try {
            final Method deleteFile = s3FileService.getClass().getMethod(KcAttachmentDataS3Constants.DELETE_FILE_METHOD, String.class);
            deleteFile.invoke(s3FileService, id);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getS3FileService() {
        return s3FileService;
    }

    public void setS3FileService(Object s3FileService) {
        this.s3FileService = s3FileService;
    }
}