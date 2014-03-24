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
package org.kuali.coeus.common.framework.print.service.impl;

import com.lowagie.text.*;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.print.service.WatermarkService;
import org.kuali.kra.util.watermark.WatermarkBean;
import org.kuali.kra.util.watermark.WatermarkConstants;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
                // flatten original PDF before adding watermark. This prevents interactive form data from getting lost.
                PdfReader origFile = new PdfReader(pdfBytes);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                PdfStamper stamper = new PdfStamper(origFile, byteArrayOutputStream);
                stamper.setFormFlattening(true);
                stamper.close();
                byteArrayOutputStream = attachWatermarking(watermarkBean, byteArrayOutputStream.toByteArray());
                pdfFileData = byteArrayOutputStream.toByteArray();
            }
        }
        catch (Exception exception) {
            LOG.error("Exception occured in WatermarkServiceImpl. Water mark Exception: " + exception);
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
        ByteArrayOutputStream copyByteArrayOutputStream = new ByteArrayOutputStream();
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
            watermarkPageDocument(document, writer, reader);
            byte[] bs = byteArrayOutputStream.toByteArray();
            pdfReader = new PdfReader(bs);
            pdfStamp = new PdfStamper(pdfReader, copyByteArrayOutputStream);
            decorateWatermark(pdfStamp, watermarkBean);
        }
        catch (IOException decorateWatermark) {
            LOG.error("Exception occured in WatermarkServiceImpl. Water mark Exception: " + decorateWatermark.getMessage());
        }
        catch (DocumentException documentException) {
            LOG.error("Exception occured in WatermarkServiceImpl. Water mark Exception: " + documentException.getMessage());
        }
        return copyByteArrayOutputStream;
    }


    /**
     * 
     * This method for Decorating the PDF with watermark.
     * 
     * @param pdfStamper - wrapper for pdf content byte and assists in decorating PDF LOg the exception if cannot open/read the file
     *        for decoration
     */
    private void decorateWatermark(PdfStamper watermarkPdfStamper, WatermarkBean watermarkBean) {
        watermarkPdfStamper.setFormFlattening(true);  
        PdfReader pdfReader = watermarkPdfStamper.getReader();
        int pageCount = pdfReader.getNumberOfPages();
        int pdfPageNumber = 0;
        
        PdfContentByte pdfContents;
        Rectangle rectangle;
        while (pdfPageNumber < pageCount) {
            pdfPageNumber++;
            pdfContents = watermarkPdfStamper.getOverContent(pdfPageNumber);
            rectangle = pdfReader.getPageSizeWithRotation(pdfPageNumber);
            if (watermarkBean.getType().equalsIgnoreCase(WatermarkConstants.WATERMARK_TYPE_IMAGE)) {  
                decoratePdfWatermarkImage(pdfContents, (int) rectangle.getWidth(), (int) rectangle.getHeight(), watermarkBean);
            }
            if (watermarkBean.getType().equalsIgnoreCase(WatermarkConstants.WATERMARK_TYPE_TEXT)) {    
                decoratePdfWatermarkText(pdfContents, rectangle, watermarkBean);
            }
            watermarkPdfStamper.setFormFlattening(true);
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
    private void decoratePdfWatermarkText(PdfContentByte pdfContentByte, Rectangle rectangle, WatermarkBean watermarkBean) {
        float x, y, x1, y1, angle;
        final float OPACITY = 0.3f;
        PdfGState pdfGState = new PdfGState();
        pdfGState.setFillOpacity(OPACITY);
        int pageWidth = (int) rectangle.getWidth();
        int pageHeight = (int) rectangle.getHeight();
        try {
            if (watermarkBean.getType().equalsIgnoreCase(WatermarkConstants.WATERMARK_TYPE_TEXT)) {
                pdfContentByte.beginText();
                pdfContentByte.setGState(pdfGState);
                Color fillColor = watermarkBean.getFont().getColor() == null ? WatermarkConstants.DEFAULT_WATERMARK_COLOR
                        : watermarkBean.getFont().getColor();
                pdfContentByte.setColorFill(fillColor);

                if (watermarkBean.getPosition().equals(WatermarkConstants.WATERMARK_POSITION_FOOTER)) {

                    pdfContentByte.setFontAndSize(watermarkBean.getFont().getBaseFont(), watermarkBean.getPositionFont().getSize());
                    if (watermarkBean.getAlignment().equals(WatermarkConstants.ALIGN_CENTER)) {
                        pdfContentByte.showTextAligned(Element.ALIGN_CENTER, watermarkBean.getText(), (rectangle.getLeft(rectangle
                                .getBorderWidthLeft()) + rectangle.getRight(rectangle.getBorderWidthRight())) / 2, rectangle
                                .getBottom(rectangle.getBorderWidthBottom() + watermarkBean.getPositionFont().getSize()), 0);
                    }
                    else if (watermarkBean.getAlignment().equals(WatermarkConstants.ALIGN_RIGHT)) {
                        pdfContentByte.showTextAligned(Element.ALIGN_RIGHT, watermarkBean.getText(),
                                rectangle.getRight(rectangle.getBorderWidthRight()),
                                rectangle.getBottom(rectangle.getBorderWidthBottom() + watermarkBean.getPositionFont().getSize()),
                                0);
                    }
                    else if (watermarkBean.getAlignment().equals(WatermarkConstants.ALIGN_LEFT)) {
                        pdfContentByte.showTextAligned(Element.ALIGN_LEFT, watermarkBean.getText(),
                                rectangle.getLeft(rectangle.getBorderWidthLeft()),
                                rectangle.getBottom(rectangle.getBorderWidthBottom() + watermarkBean.getPositionFont().getSize()),
                                0);
                    }
                }
                else if (watermarkBean.getPosition().equals(WatermarkConstants.WATERMARK_POSITION_HEADER)) {
                    pdfContentByte.setFontAndSize(watermarkBean.getFont().getBaseFont(), watermarkBean.getPositionFont().getSize());
                    if (watermarkBean.getAlignment().equals(WatermarkConstants.ALIGN_CENTER)) {
                        pdfContentByte.showTextAligned(Element.ALIGN_CENTER, watermarkBean.getText(), (rectangle.getLeft(rectangle
                                .getBorderWidthLeft()) + rectangle.getRight(rectangle.getBorderWidthRight())) / 2, rectangle
                                .getTop(rectangle.getBorderWidthTop() + watermarkBean.getPositionFont().getSize()), 0);
                    }
                    else if (watermarkBean.getAlignment().equals(WatermarkConstants.ALIGN_RIGHT)) {
                        pdfContentByte.showTextAligned(Element.ALIGN_RIGHT, watermarkBean.getText(),
                                rectangle.getRight(rectangle.getBorderWidthRight()),
                                rectangle.getTop(rectangle.getBorderWidthTop() + watermarkBean.getPositionFont().getSize()), 0);
                    }
                    else if (watermarkBean.getAlignment().equals(WatermarkConstants.ALIGN_LEFT)) {
                        pdfContentByte.showTextAligned(Element.ALIGN_LEFT, watermarkBean.getText(),
                                rectangle.getLeft(rectangle.getBorderWidthLeft()),
                                rectangle.getTop(rectangle.getBorderWidthTop() + watermarkBean.getPositionFont().getSize()), 0);
                    }
                }
                else {
                    pdfContentByte.setFontAndSize(watermarkBean.getFont().getBaseFont(), watermarkBean.getFont().getSize());
                    int textWidth = (int) pdfContentByte.getEffectiveStringWidth(watermarkBean.getText(), false);
                    int diagonal = (int) Math.sqrt((pageWidth * pageWidth) + (pageHeight * pageHeight));
                    int pivotPoint = (diagonal - textWidth) / 2;

                    angle = (float) Math.atan((float) pageHeight / pageWidth);

                    x = (float) (pivotPoint * pageWidth) / diagonal;
                    y = (float) (pivotPoint * pageHeight) / diagonal;

                    x1 = (float) (((float) watermarkBean.getFont().getSize() / 2) * Math.sin(angle));
                    y1 = (float) (((float) watermarkBean.getFont().getSize() / 2) * Math.cos(angle));

                    pdfContentByte.showTextAligned(Element.ALIGN_LEFT, watermarkBean.getText(), x + x1, y - y1,
                            (float) Math.toDegrees(angle));
                }
                pdfContentByte.endText();
            }
        }
        catch (Exception exception) {
            LOG.error("Exception occured in WatermarkServiceImpl. Water mark Exception: " + exception.getMessage());
        }


    }
   
    /**
     * This method is for setting the page properties of the document.
     * 
     * @param Document
     * @param PdfWriter
     * @param PdfReader
     
     */

    private void watermarkPageDocument(Document document,PdfWriter writer,PdfReader reader){
        document.open();
        int totalPages;
        totalPages = reader.getNumberOfPages();
        for(int pageCount= 1; pageCount<=totalPages;pageCount++){
            PdfContentByte contents = writer.getDirectContent();
            document.setPageSize(reader.getPageSize(pageCount));
            document.newPage();
            PdfImportedPage page = writer.getImportedPage(reader, pageCount);
            contents.addTemplate(page, 1, 0, 0, 1, 0, 0);
            }
        document.close();
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
        PdfGState pdfGState = new PdfGState();
        final float OPACITY = 0.3f;
        float absPosX;
        float absPosY;
        try {
            if (watermarkBean.getType().equalsIgnoreCase(WatermarkConstants.WATERMARK_TYPE_IMAGE)) {
                Image watermarkImage = Image.getInstance(watermarkBean.getFileImage());
                if (watermarkImage != null) {
                    pdfGState.setFillOpacity(OPACITY);
                    pdfContentByte.setGState(pdfGState);
                    float height = watermarkImage.getPlainHeight();
                    float width = watermarkImage.getPlainWidth();
                    int diagonal = (int) Math.sqrt((pageWidth * pageWidth) + (pageHeight * pageHeight));
                    int pivotPoint = (diagonal - (int) width) / 2;
                    float angle = (float) Math.atan((float) pageHeight / pageWidth);
                    absPosX = (float) (pivotPoint * pageWidth) / diagonal;
                    absPosY = (float) (pivotPoint * pageHeight) / diagonal;
                    watermarkImage.setAbsolutePosition(absPosX, absPosY); 
                    if((pageWidth / 2) < width) {
                        watermarkImage.scaleToFit(pageWidth / 2, pageHeight / 2);
                    } else {
                        watermarkImage.scaleToFit(width, height);
                    }
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
