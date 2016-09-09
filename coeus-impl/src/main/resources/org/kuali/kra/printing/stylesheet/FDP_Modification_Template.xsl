<?xml version="1.0" encoding="UTF-8"?>
<!--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   -
   - Copyright 2005-2016 Kuali, Inc.
   -
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   -
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   -
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
 -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:award="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/award" xmlns:subcontract="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/subcontract" xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output version="1.0" method="xml" encoding="UTF-8" indent="no"/>
    <xsl:param name="SV_OutputFormat" select="'PDF'"/>
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
                                                                                                        <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                                        <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                                        <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                                        <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                                        <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                                        <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                                        <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                                    <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                                        <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                                        <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                                        <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                                        <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                                        <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                                        <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                        <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                        <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                        <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                        <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                                <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00')"/>
                                                                                                <xsl:text>/</xsl:text>
                                                                                                <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00')"/>
                                                                                                <xsl:text>/</xsl:text>
                                                                                                <xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), '0000')"/>
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
                                                                                                <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00')"/>
                                                                                                <xsl:text>/</xsl:text>
                                                                                                <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00')"/>
                                                                                                <xsl:text>/</xsl:text>
                                                                                                <xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), '0000')"/>
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
                                                                                                <xsl:value-of select="format-number(number(string(.)), '###,##0.00')"/>
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
                                                                                                <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                                <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
                                                                                                <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00')"/>
                                                                                                <xsl:text>/</xsl:text>
                                                                                                <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00')"/>
                                                                                                <xsl:text>/</xsl:text>
                                                                                                <xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), '0000')"/>
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
                                                                                                <xsl:value-of select="format-number(number(string(.)), '###,##0.00')"/>
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
                                                                                                        <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00')"/>
                                                                                                        <xsl:text>/</xsl:text>
                                                                                                        <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00')"/>
                                                                                                        <xsl:text>/</xsl:text>
                                                                                                        <xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), '0000')"/>
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
                                                                                                            <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00')"/>
                                                                                                            <xsl:text>/</xsl:text>
                                                                                                            <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00')"/>
                                                                                                            <xsl:text>/</xsl:text>
                                                                                                            <xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), '0000')"/>
                                                                                                        </fo:inline>
                                                                                                    </xsl:for-each>
                                                                                                </xsl:for-each>
                                                                                            </xsl:for-each>
                                                                                            <xsl:text> - </xsl:text>
                                                                                            <xsl:for-each select="subcontract:SubContractData">
                                                                                                <xsl:for-each select="subcontract:SubcontractDetail">
                                                                                                    <xsl:for-each select="subcontract:EndDate">
                                                                                                        <fo:inline>
                                                                                                            <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00')"/>
                                                                                                            <xsl:text>/</xsl:text>
                                                                                                            <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00')"/>
                                                                                                            <xsl:text>/</xsl:text>
                                                                                                            <xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), '0000')"/>
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
                                                                                                            <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00')"/>
                                                                                                            <xsl:text>/</xsl:text>
                                                                                                            <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00')"/>
                                                                                                            <xsl:text>/</xsl:text>
                                                                                                            <xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), '0000')"/>
                                                                                                        </fo:inline>
                                                                                                    </xsl:for-each>
                                                                                                </xsl:for-each>
                                                                                            </xsl:for-each>
                                                                                            <xsl:text> - </xsl:text>
                                                                                            <xsl:for-each select="subcontract:SubContractData">
                                                                                                <xsl:for-each select="subcontract:SubcontractAmountInfo">
                                                                                                    <xsl:for-each select="subcontract:PerformanceEndDate">
                                                                                                        <fo:inline>
                                                                                                            <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00')"/>
                                                                                                            <xsl:text>/</xsl:text>
                                                                                                            <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00')"/>
                                                                                                            <xsl:text>/</xsl:text>
                                                                                                            <xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), '0000')"/>
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
                                                                                    <xsl:when test="contains(string($value-of-template),'&#x2029;')">
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
        <xsl:variable name="text-after-bs" select="substring-after($text, '\')"/>
        <xsl:variable name="text-after-bs-length" select="string-length($text-after-bs)"/>
        <xsl:choose>
            <xsl:when test="$text-after-bs-length = 0">
                <xsl:choose>
                    <xsl:when test="substring($text, $text-length) = '\'">
                        <xsl:value-of select="concat(substring($text,1,$text-length - 1), '\\')"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="$text"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="concat(substring($text,1,$text-length - $text-after-bs-length - 1), '\\')"/>
                <xsl:call-template name="double-backslash">
                    <xsl:with-param name="text" select="$text-after-bs"/>
                    <xsl:with-param name="text-length" select="$text-after-bs-length"/>
                </xsl:call-template>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
</xsl:stylesheet>
