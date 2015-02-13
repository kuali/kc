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
VALUES ((SELECT (MAX(ID)) FROM SEQ_SPONSOR_FORM_TEMPLATES),(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE PACKAGE_NAME = 'NIH 2590 package (Coeus 4.0)'),3,'Budget Justification','Budget Justification.xslt','text/xml',
'<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:n1="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace" xmlns:phs398="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace" xmlns:rar="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace" xmlns:xs="http://www.w3.org/2001/XMLSchema">
<xsl:variable name="fo:layout-master-set">
<fo:layout-master-set>
<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.3in" margin-right="0.3in">
<fo:region-body margin-top="0.5in" margin-bottom="0.6in" />
<fo:region-after extent="0.6in" />
</fo:simple-page-master>
</fo:layout-master-set>
</xsl:variable>
<xsl:output version="1.0" encoding="UTF-8" indent="no" omit-xml-declaration="no" media-type="text/html" />
<xsl:template match="/">
<fo:root>
<xsl:copy-of select="$fo:layout-master-set" />
<fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
<fo:static-content flow-name="xsl-region-after" display-align="after">
<fo:block>
<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column column-width="286pt" />
<fo:table-column />
<fo:table-column />
<fo:table-body>
<fo:table-row>
<fo:table-cell padding-bottom="0pt" padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" height="4pt" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block>
<fo:block color="black" space-before.optimum="-8pt">
<fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="1pt" />
</fo:block>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="286pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block>
<fo:inline font-size="9pt">PHS 2590 </fo:inline>
<fo:inline font-size="9pt">(Rev. 11/07)</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell border-style="solid" border-width="1pt" border-color="white" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block>
<fo:inline font-size="9pt">Page:</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center">
<fo:block>
<fo:inline font-size="9pt" font-weight="bold">Form Page 3</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:block>
</fo:static-content>
<fo:flow flow-name="xsl-region-body">
<fo:block font-size="7pt" line-height="10pt">
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<fo:inline font-size="8pt">Program Director/Principal Investigator (Last, First, Middle): </fo:inline>
<fo:inline font-size="9pt">&#160; </fo:inline>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="ProposalPerson">
<xsl:if test="ProjectRole =&apos;Principal Investigator&apos;">
<xsl:for-each select="Name">
<xsl:for-each select="LastName">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
<fo:inline font-size="8pt">, </fo:inline>
<xsl:for-each select="FirstName">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
<fo:inline font-size="8pt">&#160;</fo:inline>
<xsl:for-each select="MiddleName">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
<fo:inline font-size="8pt">&#160;</fo:inline>
<xsl:for-each select="NameSuffix">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:if>
</xsl:for-each>
</xsl:for-each>
<fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column column-width="5in" />
<fo:table-column column-width="2.5in" />
<fo:table-body>
<fo:table-row>
<fo:table-cell border-bottom-style="none" border-left-style="none" border-right-style="none" font-size="10pt" font-weight="bold" border-style="solid" border-width="1pt" border-color="black" padding-start="0pt" padding-end="0pt" padding-before="0pt" padding-after="0pt" display-align="center" text-align="center" width="5in">
<fo:block>BUDGET JUSTIFICATION</fo:block>
</fo:table-cell>
<fo:table-cell border-bottom-style="none" border-left-style="solid" border-right-style="none" font-size="9pt" border-style="solid" border-width="1pt" border-color="black" padding-start="0pt" padding-end="0pt" padding-before="0pt" padding-after="0pt" display-align="before" text-align="left" width="2.5in">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<fo:inline font-weight="bold">GRANT NUMBER</fo:inline>
</fo:block>
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="nihPriorGrantNumber">
<xsl:apply-templates />
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-bottom-style="none" border-left-style="none" border-right-style="none" font-size="8pt" border-style="solid" border-width="1pt" border-color="black" padding-start="0pt" padding-end="0pt" padding-before="0pt" padding-after="0pt" display-align="before" height="4in" number-columns-spanned="2" text-align="left">
<fo:block>Provide a detailed budget justification for those line items and amounts that represent a significant change from that previously recommended.&#160; Use continuation pages if necessary.<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>SEE ATTACHED<fo:block>
<xsl:text>&#xA;</xsl:text>
</fo:block>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column />
<fo:table-column />
<fo:table-column />
<fo:table-body>
<fo:table-row>
<fo:table-cell border-left-style="none" font-size="8pt" border-style="solid" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block>
<fo:inline font-weight="bold">CURRENT BUDGET PERIOD</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" border-style="solid" border-width="1pt" border-color="black" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>FROM</fo:block>
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="ProjectDates">
<xsl:for-each select="ProjectStartDate">
<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), ''00'')" />
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), ''00'')" />
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), ''0000'')" />
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell border-right-style="none" font-size="8pt" border-style="solid" border-width="1pt" border-color="black" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>THROUGH</fo:block>
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="ProjectDates">
<xsl:for-each select="ProjectEndDate">
<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), ''00'')" />
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), ''00'')" />
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(.), 1, 4)), ''0000'')" />
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row display-align="before" height="3in">
<fo:table-cell border-bottom-style="none" border-left-style="none" border-right-style="none" font-size="8pt" border-style="solid" border-width="1pt" border-color="black" display-align="before" height="3in" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
<fo:block>Explain any estimated unobligated balance (including prior year carryover) that is greater than 25% of the current year&apos;s total budget.</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
<fo:block>
<xsl:text>&#xA;</xsl:text>
</fo:block>
</fo:block>
</fo:flow>
</fo:page-sequence>
</fo:root>
</xsl:template>
</xsl:stylesheet>','admin',NOW(),UUID(),1)
/
DELIMITER ;
