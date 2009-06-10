/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.actions.request;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.struts.upload.FormFile;

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
