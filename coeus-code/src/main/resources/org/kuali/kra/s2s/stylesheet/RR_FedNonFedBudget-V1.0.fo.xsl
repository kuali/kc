<?xml version="1.0" encoding="UTF-8"?>
<!-- $Revision:   1.12  $ -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:RR_FedNonFedBudget="http://apply.grants.gov/forms/RR_FedNonFedBudget-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V1.0" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:header="http://devapply.row.com/system/Header-V1.0">
	<xsl:template match="RR_FedNonFedBudget:RR_FedNonFedBudget[name(..)!='RR_FedNonFed_SubawardBudget:BudgetAttachments']">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master master-name="AB" page-height="8.5in" page-width="11in" margin-left="0.3in" margin-right="0.3in">
					<fo:region-body margin-top="0.3in" margin-bottom=".4in"/>
					<fo:region-after extent=".3in"/>
				</fo:simple-page-master>
				<fo:simple-page-master master-name="main" page-height="8.5in" page-width="11in" margin-left="0.4in" margin-right="0.4in">
					<fo:region-body margin-top="0.5in" margin-bottom="0.5in"/>
					<fo:region-after extent=".5in"/>
				</fo:simple-page-master>
				<fo:page-sequence-master master-name="primary">
					<fo:single-page-master-reference master-reference="main"/>
				</fo:page-sequence-master>
				<fo:page-sequence-master master-name="summary">
					<fo:single-page-master-reference master-reference="main"/>
				</fo:page-sequence-master>
			</fo:layout-master-set>
			<!--==========================================================================-->
			<fo:page-sequence master-reference="AB" format="1">
				<fo:static-content flow-name="xsl-region-after">
					<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
									</fo:block>
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Expiration Date: 04/30/2008</fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<xsl:for-each select="RR_FedNonFedBudget:BudgetYear1">
						<xsl:call-template name="SingleYearPart1">
							<xsl:with-param name="year">1</xsl:with-param>
						</xsl:call-template>
					</xsl:for-each>
				</fo:flow>
			</fo:page-sequence>
			<fo:page-sequence master-reference="primary" format="1">
				<fo:static-content flow-name="xsl-region-after">
					<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
									</fo:block>
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Expiration Date: 04/30/2008</fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<xsl:for-each select="RR_FedNonFedBudget:BudgetYear1">
						<xsl:call-template name="SingleYearPart2">
							<xsl:with-param name="year">1</xsl:with-param>
						</xsl:call-template>
					</xsl:for-each>
				</fo:flow>
			</fo:page-sequence>
			<xsl:if test="RR_FedNonFedBudget:BudgetYear2!=''">
				<fo:page-sequence master-reference="AB" format="1">
					<fo:static-content flow-name="xsl-region-after">
						<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
											</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
										</fo:block>
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Expiration Date: 04/30/2008</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:static-content>
					<fo:flow flow-name="xsl-region-body">
						<xsl:for-each select="RR_FedNonFedBudget:BudgetYear2">
							<xsl:call-template name="SingleYearPart1">
								<xsl:with-param name="year">2</xsl:with-param>
							</xsl:call-template>
						</xsl:for-each>
					</fo:flow>
				</fo:page-sequence>
				<fo:page-sequence master-reference="primary" format="1">
					<fo:static-content flow-name="xsl-region-after">
						<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
											</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
										</fo:block>
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Expiration Date: 04/30/2008</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:static-content>
					<fo:flow flow-name="xsl-region-body">
						<xsl:for-each select="RR_FedNonFedBudget:BudgetYear2">
							<xsl:call-template name="SingleYearPart2">
								<xsl:with-param name="year">2</xsl:with-param>
							</xsl:call-template>
						</xsl:for-each>
					</fo:flow>
				</fo:page-sequence>
			</xsl:if>
			<xsl:if test="RR_FedNonFedBudget:BudgetYear3!=''">
				<fo:page-sequence master-reference="AB" format="1">
					<fo:static-content flow-name="xsl-region-after">
						<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
											</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
										</fo:block>
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Expiration Date: 04/30/2008</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:static-content>
					<fo:flow flow-name="xsl-region-body">
						<xsl:for-each select="RR_FedNonFedBudget:BudgetYear3">
							<xsl:call-template name="SingleYearPart1">
								<xsl:with-param name="year">3</xsl:with-param>
							</xsl:call-template>
						</xsl:for-each>
					</fo:flow>
				</fo:page-sequence>
				<fo:page-sequence master-reference="primary" format="1">
					<fo:static-content flow-name="xsl-region-after">
						<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
											</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
										</fo:block>
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Expiration Date: 04/30/2008</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:static-content>
					<fo:flow flow-name="xsl-region-body">
						<xsl:for-each select="RR_FedNonFedBudget:BudgetYear3">
							<xsl:call-template name="SingleYearPart2">
								<xsl:with-param name="year">3</xsl:with-param>
							</xsl:call-template>
						</xsl:for-each>
					</fo:flow>
				</fo:page-sequence>
			</xsl:if>
			<xsl:if test="RR_FedNonFedBudget:BudgetYear4!=''">
				<fo:page-sequence master-reference="AB" format="1">
					<fo:static-content flow-name="xsl-region-after">
						<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
											</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
										</fo:block>
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Expiration Date: 04/30/2008</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:static-content>
					<fo:flow flow-name="xsl-region-body">
						<xsl:for-each select="RR_FedNonFedBudget:BudgetYear4">
							<xsl:call-template name="SingleYearPart1">
								<xsl:with-param name="year">4</xsl:with-param>
							</xsl:call-template>
						</xsl:for-each>
					</fo:flow>
				</fo:page-sequence>
				<fo:page-sequence master-reference="primary" format="1">
					<fo:static-content flow-name="xsl-region-after">
						<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
											</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
										</fo:block>
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Expiration Date: 04/30/2008</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:static-content>
					<fo:flow flow-name="xsl-region-body">
						<xsl:for-each select="RR_FedNonFedBudget:BudgetYear4">
							<xsl:call-template name="SingleYearPart2">
								<xsl:with-param name="year">4</xsl:with-param>
							</xsl:call-template>
						</xsl:for-each>
					</fo:flow>
				</fo:page-sequence>
			</xsl:if>
			<xsl:if test="RR_FedNonFedBudget:BudgetYear5!=''">
				<fo:page-sequence master-reference="AB" format="1">
					<fo:static-content flow-name="xsl-region-after">
						<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
											</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
										</fo:block>
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Expiration Date: 04/30/2008</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:static-content>
					<fo:flow flow-name="xsl-region-body">
						<xsl:for-each select="RR_FedNonFedBudget:BudgetYear5">
							<xsl:call-template name="SingleYearPart1">
								<xsl:with-param name="year">5</xsl:with-param>
							</xsl:call-template>
						</xsl:for-each>
					</fo:flow>
				</fo:page-sequence>
				<fo:page-sequence master-reference="primary" format="1">
					<fo:static-content flow-name="xsl-region-after">
						<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-column column-width="proportional-column-width(1)"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
											</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
										</fo:block>
										<fo:block>
											<fo:inline font-size="6px" font-weight="bold">Expiration Date: 04/30/2008</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:static-content>
					<fo:flow flow-name="xsl-region-body">
						<xsl:for-each select="RR_FedNonFedBudget:BudgetYear5">
							<xsl:call-template name="SingleYearPart2">
								<xsl:with-param name="year">5</xsl:with-param>
							</xsl:call-template>
						</xsl:for-each>
					</fo:flow>
				</fo:page-sequence>
			</xsl:if>
			<!-- ====================================== new section SUMMARY ===============================-->
			<fo:page-sequence master-reference="summary" format="1">
				<fo:static-content flow-name="xsl-region-after">
					<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline>
									</fo:block>
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Expiration Date: 04/30/2008</fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<!--<xsl:for-each select="XXX">-->
					<fo:table width="100%" space-before.optimum="3pt" space-after.optimum="2pt">
						<fo:table-column/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell text-align="center" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="before">
									<fo:block>
										<fo:inline font-weight="bold" font-size="10pt">RESEARCH &amp; RELATED BUDGET (TOTAL FED + NON-FED) - Cumulative Budget</fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
					<fo:block>
						<fo:leader leader-pattern="space"/>
					</fo:block>
					<fo:block>
						<fo:leader leader-pattern="space"/>
					</fo:block>
					<fo:block font-size="8pt">
						<fo:table width="500pt" space-before.optimum="3pt" space-after.optimum="2pt">
							<fo:table-column column-width="proportional-column-width(70)"/>
							<fo:table-column column-width="proportional-column-width(30)"/>
							<fo:table-column column-width="proportional-column-width(30)"/>
							<fo:table-column column-width="proportional-column-width(30)"/>
							<fo:table-body>
								<!--============= ROWS Begin ======================-->
								<xsl:for-each select="RR_FedNonFedBudget:BudgetSummary">
									<fo:table-row>
										<fo:table-cell>
											<fo:block/>
										</fo:table-cell>
										<fo:table-cell text-align="center">
											<fo:block>
												<fo:inline font-size="8pt" font-weight="bold">Total Federal (&#36;)</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="center">
											<fo:block>
												<fo:inline font-size="8pt" font-weight="bold">Total Non-Federal (&#36;)</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="center">
											<fo:block>
												<fo:inline font-size="8pt" font-weight="bold">*Totals (&#36;)</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell number-columns-spanned="4">
											<fo:block>&#160;</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block font-weight="bold">Section A, Senior/Key Person</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTotalFundsRequestedSeniorKeyPerson/RR_FedNonFedBudget:FederalSummary = '' or not(RR_FedNonFedBudget:CumulativeTotalFundsRequestedSeniorKeyPerson/RR_FedNonFedBudget:FederalSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTotalFundsRequestedSeniorKeyPerson/RR_FedNonFedBudget:FederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="not(RR_FedNonFedBudget:CumulativeTotalFundsRequestedSeniorKeyPerson/RR_FedNonFedBudget:NonFederalSummary) or RR_FedNonFedBudget:CumulativeTotalFundsRequestedSeniorKeyPerson/RR_FedNonFedBudget:NonFederalSummary=''">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTotalFundsRequestedSeniorKeyPerson/RR_FedNonFedBudget:NonFederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTotalFundsRequestedSeniorKeyPerson/RR_FedNonFedBudget:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget:CumulativeTotalFundsRequestedSeniorKeyPerson/RR_FedNonFedBudget:TotalFedNonFedSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTotalFundsRequestedSeniorKeyPerson/RR_FedNonFedBudget:TotalFedNonFedSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block font-weight="bold">Section B, Other Personnel</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTotalFundsRequestedOtherPersonnel/RR_FedNonFedBudget:FederalSummary = '' or not(RR_FedNonFedBudget:CumulativeTotalFundsRequestedOtherPersonnel/RR_FedNonFedBudget:FederalSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number
(RR_FedNonFedBudget:CumulativeTotalFundsRequestedOtherPersonnel/RR_FedNonFedBudget:FederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTotalFundsRequestedOtherPersonnel/RR_FedNonFedBudget:NonFederalSummary = '' or not(RR_FedNonFedBudget:CumulativeTotalFundsRequestedOtherPersonnel/RR_FedNonFedBudget:NonFederalSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number
(RR_FedNonFedBudget:CumulativeTotalFundsRequestedOtherPersonnel/RR_FedNonFedBudget:NonFederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTotalFundsRequestedOtherPersonnel/RR_FedNonFedBudget:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget:CumulativeTotalFundsRequestedOtherPersonnel/RR_FedNonFedBudget:TotalFedNonFedSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTotalFundsRequestedOtherPersonnel/RR_FedNonFedBudget:TotalFedNonFedSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block>Total Number Other Personnel</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block/>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block/>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTotalNoOtherPersonnel = '' or not(RR_FedNonFedBudget:CumulativeTotalNoOtherPersonnel)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTotalNoOtherPersonnel, '#,##0')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block font-weight="bold">Total  Salary, Wages and Fringe Benefits (A + B)</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTotalFundsRequestedPersonnel/RR_FedNonFedBudget:FederalSummary = '' or not(RR_FedNonFedBudget:CumulativeTotalFundsRequestedPersonnel/RR_FedNonFedBudget:FederalSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTotalFundsRequestedPersonnel/RR_FedNonFedBudget:FederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTotalFundsRequestedPersonnel/RR_FedNonFedBudget:NonFederalSummary = '' or not(RR_FedNonFedBudget:CumulativeTotalFundsRequestedPersonnel/RR_FedNonFedBudget:NonFederalSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTotalFundsRequestedPersonnel/RR_FedNonFedBudget:NonFederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTotalFundsRequestedPersonnel/RR_FedNonFedBudget:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget:CumulativeTotalFundsRequestedPersonnel/RR_FedNonFedBudget:TotalFedNonFedSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTotalFundsRequestedPersonnel/RR_FedNonFedBudget:TotalFedNonFedSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block font-weight="bold">Section C, Equipment</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeEquipments/RR_FedNonFedBudget:CumulativeTotalFundsRequestedEquipment/RR_FedNonFedBudget:FederalSummary = '' or not(RR_FedNonFedBudget:CumulativeEquipments/RR_FedNonFedBudget:CumulativeTotalFundsRequestedEquipment/RR_FedNonFedBudget:FederalSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeEquipments/RR_FedNonFedBudget:CumulativeTotalFundsRequestedEquipment/RR_FedNonFedBudget:FederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeEquipments/RR_FedNonFedBudget:CumulativeTotalFundsRequestedEquipment/RR_FedNonFedBudget:NonFederalSummary = '' or not(RR_FedNonFedBudget:CumulativeEquipments/RR_FedNonFedBudget:CumulativeTotalFundsRequestedEquipment/RR_FedNonFedBudget:NonFederalSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeEquipments/RR_FedNonFedBudget:CumulativeTotalFundsRequestedEquipment/RR_FedNonFedBudget:NonFederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeEquipments/RR_FedNonFedBudget:CumulativeTotalFundsRequestedEquipment/RR_FedNonFedBudget:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget:CumulativeEquipments/RR_FedNonFedBudget:CumulativeTotalFundsRequestedEquipment/RR_FedNonFedBudget:TotalFedNonFedSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeEquipments/RR_FedNonFedBudget:CumulativeTotalFundsRequestedEquipment/RR_FedNonFedBudget:TotalFedNonFedSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block font-weight="bold">Section D, Travel</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeTotalFundsRequestedTravel/RR_FedNonFedBudget:FederalSummary = '' or not(RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeTotalFundsRequestedTravel/RR_FedNonFedBudget:FederalSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeTotalFundsRequestedTravel/RR_FedNonFedBudget:FederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeTotalFundsRequestedTravel/RR_FedNonFedBudget:NonFederalSummary = '' or not(RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeTotalFundsRequestedTravel/RR_FedNonFedBudget:NonFederalSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeTotalFundsRequestedTravel/RR_FedNonFedBudget:NonFederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeTotalFundsRequestedTravel/RR_FedNonFedBudget:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeTotalFundsRequestedTravel/RR_FedNonFedBudget:TotalFedNonFedSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeTotalFundsRequestedTravel/RR_FedNonFedBudget:TotalFedNonFedSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block>
												<fo:inline font-weight="bold">1. </fo:inline>Domestic</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeDomesticTravelCosts/RR_FedNonFedBudget:Federal = '' or not(RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeDomesticTravelCosts/RR_FedNonFedBudget:Federal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeDomesticTravelCosts/RR_FedNonFedBudget:Federal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeDomesticTravelCosts/RR_FedNonFedBudget:NonFederal = '' or not(RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeDomesticTravelCosts/RR_FedNonFedBudget:NonFederal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeDomesticTravelCosts/RR_FedNonFedBudget:NonFederal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeDomesticTravelCosts/RR_FedNonFedBudget:TotalFedNonFed = '' or not(RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeDomesticTravelCosts/RR_FedNonFedBudget:TotalFedNonFed)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeDomesticTravelCosts/RR_FedNonFedBudget:TotalFedNonFed, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block>
												<fo:inline font-weight="bold">2. </fo:inline>Foreign</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeForeignTravelCosts/RR_FedNonFedBudget:Federal = '' or not(RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeForeignTravelCosts/RR_FedNonFedBudget:Federal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeForeignTravelCosts/RR_FedNonFedBudget:Federal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeForeignTravelCosts/RR_FedNonFedBudget:NonFederal = '' or not(RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeForeignTravelCosts/RR_FedNonFedBudget:NonFederal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeForeignTravelCosts/RR_FedNonFedBudget:NonFederal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeForeignTravelCosts/RR_FedNonFedBudget:TotalFedNonFed = '' or not(RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeForeignTravelCosts/RR_FedNonFedBudget:TotalFedNonFed)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTravels/RR_FedNonFedBudget:CumulativeForeignTravelCosts/RR_FedNonFedBudget:TotalFedNonFed, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block font-weight="bold">Section E, Participant/Trainee Support Costs</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTotalFundsRequestedTraineeCosts/RR_FedNonFedBudget:FederalSummary = '' or not(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTotalFundsRequestedTraineeCosts/RR_FedNonFedBudget:FederalSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTotalFundsRequestedTraineeCosts/RR_FedNonFedBudget:FederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTotalFundsRequestedTraineeCosts/RR_FedNonFedBudget:NonFederalSummary = '' or not(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTotalFundsRequestedTraineeCosts/RR_FedNonFedBudget:NonFederalSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTotalFundsRequestedTraineeCosts/RR_FedNonFedBudget:NonFederalSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTotalFundsRequestedTraineeCosts/RR_FedNonFedBudget:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTotalFundsRequestedTraineeCosts/RR_FedNonFedBudget:TotalFedNonFedSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTotalFundsRequestedTraineeCosts/RR_FedNonFedBudget:TotalFedNonFedSummary, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block>
												<fo:inline font-weight="bold">1. </fo:inline>Tuition/Fees/Health Insurance</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeTuitionFeesHealthInsurance/RR_FedNonFedBudget:Federal = '' or not(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeTuitionFeesHealthInsurance/RR_FedNonFedBudget:Federal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeTuitionFeesHealthInsurance/RR_FedNonFedBudget:Federal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeTuitionFeesHealthInsurance/RR_FedNonFedBudget:NonFederal = '' or not(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeTuitionFeesHealthInsurance/RR_FedNonFedBudget:NonFederal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeTuitionFeesHealthInsurance/RR_FedNonFedBudget:NonFederal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeTuitionFeesHealthInsurance/RR_FedNonFedBudget:TotalFedNonFed = '' or not(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeTuitionFeesHealthInsurance/RR_FedNonFedBudget:TotalFedNonFed)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeTuitionFeesHealthInsurance/RR_FedNonFedBudget:TotalFedNonFed, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block>
												<fo:inline font-weight="bold">2. </fo:inline>Stipends</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeStipends/RR_FedNonFedBudget:Federal = '' or not(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeStipends/RR_FedNonFedBudget:Federal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeStipends/RR_FedNonFedBudget:Federal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeStipends/RR_FedNonFedBudget:NonFederal = '' or not(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeStipends/RR_FedNonFedBudget:NonFederal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeStipends/RR_FedNonFedBudget:NonFederal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeStipends/RR_FedNonFedBudget:TotalFedNonFed = '' or not(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeStipends/RR_FedNonFedBudget:TotalFedNonFed)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeStipends/RR_FedNonFedBudget:TotalFedNonFed, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block>
												<fo:inline font-weight="bold">3. </fo:inline>Travel</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeTravel/RR_FedNonFedBudget:Federal = '' or not(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeTravel/RR_FedNonFedBudget:Federal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeTravel/RR_FedNonFedBudget:Federal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeTravel/RR_FedNonFedBudget:NonFederal = '' or not(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeTravel/RR_FedNonFedBudget:NonFederal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeTravel/RR_FedNonFedBudget:NonFederal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeTravel/RR_FedNonFedBudget:TotalFedNonFed = '' or not(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeTravel/RR_FedNonFedBudget:TotalFedNonFed)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeTravel/RR_FedNonFedBudget:TotalFedNonFed, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block>
												<fo:inline font-weight="bold">4. </fo:inline>Subsistence</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeSubsistence/RR_FedNonFedBudget:Federal = '' or not(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeSubsistence/RR_FedNonFedBudget:Federal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeSubsistence/RR_FedNonFedBudget:Federal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeSubsistence/RR_FedNonFedBudget:NonFederal = '' or not(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeSubsistence/RR_FedNonFedBudget:NonFederal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeSubsistence/RR_FedNonFedBudget:NonFederal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeSubsistence/RR_FedNonFedBudget:TotalFedNonFed = '' or not(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeSubsistence/RR_FedNonFedBudget:TotalFedNonFed)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeTraineeSubsistence/RR_FedNonFedBudget:TotalFedNonFed, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block>
												<fo:inline font-weight="bold">5. </fo:inline>Other&#160;</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeOtherTraineeCost/RR_FedNonFedBudget:Federal = '' or not(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeOtherTraineeCost/RR_FedNonFedBudget:Federal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeOtherTraineeCost/RR_FedNonFedBudget:Federal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeOtherTraineeCost/RR_FedNonFedBudget:NonFederal = '' or not(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeOtherTraineeCost/RR_FedNonFedBudget:NonFederal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeOtherTraineeCost/RR_FedNonFedBudget:NonFederal, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeOtherTraineeCost/RR_FedNonFedBudget:TotalFedNonFed = '' or not(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeOtherTraineeCost/RR_FedNonFedBudget:TotalFedNonFed)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeOtherTraineeCost/RR_FedNonFedBudget:TotalFedNonFed, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block>
												<fo:inline font-weight="bold">6. </fo:inline>Number of Participants/Trainees</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block/>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block/>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeNoofTrainees = '' or not(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeNoofTrainees)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTrainee/RR_FedNonFedBudget:CumulativeNoofTrainees, '###')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block font-weight="bold">Section F, Other Direct Costs</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeTotalFundsRequestedOtherDirectCosts/RR_FedNonFedBudget:FederalSummary = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeTotalFundsRequestedOtherDirectCosts/RR_FedNonFedBudget:FederalSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeTotalFundsRequestedOtherDirectCosts/RR_FedNonFedBudget:FederalSummary
,'#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeTotalFundsRequestedOtherDirectCosts/RR_FedNonFedBudget:NonFederalSummary = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeTotalFundsRequestedOtherDirectCosts/RR_FedNonFedBudget:NonFederalSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeTotalFundsRequestedOtherDirectCosts/RR_FedNonFedBudget:NonFederalSummary
,'#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeTotalFundsRequestedOtherDirectCosts/RR_FedNonFedBudget:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeTotalFundsRequestedOtherDirectCosts/RR_FedNonFedBudget:TotalFedNonFedSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeTotalFundsRequestedOtherDirectCosts/RR_FedNonFedBudget:TotalFedNonFedSummary
,'#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block>
												<fo:inline font-weight="bold">1. </fo:inline>Materials and Supplies</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeMaterialAndSupplies/RR_FedNonFedBudget:Federal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeMaterialAndSupplies/RR_FedNonFedBudget:Federal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeMaterialAndSupplies/RR_FedNonFedBudget:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeMaterialAndSupplies/RR_FedNonFedBudget:NonFederal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeMaterialAndSupplies/RR_FedNonFedBudget:NonFederal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeMaterialAndSupplies/RR_FedNonFedBudget:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeMaterialAndSupplies/RR_FedNonFedBudget:TotalFedNonFed = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeMaterialAndSupplies/RR_FedNonFedBudget:TotalFedNonFed)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeMaterialAndSupplies/RR_FedNonFedBudget:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block>
												<fo:inline font-weight="bold">2. </fo:inline>Publication Costs</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativePublicationCosts/RR_FedNonFedBudget:Federal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativePublicationCosts/RR_FedNonFedBudget:Federal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativePublicationCosts/RR_FedNonFedBudget:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativePublicationCosts/RR_FedNonFedBudget:NonFederal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativePublicationCosts/RR_FedNonFedBudget:NonFederal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativePublicationCosts/RR_FedNonFedBudget:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativePublicationCosts/RR_FedNonFedBudget:TotalFedNonFed = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativePublicationCosts/RR_FedNonFedBudget:TotalFedNonFed)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativePublicationCosts/RR_FedNonFedBudget:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block>
												<fo:inline font-weight="bold">3. </fo:inline>Consultant Services</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeConsultantServices/RR_FedNonFedBudget:Federal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeConsultantServices/RR_FedNonFedBudget:Federal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeConsultantServices/RR_FedNonFedBudget:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeConsultantServices/RR_FedNonFedBudget:NonFederal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeConsultantServices/RR_FedNonFedBudget:NonFederal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeConsultantServices/RR_FedNonFedBudget:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeConsultantServices/RR_FedNonFedBudget:TotalFedNonFed = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeConsultantServices/RR_FedNonFedBudget:TotalFedNonFed)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeConsultantServices/RR_FedNonFedBudget:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block>
												<fo:inline font-weight="bold">4. </fo:inline>ADP/Computer Services</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeADPComputerServices/RR_FedNonFedBudget:Federal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeADPComputerServices/RR_FedNonFedBudget:Federal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeADPComputerServices/RR_FedNonFedBudget:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeADPComputerServices/RR_FedNonFedBudget:NonFederal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeADPComputerServices/RR_FedNonFedBudget:NonFederal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeADPComputerServices/RR_FedNonFedBudget:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeADPComputerServices/RR_FedNonFedBudget:TotalFedNonFed = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeADPComputerServices/RR_FedNonFedBudget:TotalFedNonFed)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeADPComputerServices/RR_FedNonFedBudget:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block>
												<fo:inline font-weight="bold">5. </fo:inline>Subawards/Consortium/Contractual Costs</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeSubawardConsortiumContractualCosts/RR_FedNonFedBudget:Federal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeSubawardConsortiumContractualCosts/RR_FedNonFedBudget:Federal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeSubawardConsortiumContractualCosts/RR_FedNonFedBudget:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeSubawardConsortiumContractualCosts/RR_FedNonFedBudget:NonFederal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeSubawardConsortiumContractualCosts/RR_FedNonFedBudget:NonFederal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeSubawardConsortiumContractualCosts/RR_FedNonFedBudget:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeSubawardConsortiumContractualCosts/RR_FedNonFedBudget:TotalFedNonFed = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeSubawardConsortiumContractualCosts/RR_FedNonFedBudget:TotalFedNonFed)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeSubawardConsortiumContractualCosts/RR_FedNonFedBudget:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block>
												<!--EDITED BY MATT!-->
												<fo:inline font-weight="bold">6. </fo:inline>Equipment or Facility Rental/User Fees</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeEquipmentFacilityRentalFees/RR_FedNonFedBudget:Federal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeEquipmentFacilityRentalFees/RR_FedNonFedBudget:Federal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeEquipmentFacilityRentalFees/RR_FedNonFedBudget:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeEquipmentFacilityRentalFees/RR_FedNonFedBudget:NonFederal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeEquipmentFacilityRentalFees/RR_FedNonFedBudget:NonFederal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeEquipmentFacilityRentalFees/RR_FedNonFedBudget:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeEquipmentFacilityRentalFees/RR_FedNonFedBudget:TotalFedNonFed = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeEquipmentFacilityRentalFees/RR_FedNonFedBudget:TotalFedNonFed)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeEquipmentFacilityRentalFees/RR_FedNonFedBudget:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block>
												<fo:inline font-weight="bold">7. </fo:inline>Alterations and Renovations</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeAlterationsAndRenovations/RR_FedNonFedBudget:Federal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeAlterationsAndRenovations/RR_FedNonFedBudget:Federal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeAlterationsAndRenovations/RR_FedNonFedBudget:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeAlterationsAndRenovations/RR_FedNonFedBudget:NonFederal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeAlterationsAndRenovations/RR_FedNonFedBudget:NonFederal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeAlterationsAndRenovations/RR_FedNonFedBudget:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeAlterationsAndRenovations/RR_FedNonFedBudget:TotalFedNonFed = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeAlterationsAndRenovations/RR_FedNonFedBudget:TotalFedNonFed)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeAlterationsAndRenovations/RR_FedNonFedBudget:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block>
												<fo:inline font-weight="bold">8. </fo:inline>Other 1</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther1DirectCost/RR_FedNonFedBudget:Federal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther1DirectCost/RR_FedNonFedBudget:Federal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther1DirectCost/RR_FedNonFedBudget:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther1DirectCost/RR_FedNonFedBudget:NonFederal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther1DirectCost/RR_FedNonFedBudget:NonFederal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther1DirectCost/RR_FedNonFedBudget:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther1DirectCost/RR_FedNonFedBudget:TotalFedNonFed = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther1DirectCost/RR_FedNonFedBudget:TotalFedNonFed)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther1DirectCost/RR_FedNonFedBudget:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block>
												<fo:inline font-weight="bold">9. </fo:inline>Other 2</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther2DirectCost/RR_FedNonFedBudget:Federal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther2DirectCost/RR_FedNonFedBudget:Federal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther2DirectCost/RR_FedNonFedBudget:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther2DirectCost/RR_FedNonFedBudget:NonFederal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther2DirectCost/RR_FedNonFedBudget:NonFederal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther2DirectCost/RR_FedNonFedBudget:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther2DirectCost/RR_FedNonFedBudget:TotalFedNonFed = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther2DirectCost/RR_FedNonFedBudget:TotalFedNonFed)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther2DirectCost/RR_FedNonFedBudget:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block>
												<fo:inline font-weight="bold">10. </fo:inline>Other 3</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther3DirectCost/RR_FedNonFedBudget:Federal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther3DirectCost/RR_FedNonFedBudget:Federal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther3DirectCost/RR_FedNonFedBudget:Federal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther3DirectCost/RR_FedNonFedBudget:NonFederal = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther3DirectCost/RR_FedNonFedBudget:NonFederal)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther3DirectCost/RR_FedNonFedBudget:NonFederal
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther3DirectCost/RR_FedNonFedBudget:TotalFedNonFed = '' or not(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther3DirectCost/RR_FedNonFedBudget:TotalFedNonFed)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeOtherDirect/RR_FedNonFedBudget:CumulativeOther3DirectCost/RR_FedNonFedBudget:TotalFedNonFed
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block font-weight="bold">Section G, Direct Costs (A thru F)</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTotalFundsRequestedDirectCosts/RR_FedNonFedBudget:FederalSummary = '' or not(RR_FedNonFedBudget:CumulativeTotalFundsRequestedDirectCosts/RR_FedNonFedBudget:FederalSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTotalFundsRequestedDirectCosts/RR_FedNonFedBudget:FederalSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTotalFundsRequestedDirectCosts/RR_FedNonFedBudget:NonFederalSummary = '' or not(RR_FedNonFedBudget:CumulativeTotalFundsRequestedDirectCosts/RR_FedNonFedBudget:NonFederalSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTotalFundsRequestedDirectCosts/RR_FedNonFedBudget:NonFederalSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTotalFundsRequestedDirectCosts/RR_FedNonFedBudget:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget:CumulativeTotalFundsRequestedDirectCosts/RR_FedNonFedBudget:TotalFedNonFedSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTotalFundsRequestedDirectCosts/RR_FedNonFedBudget:TotalFedNonFedSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block font-weight="bold">Section H, Indirect Costs</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTotalFundsRequestedIndirectCost/RR_FedNonFedBudget:FederalSummary = '' or not(RR_FedNonFedBudget:CumulativeTotalFundsRequestedIndirectCost/RR_FedNonFedBudget:FederalSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTotalFundsRequestedIndirectCost/RR_FedNonFedBudget:FederalSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTotalFundsRequestedIndirectCost/RR_FedNonFedBudget:NonFederalSummary = '' or not(RR_FedNonFedBudget:CumulativeTotalFundsRequestedIndirectCost/RR_FedNonFedBudget:NonFederalSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTotalFundsRequestedIndirectCost/RR_FedNonFedBudget:NonFederalSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTotalFundsRequestedIndirectCost/RR_FedNonFedBudget:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget:CumulativeTotalFundsRequestedIndirectCost/RR_FedNonFedBudget:TotalFedNonFedSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTotalFundsRequestedIndirectCost/RR_FedNonFedBudget:TotalFedNonFedSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block font-weight="bold">Section I, Total Direct and Indirect Costs (G + H)</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTotalFundsRequestedDirectIndirectCosts/RR_FedNonFedBudget:FederalSummary = '' or not(RR_FedNonFedBudget:CumulativeTotalFundsRequestedDirectIndirectCosts/RR_FedNonFedBudget:FederalSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTotalFundsRequestedDirectIndirectCosts/RR_FedNonFedBudget:FederalSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTotalFundsRequestedDirectIndirectCosts/RR_FedNonFedBudget:NonFederalSummary = '' or not(RR_FedNonFedBudget:CumulativeTotalFundsRequestedDirectIndirectCosts/RR_FedNonFedBudget:NonFederalSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTotalFundsRequestedDirectIndirectCosts/RR_FedNonFedBudget:NonFederalSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeTotalFundsRequestedDirectIndirectCosts/RR_FedNonFedBudget:TotalFedNonFedSummary = '' or not(RR_FedNonFedBudget:CumulativeTotalFundsRequestedDirectIndirectCosts/RR_FedNonFedBudget:TotalFedNonFedSummary)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeTotalFundsRequestedDirectIndirectCosts/RR_FedNonFedBudget:TotalFedNonFedSummary
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
											<fo:block font-weight="bold">Section J, Fee</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block>
												<xsl:choose>
													<xsl:when test="RR_FedNonFedBudget:CumulativeFee = '' or not(RR_FedNonFedBudget:CumulativeFee)">
														<fo:inline>&#160;</fo:inline>
													</xsl:when>
													<xsl:otherwise>
														<xsl:value-of select="format-number(RR_FedNonFedBudget:CumulativeFee
, '#,##0.00')" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"/>
													</xsl:otherwise>
												</xsl:choose>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block/>
										</fo:table-cell>
										<fo:table-cell text-align="right">
											<fo:block/>
										</fo:table-cell>
									</fo:table-row>
									<!--============ ROWS End ================================-->
								</xsl:for-each>
							</fo:table-body>
						</fo:table>
						<fo:block font-size="8pt">RESEARCH &amp; RELATED Budget (Total Fed + Non-Fed)</fo:block>
					</fo:block>
					<!--</xsl:for-each>-->
					<!--================== end new section summary ======================= -->
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	<!--==============   Single Year Template ======================================-->
	<xsl:template name="SingleYearPart1">
		<xsl:param name="year"/>
		<fo:block>
			<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
				<fo:table-column/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="10pt">RESEARCH &amp; RELATED BUDGET (TOTAL FED + NON-FED) - SECTION A &amp; B, BUDGET PERIOD&#160;<xsl:value-of select="$year"/>
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block font-size="8pt">
				<fo:inline font-weight="bold">* ORGANIZATIONAL DUNS:&#160;&#160;</fo:inline>
				<fo:inline>
					<xsl:value-of select="../globLib:DUNSID"/>
				</fo:inline>
			</fo:block>
			<fo:inline font-size="8pt" font-weight="bold">*&#160;Budget Type:&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>
			<xsl:for-each select="../RR_FedNonFedBudget:BudgetType">
				<fo:inline padding-before="-1pt" padding-after="-2pt" color="black">
					<fo:inline font-size="8pt">
						<xsl:choose>
							<xsl:when test=".='Project'">
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
							</xsl:when>
							<xsl:otherwise>
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
							</xsl:otherwise>
						</xsl:choose>
					</fo:inline>
				</fo:inline>
			</xsl:for-each>
			<fo:inline font-size="8pt"> Project&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>
			<xsl:for-each select="../RR_FedNonFedBudget:BudgetType">
				<fo:inline padding-before="-1pt" padding-after="-2pt" color="black">
					<fo:inline font-size="8pt">
						<xsl:choose>
							<xsl:when test=".='Subaward/Consortium'">
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
							</xsl:when>
							<xsl:otherwise>
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
							</xsl:otherwise>
						</xsl:choose>
					</fo:inline>
				</fo:inline>
			</xsl:for-each>
			<fo:inline font-size="8pt"> Subaward/Consortium</fo:inline>
			<fo:block>
                        
                     </fo:block>
			<fo:inline font-size="8pt" hyphenate="true" language="en" font-weight="bold">Enter name of Organization: </fo:inline>
			<fo:inline font-size="8pt">
				<xsl:value-of select="../RR_FedNonFedBudget:OrganizationName"/>
			</fo:inline>
			<fo:block>
                        
                     </fo:block>
			<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
				<fo:table-column/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">*&#160;Start Date:&#160;</fo:inline>
								<fo:inline font-size="8pt">
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="RR_FedNonFedBudget:BudgetPeriodStartDate"/>
									</xsl:call-template>
								</fo:inline>
								<fo:inline font-size="8pt" font-weight="bold">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; *&#160;End Date:&#160;</fo:inline>
								<fo:inline font-size="8pt">
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="RR_FedNonFedBudget:BudgetPeriodEndDate"/>
									</xsl:call-template>
								</fo:inline>
								<fo:inline font-size="8pt" font-weight="bold">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;* Budget Period:&#160;<xsl:value-of select="$year"/>
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<xsl:for-each select="RR_FedNonFedBudget:KeyPersons">
				<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
					<fo:table-column/>
					<fo:table-body>
						<fo:table-row>
							<fo:table-cell border-style="solid" border-color="black" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<fo:inline font-size="8pt" font-weight="bold">A. Senior/Key Person</fo:inline>
									<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
										<fo:table-column column-width="proportional-column-width(2)"/>
										<fo:table-column column-width="proportional-column-width(4)"/>
										<fo:table-column column-width="proportional-column-width(10)"/>
										<fo:table-column column-width="proportional-column-width(10)"/>
										<fo:table-column column-width="proportional-column-width(18)"/>
										<fo:table-column column-width="proportional-column-width(4)"/>
										<fo:table-column column-width="proportional-column-width(14)"/>
										<fo:table-column column-width="proportional-column-width(10)"/>
										<fo:table-column column-width="proportional-column-width(6)"/>
										<fo:table-column column-width="proportional-column-width(6)"/>
										<fo:table-column column-width="proportional-column-width(6)"/>
										<fo:table-column column-width="proportional-column-width(8)"/>
										<fo:table-column column-width="proportional-column-width(8)"/>
										<fo:table-column column-width="proportional-column-width(9)"/>
										<fo:table-column column-width="proportional-column-width(9)"/>
										<fo:table-column column-width="proportional-column-width(12)"/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell number-columns-spanned="2" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">Prefix</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">* First Name</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">Middle Name</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">* Last Name</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">Suffix</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">* Project Role</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">Base Salary (&#36;)</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">Cal. Months</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">Acad. Months</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">Sum. Months</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">* Req. Salary&#160;(&#36;)</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">* Fringe Ben. (&#36;)</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">* Total(Sal &amp; FB)(Fed + Non-Fed) (&#36;)</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">* Federal (&#36;)</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-weight="bold" font-size="8pt">* Non-Federal (&#36;)</fo:inline>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
									<xsl:for-each select="RR_FedNonFedBudget:KeyPerson">
										<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
											<fo:table-column column-width="proportional-column-width(2)"/>
											<fo:table-column column-width="proportional-column-width(4)"/>
											<fo:table-column column-width="proportional-column-width(10)"/>
											<fo:table-column column-width="proportional-column-width(10)"/>
											<fo:table-column column-width="proportional-column-width(18)"/>
											<fo:table-column column-width="proportional-column-width(4)"/>
											<fo:table-column column-width="proportional-column-width(14)"/>
											<fo:table-column column-width="proportional-column-width(10)"/>
											<fo:table-column column-width="proportional-column-width(6)"/>
											<fo:table-column column-width="proportional-column-width(6)"/>
											<fo:table-column column-width="proportional-column-width(6)"/>
											<fo:table-column column-width="proportional-column-width(8)"/>
											<fo:table-column column-width="proportional-column-width(8)"/>
											<fo:table-column column-width="proportional-column-width(9)"/>
											<fo:table-column column-width="proportional-column-width(9)"/>
											<fo:table-column column-width="proportional-column-width(12)"/>
											<fo:table-body>
												<fo:table-row>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<fo:inline font-size="8pt">
																<xsl:value-of select="position()"/>.&#160;</fo:inline>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Name">
																<xsl:for-each select="globLib:PrefixName">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Name">
																<xsl:for-each select="globLib:FirstName">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Name">
																<xsl:for-each select="globLib:MiddleName">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Name">
																<xsl:for-each select="globLib:LastName">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Name">
																<xsl:for-each select="globLib:SuffixName">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:ProjectRole">
																<fo:inline font-size="8pt">
																	<xsl:apply-templates/>
																</fo:inline>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:BaseSalary">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:CalendarMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:AcademicMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:SummerMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:RequestedSalary">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:FringeBenefits">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:Total/RR_FedNonFedBudget:TotalFedNonFed">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:Total/RR_FedNonFedBudget:Federal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:Total/RR_FedNonFedBudget:NonFederal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
												</fo:table-row>
											</fo:table-body>
										</fo:table>
									</xsl:for-each>
									<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
										<fo:table-column column-width="proportional-column-width(2)"/>
										<fo:table-column column-width="proportional-column-width(4)"/>
										<fo:table-column column-width="proportional-column-width(10)"/>
										<fo:table-column column-width="proportional-column-width(10)"/>
										<fo:table-column column-width="proportional-column-width(18)"/>
										<fo:table-column column-width="proportional-column-width(4)"/>
										<fo:table-column column-width="proportional-column-width(14)"/>
										<fo:table-column column-width="proportional-column-width(10)"/>
										<fo:table-column column-width="proportional-column-width(6)"/>
										<fo:table-column column-width="proportional-column-width(6)"/>
										<fo:table-column column-width="proportional-column-width(6)"/>
										<fo:table-column column-width="proportional-column-width(8)"/>
										<fo:table-column column-width="proportional-column-width(8)"/>
										<fo:table-column column-width="proportional-column-width(9)"/>
										<fo:table-column column-width="proportional-column-width(9)"/>
										<fo:table-column column-width="proportional-column-width(12)"/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell number-columns-spanned="13" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-size="8pt" font-weight="bold">9. Total Funds Requested for all Senior Key Persons in the attached file </fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:TotalFundForAttachedKeyPersons/RR_FedNonFedBudget:TotalFedNonFedSummary">
															<fo:inline font-size="8pt">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:TotalFundForAttachedKeyPersons/RR_FedNonFedBudget:FederalSummary">
															<fo:inline font-size="8pt">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:TotalFundForAttachedKeyPersons/RR_FedNonFedBudget:NonFederalSummary">
															<fo:inline font-size="8pt">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell number-columns-spanned="4" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-size="8pt" font-weight="bold">* Additional Senior Key Persons:</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell number-columns-spanned="3" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-size="8pt">File Name: </fo:inline>
														<xsl:for-each select="RR_FedNonFedBudget:AttachedKeyPersons">
															<xsl:for-each select="att:FileName">
																<fo:inline font-size="8pt">
																	<xsl:apply-templates/>
																</fo:inline>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell number-columns-spanned="3" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-size="8pt">Mime Type: </fo:inline>
														<xsl:for-each select="RR_FedNonFedBudget:AttachedKeyPersons">
															<xsl:for-each select="att:MimeType">
																<fo:inline font-size="8pt">
																	<xsl:apply-templates/>
																</fo:inline>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell number-columns-spanned="3" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-size="8pt" font-weight="bold">Total Senior/Key Person</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:TotalFundForKeyPersons/RR_FedNonFedBudget:TotalFedNonFedSummary">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:TotalFundForKeyPersons/RR_FedNonFedBudget:FederalSummary">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:TotalFundForKeyPersons/RR_FedNonFedBudget:NonFederalSummary">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
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
			</xsl:for-each>
			<fo:block>
				<fo:leader leader-pattern="space"/>
			</fo:block>
			<!--
      
          -->
			<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
				<fo:table-column/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell border-style="solid" border-color="black" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">B. Other Personnel</fo:inline>
								<fo:block>
									<xsl:text>&#xA;</xsl:text>
								</fo:block>
								<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(61)"/>
									<fo:table-column column-width="proportional-column-width(6)"/>
									<fo:table-column column-width="proportional-column-width(6)"/>
									<fo:table-column column-width="proportional-column-width(6)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">* Number of Personnel</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" width="150pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">* Project Role</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">Cal. Months</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">Acad. Months</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">Sum. Months</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">* Req. Salary (&#36;)</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">* Fringe Ben. (&#36;)</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">*Total(Sal &amp; FB)(Fed + Non-Fed) (&#36;)</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">*Federal (&#36;)</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">*Non-Federal (&#36;)</fo:inline>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<xsl:for-each select="RR_FedNonFedBudget:OtherPersonnel">
											<fo:table-row>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:PostDocAssociates">
															<xsl:for-each select="RR_FedNonFedBudget:NumberOfPersonnel">
																<fo:inline font-size="8pt">
																	<xsl:apply-templates/>
																</fo:inline>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="left" width="150pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-size="8pt">Post Doctoral Associates</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:PostDocAssociates">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:CalendarMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:PostDocAssociates">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:AcademicMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:PostDocAssociates">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:SummerMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:PostDocAssociates">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:RequestedSalary">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:PostDocAssociates">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:FringeBenefits">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:PostDocAssociates">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:OtherTotal/RR_FedNonFedBudget:TotalFedNonFed">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:PostDocAssociates">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:OtherTotal/RR_FedNonFedBudget:Federal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:PostDocAssociates">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:OtherTotal/RR_FedNonFedBudget:NonFederal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:GraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget:NumberOfPersonnel">
																<fo:inline font-size="8pt">
																	<xsl:apply-templates/>
																</fo:inline>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="left" width="150pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-size="8pt">Graduate Students</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:GraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:CalendarMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:GraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:AcademicMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:GraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:SummerMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:GraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:RequestedSalary">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:GraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:FringeBenefits">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:GraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:OtherTotal/RR_FedNonFedBudget:TotalFedNonFed">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:GraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:OtherTotal/RR_FedNonFedBudget:Federal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:GraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:OtherTotal/RR_FedNonFedBudget:NonFederal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:UndergraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget:NumberOfPersonnel">
																<fo:inline font-size="8pt">
																	<xsl:apply-templates/>
																</fo:inline>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="left" width="150pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-size="8pt">Undergraduate Students</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:UndergraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:CalendarMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:UndergraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:AcademicMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:UndergraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:SummerMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:UndergraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:RequestedSalary">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:UndergraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:FringeBenefits">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:UndergraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:OtherTotal/RR_FedNonFedBudget:TotalFedNonFed">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:UndergraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:OtherTotal/RR_FedNonFedBudget:Federal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:UndergraduateStudents">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:OtherTotal/RR_FedNonFedBudget:NonFederal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:SecretarialClerical">
															<xsl:for-each select="RR_FedNonFedBudget:NumberOfPersonnel">
																<fo:inline font-size="8pt">
																	<xsl:apply-templates/>
																</fo:inline>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="left" width="150pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<fo:inline font-size="8pt">Secretarial/Clerical</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:SecretarialClerical">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:CalendarMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:SecretarialClerical">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:AcademicMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:SecretarialClerical">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:SummerMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:SecretarialClerical">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:RequestedSalary">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:SecretarialClerical">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:FringeBenefits">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:SecretarialClerical">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:OtherTotal/RR_FedNonFedBudget:TotalFedNonFed">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:SecretarialClerical">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:OtherTotal/RR_FedNonFedBudget:Federal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
													<fo:block>
														<xsl:for-each select="RR_FedNonFedBudget:SecretarialClerical">
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:OtherTotal/RR_FedNonFedBudget:NonFederal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</xsl:for-each>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</xsl:for-each>
									</fo:table-body>
								</fo:table>
								<xsl:for-each select="RR_FedNonFedBudget:OtherPersonnel">
									<xsl:for-each select="RR_FedNonFedBudget:Other">
										<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
											<fo:table-column column-width="proportional-column-width(10)"/>
											<fo:table-column column-width="proportional-column-width(61)"/>
											<fo:table-column column-width="proportional-column-width(6)"/>
											<fo:table-column column-width="proportional-column-width(6)"/>
											<fo:table-column column-width="proportional-column-width(6)"/>
											<fo:table-column column-width="proportional-column-width(10)"/>
											<fo:table-column column-width="proportional-column-width(10)"/>
											<fo:table-column column-width="proportional-column-width(10)"/>
											<fo:table-column column-width="proportional-column-width(10)"/>
											<fo:table-column column-width="proportional-column-width(10)"/>
											<fo:table-body>
												<fo:table-row>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:NumberOfPersonnel">
																<fo:inline font-size="8pt">
																	<xsl:apply-templates/>
																</fo:inline>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="left" width="150pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:ProjectRole">
																<fo:inline font-size="8pt">
																	<xsl:apply-templates/>
																</fo:inline>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:CalendarMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:AcademicMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:SummerMonths">
																	<fo:inline font-size="8pt">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:RequestedSalary">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:FringeBenefits">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:OtherTotal/RR_FedNonFedBudget:TotalFedNonFed">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:OtherTotal/RR_FedNonFedBudget:Federal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:Compensation">
																<xsl:for-each select="RR_FedNonFedBudget:OtherTotal/RR_FedNonFedBudget:NonFederal">
																	<fo:inline font-size="8pt">
																		<xsl:value-of select="format-number(., '#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
												</fo:table-row>
											</fo:table-body>
										</fo:table>
									</xsl:for-each>
								</xsl:for-each>
								<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(61)"/>
									<fo:table-column column-width="proportional-column-width(6)"/>
									<fo:table-column column-width="proportional-column-width(6)"/>
									<fo:table-column column-width="proportional-column-width(6)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget:OtherPersonnel">
														<xsl:for-each select="RR_FedNonFedBudget:OtherPersonnelTotalNumber">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:apply-templates/>
															</fo:inline>
														</xsl:for-each>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="left" number-columns-spanned="2" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-size="8pt" font-weight="bold">Total Number Other Personnel</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell number-columns-spanned="4" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-size="8pt" font-weight="bold">Total Other Personnel &#160;&#160;&#160;&#160;</fo:inline>
												</fo:block>
											</fo:table-cell>
											<!-- Nikisha : added hyphenate -->
											<fo:table-cell hyphenate="true" language="en" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget:OtherPersonnel">
														<xsl:if test="RR_FedNonFedBudget:TotalOtherPersonnelFund/RR_FedNonFedBudget:TotalFedNonFedSummary!=''">
															<xsl:for-each select="RR_FedNonFedBudget:TotalOtherPersonnelFund/RR_FedNonFedBudget:TotalFedNonFedSummary">
																<fo:inline font-size="8pt" font-weight="bold">
																	<xsl:value-of select="format-number(., '#,##0.00')"/>
																</fo:inline>
															</xsl:for-each>
														</xsl:if>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											<!-- Nikisha : added hyphenate -->
											<fo:table-cell hyphenate="true" language="en"  text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget:OtherPersonnel">
														<xsl:if test="RR_FedNonFedBudget:TotalOtherPersonnelFund/RR_FedNonFedBudget:FederalSummary!=''">
															<xsl:for-each select="RR_FedNonFedBudget:TotalOtherPersonnelFund/RR_FedNonFedBudget:FederalSummary">
																<fo:inline font-size="8pt" font-weight="bold">
																	<xsl:value-of select="format-number(., '#,##0.00')"/>
																</fo:inline>
															</xsl:for-each>
														</xsl:if>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											<!-- Nikisha : added hyphenate -->
											<fo:table-cell  hyphenate="true" language="en" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget:OtherPersonnel">
														<xsl:if test="RR_FedNonFedBudget:TotalOtherPersonnelFund/RR_FedNonFedBudget:NonFederalSummary!=''">
															<xsl:for-each select="RR_FedNonFedBudget:TotalOtherPersonnelFund/RR_FedNonFedBudget:NonFederalSummary">
																<fo:inline font-size="8pt" font-weight="bold">
																	<xsl:value-of select="format-number(., '#,##0.00')"/>
																</fo:inline>
															</xsl:for-each>
														</xsl:if>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell number-columns-spanned="7" hyphenate="true" language="en" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-size="8pt" font-weight="bold">Total Salary, Wages and Fringe Benefits (A+B)&#160;&#160;&#160;</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right" hyphenate="true" language="en" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget:TotalCompensation/RR_FedNonFedBudget:TotalFedNonFedSummary">
														<fo:inline font-size="8pt" font-weight="bold">
															<xsl:value-of select="format-number(., '#,##0.00')"/>
														</fo:inline>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right" hyphenate="true" language="en" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget:TotalCompensation/RR_FedNonFedBudget:FederalSummary">
														<fo:inline font-size="8pt" font-weight="bold">
															<xsl:value-of select="format-number(., '#,##0.00')"/>
														</fo:inline>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="right" hyphenate="true" language="en" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget:TotalCompensation/RR_FedNonFedBudget:NonFederalSummary">
														<fo:inline font-size="8pt" font-weight="bold">
															<xsl:value-of select="format-number(., '#,##0.00')"/>
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
			<fo:block font-size="8pt">RESEARCH &amp; RELATED Budget {A-B} (Total Fed + Non-Fed)</fo:block>
		</fo:block>
	</xsl:template>
	<xsl:template name="SingleYearPart2">
		<xsl:param name="year"/>
		<fo:block>
			<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="10pt">RESEARCH &amp; RELATED BUDGET (TOTAL FED + NON-FED) - SECTION C, D, &amp; E, BUDGET PERIOD&#160;<xsl:value-of select="$year"/>
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block font-size="8pt">
				<fo:inline font-weight="bold">* ORGANIZATIONAL DUNS:&#160;&#160;</fo:inline>
				<fo:inline>
					<xsl:value-of select="../globLib:DUNSID"/>
				</fo:inline>
			</fo:block>
			<fo:inline font-size="8pt" font-weight="bold">* Budget Type:&#160;&#160; </fo:inline>
			<fo:inline font-size="8pt">&#160;&#160;&#160; </fo:inline>
			<xsl:for-each select="../RR_FedNonFedBudget:BudgetType">
				<fo:inline padding-before="-1pt" padding-after="-2pt" color="black">
					<fo:inline font-size="8pt">
						<xsl:choose>
							<xsl:when test=".='Project'">
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
							</xsl:when>
							<xsl:otherwise>
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
							</xsl:otherwise>
						</xsl:choose>
					</fo:inline>
				</fo:inline>
			</xsl:for-each>
			<fo:inline font-size="8pt"> Project&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>
			<xsl:for-each select="../RR_FedNonFedBudget:BudgetType">
				<fo:inline padding-before="-1pt" padding-after="-2pt" color="black">
					<fo:inline font-size="8pt">
						<xsl:choose>
							<xsl:when test=".='Subaward/Consortium'">
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
							</xsl:when>
							<xsl:otherwise>
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
							</xsl:otherwise>
						</xsl:choose>
					</fo:inline>
				</fo:inline>
			</xsl:for-each>
			<fo:inline font-size="8pt"> Subaward/Consortium</fo:inline>
			<fo:block>
                        
                     </fo:block>
			<fo:inline font-size="8pt" font-weight="bold">Enter name of Organization: </fo:inline>
			<xsl:for-each select="../RR_FedNonFedBudget:OrganizationName">
				<fo:inline font-size="8pt">
					<xsl:apply-templates/>
				</fo:inline>
			</xsl:for-each>
			<fo:block>
                        
                     </fo:block>
			<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">* Start Date:&#160;</fo:inline>
								<fo:inline font-size="8pt">
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="RR_FedNonFedBudget:BudgetPeriodStartDate"/>
									</xsl:call-template>
								</fo:inline>
								<fo:inline font-size="8pt" font-weight="bold">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;* End Date:&#160;</fo:inline>
								<fo:inline font-size="8pt">
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="RR_FedNonFedBudget:BudgetPeriodEndDate"/>
									</xsl:call-template>
								</fo:inline>
								<fo:inline font-size="8pt" font-weight="bold">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;* Budget Period:&#160;<xsl:value-of select="$year"/>
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell hyphenate="true" language="en" border-style="solid" border-color="black" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">C. Equipment Description</fo:inline>
								<fo:block>
                                          
                                       </fo:block>
								<fo:inline font-size="8pt" font-weight="bold">List items and dollar amount for each item exceeding $5,000</fo:inline>
								<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
									<fo:table-column column-width="proportional-column-width(3)"/>
									<fo:table-column column-width="proportional-column-width(50)"/>
									<fo:table-column column-width="proportional-column-width(16)"/>
									<fo:table-column column-width="proportional-column-width(16)"/>
									<fo:table-column column-width="proportional-column-width(16)"/>
									<fo:table-header>
										<fo:table-row>
											<fo:table-cell>
                                                                                            <fo:block/>
                                                                                        </fo:table-cell>
											<fo:table-cell border-before-color="white" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">*Equipment Item</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell border-before-color="white" border-end-color="white" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">*Federal (&#36;)</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell border-before-color="white" border-end-color="white" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">*Non-Federal (&#36;)</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell border-before-color="white" border-end-color="white" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-weight="bold" font-size="8pt">*Total (Fed + Non-Fed) (&#36;)</fo:inline>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-header>
									<fo:table-body>
										<xsl:for-each select="RR_FedNonFedBudget:Equipment">
											<xsl:for-each select="RR_FedNonFedBudget:EquipmentList">
												<fo:table-row>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
														<fo:block>
															<fo:inline font-size="8pt">
																<xsl:value-of select="position()"/>.&#160;</fo:inline>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
														<fo:block>
															<fo:inline font-size="8pt">
																<xsl:value-of select="RR_FedNonFedBudget:EquipmentItem"/>
															</fo:inline>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:FundsRequested/RR_FedNonFedBudget:Federal">
																<fo:inline font-size="8pt">
																<!-- Nikisha: removed apply templates and added format-number -->
																	<xsl:value-of select="format-number(., '#,##0.00')"/>
																</fo:inline>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:FundsRequested/RR_FedNonFedBudget:NonFederal">
																<fo:inline font-size="8pt">
																	<xsl:value-of select="format-number(., '#,##0.00')"/>
																</fo:inline>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:FundsRequested/RR_FedNonFedBudget:TotalFedNonFed">
																<fo:inline font-size="8pt">
																	<xsl:value-of select="format-number(., '#,##0.00')"/>
																</fo:inline>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right">
														<fo:block>
															<xsl:for-each select="RR_FedNonFedBudget:FundsRequested/RR_FedNonFedBudget:TotalFedNonFed">
																<fo:inline font-size="8pt">
																	<xsl:value-of select="format-number(., '#,##0.00')"/>
																</fo:inline>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
												</fo:table-row>
											</xsl:for-each>
										</xsl:for-each>
									</fo:table-body>
								</fo:table>
								<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
									<fo:table-column column-width="proportional-column-width(53)"/>
									<fo:table-column column-width="proportional-column-width(16)"/>
									<fo:table-column column-width="proportional-column-width(16)"/>
									<fo:table-column column-width="proportional-column-width(16)"/>
									<fo:table-header>
										<fo:table-row>
											<fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-size="8pt" font-weight="bold">11. Total funds requested for all equipment listed in the attached file</fo:inline>
												</fo:block>
											</fo:table-cell>
											<!-- Nikisha : removed 'Summary from the xpath names for the following set -->
											<fo:table-cell hyphenate="true" language="en" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget:Equipment">
														<xsl:for-each select="RR_FedNonFedBudget:TotalFundForAttachedEquipment/RR_FedNonFedBudget:Federal">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											
											<fo:table-cell hyphenate="true" language="en" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget:Equipment">
														<xsl:for-each select="RR_FedNonFedBudget:TotalFundForAttachedEquipment/RR_FedNonFedBudget:NonFederal">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell hyphenate="true" language="en" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget:Equipment">
														<xsl:for-each select="RR_FedNonFedBudget:TotalFundForAttachedEquipment/RR_FedNonFedBudget:TotalFedNonFed">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-header>
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right">
												<fo:block>
													<fo:inline font-size="8pt" font-weight="bold">Total Equipment</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell hyphenate="true" language="en" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget:Equipment">
														<xsl:for-each select="RR_FedNonFedBudget:TotalFund/RR_FedNonFedBudget:FederalSummary">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell hyphenate="true" language="en" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget:Equipment">
														<xsl:for-each select="RR_FedNonFedBudget:TotalFund/RR_FedNonFedBudget:NonFederalSummary">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell hyphenate="true" language="en" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<xsl:for-each select="RR_FedNonFedBudget:Equipment">
														<xsl:for-each select="RR_FedNonFedBudget:TotalFund/RR_FedNonFedBudget:TotalFedNonFedSummary">
															<fo:inline font-size="8pt" font-weight="bold">
																<xsl:value-of select="format-number(., '#,##0.00')"/>
															</fo:inline>
														</xsl:for-each>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
								<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
									<fo:table-column/>
									<fo:table-column/>
									<fo:table-column/>
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
												<fo:block>
													<fo:inline font-size="8pt" font-weight="bold">* Additional Equipment:</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
												<fo:block>
													<fo:inline font-size="8pt">File Name: </fo:inline>
													<xsl:for-each select="RR_FedNonFedBudget:Equipment/RR_FedNonFedBudget:AdditionalEquipmentsAttachment">
														<xsl:for-each select="att:FileName">
															<fo:inline font-size="8pt">
																<xsl:apply-templates/>
															</fo:inline>
														</xsl:for-each>
													</xsl:for-each>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
												<fo:block>
													<fo:inline font-size="8pt">Mime Type: </fo:inline>
													<xsl:for-each select="RR_FedNonFedBudget:Equipment/RR_FedNonFedBudget:AdditionalEquipmentsAttachment">
														<xsl:for-each select="att:MimeType">
															<fo:inline font-size="8pt">
																<xsl:apply-templates/>
															</fo:inline>
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
			<fo:block>
				<fo:leader leader-pattern="space"/>
			</fo:block>
			<fo:table border-style="solid" border-color="black" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column column-width="proportional-column-width(53)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">D. Travel</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-before-color="white" border-end-color="white" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-before-color="white" border-end-color="white" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Non-Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border-before-color="white" border-end-color="white" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Total (Fed + Non-Fed) (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt">1. Domestic Travel Costs ( Incl. Canada, Mexico, and U.S. Possessions)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:Travel/RR_FedNonFedBudget:DomesticTravelCost/RR_FedNonFedBudget:Federal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:Travel/RR_FedNonFedBudget:DomesticTravelCost/RR_FedNonFedBudget:NonFederal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:Travel/RR_FedNonFedBudget:DomesticTravelCost/RR_FedNonFedBudget:TotalFedNonFed">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt">2. Foreign Travel Costs</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:Travel/RR_FedNonFedBudget:ForeignTravelCost/RR_FedNonFedBudget:Federal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:Travel/RR_FedNonFedBudget:ForeignTravelCost/RR_FedNonFedBudget:NonFederal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:Travel/RR_FedNonFedBudget:ForeignTravelCost/RR_FedNonFedBudget:TotalFedNonFed">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">Total Travel Costs</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:Travel/RR_FedNonFedBudget:TotalTravelCost/RR_FedNonFedBudget:FederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:Travel/RR_FedNonFedBudget:TotalTravelCost/RR_FedNonFedBudget:NonFederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:Travel/RR_FedNonFedBudget:TotalTravelCost/RR_FedNonFedBudget:TotalFedNonFedSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block>
				<fo:leader leader-pattern="space"/>
			</fo:block>
			<fo:table border-style="solid" border-color="black" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column column-width="proportional-column-width(28)"/>
				<fo:table-column column-width="proportional-column-width(25)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">E. Participant/Trainee Support Costs</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold"> </fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Non-Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Total (Fed + Non-Fed) (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt">1. Tuition/Fees/Health Insurance</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt"> </fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:TuitionFeeHealthInsurance/RR_FedNonFedBudget:Federal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:TuitionFeeHealthInsurance/RR_FedNonFedBudget:NonFederal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:TuitionFeeHealthInsurance/RR_FedNonFedBudget:TotalFedNonFed">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt">2. Stipends</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt"> </fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:Stipends/RR_FedNonFedBudget:Federal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:Stipends/RR_FedNonFedBudget:NonFederal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:Stipends/RR_FedNonFedBudget:TotalFedNonFed">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt">3. Travel</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt"> </fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:Travel/RR_FedNonFedBudget:Federal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:Travel/RR_FedNonFedBudget:NonFederal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:Travel/RR_FedNonFedBudget:TotalFedNonFed">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt">4. Subsistence</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt"> </fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:Subsistence/RR_FedNonFedBudget:Federal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:Subsistence/RR_FedNonFedBudget:NonFederal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:Subsistence/RR_FedNonFedBudget:TotalFedNonFed">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell language="en" line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block hyphenate="true">
								<fo:inline font-size="8pt">5. Other&#160;&#160; </fo:inline>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:Other">
									<xsl:for-each select="RR_FedNonFedBudget:Description">
										<fo:inline font-size="8pt">
											<xsl:apply-templates/>
										</fo:inline>
									</xsl:for-each>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt"> </fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:Other/RR_FedNonFedBudget:Cost/RR_FedNonFedBudget:Federal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:Other/RR_FedNonFedBudget:Cost/RR_FedNonFedBudget:NonFederal">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:Other/RR_FedNonFedBudget:Cost/RR_FedNonFedBudget:TotalFedNonFed">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:ParticipantTraineeNumber">
									<fo:inline font-size="8pt">
										<xsl:apply-templates/>
									</fo:inline>
								</xsl:for-each>
								<fo:inline font-size="8pt" font-weight="bold">&#160;&#160;&#160;Number of Participants/Trainees</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">Total Participant/Trainee Support Costs</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:TotalCost/RR_FedNonFedBudget:FederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:TotalCost/RR_FedNonFedBudget:NonFederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:ParticipantTraineeSupportCosts/RR_FedNonFedBudget:TotalCost/RR_FedNonFedBudget:TotalFedNonFedSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block font-size="8pt">RESEARCH &amp; RELATED Budget {C-E} (Total Fed + Non-Fed)</fo:block>
			<fo:block break-after="page">
				<xsl:text>&#xA;</xsl:text>
			</fo:block>
			<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="10pt">RESEARCH &amp; RELATED BUDGET (TOTAL FED + NON-FED) - SECTIONS F-K, BUDGET PERIOD&#160;<xsl:value-of select="$year"/>
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block font-size="8pt">
				<fo:inline font-weight="bold">* ORGANIZATIONAL DUNS:&#160;&#160;</fo:inline>
				<fo:inline>
					<xsl:value-of select="../globLib:DUNSID"/>
				</fo:inline>
			</fo:block>
			<fo:inline font-size="8pt" font-weight="bold">* Budget Type:&#160;&#160; </fo:inline>
			<fo:inline font-size="8pt">&#160;&#160;&#160; </fo:inline>
			<xsl:for-each select="../RR_FedNonFedBudget:BudgetType">
				<fo:inline padding-before="-1pt" padding-after="-2pt" color="black">
					<fo:inline font-size="8pt">
						<xsl:choose>
							<xsl:when test=".='Project'">
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
							</xsl:when>
							<xsl:otherwise>
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
							</xsl:otherwise>
						</xsl:choose>
					</fo:inline>
				</fo:inline>
			</xsl:for-each>
			<fo:inline font-size="8pt"> Project&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>
			<xsl:for-each select="../RR_FedNonFedBudget:BudgetType">
				<fo:inline padding-before="-1pt" padding-after="-2pt" color="black">
					<fo:inline font-size="8pt">
						<xsl:choose>
							<xsl:when test=".='Subaward/Consortium'">
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
							</xsl:when>
							<xsl:otherwise>
								<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
							</xsl:otherwise>
						</xsl:choose>
					</fo:inline>
				</fo:inline>
			</xsl:for-each>
			<fo:inline font-size="8pt"> Subaward/Consortium</fo:inline>
			<fo:block>
                        
                     </fo:block>
			<fo:inline font-size="8pt" font-weight="bold">Enter name of Organization: </fo:inline>
			<xsl:for-each select="../RR_FedNonFedBudget:OrganizationName">
				<fo:inline font-size="8pt">
					<xsl:apply-templates/>
				</fo:inline>
			</xsl:for-each>
			<fo:block>
                        
                     </fo:block>
			<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">* Start Date:&#160;</fo:inline>
								<fo:inline font-size="8pt">
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="RR_FedNonFedBudget:BudgetPeriodStartDate"/>
									</xsl:call-template>
								</fo:inline>
								<fo:inline font-size="8pt" font-weight="bold">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;* End Date:&#160;</fo:inline>
								<fo:inline font-size="8pt">
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="RR_FedNonFedBudget:BudgetPeriodEndDate"/>
									</xsl:call-template>
								</fo:inline>
								<fo:inline font-size="8pt" font-weight="bold">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;* Budget Period:&#160;<xsl:value-of select="$year"/>
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:table border-style="solid" border-color="black" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column column-width="proportional-column-width(53)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">F. Other Direct Costs</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Non-Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Total (Fed + Non-Fed) (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<xsl:for-each select="RR_FedNonFedBudget:OtherDirectCosts">
						<fo:table-row>
							<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
								<fo:block>
									<fo:inline font-size="8pt">1. Materials and Supplies</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:MaterialsSupplies/RR_FedNonFedBudget:Federal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:MaterialsSupplies/RR_FedNonFedBudget:NonFederal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:MaterialsSupplies/RR_FedNonFedBudget:TotalFedNonFed">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
								<fo:block>
									<fo:inline font-size="8pt">2. Publication Costs</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:PublicationCosts/RR_FedNonFedBudget:Federal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:PublicationCosts/RR_FedNonFedBudget:NonFederal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:PublicationCosts/RR_FedNonFedBudget:TotalFedNonFed">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
								<fo:block>
									<fo:inline font-size="8pt">3. Consultant Services</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:ConsultantServices/RR_FedNonFedBudget:Federal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:ConsultantServices/RR_FedNonFedBudget:NonFederal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:ConsultantServices/RR_FedNonFedBudget:TotalFedNonFed">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
								<fo:block>
									<fo:inline font-size="8pt">4. ADP/Computer Services</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:ADPComputerServices/RR_FedNonFedBudget:Federal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:ADPComputerServices/RR_FedNonFedBudget:NonFederal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:ADPComputerServices/RR_FedNonFedBudget:TotalFedNonFed">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
								<fo:block>
									<fo:inline font-size="8pt">5. Subawards/Consortium/Contractual Costs</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:SubawardConsortiumContractualCosts/RR_FedNonFedBudget:Federal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:SubawardConsortiumContractualCosts/RR_FedNonFedBudget:NonFederal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:SubawardConsortiumContractualCosts/RR_FedNonFedBudget:TotalFedNonFed">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
								<fo:block>
									<fo:inline font-size="8pt">6. Equipment or Facility Rental/User Fees</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:EquipmentRentalFee/RR_FedNonFedBudget:Federal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:EquipmentRentalFee/RR_FedNonFedBudget:NonFederal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:EquipmentRentalFee/RR_FedNonFedBudget:TotalFedNonFed">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
								<fo:block>
									<fo:inline font-size="8pt">7. Alterations and Renovations</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:AlterationsRenovations/RR_FedNonFedBudget:Federal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:AlterationsRenovations/RR_FedNonFedBudget:NonFederal">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
								<fo:block>
									<xsl:for-each select="RR_FedNonFedBudget:AlterationsRenovations/RR_FedNonFedBudget:TotalFedNonFed">
										<fo:inline font-size="8pt">
											<xsl:value-of select="format-number(., '#,##0.00')"/>
										</fo:inline>
									</xsl:for-each>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<xsl:for-each select="RR_FedNonFedBudget:Others">
							<xsl:for-each select="RR_FedNonFedBudget:Other">
								<fo:table-row>
									<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
										<fo:block>
											<fo:inline font-size="8pt">
												<xsl:value-of select="position()+7"/>.&#160;</fo:inline>
											<xsl:for-each select="RR_FedNonFedBudget:Description">
												<fo:inline font-size="8pt">
													<xsl:apply-templates/>
												</fo:inline>
											</xsl:for-each>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
										<fo:block>
											<xsl:for-each select="RR_FedNonFedBudget:Cost/RR_FedNonFedBudget:Federal">
												<fo:inline font-size="8pt">
													<xsl:value-of select="format-number(., '#,##0.00')"/>
												</fo:inline>
											</xsl:for-each>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
										<fo:block>
											<xsl:for-each select="RR_FedNonFedBudget:Cost/RR_FedNonFedBudget:NonFederal">
												<fo:inline font-size="8pt">
													<xsl:value-of select="format-number(., '#,##0.00')"/>
												</fo:inline>
											</xsl:for-each>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
										<fo:block>
											<xsl:for-each select="RR_FedNonFedBudget:Cost/RR_FedNonFedBudget:TotalFedNonFed">
												<fo:inline font-size="8pt">
													<xsl:value-of select="format-number(., '#,##0.00')"/>
												</fo:inline>
											</xsl:for-each>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
					<fo:table-row>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">Total Other Direct Costs</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:OtherDirectCosts/RR_FedNonFedBudget:TotalOtherDirectCost/RR_FedNonFedBudget:FederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:OtherDirectCosts/RR_FedNonFedBudget:TotalOtherDirectCost/RR_FedNonFedBudget:NonFederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:OtherDirectCosts/RR_FedNonFedBudget:TotalOtherDirectCost/RR_FedNonFedBudget:TotalFedNonFedSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block>
				<fo:leader leader-pattern="space"/>
			</fo:block>
			<fo:table border-style="solid" border-color="black" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column column-width="proportional-column-width(53)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">G. Direct Costs</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Non-Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Total (Fed + Non-Fed) (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">Total Direct Costs (A thru F)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:DirectCosts/RR_FedNonFedBudget:FederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:DirectCosts/RR_FedNonFedBudget:NonFederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:DirectCosts/RR_FedNonFedBudget:TotalFedNonFedSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block>
				<fo:leader leader-pattern="space"/>
			</fo:block>
			<fo:table border-style="solid" border-color="black" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column column-width="proportional-column-width(31)"/>
				<!-- Nikisha : changed widths -->
				<fo:table-column column-width="proportional-column-width(17)"/>
				<fo:table-column column-width="proportional-column-width(17)"/>
				<fo:table-column column-width="proportional-column-width(12)"/>
				<fo:table-column column-width="proportional-column-width(12)"/>
				<fo:table-column column-width="proportional-column-width(12)"/>
				<fo:table-header>
					<fo:table-row>
						<fo:table-cell number-columns-spanned="4" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">H. Indirect Costs</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="center">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">*Indirect Cost Type</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">Indirect Cost Rate (%)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="center">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">Indirect Cost Base ($)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Non-Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Total (Fed + Non-Fed) (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-header>
				<fo:table-body>
					<xsl:for-each select="RR_FedNonFedBudget:IndirectCosts">
						<xsl:for-each select="RR_FedNonFedBudget:IndirectCost">
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
									<fo:block>
										<fo:inline font-size="8pt">
											<xsl:value-of select="position()"/>.&#160;</fo:inline>
										<xsl:for-each select="RR_FedNonFedBudget:CostType">
											<fo:inline font-size="8pt">
												<xsl:apply-templates/>
											</fo:inline>
										</xsl:for-each>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right">
									<fo:block>
										<xsl:for-each select="RR_FedNonFedBudget:Rate">
											<fo:inline font-size="8pt">
												<xsl:apply-templates/>
											</fo:inline>
										</xsl:for-each>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right">
									<fo:block>
										<xsl:for-each select="RR_FedNonFedBudget:Base">
											<fo:inline font-size="8pt">
												<xsl:value-of select="format-number(., '#,##0.00')"/>
											</fo:inline>
										</xsl:for-each>
									</fo:block>
								</fo:table-cell>
								<!-- Nikisha: added hyphenate to following 6 sets -->
								<fo:table-cell hyphenate="true" language="en"  line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right">
									<fo:block>
										<xsl:for-each select="RR_FedNonFedBudget:FundRequested/RR_FedNonFedBudget:Federal">
											<fo:inline font-size="8pt">
												<xsl:value-of select="format-number(., '#,##0.00')"/>
											</fo:inline>
										</xsl:for-each>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en"  line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right">
									<fo:block>
										<xsl:for-each select="RR_FedNonFedBudget:FundRequested/RR_FedNonFedBudget:NonFederal">
											<fo:inline font-size="8pt">
												<xsl:value-of select="format-number(., '#,##0.00')"/>
											</fo:inline>
										</xsl:for-each>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en"  line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right">
									<fo:block>
										<xsl:for-each select="RR_FedNonFedBudget:FundRequested/RR_FedNonFedBudget:TotalFedNonFed">
											<fo:inline font-size="8pt">
												<xsl:value-of select="format-number(., '#,##0.00')"/>
											</fo:inline>
										</xsl:for-each>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</xsl:for-each>
					</xsl:for-each>
					<fo:table-row>
						<fo:table-cell number-columns-spanned="3" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">Total Indirect Costs &#160;&#160;&#160;</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en"  text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">
									<xsl:choose>
										<xsl:when test="RR_FedNonFedBudget:IndirectCosts/RR_FedNonFedBudget:TotalIndirectCosts/RR_FedNonFedBudget:FederalSummary">
											<xsl:value-of select="format-number(RR_FedNonFedBudget:IndirectCosts/RR_FedNonFedBudget:TotalIndirectCosts/RR_FedNonFedBudget:FederalSummary, '#,##0.00')"/>
										</xsl:when>
										<!--<xsl:otherwise>0.00</xsl:otherwise>-->
									</xsl:choose>
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en"  text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">
									<xsl:choose>
										<xsl:when test="RR_FedNonFedBudget:IndirectCosts/RR_FedNonFedBudget:TotalIndirectCosts/RR_FedNonFedBudget:NonFederalSummary">
											<xsl:value-of select="format-number(RR_FedNonFedBudget:IndirectCosts/RR_FedNonFedBudget:TotalIndirectCosts/RR_FedNonFedBudget:NonFederalSummary, '#,##0.00')"/>
										</xsl:when>
										<!--<xsl:otherwise>0.00</xsl:otherwise>-->
									</xsl:choose>
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en"  text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">
									<xsl:choose>
										<xsl:when test="RR_FedNonFedBudget:IndirectCosts/RR_FedNonFedBudget:TotalIndirectCosts/RR_FedNonFedBudget:TotalFedNonFedSummary">
											<xsl:value-of select="format-number(RR_FedNonFedBudget:IndirectCosts/RR_FedNonFedBudget:TotalIndirectCosts/RR_FedNonFedBudget:TotalFedNonFedSummary, '#,##0.00')"/>
										</xsl:when>
										<!--<xsl:otherwise>0.00</xsl:otherwise>-->
									</xsl:choose>
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell hyphenate="true" language="en" number-columns-spanned="6" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="0pt" display-align="before">
                            <fo:block>
                                                    <fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
								<fo:table-column column-width="proportional-column-width(20)"/>
								<fo:table-column column-width="proportional-column-width(60)"/>
								<fo:table-column column-width="proportional-column-width(50)"/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell>
											<fo:block>
												<fo:inline font-size="8pt" font-weight="bold">Cognizant Agency</fo:inline>
												<fo:inline font-size="8pt">&#160; </fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell>
											<fo:block>
												<xsl:for-each select="RR_FedNonFedBudget:CognizantFederalAgency">
													<fo:inline font-size="8pt">
														<xsl:apply-templates/>
													</fo:inline>
												</xsl:for-each>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-start="4pt">
											<fo:block>
												<fo:inline font-size="8pt" font-weight="normal">(Agency Name, POC Name and Phone Number)</fo:inline>
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
			<fo:block>
				<fo:leader leader-pattern="space"/>
			</fo:block>
			<fo:table border-style="solid" border-color="black" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column column-width="proportional-column-width(53)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-column column-width="proportional-column-width(16)"/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">I. Total Direct and Indirect Costs</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Non-Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">*Total (Fed + Non-Fed) (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">Total Direct and Indirect Costs (G + H)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:TotalCosts/RR_FedNonFedBudget:FederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:TotalCosts/RR_FedNonFedBudget:NonFederalSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:TotalCosts/RR_FedNonFedBudget:TotalFedNonFedSummary">
									<fo:inline font-size="8pt" font-weight="bold">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block>
				<fo:leader leader-pattern="space"/>
			</fo:block>
			<fo:table border-style="solid" border-color="black" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column column-width="proportional-column-width(65)"/>
				<fo:table-column column-width="proportional-column-width(15)"/>
				<fo:table-column column-width="proportional-column-width(35)"/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">J. Fee</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">Federal (&#36;)</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">&#160; </fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">&#160; </fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<xsl:for-each select="RR_FedNonFedBudget:Fee">
									<fo:inline font-size="8pt">
										<xsl:value-of select="format-number(., '#,##0.00')"/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
							<fo:block>
								<fo:inline font-weight="bold" font-size="8pt">&#160; </fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block>
				<fo:leader leader-pattern="space"/>
			</fo:block>
			<!--
         <fo:table border-style="solid" border-color="black" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
            <fo:table-column/>
            <fo:table-body>
               <fo:table-row>
                  <fo:table-cell display-align="before" height="36pt" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt">
                     <fo:block>
                        <fo:inline font-size="8pt" font-weight="bold">K. Subprojects/Subaward Budgets Attachment(s)</fo:inline>
                        <xsl:for-each select="RR_FedNonFedBudget:SubawardSubprojectBudgetAttachment/att:AttachedFile">
                           <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                              <fo:table-column column-width="proportional-column-width(1)"/>
                              <fo:table-column column-width="proportional-column-width(1)"/>
                              <fo:table-body>
                                 <fo:table-row>
                                    <fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
                                       <fo:block font-size="8pt">File Name: <xsl:for-each select="att:FileName">
                                             <xsl:apply-templates/>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
                                       <fo:block font-size="8pt">Mime Type: <xsl:for-each select="att:MimeType">
                                             <xsl:apply-templates/>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                              </fo:table-body>
                           </fo:table>
                        </xsl:for-each>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
            </fo:table-body>
         </fo:table>
         <fo:block>
            <fo:leader leader-pattern="space"/>
         </fo:block>
-->
			<fo:table border-style="solid" border-color="black" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
				<fo:table-column/>
				<fo:table-column/>
				<fo:table-column/>
				<!-- Nikisha: added table-column -->
				<fo:table-column/>
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">K. * Budget Justification</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt">File Name: </fo:inline>
								<xsl:for-each select="../RR_FedNonFedBudget:BudgetYear1/RR_FedNonFedBudget:BudgetJustificationAttachment/att:FileName">
									<fo:inline font-size="8pt">
										<xsl:apply-templates/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt">Mime Type: </fo:inline>
								<xsl:for-each select="../RR_FedNonFedBudget:BudgetYear1/RR_FedNonFedBudget:BudgetJustificationAttachment/att:MimeType">
									<fo:inline font-size="8pt">
										<xsl:apply-templates/>
									</fo:inline>
								</xsl:for-each>
							</fo:block>
						</fo:table-cell>
						<!-- Nikisha or Matt ? added this cell -->
						<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
							<fo:block>
								<fo:inline font-size="8pt" font-weight="bold">(Only attach one file.)</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell>
							<fo:block/>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:block font-size="8pt">RESEARCH &amp; RELATED Budget {F-K} (Total Fed + Non-Fed)</fo:block>
		</fo:block>
	</xsl:template>
	<!--===========================End Single Year Template===========-->
	<!--========================== date template =================-->
	<xsl:template name="formatDate">
		<xsl:param name="value"/>
		<xsl:value-of select="format-number(substring($value,6,2), '00')"/>
		<xsl:text>-</xsl:text>
		<xsl:value-of select="format-number(substring($value,9,2), '00')"/>
		<xsl:text>-</xsl:text>
		<xsl:value-of select="format-number(substring($value,1,4), '0000')"/>
	</xsl:template>
</xsl:stylesheet>
