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
 * WatermarkConstants.java
 * Created on April 11, 2011, 12:44 PM
 * constants used for Watermark Settings
 */
 
package org.kuali.coeus.common.framework.print.watermark;


import java.awt.*;

/**
 * 
 * This interface is used to store all constants for 
 * watermarking purpose.
 * 
 */
public interface WatermarkConstants {
    
    //Default Constants - 
  
    public static final String ALIGN = "ALIGN";
    public static final String TYPE = "TYPE";
    public static final String FONT = "FONT";
    public static final String BOLD = Font.BOLD;
    public static final String FONT_COLOR= "FONT-COLOR";
    public static final String FONT_SIZE = "FONT-SIZE";
    
    public static final String ALIGN_RIGHT = "RIGHT";
    public static final String ALIGN_LEFT = "LEFT";
    public static final String ALIGN_CENTER = "CENTER";
    public static final String DECORATIONS = "DECORATIONS";
    public static final String DECORATION = "DECORATION";
    public static final String STATUS = "STATUS";
    public static final String WATERMARK = "WATERMARK";
    
   
    
    public static final int DEFAULT_FONT_SIZE = 25;
    public static final String DEFAULT_FONT_SIZE_CHAR = "25";
    public static final int DEFAULT_WATERMARK_FONT_SIZE = 50;
    
    public static final Color DEFAULT_COLOR = Color.BLACK;
    public static final Color DEFAULT_WATERMARK_COLOR = Color.LIGHT_GRAY;
    
    public static final String WATERMARK_TYPE_TEXT = "TEXT";    
    public static final String WATERMARK_TYPE_IMAGE = "IMAGE";
    public static final String ATTACHMENT_TYPE_PDF = "application/pdf";
    public static final String WATERMARK_POSITION_HEADER = "HEADER";
    public static final String WATERMARK_POSITION_FOOTER = "FOOTER";
    public static final String WATERMARK_POSITION_DIAGONAL = "DIAGONAL";
    /**
     * Multiply this with Font Size to get the font width.
     * (i.e. the font width is 0.4 times the font size)
     * this is only an average value. 
     * M or W will infact have more width than I.
     */
    public static final float CHAR_WIDTH_CONSTANT = 0.4f;    
    public static final int MARGIN = 25;
    public static final String IRB = "IRB";
    public static final String IACUC = "IACUC";
}
