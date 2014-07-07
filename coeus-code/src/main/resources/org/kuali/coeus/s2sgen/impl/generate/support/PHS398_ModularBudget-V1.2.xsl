<?xml version="1.0" encoding="UTF-8"?>
<!-- $Revision: 1.0 $ -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
	xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0"
	xmlns:PHS398_ModularBudget_1_2="http://apply.grants.gov/forms/PHS398_ModularBudget_1_2-V1.2"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0"
	xmlns:xs="http://www.w3.org/2001/XMLSchema">

	<xsl:output method="xml" indent="yes" />

	<xsl:template match="/">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master master-name="first"
					page-height="8.5in" page-width="11.0in" margin-left="1.0in"
					margin-right="1.0in">
					<fo:region-body margin-top="0.5in" margin-bottom="1.0in" />
					<fo:region-before region-name="region-before-first"
						extent="inherit" />
				</fo:simple-page-master>

				<fo:simple-page-master master-name="rest"
					page-height="8.5in" page-width="11.0in" margin-left="1.0in"
					margin-right="1.0in">
					<fo:region-body margin-top="0.5in" margin-bottom="1.0in" />
					<fo:region-after region-name="region-after-all"
						extent="inherit" />
				</fo:simple-page-master>

				<fo:page-sequence-master master-name="all-pages">
					<fo:repeatable-page-master-alternatives>
						<fo:conditional-page-master-reference
							master-reference="first" page-position="first" />
						<fo:conditional-page-master-reference
							master-reference="rest" page-position="rest" />
					</fo:repeatable-page-master-alternatives>
				</fo:page-sequence-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="first" format="1">
				<fo:static-content flow-name="region-before-first">
					<!-- PHS Modular Budgets header and OMB label -->
					<fo:block-container position="absolute" left="0.25in" top="1.0in" height="15px">
						<fo:block font-size="12px" font-family="Helvetica" font-weight="bold" text-align="center">PHS 398 Modular Budget</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="0.25in" top="1.2in" height="9px">
						<fo:block text-align="end" font-size="6px" font-family="Helvetica">OMB Number: 0925-0001</fo:block>
					</fo:block-container>
				</fo:static-content>

				<fo:flow flow-name="xsl-region-body">
					<fo:block>
						<xsl:for-each
							select="//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:Periods[position() = 1]">

							<!-- Budget period label with the element number -->
							<fo:block-container position="absolute" left="0.25in" top="1.25in" height="0.25in" border-style="solid" border-color="black" border-top-width="1px" border-bottom-width="0.5px" border-left-width="1px" border-right-width="1px">
								<fo:block font-size="10px" font-family="Helvetica" font-weight="bold" padding-before="2px" text-align="center">
									Budget Period:
									<xsl:value-of select="position()" />
								</fo:block>
							</fo:block-container>

							<xsl:call-template name="modularBudgetPeriods" />
						</xsl:for-each>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>

			<fo:page-sequence master-reference="rest" format="1">
				<fo:flow flow-name="xsl-region-body">
					<fo:block>
					<xsl:for-each
						select="//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:Periods[position() != 1]">

						<!-- Budget period label with the element number -->
					<fo:block-container position="absolute" left="0.25in" top="0.5in" height="15px">
						<fo:block font-size="12px" font-family="Helvetica" font-weight="bold" text-align="center">PHS 398 Modular Budget</fo:block>
					</fo:block-container>
							<fo:block-container position="absolute" left="0.25in" top="1.25in" height="0.25in" border-style="solid" border-color="black" border-top-width="1px" border-bottom-width="0.5px" border-left-width="1px" border-right-width="1px">
								<fo:block font-size="10px" font-family="Helvetica" font-weight="bold" padding-before="2px" text-align="center">
								Budget Period:
								<xsl:value-of select="position() + 1" />
							</fo:block>
						</fo:block-container>

						<xsl:call-template name="modularBudgetPeriods" />
						<fo:block break-after="page" />
					</xsl:for-each>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>


			<fo:page-sequence master-reference="rest" format="1">
				<fo:flow flow-name="xsl-region-body">
					<xsl:call-template name="cumulativeBudgetInfo" />
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>

	<!-- Template for all the budget periods -->
	<xsl:template name="modularBudgetPeriods">

					<fo:block-container position="absolute" left="0.25in" top="1.5in" height="0.3in" text-indent="3.0in" border-color="black" border-style="solid" border-top-width="1px" border-bottom-width="0.5px" border-left-width="1px" border-right-width="1px">
                    	<fo:block>
						<fo:inline font-size="9px" font-family="Helvetica" font-weight="bold">Start Date:&#160;</fo:inline>
						<fo:inline font-size="9px" font-family="Helvetica">
                            <xsl:choose>
                                <xsl:when
                                    test="not(PHS398_ModularBudget_1_2:BudgetPeriodStartDate) or PHS398_ModularBudget_1_2:BudgetPeriodStartDate = ''">
                                    <fo:inline color="#FFFFFF">&#160;</fo:inline>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:call-template name="formatDate">
                                        <xsl:with-param name="value"
                                            select="PHS398_ModularBudget_1_2:BudgetPeriodStartDate" />
                                    </xsl:call-template>
                                </xsl:otherwise>
                            </xsl:choose>
                        </fo:inline>

                        <fo:inline>&#160;&#160;&#160;&#160;&#160;</fo:inline>

						<fo:inline font-size="9px" font-family="Helvetica" font-weight="bold">End Date:&#160;</fo:inline>
						<fo:inline font-size="9px" font-family="Helvetica">
                            <xsl:choose>
                                <xsl:when
                                    test="not(PHS398_ModularBudget_1_2:BudgetPeriodEndDate) or PHS398_ModularBudget_1_2:BudgetPeriodEndDate = ''">
                                    <fo:inline color="#FFFFFF">&#160;</fo:inline>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:call-template name="formatDate">
                                        <xsl:with-param name="value"
                                            select="PHS398_ModularBudget_1_2:BudgetPeriodEndDate" />
                                    </xsl:call-template>
                                </xsl:otherwise>
                            </xsl:choose>
                        	</fo:inline>
                        </fo:block>
                    </fo:block-container>

		<!-- A. Direct Cost -->

        			<fo:block-container position="absolute" left="0.25in" top="1.8in" height="1.4in" border-color="black" border-style="solid" border-top-width="0.5px" border-bottom-width="0.5px" border-left-width="1px" border-right-width="1px">
                            <fo:block />
        				</fo:block-container>
					<fo:block-container position="absolute" left="0.5in" top="2.0in" height="15px">
						<fo:block font-size="10pt" font-family="Helvetica" font-weight="bold">A. Direct Costs</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="6.75in" top="2.0in" height="12px" width="1.4in">
						<fo:block text-align="end" font-size="9px" font-family="Helvetica" font-weight="bold">Funds Requested ($)</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="4.5in" top="2.2in" height="12px" width="2.0in">
						<fo:block text-align="end" font-size="9px" font-family="Helvetica">Direct Cost less Consortium F&amp;A*</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="6.75in" top="2.2in" height="12px" width="1.4in">
						<fo:block text-align="end" font-size="9px" font-family="Helvetica" font-weight="normal">
                            <xsl:choose>
                                <xsl:when
                                    test="not(PHS398_ModularBudget_1_2:DirectCost/PHS398_ModularBudget_1_2:DirectCostLessConsortiumFandA) or PHS398_ModularBudget_1_2:DirectCost/PHS398_ModularBudget_1_2:DirectCostLessConsortiumFandA = ''">
                                    <fo:inline color="#FFFFFF">&#160;</fo:inline>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:value-of
                                        select="format-number(PHS398_ModularBudget_1_2:DirectCost/PHS398_ModularBudget_1_2:DirectCostLessConsortiumFandA, '#,##0.00')" />
                                </xsl:otherwise>
                            </xsl:choose>
                        </fo:block>
                    </fo:block-container>

					<fo:block-container position="absolute" left="4.5in" top="2.4in" height="12px" width="2.0in">
						<fo:block text-align="end" font-size="9px" font-family="Helvetica">Consortium F&amp;A</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="6.75in" top="2.4in" height="12px" width="1.4in">
						<fo:block text-align="end" font-size="9px" font-family="Helvetica">
                            <xsl:choose>
                                <xsl:when
                                    test="not(PHS398_ModularBudget_1_2:DirectCost/PHS398_ModularBudget_1_2:ConsortiumFandA) or PHS398_ModularBudget_1_2:DirectCost/PHS398_ModularBudget_1_2:ConsortiumFandA = ''">
                                    <fo:inline color="#FFFFFF">&#160;</fo:inline>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:value-of
                                        select="format-number(PHS398_ModularBudget_1_2:DirectCost/PHS398_ModularBudget_1_2:ConsortiumFandA, '#,##0.00')" />
                                </xsl:otherwise>
                            </xsl:choose>
                        </fo:block>
                    </fo:block-container>

        			<fo:block-container position="absolute" left="4.5in" top="2.6in" height="15px" width="2.0in">
						<fo:block text-align="end" font-size="9px" font-family="Helvetica" font-weight="bold" display-align="center">Total Direct Costs*</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="6.75in" top="2.6in" height="15px" width="1.4in">
						<fo:block text-align="end" font-size="9px" font-family="Helvetica" font-weight="bold" display-align="center" border-top-color="black" border-top-style="solid" border-top-width="1px">
                            <xsl:choose>
                                <xsl:when
                                    test="not(PHS398_ModularBudget_1_2:DirectCost/PHS398_ModularBudget_1_2:TotalFundsRequestedDirectCosts) or PHS398_ModularBudget_1_2:DirectCost/PHS398_ModularBudget_1_2:TotalFundsRequestedDirectCosts = ''">
                                    <fo:inline color="#FFFFFF">&#160;</fo:inline>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:value-of
                                        select="format-number(PHS398_ModularBudget_1_2:DirectCost/PHS398_ModularBudget_1_2:TotalFundsRequestedDirectCosts, '#,##0.00')" />
                                </xsl:otherwise>
                            </xsl:choose>
                        </fo:block>
                    </fo:block-container>

		<!-- B. Indirect Costs -->
               			<fo:block-container position="absolute" left="0.25in" top="3.2in" height="3.5in" border-color="black" border-style="solid" border-top-width="0.5px" border-bottom-width="0.5px" border-left-width="1px" border-right-width="1px">
                            <fo:block />
        				</fo:block-container>


					<fo:block-container position="absolute" left="0.5in" top="3.4in" height="12px">
						<fo:block font-size="10px" font-family="Helvetica" font-weight="bold">B. Indirect Costs</fo:block>
					</fo:block-container>

                    <fo:block-container position="absolute" left="0.75in" top="3.7in" height="12px" width="2.9in">
						<fo:block text-align="center" font-size="9px" font-family="Helvetica">Indirect Cost Type</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="3.75in" top="3.7in" height="12px" width="1.45in">
						<fo:block text-align="center" font-size="9px" font-family="Helvetica">Indirect Cost&#xD;Rate (%)</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="5.25in" top="3.7in" height="12px" width="1.45in">
						<fo:block text-align="center" font-size="9px" font-family="Helvetica">Indirect Cost&#xD;Base ($)</fo:block>
                    </fo:block-container>

                    <fo:block-container position="absolute" left="6.75in" top="3.7in" height="12px" width="1.4in">
						<fo:block text-align="end" font-size="9px" font-family="Helvetica" font-weight="bold">Funds Requested ($)</fo:block>
					</fo:block-container>


                    <fo:block-container position="absolute" left="0.5in" top="4.0in" height="12px">
							<fo:block font-size="9px" font-family="Helvetica">1.  </fo:block>
						</fo:block-container>
                        <fo:block-container position="absolute" left="0.75in" top="4.0in" height="24px" width="2.9in">
						<fo:block font-size="9px" font-family="Helvetica" border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostTypeDescription) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostTypeDescription = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostTypeDescription" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>

                    <fo:block-container position="absolute" left="3.75in" top="4.0in" height="12px" width="1.45in">
						<fo:block text-align="right" font-size="9px" font-family="Helvetica" border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostRate) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostRate = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostRate, '#,##0.00')" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>


					<fo:block-container position="absolute" left="5.25in" top="4.0in" height="12px" width="1.45in">
						<fo:block text-align="right" font-size="9px" font-family="Helvetica" border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostBase) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostBase = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostBase, '#,##0.00')" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>


                    <fo:block-container position="absolute" left="6.75in" top="4.0in" height="12px" width="1.4in">
						<fo:block text-align="right" font-size="9px" font-family="Helvetica" border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostFundsRequested) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostFundsRequested = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostFundsRequested, '#,##0.00')" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>

					<fo:block-container position="absolute" left="0.5in" top="4.4in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">2.  </fo:block>
					</fo:block-container>
                	<fo:block-container position="absolute" left="0.75in" top="4.4in" height="24px" width="2.9in">
						<fo:block font-size="9px" font-family="Helvetica" border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostTypeDescription) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostTypeDescription = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostTypeDescription" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>

 					<fo:block-container position="absolute" left="3.75in" top="4.4in" height="12px" width="1.45in">
						<fo:block text-align="right" font-size="9px" font-family="Helvetica" border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">

				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostRate) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostRate = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostRate, '#,##0.00')" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>

 					<fo:block-container position="absolute" left="5.25in" top="4.4in" height="12px" width="1.45in">
						<fo:block text-align="right" font-size="9px" font-family="Helvetica" border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostBase) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostBase = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostBase, '#,##0.00')" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>

					<fo:block-container position="absolute" left="6.75in" top="4.4in" height="12px" width="1.4in">
						<fo:block text-align="right" font-size="9px" font-family="Helvetica" border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostFundsRequested) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostFundsRequested = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostFundsRequested, '#,##0.00')" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>

					<fo:block-container position="absolute" left="0.5in" top="4.8in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">3.  </fo:block>
					</fo:block-container>
    				<fo:block-container position="absolute" left="0.75in" top="4.8in" height="24px" width="2.9in">
						<fo:block font-size="9px" font-family="Helvetica" border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostTypeDescription) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostTypeDescription = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostTypeDescription" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>

					<fo:block-container position="absolute" left="3.75in" top="4.8in" height="12px" width="1.45in">
						<fo:block text-align="right" font-size="9px" font-family="Helvetica" border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostRate) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostRate = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostRate, '#,##0.00')" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>

 					<fo:block-container position="absolute" left="5.25in" top="4.8in" height="12px" width="1.45in">
						<fo:block text-align="right" font-size="9px" font-family="Helvetica" border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostBase) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostBase = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostBase, '#,##0.00')" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>

					<fo:block-container position="absolute" left="6.75in" top="4.8in" height="12px" width="1.4in">
						<fo:block text-align="right" font-size="9px" font-family="Helvetica" border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostFundsRequested) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostFundsRequested = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostFundsRequested, '#,##0.00')" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>


					<fo:block-container position="absolute" left="0.5in" top="5.2in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">4.  </fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="0.75in" top="5.2in" height="24px" width="2.9in">
						<fo:block font-size="9px" font-family="Helvetica" border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostTypeDescription) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostTypeDescription = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostTypeDescription" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>

					<fo:block-container position="absolute" left="3.75in" top="5.2in" height="12px" width="1.45in">
						<fo:block text-align="right" font-size="9px" font-family="Helvetica" border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">

				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostRate) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostRate = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostRate, '#,##0.00')" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>

					<fo:block-container position="absolute" left="5.25in" top="5.2in" height="12px" width="1.45in">
						<fo:block text-align="right" font-size="9px" font-family="Helvetica" border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostBase) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostBase = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostBase, '#,##0.00')" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>

					<fo:block-container position="absolute" left="6.75in" top="5.2in" height="12px" width="1.4in">
						<fo:block text-align="right" font-size="9px" font-family="Helvetica" border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostFundsRequested) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostFundsRequested = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostFundsRequested, '#,##0.00')" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>

					<fo:block-container position="absolute" left="0.5in" top="5.6in" height="12px" width="2in">
						<fo:block display-align="before" font-size="9px" font-family="Helvetica">Cognizant Agency </fo:block>
                        <fo:block display-align="before" font-size="6px" font-family="Helvetica">(Agency Name, POC Name and Phone Number)</fo:block>
					</fo:block-container>
  					<fo:block-container position="absolute" left="3.0in" top="5.6in" height="24px" width="3.2in">
						<fo:block text-align="left" font-size="9px" font-family="Helvetica">
				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:CognizantFederalAgency) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:CognizantFederalAgency = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:CognizantFederalAgency" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>

                    <fo:block-container position="absolute" left="0.5in" top="6.0in" height="12px" width="2in">
						<fo:block font-size="9px" font-family="Helvetica">Indirect Cost Rate Agreement Date</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="3in" top="6.0in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">
				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostAgreementDate) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostAgreementDate = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:call-template name="formatDate">
							<xsl:with-param name="value"
								select="PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostAgreementDate" />
						</xsl:call-template>
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>

		<fo:block-container position="absolute" left="4.5in" top="6.0in" height="15px" width="2.0in">
			<fo:block text-align="end" font-size="9px" font-family="Helvetica" font-weight="bold" display-align="center">Total Indirect Costs</fo:block>
		</fo:block-container>

		<fo:block-container position="absolute" left="6.75in" top="6.0in" height="15px" width="1.4in">
						<fo:block text-align="right" font-size="9px" font-family="Helvetica" font-weight="bold" display-align="center" border-top-color="black" border-top-style="solid" border-top-width="1px">
				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:TotalFundsRequestedIndirectCost) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:TotalFundsRequestedIndirectCost = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:TotalFundsRequestedIndirectCost, '#,##0.00')" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>

		<!-- C. Total Direct and Indirect Costs (A + B) -->
        		<fo:block-container position="absolute" left="0.25in" top="6.7in" height="0.5in" border-color="black" border-style="solid" border-top-width="0.5px">
                            <fo:block />
        		</fo:block-container>

		<fo:block-container position="absolute" left="0.5in" top="6.9in" height="12px">
						<fo:block font-size="10px" font-family="Helvetica" font-weight="bold">C. Total Direct and Indirect Costs (A + B)</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="4.5in" top="6.9in" height="12px" width="2.0in">
						<fo:block text-align="right" font-size="9px" font-family="Helvetica" font-weight="bold">Funds Requested ($)</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="6.75in" top="6.9in" height="12px" width="1.4in">
						<fo:block text-align="right" font-size="9px" font-family="Helvetica" font-weight="bold">
				<xsl:choose>
					<xsl:when
						test="not(PHS398_ModularBudget_1_2:TotalFundsRequestedDirectIndirectCosts) or PHS398_ModularBudget_1_2:TotalFundsRequestedDirectIndirectCosts = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="format-number(PHS398_ModularBudget_1_2:TotalFundsRequestedDirectIndirectCosts, '#,##0.00')" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>
	</xsl:template>

	<!-- Cumulative Budget Info section -->


	<xsl:template name="cumulativeBudgetInfo">
				<fo:block-container position="absolute" left="0.25in" top="0.5in" height="15px">
						<fo:block font-size="12px" font-family="Helvetica" font-weight="bold" text-align="center">PHS 398 Modular Budget</fo:block>
					</fo:block-container>

		<fo:block-container position="absolute" left="0.25in" top="1.25in" height="0.25in" border-style="solid" border-color="black" border-top-width="1px"  border-bottom-width="0.5px" border-left-width="1px" border-right-width="1px">
			<fo:block font-size="10px" font-family="Helvetica" font-weight="bold" text-align="center" padding-before="2px">
            	Cumulative Budget Information
			</fo:block>
		</fo:block-container>

		<!--  Total Costs, Entire Project Periods -->
                		<fo:block-container position="absolute" left="0.25in" top="1.5in" height="1.7in" border-color="black" border-style="solid" border-top-width="0.5px" border-bottom-width="0.5px" border-left-width="1px" border-right-width="1px">
                            <fo:block />
        		</fo:block-container>
					<fo:block-container position="absolute" left="0.5in" top="1.8in" height="15px">
						<fo:block font-size="10px" font-family="Helvetica" font-weight="bold">1. Total Costs, Entire Project Period</fo:block>
					</fo:block-container>

       				<fo:block-container position="absolute" left="0.5in" top="2.1in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Section A, Total Direct Cost less Consortium F&amp;A for Entire Project Period ($)</fo:block>
					</fo:block-container>
                    <fo:block-container position="absolute" left="5.0in" top="2.1in" height="12px" width="1.45in">
					 <fo:block text-align="right" font-size="9px" font-family="Helvetica">
				<xsl:choose>
					<xsl:when
						test="not(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeDirectCostLessConsortiumFandA) or //PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeDirectCostLessConsortiumFandA = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="format-number(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeDirectCostLessConsortiumFandA, '#,##0.00')" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>

                    <fo:block-container position="absolute" left="0.5in" top="2.3in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Section A, Total Consortium F&amp;A for Entire Project Period ($)</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="5.0in" top="2.3in" height="12px" width="1.45in">
						<fo:block text-align="right" font-size="9px" font-family="Helvetica">
				<xsl:choose>
					<xsl:when
						test="not(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeConsortiumFandA) or //PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeConsortiumFandA = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="format-number(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeConsortiumFandA, '#,##0.00')" />
					</xsl:otherwise>
				</xsl:choose>
               </fo:block>
		</fo:block-container>

                    <fo:block-container position="absolute" left="0.5in" top="2.5in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Section A, Total Direct Costs for Entire Project Period ($)</fo:block>
					</fo:block-container>
                    <fo:block-container position="absolute" left="5.0in" top="2.5in" height="12px" width="1.45in">
						<fo:block text-align="right" font-size="9px" font-family="Helvetica">
				<xsl:choose>
					<xsl:when
						test="not(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeTotalFundsRequestedDirectCosts) or //PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeTotalFundsRequestedDirectCosts = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="format-number(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeTotalFundsRequestedDirectCosts, '#,##0.00')" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>

                    <fo:block-container position="absolute" left="0.5in" top="2.7in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Section B, Total Indirect Costs for Entire Project Period ($)</fo:block>
					</fo:block-container>
                    <fo:block-container position="absolute" left="5.0in" top="2.7in" height="12px" width="1.45in">
						<fo:block text-align="right" font-size="9px" font-family="Helvetica">
				<xsl:choose>
					<xsl:when
						test="not(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeTotalFundsRequestedIndirectCost) or //PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeTotalFundsRequestedIndirectCost = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="format-number(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeTotalFundsRequestedIndirectCost, '#,##0.00')" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>


                    <fo:block-container position="absolute" left="0.5in" top="2.9in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Section C, Total Direct and Indirect Costs (A+B) for Entire Project Period ($)</fo:block>
					</fo:block-container>
       				<fo:block-container position="absolute" left="5.0in" top="2.9in" height="12px" width="1.45in">
						<fo:block text-align="right" font-size="9px" font-family="Helvetica">
				<xsl:choose>
					<xsl:when
						test="not(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeTotalFundsRequestedDirectIndirectCosts) or //PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeTotalFundsRequestedDirectIndirectCosts = ''">
						<fo:inline color="#FFFFFF">&#160;</fo:inline>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of
							select="format-number(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeTotalFundsRequestedDirectIndirectCosts, '#,##0.00')" />
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
		</fo:block-container>

		<!-- 2. Budget Justifications -->

                		<fo:block-container position="absolute" left="0.25in" top="3.2in" height="1.5in" border-color="black" border-style="solid" border-top-width="0.5px" border-bottom-width="1px" border-left-width="1px" border-right-width="1px">
                            <fo:block />
        		</fo:block-container>
  					<fo:block-container position="absolute" left="0.5in" top="3.4in" height="15px">
						<fo:block font-size="10px" font-family="Helvetica" font-weight="bold">2. Budget Justifications</fo:block>
					</fo:block-container>


					<fo:block-container position="absolute" left="0.5in" top="3.7in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Personnel Justification</fo:block>
                 	</fo:block-container>
                     <fo:block-container position="absolute" left="2.5in" top="3.7in" height="12px">
						<fo:block font-size="10px" font-family="Georgia">
				<xsl:if
					test="not(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:PersonnelJustification/PHS398_ModularBudget_1_2:attFile/att:FileName) or /PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:PersonnelJustification/PHS398_ModularBudget_1_2:attFile/att:FileName = ''">
					<fo:inline color="#FFFFFF">&#160;</fo:inline>
				</xsl:if>
				<xsl:value-of
					select="//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:PersonnelJustification/PHS398_ModularBudget_1_2:attFile/att:FileName" />
			</fo:block>
		</fo:block-container>


					<fo:block-container position="absolute" left="0.5in" top="3.9in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Consortium Justification</fo:block>
					</fo:block-container>
                    <fo:block-container position="absolute" left="2.5in" top="3.9in" height="12px">
						<fo:block font-size="10px" font-family="Georgia">
				<xsl:if
					test="not(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:ConsortiumJustification/PHS398_ModularBudget_1_2:attFile/att:FileName) or /PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:ConsortiumJustification/PHS398_ModularBudget_1_2:attFile/att:FileName = ''">
					<fo:inline color="#FFFFFF">&#160;</fo:inline>
				</xsl:if>
				<xsl:value-of
					select="//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:ConsortiumJustification/PHS398_ModularBudget_1_2:attFile/att:FileName" />
			</fo:block>
		</fo:block-container>

					<fo:block-container position="absolute" left="0.5in" top="4.1in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Additional Narrative Justification</fo:block>
					</fo:block-container>
                    <fo:block-container position="absolute" left="2.5in" top="4.1in" height="12px">
						<fo:block font-size="10px" font-family="Georgia">
				<xsl:if
					test="not(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:AdditionalNarrativeJustification/PHS398_ModularBudget_1_2:attFile/att:FileName) or /PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:AdditionalNarrativeJustification/PHS398_ModularBudget_1_2:attFile/att:FileName = ''">
					<fo:inline color="#FFFFFF">&#160;</fo:inline>
				</xsl:if>
				<xsl:value-of
					select="//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:AdditionalNarrativeJustification/PHS398_ModularBudget_1_2:attFile/att:FileName" />
			</fo:block>
		</fo:block-container>
	</xsl:template>

	<xsl:template name="radioButton">
		<xsl:param name="value" />
		<xsl:param name="schemaChoice">
			Yes
		</xsl:param>
		<xsl:choose>
			<xsl:when test="$value = $schemaChoice">
				<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format"
					xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
					xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
					xmlns:att="http://apply.grants.gov/system/Attachments-V1.0"
					xmlns:glob="http://apply.grants.gov/system/Global-V1.0"
					font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
			</xsl:when>
			<xsl:otherwise>
				<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format"
					xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
					xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
					xmlns:att="http://apply.grants.gov/system/Attachments-V1.0"
					xmlns:glob="http://apply.grants.gov/system/Global-V1.0"
					font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template name="checkbox">
		<xsl:param name="value" />
		<xsl:param name="schemaChoice">
			Yes
		</xsl:param>
		<xsl:if test="$value = $schemaChoice">
			<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format"
				xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
				xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
				xmlns:att="http://apply.grants.gov/system/Attachments-V1.0"
				xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats"
				font-size="11pt">&#x2714;</fo:inline>
		</xsl:if>
	</xsl:template>

	<xsl:template name="formatDate">
		<xsl:param name="value" />
		<xsl:if test="$value != ''">
			<xsl:value-of select="format-number(substring($value,6,2), '00')" />
			<xsl:text>/</xsl:text>
			<xsl:value-of select="format-number(substring($value,9,2), '00')" />
			<xsl:text>/</xsl:text>
			<xsl:value-of select="format-number(substring($value,1,4), '0000')" />
		</xsl:if>
	</xsl:template>

	<xsl:template name="addBlankLines">
		<xsl:param name="numLines" />
		<xsl:if test="string($numLines) != ''">
			<xsl:if test="$numLines &gt; 0">
				<fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format"
					xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
					xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
					xmlns:att="http://apply.grants.gov/system/Attachments-V1.0"
					xmlns:glob="http://apply.grants.gov/system/Global-V1.0">
					<fo:leader leader-pattern="space" />
				</fo:block>
				<xsl:call-template name="addBlankLines">
					<xsl:with-param name="numLines" select="$numLines - 1" />
				</xsl:call-template>
			</xsl:if>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>
