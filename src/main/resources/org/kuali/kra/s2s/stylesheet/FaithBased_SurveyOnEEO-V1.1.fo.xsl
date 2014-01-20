<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:FaithBased_SurveyOnEEO="http://apply.grants.gov/forms/FaithBased_SurveyOnEEO-V1.1" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:variable name="fo:layout-master-set">
		<fo:layout-master-set>
			<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.6in" margin-right="0.6in">
				<fo:region-body margin-top="0.79in" margin-bottom="0.79in"/>
				<fo:region-after extent=".79in"/>
			</fo:simple-page-master>
		</fo:layout-master-set>
	</xsl:variable>
	<xsl:template match="FaithBased_SurveyOnEEO:SurveyOnEEO">
		<fo:root>
			<xsl:copy-of select="$fo:layout-master-set"/>
			<fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
				<fo:static-content flow-name="xsl-region-after">
					<fo:block>
						<fo:inline font-size="6px" font-weight="bold">
   Tracking Number: 
  <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
						</fo:inline>
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<fo:block>
						<fo:table width="100%" space-before.optimum="2pt" space-after.optimum="2pt">
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell text-align="center" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
										<fo:block>
											<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
												<fo:table-column column-width="169pt"/>
												<fo:table-column/>
												<fo:table-body>
													<fo:table-row>
														<fo:table-cell display-align="center" number-columns-spanned="2" text-align="center" width="169pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" border-style="solid" border-width="1pt" border-color="white">
															<fo:block>
																<fo:leader leader-pattern="space"/>
															</fo:block>
															<fo:block>
																<fo:inline font-size="20px" font-weight="bold">Survey on Ensuring Equal Opportunity for Applicants</fo:inline>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell display-align="before" number-columns-spanned="2" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" border-style="solid" border-width="1pt" border-color="white">
															<fo:block>
																<fo:inline font-size="8px">OMB NO. 1890-0014&#160;&#160; EXP. 1/31/2006</fo:inline>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell background-color="silver" display-align="before" height="164pt" number-columns-spanned="2" width="169pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
															<fo:block>
																<fo:inline font-size="12px" font-weight="bold" text-decoration="underline">Purpose: </fo:inline>
																<fo:inline font-size="12px" font-weight="bold">&#160;</fo:inline>
																<fo:inline font-size="12px">The Federal government is committed to ensuring that all qualified applicants, small or large, non-religious or faith-based, have an equal opportunity to compete for Federal funding.&#160; In order for us to better understand the population of applicants for Federal funds, we are asking nonprofit private organizations (not including private universities) to fill out this survey.&#160; </fo:inline>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>
																<fo:block>
																	<fo:inline font-size="12px">Upon receipt, the survey will be separated from the application.&#160; Information provided on the survey will not be considered in any way in making funding decisions and will not be included in the Federal grants database.&#160; While your help in this data collection process is greatly appreciated, completion of this survey is voluntary. 
</fo:inline>
																</fo:block>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>
																<fo:inline font-size="12px" font-weight="bold" text-decoration="underline">Instructions for Submitting the Surve</fo:inline>
																<fo:inline font-size="12px">y:&#160; If you are applying using a hard copy application, please place the completed survey in an envelope labeled &quot;Applicant Survey.&quot;&#160; Seal the envelope and include it along with your application package.&#160;&#160; If you are applying electronically, please submit this survey along with your application.</fo:inline>
																<fo:block>
																	<fo:leader leader-pattern="space"/>
																</fo:block>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell number-columns-spanned="2" width="169pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
															<fo:block>
																<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																	<fo:table-column column-width="250pt"/>
																	<fo:table-column column-width="250pt"/>
																	<fo:table-body>
																		<fo:table-row>
																			<fo:table-cell number-columns-spanned="2" width="250pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																				<fo:block>
																					<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																						<fo:table-column column-width="250pt"/>
																						<fo:table-column/>
																						<fo:table-column/>
																						<fo:table-body>
																							<fo:table-row>
																								<fo:table-cell number-columns-spanned="3" width="250pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
																									<fo:block>
																										<fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																											<fo:table-column column-width="proportional-column-width(25)"/>
																											<fo:table-column column-width="proportional-column-width(25)"/>
																											<fo:table-body>
																												<fo:table-row>
																													<fo:table-cell number-columns-spanned="2" width="25%" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																														<fo:block>
																															<fo:inline font-size="12px" font-weight="bold">Applicant's (Organization) Name:</fo:inline>
																															<xsl:text> </xsl:text>
																															<xsl:for-each select="FaithBased_SurveyOnEEO:OrganizationName">
																																<fo:block hyphenate="true" language="en" keep-together="always" font-size="12px">
																																	<xsl:value-of select="."/>
																																</fo:block>
																															</xsl:for-each>
																														</fo:block>
																													</fo:table-cell>
																												</fo:table-row>
																												<fo:table-row>
																													<fo:table-cell number-columns-spanned="2" width="25%" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																														<fo:block>
																															<fo:inline font-size="12px" font-weight="bold">Applicant's DUNS Name:</fo:inline>
																															<xsl:text> </xsl:text>
																															<xsl:for-each select="FaithBased_SurveyOnEEO:DUNSID">
																																<fo:block hyphenate="true" language="en" keep-together="always" font-size="12px">
																																	<xsl:apply-templates/>
																																</fo:block>
																															</xsl:for-each>
																														</fo:block>
																													</fo:table-cell>
																												</fo:table-row>
																												<fo:table-row>
																													<fo:table-cell number-columns-spanned="2" width="25%" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																														<fo:block>
																															<fo:inline font-size="12px" font-weight="bold">Grant Title: </fo:inline>
																															<xsl:text> </xsl:text>
																															<xsl:for-each select="FaithBased_SurveyOnEEO:OpportunityTitle">
																																<fo:block hyphenate="true" language="en" keep-together="always" font-size="12px">
																																	<xsl:apply-templates/>
																																</fo:block>
																															</xsl:for-each>
																														</fo:block>
																													</fo:table-cell>
																												</fo:table-row>
																												<fo:table-row>
																													<fo:table-cell number-columns-spanned="2" width="25%" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																														<fo:block>
																															<fo:inline font-size="12px" font-weight="bold">CFDA Number:</fo:inline>
																															<xsl:text> </xsl:text>
																															<xsl:for-each select="FaithBased_SurveyOnEEO:CFDANumber">
																																<fo:block hyphenate="true" language="en" keep-together="always" font-size="12px">
																																	<xsl:apply-templates/>
																																</fo:block>
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
																			<fo:table-cell width="250pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																				<fo:block>
																					<fo:inline font-size="12px">1.&#160;&#160;&#160;&#160; Does the applicant have 501(c)(3) status?</fo:inline>
																					<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																						<fo:table-column/>
																						<fo:table-column/>
																						<fo:table-body>
																							<fo:table-row>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:ApplicantHas501c3">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='Y: Yes'">
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
																										<fo:inline font-size="12px"> Yes</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<fo:inline font-size="12px">&#160;</fo:inline>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:ApplicantHas501c3">
																											<fo:inline font-size="12px">
																												<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																													<fo:inline>
																														<xsl:choose>
																															<xsl:when test=".='N: No'">
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
																											</fo:inline>
																										</xsl:for-each>
																										<fo:inline font-size="12px">No</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																							</fo:table-row>
																						</fo:table-body>
																					</fo:table>
																					<fo:block>
																						<fo:leader leader-pattern="space"/>
																					</fo:block>
																				</fo:block>
																			</fo:table-cell>
																			<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																				<fo:block>
																					<fo:inline font-size="12px">4.&#160; Is the applicant a faith-based/religious organization?</fo:inline>
																					<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																						<fo:table-column/>
																						<fo:table-column/>
																						<fo:table-body>
																							<fo:table-row>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:FaithBasedReligious">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline>
																													<xsl:choose>
																														<xsl:when test=".='Y: Yes'">
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
																										</xsl:for-each>&#160;<fo:inline font-size="12px">Yes</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:FaithBasedReligious">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='N: No'">
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
																										<fo:inline font-size="12px"> No</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																							</fo:table-row>
																						</fo:table-body>
																					</fo:table>
																				</fo:block>
																			</fo:table-cell>
																		</fo:table-row>
																		<fo:table-row>
																			<fo:table-cell display-align="before" number-rows-spanned="2" width="250pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																				<fo:block>
																					<fo:inline font-size="12px">2.&#160; How many full-time equivalent&#160; employees does the applicant have?&#160; </fo:inline>
																					<fo:inline font-size="12px" font-style="italic">(Check only one box)</fo:inline>
																					<fo:inline font-size="12px">.</fo:inline>
																					<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																						<fo:table-column/>
																						<fo:table-column/>
																						<fo:table-body>
																							<fo:table-row>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:FullTimeEmployeeNumber">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline>
																													<xsl:choose>
																														<xsl:when test=".='3 or fewer'">
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
																										</xsl:for-each>&#160;<fo:inline font-size="12px">3 or Fewer</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:FullTimeEmployeeNumber">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='15-50'">
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
																										<fo:inline font-size="12px"> 15 - 50</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																							</fo:table-row>
																							<fo:table-row>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:FullTimeEmployeeNumber">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='4-5'">
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
																										<fo:inline font-size="12px"> 4 -5</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:FullTimeEmployeeNumber">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='51-100'">
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
																										<fo:inline font-size="12px"> 51 - 100</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																							</fo:table-row>
																							<fo:table-row>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:FullTimeEmployeeNumber">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='6-14'">
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
																										<fo:inline font-size="12px"> 6 -14</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:FullTimeEmployeeNumber">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='Over 100'">
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
																										<fo:inline font-size="12px"> Over 1000</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																							</fo:table-row>
																						</fo:table-body>
																					</fo:table>
																				</fo:block>
																			</fo:table-cell>
																			<fo:table-cell display-align="before" width="250pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																				<fo:block>
																					<fo:inline font-size="12px">5.&#160; Is the applicant a non-religious community-based organization? </fo:inline>
																					<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																						<fo:table-column/>
																						<fo:table-column/>
																						<fo:table-body>
																							<fo:table-row>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:NonReligiousCommunityBased">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='Y: Yes'">
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
																										<fo:inline font-size="12px"> Yes</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:NonReligiousCommunityBased">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='N: No'">
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
																										<fo:inline font-size="12px"> No</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																							</fo:table-row>
																						</fo:table-body>
																					</fo:table>
																				</fo:block>
																			</fo:table-cell>
																		</fo:table-row>
																		<fo:table-row>
																			<fo:table-cell display-align="before" width="250pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																				<fo:block>
																					<fo:inline font-size="12px">6.&#160; Is the applicant an intermediary that will manage the grant on behalf of other organizations?
</fo:inline>
																					<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																						<fo:table-column/>
																						<fo:table-column/>
																						<fo:table-body>
																							<fo:table-row>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:IsIntermediary">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='Y: Yes'">
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
																										<fo:inline font-size="12px"> Yes</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:IsIntermediary">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='N: No'">
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
																										<fo:inline font-size="12px"> No</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																							</fo:table-row>
																						</fo:table-body>
																					</fo:table>
																				</fo:block>
																			</fo:table-cell>
																		</fo:table-row>
																		<fo:table-row>
																			<fo:table-cell display-align="before" number-rows-spanned="2" width="250pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																				<fo:block>
																					<fo:inline font-size="12px">3.&#160; What is the size of the applicant's&#160; annual budget?&#160; (Check only one box.)</fo:inline>
																					<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																						<fo:table-column/>
																						<fo:table-body>
																							<fo:table-row>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:ApplicantAnnualBudget">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='Less Than $150,000'">
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
																										<fo:inline font-size="12px"> Less Than $150,000</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																							</fo:table-row>
																							<fo:table-row>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:ApplicantAnnualBudget">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='$150,000 - $299,999'">
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
																										<fo:inline font-size="12px"> $150,000 - $299,999</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																							</fo:table-row>
																							<fo:table-row>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:ApplicantAnnualBudget">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='$300,000 - $499,999'">
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
																										<fo:inline font-size="12px"> $300,000 - $499,999</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																							</fo:table-row>
																							<fo:table-row>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:ApplicantAnnualBudget">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='$500,000 - $999,999'">
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
																										<fo:inline font-size="12px"> $500,000 - $999,999</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																							</fo:table-row>
																							<fo:table-row>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:ApplicantAnnualBudget">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='$1,000,000 - $4,999,999'">
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
																										<fo:inline font-size="12px"> $1,000,000 - $4,999,999</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																							</fo:table-row>
																							<fo:table-row>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:ApplicantAnnualBudget">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='$5,000,000 or more'">
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
																										<fo:inline font-size="12px"> $5,000,000 or more</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																							</fo:table-row>
																						</fo:table-body>
																					</fo:table>
																				</fo:block>
																			</fo:table-cell>
																			<fo:table-cell display-align="before" width="250pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																				<fo:block>
																					<fo:inline font-size="12px">7.&#160;&#160;&#160;&#160; Has the applicant ever received a government grant or contract (Federal, State, or local )?
</fo:inline>
																					<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																						<fo:table-column/>
																						<fo:table-column/>
																						<fo:table-body>
																							<fo:table-row>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:EverReceivedGovGrantContract">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='Y: Yes'">
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
																										<fo:inline font-size="12px"> Yes</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:EverReceivedGovGrantContract">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='N: No'">
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
																										<fo:inline font-size="12px"> No</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																							</fo:table-row>
																						</fo:table-body>
																					</fo:table>
																				</fo:block>
																			</fo:table-cell>
																		</fo:table-row>
																		<fo:table-row>
																			<fo:table-cell display-align="before" width="250pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																				<fo:block>
																					<fo:inline font-size="12px">8.&#160; Is the applicant a local affiliate of a national organization? </fo:inline>
																					<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
																						<fo:table-column/>
																						<fo:table-column/>
																						<fo:table-body>
																							<fo:table-row>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:LocalAffiliateOFNationalOrg">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='Y: Yes'">
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
																										<fo:inline font-size="12px"> Yes</fo:inline>
																									</fo:block>
																								</fo:table-cell>
																								<fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																									<fo:block>
																										<xsl:for-each select="FaithBased_SurveyOnEEO:LocalAffiliateOFNationalOrg">
																											<fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
																												<fo:inline font-size="12px">
																													<xsl:choose>
																														<xsl:when test=".='N: No'">
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
																										<fo:inline font-size="12px"> No</fo:inline>
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
