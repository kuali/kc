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


public class KcAttachmentDataS3Constants {

    static final String S3_INTEGRATION_ENABLED = "S3_INTEGRATION_ENABLED";
    static final String S3_DUAL_SAVE_ENABLED = "S3_DUAL_SAVE_ENABLED";
    static final String S3_DUAL_RETRIEVE_ENABLED = "S3_DUAL_RETRIEVE_ENABLED";

    static final String RETRIEVE_FILE_METHOD = "retrieveFile";
    static final String GET_FILE_CONTENTS_METHOD = "getFileContents";
    static final String S3_FILE_CLASS = "co.kuali.coeus.s3.api.S3File";
    static final String SET_ID_METHOD = "setId";
    static final String SET_FILE_CONTENTS_METHOD = "setFileContents";
    static final String CREATE_FILE_METHOD = "createFile";
    static final String GET_FILE_META_DATA_METHOD = "getFileMetaData";
    static final String DELETE_FILE_METHOD = "deleteFile";

    private KcAttachmentDataS3Constants() {
        throw new UnsupportedOperationException("do not call");
    }
}
