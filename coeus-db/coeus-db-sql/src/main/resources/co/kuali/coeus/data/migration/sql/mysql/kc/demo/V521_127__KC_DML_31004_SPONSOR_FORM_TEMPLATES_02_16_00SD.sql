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
VALUES ((SELECT (MAX(ID)) FROM SEQ_SPONSOR_FORM_TEMPLATES),(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE PACKAGE_NAME = 'NIH 398 package (Coeus 4.0)'),16,'Personal Data','Personal Data.xslt','text/xml',
'<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:n1="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace" xmlns:n2="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace" xmlns:n3="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace">
<xsl:variable name="fo:layout-master-set">
<fo:layout-master-set>
<fo:simple-page-master master-name="even-page-master" margin-left="0.79in" margin-right="0.79in" page-height="11in" page-width="8.5in">
<fo:region-body margin-top="0.79in" margin-bottom="0.79in" />
<fo:region-before region-name="even-page-header" extent="0.79in" />
<fo:region-after extent="0.79in" />
</fo:simple-page-master>
<fo:simple-page-master master-name="odd-page-master" margin-left="0.79in" margin-right="0.79in" page-height="11in" page-width="8.5in">
<fo:region-body margin-top="0.79in" margin-bottom="0.79in" />
<fo:region-before region-name="odd-page-header" extent="0.79in" />
<fo:region-after extent="0.79in" />
</fo:simple-page-master>
<fo:page-sequence-master master-name="default-page">
<fo:repeatable-page-master-alternatives>
<fo:conditional-page-master-reference master-reference="odd-page-master" odd-or-even="odd" />
<fo:conditional-page-master-reference master-reference="even-page-master" odd-or-even="even" />
</fo:repeatable-page-master-alternatives>
</fo:page-sequence-master>
</fo:layout-master-set>
</xsl:variable>
<xsl:template match="/">
<fo:root>
<xsl:copy-of select="$fo:layout-master-set" />
<fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
<fo:static-content flow-name="even-page-header">
<fo:block>
<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column column-width="150pt" />
<fo:table-column />
<fo:table-body>
<fo:table-row>
<fo:table-cell padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" height="30pt" number-columns-spanned="2" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
<fo:block />
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell font-size="inherited-property-value(&apos;font-size&apos;) - 2pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="left" width="150pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
<fo:block />
</fo:table-cell>
<fo:table-cell font-size="inherited-property-value(&apos;font-size&apos;) - 2pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
<fo:block />
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" number-columns-spanned="2" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
<fo:block />
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:block>
</fo:static-content>
<fo:static-content flow-name="odd-page-header">
<fo:block />
</fo:static-content>
<fo:static-content flow-name="xsl-region-after">
<fo:block>
<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
<fo:table-column column-width="276pt" />
<fo:table-column />
<fo:table-column column-width="150pt" />
<fo:table-body>
<fo:table-row>
<fo:table-cell padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" height="30pt" number-columns-spanned="3" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
<fo:block />
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="left" width="276pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
<fo:block />
</fo:table-cell>
<fo:table-cell padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="left" display-align="center" border-style="solid" border-width="1pt" border-color="white">
<fo:block />
</fo:table-cell>
<fo:table-cell padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" width="150pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
<fo:block />
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:block>
</fo:static-content>
<fo:flow flow-name="xsl-region-body">
<fo:block line-height="12pt">
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<fo:inline font-size="12pt" font-weight="bold">PERSONAL DATA ON PRINCIPAL INVESTIGATOR/PROGRAM DIRECTOR</fo:inline>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<fo:inline font-size="10pt">Coeus does not collect personal data such as social security numbers or information pertaining to race and ethnicity.</fo:inline>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<fo:inline font-size="10pt">NIH provides a fillable PDF form for this information.</fo:inline>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<fo:inline font-size="10pt">The form is located at: </fo:inline>
<fo:basic-link text-decoration="underline" color="blue">
<xsl:attribute name="external-destination"><xsl:text disable-output-escaping="yes">http://coeus.mit.edu/nihforms/personal.pdf</xsl:text></xsl:attribute>
<fo:inline>
<fo:inline font-size="10pt">http://coeus.mit.edu/nihforms/personal.pdf</fo:inline>
</fo:inline>
</fo:basic-link>
<fo:inline font-size="10pt">.</fo:inline>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<fo:block>
<fo:leader leader-pattern="space" />
</fo:block>
<fo:inline font-size="10pt">Please note that some of the fields on this form are optional. </fo:inline>
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
