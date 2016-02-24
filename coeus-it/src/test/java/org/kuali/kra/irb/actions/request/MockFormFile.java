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
package org.kuali.kra.irb.actions.request;

import org.apache.struts.upload.FormFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MockFormFile implements FormFile {

    private String fileName = "test";
    private byte fileData[] = null;

    public MockFormFile() {
        fileData = new byte[10];
        for (int i = 0; i < 10; i++) {
            fileData[i] = (byte) i;
        }
    }
    public void destroy() {
    
    }

    public String getContentType() {
       return null;
    }

    public byte[] getFileData() throws FileNotFoundException, IOException {
        return fileData;
    }

    public String getFileName() {
        return fileName;
    }

    public int getFileSize() {
        if (fileData == null) {
            return 0;
        }
        return fileData.length;
    }

    public InputStream getInputStream() throws FileNotFoundException, IOException {
        return null;
    }

    public void setContentType(String contentType) {
        
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileSize(int fileSize) {
        
    }
}
