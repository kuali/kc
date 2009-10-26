<?xml version="1.0" encoding="UTF-8"?>
<!-- $Revision:   1.22  $ -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:RR_OtherProjectInfo="http://apply.grants.gov/forms/RR_OtherProjectInfo-V1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:header="http://devapply.row.com/system/Header-V1.0">
	<!--    -->
	<xsl:variable name="fo:layout-master-set">
		<fo:layout-master-set>
			<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.34in" margin-right="0.34in">
				<fo:region-body margin-top="0.5in" margin-bottom="0.5in" font-family="Helvetica,Times,Courier" font-size="8pt"/>
				<fo:region-after extent=".5in"/>
			</fo:simple-page-master>
		</fo:layout-master-set>
	</xsl:variable>
	<xsl:template match="RR_OtherProjectInfo:RR_OtherProjectInfo">
		<fo:root>
			<!-- -->
			<xsl:copy-of select="$fo:layout-master-set"/>
			<fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
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
				<!-- -->
				<!-- -->
				<fo:flow flow-name="xsl-region-body">
					<fo:block text-align="center" font-family="Helvetica,Times,Courier" font-size="11pt" font-weight="bold">
         RESEARCH &amp; RELATED Other Project Information
         <fo:block>&#160;</fo:block>
					</fo:block>
					<fo:table width="100%">
						<fo:table-column/>
						<fo:table-body>
							<!-- Block 1 Begin -->
							<fo:table-row font-size="8pt">
								<fo:table-cell line-height="15pt">
                                    <fo:block>
									<fo:table border-style="solid" border-color="black" width="100%">
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell line-height="15pt">
                                                    <fo:block>
													<fo:table width="100%">
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="2.0in"/>
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="0.5in"/>
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="0.5in"/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>1.</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>*  Are Human Subjects Involved?</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:for-each select="RR_OtherProjectInfo:HumanSubjectsIndicator">
																			<xsl:call-template name="radio">
																				<xsl:with-param name="value">
																					<xsl:value-of select="."/>
																				</xsl:with-param>
																				<xsl:with-param name="radio_on_value">Yes</xsl:with-param>
																			</xsl:call-template>
																		</xsl:for-each>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-size="8pt" font-weight="bold">
																	<fo:block>Yes</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:for-each select="RR_OtherProjectInfo:HumanSubjectsIndicator">
																			<xsl:call-template name="radio">
																				<xsl:with-param name="value">
																					<xsl:value-of select="."/>
																				</xsl:with-param>
																				<xsl:with-param name="radio_on_value">No</xsl:with-param>
																			</xsl:call-template>
																		</xsl:for-each>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>No</fo:block>
																</fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
                                                    </fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell padding-start="2pt" line-height="15pt" font-weight="bold">
													<fo:block>1.a. If YES to Human Subjects</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell line-height="15pt">
                                                    <fo:block>
													<fo:table width="100%">
														<fo:table-column column-width="2.2in"/>
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="0.5in"/>
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="0.5in"/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;Is the IRB review Pending?</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:call-template name="radio">
																			<xsl:with-param name="value">
																				<xsl:value-of select="RR_OtherProjectInfo:HumanSubjectsSupplement/RR_OtherProjectInfo:HumanSubjectIRBReviewIndicator"/>
																			</xsl:with-param>
																			<xsl:with-param name="radio_on_value">Yes</xsl:with-param>
																		</xsl:call-template>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-size="8pt" font-weight="bold">
																	<fo:block>Yes</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:call-template name="radio">
																			<xsl:with-param name="value">
																				<xsl:value-of select="RR_OtherProjectInfo:HumanSubjectsSupplement/RR_OtherProjectInfo:HumanSubjectIRBReviewIndicator"/>
																			</xsl:with-param>
																			<xsl:with-param name="radio_on_value">No</xsl:with-param>
																		</xsl:call-template>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>No</fo:block>
																</fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
                                                    </fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell line-height="15pt">
                                                    <fo:block>
													<fo:table width="100%">
														<fo:table-column column-width="2.0in"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;IRB Approval Date:</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:call-template name="formatDate">
																			<xsl:with-param name="value" select="RR_OtherProjectInfo:HumanSubjectsSupplement/RR_OtherProjectInfo:HumanSubjectIRBReviewDate"/>
																		</xsl:call-template>
																	</fo:block>
																</fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
                                                    </fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell line-height="15pt">
                                                    <fo:block>
													<fo:table width="100%">
														<fo:table-column column-width="1.5in"/>
														<fo:table-column column-width="3.0in"/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;Exemption Number:</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
                                                                    <fo:block>
																	<fo:table width="100%">
																		<fo:table-column column-width="0.2in"/>
																		<fo:table-column column-width="0.2in"/>
																		<fo:table-column column-width="0.2in"/>
																		<fo:table-column column-width="0.2in"/>
																		<fo:table-column column-width="0.2in"/>
																		<fo:table-column column-width="0.2in"/>
																		<fo:table-column column-width="0.2in"/>
																		<fo:table-column column-width="0.2in"/>
																		<fo:table-column column-width="0.2in"/>
																		<fo:table-column column-width="0.2in"/>
																		<fo:table-column column-width="0.2in"/>
																		<fo:table-column column-width="0.2in"/>
																		<fo:table-body>
																			<fo:table-row>
																				<fo:table-cell line-height="15pt">
																					<fo:block>
																						<xsl:choose>
																							<xsl:when test="RR_OtherProjectInfo:HumanSubjectsSupplement/RR_OtherProjectInfo:ExemptionNumbers/RR_OtherProjectInfo:ExemptionNumber = 'E1'">
																								<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt" text-decoration="underline" color="black">&#x2714;</fo:inline>
																							</xsl:when>
																							<xsl:otherwise>
																								<fo:inline text-decoration="underline" color="black">
																									<fo:leader leader-length="8pt" leader-pattern="rule"/>
																								</fo:inline>
																							</xsl:otherwise>
																						</xsl:choose>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell line-height="15pt">
																					<fo:block>1</fo:block>
																				</fo:table-cell>
																				<fo:table-cell line-height="15pt">
																					<fo:block>
																						<xsl:choose>
																							<xsl:when test="RR_OtherProjectInfo:HumanSubjectsSupplement/RR_OtherProjectInfo:ExemptionNumbers/RR_OtherProjectInfo:ExemptionNumber = 'E2'">
																								<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt" text-decoration="underline" color="black">&#x2714;</fo:inline>
																							</xsl:when>
																							<xsl:otherwise>
																								<fo:inline text-decoration="underline" color="black">
																									<fo:leader leader-length="8pt" leader-pattern="rule"/>
																								</fo:inline>
																							</xsl:otherwise>
																						</xsl:choose>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell line-height="15pt">
																					<fo:block>2</fo:block>
																				</fo:table-cell>
																				<fo:table-cell line-height="15pt">
																					<fo:block>
																						<xsl:choose>
																							<xsl:when test="RR_OtherProjectInfo:HumanSubjectsSupplement/RR_OtherProjectInfo:ExemptionNumbers/RR_OtherProjectInfo:ExemptionNumber = 'E3'">
																								<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt" text-decoration="underline" color="black">&#x2714;</fo:inline>
																							</xsl:when>
																							<xsl:otherwise>
																								<fo:inline text-decoration="underline" color="black">
																									<fo:leader leader-length="8pt" leader-pattern="rule"/>
																								</fo:inline>
																							</xsl:otherwise>
																						</xsl:choose>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell line-height="15pt">
																					<fo:block>3</fo:block>
																				</fo:table-cell>
																				<fo:table-cell line-height="15pt">
																					<fo:block>
																						<xsl:choose>
																							<xsl:when test="RR_OtherProjectInfo:HumanSubjectsSupplement/RR_OtherProjectInfo:ExemptionNumbers/RR_OtherProjectInfo:ExemptionNumber = 'E4'">
																								<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt" text-decoration="underline" color="black">&#x2714;</fo:inline>
																							</xsl:when>
																							<xsl:otherwise>
																								<fo:inline text-decoration="underline" color="black">
																									<fo:leader leader-length="8pt" leader-pattern="rule"/>
																								</fo:inline>
																							</xsl:otherwise>
																						</xsl:choose>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell line-height="15pt">
																					<fo:block>4</fo:block>
																				</fo:table-cell>
																				<fo:table-cell line-height="15pt">
																					<fo:block>
																						<xsl:choose>
																							<xsl:when test="RR_OtherProjectInfo:HumanSubjectsSupplement/RR_OtherProjectInfo:ExemptionNumbers/RR_OtherProjectInfo:ExemptionNumber = 'E5'">
																								<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt" text-decoration="underline" color="black">&#x2714;</fo:inline>
																							</xsl:when>
																							<xsl:otherwise>
																								<fo:inline text-decoration="underline" color="black">
																									<fo:leader leader-length="8pt" leader-pattern="rule"/>
																								</fo:inline>
																							</xsl:otherwise>
																						</xsl:choose>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell line-height="15pt">
																					<fo:block>5</fo:block>
																				</fo:table-cell>
																				<fo:table-cell line-height="15pt">
																					<fo:block>
																						<xsl:choose>
																							<xsl:when test="RR_OtherProjectInfo:HumanSubjectsSupplement/RR_OtherProjectInfo:ExemptionNumbers/RR_OtherProjectInfo:ExemptionNumber = 'E6'">
																								<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt" text-decoration="underline" color="black">&#x2714;</fo:inline>
																							</xsl:when>
																							<xsl:otherwise>
																								<fo:inline text-decoration="underline" color="black">
																									<fo:leader leader-length="8pt" leader-pattern="rule"/>
																								</fo:inline>
																							</xsl:otherwise>
																						</xsl:choose>
																					</fo:block>
																				</fo:table-cell>
																				<fo:table-cell line-height="15pt">
																					<fo:block>6</fo:block>
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
												<fo:table-cell line-height="15pt">
                                                    <fo:block>
													<fo:table width="100%">
														<fo:table-column column-width="3.0in"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;Human Subject Assurance Number</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:value-of select="RR_OtherProjectInfo:HumanSubjectsSupplement/RR_OtherProjectInfo:HumanSubjectAssuranceNumber"/>
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
							<!-- Block 1 End -->
							<!-- Block 2 Begin -->
							<fo:table-row font-size="8pt">
								<fo:table-cell line-height="15pt">
                                    <fo:block>
									<fo:table border-style="solid" border-color="black" width="100%">
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell line-height="15pt">
                                                    <fo:block>
													<fo:table width="100%">
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="2.0in"/>
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="0.5in"/>
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="0.5in"/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>2.</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>*  Are Vertebrate Animals Used?</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:for-each select="RR_OtherProjectInfo:VertebrateAnimalsIndicator">
																			<xsl:call-template name="radio">
																				<xsl:with-param name="value">
																					<xsl:value-of select="."/>
																				</xsl:with-param>
																				<xsl:with-param name="radio_on_value">Yes</xsl:with-param>
																			</xsl:call-template>
																		</xsl:for-each>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-size="8pt" font-weight="bold">
																	<fo:block>Yes</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:for-each select="RR_OtherProjectInfo:VertebrateAnimalsIndicator">
																			<xsl:call-template name="radio">
																				<xsl:with-param name="value">
																					<xsl:value-of select="."/>
																				</xsl:with-param>
																				<xsl:with-param name="radio_on_value">No</xsl:with-param>
																			</xsl:call-template>
																		</xsl:for-each>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>No</fo:block>
																</fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
                                                    </fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell padding-start="2pt" line-height="15pt" font-weight="bold">
													<fo:block>2.a. If YES to Vertebrate Animals</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell line-height="15pt">
                                                    <fo:block>
													<fo:table width="100%">
														<fo:table-column column-width="2.2in"/>
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="0.5in"/>
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="0.5in"/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;Is the IACUC review Pending?</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:call-template name="radio">
																			<xsl:with-param name="value">
																				<xsl:value-of select="RR_OtherProjectInfo:VertebrateAnimalsSupplement/RR_OtherProjectInfo:VertebrateAnimalsIACUCReviewIndicator"/>
																			</xsl:with-param>
																			<xsl:with-param name="radio_on_value">Yes</xsl:with-param>
																		</xsl:call-template>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-size="8pt" font-weight="bold">
																	<fo:block>Yes</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:call-template name="radio">
																			<xsl:with-param name="value">
																				<xsl:value-of select="RR_OtherProjectInfo:VertebrateAnimalsSupplement/RR_OtherProjectInfo:VertebrateAnimalsIACUCReviewIndicator"/>
																			</xsl:with-param>
																			<xsl:with-param name="radio_on_value">No</xsl:with-param>
																		</xsl:call-template>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>No</fo:block>
																</fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
                                                    </fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell line-height="15pt">
                                                    <fo:block>
													<fo:table width="100%">
														<fo:table-column column-width="2.0in"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;IACUC Approval Date:</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:call-template name="formatDate">
																			<xsl:with-param name="value" select="RR_OtherProjectInfo:VertebrateAnimalsSupplement/RR_OtherProjectInfo:VertebrateAnimalsIACUCApprovalDateReviewDate"/>
																		</xsl:call-template>
																	</fo:block>
																</fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
                                                    </fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell line-height="15pt">
                                                    <fo:block>
													<fo:table width="100%">
														<fo:table-column column-width="3.0in"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;Animal Welfare Assurance Number</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:value-of select="RR_OtherProjectInfo:VertebrateAnimalsSupplement/RR_OtherProjectInfo:AssuranceNumber"/>
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
							<!-- Block 2 End -->
							<!-- Block 3 Begin -->
							<fo:table-row font-size="8pt">
								<fo:table-cell line-height="15pt">
                                    <fo:block>
									<fo:table border-style="solid" border-color="black" width="100%">
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell line-height="15pt">
                                                    <fo:block>
													<fo:table width="100%">
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="2.0in"/>
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="0.5in"/>
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="0.5in"/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>3.</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>* Is proprietary/privileged information included in the application?</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:for-each select="RR_OtherProjectInfo:ProprietaryInformationIndicator">
																			<xsl:call-template name="radio">
																				<xsl:with-param name="value">
																					<xsl:value-of select="."/>
																				</xsl:with-param>
																				<xsl:with-param name="radio_on_value">Yes</xsl:with-param>
																			</xsl:call-template>
																		</xsl:for-each>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-size="8pt" font-weight="bold">
																	<fo:block>Yes</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:for-each select="RR_OtherProjectInfo:ProprietaryInformationIndicator">
																			<xsl:call-template name="radio">
																				<xsl:with-param name="value">
																					<xsl:value-of select="."/>
																				</xsl:with-param>
																				<xsl:with-param name="radio_on_value">No</xsl:with-param>
																			</xsl:call-template>
																		</xsl:for-each>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>No</fo:block>
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
							<!-- Block 3 End -->
							<!-- Block 4 Begin -->
							<fo:table-row font-size="8pt">
								<fo:table-cell line-height="15pt">
                                    <fo:block>
									<fo:table border-style="solid" border-color="black" width="100%">
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell line-height="15pt">
                                                    <fo:block>
													<fo:table width="100%">
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="3.0in"/>
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="0.5in"/>
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="0.5in"/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>4.a.</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>* Does this project have an actual or potential impact on the environment?</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:for-each select="RR_OtherProjectInfo:EnvironmentalImpact/RR_OtherProjectInfo:EnvironmentalImpactIndicator">
																			<xsl:call-template name="radio">
																				<xsl:with-param name="value">
																					<xsl:value-of select="."/>
																				</xsl:with-param>
																				<xsl:with-param name="radio_on_value">Yes</xsl:with-param>
																			</xsl:call-template>
																		</xsl:for-each>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-size="8pt" font-weight="bold">
																	<fo:block>Yes</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:for-each select="RR_OtherProjectInfo:EnvironmentalImpact/RR_OtherProjectInfo:EnvironmentalImpactIndicator">
																			<xsl:call-template name="radio">
																				<xsl:with-param name="value">
																					<xsl:value-of select="."/>
																				</xsl:with-param>
																				<xsl:with-param name="radio_on_value">No</xsl:with-param>
																			</xsl:call-template>
																		</xsl:for-each>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>No</fo:block>
																</fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
                                                    </fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell line-height="15pt">
                                                    <fo:block>
													<fo:table width="100%">
														<fo:table-column column-width="2.0in"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>4.b.&#160;If yes, please explain:</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" hyphenate="true" language="en">
																	<fo:block>
																		<xsl:value-of select="RR_OtherProjectInfo:EnvironmentalImpact/RR_OtherProjectInfo:EnvironmentalImpactExplanation"/>
																	</fo:block>
																</fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
                                                    </fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell line-height="15pt" font-weight="bold">
													<fo:block>4.c.&#160;If this project has an actual or potential impact on the environment, has an exemption been authorized or an environmental assessment (EA) or</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell line-height="15pt">
                                                    <fo:block>
													<fo:table width="100%">
														<fo:table-column column-width="3.2in"/>
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="0.5in"/>
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="0.5in"/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>&#160;&#160;&#160;&#160;&#160;&#160;&#160;environmental impact statement (EIS) been performed?</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:call-template name="radio">
																			<xsl:with-param name="value">
																				<xsl:value-of select="RR_OtherProjectInfo:EnvironmentalImpact/RR_OtherProjectInfo:EnvironmentalExemption/RR_OtherProjectInfo:EnvironmentalExemptionIndicator"/>
																			</xsl:with-param>
																			<xsl:with-param name="radio_on_value">Yes</xsl:with-param>
																		</xsl:call-template>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-size="8pt" font-weight="bold">
																	<fo:block>Yes</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:call-template name="radio">
																			<xsl:with-param name="value">
																				<xsl:value-of select="RR_OtherProjectInfo:EnvironmentalImpact/RR_OtherProjectInfo:EnvironmentalExemption/RR_OtherProjectInfo:EnvironmentalExemptionIndicator"/>
																			</xsl:with-param>
																			<xsl:with-param name="radio_on_value">No</xsl:with-param>
																		</xsl:call-template>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>No</fo:block>
																</fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
                                                    </fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell line-height="15pt">
                                                    <fo:block>
													<fo:table width="100%">
														<fo:table-column column-width="2.0in"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>4.d.&#160;If yes, please explain:</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" hyphenate="true" language="en">
																	<fo:block>
																		<xsl:value-of select="RR_OtherProjectInfo:EnvironmentalImpact/RR_OtherProjectInfo:EnvironmentalExemption/RR_OtherProjectInfo:EnvironmentalExemptionExplanation"/>
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
							<!-- Block 4 End -->
							<!-- Block 5 Begin -->
							<fo:table-row font-size="8pt">
								<fo:table-cell line-height="15pt">
                                    <fo:block>
									<fo:table border-style="solid" border-color="black" width="100%">
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell line-height="15pt">
                                                    <fo:block>
													<fo:table width="100%">
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="3.5in"/>
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="0.5in"/>
														<fo:table-column column-width="0.2in"/>
														<fo:table-column column-width="0.5in"/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>5.a.</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>* Does this project involve activities outside the U.S. or partnership with International Collaborators?</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:for-each select="RR_OtherProjectInfo:InternationalActivities/RR_OtherProjectInfo:InternationalActivitiesIndicator">
																			<xsl:call-template name="radio">
																				<xsl:with-param name="value">
																					<xsl:value-of select="."/>
																				</xsl:with-param>
																				<xsl:with-param name="radio_on_value">Yes</xsl:with-param>
																			</xsl:call-template>
																		</xsl:for-each>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-size="8pt" font-weight="bold">
																	<fo:block>Yes</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt">
																	<fo:block>
																		<xsl:for-each select="RR_OtherProjectInfo:InternationalActivities/RR_OtherProjectInfo:InternationalActivitiesIndicator">
																			<xsl:call-template name="radio">
																				<xsl:with-param name="value">
																					<xsl:value-of select="."/>
																				</xsl:with-param>
																				<xsl:with-param name="radio_on_value">No</xsl:with-param>
																			</xsl:call-template>
																		</xsl:for-each>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>No</fo:block>
																</fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
                                                    </fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell line-height="15pt">
                                                    <fo:block>
													<fo:table width="100%">
														<fo:table-column column-width="4.6in"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>5.b.&#160;If yes, identify countries:</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" hyphenate="true" language="en">
																	<fo:block>
																		<xsl:value-of select="RR_OtherProjectInfo:InternationalActivities/RR_OtherProjectInfo:ActivitiesPartnershipsCountries [@RR_OtherProjectInfo:InternationalActivitiesIndicator='Yes']"/>
																	</fo:block>
																</fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
                                                    </fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell line-height="15pt">
                                                    <fo:block>
													<fo:table width="100%">
														<fo:table-column column-width="1.5in"/>
														<fo:table-column/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell line-height="15pt" font-weight="bold">
																	<fo:block>5.c.&#160;Optional Explanation:</fo:block>
																</fo:table-cell>
																<fo:table-cell line-height="15pt" hyphenate="true" language="en">
																	<fo:block>
																		<xsl:value-of select="RR_OtherProjectInfo:InternationalActivities/RR_OtherProjectInfo:InternationalActivitiesExplanation"/>
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
							<!-- Block 5  End -->
							<!-- Block 6 repace -->
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num">6.</xsl:with-param>
								<xsl:with-param name="block_title">* Project Summary/Abstract</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="RR_OtherProjectInfo:AbstractAttachments/RR_OtherProjectInfo:AbstractAttachment/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="RR_OtherProjectInfo:AbstractAttachments/RR_OtherProjectInfo:AbstractAttachment/att:MimeType"/>
							</xsl:call-template>
							<!-- Block 6 End -->
							<!-- Block 7  Begin -->
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num">7.</xsl:with-param>
								<xsl:with-param name="block_title">* Project Narrative</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="RR_OtherProjectInfo:ProjectNarrativeAttachments/RR_OtherProjectInfo:ProjectNarrativeAttachment/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="RR_OtherProjectInfo:ProjectNarrativeAttachments/RR_OtherProjectInfo:ProjectNarrativeAttachment/att:MimeType"/>
							</xsl:call-template>
							<!-- Block 7 End -->
							<!-- Block 8  Begin -->
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num">8.</xsl:with-param>
								<xsl:with-param name="block_title">Bibliography &amp; References Cited</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="RR_OtherProjectInfo:BibliographyAttachments/RR_OtherProjectInfo:BibliographyAttachment/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="RR_OtherProjectInfo:BibliographyAttachments/RR_OtherProjectInfo:BibliographyAttachment/att:MimeType"/>
							</xsl:call-template>
							<!-- Block 8 End -->
							<!-- Block 9  Begin -->
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num">9.</xsl:with-param>
								<xsl:with-param name="block_title">Facilities &amp; Other Resources</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="RR_OtherProjectInfo:FacilitiesAttachments/RR_OtherProjectInfo:FacilitiesAttachment/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="RR_OtherProjectInfo:FacilitiesAttachments/RR_OtherProjectInfo:FacilitiesAttachment/att:MimeType"/>
							</xsl:call-template>
							<!-- Block 9 End -->
							<!-- Block 10  Begin -->
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num">10.</xsl:with-param>
								<xsl:with-param name="block_title">Equipment</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="RR_OtherProjectInfo:EquipmentAttachments/RR_OtherProjectInfo:EquipmentAttachment/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="RR_OtherProjectInfo:EquipmentAttachments/RR_OtherProjectInfo:EquipmentAttachment/att:MimeType"/>
							</xsl:call-template>
							<!-- Block 10 End -->
							<!-- Block 11  Begin -->
							<xsl:for-each select="RR_OtherProjectInfo:OtherAttachments/RR_OtherProjectInfo:OtherAttachment">
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num">11.</xsl:with-param>
								<xsl:with-param name="block_title">Other Attachments</xsl:with-param>
								<xsl:with-param name="filename">
									<!--<xsl:value-of select="RR_OtherProjectInfo:OtherAttachments/RR_OtherProjectInfo:OtherAttachment/att:FileName"/>-->
									<xsl:value-of select="att:FileName"/>
								</xsl:with-param>
								<!--<xsl:with-param name="mimetype" select="RR_OtherProjectInfo:OtherAttachments/RR_OtherProjectInfo:OtherAttachment/att:MimeType"/>-->
								<xsl:with-param name="mimetype" select="att:MimeType"/>
							</xsl:call-template>
							</xsl:for-each>
							<!-- Block 11 End -->
						</fo:table-body>
					</fo:table>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	







	<!-- ================================ -->
	<!-- Format Date template -->
	<!-- ================================ -->
	<xsl:template name="formatDate">
		<xsl:param name="value"/>
		<xsl:if test="string-length($value) &gt; 0 and $value != '--'">
			<xsl:value-of select="format-number(substring($value,6,2), '00')"/>
			<xsl:text>-</xsl:text>
			<xsl:value-of select="format-number(substring($value,9,2), '00')"/>
			<xsl:text>-</xsl:text>
			<xsl:value-of select="format-number(substring($value,1,4), '0000')"/>
		</xsl:if>
	</xsl:template>
	<!-- ================================ -->
	<!-- Radio  template -->
	<!-- ================================ -->
	<xsl:template name="radio">
		<xsl:param name="value"/>
		<xsl:param name="radio_on_value"/>
		<xsl:choose>
			<xsl:when test="$value = $radio_on_value">
				<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x25cf;</fo:inline>
			</xsl:when>
			<xsl:otherwise>
				<fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x274d;</fo:inline>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!--                                      NEW NEW NEW NEW -->
	<!-- Block 7  Begin -->
	<xsl:template name="attach_block">
		<xsl:param name="block_num"/>
		<xsl:param name="block_title"/>
		<xsl:param name="filename"/>
		<xsl:param name="mimetype"/>
		<xsl:element name="fo:table-row">
			<xsl:element name="fo:table-cell">
				<xsl:attribute name="font-size">8pt</xsl:attribute>
				<xsl:element name="fo:table">
					<xsl:attribute name="border-style">solid</xsl:attribute>
					<xsl:attribute name="border-color">black</xsl:attribute>
					<xsl:attribute name="width">100%</xsl:attribute>
					<xsl:element name="fo:table-column"/>
					<xsl:element name="fo:table-body">
						<xsl:element name="fo:table-row">
							<xsl:element name="fo:table-cell">
								<xsl:element name="fo:table">
									<xsl:attribute name="width">100%</xsl:attribute>
									<xsl:element name="fo:table-column">
										<xsl:attribute name="column-width">0.2in</xsl:attribute>
									</xsl:element>
									<xsl:element name="fo:table-column">
										<xsl:attribute name="column-width">2.0in</xsl:attribute>
									</xsl:element>
									<xsl:element name="fo:table-column">
										<xsl:attribute name="column-width">2.0in</xsl:attribute>
									</xsl:element>
									<fo:table-column column-width="2.0in"/>
									<xsl:element name="fo:table-body">
										<xsl:element name="fo:table-row">
											<xsl:element name="fo:table-cell">
												<xsl:attribute name="font-weight">bold</xsl:attribute>
												<xsl:element name="fo:block">
													<xsl:value-of select="$block_num"/>
												</xsl:element>
											</xsl:element>
											<xsl:element name="fo:table-cell">
												<xsl:attribute name="font-weight">bold</xsl:attribute>
												<xsl:element name="fo:block">
													<xsl:value-of select="$block_title"/>
												</xsl:element>
											</xsl:element>
											<xsl:element name="fo:table-cell">
												<xsl:element name="fo:block">
													<xsl:value-of select="$filename"/>
												</xsl:element>
											</xsl:element>
											<fo:table-cell line-height="15pt">
												<fo:block>
													<xsl:if test=" $filename  != '' ">Mime Type: <xsl:value-of select="$mimetype"/>
													</xsl:if>
												</fo:block>
											</fo:table-cell>
										</xsl:element>
									</xsl:element>
								</xsl:element>
							</xsl:element>
						</xsl:element>
					</xsl:element>
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>
	<!-- Block 7 End -->
	<!--                                     NEW NEW NEW NENW -->
</xsl:stylesheet>
