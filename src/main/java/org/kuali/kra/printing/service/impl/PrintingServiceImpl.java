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
package org.kuali.kra.printing.service.impl;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.log4j.Logger;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.rice.kns.service.DateTimeService;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

/**
 * This class provides the functionality for printing any {@link Printable}
 * object. It uses the methods available in Printable object to generate XML,
 * fetch XSL style-sheets, then transforms the XML to a PDF after applying the
 * style sheet.
 * 
 */
public class PrintingServiceImpl implements PrintingService {

	private static final Logger LOG = Logger
			.getLogger(PrintingServiceImpl.class);

	private DateTimeService dateTimeService = null;

	/**
	 * This method receives a {@link Printable} object, generates XML for it,
	 * transforms into PDF after applying style-sheet and returns the PDF bytes
	 * as {@link AttachmentDataSource}
	 * 
	 * @param printableArtifact
	 *            to be printed
	 * @return {@link AttachmentDataSource} PDF bytes
	 * @throws PrintingException
	 *             in case of any errors occur during printing process
	 */
	public AttachmentDataSource print(Printable printableArtifact)
			throws PrintingException {
		PrintableAttachment printablePdf = null;

		try {
			ArrayList<ByteArrayOutputStream> pdfBaosList = new ArrayList<ByteArrayOutputStream>();
			Map<String, byte[]> streamMap = printableArtifact.renderXML();
			String[] bookmarks = new String[streamMap.size()];
			int i = 0;
            TransformerFactory factory = TransformerFactory.newInstance();
            FopFactory fopFactory = FopFactory.newInstance();
			for (Source source : printableArtifact.getXSLT()) {
                StreamSource xslt = (StreamSource) source;
                Transformer transformer = factory.newTransformer(xslt);
				for (Map.Entry<String, byte[]> xml : streamMap.entrySet()) {
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					ByteArrayInputStream inputStream = new ByteArrayInputStream(xml.getValue());
					Source src = new StreamSource(inputStream);
                    Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF,outputStream);
					Result res = new SAXResult(fop.getDefaultHandler());
					transformer.transform(src, res);
					pdfBaosList.add(outputStream);
					bookmarks[i] = xml.getKey();
					i++;
				}
			}
			printablePdf = new PrintableAttachment();
			printablePdf.setContent(mergePdfBytes(pdfBaosList
					.toArray(new ByteArrayOutputStream[0]), bookmarks));
			StringBuilder fileName = new StringBuilder();
			fileName.append(getReportName());
			fileName.append(Constants.PDF_FILE_EXTENSION);
			printablePdf.setFileName(fileName.toString());
			printablePdf.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
		} catch (FOPException e) {
			LOG.error(e.getMessage(), e);
			throw new PrintingException(e.getMessage(), e);
		} catch (TransformerConfigurationException e) {
			LOG.error(e.getMessage(), e);
			throw new PrintingException(e.getMessage(), e);
		} catch (TransformerException e) {
			LOG.error(e.getMessage(), e);
			throw new PrintingException(e.getMessage(), e);
		}
		return printablePdf;
	}

	private String getReportName() {
		// TODO: need to get the report name
		return new java.util.Date().toString();
	}

	/**
	 * @param byteArrayOutputStream
	 * @param bookmarks
	 * @return
	 * @throws PrintingException
	 */
	private byte[] mergePdfBytes(ByteArrayOutputStream byteArrayOutputStream[],
			String bookmarks[]) throws PrintingException {
		Document document = null;
		PdfWriter writer = null;
		ByteArrayOutputStream mergedPdfReport = new ByteArrayOutputStream();
		byte fileBytes[];
		int totalNumOfPages = 0;
		PdfReader[] pdfReaderArr = new PdfReader[byteArrayOutputStream.length];
		for (int count = 0; count < byteArrayOutputStream.length; count++) {
			if (byteArrayOutputStream[count] == null) {
				continue;
			}
			fileBytes = byteArrayOutputStream[count].toByteArray();
			PdfReader reader;
			try {
				reader = new PdfReader(fileBytes);
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
				throw new PrintingException(e.getMessage(), e);
			}
			pdfReaderArr[count] = reader;
			totalNumOfPages += reader.getNumberOfPages();
		}
		Calendar calendar = dateTimeService.getCurrentCalendar();
		String dateString = formateCalendar(calendar);
		StringBuilder footerPhStr = new StringBuilder();
		footerPhStr.append(" of ");
		footerPhStr.append(totalNumOfPages);
		footerPhStr
				.append("                                                                            ");
		footerPhStr
				.append("                                                                            ");
		footerPhStr
				.append("                                                            ");
		footerPhStr.append(dateString);
		Font font = FontFactory.getFont(FontFactory.TIMES, 8, Font.NORMAL,
				Color.BLACK);
		Phrase beforePhrase = new Phrase("Page ", font);
		Phrase afterPhrase = new Phrase(footerPhStr.toString(), font);
		HeaderFooter footer = new HeaderFooter(beforePhrase, afterPhrase);
		footer.setAlignment(Element.ALIGN_BASELINE);
		footer.setBorderWidth(0f);
		for (int count = 0; count < pdfReaderArr.length; count++) {
			if (byteArrayOutputStream[count] == null) {
				continue;
			}
			PdfReader reader = pdfReaderArr[count];
			int nop = reader.getNumberOfPages();

			if (count == 0) {
				document = nop > 0 ? new com.lowagie.text.Document(reader
						.getPageSizeWithRotation(1))
						: new com.lowagie.text.Document();
				try {
					writer = PdfWriter.getInstance(document, mergedPdfReport);
				} catch (DocumentException e) {
					LOG.error(e.getMessage(), e);
					throw new PrintingException(e.getMessage(), e);
				}
				document.setFooter(footer);
				document.open();
			}
			PdfContentByte cb = writer.getDirectContent();
			int pageCount = 0;
			while (pageCount < nop) {
				document.setPageSize(reader.getPageSize(++pageCount));
				document.newPage();
				document.setFooter(footer);
				PdfImportedPage page = writer
						.getImportedPage(reader, pageCount);

				cb.addTemplate(page, 1, 0, 0, 1, 0, 0);

				PdfOutline root = cb.getRootOutline();
				if (pageCount == 1) {
					String pageName = bookmarks[count];
					cb.addOutline(new PdfOutline(root, new PdfDestination(
							PdfDestination.FITH), pageName), pageName);
				}
			}
		}
		if (document != null) {
			try {
				document.close();
				return mergedPdfReport.toByteArray();
			} catch (Exception e) {
				LOG.error("Exception occured because the generated PDF document has no pages",e);
			}
		}
		return null;
	}

	private String formateCalendar(Calendar calendar) {
		DateFormat dateFormat = new SimpleDateFormat("M/d/yy h:mm a");
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 
	 * This class populates the bytes of PDF document to be generated
	 */
	private class PrintableAttachment extends AttachmentDataSource {
		private byte[] streamData;

		public byte[] getContent() {
			return streamData;
		}

		public void setContent(byte[] streamData) {
			this.streamData = streamData;
		}
	}

	/**
	 * @return the dateTimeService
	 */
	public DateTimeService getDateTimeService() {
		return dateTimeService;
	}

	/**
	 * @param dateTimeService
	 *            the dateTimeService to set
	 */
	public void setDateTimeService(DateTimeService dateTimeService) {
		this.dateTimeService = dateTimeService;
	}

}
