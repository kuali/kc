<?xml version="1.0" encoding="UTF-8"?><!-- $Revision:   1.2  $ -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
 xmlns:att="http://apply.grants.gov/system/Attachments-V1.0"
 xmlns:glob="http://apply.grants.gov/system/Global-V1.0"
 xmlns:PHS398_Checklist="http://apply.grants.gov/forms/PHS398_Checklist-V1.1">
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
            <fo:flow flow-name="xsl-region-body"><!--Data components-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" keep-together="always" top="131.51515151515153px" height="13.333333333333334px" width="78.7878787878788px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"/>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="26.060606060606062px" hyphenate="true" language="en" keep-together="always" top="587.8787878787879px" height="13.333333333333334px" width="94.54545454545455px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"/>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="33.333333333333336px" hyphenate="true" language="en" keep-together="always" top="152.72727272727275px" height="12.121212121212121px" width="284.8484848484849px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:ApplicationType[1]"/>
                              <xsl:with-param name="schemaChoice">New</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="33.93939393939394px" hyphenate="true" language="en" keep-together="always" top="272.1212121212121px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274F;</fo:inline>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="33.93939393939394px" hyphenate="true" language="en" keep-together="always" top="269.0909090909091px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IsChangeOfPDPI[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IsChangeOfPDPI[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="checkbox">
                              <xsl:with-param name="value" select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IsChangeOfPDPI[1]"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="33.93939393939394px" hyphenate="true" language="en" keep-together="always" top="415.7575757575758px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274F;</fo:inline>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="33.93939393939394px" hyphenate="true" language="en" keep-together="always" top="412.72727272727275px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IsChangeOfInstitution[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IsChangeOfInstitution[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="checkbox">
                              <xsl:with-param name="value" select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IsChangeOfInstitution[1]"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="33.93939393939394px" hyphenate="true" language="en" keep-together="always" top="461.81818181818187px" height="23.03030303030303px" width="483.6363636363637px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FormerInstitutionName[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FormerInstitutionName[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FormerInstitutionName[1]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="75.15151515151516px" hyphenate="true" language="en" keep-together="always" top="152.72727272727275px" height="12.121212121212121px" width="328.4848484848485px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:ApplicationType[1]"/>
                              <xsl:with-param name="schemaChoice">Resubmission</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="77.57575757575758px" hyphenate="true" language="en" keep-together="always" top="327.2727272727273px" height="13.333333333333334px" width="96.96969696969697px">
                  <fo:block background-color="transparent" color="#000000" text-align="start" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FormerPD_Name[1]/globLib:PrefixName[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FormerPD_Name[1]/globLib:PrefixName[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FormerPD_Name[1]/globLib:PrefixName[1]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="77.57575757575758px" hyphenate="true" language="en" keep-together="always" top="341.21212121212125px" height="13.333333333333334px" width="280px">
                  <fo:block background-color="transparent" color="#000000" text-align="start" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FormerPD_Name[1]/globLib:FirstName[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FormerPD_Name[1]/globLib:FirstName[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FormerPD_Name[1]/globLib:FirstName[1]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="77.57575757575758px" hyphenate="true" language="en" keep-together="always" top="353.93939393939394px" height="13.333333333333334px" width="202.42424242424244px">
                  <fo:block background-color="transparent" color="#000000" text-align="start" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FormerPD_Name[1]/globLib:MiddleName[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FormerPD_Name[1]/globLib:MiddleName[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FormerPD_Name[1]/globLib:MiddleName[1]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="77.57575757575758px" hyphenate="true" language="en" keep-together="always" top="366.6666666666667px" height="13.333333333333334px" width="478.7878787878788px">
                  <fo:block background-color="transparent" color="#000000" text-align="start" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FormerPD_Name[1]/globLib:LastName[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FormerPD_Name[1]/globLib:LastName[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FormerPD_Name[1]/globLib:LastName[1]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="77.57575757575758px" hyphenate="true" language="en" keep-together="always" top="380px" height="13.333333333333334px" width="96.96969696969697px">
                  <fo:block background-color="transparent" color="#000000" text-align="start" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FormerPD_Name[1]/globLib:SuffixName[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FormerPD_Name[1]/globLib:SuffixName[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FormerPD_Name[1]/globLib:SuffixName[1]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="98.18181818181819px" hyphenate="true" language="en" keep-together="always" top="177.5757575757576px" height="13.333333333333334px" width="246.06060606060606px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FederalID[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FederalID[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:FederalID[1]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="157.5757575757576px" hyphenate="true" language="en" keep-together="always" top="152.72727272727275px" height="12.121212121212121px" width="304.24242424242425px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:ApplicationType[1]"/>
                              <xsl:with-param name="schemaChoice">Renewal</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="167.27272727272728px" hyphenate="true" language="en" keep-together="always" top="589.6969696969697px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IsPreviouslyReported[1]"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="167.87878787878788px" hyphenate="true" language="en" keep-together="always" top="538.1818181818182px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IsInventionsAndPatents[1]"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="204.24242424242425px" hyphenate="true" language="en" keep-together="always" top="538.1818181818182px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IsInventionsAndPatents[1]"/>
                              <xsl:with-param name="schemaChoice">N: No</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="205.45454545454547px" hyphenate="true" language="en" keep-together="always" top="589.6969696969697px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IsPreviouslyReported[1]"/>
                              <xsl:with-param name="schemaChoice">N: No</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="215.75757575757578px" hyphenate="true" language="en" keep-together="always" top="152.72727272727275px" height="12.121212121212121px" width="328.4848484848485px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:ApplicationType[1]"/>
                              <xsl:with-param name="schemaChoice">Continuation</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="261.8181818181818px" hyphenate="true" language="en" keep-together="always" top="538.1818181818182px" height="12.121212121212121px" width="13.333333333333334px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal"/>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="263.6363636363636px" hyphenate="true" language="en" keep-together="always" top="589.6969696969697px" height="12.121212121212121px" width="13.333333333333334px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal"/>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="290.3030303030303px" hyphenate="true" language="en" keep-together="always" top="152.72727272727275px" height="12.121212121212121px" width="313.93939393939394px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:ApplicationType[1]"/>
                              <xsl:with-param name="schemaChoice">Revision</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="401.8181818181818px" hyphenate="true" language="en" keep-together="always" top="152.72727272727275px" height="12.121212121212121px" width="13.333333333333334px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal"/>
               </fo:block-container><!--Write labels-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.212121212121215px" hyphenate="true" language="en" keep-together="always" top="85.45454545454545px" height="15.757575757575758px" width="103.03030303030303px">
                  <fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">1. Application Type:</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.212121212121215px" hyphenate="true" language="en" keep-together="always" top="240.60606060606062px" height="15.757575757575758px" width="300.2727272727273px">
                  <fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">2. Change of Investigator / Change of Institution Questions</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" keep-together="always" top="130.9090909090909px" height="13.333333333333334px" width="78.7878787878788px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Type of Application:</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="24.242424242424242px" hyphenate="true" language="en" keep-together="always" top="353.93939393939394px" height="13.333333333333334px" width="53.333333333333336px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Middle Name:</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="25.454545454545457px" hyphenate="true" language="en" keep-together="always" top="340.6060606060606px" height="13.333333333333334px" width="52.121212121212125px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* First Name:</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="33.060606060606062px" hyphenate="true" language="en" keep-together="always" top="587.8787878787879px" height="13.333333333333334px" width="94.54545454545455px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Previously Reported:  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="26.666666666666668px" hyphenate="true" language="en" keep-together="always" top="449.0909090909091px" height="12.121212121212121px" width="144.24242424242425px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Name of former institution:</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="27.272727272727273px" hyphenate="true" language="en" keep-together="always" top="366.6666666666667px" height="13.333333333333334px" width="50.303030303030305px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Last Name:</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="32.121212121212125px" hyphenate="true" language="en" keep-together="always" top="101.21212121212122px" height="23.03030303030303px" width="478.7878787878788px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">From SF 424 (R&amp;R) Cover Page.  The responses provided on the R&amp;R cover page are repeated here for your reference, as you answer the questions that are specific to the PHS398.</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="33.333333333333336px" hyphenate="true" language="en" keep-together="always" top="177.5757575757576px" height="12.121212121212121px" width="100.60606060606061px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Federal Identifier: </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="33.333333333333336px" hyphenate="true" language="en" keep-together="always" top="304.24242424242425px" height="12.121212121212121px" width="289.6969696969697px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Name of former principal investigator / program director:  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="33.333333333333336px" hyphenate="true" language="en" keep-together="always" top="538.1818181818182px" height="13.333333333333334px" width="100.27272727272728px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Inventions and Patents: </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="33.333333333333336px" hyphenate="true" language="en" keep-together="always" top="564.8484848484849px" height="13.333333333333334px" width="210.5757575757576px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">If the answer is "Yes" then please answer the following:</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="33.93939393939394px" hyphenate="true" language="en" keep-together="always" top="512.7272727272727px" height="15.757575757575758px" width="287.8787878787879px">
                  <fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">3. Inventions and Patents    (For renewal applications only)</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="42.42424242424243px" hyphenate="true" language="en" keep-together="always" top="272.1212121212121px" height="12.121212121212121px" width="250.90909090909093px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Change of principal investigator / program director</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="42.42424242424243px" hyphenate="true" language="en" keep-together="always" top="415.7575757575758px" height="12.121212121212121px" width="144.24242424242425px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Change of Grantee Institution</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="44.24242424242424px" hyphenate="true" language="en" keep-together="always" top="152.72727272727275px" height="13.333333333333334px" width="23.03030303030303px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">New</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="50.303030303030305px" hyphenate="true" language="en" keep-together="always" top="327.2727272727273px" height="13.333333333333334px" width="27.272727272727273px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Prefix:</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="50.909090909090914px" hyphenate="true" language="en" keep-together="always" top="380.6060606060606px" height="13.333333333333334px" width="26.666666666666668px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Suffix:</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="86.06060606060606px" hyphenate="true" language="en" keep-together="always" top="152.72727272727275px" height="13.333333333333334px" width="61.81818181818182px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Resubmission</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="150.3030303030303px" hyphenate="true" language="en" keep-together="always" top="538.1818181818182px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Yes</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="150.3030303030303px" hyphenate="true" language="en" keep-together="always" top="589.6969696969697px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Yes</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="168.4848484848485px" hyphenate="true" language="en" keep-together="always" top="152.72727272727275px" height="13.333333333333334px" width="36.96969696969697px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Renewal</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="191.51515151515153px" hyphenate="true" language="en" keep-together="always" top="538.1818181818182px" height="12.121212121212121px" width="13.333333333333334px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">No</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="192.12121212121212px" hyphenate="true" language="en" keep-together="always" top="589.6969696969697px" height="12.121212121212121px" width="13.333333333333334px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">No</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="221.81818181818184px" hyphenate="true" language="en" keep-together="always" top="8.484848484848484px" height="24.242424242424242px" width="134.54545454545456px">
                  <fo:block background-color="transparent" color="#000000" text-align="center" font-size="14pt" font-style="normal" font-family="Helvetica" font-weight="bold">PHS 398 Checklist</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="226.66666666666669px" hyphenate="true" language="en" keep-together="always" top="152.72727272727275px" height="13.333333333333334px" width="51.515151515151516px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Continuation</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="301.21212121212125px" hyphenate="true" language="en" keep-together="always" top="152.72727272727275px" height="12.121212121212121px" width="42.42424242424243px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Revision</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="430.3030303030303px" hyphenate="true" language="en" keep-together="always" top="62.42424242424243px" height="12.121212121212121px" width="129.69696969696972px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Expiration Date: 9/30/2007</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="455.3030303030303px" hyphenate="true" language="en" keep-together="always" top="49.6969696969697px" height="12.121212121212121px" width="105.45454545454545px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 0925-0001</fo:block>
               </fo:block-container><!--Draw lines-->
               <fo:block-container background-color="black" border-style="none" position="absolute" left="17.575757575757578px" top="75.75757575757576px" width="545.4545454545455px" height="1.2121212121212122px">
               <fo:block/>
                </fo:block-container>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="16.96969696969697px" top="76.36363636363637px" width="1.2121212121212122px" height="545.4545454545455px">
               <fo:block/> 
               </fo:block-container>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="561.8181818181819px" top="76.96969696969697px" width="1.2121212121212122px" height="545.4545454545455px">
               <fo:block/>
               </fo:block-container>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="16.96969696969697px" top="216.96969696969697px" width="545.4545454545455px" height="1.2121212121212122px">
               <fo:block/>
                </fo:block-container>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="17.575757575757578px" top="497.5757575757576px" width="545.4545454545455px" height="1.2121212121212122px">
               <fo:block/>
               </fo:block-container>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="16.96969696969697px" top="620.6060606060606px" width="545.4545454545455px" height="1.2121212121212122px">
               <fo:block/>
               </fo:block-container>
            </fo:flow>
         </fo:page-sequence>
         <fo:page-sequence master-reference="default-page" format="1" initial-page-number="2">
            <fo:static-content flow-name="xsl-region-after">
               <fo:block>
                  <fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
                  </fo:inline>
               </fo:block>
            </fo:static-content>
            <fo:flow flow-name="xsl-region-body"><!--Data components-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="26.060606060606062px" hyphenate="true" language="en" keep-together="always" top="108.48484848484848px" height="13.333333333333334px" width="321.21212121212125px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"/>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="37.57575757575758px" hyphenate="true" language="en" keep-together="always" top="299.39393939393943px" height="13.333333333333334px" width="23.03030303030303px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[3]/PHS398_Checklist:BudgetPeriod[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[3]/PHS398_Checklist:BudgetPeriod[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[3]/PHS398_Checklist:BudgetPeriod[1], '#,##0')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="37.57575757575758px" hyphenate="true" language="en" keep-together="always" top="336.969696969697px" height="13.333333333333334px" width="23.03030303030303px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[4]/PHS398_Checklist:BudgetPeriod[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[4]/PHS398_Checklist:BudgetPeriod[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[4]/PHS398_Checklist:BudgetPeriod[1], '#,##0')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="38.18181818181819px" hyphenate="true" language="en" keep-together="always" top="220.60606060606062px" height="13.333333333333334px" width="23.03030303030303px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[1]/PHS398_Checklist:BudgetPeriod[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[1]/PHS398_Checklist:BudgetPeriod[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[1]/PHS398_Checklist:BudgetPeriod[1], '#,##0')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="38.18181818181819px" hyphenate="true" language="en" keep-together="always" top="261.21212121212125px" height="13.333333333333334px" width="23.03030303030303px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[2]/PHS398_Checklist:BudgetPeriod[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[2]/PHS398_Checklist:BudgetPeriod[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[2]/PHS398_Checklist:BudgetPeriod[1], '#,##0')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="38.18181818181819px" hyphenate="true" language="en" keep-together="always" top="373.93939393939394px" height="13.333333333333334px" width="23.03030303030303px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[5]/PHS398_Checklist:BudgetPeriod[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[5]/PHS398_Checklist:BudgetPeriod[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[5]/PHS398_Checklist:BudgetPeriod[1], '#,##0')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="65.9090909090909px" hyphenate="true" language="en" keep-together="always" top="299.39393939393943px" height="13.333333333333334px" width="84.24242424242425px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[3]/PHS398_Checklist:AnticipatedAmount[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[3]/PHS398_Checklist:AnticipatedAmount[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[3]/PHS398_Checklist:AnticipatedAmount[1], '#,##0.00')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="65.9090909090909px" hyphenate="true" language="en" keep-together="always" top="336.969696969697px" height="13.333333333333334px" width="84.24242424242425px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[4]/PHS398_Checklist:AnticipatedAmount[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[4]/PHS398_Checklist:AnticipatedAmount[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[4]/PHS398_Checklist:AnticipatedAmount[1], '#,##0.00')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="65.9090909090909px" hyphenate="true" language="en" keep-together="always" top="373.93939393939394px" height="13.333333333333334px" width="84.45454545454545px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[5]/PHS398_Checklist:AnticipatedAmount[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[5]/PHS398_Checklist:AnticipatedAmount[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[5]/PHS398_Checklist:AnticipatedAmount[1], '#,##0.00')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="65.12121212121212px" hyphenate="true" language="en" keep-together="always" top="221.21212121212122px" height="13.333333333333334px" width="84.63636363636364px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[1]/PHS398_Checklist:AnticipatedAmount[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[1]/PHS398_Checklist:AnticipatedAmount[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[1]/PHS398_Checklist:AnticipatedAmount[1], '#,##0.00')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="65.12121212121212px" hyphenate="true" language="en" keep-together="always" top="261.21212121212125px" height="13.333333333333334px" width="84.24242424242425px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[2]/PHS398_Checklist:AnticipatedAmount[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[2]/PHS398_Checklist:AnticipatedAmount[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[2]/PHS398_Checklist:AnticipatedAmount[1], '#,##0.00')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="74.54545454545455px" hyphenate="true" language="en" keep-together="always" top="133.93939393939394px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:ProgramIncome[1]"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="121.81818181818183px" hyphenate="true" language="en" keep-together="always" top="640px" height="13.333333333333334px" width="149.0909090909091px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist/PHS398_Checklist:CertificationExplanation/PHS398_Checklist:Certifications/att:FileName) or /PHS398_Checklist:PHS398_Checklist/PHS398_Checklist:CertificationExplanation/PHS398_Checklist:Certifications/att:FileName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_Checklist:PHS398_Checklist/PHS398_Checklist:CertificationExplanation/PHS398_Checklist:Certifications/att:FileName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="132.72727272727275px" hyphenate="true" language="en" keep-together="always" top="133.93939393939394px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:ProgramIncome[1]"/>
                              <xsl:with-param name="schemaChoice">N: No</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="155.15151515151516px" hyphenate="true" language="en" keep-together="always" top="298.7878787878788px" height="32.72727272727273px" width="396.3636363636364px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[3]/PHS398_Checklist:Source[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[1]/PHS398_Checklist:Source[3] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[3]/PHS398_Checklist:Source[1]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="155.15151515151516px" hyphenate="true" language="en" keep-together="always" top="336.3636363636364px" height="32.72727272727273px" width="396.3636363636364px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[4]/PHS398_Checklist:Source[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[4]/PHS398_Checklist:Source[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[4]/PHS398_Checklist:Source[1]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="155.75757575757578px" hyphenate="true" language="en" keep-together="always" top="221.21212121212122px" height="32.72727272727273px" width="396.3636363636364px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[1]/PHS398_Checklist:Source[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[1]/PHS398_Checklist:Source[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[1]/PHS398_Checklist:Source[1]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="155.75757575757578px" hyphenate="true" language="en" keep-together="always" top="260.6060606060606px" height="32.72727272727273px" width="396.3636363636364px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[2]/PHS398_Checklist:Source[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[1]/PHS398_Checklist:Source[2] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[2]/PHS398_Checklist:Source[1]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="155.75757575757578px" hyphenate="true" language="en" keep-together="always" top="373.33333333333337px" height="32.72727272727273px" width="396.3636363636364px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[5]/PHS398_Checklist:Source[1]) or //PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[5]/PHS398_Checklist:Source[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_Checklist:PHS398_Checklist[1]/PHS398_Checklist:IncomeBudgetPeriod[5]/PHS398_Checklist:Source[1]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="254.54545454545456px" hyphenate="true" language="en" keep-together="always" top="133.93939393939394px" height="12.121212121212121px" width="13.333333333333334px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal"/>
               </fo:block-container><!--Write labels-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="19.393939393939394px" hyphenate="true" language="en" keep-together="always" top="81.81818181818183px" height="12.121212121212121px" width="195.75757575757576px">
                  <fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">4. * Program Income</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="19.393939393939394px" hyphenate="true" language="en" keep-together="always" top="458.7878787878788px" height="15.757575757575758px" width="228.4848484848485px">
                  <fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">5. Assurances/Certifications  (see instructions)</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="25.454545454545457px" hyphenate="true" language="en" keep-together="always" top="203.03030303030303px" height="12.121212121212121px" width="66.66666666666667px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">*Budget Period</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="26.060606060606062px" hyphenate="true" language="en" keep-together="always" top="173.33333333333334px" height="23.03030303030303px" width="478.7878787878788px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">If you checked "yes" above (indicating that program income is anticipated), then use the format below to reflect the amount and &#xD;
source(s).  Otherwise, leave this section blank.</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="26.666666666666668px" hyphenate="true" language="en" keep-together="always" top="109.0909090909091px" height="13.333333333333334px" width="321.21212121212125px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Is program income anticipated during the periods for which the grant support is requested?</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="28.484848484848488px" hyphenate="false" language="en" keep-together="always" top="479.39393939393943px" height="32.72727272727273px" width="478.7878787878788px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">In agreeing to the assurances/certification section 18 on the SF424 (R&amp;R) form, the authorized organizational representative agrees to comply with the policies, assurances and/or certifications listed in the agency's application guide, when applicable. Descriptions of individual assurances/certifications are provided at: http://grants.nih.gov/grants/funding/424</fo:block>
               </fo:block-container>
              <!-- <fo:block-container background-color="transparent" border-style="none" position="absolute" left="28.484848484848488px" hyphenate="true" language="en" keep-together="always" top="522.4242424242425px" height="71.51515151515152px" width="478.7878787878788px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">*Human Subjects; *Research Using Human Embryonic Stem Cells;  *Research on Transplantation of Human Fetal Tissue;  *Women and Minority Inclusion Policy; *Inclusion of Children Policy; *Vertebrate Animals;  *Debarment and Suspension; *Drug- Free Workplace (applicable to new [Type 1] or revised [Type 1] applications only) ; *Lobbying;  *Non-Delinquency on Federal Debt;  *Research Misconduct; *Civil Rights (Form HHS 441 or HHS 690); *Handicapped Individuals (Form HHS 641 or HHS 690); *Sex Discrimination (Form HHS 639-A or HHS 690); *Age Discrimination (Form HHS 680 or HHS 690); *Recombinant DNA and Human Gene Transfer Research; *Financial Conflict of Interest (except Phase I SBIR/STTR); *Prohibited Research; *Select Agents; *Smoke-Free Workplace;  *STTR ONLY: Certification of Research Institution Participation.</fo:block>
               </fo:block-container>-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="28.484848484848488px" hyphenate="true" language="en" keep-together="always" top="611.5151515151515px" height="12.121212121212121px" width="153.93939393939394px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt"  text-decoration="underline" font-style="normal" font-family="Helvetica" font-weight="normal">If unable to certify compliance</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="74.54545454545455px" hyphenate="true" language="en" keep-together="always" top="640px" height="12.121212121212121px" width="61.81818181818182px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Explanation:</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="82.42424242424242px" hyphenate="true" language="en" keep-together="always" top="133.93939393939394px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Yes</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.03030303030303px" hyphenate="true" language="en" keep-together="always" top="203.03030303030303px" height="12.121212121212121px" width="110.30303030303031px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">*Anticipated Amount ($)</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="135.15151515151516px" hyphenate="true" language="en" keep-together="always" top="611.5151515151515px" height="12.121212121212121px" width="294.54545454545456px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">, where applicable, provide an explanation and attach below.</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="141.21212121212122px" hyphenate="true" language="en" keep-together="always" top="133.93939393939394px" height="12.121212121212121px" width="13.333333333333334px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">No</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="345.4545454545455px" hyphenate="true" language="en" keep-together="always" top="203.03030303030303px" height="12.121212121212121px" width="47.27272727272727px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">*Source(s)</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="432.06060606060606px" hyphenate="true" language="en" keep-together="always" top="42.42424242424243px" height="12.121212121212121px" width="129.69696969696972px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Expiration Date: 9/30/2007</fo:block>
               </fo:block-container>
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="455.3030303030303px" hyphenate="true" language="en" keep-together="always" top="29.090909090909093px" height="12.121212121212121px" width="105.45454545454545px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number. 0925-0001</fo:block>
               </fo:block-container><!--Draw lines-->
               <fo:block-container background-color="black" border-style="none" position="absolute" left="15.151515151515152px" top="55.75757575757576px" width="548.4848484848485px" height="1.2121212121212122px">
                   <fo:block/>
               </fo:block-container>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="15.151515151515152px" top="56.36363636363637px" width="1.2121212121212122px" height="666.6666666666667px">
                   <fo:block/>
               </fo:block-container>
               
               <fo:block-container background-color="black" border-style="none" position="absolute" left="562.4242424242425px" top="56.36363636363637px" width="1.2121212121212122px" height="666.6666666666667px">
                   <fo:block/>
               </fo:block-container>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="15.151515151515152px" top="721.2121212121212px" width="548.4848484848485px" height="1.2121212121212122px">
                   <fo:block/>
               </fo:block-container>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="14.545454545454547px" top="432.72727272727275px" width="548.4848484848485px" height="1.2121212121212122px">
                   <fo:block/>
               </fo:block-container>
            </fo:flow>
         </fo:page-sequence>
         <fo:page-sequence master-reference="default-page" format="1" initial-page-number="1">
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
                           <fo:block font-size="10pt">CertificationExplanation_attDataGroup0</fo:block>
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
                              <xsl:value-of select="//PHS398_Checklist:PHS398_Checklist/PHS398_Checklist:CertificationExplanation/PHS398_Checklist:Certifications/att:FileName"/>
                           </fo:block>
                        </fo:table-cell>
                        <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                           <fo:block font-size="8pt">
                              <xsl:value-of select="//PHS398_Checklist:PHS398_Checklist/PHS398_Checklist:CertificationExplanation/PHS398_Checklist:Certifications/att:MimeType"/>
                           </fo:block>
                        </fo:table-cell>
                     </fo:table-row>
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
            <fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
         </xsl:when>
         <xsl:otherwise>
            <fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
         </xsl:otherwise>
      </xsl:choose>
   </xsl:template>
   <xsl:template name="checkbox">
      <xsl:param name="value"/>
      <xsl:param name="schemaChoice">Y: Yes</xsl:param>
      <xsl:if test="$value = $schemaChoice">
         <fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="11pt">&#x2714;</fo:inline>
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
            <fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0">
               <fo:leader leader-pattern="space"/>
            </fo:block>
            <xsl:call-template name="addBlankLines">
               <xsl:with-param name="numLines" select="$numLines - 1"/>
            </xsl:call-template>
         </xsl:if>
      </xsl:if>
   </xsl:template>
</xsl:stylesheet>
