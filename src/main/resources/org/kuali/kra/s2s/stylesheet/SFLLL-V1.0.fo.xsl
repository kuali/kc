<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:SFLLL="http://apply.grants.gov/forms/SFLLL-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:variable name="fo:layout-master-set">
		<fo:layout-master-set>
			<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.6in" margin-right="0.6in">
				<fo:region-body margin-top="0.48in" margin-bottom="0.48in"/>
				<fo:region-after extent=".5in"/>
			</fo:simple-page-master>
		</fo:layout-master-set>
	</xsl:variable>
	<xsl:template match="SFLLL:LobbyingActivitiesDisclosure">
		<fo:root>
			<xsl:copy-of select="$fo:layout-master-set"/>
			<fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
				<fo:static-content flow-name="xsl-region-after">
					<fo:block>
						<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
						</fo:inline>
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<fo:block>
						<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell font-size="8px" display-align="before" height="531pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
										<fo:block>
											<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
												<fo:table-column column-width="172.75pt"/>
												<fo:table-column column-width="172.75pt"/>
												<fo:table-column column-width="172.75pt"/>
												<fo:table-body>
													<fo:table-row>
														<fo:table-cell border-after-color="white" border-before-color="white" border-end-color="white" border-start-color="white" font-size="8px" display-align="before" number-columns-spanned="3" width="172.75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<fo:block color="black" space-before.optimum="-8pt">
																	<fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="2pt"/>
																</fo:block>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell border-after-color="white" border-before-color="white" border-end-color="white" border-start-color="white" font-size="8px" display-align="before" number-columns-spanned="3" text-align="center" width="172.75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<fo:leader leader-pattern="space"/>
															</fo:block>
															<fo:block>
																<fo:inline font-size="12px" font-style="normal">DISCLOSURE OF LOBBYING ACTIVITIES</fo:inline>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell border-after-color="white" border-before-color="white" border-end-color="white" border-start-color="white" font-size="8px" height="13pt" number-columns-spanned="2" text-align="center" width="172.75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<fo:inline font-size="8px" font-style="normal">Complete this form to disclose lobbying activities pursuant to 31 U.S.C. 1352 </fo:inline>
																<fo:block>
                                                                                    </fo:block>
																<fo:inline font-size="8px" font-style="normal">(See reverse for public burden disclosure.)</fo:inline>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell border-after-color="white" border-before-color="white" border-end-color="white" border-start-color="white" font-size="8px" display-align="before" height="13pt" text-align="right" width="172.75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<fo:inline font-size="8px" font-style="normal">Approved by OMB</fo:inline>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>
																<fo:inline font-size="8px" font-style="normal">0348-0046</fo:inline>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell border-after-color="white" border-before-color="black" border-end-color="black" border-start-color="black" font-size="8px" display-align="before" text-align="left" width="172.75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<fo:inline font-size="8px" font-style="normal">1.&#160; * Type of Federal Action:</fo:inline>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell border-after-color="white" border-before-color="black" border-end-color="black" border-start-color="black" font-size="8px" display-align="before" text-align="left" width="172.75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<fo:inline font-size="8px" font-style="normal">2.&#160; * Status of Federal Action:</fo:inline>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell border-after-color="white" border-before-color="black" border-end-color="black" border-start-color="black" font-size="8px" display-align="before" text-align="left" width="172.75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<fo:inline font-size="8px" font-style="normal">3.&#160; * Report Type:</fo:inline>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell border-after-color="white" border-before-color="white" font-size="8px" display-align="before" text-align="left" width="172.75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<fo:inline font-size="8px" font-style="normal">&#160;&#160; </fo:inline>
																<xsl:for-each select="SFLLL:FederalActionType">
																	<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																		<fo:inline font-size="8px" font-style="normal">
																			<xsl:choose>
																				<xsl:when test=".='Contract'">
																					<fo:inline font-family="Courier" font-size="12pt" padding-start="1.5pt" padding-end="1.5pt">&#x2022;</fo:inline>
																				</xsl:when>
																				<xsl:otherwise>
																					<fo:inline text-decoration="underline" color="black">
																						<fo:leader leader-length="7pt" leader-pattern="rule"/>
																					</fo:inline>
																				</xsl:otherwise>
																			</xsl:choose>
																		</fo:inline>
																	</fo:inline>
																</xsl:for-each>
																<fo:inline font-size="8px" font-style="normal">a. contract</fo:inline>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>&#160; <xsl:for-each select="SFLLL:FederalActionType">
																	<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																		<fo:inline font-size="8px" font-style="normal">
																			<xsl:choose>
																				<xsl:when test=".='Grant'">
																					<fo:inline font-family="Courier" font-size="12pt" padding-start="1.5pt" padding-end="1.5pt">&#x2022;</fo:inline>
																				</xsl:when>
																				<xsl:otherwise>
																					<fo:inline text-decoration="underline" color="black">
																						<fo:leader leader-length="7pt" leader-pattern="rule"/>
																					</fo:inline>
																				</xsl:otherwise>
																			</xsl:choose>
																		</fo:inline>
																	</fo:inline>
																</xsl:for-each>
																<fo:inline font-size="8px" font-style="normal">b. grant</fo:inline>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>&#160; <xsl:for-each select="SFLLL:FederalActionType">
																	<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																		<fo:inline font-size="8px" font-style="normal">
																			<xsl:choose>
																				<xsl:when test=".='CoopAgree'">
																					<fo:inline font-family="Courier" font-size="12pt" padding-start="1.5pt" padding-end="1.5pt">&#x2022;</fo:inline>
																				</xsl:when>
																				<xsl:otherwise>
																					<fo:inline text-decoration="underline" color="black">
																						<fo:leader leader-length="7pt" leader-pattern="rule"/>
																					</fo:inline>
																				</xsl:otherwise>
																			</xsl:choose>
																		</fo:inline>
																	</fo:inline>
																</xsl:for-each>
																<fo:inline font-size="8px" font-style="normal">c. cooperative agreement</fo:inline>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>&#160; <xsl:for-each select="SFLLL:FederalActionType">
																	<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																		<fo:inline font-size="8px" font-style="normal">
																			<xsl:choose>
																				<xsl:when test=".='Loan'">
																					<fo:inline font-family="Courier" font-size="12pt" padding-start="1.5pt" padding-end="1.5pt">&#x2022;</fo:inline>
																				</xsl:when>
																				<xsl:otherwise>
																					<fo:inline text-decoration="underline" color="black">
																						<fo:leader leader-length="7pt" leader-pattern="rule"/>
																					</fo:inline>
																				</xsl:otherwise>
																			</xsl:choose>
																		</fo:inline>
																	</fo:inline>
																</xsl:for-each>
																<fo:inline font-size="8px" font-style="normal">d. loan</fo:inline>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>&#160; <xsl:for-each select="SFLLL:FederalActionType">
																	<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																		<fo:inline font-size="8px" font-style="normal">
																			<xsl:choose>
																				<xsl:when test=".='LoanGuarantee'">
																					<fo:inline font-family="Courier" font-size="12pt" padding-start="1.5pt" padding-end="1.5pt">&#x2022;</fo:inline>
																				</xsl:when>
																				<xsl:otherwise>
																					<fo:inline text-decoration="underline" color="black">
																						<fo:leader leader-length="7pt" leader-pattern="rule"/>
																					</fo:inline>
																				</xsl:otherwise>
																			</xsl:choose>
																		</fo:inline>
																	</fo:inline>
																</xsl:for-each>
																<fo:inline font-size="8px" font-style="normal">e. loan guarantee</fo:inline>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>&#160; <xsl:for-each select="SFLLL:FederalActionType">
																	<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																		<fo:inline font-size="8px" font-style="normal">
																			<xsl:choose>
																				<xsl:when test=".='LoanInsurance'">
																					<fo:inline font-family="Courier" font-size="12pt" padding-start="1.5pt" padding-end="1.5pt">&#x2022;</fo:inline>
																				</xsl:when>
																				<xsl:otherwise>
																					<fo:inline text-decoration="underline" color="black">
																						<fo:leader leader-length="7pt" leader-pattern="rule"/>
																					</fo:inline>
																				</xsl:otherwise>
																			</xsl:choose>
																		</fo:inline>
																	</fo:inline>
																</xsl:for-each>
																<fo:inline font-size="8px" font-style="normal">f. loan insurance </fo:inline>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell border-after-color="white" border-before-color="white" font-size="8px" display-align="before" text-align="left" width="172.75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>&#160; <xsl:for-each select="SFLLL:FederalActionStatus">
																	<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																		<fo:inline font-size="8px" font-style="normal">
																			<xsl:choose>
																				<xsl:when test=".='BidOffer'">
																					<fo:inline font-family="Courier" font-size="12pt" padding-start="1.5pt" padding-end="1.5pt">&#x2022;</fo:inline>
																				</xsl:when>
																				<xsl:otherwise>
																					<fo:inline text-decoration="underline" color="black">
																						<fo:leader leader-length="7pt" leader-pattern="rule"/>
																					</fo:inline>
																				</xsl:otherwise>
																			</xsl:choose>
																		</fo:inline>
																	</fo:inline>
																</xsl:for-each>
																<fo:inline font-size="8px" font-style="normal">a. bid offer application</fo:inline>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>&#160; <xsl:for-each select="SFLLL:FederalActionStatus">
																	<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																		<fo:inline font-size="8px" font-style="normal">
																			<xsl:choose>
																				<xsl:when test=".='InitialAward'">
																					<fo:inline font-family="Courier" font-size="12pt" padding-start="1.5pt" padding-end="1.5pt">&#x2022;</fo:inline>
																				</xsl:when>
																				<xsl:otherwise>
																					<fo:inline text-decoration="underline" color="black">
																						<fo:leader leader-length="7pt" leader-pattern="rule"/>
																					</fo:inline>
																				</xsl:otherwise>
																			</xsl:choose>
																		</fo:inline>
																	</fo:inline>
																</xsl:for-each>
																<fo:inline font-size="8px" font-style="normal">b. initial award</fo:inline>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>&#160; <xsl:for-each select="SFLLL:FederalActionStatus">
																	<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																		<fo:inline font-size="8px" font-style="normal">
																			<xsl:choose>
																				<xsl:when test=".='PostAward'">
																					<fo:inline font-family="Courier" font-size="12pt" padding-start="1.5pt" padding-end="1.5pt">&#x2022;</fo:inline>
																				</xsl:when>
																				<xsl:otherwise>
																					<fo:inline text-decoration="underline" color="black">
																						<fo:leader leader-length="7pt" leader-pattern="rule"/>
																					</fo:inline>
																				</xsl:otherwise>
																			</xsl:choose>
																		</fo:inline>
																	</fo:inline>
																</xsl:for-each>
																<fo:inline font-size="8px" font-style="normal">c. post-award</fo:inline>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell border-after-color="white" border-before-color="white" font-size="8px" display-align="before" text-align="left" width="172.75pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>&#160; <xsl:for-each select="SFLLL:ReportType">
																	<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																		<fo:inline font-size="8px" font-style="normal">
																			<xsl:choose>
																				<xsl:when test=".='InitialFiling'">
																					<fo:inline font-family="Courier" font-size="12pt" padding-start="1.5pt" padding-end="1.5pt">&#x2022;</fo:inline>
																				</xsl:when>
																				<xsl:otherwise>
																					<fo:inline text-decoration="underline" color="black">
																						<fo:leader leader-length="7pt" leader-pattern="rule"/>
																					</fo:inline>
																				</xsl:otherwise>
																			</xsl:choose>
																		</fo:inline>
																	</fo:inline>
																</xsl:for-each>
																<fo:inline font-size="8px" font-style="normal">a. initial filing</fo:inline>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>&#160; <xsl:for-each select="SFLLL:ReportType">
																	<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																		<fo:inline font-size="8px" font-style="normal">
																			<xsl:choose>
																				<xsl:when test=".='MaterialChange'">
																					<fo:inline font-family="Courier" font-size="12pt" padding-start="1.5pt" padding-end="1.5pt">&#x2022;</fo:inline>
																				</xsl:when>
																				<xsl:otherwise>
																					<fo:inline text-decoration="underline" color="black">
																						<fo:leader leader-length="7pt" leader-pattern="rule"/>
																					</fo:inline>
																				</xsl:otherwise>
																			</xsl:choose>
																		</fo:inline>
																	</fo:inline>
																</xsl:for-each>
																<fo:inline font-size="8px" font-style="normal">b. material change</fo:inline>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>
																<fo:inline font-size="8px" font-style="normal">&#160; For Material Change Only: </fo:inline>
																<fo:block>
																	<xsl:text>&#xA;</xsl:text>
																</fo:block>
																<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																	<fo:table-column column-width="20pt"/>
																	<fo:table-column/>
																	<fo:table-body>
																		<fo:table-row>
																			<fo:table-cell font-size="8px" display-align="before" width="20pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																				<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																					<fo:table-column column-width="20pt"/>
																					<fo:table-column column-width="40pt"/>
																					<fo:table-column column-width="30pt"/>
																					<fo:table-column column-width="30pt"/>
																					<fo:table-body>
																						<fo:table-row>
																							<fo:table-cell border-style="solid" border-width="1pt" border-color="white">
																								<fo:block>
																									<fo:inline font-size="8px" font-style="normal"> year </fo:inline>
																								</fo:block>
																							</fo:table-cell>
																							<fo:table-cell border-style="solid" border-width="1pt" border-color="white">
																								<fo:block>
																									<xsl:for-each select="SFLLL:MaterialChangeSupplement">
																										<xsl:for-each select="SFLLL:MaterialChangeYear">
																											<fo:inline font-size="8px" font-style="normal">
																												<xsl:apply-templates/>
																											</fo:inline>
																										</xsl:for-each>
																									</xsl:for-each>
																								</fo:block>
																							</fo:table-cell>
																							<fo:table-cell border-style="solid" border-width="1pt" border-color="white">
																								<fo:block>
																									<fo:inline font-size="8px" font-style="normal">quarter </fo:inline>
																								</fo:block>
																							</fo:table-cell>
																							<fo:table-cell border-style="solid" border-width="1pt" border-color="white">
																								<fo:block>
																									<xsl:for-each select="SFLLL:MaterialChangeSupplement">
																										<xsl:for-each select="SFLLL:MaterialChangeQuarter">
																											<fo:inline font-size="8px" font-style="normal">
																												<xsl:apply-templates/>
																											</fo:inline>
																										</xsl:for-each>
																									</xsl:for-each>
																								</fo:block>
																							</fo:table-cell>
																						</fo:table-row>
																					</fo:table-body>
																				</fo:table>
																			</fo:table-cell>
																			<fo:table-cell font-size="8px" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																				<fo:block>
																					<fo:inline font-size="8px" font-style="normal">&#160;</fo:inline>
																				</fo:block>
																			</fo:table-cell>
																		</fo:table-row>
																		<fo:table-row>
																			<fo:table-cell border-after-color="white" font-size="8px" display-align="before" number-columns-spanned="2" width="20pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																				<fo:block>
																					<fo:inline font-size="8px" font-style="normal">date of last report </fo:inline>
																					<xsl:for-each select="SFLLL:MaterialChangeSupplement">
																						<xsl:for-each select="SFLLL:LastReportDate">
																							<fo:inline font-size="8px">
																								<xsl:value-of select="format-number(substring(.,6,2), '00')"/>
																								<xsl:text>-</xsl:text>
																								<xsl:value-of select="format-number(substring(.,9,2), '00')"/>
																								<xsl:text>-</xsl:text>
																								<xsl:value-of select="format-number(substring(.,1,4), '0000')"/>
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
											<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
												<fo:table-column column-width="30pt"/>
												<fo:table-column column-width="30pt"/>
												<fo:table-column/>
												<fo:table-column/>
												<fo:table-body>
													<fo:table-row width="425pt">
														<fo:table-cell border-after-color="white" font-size="8px" display-align="before" number-columns-spanned="3" width="30pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<fo:inline font-size="8px" font-style="normal">4.&#160; * Name and Address of Reporting Entity:</fo:inline>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell border-after-color="white" border-end-color="black" font-size="8px" display-align="before" width="30pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<fo:inline font-size="8px" font-style="normal">5.&#160; If Reporting Entity in No.4 is Subawardee, Enter Name and Address of Prime:</fo:inline>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell border-after-color="white" border-before-color="white" border-end-color="white" border-start-color="black" font-size="8px" display-align="before" number-columns-spanned="3" width="30pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<xsl:for-each select="SFLLL:ReportEntity">
																	<xsl:for-each select="SFLLL:ReportEntityIsPrime">
																		<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																			<fo:inline font-size="8px" font-style="normal">
																				<xsl:choose>
																					<xsl:when test=".='Yes'">
																						<fo:inline font-family="Courier" font-size="12pt" padding-start="1.5pt" padding-end="1.5pt">&#x2022;</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline text-decoration="underline" color="black">
																							<fo:leader leader-length="7pt" leader-pattern="rule"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																			</fo:inline>
																		</fo:inline>
																	</xsl:for-each>
																</xsl:for-each>
																<fo:inline font-size="8px" font-style="normal">Prime&#160;&#160;&#160;&#160; </fo:inline>
																<xsl:for-each select="SFLLL:ReportEntity">
																	<xsl:for-each select="SFLLL:ReportEntityIsPrime">
																		<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																			<fo:inline font-size="8px" font-style="normal">
																				<xsl:choose>
																					<xsl:when test=".='No'">
																						<fo:inline font-family="Courier" font-size="12pt" padding-start="1.5pt" padding-end="1.5pt">&#x2022;</fo:inline>
																					</xsl:when>
																					<xsl:otherwise>
																						<fo:inline text-decoration="underline" color="black">
																							<fo:leader leader-length="7pt" leader-pattern="rule"/>
																						</fo:inline>
																					</xsl:otherwise>
																				</xsl:choose>
																			</fo:inline>
																		</fo:inline>
																	</xsl:for-each>
																</xsl:for-each>
																<fo:inline font-size="8px" font-style="normal"> SubAwardee&#160;&#160;&#160;&#160;&#160; Tier if known: </fo:inline>
																<xsl:for-each select="SFLLL:ReportEntity">
																	<xsl:for-each select="SFLLL:Tier">
																		<fo:inline font-size="8px" font-style="normal">
																			<xsl:apply-templates/>
																		</fo:inline>
																	</xsl:for-each>
																</xsl:for-each>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell border-after-color="white" border-before-color="white" border-end-color="black" border-start-color="black" font-size="8px" display-align="before" width="30pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
															<fo:block/>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell border-before-color="white" font-size="8px" display-align="before" number-columns-spanned="3" width="30pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<xsl:for-each select="SFLLL:ReportEntity">
																	<xsl:for-each select="SFLLL:Prime">
																		<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																			<fo:table-column/>
																			<fo:table-body>
																				<fo:table-row>
																					<fo:table-cell border-after-color="white" border-before-color="white" border-end-color="white" border-start-color="white" font-size="8px" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
																						<fo:block>
																							<fo:inline font-size="8px" font-style="normal">* Name: </fo:inline>
																							<xsl:for-each select="globLib:OrganizationName">
																								<fo:inline font-size="8px" font-style="normal">
																									<xsl:apply-templates/>
																								</fo:inline>
																							</xsl:for-each>
																						</fo:block>
																					</fo:table-cell>
																				</fo:table-row>
																				<fo:table-row>
																					<fo:table-cell font-size="8px" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																						<fo:block>
																							<fo:inline font-size="8px" font-style="normal">* Address: </fo:inline>
																							<xsl:for-each select="SFLLL:Address">
																								<xsl:for-each select="globLib:Street1">
																									<fo:inline font-size="8px" font-style="normal">
																										<xsl:apply-templates/>
																									</fo:inline>
																								</xsl:for-each>
																								<fo:inline font-size="8px" font-style="normal">&#160;</fo:inline>
																								<xsl:for-each select="globLib:Street2">
																									<fo:inline font-size="8px" font-style="normal">
																										<xsl:apply-templates/>
																									</fo:inline>
																								</xsl:for-each>
																								<fo:block>
																									<fo:leader leader-pattern="space"/>
																								</fo:block>
																								<fo:inline font-size="8px" font-style="normal">&#160;</fo:inline>
																								<xsl:for-each select="globLib:City">
																									<fo:inline font-size="8px" font-style="normal">
																										<xsl:apply-templates/>
																									</fo:inline>
																								</xsl:for-each>
																								<fo:inline font-size="8px" font-style="normal">&#160;</fo:inline>
																								<xsl:for-each select="globLib:County">
																									<fo:inline font-size="8px" font-style="normal">
																										<xsl:apply-templates/>
																									</fo:inline>
																								</xsl:for-each>
																								<fo:inline font-size="8px" font-style="normal">&#160;</fo:inline>
																								<xsl:for-each select="globLib:State">
																									<fo:inline font-size="8px" font-style="normal">
																										<xsl:apply-templates/>
																									</fo:inline>
																								</xsl:for-each>
																								<fo:inline font-size="8px" font-style="normal">&#160;</fo:inline>
																								<xsl:for-each select="globLib:ZipCode">
																									<fo:inline font-size="8px" font-style="normal">
																										<xsl:apply-templates/>
																									</fo:inline>
																								</xsl:for-each>
																								<fo:inline font-size="8px" font-style="normal">&#160;</fo:inline>
																								<xsl:for-each select="globLib:Country">
																									<fo:inline font-size="8px" font-style="normal">
																										<xsl:apply-templates/>
																									</fo:inline>
																								</xsl:for-each>
																							</xsl:for-each>
																						</fo:block>
																					</fo:table-cell>
																				</fo:table-row>
																				<fo:table-row>
																					<fo:table-cell font-size="8px" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																						<fo:block>
																							<fo:inline font-size="8px" font-style="normal">Congressional District, if known: </fo:inline>
																							<xsl:for-each select="globLib:CongressionalDistrict">
																								<fo:inline font-size="8px" font-style="normal">
																									<xsl:apply-templates/>
																								</fo:inline>
																							</xsl:for-each>
																						</fo:block>
																					</fo:table-cell>
																				</fo:table-row>
																			</fo:table-body>
																		</fo:table>
																	</xsl:for-each>
																</xsl:for-each>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell border-before-color="white" font-size="8px" display-align="before" width="30pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<xsl:for-each select="SFLLL:ReportEntity">
																	<xsl:for-each select="SFLLL:SubAwardee">
																		<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																			<fo:table-column/>
																			<fo:table-body>
																				<fo:table-row>
																					<fo:table-cell font-size="8px" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																						<fo:block>
																							<fo:inline font-size="8px" font-style="normal">Name: </fo:inline>
																							<xsl:for-each select="globLib:OrganizationName">
																								<xsl:apply-templates/>
																							</xsl:for-each>
																						</fo:block>
																					</fo:table-cell>
																				</fo:table-row>
																				<fo:table-row>
																					<fo:table-cell font-size="8px" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																						<fo:block>
																							<fo:inline font-size="8px" font-style="normal">Address: </fo:inline>
																							<xsl:for-each select="SFLLL:Address">
																								<xsl:for-each select="globLib:Street1">
																									<fo:inline font-size="8px" font-style="normal">
																										<xsl:apply-templates/>
																									</fo:inline>
																								</xsl:for-each>
																							</xsl:for-each>
																							<fo:inline font-size="8px">&#160;</fo:inline>
																							<xsl:for-each select="SFLLL:Address">
																								<xsl:for-each select="globLib:Street2">
																									<fo:inline font-size="8px" font-style="normal">
																										<xsl:apply-templates/>
																									</fo:inline>
																								</xsl:for-each>
																								<fo:block>
																									<fo:leader leader-pattern="space"/>
																								</fo:block>
																								<fo:inline font-size="8px">&#160;</fo:inline>
																								<xsl:for-each select="globLib:City">
																									<fo:inline font-size="8px" font-style="normal">
																										<xsl:apply-templates/>
																									</fo:inline>
																								</xsl:for-each>
																								<fo:inline font-size="8px">&#160;</fo:inline>
																								<xsl:for-each select="globLib:County">
																									<fo:inline font-size="8px" font-style="normal">
																										<xsl:apply-templates/>
																									</fo:inline>
																								</xsl:for-each>
																								<fo:inline font-size="8px">&#160;</fo:inline>
																								<xsl:for-each select="globLib:State">
																									<fo:inline font-size="8px" font-style="normal">
																										<xsl:apply-templates/>
																									</fo:inline>
																								</xsl:for-each>
																								<fo:inline font-size="8px">&#160; </fo:inline>
																								<xsl:for-each select="globLib:ZipCode">
																									<fo:inline font-size="8px" font-style="normal">
																										<xsl:apply-templates/>
																									</fo:inline>
																								</xsl:for-each>
																								<fo:inline font-size="8px">&#160;</fo:inline>
																								<xsl:for-each select="globLib:Country">
																									<fo:inline font-size="8px" font-style="normal">
																										<xsl:apply-templates/>
																									</fo:inline>
																								</xsl:for-each>
																							</xsl:for-each>
																						</fo:block>
																					</fo:table-cell>
																				</fo:table-row>
																				<fo:table-row>
																					<fo:table-cell font-size="8px" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																						<fo:block>
																							<fo:inline font-size="8px" font-style="normal">Congressional District, if known: </fo:inline>
																							<xsl:for-each select="globLib:CongressionalDistrict">
																								<fo:inline font-size="8px" font-style="normal">
																									<xsl:apply-templates/>
																								</fo:inline>
																							</xsl:for-each>
																						</fo:block>
																					</fo:table-cell>
																				</fo:table-row>
																			</fo:table-body>
																		</fo:table>
																	</xsl:for-each>
																</xsl:for-each>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell font-size="8px" display-align="before" height="9pt" number-columns-spanned="3" width="30pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<fo:inline font-size="8px" font-style="normal">6.&#160; * Federal Department/Agency: </fo:inline>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>
																<xsl:for-each select="SFLLL:FederalAgencyDepartment">
																	<fo:inline font-size="8px" font-style="normal">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell font-size="8px" display-align="before" height="9pt" width="30pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<fo:inline font-size="8px" font-style="normal">7.&#160; * Federal Program Name/Description: </fo:inline>
																<xsl:for-each select="SFLLL:FederalProgramName">
																	<xsl:for-each select="SFLLL:FederalProgramName">
																		<fo:inline font-size="8px" font-style="normal">
																			<xsl:apply-templates/>
																		</fo:inline>
																	</xsl:for-each>
																	<fo:inline font-size="8px">&#160;</fo:inline>
																	<xsl:for-each select="SFLLL:FederalProgramDescription">
																		<fo:inline font-size="8px" font-style="normal">
																			<xsl:apply-templates/>
																		</fo:inline>
																	</xsl:for-each>
																	<fo:block>
																		<fo:leader leader-pattern="space"/>
																	</fo:block>
																	<fo:inline font-size="8px" font-style="normal">CFDA Number, if applicable: </fo:inline>
																	<xsl:for-each select="globLib:CFDANumber">
																		<fo:inline font-size="8px" font-style="normal">
																			<xsl:apply-templates/>
																		</fo:inline>
																	</xsl:for-each>
																</xsl:for-each>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell font-size="8px" display-align="before" number-columns-spanned="3" width="30pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<fo:inline font-size="8px" font-style="normal">8. Federal Action Number, if known:</fo:inline>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>
																<xsl:for-each select="SFLLL:FederalActionNumber">
																	<fo:inline font-size="8px" font-style="normal">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell font-size="8px" display-align="before" width="30pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<fo:inline font-size="8px" font-style="normal">9.&#160; Award Amount, if known:</fo:inline>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>
																<xsl:for-each select="SFLLL:AwardAmount">
																	<fo:inline font-size="8px" font-style="normal">
																		<xsl:value-of select="format-number(., '$#,##0.00')"/>
																	</fo:inline>
																</xsl:for-each>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell font-size="8px" display-align="before" number-columns-spanned="3" text-align="left" width="30pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<fo:inline font-size="8px" font-style="normal">10. a. Name and Address of Lobbying Registrant (if individual, complete name):</fo:inline>
																<xsl:for-each select="SFLLL:LobbyingRegistrant">
																	<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																		<fo:table-column/>
																		<fo:table-body>
																			<fo:table-row>
																				<fo:table-cell border-after-color="white" border-before-color="white" border-end-color="white" border-start-color="white" font-size="8px" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																					<fo:block>
																						<fo:inline font-size="8px">Name: </fo:inline>
																						<xsl:for-each select="globLib:OrganizationName">
																							<fo:inline font-size="8px">
																								<xsl:apply-templates/>
																							</fo:inline>
																						</xsl:for-each>
																						<xsl:for-each select="SFLLL:IndividualName">&#160;<xsl:for-each select="globLib:PrefixName">
																								<xsl:apply-templates/>
																							</xsl:for-each>&#160;<xsl:for-each select="globLib:FirstName">
																								<xsl:apply-templates/>
																							</xsl:for-each>&#160;<xsl:for-each select="globLib:MiddleName">
																								<xsl:apply-templates/>
																							</xsl:for-each>&#160;<xsl:for-each select="globLib:LastName">
																								<xsl:apply-templates/>
																							</xsl:for-each>&#160;<xsl:for-each select="globLib:SuffixName">
																								<xsl:apply-templates/>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																			</fo:table-row>
																			<fo:table-row>
																				<fo:table-cell font-size="8px" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																					<fo:block>
																						<fo:inline font-size="8px">Address: </fo:inline>
																						<xsl:for-each select="SFLLL:Address">
																							<xsl:for-each select="globLib:Street1">
																								<fo:inline font-size="8px">
																									<xsl:apply-templates/>
																								</fo:inline>
																							</xsl:for-each>
																							<fo:inline font-size="8px">&#160;</fo:inline>
																							<xsl:for-each select="globLib:Street2">
																								<fo:inline font-size="8px">
																									<xsl:apply-templates/>
																								</fo:inline>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																			</fo:table-row>
																			<fo:table-row>
																				<fo:table-cell font-size="8px" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																					<fo:block>
																						<xsl:for-each select="SFLLL:Address">
																							<xsl:for-each select="globLib:City">
																								<fo:inline font-size="8px">
																									<xsl:apply-templates/>
																								</fo:inline>
																							</xsl:for-each>
																						</xsl:for-each>
																						<fo:inline font-size="8px">&#160;</fo:inline>
																						<xsl:for-each select="SFLLL:Address">
																							<xsl:for-each select="globLib:County">
																								<fo:inline font-size="8px">
																									<xsl:apply-templates/>
																								</fo:inline>
																							</xsl:for-each>
																							<fo:inline font-size="8px">&#160;</fo:inline>
																							<xsl:for-each select="globLib:State">
																								<fo:inline font-size="8px">
																									<xsl:apply-templates/>
																								</fo:inline>
																							</xsl:for-each>
																							<fo:inline font-size="8px">&#160; </fo:inline>
																							<xsl:for-each select="globLib:ZipCode">
																								<fo:inline font-size="8px">
																									<xsl:apply-templates/>
																								</fo:inline>
																							</xsl:for-each>
																							<fo:inline font-size="8px">&#160;</fo:inline>
																							<xsl:for-each select="globLib:Country">
																								<fo:inline font-size="8px">
																									<xsl:apply-templates/>
																								</fo:inline>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</fo:table-cell>
																			</fo:table-row>
																		</fo:table-body>
																	</fo:table>
																</xsl:for-each>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell font-size="8px" display-align="before" text-align="left" width="30pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<fo:inline font-size="8px">b. Individual Performing Services (including address if different from No. 10a):</fo:inline>
																<xsl:for-each select="SFLLL:IndividualsPerformingServices">
																	<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																		<fo:table-column/>
																		<fo:table-body>
																			<fo:table-row>
																				<fo:table-cell border-after-color="white" border-before-color="white" border-end-color="white" border-start-color="white" font-size="8px" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
																					<fo:block>
																						<xsl:for-each select="SFLLL:Individual">
																							<xsl:for-each select="SFLLL:Name">
																								<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																									<fo:table-column/>
																									<fo:table-body>
																										<fo:table-row>
																											<fo:table-cell font-size="8px" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																												<fo:block>Name: <xsl:for-each select="globLib:PrefixName">
																														<xsl:apply-templates/>
																													</xsl:for-each>&#160;<xsl:for-each select="globLib:FirstName">
																														<xsl:apply-templates/>
																													</xsl:for-each>&#160;<xsl:for-each select="globLib:MiddleName">
																														<xsl:apply-templates/>
																													</xsl:for-each>&#160;<xsl:for-each select="globLib:LastName">&#160;<xsl:apply-templates/>
																													</xsl:for-each>&#160;<xsl:for-each select="globLib:SuffixName">
																														<xsl:apply-templates/>
																													</xsl:for-each>
																												</fo:block>
																											</fo:table-cell>
																										</fo:table-row>
																									</fo:table-body>
																								</fo:table>
																							</xsl:for-each>
																							<xsl:for-each select="SFLLL:Address">
																								<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																									<fo:table-column/>
																									<fo:table-body>
																										<fo:table-row>
																											<fo:table-cell font-size="8px" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																												<fo:block>Address: <xsl:for-each select="globLib:Street1">
																														<xsl:apply-templates/>
																													</xsl:for-each>&#160;<xsl:for-each select="globLib:Street2">
																														<xsl:apply-templates/>
																													</xsl:for-each>
																												</fo:block>
																											</fo:table-cell>
																										</fo:table-row>
																										<fo:table-row>
																											<fo:table-cell font-size="8px" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																												<fo:block>
																													<xsl:for-each select="globLib:City">
																														<xsl:apply-templates/>
																													</xsl:for-each>&#160;<xsl:for-each select="globLib:County">
																														<xsl:apply-templates/>
																													</xsl:for-each>&#160;<xsl:for-each select="globLib:State">
																														<xsl:apply-templates/>
																													</xsl:for-each>&#160;<xsl:for-each select="globLib:ZipCode">
																														<xsl:apply-templates/>
																													</xsl:for-each>&#160;<xsl:for-each select="globLib:Country">
																														<xsl:apply-templates/>
																													</xsl:for-each>
																												</fo:block>
																											</fo:table-cell>
																										</fo:table-row>
																									</fo:table-body>
																								</fo:table>
																							</xsl:for-each>
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
													<fo:table-row>
														<fo:table-cell font-size="8px" display-align="before" height="10pt" number-columns-spanned="3" text-align="left" width="30pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<fo:inline font-size="8px">11. Information requested through this form is authorized by title 31 U.S.C. section 1352.&#160; This disclosure of lobbying activities is a material representation of fact upon which reliance was placed by the tier above when the transaction was made or entered into.&#160; This disclosure is required pursuant to 31 U.S.C. 1352.&#160; This information will be available for public inspection.&#160; Any person who fails to file the required disclosure shall be subject to a civil penalty of not less than $10,000 and not more than $100,000 for each such failure.</fo:inline>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell font-size="8px" display-align="before" height="10pt" text-align="left" width="30pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>
																<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																	<fo:table-column/>
																	<fo:table-body>
																		<fo:table-row>
																			<fo:table-cell font-size="8px" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																				<xsl:for-each select="SFLLL:SignatureBlock">
																					<fo:block>
																						<fo:inline font-size="8px">Signature: </fo:inline>
																						<xsl:for-each select="SFLLL:Signature">
																							<fo:inline font-size="8px">
																								<xsl:apply-templates/>
																							</fo:inline>
																						</xsl:for-each>
																					</fo:block>
																				</xsl:for-each>
																			</fo:table-cell>
																		</fo:table-row>
																		<fo:table-row>
																			<fo:table-cell font-size="8px" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																				<xsl:for-each select="SFLLL:SignatureBlock">
																					<fo:block>
																						<fo:inline font-size="8px">* Name: </fo:inline>
																						<xsl:for-each select="SFLLL:Name">
																							<xsl:for-each select="globLib:PrefixName">
																								<fo:inline font-size="8px">
																									<xsl:apply-templates/>
																								</fo:inline>
																							</xsl:for-each>
																							<fo:inline font-size="8px">&#160;</fo:inline>
																							<xsl:for-each select="globLib:FirstName">
																								<fo:inline font-size="8px">
																									<xsl:apply-templates/>
																								</fo:inline>
																							</xsl:for-each>
																							<fo:inline font-size="8px">&#160;</fo:inline>
																							<xsl:for-each select="globLib:MiddleName">
																								<fo:inline font-size="8px">
																									<xsl:apply-templates/>
																								</fo:inline>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</xsl:for-each>
																			</fo:table-cell>
																		</fo:table-row>
																		<fo:table-row>
																			<fo:table-cell font-size="8px" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																				<xsl:for-each select="SFLLL:SignatureBlock">
																					<fo:block>
																						<xsl:for-each select="SFLLL:Name">
																							<xsl:for-each select="globLib:LastName">
																								<fo:inline font-size="8px">
																									<xsl:apply-templates/>
																								</fo:inline>
																							</xsl:for-each>
																							<fo:inline font-size="8px">&#160;</fo:inline>
																							<xsl:for-each select="globLib:SuffixName">
																								<fo:inline font-size="8px">
																									<xsl:apply-templates/>
																								</fo:inline>
																							</xsl:for-each>
																						</xsl:for-each>
																					</fo:block>
																				</xsl:for-each>
																			</fo:table-cell>
																		</fo:table-row>
																		<fo:table-row>
																			<fo:table-cell font-size="8px" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																				<xsl:for-each select="SFLLL:SignatureBlock">
																					<fo:block>
																						<fo:inline font-size="8px">Title: </fo:inline>
																						<xsl:for-each select="SFLLL:Title">
																							<fo:inline font-size="8px">
																								<xsl:apply-templates/>
																							</fo:inline>
																						</xsl:for-each>
																					</fo:block>
																				</xsl:for-each>
																			</fo:table-cell>
																		</fo:table-row>
																		<fo:table-row>
																			<fo:table-cell font-size="8px" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																				<xsl:for-each select="SFLLL:SignatureBlock">
																					<fo:block>
																						<fo:inline font-size="8px">Telephone No.: </fo:inline>
																						<xsl:for-each select="SFLLL:Telephone">
																							<fo:inline font-size="8px">
																								<xsl:apply-templates/>
																							</fo:inline>
																						</xsl:for-each>
																					</fo:block>
																				</xsl:for-each>
																			</fo:table-cell>
																		</fo:table-row>
																		<fo:table-row>
																			<fo:table-cell font-size="8px" display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																				<xsl:for-each select="SFLLL:SignatureBlock">
																					<fo:block>
																						<fo:inline font-size="8px">Date: </fo:inline>
																						<xsl:for-each select="SFLLL:SignedDate">
																							<fo:inline font-size="8px">
																								<xsl:value-of select="format-number(substring(.,6,2), '00')"/>
																								<xsl:text>-</xsl:text>
																								<xsl:value-of select="format-number(substring(.,9,2), '00')"/>
																								<xsl:text>-</xsl:text>
																								<xsl:value-of select="format-number(substring(.,1,4), '0000')"/>
																							</fo:inline>
																						</xsl:for-each>
																					</fo:block>
																				</xsl:for-each>
																			</fo:table-cell>
																		</fo:table-row>
																	</fo:table-body>
																</fo:table>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell background-color="silver" font-size="7pt" height="9pt" number-columns-spanned="3" text-align="left" width="30pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>&#160; Federal Use Only:</fo:block>
														</fo:table-cell>
														<fo:table-cell font-size="7pt" height="9pt" text-align="right" width="30pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
															<fo:block>Authorized for Local Reproduction<fo:block>                                                                        
                                                                    </fo:block>
Standard Form - LLL (Rev. 7-97)</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell number-columns-spanned="4">
															<fo:block font-size="10pt" text-decoration="underline" font-weight="bold"> Public Burden Disclosure Statement</fo:block>
															<fo:leader leader-pattern="space"/>
															<fo:block width="8.5pt">
					According to the Paperwork Reduction Act, as amended, no persons are required to respond to a collection of information unless it displays a valid OMB Control Number.  The valid OMB control number for this information collection is OMB No. 0348-0046.  Public reporting burden for this collection of information is estimated to average 10 minutes per response, including time for reviewing instructions, searching existing data sources, gathering and maintaining the data needed, and completing and reviewing the collection of information. Send comments regarding the burden estimate or any other aspect of this collection of information, including suggestions for reducing this burden, to the Office of Management and Budget, Paperwork Reduction Project (0348-0046), Washington, DC 20503.</fo:block>
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
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>
