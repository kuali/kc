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
package org.kuali.kra.irb.noteattachment;

import org.apache.struts.upload.FormFile;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.common.framework.attachment.AttachmentFile.CreateException;

import java.io.IOException;

import static org.hamcrest.core.Is.is;

/**
 * Tests for {@link AttachmentFile ProtocolAttachmentFile}.
 */
public class ProtocolAttachmentFileTest {

    Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
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
        AttachmentFile file = AttachmentFile.createFromFormFile(formFile);
        
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
        AttachmentFile.createFromFormFile(null);
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
        AttachmentFile file = AttachmentFile.createFromFormFile(formFile);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat(file.getName(), is(fileName.substring(fileName.length() - AttachmentFile.MAX_FILE_NAME_LENGTH)));
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
        AttachmentFile file = AttachmentFile.createFromFormFile(formFile);
        
        this.context.assertIsSatisfied();
        
        Assert.assertThat(file.getName(), is(fileName));
        Assert.assertThat(file.getType(), is(fileType.substring(fileType.length() - AttachmentFile.MAX_FILE_TYPE_LENGTH)));
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
        
        AttachmentFile.createFromFormFile(formFile);
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
