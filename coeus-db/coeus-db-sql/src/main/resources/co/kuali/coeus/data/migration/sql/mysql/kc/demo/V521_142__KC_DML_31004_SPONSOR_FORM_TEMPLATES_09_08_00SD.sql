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
VALUES ((SELECT (MAX(ID)) FROM SEQ_SPONSOR_FORM_TEMPLATES),(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE PACKAGE_NAME = 'NSF forms (Coeus 4.0)'),8,'Budget Attachment','Budget Attachment.xslt','text/xml',
'<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:n1="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace" xmlns:n2="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace" xmlns:n3="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace">
<xsl:variable name="fo:layout-master-set">
<fo:layout-master-set>
<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.4in" margin-right="0.3in">
<fo:region-body margin-top="0.2in" margin-bottom="0.2in" />
<fo:region-after extent="0.2in" />
</fo:simple-page-master>
</fo:layout-master-set>
</xsl:variable>
<xsl:template match="/">
<fo:root>
<xsl:copy-of select="$fo:layout-master-set" />
<fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
<fo:static-content flow-name="xsl-region-after" display-align="after">
<fo:block>
<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column column-width="4.5in" />
<fo:table-column column-width="4in" />
<fo:table-body>
<fo:table-row>
<fo:table-cell padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" height="4pt" number-columns-spanned="2" text-align="center" width="4.5in" display-align="center" border-style="solid" border-width="1pt" border-color="white">
<fo:block />
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell font-size="7pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="left" width="4.5in" display-align="center" border-style="solid" border-width="1pt" border-color="white">
<fo:block />
</fo:table-cell>
<fo:table-cell font-size="7pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="center" width="4in" display-align="center" border-style="solid" border-width="1pt" border-color="white">
<fo:block />
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:block>
</fo:static-content>
<fo:flow flow-name="xsl-region-body">
<fo:block font-size="7pt" line-height="10pt">
<fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column />
<fo:table-body>
<fo:table-row>
<fo:table-cell font-size="10pt" font-weight="bold" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
<fo:block>NSF Proposal Budget</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
<fo:block>
<xsl:text>&#xA;</xsl:text>
</fo:block>
<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column column-width="4.5in" />
<fo:table-column column-width=".5in" />
<fo:table-column column-width=".5in" />
<fo:table-column column-width=".5in" />
<fo:table-column column-width="1in" />
<fo:table-body>
<fo:table-row>
<fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" text-align="center" width="4.5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block />
</fo:table-cell>
<fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block />
</fo:table-cell>
<fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block />
</fo:table-cell>
<fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block />
</fo:table-cell>
<fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" text-align="center" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block />
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell font-size="9pt" font-weight="bold" number-rows-spanned="2" width="4.5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:inline font-weight="bold">Senior Personnel: PI/PD, Co-PIs, Faculty and Other Senior Associates</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="9pt" font-weight="bold" number-columns-spanned="3" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>NSF-Funded Person-Months</fo:block>
</fo:table-cell>
<fo:table-cell font-size="9pt" font-weight="bold" number-rows-spanned="2" text-align="center" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>FUNDS REQUESTED</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell font-size="9pt" font-weight="bold" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>CAL</fo:block>
</fo:table-cell>
<fo:table-cell font-size="9pt" font-weight="bold" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>ACAD</fo:block>
</fo:table-cell>
<fo:table-cell font-size="9pt" font-weight="bold" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>SUMR</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="BudgetSummary">
<xsl:for-each select="BudgetPeriod">
<xsl:if test="BudgetPeriodID=1">
<fo:table padding="5" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column column-width="4.5in" />
<fo:table-column column-width=".5in" />
<fo:table-column column-width=".5in" />
<fo:table-column column-width=".5in" />
<fo:table-column column-width="1in" />
<fo:table-header>
<fo:table-row>
<fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" font-size="8pt" number-columns-spanned="5" width="4.5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>Budget Period 1</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-header>
<fo:table-body>
<xsl:for-each select="NSFSeniorPersonnel">
<fo:table-row>
<fo:table-cell font-size="8pt" width="4.5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="FullName">
<xsl:apply-templates />
</xsl:for-each>,&#160;&#160; <xsl:for-each select="Title">
<xsl:apply-templates />
</xsl:for-each>&#160;</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="CalendarMonthsFunded">
<xsl:apply-templates />
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="AcademicMonthsFunded">
<xsl:apply-templates />
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="SummerMonthsFunded">
<xsl:apply-templates />
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="FundsRequested">$<xsl:value-of select="format-number(., ''#,###,###,##0'')" />
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
</xsl:for-each>
</fo:table-body>
</fo:table>
</xsl:if>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="BudgetSummary">
<xsl:for-each select="BudgetPeriod">
<xsl:if test="BudgetPeriodID=2">
<fo:table padding="5" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column column-width="4.5in" />
<fo:table-column column-width=".5in" />
<fo:table-column column-width=".5in" />
<fo:table-column column-width=".5in" />
<fo:table-column column-width="1in" />
<fo:table-header>
<fo:table-row>
<fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" font-size="8pt" number-columns-spanned="5" width="4.5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>Budget Period 2</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-header>
<fo:table-body>
<xsl:for-each select="NSFSeniorPersonnel">
<fo:table-row>
<fo:table-cell font-size="8pt" width="4.5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="FullName">
<xsl:apply-templates />
</xsl:for-each>,&#160;&#160; <xsl:for-each select="Title">
<xsl:apply-templates />
</xsl:for-each>&#160;</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="CalendarMonthsFunded">
<xsl:apply-templates />
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="AcademicMonthsFunded">
<xsl:apply-templates />
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="SummerMonthsFunded">
<xsl:apply-templates />
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="FundsRequested">$<xsl:value-of select="format-number(., ''#,###,###,##0'')" />
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
</xsl:for-each>
</fo:table-body>
</fo:table>
</xsl:if>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="BudgetSummary">
<xsl:for-each select="BudgetPeriod">
<xsl:if test="BudgetPeriodID=3">
<fo:table padding="5" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column column-width="4.5in" />
<fo:table-column column-width=".5in" />
<fo:table-column column-width=".5in" />
<fo:table-column column-width=".5in" />
<fo:table-column column-width="1in" />
<fo:table-header>
<fo:table-row>
<fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" font-size="8pt" number-columns-spanned="5" width="4.5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>Budget Period 3</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-header>
<fo:table-body>
<xsl:for-each select="NSFSeniorPersonnel">
<fo:table-row>
<fo:table-cell font-size="8pt" width="4.5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="FullName">
<xsl:apply-templates />
</xsl:for-each>,&#160;&#160; <xsl:for-each select="Title">
<xsl:apply-templates />
</xsl:for-each>&#160;</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="CalendarMonthsFunded">
<xsl:apply-templates />
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="AcademicMonthsFunded">
<xsl:apply-templates />
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="SummerMonthsFunded">
<xsl:apply-templates />
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="FundsRequested">$<xsl:value-of select="format-number(., ''#,###,###,##0'')" />
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
</xsl:for-each>
</fo:table-body>
</fo:table>
</xsl:if>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="BudgetSummary">
<xsl:for-each select="BudgetPeriod">
<xsl:if test="BudgetPeriodID=4">
<fo:table padding="5" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column column-width="4.5in" />
<fo:table-column column-width=".5in" />
<fo:table-column column-width=".5in" />
<fo:table-column column-width=".5in" />
<fo:table-column column-width="1in" />
<fo:table-header>
<fo:table-row>
<fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" font-size="8pt" number-columns-spanned="5" width="4.5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>Budget Period 4</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-header>
<fo:table-body>
<xsl:for-each select="NSFSeniorPersonnel">
<fo:table-row>
<fo:table-cell font-size="8pt" width="4.5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="FullName">
<xsl:apply-templates />
</xsl:for-each>,&#160;&#160; <xsl:for-each select="Title">
<xsl:apply-templates />
</xsl:for-each>&#160;</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="CalendarMonthsFunded">
<xsl:apply-templates />
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="AcademicMonthsFunded">
<xsl:apply-templates />
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="SummerMonthsFunded">
<xsl:apply-templates />
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="FundsRequested">$<xsl:value-of select="format-number(., ''#,###,###,##0'')" />
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
</xsl:for-each>
</fo:table-body>
</fo:table>
</xsl:if>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="BudgetSummary">
<xsl:for-each select="BudgetPeriod">
<xsl:if test="BudgetPeriodID=5">
<fo:table padding="5" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column column-width="4.5in" />
<fo:table-column column-width=".5in" />
<fo:table-column column-width=".5in" />
<fo:table-column column-width=".5in" />
<fo:table-column column-width="1in" />
<fo:table-header>
<fo:table-row>
<fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" font-size="8pt" number-columns-spanned="5" width="4.5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>Budget Period 5</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-header>
<fo:table-body>
<xsl:for-each select="NSFSeniorPersonnel">
<fo:table-row>
<fo:table-cell font-size="8pt" width="4.5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="FullName">
<xsl:apply-templates />
</xsl:for-each>,&#160;&#160; <xsl:for-each select="Title">
<xsl:apply-templates />
</xsl:for-each>&#160;</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="CalendarMonthsFunded">
<xsl:apply-templates />
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="AcademicMonthsFunded">
<xsl:apply-templates />
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="SummerMonthsFunded">
<xsl:apply-templates />
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="FundsRequested">$<xsl:value-of select="format-number(., ''#,###,###,##0'')" />
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
</xsl:for-each>
</fo:table-body>
</fo:table>
</xsl:if>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
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
