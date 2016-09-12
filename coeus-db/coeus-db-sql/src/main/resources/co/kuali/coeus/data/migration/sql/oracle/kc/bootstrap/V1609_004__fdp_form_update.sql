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

INSERT INTO SUBAWARD_FORMS(FORM_ID,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,FORM,FILE_NAME,CONTENT_TYPE,VER_NBR,OBJ_ID,TEMPLATE_TYPE_CODE) values ('FDP Template','FDP Agreement',sysdate,'admin',
'<?xml version="1.0" encoding="UTF-8"?>
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
</xsl:otherwise>
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
</fo:inline>
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
<xsl:for-each select="award:AwardDetails">
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
</fo:inline>
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
<fo:block> <xsl:text></xsl:text> </fo:block>
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
</xsl:call-template>
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
<xsl:when test="subcontract:SubContractData/subcontract:PrintRequirement/subcontract:Attachment4Required = &quot;Y&quot;">
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
</fo:inline>
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
<xsl:text> </xsl:text>  </fo:inline>
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
<fo:inline>
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
</fo:block>
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
</fo:inline>
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
</fo:inline>
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
','FDP_Template_Agreement.xsl','application/octet-stream',1,SYS_GUID(),4);

delete from SUBAWARD_FORMS WHERE FORM_ID = 'FDP Modification';

INSERT INTO SUBAWARD_FORMS(FORM_ID,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,FORM,FILE_NAME,CONTENT_TYPE,VER_NBR,OBJ_ID,TEMPLATE_TYPE_CODE) values ('FDP Modification','FDP Modification',sysdate,'admin',
'<?xml version="1.0" encoding="UTF-8"?>
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
<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" text-align="left" display-align="center" border="solid 1pt gray">
<fo:table-column column-width="100%"/>
<fo:table-body start-indent="0pt">
<fo:table-row>
<fo:table-cell padding="0" height="32" text-align="center" display-align="center">
<fo:table font-size="16pt" padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="100%"/>
<fo:table-body start-indent="0pt">
<fo:table-row>
<fo:table-cell>
<fo:block>
<fo:block margin-left="(100% - 100%) div 2" margin-right="(100% - 100%) div 2" text-align="center" margin="0pt">
<fo:block>
<fo:inline>
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
<fo:inline font-weight="bold">
<xsl:text>Amendment</xsl:text>
</fo:inline>
</fo:block>
</fo:block>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell text-align="center" display-align="center">
<fo:table font-size="12pt" font-weight="bold" padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="50%"/>
<fo:table-column column-width="50%"/>
<fo:table-body start-indent="0pt">
<fo:table-row>
<fo:table-cell border-top="solid 1pt gray" border-right="solid 1pt gray">
<fo:block>
<fo:inline>
<xsl:text>Pass-through Entity (PTE)</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell border-top="solid 1pt gray">
<fo:block>
<fo:inline>
<xsl:text>Subrecipient</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell display-align="center">
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="50%"/>
<fo:table-column column-width="50%"/>
<fo:table-body>
<fo:table-row >
<fo:table-cell padding="2" text-align="left" display-align="center" border-top="solid 1pt gray" border-right="solid 1pt gray">
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="100%"/>
<fo:table-body start-indent="0pt">
<fo:table-row>
<fo:table-cell>
<fo:block>
<fo:inline font-weight="bold">
<xsl:text>PTE: </xsl:text>
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
<fo:table-cell>
<fo:block>
<fo:inline font-weight="bold">
<xsl:text>Address: </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:PrimeRecipientContacts">
<xsl:for-each select="subcontract:OrgRolodexDetails">
<xsl:for-each select="subcontract:Address1">
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
<fo:block/>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:PrimeRecipientContacts">
<xsl:for-each select="subcontract:OrgRolodexDetails">
<xsl:for-each select="subcontract:Address2">
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
<fo:block/>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:PrimeRecipientContacts">
<xsl:for-each select="subcontract:OrgRolodexDetails">
<xsl:for-each select="subcontract:Address3">
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
<fo:block/>
<fo:inline>
<xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:PrimeRecipientContacts">
<xsl:for-each select="subcontract:OrgRolodexDetails">
<xsl:for-each select="subcontract:City">
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
<fo:block/>
<fo:inline>
<xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:PrimeRecipientContacts">
<xsl:for-each select="subcontract:OrgRolodexDetails">
<xsl:for-each select="subcontract:StateDescription">
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
<fo:block/>
<fo:inline>
<xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:PrimeRecipientContacts">
<xsl:for-each select="subcontract:OrgRolodexDetails">
<xsl:for-each select="subcontract:Pincode">
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
</fo:table-body>
</fo:table>
</fo:table-cell>
<fo:table-cell padding="2" text-align="left" display-align="center" border-top="solid 1pt gray">
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="100%"/>
<fo:table-body start-indent="0pt">

<fo:table-row font-weight="normal" >
<fo:table-cell  display-align="center">
<fo:block>
<fo:inline font-weight="bold">
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
<fo:table-row font-weight="normal" >
<fo:table-cell padding="0pt"  display-align="center">
<fo:block>
<fo:inline font-weight="bold">
<xsl:text>Address: </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractDetail">
<xsl:for-each select="subcontract:SubcontractorOrgRolodexDetails">
<xsl:for-each select="subcontract:Address1">
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
<fo:block/>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractDetail">
<xsl:for-each select="subcontract:SubcontractorOrgRolodexDetails">
<xsl:for-each select="subcontract:Address2">
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
<fo:block/>

<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractDetail">
<xsl:for-each select="subcontract:SubcontractorOrgRolodexDetails">
<xsl:for-each select="subcontract:Address3">
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
<fo:block/>
<fo:inline>
<xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractDetail">
<xsl:for-each select="subcontract:SubcontractorOrgRolodexDetails">
<xsl:for-each select="subcontract:City">
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
<fo:block/>
<fo:inline>
<xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractDetail">
<xsl:for-each select="subcontract:SubcontractorOrgRolodexDetails">
<xsl:for-each select="subcontract:StateDescription">
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
<fo:block/>
<fo:inline>
<xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractDetail">
<xsl:for-each select="subcontract:SubcontractorOrgRolodexDetails">
<xsl:for-each select="subcontract:Pincode">
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
</fo:table-body>
</fo:table>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell display-align="center">
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="50%"/>
<fo:table-column column-width="50%"/>
<fo:table-body>
<fo:table-row>
<fo:table-cell padding="2" text-align="left" display-align="center" border-top="solid 1pt gray" border-right="solid 1pt gray">
<fo:block>
<fo:inline font-weight="bold">
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
<fo:table-cell padding="2" text-align="left" display-align="center" border-top="solid 1pt gray">
<fo:block>
<fo:inline font-weight="bold">
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
</fo:table-body>
</fo:table>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell display-align="center">
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="40%"/>
<fo:table-column column-width="20%"/>
<fo:table-column column-width="40%"/>
<fo:table-body>
<fo:table-row>
<fo:table-cell padding="2" text-align="left" display-align="center"  border-top="solid 1pt gray" border-right="solid 1pt gray">
<fo:block>
<fo:inline font-weight="bold">
<xsl:text>PTE Federal Award No: </xsl:text>
</fo:inline>
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
<fo:table-cell padding="2" text-align="left" display-align="center" border-top="solid 1pt gray" border-right="solid 1pt gray">
<fo:block>
<fo:inline font-weight="bold">
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
<fo:table-cell padding="2" text-align="left" display-align="center" border-top="solid 1pt gray">
<fo:block>
<fo:inline font-weight="bold">
<xsl:text>Federal Awarding Agency: </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:Award">
<xsl:for-each select="award:AwardDetails">
<xsl:for-each select="award:AwardHeader">
<xsl:for-each select="award:SponsorDescription">
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
</fo:table-body>
</fo:table>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell display-align="center">
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="100%"/>
<fo:table-body>
<fo:table-row>
<fo:table-cell padding="2" text-align="left" display-align="center" border-top="solid 1pt gray">
<fo:block>
<fo:inline font-weight="bold">
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
</fo:table-body>
</fo:table>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell display-align="center">
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="31%"/>
<fo:table-column column-width="23%"/>
<fo:table-column column-width="23%"/>
<fo:table-column column-width="23%"/>
<fo:table-body>
<fo:table-row>
<fo:table-cell padding="2" text-align="left" display-align="center" border-top="solid 1pt gray" border-right="solid 1pt gray">
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="50%"/>
<fo:table-column column-width="50%"/>
<fo:table-body>
<fo:table-row>
<fo:table-cell number-columns-spanned="2">
<fo:block>
<fo:inline font-weight="bold">
<xsl:text>Subaward Period of Performance: </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="0" text-align="left" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Start Date: </xsl:text>
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
<fo:table-cell padding="0" text-align="left" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>End Date: </xsl:text>
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
<fo:table-cell padding="2" text-align="left"  border-top="solid 1pt gray" border-right="solid 1pt gray">
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="100%"/>
<fo:table-body>
<fo:table-row>
<fo:table-cell >
<fo:block>
<fo:inline font-weight="bold">
<xsl:text>
Amount Funded This Action:
</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell>
<fo:block>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractAmountInfo">
<xsl:for-each select="subcontract:ObligatedChange">
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
</fo:table-body>
</fo:table>
</fo:table-cell>
<fo:table-cell padding="2" text-align="left"  border-top="solid 1pt gray" border-right="solid 1pt gray">
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="100%"/>
<fo:table-body>
<fo:table-row>
<fo:table-cell>
<fo:block>
<fo:inline font-weight="bold">
<xsl:text>
Amendment No.:
</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell>
<fo:block>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractAmountInfo">
<xsl:for-each select="subcontract:ModificationNumber">
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
</fo:table-body>
</fo:table>
</fo:table-cell>
<fo:table-cell padding="2" text-align="left"  border-top="solid 1pt gray">
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="100%"/>
<fo:table-body>
<fo:table-row>
<fo:table-cell>
<fo:block>
<fo:inline font-weight="bold">
<xsl:text>
Subaward No.:
</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell>
<fo:block>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractDetail">
<xsl:for-each select="subcontract:PONumber">
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
</fo:table-body>
</fo:table>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell display-align="center">
<fo:table table-layout="fixed" width="100%">
<fo:table-column column-width="33%"/>
<fo:table-column column-width="33%"/>
<fo:table-column column-width="34%"/>
<fo:table-body>
<fo:table-row>
<fo:table-cell padding="2" text-align="left" display-align="center" border-top="solid 1pt gray" border-right="solid 1pt gray">
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="100%"/>
<fo:table-body>
<fo:table-row>
<fo:table-cell>
<fo:block>
<fo:inline font-weight="bold">
<xsl:text>Effective Date of Amendment: </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell>
<fo:block>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractAmountInfo">
<xsl:for-each select="subcontract:ModificationEffectiveDate">
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
<fo:table-cell padding="2" text-align="left" display-align="center" border-top="solid 1pt gray" border-right="solid 1pt gray">
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="100%"/>
<fo:table-body>
<fo:table-row>
<fo:table-cell>
<fo:block>
<fo:inline font-weight="bold">
<xsl:text>Total Amount of Federal Funds Obligated to date: </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell>
<fo:block>
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
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:table-cell>
<fo:table-cell padding="2" text-align="left" display-align="center" border-top="solid 1pt gray">
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="100%"/>
<fo:table-body>
<fo:table-row>
<fo:table-cell>
<fo:block>
<fo:inline font-weight="bold">
<xsl:text>Subject to FFATA: </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell>
<fo:block>
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
<xsl:text> Yes </xsl:text>
</fo:inline>
<fo:inline font-size="8pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<fo:inline>
<xsl:text>Or </xsl:text>
</fo:inline>
<fo:inline font-size="8pt">
<xsl:text>&#160;&#160; </xsl:text>
</fo:inline>
<xsl:choose>
<xsl:when test="subcontract:SubContractData/subcontract:SubcontractDetail/subcontract:FFATA = &quot;N&quot;">
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
</fo:table-body>
</fo:table>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="2" display-align="center" border-top="solid 1pt gray">
<fo:block>
<fo:block/>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block text-align="center" margin="0pt">
<fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block font-size="medium" font-weight="bold" margin="0pt">
<fo:block>
<fo:inline>
<xsl:text>Amendment(s) to Original Terms and Conditions</xsl:text>
</fo:inline>
</fo:block>
</fo:block>
<fo:block font-weight="bold" margin="0pt">
<fo:block>
<fo:inline>
<xsl:text>This Amendment revises the above-referenced Research Subaward Agreement as follows:</xsl:text>
</fo:inline>
</fo:block>
<fo:block text-align="center" >
<fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="1pt"/>
</fo:block>
</fo:block>
<fo:block text-align="left" font-weight="bold">
<fo:inline>
<xsl:text>Action:</xsl:text>
</fo:inline>
</fo:block>
<fo:block linefeed-treatment="preserve" margin="2pt" text-align="left">
<xsl:if test="subcontract:SubContractData/subcontract:SubcontractDetail/subcontract:ModificationType = &quot;&quot;">
<fo:inline>
<xsl:text></xsl:text>
</fo:inline>
</xsl:if>
</fo:block>
<fo:block linefeed-treatment="preserve" margin="0pt" text-align="left">
<fo:block linefeed-treatment="preserve" margin="0pt" text-align="left">
<xsl:if test="subcontract:SubContractData/subcontract:SubcontractDetail/subcontract:ModificationType = &quot;RESBOOT1001&quot;">
<fo:block>
<fo:inline>
<xsl:text>The Subaward Agreement between the Pass-through Entity and </xsl:text>
<fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractDetail">
<xsl:for-each select="subcontract:SubcontractorName">
<fo:inline>
<xsl:value-of select="."/>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
<fo:inline>
<xsl:text> is hereby modified.</xsl:text>
</fo:inline>
</fo:inline>
<fo:list-block>
<fo:list-item>
<fo:list-item-label end-indent="label-end()" text-align="right">
<fo:block>
<xsl:number format="1" value="1"/>.
</fo:block>
</fo:list-item-label>
<fo:list-item-body start-indent="body-start()">
<fo:block>
<fo:inline>
<xsl:text>This amendment authorizes incremental funding in the amount of  </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractAmountInfo">
<xsl:for-each select="subcontract:ObligatedChange">
<xsl:value-of select="."/>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:inline>
<xsl:text>.</xsl:text>
</fo:inline>
</fo:block>
</fo:list-item-body>
</fo:list-item>
<fo:list-item>
<fo:list-item-label end-indent="label-end()" text-align="right">
<fo:block>
<xsl:number format="2" value="2"/>.
</fo:block>
</fo:list-item-label>
<fo:list-item-body start-indent="body-start()">
<fo:block>
<fo:inline>
<xsl:text>Cumulative total funds authorized to date: </xsl:text>
</fo:inline>
<fo:inline>
<xsl:text>  </xsl:text>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractAmountInfo">
<xsl:for-each select="subcontract:ObligatedAmount">
<xsl:value-of select="."/>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:inline>
<xsl:text>.</xsl:text>
</fo:inline>
</fo:inline>
</fo:block>
</fo:list-item-body>
</fo:list-item>
</fo:list-block>
</fo:block>
</xsl:if>
</fo:block>
<fo:block linefeed-treatment="preserve" margin="2pt" text-align="left">
<xsl:if test="subcontract:SubContractData/subcontract:SubcontractDetail/subcontract:ModificationType = &quot;RESBOOT1000&quot;">
<fo:block>
<fo:inline>
<xsl:text>The Subaward Agreement between the Pass-through Entity and </xsl:text>
<fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractDetail">
<xsl:for-each select="subcontract:SubcontractorName">
<fo:inline>
<xsl:value-of select="."/>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
<fo:inline>
<xsl:text> is hereby modified.</xsl:text>
</fo:inline>
</fo:inline>
<fo:list-block>
<fo:list-item>
<fo:list-item-label end-indent="label-end()" text-align="right">
<fo:block>
<xsl:number format="1" value="1"/>.
</fo:block>
</fo:list-item-label>
<fo:list-item-body start-indent="body-start()">
<fo:block>
<fo:inline>
<xsl:text>This amendment extends the period of performance to </xsl:text>
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


<xsl:text> in accordance with the no-cost extension of the </xsl:text>
<fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:Award">
<xsl:for-each select="award:AwardDetails">
<xsl:for-each select="award:OtherHeaderDetails">
<xsl:for-each select="award:PrimeSponsorDescription">
<fo:inline>
<xsl:value-of select="."/>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
<fo:inline>
<xsl:text> prime award.</xsl:text>
</fo:inline>
</fo:block>
</fo:list-item-body>
</fo:list-item>
<fo:list-item>
<fo:list-item-label end-indent="label-end()" text-align="right">
<fo:block>
<xsl:number format="2" value="2"/>.
</fo:block>
</fo:list-item-label>
<fo:list-item-body start-indent="body-start()">
<fo:block>
<fo:inline>
<xsl:text>The cumulative Period of Performance is  </xsl:text>
</fo:inline>
<fo:inline>
<xsl:text>  </xsl:text>
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
<xsl:text> - </xsl:text>
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
<fo:inline>
<xsl:text>.</xsl:text>
</fo:inline>
</fo:inline>
</fo:block>
</fo:list-item-body>
</fo:list-item>
</fo:list-block>
</fo:block>
</xsl:if>
</fo:block>
<fo:block linefeed-treatment="preserve" margin="2pt" text-align="left">
<xsl:if test="subcontract:SubContractData/subcontract:SubcontractDetail/subcontract:ModificationType = &quot;RESBOOT1003&quot;">
<fo:block>
<fo:inline>
<xsl:text>The Subaward Agreement between the Pass-through Entity and </xsl:text>
<fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractDetail">
<xsl:for-each select="subcontract:SubcontractorName">
<fo:inline>
<xsl:value-of select="."/>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
<fo:inline>
<xsl:text> is hereby modified.</xsl:text>
</fo:inline>
</fo:inline>
</fo:block>
</xsl:if>
</fo:block>
<fo:block linefeed-treatment="preserve" margin="2pt" text-align="left">
<xsl:if test="subcontract:SubContractData/subcontract:SubcontractDetail/subcontract:ModificationType = &quot;RESBOOT1002&quot;">
<fo:block>
<fo:inline>
<xsl:text>The Subaward Agreement between the Pass-through Entity and </xsl:text>
<fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractDetail">
<xsl:for-each select="subcontract:SubcontractorName">
<fo:inline>
<xsl:value-of select="."/>
</fo:inline>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
</fo:inline>
<fo:inline>
<xsl:text> is hereby modified.</xsl:text>
</fo:inline>
</fo:inline>
<fo:list-block>
<fo:list-item>
<fo:list-item-label end-indent="label-end()" text-align="right">
<fo:block>
<xsl:number format="1" value="1"/>.
</fo:block>
</fo:list-item-label>
<fo:list-item-body start-indent="body-start()">
<fo:block>
<fo:inline>
<xsl:text>This amendment authorizes funds in the amount of </xsl:text>
</fo:inline>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractAmountInfo">
<xsl:for-each select="subcontract:ObligatedChange">
<xsl:value-of select="."/>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<xsl:text> to include the </xsl:text>
<fo:inline>
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
<xsl:text> - </xsl:text>
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
</fo:inline>
<fo:inline>
<xsl:text>&#160;</xsl:text><xsl:text>funding period.</xsl:text>
</fo:inline>
</fo:block>
</fo:list-item-body>
</fo:list-item>
<fo:list-item>
<fo:list-item-label end-indent="label-end()" text-align="right">
<fo:block text-align="right">
<xsl:number format="1" value="2"/>.
</fo:block>
</fo:list-item-label>
<fo:list-item-body start-indent="body-start()">
<fo:block>
<fo:inline>
<xsl:text>Cumulative total funds authorized to date:  </xsl:text>
</fo:inline>
<fo:inline>
<xsl:text>  </xsl:text>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractAmountInfo">
<xsl:for-each select="subcontract:ObligatedAmount">
<xsl:value-of select="."/>
</xsl:for-each>
</xsl:for-each>
</xsl:for-each>
<fo:inline>
<xsl:text>.</xsl:text>
</fo:inline>
</fo:inline>
</fo:block>
</fo:list-item-body>
</fo:list-item>
</fo:list-block>
</fo:block>
</xsl:if>
</fo:block>
</fo:block>
</fo:block>
</fo:block>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block linefeed-treatment="preserve" margin="2pt" text-align="left">
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="100%"/>
<fo:table-body>
<fo:table-row>
<fo:table-cell padding="2pt" height="110pt" display-align="before">
<fo:block>
<xsl:for-each select="subcontract:SubContractData">
<xsl:for-each select="subcontract:SubcontractDetail">
<xsl:for-each select="subcontract:Comments">
<xsl:variable name="value-of-template">
<xsl:apply-templates mode="without-preserve"/>
</xsl:variable>
<xsl:choose>
<xsl:when test="contains(string($value-of-template),''&#x2029;'')">
<fo:block padding-top="1pt" padding-bottom="1pt" white-space-collapse="false" linefeed-treatment="preserve" line-height="200pt">
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
</fo:table-body>
</fo:table>
</fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:block>
<xsl:choose>
<xsl:when test="subcontract:SubContractData/subcontract:SubcontractDetail/subcontract:PHSFCOI = &quot;Y&quot;">
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-body start-indent="0pt">
<fo:table-row>
<fo:table-cell padding="2pt" height="150pt" display-align="after">
<fo:block>
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="100%"/>
<fo:table-body>
<fo:table-row font-weight="bold">
<fo:table-cell padding="2pt">
<fo:block>
<fo:inline>
<xsl:text>NIH-Specific Requirements Promoting Objectivity in Research Applicable to Subrecipients (42 CFR Part 50 Subpart F) </xsl:text>
</fo:inline>
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
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-body>
<fo:table-row>
<fo:table-cell padding="2pt">
<fo:block>
<fo:block>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
<fo:list-item>
<fo:list-item-label end-indent="label-end()" text-align="right">
<fo:block>
<xsl:number format="1" value="1"/>.
</fo:block>
</fo:list-item-label>
<fo:list-item-body start-indent="body-start()">
<fo:block>
<fo:inline>
<xsl:text>42 CFR Part 50. 604 requires that institutions conducting PHS-funded research "Maintain an up-to-date, written, enforced policy on</xsl:text>
                                                                                                                    </fo:inline>
                                                                                                                    <fo:inline>
                                                                                                                        <xsl:text>financial conflicts of interest."  Further, "If the Institution carries out the PHS-funded research through a subrecipient (e.g., subcontractors or consortium members), the Institution (awardee Institution) must take reasonable steps to ensure that any subrecipient Investigator complies with this subpart by incorporating as part of a written agreement with the subrecipient terms that establish whether the financial conflicts of interest policy of the awardee Institution or that of the subrecipient will apply to the subrecipient Investigators."</xsl:text>
</fo:inline>
<fo:block white-space-collapse="false" white-space-treatment="preserve" font-size="0pt" line-height="15px"></fo:block>
<fo:block>
<xsl:text>&#x00A0;</xsl:text>
</fo:block>
<fo:inline>
<xsl:text>Subrecipient must designate herein whether the financial conflicts of interest policy of </xsl:text>
</fo:inline>
<fo:inline>
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
</fo:inline>
<xsl:text>  </xsl:text>
<fo:inline>
<xsl:text>Prime Recipient Institution, or </xsl:text>
</fo:inline>
<fo:inline>
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
</fo:inline>
<fo:inline>
<xsl:text>  </xsl:text>
</fo:inline>
<fo:inline>
<xsl:text>Subrecipient Institution (check one) will apply. If applying its own financial conflicts of interest policy, by execution of this Subaward Agreement, Subrecipient Institution certifies that its policy complies with 42 CFR Part 50.</xsl:text>
</fo:inline>
</fo:block>
<fo:block>
<xsl:text>&#x00A0;</xsl:text>
</fo:block>
<fo:block white-space-collapse="false" white-space-treatment="preserve" font-size="0pt" line-height="15px"></fo:block>
</fo:list-item-body>
</fo:list-item>
<fo:list-item>
<fo:list-item-label end-indent="label-end()" text-align="right">
<fo:block>
<xsl:number format="2" value="2"/>.
</fo:block>
</fo:list-item-label>
<fo:list-item-body start-indent="body-start()">
<fo:block>
<fo:inline>
<xsl:text>Subrecipient shall report any financial conflict of interest to Prime Recipients Administrative Representative, as designated on Attachment 3A.  Any financial conflicts of interest identified shall subsequently be reported to NIH.  </xsl:text>
</fo:inline>
<fo:inline text-decoration="underline">
<xsl:text>Such report shall be made before expenditure of funds authorized in this Subrecipient Agreement and within 45 days of any subsequently identified financial conflict of interest.</xsl:text>
</fo:inline>
</fo:block>
</fo:list-item-body>
</fo:list-item>
</fo:list-block>
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
</xsl:when>
<xsl:otherwise>
<fo:inline-container>
<fo:block>
<xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-body start-indent="0pt">
<fo:table-row>
<fo:table-cell padding="2pt" height="150pt" display-align="center">
<fo:block>
</fo:block>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</xsl:otherwise>
</xsl:choose>
</fo:block>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:block/>
<fo:inline font-weight="bold">
<xsl:text>All other terms and conditions of this Subaward Agreement remain in full force and effect.</xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell padding="0" display-align="center">
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="50%"/>
<fo:table-column column-width="50%"/>
<fo:table-body>
<fo:table-row>
<fo:table-cell padding="0" display-align="center" border-top="solid 1pt gray" border-right="solid 1pt gray">
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="75%"/>
<fo:table-column column-width="25%"/>
<fo:table-body>
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
<xsl:text>Name: </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Date: </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Title: </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:table-cell>
<fo:table-cell padding="0" display-align="center" border-top="solid 1pt gray">
<fo:table padding="0" table-layout="fixed" width="100%">
<fo:table-column column-width="75%"/>
<fo:table-column column-width="25%"/>
<fo:table-body>
<fo:table-row>
<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>By an Authorized Official of Subrecipient Entity:</xsl:text>
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
<xsl:text>Name: </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
<fo:table-cell padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Date: </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
<fo:table-row>
<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
<fo:block>
<fo:inline>
<xsl:text>Title: </xsl:text>
</fo:inline>
</fo:block>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
</fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
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
<fo:block/>
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
','FDP_Modification_Template.xsl','application/octet-stream',1,SYS_GUID(),4);

delete from SUBAWARD_FORMS WHERE FORM_ID = 'FDP_AFOSR';

INSERT INTO SUBAWARD_FORMS(FORM_ID,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,FORM,FILE_NAME,CONTENT_TYPE,VER_NBR,OBJ_ID,TEMPLATE_TYPE_CODE) values ('FDP_AFOSR',	'AFOSR Attachment',sysdate,'admin',
'<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:award="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/award" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:subcontract="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/subcontract" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:fo="http://www.w3.org/1999/XSL/Format">
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
							<fo:table font-family="Times New Roman" font-size="12pt" font-weight="bold" table-layout="fixed" width="100%" border="solid 1pt gray" border-spacing="-1pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell border="solid 1pt gray" padding="0" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:block text-align="center" margin="0pt">
													<fo:block>
														<fo:inline>
															<xsl:text>Attachment 2</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>Research Subaward Agreement</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>Prime Award Terms and Conditions</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>AFOSR</xsl:text>
														</fo:inline>
													</fo:block>
												</fo:block>
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
							<fo:table font-family="Times New Roman" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-size="12pt" font-weight="bold">
										<fo:table-cell padding="0" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Agency-Specific Certifications/Assurances</xsl:text>
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row font-size="10pt" font-weight="normal">
										<fo:table-cell padding="0" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text> By signing this Research Subaward Agreement Subrecipient makes the certifications and assurances specified in the Research Terms and Conditions Appendix C found at </xsl:text>
												</fo:inline>
												<fo:basic-link text-decoration="underline" color="blue">
													<xsl:choose>
														<xsl:when test="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf &apos;), 1, 1) = ''#''">
															<xsl:attribute name="internal-destination">
																<xsl:value-of select="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf &apos;), 2)"/>
															</xsl:attribute>
														</xsl:when>
														<xsl:otherwise>
															<xsl:attribute name="external-destination">
																<xsl:text>url(</xsl:text>
																<xsl:call-template name="double-backslash">
																	<xsl:with-param name="text">
																		<xsl:value-of select="string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf &apos;)"/>
																	</xsl:with-param>
																	<xsl:with-param name="text-length">
																		<xsl:value-of select="string-length(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf &apos;))"/>
																	</xsl:with-param>
																</xsl:call-template>
																<xsl:text>)</xsl:text>
															</xsl:attribute>
														</xsl:otherwise>
													</xsl:choose>
													<fo:inline>
														<xsl:text>http://www.nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf </xsl:text>
													</fo:inline>
												</fo:basic-link>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
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
							<fo:inline-container>
								<fo:block>
									<xsl:text>&#x2029;</xsl:text>
								</fo:block>
							</fo:inline-container>
							<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-family="Times New Roman" font-size="12pt" font-weight="bold">
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>General terms and conditions:</xsl:text>
												</fo:inline>
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
							<fo:table font-family="time" font-size="10pt" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="1"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>The restrictions on the expenditure of federal funds in appropriations acts are applicable to this subaward to the extent those restrictions are pertinent.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="2"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>32 CFR Part 32 or 32 CFR Part 33 as applicable.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="3"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>DoD 3210.6-R, Department of Defense Grants and Agreement Regulations, including addenda in effect as of the beginning date of the period of performance.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="4"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Research Terms and Conditions found at </xsl:text>
																</fo:inline>
																<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text> http://www.nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf </xsl:text>
																	</fo:inline>
																</fo:basic-link>
																<fo:inline>
																	<xsl:text> and Agency Specific Requirements found at </xsl:text>
																</fo:inline>
																<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/agencyspecifics/afosr_312.pdf&apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/agencyspecifics/afosr_312.pdf&apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;nsf.gov/pubs/policydocs/rtc/agencyspecifics/afosr_312.pdf&apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;nsf.gov/pubs/policydocs/rtc/agencyspecifics/afosr_312.pdf&apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text>http://www.nsf.gov/pubs/policydocs/rtc/agencyspecifics/afosr_312.pdf</xsl:text>
																	</fo:inline>
																</fo:basic-link>
																<fo:inline>
																	<xsl:text>, except for the following:</xsl:text>
																</fo:inline>
																<fo:inline-container>
																	<fo:block>
																		<xsl:text>&#x2029;</xsl:text>
																	</fo:block>
																</fo:inline-container>
																<fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="1"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>The right to initiate an automatic one-time extension of the end date provided by Article 25(c)(2) of the Research Terms and Conditions is replaced by the need to obtain prior written approval from the Prime Recipient;</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="2"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>The payment mechanism described in Article 22 and the financial reporting requirements in Article 52 of the Research Terms and Conditions and Article 11 of the Agency-Specific Requirements are replaced with Terms and Conditions (1) through (4) of this Subaward Agreement; and</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="3"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>Any prior approvals are to be sought from the Prime Recipient and not the Federal Awarding Agency.</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																</fo:list-block>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="5"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Title to equipment costing $5,000 or more that is purchased or fabricated with research funds or collaborator cost sharing funds, as direct costs of the project or program, shall unconditionally vest in the collaborator upon acquisition without further obligation to the Federal Awarding Agency subject to the conditions specified in Article 34(a) of the Research Terms and Conditions.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
												</fo:list-block>
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
							<fo:table font-family="time" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="28%"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell font-family="times" font-size="12pt" font-weight="bold" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Special terms and conditions: </xsl:text>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell font-family="time" font-size="10pt" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>&#160;</xsl:text>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row font-family="time" font-size="10pt">
										<fo:table-cell font-family="times" font-size="12pt" font-weight="bold" number-columns-spanned="2" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:list-block font-family="time" font-size="10pt" font-weight="normal" provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="1"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Copyrights</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>Subrecipient </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
																		<fo:inline>
																			<xsl:text>grants</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
																		<fo:inline>
																			<xsl:text>shall grant</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text> to Prime Recipient an irrevocable, royalty-free, non-transferable, non-exclusive right and license to use, reproduce, make derivative works, display, and perform publicly any copyrights or copyrighted material (including any computer software and its documentation and/or databases) first developed and delivered under this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="2"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Data Rights</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>Subrecipient </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
																		<fo:inline>
																			<xsl:text>grants</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
																		<fo:inline>
																			<xsl:text>shall grant</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text> to Prime Recipient the right to use data created in the performance of this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award. [Do not add a Patent or Inventions Clause. The prime award governs rights to patents and inventions. Prime Recipient cannot obtain rights in the Subrecipient''s subject inventions as a part of consideration for the subaward. Should it be necessary, the Federal Government can authorize the Prime Recipient''s right to practice a Subrecipients''s subject invention (as well as subject data or copyrights) on behalf of the Federal Government.]</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="3"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Automatic Carry Forward:&#160; </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;Y&apos;">
																		<fo:inline>
																			<xsl:text>Yes</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;N&apos;">
																		<fo:inline>
																			<xsl:text>No</xsl:text>
																		</fo:inline>
																		<fo:block/>
																		<fo:inline>
																			<xsl:text>Carry Forward requests must be sent to Prime Recipient''s - </xsl:text>
																		</fo:inline>
																		<xsl:for-each select="subcontract:SubContractData">
																			<xsl:for-each select="subcontract:SubcontractTemplateInfo">
																				<xsl:for-each select="subcontract:CarryForwardRequestsSentToDescription">
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
																		<fo:inline>
																			<xsl:text>, as shown in Attachment 3.</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text>&#160;&#160;&#160;&#160; </xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
												</fo:list-block>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
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
','AFOSR Attachment.xsl','application/octet-stream',1,SYS_GUID(),2);

delete from SUBAWARD_FORMS WHERE FORM_ID = 'FDP_AMRMC';

INSERT INTO SUBAWARD_FORMS(FORM_ID,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,FORM,FILE_NAME,CONTENT_TYPE,VER_NBR,OBJ_ID,TEMPLATE_TYPE_CODE) values ('FDP_AMRMC',	'AMRMC Attachment',sysdate,'admin',
'<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:award="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/award" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:subcontract="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/subcontract" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:fo="http://www.w3.org/1999/XSL/Format">
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
							<fo:table font-family="Times New Roman" font-size="12pt" font-weight="bold" table-layout="fixed" width="100%"  border-spacing="-1pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell border="solid 1pt gray" padding="0" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:block text-align="center" margin="0pt">
													<fo:block>
														<fo:inline>
															<xsl:text>Attachment 2</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>Research Subaward Agreement</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>Prime Award Terms and Conditions</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>AMRMC</xsl:text>
														</fo:inline>
													</fo:block>
												</fo:block>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell padding="0" display-align="center">
												<fo:block text-align="left" margin="0pt">
													<fo:block>
														<fo:inline>
															<xsl:text> </xsl:text>
														</fo:inline>
													</fo:block>
												</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell padding="0" display-align="center">
												<fo:block text-align="left" margin="0pt" font-size="10pt" font-weight="normal">
														<fo:inline font-weight="bold">
															<xsl:text>Please note: </xsl:text>
														</fo:inline>
														<fo:inline>
															<xsl:text>While this Attachment 2 may be used as a tool to assist recipients and utilized as such, it is no longer part of our Agency Specific Terms &amp; Conditions in use on all awards made after Oct.1, 2011. On the USAMRAA website,  </xsl:text>
														</fo:inline>
														<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;usamraa.army.mil&apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;usamraa.army.mil&apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;usamraa.army.mil&apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;usamraa.army.mil&apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text> http://www.usamraa.army.mil </xsl:text>
																	</fo:inline>
														</fo:basic-link>
														<fo:inline>
															<xsl:text>, you will continue to find Attachment 2, but only under the heading of awards made prior to Oct. 1, 2011.</xsl:text>
														</fo:inline>
												</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell padding="0" display-align="center">
												<fo:block text-align="left" margin="0pt">
													<fo:block>
														<fo:inline>
															<xsl:text> </xsl:text>
														</fo:inline>
													</fo:block>
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
							<fo:table font-family="Times New Roman" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-size="12pt" font-weight="bold">
										<fo:table-cell padding="0" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Certifications/Assurances</xsl:text>
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row font-size="10pt" font-weight="normal">
										<fo:table-cell padding="0" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text> By signing this Research Subaward Agreement Subrecipient makes the certifications and assurances specified in the Research Terms and Conditions Appendix C found at </xsl:text>
												</fo:inline>
												<fo:basic-link text-decoration="underline" color="blue">
													<xsl:choose>
														<xsl:when test="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf&apos;), 1, 1) = ''#''">
															<xsl:attribute name="internal-destination">
																<xsl:value-of select="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf&apos;), 2)"/>
															</xsl:attribute>
														</xsl:when>
														<xsl:otherwise>
															<xsl:attribute name="external-destination">
																<xsl:text>url(</xsl:text>
																<xsl:call-template name="double-backslash">
																	<xsl:with-param name="text">
																		<xsl:value-of select="string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf&apos;)"/>
																	</xsl:with-param>
																	<xsl:with-param name="text-length">
																		<xsl:value-of select="string-length(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf&apos;))"/>
																	</xsl:with-param>
																</xsl:call-template>
																<xsl:text>)</xsl:text>
															</xsl:attribute>
														</xsl:otherwise>
													</xsl:choose>
													<fo:inline>
														<xsl:text> http://www.nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf </xsl:text>
													</fo:inline>
												</fo:basic-link>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
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
							<fo:inline-container>
								<fo:block>
									<xsl:text>&#x2029;</xsl:text>
								</fo:block>
							</fo:inline-container>
							<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-family="Times New Roman" font-size="12pt" font-weight="bold">
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>General terms and conditions:</xsl:text>
												</fo:inline>
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
							<fo:table font-family="time" font-size="10pt" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="1"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>Research Terms and Conditions found at </xsl:text>
																</fo:inline>
																<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text> http://www.nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf </xsl:text>
																	</fo:inline>
																</fo:basic-link>
																<fo:inline>
																	<xsl:text> and Agency Specific Requirements found at </xsl:text>
																</fo:inline>
																<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/amrmc_708.pdf&apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/amrmc_708.pdf&apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;nsf.gov/pubs/policydocs/rtc/amrmc_708.pdf&apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;nsf.gov/pubs/policydocs/rtc/amrmc_708.pdf&apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text>http://www.nsf.gov/pubs/policydocs/rtc/amrmc_708.pdf</xsl:text>
																	</fo:inline>
																</fo:basic-link>
																<fo:inline>
																	<xsl:text>, except for the following:</xsl:text>
																</fo:inline>
																<fo:inline-container>
																	<fo:block>
																		<xsl:text>&#x2029;</xsl:text>
																	</fo:block>
																</fo:inline-container>
																<fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="1"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>The right to initiate an automatic one-time extension of the end date provided by Article 25(c)(2) is replaced by the need to obtain prior written approval from the Prime Recipient;</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="2"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>The payment mechanism described in Article 22 and the financial reporting requirements in Article 52 of the Research Terms and Conditions and Article 9 of the Agency-Specific Requirements are replaced with Terms and Conditions (1) through (4) of this agreement; and</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="3"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>Any prior approvals are to be sought from the Prime Recipient and not the Federal Awarding Agency.</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																</fo:list-block>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="2"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Title to equipment costing $5,000 or more that is purchased or fabricated with research funds or Subrecipient cost sharing&#160;&#160;&#160; funds, as direct costs of the project or program, shall unconditionally vest in the Subrecipient upon acquisition without further obligation to the Federal Awarding Agency subject to the conditions specified in Article 34(a) of the Research Terms and Conditions.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
												</fo:list-block>
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
							<fo:table font-family="time" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="28%"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell font-family="times" font-size="12pt" font-weight="bold" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Special terms and conditions: </xsl:text>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell font-family="time" font-size="10pt" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>&#160;</xsl:text>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row font-family="time" font-size="10pt">
										<fo:table-cell font-family="times" font-size="12pt" font-weight="bold" number-columns-spanned="2" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:list-block font-family="time" font-size="10pt" font-weight="normal" provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="1"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Copyrights</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>Subrecipient </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
																		<fo:inline>
																			<xsl:text>grants</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
																		<fo:inline>
																			<xsl:text>shall grant</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text> to Prime Recipient an irrevocable, royalty-free, non-transferable, non-exclusive right and license to use, reproduce, make derivative works, display, and perform publicly any copyrights or copyrighted material (including any computer software and its documentation and/or databases) first developed and delivered under this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="2"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Data Rights</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>Subrecipient </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
																		<fo:inline>
																			<xsl:text>grants</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
																		<fo:inline>
																			<xsl:text>shall grant</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text> to Prime Recipient the right to use data created in the performance of this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award.</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>[Do not add a Patent or Inventions Clause. The prime award governs rights to patents and inventions. Prime Recipient cannot obtain rights in the Subrecipient''s subject inventions as a part of consideration for the subaward.&#160; Should it be necessary, the Federal Government can authorize the Prime Recipient''s right to practice a Subrecipient''s subject invention (as well as subject data or copyrights) on behalf of the Federal Government.]</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="3"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Automatic Carry Forward:&#160; </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;Y&apos;">
																		<fo:inline>
																			<xsl:text>Yes</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;N&apos;">
																		<fo:inline>
																			<xsl:text>No</xsl:text>
																		</fo:inline>
																		<fo:block/>
																		<fo:inline>
																			<xsl:text>Carry Forward requests must be sent to Prime Recipient''s - </xsl:text>
																		</fo:inline>
																		<xsl:for-each select="subcontract:SubContractData">
																			<xsl:for-each select="subcontract:SubcontractTemplateInfo">
																				<xsl:for-each select="subcontract:CarryForwardRequestsSentToDescription">
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
																		<fo:inline>
																			<xsl:text>, as shown in Attachment 3.</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text>&#160;&#160;&#160;&#160; </xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
												</fo:list-block>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
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
','AMRMC Attachment.xsl','application/octet-stream',1,SYS_GUID(),2);

delete from SUBAWARD_FORMS WHERE FORM_ID = 'FDP_ARO';

INSERT INTO SUBAWARD_FORMS(FORM_ID,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,FORM,FILE_NAME,CONTENT_TYPE,VER_NBR,OBJ_ID,TEMPLATE_TYPE_CODE) values ('FDP_ARO',	'ARO Attachment',sysdate,'admin',
'<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:award="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/award" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:subcontract="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/subcontract" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:fo="http://www.w3.org/1999/XSL/Format">
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
							<fo:table font-family="Times New Roman" font-size="12pt" font-weight="bold" table-layout="fixed" width="100%" border="solid 1pt gray" border-spacing="-1pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell border="solid 1pt gray" padding="0" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:block text-align="center" margin="0pt">
													<fo:block>
														<fo:inline>
															<xsl:text>Attachment 2</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>Research Subaward Agreement</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>Prime Award Terms and Conditions</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>ARO</xsl:text>
														</fo:inline>
													</fo:block>
												</fo:block>
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
							<fo:table font-family="Times New Roman" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-size="12pt" font-weight="bold">
										<fo:table-cell padding="0" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Certifications/Assurances</xsl:text>
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row font-size="10pt" font-weight="normal">
										<fo:table-cell padding="0" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text> By signing this Research Subaward Agreement Subrecipient makes the certifications and assurances specified in the Research Terms and Conditions Appendix C found at </xsl:text>
												</fo:inline>
												<fo:basic-link text-decoration="underline" color="blue">
													<xsl:choose>
														<xsl:when test="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf&apos;), 1, 1) = ''#''">
															<xsl:attribute name="internal-destination">
																<xsl:value-of select="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf&apos;), 2)"/>
															</xsl:attribute>
														</xsl:when>
														<xsl:otherwise>
															<xsl:attribute name="external-destination">
																<xsl:text>url(</xsl:text>
																<xsl:call-template name="double-backslash">
																	<xsl:with-param name="text">
																		<xsl:value-of select="string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf&apos;)"/>
																	</xsl:with-param>
																	<xsl:with-param name="text-length">
																		<xsl:value-of select="string-length(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf&apos;))"/>
																	</xsl:with-param>
																</xsl:call-template>
																<xsl:text>)</xsl:text>
															</xsl:attribute>
														</xsl:otherwise>
													</xsl:choose>
													<fo:inline>
														<xsl:text>http://www.nsf.gov/bfa/dias/policy/rtc/appc.pdf </xsl:text>
													</fo:inline>
												</fo:basic-link>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
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
							<fo:inline-container>
								<fo:block>
									<xsl:text>&#x2029;</xsl:text>
								</fo:block>
							</fo:inline-container>
							<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-family="Times New Roman" font-size="12pt" font-weight="bold">
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>General terms and conditions:</xsl:text>
												</fo:inline>
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
							<fo:table font-family="time" font-size="10pt" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="1"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>The restrictions on the expenditure of federal funds in appropriations acts are applicable to this subaward to the extent those restrictions are pertinent.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="2"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>32 CFR Part 32 or 32 CFR Part 33 as applicable.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="3"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>The Department of Defense Grant and Agreement Regulations, including addenda in effect as of the beginning date of the period of performance.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="4"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>Research Terms and Conditions found at </xsl:text>
																</fo:inline>
																<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf&apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf&apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf&apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf&apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text>&lt; http://www.nsf.gov/bfa/dias/policy/rtc/terms.pdf&gt;</xsl:text>
																	</fo:inline>
																</fo:basic-link>
																<fo:inline>
																	<xsl:text> and Agency Specific Requirements found at </xsl:text>
																</fo:inline>
																<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/aro_609.pdf&apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/aro_609.pdf&apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;nsf.gov/pubs/policydocs/rtc/aro_609.pdf&apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;nsf.gov/pubs/policydocs/rtc/aro_609.pdf&apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text>http://www.nsf.gov/pubs/policydocs/rtc/aro_609.pdf</xsl:text>
																	</fo:inline>
																</fo:basic-link>
																<fo:inline>
																	<xsl:text>, except for the following:</xsl:text>
																</fo:inline>
																<fo:inline-container>
																	<fo:block>
																		<xsl:text>&#x2029;</xsl:text>
																	</fo:block>
																</fo:inline-container>
																<fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="1"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>The right to initiate an automatic one-time extension of the end date provided by Article 25(c)(2) is replaced by the need to obtain prior written approval from the Prime Recipient;</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="2"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>The payment mechanism described in Article 22 and the financial reporting requirements in Article 52 of the Research Terms and Conditions and Article 22 of the Agency-Specific Requirements are replaced with Terms and Conditions (1) through (4) of this agreement; and</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="3"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>Any prior approvals are to be sought from the Prime Recipient and not the Federal Awarding Agency.</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																</fo:list-block>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="5"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Title to equipment costing $5,000 or more that is purchased or fabricated with research funds or Subrecipient cost sharing&#160;&#160;&#160; funds, as direct costs of the project or program, shall unconditionally vest in the Subrecipient upon acquisition without further obligation to the Federal Awarding Agency subject to the conditions specified in Article 34(a) of the Research Terms and Conditions</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
												</fo:list-block>
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
							<fo:table font-family="time" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="28%"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell font-family="times" font-size="12pt" font-weight="bold" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Special terms and conditions: </xsl:text>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell font-family="time" font-size="10pt" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>&#160;</xsl:text>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row font-family="time" font-size="10pt">
										<fo:table-cell font-family="times" font-size="12pt" font-weight="bold" number-columns-spanned="2" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:list-block font-family="time" font-size="10pt" font-weight="normal" provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="1"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Copyrights</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>Subrecipient </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
																		<fo:inline>
																			<xsl:text>grants</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
																		<fo:inline>
																			<xsl:text>shall grant</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text> to Prime Recipient an irrevocable, royalty-free, non-transferable, non-exclusive right and license to use, reproduce, make derivative works, display, and perform publicly any copyrights or copyrighted material (including any computer software and its documentation and/or databases) first developed and delivered under this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="2"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Data Rights</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>Subrecipient </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
																		<fo:inline>
																			<xsl:text>grants</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
																		<fo:inline>
																			<xsl:text>shall grant</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text> to Prime Recipient the right to use data created in the performance of this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award.</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>[Do not add a Patent or Inventions Clause. The prime award governs rights to patents and inventions. Prime Recipient cannot obtain rights in the Subrecipient''s subject inventions as a part of consideration for the subaward.&#160; Should it be necessary, the Federal Government can authorize the Prime Recipient''s right to practice a Subrecipients''s subject invention (as well as subject data or copyrights) on behalf of the Federal Government.]</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="3"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Automatic Carry Forward:&#160; </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;Y&apos;">
																		<fo:inline>
																			<xsl:text>Yes</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;N&apos;">
																		<fo:inline>
																			<xsl:text>No</xsl:text>
																		</fo:inline>
																		<fo:block/>
																		<fo:inline>
																			<xsl:text>Carry Forward requests must be sent to Prime Recipient''s - </xsl:text>
																		</fo:inline>
																		<xsl:for-each select="subcontract:SubContractData">
																			<xsl:for-each select="subcontract:SubcontractTemplateInfo">
																				<xsl:for-each select="subcontract:CarryForwardRequestsSentToDescription">
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
																		<fo:inline>
																			<xsl:text>, as shown in Attachment 3.</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text>&#160;&#160;&#160;&#160; </xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
												</fo:list-block>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
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
','ARO Attachment.xsl','application/octet-stream',1,SYS_GUID(),2);

delete from SUBAWARD_FORMS WHERE FORM_ID = 'FDP_ATT_3A';

INSERT INTO SUBAWARD_FORMS(FORM_ID,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,FORM,FILE_NAME,CONTENT_TYPE,VER_NBR,OBJ_ID,TEMPLATE_TYPE_CODE) values ('FDP_ATT_3A',	'FDP Attachment 3A',sysdate,'admin',
'<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:award="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/award" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:subcontract="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/subcontract" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output version="1.0" method="xml" encoding="UTF-8" indent="no"/>
    <xsl:param name="SV_OutputFormat" select="''PDF''"/>
    <xsl:variable name="XML" select="/"/>
    <xsl:variable name="fo:layout-master-set">
        <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.2in" margin-right="0.2in">
                <fo:region-body margin-top="0.1in" margin-bottom="0.6in"/>
                <fo:region-before extent="0.1in"/>
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
                            <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" margin-left="(100% - 90%) div 2" margin-right="(100% - 90%) div 2" width="90%" border-spacing=".25">
                                <fo:table-column column-width="proportional-column-width(1)"/>
                                <fo:table-column column-width="proportional-column-width(1)"/>
                                <fo:table-column column-width="proportional-column-width(1)"/>
                                <fo:table-column column-width="proportional-column-width(1)"/>
                                <fo:table-column column-width="proportional-column-width(1)"/>
                                <fo:table-column column-width="proportional-column-width(1)"/>
                                <fo:table-column column-width="proportional-column-width(1)"/>
                                <fo:table-column column-width="proportional-column-width(1)"/>
                                <fo:table-column column-width="proportional-column-width(1)"/>
                                <fo:table-body start-indent="0pt">
                                    <fo:table-row font-family="Arial">
                                        <fo:table-cell font-family="Arial" font-size="9pt" number-columns-spanned="9" padding="2pt" height="32" text-align="right" display-align="before">
                                            <fo:block>
                                                <fo:inline-container>
                                                    <fo:block>
                                                        <xsl:text>&#x2029;</xsl:text>
                                                    </fo:block>
                                                </fo:inline-container>
                                                <fo:block text-align="center" margin="0pt">
                                                    <fo:block>
                                                        <fo:inline>
                                                            <xsl:text>&#160;</xsl:text>
                                                        </fo:inline>
                                                        <fo:inline-container>
                                                            <fo:block>
                                                                <xsl:text>&#x2029;</xsl:text>
                                                            </fo:block>
                                                        </fo:inline-container>
                                                        <fo:block font-size="small" font-weight="bold" margin="0pt">
                                                            <fo:block>
                                                                <fo:inline>
                                                                    <xsl:text>Attachment 3A</xsl:text>
                                                                </fo:inline>
                                                            </fo:block>
                                                        </fo:block>
                                                    </fo:block>
                                                </fo:block>
                                                <fo:inline-container>
                                                    <fo:block>
                                                        <xsl:text>&#x2029;</xsl:text>
                                                    </fo:block>
                                                </fo:inline-container>
                                                <fo:block text-align="center" margin="0pt">
                                                    <fo:block>
                                                        <fo:inline-container>
                                                            <fo:block>
                                                                <xsl:text>&#x2029;</xsl:text>
                                                            </fo:block>
                                                        </fo:inline-container>
                                                        <fo:block font-size="small" font-weight="bold" margin="0pt">
                                                            <fo:block>
                                                                <fo:inline>
                                                                    <xsl:text>Research Subaward Agreement</xsl:text>
                                                                </fo:inline>
                                                            </fo:block>
                                                        </fo:block>
                                                    </fo:block>
                                                </fo:block>
                                                <fo:inline>
                                                    <xsl:text>Subaward Number:</xsl:text>
                                                </fo:inline>
                                                <xsl:for-each select="subcontract:SubContractData">
                                                    <xsl:for-each select="subcontract:SubcontractDetail">
                                                        <xsl:for-each select="subcontract:PONumber">
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
                                                <fo:block/>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell number-columns-spanned="9" padding="2pt" text-align="right" display-align="center">
                                            <fo:block>
                                                <fo:inline-container>
                                                    <fo:block>
                                                        <xsl:text>&#x2029;</xsl:text>
                                                    </fo:block>
                                                </fo:inline-container>
                                                <fo:table table-layout="fixed" width="100%" border="solid 1pt gray" border-spacing="-1pt">
                                                    <fo:table-column column-width="proportional-column-width(1)"/>
                                                    <fo:table-body start-indent="0pt">
                                                        <fo:table-row>
                                                            <fo:table-cell border="solid 1pt gray" padding="0" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:block text-align="center" margin="0pt">
                                                                        <fo:block>
                                                                            <fo:inline-container>
                                                                                <fo:block>
                                                                                    <xsl:text>&#x2029;</xsl:text>
                                                                                </fo:block>
                                                                            </fo:inline-container>
                                                                            <fo:block font-size="small" font-weight="bold" margin="0pt">
                                                                                <fo:block>
                                                                                    <fo:inline>
                                                                                        <xsl:text>Pass-through Entity Contacts</xsl:text>
                                                                                    </fo:inline>
                                                                                </fo:block>
                                                                            </fo:block>
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
                                    <fo:table-row>
                                        <fo:table-cell number-columns-spanned="9" padding="2pt" display-align="center">
                                            <fo:block>
                                                <fo:inline>
                                                    <xsl:text>Pass-through Entity </xsl:text>
                                                </fo:inline>
                                                <fo:inline-container>
                                                    <fo:block>
                                                        <xsl:text>&#x2029;</xsl:text>
                                                    </fo:block>
                                                </fo:inline-container>
                                                <fo:table table-layout="fixed" width="100%" border-spacing=".5">
                                                    <fo:table-column column-width="proportional-column-width(1)"/>

                                                    <fo:table-body start-indent="0pt">
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing="0.25">
                                                                        <fo:table-column column-width="8%"/>
                                                                        <fo:table-column column-width="80%"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Name:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
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
                                                                                                            </xsl:otherwise>
                                                                                                        </xsl:choose>
                                                                                                    </xsl:for-each>
                                                                                                </xsl:for-each>
                                                                                            </xsl:for-each>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="8%"/>
                                                                        <fo:table-column column-width="80%"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Address:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeRecipientContacts">
                                                                                                <xsl:for-each select="subcontract:OrgRolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Address1">
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
                                                                                        <fo:block/>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeRecipientContacts">
                                                                                                <xsl:for-each select="subcontract:OrgRolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Address2">
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
                                                                                        <fo:block/>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeRecipientContacts">
                                                                                                <xsl:for-each select="subcontract:OrgRolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Address3">
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
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="8%"/>
                                                                        <fo:table-column column-width="27%"/>
                                                                        <fo:table-column column-width="5%"/>
                                                                        <fo:table-column column-width="proportional-column-width(1)"/>
                                                                        <fo:table-column column-width="proportional-column-width(1)"/>
                                                                        <fo:table-column column-width="30%"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>City:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeRecipientContacts">
                                                                                                <xsl:for-each select="subcontract:OrgRolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:City">
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
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>State:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell number-columns-spanned="2" padding="2pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeRecipientContacts">
                                                                                                <xsl:for-each select="subcontract:OrgRolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:StateDescription">
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
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Zip Code: </xsl:text>
                                                                                        </fo:inline>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeRecipientContacts">
                                                                                                <xsl:for-each select="subcontract:OrgRolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Pincode">
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
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                                <fo:block text-align="center">
                                                    <fo:leader leader-pattern="rule" rule-thickness="1" leader-length="100%" color="black"/>
                                                </fo:block>
                                                <fo:inline>
                                                    <xsl:text>Pass-through Entity&apos;s Administrative Contact</xsl:text>
                                                </fo:inline>
                                                <fo:inline-container>
                                                    <fo:block>
                                                        <xsl:text>&#x2029;</xsl:text>
                                                    </fo:block>
                                                </fo:inline-container>
                                                <fo:table table-layout="fixed" width="100%" border-spacing="2pt">
                                                    <fo:table-column column-width="proportional-column-width(1)"/>
                                                    <fo:table-body start-indent="0pt">
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="8%"/>
                                                                        <fo:table-column column-width="80%"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Name:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAdministrativeContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:RolodexName">
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
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="8%"/>
                                                                        <fo:table-column column-width="80%"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Address:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAdministrativeContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Address1">
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
                                                                                        <fo:block/>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAdministrativeContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Address2">
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
                                                                                        <fo:block/>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAdministrativeContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Address3">
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
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="8%"/>
                                                                        <fo:table-column column-width="27%"/>
                                                                        <fo:table-column column-width="5%"/>
                                                                        <fo:table-column column-width="proportional-column-width(1)"/>
                                                                        <fo:table-column column-width="proportional-column-width(1)"/>
                                                                        <fo:table-column column-width="30%"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>City:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAdministrativeContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:City">
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
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>State:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell number-columns-spanned="2" padding="2pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAdministrativeContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:StateDescription">
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
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Zip Code: </xsl:text>
                                                                                        </fo:inline>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAdministrativeContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Pincode">
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
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="10%"/>
                                                                        <fo:table-column column-width="25%"/>
                                                                        <fo:table-column column-width="5%"/>
                                                                        <fo:table-column column-width="30%"/>
                                                                        <fo:table-column column-width="proportional-column-width(1)"/>
                                                                        <fo:table-column column-width="proportional-column-width(1)"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Telephone:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAdministrativeContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:PhoneNumber">
                                                                                                        <fo:inline>
                                                                                                            <xsl:text>&#160;</xsl:text>
                                                                                                        </fo:inline>
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
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Fax:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAdministrativeContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Fax">
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
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block/>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block/>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="60%"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Email: </xsl:text>
                                                                                        </fo:inline>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAdministrativeContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Email">
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
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                                <fo:block text-align="center">
                                                    <fo:leader leader-pattern="rule" rule-thickness="1" leader-length="100%" color="black"/>
                                                </fo:block>
                                                <fo:inline>
                                                    <xsl:text>Pass-through Entity&apos;s Principal Investigator</xsl:text>
                                                </fo:inline>
                                                <fo:inline-container>
                                                    <fo:block>
                                                        <xsl:text>&#x2029;</xsl:text>
                                                    </fo:block>
                                                </fo:inline-container>
                                                <fo:table table-layout="fixed" width="100%" border-spacing="2pt">
                                                    <fo:table-column column-width="proportional-column-width(1)"/>
                                                    <fo:table-body start-indent="0pt">
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="8%"/>
                                                                        <fo:table-column column-width="80%"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Name:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
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
                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="8%"/>
                                                                        <fo:table-column column-width="80%"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Address:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimePrincipalInvestigator">
                                                                                                <xsl:for-each select="subcontract:AddressLine1">
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
                                                                                        <fo:block/>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimePrincipalInvestigator">
                                                                                                <xsl:for-each select="subcontract:AddressLine2">
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
                                                                                        <fo:block/>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimePrincipalInvestigator">
                                                                                                <xsl:for-each select="subcontract:AddressLine3">
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
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="8%"/>
                                                                        <fo:table-column column-width="27%"/>
                                                                        <fo:table-column column-width="5%"/>
                                                                        <fo:table-column column-width="proportional-column-width(1)"/>
                                                                        <fo:table-column column-width="proportional-column-width(1)"/>
                                                                        <fo:table-column column-width="30%"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>City:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimePrincipalInvestigator">
                                                                                                <xsl:for-each select="subcontract:City">
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
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>State:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell number-columns-spanned="2" padding="2pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimePrincipalInvestigator">
                                                                                                <xsl:for-each select="subcontract:State">
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
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Zip Code: </xsl:text>
                                                                                        </fo:inline>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimePrincipalInvestigator">
                                                                                                <xsl:for-each select="subcontract:PostalCode">
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
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="10%"/>
                                                                        <fo:table-column column-width="25%"/>
                                                                        <fo:table-column column-width="5%"/>
                                                                        <fo:table-column column-width="30%"/>
                                                                        <fo:table-column column-width="proportional-column-width(1)"/>
                                                                        <fo:table-column column-width="proportional-column-width(1)"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Telephone:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimePrincipalInvestigator">
                                                                                                <xsl:for-each select="subcontract:MobilePhoneNumber">
                                                                                                    <fo:inline>
                                                                                                        <xsl:text>&#160;</xsl:text>
                                                                                                    </fo:inline>
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
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Fax:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimePrincipalInvestigator">
                                                                                                <xsl:for-each select="subcontract:FaxNumber">
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
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block/>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block/>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="60%"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Email: </xsl:text>
                                                                                        </fo:inline>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimePrincipalInvestigator">
                                                                                                <xsl:for-each select="subcontract:EmailAddress">
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
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                                <fo:block text-align="center">
                                                    <fo:leader leader-pattern="rule" rule-thickness="1" leader-length="100%" color="black"/>
                                                </fo:block>
                                                <fo:inline>
                                                    <xsl:text>Pass-through Entity&apos;s Financial Contact</xsl:text>
                                                </fo:inline>
                                                <fo:inline-container>
                                                    <fo:block>
                                                        <xsl:text>&#x2029;</xsl:text>
                                                    </fo:block>
                                                </fo:inline-container>
                                                <fo:table table-layout="fixed" width="100%" border-spacing="2pt">
                                                    <fo:table-column column-width="proportional-column-width(1)"/>
                                                    <fo:table-body start-indent="0pt">
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="8%"/>
                                                                        <fo:table-column column-width="80%"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Name:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeFinancialContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:RolodexName">
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
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="8%"/>
                                                                        <fo:table-column column-width="80%"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Address:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeFinancialContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Address1">
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
                                                                                        <fo:block/>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeFinancialContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Address2">
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
                                                                                        <fo:block/>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeFinancialContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Address3">
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
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="8%"/>
                                                                        <fo:table-column column-width="27%"/>
                                                                        <fo:table-column column-width="5%"/>
                                                                        <fo:table-column column-width="proportional-column-width(1)"/>
                                                                        <fo:table-column column-width="proportional-column-width(1)"/>
                                                                        <fo:table-column column-width="30%"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>City:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeFinancialContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:City">
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
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>State:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell number-columns-spanned="2" padding="2pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeFinancialContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:StateDescription">
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
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Zip Code: </xsl:text>
                                                                                        </fo:inline>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeFinancialContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Pincode">
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
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="10%"/>
                                                                        <fo:table-column column-width="25%"/>
                                                                        <fo:table-column column-width="5%"/>
                                                                        <fo:table-column column-width="30%"/>
                                                                        <fo:table-column column-width="proportional-column-width(1)"/>
                                                                        <fo:table-column column-width="proportional-column-width(1)"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Telephone:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeFinancialContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:PhoneNumber">
                                                                                                        <fo:inline>
                                                                                                            <xsl:text>&#160;</xsl:text>
                                                                                                        </fo:inline>
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
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Fax:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeFinancialContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Fax">
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
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block/>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block/>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="60%"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Email: </xsl:text>
                                                                                        </fo:inline>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeFinancialContact">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Email">
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
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>




                                                <fo:block text-align="center">
                                                    <fo:leader leader-pattern="rule" rule-thickness="1" leader-length="100%" color="black"/>
                                                </fo:block>
                                                <fo:inline>
                                                    <xsl:text>Pass-through Entity&apos;s Authorized Official</xsl:text>
                                                </fo:inline>
                                                <fo:inline-container>
                                                    <fo:block>
                                                        <xsl:text>&#x2029;</xsl:text>
                                                    </fo:block>
                                                </fo:inline-container>
                                                <fo:table table-layout="fixed" width="100%" border-spacing="2pt">
                                                    <fo:table-column column-width="proportional-column-width(1)"/>
                                                    <fo:table-body start-indent="0pt">
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="8%"/>
                                                                        <fo:table-column column-width="80%"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Name:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAuthorizedOfficial">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:RolodexName">
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
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="8%"/>
                                                                        <fo:table-column column-width="80%"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Address:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAuthorizedOfficial">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Address1">
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
                                                                                        <fo:block/>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAuthorizedOfficial">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Address2">
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
                                                                                        <fo:block/>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAuthorizedOfficial">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Address3">
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
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="8%"/>
                                                                        <fo:table-column column-width="27%"/>
                                                                        <fo:table-column column-width="5%"/>
                                                                        <fo:table-column column-width="proportional-column-width(1)"/>
                                                                        <fo:table-column column-width="proportional-column-width(1)"/>
                                                                        <fo:table-column column-width="30%"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>City:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAuthorizedOfficial">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:City">
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
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>State:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell number-columns-spanned="2" padding="2pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAuthorizedOfficial">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:StateDescription">
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
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Zip Code: </xsl:text>
                                                                                        </fo:inline>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAuthorizedOfficial">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Pincode">
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
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="10%"/>
                                                                        <fo:table-column column-width="25%"/>
                                                                        <fo:table-column column-width="5%"/>
                                                                        <fo:table-column column-width="30%"/>
                                                                        <fo:table-column column-width="proportional-column-width(1)"/>
                                                                        <fo:table-column column-width="proportional-column-width(1)"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Telephone:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAuthorizedOfficial">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:PhoneNumber">
                                                                                                        <fo:inline>
                                                                                                            <xsl:text>&#160;</xsl:text>
                                                                                                        </fo:inline>
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
                                                                                <fo:table-cell padding="2pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Fax:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" text-align="left" display-align="center">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAuthorizedOfficial">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Fax">
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
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block/>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block/>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell padding="1.5pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing=".25">
                                                                        <fo:table-column column-width="60%"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="1.5pt" display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline>
                                                                                            <xsl:text>Email: </xsl:text>
                                                                                        </fo:inline>
                                                                                        <xsl:for-each select="subcontract:SubContractData">
                                                                                            <xsl:for-each select="subcontract:PrimeAuthorizedOfficial">
                                                                                                <xsl:for-each select="subcontract:RolodexDetails">
                                                                                                    <xsl:for-each select="subcontract:Email">
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
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                                <fo:block text-align="center">
                                                    <fo:leader leader-pattern="rule" rule-thickness="0.5" leader-length="100%" color="black"/>
                                                </fo:block>
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
            <fo:block/>
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
','FDP Attachment 3A.xsl','application/octet-stream',1,SYS_GUID(),3);

delete from SUBAWARD_FORMS WHERE FORM_ID = 'FDP_ATT_3B';

INSERT INTO SUBAWARD_FORMS(FORM_ID,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,FORM,FILE_NAME,CONTENT_TYPE,VER_NBR,OBJ_ID,TEMPLATE_TYPE_CODE) values ('FDP_ATT_3B',	'FDP Attachment 3B',sysdate,'admin',
'<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:award="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/award" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:subcontract="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/subcontract" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:output version="1.0" method="xml" encoding="UTF-8" indent="no"/>
	<xsl:param name="SV_OutputFormat" select="''PDF''"/>
	<xsl:variable name="XML" select="/"/>
	<xsl:variable name="fo:layout-master-set">
		<fo:layout-master-set>
			<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.2in" margin-right="0.2in">
				<fo:region-body margin-top="0.1in" margin-bottom="0.79in"/>
				<fo:region-before extent="0.1in"/>
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
							<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="90%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-family="Arial">
										<fo:table-cell font-family="Arial" font-size="9pt" number-columns-spanned="9" padding="2pt" height="32" text-align="right" display-align="before">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:block text-align="center" margin="0pt">
													<fo:block>
														<fo:inline>
															<xsl:text>&#160;</xsl:text>
														</fo:inline>
														<fo:inline-container>
															<fo:block>
																<xsl:text>&#x2029;</xsl:text>
															</fo:block>
														</fo:inline-container>
														<fo:block font-size="small" font-weight="bold" margin="0pt">
															<fo:block>
																<fo:inline>
																	<xsl:text>Attachment 3B - Research Subaward Agreement</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:block>
													</fo:block>
												</fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:block text-align="center" margin="0pt">
													<fo:block>
														<fo:inline-container>
															<fo:block>
																<xsl:text>&#x2029;</xsl:text>
															</fo:block>

														</fo:inline-container>
														<fo:block font-size="small" font-weight="bold" margin="0pt">
															<fo:block>
																<fo:inline>
																	<xsl:text>Subrecipient Contacts</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:block>
													</fo:block>
												</fo:block>
												<fo:inline>
													<xsl:text>Subaward Number:</xsl:text>
												</fo:inline>
												<xsl:for-each select="subcontract:SubContractData">
													<xsl:for-each select="subcontract:SubcontractDetail">
														<xsl:for-each select="subcontract:PONumber">
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
									<fo:table-row>
										<fo:table-cell number-columns-spanned="9" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Institution/Organization (&quot;Subrecipient&quot;) </xsl:text>
												</fo:inline>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing="2pt">
													<fo:table-column column-width="8%"/>
													<fo:table-column column-width="30%"/>
													<fo:table-column column-width="5%"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="15%"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="25%"/>
													<fo:table-body start-indent="0pt">
														<fo:table-row>
															<fo:table-cell number-columns-spanned="13" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Name:&#160; </xsl:text>
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
														<fo:table-row>
															<fo:table-cell number-columns-spanned="13" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Address: </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:SubcontractDetail">
																			<xsl:for-each select="subcontract:SubcontractorOrgRolodexDetails">
																				<xsl:for-each select="subcontract:Address1">
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
																	<fo:block/>
																	<fo:inline>
																		<xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:SubcontractDetail">
																			<xsl:for-each select="subcontract:SubcontractorOrgRolodexDetails">
																				<xsl:for-each select="subcontract:Address2">
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
																	<fo:inline>
																		<xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>City:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:SubcontractDetail">
																			<xsl:for-each select="subcontract:SubcontractorOrgRolodexDetails">
																				<xsl:for-each select="subcontract:City">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>State:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="9" padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:SubcontractDetail">
																			<xsl:for-each select="subcontract:SubcontractorOrgRolodexDetails">
																				<xsl:for-each select="subcontract:StateDescription">
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
															<fo:table-cell font-family="Arial" font-size="9pt" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Zip Code +4: </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:SubcontractDetail">
																			<xsl:for-each select="subcontract:SubcontractorOrgRolodexDetails">
																				<xsl:for-each select="subcontract:Pincode">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>EIN No.:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:SubcontractDetail">
																			<xsl:for-each select="subcontract:SubcontractorDetails">
																				<xsl:for-each select="subcontract:FedralEmployerId">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block/>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="3" padding="2pt" text-align="left" display-align="center">
																<fo:block/>
															</fo:table-cell>
															<fo:table-cell font-family="Arial" font-size="9pt" padding="2pt" display-align="center">
																<fo:block/>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="6" padding="2pt" text-align="left" display-align="center">
																<fo:block/>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing="2pt">
													<fo:table-column column-width="60%"/>
													<fo:table-column column-width="20%"/>
													<fo:table-column column-width="20%"/>
													<fo:table-body start-indent="0pt">
														<fo:table-row>
															<fo:table-cell number-columns-spanned="3" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Is the Performance Site the Same Address as Above? </xsl:text>
																	</fo:inline>
																	<xsl:choose>
																		<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:PerfSiteDiffFromOrgAddr = &quot;Y&quot;">
																			<fo:inline>
																				<xsl:text>Yes</xsl:text>
																			</fo:inline>
																		</xsl:when>
																		<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:PerfSiteDiffFromOrgAddr = &quot;N&quot;">
																			<fo:inline>
																				<xsl:text>No</xsl:text>
																			</fo:inline>
																		</xsl:when>
																	</xsl:choose>
																	<fo:block/>
																	<fo:inline>
																		<xsl:text>If no, is the Performance Site the same as PI address below? </xsl:text>
																	</fo:inline>
																	<xsl:choose>
																		<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:PerfSiteSameAsSubPiAddr = &quot;Y&quot;">
																			<fo:inline>
																				<xsl:text>Yes</xsl:text>
																			</fo:inline>
																		</xsl:when>
																		<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:PerfSiteSameAsSubPiAddr = &quot;N&quot;">
																			<fo:inline>
																				<xsl:text>No</xsl:text>
																			</fo:inline>
																		</xsl:when>
																	</xsl:choose>
																	<fo:block/>
																	<fo:inline>
																		<xsl:text>If no to both questions, please complete 3B page 2 (if ARRA funding use Attachment 4A).</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Subrecipient currently registered in CCR? </xsl:text>
																	</fo:inline>
																	<xsl:choose>
																		<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:SubRegisteredInCcr = &quot;Y&quot;">
																			<fo:inline>
																				<xsl:text>Yes</xsl:text>
																			</fo:inline>
																		</xsl:when>
																		<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:SubRegisteredInCcr = &quot;N&quot;">
																			<fo:inline>
																				<xsl:text>No</xsl:text>
																			</fo:inline>
																		</xsl:when>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>DUNS No.:</xsl:text>
																	</fo:inline>
																	<fo:block/>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:SubcontractDetail">
																			<xsl:for-each select="subcontract:SubcontractorDetails">
																				<xsl:for-each select="subcontract:DunsNumber">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Parent DUNS No.:</xsl:text>
																	</fo:inline>
																	<fo:block/>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:SubcontractTemplateInfo">
																			<xsl:for-each select="subcontract:ParentDunsNumber">
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
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Is Subrecipient exempt from reporting compensation? </xsl:text>
																	</fo:inline>
																	<xsl:choose>
																		<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:ExemptFromRprtgExecComp = &quot;Y&quot;">
																			<fo:inline>
																				<xsl:text>Yes</xsl:text>
																			</fo:inline>
																		</xsl:when>
																		<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:ExemptFromRprtgExecComp = &quot;N&quot;">
																			<fo:inline>
																				<xsl:text>No</xsl:text>
																			</fo:inline>
																		</xsl:when>
																	</xsl:choose>
																	<fo:block/>
																	<fo:inline>
																		<xsl:text>If no , please complete 3B page 2 (if ARRA funding use Attachment 4A).</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Congressional District:</xsl:text>
																	</fo:inline>
																	<fo:block/>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:SubcontractDetail">
																			<xsl:for-each select="subcontract:SubcontractorDetails">
																				<xsl:for-each select="subcontract:CongressionalDistrict">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Congressional District:</xsl:text>
																	</fo:inline>
																	<fo:block/>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:SubcontractTemplateInfo">
																			<xsl:for-each select="subcontract:ParentCongressionalDistrict">
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
													</fo:table-body>
												</fo:table>
												<fo:block text-align="center">
													<fo:leader leader-pattern="rule" rule-thickness="1" leader-length="100%" color="black"/>
												</fo:block>
												<fo:block/>
												<fo:inline>
													<xsl:text>Administrative Contact</xsl:text>
												</fo:inline>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing="2pt">
													<fo:table-column column-width="8%"/>
													<fo:table-column column-width="30%"/>
													<fo:table-column column-width="5%"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="25%"/>
													<fo:table-body start-indent="0pt">
														<fo:table-row>
															<fo:table-cell number-columns-spanned="13" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Name: </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:AdministrativeContact">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:RolodexName">
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
															<fo:table-cell number-columns-spanned="13" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Address: </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:AdministrativeContact">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:Address1">
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
																	<fo:block/>
																	<fo:inline>
																		<xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:AdministrativeContact">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:Address2">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>City:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:AdministrativeContact">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:City">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>State:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="9" padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:AdministrativeContact">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:StateDescription">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Zip Code: </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:AdministrativeContact">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:Pincode">
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
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Telephone:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:AdministrativeContact">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:PhoneNumber">
																					<fo:inline>
																						<xsl:text>&#160;</xsl:text>
																					</fo:inline>
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
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Fax:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="4" padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:AdministrativeContact">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:Fax">
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
															<fo:table-cell number-columns-spanned="6" padding="2pt" text-align="left" display-align="center">
																<fo:block/>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell number-columns-spanned="12" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Email: </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:AdministrativeContact">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:Email">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block/>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
												<fo:block text-align="center">
													<fo:leader leader-pattern="rule" rule-thickness="1" leader-length="100%" color="black"/>
												</fo:block>
												<fo:block/>
												<fo:inline>
													<xsl:text>Principal Investigator</xsl:text>
												</fo:inline>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing="2pt">
													<fo:table-column column-width="8%"/>
													<fo:table-column column-width="30%"/>
													<fo:table-column column-width="5%"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="25%"/>
													<fo:table-body start-indent="0pt">
														<fo:table-row>
															<fo:table-cell number-columns-spanned="13" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Name: </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:SubcontractDetail">
																			<xsl:for-each select="subcontract:SiteInvestigatorDetails">
																				<xsl:for-each select="subcontract:RolodexName">
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
															<fo:table-cell number-columns-spanned="13" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Address: </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:SubcontractDetail">
																			<xsl:for-each select="subcontract:SiteInvestigatorDetails">
																				<xsl:for-each select="subcontract:Address1">
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
																	<fo:block/>
																	<fo:inline>
																		<xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:SubcontractDetail">
																			<xsl:for-each select="subcontract:SiteInvestigatorDetails">
																				<xsl:for-each select="subcontract:Address2">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>City:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:SubcontractDetail">
																			<xsl:for-each select="subcontract:SiteInvestigatorDetails">
																				<xsl:for-each select="subcontract:City">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>State:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="9" padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:SubcontractDetail">
																			<xsl:for-each select="subcontract:SiteInvestigatorDetails">
																				<xsl:for-each select="subcontract:StateDescription">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Zip Code: </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:SubcontractDetail">
																			<xsl:for-each select="subcontract:SiteInvestigatorDetails">
																				<xsl:for-each select="subcontract:Pincode">
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
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Telephone:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:SubcontractDetail">
																			<xsl:for-each select="subcontract:SiteInvestigatorDetails">
																				<xsl:for-each select="subcontract:PhoneNumber">
																					<fo:inline>
																						<xsl:text>&#160;</xsl:text>
																					</fo:inline>
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
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Fax:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="4" padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:SubcontractDetail">
																			<xsl:for-each select="subcontract:SiteInvestigatorDetails">
																				<xsl:for-each select="subcontract:Fax">
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
															<fo:table-cell number-columns-spanned="6" padding="2pt" text-align="left" display-align="center">
																<fo:block/>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell number-columns-spanned="12" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Email: </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:SubcontractDetail">
																			<xsl:for-each select="subcontract:SiteInvestigatorDetails">
																				<xsl:for-each select="subcontract:Email">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block/>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
												<fo:block text-align="center">
													<fo:leader leader-pattern="rule" rule-thickness="1" leader-length="100%" color="black"/>
												</fo:block>
												<fo:block/>
												<fo:inline>
													<xsl:text>Financial Contact</xsl:text>
												</fo:inline>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing="2pt">
													<fo:table-column column-width="8%"/>
													<fo:table-column column-width="30%"/>
													<fo:table-column column-width="5%"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="25%"/>
													<fo:table-body start-indent="0pt">
														<fo:table-row>
															<fo:table-cell number-columns-spanned="13" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Name: </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:FinancialContact">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:RolodexName">
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
															<fo:table-cell number-columns-spanned="13" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Address: </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:FinancialContact">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:Address1">
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
																	<fo:block/>
																	<fo:inline>
																		<xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:FinancialContact">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:Address2">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>City:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:FinancialContact">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:City">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>State:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="9" padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:FinancialContact">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:StateDescription">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Zip Code: </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:FinancialContact">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:Pincode">
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
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Telephone: </xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:FinancialContact">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:PhoneNumber">
																					<fo:inline>
																						<xsl:text>&#160;</xsl:text>
																					</fo:inline>
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
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Fax:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="4" padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:FinancialContact">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:Fax">
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
															<fo:table-cell number-columns-spanned="6" padding="2pt" text-align="left" display-align="center">
																<fo:block/>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell number-columns-spanned="12" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Email: </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:FinancialContact">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:Email">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block/>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
												<fo:block text-align="center">
													<fo:leader leader-pattern="rule" rule-thickness="1" leader-length="100%" color="black"/>
												</fo:block>
												<fo:inline>
													<xsl:text>Authorized Official</xsl:text>
												</fo:inline>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing="2pt">
													<fo:table-column column-width="8%"/>
													<fo:table-column column-width="30%"/>
													<fo:table-column column-width="5%"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="25%"/>
													<fo:table-body start-indent="0pt">
														<fo:table-row>
															<fo:table-cell number-columns-spanned="13" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Name: </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:AuthorizedOfficial">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:RolodexName">
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
															<fo:table-cell number-columns-spanned="13" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Address: </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:AuthorizedOfficial">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:Address1">
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
																	<fo:block/>
																	<fo:inline>
																		<xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:AuthorizedOfficial">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:Address2">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>City:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:AuthorizedOfficial">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:City">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>State:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="9" padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:AuthorizedOfficial">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:StateDescription">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Zip Code: </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:AuthorizedOfficial">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:Pincode">
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
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Telephone:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:AuthorizedOfficial">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:PhoneNumber">
																					<fo:inline>
																						<xsl:text>&#160;</xsl:text>
																					</fo:inline>
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
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Fax:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="4" padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:AuthorizedOfficial">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:Fax">
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
															<fo:table-cell number-columns-spanned="6" padding="2pt" text-align="left" display-align="center">
																<fo:block/>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell number-columns-spanned="12" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Email: </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="subcontract:SubContractData">
																		<xsl:for-each select="subcontract:AuthorizedOfficial">
																			<xsl:for-each select="subcontract:RolodexDetails">
																				<xsl:for-each select="subcontract:Email">
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block/>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
												<fo:block text-align="center">
													<fo:leader leader-pattern="rule" rule-thickness="1" leader-length="100%" color="black"/>
												</fo:block>
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
			<fo:block/>
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

','FDP Attachment 3B.xsl','application/octet-stream',1,SYS_GUID(),3);

delete from SUBAWARD_FORMS WHERE FORM_ID = 'FDP_ATT_3B_2';

INSERT INTO SUBAWARD_FORMS(FORM_ID,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,FORM,FILE_NAME,CONTENT_TYPE,VER_NBR,OBJ_ID,TEMPLATE_TYPE_CODE) values ('FDP_ATT_3B_2',	'FDP Attachment 3BPage2',sysdate,'admin',
'<?xml version="1.0" encoding="UTF-8"?>
<!--Designed and generated by Altova StyleVision Enterprise Edition 2008 rel. 2 - see http://www.altova.com/stylevision for more information.-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:award="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/award" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:subcontract="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/subcontract" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:fo="http://www.w3.org/1999/XSL/Format">
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
							<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="90%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-family="Arial">
										<fo:table-cell font-family="Arial" number-columns-spanned="9" padding="2pt" height="32" text-align="right" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:block text-align="center" margin="0pt">
													<fo:block>
														<fo:inline-container>
															<fo:block>
																<xsl:text>&#x2029;</xsl:text>
															</fo:block>
														</fo:inline-container>
														<fo:block font-size="small" font-weight="bold" margin="0pt">
															<fo:block>
																<fo:inline>
																	<xsl:text>Attachment 3B - Research Subaward Agreement</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:block>
													</fo:block>
												</fo:block>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell number-columns-spanned="9" padding="2pt" text-align="right" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:block text-align="center" margin="0pt">
													<fo:block>
														<fo:inline-container>
															<fo:block>
																<xsl:text>&#x2029;</xsl:text>
															</fo:block>
														</fo:inline-container>
														<fo:block font-size="small" font-weight="bold" margin="0pt">
															<fo:block>
																<fo:inline>
																	<xsl:text>Page 2 - Place of Performance &amp; Highest Compensated Officers</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:block>
													</fo:block>
												</fo:block>
												<fo:inline>
													<xsl:text>Subaward Number: </xsl:text>
												</fo:inline>
												<xsl:for-each select="subcontract:SubContractData">
													<xsl:for-each select="subcontract:SubcontractDetail">
														<xsl:for-each select="subcontract:PONumber">
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
									<fo:table-row>
										<fo:table-cell number-columns-spanned="9" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Institution/Organization (&quot;Subrecipient&quot;) </xsl:text>
												</fo:inline>
												<fo:block/>
												<fo:inline>
													<xsl:text>Name:&#160; </xsl:text>
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
												<fo:block/>
												<fo:block text-align="center">
													<fo:leader leader-pattern="rule" rule-thickness="1" leader-length="100%" color="black"/>
												</fo:block>
												<fo:inline>
													<xsl:text>Place of Performance</xsl:text>
												</fo:inline>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing="2pt">
													<fo:table-column column-width="8%"/>
													<fo:table-column column-width="30%"/>
													<fo:table-column column-width="5%"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="12%"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-column column-width="proportional-column-width(1)"/>
													<fo:table-body start-indent="0pt">
														<fo:table-row>
															<fo:table-cell number-columns-spanned="13" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Name:&#160;&#160;&#160;&#160; </xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell number-columns-spanned="13" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Address: </xsl:text>
																	</fo:inline>
																	<fo:block/>
																	<fo:inline>
																		<xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>City:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block/>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>State:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="3" padding="2pt" text-align="left" display-align="center">
																<fo:block/>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="7" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Zip Code +4:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Telephone: </xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block/>
															</fo:table-cell>
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Fax:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="3" padding="2pt" text-align="left" display-align="center">
																<fo:block/>
															</fo:table-cell>
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block/>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="6" padding="2pt" text-align="left" display-align="center">
																<fo:block/>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Email: </xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block/>
															</fo:table-cell>
															<fo:table-cell padding="2pt" text-align="left" display-align="center">
																<fo:block/>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="3" padding="2pt" text-align="left" display-align="center">
																<fo:block/>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="7" padding="2pt" text-align="left" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Congressional District:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
												<fo:block text-align="center">
													<fo:leader leader-pattern="rule" rule-thickness="1" leader-length="100%" color="black"/>
												</fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing="1.5">
													<fo:table-column column-width="100%"/>
													<fo:table-body start-indent="0pt">
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>The names and total compensation of the five most highly compensated officers of the entity(ies) must be listed if--</xsl:text>
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
																	<fo:inline>
																		<xsl:text> (i) the entity in the preceding fiscal year received-</xsl:text>
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
																	<fo:inline>
																		<xsl:text>&#160;&#160;&#160;&#160; (I) 80 percent or more of its annual gross revenues in Federal awards (federal contracts (and subcontracts), loans, grants (and subgrants) and cooperative agreements); AND</xsl:text>
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
																		<fo:block/>
																	</fo:block>
																	<fo:inline>
																		<xsl:text>&#160;&#160;&#160;&#160; (II) $25,000,000 or more in annual gross revenues from Federal awards; and </xsl:text>
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
																		<fo:block/>
																	</fo:block>
																	<fo:inline>
																		<xsl:text>(ii) the public does not have access to information about the compensation of the senior executives of the entity through periodic reports filed under section 13(a) or 15(d) of the Securities Exchange Act of 1934 (15 U.S.C. 78m(a), 78o(d)) or section 6104 of the Internal Revenue Code of 1986.</xsl:text>
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
																		<fo:block/>
																	</fo:block>
																	<fo:inline>
																		<xsl:text>Is subaward entity exempt from reporting executive compensation? </xsl:text>
																	</fo:inline>
																	<xsl:choose>
																		<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:ExemptFromRprtgExecComp = &quot;Y&quot;">
																			<fo:inline>
																				<xsl:text>Yes</xsl:text>
																			</fo:inline>
																		</xsl:when>
																		<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:ExemptFromRprtgExecComp = &quot;N&quot;">
																			<fo:inline>
																				<xsl:text>No</xsl:text>
																			</fo:inline>
																		</xsl:when>
																	</xsl:choose>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<xsl:if test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:ExemptFromRprtgExecComp = &quot;N&quot;">
																		<fo:block/>
																		<fo:inline>
																			<xsl:text>Complete the information below.</xsl:text>
																		</fo:inline>
																		<fo:inline-container>
																			<fo:block>
																				<xsl:text>&#x2029;</xsl:text>
																			</fo:block>
																		</fo:inline-container>
																		<fo:table font-family="Arial" font-size="9pt" table-layout="fixed" width="100%" border-spacing="2pt">
																			<fo:table-column column-width="proportional-column-width(1)"/>
																			<fo:table-column column-width="proportional-column-width(1)"/>
																			<fo:table-body start-indent="0pt">
																				<fo:table-row>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block>
																							<fo:inline>
																								<xsl:text>Officer 1 Name</xsl:text>
																							</fo:inline>
																						</fo:block>
																					</fo:table-cell>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block/>
																					</fo:table-cell>
																				</fo:table-row>
																				<fo:table-row>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block>
																							<fo:inline>
																								<xsl:text>Officer 1 Compensation</xsl:text>
																							</fo:inline>
																						</fo:block>
																					</fo:table-cell>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block/>
																					</fo:table-cell>
																				</fo:table-row>
																				<fo:table-row>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block>
																							<fo:inline>
																								<xsl:text>Officer 2 Name</xsl:text>
																							</fo:inline>
																						</fo:block>
																					</fo:table-cell>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block/>
																					</fo:table-cell>
																				</fo:table-row>
																				<fo:table-row>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block>
																							<fo:inline>
																								<xsl:text>Officer 2 Compensation</xsl:text>
																							</fo:inline>
																						</fo:block>
																					</fo:table-cell>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block/>
																					</fo:table-cell>
																				</fo:table-row>
																				<fo:table-row>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block>
																							<fo:inline>
																								<xsl:text>Officer 3 Name</xsl:text>
																							</fo:inline>
																						</fo:block>
																					</fo:table-cell>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block/>
																					</fo:table-cell>
																				</fo:table-row>
																				<fo:table-row>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block>
																							<fo:inline>
																								<xsl:text>Officer 3 Compensation</xsl:text>
																							</fo:inline>
																						</fo:block>
																					</fo:table-cell>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block/>
																					</fo:table-cell>
																				</fo:table-row>
																				<fo:table-row>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block>
																							<fo:inline>
																								<xsl:text>Officer 4 Name</xsl:text>
																							</fo:inline>
																						</fo:block>
																					</fo:table-cell>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block/>
																					</fo:table-cell>
																				</fo:table-row>
																				<fo:table-row>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block>
																							<fo:inline>
																								<xsl:text>Officer 4 Compensation</xsl:text>
																							</fo:inline>
																						</fo:block>
																					</fo:table-cell>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block/>
																					</fo:table-cell>
																				</fo:table-row>
																				<fo:table-row>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block>
																							<fo:inline>
																								<xsl:text>Officer 5 Name</xsl:text>
																							</fo:inline>
																						</fo:block>
																					</fo:table-cell>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block/>
																					</fo:table-cell>
																				</fo:table-row>
																				<fo:table-row>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block>
																							<fo:inline>
																								<xsl:text>Officer 5 Compensation</xsl:text>
																							</fo:inline>
																						</fo:block>
																					</fo:table-cell>
																					<fo:table-cell padding="2pt" display-align="center">
																						<fo:block/>
																					</fo:table-cell>
																				</fo:table-row>
																			</fo:table-body>
																		</fo:table>
																	</xsl:if>
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
						</xsl:for-each>
					</fo:block>
					<fo:block id="SV_RefID_PageTotal"/>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	<xsl:template name="headerall">
		<fo:static-content flow-name="xsl-region-before">
			<fo:block/>
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

','FDP Attachment 3BPage2.xsl','application/octet-stream',1,SYS_GUID(),3);

delete from SUBAWARD_FORMS WHERE FORM_ID = 'FDP_DOE';

INSERT INTO SUBAWARD_FORMS(FORM_ID,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,FORM,FILE_NAME,CONTENT_TYPE,VER_NBR,OBJ_ID,TEMPLATE_TYPE_CODE) values ('FDP_DOE',	'DOE Attachment',sysdate,'admin',
'<?xml version="1.0" encoding="UTF-8"?>
<!--Designed and generated by Altova StyleVision Enterprise Edition 2008 rel. 2 - see http://www.altova.com/stylevision for more information.-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:award="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/award" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:subcontract="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/subcontract" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:fo="http://www.w3.org/1999/XSL/Format">
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
							<fo:table font-family="Times New Roman" font-size="12pt" font-weight="bold" table-layout="fixed" width="100%" border="solid 1pt gray" border-spacing="-1pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell border="solid 1pt gray" padding="0" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:block text-align="center" margin="0pt">
													<fo:block>
														<fo:inline>
															<xsl:text>Attachment 2</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>Research Subaward Agreement</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>Prime Award Terms and Conditions</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>DOE</xsl:text>
														</fo:inline>
													</fo:block>
												</fo:block>
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
							<fo:table font-family="Times New Roman" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-size="12pt" font-weight="bold">
										<fo:table-cell padding="0" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Certifications/Assurances</xsl:text>
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row font-size="10pt" font-weight="normal">
										<fo:table-cell padding="0" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text> By signing this Research Subaward Agreement Subrecipient makes the certifications and assurances specified in the Research Terms and Conditions Appendix C found at </xsl:text>
												</fo:inline>
												<fo:basic-link text-decoration="underline" color="blue">
													<xsl:choose>
														<xsl:when test="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf  &apos;), 1, 1) = ''#''">
															<xsl:attribute name="internal-destination">
																<xsl:value-of select="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf  &apos;), 2)"/>
															</xsl:attribute>
														</xsl:when>
														<xsl:otherwise>
															<xsl:attribute name="external-destination">
																<xsl:text>url(</xsl:text>
																<xsl:call-template name="double-backslash">
																	<xsl:with-param name="text">
																		<xsl:value-of select="string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf  &apos;)"/>
																	</xsl:with-param>
																	<xsl:with-param name="text-length">
																		<xsl:value-of select="string-length(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf  &apos;))"/>
																	</xsl:with-param>
																</xsl:call-template>
																<xsl:text>)</xsl:text>
															</xsl:attribute>
														</xsl:otherwise>
													</xsl:choose>
													<fo:inline>
														<xsl:text>http://www.nsf.gov/bfa/dias/policy/rtc/appc.pdf&#160;&#160; </xsl:text>
													</fo:inline>
												</fo:basic-link>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
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
							<fo:inline-container>
								<fo:block>
									<xsl:text>&#x2029;</xsl:text>
								</fo:block>
							</fo:inline-container>
							<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-family="Times New Roman" font-size="12pt" font-weight="bold">
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>General terms and conditions:</xsl:text>
												</fo:inline>
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
							<fo:table font-family="time" font-size="10pt" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="1"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>The restrictions on the expenditure of federal funds in appropriations acts are applicable to this subaward to the extent those restrictions are pertinent.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="2"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>Applicable program regulations </xsl:text>
																</fo:inline>
																<xsl:for-each select="subcontract:SubContractData">
																	<xsl:for-each select="subcontract:SubcontractTemplateInfo">
																		<xsl:for-each select="subcontract:ApplicableProgramRegulations">
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
																<fo:inline>
																	<xsl:text> dated </xsl:text>
																</fo:inline>
																<xsl:for-each select="subcontract:SubContractData">
																	<xsl:for-each select="subcontract:SubcontractTemplateInfo">
																		<xsl:for-each select="subcontract:ApplicableProgramRegsDate">
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
																<fo:inline>
																	<xsl:text>.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="3"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>DOE Financial Assistance Rules, 10 CFR Part 600, as amended.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="4"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>Research Terms and Conditions found at </xsl:text>
																</fo:inline>
																<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf&apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf&apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf&apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf&apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text>http://www.nsf.gov/bfa/dias/policy/rtc/terms.pdf</xsl:text>
																	</fo:inline>
																</fo:basic-link>
																<fo:inline>
																	<xsl:text> and Agency Specific Requirements found at </xsl:text>
																</fo:inline>
																<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/doe_708.pdf&apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/doe_708.pdf&apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;nsf.gov/pubs/policydocs/rtc/doe_708.pdf&apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;nsf.gov/pubs/policydocs/rtc/doe_708.pdf&apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text>http://www.nsf.gov/pubs/policydocs/rtc/doe_708.pdf</xsl:text>
																	</fo:inline>
																</fo:basic-link>
																<fo:inline>
																	<xsl:text>, except for the following:</xsl:text>
																</fo:inline>
																<fo:inline-container>
																	<fo:block>
																		<xsl:text>&#x2029;</xsl:text>
																	</fo:block>
																</fo:inline-container>
																<fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="1"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>The right to initiate an automatic one-time extension of the end date provided by Article 25(c)(2) is replaced by the need to obtain prior written approval from the Prime Recipient;</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="2"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>The payment mechanism described in Article 22 and the financial reporting requirements in Article 52 of the Research Terms and Conditions and Article 10 of the Agency-Specific Requirements are replaced with Terms and Conditions (1) through (4) of this agreement; and</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="3"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>Any prior approvals are to be sought from the Prime Recipient and not the Federal Awarding Agency.</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																</fo:list-block>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="5"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Title to equipment costing $5,000 or more that is purchased or fabricated with research funds or Subrecipient cost sharing funds, as direct costs of the project or program, shall unconditionally vest in the Subrecipient upon acquisition without further obligation to the Federal Awarding Agency subject to the conditions specified in Article 34(a) of the Research Terms and Conditions.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
												</fo:list-block>
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
							<fo:table font-family="time" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="28%"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell font-family="times" font-size="12pt" font-weight="bold" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Special terms and conditions: </xsl:text>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell font-family="time" font-size="10pt" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>&#160;</xsl:text>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row font-family="time" font-size="10pt">
										<fo:table-cell font-family="times" font-size="12pt" font-weight="bold" number-columns-spanned="2" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:list-block font-family="time" font-size="10pt" font-weight="normal" provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="1"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Copyrights</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>Subrecipient </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
																		<fo:inline>
																			<xsl:text>grants</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
																		<fo:inline>
																			<xsl:text>shall grant</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text> to Prime Recipient an irrevocable, royalty-free, non-transferable, non-exclusive right and license to use, reproduce, make derivative works, display, and perform publicly any copyrights or copyrighted material (including any computer software and its documentation and/or databases) first developed and delivered under this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="2"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Data Rights</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>Subrecipient </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
																		<fo:inline>
																			<xsl:text>grants</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
																		<fo:inline>
																			<xsl:text>shall grant</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text> to Prime Recipient the right to use data created in the performance of this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award.</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>[Do not add a Patent or Inventions Clause. The prime award governs rights to patents and inventions. Prime Recipient cannot obtain rights in the Subrecipient''s subject inventions as a part of consideration for the subaward.&#160; Should it be necessary, the Federal Government can authorize the Prime Recipient''s right to practice a Subrecipients''s subject invention (as well as subject data or copyrights) on behalf of the Federal Government.]</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="3"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Automatic Carry Forward:&#160; </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;Y&apos;">
																		<fo:inline>
																			<xsl:text>Yes</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;N&apos;">
																		<fo:inline>
																			<xsl:text>No</xsl:text>
																		</fo:inline>
																		<fo:block/>
																		<fo:inline>
																			<xsl:text>Carry Forward requests must be sent to Prime Recipient''s - </xsl:text>
																		</fo:inline>
																		<xsl:for-each select="subcontract:SubContractData">
																			<xsl:for-each select="subcontract:SubcontractTemplateInfo">
																				<xsl:for-each select="subcontract:CarryForwardRequestsSentToDescription">
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
																		<fo:inline>
																			<xsl:text>, as shown in Attachment 3.</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text>&#160;&#160;&#160;&#160; </xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
												</fo:list-block>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
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
','DOE Attachment.xsl','application/octet-stream',1,SYS_GUID(),2);

delete from SUBAWARD_FORMS WHERE FORM_ID = 'FDP_EPA';

INSERT INTO SUBAWARD_FORMS(FORM_ID,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,FORM,FILE_NAME,CONTENT_TYPE,VER_NBR,OBJ_ID,TEMPLATE_TYPE_CODE) values ('FDP_EPA',	'EPA Attachment',sysdate,'admin',
'<?xml version="1.0" encoding="UTF-8"?>
<!--Designed and generated by Altova StyleVision Enterprise Edition 2008 rel. 2 - see http://www.altova.com/stylevision for more information.-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:award="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/award" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:subcontract="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/subcontract" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:fo="http://www.w3.org/1999/XSL/Format">
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
							<fo:table font-family="Times New Roman" font-size="12pt" font-weight="bold" table-layout="fixed" width="100%" border="solid 1pt gray" border-spacing="-1pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell border="solid 1pt gray" padding="0" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:block text-align="center" margin="0pt">
													<fo:block>
														<fo:inline>
															<xsl:text>Attachment 2</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>Research Subaward Agreement</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>Prime Award Terms and Conditions</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>EPA</xsl:text>
														</fo:inline>
													</fo:block>
												</fo:block>
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
							<fo:table font-family="Times New Roman" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-size="12pt" font-weight="bold">
										<fo:table-cell padding="0" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Certifications/Assurances</xsl:text>
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row font-size="10pt" font-weight="normal">
										<fo:table-cell padding="0" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text> By signing this Research Subaward Agreement Subrecipient makes the certifications and assurances specified in the Research Terms and Conditions Appendix C found at </xsl:text>
												</fo:inline>
												<fo:basic-link text-decoration="underline" color="blue">
													<xsl:choose>
														<xsl:when test="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf&apos;), 1, 1) = ''#''">
															<xsl:attribute name="internal-destination">
																<xsl:value-of select="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf&apos;), 2)"/>
															</xsl:attribute>
														</xsl:when>
														<xsl:otherwise>
															<xsl:attribute name="external-destination">
																<xsl:text>url(</xsl:text>
																<xsl:call-template name="double-backslash">
																	<xsl:with-param name="text">
																		<xsl:value-of select="string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf&apos;)"/>
																	</xsl:with-param>
																	<xsl:with-param name="text-length">
																		<xsl:value-of select="string-length(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf&apos;))"/>
																	</xsl:with-param>
																</xsl:call-template>
																<xsl:text>)</xsl:text>
															</xsl:attribute>
														</xsl:otherwise>
													</xsl:choose>
													<fo:inline>
														<xsl:text>http://www.nsf.gov/bfa/dias/policy/rtc/appc.pdf</xsl:text>
													</fo:inline>
												</fo:basic-link>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
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
							<fo:inline-container>
								<fo:block>
									<xsl:text>&#x2029;</xsl:text>
								</fo:block>
							</fo:inline-container>
							<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-family="Times New Roman" font-size="12pt" font-weight="bold">
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>General terms and conditions:</xsl:text>
												</fo:inline>
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
							<fo:table font-family="time" font-size="10pt" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="1"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>The restrictions on the expenditure of federal funds in appropriations acts are applicable to this subaward to the extent those restrictions are pertinent.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="2"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>40 CFR Parts 30 and 40 as applicable.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="3"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>Research Terms and Conditions found at </xsl:text>
																</fo:inline>
																<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf&apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf&apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf&apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf&apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text>http://www.nsf.gov/bfa/dias/policy/rtc/terms.pdf</xsl:text>
																	</fo:inline>
																</fo:basic-link>
																<fo:inline>
																	<xsl:text> and Agency Specific Requirements found at </xsl:text>
																</fo:inline>
																<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/epa_708.pdf&apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/epa_708.pdf&apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;nsf.gov/pubs/policydocs/rtc/epa_708.pdf&apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;nsf.gov/pubs/policydocs/rtc/epa_708.pdf&apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text>http://www.nsf.gov/pubs/policydocs/rtc/epa_708.pdf</xsl:text>
																	</fo:inline>
																</fo:basic-link>
																<fo:inline>
																	<xsl:text>, except for the following:</xsl:text>
																</fo:inline>
																<fo:inline-container>
																	<fo:block>
																		<xsl:text>&#x2029;</xsl:text>
																	</fo:block>
																</fo:inline-container>
																<fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="1"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>The right to initiate an automatic one-time extension of the end date provided by Article 25(c)2 of the Research Terms and Conditions is replaced by the need to obtain prior written approval from the Prime Recipient;</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="2"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>The payment mechanism described in Article 22 and the financial reporting requirements in Article 52 of the Research Terms and Conditions and Articles 9 and 11 of the Agency-Specific Requirements are replaced with Terms and Conditions (1) through (4) of this agreement; and</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="3"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>Any prior approvals are to be sought from the Prime Recipient and not the Federal Awarding Agency.</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																</fo:list-block>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="4"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Title to equipment costing $5,000 or more that is purchased or fabricated with research funds or Subrecipient cost sharing funds, as direct costs of the project or program, shall unconditionally vest in the Subrecipient upon acquisition without further obligation to the Federal Awarding Agency subject to the conditions specified in Article 34(a) of the Research Terms and Conditions.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
												</fo:list-block>
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
							<fo:table font-family="time" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="28%"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell font-family="times" font-size="12pt" font-weight="bold" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Special terms and conditions: </xsl:text>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell font-family="time" font-size="10pt" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>&#160;</xsl:text>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row font-family="time" font-size="10pt">
										<fo:table-cell font-family="times" font-size="12pt" font-weight="bold" number-columns-spanned="2" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:list-block font-family="time" font-size="10pt" font-weight="normal" provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="1"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Copyrights</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>Subrecipient </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
																		<fo:inline>
																			<xsl:text>grants</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
																		<fo:inline>
																			<xsl:text>shall grant</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text> to Prime Recipient an irrevocable, royalty-free, non-transferable, non-exclusive right and license to use, reproduce, make derivative works, display, and perform publicly any copyrights or copyrighted material (including any computer software and its documentation and/or databases) first developed and delivered under this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="2"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Data Rights</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>Subrecipient </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
																		<fo:inline>
																			<xsl:text>grants</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
																		<fo:inline>
																			<xsl:text>shall grant</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text> to Prime Recipient the right to use data created in the performance of this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award.</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>[Do not add a Patent or Inventions Clause. The prime award governs rights to patents and inventions. Prime Recipient cannot obtain rights in the Subrecipient''s subject inventions as a part of consideration for the subaward.&#160; Should it be necessary, the Federal Government can authorize the Prime Recipient''s right to practice a Subrecipients''s subject invention (as well as subject data or copyrights) on behalf of the Federal Government.]</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="3"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Automatic Carry Forward:&#160; </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;Y&apos;">
																		<fo:inline>
																			<xsl:text>Yes</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;N&apos;">
																		<fo:inline>
																			<xsl:text>No</xsl:text>
																		</fo:inline>
																		<fo:block/>
																		<fo:inline>
																			<xsl:text>Carry Forward requests must be sent to Prime Recipient''s - </xsl:text>
																		</fo:inline>
																		<xsl:for-each select="subcontract:SubContractData">
																			<xsl:for-each select="subcontract:SubcontractTemplateInfo">
																				<xsl:for-each select="subcontract:CarryForwardRequestsSentToDescription">
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
																		<fo:inline>
																			<xsl:text>, as shown in Attachment 3.</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text>&#160;&#160;&#160;&#160; </xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
												</fo:list-block>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
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
','EPA Attachment.xsl','application/octet-stream',1,SYS_GUID(),2);

delete from SUBAWARD_FORMS WHERE FORM_ID = 'FDP_NASA';

INSERT INTO SUBAWARD_FORMS(FORM_ID,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,FORM,FILE_NAME,CONTENT_TYPE,VER_NBR,OBJ_ID,TEMPLATE_TYPE_CODE) values ('FDP_NASA',	'NASA Attachment',sysdate,'admin',
'<?xml version="1.0" encoding="UTF-8"?>
<!--Designed and generated by Altova StyleVision Enterprise Edition 2008 rel. 2 - see http://www.altova.com/stylevision for more information.-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:award="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/award" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:subcontract="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/subcontract" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:fo="http://www.w3.org/1999/XSL/Format">
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
							<fo:table font-family="Times New Roman" font-size="12pt" font-weight="bold" table-layout="fixed" width="100%" border="solid 1pt gray" border-spacing="-1pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell border="solid 1pt gray" padding="0" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:block text-align="center" margin="0pt">
													<fo:block>
														<fo:inline>
															<xsl:text>Attachment 2</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>Research Subaward Agreement</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>Prime Award Terms and Conditions</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>NASA</xsl:text>
														</fo:inline>
													</fo:block>
												</fo:block>
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
							<fo:table font-family="Times New Roman" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-size="12pt" font-weight="bold">
										<fo:table-cell padding="0" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Certifications/Assurances</xsl:text>
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row font-size="10pt" font-weight="normal">
										<fo:table-cell padding="0" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text> By signing this Research Subaward Agreement Subrecipient makes the certifications and assurances specified in the Research Terms and Conditions Appendix C found at </xsl:text>
												</fo:inline>
												<fo:basic-link text-decoration="underline" color="blue">
													<xsl:choose>
														<xsl:when test="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf&apos;), 1, 1) = ''#''">
															<xsl:attribute name="internal-destination">
																<xsl:value-of select="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf&apos;), 2)"/>
															</xsl:attribute>
														</xsl:when>
														<xsl:otherwise>
															<xsl:attribute name="external-destination">
																<xsl:text>url(</xsl:text>
																<xsl:call-template name="double-backslash">
																	<xsl:with-param name="text">
																		<xsl:value-of select="string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf&apos;)"/>
																	</xsl:with-param>
																	<xsl:with-param name="text-length">
																		<xsl:value-of select="string-length(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf&apos;))"/>
																	</xsl:with-param>
																</xsl:call-template>
																<xsl:text>)</xsl:text>
															</xsl:attribute>
														</xsl:otherwise>
													</xsl:choose>
													<fo:inline>
														<xsl:text>http://www.nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf</xsl:text>
													</fo:inline>
												</fo:basic-link>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
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
							<fo:inline-container>
								<fo:block>
									<xsl:text>&#x2029;</xsl:text>
								</fo:block>
							</fo:inline-container>
							<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-family="Times New Roman" font-size="12pt" font-weight="bold">
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>General terms and conditions:</xsl:text>
												</fo:inline>
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
							<fo:table font-family="time" font-size="10pt" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="1"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>Under the authority of 42 U.S.C. 2473(c)(5), and is subject to all applicable laws and regulations of the United States in effect on the date of this agreement, including but not limited to 14 CFR Part 1260 (Grants and Cooperative Agreements).</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="2"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>The restrictions on the expenditure of federal funds in appropriations acts are applicable to this subaward to the extent those restrictions are pertinent.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="3"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>Research Terms and Conditions found at </xsl:text>
																</fo:inline>
																<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text>  http://www.nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf </xsl:text>
																	</fo:inline>
																</fo:basic-link>
																<fo:inline>
																	<xsl:text> and Agency Specific Requirements found at </xsl:text>
																</fo:inline>
																<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/nasa_708.pdf&apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/nasa_708.pdf&apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;nsf.gov/pubs/policydocs/rtc/nasa_708.pdf&apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;nsf.gov/pubs/policydocs/rtc/nasa_708.pdf&apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text>http://www.nsf.gov/pubs/policydocs/rtc/nasa_708.pdf</xsl:text>
																	</fo:inline>
																</fo:basic-link>
																<fo:inline>
																	<xsl:text>, except for the following:</xsl:text>
																</fo:inline>
																<fo:inline-container>
																	<fo:block>
																		<xsl:text>&#x2029;</xsl:text>
																	</fo:block>
																</fo:inline-container>
																<fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="1"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>The right to initiate an automatic one-time extension of the end date provided by Article 25(c)(2) is replaced by the need to obtain prior written approval from the Prime Recipient;</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="2"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>The payment mechanism described in Article 22 and the financial reporting requirements in Article 52 of the Research Terms and Conditions and Article 9 of the Agency-Specific Requirements are replaced with Terms and Conditions (1) through (4) of this agreement; and</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="3"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>Any prior approvals are to be sought from the Prime Recipient and not the Federal Awarding Agency.</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																</fo:list-block>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="4"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Title to equipment costing $5,000 or more that is purchased or fabricated with research funds or collaborator cost sharing funds, as direct costs of the project or program, shall unconditionally vest in the Subrecipent upon acquisition without further obligation to the Federal Awarding Agency subject to the conditions specified in Article 34(a) of the Research Terms and Conditions.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
												</fo:list-block>
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
							<fo:table font-family="time" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="28%"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell font-family="times" font-size="12pt" font-weight="bold" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Special terms and conditions: </xsl:text>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell font-family="time" font-size="10pt" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>&#160;</xsl:text>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row font-family="time" font-size="10pt">
										<fo:table-cell font-family="times" font-size="12pt" font-weight="bold" number-columns-spanned="2" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:list-block font-family="time" font-size="10pt" font-weight="normal" provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="1"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Copyrights</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>Subrecipient </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
																		<fo:inline>
																			<xsl:text>grants</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
																		<fo:inline>
																			<xsl:text>shall grant</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text> to Prime Recipient an irrevocable, royalty-free, non-transferable, non-exclusive right and license to use, reproduce, make derivative works, display, and perform publicly any copyrights or copyrighted material (including any computer software and its documentation and/or databases) first developed and delivered under this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="2"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Data Rights</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>Subrecipient </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
																		<fo:inline>
																			<xsl:text>grants</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
																		<fo:inline>
																			<xsl:text>shall grant</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text> to Prime Recipient the right to use data created in the performance of this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award.</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>[Do not add a Patent or Inventions Clause. The prime award governs rights to patents and inventions. Prime Recipient cannot obtain rights in the Subrecipient''s subject inventions as a part of consideration for the subaward.&#160; Should it be necessary, the Federal Government can authorize the Prime Recipient''s right to practice a Subrecipients''s subject invention (as well as subject data or copyrights) on behalf of the Federal Government.]</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="3"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Automatic Carry Forward:&#160; </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;Y&apos;">
																		<fo:inline>
																			<xsl:text>Yes</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;N&apos;">
																		<fo:inline>
																			<xsl:text>No</xsl:text>
																		</fo:inline>
																		<fo:block/>
																		<fo:inline>
																			<xsl:text>Carry Forward requests must be sent to Prime Recipient''s - </xsl:text>
																		</fo:inline>
																		<xsl:for-each select="subcontract:SubContractData">
																			<xsl:for-each select="subcontract:SubcontractTemplateInfo">
																				<xsl:for-each select="subcontract:CarryForwardRequestsSentToDescription">
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
																		<fo:inline>
																			<xsl:text>, as shown in Attachment 3.</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text>&#160;&#160;&#160;&#160; </xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
												</fo:list-block>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
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
','NASA Attachment.xsl','application/octet-stream',1,SYS_GUID(),2);

delete from SUBAWARD_FORMS WHERE FORM_ID = 'FDP_NIH';

INSERT INTO SUBAWARD_FORMS(FORM_ID,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,FORM,FILE_NAME,CONTENT_TYPE,VER_NBR,OBJ_ID,TEMPLATE_TYPE_CODE) values ('FDP_NIH',	'NIH Attachment',sysdate,'admin',
'<?xml version="1.0" encoding="UTF-8"?>
        <!--Designed and generated by Altova StyleVision Enterprise Edition 2008 rel. 2 - see http://www.altova.com/stylevision for more information.-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:subcontract="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/subcontract"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output version="1.0" method="xml" encoding="UTF-8" indent="no"/>
    <xsl:param name="SV_OutputFormat" select="''PDF''"/>
    <xsl:variable name="XML" select="/"/>
    <xsl:variable name="fo:layout-master-set">
        <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.2in"
                                   margin-right="0.2in">
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
                        <fo:table font-family="Times New Roman" font-size="12pt" font-weight="bold" table-layout="fixed"
                                  width="100%" border="solid 1pt gray" border-spacing="-1pt">
                            <fo:table-column column-width="proportional-column-width(1)"/>
                            <fo:table-body start-indent="0pt">
                                <fo:table-row>
                                    <fo:table-cell border="solid 1pt gray" padding="0" display-align="center">
                                        <fo:block>
                                            <fo:inline-container>
                                                <fo:block>
                                                    <xsl:text>&#x2029;</xsl:text>
                                                </fo:block>
                                            </fo:inline-container>
                                            <fo:block text-align="center" margin="0pt">
                                                <fo:block>
                                                    <fo:inline>
                                                        <xsl:text>Attachment 2</xsl:text>
                                                    </fo:inline>
                                                    <fo:block/>
                                                    <fo:inline>
                                                        <xsl:text>Research Subaward Agreement</xsl:text>
                                                    </fo:inline>
                                                    <fo:block/>
                                                    <fo:inline>
                                                        <xsl:text>Prime Award Terms and Conditions</xsl:text>
                                                    </fo:inline>
                                                    <fo:block/>
                                                    <fo:inline>
                                                        <xsl:text>NIH</xsl:text>
                                                    </fo:inline>
                                                </fo:block>
                                            </fo:block>
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
                        <fo:table font-family="Times New Roman" table-layout="fixed" width="100%" border-spacing="2pt">
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-body start-indent="0pt">
                        <fo:table-row font-size="12pt" font-weight="bold">
                        <fo:table-cell padding="0" display-align="center">
                        <fo:block>
                            <fo:inline>
                                <xsl:text>Agency-Specific Certifications/Assurances</xsl:text>
                            </fo:inline>
                            <fo:block>
                                <fo:leader leader-pattern="space"/>
                            </fo:block>
                            <fo:inline-container>
                                <fo:block>
                                    <xsl:text>&#x2029;</xsl:text>
                                </fo:block>
                            </fo:inline-container>
                            <fo:block
                            margin="0pt">
                            <fo:block/>
                        </fo:block>
                    </fo:block>
                </fo:table-cell>
            </fo:table-row>
            <fo:table-row font-size="10pt" font-weight="normal">
                <fo:table-cell padding="0" display-align="center">
                    <fo:block>
                        <fo:inline>
                            <xsl:text> By signing this Research Subaward Agreement Subrecipient makes the certifications and assurances specified in the Research Terms and Conditions Appendix C found at </xsl:text>
                        </fo:inline>
                        <fo:basic-link text-decoration="underline" color="blue">
                            <xsl:choose>
                                <xsl:when
                                        test="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf&apos;), 1, 1) = ''#''">
                                    <xsl:attribute name="internal-destination">
                                        <xsl:value-of
                                                select="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf&apos;), 2)"/>
                                    </xsl:attribute>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:attribute name="external-destination">
                                        <xsl:text>url(</xsl:text>
                                        <xsl:call-template name="double-backslash">
                                            <xsl:with-param name="text">
                                                <xsl:value-of
                                                        select="string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf&apos;)"/>
                                            </xsl:with-param>
                                            <xsl:with-param name="text-length">
                                                <xsl:value-of
                                                        select="string-length(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf&apos;))"/>
                                            </xsl:with-param>
                                        </xsl:call-template>
                                        <xsl:text>)</xsl:text>
                                    </xsl:attribute>
                                </xsl:otherwise>
                            </xsl:choose>
                            <fo:inline>
                                <xsl:text>http://www.nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf</xsl:text>
                            </fo:inline>
                        </fo:basic-link>
                    </fo:block>
                </fo:table-cell>
            </fo:table-row>
        </fo:table-body>
    </fo:table>
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
    <fo:inline-container>
        <fo:block>
            <xsl:text>&#x2029;</xsl:text>
        </fo:block>
    </fo:inline-container>
    <fo:table table-layout="fixed" width="100%" border-spacing="2pt">
        <fo:table-column column-width="28%"/>
        <fo:table-column column-width="proportional-column-width(1)"/>
        <fo:table-body start-indent="0pt">
            <fo:table-row font-family="Times New Roman" font-size="12pt" font-weight="bold">
                <fo:table-cell padding="2pt" display-align="center">
                    <fo:block>
                        <fo:inline>
                            <xsl:text>General terms and conditions </xsl:text>
                        </fo:inline>
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell font-size="10pt" font-weight="normal" padding="2pt" display-align="center">
                    <fo:block>
                        <fo:inline>
                            <xsl:text>as of the effective date of this Research Subaward Agreement:</xsl:text>
                        </fo:inline>
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
    <fo:table font-family="time" font-size="10pt" table-layout="fixed" width="100%" border-spacing="2pt">
        <fo:table-column column-width="proportional-column-width(1)"/>
        <fo:table-body start-indent="0pt">
            <fo:table-row>
                <fo:table-cell padding="2pt" display-align="center">
                    <fo:block>
                        <fo:inline-container>
                            <fo:block>
                                <xsl:text>&#x2029;</xsl:text>
                            </fo:block>
                        </fo:inline-container>
                        <fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
                            <fo:list-item>
                                <fo:list-item-label end-indent="label-end()" text-align="right">
                                    <fo:block>
                                        <xsl:number format="1" value="1"/>.
                                    </fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body start-indent="body-start()">
                                    <fo:block font-family="time" font-size="10pt" font-weight="normal">
                                        <fo:inline>
                                            <xsl:text>The restrictions on the expenditure of federal funds in appropriations acts are applicable to this subaward to the extent those restrictions are pertinent.</xsl:text>
                                        </fo:inline>
                                    </fo:block>
                                </fo:list-item-body>
                            </fo:list-item>
                            <fo:list-item>
                                <fo:list-item-label end-indent="label-end()" text-align="right">
                                    <fo:block>
                                        <xsl:number format="1" value="2"/>.
                                    </fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body start-indent="body-start()">
                                    <fo:block font-family="time" font-size="10pt" font-weight="normal">
                                        <fo:inline>
                                            <xsl:text>45 CFR Part 74 or 45 CFR Part 92 as applicable.</xsl:text>
                                        </fo:inline>
                                    </fo:block>
                                </fo:list-item-body>
                            </fo:list-item>
                            <fo:list-item>
                                <fo:list-item-label end-indent="label-end()" text-align="right">
                                    <fo:block>
                                        <xsl:number format="1" value="3"/>.
                                    </fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body start-indent="body-start()">
                                    <fo:block font-family="time" font-size="10pt" font-weight="normal">
                                        <fo:inline>
                                            <xsl:text>The </xsl:text>
                                        </fo:inline>
                                        <fo:inline font-weight="bold">
                                            <xsl:text>NIH Grants Policy Statement </xsl:text>
                                        </fo:inline>
                                        <fo:inline>
                                            <xsl:text>found at </xsl:text>
                                        </fo:inline>
                                        <fo:basic-link text-decoration="underline" color="blue">
                                            <xsl:choose>
                                                <xsl:when
                                                        test="substring(string(&apos;grants.nih.gov/grants/policy/policy.htm#gps&apos;), 1, 1) = ''#''">
                                                    <xsl:attribute name="internal-destination">
                                                        <xsl:value-of
                                                                select="substring(string(&apos;grants.nih.gov/grants/policy/policy.htm#gps&apos;), 2)"/>
                                                    </xsl:attribute>
                                                </xsl:when>
                                                <xsl:otherwise>
                                                    <xsl:attribute name="external-destination">
                                                        <xsl:text>url(</xsl:text>
                                                        <xsl:call-template name="double-backslash">
                                                            <xsl:with-param name="text">
                                                                <xsl:value-of
                                                                        select="string(&apos;grants.nih.gov/grants/policy/policy.htm#gps&apos;)"/>
                                                            </xsl:with-param>
                                                            <xsl:with-param name="text-length">
                                                                <xsl:value-of
                                                                        select="string-length(string(&apos;grants.nih.gov/grants/policy/policy.htm#gps&apos;))"/>
                                                            </xsl:with-param>
                                                        </xsl:call-template>
                                                        <xsl:text>)</xsl:text>
                                                    </xsl:attribute>
                                                </xsl:otherwise>
                                            </xsl:choose>
                                            <fo:inline>
                                                <xsl:text> http://grants.nih.gov/grants/policy/policy.htm#gps </xsl:text>
                                            </fo:inline>
                                        </fo:basic-link>
                                        <fo:inline>
                                            <xsl:text>, including addenda in effect as of the beginning date of the period of performance.</xsl:text>
                                        </fo:inline>
                                    </fo:block>
                                </fo:list-item-body>
                            </fo:list-item>
                            <fo:list-item>
                                <fo:list-item-label end-indent="label-end()" text-align="right">
                                    <fo:block>
                                        <xsl:number format="1" value="4"/>.
                                    </fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body start-indent="body-start()">
                                    <fo:block font-family="time" font-size="10pt" font-weight="normal">
                                        <fo:inline>
                                            <xsl:text>Research Terms and Conditions found at </xsl:text>
                                        </fo:inline>
                                        <fo:basic-link text-decoration="underline" color="blue">
                                            <xsl:choose>
                                                <xsl:when
                                                        test="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;), 1, 1) = ''#''">
                                                    <xsl:attribute name="internal-destination">
                                                        <xsl:value-of
                                                                select="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;), 2)"/>
                                                    </xsl:attribute>
                                                </xsl:when>
                                                <xsl:otherwise>
                                                    <xsl:attribute name="external-destination">
                                                        <xsl:text>url(</xsl:text>
                                                        <xsl:call-template name="double-backslash">

                                                            <xsl:with-param name="text">
                                                                <xsl:value-of
                                                                        select="string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;)"/>
                                                            </xsl:with-param>
                                                            <xsl:with-param name="text-length">
                                                                <xsl:value-of
                                                                        select="string-length(string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;))"/>
                                                            </xsl:with-param>
                                                        </xsl:call-template>
                                                        <xsl:text>)</xsl:text>
                                                    </xsl:attribute>
                                                </xsl:otherwise>
                                            </xsl:choose>
                                            <fo:inline>
                                                <xsl:text> http://www.nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf </xsl:text>
                                            </fo:inline>
                                        </fo:basic-link>
                                        <fo:inline>
                                            <xsl:text> and Agency Specific Requirements found at </xsl:text>
                                        </fo:inline>
                                        <fo:basic-link text-decoration="underline" color="blue">
                                            <xsl:choose>
                                                <xsl:when
                                                        test="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/nih_1210.pdf&apos;), 1, 1) = ''#''">
                                                    <xsl:attribute name="internal-destination">
                                                        <xsl:value-of
                                                                select="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/nih_1210.pdf&apos;), 2)"/>
                                                    </xsl:attribute>
                                                </xsl:when>
                                                <xsl:otherwise>
                                                    <xsl:attribute name="external-destination">
                                                        <xsl:text>url(</xsl:text>
                                                        <xsl:call-template name="double-backslash">
                                                            <xsl:with-param name="text">
                                                                <xsl:value-of
                                                                        select="string(&apos;nsf.gov/pubs/policydocs/rtc/nih_1210.pdf&apos;)"/>
                                                            </xsl:with-param>
                                                            <xsl:with-param name="text-length">
                                                                <xsl:value-of
                                                                        select="string-length(string(&apos;nsf.gov/pubs/policydocs/rtc/nih_1210.pdf&apos;))"/>
                                                            </xsl:with-param>
                                                        </xsl:call-template>
                                                        <xsl:text>)</xsl:text>
                                                    </xsl:attribute>
                                                </xsl:otherwise>
                                            </xsl:choose>
                                            <fo:inline>
                                                <xsl:text>http://www.nsf.gov/pubs/policydocs/rtc/nih_1210.pdf</xsl:text>
                                            </fo:inline>
                                        </fo:basic-link>
                                        <fo:inline>
                                            <xsl:text>, except for the following:</xsl:text>
                                        </fo:inline>
                                        <fo:inline-container>
                                            <fo:block>
                                                <xsl:text>&#x2029;</xsl:text>
                                            </fo:block>
                                        </fo:inline-container>
                                        <fo:list-block provisional-distance-between-starts="7mm"
                                                       provisional-label-separation="2mm">
                                        <fo:list-item>
                                            <fo:list-item-label end-indent="label-end()" text-align="right">
                                                <fo:block>
                                                    <xsl:number format="a" value="1"/>.
                                                </fo:block>
                                            </fo:list-item-label>
                                            <fo:list-item-body start-indent="body-start()">
                                                <fo:block>
                                                    <fo:inline>
                                                        <xsl:text>The right to initiate an automatic one-time extension of the end date provided by Article 25(c)(2) of the Research Terms and Conditions is replaced by the need to obtain prior written approval from the Prime Recipient;</xsl:text>
                                                    </fo:inline>
                                                </fo:block>
                                            </fo:list-item-body>
                                        </fo:list-item>
                                        <fo:list-item>
                                        <fo:list-item-label end-indent="label-end()" text-align="right">
                                            <fo:block>
                                                <xsl:number format="a" value="2"/>.
                                            </fo:block>
                                        </fo:list-item-label>
                                        <fo:list-item-body start-indent="body-start()">
                                        <fo:block>
                                            <fo:inline>
                                            <xsl:text>The payment mechanism described in Article 22 and the financial reporting requirements in Article 52 of the Research Terms and Conditions and Article 8 of the Agency-Specific Requirements are replaced with Terms and Conditions (1) through (4) of this Subaward Agreement; and</xsl:text>
                                        </fo:inline>
                                    </fo:block>
                                </fo:list-item-body>
                            </fo:list-item>
                            <fo:list-item>
                                <fo:list-item-label end-indent="label-end()" text-align="right">
                                    <fo:block>
                                        <xsl:number format="a" value="3"/>.
                                    </fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body start-indent="body-start()">
                                    <fo:block>
                                        <fo:inline>
                                            <xsl:text>Any prior approvals are to be sought from the Prime Recipient and not the Federal Awarding Agency.</xsl:text>
                                        </fo:inline>
                                    </fo:block>
                                </fo:list-item-body>
                            </fo:list-item>
                        </fo:list-block>
                    </fo:block>
                </fo:list-item-body>
            </fo:list-item>
            <fo:list-item>
                <fo:list-item-label end-indent="label-end()" text-align="right">
                    <fo:block>
                        <xsl:number format="1" value="5"/>.
                    </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="body-start()">
                    <fo:block>
                        <fo:inline>
                            <xsl:text>Title to equipment costing $5,000 or more that is purchased or fabricated with research funds or Subrecipient cost sharing funds, as direct costs of the project or program, shall unconditionally vest in the Subrecipient upon acquisition without further obligation&#160;&#160; to the Federal Awarding Agency subject to the conditions specified in Article 34(a) of the Research Terms and Conditions. </xsl:text>
                        </fo:inline>
                    </fo:block>
                </fo:list-item-body>
            </fo:list-item>
            <fo:list-item>
                <fo:list-item-label end-indent="label-end()" text-align="right">
                    <fo:block>
                        <xsl:number format="1" value="6"/>.
                    </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="body-start()">
                    <fo:block>
                        <fo:inline>
                            <xsl:text>Treatment of Program Income: </xsl:text>
                        </fo:inline>
                        <xsl:choose>
                            <xsl:when
                                    test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:TreatmentPrgmIncomeAdditive = &quot;Y&quot;">
                                <fo:external-graphic content-height="8" content-width="8">
                                    <xsl:attribute name="src">
                                        <xsl:text>url(</xsl:text>
                                        <xsl:call-template name="double-backslash">
                                            <xsl:with-param name="text">
                                                <xsl:value-of
                                                        select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;))"/>
                                            </xsl:with-param>
                                            <xsl:with-param name="text-length">
                                                <xsl:value-of
                                                        select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;)))"/>
                                            </xsl:with-param>
                                        </xsl:call-template>
                                        <xsl:text>)</xsl:text>
                                    </xsl:attribute>
                                </fo:external-graphic>
                            </xsl:when>
                            <xsl:when
                                    test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:TreatmentPrgmIncomeAdditive = &quot;N&quot;">
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
                        </xsl:when>
                    </xsl:choose>
                    <fo:inline>
                        <xsl:text> Additive  </xsl:text>
                    </fo:inline>
                    <fo:inline>
                        <xsl:text>&#160;&#160;</xsl:text>
                    </fo:inline>
                    <xsl:choose>
                        <xsl:when
                                test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:TreatmentPrgmIncomeAdditive = &quot;N&quot;">
                            <fo:external-graphic content-height="8" content-width="8">
                                <xsl:attribute name="src">
                                    <xsl:text>url(</xsl:text>
                                    <xsl:call-template name="double-backslash">
                                        <xsl:with-param name="text">
                                            <xsl:value-of
                                                    select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;))"/>
                                        </xsl:with-param>
                                        <xsl:with-param name="text-length">
                                            <xsl:value-of
                                                    select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageCheckedPath,&apos;checked.gif&apos;)))"/>
                                        </xsl:with-param>
                                    </xsl:call-template>
                                    <xsl:text>)</xsl:text>
                                </xsl:attribute>
                            </fo:external-graphic>
                        </xsl:when>
                        <xsl:when
                                test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:TreatmentPrgmIncomeAdditive = &quot;Y&quot;">
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
                        </xsl:when>
                    </xsl:choose>
                    <fo:inline>
                        <xsl:text> Other, Prime Recipient specify alternative from NIH Agreement</xsl:text>
                    </fo:inline>
                </fo:block>
            </fo:list-item-body>
        </fo:list-item>
    </fo:list-block>
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
<fo:table font-family="time" table-layout="fixed" width="100%" border-spacing="2pt">
<fo:table-column column-width="28%"/>
<fo:table-column column-width="proportional-column-width(1)"/>
<fo:table-body start-indent="0pt">
    <fo:table-row>
        <fo:table-cell font-family="times" font-size="12pt" font-weight="bold" padding="2pt" display-align="center">
            <fo:block>
                <fo:inline>
                    <xsl:text>Special terms and conditions:</xsl:text>
                </fo:inline>
            </fo:block>
        </fo:table-cell>
        <fo:table-cell font-family="time" font-size="10pt" padding="2pt" display-align="center">
            <fo:block>
                <fo:inline>
                    <xsl:text>&#160;</xsl:text>
                </fo:inline>
            </fo:block>
        </fo:table-cell>
    </fo:table-row>
    <fo:table-row font-family="time" font-size="10pt">
        <fo:table-cell font-family="times" font-size="12pt" font-weight="bold" number-columns-spanned="2" padding="2pt"
                       display-align="center">
            <fo:block>
                <fo:inline-container>
                    <fo:block>
                        <xsl:text>&#x2029;</xsl:text>
                    </fo:block>
                </fo:inline-container>
                <fo:list-block font-family="time" font-size="10pt" font-weight="normal"
                               provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
                <fo:list-item>
                    <fo:list-item-label end-indent="label-end()" text-align="right">
                        <fo:block>
                            <xsl:number format="1" value="1"/>.
                        </fo:block>
                    </fo:list-item-label>
                    <fo:list-item-body start-indent="body-start()">
                        <fo:block>
                            <fo:inline>
                                <xsl:text>Copyrights</xsl:text>
                            </fo:inline>
                            <fo:block/>
                            <fo:inline>
                                <xsl:text>Subrecipient</xsl:text>
                            </fo:inline>
                            <xsl:choose>
                                <xsl:when
                                        test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
                                    <fo:inline>
                                        <xsl:text>grants</xsl:text>
                                    </fo:inline>
                                </xsl:when>
                                <xsl:when
                                        test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
                                    <fo:inline>
                                        <xsl:text>shall grant</xsl:text>
                                    </fo:inline>
                                </xsl:when>
                            </xsl:choose>
                            <fo:inline>
                                <xsl:text>to Prime Recipient an irrevocable, royalty-free, non-transferable, non-exclusive right and license to use, reproduce, make derivative works, display, and perform publicly any copyrights or copyrighted material (including any computer software and its documentation and/or databases) first developed and delivered under this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award.</xsl:text>
                            </fo:inline>
                        </fo:block>
                    </fo:list-item-body>
                </fo:list-item>
                <fo:list-item>
                    <fo:list-item-label end-indent="label-end()" text-align="right">
                        <fo:block>
                            <xsl:number format="1" value="2"/>.
                        </fo:block>
                    </fo:list-item-label>
                    <fo:list-item-body start-indent="body-start()">
                        <fo:block>
                            <fo:inline>
                                <xsl:text>Data Rights</xsl:text>
                            </fo:inline>
                            <fo:block/>
                            <fo:inline>
                                <xsl:text>Subrecipient</xsl:text>
                            </fo:inline>
                            <xsl:choose>
                                <xsl:when
                                        test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
                                    <fo:inline>
                                        <xsl:text>grants</xsl:text>
                                    </fo:inline>
                                </xsl:when>
                                <xsl:when
                                        test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
                                    <fo:inline>
                                        <xsl:text>shall grant</xsl:text>
                                    </fo:inline>
                                </xsl:when>
                            </xsl:choose>
                            <fo:inline>
                                <xsl:text>to Prime Recipient the right to use data created in the performance of this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award.</xsl:text>
                            </fo:inline>
                        </fo:block>
                    </fo:list-item-body>
                </fo:list-item>
                <fo:list-item>
                <fo:list-item-label end-indent="label-end()" text-align="right">
                    <fo:block>
                        <xsl:number format="1" value="3"/>.
                    </fo:block>
                </fo:list-item-label>
                <fo:list-item-body start-indent="body-start()">
                <fo:block>
                    <fo:inline>
                        <xsl:text>Automatic Carry Forward:&#160; </xsl:text>
                    </fo:inline>
                    <xsl:choose>
                        <xsl:when
                                test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;Y&apos;">
                            <fo:inline>
                                <xsl:text>Yes</xsl:text>
                            </fo:inline>
                        </xsl:when>
                        <xsl:when
                                test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;N&apos;">
                            <fo:inline>
                                <xsl:text>No</xsl:text>
                            </fo:inline>
                            <fo:block/>
                            <fo:inline>
                                <xsl:text>Carry Forward requests must be sent to</xsl:text>
                            </fo:inline>
                            <xsl:for-each select="subcontract:SubContractData">
                                <xsl:for-each select="subcontract:SubcontractTemplateInfo">
                                    <xsl:for-each select="subcontract:CarryForwardRequestsSentToDescription">
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
                        <fo:inline>
                            <xsl:text>, as shown in Attachment 3.</xsl:text>
                        </fo:inline>
                    </xsl:when>
                </xsl:choose>
                <fo:inline>
                    <xsl:text>&#160;&#160;&#160;&#160; </xsl:text>
                </fo:inline>
            </fo:block>
        </fo:list-item-body>
    </fo:list-item>
</fo:list-block>
</fo:block>
        </fo:table-cell>
        </fo:table-row>
        </fo:table-body>
        </fo:table>
        <!--  insert conditional FCOI-PHS block -->
<fo:inline-container>
<fo:block>
    <xsl:text>&#x2029;</xsl:text>
</fo:block>
</fo:inline-container>
<xsl:choose>
<xsl:when test="subcontract:SubContractData/subcontract:SubcontractDetail/subcontract:PHSFCOI = &quot;Y&quot;">
    <fo:table table-layout="fixed" width="100%" border-spacing="2pt">
        <fo:table-column column-width="proportional-column-width(1)"/>
        <fo:table-body start-indent="0pt">
            <fo:table-row>
                <fo:table-cell padding="2pt" height="280pt" display-align="center">
                    <fo:block>
                        <fo:table table-layout="fixed" width="100%" border-spacing="2pt">
                            <fo:table-column column-width="100%"/>
                            <fo:table-body start-indent="0pt">
                                <fo:table-row font-family="Times New Roman" font-size="10pt" font-weight="bold">
                                    <fo:table-cell padding="2pt">
                                        <fo:block>
                                            <fo:inline>
                                                <xsl:text>NIH-Specific Requirements Promoting Objectivity in Research Applicable to Subrecipients (42 CFR Part 50 Subpart F)</xsl:text>
                                            </fo:inline>
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
                        <fo:table font-family="time" font-size="10pt" table-layout="fixed" width="100%"
                                  border-spacing="2pt">
                            <fo:table-column column-width="proportional-column-width(1)"/>
                            <fo:table-body start-indent="0pt">
                                <fo:table-row>
                                    <fo:table-cell padding="2pt" display-align="center">
                                        <fo:block>
                                            <fo:inline-container>
                                                <fo:block>
                                                    <xsl:text>&#x2029;</xsl:text>
                                                </fo:block>
                                            </fo:inline-container>
                                            <fo:list-block provisional-distance-between-starts="7mm"
                                                           provisional-label-separation="2mm">
                                                <fo:list-item>
                                                    <fo:list-item-label end-indent="label-end()" text-align="right">
                                                        <fo:block>
                                                        </fo:block>
                                                    </fo:list-item-label>
                                                    <fo:list-item-body start-indent="body-start()">
                                                        <fo:block font-family="time" font-size="10pt"
                                                                  font-weight="normal">
                                                            <fo:inline-container>
                                                                <fo:block>
                                                                    <xsl:text>&#x2029;</xsl:text>
                                                                </fo:block>
                                                            </fo:inline-container>
                                                            <fo:list-block provisional-distance-between-starts="7mm"
                                                                           provisional-label-separation="2mm">
                                                                <fo:list-item>
                                                                    <fo:list-item-label end-indent="label-end()"
                                                                                        text-align="right">
                                                                        <fo:block>
                                                                            <xsl:number format="1" value="1"/>.
                                                                        </fo:block>
                                                                    </fo:list-item-label>
                                                                    <fo:list-item-body start-indent="body-start()">
                                                                        <fo:block>
                                                                            <fo:inline>
                                                                                <xsl:text>42 CFR Part 50. 604 requires that institutions conducting PHS-funded research "Maintain an up-to-date, written, enforced policy on</xsl:text>
                                                                            </fo:inline>
                                                                            <fo:inline>
                                                                                <xsl:text>financial conflicts of interest." Further, "If the Institution carries out the PHS-funded research through a subrecipient (e.g., subcontractors or consortium members), the Institution (awardee Institution) must take reasonable steps to ensure that any subrecipient Investigator complies with this subpart by incorporating as part of a written agreement with the subrecipient terms that establish whether the financial conflicts of interest policy of the awardee Institution or that of the subrecipient will apply to the subrecipient Investigators."</xsl:text>
                                                                            </fo:inline>
                                                                            <fo:block white-space-collapse="false"
                                                                                      white-space-treatment="preserve"
                                                                                      font-size="0pt"
                                                                                      line-height="15px"></fo:block>
                                                                            <fo:block>
                                                                                <xsl:text>&#x00A0;</xsl:text>
                                                                            </fo:block>
                                                                            <fo:inline>
                                                                                <xsl:text>Subrecipient must designate herein whether the financial conflicts of interest policy of</xsl:text>
                                                                            </fo:inline>
                                                                            <fo:inline>
                                                                                <fo:external-graphic content-height="8"
                                                                                                     content-width="7">
                                                                                    <xsl:attribute name="src">
                                                                                        <xsl:text>url(</xsl:text>
                                                                                        <xsl:call-template
                                                                                                name="double-backslash">
                                                                                            <xsl:with-param name="text">
                                                                                                <xsl:value-of
                                                                                                        select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;))"/>
                                                                                            </xsl:with-param>
                                                                                            <xsl:with-param
                                                                                                    name="text-length">
                                                                                                <xsl:value-of
                                                                                                        select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;)))"/>
                                                                                            </xsl:with-param>
                                                                                        </xsl:call-template>
                                                                                        <xsl:text>)</xsl:text>
                                                                                    </xsl:attribute>
                                                                                </fo:external-graphic>
                                                                            </fo:inline>
                                                                            <xsl:text></xsl:text>
                                                                            <fo:inline>
                                                                                <xsl:text>Prime Recipient Institution, or</xsl:text>
                                                                            </fo:inline>
                                                                            <fo:inline>
                                                                                <fo:external-graphic content-height="8"
                                                                                                     content-width="7">
                                                                                    <xsl:attribute name="src">
                                                                                        <xsl:text>url(</xsl:text>
                                                                                        <xsl:call-template
                                                                                                name="double-backslash">
                                                                                            <xsl:with-param name="text">
                                                                                                <xsl:value-of
                                                                                                        select="string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;))"/>
                                                                                            </xsl:with-param>
                                                                                            <xsl:with-param
                                                                                                    name="text-length">
                                                                                                <xsl:value-of
                                                                                                        select="string-length(string(concat(subcontract:SubContractData/subcontract:PrintRequirement/subcontract:ImageUncheckedPath,&apos;checkbox.gif&apos;)))"/>
                                                                                            </xsl:with-param>
                                                                                        </xsl:call-template>
                                                                                        <xsl:text>)</xsl:text>
                                                                                    </xsl:attribute>
                                                                                </fo:external-graphic>
                                                                            </fo:inline>
                                                                            <fo:inline>
                                                                                <xsl:text></xsl:text>
                                                                            </fo:inline>
                                                                            <fo:inline>
                                                                                <xsl:text>Subrecipient Institution (check one) will apply. If applying its own financial conflicts of interest policy, by execution of this Subaward Agreement, Subrecipient Institution certifies that its policy complies with 42 CFR Part 50.</xsl:text>
                                                                            </fo:inline>
                                                                        </fo:block>
                                                                        <fo:block>
                                                                            <xsl:text>&#x00A0;</xsl:text>
                                                                        </fo:block>
                                                                        <fo:block white-space-collapse="false"
                                                                                  white-space-treatment="preserve"
                                                                                  font-size="0pt"
                                                                                  line-height="15px"></fo:block>
                                                                    </fo:list-item-body>
                                                                </fo:list-item>
                                                                <fo:list-item>
                                                                    <fo:list-item-label end-indent="label-end()"
                                                                                        text-align="right">
                                                                        <fo:block>
                                                                            <xsl:number format="2" value="2"/>.
                                                                        </fo:block>
                                                                    </fo:list-item-label>
                                                                    <fo:list-item-body start-indent="body-start()">
                                                                        <fo:block>
                                                                            <fo:inline>
                                                                                <xsl:text>Subrecipient shall report any financial conflict of interest to Prime Recipients Administrative Representative, as designated on Attachment 3A. Any financial conflicts of interest identified shall subsequently be reported to NIH.</xsl:text>
                                                                            </fo:inline>
                                                                            <fo:inline font-size="10pt"
                                                                                       text-decoration="underline">
                                                                                <xsl:text>Such report shall be made before expenditure of funds authorized in this Subrecipient Agreement and within 45 days of any subsequently identified financial conflict of interest.</xsl:text>
                                                                            </fo:inline>
                                                                        </fo:block>
                                                                    </fo:list-item-body>
                                                                </fo:list-item>
                                                            </fo:list-block>
                                                        </fo:block>
                                                    </fo:list-item-body>
                                                </fo:list-item>
                                            </fo:list-block>
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
</xsl:when>
<xsl:otherwise>
    <fo:inline-container>
        <fo:block>
            <xsl:text>&#x2029;</xsl:text>
        </fo:block>
    </fo:inline-container>
</xsl:otherwise>
</xsl:choose>
        <!--  end of conditional FCOI-PHS block -->
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
','NIH Attachment.xsl','application/octet-stream',1,SYS_GUID(),2);

delete from SUBAWARD_FORMS WHERE FORM_ID = 'FDP_NSF';

INSERT INTO SUBAWARD_FORMS(FORM_ID,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,FORM,FILE_NAME,CONTENT_TYPE,VER_NBR,OBJ_ID,TEMPLATE_TYPE_CODE) values ('FDP_NSF',	'NSF Attachment',sysdate,'admin',
'<?xml version="1.0" encoding="UTF-8"?>
<!--Designed and generated by Altova StyleVision Enterprise Edition 2008 rel. 2 - see http://www.altova.com/stylevision for more information.-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:award="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/award" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:subcontract="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/subcontract" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:fo="http://www.w3.org/1999/XSL/Format">
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
							<fo:table font-family="Times New Roman" font-size="12pt" font-weight="bold" table-layout="fixed" width="100%" border="solid 1pt gray" border-spacing="-1pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell border="solid 1pt gray" padding="0" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:block text-align="center" margin="0pt">
													<fo:block>
														<fo:inline>
															<xsl:text>Attachment 2</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>Research Subaward Agreement</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>Prime Award Terms and Conditions</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>NSF</xsl:text>
														</fo:inline>
													</fo:block>
												</fo:block>
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
							<fo:table font-family="Times New Roman" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-size="12pt" font-weight="bold">
										<fo:table-cell padding="0" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Certifications/Assurances</xsl:text>
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row font-size="10pt" font-weight="normal">
										<fo:table-cell padding="0" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text> By signing this Research Subaward Agreement Subrecipient makes the certifications and assurances specified in the Research Terms and Conditions Appendix C found at </xsl:text>
												</fo:inline>
												<fo:basic-link text-decoration="underline" color="blue">
													<xsl:choose>
														<xsl:when test="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf&apos;), 1, 1) = ''#''">
															<xsl:attribute name="internal-destination">
																<xsl:value-of select="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf&apos;), 2)"/>
															</xsl:attribute>
														</xsl:when>
														<xsl:otherwise>
															<xsl:attribute name="external-destination">
																<xsl:text>url(</xsl:text>
																<xsl:call-template name="double-backslash">
																	<xsl:with-param name="text">
																		<xsl:value-of select="string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf&apos;)"/>
																	</xsl:with-param>
																	<xsl:with-param name="text-length">
																		<xsl:value-of select="string-length(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf&apos;))"/>
																	</xsl:with-param>
																</xsl:call-template>
																<xsl:text>)</xsl:text>
															</xsl:attribute>
														</xsl:otherwise>
													</xsl:choose>
													<fo:inline>
														<xsl:text>http://www.nsf.gov/bfa/dias/policy/rtc/appc_june11.pdf</xsl:text>
													</fo:inline>
												</fo:basic-link>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
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
							<fo:inline-container>
								<fo:block>
									<xsl:text>&#x2029;</xsl:text>
								</fo:block>
							</fo:inline-container>
							<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="28%"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-family="Times New Roman" font-size="12pt" font-weight="bold">
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>General terms and conditions </xsl:text>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell font-size="10pt" font-weight="normal" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>as of the effective date of this Research Subaward Agreement:</xsl:text>
												</fo:inline>
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
							<fo:table font-family="time" font-size="10pt" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="1"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>The restrictions on the expenditure of federal funds in appropriations acts are applicable to this subaward to the extent those restrictions are pertinent.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="2"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>OMB Circular A-110 or 45 CFR Part 602 as applicable.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="3"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>The Proposal and Award Policies and Procedures Guide, including addenda in effect as of the beginning date of the period of performance.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="4"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>Research Terms and Conditions found at </xsl:text>
																</fo:inline>
																<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf&apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text> http://www.nsf.gov/pubs/policydocs/rtc/termsidebyside_june11.pdf </xsl:text>
																	</fo:inline>
																</fo:basic-link>
																<fo:inline>
																	<xsl:text> and Agency Specific Requirements found at </xsl:text>
																</fo:inline>
																<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/nsf_212.pdf&apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/nsf_212.pdf&apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;nsf.gov/pubs/policydocs/rtc/nsf_212.pdf&apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;nsf.gov/pubs/policydocs/rtc/nsf_212.pdf&apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text>http://www.nsf.gov/pubs/policydocs/rtc/nsf_212.pdf</xsl:text>
																	</fo:inline>
																</fo:basic-link>
																<fo:inline>
																	<xsl:text>, except for the following:</xsl:text>
																</fo:inline>
																<fo:inline-container>
																	<fo:block>
																		<xsl:text>&#x2029;</xsl:text>
																	</fo:block>
																</fo:inline-container>
																<fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="1"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>The right to initiate an automatic one-time extension of the end date provided by Article 25(c)(2) is replaced by the need to obtain prior written approval from the Prime Recipient;</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="2"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>The payment mechanism described in Article 22 and the financial reporting requirements in Article 52 of the Research Terms and Conditions and Article 9 of the Agency-Specific Requirements are replaced with Terms and Conditions (1) through (4) of this agreement; and</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="3"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>Any prior approvals are to be sought from the Prime Recipient and not the Federal Awarding Agency.</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																</fo:list-block>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="5"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Title to equipment costing $5,000 or more that is purchased or fabricated with research funds or Subrecipient cost sharing&#160;&#160;&#160; funds, as direct costs of the project or program, shall unconditionally vest in the Subrecipient upon acquisition without further obligation to the Federal Awarding Agency subject to the conditions specified in Article 34(a) of the Research Terms and Conditions. </xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
												</fo:list-block>
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
							<fo:table font-family="time" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="28%"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell font-family="times" font-size="12pt" font-weight="bold" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Special terms and conditions: </xsl:text>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell font-family="time" font-size="10pt" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>&#160;</xsl:text>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>



									<fo:table-row font-family="time" font-size="10pt">
										<fo:table-cell font-family="times" font-size="12pt" font-weight="bold" number-columns-spanned="2" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:list-block font-family="time" font-size="10pt" font-weight="normal" provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="1"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Copyrights</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>Subrecipient </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
																		<fo:inline>
																			<xsl:text>grants</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
																		<fo:inline>
																			<xsl:text>shall grant</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text> to Prime Recipient an irrevocable, royalty-free, non-transferable, non-exclusive right and license to use, reproduce, make derivative works, display, and perform publicly any copyrights or copyrighted material (including any computer software and its documentation and/or databases) first developed and delivered under this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="2"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Data Rights</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>Subrecipient </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
																		<fo:inline>
																			<xsl:text>grants</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
																		<fo:inline>
																			<xsl:text>shall grant</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text> to Prime Recipient the right to use data created in the performance of this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award.</xsl:text>
																</fo:inline>

															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="3"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Automatic Carry Forward:&#160; </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;Y&apos;">
																		<fo:inline>
																			<xsl:text>Yes</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;N&apos;">
																		<fo:inline>
																			<xsl:text>No</xsl:text>
																		</fo:inline>
																		<fo:block/>
																		<fo:inline>
																			<xsl:text>Carry Forward requests must be sent to Prime Recipient''s - </xsl:text>
																		</fo:inline>
																		<xsl:for-each select="subcontract:SubContractData">
																			<xsl:for-each select="subcontract:SubcontractTemplateInfo">
																				<xsl:for-each select="subcontract:CarryForwardRequestsSentToDescription">
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
																		<fo:inline>
																			<xsl:text>, as shown in Attachment 3.</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
												</fo:list-block>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>



								</fo:table-body>
							</fo:table>
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
','NSF Attachment.xsl','application/octet-stream',1,SYS_GUID(),2);

delete from SUBAWARD_FORMS WHERE FORM_ID = 'FDP_ONR';

INSERT INTO SUBAWARD_FORMS(FORM_ID,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,FORM,FILE_NAME,CONTENT_TYPE,VER_NBR,OBJ_ID,TEMPLATE_TYPE_CODE) values ('FDP_ONR',	'ONR Attachment',sysdate,'admin',
'<?xml version="1.0" encoding="UTF-8"?>
<!--Designed and generated by Altova StyleVision Enterprise Edition 2008 rel. 2 - see http://www.altova.com/stylevision for more information.-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:award="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/award" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:subcontract="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/subcontract" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:fo="http://www.w3.org/1999/XSL/Format">
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
							<fo:table font-family="Times New Roman" font-size="12pt" font-weight="bold" table-layout="fixed" width="100%" border="solid 1pt gray" border-spacing="-1pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell border="solid 1pt gray" padding="0" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:block text-align="center" margin="0pt">
													<fo:block>
														<fo:inline>
															<xsl:text>Attachment 2</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>Research Subaward Agreement</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>Prime Award Terms and Conditions</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>ONR</xsl:text>
														</fo:inline>
													</fo:block>
												</fo:block>
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
							<fo:table font-family="Times New Roman" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-size="12pt" font-weight="bold">
										<fo:table-cell padding="0" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Certifications/Assurances</xsl:text>
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row font-size="10pt" font-weight="normal">
										<fo:table-cell padding="0" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text> By signing this Research Subaward Agreement Subrecipient makes the certifications and assurances specified in the Research Terms and Conditions Appendix C found at </xsl:text>
												</fo:inline>
												<fo:basic-link text-decoration="underline" color="blue">
													<xsl:choose>
														<xsl:when test="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf  &apos;), 1, 1) = ''#''">
															<xsl:attribute name="internal-destination">
																<xsl:value-of select="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf  &apos;), 2)"/>
															</xsl:attribute>
														</xsl:when>
														<xsl:otherwise>
															<xsl:attribute name="external-destination">
																<xsl:text>url(</xsl:text>
																<xsl:call-template name="double-backslash">
																	<xsl:with-param name="text">
																		<xsl:value-of select="string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf  &apos;)"/>
																	</xsl:with-param>
																	<xsl:with-param name="text-length">
																		<xsl:value-of select="string-length(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf  &apos;))"/>
																	</xsl:with-param>
																</xsl:call-template>
																<xsl:text>)</xsl:text>
															</xsl:attribute>
														</xsl:otherwise>
													</xsl:choose>
													<fo:inline>
														<xsl:text>http://www.nsf.gov/bfa/dias/policy/rtc/appc.pdf</xsl:text>
													</fo:inline>
												</fo:basic-link>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
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
							<fo:inline-container>
								<fo:block>
									<xsl:text>&#x2029;</xsl:text>
								</fo:block>
							</fo:inline-container>
							<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="28%"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-family="Times New Roman" font-size="12pt" font-weight="bold">
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>General terms and conditions </xsl:text>
												</fo:inline>
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
							<fo:table font-family="time" font-size="10pt" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="1"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>The restrictions on the expenditure of federal funds in appropriations acts are applicable to this subaward to the extent those restrictions are pertinent.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="2"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>DoDGARS Part 32 </xsl:text>
																</fo:inline>
																<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;dtic.mil/whs/directives/corres/pdf/321006r32p.pdf &apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;dtic.mil/whs/directives/corres/pdf/321006r32p.pdf &apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;dtic.mil/whs/directives/corres/pdf/321006r32p.pdf &apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;dtic.mil/whs/directives/corres/pdf/321006r32p.pdf &apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text>http://www.dtic.mil/whs/directives/corres/pdf/321006r32p.pdf </xsl:text>
																	</fo:inline>
																</fo:basic-link>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="3"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>Research Terms and Conditions found at </xsl:text>
																</fo:inline>
																<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf &apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf &apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf &apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf &apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text>http://www.nsf.gov/bfa/dias/policy/rtc/terms.pdf </xsl:text>
																	</fo:inline>
																</fo:basic-link>
																<fo:inline>
																	<xsl:text> and Agency Specific Requirements found at </xsl:text>
																</fo:inline>
																<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/onr_708.pdf&apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/onr_708.pdf&apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;nsf.gov/pubs/policydocs/rtc/onr_708.pdf&apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;nsf.gov/pubs/policydocs/rtc/onr_708.pdf&apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text>http://www.nsf.gov/pubs/policydocs/rtc/onr_708.pdf</xsl:text>
																	</fo:inline>
																</fo:basic-link>
																<fo:inline>
																	<xsl:text>, except for the following:</xsl:text>
																</fo:inline>
																<fo:inline-container>
																	<fo:block>
																		<xsl:text>&#x2029;</xsl:text>
																	</fo:block>
																</fo:inline-container>
																<fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="1"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>The right to initiate an automatic one-time extension of the end date provided by Article 25(c)2 is replaced by the need to obtain prior written approval from the Prime Recipient;</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="2"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>The payment mechanism described in Article 22 and the financial reporting requirements in Article 52 of the Research Terms and Conditions and Articles 9 and 11 of the Agency-Specific Requirements are replaced with Terms and Conditions (1) through (4) of this agreement; and</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="3"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>Any prior approvals are to be sought from the Prime Recipient and not the Federal Awarding Agency.</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																</fo:list-block>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="4"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Title to equipment costing $5,000 or more that is purchased or fabricated with research funds or Subrecipient cost sharing&#160;&#160;&#160; funds, as direct costs of the project or program, shall unconditionally vest in the Subrecipient upon acquisition without further obligation to the Federal Awarding Agency subject to the conditions specified in Article 34(a) of the Research Terms and Conditions</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
												</fo:list-block>
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
							<fo:table font-family="time" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="28%"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell font-family="times" font-size="12pt" font-weight="bold" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Special terms and conditions: </xsl:text>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell font-family="time" font-size="10pt" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>&#160;</xsl:text>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row font-family="time" font-size="10pt">
										<fo:table-cell font-family="times" font-size="12pt" font-weight="bold" number-columns-spanned="2" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:list-block font-family="time" font-size="10pt" font-weight="normal" provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="1"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Copyrights</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>Subrecipient </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
																		<fo:inline>
																			<xsl:text>grants</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
																		<fo:inline>
																			<xsl:text>shall grant</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text> to Prime Recipient an irrevocable, royalty-free, non-transferable, non-exclusive right and license to use, reproduce, make derivative works, display, and perform publicly any copyrights or copyrighted material (including any computer software and its documentation and/or databases) first developed and delivered under this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="2"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Data Rights</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>Subrecipient </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
																		<fo:inline>
																			<xsl:text>grants</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
																		<fo:inline>
																			<xsl:text>shall grant</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text> to Prime Recipient the right to use data created in the performance of this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award.</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>[Do not add a Patent or Inventions Clause. The prime award governs rights to patents and inventions. Prime Recipient cannot obtain rights in the Subrecipient''s subject inventions as a part of consideration for the subaward.&#160; Should it be necessary, the Federal Government can authorize the Prime Recipient''s right to practice a Subrecipient''s subject invention (as well as subject data or copyrights) on behalf of the Federal Government.]</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="3"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Automatic Carry Forward:&#160; </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;Y&apos;">
																		<fo:inline>
																			<xsl:text>Yes</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;N&apos;">
																		<fo:inline>
																			<xsl:text>No</xsl:text>
																		</fo:inline>
																		<fo:block/>
																		<fo:inline>
																			<xsl:text>Carry Forward requests must be sent to Prime Recipient''s - </xsl:text>
																		</fo:inline>
																		<xsl:for-each select="subcontract:SubContractData">
																			<xsl:for-each select="subcontract:SubcontractTemplateInfo">
																				<xsl:for-each select="subcontract:CarryForwardRequestsSentToDescription">
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
																		<fo:inline>
																			<xsl:text>, as shown in Attachment 3.</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text>&#160;&#160;&#160;&#160; </xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
												</fo:list-block>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
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
','ONR Attachment.xsl','application/octet-stream',1,SYS_GUID(),2);
delete from SUBAWARD_FORMS WHERE FORM_ID = 'FDP_USDA';

INSERT INTO SUBAWARD_FORMS(FORM_ID,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,FORM,FILE_NAME,CONTENT_TYPE,VER_NBR,OBJ_ID,TEMPLATE_TYPE_CODE) values ('FDP_USDA',	'USDA Attachment',sysdate,'admin',
'<?xml version="1.0" encoding="UTF-8"?>
<!--Designed and generated by Altova StyleVision Enterprise Edition 2008 rel. 2 - see http://www.altova.com/stylevision for more information.-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:award="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/award" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:subcontract="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/subcontract" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:fo="http://www.w3.org/1999/XSL/Format">
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
							<fo:table font-family="Times New Roman" font-size="12pt" font-weight="bold" table-layout="fixed" width="100%" border="solid 1pt gray" border-spacing="-1pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell border="solid 1pt gray" padding="0" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:block text-align="center" margin="0pt">
													<fo:block>
														<fo:inline>
															<xsl:text>Attachment 2</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>Research Subaward Agreement</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>Prime Award Terms and Conditions</xsl:text>
														</fo:inline>
														<fo:block/>
														<fo:inline>
															<xsl:text>USDA</xsl:text>
														</fo:inline>
													</fo:block>
												</fo:block>
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
							<fo:table font-family="Times New Roman" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-size="12pt" font-weight="bold">
										<fo:table-cell padding="0" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Agency-Specific Certifications/Assurances</xsl:text>
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row font-size="10pt" font-weight="normal">
										<fo:table-cell padding="0" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text> By signing this Research Subaward Agreement Subrecipient makes the certifications and assurances specified in the Research Terms and Conditions Appendix C found at </xsl:text>
												</fo:inline>
												<fo:basic-link text-decoration="underline" color="blue">
													<xsl:choose>
														<xsl:when test="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf  &apos;), 1, 1) = ''#''">
															<xsl:attribute name="internal-destination">
																<xsl:value-of select="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf  &apos;), 2)"/>
															</xsl:attribute>
														</xsl:when>
														<xsl:otherwise>
															<xsl:attribute name="external-destination">
																<xsl:text>url(</xsl:text>
																<xsl:call-template name="double-backslash">
																	<xsl:with-param name="text">
																		<xsl:value-of select="string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf  &apos;)"/>
																	</xsl:with-param>
																	<xsl:with-param name="text-length">
																		<xsl:value-of select="string-length(string(&apos;nsf.gov/bfa/dias/policy/rtc/appc.pdf  &apos;))"/>
																	</xsl:with-param>
																</xsl:call-template>
																<xsl:text>)</xsl:text>
															</xsl:attribute>
														</xsl:otherwise>
													</xsl:choose>
													<fo:inline>
														<xsl:text>http://www.nsf.gov/bfa/dias/policy/rtc/appc.pdf</xsl:text>
													</fo:inline>
												</fo:basic-link>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
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
							<fo:inline-container>
								<fo:block>
									<xsl:text>&#x2029;</xsl:text>
								</fo:block>
							</fo:inline-container>
							<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="28%"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-family="Times New Roman" font-size="12pt" font-weight="bold">
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>General terms and conditions </xsl:text>
												</fo:inline>
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
							<fo:table font-family="time" font-size="10pt" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="1"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>The restrictions on the expenditure of federal funds in appropriations acts are applicable to this subaward to the extent those restrictions are pertinent.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="2"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>7 CFR Part 3015, 3017, 3018 and 3019.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="3"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block font-family="time" font-size="10pt" font-weight="normal">
																<fo:inline>
																	<xsl:text>Research Terms and Conditions found at </xsl:text>
																</fo:inline>
																<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf&apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf&apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf&apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;nsf.gov/bfa/dias/policy/rtc/terms.pdf&apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text>http://www.nsf.gov/bfa/dias/policy/rtc/terms.pdf</xsl:text>
																	</fo:inline>
																</fo:basic-link>
																<fo:inline>
																	<xsl:text> and Agency Specific Requirements found at </xsl:text>
																</fo:inline>
																<fo:basic-link text-decoration="underline" color="blue">
																	<xsl:choose>
																		<xsl:when test="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/csrees_708.pdf&apos;), 1, 1) = ''#''">
																			<xsl:attribute name="internal-destination">
																				<xsl:value-of select="substring(string(&apos;nsf.gov/pubs/policydocs/rtc/csrees_708.pdf&apos;), 2)"/>
																			</xsl:attribute>
																		</xsl:when>
																		<xsl:otherwise>
																			<xsl:attribute name="external-destination">
																				<xsl:text>url(</xsl:text>
																				<xsl:call-template name="double-backslash">
																					<xsl:with-param name="text">
																						<xsl:value-of select="string(&apos;nsf.gov/pubs/policydocs/rtc/csrees_708.pdf&apos;)"/>
																					</xsl:with-param>
																					<xsl:with-param name="text-length">
																						<xsl:value-of select="string-length(string(&apos;nsf.gov/pubs/policydocs/rtc/csrees_708.pdf&apos;))"/>
																					</xsl:with-param>
																				</xsl:call-template>
																				<xsl:text>)</xsl:text>
																			</xsl:attribute>
																		</xsl:otherwise>
																	</xsl:choose>
																	<fo:inline>
																		<xsl:text>http://www.nsf.gov/pubs/policydocs/rtc/csrees_708.pdf</xsl:text>
																	</fo:inline>
																</fo:basic-link>
																<fo:inline>
																	<xsl:text>, except for the following:</xsl:text>
																</fo:inline>
																<fo:inline-container>
																	<fo:block>
																		<xsl:text>&#x2029;</xsl:text>
																	</fo:block>
																</fo:inline-container>
																<fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="1"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>The right to initiate an automatic one-time extension of the end date provided by Article 25 (c)(2) is replaced by the need to obtain prior written approval from the Prime Recipient;</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="2"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>The payment mechanism described in Article 22 and the financial reporting requirements in Article 52 of the Research Terms and Conditions and Article 10 of the Agency-Specific Requirements are replaced with Terms and Conditions (1) through (4) of this agreement; and</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																	<fo:list-item>
																		<fo:list-item-label end-indent="label-end()" text-align="right">
																			<fo:block>
																				<xsl:number format="a" value="3"/>.</fo:block>
																		</fo:list-item-label>
																		<fo:list-item-body start-indent="body-start()">
																			<fo:block>
																				<fo:inline>
																					<xsl:text>Any prior approvals are to be sought from the Prime Recipient and not the Federal Awarding Agency.</xsl:text>
																				</fo:inline>
																			</fo:block>
																		</fo:list-item-body>
																	</fo:list-item>
																</fo:list-block>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="4"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Title to equipment costing $5,000 or more that is purchased or fabricated with research funds or Subrecipient cost sharing&#160;&#160;&#160; funds, as direct costs of the project or program, shall unconditionally vest in the Subrecipient upon acquisition without further obligation to the Federal Awarding Agency subject to the conditions specified in Article 34(a) of the Research Terms and Conditions.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
												</fo:list-block>
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
							<fo:table font-family="time" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="28%"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell font-family="times" font-size="12pt" font-weight="bold" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>Special terms and conditions: </xsl:text>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell font-family="time" font-size="10pt" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline>
													<xsl:text>&#160;</xsl:text>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row font-family="time" font-size="10pt">
										<fo:table-cell font-family="times" font-size="12pt" font-weight="bold" number-columns-spanned="2" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:list-block font-family="time" font-size="10pt" font-weight="normal" provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="1"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Copyrights</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>Subrecipient </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
																		<fo:inline>
																			<xsl:text>grants</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
																		<fo:inline>
																			<xsl:text>shall grant</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text> to Prime Recipient an irrevocable, royalty-free, non-transferable, non-exclusive right and license to use, reproduce, make derivative works, display, and perform publicly any copyrights or copyrighted material (including any computer software and its documentation and/or databases) first developed and delivered under this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award.</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="2"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Data Rights</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>Subrecipient </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;1&quot;">
																		<fo:inline>
																			<xsl:text>grants</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:CopyRights = &quot;2&quot;">
																		<fo:inline>
																			<xsl:text>shall grant</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text> to Prime Recipient the right to use data created in the performance of this Subaward Agreement solely for the purpose of and only to the extent required to meet Prime Recipient''s obligations to the Federal Government under its Prime Award.</xsl:text>
																</fo:inline>
																<fo:block/>
																<fo:inline>
																	<xsl:text>[Do not add a Patent or Inventions Clause. The prime award governs rights to patents and inventions. Prime Recipient cannot obtain rights in the Subrecipient''s subject inventions as a part of consideration for the subaward.&#160; Should it be necessary, the Federal Government can authorize the Prime Recipient''s right to practice a Subrecipient''s subject invention (as well as subject data or copyrights) on behalf of the Federal Government.]</xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
													<fo:list-item>
														<fo:list-item-label end-indent="label-end()" text-align="right">
															<fo:block>
																<xsl:number format="1" value="3"/>.</fo:block>
														</fo:list-item-label>
														<fo:list-item-body start-indent="body-start()">
															<fo:block>
																<fo:inline>
																	<xsl:text>Automatic Carry Forward:&#160; </xsl:text>
																</fo:inline>
																<xsl:choose>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;Y&apos;">
																		<fo:inline>
																			<xsl:text>Yes</xsl:text>
																		</fo:inline>
																	</xsl:when>
																	<xsl:when test="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:AutomaticCarryForward = &apos;N&apos;">
																		<fo:inline>
																			<xsl:text>No</xsl:text>
																		</fo:inline>
																		<fo:block/>
																		<fo:inline>
																			<xsl:text>Carry Forward requests must be sent to Prime Recipient''s - </xsl:text>
																		</fo:inline>
																		<xsl:for-each select="subcontract:SubContractData">
																			<xsl:for-each select="subcontract:SubcontractTemplateInfo">
																				<xsl:for-each select="subcontract:CarryForwardRequestsSentToDescription">
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
																		<fo:inline>
																			<xsl:text>, as shown in Attachment 3.</xsl:text>
																		</fo:inline>
																	</xsl:when>
																</xsl:choose>
																<fo:inline>
																	<xsl:text>&#160;&#160;&#160;&#160; </xsl:text>
																</fo:inline>
															</fo:block>
														</fo:list-item-body>
													</fo:list-item>
												</fo:list-block>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
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
','USDA Attachment.xsl','application/octet-stream',1,SYS_GUID(),2);