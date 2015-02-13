--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

DELIMITER /
INSERT INTO SEQ_SPONSOR_FORM_TEMPLATES VALUES(NULL)
/
INSERT INTO SPONSOR_FORM_TEMPLATES (SPONSOR_FORM_TEMPLATE_ID,SPONSOR_FORM_ID,PAGE_NUMBER,PAGE_DESCRIPTION,FILE_NAME,CONTENT_TYPE,FORM_TEMPLATE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM SEQ_SPONSOR_FORM_TEMPLATES),(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE PACKAGE_NAME = 'NIH 2590 package (Coeus 4.0)'),1,'Face Page','Face Page.xslt','text/xml',
'<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:common="http://era.nih.gov/Projectmgmt/SBIR/CGAP/common.namespace" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:nih="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace" xmlns:phs398="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace" xmlns:rar="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:output version="1.0" method="xml" encoding="UTF-8" indent="no"/>
<xsl:param name="SV_OutputFormat" select="''PDF''"/>
<xsl:variable name="XML" select="/"/>
<xsl:variable name="fo:layout-master-set">
<fo:layout-master-set>
<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.4in" margin-right="0.4in">
<fo:region-body margin-top="0.5in" margin-bottom="0.5in"/>
<fo:region-before extent="0.5in"/>
<fo:region-after extent="0.5in"/>
</fo:simple-page-master>
</fo:layout-master-set>
</xsl:variable>
<xsl:template match="/">
<fo:root>
<xsl:copy-of select="$fo:layout-master-set"/>
<fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
<xsl:call-template name="headerall"/>
<xsl:call-template name="footerall"/>
<fo:flow flow-name="xsl-region-body">
<fo:block>
<xsl:for-each select="$XML">
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:table font-size="7pt" line-height="10pt" table-layout="fixed" width="7.7in" border="solid 1pt gray" border-spacing="2pt">
<fo:table-column column-width="1.2in"/>
<fo:table-column column-width="1.3in"/>
<fo:table-column column-width="1.5in"/>
<fo:table-column column-width="40"/>
<fo:table-column column-width="38"/>
<fo:table-column column-width="54"/>
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-body font-family="Verdana" font-size="8pt" start-indent="0pt">
<fo:table-row>
<fo:table-cell line-height="9pt" number-columns-spanned="3" number-rows-spanned="3" border="solid 1pt gray" padding="2pt" text-align="center" display-align="before">
<fo:block>
<fo:block/>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:text>Department of Health and Human Services</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:text>Public Health Services</xsl:text>
</fo:inline>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block>
<fo:leader leader-pattern="space"/>
</fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block margin="0pt">
<fo:block/>
</fo:block>
<fo:block>
<fo:leader leader-pattern="space"/>
</fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block margin="0pt">
<fo:block/>
</fo:block>
<fo:block>
<fo:leader leader-pattern="space"/>
</fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block margin="0pt">
<fo:block/>
</fo:block>
<fo:block/>
<fo:block>
<fo:leader leader-pattern="space"/>
</fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block margin="0pt">
<fo:block>
<fo:inline font-family="Verdana" font-size="16pt" font-weight="bold">
<xsl:text>Grant Progress Report</xsl:text>
</fo:inline>
</fo:block>
</fo:block>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" border="solid 1pt gray" padding="2pt" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>Review Group</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" border="solid 1pt gray" padding="2pt" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>Type</xsl:text>
</fo:inline>
<fo:block/>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nihApplicationTypeCode">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" border="solid 1pt gray" padding="2pt" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>Activity</xsl:text>
</fo:inline>
<fo:block/>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nihActivityCode">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" number-columns-spanned="4" border="solid 1pt gray" padding="2pt" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>Grant Number</xsl:text>
</fo:inline>
<fo:block/>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nihPriorGrantNumber">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell line-height="9pt" number-columns-spanned="3" border="solid 1pt gray" padding="2pt" height="10" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>Total project Period</xsl:text>
</fo:inline>
<fo:block/>
<fo:block>
<fo:leader leader-pattern="space"/>
</fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block margin="0pt">
<fo:block>
<fo:inline bottom="auto" font-family="Verdana" font-size="8pt">
<xsl:text>From: </xsl:text>
</fo:inline>
</fo:block>
</fo:block>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="totalProjectStartDt">
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:value-of select="format-number(number(substring(string(string(.)), 6, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(.)), 9, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(string(.))), 1, 4)), ''0000'')"/>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" number-columns-spanned="4" border="solid 1pt gray" padding="2pt" height="10" display-align="center">
<fo:block>
<fo:block>
<fo:leader leader-pattern="space"/>
</fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block line-height="9pt" margin="0pt">
<fo:block>
<fo:inline font-size="8pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
</fo:block>
</fo:block>
<fo:inline bottom="0.5em" font-family="Verdana" font-size="8pt">
<xsl:text>Through: </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="totalProjectEndDt">
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:value-of select="format-number(number(substring(string(string(.)), 6, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(.)), 9, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(string(.))), 1, 4)), ''0000'')"/>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
<fo:inline bottom="0.5em" font-family="Verdana" font-size="8pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell line-height="9pt" number-columns-spanned="3" border="solid 1pt gray" padding="2pt" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>Requested Budget Period</xsl:text>
</fo:inline>
<fo:block>
<fo:leader leader-pattern="space"/>
</fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block margin="0pt">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>From: </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="ProjectDates">
<xsl:for-each select="ProjectStartDate">
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:value-of select="format-number(number(substring(string(string(.)), 6, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(.)), 9, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(string(.))), 1, 4)), ''0000'')"/>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:block>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" number-columns-spanned="4" border="solid 1pt gray" padding="2pt" display-align="center">
<fo:block>
<fo:block>
<fo:leader leader-pattern="space"/>
</fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block line-height="9pt" margin="0pt">
<fo:block>
<fo:inline font-size="8pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
</fo:block>
</fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>Through: </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="ProjectDates">
<xsl:for-each select="ProjectEndDate">
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:value-of select="format-number(number(substring(string(string(.)), 6, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(.)), 9, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(string(.))), 1, 4)), ''0000'')"/>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell line-height="9pt" number-columns-spanned="10" border="solid 1pt gray" padding="2pt" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>1. TITLE OF PROJECT:&#160;&#160; </xsl:text>
</fo:inline>
<fo:block/>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="ProjectTitle">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell line-height="9pt" number-columns-spanned="3" number-rows-spanned="4" border="solid 1pt gray" padding="2pt" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>2a. PRINCIPAL INVESTIGATOR OR PROGRAM DIRECTOR</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline font-family="Verdana" font-size="7pt">
<xsl:text>&#160;&#160; (Name and address, street, city, state, zip code)</xsl:text>
</fo:inline>
<fo:block>
<fo:leader leader-pattern="space"/>
</fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block margin="0pt">
<fo:block/>
</fo:block>
<fo:inline font-size="7pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="nih:ProgramDirectorPrincipalInvestigator">
<xsl:for-each select="Name">
<xsl:for-each select="LastName">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:text>, </xsl:text>
</fo:inline>
<xsl:for-each select="FirstName">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
<xsl:for-each select="MiddleName">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:block/>
<fo:inline font-size="8pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="nih:ProgramDirectorPrincipalInvestigator">
<xsl:for-each select="ContactInformation">
<xsl:for-each select="PostalAddress">
<xsl:for-each select="Street">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:block/>
<fo:inline font-size="8pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="nih:ProgramDirectorPrincipalInvestigator">
<xsl:for-each select="ContactInformation">
<xsl:for-each select="PostalAddress">
<xsl:for-each select="City">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:text>, </xsl:text>
</fo:inline>
<xsl:for-each select="State">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
<xsl:for-each select="PostalCode">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" number-columns-spanned="7" border="solid 1pt gray" padding="2pt" height="20" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>2b. E-MAIL ADDRESS</xsl:text>
</fo:inline>
<fo:block/>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="nih:ProgramDirectorPrincipalInvestigator">
<xsl:for-each select="ContactInformation">
<xsl:for-each select="Email">
<fo:inline font-size="8pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell line-height="9pt" number-columns-spanned="7" border="solid 1pt gray" padding="2pt" height="20" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>2c. DEPARTMENT, SERVICE, LABORATORY, OR EQUIVALENT</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline>
<xsl:value-of select="nih:ResearchAndRelatedProject/KeyPerson[1]/OrganizationDepartment"/>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell line-height="9pt" number-columns-spanned="7" border="solid 1pt gray" padding="2pt" height="20" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>2d. MAJOR SUBDIVISION</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline>
<xsl:value-of select="nih:ResearchAndRelatedProject/KeyPerson[1]/OrganizationDivision"/>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell line-height="9pt" number-columns-spanned="3" border="solid 1pt gray" padding="2pt" height="20" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>2e. Tel:</xsl:text>
</fo:inline>
<fo:block/>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="nih:ProgramDirectorPrincipalInvestigator">
<xsl:for-each select="ContactInformation">
<xsl:for-each select="PhoneNumber">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block>
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline>
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" number-columns-spanned="4" border="solid 1pt gray" padding="2pt" height="20" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>Fax:</xsl:text>
</fo:inline>
<fo:block/>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="nih:ProgramDirectorPrincipalInvestigator">
<xsl:for-each select="ContactInformation">
<xsl:for-each select="FaxNumber">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block>
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline>
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell line-height="9pt" number-columns-spanned="3" number-rows-spanned="3" border="solid 1pt gray" padding="2pt" height="1" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>3a. APPLICANT ORGANIZATION</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
<fo:inline font-family="Verdana" font-size="7pt">
<xsl:text>(Name and address, street, city, state, zip code)</xsl:text>
</fo:inline>
<fo:block>
<fo:leader leader-pattern="space"/>
</fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block margin="0pt">
<fo:block/>
</fo:block>
<fo:inline font-size="8pt">
<xsl:text>&#160;&#160;&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="ApplicantOrganization">
<xsl:for-each select="OrganizationName">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:block/>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="ApplicantOrganization">
<xsl:for-each select="OrganizationAddress">
<xsl:for-each select="Street">
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:block/>
<fo:inline font-size="9pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="ApplicantOrganization">
<xsl:for-each select="OrganizationAddress">
<xsl:for-each select="City">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:text>, </xsl:text>
</fo:inline>
<xsl:for-each select="State">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
<xsl:for-each select="PostalCode">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" number-columns-spanned="3" border="solid 1pt gray" padding="2pt" height="1" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>3b. Tel:</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" number-columns-spanned="4" border="solid 1pt gray" padding="2pt" height="1" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>Fax:</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell line-height="9pt" number-columns-spanned="7" border="solid 1pt gray" padding="2pt" height="1" display-align="before">
<fo:block>
<fo:inline>
<xsl:text>3c. DUNS:&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="ApplicantOrganization">
<xsl:for-each select="OrganizationDUNS">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block>
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline>
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell line-height="9pt" number-columns-spanned="7" border="solid 1pt gray" padding="2pt" height="1" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>4. ENTITY IDENTIFICATION NUMBER</xsl:text>
</fo:inline>
<fo:block/>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="ApplicantOrganization">
<xsl:for-each select="OrganizationEIN">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-bottom-style="none" line-height="9pt" number-columns-spanned="3" border="solid 1pt gray" padding="2pt" display-align="center">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>6.HUMAN SUBJECTS</xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ProjectDescription">
<xsl:for-each select="nih:HumanSubject">
<xsl:for-each select="HumanSubjectsUsedQuestion">
<fo:inline font-size="8pt">
<xsl:text>&#160; </xsl:text>
</fo:inline>
<fo:inline>
<xsl:text>&#160;&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
<xsl:choose>
<xsl:when test="string(.)=''false''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:when test="string(.)=''0''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline font-size="8pt" border="solid 1pt black">
<fo:leader leader-length="10pt" leader-pattern="space"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
<fo:inline font-size="8pt">
<xsl:text>&#160; </xsl:text>
</fo:inline>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>No</xsl:text>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ProjectDescription">
<xsl:for-each select="nih:HumanSubject">
<xsl:for-each select="HumanSubjectsUsedQuestion">
<fo:inline font-size="8pt">
<xsl:text>&#160; </xsl:text>
</fo:inline>
<fo:inline>
<xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
<xsl:choose>
<xsl:when test="string(.)=''true''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:when test="string(.)=''1''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline font-size="8pt" border="solid 1pt black">
<fo:leader leader-length="10pt" leader-pattern="space"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
<fo:inline font-size="8pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>Yes</xsl:text>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" number-columns-spanned="7" number-rows-spanned="2" border="solid 1pt gray" padding="2pt" height="41" display-align="before">
<fo:block>
<fo:inline font-size="8pt">
<xsl:text>5. </xsl:text>
</fo:inline>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>TITLE AND ADDRESS OF ADMINISTRATIVE OFFICIAL </xsl:text>
</fo:inline>
<fo:block/>
<fo:inline>
<xsl:text>&#160;</xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="ApplicantOrganization">
<xsl:for-each select="OrganizationContactPerson">
<xsl:for-each select="Name">
<xsl:for-each select="FirstName">
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
<xsl:for-each select="MiddleName">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
<xsl:for-each select="LastName">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:block/>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="ApplicantOrganization">
<xsl:for-each select="OrganizationContactPerson">
<xsl:for-each select="PositionTitle">
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:block/>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="ApplicantOrganization">
<xsl:for-each select="OrganizationContactPerson">
<xsl:for-each select="ContactInformation">
<xsl:for-each select="PostalAddress">
<xsl:for-each select="Street">
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:block/>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="ApplicantOrganization">
<xsl:for-each select="OrganizationContactPerson">
<xsl:for-each select="ContactInformation">
<xsl:for-each select="PostalAddress">
<xsl:for-each select="City">
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>, </xsl:text>
</fo:inline>
<xsl:for-each select="State">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
<xsl:for-each select="PostalCode">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-bottom-style="none" border-top-style="none" line-height="9pt" border="solid 1pt gray" padding="2pt" height="9" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>6a. Research Exempt </xsl:text>
</fo:inline>
<fo:block/>
<xsl:if test="count( nih:ResearchAndRelatedProject/nih:ProjectDescription/nih:HumanSubject/ExemptionNumber ) = 0">
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ProjectDescription">
<xsl:for-each select="nih:HumanSubject">
<xsl:for-each select="HumanSubjectsUsedQuestion">
<xsl:choose>
<xsl:when test="string(.)=''true''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:when test="string(.)=''1''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline border="solid 1pt black">
<fo:leader leader-length="10pt" leader-pattern="space"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:if>
<xsl:if test="count( nih:ResearchAndRelatedProject/nih:ProjectDescription/nih:HumanSubject/ExemptionNumber ) != 0">
<fo:inline>
<xsl:text>__</xsl:text>
</fo:inline>
</xsl:if>
<fo:inline>
<xsl:text> No&#160; </xsl:text>
</fo:inline>
<xsl:if test="count( nih:ResearchAndRelatedProject/nih:ProjectDescription/nih:HumanSubject/ExemptionNumber ) != 0">
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ProjectDescription">
<xsl:for-each select="nih:HumanSubject">
<xsl:for-each select="HumanSubjectsUsedQuestion">
<xsl:choose>
<xsl:when test="string(.)=''true''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:when test="string(.)=''1''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline border="solid 1pt black">
<fo:leader leader-length="10pt" leader-pattern="space"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:if>
<xsl:if test="count( nih:ResearchAndRelatedProject/nih:ProjectDescription/nih:HumanSubject/ExemptionNumber ) = 0">
<fo:inline>
<xsl:text>__</xsl:text>
</fo:inline>
</xsl:if>
<fo:inline>
<xsl:text>&#160; Yes</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell border-bottom-style="none" border-top-style="none" line-height="9pt" border="solid 1pt gray" padding="2pt" height="9" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>If&#160; Exempt (&quot;Yes&quot; in 6a):</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>Exemption No.</xsl:text>
</fo:inline>
<fo:block/>
<xsl:if test="count(nih:ResearchAndRelatedProject/nih:ProjectDescription/nih:HumanSubject/ExemptionNumber ) != 0">
<fo:inline>
<xsl:value-of select="nih:ResearchAndRelatedProject/nih:ProjectDescription/nih:HumanSubject/ExemptionNumber [1]"/>
</fo:inline>
</xsl:if>
</fo:block>
</fo:table-cell>
<fo:table-cell border-bottom-style="none" border-top-style="none" line-height="9pt" border="solid 1pt gray" padding="2pt" height="9" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>If Not Exempt (&quot;No&quot; in 6a):</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>IRB Approval Date:</xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ProjectDescription">
<xsl:for-each select="nih:HumanSubject">
<xsl:for-each select="IRBApprovalDate">
<xsl:if test="../../rar:AnimalSubject/VertebrateAnimalsUsedQuestion = &apos;true&apos;">
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:value-of select="format-number(number(substring(string(string(.)), 6, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(.)), 9, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(string(.))), 1, 4)), ''0000'')"/>
</fo:inline>
</xsl:if>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell line-height="9pt" number-columns-spanned="3" border="solid 1pt gray" padding="2pt" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>6b.Federal Wide Assurance No.</xsl:text>
</fo:inline>
<fo:block/>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ProjectDescription">
<xsl:for-each select="nih:HumanSubject">
<xsl:for-each select="AssuranceNumber">
<fo:inline font-size="8pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" number-columns-spanned="3" border="solid 1pt gray" padding="2pt" display-align="before">
<fo:block>
<fo:inline>
<xsl:text>Tel:</xsl:text>
</fo:inline>
<fo:block/>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="ApplicantOrganization">
<xsl:for-each select="OrganizationContactPerson">
<xsl:for-each select="ContactInformation">
<xsl:for-each select="PhoneNumber">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block>
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline>
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" number-columns-spanned="4" border="solid 1pt gray" padding="2pt" display-align="before">
<fo:block>
<fo:inline>
<xsl:text>Fax:</xsl:text>
</fo:inline>
<fo:block/>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="ApplicantOrganization">
<xsl:for-each select="OrganizationContactPerson">
<xsl:for-each select="ContactInformation">
<xsl:for-each select="FaxNumber">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block>
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline>
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-bottom-style="none" line-height="9pt" number-columns-spanned="3" border="solid 1pt gray" padding="2pt" height="4" display-align="center">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>6c. NIH-Defined Phase III</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>Clinical Trial&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ProjectDescription">
<xsl:for-each select="nih:HumanSubject">
<xsl:for-each select="Phase3ClinicalTrialQuestion">
<xsl:choose>
<xsl:when test="string(.)=''false''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:when test="string(.)=''0''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="8pt" border="solid 1pt black">
<fo:leader leader-length="10pt" leader-pattern="space"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text> No&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ProjectDescription">
<xsl:for-each select="nih:HumanSubject">
<xsl:for-each select="Phase3ClinicalTrialQuestion">
<xsl:choose>
<xsl:when test="string(.)=''true''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:when test="string(.)=''1''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="8pt" border="solid 1pt black">
<fo:leader leader-length="10pt" leader-pattern="space"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>&#160; Yes</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" number-columns-spanned="7" border="solid 1pt gray" padding="2pt" display-align="before">
<fo:block>
<fo:inline>
<xsl:text>E-MAIL:</xsl:text>
</fo:inline>
<fo:block/>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="ApplicantOrganization">
<xsl:for-each select="OrganizationContactPerson">
<xsl:for-each select="ContactInformation">
<xsl:for-each select="Email">
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>&#160; </xsl:text>
</fo:inline>
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-bottom-style="none" line-height="9pt" number-columns-spanned="3" border="solid 1pt gray" padding="2pt" height="4" display-align="center">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>7. VERTEBRATE ANIMALS</xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ProjectDescription">
<xsl:for-each select="rar:AnimalSubject">
<xsl:for-each select="VertebrateAnimalsUsedQuestion">
<fo:inline font-size="8pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<xsl:choose>
<xsl:when test="string(.)=''false''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:when test="string(.)=''0''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline font-size="8pt" border="solid 1pt black">
<fo:leader leader-length="10pt" leader-pattern="space"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
<fo:inline font-size="8pt">
<xsl:text>&#160; </xsl:text>
</fo:inline>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>No</xsl:text>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ProjectDescription">
<xsl:for-each select="rar:AnimalSubject">
<xsl:for-each select="VertebrateAnimalsUsedQuestion">
<fo:inline font-size="8pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<xsl:choose>
<xsl:when test="string(.)=''true''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:when test="string(.)=''1''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline font-size="8pt" border="solid 1pt black">
<fo:leader leader-length="10pt" leader-pattern="space"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
<fo:inline font-size="8pt">
<xsl:text>&#160; </xsl:text>
</fo:inline>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>Yes</xsl:text>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell border-bottom-style="none" line-height="9pt" number-columns-spanned="7" number-rows-spanned="2" border="solid 1pt gray" padding="2pt" height="4" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>10. PROJECT/PERFORMANCE SITE(S)</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text> Organizational Name:</xsl:text>
</fo:inline>
<fo:block/>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="PrimaryProjectSite">
<xsl:for-each select="OrganizationName">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-bottom-style="none" border-left-style="none" border-top-style="none" line-height="9pt" number-columns-spanned="3" border="solid 1pt gray" padding="2pt" height="9" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>7a. If &quot;Yes&quot;, IACUC Approval Date</xsl:text>
</fo:inline>
<fo:block/>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ProjectDescription">
<xsl:for-each select="rar:AnimalSubject">
<xsl:for-each select="IACUCApprovalDate">
<xsl:if test="../VertebrateAnimalsUsedQuestion = &apos;true&apos;">
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:value-of select="format-number(number(substring(string(string(.)), 6, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(.)), 9, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(string(.))), 1, 4)), ''0000'')"/>
</fo:inline>
</xsl:if>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-bottom-style="none" border-left-style="none" border-top-style="none" line-height="9pt" number-columns-spanned="3" border="solid 1pt gray" padding="2pt" height="9" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>7b. Animal Welfare Assurance No.</xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ProjectDescription">
<xsl:for-each select="rar:AnimalSubject">
<xsl:for-each select="AssuranceNumber">
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:text>&#160;&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" number-columns-spanned="7" border="solid 1pt gray" padding="2pt" display-align="before">
<fo:block>
<fo:inline>
<xsl:text>DUNS:</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-bottom-style="none" line-height="9pt" number-columns-spanned="3" border="solid 1pt gray" padding="2pt" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>8. COSTS REQUESTED FOR NEXT BUDGET PERIOD</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell border-bottom-style="none" border-right-style="none" line-height="9pt" number-columns-spanned="7" number-rows-spanned="2" border="solid 1pt gray" padding="2pt" display-align="before">
<fo:block>
<fo:inline>
<xsl:text>Street 1:</xsl:text>
</fo:inline>
<fo:inline font-size="8pt">
<xsl:text>&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="PrimaryProjectSite">
<xsl:for-each select="PostalAddress">
<xsl:for-each select="Street">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block>
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline>
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:block text-align="center">
<fo:leader leader-pattern="rule" rule-thickness="0.5" leader-length="100%"/>
</fo:block>
<fo:block/>
<fo:inline>
<xsl:text>Street 2:</xsl:text>
</fo:inline>
<fo:inline font-size="8pt">
<xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-top-style="none" line-height="9pt" padding-top="10pt" number-columns-spanned="2" border="solid 1pt gray" padding="2pt" height="9" text-align="left" display-align="before">
<fo:block>
<fo:inline font-size="8pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>8a. DIRECT</xsl:text>
</fo:inline>
<fo:inline font-family="Verdana" font-size="7pt">
<xsl:text>&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="BudgetSummary">
<xsl:for-each select="BudgetPeriod">
<xsl:if test="BudgetPeriodID=1">
<xsl:for-each select="PeriodDirectCostsTotal">
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:text>$</xsl:text>
</fo:inline>
<fo:inline>
<xsl:value-of select="format-number(number(string(.)), ''#,###,###,###,##0'')"/>
</fo:inline>
</xsl:for-each>
</xsl:if>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell border-top-style="none" line-height="9pt" padding-top="10pt" border="solid 1pt gray" padding="2pt" height="9" display-align="before">
<fo:block>
<fo:inline font-size="8pt">
<xsl:text> 8b. </xsl:text>
</fo:inline>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>TOTAL </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="BudgetSummary">
<xsl:for-each select="BudgetPeriod">
<xsl:if test="BudgetPeriodID=1">
<xsl:for-each select="PeriodCostsTotal">
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:text>$</xsl:text>
</fo:inline>
<fo:inline>
<xsl:value-of select="format-number(number(string(.)), ''#,###,###,###,##0'')"/>
</fo:inline>
</xsl:for-each>
</xsl:if>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-bottom-style="none" border-right-style="none" line-height="9pt" number-columns-spanned="3" border="solid 1pt gray" padding="2pt" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>9. INVENTIONS AND PATENTS&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
<xsl:if test="boolean( nih:ResearchAndRelatedProject/nihInventions ) = false()">
<fo:inline font-size="8pt">
<xsl:text>__</xsl:text>
</fo:inline>
</xsl:if>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nihInventions">
<xsl:choose>
<xsl:when test="string(.)=''X''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:when test="string(.)=''x''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline font-size="8pt" border="solid 1pt black">
<fo:leader leader-length="10pt" leader-pattern="space"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
<fo:inline font-size="8pt">
<xsl:text>&#160; No&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
<xsl:if test="boolean( nih:ResearchAndRelatedProject/nihInventions ) = false()">
<fo:inline font-size="8pt">
<xsl:text>__</xsl:text>
</fo:inline>
</xsl:if>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nihInventions">
<xsl:choose>
<xsl:when test="string(.)=''Y''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:when test="string(.)=''y''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:when test="string(.)=''N''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:when test="string(.)=''n''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline font-size="8pt" border="solid 1pt black">
<fo:leader leader-length="10pt" leader-pattern="space"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
<fo:inline font-size="8pt">
<xsl:text>&#160; Yes </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" number-columns-spanned="3" border="solid 1pt gray" padding="2pt" height="25" display-align="before">
<fo:block>
<fo:inline>
<xsl:text>City:&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="PrimaryProjectSite">
<xsl:for-each select="PostalAddress">
<xsl:for-each select="City">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block>
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline>
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" number-columns-spanned="4" border="solid 1pt gray" padding="2pt" height="25" display-align="before">
<fo:block>
<fo:inline>
<xsl:text>County:&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="PrimaryProjectSite">
<xsl:for-each select="CountyName">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block>
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline>
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell line-height="9pt" number-columns-spanned="3" number-rows-spanned="3" border="solid 1pt gray" padding="2pt" height="25" display-align="before">
<fo:block>
<fo:inline>
<xsl:text>If &quot;Yes&quot;, </xsl:text>
</fo:inline>
<fo:block>
<fo:leader leader-pattern="space"/>
</fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block border-left-style="none" border-top-style="none" line-height="9pt" margin="0pt">
<fo:block>
<fo:inline>
<xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
<xsl:if test="boolean( nih:ResearchAndRelatedProject/nihInventions ) = false()">
<fo:inline font-size="8pt">
<xsl:text>__</xsl:text>
</fo:inline>
</xsl:if>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nihInventions">
<xsl:choose>
<xsl:when test="string(.)=''Y''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:when test="string(.)=''y''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline font-size="8pt" border="solid 1pt black">
<fo:leader leader-length="10pt" leader-pattern="space"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
<fo:inline font-size="8pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>Previously Reported</xsl:text>
</fo:inline>
</fo:block>
</fo:block>
<fo:block>
<fo:leader leader-pattern="space"/>
</fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block margin="0pt">
<fo:block/>
</fo:block>
<fo:inline>
<xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
<xsl:if test="boolean( nih:ResearchAndRelatedProject/nihInventions ) = false()">
<fo:inline font-size="8pt">
<xsl:text>__</xsl:text>
</fo:inline>
</xsl:if>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nihInventions">
<xsl:choose>
<xsl:when test="string(.)=''N''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:when test="string(.)=''n''">
<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="10pt ">
<xsl:text>&#x2714;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="8pt" border="solid 1pt black">
<fo:leader leader-length="10pt" leader-pattern="space"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>&#160;&#160; Not Previously Reported</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" number-columns-spanned="3" border="solid 1pt gray" padding="2pt" height="25" display-align="before">
<fo:block>
<fo:inline>
<xsl:text>State:&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="PrimaryProjectSite">
<xsl:for-each select="PostalAddress">
<xsl:for-each select="State">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block>
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline>
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" number-columns-spanned="4" border="solid 1pt gray" padding="2pt" height="25" display-align="before">
<fo:block>
<fo:inline>
<xsl:text>Province:</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell line-height="9pt" number-columns-spanned="3" border="solid 1pt gray" padding="2pt" height="25" display-align="before">
<fo:block>
<fo:inline>
<xsl:text>Country:&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="PrimaryProjectSite">
<xsl:for-each select="PostalAddress">
<xsl:for-each select="Country">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block>
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline>
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" number-columns-spanned="4" border="solid 1pt gray" padding="2pt" height="25" display-align="before">
<fo:block>
<fo:inline>
<xsl:text>Zip/Postal Code:&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="PrimaryProjectSite">
<xsl:for-each select="PostalAddress">
<xsl:for-each select="PostalCode">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block>
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline>
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell line-height="9pt" number-columns-spanned="7" border="solid 1pt gray" padding="2pt" height="25" display-align="before">
<fo:block>
<fo:inline>
<xsl:text>Congressional Districts:&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="PrimaryProjectSite">
<xsl:for-each select="CongressionalDistrict">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block>
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline>
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-bottom-style="none" line-height="9pt" number-columns-spanned="10" border="solid 1pt gray" padding="2pt" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="7pt">
<xsl:text>11.&#160; NAME AND TITLE OF OFFICIAL SIGNING FOR APPLICANT ORGANIZATION (item 13)</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline>
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>NAME:&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="AuthorizedOrganizationalRepresentative">
<xsl:for-each select="Name">
<xsl:for-each select="FirstName">
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
</xsl:for-each>
<xsl:for-each select="MiddleName">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
</xsl:for-each>
<xsl:for-each select="LastName">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:inline>
<xsl:text>&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
<fo:inline font-family="Verdana" font-size="7pt">
<xsl:text>&#160;&#160; TITLE</xsl:text>
</fo:inline>
<fo:inline>
<xsl:text>:&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="AuthorizedOrganizationalRepresentative">
<xsl:for-each select="PositionTitle">
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell line-height="9pt" number-columns-spanned="3" border="solid 1pt gray" padding="2pt" height="25" display-align="before">
<fo:block>
<fo:inline>
<xsl:text>TEL:&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="AuthorizedOrganizationalRepresentative">
<xsl:for-each select="ContactInformation">
<xsl:for-each select="PhoneNumber">
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" number-columns-spanned="3" border="solid 1pt gray" padding="2pt" height="25" display-align="before">
<fo:block>
<fo:inline>
<xsl:text>FAX:&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="AuthorizedOrganizationalRepresentative">
<xsl:for-each select="ContactInformation">
<xsl:for-each select="FaxNumber">
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>&#160;&#160;&#160; </xsl:text>
</fo:inline>
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell line-height="9pt" number-columns-spanned="4" border="solid 1pt gray" padding="2pt" height="25" display-align="before">
<fo:block>
<fo:inline>
<xsl:text>E-MAIL:&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="nih:ResearchAndRelatedProject">
<xsl:for-each select="nih:ResearchCoverPage">
<xsl:for-each select="AuthorizedOrganizationalRepresentative">
<xsl:for-each select="ContactInformation">
<xsl:for-each select="Email">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Verdana" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-bottom-style="none" line-height="9pt" number-columns-spanned="10" border="solid 1pt gray" padding="2pt" height="15" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="8pt">
<xsl:text>12. Corrections to Page 1 Face Page</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell line-height="9pt" number-columns-spanned="5" border="solid 1pt gray" padding="2pt" display-align="before">
<fo:block>
<fo:inline font-size="7pt">
<xsl:text>13. </xsl:text>
</fo:inline>
<fo:inline font-family="Verdana" font-size="7pt">
<xsl:text>APPLICANT ORGANIZATION CERTIFICATION AND ACCEPTANCE: </xsl:text>
</fo:inline>
<fo:inline font-size="7pt">
<xsl:text>I certify that the statements herein are true, complete and accurate to the best of my knowledge and accept the obligation to comply with Public Health Services terms and conditions if a grant is awarded as a result of this application. I am aware that any false, fictitious, or fraudulent statements or claims may subject me to criminal, civil, or administrative penalties.</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell border-bottom-style="none" line-height="9pt" number-columns-spanned="3" border="solid 1pt gray" padding="2pt" height="13" display-align="before">
<fo:block>
<fo:inline font-family="Verdana" font-size="7pt">
<xsl:text>SIGNATURE OF OFFICIAL NAMED IN 11. </xsl:text>
</fo:inline>
<fo:inline font-size="7pt">
<xsl:text>(in ink)</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell border-bottom-style="none" line-height="9pt" number-columns-spanned="2" border="solid 1pt gray" padding="2pt" height="13" display-align="before">
<fo:block>
<fo:inline>
<xsl:text>DATE</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</xsl:for-each>
</fo:block>
<fo:block id="SV_RefID_PageTotal"/>
</fo:flow>
</fo:page-sequence>
</fo:root>
</xsl:template>
<xsl:template name="headerall">
<fo:static-content flow-name="xsl-region-before">
<fo:block>
<xsl:for-each select="$XML">
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-column column-width="150"/>
<fo:table-body start-indent="0pt">
<fo:table-row>
<fo:table-cell padding="0" number-columns-spanned="2" height="24" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>&#160;</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell font-size="7pt" padding="0" text-align="left" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Form Approved Through 06/30/2012</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="7pt" padding="0" text-align="right" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>OMB No.0925-001</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="0" number-columns-spanned="2" height="1" display-align="center">
<fo:block/>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</xsl:for-each>
</fo:block>
</fo:static-content>
</xsl:template>
<xsl:template name="footerall">
<fo:static-content flow-name="xsl-region-after">
<fo:block>
<xsl:for-each select="$XML">
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-column column-width="150"/>
<fo:table-body start-indent="0pt">
<fo:table-row>
<fo:table-cell padding="0" number-columns-spanned="2" height="1" display-align="center">
<fo:block/>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell number-columns-spanned="2" padding="2pt" height="1" display-align="center">
<fo:block/>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell font-size="7pt" padding="0" text-align="left" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>&#160;&#160;&#160;&#160; PHS 2590 (Rev. 11/07)&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; Face&#160; Page</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="7pt" padding="0" text-align="right" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Form Page 1</xsl:text>
</fo:inline>
<fo:inline>
<xsl:text>&#160;</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</xsl:for-each>
</fo:block>
</fo:static-content>
</xsl:template>
<xsl:template name="double-backslash">
<xsl:param name="text"/>
<xsl:param name="text-length"/>
<xsl:variable name="text-after-bs" select="substring-after($text, '''')"/>
<xsl:variable name="text-after-bs-length" select="string-length($text-after-bs)"/>
<xsl:choose>
<xsl:when test="$text-after-bs-length = 0">
<xsl:choose>
<xsl:when test="substring($text, $text-length) = ''''">
<xsl:value-of select="concat(substring($text,1,$text-length - 1), \'\'\\\'\')"/>
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="$text"/>
</xsl:otherwise>
</xsl:choose>
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="concat(substring($text,1,$text-length - $text-after-bs-length - 1), \'\'\\\'\')"/>
<xsl:call-template name="double-backslash">
<xsl:with-param name="text" select="$text-after-bs"/>
<xsl:with-param name="text-length" select="$text-after-bs-length"/>
</xsl:call-template>
</xsl:otherwise>
</xsl:choose>
</xsl:template>
</xsl:stylesheet>','admin',NOW(),UUID(),1)
/
DELIMITER ;
