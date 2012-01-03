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
package org.kuali.kra.irb.actions.print;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Watermark;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.util.watermark.Font;
import org.kuali.kra.util.watermark.WatermarkBean;
import org.kuali.kra.util.watermark.WatermarkConstants;
import org.kuali.kra.util.watermark.Watermarkable;
import org.kuali.rice.kns.service.BusinessObjectService;

import com.lowagie.text.Image;

/**
 * 
 * This class for setting watermark to the protocol related reports.
 */
public class ProtocolPrintWatermark implements Watermarkable {

    private KraPersistableBusinessObjectBase persistableBusinessObject;
    private static final Log LOG = LogFactory.getLog(ProtocolPrintWatermark.class);
    private static final String INVALID_WATERMARK_CODE = "-1";
    private WatermarkBean watermarkBean;


    private BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }


    /**
     * This method is to return appropriate watermark with respect to the protocol document status.
     * 
     * @return waterMarkBean
     */
    public WatermarkBean getWatermark() {
        Protocol protocol = (Protocol) getPersistableBusinessObject();
        String protocolStatusCode = protocol.getProtocolStatusCode();
        if (protocolStatusCode != null) {
            WatermarkBean waterMarkBean = null;
            try {
                waterMarkBean = getProtocolWatermarkBeanObject(protocolStatusCode);
            }
            catch (Exception e) {
                LOG.error("Exception Occured in (ProtocolPrintWatermark) :", e);
            }
            return waterMarkBean;
        }
        return null;
    }

    /**
     * This method is to return invalid watermark with respect to the protocol document status.
     * 
     * @return waterMarkBean
     */
    public WatermarkBean getInvalidWatermark(){
        String watermarkStatusCode = INVALID_WATERMARK_CODE;
        {
            WatermarkBean waterMarkBean = null;
            try {
                waterMarkBean = getProtocolWatermarkBeanObject(watermarkStatusCode);
            }
            catch (Exception e) {
                LOG.error("Exception Occured in (ProtocolPrintWatermark) :", e);
            }
            return waterMarkBean;
        }
    }

    /**
     * This method for getting the watermark from the database.
     * 
     * @param statusCode is the status of the protocol
     * @return WatermarkBean LOG Exception
     * @see org.kuali.kra.util.watermark.WatermarkDao#getProtocolWatermarkBeanObject(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public WatermarkBean getProtocolWatermarkBeanObject(String protocolStatusCode) {
        WatermarkBean watermarkBean = new WatermarkBean();
        Watermark watermark = null;
        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("statusCode", protocolStatusCode);
        Collection<Watermark> watermarks = getBusinessObjectService().findMatching(Watermark.class, fields);
        if (watermarks != null && watermarks.size() > 0) {
            watermark = watermarks.iterator().next();
        }
        if (watermark != null && watermark.isWatermarkStatus()) {
            try {
                String watermarkFontSize = watermark.getFontSize() == null ? WatermarkConstants.DEFAULT_FONT_SIZE_CHAR : watermark
                        .getFontSize();
                String watermarkFontColour = watermark.getFontColor() == null ? WatermarkConstants.FONT_COLOR : watermark
                        .getFontColor();
                watermarkBean.setType(watermark.getWatermarkType() == null ? WatermarkConstants.WATERMARK_TYPE_TEXT : watermark
                        .getWatermarkType());
                watermarkBean.setPosition(watermark.getWatermarkPosition() == null ? WatermarkConstants.WATERMARK_POSITION_HEADER : watermark
                        .getWatermarkPosition());
                
                String watermarkPositionFontSize = watermark.getPositionFontSize() == null ? WatermarkConstants.DEFAULT_FONT_SIZE_CHAR: watermark
                        .getPositionFontSize();
                
                watermarkBean.setAlignment(watermark.getWatermarkAlignment() == null ? WatermarkConstants.ALIGN_CENTER : watermark
                        .getWatermarkAlignment());
               
                watermarkBean.setFont(getWatermarkFont(WatermarkConstants.FONT, watermarkFontSize, watermarkFontColour));
                watermarkBean.setPositionFont(getWatermarkPositionFont(WatermarkConstants.FONT, watermarkPositionFontSize, watermarkFontColour));
                
                watermarkBean.setText(watermark.getWatermarkText());
                if (watermarkBean.getType().equals(WatermarkConstants.WATERMARK_TYPE_IMAGE)) {
                    watermarkBean.setText(watermark.getFileName());
                    byte[] imageData = watermark.getAttachmentContent();
                    if (imageData != null) {
                        Image imageFile = Image.getInstance(imageData);
                        watermarkBean.setFileImage(imageFile);
                    }
                }

            }
            catch (Exception e) {
                LOG.error("Exception Occured in (ProtocolPrintWatermark) :", e);
            }
            return watermarkBean;
        }
        return null;
    }


    /**
     * 
     * This method for setting the font details to the watermark Object.
     * 
     * @param watermarkFontName is default font
     * @param watermarkSize is the size of the font
     * @param watermarkColour is the colour of the font
     * @return Font
     */
    private Font getWatermarkFont(String watermarkFontName, String watermarkSize, String watermarkColour) {
        Font watermarkFont = new Font(WatermarkConstants.DEFAULT_FONT_SIZE);
        watermarkFont.setFont(watermarkFontName);
        if (StringUtils.isNotBlank(watermarkSize)) {
            try {
                watermarkFont.setSize(Integer.parseInt(watermarkSize));
            }
            catch (NumberFormatException numberFormatException) {
                watermarkFont.setSize(WatermarkConstants.DEFAULT_WATERMARK_FONT_SIZE);
                LOG.error("Exception Occuring in ProtocolPrintWatermark:(getFont:numberFormatException)");
            }
        }
        else {
            watermarkFont.setSize(WatermarkConstants.DEFAULT_WATERMARK_FONT_SIZE);
        }
        if (StringUtils.isNotBlank(watermarkColour)) {
            watermarkFont.setColor(watermarkColour);
        }
        else {
            watermarkFont.setColor(WatermarkConstants.DEFAULT_WATERMARK_COLOR);
           
        }
        return watermarkFont;
    }
    private Font getWatermarkPositionFont(String watermarkFontName, String watermarkSize, String watermarkColour) {
        Font watermarkFont = new Font(WatermarkConstants.DEFAULT_FONT_SIZE);
        watermarkFont.setFont(watermarkFontName);
        if (StringUtils.isNotBlank(watermarkSize)) {
            try {
                watermarkFont.setSize(Integer.parseInt(watermarkSize));
            }
            catch (NumberFormatException numberFormatException) {
                watermarkFont.setSize(WatermarkConstants.DEFAULT_WATERMARK_FONT_SIZE);
                LOG.error("Exception Occuring in ProtocolPrintWatermark:(getFont:numberFormatException)");
            }
        }
        else {
            watermarkFont.setSize(WatermarkConstants.DEFAULT_WATERMARK_FONT_SIZE);
        }
        if (StringUtils.isNotBlank(watermarkColour)) {
            watermarkFont.setColor(watermarkColour);
        }
        else {
            watermarkFont.setColor(WatermarkConstants.DEFAULT_WATERMARK_COLOR);
           
        }
        return watermarkFont;
    }
    
    public WatermarkBean getWatermarkBean() {
        return watermarkBean;
    }

    public void setWatermarkBean(WatermarkBean watermarkBean) {
        this.watermarkBean = watermarkBean;
    }

    public KraPersistableBusinessObjectBase getPersistableBusinessObject() {
        return persistableBusinessObject;
    }

    public void setPersistableBusinessObject(KraPersistableBusinessObjectBase persistableBusinessObject) {
        this.persistableBusinessObject = persistableBusinessObject;
    }


}
