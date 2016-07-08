<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:PHS_Fellowship_Supplemental_3_1="http://apply.grants.gov/forms/PHS_Fellowship_Supplemental_3_1-V3.1" 
xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:codes="http://apply.grants.gov/system/UniversalCodes-V2.0" 
xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" 
xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" 
xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
xmlns:header="http://apply.grants.gov/system/Header-V1.0"
xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:fo="http://www.w3.org/1999/XSL/Format">

	<xsl:param name="SV_OutputFormat" select="'PDF'"/>
	<xsl:variable name="XML" select="/"/>
	<xsl:variable name="fo:layout-master-set">
		<fo:layout-master-set>
			<fo:simple-page-master master-name="default-page" page-height="11.0in" page-width="8.5in" margin-left="0.35in" margin-right="0.35in">
				<fo:region-body margin-top="0.5in" margin-bottom="0.5in"/>
				<fo:region-before extent="0.7in"/>
				<fo:region-after extent=".3in" />
			</fo:simple-page-master>
		</fo:layout-master-set>
	</xsl:variable>
	<xsl:template match="/">
		<fo:root>
			<xsl:copy-of select="$fo:layout-master-set"/>
			<fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
				<xsl:call-template name="headerall"/>
            <fo:static-content flow-name="xsl-region-after">
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
							<fo:inline-container>
								<fo:block>
									<xsl:text>&#x2029;</xsl:text>
								</fo:block>
							</fo:inline-container>
							
							<!-- Added static section on Page 1 for OMB Number / Expiration Date -->
							<fo:table font-family="Verdana" table-layout="fixed" width="100%" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row font-size="6px">
										<fo:table-cell height="17" margin-top="1pt" padding="0" padding-top="0pt" number-columns-spanned="2" text-align="right" display-align="before">
											<fo:block>
												<fo:inline>
													<xsl:text>OMB Number: 0925-0001</xsl:text>
												</fo:inline>
											</fo:block>
											<fo:block>
											    <fo:inline>
											        <xsl:text>Expiration Date: 10/31/2018</xsl:text>
											    </fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>							
							
							<fo:table font-family="Helvetica" font-size="9px" table-layout="fixed" width="100%" border="solid 1pt black" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									
									<!-- Deleted Section A.Application Type -->
									
									<!-- Pasted Introduction section START -->
									<fo:table-row>
										<fo:table-cell border="solid 1pt black" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
													<fo:table-column column-width="40%"/>
													<fo:table-column column-width="60%"/>
													<fo:table-body start-indent="0pt">
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline font-weight="bold" font-size="10px">
																		<xsl:text>Introduction</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>1. Introduction</xsl:text>
																	</fo:inline>
																	<fo:block/>
																	<fo:inline font-size="6px">
																		<xsl:text>(RESUBMISSION)</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table font-family="arialuni" font-size="9px" table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Introduction">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:IntroductionToApplication">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																										<xsl:for-each select="att:FileName">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:for-each>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>									
									<!-- Pasted Introduction section END -->

									<!-- Pasted BackgroundAndGoals section START -->
									<fo:table-row>
										<fo:table-cell border="solid 1pt black" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
													<fo:table-column column-width="40%"/>
													<fo:table-column column-width="60%"/>
													<fo:table-body start-indent="0pt">
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline font-weight="bold" font-size="10px">
																		<xsl:text>Fellowship Applicant Section</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>2. Applicant's Background and Goals for Fellowship Training*</xsl:text>
																	</fo:inline>
																	<fo:block/>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table font-family="arialuni" font-size="9px" table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:FellowshipApplicant">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:BackgroundandGoals">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																										<xsl:for-each select="att:FileName">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:for-each>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>																		
									<!-- Pasted BackgroundAndGoals section END -->
									
									<fo:table-row>
										<fo:table-cell border="solid 1pt black" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
													<fo:table-column column-width="40%"/>
													<fo:table-column column-width="60%"/>
													<fo:table-body start-indent="0pt">
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline font-weight="bold" font-size="10px">
																		<xsl:text>Research Training Plan Section</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<!-- Deleted Introduction to Application from this section -->
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>3. Specific Aims*</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table font-family="arialuni" font-size="9px" table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:ResearchTrainingPlan">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:SpecificAims">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																										<xsl:for-each select="att:FileName">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>4. Research Strategy*</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table font-family="arialuni" font-size="9px" table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:ResearchTrainingPlan">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:ResearchStrategy">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																										<xsl:for-each select="att:FileName">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
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

														<!-- Respective Contributions START -->
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>5. Respective Contributions*</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table font-family="arialuni" font-size="9px" table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:ResearchTrainingPlan">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:RespectiveContribution">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																										<xsl:for-each select="att:FileName">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
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
														<!-- Respective Contributions END -->
														
														<!-- Selection of Sponsor and Institution START -->
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>6. Selection of Sponsor and Institution*</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table font-family="arialuni" font-size="9px" table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:ResearchTrainingPlan">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:SponsorandInstitution">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																										<xsl:for-each select="att:FileName">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
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
														<!-- Selection of Sponsor and Institution END -->

														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>7. Progress Report Publication List</xsl:text>
																	</fo:inline>
																	<fo:block/>
																	<fo:inline font-size="6px">
																		<xsl:text>(RENEWAL)</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:ResearchTrainingPlan">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:ProgressReportPublicationList">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																										<xsl:for-each select="att:FileName">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
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
														
														<!-- Training in the Responsible Conduct of Research START -->
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>8. Training in the Responsible Conduct of Research*</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table font-family="arialuni" font-size="9px" table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:ResearchTrainingPlan">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:TrainingInResponsibleConductOfResearch">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																										<xsl:for-each select="att:FileName">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
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
														<!-- Training in the Responsible Conduct of Research END -->														
														
													</fo:table-body>
												</fo:table>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									
									<!-- Pasted Sponsors, Collaborators, Consultants section START -->
									<fo:table-row>
										<fo:table-cell border="solid 1pt black" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
													<fo:table-column column-width="40%"/>
													<fo:table-column column-width="60%"/>
													<fo:table-body start-indent="0pt">
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline font-weight="bold" font-size="10px">
																		<xsl:text>Sponsor(s), Collaborator(s) and Consultant(s) Section</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>9. Sponsor and Co-Sponsor Statements</xsl:text>
																	</fo:inline>
																	<fo:block/>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table font-family="arialuni" font-size="9px" table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Sponsors">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:SponsorAndCoSponsorStatements">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																										<xsl:for-each select="att:FileName">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:for-each>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																			</fo:table-row>
																		</fo:table-body>
																	</fo:table>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>														
														<!-- Added Subsection for Letters of Support -->
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>10. Letters of Support from Collaborators, Contributors and Consultants</xsl:text>
																	</fo:inline>
																	<fo:block/>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table font-family="arialuni" font-size="9px" table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Sponsors">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:LettersOfSupport">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																										<xsl:for-each select="att:FileName">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:for-each>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>																		
									<!-- Pasted Sponsors, Collaborators, Consultants section END -->
									
									<!-- Pasted Institutional Environment section START -->
									<fo:table-row>
										<fo:table-cell border="solid 1pt black" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
													<fo:table-column column-width="40%"/>
													<fo:table-column column-width="60%"/>
													<fo:table-body start-indent="0pt">
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline font-weight="bold" font-size="10px">
																		<xsl:text>Institutional Environment and Commitment to Training Section</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>11. Description of Institutional Environment and Commitment to Training</xsl:text>
																	</fo:inline>
																	<fo:block/>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table font-family="arialuni" font-size="9px" table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:InstitutionalEnvironment">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:InstitutionalEnvironmentCommitmenttoTraining">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																										<xsl:for-each select="att:FileName">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:for-each>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>																		
									<!-- Pasted Institutional Environment section END -->									
									
									<fo:table-row>
										<fo:table-cell border="solid 1pt black" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%">
													<fo:table-column column-width="40%"/>
													<fo:table-column column-width="60%"/>
													<fo:table-body start-indent="0pt">
													    <!-- Inserted heading "Other Research Training Plan Section" START -->
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline font-weight="bold" font-size="10px">
																		<xsl:text>Other Research Training Plan Section</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<!-- Inserted heading "Other Research Training Plan Section" END -->													    
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline font-size="9px" font-weight="bold">
																		<xsl:text>Human Subjects</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>Please note. The following item is taken from the Research &amp; Related Other Project Information form. The response provided on that page, regarding the involvement of human subjects, is repeated here for your reference as you provide related responses for this Fellowship application. If you wish to change the answer to the item shown below, please do so on the Research &amp; Related Other Project Information form; you will not be able to edit the response here.</xsl:text>
																						</fo:inline>
																						</fo:block>
                                                                                     	<fo:block padding-top="2pt" text-align="center">
																						<fo:inline>
																							<xsl:text>Are Human Subjects Involved? &#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:OtherResearchTrainingPlan">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:HumanSubjectsInvolved">
																									<xsl:choose>
																										<xsl:when test="string(.)='Y: Yes'">
																											<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																												<xsl:text>&#x2714;</xsl:text>
																											</fo:inline>
																										</xsl:when>
																										<xsl:otherwise>
																											<fo:inline border="solid 1pt black">
																												<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																											</fo:inline>
																										</xsl:otherwise>
																									</xsl:choose>
																									<fo:inline>
																										<xsl:text> Yes&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																									</fo:inline>
																									<xsl:choose>
																										<xsl:when test="string(.)='N: No'">
																											<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																												<xsl:text>&#x2714;</xsl:text>
																											</fo:inline>
																										</xsl:when>
																										<xsl:otherwise>
																											<fo:inline border="solid 1pt black">
																												<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																											</fo:inline>
																										</xsl:otherwise>
                                                                                                        </xsl:choose>
                                                                                                        <fo:inline>
                                                                                                            <xsl:text> No</xsl:text>
                                                                                                        </fo:inline>
                                                                                                    </xsl:for-each>
                                                                                                </xsl:for-each>
                                                                                            </xsl:for-each>
                                                                                            <fo:inline>
																							<xsl:text>&#160;&#160;&#160;&#160;&#160; </xsl:text>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																			</fo:table-row>
																		</fo:table-body>
																	</fo:table>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>12.  Human Subjects Involvement Indefinite?</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>&#160;</xsl:text>
																	</fo:inline>
																	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																		<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:OtherResearchTrainingPlan">
																			<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:HumanSubjectsIndefinite">
																				<xsl:choose>
																					<xsl:when test="string(.)='Y: Yes'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> Yes&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																				</fo:inline>
																				<xsl:choose>
																					<xsl:when test="string(.)='N: No'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> No</xsl:text>
																				</fo:inline>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>13. Clinical Trial?</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>&#160;</xsl:text>
																	</fo:inline>
																	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																		<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:OtherResearchTrainingPlan">
																			<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:ClinicalTrial">
																				<xsl:choose>
																					<xsl:when test="string(.)='Y: Yes'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> Yes&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																				</fo:inline>
																				<xsl:choose>
																					<xsl:when test="string(.)='N: No'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> No</xsl:text>
																				</fo:inline>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row padding-top="2pt">
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>14. Agency-Defined Phase III Clinical Trial?</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>&#160;</xsl:text>
																	</fo:inline>
																	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																		<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:OtherResearchTrainingPlan">
																			<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Phase3ClinicalTrial">
																				<xsl:choose>
																					<xsl:when test="string(.)='Y: Yes'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> Yes&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																				</fo:inline>
																				<xsl:choose>
																					<xsl:when test="string(.)='N: No'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> No</xsl:text>
																				</fo:inline>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>15. Protection of Human Subjects</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block font-family="arialuni">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:OtherResearchTrainingPlan">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:ProtectionOfHumanSubjects">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																										<xsl:for-each select="att:FileName">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
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
														<!-- Inserted Data Safety Monitoring Plan START -->
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>16. Data Safety Monitoring Plan</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block font-family="arialuni">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:OtherResearchTrainingPlan">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:DataSafetyMonitoringPlan">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																										<xsl:for-each select="att:FileName">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
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
														<!-- Inserted Data Safety Monitoring Plan END -->
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>17. Inclusion of Women and Minorities</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block font-family="arialuni">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:OtherResearchTrainingPlan">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:InclusionOfWomenAndMinorities">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																										<xsl:for-each select="att:FileName">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>18. Inclusion of Children</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block font-family="arialuni">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:OtherResearchTrainingPlan">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:InclusionOfChildren">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																										<xsl:for-each select="att:FileName">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell border="solid 1pt black" padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
													<fo:table-column column-width="40%"/>
													<fo:table-column column-width="60%"/>
													<fo:table-body start-indent="0pt">
														<!-- Removed the previous header above Vertebrate Animals for 'Other Research Training Plan' -->
														<!-- Added the sub-header for Vertebrate Animals -->
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline font-size="9px" font-weight="bold">
																		<xsl:text>Vertebrate Animals</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>														
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="before">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>The following item is taken from the Research &amp; Related Other Project Information form and repeated here for your reference. Any change to this item must be made on the Research &amp; Related Other Project Information form.</xsl:text>
																						</fo:inline>
																						</fo:block>
																						<fo:block text-align="center">
                                                                                        <fo:inline>
																							<xsl:text>Are Vertebrate Animals Used? &#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:OtherResearchTrainingPlan">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:VertebrateAnimalsUsed">
																									<xsl:choose>
																										<xsl:when test="string(.)='Y: Yes'">
																											<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																												<xsl:text>&#x2714;</xsl:text>
																											</fo:inline>
																										</xsl:when>
																										<xsl:otherwise>
																											<fo:inline border="solid 1pt black">
																												<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																											</fo:inline>
																										</xsl:otherwise>
																									</xsl:choose>
																									<fo:inline>
																										<xsl:text> Yes&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																									</fo:inline>
																									<xsl:choose>
																										<xsl:when test="string(.)='N: No'">
																											<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																												<xsl:text>&#x2714;</xsl:text>
																											</fo:inline>
																										</xsl:when>
																										<xsl:otherwise>
																											<fo:inline border="solid 1pt black">
																												<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																											</fo:inline>
																										</xsl:otherwise>
																									</xsl:choose>
																									<fo:inline>
																										<xsl:text> No</xsl:text>
																									</fo:inline>
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>19. Vertebrate Animals Use Indefinite?</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>&#160;</xsl:text>
																	</fo:inline>
																	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																		<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:OtherResearchTrainingPlan">
																			<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:VertebrateAnimalsIndefinite">
																				<xsl:choose>
																					<xsl:when test="string(.)='Y: Yes'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> Yes&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																				</fo:inline>
																				<xsl:choose>
																					<xsl:when test="string(.)='N: No'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> No</xsl:text>
																				</fo:inline>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
													<!-- End table on this page to start a new one on the next -->
													</fo:table-body>
												</fo:table>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
							
							<!-- ============================ --> 
							<!-- Next Page --> 
							<!-- ============================ --> 
							<fo:block break-after="page"> 
								<xsl:text>&#xA;</xsl:text> 
							</fo:block> 							

							<fo:table font-family="Helvetica" font-size="9px" table-layout="fixed" width="100%" border="solid 1pt black" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
													<fo:table-column column-width="40%"/>
													<fo:table-column column-width="60%"/>
													<fo:table-body start-indent="0pt">													
													
														<!-- Added Vertebrate Animals Euthanized Section START -->
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>20. Are vertebrate animals euthanized?</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>&#160;</xsl:text>
																	</fo:inline>
																	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																		<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:OtherResearchTrainingPlan">
																			<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AreAnimalsEuthanized">
																				<xsl:choose>
																					<xsl:when test="string(.)='Y: Yes'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> Yes&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																				</fo:inline>
																				<xsl:choose>
																					<xsl:when test="string(.)='N: No'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> No</xsl:text>
																				</fo:inline>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>														
														<!-- Added Vertebrate Animals Euthanized Section END -->														
														
														<!-- Added Method Consistent with AVMA Guidelines Padding-Left:17pt START -->
														<fo:table-row>
															<!-- Added cell block for "If Euthanized" Padding-Left:17pt START -->
															<fo:table-cell padding-left="17pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>If "Yes" to euthanasia</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<!-- Added cell block for "If Euthanized" END -->
															<fo:table-cell padding-left="17pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Is method consistent with American Veterinary Medical Association (AVMA) guidelines?</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>&#160;</xsl:text>
																	</fo:inline>
																	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																		<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:OtherResearchTrainingPlan">
																			<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AVMAConsistentIndicator">
																				<xsl:choose>
																					<xsl:when test="string(.)='Y: Yes'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> Yes&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																				</fo:inline>
																				<xsl:choose>
																					<xsl:when test="string(.)='N: No'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> No</xsl:text>
																				</fo:inline>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>														
														<!-- Added Method Consistent with AVMA Guidelines END -->
														<!-- Added Method and Scientific Justification Padding-Left:17pt START -->
														<fo:table-row>
															<fo:table-cell padding-left="17pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>If "No" to AVMA guidelines, describe method and provide scientific justification</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="5pt" display-align="center" height="auto">
																					<fo:block font-family="arialuni">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:OtherResearchTrainingPlan">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:EuthanasiaMethodDescription">
																									<!-- Method Description START -->
																									<xsl:variable name="value-of-template">
																										<xsl:apply-templates/>
																									</xsl:variable>
																									<xsl:choose>
																										<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																											<fo:block>
																												<xsl:copy-of select="$value-of-template"/>
																											</fo:block>
																										</xsl:when>
																										<xsl:otherwise>
																											<fo:inline>
																												<xsl:copy-of select="$value-of-template"/>
																											</fo:inline>
																										</xsl:otherwise>
																									</xsl:choose>																				
																								<!-- Method Description END -->
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
														<!-- Added Method and Scientific Justification END -->														
																												
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>21. Vertebrate Animals</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block font-family="arialuni">
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:OtherResearchTrainingPlan">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:VertebrateAnimals">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																										<xsl:for-each select="att:FileName">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>

									<fo:table-row>
										<fo:table-cell border="solid 1pt black" padding="2pt" display-align="center">
											<fo:block>														
												<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
													<fo:table-column column-width="40%"/>
													<fo:table-column column-width="60%"/>
													<fo:table-body start-indent="0pt">														
														<!-- Inserted divider and new sub-header "Other Research Training Plan Information" START -->
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">

																<fo:block>
																	<fo:inline font-size="9px" font-weight="bold">
																		<xsl:text>Other Research Training Plan Information</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>																												
														<!-- Inserted divider and new sub-header "Other Research Training Plan Information" END -->
														
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>22. Select Agent Research</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:OtherResearchTrainingPlan">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:SelectAgentResearch">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																										<xsl:for-each select="att:FileName">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>23. Resource Sharing Plan</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:OtherResearchTrainingPlan">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:ResourceSharingPlan">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																										<xsl:for-each select="att:FileName">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>24. Authentication of Key Biological and/or Chemical Resources</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:OtherResearchTrainingPlan">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:KeyBiologicalAndOrChemicalResources">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																										<xsl:for-each select="att:FileName">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
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
														
														<!-- Removed extra elements under 'Other Research Training Plan Information'   -->
														
													</fo:table-body>
												</fo:table>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
                                 </fo:table-body>   
                                </fo:table>
                                
                                
                	<fo:block break-after="page"> 
						<xsl:text>&#xA;</xsl:text> 
					</fo:block> 
					    
							<fo:table font-family="Helvetica" font-size="9px" table-layout="fixed" width="100%" border="solid 1pt black" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
													<fo:table-column column-width="40%"/>
													<fo:table-column column-width="60%"/>
													<fo:table-body start-indent="0pt">
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline font-weight="bold" size="10px">
																		<xsl:text>Additional Information Section</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline font-weight="bold" font-size="9px">
																		<xsl:text>25. Human Embryonic Stem Cells</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Does the proposed project involve human embryonic stem cells?*&#160;&#160; </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																		<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																			<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																				<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:isHumanStemCellsInvolved">
																					<xsl:choose>
																						<xsl:when test="string(.)='Y: Yes'">
																							<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																								<xsl:text>&#x2714;</xsl:text>
																							</fo:inline>
																						</xsl:when>
																						<xsl:otherwise>
																							<fo:inline border="solid 1pt black">
																								<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																							</fo:inline>
																						</xsl:otherwise>
																					</xsl:choose>
																					<fo:inline>
																						<xsl:text> Yes&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																					</fo:inline>
																					<xsl:choose>
																						<xsl:when test="string(.)='N: No'">
																							<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																								<xsl:text>&#x2714;</xsl:text>
																							</fo:inline>
																						</xsl:when>
																						<xsl:otherwise>
																							<fo:inline border="solid 1pt black">
																								<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																							</fo:inline>
																						</xsl:otherwise>
																					</xsl:choose>
																					<fo:inline>
																						<xsl:text> No</xsl:text>
																					</fo:inline>
																				</xsl:for-each>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>If the proposed project involves human embryonic stem cells, list below the registration number of the specific cell line(s), using the registry information provided within the agency instructions. Or, if a specific stem cell line cannot be referenced at this time, please check the box indicating that one from the registry will be used:</xsl:text>
																	</fo:inline>
																</fo:block>
                                                                <fo:block padding-top="2pt">
																	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																		<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																			<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																				<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCellsIndicator">
																					<xsl:choose>
																						<xsl:when test="string(.)='Y: Yes'">
																							<fo:inline font-family="ZapfDingbats" font-size="9px" border="solid 1pt black">
																								<xsl:text>&#x2714;</xsl:text>
																							</fo:inline>
																						</xsl:when>
																						<xsl:otherwise>
																							<fo:inline border="solid 1pt black">
																								<fo:leader leader-length="10pt" leader-pattern="space"/>
																							</fo:inline>
																						</xsl:otherwise>
																					</xsl:choose>
																				</xsl:for-each>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																	<fo:inline>
																		<xsl:text>&#160;&#160; Specific stem cell line cannot be referenced at this time. One from the registry will be used.</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Cell Line(s):</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 1">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 2">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 3">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 4">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																			</fo:table-row>
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 5">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 6">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 7">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 8">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																			</fo:table-row>
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 9">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 10">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 11">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 12">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																			</fo:table-row>
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 13">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 14">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 15">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 16">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																			</fo:table-row>
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 17">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 18">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 19">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block border="solid 1pt black">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StemCells">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CellLines">
																										<xsl:if test="position() = 20">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:if>
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
														
														<!-- Removed Table Break before Alternate Phone Number -->
														
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>26. Alternate Phone Number:&#160; </xsl:text>
																	</fo:inline>
                                                                        <xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
                                                                            <xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
                                                                                <xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AlernatePhoneNumber">
                                                                                    <xsl:variable name="value-of-template">
                                                                                        <xsl:apply-templates/>
                                                                                    </xsl:variable>
                                                                                    <xsl:choose>
                                                                                        <xsl:when test="contains(string($value-of-template),'&#x2029;')">
                                                                                            <fo:block>
                                                                                                <xsl:copy-of select="$value-of-template"/>
                                                                                            </fo:block>
                                                                                        </xsl:when>
                                                                                        <xsl:otherwise>
                                                                                            <fo:inline>
                                                                                                <xsl:copy-of select="$value-of-template"/>
                                                                                            </fo:inline>
                                                                                        </xsl:otherwise>
                                                                                    </xsl:choose>
                                                                                </xsl:for-each>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="after">
																<fo:block>
																	<fo:inline>
																		<xsl:text>27. Degree Sought During Proposed Award:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" display-align="center">
																<fo:block>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="38%"/>
																		<fo:table-column column-width="30%"/>
																		<fo:table-column column-width="32%"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="after">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>Degree:</xsl:text>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="after">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>If &quot;other&quot;, please indicate degree type:</xsl:text>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="after">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>Expected Completion Date (month/year):</xsl:text>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																			</fo:table-row>
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:table table-layout="fixed" width="100%">
																							<fo:table-column column-width="proportional-column-width(1)"/>
																							<fo:table-body start-indent="0pt">
																								<fo:table-row>
																									<fo:table-cell display-align="center">
																										<fo:block font-family="arialuni" border-bottom="dotted 1pt gray">
																											<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																												<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																													<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:GraduateDegreeSought">
																														<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:DegreeType">
																															<xsl:variable name="value-of-template">
																																<xsl:apply-templates/>
																															</xsl:variable>
																															<xsl:choose>
																																<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																																	<fo:block>
																																		<xsl:copy-of select="$value-of-template"/>
																																	</fo:block>
																																</xsl:when>
																																<xsl:otherwise>
																																	<fo:block>
																																		<xsl:copy-of select="$value-of-template"/><xsl:text>&#160;</xsl:text>
																																	</fo:block>
																																</xsl:otherwise>
																															</xsl:choose>
																														</xsl:for-each>
																													</xsl:for-each>
																													<!-- Keep blank space here if section missing -->
																													<xsl:if test="not(//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1/PHS_Fellowship_Supplemental_3_1:AdditionalInformation/PHS_Fellowship_Supplemental_3_1:GraduateDegreeSought/PHS_Fellowship_Supplemental_3_1:DegreeType)">
																																																																																							<fo:block>
																															<xsl:text>&#160;</xsl:text>
																														</fo:block>
																													</xsl:if>																													
																												</xsl:for-each>
																											</xsl:for-each>
																										</fo:block>
																									</fo:table-cell>
																								</fo:table-row>
																							</fo:table-body>
																						</fo:table>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell display-align="center">
																					<fo:block>
																						<fo:table table-layout="fixed" width="100%">
																							<fo:table-column column-width="proportional-column-width(1)"/>
																							<fo:table-body start-indent="0pt">
																								<fo:table-row>
																									<fo:table-cell padding="2pt" display-align="center">
																										<fo:block font-family="arialuni"  border-bottom="dotted 1pt gray">
                                                                                                        	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																												<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																													<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:GraduateDegreeSought">
																														<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:OtherDegreeTypeText">
																															<xsl:variable name="value-of-template">
																																<xsl:apply-templates/>
																															</xsl:variable>
																															<xsl:choose>
																																<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																																	<fo:block>
																																		<xsl:copy-of select="$value-of-template"/>
																																	</fo:block>
																																</xsl:when>
																																<xsl:otherwise>
																																	<fo:block>
																																		<xsl:copy-of select="$value-of-template"/><xsl:text>&#160;</xsl:text>
																																	</fo:block>
																																</xsl:otherwise>
																															</xsl:choose>
																														</xsl:for-each>
																													</xsl:for-each>
																													<!-- Keep blank space here if section missing -->
																													<xsl:if test="not(//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1/PHS_Fellowship_Supplemental_3_1:AdditionalInformation/PHS_Fellowship_Supplemental_3_1:GraduateDegreeSought/PHS_Fellowship_Supplemental_3_1:OtherDegreeTypeText)">
																																																																																							<fo:block>
																															<xsl:text>&#160;</xsl:text>
																														</fo:block>
																													</xsl:if>
																												</xsl:for-each>
																											</xsl:for-each>
																										</fo:block>
																									</fo:table-cell>
																								</fo:table-row>
																							</fo:table-body>
																						</fo:table>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell display-align="center">
																					<fo:block>
																						<fo:table table-layout="fixed" width="100%">
																							<fo:table-column column-width="proportional-column-width(1)"/>
																							<fo:table-body start-indent="0pt">
																								<fo:table-row>
																									<fo:table-cell display-align="center">
																										<fo:block font-family="arialuni"  border-bottom="dotted 1pt gray">
                                                                                                        	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																												<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																													<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:GraduateDegreeSought">
																														<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:DegreeDate">
																															<xsl:variable name="value-of-template">
																																<xsl:apply-templates/>
																															</xsl:variable>
																															<xsl:choose>
																																<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																																	<fo:block>
																																		<xsl:copy-of select="$value-of-template"/>
																																	</fo:block>
																																</xsl:when>
																																<xsl:otherwise>
																																	<fo:block>
																																		<xsl:copy-of select="$value-of-template"/><xsl:text>&#160;</xsl:text>
																																	</fo:block>
																																</xsl:otherwise>
																															</xsl:choose>
																														</xsl:for-each>
																													</xsl:for-each>
																													<!-- Keep blank space here if section missing -->
																													<xsl:if test="not(//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1/PHS_Fellowship_Supplemental_3_1:AdditionalInformation/PHS_Fellowship_Supplemental_3_1:GraduateDegreeSought/PHS_Fellowship_Supplemental_3_1:DegreeDate)">
																																																																																							<fo:block>
																															<xsl:text>&#160;</xsl:text>
																														</fo:block>
																													</xsl:if>																													
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
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>28. Field of Training for Current Proposal*:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																					
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:FieldOfTraining">
																									<xsl:variable name="value-of-template">
																										<xsl:apply-templates/>
																									</xsl:variable>
																									<xsl:choose>
																										<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																											<fo:block>
																												<xsl:copy-of select="$value-of-template"/>
																											</fo:block>
																										</xsl:when>
																										<xsl:otherwise>
																											<fo:inline>
																												<xsl:copy-of select="$value-of-template"/>
																											</fo:inline>
																										</xsl:otherwise>
																									</xsl:choose>
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>29. Current Or Prior Kirschstein-NRSA Support?*&#160;&#160;&#160;&#160;&#160; </xsl:text>
																	</fo:inline>
                                                                    </fo:block>
                                                                   </fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block> 
																	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																		<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																			<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupportIndicator">
																				<xsl:choose>
																					<xsl:when test="string(.)='Y: Yes'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> Yes&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																				</fo:inline>
																				<xsl:choose>
																					<xsl:when test="string(.)='N: No'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> No</xsl:text>
																				</fo:inline>
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
																<fo:block font-style="italic">
																	<fo:inline>
																		<xsl:text>If yes, please identify current and prior Kirschstein-NRSA support below:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="18%"/>
																		<fo:table-column column-width="20%"/>
																		<fo:table-column column-width="18%"/>
																		<fo:table-column column-width="18%"/>
																		<fo:table-column column-width="26%"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>Level*</xsl:text>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>Type*</xsl:text>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>Start Date (if known)</xsl:text>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>End Date (if known)</xsl:text>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>Grant Number (if known)</xsl:text>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																			</fo:table-row>
																			<fo:table-row>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() = 1">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Level">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:for-each>
																									</xsl:if>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() =1">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Type">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:for-each>
																									</xsl:if>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() =1">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StartDate">
																											<fo:inline>
																												<xsl:value-of select="format-number(number(substring(string(string(.)), 6, 2)), '00')"/>
																												<xsl:text>/</xsl:text>
																												<xsl:value-of select="format-number(number(substring(string(string(.)), 9, 2)), '00')"/>
																												<xsl:text>/</xsl:text>
																												<xsl:value-of select="format-number(number(substring(string(string(string(.))), 1, 4)), '0000')"/>
																											</fo:inline>
																										</xsl:for-each>
																									</xsl:if>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() =1">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:EndDate">
																											<fo:inline>
																												<xsl:value-of select="format-number(number(substring(string(string(.)), 6, 2)), '00')"/>
																												<xsl:text>/</xsl:text>
																												<xsl:value-of select="format-number(number(substring(string(string(.)), 9, 2)), '00')"/>
																												<xsl:text>/</xsl:text>
																												<xsl:value-of select="format-number(number(substring(string(string(string(.))), 1, 4)), '0000')"/>
																											</fo:inline>
																										</xsl:for-each>
																									</xsl:if>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() =1">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:GrantNumber">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:for-each>
																									</xsl:if>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																			</fo:table-row>
																			<fo:table-row>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() = 2">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Level">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:for-each>
																									</xsl:if>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() =2">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Type">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:for-each>
																									</xsl:if>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() =2">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StartDate">
																											<fo:inline>
																												<xsl:value-of select="format-number(number(substring(string(string(.)), 6, 2)), '00')"/>
																												<xsl:text>/</xsl:text>
																												<xsl:value-of select="format-number(number(substring(string(string(.)), 9, 2)), '00')"/>
																												<xsl:text>/</xsl:text>
																												<xsl:value-of select="format-number(number(substring(string(string(string(.))), 1, 4)), '0000')"/>
																											</fo:inline>
																										</xsl:for-each>
																									</xsl:if>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() =2">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:EndDate">
																											<fo:inline>
																												<xsl:value-of select="format-number(number(substring(string(string(.)), 6, 2)), '00')"/>
																												<xsl:text>/</xsl:text>
																												<xsl:value-of select="format-number(number(substring(string(string(.)), 9, 2)), '00')"/>
																												<xsl:text>/</xsl:text>
																												<xsl:value-of select="format-number(number(substring(string(string(string(.))), 1, 4)), '0000')"/>
																											</fo:inline>
																										</xsl:for-each>
																									</xsl:if>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() =2">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:GrantNumber">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:for-each>
																									</xsl:if>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																			</fo:table-row>
																			<fo:table-row>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() = 3">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Level">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:for-each>
																									</xsl:if>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() =3">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Type">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:for-each>
																									</xsl:if>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() =3">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StartDate">
																											<fo:inline>
																												<xsl:value-of select="format-number(number(substring(string(string(.)), 6, 2)), '00')"/>
																												<xsl:text>/</xsl:text>
																												<xsl:value-of select="format-number(number(substring(string(string(.)), 9, 2)), '00')"/>
																												<xsl:text>/</xsl:text>
																												<xsl:value-of select="format-number(number(substring(string(string(string(.))), 1, 4)), '0000')"/>
																											</fo:inline>
																										</xsl:for-each>
																									</xsl:if>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() =3">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:EndDate">
																											<fo:inline>
																												<xsl:value-of select="format-number(number(substring(string(string(.)), 6, 2)), '00')"/>
																												<xsl:text>/</xsl:text>
																												<xsl:value-of select="format-number(number(substring(string(string(.)), 9, 2)), '00')"/>
																												<xsl:text>/</xsl:text>
																												<xsl:value-of select="format-number(number(substring(string(string(string(.))), 1, 4)), '0000')"/>
																											</fo:inline>
																										</xsl:for-each>
																									</xsl:if>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() =3">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:GrantNumber">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:for-each>
																									</xsl:if>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																			</fo:table-row>
																			<fo:table-row>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() = 4">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Level">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:for-each>
																									</xsl:if>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() =4">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Type">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:for-each>
																									</xsl:if>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() =4">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:StartDate">
																											<fo:inline>
																												<xsl:value-of select="format-number(number(substring(string(string(.)), 6, 2)), '00')"/>
																												<xsl:text>/</xsl:text>
																												<xsl:value-of select="format-number(number(substring(string(string(.)), 9, 2)), '00')"/>
																												<xsl:text>/</xsl:text>
																												<xsl:value-of select="format-number(number(substring(string(string(string(.))), 1, 4)), '0000')"/>
																											</fo:inline>
																										</xsl:for-each>
																									</xsl:if>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() =4">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:EndDate">
																											<fo:inline>
																												<xsl:value-of select="format-number(number(substring(string(string(.)), 6, 2)), '00')"/>
																												<xsl:text>/</xsl:text>
																												<xsl:value-of select="format-number(number(substring(string(string(.)), 9, 2)), '00')"/>
																												<xsl:text>/</xsl:text>
																												<xsl:value-of select="format-number(number(substring(string(string(string(.))), 1, 4)), '0000')"/>
																											</fo:inline>
																										</xsl:for-each>
																									</xsl:if>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border-bottom="dotted 1pt gray" padding-top="2pt" padding-bottom="2pt" padding-right="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:CurrentPriorNRSASupport">
																									<xsl:if test="position() =4">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:GrantNumber">
																											<xsl:variable name="value-of-template">
																												<xsl:apply-templates/>
																											</xsl:variable>
																											<xsl:choose>
																												<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																													<fo:block>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:block>
																												</xsl:when>
																												<xsl:otherwise>
																													<fo:inline>
																														<xsl:copy-of select="$value-of-template"/>
																													</fo:inline>
																												</xsl:otherwise>
																											</xsl:choose>
																										</xsl:for-each>
																									</xsl:if>
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
														<fo:table-row padding-top="3pt">
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>30. Applications for Concurrent Support?*&#160;&#160; </xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>&#160;</xsl:text>
																	</fo:inline>
																	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																		<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																			<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:ConcurrentSupport">
																				<xsl:choose>
																					<xsl:when test="string(.)='Y: Yes'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> Yes&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																				</fo:inline>
																				<xsl:choose>
																					<xsl:when test="string(.)='N: No'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> No</xsl:text>
																				</fo:inline>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block font-style="italic">
																	<fo:inline>
																		<xsl:text>If yes, please describe in an attached file:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block font-family="arialuni">
																	<fo:inline>
																		<xsl:text>&#160;</xsl:text>
																	</fo:inline>
																	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																		<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																			<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:ConcurrentSupportDescription">
																				<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:attFile">
																					<xsl:for-each select="att:FileName">
																						<xsl:variable name="value-of-template">
																							<xsl:apply-templates/>
																						</xsl:variable>
																						<xsl:choose>
																							<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																								<fo:block>
																									<xsl:copy-of select="$value-of-template"/>
																								</fo:block>
																							</xsl:when>
																							<xsl:otherwise>
																								<fo:inline>
																									<xsl:copy-of select="$value-of-template"/>
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
														
														<!-- Removed extra attachments before Citizenship -->
														
														<!-- New Citizenship section -->
														<fo:table-row padding-top="3pt">
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>31. Citizenship*&#160;&#160; </xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>														
														<fo:table-row padding-top="3pt">
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>U.S. Citizen&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</xsl:text>
																	</fo:inline>
																	<fo:inline>U.S. Citizen or Non-Citizen National?</fo:inline>																	
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>&#160;</xsl:text>
																	</fo:inline>
																	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																		<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																			<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:USCitizen">
																				<xsl:choose>
																					<xsl:when test="string(.)='Y: Yes'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> Yes&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																				</fo:inline>
																				<xsl:choose>
																					<xsl:when test="string(.)='N: No'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> No</xsl:text>
																				</fo:inline>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>

														<fo:table-row padding-top="3pt">
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Non-U.S. Citizen</xsl:text>
																	</fo:inline>																
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>&#160;</xsl:text>
																	</fo:inline>
																	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																		<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																			<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:NonUSCitizen">
																				<xsl:choose>
																					<xsl:when test="string(.)='With a Permanent U.S. Resident Visa'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> With a Permanent U.S. Resident Visa</xsl:text>
																				</fo:inline>
																			</xsl:for-each>
																			<!-- Also display a blank box if section missing -->
																			<xsl:if test="not(//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1/PHS_Fellowship_Supplemental_3_1:AdditionalInformation/PHS_Fellowship_Supplemental_3_1:NonUSCitizen)">
																				<fo:inline border="solid 1pt black">
																					<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																				</fo:inline>
																				<fo:inline>
																					<xsl:text> With a Permanent U.S. Resident Visa</xsl:text>
																				</fo:inline>																				
																			</xsl:if>
																		</xsl:for-each>
																	</xsl:for-each>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>				
														
														<fo:table-row padding-top="3pt">
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>&#160;</xsl:text>
																	</fo:inline>																
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>&#160;</xsl:text>
																	</fo:inline>
																	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																		<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																			<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:NonUSCitizen">
																				<xsl:choose>
																					<xsl:when test="string(.)='With a Temporary U.S. Visa'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																				<fo:inline>
																					<xsl:text> With a Temporary U.S. Visa</xsl:text>
																				</fo:inline>
																			</xsl:for-each>
																			<!-- Also display a blank box if section missing -->
																			<xsl:if test="not(//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1/PHS_Fellowship_Supplemental_3_1:AdditionalInformation/PHS_Fellowship_Supplemental_3_1:NonUSCitizen)">
																				<fo:inline border="solid 1pt black">
																					<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																				</fo:inline>
																				<fo:inline>
																					<xsl:text> With a Temporary U.S. Visa</xsl:text>
																				</fo:inline>																				
																			</xsl:if>																				
																		</xsl:for-each>
																	</xsl:for-each>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>				

														<fo:table-row padding-top="3pt">
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>If you are a non-U.S. citizen with a temporary visa who has applied for permanent resident status and expect to hold a permanent resident visa by the earliest possible start date of the award, please also check here.&#160;&#160;</xsl:text>
																	</fo:inline>																	 
																	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																		<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																			<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:PermanentResidentByAwardIndicator">
																				<xsl:choose>
																					<xsl:when test="string(.)='Y: Yes'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																			</xsl:for-each>
																			<!-- Also display a blank box if section missing -->
																			<xsl:if test="not(//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1/PHS_Fellowship_Supplemental_3_1:AdditionalInformation/PHS_Fellowship_Supplemental_3_1:PermanentResidentByAwardIndicator)">
																				<fo:inline border="solid 1pt black">
																					<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																				</fo:inline>																				
																			</xsl:if>																																								
																		</xsl:for-each>
																	</xsl:for-each>																		
																</fo:block>
															</fo:table-cell>
														</fo:table-row>														
																																			
														<!-- Removed Institution separator -->
														
														<fo:table-row>
															<fo:table-cell number-rows-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>32.&#160; </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																		<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																			<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:ChangeOfInstitution">
																				<xsl:choose>
																					<xsl:when test="string(.)='Y: Yes'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="10pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																	<fo:inline>
																		<xsl:text> Change of Sponsoring Institution</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="after">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Name of Former Institution:*</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block font-family="arialuni">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AdditionalInformation">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:FormerInstitution">
																									<xsl:variable name="value-of-template">
																										<xsl:apply-templates/>
																									</xsl:variable>
																									<xsl:choose>
																										<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																											<fo:block>
																												<xsl:copy-of select="$value-of-template"/>
																											</fo:block>
																										</xsl:when>
																										<xsl:otherwise>
																											<fo:inline>
																												<xsl:copy-of select="$value-of-template"/>
																											</fo:inline>
																										</xsl:otherwise>
																									</xsl:choose>
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
                         		</fo:table-body>   
                      		</fo:table>
                           
                                
                	<fo:block break-after="page"> 
						<xsl:text>&#xA;</xsl:text> 
					</fo:block> 
					    
							<fo:table font-family="Helvetica" font-size="9px" table-layout="fixed" width="100%" border="solid 1pt black" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>

												<!-- Removed Sponsor section at the bottom -->
												<fo:table table-layout="fixed" width="100%">
													<fo:table-column column-width="40%"/>
													<fo:table-column column-width="60%"/>
													<fo:table-body start-indent="0pt">
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline font-weight="bold" font-size="10px">
																		<xsl:text>Budget Section</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline font-weight="bold">
																		<xsl:text>All Fellowship Applicants:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block/>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>1. Tuition and Fees*:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block/>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>&#160;</xsl:text>
																	</fo:inline>
																	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																		<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Budget">
																			<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:TuitionAndFeesRequested">
																				<xsl:choose>
																					<xsl:when test="string(.)='N: No'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																	<fo:inline>
																		<xsl:text> None Requested&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
																	</fo:inline>
																	<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																		<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Budget">
																			<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:TuitionAndFeesRequested">
																				<xsl:choose>
																					<xsl:when test="string(.)='Y: Yes'">
																						<fo:inline border="solid 1pt black" font-family="ZapfDingbats" font-size="9px ">
																							<xsl:text>&#x2714;</xsl:text>
																						</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline border="solid 1pt black">
																							<fo:leader leader-length="7.5pt" leader-pattern="space"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																			</xsl:for-each>
																		</xsl:for-each>
																	</xsl:for-each>
																	<fo:inline>
																		<xsl:text> Funds Requested </xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block/>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell font-size="9px" padding-left="130pt" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Year 1</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding-right="100pt" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Budget">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:TuitionRequestedYear1">
																									<fo:inline>
																										<xsl:text>$</xsl:text>
																									</fo:inline>
																									<fo:inline>
																										<xsl:value-of select="format-number(number(string(.)), '#,###,###,##0.00')"/>
																									</fo:inline>
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
															<fo:table-cell font-size="9px" padding-left="130pt" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Year 2</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding-right="100pt" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Budget">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:TuitionRequestedYear2">
																									<fo:inline>
																										<xsl:text>$</xsl:text>
																									</fo:inline>
																									<fo:inline>
																										<xsl:value-of select="format-number(number(string(.)), '#,###,###,##0.00')"/>
																									</fo:inline>
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
															<fo:table-cell font-size="9px" padding-left="130pt" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Year 3</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding-right="100pt" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Budget">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:TuitionRequestedYear3">
																									<fo:inline>
																										<xsl:text>$</xsl:text>
																									</fo:inline>
																									<fo:inline>
																										<xsl:value-of select="format-number(number(string(.)), '#,###,###,##0.00')"/>
																									</fo:inline>
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
															<fo:table-cell font-size="9px" padding-left="130pt" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Year 4</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding-right="100pt" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Budget">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:TuitionRequestedYear4">
																									<fo:inline>
																										<xsl:text>$</xsl:text>
																									</fo:inline>
																									<fo:inline>
																										<xsl:value-of select="format-number(number(string(.)), '#,###,###,##0.00')"/>
																									</fo:inline>
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
															<fo:table-cell font-size="9px" padding-left="130pt" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Year 5</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding-right="100pt" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Budget">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:TuitionRequestedYear5">
																									<fo:inline>
																										<xsl:text>$</xsl:text>
																									</fo:inline>
																									<fo:inline>
																										<xsl:value-of select="format-number(number(string(.)), '#,###,###,##0.00')"/>
																									</fo:inline>
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
															<fo:table-cell font-size="9px" padding-left="60pt" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Year 6 (when applicable)</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding-right="100pt" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Budget">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:TuitionRequestedYear6">
																									<fo:inline>
																										<xsl:text>$</xsl:text>
																									</fo:inline>
																									<fo:inline>
																										<xsl:value-of select="format-number(number(string(.)), '#,###,###,##0.00')"/>
																									</fo:inline>
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
															<fo:table-cell font-size="9px" padding-left="57pt" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline font-weight="bold">
																		<xsl:text>Total Funds Requested:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding-right="100pt" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Budget">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:TuitionRequestedTotal">
																									<fo:inline>
																										<xsl:text>$</xsl:text>
																									</fo:inline>
																									<fo:inline>
																										<xsl:value-of select="format-number(number(string(.)), '#,###,###,##0.00')"/>
																									</fo:inline>
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
													
													
							<!-- Start new table for Senior Fellowship -->
							<fo:table font-family="Helvetica" font-size="9px" table-layout="fixed" width="100%" border="solid 1pt black" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>

												<!-- Removed Sponsor section at the bottom -->
												<fo:table table-layout="fixed" width="100%">
													<fo:table-column column-width="40%"/>
													<fo:table-column column-width="60%"/>
													<fo:table-body start-indent="0pt">														
														
														
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline font-weight="bold" font-size="9px">
																		<xsl:text>Senior Fellowship Applicants Only:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>2. Present Institutional Base Salary:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table border="0" table-layout="fixed" width="100%" border-spacing="2pt">
																		<fo:table-column column-width="45%"/>
																		<fo:table-column column-width="30%"/>
																		<fo:table-column column-width="25%"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell border="0" padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline font-family="Helvetica" font-size="9px">
																							<xsl:text>Amount</xsl:text>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border="0" padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline font-family="Helvetica" font-size="9px">
																							<xsl:text>Academic Period</xsl:text>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border="0" padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline font-family="Helvetica" font-size="9px">
																							<xsl:text>Number of Months</xsl:text>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																			</fo:table-row>
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Budget">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:InstitutionalBaseSalary">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Amount">
																										<fo:inline>
																											<xsl:text>$</xsl:text>
																										</fo:inline>
																										<fo:inline>
																											<xsl:value-of select="format-number(number(string(.)), '#,###,###,##0.00')"/>
																										</fo:inline>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Budget">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:InstitutionalBaseSalary">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:AcademicPeriod">
																										<xsl:variable name="value-of-template">
																											<xsl:apply-templates/>
																										</xsl:variable>
																										<xsl:choose>
																											<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																												<fo:block>
																													<xsl:copy-of select="$value-of-template"/>
																												</fo:block>
																											</xsl:when>
																											<xsl:otherwise>
																												<fo:inline>
																													<xsl:copy-of select="$value-of-template"/>
																												</fo:inline>
																											</xsl:otherwise>
																										</xsl:choose>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Budget">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:InstitutionalBaseSalary">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:NumberOfMonths">
																										<xsl:variable name="value-of-template">
																											<xsl:apply-templates/>
																										</xsl:variable>
																										<xsl:choose>
																											<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																												<fo:block>
																													<xsl:copy-of select="$value-of-template"/>
																												</fo:block>
																											</xsl:when>
																											<xsl:otherwise>
																												<fo:inline>
																													<xsl:copy-of select="$value-of-template"/>
																												</fo:inline>
																											</xsl:otherwise>
																										</xsl:choose>
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
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="after">
																<fo:block>
																	<fo:inline>
																		<xsl:text>3. Stipends/Salary During First Year of Proposed Fellowship:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>&#160;&#160; a. Federal Stipend Requested:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table border="0" table-layout="fixed" width="100%">
																		<fo:table-column column-width="45%"/>
																		<fo:table-column column-width="30%"/>
																		<fo:table-column column-width="25%"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell border="0" padding="2pt" height="19" display-align="after">
																					<fo:block>
																						<fo:inline font-family="Helvetica" font-size="9px">
																							<xsl:text>Amount</xsl:text>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border="0" padding="2pt" height="19" display-align="after">
																					<fo:block>
																						<fo:inline font-family="Helvetica" font-size="9px">
																							<xsl:text>Number of Months</xsl:text>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border="0" border-right-style="none" padding="2pt" height="19" display-align="center">
																					<fo:block/>
																				</fo:table-cell>
																			</fo:table-row>
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Budget">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:FederalStipendRequested">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Amount">
																										<fo:inline>
																											<xsl:text>$</xsl:text>
																										</fo:inline>
																										<fo:inline>
																											<xsl:value-of select="format-number(number(string(.)), '#,###,###,##0.00')"/>
																										</fo:inline>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Budget">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:FederalStipendRequested">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:NumberOfMonths">
																										<xsl:variable name="value-of-template">
																											<xsl:apply-templates/>
																										</xsl:variable>
																										<xsl:choose>
																											<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																												<fo:block>
																													<xsl:copy-of select="$value-of-template"/>
																												</fo:block>
																											</xsl:when>
																											<xsl:otherwise>
																												<fo:inline>
																													<xsl:copy-of select="$value-of-template"/>
																												</fo:inline>
																											</xsl:otherwise>
																										</xsl:choose>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border="0" padding="2pt" display-align="center">
																					<fo:block/>
																				</fo:table-cell>
																			</fo:table-row>
																		</fo:table-body>
																	</fo:table>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline>
																		<xsl:text>&#160;&#160; b. Supplementation from other sources:</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table border="0" table-layout="fixed" width="100%" border-spacing="2pt">
																		<fo:table-column column-width="45%"/>
																		<fo:table-column column-width="30%"/>
																		<fo:table-column column-width="25%"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell border="0" font-family="Helvetica" font-size="9px" padding="2pt" display-align="after">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>Amount</xsl:text>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border="0" font-family="Helvetica" font-size="9px" padding="2pt" display-align="after">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>Number of Months</xsl:text>
																						</fo:inline>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border="0" padding="2pt" display-align="center">
																					<fo:block/>
																				</fo:table-cell>
																			</fo:table-row>
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Budget">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:SupplementationFromOtherSources">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Amount">
																										<fo:inline>
																											<xsl:text>$</xsl:text>
																										</fo:inline>
																										<fo:inline>
																											<xsl:value-of select="format-number(number(string(.)), '#,###,###,##0.00')"/>
																										</fo:inline>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block>
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Budget">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:SupplementationFromOtherSources">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:NumberOfMonths">
																										<xsl:variable name="value-of-template">
																											<xsl:apply-templates/>
																										</xsl:variable>
																										<xsl:choose>
																											<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																												<fo:block>
																													<xsl:copy-of select="$value-of-template"/>
																												</fo:block>
																											</xsl:when>
																											<xsl:otherwise>
																												<fo:inline>
																													<xsl:copy-of select="$value-of-template"/>
																												</fo:inline>
																											</xsl:otherwise>
																										</xsl:choose>
																									</xsl:for-each>
																								</xsl:for-each>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell border="0" padding="2pt" display-align="center">
																					<fo:block/>
																				</fo:table-cell>
																			</fo:table-row>
																		</fo:table-body>
																	</fo:table>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block/>
															</fo:table-cell>
															<fo:table-cell font-family="Helvetica" font-size="9px" padding="2pt" display-align="after">
																<fo:block>
																	<fo:inline>
																		<xsl:text>&#160;</xsl:text>
																	</fo:inline>
																	<fo:block/>
																	<fo:inline>
																		<xsl:text>Type (sabbatical leave, salary, etc.)</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block/>
															</fo:table-cell>
															<fo:table-cell padding-right="25%" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block font-family="arialuni">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Budget">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:SupplementationFromOtherSources">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Type">
																										<xsl:variable name="value-of-template">
																											<xsl:apply-templates/>
																										</xsl:variable>
																										<xsl:choose>
																											<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																												<fo:block>
																													<xsl:copy-of select="$value-of-template"/>
																												</fo:block>
																											</xsl:when>
																											<xsl:otherwise>
																												<fo:inline>
																													<xsl:copy-of select="$value-of-template"/>
																												</fo:inline>
																											</xsl:otherwise>
																										</xsl:choose>
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
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block/>
															</fo:table-cell>
															<fo:table-cell font-family="Helvetica" font-size="9px" padding="2pt" display-align="after">
																<fo:block>
																	<fo:inline>
																		<xsl:text>Source</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block/>
															</fo:table-cell>
															<fo:table-cell padding-right="10%" padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																				<fo:table-cell padding="2pt" display-align="center">
																					<fo:block font-family="arialuni">
																						<fo:inline>
																							<xsl:text>&#160;</xsl:text>
																						</fo:inline>
																						<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																							<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Budget">
																								<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:SupplementationFromOtherSources">
																									<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Source">
																										<xsl:variable name="value-of-template">
																											<xsl:apply-templates/>
																										</xsl:variable>
																										<xsl:choose>
																											<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																												<fo:block>
																													<xsl:copy-of select="$value-of-template"/>
																												</fo:block>
																											</xsl:when>
																											<xsl:otherwise>
																												<fo:inline>
																													<xsl:copy-of select="$value-of-template"/>
																												</fo:inline>
																											</xsl:otherwise>
																										</xsl:choose>
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
													
													
							<!-- Start new table for Appendix -->
							<fo:table font-family="Helvetica" font-size="9px" table-layout="fixed" width="100%" border="solid 1pt black" border-spacing="2pt">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">									
									
									<fo:table-row>
										<fo:table-cell padding="2pt" display-align="center">
											<fo:block>
												<fo:inline-container>
													<fo:block>
														<xsl:text>&#x2029;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%">
													<fo:table-column column-width="40%"/>
													<fo:table-column column-width="60%"/>
													<fo:table-body start-indent="0pt">
														<fo:table-row>
															<fo:table-cell padding="2pt" display-align="before">
																<fo:block>
																	<fo:inline font-weight="bold" font-size="10px">
																		<xsl:text>Appendix</xsl:text>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding="2pt" display-align="center">
																<fo:block>
																	<fo:inline-container>
																		<fo:block>
																			<xsl:text>&#x2029;</xsl:text>
																		</fo:block>
																	</fo:inline-container>
																	<fo:table table-layout="fixed" width="100%">
																		<fo:table-column column-width="proportional-column-width(1)"/>
																		<fo:table-body start-indent="0pt">
																			<fo:table-row>
																			
																			    <!-- New Appendix Table cell -->
																				<fo:table-cell padding="1pt" display-align="before">
																					<fo:block>
																						<fo:inline-container>
																							<fo:block>
																								<xsl:text>&#x2029;</xsl:text>
																							</fo:block>
																						</fo:inline-container>
																						<xsl:if test="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1/PHS_Fellowship_Supplemental_3_1:Appendix/att:AttachedFile">
																							<fo:table font-family="arialuni" font-size="9px" table-layout="fixed" width="100%">
																								<fo:table-column column-width="proportional-column-width(1)"/>
																								<fo:table-body start-indent="0pt">
																									<xsl:for-each select="//PHS_Fellowship_Supplemental_3_1:PHS_Fellowship_Supplemental_3_1">
																										<xsl:for-each select="PHS_Fellowship_Supplemental_3_1:Appendix">
																											<xsl:for-each select="att:AttachedFile">
																												<fo:table-row>
																													<fo:table-cell padding="1pt" display-align="center">
																														<fo:block>
																															<xsl:for-each select="att:FileName"> 
																																<xsl:variable name="value-of-template">
																																	<xsl:apply-templates/>
																																</xsl:variable>
																																<xsl:choose>
																																	<xsl:when test="contains(string($value-of-template),'&#x2029;')">
																																		<fo:block>
																																			<xsl:copy-of select="$value-of-template"/>
																																		</fo:block>
																																	</xsl:when>
																																	<xsl:otherwise>
																																		<fo:inline>
																																			<xsl:copy-of select="$value-of-template"/>
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
																								</fo:table-body>
																							</fo:table>
																						</xsl:if>
																					</fo:block>
																				</fo:table-cell>
																			    
																			<!-- New Appendix Table cell  END -->
																			</fo:table-row>
																		</fo:table-body>
																	</fo:table>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding="2pt" display-align="center">
																<fo:block/>
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
					</fo:block>
					<fo:block id="SV_RefID_PageTotal"/>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	<xsl:template name="headerall">
		<fo:static-content flow-name="xsl-region-before">
			<fo:block>
				<fo:inline-container>
					<fo:block>
						<xsl:text>&#x2029;</xsl:text>
					</fo:block>
				</fo:inline-container>
				<fo:table font-family="Verdana" table-layout="fixed" width="100%" border-spacing="2pt">
					<fo:table-column column-width="proportional-column-width(1)"/>
					<fo:table-column column-width="proportional-column-width(1)"/>
					<fo:table-body start-indent="0pt">
						<fo:table-row height="3pt">
							<fo:table-cell height="15pt" number-columns-spanned="2" padding="2pt" display-align="center">
								<fo:block/>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell margin-top="1pt" padding="0" padding-top="0pt" number-columns-spanned="2" text-align="center" display-align="before">
								<fo:block>
									<fo:inline font-weight="bold" font-size="12px" font-family="Helvetica">
										<xsl:text>PHS Fellowship Supplemental Form</xsl:text>
									</fo:inline>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						
						<!-- Removed repeatable header with OMB Number / Expiration Date -->
						
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:static-content>
	</xsl:template>
	<xsl:template name="double-backslash">
		<xsl:param name="text"/>
		<xsl:param name="text-length"/>
		<xsl:variable name="text-after-bs" select="substring-after($text, '\')"/>
		<xsl:variable name="text-after-bs-length" select="string-length($text-after-bs)"/>
		<xsl:choose>
			<xsl:when test="$text-after-bs-length = 0">
				<xsl:choose>
					<xsl:when test="substring($text, $text-length) = '\'">
						<xsl:value-of select="concat(substring($text,1,$text-length - 1), '\\')"/>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="$text"/>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="concat(substring($text,1,$text-length - $text-after-bs-length - 1), '\\')"/>
				<xsl:call-template name="double-backslash">
					<xsl:with-param name="text" select="$text-after-bs"/>
					<xsl:with-param name="text-length" select="$text-after-bs-length"/>
				</xsl:call-template>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>