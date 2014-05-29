<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:PHS_CoverLetter_1_2="http://apply.grants.gov/forms/PHS_CoverLetter_1_2-V1.2">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.2in" margin-right="0.2in">
                    <fo:region-body margin-top="0.2in" margin-bottom="0.4in"/>
                    <fo:region-after extent="0.3in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="default-page" format="1" initial-page-number="1">
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block>
                        <fo:inline font-size="8px">
                              Tracking Number: 
                              <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
                        </fo:inline>
                    </fo:block>
                </fo:static-content>
                
                <fo:flow flow-name="xsl-region-body">
                    <fo:block-container background-color="black" border-style="none" position="absolute" left="10.96969696969697px" top="96.96969696969697px" width="533.3333333333334px" height="1.2121212121212122px">
                  				<fo:block/>
                	</fo:block-container>
                    
                    <fo:block-container background-color="black" border-style="none" position="absolute" left="10.96969696969697px" top="701.96969696969697px" width="533.3333333333334px" height="1.2121212121212122px">
                       	<fo:block/>
                 	</fo:block-container>
                    
                    <fo:block-container background-color="black" border-style="none" position="absolute" left="10.96969696969697px" top="96.3939393939394px" width="0.6060606060606061px" height="605.3636363636364px">
                     	<fo:block/>
                 	</fo:block-container>
                    
                    <fo:block-container background-color="black" border-style="none" position="absolute" left="543.8787878787879px" top="96.96969696969697px" width="0.6060606060606061px" height="605.3636363636364px">
                      	<fo:block/>
                	</fo:block-container>
                   
                   <fo:block-container background-color="transparent" border-style="none" position="absolute" left="161.8181818181818px" hyphenate="true" language="en" top="115.75757575757576px" height="13.333333333333334px" width="318.7878787878788px">
                        <fo:block font-size="9px" font-family="Georgia">
                            <xsl:choose>
                                <xsl:when test="not(//PHS_CoverLetter_1_2:PHS_CoverLetter_1_2/PHS_CoverLetter_1_2:CoverLetterFile/PHS_CoverLetter_1_2:CoverLetterFilename/att:FileName) or /PHS_CoverLetter_1_2:PHS_CoverLetter_1_2/PHS_CoverLetter_1_2:CoverLetterFile/PHS_CoverLetter_1_2:CoverLetterFilename/att:FileName = ''">
                                    <fo:inline color="#FFFFFF"> </fo:inline>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:value-of select="//PHS_CoverLetter_1_2:PHS_CoverLetter_1_2/PHS_CoverLetter_1_2:CoverLetterFile/PHS_CoverLetter_1_2:CoverLetterFilename/att:FileName"/>
                                </xsl:otherwise>
                            </xsl:choose>
                        </fo:block>
                    </fo:block-container>
                    
                    <fo:block-container background-color="transparent" border-style="none" position="absolute" left="200.181818181818183px" hyphenate="true" language="en" top="40.30303030303031px" height="20.606060606060606px" width="160.3939393939394px">
                        <fo:block background-color="transparent" color="#000000" font-size="12px" font-style="normal" font-family="Helvetica" font-weight="bold">PHS Cover Letter</fo:block>
                    </fo:block-container>
                    
                    <fo:block-container background-color="transparent" border-style="none" position="absolute" left="450.3030303030303px" hyphenate="true" language="en" top="63.63636363636364px" height="12.121212121212121px" width="105.45454545454545px">
                        <fo:block background-color="transparent" color="#000000" font-size="6px" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 0925-0001</fo:block>
                    </fo:block-container>
                    
                    <fo:block-container background-color="transparent" border-style="none" position="absolute" left="446.06060606060606px" hyphenate="true" language="en" top="76.96969696969697px" height="12.121212121212121px" width="129.69696969696972px">
                        <fo:block background-color="transparent" color="#000000" font-size="6px" font-style="normal" font-family="Helvetica" font-weight="normal">0925-0002</fo:block>
                    </fo:block-container>
                    
                    <fo:block-container background-color="transparent" border-style="none" position="absolute" left="18.78787878787879px" hyphenate="true" language="en" top="115.15151515151516px" height="13.333333333333334px" width="140.27272727272728px">
                        <fo:block background-color="transparent" color="#000000" font-size="9px" font-style="normal" font-family="Helvetica" font-weight="bold"> Mandatory Cover Letter Filename:*</fo:block>
                    </fo:block-container>
                </fo:flow>
            </fo:page-sequence>
            
            <fo:page-sequence master-reference="default-page" format="1" initial-page-number="2">
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block>
                        <fo:inline font-size="8px">
  							Tracking Number: 
  							<xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
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
                                    <fo:block font-size="10px" font-weight="bold">Attachments</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
                                    <xsl:call-template name="addBlankLines">
                                        <xsl:with-param name="numLines">1</xsl:with-param>
                                    </xsl:call-template>
                                    <fo:block font-size="9px">CoverLetterFilename_attDataGroup0</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                                    <fo:block font-size="9px">File Name</fo:block>
                                </fo:table-cell>
                                <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                                    <fo:block font-size="8pt" colr="white">Mime Type</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                                    <fo:block font-size="9px" font-family="Georgia">
                                        <xsl:value-of select="//PHS_CoverLetter_1_2:PHS_CoverLetter_1_2/PHS_CoverLetter_1_2:CoverLetterFile/PHS_CoverLetter_1_2:CoverLetterFilename/att:FileName"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
                                    <fo:block font-size="8pt" color="white">
                                        <xsl:value-of select="//PHS_CoverLetter_1_2:PHS_CoverLetter_1_2/PHS_CoverLetter_1_2:CoverLetterFile/PHS_CoverLetter_1_2:CoverLetterFilename/att:MimeType"/>
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
        <xsl:param name="schemaChoice">Yes</xsl:param>
        <xsl:choose>
            <xsl:when test="$value = $schemaChoice">
                <fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="9px">&#x25cf;</fo:inline>
            </xsl:when>
            <xsl:otherwise>
                <fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="9px">&#x274d;</fo:inline>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    <xsl:template name="checkbox">
        <xsl:param name="value"/>
        <xsl:param name="schemaChoice">Yes</xsl:param>
        <xsl:if test="$value = $schemaChoice">
            <fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="9px">&#x2714;</fo:inline>
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
            <xsl:if test="$numLines > 0">
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
