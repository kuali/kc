<?xml version="1.0" encoding="UTF-8"?><!-- $Revision:   1.11  $ -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:NASA_OtherProjectInformation="http://apply.grants.gov/forms/NASA_OtherProjectInformation-V1.0">
   <xsl:output method="xml" indent="yes"/>
   <xsl:template match="/">
      <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0">
         <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.2in" margin-right="0.2in">
               <fo:region-body margin-top="0.2in" margin-bottom="0.4in"/>
               <fo:region-after extent="0.4in"/>
            </fo:simple-page-master>
         </fo:layout-master-set>
         <fo:page-sequence master-reference="default-page" format="1" initial-page-number="1">
            <fo:static-content flow-name="xsl-region-after">
               <fo:block>
                  <fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
                  </fo:inline>
               </fo:block>
            </fo:static-content>
            <fo:flow flow-name="xsl-region-body"><!--Data components--><!--Block below is for the field named FIELD1 with FieldID -->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="40px" hyphenate="true" language="en" keep-together="always" top="93.93939393939394px" height="13.333333333333334px" width="218.7878787878788px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"/>
               </fo:block-container><!--Block below is for the field named HistoricImpactEx with FieldID 3-1-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="40.60606060606061px" hyphenate="true" language="en" keep-together="always" top="324.24242424242425px" height="323.6363636363636px" width="488.4848484848485px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:HistoricImpact/NASA_OtherProjectInformation:HistoricImpactEx) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:HistoricImpact/NASA_OtherProjectInformation:HistoricImpactEx = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:HistoricImpact/NASA_OtherProjectInformation:HistoricImpactEx"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the radio named CivilServicePersonnel_YES with FieldID 2-0-1-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="41.21212121212121px" hyphenate="true" language="en" keep-together="always" top="106.66666666666667px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:CivilServicePersonnel"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the field named FIELD2 with FieldID -->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="41.21212121212121px" hyphenate="true" language="en" keep-together="always" top="243.63636363636365px" height="27.87878787878788px" width="487.2727272727273px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"/>
               </fo:block-container><!--Block below is for the radio named HistoricImpactQ_YES with FieldID 3-0-1-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="41.21212121212121px" hyphenate="true" language="en" keep-together="always" top="271.5151515151515px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:HistoricImpact/NASA_OtherProjectInformation:HistoricImpactQ"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the radio named HistoricImpactQ_NO with FieldID 3-0-2-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="89.0909090909091px" hyphenate="true" language="en" keep-together="always" top="271.5151515151515px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:HistoricImpact/NASA_OtherProjectInformation:HistoricImpactQ"/>
                              <xsl:with-param name="schemaChoice">N: No</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the radio named CivilServicePersonnel_NO with FieldID 2-0-2-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="93.93939393939394px" hyphenate="true" language="en" keep-together="always" top="106.66666666666667px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:CivilServicePersonnel"/>
                              <xsl:with-param name="schemaChoice">N: No</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

              <fo:block-container background-color="transparent" border-style="none" position="absolute" left="122.42424242424244px" hyphenate="true" language="en" keep-together="always" top="166.66666666666669px" height="13.333333333333334px" width="50.303030303030305px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Fiscal Year 1</fo:block>
               </fo:block-container>
               
               
              <fo:block-container background-color="transparent" border-style="none" position="absolute" left="178.7878787878788px" hyphenate="true" language="en" keep-together="always" top="166.66666666666669px" height="13.333333333333334px" width="50.303030303030305px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Fiscal Year 2</fo:block>
               </fo:block-container>
               
               
              <fo:block-container background-color="transparent" border-style="none" position="absolute" left="236.96969696969697px" hyphenate="true" language="en" keep-together="always" top="166.66666666666669px" height="13.333333333333334px" width="50.303030303030305px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Fiscal Year 3</fo:block>
               </fo:block-container>
               
               
              <fo:block-container background-color="transparent" border-style="none" position="absolute" left="295.1515151515152px" hyphenate="true" language="en" keep-together="always" top="166.66666666666669px" height="13.333333333333334px" width="50.303030303030305px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Fiscal Year 4</fo:block>
               </fo:block-container>
               
               
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="352.72727272727275px" hyphenate="true" language="en" keep-together="always" top="166.66666666666669px" height="13.333333333333334px" width="50.303030303030305px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Fiscal Year 5</fo:block>
               </fo:block-container>
               
               
               
              <fo:block-container background-color="transparent" border-style="none" position="absolute" left="412.1212121212121px" hyphenate="true" language="en" keep-together="always" top="166.66666666666669px" height="13.333333333333334px" width="50.303030303030305px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Fiscal Year 6</fo:block>
               </fo:block-container>

<!--Block below is for the popup named FY1 with FieldID 2-1-1-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="127.42424242424244px" hyphenate="true" language="en" keep-together="always" top="175.15151515151516px" height="13.333333333333334px" width="37.57575757575758px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE1/NASA_OtherProjectInformation:FY1) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE1/NASA_OtherProjectInformation:FY1 = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE1/NASA_OtherProjectInformation:FY1"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the field named FTE1 with FieldID 2-2-1-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="122.42424242424244px" hyphenate="true" language="en" keep-together="always" top="192.72727272727275px" height="13.333333333333334px" width="42.42424242424243px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE1/NASA_OtherProjectInformation:FTE1) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE1/NASA_OtherProjectInformation:FTE1 = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <!--<xsl:value-of select="format-number(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE1/NASA_OtherProjectInformation:FTE1, '#,##0.00')"/>-->
                           <xsl:value-of select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE1/NASA_OtherProjectInformation:FTE1"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the popup named FY2 with FieldID 2-1-2-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="183.7878787878788px" hyphenate="true" language="en" keep-together="always" top="175.15151515151516px" height="13.333333333333334px" width="37.57575757575758px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE2/NASA_OtherProjectInformation:FY2) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE2/NASA_OtherProjectInformation:FY2 = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE2/NASA_OtherProjectInformation:FY2"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the field named FTE2 with FieldID 2-2-2-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="178.7878787878788px" hyphenate="true" language="en" keep-together="always" top="192.72727272727275px" height="13.333333333333334px" width="42.42424242424243px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE2/NASA_OtherProjectInformation:FTE2) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE2/NASA_OtherProjectInformation:FTE2 = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <!--<xsl:value-of select="format-number(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE2/NASA_OtherProjectInformation:FTE2, '#,##0.00')"/>-->
                            <xsl:value-of select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE2/NASA_OtherProjectInformation:FTE2"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the popup named FY3 with FieldID 2-1-3-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="241.96969696969697px" hyphenate="true" language="en" keep-together="always" top="175.15151515151516px" height="13.333333333333334px" width="37.57575757575758px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE3/NASA_OtherProjectInformation:FY3) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE3/NASA_OtherProjectInformation:FY3 = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE3/NASA_OtherProjectInformation:FY3"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the field named FTE3 with FieldID 2-2-3-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="236.96969696969697px" hyphenate="true" language="en" keep-together="always" top="192.72727272727275px" height="13.333333333333334px" width="42.42424242424243px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE3/NASA_OtherProjectInformation:FTE3) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE3/NASA_OtherProjectInformation:FTE3 = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <!--<xsl:value-of select="format-number(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE3/NASA_OtherProjectInformation:FTE3, '#,##0.00')"/>-->
                           <xsl:value-of select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE3/NASA_OtherProjectInformation:FTE3"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the popup named FY4 with FieldID 2-1-4-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="300.1515151515152px" hyphenate="true" language="en" keep-together="always" top="175.15151515151516px" height="13.333333333333334px" width="37.57575757575758px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE4/NASA_OtherProjectInformation:FY4) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE4/NASA_OtherProjectInformation:FY4 = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE4/NASA_OtherProjectInformation:FY4"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the field named FTE4 with FieldID 2-2-4-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="295.1515151515152px" hyphenate="true" language="en" keep-together="always" top="192.72727272727275px" height="13.333333333333334px" width="42.42424242424243px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE4/NASA_OtherProjectInformation:FTE4) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE4/NASA_OtherProjectInformation:FTE4 = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                          <!-- <xsl:value-of select="format-number(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE4/NASA_OtherProjectInformation:FTE4, '#,##0.00')"/>-->
                           <xsl:value-of select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE4/NASA_OtherProjectInformation:FTE4"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the popup named FY5 with FieldID 2-1-5-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="357.72727272727275px" hyphenate="true" language="en" keep-together="always" top="175.15151515151516px" height="13.333333333333334px" width="37.57575757575758px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE5/NASA_OtherProjectInformation:FY5) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE5/NASA_OtherProjectInformation:FY5 = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE5/NASA_OtherProjectInformation:FY5"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the field named FTE5 with FieldID 2-2-5-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="352.72727272727275px" hyphenate="true" language="en" keep-together="always" top="192.72727272727275px" height="13.333333333333334px" width="42.42424242424243px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE5/NASA_OtherProjectInformation:FTE5) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE5/NASA_OtherProjectInformation:FTE5 = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <!--<xsl:value-of select="format-number(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE5/NASA_OtherProjectInformation:FTE5, '#,##0.00')"/>-->
                           <xsl:value-of select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE5/NASA_OtherProjectInformation:FTE5"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the popup named FY6 with FieldID 2-1-6-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="417.1212121212121px" hyphenate="true" language="en" keep-together="always" top="175.15151515151516px" height="13.333333333333334px" width="37.57575757575758px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE6/NASA_OtherProjectInformation:FY6) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE6/NASA_OtherProjectInformation:FY6 = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE6/NASA_OtherProjectInformation:FY6"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the field named FTE6 with FieldID 2-2-6-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="412.1212121212121px" hyphenate="true" language="en" keep-together="always" top="192.72727272727275px" height="13.333333333333334px" width="42.42424242424243px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE6/NASA_OtherProjectInformation:FTE6) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE6/NASA_OtherProjectInformation:FTE6 = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <!--<xsl:value-of select="format-number(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE6/NASA_OtherProjectInformation:FTE6, '#,##0.00')"/>-->
                           <xsl:value-of select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:NASACivilServicePersonnel/NASA_OtherProjectInformation:FYFTE6/NASA_OtherProjectInformation:FTE6"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Write labels--><!--Block below is for the label named --><!--Block below is for the label named d1e2734-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="40.60606060606061px" hyphenate="true" language="en" keep-together="always" top="92.72727272727273px" height="13.333333333333334px" width="217.5757575757576px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">1. * Will NASA civil service personnel work on this project?</fo:block>
               </fo:block-container><!--Block below is for the label named d1e3240-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="40.60606060606061px" hyphenate="true" language="en" keep-together="always" top="144.24242424242425px" height="13.333333333333334px" width="292.1212121212121px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">1.a.  If Yes, provide the total number of NASA FTEs by Government fiscal year:</fo:block>
               </fo:block-container><!--Block below is for the label named FY1_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="40.60606060606061px" hyphenate="true" language="en" keep-together="always" top="175.15151515151516px" height="13.333333333333334px" width="58.78787878787879px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Fiscal Year:</fo:block>
               </fo:block-container><!--Block below is for the label named FTE1_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="40.60606060606061px" hyphenate="true" language="en" keep-together="always" top="192.72727272727275px" height="13.333333333333334px" width="70.9090909090909px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Number of FTE's:</fo:block>
               </fo:block-container><!--Block below is for the label named HistoricImpactEx_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="40.60606060606061px" hyphenate="true" language="en" keep-together="always" top="310.3030303030303px" height="13.333333333333334px" width="110.90909090909092px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">2.a. *  If Yes, please explain:</fo:block>
               </fo:block-container><!--Block below is for the label named d1e5698-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="41.21212121212121px" hyphenate="true" language="en" keep-together="always" top="243.03030303030303px" height="27.87878787878788px" width="488.4848484848485px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">2. * Does this project have the potential to affect historic, archeological, or traditional cultural sites (such as Native American burial or ceremonial grounds) or historic objects (such as an historic aircraft or spacecraft)?</fo:block>
               </fo:block-container><!--Block below is for the label named CivilServicePersonnel_YES_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="49.6969696969697px" hyphenate="true" language="en" keep-together="always" top="106.66666666666667px" height="13.333333333333334px" width="27.87878787878788px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Yes</fo:block>
               </fo:block-container><!--Block below is for the label named HistoricImpactQ_YES_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="49.6969696969697px" hyphenate="true" language="en" keep-together="always" top="271.5151515151515px" height="13.333333333333334px" width="27.87878787878788px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Yes</fo:block>
               </fo:block-container><!--Block below is for the label named HistoricImpactQ_NO_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="97.57575757575758px" hyphenate="true" language="en" keep-together="always" top="271.5151515151515px" height="13.333333333333334px" width="23.03030303030303px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">No</fo:block>
               </fo:block-container><!--Block below is for the label named CivilServicePersonnel_NO_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="102.42424242424244px" hyphenate="true" language="en" keep-together="always" top="106.66666666666667px" height="13.333333333333334px" width="23.03030303030303px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">No</fo:block>
               </fo:block-container><!--Block below is for the label named title-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="172.12121212121212px" hyphenate="true" language="en" keep-together="always" top="38.18181818181819px" height="20px" width="211.51515151515153px">
                  <fo:block background-color="transparent" color="#000000" font-size="14pt" font-style="normal" font-family="Helvetica" font-weight="normal">NASA - Other Project Information</fo:block>
               </fo:block-container><!--Block below is for the label named omb_number-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="430.06060606060606px" hyphenate="true" language="en" keep-together="always" top="3.6363636363636367px" height="12.121212121212121px" width="100.03030303030303px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 2700-0085 </fo:block>
               </fo:block-container>
				<fo:block-container background-color="transparent" border-style="none" position="absolute" left="430.06060606060606px" hyphenate="true" language="en" keep-together="always" top="13.6363636363636367px" height="12.121212121212121px" width="100.03030303030303px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 2700-0087 </fo:block>
               </fo:block-container><!--Draw lines-->
               <fo:block-container background-color="black" border-style="none" position="absolute" left="33.333333333333336px" top="76.36363636363637px" width="503.03030303030306px" height="0.6060606060606061px"/>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="32.72727272727273px" top="228.4848484848485px" width="503.03030303030306px" height="0.6060606060606061px"/>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="33.333333333333336px" top="666.0606060606061px" width="503.03030303030306px" height="0.6060606060606061px"/>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="32.72727272727273px" top="76.36363636363637px" width="0.6060606060606061px" height="590.909090909091px"/>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="536.969696969697px" top="75.75757575757576px" width="0.6060606060606061px" height="590.909090909091px"/>
            </fo:flow>
         </fo:page-sequence>
         <fo:page-sequence master-reference="default-page" format="1" initial-page-number="2">
            <fo:static-content flow-name="xsl-region-after">
               <fo:block>
                  <fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
                  </fo:inline>
               </fo:block>
            </fo:static-content>
            <fo:flow flow-name="xsl-region-body"><!--Data components--><!--Block below is for the field named FIELD1 with FieldID -->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="34.54545454545455px" hyphenate="true" language="en" keep-together="always" top="78.7878787878788px" height="26.666666666666668px" width="488.4848484848485px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"/>
               </fo:block-container><!--Block below is for the radio named InternationalParticipationQ_YES with FieldID 4-0-1-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="34.54545454545455px" hyphenate="true" language="en" keep-together="always" top="103.63636363636364px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipationQ"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the field named FIELD2 with FieldID -->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="34.54545454545455px" hyphenate="true" language="en" keep-together="always" top="130.3030303030303px" height="13.333333333333334px" width="154.54545454545456px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"/>
               </fo:block-container><!--Block below is for the field named InternationalParticipatioEx with FieldID 4-2-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="34.54545454545455px" hyphenate="true" language="en" keep-together="always" top="178.1818181818182px" height="323.6363636363636px" width="488.4848484848485px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipatioEx) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipatioEx = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipatioEx"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the radio named InternationalParticipationQ_NO with FieldID 4-0-2-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="92.72727272727273px" hyphenate="true" language="en" keep-together="always" top="103.63636363636364px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipationQ"/>
                              <xsl:with-param name="schemaChoice">N: No</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the field named PSDataAttach0 with FieldID 5-1-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="134.54545454545456px" hyphenate="true" language="en" keep-together="always" top="565.7575757575758px" height="12.121212121212121px" width="231.51515151515153px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:PSDataAttach/att:FileName) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:PSDataAttach/att:FileName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:PSDataAttach/att:FileName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the check named InternationalParticipationPI with FieldID 4-1-1-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="213.33333333333334px" hyphenate="true" language="en" keep-together="always" top="130.9090909090909px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;</fo:inline>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="213.33333333333334px" hyphenate="true" language="en" keep-together="always" top="127.87878787878789px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipationPI) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipationPI = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="checkbox">
                              <xsl:with-param name="value" select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipationPI"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the check named InternationalParticipationCo_I with FieldID 4-1-2-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="260.6060606060606px" hyphenate="true" language="en" keep-together="always" top="130.9090909090909px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;</fo:inline>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="260.6060606060606px" hyphenate="true" language="en" keep-together="always" top="127.87878787878789px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipationCo-I) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipationCo-I = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="checkbox">
                              <xsl:with-param name="value" select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipationCo-I"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the check named InternationalParticipationCollaborator with FieldID 4-1-3-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="312.72727272727275px" hyphenate="true" language="en" keep-together="always" top="130.9090909090909px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;</fo:inline>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="312.72727272727275px" hyphenate="true" language="en" keep-together="always" top="127.87878787878789px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipationCollaborator) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipationCollaborator = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="checkbox">
                              <xsl:with-param name="value" select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipationCollaborator"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the check named InternationalParticipationEquipment with FieldID 4-1-4-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="387.8787878787879px" hyphenate="true" language="en" keep-together="always" top="131.51515151515153px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;</fo:inline>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="387.8787878787879px" hyphenate="true" language="en" keep-together="always" top="128.4848484848485px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipationEquipment) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipationEquipment = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="checkbox">
                              <xsl:with-param name="value" select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipationEquipment"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the check named InternationalParticipationFacility with FieldID 4-1-5-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="461.21212121212125px" hyphenate="true" language="en" keep-together="always" top="131.51515151515153px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;</fo:inline>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="461.21212121212125px" hyphenate="true" language="en" keep-together="always" top="128.4848484848485px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="not(//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipationFacility) or //NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipationFacility = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="checkbox">
                              <xsl:with-param name="value" select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:InternationalParticipation/NASA_OtherProjectInformation:InternationalParticipationFacility"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Write labels--><!--Block below is for the label named --><!--Block below is for the label named d1e6436-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="34.54545454545455px" hyphenate="true" language="en" keep-together="always" top="79.3939393939394px" height="26.060606060606062px" width="488.4848484848485px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">3. * Does this proposed project involve any international participation, either non-U.S. employees or non-U.S. organizations, providing support for facilities, equipment, etc.  (see instructions for details):</fo:block>
               </fo:block-container><!--Block below is for the label named InternationalParticipatioEx_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="34.54545454545455px" hyphenate="true" language="en" keep-together="always" top="165.45454545454547px" height="13.333333333333334px" width="117.57575757575758px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"> 3.b.   * If Yes, please explain:</fo:block>
               </fo:block-container><!--Block below is for the label named d1e7897-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="34.54545454545455px" hyphenate="true" language="en" keep-together="always" top="523.030303030303px" height="13.333333333333334px" width="373.33333333333337px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">4. Some NASA programs require that additional information be provided on a form template.  Please go to </fo:block>
               </fo:block-container><!--Block below is for the label named PSDataAttach_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="34.54545454545455px" hyphenate="true" language="en" keep-together="always" top="565.7575757575758px" height="13.333333333333334px" width="89.6969696969697px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Program Specific Data:</fo:block>
               </fo:block-container><!--Block below is for the label named AppendAttach_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="34.54545454545455px" hyphenate="true" language="en" keep-together="always" top="592.7272727272727px" height="13.333333333333334px" width="58.78787878787879px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">5. Appendices:</fo:block>
               </fo:block-container><!--Block below is for the label named LetterEndorsAttach_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="34.54545454545455px" hyphenate="true" language="en" keep-together="always" top="609.6969696969697px" height="13.333333333333334px" width="187.27272727272728px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">6. Non-U.S. Organization Letters of Endorsement:</fo:block>
               </fo:block-container><!--Block below is for the label named IRBACUCLettersAttach_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="34.54545454545455px" hyphenate="true" language="en" keep-together="always" top="627.8787878787879px" height="13.333333333333334px" width="93.33333333333334px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">7. IRB &amp; ACUC Letters:</fo:block>
               </fo:block-container><!--Block below is for the label named d1e6940-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="35.151515151515156px" hyphenate="true" language="en" keep-together="always" top="130.9090909090909px" height="13.333333333333334px" width="153.33333333333334px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">3.a.  * If Yes, please mark all that apply:</fo:block>
               </fo:block-container><!--Block below is for the label named LABEL2-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="35.151515151515156px" hyphenate="true" language="en" keep-together="always" top="544.2424242424242px" height="12.121212121212121px" width="376.969696969697px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Be sure to follow the instructions provided in the solicitation announcement.</fo:block>
               </fo:block-container><!--Block below is for the label named InternationalParticipationQ_YES_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="43.03030303030303px" hyphenate="true" language="en" keep-together="always" top="103.63636363636364px" height="13.333333333333334px" width="27.87878787878788px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Yes</fo:block>
               </fo:block-container><!--Block below is for the label named InternationalParticipationQ_NO_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="101.21212121212122px" hyphenate="true" language="en" keep-together="always" top="103.63636363636364px" height="13.333333333333334px" width="23.03030303030303px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">No</fo:block>
               </fo:block-container><!--Block below is for the label named LABEL1-->
               
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="34.75757575757578px" hyphenate="true" language="en" keep-together="always" top="532.7272727272727px" height="13.333333333333334px" width="141.21212121212125px">
                  <fo:block background-color="transparent" text-decoration="underline" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">https://nspires.nasaprs.com/Grants.gov</fo:block>
               </fo:block-container>
               
               
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="181.75757575757578px" hyphenate="true" language="en" keep-together="always" top="532.7272727272727px" height="13.333333333333334px" width="281.21212121212125px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"> to look up this funding opportunity and download the program-specific form.</fo:block>
               </fo:block-container>

<!--Block below is for the label named title-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="160.36363636363637px" hyphenate="true" language="en" keep-together="always" top="32.121212121212125px" height="20px" width="211.51515151515153px">
                  <fo:block background-color="transparent" color="#000000" font-size="14pt" font-style="normal" font-family="Helvetica" font-weight="normal">NASA - Other Project Information</fo:block>
               </fo:block-container><!--Block below is for the label named InternationalParticipationPI_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="222.42424242424244px" hyphenate="true" language="en" keep-together="always" top="130.9090909090909px" height="13.333333333333334px" width="23.03030303030303px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">PI</fo:block>
               </fo:block-container><!--Block below is for the label named InternationalParticipationCo_I_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="269.0909090909091px" hyphenate="true" language="en" keep-together="always" top="130.9090909090909px" height="13.333333333333334px" width="21.212121212121215px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Co-I</fo:block>
               </fo:block-container><!--Block below is for the label named InternationalParticipationCollaborator_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="321.8181818181818px" hyphenate="true" language="en" keep-together="always" top="130.9090909090909px" height="13.333333333333334px" width="50.303030303030305px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Collaborator</fo:block>
               </fo:block-container>

<!--Block below is for the label named omb_number-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="430.39393939393943px" hyphenate="true" language="en" keep-together="always" top="4.242424242424242px" height="12.121212121212121px" width="100.03030303030303px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 2700-0085</fo:block>
               </fo:block-container>
<fo:block-container background-color="transparent" border-style="none" position="absolute" left="430.39393939393943px" hyphenate="true" language="en" keep-together="always" top="14.242424242424242px" height="12.121212121212121px" width="100.03030303030303px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 2700-0087</fo:block>
               </fo:block-container>

<!--Block below is for the label named InternationalParticipationEquipment_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="396.3636363636364px" hyphenate="true" language="en" keep-together="always" top="131.51515151515153px" height="13.333333333333334px" width="47.87878787878788px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Equipment</fo:block>
               </fo:block-container><!--Block below is for the label named InternationalParticipationFacility_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="469.69696969696975px" hyphenate="true" language="en" keep-together="always" top="131.51515151515153px" height="13.333333333333334px" width="32.121212121212125px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Facility</fo:block>
               </fo:block-container><!--Draw lines-->
               <fo:block-container background-color="black" border-style="none" position="absolute" left="28.484848484848488px" top="67.27272727272728px" width="502.42424242424244px" height="0.6060606060606061px"/>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="29.6969696969697px" top="512.7272727272727px" width="502.42424242424244px" height="0.6060606060606061px"/>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="29.090909090909093px" top="654.5454545454546px" width="502.42424242424244px" height="0.6060606060606061px"/>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="29.090909090909093px" top="67.27272727272728px" width="0.6060606060606061px" height="588.4848484848485px"/>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="531.5151515151515px" top="67.87878787878788px" width="0.6060606060606061px" height="588.4848484848485px"/>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="29.6969696969697px" top="585.7272727272727px" width="502.42424242424244px" height="0.6060606060606061px"/>
            </fo:flow>
         </fo:page-sequence>
         <fo:page-sequence master-reference="default-page" format="1" initial-page-number="3">
            <fo:static-content flow-name="xsl-region-after">
               <fo:block>
                  <fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
                  </fo:inline>
               </fo:block>
            </fo:static-content>
            <fo:flow flow-name="xsl-region-body">
               <fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
                  <fo:table-column column-width="proportional-column-width(1)"/>
                  <fo:table-column column-width="proportional-column-width(1)"/>
                  <fo:table-body>
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="center" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
                           <fo:block font-size="14pt" text-decoration="underline">Attachments</fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
                           <xsl:call-template name="addBlankLines">
                              <xsl:with-param name="numLines">1</xsl:with-param>
                           </xsl:call-template>
                           <fo:block font-size="10pt">PSDataAttach</fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                           <fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
                        </fo:table-cell>
                        <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                           <fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                           <fo:block font-size="8pt">
                              <xsl:value-of select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:PSDataAttach/att:FileName"/>
                           </fo:block>
                        </fo:table-cell>
                        <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                           <fo:block font-size="8pt">
                              <xsl:value-of select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:PSDataAttach/att:MimeType"/>
                           </fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
                           <xsl:call-template name="addBlankLines">
                              <xsl:with-param name="numLines">1</xsl:with-param>
                           </xsl:call-template>
                           <fo:block font-size="10pt">AppendAttach</fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                           <fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
                        </fo:table-cell>
                        <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                           <fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <xsl:for-each select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:AppendAttach/att:AttachedFile">
                        <fo:table-row>
                           <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                              <fo:block font-size="8pt">
                                 <xsl:value-of select="att:FileName"/>
                              </fo:block>
                           </fo:table-cell>
                           <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                              <fo:block font-size="8pt">
                                 <xsl:value-of select="att:MimeType"/>
                              </fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                     </xsl:for-each>
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
                           <xsl:call-template name="addBlankLines">
                              <xsl:with-param name="numLines">1</xsl:with-param>
                           </xsl:call-template>
                           <fo:block font-size="10pt">LetterEndorsAttach</fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                           <fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
                        </fo:table-cell>
                        <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                           <fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <xsl:for-each select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:LetterEndorsAttach/att:AttachedFile">
                        <fo:table-row>
                           <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                              <fo:block font-size="8pt">
                                 <xsl:value-of select="att:FileName"/>
                              </fo:block>
                           </fo:table-cell>
                           <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                              <fo:block font-size="8pt">
                                 <xsl:value-of select="att:MimeType"/>
                              </fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                     </xsl:for-each>
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
                           <xsl:call-template name="addBlankLines">
                              <xsl:with-param name="numLines">1</xsl:with-param>
                           </xsl:call-template>
                           <fo:block font-size="10pt">IRBACUCLettersAttach</fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                           <fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
                        </fo:table-cell>
                        <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                           <fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <xsl:for-each select="//NASA_OtherProjectInformation:NASA_OtherProjectInformation/NASA_OtherProjectInformation:IRBACUCLettersAttach/att:AttachedFile">
                        <fo:table-row>
                           <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                              <fo:block font-size="8pt">
                                 <xsl:value-of select="att:FileName"/>
                              </fo:block>
                           </fo:table-cell>
                           <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                              <fo:block font-size="8pt">
                                 <xsl:value-of select="att:MimeType"/>
                              </fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                     </xsl:for-each>
                  </fo:table-body>
               </fo:table>
            </fo:flow>
         </fo:page-sequence>
      </fo:root>
   </xsl:template>
   
   
   <xsl:template name="radioButton">
      <xsl:param name="value"/>
      <xsl:param name="schemaChoice">Y: Yes</xsl:param>
      <xsl:choose>
         <xsl:when test="$value = $schemaChoice">
            <fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="8pt">&#x25cf;</fo:inline>
         </xsl:when>
         <xsl:otherwise>
            <fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="8pt">&#x274d;</fo:inline>
         </xsl:otherwise>
      </xsl:choose>
   </xsl:template>
   <xsl:template name="checkbox">
      <xsl:param name="value"/>
      <xsl:param name="schemaChoice">Yes</xsl:param>
      <xsl:if test="$value = $schemaChoice">
         <fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="8pt">&#x2714;</fo:inline>
      </xsl:if>
   </xsl:template>
   <xsl:template name="formatDate">
      <xsl:param name="value"/>
      <xsl:if test="$value != ''">
         <xsl:value-of select="format-number(substring($value,6,2), '00')"/>
         <xsl:text>/</xsl:text>
         <xsl:value-of select="format-number(substring($value,9,2), '00')"/>
         <xsl:text>/</xsl:text>
         <xsl:value-of select="format-number(substring($value,1,4), '0000')"/>
      </xsl:if>
   </xsl:template>
   <xsl:template name="addBlankLines">
      <xsl:param name="numLines"/>
      <xsl:if test="string($numLines) != ''">
         <xsl:if test="$numLines &gt; 0">
            <fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0">
               <fo:leader leader-pattern="space"/>
            </fo:block>
            <xsl:call-template name="addBlankLines">
               <xsl:with-param name="numLines" select="$numLines - 1"/>
            </xsl:call-template>
         </xsl:if>
      </xsl:if>
   </xsl:template>
</xsl:stylesheet>
