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
package org.kuali.kra.protocol.actions.print;

import com.lowagie.text.Image;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.print.watermark.Font;
import org.kuali.coeus.common.framework.print.watermark.Watermark;
import org.kuali.coeus.common.framework.print.watermark.WatermarkBean;
import org.kuali.coeus.common.framework.print.watermark.WatermarkConstants;
import org.kuali.coeus.common.framework.print.watermark.Watermarkable;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.notification.ProtocolNotificationRendererBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This class for setting watermark to the protocol related reports.
 */
public abstract class ProtocolPrintWatermarkBase implements Watermarkable {

    private KcPersistableBusinessObjectBase persistableBusinessObject;
    private static final Log LOG = LogFactory.getLog(ProtocolPrintWatermarkBase.class);
    private static final String INVALID_WATERMARK_CODE = "-1";
    private WatermarkBean watermarkBean;


    private BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }


    /**
     * This method is to return appropriate watermark with respect to the protocol document status.
     * 
     * @return waterMarkBean
     */
    public WatermarkBean getWatermark() {
        ProtocolBase protocol = (ProtocolBase) getPersistableBusinessObject();
        String protocolStatusCode = protocol.getProtocolStatusCode();
        if (protocolStatusCode != null) {
            WatermarkBean waterMarkBean = null;
            try {
                waterMarkBean = getProtocolWatermarkBeanObject(protocolStatusCode);
            }
            catch (Exception e) {
                LOG.error("Exception Occured in (ProtocolPrintWatermarkBase) :", e);
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
                LOG.error("Exception Occured in (ProtocolPrintWatermarkBase) :", e);
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
        if(getPersistableBusinessObject() instanceof Protocol){
            fields.put("groupName", WatermarkConstants.IRB);   
        }
        else if(getPersistableBusinessObject() instanceof IacucProtocol){
            fields.put("groupName", WatermarkConstants.IACUC);
        }
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

              ProtocolNotificationRendererBase renderer = getNewProtocolNotificationRendererInstanceHook((ProtocolBase) getPersistableBusinessObject());

              if (watermark.getWatermarkText() != null) {
                  watermarkBean.setText(renderer.render(watermark.getWatermarkText()));
              } 

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
                LOG.error("Exception Occured in (ProtocolPrintWatermarkBase) :", e);
            }
            return watermarkBean;
        }
        return null;
    }

    protected abstract ProtocolNotificationRendererBase getNewProtocolNotificationRendererInstanceHook(ProtocolBase protocol);

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
                LOG.error("Exception Occuring in ProtocolPrintWatermarkBase:(getFont:numberFormatException)");
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
                LOG.error("Exception Occuring in ProtocolPrintWatermarkBase:(getFont:numberFormatException)");
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

    public KcPersistableBusinessObjectBase getPersistableBusinessObject() {
        return persistableBusinessObject;
    }

    public void setPersistableBusinessObject(KcPersistableBusinessObjectBase persistableBusinessObject) {
        this.persistableBusinessObject = persistableBusinessObject;
    }


}
