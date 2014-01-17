/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.printing.util;

import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

public class PrintingTestUtils {

	public static String FILE_DIR = System.getProperty("java.io.tmpdir");

	public static void writePdftoDisk(AttachmentDataSource pdfBytes,
			String reportType) {
		if (pdfBytes == null || pdfBytes.getContent() == null) {
			return;
		}
		try {
			FileOutputStream fos = new FileOutputStream(new File(
					PrintingTestUtils.FILE_DIR
					        + File.separator
							+ reportType
							+ "_"
							+ new SimpleDateFormat("ddMMyyyy_HHmmss")
									.format(new java.util.Date()) + ".pdf"));
			fos.write(pdfBytes.getContent());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
