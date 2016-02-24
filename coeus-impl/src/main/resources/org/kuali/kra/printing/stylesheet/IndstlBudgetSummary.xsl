<?xml version="1.0" encoding="ISO-8859-1"?>
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
<!-- Document generated with XSLfast v1.3 Visit www.XSLfast.com for information. -->
<xsl:stylesheet xmlns:fox="http://xml.apache.org/fop/extensions" xmlns:print="http://www.jcatalog.com/com.jcatalog.output.xslextensions.print.PrintElementFactory" xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:saxon="http://icl.com/saxon" extension-element-prefixes="saxon print">
    <xsl:variable name="currentDate">10-02-2004 17:54:26</xsl:variable>
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="BudgetSummaryWithoutHeader" page-height="11in" page-width="8.5in">
                    <fo:region-body  margin-left=".79in" margin-top="100.0pt" margin-bottom=".79in" margin-right=".79in">
                    </fo:region-body>
                    <fo:region-before extent="80pt" precedence="true"/>
                    <fo:region-after extent="28pt"  precedence="true"/>
                    <fo:region-start extent="28pt" precedence="false"/>
                    <fo:region-end extent="27pt" precedence="false"/>
                </fo:simple-page-master>
                <fo:simple-page-master master-name="CalculationMethodologyWithoutHeader" page-height="11in" page-width="8.5in">
                    <fo:region-body margin-left=".79in" margin-top="100.0pt" margin-bottom=".79in" margin-right=".79in">
                        
                    </fo:region-body>
                    <fo:region-before extent="80pt" precedence="true"/>
                    <fo:region-after extent="28pt" precedence="true"/>
                    <fo:region-start extent="28pt" precedence="false"/>
                    <fo:region-end extent="28pt" precedence="false"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <xsl:for-each select="BudgetSummaryReport">
                <xsl:if test="1">
                    <xsl:call-template name="BudgetSummaryWithoutHeader"/>
                </xsl:if>
                <xsl:if test="1">
                    <xsl:call-template name="CalculationMethodologyWithoutHeader"/>
                </xsl:if>
            </xsl:for-each>
        </fo:root>
    </xsl:template>
    <!-- ################# TEMPLATE FOR LAYOUT:  BudgetSummaryWithoutHeader.lay ######### -->
    <xsl:template name="BudgetSummaryWithoutHeader">
        <fo:page-sequence master-name="BudgetSummaryWithoutHeader" master-reference="BudgetSummaryWithoutHeader">
            <fo:static-content flow-name="xsl-region-before">
                <xsl:call-template name="BudgetHeader"/>
            </fo:static-content>
            <fo:static-content flow-name="xsl-region-after">
                <xsl:call-template name="BudgetFooter"/>
            </fo:static-content>
            <fo:static-content flow-name="xsl-region-start">
                <fo:block/>
            </fo:static-content>
            <fo:static-content flow-name="xsl-region-end">
                <fo:block/>
            </fo:static-content>
            <fo:flow flow-name="xsl-region-body">
                <fo:block/>
                <!-- GENERATE TABLE START-->
                <fo:block span="none">
                    <fo:table table-layout="fixed">
                        <fo:table-column column-width="100pt"/>
                        <fo:table-column column-width="125pt"/>
                        <fo:table-column column-width="49pt"/>
                        <fo:table-column column-width="49pt"/>
                        <fo:table-column column-width="29pt"/>
                        <fo:table-column column-width="29pt"/>
                        <fo:table-column column-width="50pt"/>
                        <fo:table-column column-width="20pt"/>
                        <fo:table-column column-width="55pt"/>
                        <fo:table-header>
                            <fo:table-row>
                                <fo:table-cell><fo:block/></fo:table-cell>
                                <fo:table-cell text-align="start" display-align="center">
                                    <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                                        <xsl:if test="count(ReportPage/BudgetSummary/SalarySummaryFromEDI/Group/Details) != 0">Personnel Category</xsl:if>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="start" display-align="center">
                                    <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                                        <xsl:if test="count(ReportPage/BudgetSummary/SalarySummaryFromEDI/Group/Details) != 0">Start Date</xsl:if>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="start" display-align="center">
                                    <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                                        <xsl:if test="count(ReportPage/BudgetSummary/SalarySummaryFromEDI/Group/Details) != 0">End Date</xsl:if>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="center" display-align="center">
                                    <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                                        
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="center" display-align="center">
                                    <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                                        
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="center" display-align="center">
                                    <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                                        <xsl:if test="count(ReportPage/BudgetSummary/SalarySummaryFromEDI/Group/Details) != 0">Percentage Charged/Effort</xsl:if>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="center" display-align="center">
                                    <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt" font-weight="bold">
                                        
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="center" display-align="center">
                                    <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt" font-weight="bold">
                                        <xsl:if test="count(ReportPage/BudgetSummary/SalarySummaryFromEDI/Group/Details) != 0">Salaries &amp; Wages</xsl:if>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-header>
                        <fo:table-body>
                            <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                            <xsl:for-each select="ReportPage/BudgetSummary/SalarySummaryFromEDI/Group">
                                <fo:table-row>
                                    <fo:table-cell text-align="start" display-align="before">
                                        <xsl:call-template name="SalarySummaryForEDI"/>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block/>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block/>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block/>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block/>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block/>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block/>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block/>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>
                </fo:block>
                <!-- GENERATE TABLE START-->
                <fo:block span="none">
                    <fo:table table-layout="fixed">
                        <fo:table-column column-width="540.0pt"/>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell text-align="start" display-align="before">
                                    <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="12.0pt">
                                        <xsl:if test="count(ReportPage/BudgetSummary/SalarySummaryFromEDI/Group/Details) != 0">
                                            <xsl:call-template name="SalarySummaryForEDITotals"/>
                                        </xsl:if>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                </fo:block>
                <!-- GENERATE TABLE START-->
                <fo:block span="none">
                    <fo:table table-layout="fixed">
                        <fo:table-column column-width="400pt"/>
                        <fo:table-column column-width="60pt"/>
                        <fo:table-body>
                            <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                            <xsl:for-each select="ReportPage/BudgetSummary/BudgetSummaryNonPersonnel/Group">
                                <fo:table-row>
                                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                                        <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                                            <xsl:value-of disable-output-escaping="no" select="Description"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block/>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell text-align="start" display-align="before">
                                        <xsl:call-template name="SummaryNonPersonnel"/>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block/>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                        <fo:table-body>
                            <fo:table-row><fo:table-cell>
                                    <fo:block></fo:block>
                                </fo:table-cell>
                                <!--<fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
									<fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">

Total Direct Costs</fo:block>
								</fo:table-cell>
								<fo:table-cell border-bottom-style="solid" border-bottom-width="0.5pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
									<fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">

$<xsl:value-of select="format-number(ReportPage/BudgetSummary/TotalDirectCost, '#,###,###,###,###,###')"/>
									</fo:block>
								</fo:table-cell>-->
                            </fo:table-row>
                        </fo:table-body>
                        
                    </fo:table>
                </fo:block>
                <!-- GENERATE TABLE START-->
                <!--<fo:block span="none">
					<fo:table table-layout="fixed">
						<fo:table-column column-width="446pt"/>
						<fo:table-body>
							<xsl:for-each select="ReportPage/BudgetSummary/BudgetIndirectCostsForReport/Group">
								<fo:table-row>
									<fo:table-cell text-align="start" display-align="before">
										<fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="12.0pt">
											<xsl:if test="count(Details) != 0">
												<xsl:call-template name="IndirectCosts"/>
											</xsl:if>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</xsl:for-each>
						</fo:table-body>
					</fo:table>
				</fo:block>-->
                <!-- GENERATE TABLE START-->
                <fo:block span="none">
                    <fo:table table-layout="fixed">
                        <fo:table-column column-width="430pt"/>
                        <fo:table-column column-width="75pt"/>
                        <fo:table-body>
                            <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                            <xsl:for-each select="ReportPage/BudgetSummary">
                                <fo:table-row>
                                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                                        <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                                            <xsl:text>Total Cost to Sponsor</xsl:text>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-bottom-style="solid" border-bottom-width="0.5pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                            
                                            $<xsl:value-of select="format-number(TotalCostToSponsor,'#,###,###,###,###,###,##0.00')"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <xsl:if test="number(TotalUnderrecoveryAmount) > 0">
                                    <fo:table-row>
                                        <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                                            <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                                                <xsl:text>Total Underrecovery Amount</xsl:text>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-bottom-style="solid" border-bottom-width="0.5pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                                            <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                                $<xsl:value-of select="format-number(TotalUnderrecoveryAmount,'#,###,###,###,###,###,##0.00')"/>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </xsl:if>
                                <fo:table-row>
                                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                                        <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                                            <xsl:text>Total Cost Sharing Amount</xsl:text>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-bottom-style="solid" border-bottom-width="0.5pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                            
                                            $<xsl:value-of select="format-number(TotalCostSharingAmount,'#,###,###,###,###,###,##0.00')"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                                        <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                                            <xsl:text>TOTAL COST OF PROJECT</xsl:text>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-bottom-style="solid" border-bottom-width="0.5pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                            <xsl:variable name="Sponsor">
                                                <xsl:value-of select="TotalCostToSponsor"/>
                                            </xsl:variable>
                                            <xsl:variable name="Underrecovery">
                                                <xsl:value-of select="TotalUnderrecoveryAmount"/>
                                            </xsl:variable>
                                            <xsl:variable name="CostSharing">
                                                <xsl:value-of select="TotalCostSharingAmount"/>
                                            </xsl:variable>
                                            $<xsl:value-of select="format-number(($Sponsor+$Underrecovery+$CostSharing),'#,###,###,###,###,##0.00')"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>
                </fo:block>
            </fo:flow>
        </fo:page-sequence>
    </xsl:template>
    <!-- GENERATED TEMPLATE SumCalculatedCost -->
    <xsl:template name="SumCalculatedCost">
        <!-- GENERATE TABLE START-->
        <fo:table table-layout="fixed">
            <fo:table-column column-width="88pt"/>
            <fo:table-body>
                <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                <xsl:for-each select="ReportPage/BudgetSummary/BudgetIndirectCostsForReport/Group/Details">
                    <fo:table-row>
                        <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                            <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                
                                $<xsl:value-of select="format-number(sum(CalculatedCost),'###,###,###,###,###,##0.00')"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </xsl:for-each>
            </fo:table-body>
        </fo:table>
    </xsl:template>
    <!-- GENERATED TEMPLATE SummaryNonPersonnel -->
    <xsl:template name="SummaryNonPersonnel">
        <!-- GENERATE TABLE START-->
        <fo:table table-layout="fixed" table-omit-footer-at-break="true">
            <fo:table-column column-width="250pt"/>
            <fo:table-column column-width="180pt"/>
            <fo:table-column column-width="75pt"/>
            <fo:table-footer>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block></fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt" font-weight="bold">
                            
                            Total <xsl:value-of select="Description"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell border-bottom-style="solid" border-bottom-width="0.5pt" border-top-style="solid" border-top-width="0.5pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                            
                            $<xsl:value-of select="format-number(sum(Details/CalculatedCost), 
                                           
                            '##,###,###,###,###,###,##0.00')"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-footer>                        
            <fo:table-body>
                <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                <xsl:for-each select="Details">
                    <fo:table-row>
                        <fo:table-cell padding-left="15.0pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                            <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">
                                <xsl:value-of disable-output-escaping="no" select="CostElementDescription"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-left="15.0pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                            <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">
                                <!--<xsl:choose>
									<xsl:when test="OnOffCampus = 'true'">
										<xsl:text>ON-CAMPUS</xsl:text>
									</xsl:when>
									<xsl:otherwise>
										<xsl:text>OFF-CAMPUS</xsl:text>
									</xsl:otherwise>
								</xsl:choose>-->
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                            <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                <xsl:text>$</xsl:text>
                                <xsl:value-of disable-output-escaping="no" select="format-number(CalculatedCost, '##,###,###,###,###,##0.00')"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </xsl:for-each>
            </fo:table-body>
        </fo:table>
    </xsl:template>
    <!-- GENERATED TEMPLATE SalarySummaryForEDI -->
    <xsl:template name="SalarySummaryForEDI">
        <!-- GENERATE TABLE START-->
        <fo:table table-layout="fixed" table-omit-footer-at-break="true">
            <fo:table-column column-width="100pt"/>
            <fo:table-column column-width="125pt"/>
            <fo:table-column column-width="49pt"/>
            <fo:table-column column-width="49pt"/>
            <fo:table-column column-width="29pt"/>
            <fo:table-column column-width="29pt"/>
            <fo:table-column column-width="50pt"/>
            <fo:table-column column-width="20pt"/>
            <fo:table-column column-width="55pt"/>
            <fo:table-header>
                <fo:table-row>
                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="before">
                        <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                            <xsl:value-of select="Description"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block/>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block/>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block/>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block/>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block/>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block/>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block/>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block/>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-header>
            <fo:table-footer>
                <fo:table-row>
                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" number-columns-spanned="7" text-align="end" display-align="center">
                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt" font-weight="bold">
                            Total <xsl:value-of select="Description"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell border-bottom-style="solid" border-bottom-width="0.5pt" border-top-style="solid" border-top-width="0.5pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell border-bottom-style="solid" border-bottom-width="0.5pt" border-top-style="solid" border-top-width="0.5pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                            $<xsl:value-of select="format-number(sum(Details/SalaryRequested), '##,###,###,###,###,###,##0.00')"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-footer>
            <fo:table-body border="1">
                <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                <xsl:for-each select="Details">
                    <fo:table-row>
                        <fo:table-cell padding-left="4.0pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                            <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">
                                <xsl:value-of disable-output-escaping="no" select="PersonName"/> 
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-left="2.0pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                            <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">
                                <xsl:value-of disable-output-escaping="no" select="CostElementDescription"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                            <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">
                                <xsl:value-of disable-output-escaping="no" select="StartDate"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                            <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">
                                <xsl:value-of disable-output-escaping="no" select="EndDate"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                            <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                            <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                            <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">
                                <xsl:choose>
                                    <xsl:when test="PersonName = 'Allocated Admin Support' or PercentEffort &lt;= 0.0">
                                        <xsl:text> </xsl:text>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="PercentCharged"/> / <xsl:value-of select="PercentEffort"/>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                            <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell text-align="end" display-align="center">
                            <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                <xsl:text>$</xsl:text>
                                <xsl:value-of disable-output-escaping="no" select="format-number(SalaryRequested, '##,###,###,###,###,###,##0.00')"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </xsl:for-each>
            </fo:table-body>
        </fo:table>
    </xsl:template>
    <!-- GENERATED TEMPLATE SalarySummaryForEDITotals -->
    <xsl:template name="SalarySummaryForEDITotals">
        <!-- GENERATE TABLE START-->
        <fo:table table-layout="fixed">
            <fo:table-column column-width="82pt"/>
            <fo:table-column column-width="340pt"/>
            <fo:table-column column-width="80pt"/>
            <fo:table-body>
                <fo:table-row>
                     <fo:table-cell><fo:block/></fo:table-cell>
                    <!--<fo:table-cell number-columns-spanned="2" text-align="start" display-align="before">
						<fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">
TOTAL SALARIES &amp; WAGES
</fo:block>
					</fo:table-cell>
					<fo:table-cell border-bottom-style="solid" border-bottom-width="0.5pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
						<fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
$<xsl:value-of select="format-number(sum(ReportPage/BudgetSummary/SalarySummaryFromEDI/Group/Details/SalaryRequested),'#,###,###,###,###,###,###')"/>
						</fo:block>
					</fo:table-cell>-->
                </fo:table-row>
                <!--<fo:table-row>
					<fo:table-cell text-align="start" display-align="before">
						<fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">

Fringe Benefits:

</fo:block>
					</fo:table-cell>
					<fo:table-cell text-align="start" display-align="before">
						<fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">

Total Fringe Benefits and Vacation Accrual:</fo:block>
					</fo:table-cell>
					<fo:table-cell border-bottom-style="solid" border-bottom-width="0.5pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
						<fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">

$<xsl:value-of select="format-number(sum(ReportPage/BudgetSummary/SalarySummaryFromEDI/Group/Details/Fringe),'#,###,###,###,###,###,###')"/>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<fo:table-row>
					<fo:table-cell number-columns-spanned="2" text-align="start" display-align="before">
						<fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">

TOTAL SALARIES &amp; WAGES</fo:block>
					</fo:table-cell>
					<fo:table-cell border-bottom-style="solid" border-bottom-width="0.5pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
						<fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
							<xsl:variable name="totSalaries">
								<xsl:value-of select="sum(ReportPage/BudgetSummary/SalarySummaryFromEDI/Group/Details/SalaryRequested)"/>
							</xsl:variable>
							<xsl:variable name="totFringe">
								<xsl:value-of select="sum(ReportPage/BudgetSummary/SalarySummaryFromEDI/Group/Details/Fringe)"/>
							</xsl:variable>
							<xsl:variable name="totSalariesAndWages">
								<xsl:value-of select="format-number(($totSalaries), 

'#,###,###,###,###,###,###,###,###')"/>
							</xsl:variable>



$<xsl:value-of select="$totSalariesAndWages"/>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>-->
            </fo:table-body>
        </fo:table>
    </xsl:template>
    <!-- GENERATED TEMPLATE IndirectCosts -->
    <xsl:template name="IndirectCosts">
        <!-- GENERATE TABLE START-->
        <fo:table table-layout="fixed" table-omit-footer-at-break="true">
            <fo:table-column column-width="440pt"/>
            <fo:table-column column-width="91pt"/>
            <fo:table-header>
                <fo:table-row>
                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                        <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                            <xsl:text>F&amp;A (Indirect) Costs</xsl:text>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-header>
            <fo:table-footer>
                <fo:table-row>
                    <fo:table-cell padding-left="15.0pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                        <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">
                            <xsl:text>Total F&amp;A (Indirect) Costs</xsl:text>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell border-bottom-style="solid" border-bottom-width="0.5pt" border-top-style="solid" border-top-width="0.5pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                            $<xsl:value-of select="format-number(sum(Details/CalculatedCost),'###,###,###,###,###,##0.00')"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-footer>
            <fo:table-body>
                <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                <xsl:for-each select="Details">
                    <fo:table-row>
                        <fo:table-cell padding-left="15.0pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                            <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">
                                <xsl:choose>
                                    <xsl:when test="OnOffCampus = 'true'">
                                        <xsl:text>ON-CAMPUS</xsl:text>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:text>OFF-CAMPUS</xsl:text>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                            <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                <xsl:text>$</xsl:text>
                                <xsl:value-of disable-output-escaping="no" select="format-number(CalculatedCost, '##,###,###,###,###,##0.00')"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </xsl:for-each>
            </fo:table-body>
        </fo:table>
    </xsl:template>
    <!-- ################# END OF TEMPLATE FOR LAYOUT:  

BudgetSummaryWithoutHeader.lay ## -->
    <!-- ################# TEMPLATE FOR LAYOUT:  

CalculationMethodologyWithoutHeader.lay ######### -->
    <xsl:template name="CalculationMethodologyWithoutHeader">
        <fo:page-sequence master-name="CalculationMethodologyWithoutHeader" master-reference="CalculationMethodologyWithoutHeader">
            <fo:static-content flow-name="xsl-region-before">
                <xsl:call-template name="BudgetHeader"/>
            </fo:static-content>
            <fo:static-content flow-name="xsl-region-after">
                <xsl:call-template name="BudgetFooter"/>
            </fo:static-content>
            <fo:static-content flow-name="xsl-region-start">
                <fo:block/>
            </fo:static-content>
            <fo:static-content flow-name="xsl-region-end">
                <fo:block/>
            </fo:static-content>
            <fo:flow flow-name="xsl-region-body">
                <fo:block/>
                <!-- GENERATE TABLE START-->
                <fo:block span="none">
                    <fo:table table-layout="fixed">
                        <fo:table-column column-width="528pt"/>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell text-align="start" display-align="before">
                                    <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="10.0pt" font-weight="bold">
                                        <xsl:text>Calculation Methodology</xsl:text>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                </fo:block>
                <!-- GENERATE TABLE START-->
                <fo:block span="none">
                    <fo:table table-layout="fixed" table-omit-footer-at-break="true">
                        <fo:table-column column-width="386pt"/>
                        <fo:table-column column-width="60pt"/>
                        <fo:table-column column-width="81pt"/>
                        <fo:table-header>
                            <fo:table-row>
                                <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" number-columns-spanned="3" text-align="start" display-align="center">
                                    <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                                        <xsl:text>The full F&amp;A (Indirect) Cost Rate is applied to the total 
                                            
                                        direct costs, less the following exclusions</xsl:text>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-header>
                        <fo:table-footer>
                            <fo:table-row>
                                <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                                    <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt" font-weight="bold">
                                        <xsl:text>Total exclusions from F&amp;A base</xsl:text>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block/>
                                </fo:table-cell>
                                <fo:table-cell border-bottom-style="solid" border-bottom-width="0.5pt" border-top-style="solid" border-top-width="0.5pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                                    <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                        
                                        $<xsl:value-of select="format-number(sum(ReportPage/CalculationMethodology/BudgetOHExclusions/Group/Details/CalculatedCost), 
                                                       
                                        '#,###,###,###,###,###,###,###,##0.00')"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-footer>
                        <fo:table-body>
                            <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                            <xsl:for-each select="ReportPage/CalculationMethodology/BudgetOHExclusions/Group/Details">
                                <fo:table-row>
                                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                            <xsl:value-of disable-output-escaping="no" select="CostElementDescription"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                       <fo:block/> 
                                    </fo:table-cell>
                                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                            <xsl:text>$</xsl:text>
                                            <xsl:value-of disable-output-escaping="no" select="format-number(CalculatedCost, '#,###,###,###,###,###,##0.00')"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>
                </fo:block>
                <!-- GENERATE TABLE START-->
                <fo:block span="none">
                    <fo:table table-layout="fixed" table-omit-footer-at-break="true">
                        <fo:table-column column-width="385pt"/>
                        <fo:table-column column-width="63pt"/>
                        <fo:table-column column-width="80pt"/>
                        <fo:table-header>
                            <fo:table-row>
                                <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" number-columns-spanned="3" text-align="start" display-align="center">
                                    <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                                        <xsl:text>The Allocated Administrative Support and Allocated Lab 
                                            
                                            Expense Rates are applied to the total direct costs, less the following 
                                            
                                        exclusions.</xsl:text>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-header>
                        <fo:table-footer>
                            <fo:table-row>
                                <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                                    <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt" font-weight="bold">
                                        <xsl:text>Total exclusions from Allocated Expense 
                                            
                                        base</xsl:text>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block/>
                                </fo:table-cell>
                                <fo:table-cell border-bottom-style="solid" border-bottom-width="0.5pt" border-top-style="solid" border-top-width="0.5pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                                    <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                        
                                        $<xsl:value-of select="format-number(sum(ReportPage/CalculationMethodology/BudgetLAExclusions/Group/Details/CalculatedCost), 
                                                       
                                        '#,###,###,###,###,###,###,###,##0.00')"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block/>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block/>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block/>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-footer>
                        <fo:table-body>
                            <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                            <xsl:for-each select="ReportPage/CalculationMethodology/BudgetLAExclusions/Group/Details">
                                <fo:table-row>
                                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                            <xsl:value-of disable-output-escaping="no" select="CostElementDescription"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block/>
                                    </fo:table-cell>
                                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                            <xsl:text>$</xsl:text>
                                            <xsl:value-of disable-output-escaping="no" select="format-number(CalculatedCost, '#,###,###,###,###,##0.00')"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>
                </fo:block>
                <!-- GENERATE TABLE START-->
                <fo:block span="none">
                    <fo:table table-layout="fixed">
                        <fo:table-column column-width="527pt"/>
                        <fo:table-body>
                            <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                            <xsl:for-each select="ReportPage/CalculationMethodology/BudgetOHRateBaseForPeriod/Group">
                                <fo:table-row>
                                    <fo:table-cell text-align="start" display-align="before">
                                        <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">
                                            <xsl:if test="count(Details) != 0">
                                                
                                                __________________________________________________________________________________________________________________
                                                
                                                <xsl:call-template name="OHRateBase"/>
                                            </xsl:if>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>
                </fo:block>
                <!-- GENERATE TABLE START-->
                <fo:block span="none">
                    <fo:table table-layout="fixed">
                        <fo:table-column column-width="456pt"/>
                        <fo:table-header>
                            <fo:table-row>
                                <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                                    <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                                        <xsl:if test="count(ReportPage/CalculationMethodology/BudgetEBRateBaseForPeriod/Group/Details) != 0"> 
                                            
                                            __________________________________________________________________________________________________________________
                                            
                                            <xsl:text>Employee Benefit Rates and Base</xsl:text>
                                        </xsl:if>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-header>
                        <fo:table-body>
                            <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                            <xsl:for-each select="ReportPage/CalculationMethodology/BudgetEBRateBaseForPeriod">
                                <fo:table-row>
                                    <fo:table-cell text-align="start" display-align="before">
                                        <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="12.0pt">
                                            <xsl:if test="count(Group/Details) != 0">
                                                <xsl:call-template name="RatesAndBase"/>
                                            </xsl:if>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>
                </fo:block>
                <!-- GENERATE TABLE START-->
                <fo:block span="none">
                    <fo:table table-layout="fixed">
                        <fo:table-column column-width="457pt"/>
                        <fo:table-header>
                            <fo:table-row>
                                <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                                    <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                                        <xsl:if test="count(ReportPage/CalculationMethodology/BudgetLARateBaseForPeriod/Group/Details) != 0"> 
                                            
                                            __________________________________________________________________________________________________________________
                                            
                                            <xsl:text>Allocated Administrative Support and Lab Expense Rates 
                                                
                                            and Base</xsl:text>
                                        </xsl:if>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-header>
                        <fo:table-body>
                            <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                            <xsl:for-each select="ReportPage/CalculationMethodology/BudgetLARateBaseForPeriod">
                                <fo:table-row>
                                    <fo:table-cell text-align="start" display-align="before">
                                        <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="12.0pt">
                                            <xsl:if test="count(Group/Details) != 0">
                                                <xsl:call-template name="RatesAndBase"/>
                                            </xsl:if>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>
                </fo:block>
                <!-- GENERATE TABLE START-->
                <fo:block span="none">
                    <fo:table table-layout="fixed">
                        <fo:table-column column-width="457pt"/>
                        <fo:table-header>
                            <fo:table-row>
                                <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                                    <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                                        <xsl:if test="count(ReportPage/CalculationMethodology/BudgetVacRateBaseForPeriod/Group/Details) != 0"> 
                                            
                                            __________________________________________________________________________________________________________________
                                            
                                            <xsl:text>Vacation Accrual Rates and Base</xsl:text>
                                        </xsl:if>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-header>
                        <fo:table-body>
                            <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                            <xsl:for-each select="ReportPage/CalculationMethodology/BudgetVacRateBaseForPeriod">
                                <fo:table-row>
                                    <fo:table-cell text-align="start" display-align="before">
                                        <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="12.0pt">
                                            <xsl:if test="count(Group/Details) != 0">
                                                <xsl:call-template name="RatesAndBase"/>
                                            </xsl:if>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>
                </fo:block>null<!-- GENERATE TABLE START-->
                <fo:block span="none">
                    <fo:table table-layout="fixed">
                        <fo:table-column column-width="457pt"/>
                        <fo:table-header>
                            <fo:table-row>
                                <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                                    <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                                        <xsl:if test="count(ReportPage/CalculationMethodology/BudgetOtherRateBaseForPeriod/Group/Details) != 0"> 
                                            
                                            _______________________________________________________________________________________________________________________
                                            
                                            <xsl:text>Other Rates and Base</xsl:text>
                                        </xsl:if>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-header>
                        <fo:table-body>
                            <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                            <xsl:for-each select="ReportPage/CalculationMethodology/BudgetOtherRateBaseForPeriod">
                                <fo:table-row>
                                    <fo:table-cell text-align="start" display-align="before">
                                        <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="12.0pt">
                                            <xsl:if test="count(Group/Details) != 0">
                                                <xsl:call-template name="RatesAndBase"/>
                                            </xsl:if>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>
                </fo:block>
            </fo:flow>
        </fo:page-sequence>
    </xsl:template>
    <!-- GENERATED TEMPLATE RatesAndBase -->
    <xsl:template name="RatesAndBase">
        <!-- GENERATE TABLE START-->
        <fo:table table-layout="fixed" table-omit-footer-at-break="true">
            <fo:table-column column-width="80pt"/>
            <fo:table-column column-width="80pt"/>
            <fo:table-column column-width="56pt"/>
            <fo:table-column column-width="65pt"/>
            <fo:table-column column-width="103pt"/>
            <fo:table-column column-width="72pt"/>
            <fo:table-header>
                <fo:table-row>
                    <fo:table-cell padding-left="15.0pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                        <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                            <xsl:text>Start Date</xsl:text>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                        <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                            <xsl:text>End Date</xsl:text>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                        <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                            <xsl:text>Campus</xsl:text>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt" font-weight="bold">
                            <xsl:text>Rate</xsl:text>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt" font-weight="bold">
                            <xsl:text>Base</xsl:text>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt" font-weight="bold">
                            <xsl:text>Calculated Cost</xsl:text>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-header>
            <fo:table-footer>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block/>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block/>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block/>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block/>
                    </fo:table-cell>
                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt" font-weight="bold">
                            <xsl:text>Total</xsl:text>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell border-bottom-style="solid" border-bottom-width="0.5pt" border-top-style="solid" border-top-width="0.5pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                            
                            $<xsl:value-of select="format-number(sum(Group/Details/CalculatedCost), 
                                           
                            '#,###,###,###,###,###,###,###,##0.00')"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-footer>
            <fo:table-body>
                <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                <xsl:for-each select="Group">
                    <fo:table-row>
                        <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" number-columns-spanned="6" text-align="start" display-align="center">
                            <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                                <xsl:value-of disable-output-escaping="no" select="Description"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                    <xsl:for-each select="Details">
                        <fo:table-row>
                            <fo:table-cell padding-left="15.0pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                                <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">
                                    <xsl:value-of disable-output-escaping="no" select="StartDate"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                                <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">
                                    <xsl:value-of disable-output-escaping="no" select="EndDate"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                                <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">
                                    <xsl:choose>
                                        <xsl:when test="OnOffCampus = 'true'">
                                            <xsl:text>On</xsl:text>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <xsl:text>Off</xsl:text>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                                <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                    <xsl:value-of disable-output-escaping="no" select="format-number(AppliedRate, '###,###,###,###,###,##0.00')"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                                <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                    <xsl:text>$</xsl:text>
                                    <xsl:value-of disable-output-escaping="no" select="format-number(SalaryRequested, '#,###,###,###,###,###,###,###,##0.00')"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                                <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                    <xsl:text>$</xsl:text>
                                    <xsl:value-of disable-output-escaping="no" select="format-number(CalculatedCost, '#,###,###,###,###,###,##0.00')"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:for-each>
                </xsl:for-each>
            </fo:table-body>
        </fo:table>
    </xsl:template>
    <!-- GENERATED TEMPLATE OHRateBase -->
    <xsl:template name="OHRateBase">
        <!-- GENERATE TABLE START-->
        <fo:table table-layout="fixed" table-omit-footer-at-break="true">
            <fo:table-column column-width="79pt"/>
            <fo:table-column column-width="81pt"/>
            <fo:table-column column-width="39pt"/>
            <fo:table-column column-width="35pt"/>
            <fo:table-column column-width="75pt"/>
            <fo:table-column column-width="71pt"/>
            <fo:table-column column-width="74pt"/>
            <fo:table-header>
                <fo:table-row>
                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" number-columns-spanned="6" text-align="start" display-align="center">
                        <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                            <xsl:text>F&amp;A (Indirect) Cost Rates and Base    
                                
                            </xsl:text>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell padding-left="15.0pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                        <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                            <xsl:text>Start Date</xsl:text>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                        <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                            <xsl:text>End Date</xsl:text>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                        <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt" font-weight="bold">
                            <xsl:text>Campus</xsl:text>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt" font-weight="bold">
                            <xsl:text>Rate</xsl:text>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt" font-weight="bold">
                            <xsl:text>Rate Type</xsl:text>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt" font-weight="bold">
                            <xsl:text>Base</xsl:text>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt" font-weight="bold">
                            <xsl:text>Indirect Cost</xsl:text>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-header>
            <fo:table-footer>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block/>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block/>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block/>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block/>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block/>
                    </fo:table-cell>
                    <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                            <xsl:text>Total</xsl:text>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell border-bottom-style="solid" border-bottom-width="0.5pt" border-top-style="solid" border-top-width="0.5pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                        <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                            
                            $<xsl:value-of select="format-number(sum(Details/CalculatedCost), 
                                           
                            '#,###,###,###,###,###,###,###,##0.00')"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-footer>
            <fo:table-body>
                <fo:table-row><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
                <xsl:for-each select="Details">
                    <fo:table-row>
                        <fo:table-cell padding-left="15.0pt" padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                            <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">
                                <xsl:value-of disable-output-escaping="no" select="StartDate"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                            <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">
                                <xsl:value-of disable-output-escaping="no" select="EndDate"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="start" display-align="center">
                            <fo:block white-space-collapse="true" text-align="start" color="#000000"  font-size="8.0pt">
                                <xsl:choose>
                                    <xsl:when test="OnOffCampus = 'true'">
                                        <xsl:text>On</xsl:text>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:text>Off</xsl:text>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                            <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                <xsl:value-of disable-output-escaping="no" select="format-number(AppliedRate, '##,###,###,###,##0.00')"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                            <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                <xsl:value-of disable-output-escaping="no" select="RateClassDesc"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                            <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                <xsl:text>$</xsl:text>
                                <xsl:value-of disable-output-escaping="no" select="format-number(SalaryRequested, '#,###,###,###,###,###,###,##0.00')"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-top="2.0pt" padding-bottom="2.0pt" text-align="end" display-align="center">
                            <fo:block white-space-collapse="true" text-align="end" color="#000000"  font-size="8.0pt">
                                <xsl:text>$</xsl:text>
                                <xsl:value-of disable-output-escaping="no" select="format-number(CalculatedCost, '#,###,###,###,###,###,##0.00')"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </xsl:for-each>
            </fo:table-body>
        </fo:table>
    </xsl:template>
    <!-- ################# END OF TEMPLATE FOR LAYOUT:  

CalculationMethodologyWithoutHeader.lay ## -->
    <!-- ################# TEMPLATE FOR HEADER 

##############################-->
    <xsl:template name="BudgetHeader">
        <fo:block-container position="absolute" top="12pt" left="28pt" height="15.0pt" width="384.0pt" border-width="1.0pt" display-align="before">
            <fo:block span="none" white-space-collapse="true" color="#000000"  font-size="12.0pt" font-weight="bold" text-align="start" position="relative" top="12pt" left="28pt" height="15.0pt" width="384.0pt">
                <xsl:text>Coeus Proposal Development - Industrial Budget Summary</xsl:text>
            </fo:block>
        </fo:block-container>
        <fo:block-container position="absolute" top="30pt" left="28pt" height="15.0pt" width="100.0pt" border-width="1.0pt" display-align="before">
            <fo:block span="none" white-space-collapse="true" color="#000000"  font-size="10.0pt" font-weight="bold" text-align="start" position="relative" top="30pt" left="28pt" height="15.0pt" width="100.0pt">
                <xsl:text>Proposal Number:</xsl:text>
            </fo:block>
        </fo:block-container>
        <fo:block-container position="absolute" top="30pt" left="140pt" height="15.0pt" width="132.0pt" border-width="1.0pt" display-align="before">
            <fo:block span="none" white-space-collapse="true" color="#000000"  font-size="10.0pt" text-align="start" position="relative" top="30pt" left="140pt" height="15.0pt" width="132.0pt">
                <xsl:value-of disable-output-escaping="no" select="ReportHeader/ProposalNumber"/>
            </fo:block>
        </fo:block-container>
        <fo:block-container position="absolute" top="30pt" left="250pt" height="15.0pt" width="148pt" border-width="1.0pt" display-align="before">
            <fo:block span="none" white-space-collapse="true" color="#000000"  font-size="10.0pt" font-weight="bold" text-align="end" position="relative" top="30pt" left="250pt" height="15.0pt" width="148.0pt">
                Budget Version : <xsl:value-of select="ReportHeader/BudgetVersion"/>
            </fo:block>
        </fo:block-container>
        <fo:block-container position="absolute" top="45pt" left="28pt" height="15.0pt" width="100.0pt" border-width="1.0pt" display-align="before">
            <fo:block span="none" white-space-collapse="true" color="#000000"  font-size="10.0pt" font-weight="bold" text-align="start" position="relative" top="45pt" left="28pt" height="15.0pt" width="100.0pt">
                <xsl:text>Proposal Title:</xsl:text>
            </fo:block>
        </fo:block-container>
        <fo:block-container position="absolute" top="45pt" left="140pt" height="15.0pt" width="350.0pt" border-width="1.0pt" display-align="before" wrap-option="no-wrap" overflow="hidden">
            <fo:block span="none" white-space-collapse="true" color="#000000"  font-size="10.0pt" text-align="start" position="relative" top="45pt" left="140pt" height="15.0pt" width="200.0pt">
                <xsl:value-of disable-output-escaping="no" select="ReportHeader/ProposalTitle"/>
            </fo:block>
        </fo:block-container>
        <fo:block-container position="absolute" top="60pt" left="28pt" height="15.0pt" width="100.0pt" border-width="1.0pt" display-align="before">
            <fo:block span="none" white-space-collapse="true" color="#000000"  font-size="10.0pt" font-weight="bold" text-align="start" position="relative" top="60pt" left="28pt" height="15.0pt" width="100.0pt">
                <xsl:text>Investigator Name:</xsl:text>
            </fo:block>
        </fo:block-container>
        <fo:block-container position="absolute" top="60pt" left="140pt" height="15.0pt" width="250.0pt" border-width="1.0pt" display-align="before">
            <fo:block span="none" white-space-collapse="true" color="#000000"  font-size="10.0pt" text-align="start" position="relative" top="60pt" left="140pt" height="15.0pt" width="200.0pt">
                <xsl:value-of disable-output-escaping="no" select="ReportHeader/PIName"/>
            </fo:block>
        </fo:block-container>
        <fo:block-container position="absolute" top="75pt" left="28pt" height="15.0pt" width="100.0pt" border-width="1.0pt" display-align="before">
            <fo:block span="none" white-space-collapse="true" color="#000000"  font-size="10.0pt" font-weight="bold" text-align="start" position="relative" top="75pt" left="28pt" height="15.0pt" width="148.0pt">
                Period : <xsl:value-of select="ReportHeader/Period"/>
            </fo:block>
        </fo:block-container>
        <fo:block-container position="absolute" top="75pt" left="140pt" height="15.0pt" width="135.0pt" border-width="1.0pt" display-align="before">
            <fo:block span="none" white-space-collapse="true" color="#000000"  font-size="10.0pt" text-align="start" position="relative" top="90pt" left="140pt" height="15.0pt" width="135.0pt">
                <xsl:value-of select="ReportHeader/PeriodStartDate"/> - <xsl:value-of select="ReportHeader/PeriodEndDate"/>
            </fo:block>
        </fo:block-container>
          <fo:block-container position="absolute" top="85pt" left="28pt" height="15.0pt" width="100.0pt" border-width="1.0pt" display-align="before">
            <fo:block span="none" white-space-collapse="true" color="#000000"  font-size="10.0pt" font-weight="bold" text-align="start" position="relative" top="90pt" left="28pt" height="15.0pt" width="100.0pt">
                <xsl:text>Comments:</xsl:text>
            </fo:block>
        </fo:block-container>
        <fo:block-container position="absolute" top="85pt" left="140pt" height="15.0pt" width="250.0pt" border-width="1.0pt" display-align="before">
            <fo:block span="none" white-space-collapse="true" color="#000000"  font-size="10.0pt" text-align="start" position="relative" top="120pt" left="140pt" height="15.0pt" width="200.0pt">
                <xsl:value-of disable-output-escaping="no" select="ReportHeader/Comments"/>
            </fo:block>
        </fo:block-container>
        <!-- OFFSET = 2.0 -->
        <!--<fo:block-container  position="absolute" top="57pt" left="23pt" 

height="25.0pt" width="570.0pt" >

 <fo:block>

  <fo:instream-foreign-object>

   <svg:svg xmlns:svg="http://www.w3.org/2000/svg" width = "555.0" 

height="10.0">

    <svg:g style="stroke:#000000; stroke-width:2.0">

     <svg:line x1="3.0pt" y1="3.0pt" x2="548.0pt" y2="3.0pt" />

     </svg:g>

   </svg:svg>

  </fo:instream-foreign-object>

 </fo:block>

 

 </fo:block-container>-->
    </xsl:template>
    <!-- ################# END TEMPLATE FOR HEADER 

##############################-->
    <!-- ################# TEMPLATE FOR FOOTER 

##################################-->
    <xsl:template name="BudgetFooter">
        <fo:block-container position="absolute" top="1pt" left="28pt" height="15.0pt" width="268.0pt" border-width="1.0pt" display-align="before">
            <fo:block text-align="left"  font-size="8.0pt">
            </fo:block>
        </fo:block-container>
    </xsl:template>
    <!-- ################# END TEMPLATE FOR FOOTER 

##################################-->
    <xsl:template match="b">
        <fo:inline font-weight="bold">
            <xsl:apply-templates/>
        </fo:inline>
    </xsl:template>
    <xsl:template match="u">
        <fo:inline text-decoration="underline">
            <xsl:apply-templates/>
        </fo:inline>
    </xsl:template>
    <xsl:template match="i">
        <fo:inline font-style="italic">
            <xsl:apply-templates/>
        </fo:inline>
    </xsl:template>
    <xsl:template match="s">
        <fo:inline font-size="{@s}">
            <xsl:apply-templates/>
        </fo:inline>
    </xsl:template>
    <xsl:template match="UP">
        <fo:inline vertical-align="super">
            <xsl:apply-templates/>
        </fo:inline>
    </xsl:template>
    <xsl:template match="DN">
        <fo:inline vertical-align="sub">
            <xsl:apply-templates/>
        </fo:inline>
    </xsl:template>
    <xsl:template match="sup">
        <fo:inline vertical-align="super">
            <xsl:apply-templates/>
        </fo:inline>
    </xsl:template>
    <xsl:template match="sub">
        <fo:inline vertical-align="sub">
            <xsl:apply-templates/>
        </fo:inline>
    </xsl:template>
</xsl:stylesheet>
