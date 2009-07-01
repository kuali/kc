<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:ns1="http://apply.grants.gov/coeus/ExtraKeyPerson" xmlns:xs="http://www.w3.org/2001/XMLSchema">
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
                                <fo:inline font-weight="bold">Additional Key Personnel for Proposal</fo:inline>&#160;<xsl:for-each select="ns1:ExtraKeyPersonList">
                                    <xsl:for-each select="ns1:ProposalNumber">
                                        <fo:inline font-weight="bold">
                                            <xsl:apply-templates />
                                        </fo:inline>
                                    </xsl:for-each>
                                </xsl:for-each>
                            </fo:block>
                        </fo:block>
                        <fo:block>
                            <xsl:text>&#xA;</xsl:text>
                        </fo:block>
                        <fo:block text-align="center" space-before.optimum="1pt" space-after.optimum="2pt">
                            <fo:block>
                                <fo:inline font-weight="bold">Budget Period</fo:inline>&#160; <xsl:for-each select="ns1:ExtraKeyPersonList">
                                    <xsl:for-each select="ns1:BudgetPeriod">
                                        <fo:inline font-weight="bold">
                                            <xsl:apply-templates />
                                        </fo:inline>
                                    </xsl:for-each>
                                </xsl:for-each>
                            </fo:block>
                        </fo:block>&#160;<fo:block>
                            <fo:leader leader-pattern="space" />
                        </fo:block>
                        <fo:block>
                            <fo:leader leader-pattern="space" />
                        </fo:block>
                        <xsl:for-each select="ns1:ExtraKeyPersonList">
                            <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                <fo:table-column column-width="proportional-column-width(15)" />
                                <fo:table-column column-width="proportional-column-width(85)" />
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-body>
                                    <xsl:for-each select="ns1:KeyPersons">
                                        <fo:table-row>
                                            <fo:table-cell border-top-width="3pt" border-style="solid" border-width="1pt" border-color="black" width="15%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                <fo:block>
                                                    <fo:inline font-size="9pt" font-weight="bold">Name</fo:inline>
                                                    <fo:inline font-size="9pt">:</fo:inline>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell border-top-width="3pt" border-style="solid" border-width="1pt" border-color="black" number-columns-spanned="3" width="85%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                <fo:block>
                                                    <xsl:for-each select="ns1:FirstName">
                                                        <fo:inline font-size="9pt">
                                                            <xsl:apply-templates />
                                                        </fo:inline>
                                                    </xsl:for-each>
                                                    <fo:inline font-size="9pt">&#160;</fo:inline>
                                                    <xsl:for-each select="ns1:MiddleName">
                                                        <fo:inline font-size="9pt">
                                                            <xsl:apply-templates />
                                                        </fo:inline>
                                                    </xsl:for-each>
                                                    <fo:inline font-size="9pt">&#160;</fo:inline>
                                                    <xsl:for-each select="ns1:LastName">
                                                        <fo:inline font-size="9pt">
                                                            <xsl:apply-templates />
                                                        </fo:inline>
                                                    </xsl:for-each>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                        <fo:table-row>
                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="black" width="15%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                <fo:block>
                                                    <fo:inline font-size="9pt" font-weight="bold">Project Role:</fo:inline>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="black" number-columns-spanned="3" width="85%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                <fo:block>
                                                    <xsl:for-each select="ns1:ProjectRole">
                                                        <fo:inline font-size="9pt">
                                                            <xsl:apply-templates />
                                                        </fo:inline>
                                                    </xsl:for-each>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                        <fo:table-row>
                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="black" width="15%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                <fo:block>
                                                    <fo:inline font-size="9pt" font-weight="bold">Compensation</fo:inline>
                                                    <fo:inline font-size="9pt">:</fo:inline>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="black" number-columns-spanned="3" width="85%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                <fo:block>
                                                    <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                        <fo:table-column />
                                                        <fo:table-column />
                                                        <fo:table-column />
                                                        <fo:table-column />
                                                        <fo:table-column />
                                                        <fo:table-column />
                                                        <fo:table-column />
                                                        <fo:table-header>
                                                            <fo:table-row>
                                                                <fo:table-cell border-bottom-width="0pt" border-left-width="0pt" border-top-width="0pt" border-style="solid" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                    <fo:block>
                                                                        <fo:inline font-size="9pt">Cal Months</fo:inline>
                                                                    </fo:block>
                                                                </fo:table-cell>
                                                                <fo:table-cell border-bottom-width="0pt" border-top-width="0pt" border-style="solid" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                    <fo:block>
                                                                        <fo:inline font-size="9pt">Sum Months</fo:inline>
                                                                    </fo:block>
                                                                </fo:table-cell>
                                                                <fo:table-cell border-top-width="0pt" border-style="solid" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                    <fo:block>
                                                                        <fo:inline font-size="9pt">Acad Months</fo:inline>
                                                                    </fo:block>
                                                                </fo:table-cell>
                                                                <fo:table-cell border-top-width="0pt" border-style="solid" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                    <fo:block>
                                                                        <fo:inline font-size="9pt">Req Salary</fo:inline>
                                                                    </fo:block>
                                                                </fo:table-cell>
                                                                <fo:table-cell border-top-width="0pt" border-style="solid" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                    <fo:block>
                                                                        <fo:inline font-size="9pt">Fringe Benefits</fo:inline>
                                                                    </fo:block>
                                                                </fo:table-cell>
                                                                <fo:table-cell border-top-width="0pt" border-style="solid" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                    <fo:block>
                                                                        <fo:inline font-size="9pt">Funds Requested</fo:inline>
                                                                    </fo:block>
                                                                </fo:table-cell>
                                                                <fo:table-cell border-right-width="0pt" border-top-width="0pt" border-style="solid" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                    <fo:block>
                                                                        <fo:inline font-size="9pt">Base Salary</fo:inline>
                                                                    </fo:block>
                                                                </fo:table-cell>
                                                            </fo:table-row>
                                                        </fo:table-header>
                                                        <fo:table-body>
                                                            <xsl:for-each select="ns1:Compensation">
                                                                <fo:table-row>
                                                                    <fo:table-cell border-bottom-width="0pt" border-left-width="0pt" border-style="solid" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                        <fo:block>
                                                                            <xsl:for-each select="ns1:CalendarMonths">
                                                                                <fo:inline font-size="9pt">
                                                                                    <xsl:apply-templates />
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-bottom-width="0pt" border-style="solid" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                        <fo:block>
                                                                            <xsl:for-each select="ns1:SummerMonths">
                                                                                <fo:inline font-size="9pt">
                                                                                    <xsl:apply-templates />
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-bottom-width="0pt" border-style="solid" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                        <fo:block>
                                                                            <xsl:for-each select="ns1:AcademicMonths">
                                                                                <fo:inline font-size="9pt">
                                                                                    <xsl:apply-templates />
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-bottom-width="0pt" font-size="9pt" border-style="solid" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                        <fo:block>$<xsl:for-each select="ns1:RequestedSalary">
                                                                                <fo:inline font-size="9pt">
                                                                                    <xsl:value-of select="format-number(number(string(.)), '#,###,###,##0.00')" />
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-bottom-width="0pt" font-size="9pt" border-style="solid" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                        <fo:block>$<xsl:for-each select="ns1:FringeBenefits">
                                                                                <fo:inline font-size="9pt">
                                                                                    <xsl:value-of select="format-number(number(string(.)), '#,###,###,##0.00')" />
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-bottom-width="0pt" font-size="9pt" border-style="solid" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                        <fo:block>$<xsl:for-each select="ns1:FundsRequested">
                                                                                <fo:inline font-size="9pt">
                                                                                    <xsl:value-of select="format-number(number(string(.)), '#,###,###,##0.00')" />
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-bottom-width="0pt" border-right-width="0pt" font-size="9pt" border-style="solid" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                        <fo:block>$<xsl:for-each select="ns1:BaseSalary">
                                                                                <fo:inline font-size="9pt">
                                                                                    <xsl:value-of select="format-number(number(string(.)), '#,###,###,##0.00')" />
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                </fo:table-row>
                                                            </xsl:for-each>
                                                        </fo:table-body>
                                                    </fo:table>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </xsl:for-each>
                                </fo:table-body>
                            </fo:table>
                        </xsl:for-each>
                        <fo:block>
                            <xsl:text>&#xA;</xsl:text>
                        </fo:block>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
