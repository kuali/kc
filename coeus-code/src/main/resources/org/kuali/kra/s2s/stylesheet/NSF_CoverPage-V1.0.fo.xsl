<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:NSF_CoverPage="http://apply.grants.gov/forms/NSF_CoverPage-V1.0" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema">
	<xsl:variable name="fo:layout-master-set">
		<fo:layout-master-set>
			<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.4in" margin-right="0.4in">
				<fo:region-body margin-top="0.9in" margin-bottom="0.6in"/>
				<fo:region-after extent=".5in"/>
			</fo:simple-page-master>
		</fo:layout-master-set>
	</xsl:variable>
	<xsl:template match="NSF_CoverPage:NSF_CoverPage">
		<fo:root>
			<xsl:copy-of select="$fo:layout-master-set"/>
			<fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
				<fo:static-content flow-name="xsl-region-after">
					<fo:table width="100%">
						<fo:table-column width="50%"/>
						<fo:table-column width="50%"/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell line-height="11pt" display-align="before">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell line-height="11pt" display-align="after" text-align="right">
									<fo:block>
										<fo:inline text-align="right" font-size="6px">OMB Number 3145-0058</fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<fo:block>
						<fo:leader leader-pattern="space"/>
						<fo:leader leader-pattern="space"/>
						<fo:block text-align="center">
							<fo:inline font-weight="bold" font-size="12pt"> National Science Foundation</fo:inline>
						</fo:block>
						<fo:block text-align="center">
							<fo:inline font-weight="bold" font-size="12pt"> Grant Application Cover Page</fo:inline>
						</fo:block>
						<fo:table border-style="solid" border-color="black" space-before.optimum="5pt" space-after.optimum="2pt" border-width="1pt" width="100%">
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell> 
                                                                            <fo:block/>
								</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" border-style="solid" border-before-width="0pt" border-color="black" border-width="1pt">
										<fo:block>
											<fo:table width="100%">
												<fo:table-column column-width=".9in"/>
												<fo:table-column column-width=".9in"/>
												<fo:table-column column-width="2.0in"/>
												<fo:table-column column-width="1.1in"/>
												<fo:table-column column-width="1.1in"/>
												<fo:table-column/>
												<fo:table-body>
													<fo:table-row border-style="solid" border-color="black" border-before-width="0pt" border-width="1pt">
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt" number-columns-spanned="6">
															<fo:block font-size="8pt">Please complete the following NSF forms in conjunction with the relevant Research and Related forms. If you are an organization or individual and you are not registered with NSF FastLane, please complete the Organization and Individual Registration Form in this package.</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt" number-columns-spanned="6">
															<fo:block font-size="8pt" font-weight="bold">1. Funding Opportunity Number</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt" number-columns-spanned="2">
															<fo:block font-size="8pt">*Funding Opportunity Number:&#160;</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" hyphenate="true" language="en">
															<fo:block font-size="8pt">
																<xsl:value-of select="NSF_CoverPage:FundingOpportunityNumber"/>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" number-columns-spanned="3">
															<fo:block font-size="8pt">Opportunity closing date:
														
														
															
																<xsl:for-each select="NSF_CoverPage:DueDate">
																	<xsl:value-of select="format-number(substring(.,6,2), '00')"/>
																	<xsl:text>-</xsl:text>
																	<xsl:value-of select="format-number(substring(.,9,2), '00')"/>
																	<xsl:text>-</xsl:text>
																	<xsl:value-of select="format-number(substring(.,1,4), '0000')"/>
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
									<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="1pt" border-style="solid" border-color="black" border-width="1pt">
										<fo:block>
											<fo:table space-before.optimum="1pt" space-after.optimum="2pt" width="100%">
												<fo:table-column column-width="1.1in"/>
												<fo:table-column/>
												<fo:table-column column-width="1.2in"/>
												<fo:table-column/>
												<fo:table-body>
													<fo:table-row>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt" number-columns-spanned="4">
															<fo:block font-size="8pt" font-weight="bold">2. NSF Unit Consideration</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt" number-columns-spanned="4">
															<fo:block font-size="8pt">Go to	https://www.fastlane.nsf.gov/pgmannounce.jsp and follow the instructions to find the Division and Program information for this funding opportunity.</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt">
															<fo:block font-size="8pt">*Division Code:</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="1pt">
															<fo:block font-size="8pt" hyphenate="true" language="en">
																<xsl:value-of select="NSF_CoverPage:NSFUnitConsideration/NSF_CoverPage:DivisionCode"/>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="1pt">
															<fo:block font-size="8pt">Division Name:</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="1pt">
															<fo:block font-size="8pt" hyphenate="true" language="en">
																<xsl:value-of select="NSF_CoverPage:NSFUnitConsideration/NSF_CoverPage:DivisionName"/>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt">
															<fo:block font-size="8pt">*Program Code:</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="1pt">
															<fo:block font-size="8pt" hyphenate="true" language="en">
																<xsl:value-of select="NSF_CoverPage:NSFUnitConsideration/NSF_CoverPage:ProgramCode"/>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="1pt">
															<fo:block font-size="8pt">Program Name:</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="1pt">
															<fo:block font-size="8pt" hyphenate="true" language="en">
																<xsl:value-of select="NSF_CoverPage:NSFUnitConsideration/NSF_CoverPage:ProgramName"/>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
												</fo:table-body>
											</fo:table>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" border-style="solid" border-color="black" border-width="1pt">
										<fo:block>
											<fo:table space-before.optimum="1pt" space-after.optimum="2pt" width="100%">
												<fo:table-column/>
												<fo:table-column/>
												<fo:table-column/>
												<fo:table-column/>
												<fo:table-body>
													<fo:table-row>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt" number-columns-spanned="4">>
														<fo:block font-size="8pt" font-weight="bold">3. Principal Investigator (PI) Information</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt">
															<fo:block font-size="8pt">*Degree Type:</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt">
															<fo:block font-size="8pt" hyphenate="true" language="en">
																<xsl:value-of select="NSF_CoverPage:PIInfo/NSF_CoverPage:DegreeType"/>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="1pt">
															<fo:block font-size="8pt">*Degree Year:</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="1pt">
															<fo:block font-size="8pt" hyphenate="true" language="en">
																<xsl:value-of select="NSF_CoverPage:PIInfo/NSF_CoverPage:DegreeYear"/>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt" number-columns-spanned="4">
															<fo:block font-size="8pt">
																<xsl:choose>
																	<xsl:when test="NSF_CoverPage:PIInfo/NSF_CoverPage:isCurrentPI !='No'">
																		<fo:inline text-decoration="underline" font-family="ZapfDingbats" font-size="8pt" padding-start="1.5pt" padding-end="1.5pt">&#x2714;</fo:inline>
																	</xsl:when>
																	<xsl:otherwise>
																		<fo:inline text-decoration="underline" color="black">
																			<fo:leader leader-length="7pt" leader-pattern="rule"/>
																		</fo:inline>
																	</xsl:otherwise>
																</xsl:choose>
															Check here if you are currently serving (or have previously served) as a PI, co-PI or Program Director (PD) on any Federally funded project.</fo:block>
														</fo:table-cell>
													</fo:table-row>
												</fo:table-body>
											</fo:table>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" border-style="solid" border-color="black" border-width="1pt">
										<fo:block>
											<fo:table space-before.optimum="1pt" space-after.optimum="2pt" width="100%">
												<fo:table-column column-width="0.5in"/>
												<fo:table-column column-width="0.8in"/>
												<fo:table-column column-width="0.7in"/>
												<fo:table-column/>
												<fo:table-column column-width="0.9in"/>
												<fo:table-column/>
												<fo:table-column column-width="0.7in"/>
												<fo:table-column/>
												<fo:table-column column-width="0.4in"/>
												<fo:table-column/>
												<fo:table-body>
													<fo:table-row>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt" number-columns-spanned="10">
															<fo:block font-size="8pt" font-weight="bold">4. Co-Principal Investigator (co-PI) Information</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt" number-columns-spanned="10">
															<fo:block font-size="8pt">NSF applications can identify a maximum of four co-Principal Investigators. Please enter below the co-PI information exactly as entered on the Research and Related Senior/Key Person form.</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<xsl:for-each select="NSF_CoverPage:CoPIInfo/NSF_CoverPage:CoPI">
														<fo:table-row>
															<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt" number-columns-spanned="10">
																<fo:block font-size="8pt" font-weight="bold">co-PI <xsl:value-of select="position()"/>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt" number-columns-spanned="2">
																<fo:block font-size="8pt">Prefix:</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="2" padding-before="2pt" padding-after="2pt" padding-end="1pt" font-size="8pt">
																<fo:block>* First Name:</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="2" padding-before="2pt" padding-after="2pt" padding-end="1pt" font-size="8pt">
																<fo:block>Middle Name:</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="2" padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="1pt">
																<fo:block font-size="8pt">* Last Name:</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="2" padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="1pt">
																<fo:block font-size="8pt">Suffix:</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell number-columns-spanned="2" padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt">
																<fo:block font-size="8pt" hyphenate="true" language="en">
																	<xsl:value-of select="NSF_CoverPage:Name/globLib:PrefixName"/>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="2" padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="1pt">
																<fo:block font-size="8pt" hyphenate="true" language="en">
																	<xsl:value-of select="NSF_CoverPage:Name/globLib:FirstName"/>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="2" padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="1pt">
																<fo:block font-size="8pt" hyphenate="true" language="en">
																	<xsl:value-of select="NSF_CoverPage:Name/globLib:MiddleName"/>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="2" padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="1pt">
																<fo:block font-size="8pt" hyphenate="true" language="en">
																	<xsl:value-of select="NSF_CoverPage:Name/globLib:LastName"/>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="2" padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="1pt">
																<fo:block font-size="8pt" hyphenate="true" language="en">
																	<xsl:value-of select="NSF_CoverPage:Name/globLib:SuffixName"/>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell number-columns-spanned="5" padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt">
																<fo:block font-size="8pt" hyphenate="true" language="en">*Degree Type:	
																<xsl:value-of select="NSF_CoverPage:DegreeType"/>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell number-columns-spanned="5" padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="1pt">
																<fo:block font-size="8pt" hyphenate="true" language="en">*Degree Year:
																<xsl:value-of select="NSF_CoverPage:DegreeYear"/>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
													</xsl:for-each>
												</fo:table-body>
											</fo:table>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" border-style="solid" border-color="black" border-width="1pt">
										<fo:block>
											<fo:table space-before.optimum="1pt" space-after.optimum="2pt" width="100%">
												<fo:table-column column-width="proportional-column-width(5)"/>
												<fo:table-column column-width="proportional-column-width(45)"/>
												<fo:table-column column-width="proportional-column-width(2)"/>
												<fo:table-column column-width="proportional-column-width(48)"/>
												<fo:table-body>
													<fo:table-row>
														<fo:table-cell padding-before="1pt" padding-after="1pt" padding-end="1pt" padding-start="15pt" number-columns-spanned="4">
															<fo:block font-size="8pt" font-weight="bold">5. Other Information</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt" number-columns-spanned="4">
															<fo:block font-size="8pt">Check Appropriate Box (es) if this proposal includes any of the items listed below.</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell padding-before="1pt" padding-after="1pt" padding-end="1pt" padding-start="15pt">
															<fo:block font-size="8pt">
																<xsl:choose>
																	<xsl:when test="NSF_CoverPage:OtherInfo/NSF_CoverPage:isBeginInvestigator !='No'">
																		<fo:inline text-decoration="underline" font-family="ZapfDingbats" font-size="8pt" padding-start="1.5pt" padding-end="1.5pt">&#x2714;</fo:inline>
																	</xsl:when>
																	<xsl:otherwise>
																		<fo:inline text-decoration="underline" color="black">
																			<fo:leader leader-length="7pt" leader-pattern="rule"/>
																		</fo:inline>
																	</xsl:otherwise>
																</xsl:choose>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="1pt" padding-after="1pt" padding-end="1pt" padding-start="1pt">
															<fo:block font-size="8pt">Beginning Investigator (Grant Proposal Guide(GPG), Chapter I.A)</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="1pt" padding-after="1pt" padding-end="1pt" padding-start="1pt">
															<fo:block font-size="8pt">
																<xsl:choose>
																	<xsl:when test="NSF_CoverPage:OtherInfo/NSF_CoverPage:isDisclosureLobbyingActivities !='No'">
																		<fo:inline text-decoration="underline" font-family="ZapfDingbats" font-size="8pt" padding-start="1.5pt" padding-end="1.5pt">&#x2714;</fo:inline>
																	</xsl:when>
																	<xsl:otherwise>
																		<fo:inline text-decoration="underline" color="black">
																			<fo:leader leader-length="7pt" leader-pattern="rule"/>
																		</fo:inline>
																	</xsl:otherwise>
																</xsl:choose>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="1pt" padding-after="1pt" padding-end="1pt" padding-start="1pt">
															<fo:block font-size="8pt">Disclosure of Lobbying Activities (GPG, Chapter II.C.1.e)</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell padding-before="1pt" padding-after="1pt" padding-end="1pt" padding-start="15pt">
															<fo:block font-size="8pt">
																<xsl:choose>
																	<xsl:when test="NSF_CoverPage:OtherInfo/NSF_CoverPage:isExploratoryResearch !='No'">
																		<fo:inline text-decoration="underline" font-family="ZapfDingbats" font-size="8pt" padding-start="1.5pt" padding-end="1.5pt">&#x2714;</fo:inline>
																	</xsl:when>
																	<xsl:otherwise>
																		<fo:inline text-decoration="underline" color="black">
																			<fo:leader leader-length="7pt" leader-pattern="rule"/>
																		</fo:inline>
																	</xsl:otherwise>
																</xsl:choose>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="1pt" padding-after="1pt" padding-end="1pt" padding-start="1pt">
															<fo:block font-size="8pt">Small Grants for Exploratory Research (GPG, Chapter II.D.1)</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="1pt" padding-after="1pt" padding-end="1pt" padding-start="1pt">
															<fo:block font-size="8pt">
																<xsl:choose>
																	<xsl:when test="NSF_CoverPage:OtherInfo/NSF_CoverPage:isHistoricPlaces !='No'">
																		<fo:inline text-decoration="underline" font-family="ZapfDingbats" font-size="8pt" padding-start="1.5pt" padding-end="1.5pt">&#x2714;</fo:inline>
																	</xsl:when>
																	<xsl:otherwise>
																		<fo:inline text-decoration="underline" color="black">
																			<fo:leader leader-length="7pt" leader-pattern="rule"/>
																		</fo:inline>
																	</xsl:otherwise>
																</xsl:choose>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="1pt" padding-after="1pt" padding-end="1pt" padding-start="1pt">
															<fo:block font-size="8pt">Historic Places (GPG, Chapter II.C.2.j)</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell padding-before="1pt" padding-after="1pt" padding-end="1pt" padding-start="15pt">
															<fo:block font-size="8pt">
																<xsl:choose>
																	<xsl:when test="NSF_CoverPage:OtherInfo/NSF_CoverPage:isAccomplishmentRenewal !='No'">
																		<fo:inline text-decoration="underline" font-family="ZapfDingbats" font-size="8pt" padding-start="1.5pt" padding-end="1.5pt">&#x2714;</fo:inline>
																	</xsl:when>
																	<xsl:otherwise>
																		<fo:inline text-decoration="underline" color="black">
																			<fo:leader leader-length="7pt" leader-pattern="rule"/>
																		</fo:inline>
																	</xsl:otherwise>
																</xsl:choose>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="1pt" padding-after="1pt" padding-end="1pt" padding-start="1pt">
															<fo:block font-size="8pt">Accomplishment-Based Renewal  (GPG, Chapter V.B.2)																		</fo:block>
															<fo:block font-size="4pt"> &#160;</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="1pt" padding-after="1pt" padding-end="1pt" padding-start="1pt">
															<fo:block font-size="8pt">
																<xsl:choose>
																	<xsl:when test="NSF_CoverPage:OtherInfo/NSF_CoverPage:isHighResolutionGraphics !='No'">
																		<fo:inline text-decoration="underline" font-family="ZapfDingbats" font-size="8pt" padding-start="1.5pt" padding-end="1.5pt">&#x2714;</fo:inline>
																	</xsl:when>
																	<xsl:otherwise>
																		<fo:inline text-decoration="underline" color="black">
																			<fo:leader leader-length="7pt" leader-pattern="rule"/>
																		</fo:inline>
																	</xsl:otherwise>
																</xsl:choose>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="1pt" padding-after="1pt" padding-end="1pt" padding-start="1pt">
															<fo:block font-size="8pt">
High Resolution Graphics/Other Graphics Where Exact Color Representation Is Required For Proper Interpretation (GPG, Chapter I.G.1)															</fo:block>
														</fo:table-cell>
													</fo:table-row>
												</fo:table-body>
											</fo:table>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="1pt">
										<fo:block>
											<fo:table space-before.optimum="1pt" space-after.optimum="2pt" width="100%">
												<fo:table-column/>
												<fo:table-column/>
												<fo:table-body>
													<fo:table-row>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt">
															<fo:block font-size="8pt">
																<fo:inline font-weight="bold">6. Additional Single-Copy Documents</fo:inline> &#160;&#160;&#160;Attach PDF Files</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt">
															<fo:block font-size="8pt">File Name:</fo:block>
														</fo:table-cell>
														<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="1pt">
															<fo:block font-size="8pt">Mime Type:</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<xsl:for-each select="NSF_CoverPage:Single-CopyDocuments/att:AttachedFile">
														<fo:table-row>
															<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="15pt">
																<fo:block font-size="8pt" hyphenate="true" language="en">
																	<xsl:value-of select="att:FileName"/>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell padding-before="2pt" padding-after="2pt" padding-end="1pt" padding-start="1pt">
																<fo:block font-size="8pt" hyphenate="true" language="en">
																	<xsl:value-of select="att:MimeType"/>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
													</xsl:for-each>
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
