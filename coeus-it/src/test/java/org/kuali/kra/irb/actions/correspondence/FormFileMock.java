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
package org.kuali.kra.irb.actions.correspondence;

import org.apache.struts.upload.FormFile;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FormFileMock implements FormFile {
    
    public FormFileMock(){
        
    }

    public void destroy() {


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


    }

    public void setFileName(String fileName) {


    }

    public void setFileSize(int fileSize) {


    }

}
