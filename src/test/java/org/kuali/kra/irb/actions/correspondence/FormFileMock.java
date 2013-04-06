/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.irb.actions.correspondence;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.struts.upload.FormFile;

public class FormFileMock implements FormFile {
    
    public FormFileMock(){
        
    }

    public void destroy() {
        // TODO Auto-generated method stub

    }

    public String getContentType() {
        return "xml";
    }

    public byte[] getFileData() throws FileNotFoundException, IOException {
        byte[] myByteArray = {'a', 'b', 'c', 'd'};
        return myByteArray;
    }

    public String getFileName() {
        return "test.xml";
    }

    public int getFileSize() {
        return 1;
    }

    public InputStream getInputStream() throws FileNotFoundException, IOException {
        InputStream stream = new ByteArrayInputStream(getFileData());
        return stream;
    }

    public void setContentType(String contentType) {
        // TODO Auto-generated method stub

    }

    public void setFileName(String fileName) {
        // TODO Auto-generated method stub

    }

    public void setFileSize(int fileSize) {
        // TODO Auto-generated method stub

    }

}
