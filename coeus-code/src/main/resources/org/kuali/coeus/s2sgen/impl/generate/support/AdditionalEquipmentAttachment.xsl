<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:n1="http://apply.grants.gov/coeus/AdditionalEquipment" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xsl:variable name="fo:layout-master-set">
        <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.6in" margin-right="0.6in">
                <fo:region-body margin-top="0.79in" margin-bottom="0.79in" />
            </fo:simple-page-master>
        </fo:layout-master-set>
    </xsl:variable>
    <xsl:output version="1.0" encoding="UTF-8" indent="no" omit-xml-declaration="no" media-type="text/html" />
    <xsl:template match="/">
        <fo:root>
            <xsl:copy-of select="$fo:layout-master-set" />
            <fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <fo:block text-align="center" space-before.optimum="1pt" space-after.optimum="2pt">
                            <fo:block>
                                <fo:inline font-size="14pt" font-weight="bold">Extra Equipment for Proposal </fo:inline>
                                <xsl:for-each select="n1:AdditionalEquipmentList">
                                    <xsl:for-each select="n1:ProposalNumber">
                                        <fo:inline font-size="14pt" font-weight="bold">
                                            <xsl:apply-templates />
                                        </fo:inline>
                                    </xsl:for-each>
                                </xsl:for-each>
                            </fo:block>
                        </fo:block>
                        <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                            <fo:block text-align="center">
                                <fo:inline font-size="14pt" font-weight="bold">Budget Period</fo:inline>
                                <fo:inline font-size="14pt">&#160; </fo:inline>
                                <xsl:for-each select="n1:AdditionalEquipmentList">
                                    <xsl:for-each select="n1:BudgetPeriod">
                                        <fo:inline font-size="14pt" font-weight="bold">
                                            <xsl:apply-templates />
                                        </fo:inline>
                                    </xsl:for-each>
                                </xsl:for-each>
                            </fo:block>
                        </fo:block>
                        <xsl:for-each select="n1:AdditionalEquipmentList">
                            <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-header>
                                    <fo:table-row>
                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                            <fo:block>
                                                <fo:inline font-weight="bold">Equipment Item</fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                            <fo:block>
                                                <fo:inline font-weight="bold">Funds Requested</fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-header>
                                <fo:table-body>
                                    <xsl:for-each select="n1:EquipmentList">
                                        <fo:table-row>
                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                <fo:block>
                                                    <xsl:for-each select="n1:EquipmentItem">
                                                        <xsl:apply-templates />
                                                    </xsl:for-each>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                <fo:block>
                                                    <xsl:for-each select="n1:FundsRequested">$<xsl:value-of select="format-number(number(string(.)), '#,###,###,##0.00')" />
                                                    </xsl:for-each>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </xsl:for-each>
                                </fo:table-body>
                            </fo:table>
                        </xsl:for-each>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
