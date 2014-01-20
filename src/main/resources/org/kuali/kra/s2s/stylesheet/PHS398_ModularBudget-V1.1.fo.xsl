<?xml version="1.0" encoding="UTF-8"?>
<!-- $Revision:   1.0  $ -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:PHS398_ModularBudget="http://apply.grants.gov/forms/PHS398_ModularBudget-V1.1">
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
				<fo:static-content flow-name="xsl-region-after">
					<fo:block>
						<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
						</fo:inline>
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.96969696969697px" top="43.96969696969697px" width="551.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
                                   	<fo:block-container background-color="black" border-style="none" position="absolute" left="10.96969696969697px" top="723.96969696969697px" width="551.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.96969696969697px" top="43.3939393939394px" width="0.6060606060606061px" height="680.3636363636364px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="561.8787878787879px" top="43.96969696969697px" width="0.6060606060606061px" height="680.3636363636364px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.363636363636363px" top="144.24242424242425px" width="551.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="18.181818181818183px" top="48.21212121212121px" width="536.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="18.78787878787879px" top="80.60606060606061px" width="536.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="18.78787878787879px" top="48.81818181818182px" width="0.6060606060606061px" height="33.3939393939394px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="553.939393939394px" top="48.81818181818182px" width="0.6060606060606061px" height="33.3939393939394px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="15.151515151515152px" top="385.4545454545455px" width="536.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="15.151515151515152px" top="423.03030303030306px" width="536.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="15.151515151515152px" top="385.4545454545455px" width="0.6060606060606061px" height="39.3939393939394px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="551.5151515151515px" top="384.8484848484849px" width="0.6060606060606061px" height="39.3939393939394px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.757575757575758px" top="351.51515151515156px" width="551.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>                                        
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.757575757575758px" top="488.4848484848485px" width="551.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.121212121212121px" top="698.7878787878789px" width="551.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="211.51515151515153px" hyphenate="true" language="en" top="61.81818181818182px" height="13.333333333333334px" width="56.969696969696976px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:BudgetPeriodStartDate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:BudgetPeriodStartDate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:BudgetPeriodStartDate[1]"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="345.4545454545455px" hyphenate="true" language="en" top="61.81818181818182px" height="13.333333333333334px" width="56.969696969696976px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:BudgetPeriodEndDate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:BudgetPeriodEndDate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:BudgetPeriodEndDate[1]"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--<fo:block-container background-color="transparent" border-style="none" position="absolute" left="104.24242424242425px" hyphenate="true" language="en" top="50.63636363636364px" height="15.757575757575758px" width="16.363636363636363px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:BudgetPeriod[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:BudgetPeriod[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:BudgetPeriod[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="464.24242424242425px" hyphenate="true" language="en" top="112.12121212121212px" height="13.333333333333334px" width="89.0909090909091px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:DirectCost[1]/PHS398_ModularBudget:ConsortiumFandA[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:DirectCost[1]/PHS398_ModularBudget:ConsortiumFandA[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:DirectCost[1]/PHS398_ModularBudget:ConsortiumFandA[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="464.24242424242425px" hyphenate="true" language="en" top="127.27272727272728px" height="13.333333333333334px" width="89.0909090909091px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:DirectCost[1]/PHS398_ModularBudget:TotalFundsRequestedDirectCosts[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:DirectCost[1]/PHS398_ModularBudget:TotalFundsRequestedDirectCosts[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:DirectCost[1]/PHS398_ModularBudget:TotalFundsRequestedDirectCosts[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="29.6969696969697px" hyphenate="true" language="en" top="180px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[1]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[1]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[1]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="358.1818181818182px" hyphenate="true" language="en" top="180px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[1]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[1]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[1]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="397.5757575757576px" hyphenate="true" language="en" top="180px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[1]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[1]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[1]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="473.33333333333337px" hyphenate="true" language="en" top="180px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[1]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[1]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[1]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="259.39393939393943px" hyphenate="true" language="en" top="280.6060606060606px" height="52.121212121212125px" width="294.54545454545456px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:CognizantFederalAgency[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:CognizantFederalAgency[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:CognizantFederalAgency[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="151.51515151515153px" hyphenate="true" language="en" top="334.54545454545456px" height="13.333333333333334px" width="56.969696969696976px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostAgreementDate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostAgreementDate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostAgreementDate[1]"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="473.93939393939394px" hyphenate="true" language="en" top="333.93939393939394px" height="13.333333333333334px" width="80px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:TotalFundsRequestedIndirectCost[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:TotalFundsRequestedIndirectCost[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:TotalFundsRequestedIndirectCost[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="473.33333333333337px" hyphenate="true" language="en" top="357.5757575757576px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:TotalFundsRequestedDirectIndirectCosts[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:TotalFundsRequestedDirectIndirectCosts[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:TotalFundsRequestedDirectIndirectCosts[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="29.090909090909093px" hyphenate="true" language="en" top="206.06060606060606px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[2]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[2]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[2]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="357.5757575757576px" hyphenate="true" language="en" top="206.06060606060606px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[2]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[2]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[2]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="396.969696969697px" hyphenate="true" language="en" top="206.06060606060606px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[2]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[2]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[2]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="472.72727272727275px" hyphenate="true" language="en" top="206.06060606060606px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[2]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[2]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[2]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="29.090909090909093px" hyphenate="true" language="en" top="230.3030303030303px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[3]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[3]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[3]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="357.5757575757576px" hyphenate="true" language="en" top="230.3030303030303px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[3]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[3]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[3]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="396.969696969697px" hyphenate="true" language="en" top="230.3030303030303px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[3]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[3]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[3]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="472.72727272727275px" hyphenate="true" language="en" top="230.3030303030303px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[3]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[3]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[3]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="29.090909090909093px" hyphenate="true" language="en" top="254.54545454545456px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[4]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[4]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[4]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="357.5757575757576px" hyphenate="true" language="en" top="254.54545454545456px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[4]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[4]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[4]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="396.969696969697px" hyphenate="true" language="en" top="254.54545454545456px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[4]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[4]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[4]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="472.72727272727275px" hyphenate="true" language="en" top="254.54545454545456px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[4]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[4]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:IndirectCost[1]/PHS398_ModularBudget:IndirectCostItems[4]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="215.75757575757578px" hyphenate="true" language="en" top="404.24242424242425px" height="13.333333333333334px" width="56.969696969696976px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:BudgetPeriodStartDate2[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:BudgetPeriodStartDate2[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:BudgetPeriodStartDate2[1]"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="349.6969696969697px" hyphenate="true" language="en" top="404.24242424242425px" height="13.333333333333334px" width="56.969696969696976px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:BudgetPeriodEndDate2[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:BudgetPeriodEndDate2[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:BudgetPeriodEndDate2[1]"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--<fo:block-container background-color="transparent" border-style="none" position="absolute" left="108.48484848484848px" hyphenate="true" language="en" top="387.2727272727273px" height="15.757575757575758px" width="13.333333333333334px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:BudgetPeriod2[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:BudgetPeriod2[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:BudgetPeriod2[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="463.03030303030306px" hyphenate="true" language="en" top="453.93939393939394px" height="13.333333333333334px" width="89.0909090909091px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:DirectCost2[1]/PHS398_ModularBudget:ConsortiumFandA2[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:DirectCost2[1]/PHS398_ModularBudget:ConsortiumFandA2[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:DirectCost2[1]/PHS398_ModularBudget:ConsortiumFandA2[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="463.03030303030306px" hyphenate="true" language="en" top="469.0909090909091px" height="13.333333333333334px" width="89.0909090909091px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:DirectCost2[1]/PHS398_ModularBudget:TotalFundsRequestedDirectCosts2[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:DirectCost2[1]/PHS398_ModularBudget:TotalFundsRequestedDirectCosts2[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:DirectCost2[1]/PHS398_ModularBudget:TotalFundsRequestedDirectCosts2[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="29.090909090909093px" hyphenate="true" language="en" top="526.0606060606061px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[1]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[1]/PHS398_ModularBudget:IndirectCostItems2[1]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[1]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="355.75757575757575px" hyphenate="true" language="en" top="526.0606060606061px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[1]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[1]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[1]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="395.1515151515152px" hyphenate="true" language="en" top="526.0606060606061px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[1]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[1]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[1]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="472.72727272727275px" hyphenate="true" language="en" top="526.0606060606061px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[1]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCostItems2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[1]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="259.39393939393943px" hyphenate="true" language="en" top="627.2727272727273px" height="52.121212121212125px" width="294.54545454545456px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:CognizantFederalAgency2[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:CognizantFederalAgency2[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:CognizantFederalAgency2[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="150.3030303030303px" hyphenate="true" language="en" top="681.2121212121212px" height="13.333333333333334px" width="56.969696969696976px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostAgreementDate2[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostAgreementDate2[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostAgreementDate2[1]"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="476.3636363636364px" hyphenate="true" language="en" top="681.2121212121212px" height="13.333333333333334px" width="80.36363636363637px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:TotalFundsRequestedIndirectCost2[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:TotalFundsRequestedIndirectCost2[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:TotalFundsRequestedIndirectCost2[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="472.1212121212121px" hyphenate="true" language="en" top="704.2424242424242px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:TotalFundsRequestedDirectIndirectCosts2[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:TotalFundsRequestedDirectIndirectCosts2[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:TotalFundsRequestedDirectIndirectCosts2[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="29.090909090909093px" hyphenate="true" language="en" top="551.5151515151515px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[2]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[2]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[2]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="355.75757575757575px" hyphenate="true" language="en" top="551.5151515151515px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[2]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[2]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[2]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="395.1515151515152px" hyphenate="true" language="en" top="551.5151515151515px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[2]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[2]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[2]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="472.72727272727275px" hyphenate="true" language="en" top="551.5151515151515px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[2]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[2]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[2]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="29.090909090909093px" hyphenate="true" language="en" top="576.3636363636364px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[3]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[3]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[3]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="355.75757575757575px" hyphenate="true" language="en" top="576.3636363636364px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[3]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[3]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[3]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="395.1515151515152px" hyphenate="true" language="en" top="576.3636363636364px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[3]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[3]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[3]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="472.72727272727275px" hyphenate="true" language="en" top="576.3636363636364px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[3]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[3]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[3]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="29.090909090909093px" hyphenate="true" language="en" top="601.2121212121212px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[4]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[4]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[4]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="355.75757575757575px" hyphenate="true" language="en" top="601.2121212121212px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[4]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[4]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[4]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="395.1515151515152px" hyphenate="true" language="en" top="601.2121212121212px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[4]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[4]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[4]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="472.72727272727275px" hyphenate="true" language="en" top="601.2121212121212px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[4]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[4]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:IndirectCost2[1]/PHS398_ModularBudget:IndirectCostItems2[4]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="125.12121212121212px" hyphenate="true" language="en" top="1.2121212121212122px" height="20.606060606060606px" width="416.969696969697px">
						<fo:block background-color="transparent" color="#000000" font-size="14pt" font-style="normal" font-family="Helvetica" font-weight="bold">PHS 398 Modular Budget, Periods 1 and 2</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="460.06060606060606px" hyphenate="true" language="en" top="20.0303030303031px" height="12.121212121212121px" width="105.45454545454545px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 0925-0001</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" top="180px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">1.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="460px" hyphenate="true" language="en" top="30.0303030303031px" height="13.333333333333334px" width="98.18181818181819px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Expiration Date: 9/30/2007</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="167.87878787878788px" hyphenate="true" language="en" top="61.81818181818182px" height="13.333333333333334px" width="44.24242424242424px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Start Date:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="304.24242424242425px" hyphenate="true" language="en" top="61.81818181818182px" height="13.333333333333334px" width="41.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">End Date:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="28.484848484848488px" hyphenate="true" language="en" top="50.63636363636364px" height="15.757575757575758px" width="80.75757575757576px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">Budget Period: 1</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="333.93939393939394px" hyphenate="true" language="en" top="97.57575757575758px" height="13.333333333333334px" width="129.69696969696972px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Direct Cost less Consortium F&amp;A</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="19.393939393939394px" hyphenate="true" language="en" top="83.03030303030303px" height="12.121212121212121px" width="76.36363636363637px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">A. Direct Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="17.575757575757578px" hyphenate="true" language="en" top="146.06060606060606px" height="12.121212121212121px" width="86.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">B. Indirect Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="398.7878787878788px" hyphenate="true" language="en" top="112.12121212121212px" height="13.333333333333334px" width="64.84848484848486px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Consortium F&amp;A</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="390.3030303030303px" hyphenate="true" language="en" top="127.27272727272728px" height="13.333333333333334px" width="73.33333333333334px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Total Direct Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="154.54545454545456px" hyphenate="true" language="en" top="166.06060606060606px" height="13.333333333333334px" width="77.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost Type</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="347.8787878787879px" hyphenate="true" language="en" top="155.75757575757578px" height="24.242424242424242px" width="50.909090909090914px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost&#xD;
Rate (%)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="407.8787878787879px" hyphenate="true" language="en" top="155.15151515151516px" height="24.242424242424242px" width="48.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost&#xD;
Base ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="469.0909090909091px" hyphenate="true" language="en" top="166.06060606060606px" height="13.333333333333334px" width="84.84848484848486px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Funds Requested ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="15.757575757575758px" hyphenate="true" language="en" top="280px" height="13.333333333333334px" width="243.63636363636365px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Cognizant Agency (Agency Name, POC Name and Phone Number)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="15.757575757575758px" hyphenate="true" language="en" top="333.93939393939394px" height="13.333333333333334px" width="135.75757575757575px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost Rate Agreement Date</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="400.6060606060606px" hyphenate="true" language="en" top="333.93939393939394px" height="13.333333333333334px" width="73.93939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Total Indirect Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="393.33333333333337px" hyphenate="true" language="en" top="357.5757575757576px" height="13.333333333333334px" width="80px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Funds Requested ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="15.151515151515152px" hyphenate="true" language="en" top="357.5757575757576px" height="12.121212121212121px" width="207.27272727272728px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">C. Total Direct and Indirect Costs (A + B)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="15.757575757575758px" hyphenate="true" language="en" top="206.06060606060606px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">2.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="15.757575757575758px" hyphenate="true" language="en" top="230.3030303030303px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">3.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="15.757575757575758px" hyphenate="true" language="en" top="254.54545454545456px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">4.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="13.93939393939394px" hyphenate="true" language="en" top="526.0606060606061px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">1.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="172.12121212121212px" hyphenate="true" language="en" top="404.24242424242425px" height="13.333333333333334px" width="44.24242424242424px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Start Date:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="308.4848484848485px" hyphenate="true" language="en" top="404.24242424242425px" height="13.333333333333334px" width="41.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">End Date:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="32.72727272727273px" hyphenate="true" language="en" top="387.2727272727273px" height="15.757575757575758px" width="80.75757575757576px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">Budget Period: 2</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="333.54545454545456px" hyphenate="true" language="en" top="439.39393939393943px" height="13.333333333333334px" width="129.69696969696972px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Direct Cost less Consortium F&amp;A</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="15.757575757575758px" hyphenate="true" language="en" top="425.4545454545455px" height="12.121212121212121px" width="76.36363636363637px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">A. Direct Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="15.151515151515152px" hyphenate="true" language="en" top="492.1212121212121px" height="12.121212121212121px" width="86.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">B. Indirect Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="398.39393939393943px" hyphenate="true" language="en" top="453.93939393939394px" height="13.333333333333334px" width="64.84848484848486px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Consortium F&amp;A</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="386.90909090909093px" hyphenate="true" language="en" top="469.0909090909091px" height="13.333333333333334px" width="73.33333333333334px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Total Direct Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="115.75757575757576px" hyphenate="true" language="en" top="512.1212121212121px" height="13.333333333333334px" width="77.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost Type</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="346.6666666666667px" hyphenate="true" language="en" top="501.21212121212125px" height="24.242424242424242px" width="50.909090909090914px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost&#xD;
Rate (%)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="407.8787878787879px" hyphenate="true" language="en" top="501.21212121212125px" height="24.242424242424242px" width="48.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost&#xD;
Base ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="469.0909090909091px" hyphenate="true" language="en" top="512.1212121212121px" height="13.333333333333334px" width="84.84848484848486px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Funds Requested ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="13.93939393939394px" hyphenate="true" language="en" top="626.6666666666667px" height="13.333333333333334px" width="243.63636363636365px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Cognizant Agency (Agency Name, POC Name and Phone Number)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="12.727272727272728px" hyphenate="true" language="en" top="681.2121212121212px" height="13.333333333333334px" width="138.1818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost Rate Agreement Date</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="403.6363636363637px" hyphenate="true" language="en" top="681.2121212121212px" height="13.333333333333334px" width="73.93939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Total Indirect Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="392.72727272727275px" hyphenate="true" language="en" top="704.2424242424242px" height="13.333333333333334px" width="80px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Funds Requested ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="12.727272727272728px" hyphenate="true" language="en" top="701.8181818181819px" height="12.121212121212121px" width="207.27272727272728px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">C. Total Direct and Indirect Costs (A + B)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="13.93939393939394px" hyphenate="true" language="en" top="551.5151515151515px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">2.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="13.93939393939394px" hyphenate="true" language="en" top="576.3636363636364px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">3.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="13.93939393939394px" hyphenate="true" language="en" top="602.4242424242425px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">4.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="476.969696969697px" hyphenate="true" language="en" top="83.63636363636364px" height="12.121212121212121px" width="95.75757575757576px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Funds Requested ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="476.969696969697px" hyphenate="true" language="en" top="424.8484848484849px" height="12.121212121212121px" width="95.75757575757576px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Funds Requested ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="464.24242424242425px" hyphenate="true" language="en" top="97.57575757575758px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:DirectCost[1]/PHS398_ModularBudget:DirectCostLessConsortiumFandA[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:DirectCost[1]/PHS398_ModularBudget:DirectCostLessConsortiumFandA[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods[1]/PHS398_ModularBudget:DirectCost[1]/PHS398_ModularBudget:DirectCostLessConsortiumFandA[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="463.03030303030306px" hyphenate="true" language="en" top="437.5757575757576px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:DirectCost2[1]/PHS398_ModularBudget:DirectCostLessConsortiumFandA2[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:DirectCost2[1]/PHS398_ModularBudget:DirectCostLessConsortiumFandA2[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods2[1]/PHS398_ModularBudget:DirectCost2[1]/PHS398_ModularBudget:DirectCostLessConsortiumFandA2[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
				</fo:flow>
			</fo:page-sequence>
			<fo:page-sequence master-reference="default-page" format="1" initial-page-number="2">
				<fo:static-content flow-name="xsl-region-after">
					<fo:block>
						<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
						</fo:inline>
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.96969696969697px" top="40.96969696969697px" width="551.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.96969696969697px" top="725.96969696969697px" width="551.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.96969696969697px" top="40.3939393939394px" width="0.6060606060606061px" height="683.3636363636364px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="561.8787878787879px" top="40.96969696969697px" width="0.6060606060606061px" height="683.3636363636364px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="18.181818181818183px" top="45.78787878787879px" width="536.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="18.78787878787879px" top="77.57575757575758px" width="536.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="18.78787878787879px" top="45.78787878787879px" width="0.6060606060606061px" height="32.3939393939394px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="554.5454545454546px" top="45.78787878787879px" width="0.6060606060606061px" height="32.3939393939394px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="16.96969696969697px" top="379.39393939393943px" width="536.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="16.96969696969697px" top="418.1818181818182px" width="536.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="16.363636363636363px" top="380px" width="0.6060606060606061px" height="39.3939393939394px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="553.3333333333334px" top="379.39393939393943px" width="0.6060606060606061px" height="39.3939393939394px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.575757575757578px" top="139.3939393939394px" width="551.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.757575757575758px" top="352.1212121212121px" width="551.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.181818181818183px" top="480px" width="551.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.333333333333334px" top="700.6060606060606px" width="551.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="211.51515151515153px" hyphenate="true" language="en" top="59.3939393939394px" height="13.333333333333334px" width="56.969696969696976px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:BudgetPeriodStartDate3[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:BudgetPeriodStartDate3[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:BudgetPeriodStartDate3[1]"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="345.4545454545455px" hyphenate="true" language="en" top="59.3939393939394px" height="13.333333333333334px" width="56.969696969696976px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:BudgetPeriodEndDate3[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:BudgetPeriodEndDate3[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:BudgetPeriodEndDate3[1]"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--<fo:block-container background-color="transparent" border-style="none" position="absolute" left="103.63636363636364px" hyphenate="true" language="en" top="48.21212121212121px" height="15.757575757575758px" width="16.363636363636363px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:BudgetPeriod3[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:BudgetPeriod3[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:BudgetPeriod3[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="464.8484848484849px" hyphenate="true" language="en" top="107.87878787878789px" height="13.333333333333334px" width="89.0909090909091px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:DirectCost3[1]/PHS398_ModularBudget:ConsortiumFandA3[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:DirectCost3[1]/PHS398_ModularBudget:ConsortiumFandA3[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:DirectCost3[1]/PHS398_ModularBudget:ConsortiumFandA3[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="464.8484848484849px" hyphenate="true" language="en" top="123.03030303030303px" height="13.333333333333334px" width="89.0909090909091px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:DirectCost3[1]/PHS398_ModularBudget:TotalFundsRequestedDirectCosts3[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:DirectCost3[1]/PHS398_ModularBudget:TotalFundsRequestedDirectCosts3[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:DirectCost3[1]/PHS398_ModularBudget:TotalFundsRequestedDirectCosts3[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="29.6969696969697px" hyphenate="true" language="en" top="177.5757575757576px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[1]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[1]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[1]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="358.1818181818182px" hyphenate="true" language="en" top="177.5757575757576px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[1]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[1]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[1]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="397.5757575757576px" hyphenate="true" language="en" top="177.5757575757576px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[1]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[1]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[1]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="473.33333333333337px" hyphenate="true" language="en" top="177.5757575757576px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[1]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[1]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[1]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="259.39393939393943px" hyphenate="true" language="en" top="280.6060606060606px" height="52.121212121212125px" width="294.54545454545456px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:CognizantFederalAgency3[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:CognizantFederalAgency3[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:CognizantFederalAgency3[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="156.96969696969697px" hyphenate="true" language="en" top="335.75757575757575px" height="13.333333333333334px" width="56.969696969696976px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostAgreementDate3[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostAgreementDate3[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostAgreementDate3[1]"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="475.1515151515152px" hyphenate="true" language="en" top="335.75757575757575px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:TotalFundsRequestedIndirectCost3[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:TotalFundsRequestedIndirectCost3[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:TotalFundsRequestedIndirectCost3[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="474.54545454545456px" hyphenate="true" language="en" top="358.1818181818182px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:TotalFundsRequestedDirectIndirectCosts3[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:TotalFundsRequestedDirectIndirectCosts3[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:TotalFundsRequestedDirectIndirectCosts3[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="29.090909090909093px" hyphenate="true" language="en" top="203.03030303030303px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[2]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[2]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[2]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="357.5757575757576px" hyphenate="true" language="en" top="203.03030303030303px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[2]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[2]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[2]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="396.969696969697px" hyphenate="true" language="en" top="203.03030303030303px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[2]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[2]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[2]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="472.72727272727275px" hyphenate="true" language="en" top="203.03030303030303px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[2]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[2]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[2]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="29.090909090909093px" hyphenate="true" language="en" top="227.27272727272728px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[3]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[3]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[3]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="357.5757575757576px" hyphenate="true" language="en" top="227.27272727272728px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[3]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[3]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[3]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="396.969696969697px" hyphenate="true" language="en" top="227.27272727272728px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[3]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[3]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[3]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="472.72727272727275px" hyphenate="true" language="en" top="227.27272727272728px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[3]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[3]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[3]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="29.090909090909093px" hyphenate="true" language="en" top="251.51515151515153px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[4]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[4]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[4]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="357.5757575757576px" hyphenate="true" language="en" top="251.51515151515153px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[4]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[4]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[4]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="396.969696969697px" hyphenate="true" language="en" top="251.51515151515153px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[4]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[4]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[4]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="472.72727272727275px" hyphenate="true" language="en" top="251.51515151515153px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[4]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[4]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:IndirectCost3[1]/PHS398_ModularBudget:IndirectCostItems3[4]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="216.96969696969697px" hyphenate="true" language="en" top="399.39393939393943px" height="13.333333333333334px" width="56.969696969696976px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:BudgetPeriodStartDate4[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:BudgetPeriodStartDate4[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:BudgetPeriodStartDate4[1]"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="350.90909090909093px" hyphenate="true" language="en" top="399.39393939393943px" height="13.333333333333334px" width="56.969696969696976px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:BudgetPeriodEndDate4[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:BudgetPeriodEndDate4[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:BudgetPeriodEndDate4[1]"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--<fo:block-container background-color="transparent" border-style="none" position="absolute" left="111.51515151515152px" hyphenate="true" language="en" top="382.42424242424244px" height="15.757575757575758px" width="15.757575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:BudgetPeriod4[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:BudgetPeriod4[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:BudgetPeriod4[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="465.4545454545455px" hyphenate="true" language="en" top="447.8787878787879px" height="13.333333333333334px" width="89.0909090909091px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:DirectCost4[1]/PHS398_ModularBudget:ConsortiumFandA4[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:DirectCost4[1]/PHS398_ModularBudget:ConsortiumFandA4[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:DirectCost4[1]/PHS398_ModularBudget:ConsortiumFandA4[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="465.4545454545455px" hyphenate="true" language="en" top="463.03030303030306px" height="13.333333333333334px" width="89.0909090909091px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:DirectCost4[1]/PHS398_ModularBudget:TotalFundsRequestedDirectCosts4[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:DirectCost4[1]/PHS398_ModularBudget:TotalFundsRequestedDirectCosts4[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:DirectCost4[1]/PHS398_ModularBudget:TotalFundsRequestedDirectCosts4[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="28.484848484848488px" hyphenate="true" language="en" top="515.7575757575758px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[1]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[1]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[1]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="355.1515151515152px" hyphenate="true" language="en" top="515.7575757575758px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[1]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[1]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[1]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="394.54545454545456px" hyphenate="true" language="en" top="515.7575757575758px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[1]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[1]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[1]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="472.1212121212121px" hyphenate="true" language="en" top="515.7575757575758px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[1]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[1]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[1]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="258.7878787878788px" hyphenate="true" language="en" top="623.6363636363636px" height="52.121212121212125px" width="294.54545454545456px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:CognizantFederalAgency4[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:CognizantFederalAgency4[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:CognizantFederalAgency4[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="153.93939393939394px" hyphenate="true" language="en" top="681.8181818181819px" height="13.333333333333334px" width="56.969696969696976px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostAgreementDate4[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostAgreementDate4[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostAgreementDate4[1]"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="475.7575757575758px" hyphenate="true" language="en" top="681.2121212121212px" height="13.333333333333334px" width="80.36363636363637px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:TotalFundsRequestedIndirectCost4[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:TotalFundsRequestedIndirectCost4[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:TotalFundsRequestedIndirectCost4[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="472.72727272727275px" hyphenate="true" language="en" top="706.6666666666667px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:TotalFundsRequestedDirectIndirectCosts4[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:TotalFundsRequestedDirectIndirectCosts4[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:TotalFundsRequestedDirectIndirectCosts4[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="28.484848484848488px" hyphenate="true" language="en" top="541.2121212121212px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[2]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[2]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[2]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="355.1515151515152px" hyphenate="true" language="en" top="541.2121212121212px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[2]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[2]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[2]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="394.54545454545456px" hyphenate="true" language="en" top="541.2121212121212px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[2]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[2]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[2]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="472.1212121212121px" hyphenate="true" language="en" top="541.2121212121212px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[2]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[2]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[2]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="28.484848484848488px" hyphenate="true" language="en" top="567.2727272727273px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[3]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[3]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[3]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="355.1515151515152px" hyphenate="true" language="en" top="567.2727272727273px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[3]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[3]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[3]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="394.54545454545456px" hyphenate="true" language="en" top="567.2727272727273px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[3]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[3]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[3]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="472.1212121212121px" hyphenate="true" language="en" top="567.2727272727273px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[3]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[3]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[3]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="28.484848484848488px" hyphenate="true" language="en" top="593.939393939394px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[4]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[4]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[4]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="355.1515151515152px" hyphenate="true" language="en" top="593.939393939394px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[4]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[4]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[4]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="394.54545454545456px" hyphenate="true" language="en" top="593.939393939394px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[4]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[4]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[4]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="472.1212121212121px" hyphenate="true" language="en" top="593.939393939394px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[4]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[4]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:IndirectCost4[1]/PHS398_ModularBudget:IndirectCostItems4[4]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="460.4848484848485px" hyphenate="true" language="en" top="18.4545454545455px" height="13.333333333333334px" width="93.33333333333334px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 0925-0001</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" top="177.5757575757576px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">1.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="460.81818181818187px" hyphenate="true" language="en" top="27.4545454545455px" height="12.121212121212121px" width="129.69696969696972px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Expiration Date: 9/30/2007</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="167.87878787878788px" hyphenate="true" language="en" top="59.3939393939394px" height="13.333333333333334px" width="44.24242424242424px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Start Date:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="304.24242424242425px" hyphenate="true" language="en" top="59.3939393939394px" height="13.333333333333334px" width="41.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">End Date:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="28.484848484848488px" hyphenate="true" language="en" top="48.21212121212121px" height="15.757575757575758px" width="80.75757575757576px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">Budget Period: 3</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="334.54545454545456px" hyphenate="true" language="en" top="93.33333333333334px" height="13.333333333333334px" width="129.69696969696972px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Direct Cost less Consortium F&amp;A</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="19.393939393939394px" hyphenate="true" language="en" top="76.96969696969697px" height="12.121212121212121px" width="76.36363636363637px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">A. Direct Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="17.575757575757578px" hyphenate="true" language="en" top="143.03030303030303px" height="12.121212121212121px" width="86.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">B. Indirect Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="399.39393939393943px" hyphenate="true" language="en" top="107.87878787878789px" height="13.333333333333334px" width="64.84848484848486px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Consortium F&amp;A</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="390.90909090909093px" hyphenate="true" language="en" top="123.03030303030303px" height="13.333333333333334px" width="73.33333333333334px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Total Direct Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="158.7878787878788px" hyphenate="true" language="en" top="164.84848484848484px" height="13.333333333333334px" width="77.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost Type</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="348.4848484848485px" hyphenate="true" language="en" top="153.93939393939394px" height="24.242424242424242px" width="50.909090909090914px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost&#xD;
Rate (%)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="408.4848484848485px" hyphenate="true" language="en" top="153.93939393939394px" height="24.242424242424242px" width="48.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost&#xD;
Base ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="469.0909090909091px" hyphenate="true" language="en" top="163.63636363636365px" height="13.333333333333334px" width="84.84848484848486px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Funds Requested ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="15.757575757575758px" hyphenate="true" language="en" top="280px" height="13.333333333333334px" width="243.63636363636365px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Cognizant Agency (Agency Name, POC Name and Phone Number)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.96969696969697px" hyphenate="true" language="en" top="335.75757575757575px" height="13.333333333333334px" width="139.3939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost Rate Agreement Date</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="400.6060606060606px" hyphenate="true" language="en" top="335.75757575757575px" height="13.333333333333334px" width="73.93939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Total Indirect Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="394.54545454545456px" hyphenate="true" language="en" top="358.1818181818182px" height="13.333333333333334px" width="80px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Funds Requested ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" top="355.75757575757575px" height="12.121212121212121px" width="207.27272727272728px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">C. Total Direct and Indirect Costs (A + B)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="15.757575757575758px" hyphenate="true" language="en" top="203.03030303030303px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">2.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="15.757575757575758px" hyphenate="true" language="en" top="227.27272727272728px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">3.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="15.757575757575758px" hyphenate="true" language="en" top="251.51515151515153px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">4.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="13.333333333333334px" hyphenate="true" language="en" top="515.7575757575758px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">1.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="173.33333333333334px" hyphenate="true" language="en" top="399.39393939393943px" height="13.333333333333334px" width="44.24242424242424px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Start Date:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="309.6969696969697px" hyphenate="true" language="en" top="399.39393939393943px" height="13.333333333333334px" width="41.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">End Date:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="33.93939393939394px" hyphenate="true" language="en" top="382.42424242424244px" height="15.757575757575758px" width="80.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">Budget Period: 4</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="335.1515151515152px" hyphenate="true" language="en" top="433.33333333333337px" height="13.333333333333334px" width="129.69696969696972px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Direct Cost less Consortium F&amp;A</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" top="420.6060606060606px" height="12.121212121212121px" width="76.36363636363637px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">A. Direct Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="14.545454545454547px" hyphenate="true" language="en" top="483.03030303030306px" height="12.121212121212121px" width="86.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">B. Indirect Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="400px" hyphenate="true" language="en" top="447.8787878787879px" height="13.333333333333334px" width="64.84848484848486px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Consortium F&amp;A</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="391.51515151515156px" hyphenate="true" language="en" top="463.03030303030306px" height="13.333333333333334px" width="73.33333333333334px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Total Direct Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="115.15151515151516px" hyphenate="true" language="en" top="501.81818181818187px" height="13.333333333333334px" width="77.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost Type</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="346.06060606060606px" hyphenate="true" language="en" top="490.90909090909093px" height="24.242424242424242px" width="50.909090909090914px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost&#xD;
Rate (%)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="407.2727272727273px" hyphenate="true" language="en" top="490.90909090909093px" height="24.242424242424242px" width="48.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost&#xD;
Base ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="468.4848484848485px" hyphenate="true" language="en" top="501.81818181818187px" height="13.333333333333334px" width="84.84848484848486px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Funds Requested ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="13.333333333333334px" hyphenate="true" language="en" top="623.0303030303031px" height="13.333333333333334px" width="243.63636363636365px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Cognizant Agency (Agency Name, POC Name and Phone Number)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="12.121212121212121px" hyphenate="true" language="en" top="681.2121212121212px" height="13.333333333333334px" width="141.8181818181818px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Indirect Cost Rate Agreement Date</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="403.03030303030306px" hyphenate="true" language="en" top="681.2121212121212px" height="13.333333333333334px" width="73.93939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Total Indirect Costs</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="393.33333333333337px" hyphenate="true" language="en" top="706.6666666666667px" height="13.333333333333334px" width="80px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Funds Requested ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="12.727272727272728px" hyphenate="true" language="en" top="704.2424242424242px" height="12.121212121212121px" width="207.27272727272728px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">C. Total Direct and Indirect Costs (A + B)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="13.333333333333334px" hyphenate="true" language="en" top="541.2121212121212px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">2.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="13.333333333333334px" hyphenate="true" language="en" top="567.2727272727273px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">3.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="13.333333333333334px" hyphenate="true" language="en" top="593.939393939394px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">4.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="125.12121212121212px" hyphenate="true" language="en" top="1.2121212121212122px" height="20.606060606060606px" width="418.1818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="14pt" font-style="normal" font-family="Helvetica" font-weight="bold">PHS 398 Modular Budget, Periods 3 and 4</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="476.969696969697px" hyphenate="true" language="en" top="420.6060606060606px" height="12.121212121212121px" width="95.75757575757576px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Funds Requested ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="477.5757575757576px" hyphenate="true" language="en" top="79.3939393939394px" height="12.121212121212121px" width="95.75757575757576px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Funds Requested ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="464.8484848484849px" hyphenate="true" language="en" top="93.33333333333334px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:DirectCost3[1]/PHS398_ModularBudget:DirectCostLessConsortiumFandA3[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:DirectCost3[1]/PHS398_ModularBudget:DirectCostLessConsortiumFandA3[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods3[1]/PHS398_ModularBudget:DirectCost3[1]/PHS398_ModularBudget:DirectCostLessConsortiumFandA3[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="465.4545454545455px" hyphenate="true" language="en" top="433.33333333333337px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:DirectCost4[1]/PHS398_ModularBudget:DirectCostLessConsortiumFandA4[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:DirectCost4[1]/PHS398_ModularBudget:DirectCostLessConsortiumFandA4[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods4[1]/PHS398_ModularBudget:DirectCost4[1]/PHS398_ModularBudget:DirectCostLessConsortiumFandA4[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
				</fo:flow>
			</fo:page-sequence>
			<fo:page-sequence master-reference="default-page" format="1" initial-page-number="3">
				<fo:static-content flow-name="xsl-region-after">
					<fo:block>
						<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
						</fo:inline>
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.96969696969697px" top="43.96969696969697px" width="551.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.96969696969697px" top="725.96969696969697px" width="551.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.96969696969697px" top="43.3939393939394px" width="0.6060606060606061px" height="680.3636363636364px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="561.8787878787879px" top="43.96969696969697px" width="0.6060606060606061px" height="680.3636363636364px">
                                            <fo:block/>
                                        </fo:block-container>                                        
					<fo:block-container background-color="black" border-style="none" position="absolute" left="19.393939393939394px" top="50.03030303030303px" width="536.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="19.393939393939394px" top="81.81818181818183px" width="536.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="19.393939393939394px" top="50.24242424242424px" width="0.6060606060606061px" height="32.3939393939394px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="555.1515151515151px" top="50.42424242424243px" width="0.6060606060606061px" height="32.3939393939394px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="17.575757575757578px" top="442.42424242424244px" width="536.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="17.575757575757578px" top="481.21212121212125px" width="536.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="17.575757575757578px" top="443.03030303030306px" width="0.6060606060606061px" height="39.3939393939394px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="553.3333333333334px" top="442.42424242424244px" width="0.6060606060606061px" height="39.3939393939394px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.181818181818183px" top="148.4848484848485px" width="551.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.757575757575758px" top="372.1212121212121px" width="551.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.212121212121215px" top="619.3939393939394px" width="551.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="344.8484848484849px" hyphenate="true" language="en" top="516.3636363636364px" height="13.333333333333334px" width="124.84848484848486px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:CummulativeBudgetInfo[1]/PHS398_ModularBudget:EntirePeriodTotalCost[1]/PHS398_ModularBudget:CumulativeDirectCostLessConsortiumFandA[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:CummulativeBudgetInfo[1]/PHS398_ModularBudget:EntirePeriodTotalCost[1]/PHS398_ModularBudget:CumulativeDirectCostLessConsortiumFandA[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:CummulativeBudgetInfo[1]/PHS398_ModularBudget:EntirePeriodTotalCost[1]/PHS398_ModularBudget:CumulativeDirectCostLessConsortiumFandA[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="344.8484848484849px" hyphenate="true" language="en" top="535.1515151515151px" height="13.333333333333334px" width="124.84848484848486px">
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:CummulativeBudgetInfo[1]/PHS398_ModularBudget:EntirePeriodTotalCost[1]/PHS398_ModularBudget:CumulativeConsortiumFandA[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:CummulativeBudgetInfo[1]/PHS398_ModularBudget:EntirePeriodTotalCost[1]/PHS398_ModularBudget:CumulativeConsortiumFandA[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:CummulativeBudgetInfo[1]/PHS398_ModularBudget:EntirePeriodTotalCost[1]/PHS398_ModularBudget:CumulativeConsortiumFandA[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="344.8484848484849px" hyphenate="true" language="en" top="553.939393939394px" height="13.333333333333334px" width="124.84848484848486px">
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:CummulativeBudgetInfo[1]/PHS398_ModularBudget:EntirePeriodTotalCost[1]/PHS398_ModularBudget:CumulativeTotalFundsRequestedDirectCosts[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:CummulativeBudgetInfo[1]/PHS398_ModularBudget:EntirePeriodTotalCost[1]/PHS398_ModularBudget:CumulativeTotalFundsRequestedDirectCosts[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:CummulativeBudgetInfo[1]/PHS398_ModularBudget:EntirePeriodTotalCost[1]/PHS398_ModularBudget:CumulativeTotalFundsRequestedDirectCosts[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="344.8484848484849px" hyphenate="true" language="en" top="571.5151515151515px" height="13.333333333333334px" width="124.84848484848486px">
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:CummulativeBudgetInfo[1]/PHS398_ModularBudget:EntirePeriodTotalCost[1]/PHS398_ModularBudget:CumulativeTotalFundsRequestedIndirectCost[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:CummulativeBudgetInfo[1]/PHS398_ModularBudget:EntirePeriodTotalCost[1]/PHS398_ModularBudget:CumulativeTotalFundsRequestedIndirectCost[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:CummulativeBudgetInfo[1]/PHS398_ModularBudget:EntirePeriodTotalCost[1]/PHS398_ModularBudget:CumulativeTotalFundsRequestedIndirectCost[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="344.8484848484849px" hyphenate="true" language="en" top="587.8787878787879px" height="13.333333333333334px" width="124.84848484848486px">
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:CummulativeBudgetInfo[1]/PHS398_ModularBudget:EntirePeriodTotalCost[1]/PHS398_ModularBudget:CumulativeTotalFundsRequestedDirectIndirectCosts[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:CummulativeBudgetInfo[1]/PHS398_ModularBudget:EntirePeriodTotalCost[1]/PHS398_ModularBudget:CumulativeTotalFundsRequestedDirectIndirectCosts[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:CummulativeBudgetInfo[1]/PHS398_ModularBudget:EntirePeriodTotalCost[1]/PHS398_ModularBudget:CumulativeTotalFundsRequestedDirectIndirectCosts[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="220.60606060606062px" hyphenate="true" language="en" top="63.03030303030303px" height="13.333333333333334px" width="56.969696969696976px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:BudgetPeriodStartDate5[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:BudgetPeriodStartDate5[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:BudgetPeriodStartDate5[1]"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="354.54545454545456px" hyphenate="true" language="en" top="63.03030303030303px" height="13.333333333333334px" width="56.969696969696976px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:BudgetPeriodEndDate5[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:BudgetPeriodEndDate5[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:BudgetPeriodEndDate5[1]"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--<fo:block-container background-color="transparent" border-style="none" position="absolute" left="114.54545454545455px" hyphenate="true" language="en" top="53.06060606060606px" height="15.757575757575758px" width="13.333333333333334px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:BudgetPeriod5[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:BudgetPeriod5[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:BudgetPeriod5[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="467.2727272727273px" hyphenate="true" language="en" top="116.36363636363637px" height="13.333333333333334px" width="89.0909090909091px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:DirectCost5[1]/PHS398_ModularBudget:ConsortiumFandA5[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:DirectCost5[1]/PHS398_ModularBudget:ConsortiumFandA5[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:DirectCost5[1]/PHS398_ModularBudget:ConsortiumFandA5[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="467.2727272727273px" hyphenate="true" language="en" top="131.51515151515153px" height="13.333333333333334px" width="89.0909090909091px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:DirectCost5[1]/PHS398_ModularBudget:TotalFundsRequestedDirectCosts5[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:DirectCost5[1]/PHS398_ModularBudget:TotalFundsRequestedDirectCosts5[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:DirectCost5[1]/PHS398_ModularBudget:TotalFundsRequestedDirectCosts5[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="32.121212121212125px" hyphenate="true" language="en" top="190.3030303030303px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[1]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[1]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[1]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="358.7878787878788px" hyphenate="true" language="en" top="190.3030303030303px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[1]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[1]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[1]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="398.1818181818182px" hyphenate="true" language="en" top="190.3030303030303px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[1]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[1]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[1]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="475.7575757575758px" hyphenate="true" language="en" top="190.3030303030303px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[1]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[1]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[1]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="262.42424242424244px" hyphenate="true" language="en" top="296.3636363636364px" height="52.121212121212125px" width="294.54545454545456px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:CognizantFederalAgency5[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:CognizantFederalAgency5[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:CognizantFederalAgency5[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="156.96969696969697px" hyphenate="true" language="en" top="353.93939393939394px" height="13.333333333333334px" width="56.969696969696976px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostAgreementDate5[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostAgreementDate5[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostAgreementDate5[1]"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="476.969696969697px" hyphenate="true" language="en" top="355.1515151515152px" height="13.333333333333334px" width="80px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:TotalFundsRequestedIndirectCost5[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:TotalFundsRequestedIndirectCost5[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:TotalFundsRequestedIndirectCost5[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="475.7575757575758px" hyphenate="true" language="en" top="380px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:TotalFundsRequestedDirectIndirectCosts5[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:TotalFundsRequestedDirectIndirectCosts5[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:TotalFundsRequestedDirectIndirectCosts5[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="32.121212121212125px" hyphenate="true" language="en" top="215.75757575757578px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[2]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[2]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[2]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="358.7878787878788px" hyphenate="true" language="en" top="215.75757575757578px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[2]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[2]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[2]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="398.1818181818182px" hyphenate="true" language="en" top="215.75757575757578px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[2]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[2]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[2]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="475.7575757575758px" hyphenate="true" language="en" top="215.75757575757578px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[2]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[2]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[2]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="32.121212121212125px" hyphenate="true" language="en" top="244.24242424242425px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[3]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[3]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[3]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="358.7878787878788px" hyphenate="true" language="en" top="244.24242424242425px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[3]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[3]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[3]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="398.1818181818182px" hyphenate="true" language="en" top="244.24242424242425px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[3]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[3]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[3]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="475.7575757575758px" hyphenate="true" language="en" top="244.24242424242425px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[3]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[3]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[3]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="32.121212121212125px" hyphenate="true" language="en" top="270.3030303030303px" height="23.03030303030303px" width="318.7878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[4]/PHS398_ModularBudget:IndirectCostTypeDescription[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[4]/PHS398_ModularBudget:IndirectCostTypeDescription[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[4]/PHS398_ModularBudget:IndirectCostTypeDescription[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="358.7878787878788px" hyphenate="true" language="en" top="270.3030303030303px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[4]/PHS398_ModularBudget:IndirectCostRate[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[4]/PHS398_ModularBudget:IndirectCostRate[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[4]/PHS398_ModularBudget:IndirectCostRate[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="398.1818181818182px" hyphenate="true" language="en" top="270.3030303030303px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[4]/PHS398_ModularBudget:IndirectCostBase[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[4]/PHS398_ModularBudget:IndirectCostBase[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[4]/PHS398_ModularBudget:IndirectCostBase[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="475.7575757575758px" hyphenate="true" language="en" top="270.3030303030303px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[4]/PHS398_ModularBudget:IndirectCostFundsRequested[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[4]/PHS398_ModularBudget:IndirectCostFundsRequested[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:IndirectCost5[1]/PHS398_ModularBudget:IndirectCostItems5[4]/PHS398_ModularBudget:IndirectCostFundsRequested[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="174.54545454545456px" hyphenate="true" language="en" top="666.0606060606061px" height="13.333333333333334px" width="150.9090909090909px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:if test="not(//PHS398_ModularBudget:PHS398_ModularBudget/PHS398_ModularBudget:CummulativeBudgetInfo/PHS398_ModularBudget:BudgetJustifications/PHS398_ModularBudget:PersonnelJustification/PHS398_ModularBudget:attFile/att:FileName) or /PHS398_ModularBudget:PHS398_ModularBudget/PHS398_ModularBudget:CummulativeBudgetInfo/PHS398_ModularBudget:BudgetJustifications/PHS398_ModularBudget:PersonnelJustification/PHS398_ModularBudget:attFile/att:FileName = ''">
								<fo:inline color="#FFFFFF">&#160;</fo:inline>
							</xsl:if>
							<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget/PHS398_ModularBudget:CummulativeBudgetInfo/PHS398_ModularBudget:BudgetJustifications/PHS398_ModularBudget:PersonnelJustification/PHS398_ModularBudget:attFile/att:FileName"/>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="174.54545454545456px" hyphenate="true" language="en" top="681.2121212121212px" height="13.333333333333334px" width="150.9090909090909px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:if test="not(//PHS398_ModularBudget:PHS398_ModularBudget/PHS398_ModularBudget:CummulativeBudgetInfo/PHS398_ModularBudget:BudgetJustifications/PHS398_ModularBudget:ConsortiumJustification/PHS398_ModularBudget:attFile/att:FileName) or /PHS398_ModularBudget:PHS398_ModularBudget/PHS398_ModularBudget:CummulativeBudgetInfo/PHS398_ModularBudget:BudgetJustifications/PHS398_ModularBudget:ConsortiumJustification/PHS398_ModularBudget:attFile/att:FileName = ''">
								<fo:inline color="#FFFFFF">&#160;</fo:inline>
							</xsl:if>
							<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget/PHS398_ModularBudget:CummulativeBudgetInfo/PHS398_ModularBudget:BudgetJustifications/PHS398_ModularBudget:ConsortiumJustification/PHS398_ModularBudget:attFile/att:FileName"/>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="175.15151515151516px" hyphenate="true" language="en" top="698.1818181818182px" height="13.333333333333334px" width="149.69696969696972px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:if test="not(//PHS398_ModularBudget:PHS398_ModularBudget/PHS398_ModularBudget:CummulativeBudgetInfo/PHS398_ModularBudget:BudgetJustifications/PHS398_ModularBudget:AdditionalNarrativeJustification/PHS398_ModularBudget:attFile/att:FileName) or /PHS398_ModularBudget:PHS398_ModularBudget/PHS398_ModularBudget:CummulativeBudgetInfo/PHS398_ModularBudget:BudgetJustifications/PHS398_ModularBudget:AdditionalNarrativeJustification/PHS398_ModularBudget:attFile/att:FileName = ''">
								<fo:inline color="#FFFFFF">&#160;</fo:inline>
							</xsl:if>
							<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget/PHS398_ModularBudget:CummulativeBudgetInfo/PHS398_ModularBudget:BudgetJustifications/PHS398_ModularBudget:AdditionalNarrativeJustification/PHS398_ModularBudget:attFile/att:FileName"/>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="55.75757575757576px" hyphenate="true" language="en" top="516.3636363636364px" height="13.333333333333334px" width="273.93939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Section A, Total Direct Cost less Consortium F&amp;A for Entire Project Period</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="337.5757575757576px" hyphenate="true" language="en" top="516.3636363636364px" height="12.121212121212121px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">$</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="55.75757575757576px" hyphenate="true" language="en" top="535.1515151515151px" height="13.333333333333334px" width="207.8787878787879px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Section A, Total Consortium F&amp;A for Entire Project Period</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="337.5757575757576px" hyphenate="true" language="en" top="535.1515151515151px" height="12.121212121212121px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">$</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="55.75757575757576px" hyphenate="true" language="en" top="553.939393939394px" height="13.333333333333334px" width="201.81818181818184px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Section A, Total Direct Costs for Entire Project Period</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="55.75757575757576px" hyphenate="true" language="en" top="571.5151515151515px" height="13.333333333333334px" width="205.45454545454547px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Section B, Total Indirect Costs for Entire Project Period</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="55.75757575757576px" hyphenate="true" language="en" top="587.8787878787879px" height="13.333333333333334px" width="264.24242424242425px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Section C, Total Direct and Indirect Costs (A+B) for Entire Project Period</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="55.151515151515156px" hyphenate="true" language="en" top="667.8787878787879px" height="13.333333333333334px" width="85.45454545454545px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Personnel Justification</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="55.151515151515156px" hyphenate="true" language="en" top="683.0303030303031px" height="13.333333333333334px" width="90.30303030303031px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Consortium Justification</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="55.151515151515156px" hyphenate="true" language="en" top="698.1818181818182px" height="13.333333333333334px" width="118.18181818181819px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Additional Narrative Justification</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.96969696969697px" hyphenate="true" language="en" top="190.3030303030303px" height="12.121212121212121px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">1.  </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="176.36363636363637px" hyphenate="true" language="en" top="63.03030303030303px" height="13.333333333333334px" width="44.24242424242424px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Start Date:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="312.72727272727275px" hyphenate="true" language="en" top="63.03030303030303px" height="13.333333333333334px" width="41.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">End Date:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="36.96969696969697px" hyphenate="true" language="en" top="53.06060606060606px" height="15.757575757575758px" width="80.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">Budget Period: 5</fo:block>
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
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="32.121212121212125px" hyphenate="true" language="en" top="455.1515151515152px" height="12.121212121212121px" width="150.24242424242425px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">Cumulative Budget Information</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="55.151515151515156px" hyphenate="true" language="en" top="492.72727272727275px" height="12.121212121212121px" width="183.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">1. Total Costs, Entire Project Period</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="53.939393939393945px" hyphenate="true" language="en" top="643.0303030303031px" height="12.121212121212121px" width="120px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">2. Budget Justifications</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="100.75757575757576px" hyphenate="true" language="en" top="1.8181818181818183px" height="20.606060606060606px" width="485.4545454545455px">
						<fo:block background-color="transparent" color="#000000" font-size="14pt" font-style="normal" font-family="Helvetica" font-weight="bold">PHS 398 Modular Budget, Period 5 and Cumulative</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="460.0909090909091px" hyphenate="true" language="en" top="20.2121212121212px" height="12.121212121212121px" width="105.45454545454545px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 0925-0001</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="460.21212121212125px" hyphenate="true" language="en" top="30.2121212121212px" height="12.121212121212121px" width="129.69696969696972px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Expiration Date: 9/30/2007</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="337.5757575757576px" hyphenate="true" language="en" top="553.939393939394px" height="12.121212121212121px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">$</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="337.5757575757576px" hyphenate="true" language="en" top="571.5151515151515px" height="12.121212121212121px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">$</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="337.5757575757576px" hyphenate="true" language="en" top="587.8787878787879px" height="12.121212121212121px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">$</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="480.6060606060606px" hyphenate="true" language="en" top="86.66666666666667px" height="12.121212121212121px" width="95.75757575757576px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Funds Requested ($)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="467.2727272727273px" hyphenate="true" language="en" top="101.21212121212122px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block line-height="normal" background-color="transparent" color="#000000" font-style="normal" font-size="8pt" font-family="Helvetica" font-weight="normal"/>
						<fo:block text-align="right" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:DirectCost5[1]/PHS398_ModularBudget:DirectCostLessConsortiumFandA5[1]) or //PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:DirectCost5[1]/PHS398_ModularBudget:DirectCostLessConsortiumFandA5[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//PHS398_ModularBudget:PHS398_ModularBudget[1]/PHS398_ModularBudget:Periods5[1]/PHS398_ModularBudget:DirectCost5[1]/PHS398_ModularBudget:DirectCostLessConsortiumFandA5[1], '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
				</fo:flow>
			</fo:page-sequence>
			<fo:page-sequence master-reference="default-page" format="1" initial-page-number="2">
				<fo:static-content flow-name="xsl-region-after">
					<fo:block>
						<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
						</fo:inline>
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
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
										<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget/PHS398_ModularBudget:CummulativeBudgetInfo/PHS398_ModularBudget:BudgetJustifications/PHS398_ModularBudget:PersonnelJustification/PHS398_ModularBudget:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget/PHS398_ModularBudget:CummulativeBudgetInfo/PHS398_ModularBudget:BudgetJustifications/PHS398_ModularBudget:PersonnelJustification/PHS398_ModularBudget:attFile/att:MimeType"/>
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
										<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget/PHS398_ModularBudget:CummulativeBudgetInfo/PHS398_ModularBudget:BudgetJustifications/PHS398_ModularBudget:ConsortiumJustification/PHS398_ModularBudget:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget/PHS398_ModularBudget:CummulativeBudgetInfo/PHS398_ModularBudget:BudgetJustifications/PHS398_ModularBudget:ConsortiumJustification/PHS398_ModularBudget:attFile/att:MimeType"/>
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
										<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget/PHS398_ModularBudget:CummulativeBudgetInfo/PHS398_ModularBudget:BudgetJustifications/PHS398_ModularBudget:AdditionalNarrativeJustification/PHS398_ModularBudget:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ModularBudget:PHS398_ModularBudget/PHS398_ModularBudget:CummulativeBudgetInfo/PHS398_ModularBudget:BudgetJustifications/PHS398_ModularBudget:AdditionalNarrativeJustification/PHS398_ModularBudget:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
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
