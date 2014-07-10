<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:ED_CertificationDebarment="http://apply.grants.gov/forms/ED_CertificationDebarment-V1.1" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:variable name="fo:layout-master-set">
		<fo:layout-master-set>
			<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.5in" margin-right="0.5in">
				<fo:region-body margin-top="0.5in" margin-bottom="0.8in"/>
				<fo:region-after extent=".8in"/>
			</fo:simple-page-master>
		</fo:layout-master-set>
	</xsl:variable>
	<xsl:template match="ED_CertificationDebarment:CertificationDebarment">
		<fo:root>
			<xsl:copy-of select="$fo:layout-master-set"/>
			<fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
				<fo:static-content flow-name="xsl-region-after">
					<fo:block>
						<fo:inline font-size="7px">
ED 80-0014, 9/90 (Replaces GCS-009 (REV.12/88), which is obsolete)
</fo:inline>
					</fo:block>
					<fo:block>
						<fo:inline font-size="6px" font-weight="bold">
   Tracking Number: 
  <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
						</fo:inline>
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<fo:block>
						<fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
							<fo:table-column column-width="proportional-column-width(49)"/>
							<fo:table-column column-width="proportional-column-width(2)"/>
							<fo:table-column column-width="proportional-column-width(49)"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell line-height="12pt" number-columns-spanned="3" text-align="center" width="49%" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
										<fo:block>
											<fo:inline font-size="10px" font-weight="bold">Certification Regarding Debarment, Suspension, Ineligibility and
Voluntary Exclusion - Lower Tier Covered Transactions
</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell line-height="12pt" number-columns-spanned="3" width="49%" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
										<fo:block>
											<fo:block color="black" space-before.optimum="-8pt">
												<fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="3pt"/>
											</fo:block>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell line-height="12pt" number-columns-spanned="3" width="49%" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
										<fo:block>
											<fo:inline font-size="8px">This certification is required by the Department of Education regulations implementing Executive Order 12549, Debarment and Suspension, 34 CFR Part 85, for all lower tier transactions meeting the threshold and tier requirements stated at Section 85.110.
</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell line-height="12pt" number-columns-spanned="3" width="49%" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
										<fo:block>
											<fo:inline font-size="8px" font-weight="bold">Instructions for Certification
</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell line-height="12pt" display-align="before" width="49%" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
										<fo:block>
											<fo:inline font-size="8px">1. By signing and submitting this proposal, the prospective lower tier participant is providing the certification set out below.
<fo:block>
													<fo:leader leader-pattern="space"/>
												</fo:block>

2. The certification in this clause is a material representation of fact upon which reliance was placed when this transaction was entered into. If it is later determined that the prospective lower tier participant knowingly rendered an erroneous certification, in addition to other remedies available to the Federal Government, the department or agency with which this transaction originated may pursue available remedies, including suspension and/or debarment.
<fo:block>
													<fo:leader leader-pattern="space"/>
												</fo:block>

3. The prospective lower tier participant shall provide immediate written notice to the person to which this proposal is submitted if at any time the prospective lower tier participant learns that its certification was erroneous when submitted or has become erroneous by reason of changed circumstances.
<fo:block>
													<fo:leader leader-pattern="space"/>
												</fo:block>

4. The terms "covered transaction," "debarred," "suspended," "ineligible," "lower tier covered transaction," "participant," "person," "primary covered transaction," "principal," "proposal," and "voluntarily excluded," as used in this clause, have the meanings set out in the Definitions and Coverage sections of rules implementing Executive Order 12549. You may contact the person to which this proposal is submitted for assistance in obtaining a copy of those regulations.
<fo:block>
													<fo:leader leader-pattern="space"/>
												</fo:block>

5. The prospective lower tier participant agrees by submitting this proposal that, should the proposed covered transaction be entered into, it shall not knowingly enter into any lower tier covered transaction with a person who is debarred, suspended, declared ineligible, or voluntarily excluded from participation in this covered transaction, unless authorized by the department or agency with which this transaction originated.</fo:inline>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell line-height="12pt" width="2%" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
										<fo:block/>
									</fo:table-cell>
									<fo:table-cell line-height="12pt" display-align="before" width="49%" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
										<fo:block>
											<fo:inline font-size="8px">6. The prospective lower tier participant further agrees by submitting this proposal that it will include the clause titled A Certification Regarding Debarment, Suspension, Ineligibility, and Voluntary Exclusion-Lower Tier Covered Transactions, and without modification, in all lower tier covered transactions and in all solicitations for lower tier covered transactions.
<fo:block>
													<fo:leader leader-pattern="space"/>
												</fo:block>

7. A participant in a covered transaction may rely upon a certification of a prospective participant in a lower tier covered transaction that it is not debarred, suspended, ineligible, or voluntarily excluded from the covered transaction, unless it knows that the certification is erroneous. A participant may decide the method and frequency by which it
determines the eligibility of its principals. Each participant may but is not required to, check the Nonprocurement List.
<fo:block>
													<fo:leader leader-pattern="space"/>
												</fo:block>

8. Nothing contained in the foregoing shall be construed to require establishment of a system of records in order to render in good faith the certification required by this clause. The knowledge and information of a participant is not required to exceed that which is normally possessed by a prudent person in the ordinary course of business dealings.
<fo:block>
													<fo:leader leader-pattern="space"/>
												</fo:block>

9. Except for transactions authorized under paragraph 5 of these instructions, if a participant in a covered transaction knowingly enters into a lower tier covered transaction with a person who is suspended, debarred, ineligible, or voluntarily excluded from participation in this transaction, in addition to other remedies available to the ederal
Government, the department or agency with which this transaction originated may pursue available remedies, including suspension and/or debarment.</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell line-height="12pt" number-columns-spanned="3" width="49%" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
										<fo:block>
											<fo:block color="black" space-before.optimum="-8pt">
												<fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="3pt"/>
											</fo:block>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell line-height="12pt" number-columns-spanned="3" width="49%" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
										<fo:block>
											<fo:inline font-size="8px" font-weight="bold">Certification</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell line-height="12pt" number-columns-spanned="3" width="49%" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
										<fo:block>
											<fo:inline font-size="8px">(1) The prospective lower tier participant certifies, by submission of this proposal, that neither it nor its principals are presently debarred suspended, proposed for debarment, declared ineligible, or voluntarily excluded from participation in this transaction by any Federal&#160; department or agency.</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell line-height="12pt" number-columns-spanned="3" width="49%" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
										<fo:block>
											<fo:inline font-size="8px">(2) Where the prospective lower tier participant is unable to certify to any of the statements in this certification, such prospective participant shall attach an explanation to this proposal.</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell line-height="12pt" number-columns-spanned="3" width="49%" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
										<fo:block>
											<fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
												<fo:table-column/>
												<fo:table-column/>
												<fo:table-column/>
												<fo:table-column/>
												<fo:table-body>
													<fo:table-row>
														<fo:table-cell line-height="12pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
															<fo:block>
																<fo:inline font-size="8px">* NAME OF APPLICANT</fo:inline>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell line-height="12pt" number-columns-spanned="3" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
															<fo:block>
																<fo:inline font-size="8px">
</fo:inline>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell line-height="12pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
															<fo:block>
																<xsl:for-each select="ED_CertificationDebarment:OrganizationName">
																	<fo:inline font-size="8px">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell line-height="12pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
															<fo:block>
																<fo:inline font-size="8px"/>
																<xsl:for-each select="globLib:ProjectAwardNumber">
																	<fo:inline font-size="8px">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell line-height="12pt" number-columns-spanned="2" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
															<fo:block>
																<fo:inline font-size="8px"> </fo:inline>
																<xsl:for-each select="ED_CertificationDebarment:ProjectName">
																	<fo:inline font-size="8px">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell line-height="12pt" number-columns-spanned="4" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
															<fo:block>
																<fo:inline font-size="8px">* PRINTED NAME AND TITLE OF AUTHORIZED REPRESENTATIVE</fo:inline>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell line-height="12pt" number-columns-spanned="4" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white" hyphenate="true" language="en">
															<fo:block>
																<fo:inline font-size="8px">* Name: </fo:inline>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<xsl:for-each select="ED_CertificationDebarment:AuthorizedRepresentativeName">
														<fo:table-row>
															<fo:table-cell line-height="12pt" number-columns-spanned="4" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white" hyphenate="true" language="en">
																<fo:block>
																	<fo:inline font-size="8px">Prefix:  </fo:inline>
																	<xsl:for-each select="globLib:PrefixName">
																		<fo:inline font-size="8px">
																			<xsl:apply-templates/>
																		</fo:inline>
																	</xsl:for-each>
																	<fo:inline font-size="8px">&#160;&#160;&#160;* First Name:  </fo:inline>
																	<xsl:for-each select="globLib:FirstName">
																		<fo:inline font-size="8px">
																			<xsl:apply-templates/>
																		</fo:inline>
																	</xsl:for-each>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell line-height="12pt" number-columns-spanned="4" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																<fo:block>
																	<fo:inline font-size="8px">Middle Name:  </fo:inline>
																	<xsl:for-each select="globLib:MiddleName">
																		<fo:inline font-size="8px">
																			<xsl:apply-templates/>
																		</fo:inline>
																	</xsl:for-each>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell line-height="12pt" number-columns-spanned="4" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																<fo:block>
																	<fo:inline font-size="8px">* Last Name:  </fo:inline>
																	<xsl:for-each select="globLib:LastName">
																		<fo:inline font-size="8px">
																			<xsl:apply-templates/>
																		</fo:inline>
																	</xsl:for-each>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell line-height="12pt" number-columns-spanned="4" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
																<fo:block>
																	<fo:inline font-size="8px">Suffix:  </fo:inline>
																	<xsl:for-each select="globLib:SuffixName">
																		<fo:inline font-size="8px">
																			<xsl:apply-templates/>
																		</fo:inline>
																	</xsl:for-each>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
													</xsl:for-each>
													<fo:table-row>
														<fo:table-cell line-height="12pt" number-columns-spanned="2" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white" hyphenate="true" language="en">
															<fo:block>
																<fo:inline font-size="8px">* Title: </fo:inline>
																<xsl:for-each select="ED_CertificationDebarment:AuthorizedRepresentativeTitle">
																	<fo:inline font-size="8px">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell line-height="12pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
															<fo:block/>
														</fo:table-cell>
														<fo:table-cell line-height="12pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
															<fo:block/>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell line-height="12pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
															<fo:block>
																<fo:inline font-size="8px">Signature: </fo:inline>
																<xsl:for-each select="ED_CertificationDebarment:AuthorizedRepresentativeSignature">
																	<fo:inline font-size="8px">
																		<xsl:apply-templates/>
																	</fo:inline>
																</xsl:for-each>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell line-height="12pt" number-columns-spanned="3" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
															<fo:block>
																<fo:inline font-size="8px">Submitted Date:&#160; </fo:inline>
																<xsl:for-each select="ED_CertificationDebarment:SubmittedDate">
																	<fo:inline font-size="8px">
																		<xsl:value-of select="format-number(substring(.,6,2), '00')"/>
																		<xsl:text>-</xsl:text>
																		<xsl:value-of select="format-number(substring(.,9,2), '00')"/>
																		<xsl:text>-</xsl:text>
																		<xsl:value-of select="format-number(substring(.,1,4), '0000')"/>
																	</fo:inline>
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
					<fo:block>
						<xsl:if test="ED_CertificationDebarment:Attachment!=''">
							<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
								<fo:table-column/>
								<fo:table-column/>
								<fo:table-column/>
								<fo:table-body>
									<fo:table-row>
										<fo:table-cell line-height="12pt" number-columns-spanned="3" width="49%" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
											<fo:block>
												<fo:block color="black" space-before.optimum="-8pt">
													<fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="3pt"/>
												</fo:block>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
											<fo:block>
												<fo:inline font-size="8pt">Optional Attachment</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
											<fo:block>
												<fo:inline font-size="8pt">FileName</fo:inline>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
											<fo:block>
												<fo:inline font-size="8pt">MimeType</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<fo:table-row>
										<fo:table-cell>
											<fo:block/>
										</fo:table-cell>
										<fo:table-cell padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white" hyphenate="true" language="en">
											<fo:block>
												<xsl:for-each select="ED_CertificationDebarment:Attachment">
													<xsl:for-each select="att:FileName">
														<fo:inline font-size="8pt">
															<xsl:apply-templates/>
														</fo:inline>
													</xsl:for-each>
												</xsl:for-each>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white" hyphenate="true" language="en">
											<fo:block>
												<xsl:for-each select="ED_CertificationDebarment:Attachment">
													<xsl:for-each select="att:MimeType">
														<fo:inline font-size="8pt">
															<xsl:apply-templates/>
														</fo:inline>
													</xsl:for-each>
												</xsl:for-each>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
						</xsl:if>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>
