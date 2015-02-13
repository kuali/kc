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
VALUES ((SELECT (MAX(ID)) FROM SEQ_SPONSOR_FORM_TEMPLATES),(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE PACKAGE_NAME = 'NIH 2590 package (Coeus 4.0)'),4,'Progress Summary','Progress Summary.xslt','text/xml',
'<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:n1="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace" xmlns:phs398="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace" xmlns:rar="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace" xmlns:xs="http://www.w3.org/2001/XMLSchema">
<xsl:variable name="fo:layout-master-set">
<fo:layout-master-set>
<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.3in" margin-right="0.3in">
<fo:region-body margin-top="0.6in" margin-bottom="0.6in" />
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
<fo:table-column column-width="3.5in" />
<fo:table-column />
<fo:table-column column-width="150pt" />
<fo:table-body>
<fo:table-row>
<fo:table-cell padding-bottom="0pt" padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" height="8pt" number-columns-spanned="3" width="3.5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block />
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding-bottom="0pt" padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" height="4pt" number-columns-spanned="3" width="3.5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block>
<fo:block color="black" space-before.optimum="-8pt">
<fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="1pt" />
</fo:block>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell font-size="7pt" padding-bottom="0pt" padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" text-align="left" width="3.5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center">
<fo:block>
<fo:inline font-size="9pt">PHS 2590 (Rev. 11/07)</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="7pt" padding-bottom="0pt" padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" text-align="left" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center">
<fo:block>
<fo:inline font-size="9pt">Page: </fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="inherited-property-value(&apos;font-size&apos;) - 2pt" padding-bottom="0pt" padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" text-align="right" width="150pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center">
<fo:block>
<fo:inline font-size="9pt" font-weight="bold">Form Page 5</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:block>
</fo:static-content>
<fo:flow flow-name="xsl-region-body">
<fo:block font-size="9pt" line-height="10pt">
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<fo:block>
<xsl:text>&#xA;</xsl:text>
</fo:block>
<fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column column-width="4in" />
<fo:table-column column-width="1in" />
<fo:table-column column-width="1in" />
<fo:table-column />
<fo:table-body>
<fo:table-row>
<fo:table-cell border-left-style="none" border-right-style="none" border-top-style="none" font-size="9pt" border-style="solid" border-width="1pt" border-color="black" padding-start="0pt" padding-end="0pt" padding-before="0pt" padding-after="0pt" number-columns-spanned="4" text-align="center" width="1.5in" display-align="center">
<fo:block>
<fo:inline font-size="9pt">PROGRAM DIRECTOR/PRINCIPAL INVESTIGATOR&#160;&#160;&#160; </fo:inline>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<fo:inline font-size="7pt">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="n1:ProgramDirectorPrincipalInvestigator">
<xsl:for-each select="Name">
<xsl:for-each select="LastName">
<fo:inline font-size="9pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
<fo:inline font-size="9pt">, </fo:inline>
<xsl:for-each select="FirstName">
<fo:inline font-size="9pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
<fo:inline font-size="9pt">&#160;</fo:inline>
<xsl:for-each select="MiddleName">
<fo:inline font-size="9pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
<fo:inline font-size="9pt">&#160;</fo:inline>
<xsl:for-each select="NameSuffix">
<fo:inline font-size="9pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-left-style="none" border-right-style="none" font-size="9pt" border-style="solid" border-width="1pt" border-color="black" padding-start="0pt" padding-end="0pt" padding-before="0pt" padding-after="0pt" number-columns-spanned="2" number-rows-spanned="2" text-align="center" width="4in" display-align="center">
<fo:block>
<fo:inline font-size="11pt" font-weight="bold">PROGRESS REPORT SUMMARY</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell border-right-style="none" font-size="9pt" border-style="solid" border-width="1pt" border-color="black" padding-start="0pt" padding-end="0pt" padding-before="0pt" padding-after="0pt" number-columns-spanned="2" width="1in" display-align="center" text-align="start">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block padding-top="3pt">GRANT NUMBER&#160;&#160; </fo:block>
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="nihPriorGrantNumber">
<xsl:apply-templates />
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell border-right-style="none" font-size="9pt" border-style="solid" border-width="1pt" border-color="black" padding-start="0pt" padding-end="0pt" padding-before="0pt" padding-after="0pt" number-columns-spanned="2" width="1in" display-align="center" text-align="start">
<fo:block />
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-right-style="none" font-size="9pt" border-style="solid" border-width="1pt" border-color="black" padding-start="0pt" padding-end="0pt" padding-before="0pt" padding-after="0pt" display-align="center" number-columns-spanned="2" width="1.5in" text-align="start">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block padding-top="5pt">PERIOD COVERED BY THIS REPORT</fo:block>
</fo:block>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-left-style="none" font-size="9pt" border-style="solid" border-width="1pt" border-color="black" padding-start="0pt" padding-end="0pt" padding-before="0pt" padding-after="0pt" number-columns-spanned="2" width="4in" display-align="center" text-align="start">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>PROGRAM DIRECTOR/PRINCIPAL INVESTIGATOR </fo:block>
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<fo:inline font-size="7pt">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="n1:ProgramDirectorPrincipalInvestigator">
<xsl:for-each select="Name">
<xsl:for-each select="LastName">
<fo:inline font-size="9pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
<fo:inline font-size="9pt">, </fo:inline>
<xsl:for-each select="FirstName">
<fo:inline font-size="9pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
<fo:inline font-size="9pt">&#160;</fo:inline>
<xsl:for-each select="MiddleName">
<fo:inline font-size="9pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
<fo:inline font-size="9pt">&#160;</fo:inline>
<xsl:for-each select="NameSuffix">
<fo:inline font-size="9pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
</xsl:for-each>
</fo:block>
</fo:block>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="9pt" border-style="solid" border-width="1pt" border-color="black" padding-start="0pt" padding-end="0pt" padding-before="0pt" padding-after="0pt" display-align="before" width="1.5in" text-align="start">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
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
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<xsl:value-of select="substring( n1:ResearchAndRelatedProject/n1:ResearchCoverPage/ProjectDates/ProjectStartDate , 1, 4 ) -1" />
</fo:block>
</fo:block>
</fo:block>
</fo:table-cell>
<fo:table-cell border-right-style="none" font-size="9pt" border-style="solid" border-width="1pt" border-color="black" padding-start="0pt" padding-end="0pt" padding-before="0pt" padding-after="0pt" display-align="before" width="1.5in" text-align="start">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
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
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<xsl:value-of select="substring( n1:ResearchAndRelatedProject/n1:ResearchCoverPage/ProjectDates/ProjectEndDate , 1, 4 ) -1" />
</fo:block>
</fo:block>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-left-style="none" border-right-style="none" font-size="9pt" border-style="solid" border-width="1pt" border-color="black" padding-start="0pt" padding-end="0pt" padding-before="0pt" padding-after="0pt" display-align="before" number-columns-spanned="4" width="4in" text-align="start">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block border-right-style="none">APPLICANT ORGANIZATION</fo:block>
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="ApplicantOrganization">
<xsl:for-each select="OrganizationName">
<xsl:apply-templates />
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:block>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-left-style="none" border-right-style="none" font-size="9pt" border-style="solid" border-width="1pt" border-color="black" padding-start="0pt" padding-end="0pt" padding-before="0pt" padding-after="0pt" display-align="before" number-columns-spanned="4" width="4in" text-align="start">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block border-right-style="none">TITLE OF PROJECT (Repeat title shown in Item 1 on first page)</fo:block>
</fo:block>
</fo:block>
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="ProjectTitle">
<xsl:apply-templates />
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:block>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-left-style="none" border-right-style="none" font-size="9pt" padding-left="5pt" border-style="solid" border-width="1pt" border-color="black" padding-start="0pt" padding-end="0pt" padding-before="0pt" padding-after="0pt" display-align="before" height="15pt" number-columns-spanned="4" width="4in" text-align="start">
<fo:block>
<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column column-width="proportional-column-width(40)" />
<fo:table-column column-width="proportional-column-width(40)" />
<fo:table-column column-width="proportional-column-width(20)" />
<fo:table-body>
<fo:table-row>
<fo:table-cell border-right-style="none" font-size="9pt" padding-left="5pt" border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" width="40%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block>A. Human Subjects (Complete Item 6 on Face Page)</fo:block>
</fo:table-cell>
<fo:table-cell border-right-style="none" font-size="9pt" padding-left="5pt" border-style="solid" border-width="1pt" border-color="white" width="20%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block />
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-right-style="none" font-size="9pt" padding-left="15pt" border-style="solid" border-width="1pt" border-color="white" width="40%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block>Involvement of Human Subjects</fo:block>
</fo:table-cell>
<fo:table-cell border-right-style="none" font-size="9pt" padding-left="5pt" border-style="solid" border-width="1pt" border-color="white" width="40%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block>___No Change Since Previous Submission</fo:block>
</fo:table-cell>
<fo:table-cell border-right-style="none" font-size="9pt" padding-left="5pt" border-style="solid" border-width="1pt" border-color="white" width="20%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block>___Change</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-right-style="none" font-size="9pt" padding-left="5pt" border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" width="40%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block>B. Vertebrate Animals (Complete Item 7 on Face Page</fo:block>
</fo:table-cell>
<fo:table-cell border-right-style="none" font-size="9pt" padding-left="5pt" border-style="solid" border-width="1pt" border-color="white" width="20%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block />
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-right-style="none" font-size="9pt" padding-left="15pt" border-style="solid" border-width="1pt" border-color="white" width="40%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block>Use of Vertebrate Animals</fo:block>
</fo:table-cell>
<fo:table-cell border-right-style="none" font-size="9pt" padding-left="5pt" border-style="solid" border-width="1pt" border-color="white" width="40%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block>___No Change Since Previous Submission</fo:block>
</fo:table-cell>
<fo:table-cell border-right-style="none" font-size="9pt" padding-left="5pt" border-style="solid" border-width="1pt" border-color="white" width="20%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block>___Change</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-right-style="none" font-size="9pt" padding-left="5pt" border-style="solid" border-width="1pt" border-color="white" width="40%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block>C. Select Agent Research</fo:block>
</fo:table-cell>
<fo:table-cell border-right-style="none" font-size="9pt" padding-left="5pt" border-style="solid" border-width="1pt" border-color="white" width="40%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block>___No Change Since Previous Submission</fo:block>
</fo:table-cell>
<fo:table-cell border-right-style="none" font-size="9pt" padding-left="5pt" border-style="solid" border-width="1pt" border-color="white" width="20%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block>___Change</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-right-style="none" font-size="9pt" padding-left="5pt" border-style="solid" border-width="1pt" border-color="white" width="40%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block>D. Multiple PI Leadership Plan</fo:block>
</fo:table-cell>
<fo:table-cell border-right-style="none" font-size="9pt" padding-left="5pt" border-style="solid" border-width="1pt" border-color="white" width="40%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block>___No Change Since Previous Submission</fo:block>
</fo:table-cell>
<fo:table-cell border-right-style="none" font-size="9pt" padding-left="5pt" border-style="solid" border-width="1pt" border-color="white" width="20%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
<fo:block>___Change</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border-bottom-style="none" border-left-style="none" border-right-style="none" font-size="9pt" border-style="solid" border-width="1pt" border-color="black" padding-start="0pt" padding-end="0pt" padding-before="0pt" padding-after="0pt" display-align="before" number-columns-spanned="4" width="4in" text-align="start">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block border-bottom-style="none">See PHS 2590 Instructions</fo:block>
</fo:block>
<fo:inline font-weight="bold">WOMEN AND MINORITY INCLUSION: See PHS 398 Instructions.&#160; Use Inclusion Enrollment Report Format Page and, if necessary, Targeted/Planned Enrollment Format Page.</fo:inline>
</fo:block>
</fo:block>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<fo:block>
<xsl:text>&#xA;</xsl:text>
</fo:block>
</fo:block>
</fo:block>
</fo:block>
</fo:flow>
</fo:page-sequence>
</fo:root>
</xsl:template>
</xsl:stylesheet>','admin',NOW(),UUID(),1)
/
DELIMITER ;
