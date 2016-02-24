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
/*
 * WatermarkBean.java
 * For set the watermark text,font, status etc.
 */

package org.kuali.coeus.common.framework.print.watermark;


import com.lowagie.text.Image;



/**
 * 
 * This class for setting watermark text,font and Type.
 *
 */
public class WatermarkBean {
    
    
    private String text;
   
    private String type;    
    
    private Font font; 
    
    private Image fileImage;
  
    private String position;
    
    public Font getPositionFont() {
        return positionFont;
    }

    public void setPositionFont(Font positionFont) {
        this.positionFont = positionFont;
    }


    private String alignment;
    
    private Font positionFont;
    
    
    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getPosition() {
        return position;
    }


    public Image getFileImage() {
        return fileImage;
    }
    
    /**
     * 
     * This method for set Image.
     * @param font
     */
    public void setFileImage(Image fileImage) {
        this.fileImage = fileImage;
    }
    public String getText() {
        return text;
    }
/**
 * 
 * This method for set text.
 * @param text
 */
    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }
/**
 * 
 * This method for set Type.
 * @param type
 */
    public void setType(String type) {
        this.type = type;
    }

    public Font getFont() {
        return font;
    }
/**
 * 
 * This method for set Font.
 * @param font
 */
    public void setFont(Font font) {
        this.font = font;
    }


public void setPosition(String position) {
    this.position = position;
}


}
