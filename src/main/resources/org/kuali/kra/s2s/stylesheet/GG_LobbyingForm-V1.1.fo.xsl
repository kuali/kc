<?xml version="1.0" encoding="UTF-8"?>
<!-- $Revision:   1.0  $ -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:GG_LobbyingForm="http://apply.grants.gov/forms/GG_LobbyingForm-V1.1" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:xs="http://www.w3.org/2001/XMLSchema">
	<xsl:variable name="fo:layout-master-set">
		<fo:layout-master-set>
			<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.6in" margin-right="0.6in">
				<fo:region-body margin-top="0.6in" margin-bottom="0.6in"/>
				<fo:region-after extent=".5in"/>
			</fo:simple-page-master>
		</fo:layout-master-set>
	</xsl:variable>
	<xsl:template match="GG_LobbyingForm:LobbyingForm">
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
					<!--==HR==-->
					<fo:block>
						<fo:leader leader-length="100%" leader-pattern="rule"/>
					</fo:block>
					<fo:block>
						<!--==HR==-->
						<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt" wrap-option="wrap" line-height="10pt">
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row height="20pt">
									<fo:table-cell>
										<fo:block/>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="center" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="0pt" border-color="white">
										<fo:block/>
										<fo:block>
											<fo:inline font-size="12pt" font-weight="bold">CERTIFICATION REGARDING LOBBYING</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row height="20pt">
									<fo:table-cell>
										<fo:block/>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="0pt" border-color="white">
										<fo:block/>
										<fo:block/>
										<fo:block>
											<fo:inline font-size="10pt">Certification for Contracts, Grants, Loans, and Cooperative Agreements</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row height="10pt">
									<fo:table-cell>
										<fo:block/>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="0pt" border-color="white">
										<fo:block/>
										<fo:block/>
										<fo:block>
											<fo:inline font-size="10pt">The undersigned certifies, to the best of his or her knowledge and belief, that:</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row height="10pt">
									<fo:table-cell>
										<fo:block/>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="0pt" border-color="white">
										<fo:block/>
										<fo:block/>
										<fo:block>
											<fo:inline font-size="10pt">(1) No Federal appropriated funds have been paid or will be paid, by or on behalf of the undersigned, to any person for influencing or attempting to influence an officer or employee of an agency, a Member of Congress, an officer or employee of Congress, or an employee of a Member of Congress in connection with the awarding of any Federal contract, the making of any Federal grant, the making of any Federal loan, the entering into of any cooperative agreement, and the extension, continuation, renewal, amendment, or modification of any Federal contract, grant, loan, or cooperative agreement.
</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row height="10pt">
									<fo:table-cell>
										<fo:block/>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="0pt" border-color="white">
										<fo:block/>
										<fo:block/>
										<fo:block>
											<fo:inline font-size="10pt">(2) If any funds other than Federal appropriated funds have been paid or will be paid to any person for influencing or attempting to influence an officer or employee of any agency, a Member of Congress, an officer or employee of Congress, or an employee of a Member of Congress in connection with this Federal contract, grant, loan, or cooperative agreement, the undersigned shall complete and submit Standard Form-LLL, ''Disclosure of Lobbying Activities,'' in accordance with its instructions. </fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row height="10pt">
									<fo:table-cell>
										<fo:block/>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="0pt" border-color="white">
										<fo:block/>
										<fo:block/>
										<fo:block>
											<fo:inline font-size="10pt">(3) The undersigned shall require that the language of this certification be included in the award documents for all subawards at all tiers (including subcontracts, subgrants, and contracts under grants, loans, and cooperative agreements) and that all subrecipients shall certify and disclose accordingly. This certification is a material representation of fact upon which reliance was placed when this transaction was made or entered into. Submission of this certification is a prerequisite for making or entering into this transaction imposed by section 1352, title 31, U.S. Code. Any person who fails to file the required certification shall be subject to a civil penalty of not less than $10,000 and not more than $100,000 for each such failure. </fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row height="10pt">
									<fo:table-cell>
										<fo:block/>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="0pt" border-color="white">
										<fo:block/>
										<fo:block/>
										<fo:block>
											<fo:inline font-size="10pt">Statement for Loan Guarantees and Loan Insurance</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row height="10pt">
									<fo:table-cell>
										<fo:block/>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="0pt" border-color="white">
										<fo:block/>
										<fo:block/>
										<fo:block>
											<fo:inline font-size="10pt">The undersigned states, to the best of his or her knowledge and belief, that:  </fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row height="10pt">
									<fo:table-cell>
										<fo:block/>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="0pt" border-color="white">
										<fo:block/>
										<fo:block/>
										<fo:block>
											<fo:inline font-size="10pt">If any funds have been paid or will be paid to any person for influencing or attempting to influence an officer or employee of any agency, a Member of Congress, an officer or employee of Congress, or an employee of a Member of Congress in connection with this commitment providing for the United States to insure or guarantee a loan, the undersigned shall complete and submit Standard Form-LLL, ''Disclosure of Lobbying Activities,'' in accordance with its instructions. Submission of this statement is a prerequisite for making or entering into this transaction imposed by section 1352, title 31, U.S. Code. Any person who fails to file the required statement shall be subject to a civil penalty of not less than $10,000 and not more than $100,000 for each such failure.</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row height="25pt">
									<fo:table-cell>
										<fo:block/>
									</fo:table-cell>
								</fo:table-row>

							</fo:table-body>
						</fo:table>
						<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt" wrap-option="wrap" border-width="10pt">
							<fo:table-column/>
							<fo:table-column/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell hyphenate="true" language="EN" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-after="3pt" padding-before="3pt" border-style="solid" number-columns-spanned="2">
										<fo:block>
											<fo:inline font-size="10pt">* APPLICANT'S ORGANIZATION</fo:inline>
										</fo:block>
										<fo:block>
											<fo:inline font-size="10pt">
												<xsl:value-of select="GG_LobbyingForm:ApplicantName"/>
											</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell hyphenate="true" language="EN" border-width="1pt" border-color="black" padding-start="3pt" padding-end="3pt" padding-after="3pt" padding-before="3pt" border-style="solid" number-columns-spanned="2">
										<fo:block>
											<fo:inline font-size="10pt">* PRINTED NAME AND TITLE OF AUTHORIZED REPRESENTATIVE</fo:inline>
										</fo:block>
										<fo:block language="en" hyphenate="true" keep-together="always" font-size="10pt">
											<fo:inline font-size="10pt">
												Prefix:&#160;&#160;&#160;<xsl:value-of select="GG_LobbyingForm:AuthorizedRepresentativeName/globLib:PrefixName"/>&#160;&#160;&#160;&#160;&#160;&#160; * First Name: &#160;&#160;&#160;<xsl:value-of select="GG_LobbyingForm:AuthorizedRepresentativeName/globLib:FirstName"/>&#160;&#160;&#160;&#160;&#160;&#160; Middle Name: &#160;&#160;&#160;<xsl:value-of select="GG_LobbyingForm:AuthorizedRepresentativeName/globLib:MiddleName"/>
											</fo:inline>
										</fo:block>
										<fo:block language="en" hyphenate="true" keep-together="always" font-size="10pt">
											<fo:inline font-size="10pt">* Last Name: &#160;&#160;&#160;<xsl:value-of select="GG_LobbyingForm:AuthorizedRepresentativeName/globLib:LastName"/>&#160;&#160;&#160;&#160;&#160;&#160; Suffix: &#160;&#160;&#160;<xsl:value-of select="GG_LobbyingForm:AuthorizedRepresentativeName/globLib:SuffixName"/>&#160;&#160;&#160;&#160;&#160;&#160;* Title: &#160;&#160;&#160;<xsl:value-of select="GG_LobbyingForm:AuthorizedRepresentativeTitle"/>
											</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell border-width="1pt" border-color="black" border-style="solid" padding-start="3pt" padding-end="3pt" padding-after="3pt" padding-before="3pt" number-columns-spanned="2">
										<fo:block>
											<fo:inline font-size="10pt">* SIGNATURE: </fo:inline>
											<fo:inline font-size="10pt">
												<xsl:value-of select="GG_LobbyingForm:AuthorizedRepresentativeSignature"/>
											</fo:inline>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;

											<fo:inline font-size="10pt">* DATE: </fo:inline>
											<xsl:for-each select="GG_LobbyingForm:SubmittedDate">
												<fo:inline font-size="10pt">
													<xsl:call-template name="formatDate">
														<xsl:with-param name="value" select="."/>
													</xsl:call-template>
												</fo:inline>
											</xsl:for-each>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row height="20pt"> <!-- inserted row-->
									<fo:table-cell>
										<fo:block/>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell number-columns-spanned="2" text-align="right" height="20pt">
										<!--
										<fo:block>
											<fo:inline font-size="8pt">OMB No. 0348-0046</fo:inline>
										</fo:block>
										-->
										<!--==HR==-->
										<fo:block>
											<fo:leader leader-length="100%" leader-pattern="rule"/>
										</fo:block>
										<!--==HR==-->
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
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
			<xsl:text>/</xsl:text>
			<xsl:value-of select="format-number(substring($value,9,2), '00')"/>
			<xsl:text>/</xsl:text>
			<xsl:value-of select="format-number(substring($value,1,4), '0000')"/>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>
