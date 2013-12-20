<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:NSF_SuggestedReviewers="http://apply.grants.gov/forms/NSF_SuggestedReviewers-V1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<!--    -->
	<xsl:param name="l10n.gentext.default.language" select="'en'"/>
	<!--   -->
	<xsl:variable name="fo:layout-master-set">
		<fo:layout-master-set>
			<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.34in" margin-right="0.34in">
				<fo:region-body margin-top="0.5in" margin-bottom="0.5in" font-family="Helvetica,Times,Courier" font-size="8pt"/>
				<fo:region-after extent=".5in"/>
			</fo:simple-page-master>
		</fo:layout-master-set>
	</xsl:variable>
	<xsl:template match="NSF_SuggestedReviewers:NSF_SuggestedReviewers">
		<fo:root>
			<!-- -->
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
								<fo:table-cell line-height="11pt" display-align="after" text-align="right" >
									<fo:block>
										<fo:inline text-align="right" font-size="6px" >OMB Number 3145-0058</fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>
				<!-- -->
				<!-- -->
				<fo:flow flow-name="xsl-region-body">
					<fo:block text-align="center" font-family="Helvetica,Times,Courier" font-size="11pt" font-weight="bold">List of Suggested Reviewers or Reviewers Not to Include (optional)</fo:block>
					<fo:table width="100%">
						<fo:table-column/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell>
									<fo:block>&#160;</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<!-- -->
							<fo:table-row font-size="8pt">
								<fo:table-cell padding-before="1pt" padding-after="1pt" padding-start="1pt">
									<fo:block>Applicants may include a list of suggested reviewers who they believe are especially well qualified to review the proposal.  Applicants also may designate persons they would prefer not review the proposal, indicating why.  These suggestions are optional.  Grant Proposal Guide Appendix B, Potentially Disqualifying Conflicts of Interest (http://www.nsf.gov/pubs/2004/nsf042/appb.htm), contains information on conflicts of interest that may be useful in preparation of this list.</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row font-size="8pt">
								<fo:table-cell>
                                                                    <fo:block>
									<fo:table width="100%">
										<fo:table-column column-width="1.25in"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell padding-before="1pt" padding-after="1pt" padding-start="1pt" font-weight="bold">
													<fo:block>Suggested Reviewers:</fo:block>
												</fo:table-cell>
												<fo:table-cell padding-before="1pt" padding-after="1pt" padding-start="1pt" >
													<fo:block>Provide the First, Middle, and Last Name of suggested reviewers that you believe are especially well qualified to review this </fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell number-columns-spanned="2">
													<fo:block>proposal.</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								    </fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row font-size="8pt">
								<fo:table-cell font-weight="bold" padding-before="1pt" padding-after="1pt" padding-start="1pt" >
									<fo:block>Enter text in the box below</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row line-height="4pt">
								<fo:table-cell>
									<fo:block>&#160;</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row font-size="8pt">
								<fo:table-cell border-style="solid" border-color="grey" hyphenate="true"> <!--  border-style="solid" border-color="grey" -->
									<fo:block>
										<xsl:value-of select="NSF_SuggestedReviewers:SuggestedReviewers"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
														<fo:table-row line-height="8pt">
								<fo:table-cell>
									<fo:block>&#160;</fo:block>
								</fo:table-cell>
							</fo:table-row>

							<fo:table-row font-size="8pt">
								<fo:table-cell>
                                                                    <fo:block>
									<fo:table width="100%">
										<fo:table-column column-width="1.48in"/>
										<fo:table-column/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell font-weight="bold" padding-before="1pt" padding-after="1pt" padding-start="1pt" >
													<fo:block>Reviewers Not to Include:</fo:block>
												</fo:table-cell>
												<fo:table-cell padding-before="1pt" padding-after="1pt" padding-start="1pt" >
													<fo:block>Designate persons you would prefer not review this proposal and indicate why.</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								    </fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row font-size="8pt">
								<fo:table-cell font-weight="bold" padding-before="1pt" padding-after="1pt" padding-start="1pt" >
									<fo:block>Enter text in the box below</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row line-height="4pt">
								<fo:table-cell>
									<fo:block>&#160;</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row font-size="8pt">
								<fo:table-cell border-style="solid" border-color="grey" hyphenate="true"> <!--  border-style="solid" border-color="grey" -->
									<fo:block>
										<xsl:value-of select="NSF_SuggestedReviewers:ReviewersNotToInclude"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>
