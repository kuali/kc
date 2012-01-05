/*
 * Copyright 2005-2010 The Kuali Foundation
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
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.printing.service.WatermarkService;
import org.kuali.kra.util.watermark.WatermarkBean;
import org.kuali.kra.util.watermark.WatermarkConstants;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 
 * This class for implementing the watermark service methods, which decorates the PDF Data with the appropriate watermark and return
 * that pdfByte Array
 */
public class WatermarkServiceImpl implements WatermarkService {


    private static final Log LOG = LogFactory.getLog(WatermarkServiceImpl.class);

    /**
     * This method for applying watermark to the pdf
     * 
     * @return pdfFileData
     */
    public byte[] applyWatermark(byte[] pdfBytes, WatermarkBean watermarkBean) throws Exception {

        byte[] pdfFileData = pdfBytes;

        try {
            if (watermarkBean != null) {
                ByteArrayOutputStream byteArrayOutputStream = attachWatermarking(watermarkBean, pdfFileData);
                pdfFileData = byteArrayOutputStream.toByteArray();
            }
        }
        catch (Exception exception) {
            LOG.error("Exception occured in WatermarkServiceImpl. Water mark Exception: " + exception.getMessage());
        }

        return pdfFileData;
    }


    /**
     * This method for attach watermark with PDF with the help of PdfReader and PdfStamper
     * 
     * @param pdfContent pdfContent
     * @throws DocumentException throws this exception if cannot decorate the pdf
     * @return byteArrayOutputStream
     */
    private ByteArrayOutputStream attachWatermarking(WatermarkBean watermarkBean, byte pdfContent[]) {

        PdfReader pdfReader;
        PdfReader reader;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfStamper pdfStamp;
        Document document = null;
        PdfWriter writer = null;
    
        int nop;
        try {
            reader = new PdfReader(pdfContent);
            pdfReader = new PdfReader(pdfContent);
            nop = reader.getNumberOfPages();
            document = nop > 0 ? new com.lowagie.text.Document(reader
                    .getPageSizeWithRotation(1))
                    : new com.lowagie.text.Document();
            writer = PdfWriter.getInstance(document, byteArrayOutputStream);
            BaseFont bf_courier = BaseFont.createFont(BaseFont.COURIER, "Cp1252", false);
            BaseFont bf_courier1 = BaseFont.createFont(BaseFont.COURIER, "Cp1252", false); 
            if(watermarkBean.getPosition().equals(WatermarkConstants.WATERMARK_POSITION_FOOTER)){
             HeaderFooter footer = new HeaderFooter(new Phrase(watermarkBean.getText(), new Font(bf_courier1)), false);
             footer.setBorder(Rectangle.NO_BORDER);
            footer.setBorderColor(watermarkBean.getFont().getColor());
            if(watermarkBean.getAlignment().equals(WatermarkConstants.ALIGN_CENTER)){
             footer.setAlignment(Element.ALIGN_CENTER);}
            else if(watermarkBean.getAlignment().equals(WatermarkConstants.ALIGN_RIGHT)){
                footer.setAlignment(Element.ALIGN_RIGHT); 
            }
            else if(watermarkBean.getAlignment().equals(WatermarkConstants.ALIGN_LEFT)){
                footer.setAlignment(Element.ALIGN_LEFT); 
            }
             document.setFooter(footer);}
            else
            {
              HeaderFooter header = new HeaderFooter(
                         new Phrase(watermarkBean.getText(), new Font(bf_courier)), false);
             header.setBorder(Rectangle.NO_BORDER);
             if(watermarkBean.getAlignment().equals(WatermarkConstants.ALIGN_CENTER)){
                 header.setAlignment(Element.ALIGN_CENTER);}
                else if(watermarkBean.getAlignment().equals(WatermarkConstants.ALIGN_RIGHT)){
                    header.setAlignment(Element.ALIGN_RIGHT); 
                }
                else if(watermarkBean.getAlignment().equals(WatermarkConstants.ALIGN_LEFT)){
                    header.setAlignment(Element.ALIGN_LEFT); 
                }
              document.setHeader(header);}
              document.open();
              int pageCount = 1;
              PdfContentByte cb = writer.getDirectContent();
              document.setPageSize(reader.getPageSize(pageCount));
              document.newPage();
              PdfImportedPage page = writer.getImportedPage(reader, pageCount);
              cb.addTemplate(page, 1, 0, 0, 1, 0, 0);
      PdfOutline root = cb.getRootOutline();
      document.close();
      byte[]bs=byteArrayOutputStream.toByteArray();
      pdfReader=new PdfReader(bs);
            pdfStamp = new PdfStamper(pdfReader, byteArrayOutputStream);
            decorateWatermark(pdfStamp, watermarkBean);
        }
        catch (IOException decorateWatermark) {
            LOG.error("Exception occured in WatermarkServiceImpl. Water mark Exception: " + decorateWatermark.getMessage());
        }
        catch (DocumentException documentException) {
            LOG.error("Exception occured in WatermarkServiceImpl. Water mark Exception: " + documentException.getMessage());
        }
        return byteArrayOutputStream;
    }


    /**
     * 
     * This method for Decorating the PDF with watermark.
     * 
     * @param pdfStamper - wrapper for pdf content byte and assists in decorating PDF LOg the exception if cannot open/read the file
     *        for decoration
     */
    private void decorateWatermark(PdfStamper watermarkPdfStamper, WatermarkBean watermarkBean) {
        PdfReader pdfReader = watermarkPdfStamper.getReader();
        int pageCount = pdfReader.getNumberOfPages();
        int pdfPageNumber = 0;

        PdfContentByte pdfContents;
        Rectangle rectangle;
        while (pdfPageNumber < pageCount) {
            pdfPageNumber++;
            // pdfContents = watermarkPdfStamper.getOverContent(pdfPageNumber);
            rectangle = pdfReader.getPageSizeWithRotation(pdfPageNumber);
            pdfContents = watermarkPdfStamper.getUnderContent(pdfPageNumber);
            if (watermarkBean.getType().equalsIgnoreCase(WatermarkConstants.WATERMARK_TYPE_IMAGE)) {
                decoratePdfWatermarkImage(pdfContents, (int) rectangle.getHeight(), (int) rectangle.getHeight(), watermarkBean);
            }
            if (watermarkBean.getType().equalsIgnoreCase(WatermarkConstants.WATERMARK_TYPE_TEXT)) {
                decoratePdfWatermarkText(pdfContents, (int) rectangle.getHeight(), (int) rectangle.getHeight(), watermarkBean);
            }

        }
        try {
            watermarkPdfStamper.close();
        }
        catch (IOException decorateWatermark) {
            LOG.error("Exception occured in WatermarkServiceImpl. decorateWatermark Exception: " + decorateWatermark.getMessage());
        }
        catch (DocumentException documentException) {
            LOG.error("Exception occured in WatermarkServiceImpl. decorateWatermark Exception: " + documentException.getMessage());
        }

    }

    /**
     * This method is for setting the properties of watermark Text.
     * 
     * @param pdfContentByte
     * @param pageWidth
     * @param pageHeight
     * @param watermarkBean
     */
    private void decoratePdfWatermarkText(PdfContentByte pdfContentByte, int pageWidth, int pageHeight, WatermarkBean watermarkBean) {
        float x, y, x1, y1, angle;
        try {
            if (watermarkBean.getType().equalsIgnoreCase(WatermarkConstants.WATERMARK_TYPE_TEXT)) {
                pdfContentByte.beginText();
                pdfContentByte.setFontAndSize(watermarkBean.getFont().getBaseFont(), watermarkBean.getFont().getSize());
                Color fillColor = watermarkBean.getFont().getColor() == null ? WatermarkConstants.DEFAULT_WATERMARK_COLOR
                        : watermarkBean.getFont().getColor();
                pdfContentByte.setColorFill(fillColor);
                int textWidth = (int) pdfContentByte.getEffectiveStringWidth(watermarkBean.getText(), false);
                int diagonal = (int) Math.sqrt((pageWidth * pageWidth) + (pageHeight * pageHeight));
                int pivotPoint = (diagonal - textWidth) / 2;

                angle = (float) Math.atan((float) pageHeight / pageWidth);

                x = (float) (pivotPoint * pageWidth) / diagonal;
                y = (float) (pivotPoint * pageHeight) / diagonal;

                x1 = (float) (((float) watermarkBean.getFont().getSize() / 2) * Math.sin(angle));
                y1 = (float) (((float) watermarkBean.getFont().getSize() / 2) * Math.cos(angle));

                pdfContentByte.showTextAligned(Element.ALIGN_LEFT, watermarkBean.getText(), x + x1, y - y1, (float) Math
                        .toDegrees(angle));
                pdfContentByte.endText();
            }

        }
        catch (Exception exception) {
            LOG.error("Exception occured in WatermarkServiceImpl. Water mark Exception: " + exception.getMessage());
        }


    }

    /**
     * This method is for setting the properties of watermark Image.
     * 
     * @param pdfContentByte
     * @param pageWidth
     * @param pageHeight
     * @param watermarkBean
     */
    private void decoratePdfWatermarkImage(PdfContentByte pdfContentByte, int pageWidth, int pageHeight, WatermarkBean watermarkBean) {
        try {
            if (watermarkBean.getType().equalsIgnoreCase(WatermarkConstants.WATERMARK_TYPE_IMAGE)) {
                Image watermarkImage = Image.getInstance(watermarkBean.getFileImage());
                if (watermarkImage != null) {
                    float height = watermarkImage.getPlainHeight();
                    float width = watermarkImage.getPlainWidth();
                    watermarkImage.setAbsolutePosition((pageWidth - width) / 2, (pageHeight - height) / 2);
                    pdfContentByte.addImage(watermarkImage);
                }

            }

        }
        catch (BadElementException badElementException) {

            LOG.error("WatermarkDecoratorImpl  Error found: " + badElementException.getMessage());
        }
        catch (DocumentException documentException) {

            LOG.error("WatermarkDecoratorImpl Error found: " + documentException.getMessage());
        }

    }

}
