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
package org.kuali.kra.irb.noteattachment;

import static org.hamcrest.core.Is.is;

import java.io.IOException;

import org.apache.struts.upload.FormFile;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentFile.CreateException;

/**
 * Tests for {@link ProtocolAttachmentFile ProtocolAttachmentFile}.
 */
public class ProtocolAttachmentFileTest {

    Mockery context = new JUnit4Mockery();
    
    /**
     * Tests that the factory method works correctly with normal input.
     * @throws Exception if bad happens.
     */
    @Test
    public void createFromFormFileBasic() throws Exception {

        final String fileName = "fileName";
        final String fileType = "fileType";
        final byte[] data = new byte[] {1,};
        
        FormFile formFile = createMockFormFileWithExpectations(fileName, fileType, data);
        ProtocolAttachmentFile file = ProtocolAttachmentFile.createFromFormFile(formFile);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat(file.getName(), is(fileName));
        Assert.assertThat(file.getType(), is(fileType));
        Assert.assertThat(file.getData(), is(data));
    }
    
    /**
     * Tests that factory method properly handles a null form file.
     */
    @Test(expected=IllegalArgumentException.class)
    public void createFromFormFileNullFormFile() {
        ProtocolAttachmentFile.createFromFormFile(null);
    }
    
    /**
     * Test that confirms proper handling of long file names.
     * @throws Exception if bad happens.
     */
    @Test
    public void createFromFormFileLongName() throws Exception {
        final String fileName = "fileNamethrows Exceptionthrows Exceptionthrows Exceptionthrows Exceptionthrows Exceptionthrows Exceptionthrows Exceptionthrows Exceptionthrows Exceptionthrows Exception";
        final String fileType = "fileType";
        final byte[] data = new byte[] {1,};
        
        FormFile formFile = createMockFormFileWithExpectations(fileName, fileType, data);
        ProtocolAttachmentFile file = ProtocolAttachmentFile.createFromFormFile(formFile);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat(file.getName(), is(fileName.substring(fileName.length() - ProtocolAttachmentFile.MAX_FILE_NAME_LENGTH)));
        Assert.assertThat(file.getType(), is(fileType));
        Assert.assertThat(file.getData(), is(data));
    }
    
    /**
     * Test that confirms proper handling of long file types.
     * @throws Exception if bad happens.
     */
    @Test
    public void createFromFormFileLongType() throws Exception {
        final String fileName = "fileName";
        final String fileType = "fileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileTypefileType";
        final byte[] data = new byte[] {1,};
        
        FormFile formFile = createMockFormFileWithExpectations(fileName, fileType, data);
        ProtocolAttachmentFile file = ProtocolAttachmentFile.createFromFormFile(formFile);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat(file.getName(), is(fileName));
        Assert.assertThat(file.getType(), is(fileType.substring(fileType.length() - ProtocolAttachmentFile.MAX_FILE_TYPE_LENGTH)));
        Assert.assertThat(file.getData(), is(data));
    }
    
    /**
     * Tests that IOExceptions are properly handled by wrapping in a CreateException.
     * @throws Exception if bad happens.
     */
    @Test(expected=CreateException.class)
    public void testCreateException() throws Exception {
        final FormFile formFile = this.context.mock(FormFile.class);
        final String fileName = "fileName";
        final String fileType = "fileType";
        
        this.context.checking(new Expectations() {{
            one(formFile).getFileName();
            will(returnValue(fileName));
            
            one(formFile).getContentType();
            will(returnValue(fileType));
            
            one(formFile).getFileData();
            will(throwException(new IOException()));
        }});
        
        ProtocolAttachmentFile.createFromFormFile(formFile);
        this.context.assertIsSatisfied();
    }
    
    /**
     * 
     * Creates a form file setting jmock expectations.
     * @param fileName the fileName
     * @param type the type
     * @param data the data
     * @return the form file.
     * @throws IOException if unable to access file data
     */
    private FormFile createMockFormFileWithExpectations(final String fileName, final String type, final byte[] data) throws IOException {
        final FormFile formFile = this.context.mock(FormFile.class);
        
        this.context.checking(new Expectations() {{
            one(formFile).getFileName();
            will(returnValue(fileName));
            
            one(formFile).getContentType();
            will(returnValue(type));
            
            one(formFile).getFileData();
            will(returnValue(data));
        }});
        
        return formFile;
    }
}
