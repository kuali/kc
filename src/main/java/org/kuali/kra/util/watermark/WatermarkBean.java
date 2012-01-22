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
/*
 * WatermarkBean.java
 * For set the watermark text,font, status etc.
 */

package org.kuali.kra.util.watermark;

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
