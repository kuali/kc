<?xml version="1.0" encoding="UTF-8"?>
<!-- $Revision:   1.0  $ -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:PHS398_ModularBudget_1_2="http://apply.grants.gov/forms/PHS398_ModularBudget_1_2-V1.2">
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.2in" margin-right="0.2in">
					<fo:region-body margin-top="0.2in" margin-bottom="0.4in"/>
					<fo:region-after extent="0.4in"/>
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="default-page" format="1" initial-page-number="1">
				<!--fo:static-content flow-name="xsl-region-after">
					<fo:block>
						<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
						</fo:inline>
					</fo:block>
				</fo:static-content-->

				<fo:flow flow-name="xsl-region-body">

					
					<xsl:for-each select="PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:Periods">
					
					<fo:block-container left="10" top="10" width="550" height="400">
					
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10px" top="43px" width="551px" height="0.5px">
						<fo:block/>
					</fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10px" top="400px" width="551px" height="0.5px">
						<fo:block/>
					</fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10px" top="43px" width="0.5px" height="357px">
						<fo:block/>
					</fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="561px" top="43px" width="0.5px" height="357px">
						<fo:block/>
					</fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10px" top="62px" width="551px" height="0.5px">
						<fo:block/>
					</fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10px" top="80px" width="551px" height="0.5px">
						<fo:block/>
					</fo:block-container>
					<!--fo:block-container background-color="red" border-style="none" position="absolute" left="19px" top="50px" width="0.5px" height="32px">
						<fo:block/>
					</fo:block-container>
					<fo:block-container background-color="red" border-style="none" position="absolute" left="555px" top="50px" width="0.5px" height="32px">
						<fo:block/>
					</fo:block-container-->
					
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10px" top="148px" width="551px" height="0.5px">
						<fo:block/>
					</fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10px" top="372px" width="551px" height="0.5px">
						<fo:block/>
					</fo:block-container>
					
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="220px" hyphenate="true" language="en" top="66px" height="13px" width="56px">
						<!--fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/-->
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:BudgetPeriodStartDate) or PHS398_ModularBudget_1_2:BudgetPeriodStartDate = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="PHS398_ModularBudget_1_2:BudgetPeriodStartDate"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="354.54545454545456px" hyphenate="true" language="en" top="66px" height="13.333333333333334px" width="56.969696969696976px">
						<!--fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/-->
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:BudgetPeriodEndDate) or PHS398_ModularBudget_1_2:BudgetPeriodEndDate = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="PHS398_ModularBudget_1_2:BudgetPeriodEndDate"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="467.2727272727273px" hyphenate="true" language="en" top="116.36363636363637px" height="13.333333333333334px" width="89.0909090909091px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:DirectCost/PHS398_ModularBudget_1_2:ConsortiumFandA) or PHS398_ModularBudget_1_2:DirectCost/PHS398_ModularBudget_1_2:ConsortiumFandA = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(PHS398_ModularBudget_1_2:DirectCost/PHS398_ModularBudget_1_2:ConsortiumFandA, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="467.2727272727273px" hyphenate="true" language="en" top="131.51515151515153px" height="13.333333333333334px" width="89.0909090909091px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:DirectCost/PHS398_ModularBudget_1_2:TotalFundsRequestedDirectCosts) or PHS398_ModularBudget_1_2:DirectCost/PHS398_ModularBudget_1_2:TotalFundsRequestedDirectCosts = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(PHS398_ModularBudget_1_2:DirectCost/PHS398_ModularBudget_1_2:TotalFundsRequestedDirectCosts, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="32.121212121212125px" hyphenate="true" language="en" top="190.3030303030303px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostTypeDescription) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostTypeDescription = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostTypeDescription"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="358.7878787878788px" hyphenate="true" language="en" top="190.3030303030303px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostRate) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostRate = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostRate, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="398.1818181818182px" hyphenate="true" language="en" top="190.3030303030303px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostBase) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostBase = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostBase, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="475.7575757575758px" hyphenate="true" language="en" top="190.3030303030303px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostFundsRequested) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostFundsRequested = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems/PHS398_ModularBudget_1_2:IndirectCostFundsRequested, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="262.42424242424244px" hyphenate="true" language="en" top="296.3636363636364px" height="52.121212121212125px" width="294.54545454545456px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px" linefeed-treatment="preserve">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:CognizantFederalAgency) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:CognizantFederalAgency = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:CognizantFederalAgency"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="156.96969696969697px" hyphenate="true" language="en" top="353.93939393939394px" height="13.333333333333334px" width="56.969696969696976px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostAgreementDate) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostAgreementDate = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostAgreementDate"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="476.969696969697px" hyphenate="true" language="en" top="355.1515151515152px" height="13.333333333333334px" width="80px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:TotalFundsRequestedIndirectCost) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:TotalFundsRequestedIndirectCost = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:TotalFundsRequestedIndirectCost, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="475.7575757575758px" hyphenate="true" language="en" top="380px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:TotalFundsRequestedDirectIndirectCosts) or PHS398_ModularBudget_1_2:TotalFundsRequestedDirectIndirectCosts = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(PHS398_ModularBudget_1_2:TotalFundsRequestedDirectIndirectCosts, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="32.121212121212125px" hyphenate="true" language="en" top="215.75757575757578px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostTypeDescription) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostTypeDescription = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostTypeDescription"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="358.7878787878788px" hyphenate="true" language="en" top="215.75757575757578px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostRate) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostRate = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostRate, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="398.1818181818182px" hyphenate="true" language="en" top="215.75757575757578px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostBase) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostBase = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostBase, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="475.7575757575758px" hyphenate="true" language="en" top="215.75757575757578px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostFundsRequested) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostFundsRequested = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[2]/PHS398_ModularBudget_1_2:IndirectCostFundsRequested, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="32.121212121212125px" hyphenate="true" language="en" top="244.24242424242425px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostTypeDescription) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostTypeDescription = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostTypeDescription"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="358.7878787878788px" hyphenate="true" language="en" top="244.24242424242425px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostRate) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostRate = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostRate, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="398.1818181818182px" hyphenate="true" language="en" top="244.24242424242425px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostBase) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostBase = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostBase, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="475.7575757575758px" hyphenate="true" language="en" top="244.24242424242425px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostFundsRequested) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostFundsRequested = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[3]/PHS398_ModularBudget_1_2:IndirectCostFundsRequested, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="32.121212121212125px" hyphenate="true" language="en" top="270.3030303030303px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostTypeDescription) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostTypeDescription = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostTypeDescription"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="358.7878787878788px" hyphenate="true" language="en" top="270.3030303030303px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostRate) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostRate = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostRate, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="398.1818181818182px" hyphenate="true" language="en" top="270.3030303030303px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostBase) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostBase = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostBase, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="475.7575757575758px" hyphenate="true" language="en" top="270.3030303030303px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostFundsRequested) or PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostFundsRequested = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(PHS398_ModularBudget_1_2:IndirectCost/PHS398_ModularBudget_1_2:IndirectCostItems[4]/PHS398_ModularBudget_1_2:IndirectCostFundsRequested, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.96969696969697px" hyphenate="true" language="en" top="190.3030303030303px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">1.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="176.36363636363637px" hyphenate="true" language="en" top="68px" height="13.333333333333334px" width="44.24242424242424px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Start Date:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="312.72727272727275px" hyphenate="true" language="en" top="68px" height="13.333333333333334px" width="41.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">End Date:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="10px" hyphenate="true" language="en" top="47px" height="15px" width="550px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold" text-align="center">Budget Period: <xsl:value-of select="position()" /></fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="335.75757575757575px" hyphenate="true" language="en" top="101.21212121212122px" height="13.333333333333334px" width="129.69696969696972px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Direct Cost less Consortium F&amp;A</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="18.181818181818183px" hyphenate="true" language="en" top="86.06060606060606px" height="12.121212121212121px" width="76.36363636363637px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">A. Direct Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="18.181818181818183px" hyphenate="true" language="en" top="152.12121212121212px" height="12.121212121212121px" width="86.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">B. Indirect Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="400.6060606060606px" hyphenate="true" language="en" top="115.75757575757576px" height="13.333333333333334px" width="64.84848484848486px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Consortium F&amp;A</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="392.1212121212121px" hyphenate="true" language="en" top="130.9090909090909px" height="13.333333333333334px" width="73.33333333333334px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Total Direct Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="118.7878787878788px" hyphenate="true" language="en" top="176.36363636363637px" height="13.333333333333334px" width="77.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost Type</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="349.6969696969697px" hyphenate="true" language="en" top="165.45454545454547px" height="24.242424242424242px" width="50.909090909090914px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost&#xD;
Rate (%)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="410.90909090909093px" hyphenate="true" language="en" top="165.45454545454547px" height="24.242424242424242px" width="48.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost&#xD;
Base ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="472.1212121212121px" hyphenate="true" language="en" top="176.36363636363637px" height="13.333333333333334px" width="84.84848484848486px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Funds Requested ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.96969696969697px" hyphenate="true" language="en" top="295.75757575757575px" height="13.333333333333334px" width="243.63636363636365px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Cognizant Agency (Agency Name, POC Name and Phone Number)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="15.757575757575758px" hyphenate="true" language="en" top="353.93939393939394px" height="13.333333333333334px" width="141.8181818181818px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost Rate Agreement Date</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="403.03030303030306px" hyphenate="true" language="en" top="355.1515151515152px" height="13.333333333333334px" width="73.93939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Total Indirect Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="396.3636363636364px" hyphenate="true" language="en" top="380px" height="13.333333333333334px" width="80px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Funds Requested ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="14.545454545454547px" hyphenate="true" language="en" top="377.5757575757576px" height="12.121212121212121px" width="207.27272727272728px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">C. Total Direct and Indirect Costs (A + B)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.96969696969697px" hyphenate="true" language="en" top="215.75757575757578px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">2.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.96969696969697px" hyphenate="true" language="en" top="244.24242424242425px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">3.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.96969696969697px" hyphenate="true" language="en" top="270.3030303030303px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">4.  </fo:block>
					</fo:block-container>
					
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="10px" hyphenate="true" language="en" top="20px" height="20px" width="550px">
						<fo:block background-color="transparent" color="#000000" font-size="14pt" font-style="normal" font-family="Helvetica" font-weight="bold" text-align="center">PHS 398 Modular Budget</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="460.0909090909091px" hyphenate="true" language="en" top="33.2121212121212px" height="12.121212121212121px" width="105.45454545454545px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 0925-0001</fo:block>
					</fo:block-container>
					<!--fo:block-container background-color="transparent" border-style="none" position="absolute" left="460.21212121212125px" hyphenate="true" language="en" top="30.2121212121212px" height="12.121212121212121px" width="129.69696969696972px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Expiration Date: 9/30/2007</fo:block>
					</fo:block-container-->
					
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="480.6060606060606px" hyphenate="true" language="en" top="86.66666666666667px" height="12.121212121212121px" width="95.75757575757576px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Funds Requested ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="467.2727272727273px" hyphenate="true" language="en" top="101.21212121212122px" height="13.333333333333334px" width="89.0909090909091px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(PHS398_ModularBudget_1_2:DirectCost/PHS398_ModularBudget_1_2:DirectCostLessConsortiumFandA) or PHS398_ModularBudget_1_2:DirectCost/PHS398_ModularBudget_1_2:DirectCostLessConsortiumFandA = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(PHS398_ModularBudget_1_2:DirectCost/PHS398_ModularBudget_1_2:DirectCostLessConsortiumFandA, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
	
					
					</fo:block-container>
					</xsl:for-each>
					
					
					
				</fo:flow>
				
				
			
			</fo:page-sequence>
			<fo:page-sequence master-reference="default-page" format="1" initial-page-number="2">
				<!--fo:static-content flow-name="xsl-region-after">
					<fo:block>
						<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
						</fo:inline>
					</fo:block>
				</fo:static-content-->
				<fo:flow flow-name="xsl-region-body">
				
				<!-- Cumulative budget - START-->
				<fo:block-container background-color="black" border-style="none" position="absolute" left="17px" top="50px" width="535px" height="0.5px">
						<fo:block/>
					</fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="17px" top="75px" width="535px" height="0.5px">
						<fo:block/>
					</fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="17px" top="50px" width="0.5px" height="270px">
						<fo:block/>
					</fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="552px" top="50px" width="0.5px" height="270px">
						<fo:block/>
					</fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="17px" top="230px" width="535px" height="0.5px">
						<fo:block/>
					</fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="17px" top="320px" width="535px" height="0.5px">
						<fo:block/>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="344px" hyphenate="true" language="en" top="116px" height="13px" width="124px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeDirectCostLessConsortiumFandA) or //PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeDirectCostLessConsortiumFandA = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeDirectCostLessConsortiumFandA, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="344.8484848484849px" hyphenate="true" language="en" top="135.1515151515151px" height="13.333333333333334px" width="124.84848484848486px">
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeConsortiumFandA) or //PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeConsortiumFandA = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeConsortiumFandA, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="344.8484848484849px" hyphenate="true" language="en" top="153.939393939394px" height="13.333333333333334px" width="124.84848484848486px">
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeTotalFundsRequestedDirectCosts) or //PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeTotalFundsRequestedDirectCosts = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeTotalFundsRequestedDirectCosts, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="344.8484848484849px" hyphenate="true" language="en" top="171.5151515151515px" height="13.333333333333334px" width="124.84848484848486px">
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeTotalFundsRequestedIndirectCost) or //PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeTotalFundsRequestedIndirectCost = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeTotalFundsRequestedIndirectCost, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="344.8484848484849px" hyphenate="true" language="en" top="187.8787878787879px" height="13.333333333333334px" width="124.84848484848486px">
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeTotalFundsRequestedDirectIndirectCosts) or //PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeTotalFundsRequestedDirectIndirectCosts = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:EntirePeriodTotalCost/PHS398_ModularBudget_1_2:CumulativeTotalFundsRequestedDirectIndirectCosts, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="174.54545454545456px" hyphenate="true" language="en" top="266.0606060606061px" height="13.333333333333334px" width="150.9090909090909px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:if test="not(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:PersonnelJustification/PHS398_ModularBudget_1_2:attFile/att:FileName) or /PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:PersonnelJustification/PHS398_ModularBudget_1_2:attFile/att:FileName = ''">
								<fo:inline color="#FFFFFF">&#160;</fo:inline>
							</xsl:if>
							<xsl:value-of select="//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:PersonnelJustification/PHS398_ModularBudget_1_2:attFile/att:FileName"/>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="174.54545454545456px" hyphenate="true" language="en" top="281.2121212121212px" height="13.333333333333334px" width="150.9090909090909px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:if test="not(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:ConsortiumJustification/PHS398_ModularBudget_1_2:attFile/att:FileName) or /PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:ConsortiumJustification/PHS398_ModularBudget_1_2:attFile/att:FileName = ''">
								<fo:inline color="#FFFFFF">&#160;</fo:inline>
							</xsl:if>
							<xsl:value-of select="//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:ConsortiumJustification/PHS398_ModularBudget_1_2:attFile/att:FileName"/>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="175.15151515151516px" hyphenate="true" language="en" top="298.1818181818182px" height="13.333333333333334px" width="149.69696969696972px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid" border-width="0.5px">
							<xsl:if test="not(//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:AdditionalNarrativeJustification/PHS398_ModularBudget_1_2:attFile/att:FileName) or /PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:AdditionalNarrativeJustification/PHS398_ModularBudget_1_2:attFile/att:FileName = ''">
								<fo:inline color="#FFFFFF">&#160;</fo:inline>
							</xsl:if>
							<xsl:value-of select="//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:AdditionalNarrativeJustification/PHS398_ModularBudget_1_2:attFile/att:FileName"/>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="55.75757575757576px" hyphenate="true" language="en" top="116.3636363636364px" height="13.333333333333334px" width="273.93939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Section A, Total Direct Cost less Consortium F&amp;A for Entire Project Period</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="337.5757575757576px" hyphenate="true" language="en" top="116.3636363636364px" height="12.121212121212121px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">$</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="55.75757575757576px" hyphenate="true" language="en" top="135.1515151515151px" height="13.333333333333334px" width="207.8787878787879px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Section A, Total Consortium F&amp;A for Entire Project Period</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="337.5757575757576px" hyphenate="true" language="en" top="135.1515151515151px" height="12.121212121212121px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">$</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="55.75757575757576px" hyphenate="true" language="en" top="153.939393939394px" height="13.333333333333334px" width="201.81818181818184px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Section A, Total Direct Costs for Entire Project Period</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="55.75757575757576px" hyphenate="true" language="en" top="171.5151515151515px" height="13.333333333333334px" width="205.45454545454547px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Section B, Total Indirect Costs for Entire Project Period</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="55.75757575757576px" hyphenate="true" language="en" top="187.8787878787879px" height="13.333333333333334px" width="264.24242424242425px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Section C, Total Direct and Indirect Costs (A+B) for Entire Project Period</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="55.151515151515156px" hyphenate="true" language="en" top="267.8787878787879px" height="13.333333333333334px" width="85.45454545454545px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Personnel Justification</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="55.151515151515156px" hyphenate="true" language="en" top="283.0303030303031px" height="13.333333333333334px" width="90.30303030303031px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Consortium Justification</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="55.151515151515156px" hyphenate="true" language="en" top="298.1818181818182px" height="13.333333333333334px" width="118.18181818181819px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Additional Narrative Justification</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="32.121212121212125px" hyphenate="true" language="en" top="55.1515151515152px" height="12.121212121212121px" width="535.24242424242425px">
						<fo:block background-color="transparent" color="#000000" text-align="center" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">Cumulative Budget Information</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="55.151515151515156px" hyphenate="true" language="en" top="92.72727272727275px" height="12.121212121212121px" width="183.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">1. Total Costs, Entire Project Period</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="53.939393939393945px" hyphenate="true" language="en" top="243.0303030303031px" height="12.121212121212121px" width="120px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">2. Budget Justifications</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="337.5757575757576px" hyphenate="true" language="en" top="153.939393939394px" height="12.121212121212121px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">$</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="337.5757575757576px" hyphenate="true" language="en" top="171.5151515151515px" height="12.121212121212121px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">$</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="337.5757575757576px" hyphenate="true" language="en" top="187.8787878787879px" height="12.121212121212121px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">$</fo:block>
					</fo:block-container>
				<!-- Cumulative budget - END-->
					<!--
					<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="center" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<fo:block font-size="14pt" text-decoration="underline">Attachments</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">PersonnelJustification_attDataGroup0</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:PersonnelJustification/PHS398_ModularBudget_1_2:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:PersonnelJustification/PHS398_ModularBudget_1_2:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">ConsortiumJustification_attDataGroup0</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:ConsortiumJustification/PHS398_ModularBudget_1_2:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="/PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:ConsortiumJustification/PHS398_ModularBudget_1_2:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">AdditionalNarrativeJustification_attDataGroup0</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:AdditionalNarrativeJustification/PHS398_ModularBudget_1_2:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ModularBudget_1_2:PHS398_ModularBudget_1_2/PHS398_ModularBudget_1_2:CummulativeBudgetInfo/PHS398_ModularBudget_1_2:BudgetJustifications/PHS398_ModularBudget_1_2:AdditionalNarrativeJustification/PHS398_ModularBudget_1_2:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
					-->
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	<xsl:template name="radioButton">
		<xsl:param name="value"/>
		<xsl:param name="schemaChoice">Yes</xsl:param>
		<xsl:choose>
			<xsl:when test="$value = $schemaChoice">
				<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
			</xsl:when>
			<xsl:otherwise>
				<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="checkbox">
		<xsl:param name="value"/>
		<xsl:param name="schemaChoice">Yes</xsl:param>
		<xsl:if test="$value = $schemaChoice">
			<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="11pt">&#x2714;</fo:inline>
		</xsl:if>
	</xsl:template>
	<xsl:template name="formatDate">
		<xsl:param name="value"/>
		<xsl:if test="$value != ''">
			<xsl:value-of select="format-number(substring($value,6,2), '00')"/>
			<xsl:text>/</xsl:text>
			<xsl:value-of select="format-number(substring($value,9,2), '00')"/>
			<xsl:text>/</xsl:text>
			<xsl:value-of select="format-number(substring($value,1,4), '0000')"/>
		</xsl:if>
	</xsl:template>
	<xsl:template name="addBlankLines">
		<xsl:param name="numLines"/>
		<xsl:if test="string($numLines) != ''">
			<xsl:if test="$numLines &gt; 0">
				<fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0">
					<fo:leader leader-pattern="space"/>
				</fo:block>
				<xsl:call-template name="addBlankLines">
					<xsl:with-param name="numLines" select="$numLines - 1"/>
				</xsl:call-template>
			</xsl:if>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>
