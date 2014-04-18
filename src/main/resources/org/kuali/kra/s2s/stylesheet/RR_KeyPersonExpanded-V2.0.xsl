<?xml version="1.0" encoding="UTF-8"?>
<!-- $Revision: 1.4 $ -->
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0"
	xmlns:RR_KeyPersonExpanded_2_0="http://apply.grants.gov/forms/RR_KeyPersonExpanded_2_0-V2.0"
	xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
	xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:header="http://apply.grants.gov/system/Header-V1.0">
	<xsl:variable name="fo:layout-master-set">
		<fo:layout-master-set>
			<fo:simple-page-master master-name="first"
				page-height="11in" page-width="8.5in" margin-left="0.5in"
				margin-right="0.5in">
				<fo:region-body margin-top="0.5in" margin-bottom="0.7in"
					font-family="Helvetica,Times,Courier" font-size="14pt" />
				<fo:region-before region-name="region-before-first"
					extent=".5in" />
				<fo:region-after region-name="region-after-all"
					extent=".3in" />
			</fo:simple-page-master>

			<fo:simple-page-master master-name="rest"
				page-height="11in" page-width="8.5in" margin-left="0.5in"
				margin-right="0.5in">
				<fo:region-body margin-top="0.5in" margin-bottom="0.7in"
					font-family="Helvetica,Times,Courier" font-size="14pt" />
				<fo:region-after region-name="region-after-all"
					extent=".3in" />
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

	</xsl:variable>
	<xsl:template match="RR_KeyPersonExpanded_2_0:RR_KeyPersonExpanded_2_0">
		<fo:root>
			<xsl:copy-of select="$fo:layout-master-set" />
			<fo:page-sequence master-reference="all-pages"
				initial-page-number="1" format="1">
				<fo:static-content flow-name="region-before-first">
					<fo:table width="100%" space-before.optimum="0pt"
						space-after.optimum="0pt" table-layout="fixed">
						<fo:table-column column-width="proportional-column-width(1)" />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en"
									line-height="9pt" padding-start="0pt" padding-end="0pt"
									padding-before="10pt" padding-after="1pt" display-align="before"
									text-align="right" border-style="solid" border-width="0pt"
									border-color="white">
									<fo:block>
										<fo:inline font-size="6px">OMB Number: 4040-0001
										</fo:inline>
									</fo:block>
									<fo:block>
										<fo:inline font-size="6px" color="white">Expiration Date: 06/30/2011
										</fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>
				<xsl:call-template name="footer" />
				<fo:flow flow-name="xsl-region-body" font-family="Helvetica,Times,Courier">
					<!--title -->
					<fo:block text-align="center" font-size="12px"
						font-weight="bold">RESEARCH &amp; RELATED Senior/Key Person Profile
						(Expanded)</fo:block>
					<fo:block>&#160;</fo:block>

					<!--==========PDPI============================================================================ -->
					<xsl:for-each
						select="RR_KeyPersonExpanded_2_0:PDPI/RR_KeyPersonExpanded_2_0:Profile">
						<xsl:call-template name="KPTemplate">
							<xsl:with-param name="heading">
								PROFILE - Project Director/Principal Investigator
							</xsl:with-param>
						</xsl:call-template>
					</xsl:for-each>
					<!--========== End of PDPI======================================================================== -->
					<xsl:for-each
						select="RR_KeyPersonExpanded_2_0:KeyPerson/RR_KeyPersonExpanded_2_0:Profile">
						<xsl:call-template name="KPTemplate">
							<xsl:with-param name="heading">
								PROFILE - Senior/Key Person
							</xsl:with-param>
						</xsl:call-template>
					</xsl:for-each>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	<xsl:template name="footer">
		<fo:static-content flow-name="region-after-all">
			<fo:table width="100%" space-before.optimum="0pt"
				space-after.optimum="0pt" table-layout="fixed">
				<fo:table-column column-width="proportional-column-width(1)" />
				<fo:table-column column-width="proportional-column-width(1)" />
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell hyphenate="true" language="en"
							padding-start="0pt" padding-end="0pt" padding-before="1pt"
							padding-after="1pt" display-align="before" text-align="left"
							border-style="solid" border-width="0pt" border-color="white">
							<fo:block>
								<fo:inline font-size="8px">
									Tracking Number:
									<xsl:value-of select="/*/*/footer:Grants_govTrackingNumber" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" padding-start="0pt" padding-end="0pt"
							padding-before="1pt" padding-after="1pt" display-align="before"
							text-align="right" border-style="solid" border-width="0pt"
							border-color="white">
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
	</xsl:template>
	<!--========================================= KP Template ==================================== -->
	<xsl:template name="KPTemplate">
		<xsl:param name="heading" />
		<fo:block font-size="8pt" page-break-inside="avoid">
			<!--table for Senior/Key person information -->
			<fo:table width="100%" border-style="solid" border-width="1pt"
				border-top-width="1.5pt" border-color="black" border-bottom-width="1.0pt"
				table-layout="fixed">
				<fo:table-column column-width="proportional-column-width(1)" />
				<fo:table-body>
					<!--row1, header -->
					<fo:table-row>
						<fo:table-cell padding-before="2pt" padding-after="2pt">
							<fo:block text-align="center">
								<fo:inline font-weight="bold">
									<xsl:value-of select="$heading" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<!--row2, name titles -->
					<xsl:for-each select="RR_KeyPersonExpanded_2_0:Name">
						<fo:table-row>
							<fo:table-cell line-height="10pt" border-top-style="solid"
								border-width="1pt" border-top-color="black">
								<fo:block text-align="left">
									<fo:leader leader-pattern="space" />
									<fo:table width="100%" table-layout="fixed">
										<fo:table-column column-width="proportional-column-width(10)" />
										<fo:table-column column-width="proportional-column-width(23)" />
										<fo:table-column column-width="proportional-column-width(22)" />
										<fo:table-column column-width="proportional-column-width(35)" />
										<fo:table-column column-width="proportional-column-width(10)" />
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell padding-start="4pt">
													<fo:block font-size="9px">
														<fo:inline>Prefix:&#160;</fo:inline>
														<fo:inline font-family="Georgia" font-size="10px">
															<xsl:value-of select="./globLib:PrefixName" />
														</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell padding-start="4pt">
													<fo:block font-size="9px">
														<fo:inline>First Name*:&#160;</fo:inline>
														<fo:inline font-family="Georgia" font-size="10px">
															<xsl:value-of select="./globLib:FirstName" />
														</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell padding-start="4pt">
													<fo:block font-size="9px">
														<fo:inline>Middle Name&#160;</fo:inline>
														<fo:inline font-family="Georgia" font-size="10px">
															<xsl:value-of select="./globLib:MiddleName" />
														</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell padding-start="4pt">
													<fo:block font-size="9px">
														<fo:inline>Last Name*:&#160;</fo:inline>
														<fo:inline font-family="Georgia" font-size="10px">
															<xsl:value-of select="./globLib:LastName" />
														</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell padding-start="4pt">
													<fo:block font-size="9px">
														<fo:inline>Suffix:&#160;</fo:inline>
														<fo:inline font-family="Georgia" font-size="10px">
															<xsl:value-of select="./globLib:SuffixName" />
														</fo:inline>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
					</xsl:for-each>
					<!--row4 -->
					<fo:table-row>
						<fo:table-cell line-height="10pt">
							<fo:block>
								<fo:leader leader-pattern="space" />
								<fo:table width="100%" table-layout="fixed">
									<fo:table-column column-width="1.6in" />
									<fo:table-column column-width="6.1in" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en"
												padding-start="4pt" padding-before="2pt" padding-after="1pt">
												<fo:block font-size="9px">Position/Title*:</fo:block>
											</fo:table-cell>
											<fo:table-cell hyphenate="true" language="en"
												padding-before="2pt">
												<fo:block font-family="Georgia" font-size="10px">
													<xsl:value-of select="RR_KeyPersonExpanded_2_0:Title" />
												</fo:block>
											</fo:table-cell>
										</fo:table-row>

										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en"
												padding-start="4pt" padding-before="2pt" padding-after="1pt">
												<fo:block font-size="9px">Organization Name*:</fo:block>
											</fo:table-cell>
											<fo:table-cell hyphenate="true" language="en"
												padding-before="2pt">
												<fo:block font-family="Georgia" font-size="10px">
													<xsl:value-of select="RR_KeyPersonExpanded_2_0:OrganizationName" />
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<!--row5 -->
					<fo:table-row>
						<fo:table-cell line-height="10pt">
							<fo:block>
								<fo:table width="100%" table-layout="fixed">
									<fo:table-column column-width="1.6in" />
									<fo:table-column column-width="6.1in" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en"
												padding-start="4pt" padding-before="2pt" padding-after="1pt">
												<fo:block font-size="9px">Department:</fo:block>
											</fo:table-cell>
											<fo:table-cell hyphenate="true" language="en"
												padding-before="2pt">
												<fo:block font-family="Georgia" font-size="10px">
													<xsl:value-of select="RR_KeyPersonExpanded_2_0:DepartmentName" />
												</fo:block>
											</fo:table-cell>
										</fo:table-row>

										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en"
												padding-start="4pt" padding-before="2pt" padding-after="1pt">
												<fo:block font-size="9px">Division:</fo:block>
											</fo:table-cell>
											<fo:table-cell hyphenate="true" language="en"
												padding-before="2pt">
												<fo:block font-family="Georgia" font-size="10px">
													<xsl:value-of select="RR_KeyPersonExpanded_2_0:DivisionName" />
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<!--address for-each loop -->
					<xsl:for-each select="RR_KeyPersonExpanded_2_0:Address">
						<!--row6 -->
						<fo:table-row>
							<fo:table-cell line-height="10pt">
								<fo:block>
									<fo:table width="100%" table-layout="fixed">
										<fo:table-column column-width="1.6in" />
										<fo:table-column column-width="6.1in" />
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell hyphenate="true" language="en"
													padding-start="4pt" padding-before="2pt" padding-after="1pt">
													<fo:block font-size="9px">Street1*:</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en"
													padding-before="2pt">
													<fo:block font-family="Georgia" font-size="10px">
														<xsl:value-of select="globLib:Street1" />
													</fo:block>
												</fo:table-cell>
											</fo:table-row>

											<fo:table-row>
												<fo:table-cell hyphenate="true" language="en"
													padding-start="4pt" padding-before="2pt" padding-after="1pt">
													<fo:block font-size="9px">Street2:</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en"
													padding-before="2pt">
													<fo:block font-family="Georgia" font-size="10px">
														<xsl:value-of select="globLib:Street2" />
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<!--row7 (5 columns) -->
						<fo:table-row>
							<fo:table-cell line-height="10pt" padding-after="3pt">
								<fo:block>
									<fo:table width="100%" table-layout="fixed">
										<fo:table-column column-width="1.6in" />
										<fo:table-column column-width="6.1in" />
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell hyphenate="true" language="en"
													padding-start="4pt" padding-before="2pt" padding-after="1pt">
													<fo:block font-size="9px">City*:</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en"
													padding-before="2pt">
													<fo:block font-family="Georgia" font-size="10px">
														<xsl:value-of select="globLib:City" />
													</fo:block>
												</fo:table-cell>
											</fo:table-row>

											<fo:table-row>
												<fo:table-cell hyphenate="true" language="en"
													padding-start="4pt" padding-before="2pt" padding-after="1pt">
													<fo:block font-size="9px">County:</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en"
													padding-before="2pt">
													<fo:block font-family="Georgia" font-size="10px">
														<xsl:value-of select="globLib:County" />
													</fo:block>
												</fo:table-cell>
											</fo:table-row>

											<fo:table-row>
												<fo:table-cell hyphenate="true" language="en"
													padding-start="4pt" padding-before="2pt" padding-after="1pt">
													<fo:block font-size="9px">State*:</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en"
													padding-before="2pt">
													<fo:block font-family="Georgia" font-size="10px">
														<xsl:value-of select="globLib:State" />
													</fo:block>
												</fo:table-cell>
											</fo:table-row>

											<fo:table-row>
												<fo:table-cell hyphenate="true" language="en"
													padding-start="4pt" padding-before="2pt" padding-after="1pt">
													<fo:block font-size="9px">Province:</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en"
													padding-before="2pt">
													<fo:block font-family="Georgia" font-size="10px">
														<xsl:value-of select="globLib:Province" />
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell line-height="10pt" padding-after="3pt">
								<fo:block>
									<fo:table width="100%" table-layout="fixed">
										<fo:table-column column-width="1.6in" />
										<fo:table-column column-width="6.1in" />
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell hyphenate="true" language="en"
													padding-start="4pt" padding-before="2pt" padding-after="1pt">
													<fo:block font-size="9px">Country*:</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en"
													padding-before="2pt">
													<fo:block font-family="Georgia" font-size="10px">
														<xsl:value-of select="globLib:Country" />
													</fo:block>
												</fo:table-cell>
											</fo:table-row>

											<fo:table-row>
												<fo:table-cell hyphenate="true" language="en"
													padding-start="4pt" padding-before="2pt" padding-after="1pt">
													<fo:block font-size="9px">Zip / Postal Code*:</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en"
													padding-before="2pt">
													<fo:block font-family="Georgia" font-size="10px">
														<xsl:value-of select="globLib:ZipPostalCode" />
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<!--end Address loop -->
					</xsl:for-each>
					<!--row phone, etc. names -->
					<fo:table-row>
						<fo:table-cell line-height="10pt" border-top-style="solid"
							border-top-color="black">
							<fo:block text-align="left">
								<fo:table width="100%" table-layout="fixed">
									<fo:table-column column-width="proportional-column-width(25)" />
									<fo:table-column column-width="proportional-column-width(25)" />
									<fo:table-column column-width="proportional-column-width(50)" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell padding-start="4pt"
												padding-after="4pt" padding-before="4pt">
												<fo:block font-size="9px">
													<fo:inline>Phone Number*:&#160;</fo:inline>
													<fo:inline font-family="Georgia" font-size="10px">
														<xsl:value-of select="RR_KeyPersonExpanded_2_0:Phone" />
													</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell padding-start="4pt"
												padding-after="4pt" padding-before="4pt">
												<fo:block font-size="9px">
													<fo:inline>Fax Number:&#160;</fo:inline>
													<fo:inline font-family="Georgia" font-size="10px">
														<xsl:value-of select="RR_KeyPersonExpanded_2_0:Fax" />
													</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell padding-start="4pt"
												padding-after="4pt" padding-before="4pt">
												<fo:block font-size="9px">
													<fo:inline>E-Mail*:&#160;</fo:inline>
													<fo:inline font-family="Georgia" font-size="10px">
														<xsl:value-of select="RR_KeyPersonExpanded_2_0:Email" />
													</fo:inline>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<!--row phone, etc. values -->
					<!--row, credentials -->
					<fo:table-row>
						<fo:table-cell padding-start="4pt" padding-before="2pt"
							padding-after="1pt" line-height="10pt" border-top-style="solid"
							border-top-color="black" border-top-width="1.0pt">
							<fo:block>
								<fo:inline font-size="9px">Credential, e.g., agency
									login:&#160;</fo:inline>
								<fo:inline font-family="Georgia" font-size="10px">
									<xsl:value-of select="RR_KeyPersonExpanded_2_0:Credential" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<!--row, project role, project category -->
					<fo:table-row>
						<fo:table-cell padding-before="2pt" padding-after="1pt"
							line-height="10pt" border-top-style="solid" border-top-color="black"
							border-top-width="1.0pt">
							<fo:block>
								<fo:table width="100%" table-layout="fixed">
									<fo:table-column column-width="proportional-column-width(45)" />
									<fo:table-column column-width="proportional-column-width(55)" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en"
												padding-start="4pt">
												<fo:block>
													<fo:inline font-size="9px">Project Role*:&#160;
													</fo:inline>
													<fo:inline font-family="Georgia" font-size="10px">
														<xsl:value-of select="RR_KeyPersonExpanded_2_0:ProjectRole" />
													</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell hyphenate="true" language="en"
												padding-start="4pt">
												<fo:block>
													<fo:inline font-size="9px">Other Project Role
														Category:&#160;</fo:inline>
													<fo:inline font-family="Georgia" font-size="10px">
														<xsl:value-of
															select="RR_KeyPersonExpanded_2_0:OtherProjectRoleCategory" />
													</fo:inline>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<!-- Degree type -->
					<fo:table-row>
						<fo:table-cell padding-before="2pt" padding-after="1pt"
							line-height="10pt" border-top-style="solid" border-top-color="black"
							border-top-width="1.0pt">
							<fo:block>
								<fo:table width="100%" table-layout="fixed">
									<fo:table-column column-width="proportional-column-width(45)" />
									<fo:table-column column-width="proportional-column-width(55)" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en"
												padding-start="4pt">
												<fo:block>
													<fo:inline font-size="9px">Degree Type:&#160;
													</fo:inline>
													<fo:inline font-family="Georgia" font-size="10px">
														<xsl:value-of select="RR_KeyPersonExpanded_2_0:DegreeType" />
													</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell hyphenate="true" language="en"
												padding-start="4pt">
												<fo:block>
													<fo:inline font-size="9px">Degree Year:&#160;
													</fo:inline>
													<fo:inline font-family="Georgia" font-size="10px">
														<xsl:value-of select="RR_KeyPersonExpanded_2_0:DegreeYear" />
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
						<fo:table-cell padding-start="4pt" padding-before="2pt"
							padding-after="1pt" line-height="10pt" border-top-style="solid"
							border-top-color="black" border-top-width="1.0pt">
							<fo:block>
								<fo:table width="100%" table-layout="fixed">
									<fo:table-column column-width="proportional-column-width(45)" />
									<fo:table-column column-width="proportional-column-width(28)" />
									<fo:table-column column-width="proportional-column-width(27)" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell padding-after="4pt">
												<fo:block>&#160;</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="left">
												<fo:block font-size="9">File Name</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center">
												<fo:block color="white">Mime Type</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell padding-after="6pt">
												<fo:block font-size="9" font-weight="bold">Attach
													Biographical Sketch*:</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="left" hyphenate="true"
												language="en">
												<fo:block font-family="Georgia" font-size="10">
													<xsl:value-of
														select="RR_KeyPersonExpanded_2_0:BioSketchsAttached/RR_KeyPersonExpanded_2_0:BioSketchAttached/att:FileName" />
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="left">
												<fo:block color="white">
													<xsl:value-of
														select="RR_KeyPersonExpanded_2_0:BioSketchsAttached/RR_KeyPersonExpanded_2_0:BioSketchAttached/att:MimeType" />
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell padding-after="6pt">
												<fo:block font-size="9" font-weight="bold">Attach Current
													&#38; Pending Support:</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="left" hyphenate="true"
												language="en">
												<fo:block font-family="Georgia" font-size="10">
													<xsl:value-of
														select="RR_KeyPersonExpanded_2_0:SupportsAttached/RR_KeyPersonExpanded_2_0:SupportAttached/att:FileName" />
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center">
												<fo:block color="white">
													<xsl:value-of
														select="RR_KeyPersonExpanded_2_0:SupportsAttached/RR_KeyPersonExpanded_2_0:SupportAttached/att:MimeType" />
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
			<!--space after tables -->
			<fo:block>&#160;</fo:block>
		</fo:block>
	</xsl:template>
	<!--========= End Key Person Template ============================================================= -->
</xsl:stylesheet>
