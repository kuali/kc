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
VALUES ((SELECT (MAX(ID)) FROM SEQ_SPONSOR_FORM_TEMPLATES),(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE PACKAGE_NAME = 'Generic Printing Forms (Coeus 4.x)'),1,'CoverPage','CoverPage.xslt','text/xml',
'<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:n1="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace" xmlns:n2="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace" xmlns:n3="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace">
<xsl:variable name="fo:layout-master-set">
<fo:layout-master-set>
<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.25in" margin-right="0.3in">
<fo:region-body margin-top="0.3in" margin-bottom="0.2in" />
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
<fo:table padding="5" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column />
<fo:table-column column-width="150pt" />
<fo:table-body>
<fo:table-row>
<fo:table-cell padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" height="7pt" number-columns-spanned="2" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
<fo:block />
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell font-size="inherited-property-value(&apos;font-size&apos;) - 2pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="left" display-align="center" border-style="solid" border-width="1pt" border-color="white">
<fo:block>
<fo:inline font-size="8pt">NSF Form 1207 (10/89)</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="inherited-property-value(&apos;font-size&apos;) - 2pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" width="150pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
<fo:block />
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:block>
</fo:static-content>
<fo:flow flow-name="xsl-region-body">
<fo:block font-size="8pt" line-height="9pt">
<fo:table padding="5" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column />
<fo:table-body>
<fo:table-row>
<fo:table-cell text-align="center" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
<fo:block>
<fo:inline font-size="10pt" font-weight="bold">COVER SHEET FOR PROPOSAL TO SPONSOR</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
<fo:block>
<xsl:text>&#xA;</xsl:text>
</fo:block>
<fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column />
<fo:table-body>
<fo:table-row>
<fo:table-cell padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
<fo:block>
<fo:table padding="5" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column column-width="1.1in" />
<fo:table-column column-width="1.15in" />
<fo:table-column column-width="1.1in" />
<fo:table-column column-width="1.15in" />
<fo:table-column column-width="1.75in" />
<fo:table-column column-width="1.75in" />
<fo:table-body>
<fo:table-row height=".2in">
<fo:table-cell display-align="before" number-columns-spanned="4" number-rows-spanned="2" width="1.1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<fo:inline font-size="7pt">PROGRAM ANNOUNCEMENT/SOLICITATION NO/CLOSING DATE</fo:inline>
</fo:block>
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="FundingOpportunityDetails">
<xsl:for-each select="FundingOpportunityNumber">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell background-color="gray" display-align="before" number-columns-spanned="2" text-align="center" width="1.75in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:inline font-size="8pt">FOR SPONSOR USE ONLY</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell background-color="gray" display-align="before" number-columns-spanned="2" number-rows-spanned="2" text-align="center" width="1.75in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:inline font-size="8pt">SPOSOR PROPOSAL NUMBER</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row height=".2in">
<fo:table-cell display-align="before" number-columns-spanned="4" width="1.1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:inline font-size="7pt">FOR CONSIDERATION BY ORGANIZATIONAL UNIT</fo:inline>
<fo:inline font-size="7pt">(S) (Indicate the most specific unit known, i.e. program, division etc.)&#160;&#160;&#160; </fo:inline>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="FundingOpportunityDetails">
<xsl:for-each select="FundingOpportunityTitle">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell width="1.1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:inline font-size="8pt">DATE RECEIVED</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell width="1.15in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:inline font-size="8pt">NUMBER OF COPIES</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell width="1.1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:inline font-size="8pt">DIVISION ASSIGNED</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell width="1.15in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:inline font-size="8pt">FUND CODE</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell display-align="before" width="1.75in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<fo:inline font-size="8pt">DUNS # (Data Universal Numbering System)</fo:inline>
</fo:block>
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="ApplicantOrganization">
<xsl:for-each select="OrganizationDUNS">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell width="1.75in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:inline font-size="8pt">FILE LOCATION</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
<fo:table padding="5" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column column-width="2.5in" />
<fo:table-column column-width="2.25in" />
<fo:table-column column-width="3.25in" />
<fo:table-body>
<fo:table-row>
<fo:table-cell width="2.5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:inline font-size="8pt">EMPLOYER IDENTIFICATION NUMBER (EIN) OR </fo:inline>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<fo:inline font-size="8pt">TAXPAYER </fo:inline>
<fo:inline font-size="8pt">IDENTIFICATION NUMBER (TIN)</fo:inline>
</fo:block>
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="ApplicantOrganization">
<xsl:for-each select="OrganizationEIN">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell display-align="before" width="2.25in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<fo:inline font-size="8pt">SHOW PREVIOUS AWARD NO. IF</fo:inline>
<fo:inline font-size="8pt"> THIS IS</fo:inline>
</fo:block>
</fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="ApplicationCategory">
<xsl:for-each select="CategoryIdentifier">
<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
<fo:inline font-size="8pt">
<xsl:choose>
<xsl:when test=".=''Renewal''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:when test=".=''1''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline text-decoration="underline" color="black">
<fo:leader leader-length="8pt" leader-pattern="rule" />
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</fo:inline>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:inline font-size="8pt"> A Renewal</fo:inline>
</fo:block>
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<fo:inline font-size="8pt">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="ApplicationCategory">
<xsl:for-each select="CategoryIdentifier">
<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
<fo:inline>
<xsl:choose>
<xsl:when test=".=''Accomplishment-based Renewal''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:when test=".=''1''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline text-decoration="underline" color="black">
<fo:leader leader-length="8pt" leader-pattern="rule" />
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</fo:inline>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
</xsl:for-each>
<fo:inline font-size="8pt">An Accomplishment Based Renewal</fo:inline>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" width="3.25in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<fo:inline font-size="8pt">IS THIS PROPOSAL BEING SUBMITTED TO ANOTHER AGENCY?&#160;&#160; YES</fo:inline>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="OtherAgencyQuestions">
<xsl:for-each select="OtherAgencyIndicator">
<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
<fo:inline font-size="8pt">
<xsl:choose>
<xsl:when test=".=''true''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:when test=".=''1''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline text-decoration="underline" color="black">
<fo:leader leader-length="8pt" leader-pattern="rule" />
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</fo:inline>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:inline font-size="8pt">&#160;&#160; NO</fo:inline>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<fo:inline font-size="8pt">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="OtherAgencyQuestions">
<xsl:for-each select="OtherAgencyIndicator">
<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
<fo:inline>
<xsl:choose>
<xsl:when test=".=''false''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:when test=".=''''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline text-decoration="underline" color="black">
<fo:leader leader-length="8pt" leader-pattern="rule" />
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</fo:inline>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
</xsl:for-each> IF YES, LIST ACRONYM(S)</fo:block>
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="OtherAgencyQuestions">
<xsl:for-each select="OtherAgencyNames">
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<xsl:apply-templates />
</fo:block>
</fo:block>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
<fo:table padding="5" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column column-width="4in" />
<fo:table-column column-width="4in" />
<fo:table-body>
<fo:table-row>
<fo:table-cell width="4in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<fo:inline font-size="8pt">NAME OF ORGANIZATION TO WHICH AWARD SHOULD BE MADE</fo:inline>
</fo:block>
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="ApplicantOrganization">
<xsl:for-each select="OrganizationName">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell number-rows-spanned="2" width="4in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<fo:inline font-size="8pt">ADDRESS OF AWARDEE ORGANIZATION, INCLUDING 9 DIGIT ZIP CODE</fo:inline>
</fo:block>
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="ApplicantOrganization">
<xsl:for-each select="OrganizationAddress">
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<xsl:for-each select="Street">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
</fo:block>
</fo:block>
<xsl:for-each select="MailStopCode">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<xsl:for-each select="City">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
<fo:inline font-size="8pt">, </fo:inline>
<xsl:for-each select="State">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
<fo:inline font-size="8pt">&#160; </fo:inline>
<xsl:for-each select="PostalCode">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
</fo:block>
</fo:block>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell width="4in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:inline font-size="8pt">AWARDEE ORGANIZATION CODE (IF KNOWN)</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell font-size="8pt" width="4in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:inline font-size="8pt">NAME OF PERFORMING ORGANIZATION, IF DIFFERENT FROM ABOVE</fo:inline>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="PrimaryProjectSite">
<xsl:if test="OrganizationName != ../ApplicantOrganization/OrganizationName">
<xsl:for-each select="OrganizationName">
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<xsl:apply-templates />
</fo:block>
</fo:block>
</xsl:for-each>
</xsl:if>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" number-rows-spanned="2" width="4in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:inline font-size="8pt">ADDRESS OF PERFORMING ORGANIZATION, IF DIFFERENT, INCLUDING 9 DIGIT ZIP CODE</fo:inline>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="PrimaryProjectSite">
<xsl:if test="PostalAddress/Street != ../ApplicantOrganization/OrganizationAddress/Street or PostalAddress/MailStopCode != ../ApplicantOrganization/OrganizationAddress/MailStopCode or PostalAddress/PostalCode != ../ApplicantOrganization/OrganizationAddress/PostalCode">
<xsl:for-each select="PostalAddress">
<xsl:for-each select="Street">
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<xsl:apply-templates />
</fo:block>
</fo:block>
</xsl:for-each>
<xsl:for-each select="City">
<xsl:apply-templates />
</xsl:for-each>,<xsl:for-each select="State">
<xsl:apply-templates />
</xsl:for-each>&#160;<xsl:for-each select="PostalCode">
<xsl:apply-templates />
</xsl:for-each>
</xsl:for-each>
</xsl:if>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell width="4in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:inline font-size="8pt">PERFORMING ORGANIZATION CODE (IF KNOWN)</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
<fo:table padding="5" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column column-width="2in" />
<fo:table-column column-width="2in" />
<fo:table-column column-width="2in" />
<fo:table-column column-width="2in" />
<fo:table-body>
<fo:table-row>
<fo:table-cell display-align="before" number-columns-spanned="4" width="2in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<fo:inline font-size="8pt">IS AWARDEE ORGANIZATION (Check all that apply)</fo:inline>
</fo:block>
</fo:block>
<fo:inline font-size="8pt">(See GPG II.D.1 for Definitions)&#160; __FOR-PROFIT ORGANIZATION&#160;&#160; </fo:inline>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<fo:inline font-size="8pt">
<xsl:for-each select="n3:OrgAssurances">
<xsl:for-each select="n3:SBIRSurvey">
<xsl:for-each select="SBCertificationQuestion">
<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
<fo:inline>
<xsl:choose>
<xsl:when test=".=''true''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:when test=".=''1''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline text-decoration="underline" color="black">
<fo:leader leader-length="8pt" leader-pattern="rule" />
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</fo:inline>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
</xsl:for-each>
<fo:inline font-size="8pt">&#160; __SMALL BUSINESS&#160;&#160;&#160;&#160; __MINORITY BUSINESS&#160;&#160;&#160; </fo:inline>
<xsl:for-each select="n3:OrgAssurances">
<xsl:for-each select="n3:SBIRSurvey">
<xsl:for-each select="WomenOwnedQuestion">
<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
<fo:inline>
<xsl:choose>
<xsl:when test=".=''true''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:when test=".=''1''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline text-decoration="underline" color="black">
<fo:leader leader-length="8pt" leader-pattern="rule" />
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</fo:inline>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:inline font-size="8pt">__WOMAN-OWNED BUSINESS</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell display-align="before" number-columns-spanned="4" width="2in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:inline font-size="8pt">TITLE OF PROPOSED PROJECT&#160;&#160; </fo:inline>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="ProjectTitle">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell display-align="before" width="2in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<fo:inline font-size="8pt">REQUESTED AMOUNT</fo:inline>
</fo:block>
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<fo:inline font-size="8pt">
<xsl:for-each select="BudgetSummary">
<xsl:for-each select="BudgetCostsTotal">$<xsl:value-of select="format-number(., ''#,###,###,##0'')" />
</xsl:for-each>
</xsl:for-each>
</fo:inline>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell font-size="8pt" display-align="before" width="2in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block font-size="8pt">
<fo:inline font-size="8pt">PROPOSED DURATION (1-60 MONTHS)</fo:inline>
</fo:block>
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="NSFProjectDuration">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
</xsl:for-each> months</fo:block>
</fo:table-cell>
<fo:table-cell display-align="before" width="2in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:inline font-size="8pt">RE</fo:inline>
<fo:inline font-size="8pt">QUESTED STARTING DA</fo:inline>
<fo:inline font-size="8pt">TE</fo:inline>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<fo:inline font-size="8pt">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="ProjectDates">
<xsl:for-each select="ProjectStartDate">
<xsl:value-of select="format-number(substring(., 6, 2), ''00'')" />
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(substring(., 9, 2), ''00'')" />
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(substring(., 1, 4), ''0000'')" />
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
</xsl:for-each>
</fo:block>
</fo:block>
</fo:block>
</fo:table-cell>
<fo:table-cell display-align="before" width="2in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<fo:inline font-size="8pt">SHOW RELATED PROPOSAL NO., IF APPLICABLE</fo:inline>
</fo:block>
</fo:block>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell number-columns-spanned="4" width="2in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:table padding="5" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column />
<fo:table-column column-width="4in" />
<fo:table-body>
<fo:table-row>
<fo:table-cell display-align="before" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
<fo:block>
<fo:inline font-size="8pt">CHECK APPROPRIATE BOX(ES) IF THIS PROPOSAL INCLUDES ANY OF THE ITEMS LISTED BELOW</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
<fo:block />
</fo:table-cell>
<fo:table-cell display-align="before" width="4in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
<fo:block />
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<fo:inline font-size="8pt">
<xsl:for-each select="n1:ProjectDescription">
<xsl:for-each select="n3:ProjectSurvey">
<xsl:for-each select="NSFbeginningInvestQuestion">
<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
<fo:inline>
<xsl:choose>
<xsl:when test=".=''true''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:when test=".=''1''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline text-decoration="underline" color="black">
<fo:leader leader-length="8pt" leader-pattern="rule" />
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</fo:inline>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
</xsl:for-each>
<fo:inline font-size="8pt">BEGINNING INVESTIGATOR (GPG I.A)</fo:inline>
</fo:block>
</fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ProjectDescription">
<xsl:for-each select="n3:ProjectSurvey">
<xsl:for-each select="H4Question">
<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
<fo:inline>
<xsl:choose>
<xsl:when test=".=''true''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:when test=".=''1''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline text-decoration="underline" color="black">
<fo:leader leader-length="8pt" leader-pattern="rule" />
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</fo:inline>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:inline font-size="8pt">DISCLOSURE OF LOBBYING ACTIVITIES (GPG I.A)</fo:inline>
</fo:block>
</fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<fo:inline font-size="8pt">
<xsl:for-each select="n1:ProjectDescription">
<xsl:for-each select="n3:ProjectSurvey">
<xsl:for-each select="G8Question">
<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
<fo:inline>
<xsl:choose>
<xsl:when test=".=''true''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:when test=".=''1''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline text-decoration="underline" color="black">
<fo:leader leader-length="8pt" leader-pattern="rule" />
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</fo:inline>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
</xsl:for-each>
<fo:inline font-size="8pt">PROPRIETARY AND PRIVILEGED INFORMATION (PGP II.C)</fo:inline>
</fo:block>
</fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<fo:inline font-size="8pt">
<xsl:for-each select="n1:ProjectDescription">
<xsl:for-each select="n3:ProjectSurvey">
<xsl:for-each select="G6Question">
<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
<fo:inline>
<xsl:choose>
<xsl:when test=".=''true''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:when test=".=''1''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline text-decoration="underline" color="black">
<fo:leader leader-length="8pt" leader-pattern="rule" />
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</fo:inline>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
</xsl:for-each>
<fo:inline font-size="8pt">HISTORIC PLACES (GPG II.C.9)</fo:inline>
</fo:block>
</fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<fo:inline font-size="8pt">
<xsl:for-each select="n1:ProjectDescription">
<xsl:for-each select="n3:ProjectSurvey">
<xsl:for-each select="SmallGrantQuestion">
<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
<fo:inline>
<xsl:choose>
<xsl:when test=".=''true''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:when test=".=''1''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline text-decoration="underline" color="black">
<fo:leader leader-length="8pt" leader-pattern="rule" />
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</fo:inline>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
</xsl:for-each>
<fo:inline font-size="8pt">SMALL GRANT FOR EXPLOR. RESEARCH (SGER) (GPG II.C.11)</fo:inline>
</fo:block>
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<fo:inline font-size="8pt">
<xsl:for-each select="n1:ProjectDescription">
<xsl:for-each select="n3:AnimalSubject">
<xsl:for-each select="VertebrateAnimalsUsedQuestion">
<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
<fo:inline>
<xsl:choose>
<xsl:when test=".=''true''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:when test=".=''1''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline text-decoration="underline" color="black">
<fo:leader leader-length="8pt" leader-pattern="rule" />
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</fo:inline>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
</xsl:for-each>
<fo:inline font-size="8pt">VERTEBRATE ANIMALS (GPG II.C.11) IACUC App. Date:&#160; </fo:inline>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ProjectDescription">
<xsl:for-each select="n3:AnimalSubject">
<xsl:for-each select="IACUCApprovalDate">
<fo:inline font-size="8pt" />
<xsl:value-of select="format-number(substring(., 6, 2), ''00'')" />
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(substring(., 9, 2), ''00'')" />
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(substring(., 1, 4), ''0000'')" />
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell display-align="before" width="4in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<fo:inline font-size="8pt">
<xsl:for-each select="n1:ProjectDescription">
<xsl:for-each select="n1:HumanSubject">
<xsl:for-each select="HumanSubjectsUsedQuestion">
<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
<fo:inline>
<xsl:choose>
<xsl:when test=".=''true''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:when test=".=''1''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline text-decoration="underline" color="black">
<fo:leader leader-length="8pt" leader-pattern="rule" />
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</fo:inline>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
</xsl:for-each>
<fo:inline font-size="8pt">HUMAN SUBJECTS (GPG II.C.11)</fo:inline>
</fo:block>
</fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<fo:inline font-size="8pt">Exemption Subsection&#160;&#160; or IRB App.Date:&#160; </fo:inline>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ProjectDescription">
<xsl:for-each select="n1:HumanSubject">
<xsl:for-each select="IRBApprovalDate">
<xsl:value-of select="format-number(substring(., 6, 2), ''00'')" />
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(substring(., 9, 2), ''00'')" />
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(substring(., 1, 4), ''0000'')" />
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<fo:inline font-size="7pt">
<xsl:for-each select="n1:ProjectDescription">
<xsl:for-each select="n3:ProjectSurvey">
<xsl:for-each select="H1Question">
<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
<fo:inline>
<xsl:choose>
<xsl:when test=".=''true''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:when test=".=''1''">
<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline text-decoration="underline" color="black">
<fo:leader leader-length="8pt" leader-pattern="rule" />
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</fo:inline>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
</xsl:for-each>
<fo:inline font-size="8pt">INTERNATIONAL COOPERATIVE ACTIVITIES: COUNTRY/COUNTRIES&#160;&#160; </fo:inline>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<fo:inline font-size="7pt">
<xsl:for-each select="n1:ProjectDescription">
<xsl:for-each select="n3:ProjectSurvey">
<xsl:for-each select="H1Text">
<xsl:apply-templates />
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
<fo:table padding="5" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column column-width="2.75in" />
<fo:table-column column-width="1in" />
<fo:table-column column-width="1in" />
<fo:table-column column-width="1.25in" />
<fo:table-column column-width="2in" />
<fo:table-body>
<fo:table-row>
<fo:table-cell display-align="before" width="2.75in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:inline font-size="8pt">PI/PD DEPARTMENT&#160; </fo:inline>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<fo:inline font-size="8pt">
<xsl:for-each select="KeyPerson">
<xsl:if test="n1:AccountIdentifier = ../n1:ResearchCoverPage/n1:ProgramDirectorPrincipalInvestigator/n1:AccountIdentifier">
<xsl:for-each select="OrganizationDepartment">
<xsl:apply-templates />
</xsl:for-each>
</xsl:if>
</xsl:for-each>
</fo:inline>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell display-align="before" number-columns-spanned="4" number-rows-spanned="2" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<fo:inline font-size="8pt">PI/PD POSTAL ADDRESS</fo:inline>
</fo:block>
</fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="n1:ProgramDirectorPrincipalInvestigator">
<xsl:for-each select="ContactInformation">
<xsl:for-each select="PostalAddress">
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<xsl:for-each select="Street">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
</fo:block>
</fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block>
<xsl:for-each select="MailStopCode">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
</fo:block>
</fo:block>
<xsl:for-each select="City">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>
<fo:inline font-size="8pt">, </fo:inline>
<xsl:for-each select="State">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
</xsl:for-each>&#160;<xsl:for-each select="PostalCode">
<fo:inline font-size="8pt">
<xsl:apply-templates />
</fo:inline>
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
<fo:table-cell width="2.75in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:inline font-size="8pt">PI/PD FAX NUMBER&#160;&#160; </fo:inline>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<fo:inline font-size="8pt">
<xsl:for-each select="n1:ResearchCoverPage">
<xsl:for-each select="n1:ProgramDirectorPrincipalInvestigator">
<xsl:for-each select="ContactInformation">
<xsl:for-each select="FaxNumber">
<xsl:apply-templates />
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
</xsl:for-each>
<fo:inline font-size="8pt">&#160;&#160; </fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell font-size="8pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="before" number-columns-spanned="5" width="2.75in" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<xsl:for-each select="ProposalPerson" />
</xsl:for-each>
<xsl:for-each select="n1:ResearchAndRelatedProject">
<fo:table padding="5" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column column-width="2.75in" />
<fo:table-column column-width="1in" />
<fo:table-column column-width="1in" />
<fo:table-column column-width="1.25in" />
<fo:table-column column-width="2in" />
<fo:table-header>
<fo:table-row>
<fo:table-cell border-before-style="none" border-start-style="none" font-size="8pt" width="2.75in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>Name</fo:block>
</fo:table-cell>
<fo:table-cell border-before-style="none" font-size="8pt" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>High Degree</fo:block>
</fo:table-cell>
<fo:table-cell border-before-style="none" font-size="8pt" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>Yr of Degree</fo:block>
</fo:table-cell>
<fo:table-cell border-before-style="none" font-size="8pt" width="1.25in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>Telephone Number</fo:block>
</fo:table-cell>
<fo:table-cell border-before-style="none" border-end-style="none" font-size="8pt" width="2in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>Electronic Mail Address</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-header>
<fo:table-body>
<xsl:for-each select="ProposalPerson">
<fo:table-row>
<fo:table-cell border-after-style="none" border-start-style="none" font-size="8pt" padding-start="0pt" width="2.75in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:if test="ProjectRole =&apos;PI/PD&apos;">
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block border-after-style="none" border-start-style="none" padding-start="0pt">PI/PD NAME</fo:block>
</fo:block>
</xsl:if>
<xsl:if test="ProjectRole =&apos;Co-PI/PD&apos;">
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block border-after-style="none">CO-PI/PD NAME</fo:block>
</fo:block>
</xsl:if>
<xsl:if test="ProjectRole =&apos;PI/PD&apos; or ProjectRole=&apos;Co-PI/PD&apos;">
<xsl:for-each select="Name">
<xsl:for-each select="LastName">
<xsl:apply-templates />
</xsl:for-each>, <xsl:for-each select="FirstName">
<xsl:apply-templates />
</xsl:for-each>&#160;<xsl:for-each select="MiddleName">
<xsl:apply-templates />
</xsl:for-each>&#160;<xsl:for-each select="NameSuffix">
<xsl:apply-templates />
</xsl:for-each>
</xsl:for-each>
</xsl:if>
</fo:block>
</fo:table-cell>
<fo:table-cell border-after-style="none" font-size="8pt" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:if test="ProjectRole=&apos;PI/PD&apos; or ProjectRole=&apos;Co-PI/PD&apos;">
<xsl:for-each select="Degree">
<xsl:apply-templates />
</xsl:for-each>
</xsl:if>
</fo:block>
</fo:table-cell>
<fo:table-cell border-after-style="none" font-size="8pt" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block />
</fo:table-cell>
<fo:table-cell border-after-style="none" font-size="8pt" width="1.25in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:if test="ProjectRole =&apos;PI/PD&apos; or ProjectRole=&apos;Co-PI/PD&apos;">
<xsl:for-each select="Phone">
<xsl:apply-templates />
</xsl:for-each>
</xsl:if>
</fo:block>
</fo:table-cell>
<fo:table-cell border-after-style="none" border-end-style="none" font-size="8pt" width="2in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<xsl:if test="ProjectRole=&apos;PI/PD&apos; or ProjectRole=&apos;Co-PI/PD&apos;">
<xsl:for-each select="Email">
<xsl:apply-templates />
</xsl:for-each>
</xsl:if>
</fo:block>
</fo:table-cell>
</fo:table-row>
</xsl:for-each>
</fo:table-body>
</fo:table>
</xsl:for-each>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row height="1pt">
<fo:table-cell border-after-style="none" border-end-style="none" border-start-style="none" font-size="8pt" height="1pt" display-align="before" width="2.75in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block>
<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
<fo:block border-after-style="none" border-end-style="none" border-start-style="none" font-size="8pt" height="1pt" />
</fo:block>
</fo:block>
</fo:table-cell>
<fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" font-size="8pt" height="1pt" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block />
</fo:table-cell>
<fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" font-size="8pt" height="1pt" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block />
</fo:table-cell>
<fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" font-size="8pt" height="1pt" width="1.25in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block />
</fo:table-cell>
<fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" font-size="8pt" height="1pt" width="2in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
<fo:block />
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:block>
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
</xsl:stylesheet>
','admin',NOW(),UUID(),1)
/
DELIMITER ;
