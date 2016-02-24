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
package org.kuali.kra.printing.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

public class PrintingTestUtils {

    private static final Log LOG = LogFactory.getLog(PrintingTestUtils.class);

	public static String FILE_DIR = System.getProperty("java.io.tmpdir");

	public static void writePdftoDisk(AttachmentDataSource pdfBytes,
			String reportType) {
		if (pdfBytes == null || pdfBytes.getData() == null) {
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
			fos.write(pdfBytes.getData());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
