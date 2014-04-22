<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xmlns:SF424C_2_0="http://apply.grants.gov/forms/SF424C_2_0-V2.0" xmlns:codes="http://apply.grants.gov/system/UniversalCodes-V2.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"  xmlns:xs="http://www.w3.org/2001/XMLSchema"  > 
 
	<xsl:template match="SF424C_2_0:SF424C_2_0"> 
		<fo:root> 
 
			<fo:layout-master-set> 
				<fo:simple-page-master master-name="first" 
					page-height="8.5in" page-width="11in" margin-left="0.5in" 
					margin-right="0.5in"> 
					<fo:region-body margin-top="1.0in" margin-bottom="0.6in" /> 
					<fo:region-before region-name="region-before-first" 
						extent="0.5in" /> 
					<fo:region-after region-name="region-after-all" 
						extent=".6in" />
				</fo:simple-page-master>
				<fo:simple-page-master master-name="rest"
					page-height="8.5in" page-width="11in" margin-left="0.5in"
					margin-right="0.5in">
					<fo:region-body margin-top="1.0in" margin-bottom="0.6in" />
					<fo:region-after region-name="region-after-all"
						extent=".6in" />
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
 
			<fo:page-sequence master-reference="all-pages" 
				initial-page-number="1" format="1">
				<!-- ===================================== -->
				<!-- Header for Page 1 -->
				<!-- ===================================== -->
				<fo:static-content flow-name="region-before-first">
					<fo:table width="100%" space-before.optimum="0pt"
						space-after.optimum="0pt" table-layout="fixed">
						<fo:table-column column-width="proportional-column-width(1)" />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en"
									line-height="9pt" padding-start="0pt" padding-end="0pt"
									padding-before="30pt" padding-after="1pt" display-align="before"
									text-align="right" border-style="solid" border-width="0pt"
									border-color="white">
									<fo:block>
										<fo:inline font-size="6px">OMB Number:
											4040-0008</fo:inline>
									</fo:block>
									<fo:block>
										<fo:inline font-size="6px">Expiration
											Date: 06/30/2014</fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>

				<fo:flow flow-name="xsl-region-body" > 

				
						<fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
							<fo:table-column column-width="proportional-column-width(100)"/>
							<fo:table-column column-width="135pt"/>
							<fo:table-column column-width="135pt"/>
							<fo:table-column column-width="135pt"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell number-columns-spanned="4" width="100%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
										<fo:block>
											<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
												<fo:block text-align="center">
													<fo:inline font-size="12px" font-weight="bold">BUDGET INFORMATION - Construction Programs</fo:inline>
												</fo:block>
											</fo:block>
											<fo:inline font-size="8px">NOTE: </fo:inline>
											<fo:inline font-size="8px" font-style="italic">Certain Federal assistance programs require additional computations to arrive at the Federal share of project costs eligible for participation. If such is the case, you will be notified.</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								
								<fo:table-row>
									<fo:table-cell font-size="9px" padding-after="0pt" text-align="center" width="100%" padding-start="3pt" padding-end="3pt" padding-before="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
										<fo:block>COST CLASSIFICATION</fo:block>
									</fo:table-cell>
									
                                    <fo:table-cell font-size="9px" padding-after="0pt" text-align="center" width="135pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
										<fo:block>a. Total Cost</fo:block>
									</fo:table-cell>
									
                                    <fo:table-cell font-size="9px" padding-after="0pt" text-align="center" width="135pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
										<fo:block>b. Costs Not Allowable for Participation</fo:block>
									</fo:table-cell>
									
                                    <fo:table-cell font-size="9px" padding-after="0pt" text-align="center" width="135pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
										<fo:block>
											<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
												<fo:block padding-after="0pt" text-align="center">c. Total Allowable Costs</fo:block>
											</fo:block>(Columns a-b)</fo:block>
									</fo:table-cell>
								</fo:table-row>
					
                    <xsl:for-each select="SF424C_2_0:ProjectCosts">
							<xsl:for-each select="SF424C_2_0:AdministrationCost|SF424C_2_0:LandCost|SF424C_2_0:RelocationCost|SF424C_2_0:ArchitecturalCost|SF424C_2_0:Miscellaneous|SF424C_2_0:Contingencies|SF424C_2_0:ProgramIncome|SF424C_2_0:OtherArchitecturalCost|SF424C_2_0:InspectionFeesCost|SF424C_2_0:SiteWorkCost|SF424C_2_0:DemolitionCost|SF424C_2_0:ConstructionCost|SF424C_2_0:EquipmentCost|SF424C_2_0:CostSubtotalBeforeContingencies|SF424C_2_0:CostSubtotalAfterContingencies|SF424C_2_0:TotalProjectCosts" >

							<fo:table-row>
									<fo:table-cell font-size="9px" padding-after="3pt" text-align="left" width="100%" padding-start="3pt" padding-end="3pt" padding-before="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
								<!--	-->		
											<xsl:apply-templates  select="." />
                                	</fo:table-cell>
																	
                                    <xsl:apply-templates select="SF424C_2_0:BudgetEstimatedCostAmount" />
                                    <xsl:apply-templates select="SF424C_2_0:BudgetNonAllowableCostAmount" />
                                    <xsl:apply-templates select="SF424C_2_0:BudgetTotalAllowableCostAmount" />

								</fo:table-row>
                                     </xsl:for-each> 
                                     </xsl:for-each> 
								
                                <fo:table-row>
									<fo:table-cell font-size="9px" text-align="center" number-columns-spanned="4" width="100%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
										<fo:block>FEDERAL FUNDING</fo:block>
									</fo:table-cell>
								</fo:table-row>
								
                                <fo:table-row>
									<fo:table-cell font-size="9px" number-columns-spanned="3" width="100%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
										<fo:block>
											<fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
												<fo:table-column />
												<fo:table-column />
												<fo:table-column />
												<fo:table-body>
													<fo:table-row>
														<fo:table-cell font-size="9px" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="before" width="15px" text-align="start">
															<fo:block>17. </fo:block>
														</fo:table-cell>
														
                                                        <fo:table-cell font-size="9px" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start">
															 
																	<fo:block padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt">Federal assistance requested, calculate as follows:</fo:block>
																<fo:block>(Consult Federal agency for Federal percentage share.)</fo:block> 
                                                                <fo:block>Enter the resulting Federal share.</fo:block>
														</fo:table-cell>
														
                                                        <fo:table-cell font-size="9px" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="5pt" display-align="center" text-align="start">
															<fo:block>Enter eligible costs from line 16c Multiply X &#160;&#160;&#160;&#160;&#160;<xsl:for-each select="SF424C_2_0:FederalFundingPercentageShareValue">
																	<fo:inline font-size="9px" text-align="right">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>%</fo:block>
														</fo:table-cell>
													</fo:table-row>
												</fo:table-body>
											</fo:table>
										</fo:block>
									</fo:table-cell>
									
                                    <fo:table-cell font-size="9px" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
										<fo:block>
											<fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
												<fo:table-column/>
												<fo:table-column/>
												<fo:table-body>
													<fo:table-row>
														<fo:table-cell font-size="9px" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="top" text-align="start">
															<fo:block>&#160;</fo:block>
														</fo:table-cell>
														<fo:table-cell font-size="9px" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center">
															<fo:block>$&#160;
																<xsl:for-each select="SF424C_2_0:FederalFundingShareValue">
																	<xsl:value-of select="format-number(., '###,##0.00')"/>
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
				</fo:flow> 
			</fo:page-sequence> 
		</fo:root> 
	</xsl:template> 
 
 
	<!-- ============================================= --> 
	<!-- FORMAT DATE --> 
	<!-- Writes XSD:date style text into to mm-dd-yyyy --> 
	<!-- ============================================= --> 
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
 
	<!-- ============================================= --> 
	<!-- PRINT ADDRESS --> 
	<!-- Prints address in the usual format --> 
	<!-- ============================================= --> 
	<xsl:template name="printAddress"> 
		<xsl:param name="street1" /> 
		<xsl:param name="street2" /> 
		<xsl:param name="city" /> 
		<xsl:param name="state" /> 
		<xsl:param name="province" /> 
		<xsl:param name="country" /> 
		<xsl:param name="zip" /> 
		<fo:block line-height="9pt"> 
			<fo:block font-size="9px"> 
				<xsl:value-of select="$street1" /> 
			</fo:block> 
			<fo:block font-size="9px"> 
				<xsl:value-of select="$street2" /> 
			</fo:block> 
			<fo:block font-size="9px"> 
				<xsl:value-of select="$city" /> 
				,&#160; 
				<xsl:value-of select="$state" /> 
				&#160; 
				<xsl:value-of select="$province" /> 
				&#160; 
				<xsl:value-of select="$country" /> 
				<xsl:value-of select="$zip" /> 
				&#160; 
			</fo:block> 
		</fo:block> 
	</xsl:template> 
 
	<!-- ============================================= --> 
	<!-- PRINT ADDRESS FORMATTED For Contact --> 
	<!-- Prints address in the format used in XFD --> 
	<!-- ============================================= --> 
	<xsl:template name="printAddressFormattedForContact"> 
		<xsl:param name="position" /> 
		<xsl:param name="street1" /> 
		<xsl:param name="street2" /> 
		<xsl:param name="city" /> 
		<xsl:param name="county" /> 
		<xsl:param name="state" /> 
		<xsl:param name="province" /> 
		<xsl:param name="country" /> 
		<xsl:param name="zip" /> 
		<fo:table-row> 
			<fo:table-cell hyphenate="true" language="en" 
				padding-start="0pt" padding-end="0pt" padding-before="1pt" 
				padding-after="1pt" display-align="before" text-align="start" 
				border-style="solid" border-width="0pt" border-color="white"> 
				<fo:block font-size="9px"> 
					Position/Title: 
					<xsl:value-of select="$position" /> 
				</fo:block> 
			</fo:table-cell> 
		</fo:table-row> 
		<fo:table-row> 
			<fo:table-cell hyphenate="true" language="en" 
				padding-start="0pt" padding-end="0pt" padding-before="1pt" 
				padding-after="1pt" display-align="before" text-align="start" 
				border-style="solid" border-width="0pt" border-color="white"> 
				<fo:block font-size="9px"> 
					Street1*: 
					<xsl:value-of select="$street1" /> 
				</fo:block> 
			</fo:table-cell> 
			<fo:table-cell hyphenate="true" language="en" 
				padding-start="0pt" padding-end="0pt" padding-before="1pt" 
				padding-after="1pt" display-align="before" text-align="start" 
				border-style="solid" border-width="0pt" border-color="white"> 
				<fo:block font-size="9px"> 
					Street2: 
					<xsl:value-of select="$street2" /> 
				</fo:block> 
			</fo:table-cell> 
		</fo:table-row> 
		<fo:table-row> 
			<fo:table-cell hyphenate="true" language="en" 
				number-columns-spanned="2" padding-start="0pt" padding-end="0pt" 
				padding-before="1pt" padding-after="1pt" display-align="before" 
				text-align="start" border-style="solid" border-width="0pt" 
				border-color="white"> 
				<fo:table width="100%" space-before.optimum="0pt" 
					space-after.optimum="0pt" table-layout="fixed"> 
					<fo:table-column column-width="proportional-column-width(2)" /> 
					<fo:table-column column-width="proportional-column-width(2)" /> 
					<fo:table-column column-width="proportional-column-width(1)" /> 
					<fo:table-column column-width="proportional-column-width(1)" /> 
					<fo:table-body> 
						<fo:table-row> 
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:block font-size="9px"> 
									City*: 
									<xsl:value-of select="$city" /> 
								</fo:block> 
							</fo:table-cell> 
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:block font-size="9px"> 
									County: 
									<xsl:value-of select="$county" /> 
								</fo:block> 
							</fo:table-cell> 
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:block font-size="9px"> 
									State*: 
									<xsl:value-of select="$state" /> 
								</fo:block> 
							</fo:table-cell> 
						</fo:table-row> 
					</fo:table-body> 
				</fo:table> 
			</fo:table-cell> 
		</fo:table-row> 
		<fo:table-row> 
			<fo:table-cell hyphenate="true" language="en" 
				number-columns-spanned="2" padding-start="0pt" padding-end="0pt" 
				padding-before="1pt" padding-after="1pt" display-align="before" 
				text-align="start" border-style="solid" border-width="0pt" 
				border-color="white"> 
				<fo:table width="100%" space-before.optimum="0pt" 
					space-after.optimum="0pt" table-layout="fixed"> 
					<fo:table-column column-width="proportional-column-width(2)" /> 
					<fo:table-column column-width="proportional-column-width(2)" /> 
					<fo:table-column column-width="proportional-column-width(1)" /> 
					<fo:table-column column-width="proportional-column-width(1)" /> 
					<fo:table-body> 
						<fo:table-row> 
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:block font-size="9px"> 
									Province: 
									<xsl:value-of select="$province" /> 
								</fo:block> 
							</fo:table-cell> 
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:block font-size="9px"> 
									Country*: 
									<xsl:value-of select="$country" /> 
								</fo:block> 
							</fo:table-cell> 
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:block font-size="9px"> 
									ZIP / Postal Code*: 
									<xsl:value-of select="$zip" /> 
								</fo:block> 
							</fo:table-cell> 
						</fo:table-row> 
					</fo:table-body> 
				</fo:table> 
			</fo:table-cell> 
		</fo:table-row> 
	</xsl:template> 
 
	<!-- ============================================= --> 
	<!-- PRINT ADDRESS FORMATTED --> 
	<!-- Prints address in the format used in XFD --> 
	<!-- ============================================= --> 
	<xsl:template name="printAddressFormatted"> 
		<xsl:param name="department" /> 
		<xsl:param name="division" /> 
		<xsl:param name="street1" /> 
		<xsl:param name="street2" /> 
		<xsl:param name="city" /> 
		<xsl:param name="county" /> 
		<xsl:param name="state" /> 
		<xsl:param name="province" /> 
		<xsl:param name="country" /> 
		<xsl:param name="zip" /> 
		<fo:table-row> 
			<fo:table-cell hyphenate="true" language="en" 
				padding-start="0pt" padding-end="0pt" padding-before="1pt" 
				padding-after="1pt" display-align="before" text-align="start" 
				border-style="solid" border-width="0pt" border-color="white"> 
				<fo:block font-size="9px"> 
					Department: 
					<xsl:value-of select="$department" /> 
				</fo:block> 
			</fo:table-cell> 
			<fo:table-cell hyphenate="true" language="en" 
				padding-start="0pt" padding-end="0pt" padding-before="1pt" 
				padding-after="1pt" display-align="before" text-align="start" 
				border-style="solid" border-width="0pt" border-color="white"> 
				<fo:block font-size="9px"> 
					Division: 
					<xsl:value-of select="$division" /> 
				</fo:block> 
			</fo:table-cell> 
		</fo:table-row> 
		<fo:table-row> 
			<fo:table-cell hyphenate="true" language="en" 
				padding-start="0pt" padding-end="0pt" padding-before="1pt" 
				padding-after="1pt" display-align="before" text-align="start" 
				border-style="solid" border-width="0pt" border-color="white"> 
				<fo:block font-size="9px"> 
					Street1*: 
					<xsl:value-of select="$street1" /> 
				</fo:block> 
			</fo:table-cell> 
			<fo:table-cell hyphenate="true" language="en" 
				padding-start="0pt" padding-end="0pt" padding-before="1pt" 
				padding-after="1pt" display-align="before" text-align="start" 
				border-style="solid" border-width="0pt" border-color="white"> 
				<fo:block font-size="9px"> 
					Street2: 
					<xsl:value-of select="$street2" /> 
				</fo:block> 
			</fo:table-cell> 
		</fo:table-row> 
		<fo:table-row> 
			<fo:table-cell hyphenate="true" language="en" 
				number-columns-spanned="2" padding-start="0pt" padding-end="0pt" 
				padding-before="1pt" padding-after="1pt" display-align="before" 
				text-align="start" border-style="solid" border-width="0pt" 
				border-color="white"> 
				<fo:table width="100%" space-before.optimum="0pt" 
					space-after.optimum="0pt" table-layout="fixed"> 
					<fo:table-column column-width="proportional-column-width(2)" /> 
					<fo:table-column column-width="proportional-column-width(2)" /> 
					<fo:table-column column-width="proportional-column-width(1)" /> 
					<fo:table-column column-width="proportional-column-width(1)" /> 
					<fo:table-body> 
						<fo:table-row> 
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:block font-size="9px"> 
									City*: 
									<xsl:value-of select="$city" /> 
								</fo:block> 
							</fo:table-cell> 
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:block font-size="9px"> 
									County: 
									<xsl:value-of select="$county" /> 
								</fo:block> 
							</fo:table-cell> 
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:block font-size="9px"> 
									State*: 
									<xsl:value-of select="$state" /> 
								</fo:block> 
							</fo:table-cell> 
						</fo:table-row> 
					</fo:table-body> 
				</fo:table> 
			</fo:table-cell> 
		</fo:table-row> 
		<fo:table-row> 
			<fo:table-cell hyphenate="true" language="en" 
				number-columns-spanned="2" padding-start="0pt" padding-end="0pt" 
				padding-before="1pt" padding-after="1pt" display-align="before" 
				text-align="start" border-style="solid" border-width="0pt" 
				border-color="white"> 
				<fo:table width="100%" space-before.optimum="0pt" 
					space-after.optimum="0pt" table-layout="fixed"> 
					<fo:table-column column-width="proportional-column-width(2)" /> 
					<fo:table-column column-width="proportional-column-width(2)" /> 
					<fo:table-column column-width="proportional-column-width(1)" /> 
					<fo:table-column column-width="proportional-column-width(1)" /> 
					<fo:table-body> 
						<fo:table-row> 
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:block font-size="9px"> 
									Province: 
									<xsl:value-of select="$province" /> 
								</fo:block> 
							</fo:table-cell> 
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:block font-size="9px"> 
									Country*: 
									<xsl:value-of select="$country" /> 
								</fo:block> 
							</fo:table-cell> 
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:block font-size="9px"> 
									ZIP / Postal Code*: 
									<xsl:value-of select="$zip" /> 
								</fo:block> 
							</fo:table-cell> 
						</fo:table-row> 
					</fo:table-body> 
				</fo:table> 
			</fo:table-cell> 
		</fo:table-row> 
	</xsl:template> 
 
	<!-- ============================================= --> 
	<!-- PRINT NAME --> 
	<!-- Prints name in the usual format --> 
	<!-- ============================================= --> 
	<xsl:template name="printName"> 
		<xsl:param name="prefix" /> 
		<xsl:param name="first" /> 
		<xsl:param name="middle" /> 
		<xsl:param name="last" /> 
		<xsl:param name="suffix" /> 
		<xsl:if test="$prefix != ''"> 
			<xsl:value-of select="$prefix" /> 
			&#160; 
		</xsl:if> 
		<xsl:value-of select="$first" /> 
		&#160; 
		<xsl:if test="$middle != ''"> 
			<xsl:value-of select="$middle" /> 
			&#160; 
		</xsl:if> 
		<xsl:value-of select="$last" /> 
		&#160; 
		<xsl:if test="$suffix != ''"> 
			<xsl:value-of select="$suffix" /> 
			&#160; 
		</xsl:if> 
	</xsl:template> 
 
	<!-- ============================================= --> 
	<!-- PRINT NAME FORMATTED --> 
	<!-- Prints name in the same format as the XFD --> 
	<!-- ============================================= --> 
	<xsl:template name="printNameFormatted"> 
		<xsl:param name="prefix" /> 
		<xsl:param name="first" /> 
		<xsl:param name="middle" /> 
		<xsl:param name="last" /> 
		<xsl:param name="suffix" /> 
		<fo:table width="100%" space-before.optimum="0pt" 
			space-after.optimum="0pt" table-layout="fixed"> 
			<fo:table-column column-width="proportional-column-width(1)" /> 
			<fo:table-column column-width="proportional-column-width(2)" /> 
			<fo:table-column column-width="proportional-column-width(2)" /> 
			<fo:table-column column-width="proportional-column-width(2)" /> 
			<fo:table-column column-width="proportional-column-width(1)"  /> 
			<fo:table-body> 
				<fo:table-row> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px">Prefix:&#160;
                        <fo:inline font-family="Georgia"> 
							<xsl:value-of select="$prefix" /> 
						</fo:inline> 
                        </fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px">First Name*:&#160;
                        	<fo:inline font-family="Georgia"> 
								<xsl:value-of select="$first" /> 
                        	</fo:inline>	
                        </fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px">Middle Name:&#160;
                        <fo:inline font-family="Georgia"> 
							<xsl:value-of select="$middle" /> 
                        </fo:inline>	
                        </fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px">Last Name*:&#160;
                        <fo:inline font-family="Georgia"> 
							<xsl:value-of select="$last" /> 						
                        </fo:inline>	
                        </fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px">Suffix:&#160;
                        <fo:inline font-family="Georgia"> 
							<xsl:value-of select="$suffix" /> 
                        </fo:inline>	
                        </fo:block> 
					</fo:table-cell> 
				</fo:table-row> 
			</fo:table-body> 
		</fo:table> 
	</xsl:template> 
 
	<!-- ============================================= --> 
	<!-- PRINT CONTACT INFO --> 
	<!-- Prints phone, fax, and email information. --> 
	<!-- ============================================= --> 
	<xsl:template name="printContactInfo"> 
		<xsl:param name="phone" /> 
		<xsl:param name="fax" /> 
		<xsl:param name="email" /> 
		<fo:table width="100%" space-before.optimum="0pt" 
			space-after.optimum="0pt" table-layout="fixed"> 
			<fo:table-column column-width="proportional-column-width(1)" /> 
			<fo:table-column column-width="proportional-column-width(1)" /> 
			<fo:table-column column-width="proportional-column-width(1)" /> 
			<fo:table-body> 
				<fo:table-row> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px"> 
							Phone Number*: 
							<xsl:value-of select="$phone" /> 
						</fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px"> 
							Fax Number: 
							<xsl:value-of select="$fax" /> 
						</fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px"> 
							Email*: 
							<xsl:value-of select="$email" /> 
						</fo:block> 
					</fo:table-cell> 
				</fo:table-row> 
			</fo:table-body> 
		</fo:table> 
	</xsl:template> 
	<!-- ============================================= --> 
	<!-- PRINT CONTACT INFO WITHOUT * --> 
	<!-- Prints phone, fax, and email information. --> 
	<!-- ============================================= --> 
	<xsl:template name="printContactInfo2"> 
		<xsl:param name="phone" /> 
		<xsl:param name="fax" /> 
		<xsl:param name="email" /> 
		<fo:table width="100%" space-before.optimum="0pt" 
			space-after.optimum="0pt" table-layout="fixed"> 
			<fo:table-column column-width="proportional-column-width(1)" /> 
			<fo:table-column column-width="proportional-column-width(1)" /> 
			<fo:table-column column-width="proportional-column-width(1)" /> 
			<fo:table-body> 
				<fo:table-row> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px"> 
							Phone Number*: 
							<xsl:value-of select="$phone" /> 
						</fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px"> 
							Fax Number: 
							<xsl:value-of select="$fax" /> 
						</fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px"> 
							Email: 
							<xsl:value-of select="$email" /> 
						</fo:block> 
					</fo:table-cell> 
				</fo:table-row> 
			</fo:table-body> 
		</fo:table> 
	</xsl:template> 
 
 
	<!-- ============================================= --> 
	<!-- ADD BLANK LINES --> 
	<!-- Adds blank lines to fill up page space. --> 
	<!-- ============================================= --> 
	<xsl:template name="addBlankLines"> 
		<xsl:param name="numLines" /> 
		<xsl:if test="$numLines &gt; 0"> 
			<fo:block> 
				<fo:leader leader-pattern="space" /> 
			</fo:block> 
			<xsl:call-template name="addBlankLines"> 
				<xsl:with-param name="numLines" select="$numLines - 1" /> 
			</xsl:call-template> 
		</xsl:if> 
	</xsl:template> 
 
	<!-- ============================================= --> 
	<!-- CHECKBOX --> 
	<!-- Print out a checkbox according to value. --> 
	<!-- ============================================= --> 
	<xsl:template name="checkbox"> 
		<xsl:param name="value" /> 
		<xsl:param name="check"> 
			Y: Yes 
		</xsl:param> 
		<xsl:choose> 
			<xsl:when test="$value = $check"> 
				<fo:inline font-family="ZapfDingbats" font-size="9px">&#x25cf; 
				</fo:inline> 
			</xsl:when> 
			<xsl:otherwise> 
				<fo:inline font-family="ZapfDingbats" font-size="9px">&#x274d; 
				</fo:inline> 
			</xsl:otherwise> 
		</xsl:choose> 
	</xsl:template> 
 
	<!-- ============================================= --> 
	<!-- YES NO RADIO --> 
	<!-- Print out a radio button according to value. --> 
	<!-- ============================================= --> 
	<xsl:template name="yes_no_radio"> 
		<xsl:param name="value" /> 
		<xsl:choose> 
			<xsl:when test="$value = 'N: No'"> 
				<fo:inline font-family="ZapfDingbats" font-size="9px">&#x274d; 
				</fo:inline> 
				<fo:inline font-size="9px">&#160;Yes&#160;&#160;</fo:inline> 
				<fo:inline font-family="ZapfDingbats" font-size="9px">&#x25cf; 
				</fo:inline> 
				<fo:inline font-size="9px">&#160;No&#160;&#160;</fo:inline> 
			</xsl:when> 
			<xsl:when test="$value = 'Y: Yes'"> 
				<fo:inline font-family="ZapfDingbats" font-size="9px">&#x25cf; 
				</fo:inline> 
				<fo:inline font-size="9px">&#160;Yes&#160;&#160;</fo:inline> 
				<fo:inline font-family="ZapfDingbats" font-size="9px">&#x274d; 
				</fo:inline> 
				<fo:inline font-size="9px">&#160;No&#160;&#160;</fo:inline> 
			</xsl:when> 
			<!-- Use this if production box doesn't have ZapfDingbats font. <xsl:when  
				test="$value = 'No'"> <fo:inline font-family="Courier" font-size="9px">&#x2022;</fo:inline>  
				<fo:inline font-size="9px">&#160;No&#160;&#160;</fo:inline> <fo:inline font-family="Courier"  
				font-size="9px">&#160;</fo:inline> <fo:inline font-size="9px">&#160;Yes</fo:inline>  
				</xsl:when> <xsl:when test="$value = 'Yes'"> <fo:inline font-family="Courier"  
				font-size="9px">&#160;</fo:inline> <fo:inline font-size="9px">&#160;No&#160;&#160;</fo:inline>  
				<fo:inline font-family="Courier" font-size="9px">&#x2022;</fo:inline> <fo:inline  
				font-size="9px">&#160;Yes</fo:inline> </xsl:when> --> 
		</xsl:choose> 
	</xsl:template> 
 
 
 <xsl:template match="SF424C_2_0:AdministrationCost">
  <fo:block font-size="9px" font-family="Helvetica">
  1. Administrative and legal expenses
  </fo:block>
</xsl:template>
<xsl:template match="SF424C_2_0:LandCost">
  <fo:block font-size="9px" font-family="Helvetica">
  2. Land, structures, rights-of-way, appraisals, etc.
  </fo:block>
</xsl:template>
<xsl:template match="SF424C_2_0:RelocationCost">
  <fo:block font-size="9px" font-family="Helvetica">
  3. Relocation expenses and payments
  </fo:block>
</xsl:template>
<xsl:template match="SF424C_2_0:ArchitecturalCost">
  <fo:block font-size="9px" font-family="Helvetica">
  4. Architectural and engineering fees
  </fo:block>
</xsl:template>
<xsl:template match="SF424C_2_0:OtherArchitecturalCost">
  <fo:block font-size="9px" font-family="Helvetica">
  5. Other architectural and engineering fees
  </fo:block>
</xsl:template>
<xsl:template match="SF424C_2_0:InspectionFeesCost">
  <fo:block font-size="9px" font-family="Helvetica">
  6. Project inspection fees
  </fo:block>
</xsl:template>
<xsl:template match="SF424C_2_0:SiteWorkCost">
  <fo:block font-size="9px" font-family="Helvetica">
  7. Site work
  </fo:block>
</xsl:template>
<xsl:template match="SF424C_2_0:DemolitionCost">
  <fo:block font-size="9px" font-family="Helvetica">
  8. Demolition and removal
  </fo:block>
</xsl:template>
<xsl:template match="SF424C_2_0:ConstructionCost">
  <fo:block font-size="9px" font-family="Helvetica">
  9. Construction
  </fo:block>
</xsl:template>
<xsl:template match="SF424C_2_0:EquipmentCost">
  <fo:block font-size="9px" font-family="Helvetica">
  10. Equipment
  </fo:block>
</xsl:template>
<xsl:template match="SF424C_2_0:Miscellaneous">
  <fo:block font-size="9px" font-family="Helvetica">
  11. Miscellaneous
  </fo:block>
</xsl:template>
<xsl:template match="SF424C_2_0:Contingencies">
  <fo:block font-size="9px" font-family="Helvetica">
  13. Contingencies
  </fo:block>
</xsl:template>
<xsl:template match="SF424C_2_0:ProgramIncome">
  <fo:block font-size="9px" font-family="Helvetica">
  15. Project (program) income
  </fo:block>
</xsl:template>
<xsl:template match="SF424C_2_0:CostSubtotalBeforeContingencies">
  <fo:block font-size="9px" font-family="Helvetica">
  12. SUBTOTAL (sum of lines 1-11)
  </fo:block>
</xsl:template>
<xsl:template match="SF424C_2_0:CostSubtotalAfterContingencies">
  <fo:block font-size="9px" font-family="Helvetica">
  14. SUBTOTAL
  </fo:block>
</xsl:template>
<xsl:template match="SF424C_2_0:TotalProjectCosts">
  <fo:block font-size="9px" font-family="Helvetica">
  16. TOTAL PROJECT COSTS (subtract #15 from #14)
  </fo:block>
</xsl:template>

<!-- -->
 <xsl:template match="SF424C_2_0:BudgetEstimatedCostAmount|SF424C_2_0:BudgetNonAllowableCostAmount|SF424C_2_0:BudgetTotalAllowableCostAmount">
	 <fo:table-cell font-size="9px" padding-after="0pt" text-align="right" width="100%" padding-start="3pt" padding-end="3pt" padding-before="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
	<!--	-->																
		<fo:block font-size="9px" font-family="Helvetica">
			$ <xsl:value-of select="."/>
		</fo:block>
  </fo:table-cell>  <!-- -->
</xsl:template>
<!-- -->
</xsl:stylesheet> 

