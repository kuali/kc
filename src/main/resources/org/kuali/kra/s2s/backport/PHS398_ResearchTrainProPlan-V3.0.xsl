<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:PHS398_ResearchTrainingProgramPlan_3_0="http://apply.grants.gov/forms/PHS398_ResearchTrainingProgramPlan_3_0-V3.0"
	xmlns:att="http://apply.grants.gov/system/Attachments-V1.0"
	xmlns:codes="http://apply.grants.gov/system/UniversalCodes-V2.0"
	xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:glob="http://apply.grants.gov/system/Global-V1.0"
	xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
	xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:header="http://apply.grants.gov/system/Header-V1.0"
	xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:fo="http://www.w3.org/1999/XSL/Format">

	<xsl:variable name="XML" select="/" />
	<xsl:template
		match="PHS398_ResearchTrainingProgramPlan_3_0:PHS398_ResearchTrainingProgramPlan_3_0">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master master-name="first"
					page-height="11in" page-width="8.5in" margin-left="0.6in"
					margin-right="0.6in">
					<fo:region-body margin-top="0.4in" margin-bottom="0.1in" />
					<fo:region-before region-name="region-before-first"
						extent="0.2in" />
					<fo:region-after region-name="region-after-all"
						extent="0.3in" />
				</fo:simple-page-master>

				<fo:simple-page-master master-name="rest"
					page-height="11in" page-width="8.5in" margin-left="0.6in"
					margin-right="0.6in">
					<fo:region-body margin-top="0.2in" margin-bottom="0.2in" />
					<fo:region-after region-name="region-after-all"
						extent="0.3in" />
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
					<fo:table width="100%" space-before.optimum="0pt"
						space-after.optimum="0pt" table-layout="fixed">
						<fo:table-column column-width="proportional-column-width(1)" />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en"
									line-height="9pt" padding-start="0pt" padding-end="0pt"
									padding-before="3pt" padding-after="0pt" display-align="before"
									text-align="right" border-style="solid" border-width="0pt"
									border-color="white">
									<fo:block>

									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>
            <fo:static-content flow-name="region-after-all">
               <fo:table width="100%"
                  space-before.optimum="0pt"
                  space-after.optimum="0pt"
                  table-layout="fixed">
                  <fo:table-column column-width="proportional-column-width(1)"/>
                  <fo:table-column column-width="proportional-column-width(1)"/>
                  <fo:table-body>
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en"
                         padding-start="0pt"
                         padding-end="0pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="0pt"
                         border-color="white">
                           <fo:block>
                              <fo:inline font-size="8px">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
                              </fo:inline>
                           </fo:block>
                        </fo:table-cell>
                        <fo:table-cell hyphenate="true" language="en" line-height="10pt"
                         padding-start="0pt"
                         padding-end="0pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="right"
                         border-style="solid"
                         border-width="0pt"
                         border-color="white">
                           <fo:block><fo:inline font-size="8px">Funding Opportunity Number: <xsl:value-of select="/*/*/header:OpportunityID"/></fo:inline>
                                     <fo:inline font-size="8px">.       Received Date: <xsl:value-of select="/*/*/footer:ReceivedDateTime"/></fo:inline></fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                  </fo:table-body>
               </fo:table>
            </fo:static-content>

				<fo:flow flow-name="xsl-region-body">
					<fo:block>
						<xsl:for-each select="$XML">
							<xsl:call-template name="phs398TrainProPlan" />
						</xsl:for-each>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>

		</fo:root>
	</xsl:template>

	<xsl:template name="phs398TrainProPlan">
		<fo:block>
			<!-- PHS398 Research Training Program Plan Page header -->
			<fo:table font-size="12pt" width="100%"
				space-before.optimum="1pt" space-after.optimum="2pt" table-layout="fixed">
				<fo:table-column column-width="proportional-column-width(1)" />
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell text-align="center" padding-start="2pt"
							padding-end="2pt" padding-before="20pt" padding-after="1pt"
							display-align="center" border-style="solid" border-width="1pt"
							border-color="white">
							<fo:block font-family="arialuni" font-size="12px" font-weight="bold">
								PHS 398 Research
								Training Program Plan
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<!-- Empty line -->
					<fo:table-row>
						<fo:table-cell>
							<fo:block>
								<fo:leader leader-pattern="space" />
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<!-- text underneath the label -->
					<fo:table-row>
						<!-- Text contents -->
						<fo:table-cell text-align="left" padding-start="1pt"
							padding-end="1pt" padding-before="1pt" padding-after="1pt"
							display-align="left" border-style="solid" border-width="1pt"
							border-color="white">
							<fo:block>
								<fo:table font-size="9px" table-layout="fixed" width="100%">
									<fo:table-column column-width="70%" />
									<fo:table-column column-width="30%" />
									<fo:table-body start-indent="0pt">
										<fo:table-row>
											<fo:table-cell text-align="left" padding-start="1pt"
												padding-end="1pt" padding-before="1pt" padding-after="1pt"
												display-align="center" border-style="solid" border-width="1pt"
												border-color="white">
												<fo:block font-size="9px" color="white">
													Please attach applicable
													sections of
													the research training program plan, below.
												</fo:block>
											</fo:table-cell>
											<!-- OMB number label -->
											<fo:table-cell text-align="right"
												padding-start="1pt" padding-end="1pt" padding-before="1pt"
												padding-after="1pt" display-align="right" border-style="solid"
												border-width="1pt" border-color="white">
												<fo:block font-size="6px">
													OMB Number:
													0925-0001
												</fo:block>
											</fo:table-cell>
											
											
										</fo:table-row>
						<fo:table-row>
                        	<fo:table-cell font-size="smaller" padding="0" number-columns-spanned="2" text-align="right" display-align="center">
                                <fo:block>
                                    <fo:inline font-family="arialuni" font-size="6px">
                                        <xsl:text>Expiration Date: 10/31/2018</xsl:text>
                                    </fo:inline>
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

			<!-- table which should have all the attachment listings -->
			<fo:table width="100%" table-layout="fixed" border-style="solid" border-color="black" border-top-width="1pt" border-bottom-width="0pt" border-left-width="1pt" border-right-width="1pt"
				border-spacing="1pt">
				<fo:table-column column-width="40%" />
				<fo:table-column column-width="60%" />
				<fo:table-body start-indent="0pt">
					<!-- Empty line -->
					<fo:table-row>
						<fo:table-cell>
							<fo:block>
								<fo:leader leader-pattern="space" />
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
							<fo:table-row>
                                  <fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline font-weight="bold" font-size="10px">
                                                                        <xsl:text>Introduction</xsl:text>
                                                                    </fo:inline>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
					
					<!-- 1. Introduction to Application -->
					<fo:table-row>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="1pt" padding-after="6pt"
							display-align="left">
							<fo:block font-family="arialuni" font-size="9px">
								1. Introduction to Application
							</fo:block>
							<fo:block font-size="6px">
								(for RESUBMISSION and REVISION)
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="1pt" padding-after="6pt"
							display-align="left">
							<fo:block>
								<fo:inline-container>
									<fo:block>
										<xsl:text>&#x2029;</xsl:text>
									</fo:block>
								</fo:inline-container>
								<fo:table font-size="9px" table-layout="fixed" width="100%">
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-body start-indent="0pt">
										<fo:table-row>
											<fo:table-cell border="solid 1pt white"
												border-spacing="1pt" padding="2pt" display-align="center">
												<fo:block font-family="arialuni" font-size="10px">
													<fo:inline>
														<xsl:text>&#160;</xsl:text>
													</fo:inline>
													<xsl:for-each
														select="//PHS398_ResearchTrainingProgramPlan_3_0:PHS398_ResearchTrainingProgramPlan_3_0">
														<xsl:for-each
															select="PHS398_ResearchTrainingProgramPlan_3_0:ResearchTrainingProgramPlanAttachments">
															<xsl:for-each
																select="PHS398_ResearchTrainingProgramPlan_3_0:IntroductionToApplication">
																<xsl:for-each
																	select="PHS398_ResearchTrainingProgramPlan_3_0:attFile">
																	<xsl:for-each select="att:FileName">
																		<xsl:variable name="value-of-template">
																			<xsl:apply-templates />
																		</xsl:variable>
																		<xsl:choose>
																			<xsl:when
																				test="contains(string($value-of-template),'&#x2029;')">
																				<fo:block>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:block>
																			</xsl:when>
																			<xsl:otherwise>
																				<fo:inline>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:inline>
																			</xsl:otherwise>
																		</xsl:choose>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
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

							<fo:table-row>
                                  <fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline font-weight="bold" font-size="10px">
                                                                        <xsl:text>Training Program Section</xsl:text>
                                                                    </fo:inline>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>

					<!-- Program Plan -->
					<fo:table-row>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block font-family="arialuni" font-size="9px">
							* 2. Program Plan
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block>
								<fo:inline-container>
									<fo:block>
										<xsl:text>&#x2029;</xsl:text>
									</fo:block>
								</fo:inline-container>
								<fo:table font-size="9px" table-layout="fixed" width="100%">
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-body start-indent="0pt">
										<fo:table-row>
											<fo:table-cell border="solid 1pt white"
												border-spacing="1pt" padding="2pt" display-align="center">
												<fo:block font-family="arialuni" font-size="10px">
													<fo:inline>
														<xsl:text>&#160;</xsl:text>
													</fo:inline>
													<xsl:for-each
														select="//PHS398_ResearchTrainingProgramPlan_3_0:PHS398_ResearchTrainingProgramPlan_3_0">
														<xsl:for-each
															select="PHS398_ResearchTrainingProgramPlan_3_0:ResearchTrainingProgramPlanAttachments">
															<xsl:for-each
																select="PHS398_ResearchTrainingProgramPlan_3_0:ProgramPlan">
																<xsl:for-each
																	select="PHS398_ResearchTrainingProgramPlan_3_0:attFile">
																	<xsl:for-each select="att:FileName">
																		<xsl:variable name="value-of-template">
																			<xsl:apply-templates />
																		</xsl:variable>
																		<xsl:choose>
																			<xsl:when
																				test="contains(string($value-of-template),'&#x2029;')">
																				<fo:block>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:block>
																			</xsl:when>
																			<xsl:otherwise>
																				<fo:inline>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:inline>
																			</xsl:otherwise>
																		</xsl:choose>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
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
					
					<!-- Plan for Instruction in the Responsible Conduct of Research -->
					<fo:table-row>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="6pt"
							display-align="left">
							<fo:block font-family="arialuni" font-size="9px">
								3. Plan for Instruction in the
								Responsible Conduct of Research
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="6pt"
							display-align="left">
							<fo:block>
								<fo:inline-container>
									<fo:block>
										<xsl:text>&#x2029;</xsl:text>
									</fo:block>
								</fo:inline-container>
								<fo:table font-size="9px" table-layout="fixed" width="100%">
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-body start-indent="0pt">
										<fo:table-row>
											<fo:table-cell border="solid 1pt white"
												border-spacing="1pt" padding="2pt" display-align="center">
												<fo:block font-family="arialuni" font-size="10px">
													<fo:inline>
														<xsl:text>&#160;</xsl:text>
													</fo:inline>
													<xsl:for-each
														select="//PHS398_ResearchTrainingProgramPlan_3_0:PHS398_ResearchTrainingProgramPlan_3_0">
														<xsl:for-each
															select="PHS398_ResearchTrainingProgramPlan_3_0:ResearchTrainingProgramPlanAttachments">
															<xsl:for-each
																select="PHS398_ResearchTrainingProgramPlan_3_0:ResponsibleConductOfResearch">
																<xsl:for-each
																	select="PHS398_ResearchTrainingProgramPlan_3_0:attFile">
																	<xsl:for-each select="att:FileName">
																		<xsl:variable name="value-of-template">
																			<xsl:apply-templates />
																		</xsl:variable>
																		<xsl:choose>
																			<xsl:when
																				test="contains(string($value-of-template),'&#x2029;')">
																				<fo:block>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:block>
																			</xsl:when>
																			<xsl:otherwise>
																				<fo:inline>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:inline>
																			</xsl:otherwise>
																		</xsl:choose>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
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
					
					<!-- Plan for instruction in Methods for Enhancing Reproducibility -->
					<fo:table-row>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="6pt"
							display-align="left">
							<fo:block font-family="arialuni" font-size="9px">
								4. Plan for Instruction in Methods for Enhancing Reproducibility
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="6pt"
							display-align="left">
							<fo:block>
								<fo:inline-container>
									<fo:block>
										<xsl:text>&#x2029;</xsl:text>
									</fo:block>
								</fo:inline-container>
								<fo:table font-size="9px" table-layout="fixed" width="100%">
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-body start-indent="0pt">
										<fo:table-row>
											<fo:table-cell border="solid 1pt white"
												border-spacing="1pt" padding="2pt" display-align="center">
												<fo:block font-family="arialuni" font-size="10px">
													<fo:inline>
														<xsl:text>&#160;</xsl:text>
													</fo:inline>
													<xsl:for-each
														select="//PHS398_ResearchTrainingProgramPlan_3_0:PHS398_ResearchTrainingProgramPlan_3_0">
														<xsl:for-each
															select="PHS398_ResearchTrainingProgramPlan_3_0:ResearchTrainingProgramPlanAttachments">
															<xsl:for-each
																select="PHS398_ResearchTrainingProgramPlan_3_0:MethodsForEnhancingReproducibility">
																<xsl:for-each
																	select="PHS398_ResearchTrainingProgramPlan_3_0:attFile">
																	<xsl:for-each select="att:FileName">
																		<xsl:variable name="value-of-template">
																			<xsl:apply-templates />
																		</xsl:variable>
																		<xsl:choose>
																			<xsl:when
																				test="contains(string($value-of-template),'&#x2029;')">
																				<fo:block>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:block>
																			</xsl:when>
																			<xsl:otherwise>
																				<fo:inline>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:inline>
																			</xsl:otherwise>
																		</xsl:choose>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
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
					

				<!-- Multiple PD/PI Leadership Plan (if applicable) -->
					<fo:table-row>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block font-family="arialuni" font-size="9px">
								5. Multiple PD/PI Leadership Plan(if applicable)
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block>
								<fo:inline-container>
									<fo:block>
										<xsl:text>&#x2029;</xsl:text>
									</fo:block>
								</fo:inline-container>
								<fo:table font-size="9px" table-layout="fixed" width="100%">
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-body start-indent="0pt">
										<fo:table-row>
											<fo:table-cell border="solid 1pt white"
												border-spacing="1pt" padding="2pt" display-align="center">
												<fo:block font-family="arialuni" font-size="10px">
													<fo:inline>
														<xsl:text>&#160;</xsl:text>
													</fo:inline>
													<xsl:for-each
														select="//PHS398_ResearchTrainingProgramPlan_3_0:PHS398_ResearchTrainingProgramPlan_3_0">
														<xsl:for-each
															select="PHS398_ResearchTrainingProgramPlan_3_0:ResearchTrainingProgramPlanAttachments">
															<xsl:for-each
																select="PHS398_ResearchTrainingProgramPlan_3_0:MultiplePDPILeadershipPlan">
																<xsl:for-each
																	select="PHS398_ResearchTrainingProgramPlan_3_0:attFile">
																	<xsl:for-each select="att:FileName">
																		<xsl:variable name="value-of-template">
																			<xsl:apply-templates />
																		</xsl:variable>
																		<xsl:choose>
																			<xsl:when
																				test="contains(string($value-of-template),'&#x2029;')">
																				<fo:block>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:block>
																			</xsl:when>
																			<xsl:otherwise>
																				<fo:inline>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:inline>
																			</xsl:otherwise>
																		</xsl:choose>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
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
					
						<!-- Progress Report (for RENEWAL applications only) -->
					<fo:table-row>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="6pt"
							display-align="left">
							<fo:block font-family="arialuni" font-size="9px">
								6. Progress Report 								
							</fo:block>
							<fo:block font-size="6px">&#160;(for RENEWAL applications only)</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="6pt"
							display-align="left">
							<fo:block>
								<fo:inline-container>
									<fo:block>
										<xsl:text>&#x2029;</xsl:text>
									</fo:block>
								</fo:inline-container>
								<fo:table font-size="9px" table-layout="fixed" width="100%">
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-body start-indent="0pt">
										<fo:table-row>
											<fo:table-cell border="solid 1pt white"
												border-spacing="1pt" padding="2pt" display-align="center">
												<fo:block font-family="arialuni" font-size="10px">
													<fo:inline>
														<xsl:text>&#160;</xsl:text>
													</fo:inline>
													<xsl:for-each
														select="//PHS398_ResearchTrainingProgramPlan_3_0:PHS398_ResearchTrainingProgramPlan_3_0">
														<xsl:for-each
															select="PHS398_ResearchTrainingProgramPlan_3_0:ResearchTrainingProgramPlanAttachments">
															<xsl:for-each
																select="PHS398_ResearchTrainingProgramPlan_3_0:ProgressReport">
																<xsl:for-each
																	select="PHS398_ResearchTrainingProgramPlan_3_0:attFile">
																	<xsl:for-each select="att:FileName">
																		<xsl:variable name="value-of-template">
																			<xsl:apply-templates />
																		</xsl:variable>
																		<xsl:choose>
																			<xsl:when
																				test="contains(string($value-of-template),'&#x2029;')">
																				<fo:block>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:block>
																			</xsl:when>
																			<xsl:otherwise>
																				<fo:inline>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:inline>
																			</xsl:otherwise>
																		</xsl:choose>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
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
					
						<fo:table-row>
                                  <fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline font-weight="bold" font-size="10px">
                                                                        <xsl:text>Faculty, Trainees and Training Record Section</xsl:text>
                                                                    </fo:inline>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
					
					<!-- Participating Faculty Biosketches -->
					<fo:table-row>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block font-family="arialuni" font-size="9px">
								7. Participating Faculty
								Biosketches
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block>
								<fo:inline-container>
									<fo:block>
										<xsl:text>&#x2029;</xsl:text>
									</fo:block>
								</fo:inline-container>
								<fo:table font-size="9px" table-layout="fixed" width="100%">
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-body start-indent="0pt">
										<fo:table-row>
											<fo:table-cell border="solid 1pt white"
												border-spacing="1pt" padding="2pt" display-align="center">
												<fo:block font-family="arialuni" font-size="10px">
													<fo:inline>
														<xsl:text>&#160;</xsl:text>
													</fo:inline>
													<xsl:for-each
														select="//PHS398_ResearchTrainingProgramPlan_3_0:PHS398_ResearchTrainingProgramPlan_3_0">
														<xsl:for-each
															select="PHS398_ResearchTrainingProgramPlan_3_0:ResearchTrainingProgramPlanAttachments">
															<xsl:for-each
																select="PHS398_ResearchTrainingProgramPlan_3_0:ParticipatingFacultyBiosketches">
																<xsl:for-each
																	select="PHS398_ResearchTrainingProgramPlan_3_0:attFile">
																	<xsl:for-each select="att:FileName">
																		<xsl:variable name="value-of-template">
																			<xsl:apply-templates />
																		</xsl:variable>
																		<xsl:choose>
																			<xsl:when
																				test="contains(string($value-of-template),'&#x2029;')">
																				<fo:block>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:block>
																			</xsl:when>
																			<xsl:otherwise>
																				<fo:inline>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:inline>
																			</xsl:otherwise>
																		</xsl:choose>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
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
					
					
					<!-- Letters of Support -->
					<fo:table-row>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block font-family="arialuni" font-size="9px">
								8. Letters of Support
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block>
								<fo:inline-container>
									<fo:block>
										<xsl:text>&#x2029;</xsl:text>
									</fo:block>
								</fo:inline-container>
								<fo:table font-size="9px" table-layout="fixed" width="100%">
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-body start-indent="0pt">
										<fo:table-row>
											<fo:table-cell border="solid 1pt white"
												border-spacing="1pt" padding="2pt" display-align="center">
												<fo:block font-family="arialuni" font-size="10px">
													<fo:inline>
														<xsl:text>&#160;</xsl:text>
													</fo:inline>
													<xsl:for-each
														select="//PHS398_ResearchTrainingProgramPlan_3_0:PHS398_ResearchTrainingProgramPlan_3_0">
														<xsl:for-each
															select="PHS398_ResearchTrainingProgramPlan_3_0:ResearchTrainingProgramPlanAttachments">
															<xsl:for-each
																select="PHS398_ResearchTrainingProgramPlan_3_0:LettersOfSupport">
																<xsl:for-each
																	select="PHS398_ResearchTrainingProgramPlan_3_0:attFile">
																	<xsl:for-each select="att:FileName">
																		<xsl:variable name="value-of-template">
																			<xsl:apply-templates />
																		</xsl:variable>
																		<xsl:choose>
																			<xsl:when
																				test="contains(string($value-of-template),'&#x2029;')">
																				<fo:block>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:block>
																			</xsl:when>
																			<xsl:otherwise>
																				<fo:inline>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:inline>
																			</xsl:otherwise>
																		</xsl:choose>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
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
					
					<!-- Data Tables -->
					<fo:table-row>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block font-family="arialuni" font-size="9px">
								9. Data Tables
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block>
								<fo:inline-container>
									<fo:block>
										<xsl:text>&#x2029;</xsl:text>
									</fo:block>
								</fo:inline-container>
								<fo:table font-size="9px" table-layout="fixed" width="100%">
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-body start-indent="0pt">
										<fo:table-row>
											<fo:table-cell border="solid 1pt white"
												border-spacing="1pt" padding="2pt" display-align="center">
												<fo:block font-family="arialuni" font-size="10px">
													<fo:inline>
														<xsl:text>&#160;</xsl:text>
													</fo:inline>
													<xsl:for-each
														select="//PHS398_ResearchTrainingProgramPlan_3_0:PHS398_ResearchTrainingProgramPlan_3_0">
														<xsl:for-each
															select="PHS398_ResearchTrainingProgramPlan_3_0:ResearchTrainingProgramPlanAttachments">
															<xsl:for-each
																select="PHS398_ResearchTrainingProgramPlan_3_0:DataTables">
																<xsl:for-each
																	select="PHS398_ResearchTrainingProgramPlan_3_0:attFile">
																	<xsl:for-each select="att:FileName">
																		<xsl:variable name="value-of-template">
																			<xsl:apply-templates />
																		</xsl:variable>
																		<xsl:choose>
																			<xsl:when
																				test="contains(string($value-of-template),'&#x2029;')">
																				<fo:block>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:block>
																			</xsl:when>
																			<xsl:otherwise>
																				<fo:inline>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:inline>
																			</xsl:otherwise>
																		</xsl:choose>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
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
						<fo:table-row>
                                  <fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline font-weight="bold" font-size="10px">
                                                                        <xsl:text>Other Training Program Section</xsl:text>
                                                                    </fo:inline>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
					
					<!-- Human Subjects -->
					<fo:table-row>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block font-family="arialuni" font-size="9px">
								10. Human Subjects
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block>
								<fo:inline-container>
									<fo:block>
										<xsl:text>&#x2029;</xsl:text>
									</fo:block>
								</fo:inline-container>
								<fo:table font-size="9px" table-layout="fixed" width="100%">
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-body start-indent="0pt">
										<fo:table-row>
											<fo:table-cell border="solid 1pt white"
												border-spacing="1pt" padding="2pt" display-align="center">
												<fo:block font-family="arialuni" font-size="10px">
													<fo:inline>
														<xsl:text>&#160;</xsl:text>
													</fo:inline>
													<xsl:for-each
														select="//PHS398_ResearchTrainingProgramPlan_3_0:PHS398_ResearchTrainingProgramPlan_3_0">
														<xsl:for-each
															select="PHS398_ResearchTrainingProgramPlan_3_0:ResearchTrainingProgramPlanAttachments">
															<xsl:for-each
																select="PHS398_ResearchTrainingProgramPlan_3_0:HumanSubjects">
																<xsl:for-each
																	select="PHS398_ResearchTrainingProgramPlan_3_0:attFile">
																	<xsl:for-each select="att:FileName">
																		<xsl:variable name="value-of-template">
																			<xsl:apply-templates />
																		</xsl:variable>
																		<xsl:choose>
																			<xsl:when
																				test="contains(string($value-of-template),'&#x2029;')">
																				<fo:block>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:block>
																			</xsl:when>
																			<xsl:otherwise>
																				<fo:inline>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:inline>
																			</xsl:otherwise>
																		</xsl:choose>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
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
					
					<!-- Data safety Monitoring Plan Subjects -->
					<fo:table-row>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block font-family="arialuni" font-size="9px">
								11. Data Safety Monitoring Plan
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block>
								<fo:inline-container>
									<fo:block>
										<xsl:text>&#x2029;</xsl:text>
									</fo:block>
								</fo:inline-container>
								<fo:table font-size="9px" table-layout="fixed" width="100%">
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-body start-indent="0pt">
										<fo:table-row>
											<fo:table-cell border="solid 1pt white"
												border-spacing="1pt" padding="2pt" display-align="center">
												<fo:block font-family="arialuni" font-size="10px">
													<fo:inline>
														<xsl:text>&#160;</xsl:text>
													</fo:inline>
													<xsl:for-each
														select="//PHS398_ResearchTrainingProgramPlan_3_0:PHS398_ResearchTrainingProgramPlan_3_0">
														<xsl:for-each
															select="PHS398_ResearchTrainingProgramPlan_3_0:ResearchTrainingProgramPlanAttachments">
															<xsl:for-each
																select="PHS398_ResearchTrainingProgramPlan_3_0:DataSafetyMonitoringPlan">
																<xsl:for-each
																	select="PHS398_ResearchTrainingProgramPlan_3_0:attFile">
																	<xsl:for-each select="att:FileName">
																		<xsl:variable name="value-of-template">
																			<xsl:apply-templates />
																		</xsl:variable>
																		<xsl:choose>
																			<xsl:when
																				test="contains(string($value-of-template),'&#x2029;')">
																				<fo:block>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:block>
																			</xsl:when>
																			<xsl:otherwise>
																				<fo:inline>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:inline>
																			</xsl:otherwise>
																		</xsl:choose>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
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
					
					<!-- Vertebrate Animals -->
					<fo:table-row>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block font-family="arialuni" font-size="9px">
								12. Vertebrate Animals
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block>
								<fo:inline-container>
									<fo:block>
										<xsl:text>&#x2029;</xsl:text>
									</fo:block>
								</fo:inline-container>
								<fo:table font-size="9px" table-layout="fixed" width="100%">
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-body start-indent="0pt">
										<fo:table-row>
											<fo:table-cell border="solid 1pt white"
												border-spacing="1pt" padding="2pt" display-align="center">
												<fo:block font-family="arialuni" font-size="10px">
													<fo:inline>
														<xsl:text>&#160;</xsl:text>
													</fo:inline>
													<xsl:for-each
														select="//PHS398_ResearchTrainingProgramPlan_3_0:PHS398_ResearchTrainingProgramPlan_3_0">
														<xsl:for-each
															select="PHS398_ResearchTrainingProgramPlan_3_0:ResearchTrainingProgramPlanAttachments">
															<xsl:for-each
																select="PHS398_ResearchTrainingProgramPlan_3_0:VertebrateAnimals">
																<xsl:for-each
																	select="PHS398_ResearchTrainingProgramPlan_3_0:attFile">
																	<xsl:for-each select="att:FileName">
																		<xsl:variable name="value-of-template">
																			<xsl:apply-templates />
																		</xsl:variable>
																		<xsl:choose>
																			<xsl:when
																				test="contains(string($value-of-template),'&#x2029;')">
																				<fo:block>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:block>
																			</xsl:when>
																			<xsl:otherwise>
																				<fo:inline>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:inline>
																			</xsl:otherwise>
																		</xsl:choose>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
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
					
						<!-- Select Agent Research -->
					<fo:table-row>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block font-family="arialuni" font-size="9px">
								13. Select Agent Research
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block>
								<fo:inline-container>
									<fo:block>
										<xsl:text>&#x2029;</xsl:text>
									</fo:block>
								</fo:inline-container>
								<fo:table font-size="9px" table-layout="fixed" width="100%">
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-body start-indent="0pt">
										<fo:table-row>
											<fo:table-cell border="solid 1pt white"
												border-spacing="1pt" padding="2pt" display-align="center">
												<fo:block font-family="arialuni" font-size="10px">
													<fo:inline>
														<xsl:text>&#160;</xsl:text>
													</fo:inline>
													<xsl:for-each
														select="//PHS398_ResearchTrainingProgramPlan_3_0:PHS398_ResearchTrainingProgramPlan_3_0">
														<xsl:for-each
															select="PHS398_ResearchTrainingProgramPlan_3_0:ResearchTrainingProgramPlanAttachments">
															<xsl:for-each
																select="PHS398_ResearchTrainingProgramPlan_3_0:SelectAgentResearch">
																<xsl:for-each
																	select="PHS398_ResearchTrainingProgramPlan_3_0:attFile">
																	<xsl:for-each select="att:FileName">
																		<xsl:variable name="value-of-template">
																			<xsl:apply-templates />
																		</xsl:variable>
																		<xsl:choose>
																			<xsl:when
																				test="contains(string($value-of-template),'&#x2029;')">
																				<fo:block>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:block>
																			</xsl:when>
																			<xsl:otherwise>
																				<fo:inline>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:inline>
																			</xsl:otherwise>
																		</xsl:choose>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
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
					
					
					<!-- Consortium/Contractual Arrangements -->
					<fo:table-row>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block font-family="arialuni" font-size="9px">
								14. Consortium/Contractual
								Arrangements
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="2pt" padding-after="2pt"
							display-align="left">
							<fo:block>
								<fo:inline-container>
									<fo:block>
										<xsl:text>&#x2029;</xsl:text>
									</fo:block>
								</fo:inline-container>
								<fo:table font-size="9px" table-layout="fixed" width="100%">
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-body start-indent="0pt">
										<fo:table-row>
											<fo:table-cell border="solid 1pt white"
												border-spacing="1pt" padding="2pt" display-align="center">
												<fo:block font-family="arialuni" font-size="10px">
													<fo:inline>
														<xsl:text>&#160;</xsl:text>
													</fo:inline>
													<xsl:for-each
														select="//PHS398_ResearchTrainingProgramPlan_3_0:PHS398_ResearchTrainingProgramPlan_3_0">
														<xsl:for-each
															select="PHS398_ResearchTrainingProgramPlan_3_0:ResearchTrainingProgramPlanAttachments">
															<xsl:for-each
																select="PHS398_ResearchTrainingProgramPlan_3_0:ConsortiumContractualArrangements">
																<xsl:for-each
																	select="PHS398_ResearchTrainingProgramPlan_3_0:attFile">
																	<xsl:for-each select="att:FileName">
																		<xsl:variable name="value-of-template">
																			<xsl:apply-templates />
																		</xsl:variable>
																		<xsl:choose>
																			<xsl:when
																				test="contains(string($value-of-template),'&#x2029;')">
																				<fo:block>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:block>
																			</xsl:when>
																			<xsl:otherwise>
																				<fo:inline>
																					<xsl:copy-of select="$value-of-template" />
																				</fo:inline>
																			</xsl:otherwise>
																		</xsl:choose>
																	</xsl:for-each>
																</xsl:for-each>
															</xsl:for-each>
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
			<fo:table width="100%" table-layout="fixed" border-style="solid" border-color="black" border-top-width="0pt" border-bottom-width="1pt" border-left-width="1pt" border-right-width="1pt"
				border-spacing="1pt">
				<fo:table-column column-width="40%" />
				<fo:table-column column-width="60%" />
				<fo:table-body start-indent="0pt">
					<fo:table-row>
                                  <fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
                                                                <fo:block>
                                                                    <fo:inline font-weight="bold" font-size="10px">
                                                                        <xsl:text>Appendix</xsl:text>
                                                                    </fo:inline>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
					<!-- Appendix -->
					<fo:table-row>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="1pt" padding-after="2pt"
							display-align="before">
							<fo:block font-family="arialuni" font-size="9px">
								15. Appendix
							</fo:block>
						</fo:table-cell>
						<fo:table-cell text-align="left" padding-start="3pt"
							padding-end="3pt" padding-before="1pt" padding-after="2pt"
							display-align="left">
							<fo:block font-family="arialuni" font-size="10px">
								<fo:inline-container>
									<fo:block>
										<xsl:text>&#x2029;</xsl:text>
									</fo:block>
								</fo:inline-container>
								<xsl:if
									test="//PHS398_ResearchTrainingProgramPlan_3_0:PHS398_ResearchTrainingProgramPlan_3_0/PHS398_ResearchTrainingProgramPlan_3_0:ResearchTrainingProgramPlanAttachments/PHS398_ResearchTrainingProgramPlan_3_0:Appendix/att:AttachedFile">
									<fo:table font-size="9px" table-layout="fixed" width="100%">
										<fo:table-column column-width="proportional-column-width(1)" />
										<fo:table-body start-indent="0pt">
											<xsl:for-each
												select="//PHS398_ResearchTrainingProgramPlan_3_0:PHS398_ResearchTrainingProgramPlan_3_0">
												<xsl:for-each
													select="PHS398_ResearchTrainingProgramPlan_3_0:ResearchTrainingProgramPlanAttachments">
													<xsl:for-each
														select="PHS398_ResearchTrainingProgramPlan_3_0:Appendix">
														<xsl:for-each select="att:AttachedFile">
															<fo:table-row>
																<fo:table-cell border="solid 1pt white"
																	padding="2pt" display-align="center">
																	<fo:block font-family="arialuni">
																		<xsl:for-each select="att:FileName">
																			<xsl:variable name="value-of-template">
																				<xsl:apply-templates />
																			</xsl:variable>
																			<xsl:choose>
																				<xsl:when
																					test="contains(string($value-of-template),'&#x2029;')">
																					<fo:block>
																						<xsl:copy-of select="$value-of-template" />
																					</fo:block>
																				</xsl:when>
																				<xsl:otherwise>
																					<fo:inline>
																						<xsl:copy-of select="$value-of-template" />
																					</fo:inline>
																				</xsl:otherwise>
																			</xsl:choose>
																		</xsl:for-each>
																	</fo:block>
																</fo:table-cell>
															</fo:table-row>
														</xsl:for-each>
													</xsl:for-each>
												</xsl:for-each>
											</xsl:for-each>
										</fo:table-body>
									</fo:table>
								</xsl:if>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
		</fo:block>
	</xsl:template>

	<xsl:template name="double-backslash">
		<xsl:param name="text" />
		<xsl:param name="text-length" />
		<xsl:variable name="text-after-bs" select="substring-after($text, '\')" />
		<xsl:variable name="text-after-bs-length" select="string-length($text-after-bs)" />
		<xsl:choose>
			<xsl:when test="$text-after-bs-length = 0">
				<xsl:choose>
					<xsl:when test="substring($text, $text-length) = '\'">
						<xsl:value-of select="concat(substring($text,1,$text-length - 1), '\\')" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="$text" />
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of
					select="concat(substring($text,1,$text-length - $text-after-bs-length - 1), '\\')" />
				<xsl:call-template name="double-backslash">
					<xsl:with-param name="text" select="$text-after-bs" />
					<xsl:with-param name="text-length" select="$text-after-bs-length" />
				</xsl:call-template>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>