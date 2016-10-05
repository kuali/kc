--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

delete from SUBAWARD_FORMS WHERE FORM_ID = 'FDP Template';
commit;
INSERT INTO SUBAWARD_FORMS(FORM_ID,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,FORM,FILE_NAME,CONTENT_TYPE,VER_NBR,OBJ_ID,TEMPLATE_TYPE_CODE) values ('FDP Template','FDP Agreement',sysdate,'admin', EMPTY_CLOB(), 'FDP_Template_Agreement.xsl','application/octet-stream',1,SYS_GUID(),4);
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:award="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/award" xmlns:subcontract="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/subcontract" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:output version="1.0" method="xml" encoding="UTF-8" indent="no"/>
<xsl:param name="SV_OutputFormat" select="''PDF''"/>
<xsl:variable name="XML" select="/"/>
<xsl:variable name="fo:layout-master-set">
<fo:layout-master-set>
<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.2in" margin-right="0.2in">
<fo:region-body margin-top="0.5in" margin-bottom="0.79in"/>
<fo:region-before extent="0.5in"/>
</fo:simple-page-master>
</fo:layout-master-set>
</xsl:variable>
<xsl:template match="/">
<fo:root>
<xsl:copy-of select="$fo:layout-master-set"/>
<fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
<xsl:call-template name="headerall"/>
<fo:flow flow-name="xsl-region-body">
<fo:block>
<fo:block/>
<xsl:for-each select="$XML">
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border="solid 1pt gray" border-spacing="-1pt">
<fo:table-column column-width="50%"/>
<fo:table-column column-width="50%"/>
<fo:table-body start-indent="0pt">
<fo:table-row font-size="12pt" font-weight="bold">
<fo:table-cell number-columns-spanned="2" border="solid 1pt gray" padding="0" display-align="center">
<fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block text-align="center" margin="0pt">
<fo:block>
<fo:inline>
<xsl:text>FDP Cost Reimbursement Research Subaward Agreement</xsl:text>
</fo:inline>
</fo:block>
</fo:block>
</fo:block>
</fo:table-cell>
</fo:table-row>

<fo:table-row font-size="9pt" font-weight="normal">
<fo:table-cell border="solid 1pt gray" padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Pass-through Entity (PTE): </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:PrimeRecipientContacts">
<xsl:for-each select="subcontract:RequisitionerOrgDetails">
<xsl:for-each select="subcontract:OrganizationName">
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
</xsl:otherwise>';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell border="solid 1pt gray" padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Subrecipient: </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractDetail">
<xsl:for-each select="subcontract:SubcontractorName">
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
</fo:block>
</fo:table-cell>
</fo:table-row>


<fo:table-row font-size="9pt" font-weight="normal">
<fo:table-cell border="solid 1pt gray" padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>PTE Principal Investigator (PI): </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:PrimePrincipalInvestigator">
<xsl:for-each select="subcontract:FullName">
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
</fo:block>
</fo:table-cell>
<fo:table-cell border="solid 1pt gray" padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Subrecipient Principal Investigator (PI): </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractDetail">
<xsl:for-each select="subcontract:SiteInvestigator">
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
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row font-size="9pt">
<fo:table-cell number-columns-spanned="2" border="solid 1pt gray" padding="0" display-align="center">
<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing="-1pt">
<fo:table-column column-width="25%"/>
<fo:table-column column-width="25%"/>
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-body start-indent="0pt">
<fo:table-row>
<fo:table-cell padding="2pt"  border="solid 1pt gray" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>PTE Federal Award No: </xsl:text>
</fo:inline>';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:Award">
<xsl:for-each select="award:AwardDetails">
<xsl:for-each select="award:AwardHeader">
<xsl:for-each select="award:SponsorAwardNumber">
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
<fo:table-cell  padding="2pt" border="solid 1pt gray" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>FAIN: </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:Award">
<xsl:for-each select="award:AwardDetails">
<xsl:for-each select="award:OtherHeaderDetails">
<xsl:for-each select="award:FAIN">
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
<fo:table-cell  padding="2pt"  border="solid 1pt gray" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Federal Awarding Agency: </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:Award">
<xsl:for-each select="award:AwardDetails">
<xsl:for-each select="award:AwardHeader">
<xsl:for-each select="award:SponsorDescription">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '
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
</fo:table-body>
</fo:table>

</fo:table-cell>
</fo:table-row>

<fo:table-row font-size="9pt">
<fo:table-cell number-columns-spanned="2" border="solid 1pt gray" padding="0" display-align="center">
<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing="-1pt">
<fo:table-column column-width="25%"/>
<fo:table-column column-width="35%"/>
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-body start-indent="0pt">
<fo:table-row>
<fo:table-cell padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Federal Award Issue Date: </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:Award">
<xsl:for-each select="award:AwardDetails">
<xsl:for-each select="award:OtherHeaderDetails">
<xsl:for-each select="award:LastUpdate">
<fo:inline>
<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), ''0000'')"/>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell  padding="2pt" border="solid 1pt gray" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Total Amount of Federal Award to PTE: </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:Award">
<xsl:for-each select="award:AwardDetails">
<xsl:for-each select="award:OtherHeaderDetails">
<xsl:for-each select="award:ObligatedAmt">
<fo:inline>
<xsl:text>$</xsl:text>
</fo:inline>
<fo:inline>
<xsl:value-of select="format-number(number(string(.)), ''###,##0.00'')"/>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell  padding="2pt" border="solid 1pt gray" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>CFDA No: </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:Award">
<xsl:for-each select="award:AwardDetails">';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '
<xsl:for-each select="award:OtherHeaderDetails">
<xsl:for-each select="award:CFDANumber">
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
<fo:table-cell padding="2pt"  display-align="center">
<fo:block>
<fo:inline>
<xsl:text>CFDA Title: </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:table-cell>
</fo:table-row>
<fo:table-row font-size="9pt" >
<fo:table-cell number-columns-spanned="2" border="solid 1pt gray" padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Project Title: </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:Award">
<xsl:for-each select="award:AwardDetails">
<xsl:for-each select="award:AwardHeader">
<xsl:for-each select="award:Title">
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
<fo:table-row font-size="9pt" font-weight="normal">
<fo:table-cell number-columns-spanned="2"  padding="1pt" display-align="center">
<fo:block>
<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing="-1pt">
<fo:table-column column-width="35%"/>
<fo:table-column column-width="30%"/>
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-body start-indent="0pt">
<fo:table-row>
<fo:table-cell border="solid 1pt gray" padding="0pt" display-align="center">
<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing="2pt">
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-body start-indent="0pt">
<fo:table-row>
<fo:table-cell padding="2pt"  number-columns-spanned="3"   display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Subaward Period of Performance: </xsl:text>
</fo:inline>';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="1pt"  display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Start: </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractAmountInfo">
<xsl:for-each select="subcontract:PerformanceStartDate">
<fo:inline>
<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), ''0000'')"/>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell padding="1pt"  >
<fo:block>
<fo:inline>
<xsl:text>End: </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractAmountInfo">
<xsl:for-each select="subcontract:PerformanceEndDate">
<fo:inline>
<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), ''0000'')"/>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:table-cell>
<fo:table-cell padding="1pt" border="solid 1pt gray"  display-align="center">
<fo:block>
<fo:inline >
<xsl:text>Amount Funded This Action: </xsl:text>
</fo:inline>
<fo:block>
<xsl:text></xsl:text>
</fo:block>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractAmountInfo">
<xsl:for-each select="subcontract:ObligatedAmount">
<fo:inline>
<xsl:text>$</xsl:text>
</fo:inline>
<fo:inline>
<xsl:value-of select="format-number(number(string(.)), ''###,##0.00'')"/>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell padding="2pt" border="solid 1pt gray"  display-align="center">
<fo:block>
<fo:inline >
<xsl:text>Subaward No: </xsl:text>
</fo:inline>
<fo:block>
<xsl:text></xsl:text>
</fo:block>
<fo:block>
<xsl:text></xsl:text>
</fo:block>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractDetail">
<xsl:for-each select="subcontract:PONumber">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block>
<xsl:copy-of select="$value-of-template"/>';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '
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
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell border="solid 1pt gray"  display-align="center">
<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing="2pt">
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-body start-indent="0pt">
<fo:table-row>
<fo:table-cell padding="1pt"  number-columns-spanned="3"  display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Estimated Project Period (if incrementally funded): </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell  display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Start: </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractDetail">
<xsl:for-each select="subcontract:StartDate">
<fo:inline>
<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), ''0000'')"/>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
<fo:table-cell  >
<fo:block>
<fo:inline>
<xsl:text>End: </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractDetail">
<xsl:for-each select="subcontract:EndDate">
<fo:inline>
<xsl:value-of select="format-number(number(substring(string(.), 6, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(.), 9, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), ''0000'')"/>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:table-cell>
<fo:table-cell  border="solid 1pt gray" display-align="center">
<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing="-1pt">
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-body >
<fo:table-row>
<fo:table-cell padding="4pt"  number-columns-spanned="3"  display-align="center">
<fo:block>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="2pt"  number-columns-spanned="3"  display-align="center">
<fo:block> <xsl:text></xsl:text> </fo:block>';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '
<fo:block>
<fo:inline>
<xsl:text>Incrementally Estimated Total: </xsl:text>
</fo:inline>
<fo:block>
<xsl:text></xsl:text>
</fo:block>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractAmountInfo">
<xsl:for-each select="subcontract:AnticipatedAmount">
<fo:inline>
<xsl:text>$</xsl:text>
</fo:inline>
<fo:inline>
<xsl:value-of select="format-number(number(string(.)), ''###,##0.00'')"/>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="1pt" number-columns-spanned="1.5"  display-align="center">
<fo:block>
<fo:inline>
<xsl:text> </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell padding="1pt" number-columns-spanned="1.5"  >
<fo:block>
<fo:inline>
<xsl:text> </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:table-cell>
<fo:table-cell border="solid 1pt gray" padding="1pt" display-align="center">
<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing="-1pt">
<fo:table-column column-width="50%"/>
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-body start-indent="0pt">
<fo:table-row>
<fo:table-cell padding="1pt"  number-columns-spanned="3"  display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Is this Award R &amp; D </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="1pt" number-columns-spanned="3"  display-align="center">
<fo:block>
<xsl:choose>
<xsl:when test="subcontract:SubContractData/subcontract:SubcontractDetail/subcontract:RANDD = &quot;Y&quot;">
<fo:external-graphic content-height="8" content-width="7">
<xsl:attribute name="src">
<xsl:text>url(</xsl:text>
<xsl:call-template name="double-backslash">
<xsl:with-param name="text">
<xsl:value-of select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;))"/>
</xsl:with-param>
<xsl:with-param name="text-length">
<xsl:value-of select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;)))"/>
</xsl:with-param>
</xsl:call-template>
<xsl:text>)</xsl:text>
</xsl:attribute>
</fo:external-graphic>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Arial" font-size="8pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
<fo:external-graphic content-height="8" content-width="7">
<xsl:attribute name="src">
<xsl:text>url(</xsl:text>
<xsl:call-template name="double-backslash">
<xsl:with-param name="text">
<xsl:value-of select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;))"/>
</xsl:with-param>
<xsl:with-param name="text-length">
<xsl:value-of select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;)))"/>
</xsl:with-param>
</xsl:call-template>';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '
<xsl:text>)</xsl:text>
</xsl:attribute>
</fo:external-graphic>
</xsl:otherwise>
</xsl:choose>
<fo:inline>
<xsl:text> Yes </xsl:text>
</fo:inline>
<fo:inline font-family="Arial" font-size="8pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<fo:inline>
<xsl:text>Or </xsl:text>
</fo:inline>
<fo:inline font-family="Arial" font-size="8pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<xsl:choose>
<xsl:when test="subcontract:SubContractData/subcontract:SubcontractDetail/subcontract:RANDD  = &quot;N&quot;">
<fo:external-graphic content-height="8" content-width="7">
<xsl:attribute name="src">
<xsl:text>url(</xsl:text>
<xsl:call-template name="double-backslash">
<xsl:with-param name="text">
<xsl:value-of select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;))"/>
</xsl:with-param>
<xsl:with-param name="text-length">
<xsl:value-of select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;)))"/>
</xsl:with-param>
</xsl:call-template>
<xsl:text>)</xsl:text>
</xsl:attribute>
</fo:external-graphic>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Arial" font-size="8pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
<fo:external-graphic content-height="8" content-width="7">
<xsl:attribute name="src">
<xsl:text>url(</xsl:text>
<xsl:call-template name="double-backslash">
<xsl:with-param name="text">
<xsl:value-of select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;))"/>
</xsl:with-param>
<xsl:with-param name="text-length">
<xsl:value-of select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;)))"/>
</xsl:with-param>
</xsl:call-template>
<xsl:text>)</xsl:text>
</xsl:attribute>
</fo:external-graphic>
</xsl:otherwise>
</xsl:choose>
<fo:inline>
<xsl:text> No </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell number-columns-spanned="3" padding="2pt" display-align="center">
<fo:block>
<fo:inline font-family="Arial" font-size="9pt">
<xsl:text>Check all that apply: </xsl:text>
</fo:inline>
<xsl:choose>
<xsl:when test="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:Attachment4Required = &quot;N&quot;">
<fo:external-graphic content-height="8" content-width="7">
<xsl:attribute name="src">
<xsl:text>url(</xsl:text>
<xsl:call-template name="double-backslash">
<xsl:with-param name="text">
<xsl:value-of select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;))"/>
</xsl:with-param>
<xsl:with-param name="text-length">
<xsl:value-of select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;)))"/>
</xsl:with-param>
</xsl:call-template>
<xsl:text>)</xsl:text>
</xsl:attribute>
</fo:external-graphic>
<fo:inline font-family="Arial" font-size="9pt">
<xsl:text> Reporting Requirements (Attachment 4)</xsl:text>
</fo:inline>
</xsl:when>
<xsl:when test="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:Attachment4Required = &quot;Y&quot;">
<fo:external-graphic content-height="8" content-width="8">
<xsl:attribute name="src">';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '
<xsl:text>url(</xsl:text>
<xsl:call-template name="double-backslash">
<xsl:with-param name="text">
<xsl:value-of select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;))"/>
</xsl:with-param>
<xsl:with-param name="text-length">
<xsl:value-of select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;)))"/>
</xsl:with-param>
</xsl:call-template>
<xsl:text>)</xsl:text>
</xsl:attribute>
</fo:external-graphic>
<fo:inline font-family="Arial" font-size="9pt">
<xsl:text> Reporting Requirements (Attachment 4)</xsl:text>
</fo:inline>
</xsl:when>
</xsl:choose>
<fo:inline font-family="Arial" font-size="8pt">
<xsl:text>&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
<xsl:choose>
<xsl:when test="subcontract:SubContractData/subcontract:SubcontractDetail/subcontract:FFATA = &quot;Y&quot;">
<fo:external-graphic content-height="8" content-width="7">
<xsl:attribute name="src">
<xsl:text>url(</xsl:text>
<xsl:call-template name="double-backslash">
<xsl:with-param name="text">
<xsl:value-of select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;))"/>
</xsl:with-param>
<xsl:with-param name="text-length">
<xsl:value-of select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;)))"/>
</xsl:with-param>
</xsl:call-template>
<xsl:text>)</xsl:text>
</xsl:attribute>
</fo:external-graphic>
<fo:inline font-family="Arial" font-size="9pt">
<xsl:text>Subject to FFATA (Attachment 3B) </xsl:text>
</fo:inline>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Arial" font-size="8pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
<fo:external-graphic content-height="8" content-width="7">
<xsl:attribute name="src">
<xsl:text>url(</xsl:text>
<xsl:call-template name="double-backslash">
<xsl:with-param name="text">
<xsl:value-of select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;))"/>
</xsl:with-param>
<xsl:with-param name="text-length">
<xsl:value-of select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;)))"/>
</xsl:with-param>
</xsl:call-template>
<xsl:text>)</xsl:text>
</xsl:attribute>
</fo:external-graphic>
<fo:inline font-family="Arial" font-size="9pt">
<xsl:text> Subject to FFATA (Attachment 3B) </xsl:text>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
<fo:inline font-family="Arial" font-size="8pt">
<xsl:text>&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
<fo:inline font-family="Arial" font-size="8pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
<xsl:choose>
<xsl:when test="subcontract:SubContractData/subcontract:SubcontractDetail/subcontract:COSTSHARE = &quot;Y&quot;">
<fo:external-graphic content-height="8" content-width="7">
<xsl:attribute name="src">
<xsl:text>url(</xsl:text>
<xsl:call-template name="double-backslash">
<xsl:with-param name="text">
<xsl:value-of select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;))"/>
</xsl:with-param>
<xsl:with-param name="text-length">
<xsl:value-of select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;)))"/>
</xsl:with-param>
</xsl:call-template>
<xsl:text>)</xsl:text>
</xsl:attribute>
</fo:external-graphic>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Arial" font-size="8pt">
<xsl:text>&#160;</xsl:text>';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '
</fo:inline>
<fo:external-graphic content-height="8" content-width="7">
<xsl:attribute name="src">
<xsl:text>url(</xsl:text>
<xsl:call-template name="double-backslash">
<xsl:with-param name="text">
<xsl:value-of select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;))"/>
</xsl:with-param>
<xsl:with-param name="text-length">
<xsl:value-of select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;)))"/>
</xsl:with-param>
</xsl:call-template>
<xsl:text>)</xsl:text>
</xsl:attribute>
</fo:external-graphic>
</xsl:otherwise>
</xsl:choose>
<fo:inline font-family="Arial" font-size="9pt">
<xsl:text> Cost Sharing (Attachment 5) </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row font-size="10pt" font-weight="bold">
<fo:table-cell number-columns-spanned="2" border="solid 1pt gray" padding="1pt" display-align="center">
<fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block text-align="center" margin="0pt">
<fo:block>
<fo:inline>
<xsl:text>Terms and Conditions</xsl:text>
</fo:inline>
</fo:block>
</fo:block>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row font-weight="normal">
<fo:table-cell font-family="Arial" font-size="9pt" number-columns-spanned="2" border="solid 1pt gray" padding="2" display-align="center">
<fo:block>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>1) PTE hereby awards a cost reimbursable subaward, as described above, to Subrecipient. The statement of work and budget for this </xsl:text>
</fo:inline>

<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>subaward are (check one)</xsl:text>
</fo:inline>
<fo:block/>
<xsl:choose>
<xsl:when test="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:Attachment5Required = &quot;N&quot;">
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>&#160;</xsl:text>
</fo:inline>
<xsl:choose>
<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:SowOrSubProposalBudget  = &quot;Y&quot;">
<fo:external-graphic content-height="8" content-width="8">
<xsl:attribute name="src">
<xsl:text>url(</xsl:text>
<xsl:call-template name="double-backslash">
<xsl:with-param name="text">
<xsl:value-of select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;))"/>
</xsl:with-param>
<xsl:with-param name="text-length">
<xsl:value-of select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;)))"/>
</xsl:with-param>
</xsl:call-template>
<xsl:text>)</xsl:text>
</xsl:attribute>
</fo:external-graphic>
<fo:inline font-family="Arial" font-size="8pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:SowOrSubProposalBudget  = &quot;N&quot;">
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>&#160;</xsl:text>
</fo:inline>
<fo:external-graphic content-height="8" content-width="7">
<xsl:attribute name="src">
<xsl:text>url(</xsl:text>
<xsl:call-template name="double-backslash">
<xsl:with-param name="text">';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '
<xsl:value-of select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;))"/>
</xsl:with-param>
<xsl:with-param name="text-length">
<xsl:value-of select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;)))"/>
</xsl:with-param>
</xsl:call-template>
<xsl:text>)</xsl:text>
</xsl:attribute>
</fo:external-graphic>
<fo:inline font-family="Arial" font-size="8pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
</xsl:when>
</xsl:choose>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>as specified in Subrecipient&apos;s proposal dated </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractTemplateInfo">
<xsl:for-each select="subcontract:SubProposalDate">
<fo:inline font-family="Arial" font-size="9pt">
<xsl:value-of select="format-number(number(substring(string(string(.)), 6, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(.)), 9, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(string(.))), 1, 4)), ''0000'')"/>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text> or</xsl:text>
</fo:inline>
<fo:inline>
<xsl:text>&#160;</xsl:text>
</fo:inline>
<xsl:choose>
<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:SowOrSubProposalBudget  = &quot;N&quot;">
<fo:external-graphic content-height="8" content-width="8">
<xsl:attribute name="src">
<xsl:text>url(</xsl:text>
<xsl:call-template name="double-backslash">
<xsl:with-param name="text">
<xsl:value-of select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;))"/>
</xsl:with-param>
<xsl:with-param name="text-length">
<xsl:value-of select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;)))"/>
</xsl:with-param>
</xsl:call-template>
<xsl:text>)</xsl:text>
</xsl:attribute>
</fo:external-graphic>
<fo:inline font-family="Arial" font-size="8pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:SowOrSubProposalBudget  = &quot;Y&quot;">
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>&#160;</xsl:text>
</fo:inline>
<fo:external-graphic content-height="8" content-width="7">
<xsl:attribute name="src">
<xsl:text>url(</xsl:text>
<xsl:call-template name="double-backslash">
<xsl:with-param name="text">
<xsl:value-of select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;))"/>
</xsl:with-param>
<xsl:with-param name="text-length">
<xsl:value-of select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;)))"/>
</xsl:with-param>
</xsl:call-template>
<xsl:text>)</xsl:text>
</xsl:attribute>
</fo:external-graphic>
<fo:inline font-family="Arial" font-size="8pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
</xsl:when>
</xsl:choose>
<fo:inline font-family="Arial" font-size="8pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>as shown in Attachment 5.</xsl:text>
</fo:inline>
<fo:inline>
<xsl:text>&#160;</xsl:text>
</fo:inline>
</xsl:when>
<xsl:when test="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:Attachment5Required = &quot;Y&quot;">
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>&#160;</xsl:text>';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '
</fo:inline>
<fo:external-graphic content-height="8" content-width="7">
<xsl:attribute name="src">
<xsl:text>url(</xsl:text>
<xsl:call-template name="double-backslash">
<xsl:with-param name="text">
<xsl:value-of select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;))"/>
</xsl:with-param>
<xsl:with-param name="text-length">
<xsl:value-of select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;)))"/>
</xsl:with-param>
</xsl:call-template>
<xsl:text>)</xsl:text>
</xsl:attribute>
</fo:external-graphic>
<fo:inline font-family="Arial" font-size="8pt">
<xsl:text>&#160;</xsl:text>
</fo:inline>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>As specified in Subrecipient&apos;s proposal dated</xsl:text>
</fo:inline>

<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractTemplateInfo">
<xsl:for-each select="subcontract:SubProposalDate">
<fo:inline>
<xsl:value-of select="format-number(number(substring(string(string(.)), 6, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(.)), 9, 2)), ''00'')"/>
<xsl:text>/</xsl:text>
<xsl:value-of select="format-number(number(substring(string(string(string(.))), 1, 4)), ''0000'')"/>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>

<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>; or&#160;</xsl:text>
</fo:inline>
<fo:inline>
<xsl:text>;</xsl:text>
</fo:inline>
<fo:external-graphic content-height="8" content-width="8">
<xsl:attribute name="src">
<xsl:text>url(</xsl:text>
<xsl:call-template name="double-backslash">
<xsl:with-param name="text">
<xsl:value-of select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;))"/>
</xsl:with-param>
<xsl:with-param name="text-length">
<xsl:value-of select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;)))"/>
</xsl:with-param>
</xsl:call-template>
<xsl:text>)</xsl:text>
</xsl:attribute>
</fo:external-graphic>
<fo:inline>
<xsl:text>&#160;</xsl:text>
</fo:inline>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>as shown in Attachment 5.</xsl:text>
</fo:inline>
</xsl:when>
</xsl:choose>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text> In its performance of the subaward work, Subrecipient shall be an independent entity and not an employee or agent of PTE.</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>2) PTE shall reimburse Subrecipient not more often than monthly for allowable costs. All invoices shall be submitted using Subrecipient&apos;s standard invoice, but at a minimum shall include current and cumulative costs (including cost sharing), subaward number, and certification, as required in 2 CFR 200.415 (a).</xsl:text></fo:inline><fo:inline font-family="Arial" font-size="9pt"  text-decoration="underline" font-weight="normal"> <xsl:text> Invoices that do not reference PTE Subaward Number shall be returned to Subrecipient.</xsl:text></fo:inline> <fo:inline font-family="Arial" font-size="9pt" font-weight="normal"><xsl:text> Invoices and questions concerning invoice receipt or payments should be directed to the appropriate party&apos;s </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractTemplateInfo">
<xsl:for-each select="subcontract:InvoiceOrPaymentContactDescription">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Arial" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Arial" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:inline>
<xsl:text>&#160;</xsl:text>
</fo:inline>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>as shown in Attachments 3A.</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>3) A final statement of cumulative costs incurred, including cost sharing, marked &quot;FINAL&quot; must be submitted to PTE&apos;s</xsl:text>
</fo:inline>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text> </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractTemplateInfo">
<xsl:for-each select="subcontract:FinalStmtOfCostsContactDescription">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Arial" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Arial" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>, as shown in Attachments 3A and 3B, NOT LATER THAN </xsl:text> </fo:inline> <fo:inline font-family="Arial" font-size="9pt" font-weight="bold"> <xsl:text>60</xsl:text> </fo:inline><fo:inline font-family="Arial" font-size="9pt" font-weight="normal"><xsl:text>  days after subaward end date. The final statement of costs shall constitute Subrecipient&apos;s final financial report.</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>4) All payments shall be considered provisional and subject to adjustment within the total estimated cost in the event such adjustment is necessary as a result of an adverse audit finding against the Subrecipient. PTE  reserves the right to reject an invoice,in accordance with 2 CFR 200.305.</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>5) Matters concerning the technical performance of this subaward should be directed to the appropriate party&apos;s Principal Investigator as shown in Attachments 3A and 3B. Technical reports are required as shown above, &quot;Reporting Requirements&quot;.</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>6) Matters concerning the request or negotiation of any changes in the terms, conditions, or amounts cited in this subaward agreement, and any changes requiring prior approval, should be directed to the appropriate party&apos;s </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractTemplateInfo">
<xsl:for-each select="subcontract:ChangeRequestsContactDescription">
<xsl:variable name="value-of-template">
<xsl:apply-templates/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Arial" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Arial" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>, as shown in Attachments 3A &amp; 3B.</xsl:text>
</fo:inline>

<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>  Any such changes made to this subaward agreement require the written approval of each party&apos;s  Authorized Official, as shown in Attachments 3A and 3B.</xsl:text>
</fo:inline>

<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text> </xsl:text>  </fo:inline>';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '
<fo:block/> <xsl:text>7)Substantive changes made to this subaward agreement require the written approval of each party''s Authorized Official as shown in Attachments 3A and 3B.  The PTE  may issue non-substantive changes to the Period of Performance  (check one) </xsl:text>

                                               <fo:external-graphic content-height="8" content-width="8">
                                                    <xsl:attribute name="src">
                                                        <xsl:text>url(</xsl:text>
                                                        <xsl:call-template name="double-backslash">
                                                            <xsl:with-param name="text">
                                                                <xsl:value-of select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;))"/>
                                                            </xsl:with-param>
                                                            <xsl:with-param name="text-length">
                                                                <xsl:value-of select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;)))"/>
                                                            </xsl:with-param>
                                                        </xsl:call-template>
                                                        <xsl:text>)</xsl:text>
                                                    </xsl:attribute>
                                                </fo:external-graphic>
                                                <fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
                                                    <xsl:text> Bilaterally</xsl:text>
                                                </fo:inline>
                                                <fo:inline font-family="Arial" font-size="8pt">
                                                    <xsl:text>&#160;&#160; </xsl:text>
                                                </fo:inline>
                                                <fo:external-graphic content-height="8" content-width="7">
                                                    <xsl:attribute name="src">
                                                        <xsl:text>url(</xsl:text>
                                                        <xsl:call-template name="double-backslash">
                                                            <xsl:with-param name="text">
                                                                <xsl:value-of
                                                                        select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;))"/>
                                                            </xsl:with-param>
                                                            <xsl:with-param name="text-length">
                                                                <xsl:value-of
                                                                        select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;)))"/>
                                                            </xsl:with-param>
                                                        </xsl:call-template>

                                                        <xsl:text>)</xsl:text>
                                                    </xsl:attribute>
                                                </fo:external-graphic>
                                                <fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
                                                    <xsl:text> Unilaterally.</xsl:text>
                                                </fo:inline>

                                                <fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
                                                    <xsl:text> Unilateral modifications shall be considered valid 14 days after receipt unless otherwise indicated by Subrecipient.</xsl:text>
                                                </fo:inline>
                                                <fo:block/>
                                                <fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
                                                    <xsl:text>8) Each party shall be responsible for its negligent acts or omissions and the negligent acts or omissions of its employees, officers, or directors, to the extent allowed by law.</xsl:text>
                                                </fo:inline>
                                                <fo:block/>
                                                <fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
                                                    <xsl:text>9) Either party may terminate this subaward with thirty days written notice to the appropriate party&apos;s </xsl:text>
                                                </fo:inline>
                                                <xsl:for-each select="subcontract:SubContractData">
                                                    <xsl:for-each select="subcontract:SubcontractTemplateInfo">
                                                        <xsl:for-each select="subcontract:TerminationContactDescription">
                                                            <xsl:variable name="value-of-template">
                                                                <xsl:apply-templates/>
                                                            </xsl:variable>
                                                            <xsl:choose>
                                                                <xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block font-family="Arial" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:block>
</xsl:when>
<xsl:otherwise>
<fo:inline font-family="Arial" font-size="9pt">
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text> as shown in Attachments 3A &amp; 3B. PTE shall pay Subrecipient for termination costs as allowable under Uniform Guidance, 2 CFR 200 or 45 CFR Part 75 Appendix IX, &quot;Principles for Determining Costs Applicable to Research &amp; Development under Grants and Contracts with Hospitals&quot; as applicable.</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>10) No-cost extensions require the approval of the PTE. Any requests for a no-cost extension should be addressed to and received by the</xsl:text>
</fo:inline>
<fo:inline>
<xsl:text>&#160;</xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractTemplateInfo">
<xsl:for-each select="subcontract:NoCostExtensionContactDescription">
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
<fo:inline>';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '
<xsl:copy-of select="$value-of-template"/>
</fo:inline>
</xsl:otherwise>
</xsl:choose>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>, as shown in Attachments 3A, not less than 30 days prior to the desired effective date of the requested change.</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>11) The Subaward is subject to the terms and conditions of the PTE Award and other special terms and conditions, as identified in Attachment 2.</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>12) By signing this Research Subaward Agreement Subrecipient makes the certifications and assurances shown in Attachments 1 and 2.</xsl:text>  </fo:inline>
<fo:block/>
<fo:inline font-family="Arial" font-size="9pt" font-weight="normal">
<xsl:text>13) Research Terms &amp; Conditions-RESERVED </xsl:text>
</fo:inline>

<fo:inline>
<xsl:text>&#160;</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row font-size="9pt" font-weight="normal">
<fo:table-cell border="solid 1pt gray" padding="1pt" display-align="center">
<fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing="2pt">
<fo:table-column column-width="75%"/>
<fo:table-column column-width="25%"/>
<fo:table-body start-indent="0pt">
<fo:table-row>
<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>By an Authorized Official of Pass-through Entity:</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>______________________________________________</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text> _______________</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Name:&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Date&#160;&#160; </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Title:&#160;&#160;&#160; </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:block>
</fo:table-cell>
<fo:table-cell border="solid 1pt gray" padding="1pt" display-align="center">
<fo:block>';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing="2pt">
<fo:table-column column-width="75%"/>
<fo:table-column column-width="25%"/>
<fo:table-body start-indent="0pt">
<fo:table-row>
<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>By an Authorized Official of Subrecipient:</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>______________________________________________</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text> _______________</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Name:&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Date&#160;&#160; </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Title:&#160;&#160;&#160; </xsl:text>
</fo:inline>
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
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block break-after="page">
<fo:leader leader-pattern="space"/>
</fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-body start-indent="0pt">
<fo:table-row>
<fo:table-cell padding="2pt" display-align="center">
<fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '
</fo:inline-container>
<fo:table table-layout="fixed" width="100%" border="solid 1pt gray" border-spacing="-1pt">
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-body start-indent="0pt">
<fo:table-row font-family="Arial" font-size="12pt">
<fo:table-cell border="solid 1pt gray" padding="0" display-align="center">
<fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block text-align="center" margin="0pt">
<fo:block>
<fo:inline font-family="Arial" font-size="14pt" font-weight="bold">
<xsl:text>Attachment 1</xsl:text>
</fo:inline>
</fo:block>
</fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block text-align="center" margin="0pt">
<fo:block>
<fo:inline font-family="Arial" font-size="14pt" font-weight="bold">
<xsl:text>Research Subaward Agreement</xsl:text>
</fo:inline>
</fo:block>
</fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block text-align="center" margin="0pt">
<fo:block>
<fo:inline font-family="Arial" font-size="14pt" font-weight="bold">
<xsl:text>Certifications and Assurances</xsl:text>
</fo:inline>
<fo:inline font-family="Arial" font-size="12pt" font-weight="bold">
<xsl:text>&#160; </xsl:text>
</fo:inline>
</fo:block>
</fo:block>
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
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-body start-indent="0pt">
<fo:table-row>
<fo:table-cell padding="2pt" display-align="center">
<fo:block>
<fo:inline font-family="Arial" font-size="8pt">
<xsl:text>By signing the Subaward Agreement, the authorized official of Subrecipient certifies, to the best of his/her knowledge and belief that:</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="2pt" display-align="center">
<fo:block/>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="2pt" display-align="center">
<fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block text-align="left" margin="0pt">
<fo:block>
<fo:inline font-family="Arial" font-size="12pt" font-weight="bold">
<xsl:text>Certification Regarding Lobbying</xsl:text>
</fo:inline>';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '
</fo:block>
</fo:block>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="2pt" display-align="center">
<fo:block>
<fo:inline font-family="Arial" font-size="9pt">
<xsl:text>1) No Federal appropriated funds have been paid or will be paid, by or on behalf of the Subrecipient, to any person for influencing or attempting to influence an officer or employee of any agency, a Member of Congress, an officer or employee of Congress, or an employee of a Member of Congress in connection with the awarding of any Federal contract, the making of any Federal grant,the making of any Federal loan, the entering into of any cooperative agreement, and the extension, continuation, renewal, amendment, or modification of any Federal contract, grant, loan, or cooperative agreement.</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline font-family="Arial" font-size="9pt">
<xsl:text> 2) If any funds other than Federal appropriated funds have been paid or will be paid to any person for influencing or intending to influence an officer or employee of any agency, a Member of Congress, an officer or employee of Congress, or an employee of a Member of Congress in connection with this Federal contract, grant, loan, or cooperative agreement, the Subrecipient shall complete and submit Standard Form -LLL, &quot;Disclosure Form to Report Lobbying&quot;, to the the Pass-through Entity.</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline font-family="Arial" font-size="9pt">
<xsl:text>3) The Subrecipient shall require that the language of this certification be included in the award documents for all subawards at all tiers (including subcontracts, subgrants, and contracts under grants, loans, and cooperative agreements) and that all subrecipients shall certify and disclose accordingly. </xsl:text>
<xsl:text>  This certification is a material representation of fact upon which reliance was placed when this transaction was made or entered into. Submission of this certification is a prerequisite for making or entering into this transaction imposed by Section 1352, Title 31, U.S. Code. Any person who fails to file the required certification shall be subject to a civil penalty of not less than $10,000 and not more than $100,000 for each such failure.</xsl:text>
</fo:inline>
<fo:block/>
<fo:inline font-family="Arial" font-size="9pt">
<xsl:text>  This certification is a material representation of fact upon which reliance was placed when this transaction was made or entered into. Submission of this certification is a prerequisite for making or entering into this transaction imposed by Section 1352, Title 31, U.S. Code. Any person who fails to file the required certification shall be subject to a civil penalty of not less than $10,000 and not more than $100,000 for each such failure.</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="2pt" display-align="center">
<fo:block/>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="2pt" display-align="center">
<fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block text-align="center" margin="0pt">
<fo:block text-align="left" margin="0pt">
<fo:inline font-family="Arial" font-size="12pt" font-weight="bold">
<xsl:text>Debarment, Suspension, and Other Responsibility Matters</xsl:text>
</fo:inline>
</fo:block>
</fo:block>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="2pt" display-align="center">
<fo:block>
<fo:block/>
<fo:inline font-family="Arial" font-size="9pt">
<xsl:text>Subrecipient certifies by signing this Subaward Agreement that neither it nor its principals are presently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from participation in this transaction by any federal department or agency.</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="2pt" display-align="center">
<fo:block/>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="2pt" display-align="center">
<fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block text-align="left" margin="0pt">
<fo:block>
<fo:inline font-family="Arial" font-size="12pt" font-weight="bold">
<xsl:text>Audit and Access to Records</xsl:text>
</fo:inline>
</fo:block>
</fo:block>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="2pt" display-align="center">
<fo:block>
<fo:block/>
<fo:inline font-family="Arial" font-size="9pt">
<xsl:text>Subrecipient certifies by signing this Subaward Agreement that it complies with the Uniform Guidance, will provide notice of the completion of required audits and any adverse findings which impact this subaward as required by parts 200.501-200.521, and will provide access to records as required by parts 200.336, 200.337, and 200.201 as applicable.</xsl:text>
</fo:inline>';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
DECLARE data CLOB; buffer VARCHAR2(30000);
BEGIN
  SELECT FORM INTO data FROM SUBAWARD_FORMS
  WHERE
    TEMPLATE_TYPE_CODE=4 AND FORM_ID = 'FDP Template' FOR UPDATE;
  buffer := '
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
</xsl:for-each>
<fo:block/>
</fo:block>
<fo:block id="SV_RefID_PageTotal"/>
</fo:flow>
</fo:page-sequence>
</fo:root>
</xsl:template>
<xsl:template name="headerall">
<fo:static-content flow-name="xsl-region-before">
<fo:block>
<xsl:for-each select="$XML"/>
</fo:block>
</fo:static-content>
</xsl:template>
<xsl:template name="double-backslash">
<xsl:param name="text"/>
<xsl:param name="text-length"/>
<xsl:variable name="text-after-bs" select="substring-after($text, ''\\'')"/>
<xsl:variable name="text-after-bs-length" select="string-length($text-after-bs)"/>
<xsl:choose>
<xsl:when test="$text-after-bs-length = 0">
<xsl:choose>
<xsl:when test="substring($text, $text-length) = ''\\''">
<xsl:value-of select="concat(substring($text,1,$text-length - 1), ''\\'')"/>
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="$text"/>
</xsl:otherwise>
</xsl:choose>
</xsl:when>
<xsl:otherwise>
<xsl:value-of select="concat(substring($text,1,$text-length - $text-after-bs-length - 1), ''\\'')"/>
<xsl:call-template name="double-backslash">
<xsl:with-param name="text" select="$text-after-bs"/>
<xsl:with-param name="text-length" select="$text-after-bs-length"/>
</xsl:call-template>
</xsl:otherwise>
</xsl:choose>
</xsl:template>
</xsl:stylesheet>
';
  DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
end;
/
