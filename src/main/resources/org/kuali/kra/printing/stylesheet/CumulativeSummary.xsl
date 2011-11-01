<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
    <xsl:variable name="fo:layout-master-set">
        <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.6in" margin-right="1.25in">
                <fo:region-body margin-top="1.9in" margin-bottom=".74in" />
                <fo:region-before extent="1.85in" />
                <fo:region-after extent=".74in" />
            </fo:simple-page-master>
        </fo:layout-master-set>
    </xsl:variable>
    <xsl:output version="1.0" encoding="UTF-8" indent="no" omit-xml-declaration="no" media-type="text/html" />
    <xsl:template match="/">
        <fo:root>
            <xsl:copy-of select="$fo:layout-master-set" />
            <fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block>
                        <xsl:variable name="parentTypeName" select="BudgetSummaryReport/ReportHeader/parentTypeName"/>
    	
                        <fo:table width="520pt" space-before.optimum="1pt" space-after.optimum="2pt">
                            <fo:table-column/>
                            <fo:table-column/>
                            <fo:table-column column-width="150pt" />
                            <fo:table-column/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell  padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" height="30pt" number-columns-spanned="4" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                        <fo:block>
                                            <fo:block>
                                                <fo:leader leader-pattern="space" />
                                            </fo:block>
                                            <fo:inline font-size="12.0pt" font-weight="bold">Coeus </fo:inline>
                                            <fo:inline font-size="12.0pt" font-weight="bold"> <xsl:value-of select="$parentTypeName" /> </fo:inline>
                                            <fo:inline font-size="12.0pt" font-weight="bold"> Development - Cumulative Budget Summary</fo:inline>
                                            <fo:block>
                                                <fo:leader leader-pattern="space" />
                                            </fo:block>
                                            <fo:block>
                                                <fo:leader leader-pattern="space" />
                                            </fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell font-size="inherited-property-value(&apos;font-size&apos;) - 2pt"  padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" text-align="left" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                        <fo:block>
                                            <fo:inline font-size="9.0pt" font-weight="bold"><xsl:value-of select="$parentTypeName" /> Number </fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="inherited-property-value(&apos;font-size&apos;) - 2pt"  padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" text-align="left" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                        <fo:block>
                                            <xsl:for-each select="BudgetSummaryReport">
                                                <xsl:for-each select="ReportHeader">
                                                    <xsl:for-each select="ProposalNumber">
                                                        <fo:inline font-size="9.0pt">
                                                            <xsl:apply-templates />
                                                        </fo:inline>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="inherited-property-value(&apos;font-size&apos;) - 2pt"  padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" text-align="right" width="150pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                        <fo:block>
                                            <fo:inline font-size="9.5pt" font-weight="bold">Budget Version :</fo:inline>
                                            <fo:inline font-size="4mm" font-weight="bold">&#160; </fo:inline>
                                            <xsl:for-each select="BudgetSummaryReport">
                                                <xsl:for-each select="ReportHeader">
                                                    <xsl:for-each select="BudgetVersion">
                                                        <fo:inline font-size="9.0pt">
                                                            <xsl:apply-templates />
                                                        </fo:inline>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell  padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                        <fo:block>
                                            <fo:inline font-size="9.5pt" font-weight="bold">Project</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                        <fo:block>
                                            <xsl:for-each select="BudgetSummaryReport">
                                                <xsl:for-each select="ReportHeader">
                                                    <xsl:for-each select="PeriodStartDate">
                                                        <fo:inline font-size="9.0pt">
                                                            <xsl:apply-templates />
                                                        </fo:inline>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                            <fo:inline font-size="9.0pt"> - </fo:inline>
                                            <xsl:for-each select="BudgetSummaryReport">
                                                <xsl:for-each select="ReportHeader">
                                                    <xsl:for-each select="PeriodEndDate">
                                                        <fo:inline font-size="9.0pt">
                                                            <xsl:apply-templates />
                                                        </fo:inline>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell  padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" width="235pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                        <fo:block>
                                            <fo:inline font-size="9.5pt" font-weight="bold">Investigator&#160; </fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                        <fo:block>
                                            <xsl:for-each select="BudgetSummaryReport">
                                                <xsl:for-each select="ReportHeader">
                                                    <xsl:for-each select="PIName">
                                                        <fo:inline font-size="9.0pt">
                                                            <xsl:apply-templates />
                                                        </fo:inline>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell  padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" width="235pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                        <fo:block>
                                            <fo:inline font-size="9.5pt" font-weight="bold"><xsl:value-of select="$parentTypeName" /> Title</fo:inline>
                                            <fo:inline font-size="9.5pt">&#160;</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start" wrap-option="no-wrap" overflow="hidden" width="200pt">
                                        <fo:block>
                                            <xsl:for-each select="BudgetSummaryReport">
                                                <xsl:for-each select="ReportHeader">
                                                    <xsl:for-each select="ProposalTitle">
                                                        <fo:inline font-size="9.0pt">
                                                            <xsl:apply-templates />
                                                        </fo:inline>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                 <fo:table-row>
                                    <fo:table-cell  padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" width="235pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                        <fo:block>
                                            <fo:inline font-size="9.5pt" font-weight="bold">Comments&#160; </fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                        <fo:block>
                                            <xsl:for-each select="BudgetSummaryReport">
                                                <xsl:for-each select="ReportHeader">
                                                    <xsl:for-each select="Comments">
                                                        <fo:inline font-size="9.0pt">
                                                            <xsl:apply-templates />
                                                        </fo:inline>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell  padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" height="22pt" number-columns-spanned="4" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                        <fo:block>
                                            <fo:block color="black" space-before.optimum="-8pt">
                                                <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="1pt" />
                                            </fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                </fo:static-content>
                <fo:static-content flow-name="xsl-region-after" display-align="after">
                    <fo:block>
                        <fo:table width="520pt" space-before.optimum="1pt" space-after.optimum="2pt">
                            <fo:table-column column-width="150pt" />
                            <fo:table-column column-width="150pt" />
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell  padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" text-align="left" width="150pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                        <fo:block>
                                            <fo:block>
                                                <fo:leader leader-pattern="space" />
                                            </fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="inherited-property-value(&apos;font-size&apos;) - 2pt"  padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" text-align="right" width="150pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell  padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" text-align="left" width="150pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                        <fo:block>
                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                <fo:block>
                                                    <fo:inline font-weight="bold">&#160; </fo:inline>&#160;<fo:inline font-size="9.0pt">Page: </fo:inline>
                                                    <fo:page-number font-size="9.0pt" />
                                                </fo:block>
                                            </fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="150pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                        <fo:block>
                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                <fo:block>
                                                    <xsl:for-each select="BudgetSummaryReport">
                                                        <xsl:for-each select="ReportHeader">
                                                            <xsl:for-each select="CreateDate">
                                                                <fo:inline font-size="9.0pt">
                                                                    <xsl:apply-templates />
                                                                </fo:inline>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:block>
                                            </fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="8pt">
                        <fo:block>
                            <xsl:text>&#xA;</xsl:text>
                        </fo:block>
                        <fo:table width="520pt" space-before.optimum="1pt" space-after.optimum="2pt">
                            <fo:table-column column-width="97pt" />
                            <fo:table-column column-width="75pt" />
                            <fo:table-column column-width="65pt" />
                            <fo:table-column column-width="65pt" />
                            <fo:table-column column-width="75pt" />
                            <fo:table-column column-width="90pt" />
                            <fo:table-column/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="97pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" width="75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                        <fo:block>
                                            <fo:inline font-size="8.0pt" font-weight="bold">Personnel Category</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="65pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                        <fo:block>
                                            <fo:inline font-size="8.0pt" font-weight="bold">Start Date</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="65pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                        <fo:block>
                                            <fo:inline font-size="8.0pt" font-weight="bold">End Date</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="center" width="75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                        <fo:block>
                                            <fo:inline font-size="8.0pt" font-weight="bold">Fringe Benefits</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="center" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                        <fo:block>
                                            <fo:inline font-size="8.0pt" font-weight="bold">Salaries &amp; Wages</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="7" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                        <fo:block>
                                            <xsl:for-each select="BudgetSummaryReport">
                                                <xsl:for-each select="CumilativePage">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="SalarySummaryFromEDI">
                                                            <xsl:for-each select="Group">
                                                                <fo:block>
                                                                    <xsl:text>&#xA;</xsl:text>
                                                                </fo:block>
                                                                <fo:table width="520pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                    <fo:table-column/>
                                                                    <fo:table-column column-width="75pt" />
                                                                    <fo:table-column column-width="77pt" />
                                                                    <fo:table-column/>
                                                                    <fo:table-column/>
                                                                    <fo:table-column />
                                                                    <fo:table-column/>
                                                                    <fo:table-body>
                                                                        <fo:table-row>
                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="7" width="314pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                <fo:block>
                                                                                    <xsl:for-each select="Description">
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">
                                                                                            <xsl:apply-templates />
                                                                                        </fo:inline>
                                                                                    </xsl:for-each>
                                                                                    <fo:table width="515pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column column-width="90pt" />
                                                                                        <fo:table-column column-width="75pt" />
                                                                                        <fo:table-column column-width="65pt" />
                                                                                        <fo:table-column column-width="65pt" />
                                                                                        <fo:table-column column-width="80pt" />
                                                                                        <fo:table-column column-width="50pt" />
                                                                                        <fo:table-column/>
                                                                                        <fo:table-body>
                                                                                            <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                                                                                            <xsl:for-each select="Details">
                                                                                                <fo:table-row>
                                                                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                        <fo:block>
                                                                                                            <xsl:for-each select="PersonName">
                                                                                                                <fo:inline font-size="8.0pt" font-weight="normal">
                                                                                                                    <xsl:apply-templates />
                                                                                                                </fo:inline>
                                                                                                            </xsl:for-each>
                                                                                                        </fo:block>
                                                                                                    </fo:table-cell>
                                                                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" width="75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                        <fo:block>
                                                                                                            <xsl:for-each select="CostElementDescription">
                                                                                                                <fo:inline font-size="8.0pt" font-weight="normal">
                                                                                                                    <xsl:apply-templates />
                                                                                                                </fo:inline>
                                                                                                            </xsl:for-each>
                                                                                                        </fo:block>
                                                                                                    </fo:table-cell>
                                                                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="65pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                        <fo:block>
                                                                                                            <xsl:for-each select="StartDate">
                                                                                                                <fo:inline font-size="8.0pt" font-weight="normal">
                                                                                                                    <xsl:apply-templates />
                                                                                                                </fo:inline>
                                                                                                            </xsl:for-each>
                                                                                                        </fo:block>
                                                                                                    </fo:table-cell>
                                                                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="65pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                        <fo:block>
                                                                                                            <xsl:for-each select="EndDate">
                                                                                                                <fo:inline font-size="8.0pt" font-weight="normal">
                                                                                                                    <xsl:apply-templates />
                                                                                                                </fo:inline>
                                                                                                            </xsl:for-each>
                                                                                                        </fo:block>
                                                                                                    </fo:table-cell>
                                                                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="80pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                                        <fo:block>
                                                                                                            <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                                            <fo:inline font-size="8.0pt">
                                                                                                                <xsl:value-of select="format-number(  Fringe  ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                                            </fo:inline>
                                                                                                        </fo:block>
                                                                                                    </fo:table-cell>
                                                                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="50pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                                        <fo:block>
                                                                                                            <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                                            <fo:inline font-size="8.0pt">
                                                                                                                <xsl:value-of select="format-number(SalaryRequested,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                                            </fo:inline>
                                                                                                        </fo:block>
                                                                                                    </fo:table-cell>
                                                                                                </fo:table-row>
                                                                                            </xsl:for-each>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                        </fo:table-row>
                                                                        <fo:table-row>
                                                                              <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="7" width="314pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                            <fo:table width="515pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                <fo:table-column column-width="90pt" />
                                                                                <fo:table-column column-width="75pt" />
                                                                                <fo:table-column column-width="65pt" />
                                                                                <fo:table-column column-width="65pt" />
                                                                                <fo:table-column column-width="80pt" />
                                                                                <fo:table-column column-width="50pt" />
                                                                                <fo:table-column/>
                                                                                <fo:table-body>                                                                                    
                                                                                    <fo:table-row height=".6pt">
                                                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="5" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                            <fo:block />
                                                                                        </fo:table-cell>
                                                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                            <fo:block>
                                                                                                <fo:block color="black" space-before.optimum="-8pt">
                                                                                                    <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                                                                </fo:block>
                                                                                            </fo:block>
                                                                                        </fo:table-cell>
                                                                                    </fo:table-row>
                                                                                    <fo:table-row>
                                                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="5" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                            <fo:block>
                                                                                                <fo:inline font-size="8.0pt" font-weight="bold">Total </fo:inline>
                                                                                                <xsl:for-each select="Description">
                                                                                                    <fo:inline font-size="8.0pt" font-weight="bold">
                                                                                                        <xsl:apply-templates />
                                                                                                    </fo:inline>
                                                                                                </xsl:for-each>
                                                                                            </fo:block>
                                                                                        </fo:table-cell>
                                                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right"  padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" >
                                                                                            <fo:block>
                                                                                                <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                                <fo:inline font-size="8.0pt">
                                                                                                    <xsl:value-of select="format-number(sum(Details/Fringe),&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                                </fo:inline>
                                                                                            </fo:block>
                                                                                        </fo:table-cell>
                                                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right"  padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                            <fo:block>
                                                                                                <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                                <fo:inline font-size="8.0pt">
                                                                                                    <xsl:value-of select="format-number(  sum(  Details/SalaryRequested  ),&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                            </fo:inline> </fo:block>
                                                                                        </fo:table-cell>
                                                                                    </fo:table-row>
                                                                                </fo:table-body>
                                                                            </fo:table>
                                                                            </fo:table-cell>
                                                                        </fo:table-row>
                                                                    </fo:table-body>
                                                                </fo:table>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="7" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                        <fo:table width="520pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                            <fo:table-column/>
                                            <fo:table-column column-width="75pt" />
                                            <fo:table-column column-width="77pt" />
                                            <fo:table-column/>
                                            <fo:table-column/>
                                            <fo:table-column />
                                            <fo:table-column/>
                                            <fo:table-body>
                                                <fo:table-row height=".6pt">
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="5" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                        <fo:block />
                                                    </fo:table-cell>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                        <fo:block space-after.optimum="2pt"> 
                                                            <fo:block color="black" space-before.optimum="-8pt">
                                                                <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                            </fo:block>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>
                                                <fo:table-row>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="5" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                        <fo:block space-after.optimum="2pt">
                                                            <fo:inline font-size="8.0pt" font-weight="bold">Total Fringe Benefits</fo:inline>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white"  padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                        <fo:block />
                                                    </fo:table-cell>
                                                    <fo:table-cell border-style="solid"  border-width="1pt" border-color="white"  text-align="right" padding-start="3pt" padding-end="3pt"  padding-before="3pt"  display-align="center">
                                                        <fo:block>
                                                            <fo:inline font-size="8.0pt">$</fo:inline>
                                                            <fo:inline font-size="8.0pt" font-weight="normal">
                                                                <xsl:value-of select="format-number(sum(BudgetSummaryReport/CumilativePage/BudgetSummary/SalarySummaryFromEDI/Group/Details/Fringe),&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                            </fo:inline>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>
                                                <fo:table-row height=".6pt">
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="5" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                        <fo:block />
                                                    </fo:table-cell>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                        <fo:block space-after.optimum="2pt"> 
                                                            <fo:block color="black" space-before.optimum="-8pt">
                                                                <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                            </fo:block>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>
                                                <fo:table-row>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="5" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                        <fo:block>
                                                            <fo:inline font-size="8.0pt" font-weight="bold">Total Salary and Wages</fo:inline>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                        
                                                        <fo:block>
                                                            <fo:inline font-size="8.0pt">$</fo:inline>
                                                            <fo:inline font-size="8.0pt">
                                                                <xsl:value-of select="format-number(sum(BudgetSummaryReport/CumilativePage/BudgetSummary/SalarySummaryFromEDI/Group/Details/SalaryRequested),&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                            </fo:inline>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>
                                                <fo:table-row height=".6pt">
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="5" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                        <fo:block />
                                                    </fo:table-cell>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                        <fo:block space-after.optimum="2pt"> 
                                                            <fo:block color="black" space-before.optimum="-8pt">
                                                                <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                            </fo:block>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>
                                                <fo:table-row>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="5" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                        <fo:block>
                                                            <fo:inline font-size="8.0pt" font-weight="bold">TOTAL WAGES AND FRINGE&#160; BENEFITS</fo:inline>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                        <fo:block>                                           
                                                            <fo:inline font-size="8.0pt">$</fo:inline>
                                                            <fo:inline font-size="8.0pt" font-weight="normal">
                                                                <xsl:value-of select="format-number(sum(BudgetSummaryReport/CumilativePage/BudgetSummary/SalarySummaryFromEDI/Group/Details/Fringe)+sum(BudgetSummaryReport/CumilativePage/BudgetSummary/SalarySummaryFromEDI/Group/Details/SalaryRequested),&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                            </fo:inline>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>
                                            </fo:table-body>
                                        </fo:table>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="7" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                        <fo:block>
                                            <xsl:if test="count(  BudgetSummaryReport/CumilativePage/BudgetSummary/SalarySummaryFromEDI/Group/Details  )  &gt; 12">
                                                <fo:block break-after="page">
                                                    <fo:leader leader-pattern="space" />
                                                </fo:block>
                                            </xsl:if>
                                            <xsl:for-each select="BudgetSummaryReport">
                                                <xsl:for-each select="CumilativePage">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetSummaryNonPersonnel">
                                                            <xsl:for-each select="Group">
                                                                <fo:table width="518pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                    <fo:table-column/>
                                                                    <fo:table-column column-width="75pt" />
                                                                    <fo:table-body>
                                                                        <fo:table-row>
                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" height="14pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                <fo:block>
                                                                                    <xsl:for-each select="Description">
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">
                                                                                            <xsl:apply-templates />
                                                                                        </fo:inline>
                                                                                    </xsl:for-each>
                                                                                    <fo:table width="515pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column/>
                                                                                        <fo:table-column column-width="75pt" />
                                                                                        <fo:table-column column-width="77pt" />
                                                                                        <fo:table-column/>
                                                                                        <fo:table-column/>
                                                                                        <fo:table-column />
                                                                                        <fo:table-column/>
                                                                                        <fo:table-body>
                                                                                            <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                                                                                            <xsl:for-each select="Details">
                                                                                                <fo:table-row height="5pt">
                                                                                                    <fo:table-cell border-style="solid" border-width="1pt" number-columns-spanned="5" border-color="white" width="200pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                        <fo:block>
                                                                                                            <xsl:for-each select="CostElementDescription">
                                                                                                                <fo:inline font-size="8.0pt" font-weight="normal">
                                                                                                                    <xsl:apply-templates />
                                                                                                                </fo:inline>
                                                                                                            </xsl:for-each>
                                                                                                        </fo:block>
                                                                                                    </fo:table-cell>
                                                                                                    
                                                                                                    <fo:table-cell border-style="solid" border-width="1pt" number-columns-spanned="2" border-color="white" text-align="right" width="117pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                                        <fo:block>
                                                                                                            <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                                            <fo:inline font-size="8.0pt">
                                                                                                                <xsl:value-of select="format-number(  CalculatedCost  ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                                            </fo:inline>
                                                                                                        </fo:block>
                                                                                                    </fo:table-cell>
                                                                                                </fo:table-row>
                                                                                            </xsl:for-each>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                        </fo:table-row>
                                                                        <fo:table-row>
                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" height="14pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                <fo:table width="515pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                    <fo:table-column/>
                                                                                    <fo:table-column column-width="75pt" />
                                                                                    <fo:table-column column-width="77pt" />
                                                                                    <fo:table-column/>
                                                                                    <fo:table-column/>
                                                                                    <fo:table-column />
                                                                                    <fo:table-column/>
                                                                                    <fo:table-body>
                                                                                        <fo:table-row height=".6pt">
                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="5" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                <fo:block />
                                                                                            </fo:table-cell>
                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                <fo:block space-after.optimum="2pt"> 
                                                                                                    <fo:block color="black" space-before.optimum="-8pt">
                                                                                                        <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                                                                    </fo:block>
                                                                                                </fo:block>
                                                                                            </fo:table-cell>
                                                                                        </fo:table-row>
                                                                                        <fo:table-row>
                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="6" display-align="after" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                                                                <fo:block>
                                                                                                    <fo:inline font-size="8.0pt" font-weight="bold">Total </fo:inline>
                                                                                                    <xsl:for-each select="Description">
                                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">
                                                                                                            <xsl:apply-templates />
                                                                                                        </fo:inline>
                                                                                                    </xsl:for-each>
                                                                                                </fo:block>
                                                                                            </fo:table-cell>
                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                                <fo:block>
                                                                                                    <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                                    <fo:inline font-size="8.0pt" font-weight="normal">
                                                                                                        <xsl:value-of select="format-number(sum(Details/CalculatedCost),&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
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
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                
                                <fo:table-row>
                                    <fo:table-cell  border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="7" width="214pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                        <fo:table width="520pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                            <fo:table-column/>
                                            <fo:table-column column-width="75pt" />
                                            <fo:table-column column-width="77pt" />
                                            <fo:table-column/>
                                            <fo:table-column/>
                                            <fo:table-column />
                                            <fo:table-column/>
                                            <fo:table-body>
                                                <fo:table-row height=".6pt">
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="5" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                        <fo:block />
                                                    </fo:table-cell>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                        <fo:block space-after.optimum="2pt"> 
                                                            <fo:block color="black" space-before.optimum="-8pt">
                                                                <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                            </fo:block>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>
                                                <fo:table-row>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="6" width="214pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                        <fo:block>
                                                            <fo:inline font-size="8.0pt" font-weight="bold">Total Direct Costs</fo:inline>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                        <fo:block>
                                                            <fo:inline font-size="8.0pt">$</fo:inline>
                                                            <fo:inline font-size="8.0pt">
                                                                <xsl:value-of select="format-number(  BudgetSummaryReport/CumilativePage/BudgetSummary/TotalDirectCost   ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                            </fo:inline>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>
                                            </fo:table-body>
                                        </fo:table>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="7" width="214pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                        <fo:block>
                                            <xsl:for-each select="BudgetSummaryReport">
                                                <xsl:for-each select="CumilativePage">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetIndirectCostsForReport">
                                                            <xsl:for-each select="Group">
                                                                <fo:block>
                                                                    <xsl:text>&#xA;</xsl:text>
                                                                </fo:block>
                                                                <fo:table width="520pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                    <fo:table-column/>
                                                                    <fo:table-column/>
                                                                    <fo:table-column column-width="75pt" />
                                                                    <fo:table-body>
                                                                        <fo:table-row>
                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                <fo:block>
                                                                                    <fo:inline font-size="8.0pt" font-weight="bold">F&amp;A (Indirect) Cost</fo:inline>
                                                                                    <fo:table width="520pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column/>
                                                                                        <fo:table-column column-width="75pt" />
                                                                                        <fo:table-column column-width="77pt" />
                                                                                        <fo:table-column/>
                                                                                        <fo:table-column/>
                                                                                        <fo:table-column />
                                                                                        <fo:table-column/>
                                                                                        <fo:table-body>
                                                                                            <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                                                                                            <xsl:for-each select="Details">
                                                                                                <fo:table-row>
                                                                                                    <fo:table-cell border-style="solid" number-columns-spanned="6" border-width="1pt" border-color="white"  padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                        <fo:block>
                                                                                                            <xsl:choose>
                                                                                                                <xsl:when test="OnOffCampus  = &apos;false&apos;">
                                                                                                                    <fo:inline font-size="8.0pt">Off Campus</fo:inline>
                                                                                                                </xsl:when>
                                                                                                                <xsl:when test="OnOffCampus  = &apos;true&apos;">
                                                                                                                    <fo:inline font-size="8.0pt">On Campus</fo:inline>
                                                                                                                </xsl:when>
                                                                                                            </xsl:choose>
                                                                                                        </fo:block>
                                                                                                    </fo:table-cell>
                                                                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                                        <fo:block>
                                                                                                            <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                                            <fo:inline font-size="8.0pt">
                                                                                                                <xsl:value-of select="format-number(  CalculatedCost  ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                                        </fo:inline>&#160;</fo:block>
                                                                                                    </fo:table-cell>
                                                                                                </fo:table-row>
                                                                                            </xsl:for-each>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                        </fo:table-row>
                                                                        <fo:table-row>
                                                                            <fo:table-cell>
                                                                                <fo:table width="521pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                    <fo:table-column/>
                                                                                    <fo:table-column column-width="75pt" />
                                                                                    <fo:table-column column-width="77pt" />
                                                                                    <fo:table-column/>
                                                                                    <fo:table-column/>
                                                                                    <fo:table-column />
                                                                                    <fo:table-column/>
                                                                                    <fo:table-body>
                                                                                        <fo:table-row height=".6pt">
                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="5" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                <fo:block />
                                                                                            </fo:table-cell>
                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                <fo:block space-after.optimum="2pt"> 
                                                                                                    <fo:block color="black" space-before.optimum="-8pt">
                                                                                                        <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                                                                    </fo:block>
                                                                                                </fo:block>
                                                                                            </fo:table-cell>
                                                                                        </fo:table-row>                                                                       
                                                                                        <fo:table-row>
                                                                                            <fo:table-cell border-style="solid" number-columns-spanned="6" border-width="1pt" border-color="white" display-align="after" text-align="left" padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                                                                <fo:block>
                                                                                                    <fo:inline font-size="8.0pt" font-weight="bold">Total F&amp;A (Indirect) Cost</fo:inline>
                                                                                                </fo:block>
                                                                                            </fo:table-cell>
                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="after" text-align="right"  padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                                                                <fo:block>   
                                                                                                    <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                                    <fo:inline font-size="8.0pt" font-weight="normal">
                                                                                                        <xsl:value-of select="format-number(sum(Details/CalculatedCost),&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
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
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:table width="526pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                            <fo:table-column/>
                                            <fo:table-column column-width="75pt" />
                                            <fo:table-column column-width="77pt" />
                                            <fo:table-column/>
                                            <fo:table-column/>
                                            <fo:table-column />
                                            <fo:table-column/>
                                            <fo:table-body>                
                                                <fo:table-row height=".6pt">
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="5" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                        <fo:block />
                                                    </fo:table-cell>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                        <fo:block space-after.optimum="2pt"> 
                                                            <fo:block color="black" space-before.optimum="-8pt">
                                                                <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                            </fo:block>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>
                                                <fo:table-row>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="center" number-columns-spanned="6" width="183pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  text-align="start">
                                                        <fo:block>
                                                            <fo:inline font-size="8.0pt" font-weight="bold">Total Cost to Sponsor</fo:inline>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="center" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                        <fo:block>                            
                                                            <fo:inline font-size="8.0pt">$</fo:inline>
                                                            <fo:inline font-size="8.0pt">
                                                                <xsl:value-of select="format-number(  BudgetSummaryReport/CumilativePage/BudgetSummary/TotalCostToSponsor   ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                            </fo:inline>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>          
                                                <fo:table-row height=".6pt">
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="5" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                        <fo:block />
                                                    </fo:table-cell>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                        <fo:block space-after.optimum="2pt"> 
                                                            <fo:block color="black" space-before.optimum="-8pt">
                                                                <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                            </fo:block>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>
                                                
                                                <fo:table-row>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="center" number-columns-spanned="6" width="183pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  text-align="start">
                                                        <fo:block>
                                                            <fo:inline font-size="8.0pt" font-weight="bold">Total Underrecovery Amount</fo:inline>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="center" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                        <fo:block>
                                                            
                                                            <fo:inline font-size="8.0pt">$</fo:inline>
                                                            <fo:inline font-size="8.0pt">
                                                                <xsl:value-of select="format-number(  BudgetSummaryReport/CumilativePage/BudgetSummary/TotalUnderrecoveryAmount   ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                            </fo:inline>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>
                                                
                                                <fo:table-row height=".6pt">
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="5" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                        <fo:block />
                                                    </fo:table-cell>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                        <fo:block space-after.optimum="2pt"> 
                                                            <fo:block color="black" space-before.optimum="-8pt">
                                                                <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                            </fo:block>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>
                                                
                                                <fo:table-row>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="center" number-columns-spanned="6" width="183pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  text-align="start">
                                                        <fo:block>
                                                            <fo:inline font-size="8.0pt" font-weight="bold">Total Cost Sharing Amount</fo:inline>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="center" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                        <fo:block>
                                                            
                                                            <fo:inline font-size="8.0pt">$</fo:inline>
                                                            <fo:inline font-size="8.0pt">
                                                                <xsl:value-of select="format-number(  BudgetSummaryReport/CumilativePage/BudgetSummary/TotalCostSharingAmount   ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                            </fo:inline>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>
                                                
                                                <fo:table-row height=".6pt">
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="5" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                        <fo:block />
                                                    </fo:table-cell>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                        <fo:block space-after.optimum="2pt"> 
                                                            <fo:block color="black" space-before.optimum="-8pt">
                                                                <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                            </fo:block>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>
                                                
                                                <fo:table-row>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="center" number-columns-spanned="6" width="183pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  text-align="start">
                                                        <fo:block>
                                                            <fo:inline font-size="8.0pt" font-weight="bold"> TOTAL COST OF THE PROJECT</fo:inline>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="center" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                        <fo:block>
                                                            
                                                            <fo:inline font-size="8.0pt">$</fo:inline>
                                                            <fo:inline font-size="8.0pt">
                                                                <xsl:value-of select="format-number( number( BudgetSummaryReport/CumilativePage/BudgetSummary/TotalCostToSponsor)+number( BudgetSummaryReport/CumilativePage/BudgetSummary/TotalUnderrecoveryAmount)+number( BudgetSummaryReport/CumilativePage/BudgetSummary/TotalCostSharingAmount)   ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                            </fo:inline>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>                                
                                            </fo:table-body>
                                        </fo:table>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="7" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                        <fo:block>
                                            <fo:block break-after="page">
                                                <fo:leader leader-pattern="space" />
                                            </fo:block>
                                            <fo:inline font-size="10.0pt" font-weight="bold">Calculation Methodology</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="7" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                        <fo:block>
                                            <fo:table width="520pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                <fo:table-column/>
                                                <fo:table-column/>
                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                            <fo:block>
                                                                <xsl:if test="count(  BudgetSummaryReport/CumilativePage/CalculationMethodology/BudgetOHExclusions/Group/Details  )">
                                                                    <fo:table width="520pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column/>
                                                                        <fo:table-column/>
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">The full F&amp;A (Indirect) Cost Rate is applied to the total direct costs, less the following exclusions</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" number-columns-spanned="2" border-width="1pt" border-color="white" display-align="after" text-align="right" padding-start="3pt" padding-end="3pt">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="BudgetSummaryReport">
                                                                                            <xsl:for-each select="CumilativePage">
                                                                                                <xsl:for-each select="CalculationMethodology">
                                                                                                    <xsl:for-each select="BudgetOHExclusions">
                                                                                                        <xsl:for-each select="Group">
                                                                                                            <fo:table width="100%">
                                                                                                                <fo:table-column/>
                                                                                                                <fo:table-column/>
                                                                                                                <fo:table-body>
                                                                                                                    <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                                                                                                                    <xsl:for-each select="Details">
                                                                                                                        <fo:table-row>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:for-each select="CostElementDescription">
                                                                                                                                        <fo:inline font-size="8.0pt">
                                                                                                                                            <xsl:apply-templates />
                                                                                                                                        </fo:inline>
                                                                                                                                    </xsl:for-each>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                                                                <fo:block>
                                                                                                                                    <fo:inline font-size="8.0pt">
                                                                                                                                        <xsl:value-of select="format-number(  CalculatedCost  ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                                                                    </fo:inline>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                        </fo:table-row>
                                                                                                                    </xsl:for-each>
                                                                                                                </fo:table-body>
                                                                                                            </fo:table>
                                                                                                        </xsl:for-each>
                                                                                                    </xsl:for-each>
                                                                                                </xsl:for-each>
                                                                                            </xsl:for-each>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="after" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Total exclusions from F&amp;A base</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:block color="black" space-before.optimum="-8pt">
                                                                                            <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                                                        </fo:block>
                                                                                        <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                        <fo:inline font-size="8.0pt">
                                                                                            <xsl:value-of select="format-number(sum(BudgetSummaryReport/CumilativePage/CalculationMethodology/BudgetOHExclusions/Group/Details/CalculatedCost),&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </xsl:if>
                                                                <fo:block>
                                                                    <fo:leader leader-pattern="space" />
                                                                </fo:block>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                            <fo:block>
                                                                <xsl:if test="count(  BudgetSummaryReport/CumilativePage/CalculationMethodology/BudgetLAExclusions/Group/Details  ) &gt;0">
                                                                    <fo:table width="520pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column/>
                                                                        <fo:table-column/>
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">The Allocated Administrative Support and Allocated Lab Expense Rates are applied to the total direct costs, less the following exclusions</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" number-columns-spanned="2" border-width="1pt" border-color="white" display-align="center" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="BudgetSummaryReport">
                                                                                            <xsl:for-each select="CumilativePage">
                                                                                                <xsl:for-each select="CalculationMethodology">
                                                                                                    <xsl:for-each select="BudgetLAExclusions">
                                                                                                        <xsl:for-each select="Group">
                                                                                                            <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                                <fo:table-column/>
                                                                                                                <fo:table-column/>
                                                                                                                <fo:table-body>
                                                                                                                    <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                                                                                                                    <xsl:for-each select="Details">
                                                                                                                        <fo:table-row>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" padding-start="3pt" padding-end="3pt"   display-align="center">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:for-each select="CostElementDescription">
                                                                                                                                        <fo:inline font-size="8.0pt">
                                                                                                                                            <xsl:apply-templates />
                                                                                                                                        </fo:inline>
                                                                                                                                    </xsl:for-each>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" padding-start="3pt" padding-end="3pt"   display-align="center">
                                                                                                                                <fo:block>
                                                                                                                                    <fo:inline font-size="8.0pt">
                                                                                                                                        <xsl:value-of select="format-number(  CalculatedCost  ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                                                                    </fo:inline>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                        </fo:table-row>
                                                                                                                    </xsl:for-each>
                                                                                                                </fo:table-body>
                                                                                                            </fo:table>
                                                                                                        </xsl:for-each>
                                                                                                    </xsl:for-each>
                                                                                                </xsl:for-each>
                                                                                            </xsl:for-each>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="after" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Total exclusions from Allocated Expense base</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="1pt"  display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:block color="black" space-before.optimum="-8pt">
                                                                                            <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                                                        </fo:block>
                                                                                        <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                        <fo:inline font-size="8.0pt">
                                                                                            <xsl:value-of select="format-number(sum(BudgetSummaryReport/CumilativePage/CalculationMethodology/BudgetLAExclusions/Group/Details/CalculatedCost),&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </xsl:if>
                                                                <fo:block>
                                                                    <fo:leader leader-pattern="space" />
                                                                </fo:block>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  text-align="start">
                                                            <fo:block>
                                                                <xsl:if test="count(  BudgetSummaryReport/CumilativePage/CalculationMethodology/BudgetOHRateBaseForPeriod/Group/Details  ) &gt;0">
                                                                    <fo:block color="black" space-before.optimum="-8pt">
                                                                        <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                                    </fo:block>
                                                                    <fo:table width="518pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="85pt" />
                                                                        <fo:table-column column-width="80pt" />
                                                                        <fo:table-column column-width="80pt" />
                                                                        <fo:table-column column-width="60pt" />
                                                                        <fo:table-column column-width="60pt" />
                                                                        <fo:table-column column-width="70pt" />
                                                                        <fo:table-column column-width="85pt" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="7" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">F&amp;A (Indirect) Cost Rates and Base</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="85pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Start Date</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="80pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">End Date</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="80pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Campus</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="60pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Rate</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="60pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Rate Type</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="70pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Base</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="85pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Indirect Cost&#160; </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="7" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="BudgetSummaryReport">
                                                                                            <xsl:for-each select="CumilativePage">
                                                                                                <xsl:for-each select="CalculationMethodology">
                                                                                                    <xsl:for-each select="BudgetOHRateBaseForPeriod">
                                                                                                        <xsl:for-each select="Group">
                                                                                                            <fo:table width="516pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                                <fo:table-column column-width="80pt" />
                                                                                                                <fo:table-column column-width="80pt" />
                                                                                                                <fo:table-column column-width="80pt" />
                                                                                                                <fo:table-column column-width="60pt" />
                                                                                                                <fo:table-column column-width="60pt" />
                                                                                                                <fo:table-column column-width="65pt" />
                                                                                                                <fo:table-column column-width="87pt" />
                                                                                                                <fo:table-body>
                                                                                                                    <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                                                                                                                    <xsl:for-each select="Details">
                                                                                                                        <fo:table-row>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="80pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:for-each select="StartDate">
                                                                                                                                        <fo:inline font-size="8.0pt">
                                                                                                                                            <xsl:apply-templates />
                                                                                                                                        </fo:inline>
                                                                                                                                    </xsl:for-each>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="80pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:for-each select="EndDate">
                                                                                                                                        <fo:inline font-size="8.0pt">
                                                                                                                                            <xsl:apply-templates />
                                                                                                                                        </fo:inline>
                                                                                                                                    </xsl:for-each>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="80pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:choose>
                                                                                                                                        <xsl:when test="OnOffCampus  = &apos;false&apos;">
                                                                                                                                            <fo:inline font-size="8.0pt">Off Campus</fo:inline>
                                                                                                                                        </xsl:when>
                                                                                                                                        <xsl:when test="OnOffCampus  = &apos;true&apos;">
                                                                                                                                            <fo:inline font-size="8.0pt">On Campus</fo:inline>
                                                                                                                                        </xsl:when>
                                                                                                                                    </xsl:choose>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="60pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:for-each select="AppliedRate">
                                                                                                                                        <fo:inline font-size="8.0pt">
                                                                                                                                            <xsl:apply-templates />
                                                                                                                                        </fo:inline>
                                                                                                                                    </xsl:for-each>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="60pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:for-each select="RateClassDesc">
                                                                                                                                        <fo:inline font-size="8.0pt">
                                                                                                                                            <xsl:apply-templates />
                                                                                                                                        </fo:inline>
                                                                                                                                    </xsl:for-each>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="65pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                                                                <fo:block>
                                                                                                                                    <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                                                                    <fo:inline font-size="8.0pt">
                                                                                                                                        <xsl:value-of select="format-number(  SalaryRequested  ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                                                                    </fo:inline>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="87pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                                                                <fo:block>
                                                                                                                                    <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                                                                    <fo:inline font-size="8.0pt">
                                                                                                                                        <xsl:value-of select="format-number(  CalculatedCost  ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                                                                </fo:inline>&#160;</fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                        </fo:table-row>
                                                                                                                    </xsl:for-each>
                                                                                                                </fo:table-body>
                                                                                                            </fo:table>
                                                                                                        </xsl:for-each>
                                                                                                    </xsl:for-each>
                                                                                                </xsl:for-each>
                                                                                            </xsl:for-each>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white"  display-align="after" number-columns-spanned="6" text-align="right" width="370pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Total</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt"  border-color="white" display-align="after" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt">
                                                                                    <fo:block>
                                                                                        <fo:block color="black" space-before.optimum="-8pt">
                                                                                            <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                                                        </fo:block>
                                                                                        <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                        <fo:inline font-size="8.0pt">
                                                                                            <xsl:value-of select="format-number(sum(BudgetSummaryReport/CumilativePage/CalculationMethodology/BudgetOHRateBaseForPeriod/Group/Details/CalculatedCost),&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </xsl:if>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                            <fo:block>
                                                                <xsl:if test="count(  BudgetSummaryReport/CumilativePage/CalculationMethodology/BudgetEBRateBaseForPeriod/Group/Details  ) &gt;0">
                                                                    <fo:block color="black" space-before.optimum="-8pt">
                                                                        <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                                    </fo:block>
                                                                    <fo:table width="520pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="80pt" />
                                                                        <fo:table-column column-width="80pt" />
                                                                        <fo:table-column column-width="80pt" />
                                                                        <fo:table-column column-width="70pt" />
                                                                        <fo:table-column column-width="70pt" />
                                                                        <fo:table-column column-width="80pt" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="6" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Employee Benefit Rates and Base</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" height="15pt" width="80pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Start Date</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" height="15pt" width="80pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">End Date</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" height="15pt" width="80pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Campus</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" height="15pt" width="70pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Rate</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="70pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Base</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="80pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Calculated Cost</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="6" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="BudgetSummaryReport">
                                                                                            <xsl:for-each select="CumilativePage">
                                                                                                <xsl:for-each select="CalculationMethodology">
                                                                                                    <xsl:for-each select="BudgetEBRateBaseForPeriod">
                                                                                                        <xsl:for-each select="Group">
                                                                                                            <xsl:for-each select="Description">
                                                                                                                <fo:inline font-size="8.0pt">
                                                                                                                    <xsl:apply-templates />
                                                                                                                </fo:inline>
                                                                                                            </xsl:for-each>
                                                                                                            <fo:table width="516pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                                <fo:table-column column-width="80pt" />
                                                                                                                <fo:table-column column-width="80pt" />
                                                                                                                <fo:table-column column-width="80pt" />
                                                                                                                <fo:table-column column-width="70pt" />
                                                                                                                <fo:table-column column-width="70pt" />
                                                                                                                <fo:table-column column-width="75pt" />
                                                                                                                <fo:table-body>
                                                                                                                    <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                                                                                                                    <xsl:for-each select="Details">
                                                                                                                        <fo:table-row>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="80pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:for-each select="StartDate">
                                                                                                                                        <fo:inline font-size="8.0pt">
                                                                                                                                            <xsl:apply-templates />
                                                                                                                                        </fo:inline>
                                                                                                                                    </xsl:for-each>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="80pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:for-each select="EndDate">
                                                                                                                                        <fo:inline font-size="8.0pt">
                                                                                                                                            <xsl:apply-templates />
                                                                                                                                        </fo:inline>
                                                                                                                                    </xsl:for-each>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="80pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:choose>
                                                                                                                                        <xsl:when test="OnOffCampus  = &apos;false&apos;">
                                                                                                                                            <fo:inline font-size="8.0pt">Off Campus</fo:inline>
                                                                                                                                        </xsl:when>
                                                                                                                                        <xsl:when test="OnOffCampus  = &apos;true&apos;">
                                                                                                                                            <fo:inline font-size="8.0pt">On Campus</fo:inline>
                                                                                                                                        </xsl:when>
                                                                                                                                    </xsl:choose>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="70pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:for-each select="AppliedRate">
                                                                                                                                        <fo:inline font-size="8.0pt">
                                                                                                                                            <xsl:apply-templates />
                                                                                                                                        </fo:inline>
                                                                                                                                    </xsl:for-each>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="70pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                                                                <fo:block>
                                                                                                                                    <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                                                                    <fo:inline font-size="8.0pt">
                                                                                                                                        <xsl:value-of select="format-number(  SalaryRequested  ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                                                                    </fo:inline>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                                                                <fo:block>
                                                                                                                                    <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                                                                    <fo:inline font-size="8.0pt">
                                                                                                                                        <xsl:value-of select="format-number(  CalculatedCost  ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                                                                    </fo:inline>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                        </fo:table-row>
                                                                                                                    </xsl:for-each>
                                                                                                                </fo:table-body>
                                                                                                            </fo:table>
                                                                                                        </xsl:for-each>
                                                                                                    </xsl:for-each>
                                                                                                </xsl:for-each>
                                                                                            </xsl:for-each>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="after" number-columns-spanned="5" text-align="right" width="370pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Total</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="after" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                                                    <fo:block>
                                                                                        <fo:block color="black" space-before.optimum="-8pt">
                                                                                            <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                                                        </fo:block>
                                                                                        <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                        <fo:inline font-size="8.0pt">
                                                                                            <xsl:value-of select="format-number(sum(BudgetSummaryReport/CumilativePage/CalculationMethodology/BudgetEBRateBaseForPeriod/Group/Details/CalculatedCost),&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </xsl:if>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                            <fo:block>
                                                                <xsl:if test="count(  BudgetSummaryReport/CumilativePage/CalculationMethodology/BudgetLARateBaseForPeriod/Group/Details  ) &gt;0">
                                                                    <fo:block color="black" space-before.optimum="-8pt">
                                                                        <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                                    </fo:block>
                                                                    <fo:table width="518pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="100pt" />
                                                                        <fo:table-column column-width="90pt" />
                                                                        <fo:table-column column-width="90pt" />
                                                                        <fo:table-column column-width="90pt" />
                                                                        <fo:table-column column-width="75pt" />
                                                                        <fo:table-column column-width="75pt" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="6" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Allocated Administrative Support and Lab Expense Rates and Base</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="100pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Start Date</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">End Date</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Campus</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Rate</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="center" width="75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Base</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Calculated Cost</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="6" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="BudgetSummaryReport">
                                                                                            <xsl:for-each select="CumilativePage">
                                                                                                <xsl:for-each select="CalculationMethodology">
                                                                                                    <xsl:for-each select="BudgetLARateBaseForPeriod">
                                                                                                        <xsl:for-each select="Group">
                                                                                                            <xsl:for-each select="Description">
                                                                                                                <fo:inline font-size="8.0pt">
                                                                                                                    <xsl:apply-templates />
                                                                                                                </fo:inline>
                                                                                                            </xsl:for-each>
                                                                                                            <fo:table width="513pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                                <fo:table-column column-width="90pt" />
                                                                                                                <fo:table-column column-width="90pt" />
                                                                                                                <fo:table-column column-width="100pt" />
                                                                                                                <fo:table-column column-width="80pt" />
                                                                                                                <fo:table-column column-width="75pt" />
                                                                                                                <fo:table-column column-width="80pt" />
                                                                                                                <fo:table-body>
                                                                                                                    <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                                                                                                                    <xsl:for-each select="Details">
                                                                                                                        <fo:table-row>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:for-each select="StartDate">
                                                                                                                                        <fo:inline font-size="8.0pt">
                                                                                                                                            <xsl:apply-templates />
                                                                                                                                        </fo:inline>
                                                                                                                                    </xsl:for-each>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:for-each select="EndDate">
                                                                                                                                        <fo:inline font-size="8.0pt">
                                                                                                                                            <xsl:apply-templates />
                                                                                                                                        </fo:inline>
                                                                                                                                    </xsl:for-each>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="100pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:choose>
                                                                                                                                        <xsl:when test="OnOffCampus  = &apos;false&apos;">
                                                                                                                                            <fo:inline font-size="8.0pt">Off Campus</fo:inline>
                                                                                                                                        </xsl:when>
                                                                                                                                        <xsl:when test="OnOffCampus  = &apos;true&apos;">
                                                                                                                                            <fo:inline font-size="8.0pt">On Campus</fo:inline>
                                                                                                                                        </xsl:when>
                                                                                                                                    </xsl:choose>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="80pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:for-each select="AppliedRate">
                                                                                                                                        <fo:inline font-size="8.0pt">
                                                                                                                                            <xsl:apply-templates />
                                                                                                                                        </fo:inline>
                                                                                                                                    </xsl:for-each>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                                                                <fo:block>
                                                                                                                                    <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                                                                    <fo:inline font-size="8.0pt">
                                                                                                                                        <xsl:value-of select="format-number(  SalaryRequested  ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                                                                    </fo:inline>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="80pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                                                                <fo:block>
                                                                                                                                    <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                                                                    <fo:inline font-size="8.0pt">
                                                                                                                                        <xsl:value-of select="format-number(  CalculatedCost  ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                                                                    </fo:inline>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                        </fo:table-row>
                                                                                                                    </xsl:for-each>
                                                                                                                </fo:table-body>
                                                                                                            </fo:table>
                                                                                                        </xsl:for-each>
                                                                                                    </xsl:for-each>
                                                                                                </xsl:for-each>
                                                                                            </xsl:for-each>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="after" number-columns-spanned="5" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Total</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="after" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                                                    <fo:block>
                                                                                        <fo:block color="black" space-before.optimum="-8pt">
                                                                                            <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                                                        </fo:block>
                                                                                        <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                        <fo:inline font-size="8.0pt">
                                                                                            <xsl:value-of select="format-number(sum(BudgetSummaryReport/CumilativePage/CalculationMethodology/BudgetLARateBaseForPeriod/Group/Details/CalculatedCost),&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </xsl:if>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                            <fo:block>
                                                                <xsl:if test="count(  BudgetSummaryReport/CumilativePage/CalculationMethodology/BudgetVacRateBaseForPeriod/Group/Details  ) &gt;0">
                                                                    <fo:block color="black" space-before.optimum="-8pt">
                                                                        <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                                    </fo:block>
                                                                    <fo:table width="518pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="90pt" />
                                                                        <fo:table-column column-width="90pt" />
                                                                        <fo:table-column column-width="90pt" />
                                                                        <fo:table-column column-width="90pt" />
                                                                        <fo:table-column column-width="75pt" />
                                                                        <fo:table-column column-width="75pt" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="6" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Vacation Accrual Rates and Base</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Start Date</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">End Date</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Campus</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Rate</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="center" width="75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Base</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Calculated Cost</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="6" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="BudgetSummaryReport">
                                                                                            <xsl:for-each select="CumilativePage">
                                                                                                <xsl:for-each select="CalculationMethodology">
                                                                                                    <xsl:for-each select="BudgetVacRateBaseForPeriod">
                                                                                                        <xsl:for-each select="Group">
                                                                                                            <xsl:for-each select="Description">
                                                                                                                <fo:inline font-size="8.0pt">
                                                                                                                    <xsl:apply-templates />
                                                                                                                </fo:inline>
                                                                                                            </xsl:for-each>
                                                                                                            <fo:table width="516pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                                <fo:table-column column-width="90pt" />
                                                                                                                <fo:table-column column-width="90pt" />
                                                                                                                <fo:table-column column-width="90pt" />
                                                                                                                <fo:table-column column-width="70pt" />
                                                                                                                <fo:table-column column-width="75pt" />
                                                                                                                <fo:table-column column-width="90pt" />
                                                                                                                <fo:table-body>
                                                                                                                    <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                                                                                                                    <xsl:for-each select="Details">
                                                                                                                        <fo:table-row>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:for-each select="StartDate">
                                                                                                                                        <fo:inline font-size="8.0pt">
                                                                                                                                            <xsl:apply-templates />
                                                                                                                                        </fo:inline>
                                                                                                                                    </xsl:for-each>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:for-each select="EndDate">
                                                                                                                                        <fo:inline font-size="8.0pt">
                                                                                                                                            <xsl:apply-templates />
                                                                                                                                        </fo:inline>
                                                                                                                                    </xsl:for-each>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:choose>
                                                                                                                                        <xsl:when test="OnOffCampus  = &apos;false&apos;">
                                                                                                                                            <fo:inline font-size="8.0pt">Off Campus</fo:inline>
                                                                                                                                        </xsl:when>
                                                                                                                                        <xsl:when test="OnOffCampus  = &apos;true&apos;">
                                                                                                                                            <fo:inline font-size="8.0pt">On Campus</fo:inline>
                                                                                                                                        </xsl:when>
                                                                                                                                    </xsl:choose>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="70pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:for-each select="AppliedRate">
                                                                                                                                        <fo:inline font-size="8.0pt">
                                                                                                                                            <xsl:apply-templates />
                                                                                                                                        </fo:inline>
                                                                                                                                    </xsl:for-each>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                                                                <fo:block>
                                                                                                                                    <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                                                                    <fo:inline font-size="8.0pt">
                                                                                                                                        <xsl:value-of select="format-number(  SalaryRequested  ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                                                                    </fo:inline>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                                                                <fo:block>
                                                                                                                                    <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                                                                    <fo:inline font-size="8.0pt">
                                                                                                                                        <xsl:value-of select="format-number(  CalculatedCost  ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                                                                    </fo:inline>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                        </fo:table-row>
                                                                                                                    </xsl:for-each>
                                                                                                                </fo:table-body>
                                                                                                            </fo:table>
                                                                                                        </xsl:for-each>
                                                                                                    </xsl:for-each>
                                                                                                </xsl:for-each>
                                                                                            </xsl:for-each>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="after" number-columns-spanned="4" text-align="right" width="270pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Total</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="after" text-align="right" width="180pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                                                    <fo:block />
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="after" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                                                    <fo:block>
                                                                                        <fo:block color="black" space-before.optimum="-8pt">
                                                                                            <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                                                        </fo:block>
                                                                                        <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                        <fo:inline font-size="8.0pt">
                                                                                            <xsl:value-of select="format-number(sum(BudgetSummaryReport/CumilativePage/CalculationMethodology/BudgetVacRateBaseForPeriod/Group/Details/CalculatedCost),&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </xsl:if>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                            <fo:block>
                                                                <xsl:if test="count(  BudgetSummaryReport/CumilativePage/CalculationMethodology/BudgetOtherRateBaseForPeriod/Group/Details  ) &gt;0">
                                                                    <fo:block color="black" space-before.optimum="-8pt">
                                                                        <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                                    </fo:block>
                                                                    <fo:table width="518pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="90pt" />
                                                                        <fo:table-column column-width="90pt" />
                                                                        <fo:table-column column-width="90pt" />
                                                                        <fo:table-column column-width="90pt" />
                                                                        <fo:table-column column-width="80pt" />
                                                                        <fo:table-column column-width="75pt" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="6" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Other Rates and Base</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Start Date</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">End Date</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Campus</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Rate</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="left" width="80pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Base</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Calculated Cost</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="6" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="BudgetSummaryReport">
                                                                                            <xsl:for-each select="CumilativePage">
                                                                                                <xsl:for-each select="CalculationMethodology">
                                                                                                    <xsl:for-each select="BudgetOtherRateBaseForPeriod">
                                                                                                        <xsl:for-each select="Group">
                                                                                                            <xsl:for-each select="Description">
                                                                                                                <fo:inline font-size="8.0pt">
                                                                                                                    <xsl:apply-templates />
                                                                                                                </fo:inline>
                                                                                                            </xsl:for-each>
                                                                                                            <fo:table width="516pt" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                                <fo:table-column column-width="90pt" />
                                                                                                                <fo:table-column column-width="90pt" />
                                                                                                                <fo:table-column column-width="90pt" />
                                                                                                                <fo:table-column column-width="120pt" />
                                                                                                                <fo:table-column column-width="120pt" />
                                                                                                                <fo:table-body>
                                                                                                                    <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                                                                                                                    <xsl:for-each select="Details">
                                                                                                                        <fo:table-row>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:for-each select="StartDate">
                                                                                                                                        <fo:inline font-size="8.0pt">
                                                                                                                                            <xsl:apply-templates />
                                                                                                                                        </fo:inline>
                                                                                                                                    </xsl:for-each>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:for-each select="EndDate">
                                                                                                                                        <fo:inline font-size="8.0pt">
                                                                                                                                            <xsl:apply-templates />
                                                                                                                                        </fo:inline>
                                                                                                                                    </xsl:for-each>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="90pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
                                                                                                                                <fo:block>
                                                                                                                                    <xsl:choose>
                                                                                                                                        <xsl:when test="OnOffCampus  = &apos;false&apos;">
                                                                                                                                            <fo:inline font-size="8.0pt">Off Campus</fo:inline>
                                                                                                                                        </xsl:when>
                                                                                                                                        <xsl:when test="OnOffCampus  = &apos;true&apos;">
                                                                                                                                            <fo:inline font-size="8.0pt">On Campus</fo:inline>
                                                                                                                                        </xsl:when>
                                                                                                                                    </xsl:choose>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="120pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="right">
                                                                                                                                <fo:block>
                                                                                                                                    <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                                                                    <fo:inline font-size="8.0pt">
                                                                                                                                        <xsl:value-of select="format-number(  SalaryRequested  ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                                                                    </fo:inline>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                            <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="right" width="120pt" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center">
                                                                                                                                <fo:block>
                                                                                                                                    <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                                                                    <fo:inline font-size="8.0pt">
                                                                                                                                        <xsl:value-of select="format-number(  CalculatedCost  ,&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                                                                    </fo:inline>
                                                                                                                                </fo:block>
                                                                                                                            </fo:table-cell>
                                                                                                                        </fo:table-row>
                                                                                                                    </xsl:for-each>
                                                                                                                </fo:table-body>
                                                                                                            </fo:table>
                                                                                                        </xsl:for-each>
                                                                                                    </xsl:for-each>
                                                                                                </xsl:for-each>
                                                                                            </xsl:for-each>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="after" number-columns-spanned="4" text-align="right" width="270pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8.0pt" font-weight="bold">Total</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="after" text-align="right" width="180pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                                                    <fo:block />
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="after" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" >
                                                                                    <fo:block>
                                                                                        <fo:block color="black" space-before.optimum="-8pt">
                                                                                            <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.25pt" />
                                                                                        </fo:block>
                                                                                        <fo:inline font-size="8.0pt">$</fo:inline>
                                                                                        <fo:inline font-size="8.0pt">
                                                                                            <xsl:value-of select="format-number(sum(BudgetSummaryReport/CumilativePage/CalculationMethodology/BudgetOtherRateBaseForPeriod/Group/Details/CalculatedCost),&apos;#,###,###,###,###,###,###,###,##0.00&apos;)" />
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </xsl:if>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt"  display-align="center" text-align="start">
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
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
