<?xml version="1.0" encoding="UTF-8"?><!-- $Revision: 1.4 $ -->
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:PHS398_CoverPageSupplement_3_0="http://apply.grants.gov/forms/PHS398_CoverPageSupplement_3_0-V3.0"
	xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0"
	xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
	xmlns:header="http://apply.grants.gov/system/Header-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema">
	<xsl:output method="xml" indent="yes" />
	<xsl:template
		match="PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master master-name="first-page"
					page-height="11in" page-width="8.5in" margin-left="0.5in"
					margin-right="0.5in">
					<fo:region-body region-name="region-body"
						margin-top="1.2in" margin-bottom="0.4in" />
					<fo:region-before region-name="region-before-first"
						margin-top="0.2in" margin-bottom="0.2in" />
					<fo:region-after region-name="region-after-all"
						extent=".3in" />
				</fo:simple-page-master>
				<fo:simple-page-master master-name="other-page"
					page-height="11in" page-width="8.5in" margin-left="0.5in"
					margin-right="0.5in">
					<fo:region-body region-name="region-body"
						margin-top="0.8in" margin-bottom="0.4in" />
					<fo:region-before region-name="region-before-other"
						margin-top="0.2in" margin-bottom="0.2in" />
					<fo:region-after region-name="region-after-all"
						extent=".3in" />
				</fo:simple-page-master>
				<fo:page-sequence-master master-name="pages">
					<fo:repeatable-page-master-alternatives>
						<fo:conditional-page-master-reference
							page-position="first" master-reference="first-page" />
						<fo:conditional-page-master-reference
							master-reference="other-page" page-position="any" />
					</fo:repeatable-page-master-alternatives>
				</fo:page-sequence-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="pages" format="1"
				initial-page-number="1">
				<fo:static-content flow-name="region-after-all">
					<fo:table width="100%" space-before.optimum="0pt"
						space-after.optimum="0pt" table-layout="fixed">
						<fo:table-column column-width="proportional-column-width(1)" />
						<fo:table-column column-width="proportional-column-width(1)" />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell padding-start="0pt" padding-end="0pt"
									padding-before="1pt" padding-after="1pt" display-align="before"
									text-align="left" border-style="solid" border-width="0pt"
									border-color="white">
									<fo:block>
										<fo:inline font-size="8px">
											Tracking Number:
											<xsl:value-of select="/*/*/footer:Grants_govTrackingNumber" />
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell line-height="9pt" padding-start="0pt"
									padding-end="0pt" padding-before="1pt" padding-after="1pt"
									display-align="before" text-align="right" border-style="solid"
									border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="8px">
											Funding Opportunity Number:
											<xsl:value-of select="/*/*/header:OpportunityID" />
										</fo:inline>
										<fo:inline font-size="8px">
											. Received Date:
											<xsl:value-of select="/*/*/footer:ReceivedDateTime" />
										</fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>
				<fo:static-content flow-name="region-before-first">
					<fo:block-container position="absolute" left="0.2in"
						top="0.5in" height="15px">
						<fo:block text-align="center" font-size="12px"
							font-family="arialuni" font-weight="bold">PHS 398 Cover Page Supplement
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="0.2in"
						top="0.7in" height="12px">
						<fo:block text-align="end" font-size="6px" font-family="arialuni">OMB
							Number: 0925-0001
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="0.2in"
						top="0.9in" height="12px">
						<fo:block text-align="end" font-size="6px" font-family="arialuni">Expiration
							Date: 10/31/2018
						</fo:block>
					</fo:block-container>
				</fo:static-content>
				<fo:static-content flow-name="region-before-other">
					<fo:block-container position="absolute" left="0.2in"
						top="0.5in" height="15px">
						<fo:block text-align="center" font-size="12px"
							font-family="arialuni" font-weight="bold">PHS 398 Cover Page Supplement
						</fo:block>
					</fo:block-container>
				</fo:static-content>

				<fo:flow flow-name="region-body">

					<xsl:variable name="lineLen"
						select="string-length(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:VertebrateAnimals/PHS398_CoverPageSupplement_3_0:EuthanasiaMethodDescription)" />
					<xsl:variable name="vertAnimHeight"
						select="2.1 + ceiling($lineLen div 105) * .13" />
					<xsl:variable name="top" select="1.9 + $vertAnimHeight" />

					<fo:block-container position="absolute" left="0.1in"
						top="-0.1in" height="1.0in" width="7.5in" border-color="black"
						border-style="solid" border-top-width="1px" border-bottom-width="0.5px"
						border-left-width="1px" border-right-width="1px">
						<fo:block />
					</fo:block-container>
					<fo:block-container position="absolute" left="0.1in"
						top="0.9in" height="{$vertAnimHeight + 0.4}in" width="7.5in"
						border-color="black" border-style="solid" border-top-width="0.5px"
						border-bottom-width="0.5px" border-left-width="1px"
						border-right-width="1px">
						<fo:block />
					</fo:block-container>
					<fo:block-container position="absolute" left="0.1in"
						top="{$vertAnimHeight + 1.3}in" height="1.975in" width="7.5in"
						border-color="black" border-style="solid" border-top-width="0.5px"
						border-bottom-width="0px" border-left-width="1px"
						border-right-width="1px">
						<fo:block />
					</fo:block-container>

					<!-- Human Subjects -->
					<fo:table table-layout="fixed" margin-left="0.2in">
						<fo:table-column column-width="2.5in" />
						<fo:table-column column-width="0.25in" />
						<fo:table-column column-width="0.50in" />
						<fo:table-column column-width="0.25in" />
						<fo:table-column column-width="3.7in" />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell number-columns-spanned="5" height=".3in"
									keep-together="always">
									<fo:block font-size="10px" font-family="arialuni"
										font-weight="bold">1. Human Subjects Section</fo:block>
								</fo:table-cell>

							</fo:table-row>
							<fo:table-row>
								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="arialuni">Clinical Trial?
									</fo:block>
								</fo:table-cell>

								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="ZapfDingbats">
										<xsl:choose>
											<xsl:when
												test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:ClinicalTrial/PHS398_CoverPageSupplement_3_0:isClinicalTrial) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:ClinicalTrial/PHS398_CoverPageSupplement_3_0:isClinicalTrial = ''">
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value">
														N: No
													</xsl:with-param>
												</xsl:call-template>
											</xsl:when>
											<xsl:otherwise>
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value"
														select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:ClinicalTrial/PHS398_CoverPageSupplement_3_0:isClinicalTrial" />
												</xsl:call-template>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="arialuni">Yes</fo:block>
								</fo:table-cell>

								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="ZapfDingbats">
										<xsl:choose>
											<xsl:when
												test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:ClinicalTrial/PHS398_CoverPageSupplement_3_0:isClinicalTrial) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:ClinicalTrial/PHS398_CoverPageSupplement_3_0:isClinicalTrial = ''">
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value">
														N: No
													</xsl:with-param>
												</xsl:call-template>
											</xsl:when>
											<xsl:otherwise>
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value"
														select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:ClinicalTrial/PHS398_CoverPageSupplement_3_0:isClinicalTrial" />
													<xsl:with-param name="schemaChoice">
														N: No
													</xsl:with-param>
												</xsl:call-template>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="arialuni">No</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="arialuni">*Agency-Defined
										Phase III Clinical Trial?
									</fo:block>
								</fo:table-cell>
								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="ZapfDingbats">
										<xsl:choose>
											<xsl:when
												test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:ClinicalTrial/PHS398_CoverPageSupplement_3_0:isPhaseIIIClinicalTrial) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:ClinicalTrial/PHS398_CoverPageSupplement_3_0:isPhaseIIIClinicalTrial = ''">
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value">
														N: No
													</xsl:with-param>
												</xsl:call-template>
											</xsl:when>
											<xsl:otherwise>
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value"
														select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:ClinicalTrial/PHS398_CoverPageSupplement_3_0:isPhaseIIIClinicalTrial" />
												</xsl:call-template>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="arialuni">Yes</fo:block>
								</fo:table-cell>

								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="ZapfDingbats">
										<xsl:choose>
											<xsl:when
												test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:ClinicalTrial/PHS398_CoverPageSupplement_3_0:isPhaseIIIClinicalTrial) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:ClinicalTrial/PHS398_CoverPageSupplement_3_0:isPhaseIIIClinicalTrial = ''">
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value">
														N: No
													</xsl:with-param>
												</xsl:call-template>
											</xsl:when>
											<xsl:otherwise>
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value"
														select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:ClinicalTrial/PHS398_CoverPageSupplement_3_0:isPhaseIIIClinicalTrial" />
													<xsl:with-param name="schemaChoice">
														N: No
													</xsl:with-param>
												</xsl:call-template>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="arialuni">No</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<!-- Vertebrate Animals -->
							<fo:table-row>
								<fo:table-cell number-columns-spanned="5" height="0.4in"
									keep-together="always" padding-top="0.1in">
									<fo:block font-size="10px" font-family="arialuni"
										font-weight="bold">2. Vertebrate Animals Section</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="arialuni">Are vertebrate
										animals euthanized?
									</fo:block>
								</fo:table-cell>

								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="ZapfDingbats">
										<xsl:choose>
											<xsl:when
												test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:VertebrateAnimals/PHS398_CoverPageSupplement_3_0:AnimalEuthanasiaIndicator) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:VertebrateAnimals/PHS398_CoverPageSupplement_3_0:AnimalEuthanasiaIndicator = ''">
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value">
														N: No
													</xsl:with-param>
												</xsl:call-template>
											</xsl:when>
											<xsl:otherwise>
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value"
														select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:VertebrateAnimals/PHS398_CoverPageSupplement_3_0:AnimalEuthanasiaIndicator" />
												</xsl:call-template>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="arialuni">Yes</fo:block>
								</fo:table-cell>

								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="ZapfDingbats">
										<xsl:choose>
											<xsl:when
												test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:VertebrateAnimals/PHS398_CoverPageSupplement_3_0:AnimalEuthanasiaIndicator) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:VertebrateAnimals/PHS398_CoverPageSupplement_3_0:AnimalEuthanasiaIndicator= ''">
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value">
														N: No
													</xsl:with-param>
												</xsl:call-template>
											</xsl:when>
											<xsl:otherwise>
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value"
														select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:VertebrateAnimals/PHS398_CoverPageSupplement_3_0:AnimalEuthanasiaIndicator" />
													<xsl:with-param name="schemaChoice">
														N: No
													</xsl:with-param>
												</xsl:call-template>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="arialuni">No</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell number-columns-spanned="5" height="24px">
									<fo:block font-size="9px" font-family="arialuni">
										If
										<fo:inline font-weight="bold">"Yes"</fo:inline>
										to euthanasia
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell number-columns-spanned="5" height=".3in"
									keep-together="always">
									<fo:block font-size="9px" font-family="arialuni">Is the method
										consistent with American Veterinary Medical Association (AVMA)
										guidelines?
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>

								<fo:table-cell height=".3in" keep-together="always">
									<fo:block />
								</fo:table-cell>
								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="ZapfDingbats">
										<xsl:choose>
											<xsl:when
												test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:VertebrateAnimals/PHS398_CoverPageSupplement_3_0:AVMAConsistentIndicator) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:VertebrateAnimals/PHS398_CoverPageSupplement_3_0:AVMAConsistentIndicator= ''">
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value">
														N: No
													</xsl:with-param>
												</xsl:call-template>
											</xsl:when>
											<xsl:otherwise>
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value"
														select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:VertebrateAnimals/PHS398_CoverPageSupplement_3_0:AVMAConsistentIndicator" />
												</xsl:call-template>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="arialuni">Yes</fo:block>
								</fo:table-cell>

								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="ZapfDingbats">
										<xsl:choose>
											<xsl:when
												test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:VertebrateAnimals/PHS398_CoverPageSupplement_3_0:AVMAConsistentIndicator) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:VertebrateAnimals/PHS398_CoverPageSupplement_3_0:AVMAConsistentIndicator= ''">
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value">
														N: No
													</xsl:with-param>
												</xsl:call-template>
											</xsl:when>
											<xsl:otherwise>
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value"
														select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:VertebrateAnimals/PHS398_CoverPageSupplement_3_0:AVMAConsistentIndicator" />
													<xsl:with-param name="schemaChoice">
														N: No
													</xsl:with-param>
												</xsl:call-template>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="arialuni">No</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell number-columns-spanned="5" height=".3in">
									<fo:block font-size="9px" font-family="arialuni">
										If
										<fo:inline font-weight="bold">"No"</fo:inline>
										to AVMA guidelines, describe method and proved scientific
										justification
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell number-columns-spanned="5" height="auto"
									margin-left="0.5in">
									<fo:block font-size="9px" font-family="arialuni"
										border-bottom-color="gray" border-bottom-style="dotted"
										border-bottom-width="1pt">
										<xsl:choose>
											<xsl:when
												test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:VertebrateAnimals/PHS398_CoverPageSupplement_3_0:EuthanasiaMethodDescription) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:VertebrateAnimals/PHS398_CoverPageSupplement_3_0:EuthanasiaMethodDescription = ''">
												<fo:inline color="#FFFFFF">&#160;</fo:inline>
											</xsl:when>
											<xsl:otherwise>
												<xsl:value-of
													select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:VertebrateAnimals/PHS398_CoverPageSupplement_3_0:EuthanasiaMethodDescription" />
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<!-- program income -->
							<fo:table-row>
								<fo:table-cell number-columns-spanned="5"
									padding-top=".4in" height=".3in" keep-together="always">
									<fo:block font-size="10px" font-family="arialuni"
										font-weight="bold">3. *Program Income Section</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell height=".3in" keep-together="always"
									number-columns-spanned="5">
									<fo:block font-size="9px" font-family="arialuni">*Is program
										income
										anticipated during the periods for which the grant
										support is
										requested?
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell height=".3in">
									<fo:block />
								</fo:table-cell>
								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="ZapfDingbats">
										<xsl:choose>
											<xsl:when
												test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:ProgramIncome) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:ProgramIncome = ''">
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value">
														N: No
													</xsl:with-param>
												</xsl:call-template>
											</xsl:when>
											<xsl:otherwise>
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value"
														select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:ProgramIncome" />
												</xsl:call-template>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="arialuni">Yes</fo:block>
								</fo:table-cell>

								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-style="normal"
										font-family="ZapfDingbats">
										<xsl:choose>
											<xsl:when
												test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:ProgramIncome) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:ProgramIncome = ''">
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value">
														N: No
													</xsl:with-param>
												</xsl:call-template>
											</xsl:when>
											<xsl:otherwise>
												<xsl:call-template name="radioButton">
													<xsl:with-param name="value"
														select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:ProgramIncome" />
													<xsl:with-param name="schemaChoice">
														N: No
													</xsl:with-param>
												</xsl:call-template>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell height=".3in" keep-together="always">
									<fo:block font-size="9px" font-family="arialuni">No</fo:block>
								</fo:table-cell>

							</fo:table-row>
							<fo:table-row>
								<fo:table-cell height=".3in" width="7.5in"
									number-columns-spanned="5">
									<fo:block font-size="9px" font-family="arialuni">
										If you checked
										"yes" above (indicating that program income is
										anticipated),
										then
										use the format below to reflect the amount and
										source(s).
										Otherwise, leave this section blank.
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
					<!-- Program income entries - MAX 10 for V3.0 -->

					<xsl:call-template name="tableHeader">
						<xsl:with-param name="topBorderWidth" select="0.0" />
						<xsl:with-param name="rowMargin" select="0.1" />
					</xsl:call-template>
					<xsl:call-template name="incomeTable">
						<xsl:with-param name="beginPosition" select="1.6" />
						<xsl:with-param name="boxTop" select="$top" />
						<xsl:with-param name="items"
							select="PHS398_CoverPageSupplement_3_0:IncomeBudgetPeriod" />
					</xsl:call-template>

					<!-- END program income entries -->

					<fo:block break-after="page">
						<xsl:text>&#xA;</xsl:text>
					</fo:block>

					<fo:block-container position="absolute" left="0.1in"
						top="0.1in" height="4.9in" width="7.5in" border-color="black"
						border-style="solid" border-top-width="1.0px" border-bottom-width="0.5px"
						border-left-width="1px" border-right-width="1px">
						<fo:block />
					</fo:block-container>

					<fo:block-container position="absolute" left="0.2in"
						top="0.2in" height="15px" keep-together="always">
						<fo:block font-size="10px" font-family="arialuni"
							font-weight="bold">4. Human Embryonic Stem Cells Section</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="0.2in"
						top="0.6in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">*Does the proposed
							project involve human embryonic stem cells?
						</fo:block>
					</fo:block-container>

					`
					<fo:block-container position="absolute" left="4.5in"
						top="0.6in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when
									test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:StemCells/PHS398_CoverPageSupplement_3_0:isHumanStemCellsInvolved) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:StemCells/PHS398_CoverPageSupplement_3_0:isHumanStemCellsInvolved = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value"
											select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:StemCells/PHS398_CoverPageSupplement_3_0:isHumanStemCellsInvolved" />
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="4.75in"
						top="0.6in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">Yes</fo:block>
					</fo:block-container>


					<fo:block-container position="absolute" left="5.25in"
						top="0.6in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when
									test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:StemCells/PHS398_CoverPageSupplement_3_0:isHumanStemCellsInvolved) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:StemCells/PHS398_CoverPageSupplement_3_0:isHumanStemCellsInvolved = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value"
											select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:StemCells/PHS398_CoverPageSupplement_3_0:isHumanStemCellsInvolved" />
										<xsl:with-param name="schemaChoice">
											N: No
										</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="5.5in"
						top="0.6in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">No</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute"
						left="421.8181818181818px" top="106.06060606060606px" height="12px"
						width="13.333333333333334px">
						<fo:block font-size="9px" font-family="ZapfDingbats" />
					</fo:block-container>

					<fo:block-container position="absolute" left="0.2in"
						top="1.0in" height="36px">
						<fo:block font-size="9px" font-family="arialuni">
							If the proposed
							project involves human embryonic stem cells, list
							below the
							registration number of the specific cell line(s) from
							the
							following list:
							http://grants.nih.gov/stem_cells/registry/current.htm. Or,
							if a
							specific stem cell line cannot be referenced at this time,
							please
							check the box indicating that one from the registry will
							be used:
						</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="1.5in"
						top="1.5in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when
									test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:StemCells/PHS398_CoverPageSupplement_3_0:StemCellsIndicator) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:StemCells/PHS398_CoverPageSupplement_3_0:StemCellsIndicator = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<fo:inline font-family="ZapfDingbats" font-size="9px">&#x274F;
									</fo:inline>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="1.5in"
						top="1.45in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when
									test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:StemCells/PHS398_CoverPageSupplement_3_0:StemCellsIndicator) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:StemCells/PHS398_CoverPageSupplement_3_0:StemCellsIndicator = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="checkbox">
										<xsl:with-param name="value"
											select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:StemCells/PHS398_CoverPageSupplement_3_0:StemCellsIndicator" />
										<xsl:with-param name="schemaChoice">
											Y: Yes
										</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>

					</fo:block-container>
					<fo:block-container position="absolute" left="1.75in"
						top="1.5in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">Specific stem cell
							line cannot be referenced at this time. One from the registry
							will be used.
						</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="0.2in"
						top="1.7in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni"
							font-weight="bold">Cell Line(s) (Example: 0004):</fo:block>
					</fo:block-container>

					<xsl:for-each
						select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:StemCells/PHS398_CoverPageSupplement_3_0:CellLines">
						<xsl:call-template name="stemCellBlock">
							<xsl:with-param name="value" select="." />
							<xsl:with-param name="index" select="position()" />
						</xsl:call-template>
					</xsl:for-each>

					<fo:block-container position="absolute" left="0.1in"
						top="5.0in" height="1.35in" width="7.5in" border-color="black"
						border-style="solid" border-top-width="0.5px" border-bottom-width="0.5px"
						border-left-width="1px" border-right-width="1px">
						<fo:block />
					</fo:block-container>

					<fo:block-container position="absolute" left="0.2in"
						top="5.2in" height="15px" keep-together="always">
						<fo:block font-size="10px" font-family="arialuni"
							font-weight="bold">5. Inventions and Patents Section (RENEWAL)
						</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="0.2in"
						top="5.5in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">*Inventions and
							Patents:
						</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="2.0in"
						top="5.5in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value"
											select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:IsInventionsAndPatents" />
										<xsl:with-param name="schemaChoice">
											Y: Yes
										</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="2.25in"
						top="5.5in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">Yes</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="2.75in"
						top="5.5in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value"
											select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:IsInventionsAndPatents" />
										<xsl:with-param name="schemaChoice">
											N: No
										</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="3.0in"
						top="5.5in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">No</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="0.2in"
						top="5.8in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">
							If the answer is
							<fo:inline font-weight="bold">"Yes"</fo:inline>
							then please answer the following:
						</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="0.2in"
						top="6.1in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">*Previously
							Reported:
						</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="2.0in"
						top="6.1in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value"
											select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:IsPreviouslyReported" />
										<xsl:with-param name="schemaChoice">
											Y: Yes
										</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="2.25in"
						top="6.1in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">Yes</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="2.75in"
						top="6.1in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value"
											select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:IsPreviouslyReported" />
										<xsl:with-param name="schemaChoice">
											N: No
										</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="3.0in"
						top="6.1in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">No</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="0.1in"
						top="6.35in" height="3.0in" width="7.5in" border-color="black"
						border-style="solid" border-top-width="0.5px" border-bottom-width="1px"
						border-left-width="1px" border-right-width="1px">
						<fo:block />
					</fo:block-container>

					<fo:block-container position="absolute" left="0.2in"
						top="6.5in" height="15px" keep-together="always">
						<fo:block font-size="10px" font-family="arialuni"
							font-weight="bold">6. Change of Investigator / Change of Institution
							Section
						</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="0.2in"
						top="6.9in" height="12px" keep-together="always">
						<fo:block font-size="10px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<fo:inline font-family="ZapfDingbats" font-size="10px">&#x274F;
									</fo:inline>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="0.2in"
						top="6.85in" height="12px" keep-together="always">
						<fo:block font-size="10px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when
									test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:IsChangeOfPDPI) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:IsChangeOfPDPI = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="checkbox">
										<xsl:with-param name="value"
											select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:IsChangeOfPDPI" />
										<xsl:with-param name="schemaChoice">
											Y: Yes
										</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="0.75in"
						top="6.9in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">Change of Project
							Director / Principal Investigator
						</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="0.2in"
						top="7.1in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">Name of former
							Project Director / Principal Investigator
						</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="0.2in"
						top="7.3in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">Prefix:</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="1.5in"
						top="7.3in" height="12px" keep-together="always">
						<fo:block text-align="start" font-size="9px" font-family="arialuni">
							<xsl:choose>
								<xsl:when
									test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:FormerPD_Name/globLib:PrefixName) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:FormerPD_Name/globLib:PrefixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of
										select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:FormerPD_Name/globLib:PrefixName" />
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="0.2in"
						top="7.5in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">*First Name:
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="1.5in"
						top="7.5in" height="12px" keep-together="always">
						<fo:block text-align="start" font-size="9px" font-family="arialuni">
							<xsl:choose>
								<xsl:when
									test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:FormerPD_Name/globLib:FirstName) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:FormerPD_Name/globLib:FirstName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of
										select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:FormerPD_Name/globLib:FirstName" />
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="0.2in"
						top="7.7in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">Middle Name:
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="1.5in"
						top="7.7in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">
							<xsl:choose>
								<xsl:when
									test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:FormerPD_Name/globLib:MiddleName) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:FormerPD_Name/globLib:MiddleName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of
										select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:FormerPD_Name/globLib:MiddleName" />
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="0.2in"
						top="7.9in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">*Last Name:
						</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="1.5in"
						top="7.9in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">
							<xsl:choose>
								<xsl:when
									test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:FormerPD_Name/globLib:LastName) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:FormerPD_Name/globLib:LastName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of
										select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:FormerPD_Name/globLib:LastName" />
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="0.2in"
						top="8.1in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">Suffix:</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="1.5in"
						top="8.1in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">
							<xsl:choose>
								<xsl:when
									test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:FormerPD_Name/globLib:SuffixName) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:FormerPD_Name/globLib:SuffixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of
										select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:FormerPD_Name/globLib:SuffixName" />
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="0.2in"
						top="8.4in" height="12px" keep-together="always">
						<fo:block font-size="10px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<fo:inline font-family="ZapfDingbats" font-size="10px">&#x274F;
									</fo:inline>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="0.2in"
						top="8.35in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when
									test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:IsChangeOfInstitution) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:IsChangeOfInstitution = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="checkbox">
										<xsl:with-param name="value"
											select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:IsChangeOfInstitution" />
										<xsl:with-param name="schemaChoice">
											Y: Yes
										</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="0.75in"
						top="8.4in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">Change of Grantee
							Institution
						</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="0.2in"
						top="8.7in" height="12px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">*Name of former
							institution:
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="0.2in"
						top="8.9in" height="24px" keep-together="always">
						<fo:block font-size="9px" font-family="arialuni">
							<xsl:choose>
								<xsl:when
									test="not(//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:FormerInstitutionName) or //PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:FormerInstitutionName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of
										select="//PHS398_CoverPageSupplement_3_0:PHS398_CoverPageSupplement_3_0/PHS398_CoverPageSupplement_3_0:FormerInstitutionName" />
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>

				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	<xsl:template name="radioButton">
		<xsl:param name="value" />
		<xsl:param name="schemaChoice">
			Y: Yes
		</xsl:param>
		<xsl:choose>
			<xsl:when test="normalize-space($value) = normalize-space($schemaChoice)">
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
			Y: Yes
		</xsl:param>
		<xsl:if test="normalize-space($value) = normalize-space($schemaChoice)">
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

	<!-- Program income table - MAX 10 for V3.0 -->
	<xsl:template name="incomeTable">
		<xsl:param name="beginPosition" />
		<xsl:param name="boxTop" />
		<xsl:param name="items" />
		<xsl:variable name="index" select="1" />
		<fo:table table-layout="fixed" width="7.51in" border-color="black"
			margin-left="0.08in" border-style="solid" border-top-width="0px"
			border-bottom-width="0.5px" border-left-width="1px"
			border-right-width="1px">
			<fo:table-column column-width="1.0in" padding-top="0.2in"
				padding-left="0.1in" padding-before="0.2in" />
			<fo:table-column column-width="1.6in" padding-top="0.2in"
				padding-left="0.1in" padding-before="0.2in" />
			<fo:table-column column-width="4.91in" padding-top="0.2in"
				padding-left="0.1in" padding-before="0.2in" />
			<fo:table-body>
				<xsl:call-template name="incomeTableRow">
					<xsl:with-param name="beginPosition" select="$beginPosition + .2" />
					<xsl:with-param name="boxTop" select="$boxTop" />
					<xsl:with-param name="items" select="$items" />
					<xsl:with-param name="index" select="$index" />
					<xsl:with-param name="pageIndex" select="$index" />
				</xsl:call-template>
			</fo:table-body>
		</fo:table>
	</xsl:template>
	<xsl:template name="incomeTableRow">
		<xsl:param name="beginPosition" />
		<xsl:param name="boxTop" />
		<xsl:param name="items" />
		<xsl:param name="index" />
		<xsl:param name="pageIndex" />
		<xsl:variable name="lineLen"
			select="string-length($items[$index]/PHS398_CoverPageSupplement_3_0:Source)" />
		<xsl:variable name="sourceHeight">
			<xsl:value-of select="(ceiling($lineLen div 83) * .0855)" />
		</xsl:variable>
		<xsl:choose>
			<xsl:when
				test="ceiling($sourceHeight + $boxTop + $beginPosition) &gt; 9">
				<fo:table-row keep-together.within-page="always"
					border-color="black" border-style="solid" border-top-width="0px"
					border-bottom-width="1px" border-left-width="1px"
					border-right-width="1px">
					<fo:table-cell number-columns-spanned="3" height=".3in">
						<fo:block />
					</fo:table-cell>
				</fo:table-row>
				<fo:table-row keep-together.within-page="always">
					<fo:table-cell number-columns-spanned="3">
						<fo:block break-after="page">
							<xsl:text>&#xA;</xsl:text>
						</fo:block>
						<fo:block margin-left="-0.18in">
							<xsl:call-template name="tableHeader">
								<xsl:with-param name="topBorderWidth" select="1.0" />
								<xsl:with-param name="sideBorderWidth" select="0" />
							</xsl:call-template>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<xsl:call-template name="incomeTableRow">
					<xsl:with-param name="beginPosition" select="0.1" />
					<xsl:with-param name="boxTop" select="0.7" />
					<xsl:with-param name="items" select="$items" />
					<xsl:with-param name="index" select="$index" />
					<xsl:with-param name="pageIndex" select="1" />
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<fo:table-row keep-together.within-page="always">
					<fo:table-cell left="0.7in" display-align="after"
						text-align="right" height="auto" padding-top="0.1in" padding-left="0.1in"
						padding-before="0.2in">
						<fo:block text-align="end" font-size="9px" font-family="arialuni"
							border-bottom-color="gray" border-bottom-style="dotted"
							border-bottom-width="1pt">
							<xsl:choose>
								<xsl:when
									test="not($items[$index]/PHS398_CoverPageSupplement_3_0:BudgetPeriod) or $items[$index]/PHS398_CoverPageSupplement_3_0:BudgetPeriod = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of
										select="format-number($items[$index]/PHS398_CoverPageSupplement_3_0:BudgetPeriod, '#,##0')" />
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:table-cell>
					<fo:table-cell left="1.7in" display-align="after"
						text-align="right" height="auto" padding-top="0.1in" padding-left="0.1in"
						padding-right="0.1in" padding-before="0.2in">
						<fo:block text-align="end" font-size="9px" font-family="arialuni"
							border-bottom-color="gray" border-bottom-style="dotted"
							border-bottom-width="1pt">
							<xsl:choose>
								<xsl:when
									test="not($items[$index]/PHS398_CoverPageSupplement_3_0:AnticipatedAmount) or $items[$index]/PHS398_CoverPageSupplement_3_0:AnticipatedAmount = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of
										select="format-number($items[$index]/PHS398_CoverPageSupplement_3_0:AnticipatedAmount, '#,##0.00')" />
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:table-cell>
					<fo:table-cell left="3.4in" display-align="after"
						height="auto" padding-top="0.1in" padding-left="0.1in"
						padding-right="0.1in" padding-before="0.2in">
						<fo:block text-align="start" font-size="9px" font-family="arialuni"
							border-bottom-color="gray" border-bottom-style="dotted"
							border-bottom-width="1pt">
							<xsl:choose>
								<xsl:when
									test="not($items[$index]/PHS398_CoverPageSupplement_3_0:Source) or $items[$index]/PHS398_CoverPageSupplement_3_0:Source = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of
										select="$items[$index]/PHS398_CoverPageSupplement_3_0:Source" />
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<xsl:choose>
					<xsl:when test="$items[$index + 1]">
						<xsl:call-template name="incomeTableRow">
							<xsl:with-param name="beginPosition"
								select="$beginPosition + $sourceHeight + .1" />
							<xsl:with-param name="boxTop" select="$boxTop" />
							<xsl:with-param name="items" select="$items" />
							<xsl:with-param name="index" select="$index + 1" />
							<xsl:with-param name="pageIndex" select="$pageIndex + 1" />
						</xsl:call-template>
					</xsl:when>
					<xsl:otherwise>
						<fo:table-row keep-together.within-page="always"
							border-color="black" border-style="solid" border-top-width="0px"
							border-bottom-width="1px" border-left-width="1px"
							border-right-width="1px">
							<fo:table-cell number-columns-spanned="3" height=".3in">
								<fo:block />
							</fo:table-cell>
						</fo:table-row>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="tableHeader">
		<xsl:param name="topBorderWidth" />
		<xsl:param name="sideBorderWidth">
			1.0
		</xsl:param>
		<xsl:param name="rowMargin">
			0.2
		</xsl:param>
		<fo:table table-layout="fixed" width="7.51in" border-color="black"
			margin-left="0.08in" border-style="solid" border-top-width="{$topBorderWidth}px"
			border-bottom-width="0px" border-left-width="{normalize-space($sideBorderWidth)}px"
			border-right-width="{normalize-space($sideBorderWidth)}px">
			<fo:table-column column-width="1.0in" padding-top="0.1in"
				padding-left="0.1in" padding-before="0.1in" />
			<fo:table-column column-width="1.6in" padding-top="0.1in"
				padding-left="0.1in" padding-before="0.1in" />
			<fo:table-column column-width="4.91in" padding-top="0.1in"
				padding-left="0.1in" padding-before="0.1in" />
			<fo:table-body>
				<fo:table-row margin-left="{normalize-space($rowMargin)}in">
					<fo:table-cell keep-together="always" height=".1in"
						padding-top=".1in">
						<fo:block font-size="9px" font-family="arialuni">*Budget Period
						</fo:block>
					</fo:table-cell>

					<fo:table-cell keep-together="always" height=".1in"
						padding-top=".1in">
						<fo:block font-size="9px" font-family="arialuni">*Anticipated Amount
							($)
						</fo:block>
					</fo:table-cell>

					<fo:table-cell keep-together="always" height=".1in"
						padding-top=".1in">
						<fo:block font-size="9px" font-family="arialuni">*Source(s)
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
	</xsl:template>
	<xsl:template name="stemCellBlock">
		<xsl:param name="value" />
		<xsl:param name="index" />
		<xsl:choose>
			<xsl:when test="$value">
				<xsl:choose>
					<xsl:when test="$value != ''">
						<fo:block-container position="absolute"
							left="{0.4 + (floor(($index - 1) div 12) * 0.4)}in" top="{1.9 + (floor(($index - 1) mod 12) * 0.25)}in"
							height="12px" width="0.35in" border-top-style="solid"
							border-bottom-style="solid" border-start-style="solid"
							border-end-style="solid">
							<fo:block font-size="9px" font-family="arialuni"
								display-align="center" text-align="center" padding-top="1.5px">
								<xsl:value-of select="$value" />
							</fo:block>
						</fo:block-container>
					</xsl:when>
				</xsl:choose>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
