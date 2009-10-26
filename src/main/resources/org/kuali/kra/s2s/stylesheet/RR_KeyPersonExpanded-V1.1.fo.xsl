<?xml version="1.0" encoding="UTF-8"?>
<!-- $Revision:   1.4  $ -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:RR_KeyPersonExpanded="http://apply.grants.gov/forms/RR_KeyPersonExpanded-V1.1" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:header="http://devapply.row.com/system/Header-V1.0">
	<xsl:variable name="fo:layout-master-set">
		<fo:layout-master-set>
			<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.5in" margin-right="0.5in">
				<fo:region-body margin-top="0.5in" margin-bottom="0.7in" font-family="Helvetica,Times,Courier" font-size="14pt"/>
				<fo:region-after extent=".5in"/>
			</fo:simple-page-master>
		</fo:layout-master-set>
	</xsl:variable>
	<xsl:template match="RR_KeyPersonExpanded:RR_KeyPersonExpanded">
		<fo:root>
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
				<fo:flow flow-name="xsl-region-body" font-family="Helvetica,Times,Courier">
					<!--title-->
					<fo:block text-align="center" font-size="15pt" font-weight="200">RESEARCH &amp; RELATED Senior/Key Person Profile (Expanded)
               </fo:block>
					<fo:block>&#160;</fo:block>
					<!--==========PDPI============================================================================-->
					<fo:block font-size="8pt">
						<!--table for PDPI information-->
						<fo:table width="100%" border-style="solid" border-width="1pt" border-top-width="1.5pt" border-color="black" border-bottom-width="1.5pt">
							<fo:table-column/>
							<fo:table-body>
								<!--row1, header-->
								<fo:table-row>
									<fo:table-cell padding-before="3pt" padding-after="3pt">
										<fo:block text-align="center">
											<fo:inline font-weight="bold">PROFILE - Project Director/Principal Investigator</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<!--row2, name titles-->
								<fo:table-row>
									<fo:table-cell line-height="10pt" border-top-style="solid" border-width="1.5pt" border-top-color="black">
										<fo:block text-align="center">
											<fo:leader leader-pattern="space"/>
											<fo:table width="100%">
												<fo:table-column column-width="proportional-column-width(10)"/>
												<fo:table-column column-width="proportional-column-width(25)"/>
												<fo:table-column column-width="proportional-column-width(20)"/>
												<fo:table-column column-width="proportional-column-width(35)"/>
												<fo:table-column column-width="proportional-column-width(10)"/>
												<fo:table-body>
													<fo:table-row>
														<fo:table-cell>
															<fo:block>Prefix</fo:block>
														</fo:table-cell>
														<fo:table-cell>
															<fo:block>* First Name</fo:block>
														</fo:table-cell>
														<fo:table-cell>
															<fo:block>Middle Name</fo:block>
														</fo:table-cell>
														<fo:table-cell>
															<fo:block>* Last Name</fo:block>
														</fo:table-cell>
														<fo:table-cell>
															<fo:block>Suffix</fo:block>
														</fo:table-cell>
													</fo:table-row>
												</fo:table-body>
											</fo:table>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<!--for-each loops to PDPI-->
								<!--row3, name values-->
								<xsl:for-each select="RR_KeyPersonExpanded:PDPI/RR_KeyPersonExpanded:Profile">
									<xsl:for-each select="RR_KeyPersonExpanded:Name">
										<fo:table-row>
											<fo:table-cell line-height="10pt" padding-before="3pt">
												<fo:block text-align="center">
													<fo:table width="100%">
														<fo:table-column column-width="proportional-column-width(10)"/>
														<fo:table-column column-width="proportional-column-width(25)"/>
														<fo:table-column column-width="proportional-column-width(20)"/>
														<fo:table-column column-width="proportional-column-width(35)"/>
														<fo:table-column column-width="proportional-column-width(10)"/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell hyphenate="true" language="en">
																	<fo:block>
																		<xsl:value-of select="./globLib:PrefixName"/>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell hyphenate="true" language="en">
																	<fo:block>
																		<xsl:value-of select="./globLib:FirstName"/>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell hyphenate="true" language="en">
																	<fo:block>
																		<xsl:value-of select="./globLib:MiddleName"/>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell hyphenate="true" language="en">
																	<fo:block>
																		<xsl:value-of select="./globLib:LastName"/>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell hyphenate="true" language="en">
																	<fo:block>
																		<xsl:value-of select="./globLib:SuffixName"/>
																	</fo:block>
																</fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</xsl:for-each>
									<!--row4-->
									<fo:table-row>
										<fo:table-cell line-height="10pt">
											<fo:block>
												<fo:leader leader-pattern="space"/>
												<fo:table width="100%">
													<fo:table-column column-width="proportional-column-width(50)"/>
													<fo:table-column column-width="proportional-column-width(50)"/>
													<fo:table-body>
														<fo:table-row>
															<fo:table-cell hyphenate="true" language="en" padding-start="4pt">
																<fo:block>Position/Title:&#160;<xsl:value-of select="RR_KeyPersonExpanded:Title"/>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell hyphenate="true" language="en">
																<fo:block>Department:&#160;<xsl:value-of select="RR_KeyPersonExpanded:DepartmentName"/>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<!--row5-->
									<fo:table-row>
										<fo:table-cell line-height="10pt">
											<fo:block>
												<fo:leader leader-pattern="space"/>
												<fo:table width="100%">
													<fo:table-column column-width="proportional-column-width(50)"/>
													<fo:table-column column-width="proportional-column-width(50)"/>
													<fo:table-body>
														<fo:table-row>
															<fo:table-cell hyphenate="true" language="en" padding-start="4pt">
																<fo:block>Organization Name:&#160;<xsl:value-of select="RR_KeyPersonExpanded:OrganizationName"/>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell hyphenate="true" language="en">
																<fo:block>Division:&#160;<xsl:value-of select="RR_KeyPersonExpanded:DivisionName"/>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<!--address for-each loop-->
									<xsl:for-each select="RR_KeyPersonExpanded:Address">
										<!--row6-->
										<fo:table-row>
											<fo:table-cell line-height="10pt">
												<fo:block>
													<fo:leader leader-pattern="space"/>
													<fo:table width="100%">
														<fo:table-column column-width="proportional-column-width(50)"/>
														<fo:table-column column-width="proportional-column-width(50)"/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell hyphenate="true" language="en" padding-start="4pt">
																	<fo:block>* Street1:&#160;<xsl:value-of select="globLib:Street1"/>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell hyphenate="true" language="en">
																	<fo:block>Street2:&#160;<xsl:value-of select="globLib:Street2"/>
																	</fo:block>
																</fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<!--row7 (5 columns)-->
										<fo:table-row>
											<fo:table-cell line-height="10pt" padding-after="3pt">
												<fo:block>
													<fo:leader leader-pattern="space"/>
													<fo:table width="100%">
														<fo:table-column column-width="proportional-column-width(28)"/>
														<fo:table-column column-width="proportional-column-width(27)"/>
														<fo:table-column column-width="proportional-column-width(15)"/>
														<fo:table-column column-width="proportional-column-width(15)"/>
														<fo:table-column column-width="proportional-column-width(15)"/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell hyphenate="true" language="en" padding-start="4pt">
																	<fo:block>* City:&#160;<xsl:value-of select="globLib:City"/>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell hyphenate="true" language="en">
																	<fo:block>County:&#160;<xsl:value-of select="globLib:County"/>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell hyphenate="true" language="en">
																	<fo:block>* State:&#160;<xsl:value-of select="globLib:State"/>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell hyphenate="true" language="en">
																	<fo:block>Province:&#160;<xsl:value-of select="globLib:Province"/>
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
													<fo:leader leader-pattern="space"/>
													<fo:table width="100%">
														<fo:table-column column-width="proportional-column-width(28)"/>
														<fo:table-column column-width="proportional-column-width(27)"/>
														<fo:table-column column-width="proportional-column-width(15)"/>
														<fo:table-column column-width="proportional-column-width(15)"/>
														<fo:table-column column-width="proportional-column-width(15)"/>
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell hyphenate="true" language="en" padding-start="4pt">
																	<fo:block>* Country:&#160;<xsl:value-of select="globLib:Country"/>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell hyphenate="true" language="en">
																	<fo:block>* Zip / Postal Code:&#160;<xsl:value-of select="globLib:ZipPostalCode"/>
																	</fo:block>
																</fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>										
										<!--end Address loop-->
									</xsl:for-each>
									<!--row phone, etc. names-->
									<fo:table-row>
										<fo:table-cell line-height="10pt" border-top-style="solid" border-top-color="black">
											<fo:block text-align="center">
												<fo:leader leader-pattern="space"/>
												<fo:table width="100%">
													<fo:table-column column-width="proportional-column-width(34)"/>
													<fo:table-column column-width="proportional-column-width(32)"/>
													<fo:table-column column-width="proportional-column-width(32)"/>
													<fo:table-body>
														<fo:table-row>
															<fo:table-cell>
																<fo:block>*Phone Number</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block>Fax Number</fo:block>
															</fo:table-cell>
															<fo:table-cell>
																<fo:block>* E-Mail</fo:block>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<!--row phone, etc. values-->
									<fo:table-row>
										<fo:table-cell line-height="10pt" padding-before="3pt" padding-after="5pt">
											<fo:block text-align="center">
												<fo:table width="100%">
													<fo:table-column column-width="proportional-column-width(34)"/>
													<fo:table-column column-width="proportional-column-width(32)"/>
													<fo:table-column column-width="proportional-column-width(32)"/>
													<fo:table-body>
														<fo:table-row>
															<fo:table-cell hyphenate="true" language="en">
																<fo:block>
																	<xsl:value-of select="RR_KeyPersonExpanded:Phone"/>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell hyphenate="true" language="en">
																<fo:block>
																	<xsl:value-of select="RR_KeyPersonExpanded:Fax"/>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell hyphenate="true" language="en">
																<fo:block>
																	<xsl:value-of select="RR_KeyPersonExpanded:Email"/>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<!--row, credentials-->
									<fo:table-row>
										<fo:table-cell padding-start="4pt" padding-before="3pt" padding-after="3pt" line-height="8pt" border-top-style="solid" border-top-color="black" border-top-width="1.5pt">
											<fo:block>Credential, e.g., agency login:&#160;<xsl:value-of select="RR_KeyPersonExpanded:Credential"/>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<!--row, project role, project category-->
									<fo:table-row>
										<fo:table-cell padding-start="4pt" padding-before="3pt" padding-after="3pt" line-height="8pt" border-top-style="solid" border-top-color="black" border-top-width="1.5pt">
											<fo:block font-weight="bold">
												<fo:table width="100%">
													<fo:table-column column-width="proportional-column-width(45)"/>
													<fo:table-column column-width="proportional-column-width(55)"/>
													<fo:table-body>
														<fo:table-row>
															<fo:table-cell hyphenate="true" language="en" padding-start="4pt">
																<fo:block>* Project Role:&#160;<fo:inline font-weight="100">
																		<xsl:value-of select="RR_KeyPersonExpanded:ProjectRole"/>
																	</fo:inline>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell hyphenate="true" language="en">
																<fo:block>Other Project Role Category:&#160;<fo:inline font-weight="100">
																		<xsl:value-of select="RR_KeyPersonExpanded:OtherProjectRoleCategory"/>
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
										<fo:table-cell padding-start="4pt" padding-before="3pt" padding-after="3pt" line-height="8pt" border-top-style="solid" border-top-color="black" border-top-width="1.5pt">
											<fo:block>
												<fo:table width="100%">
													<fo:table-column column-width="proportional-column-width(45)"/>
													<fo:table-column column-width="proportional-column-width(28)"/>
													<fo:table-column column-width="proportional-column-width(27)"/>
													<fo:table-body>
														<fo:table-row>
															<fo:table-cell padding-after="4pt">
																<fo:block>&#160;</fo:block>
															</fo:table-cell>
															<fo:table-cell text-align="center">
																<fo:block>File Name</fo:block>
															</fo:table-cell>
															<fo:table-cell text-align="center">
																<fo:block>Mime Type</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell font-weight="bold" padding-after="6pt">
																<fo:block>*Attach Biographical Sketch</fo:block>
															</fo:table-cell>
															<fo:table-cell text-align="center" hyphenate="true" language="en">
																<fo:block>
																	<xsl:value-of select="RR_KeyPersonExpanded:BioSketchsAttached/RR_KeyPersonExpanded:BioSketchAttached/att:FileName"/>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell text-align="center">
																<fo:block>
																	<xsl:value-of select="RR_KeyPersonExpanded:BioSketchsAttached/RR_KeyPersonExpanded:BioSketchAttached/att:MimeType"/>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
														<fo:table-row>
															<fo:table-cell font-weight="bold" padding-after="6pt">
																<fo:block>Attach Current &#38; Pending Support</fo:block>
															</fo:table-cell>
															<fo:table-cell text-align="center" hyphenate="true" language="en">
																<fo:block>
																	<xsl:value-of select="RR_KeyPersonExpanded:SupportsAttached/RR_KeyPersonExpanded:SupportAttached/att:FileName"/>
																</fo:block>
															</fo:table-cell>
															<fo:table-cell text-align="center">
																<fo:block>
																	<xsl:value-of select="RR_KeyPersonExpanded:SupportsAttached/RR_KeyPersonExpanded:SupportAttached/att:MimeType"/>
																</fo:block>
															</fo:table-cell>
														</fo:table-row>
													</fo:table-body>
												</fo:table>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									<!-- end PDPI loop-->
								</xsl:for-each>
							</fo:table-body>
						</fo:table>
						<!--space after tables-->
						<fo:block>&#160;</fo:block>
						<fo:block>&#160;</fo:block>
					</fo:block>
					<!--========== End of PDPI========================================================================-->
					<xsl:for-each select="RR_KeyPersonExpanded:KeyPerson/RR_KeyPersonExpanded:Profile">
						<xsl:call-template name="KPTemplate"/>
					</xsl:for-each>
				</fo:flow>
			</fo:page-sequence>
			<fo:page-sequence master-reference="default-page" format="1">
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
				<fo:flow flow-name="xsl-region-body">
					<!--Data components-->
					<!--Block below is for the field named FilenameAtt01 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="138.78787878787878px" hyphenate="true" language="en" keep-together="always" top="350.90909090909093px" height="13.333333333333334px" width="300px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:KeyPersonAttachmentFileName1) or //RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:KeyPersonAttachmentFileName1 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:KeyPersonAttachmentFileName1"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named FilenameAtt02 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="138.78787878787878px" hyphenate="true" language="en" keep-together="always" top="366.06060606060606px" height="13.333333333333334px" width="300px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:KeyPersonAttachmentFileName2) or //RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:KeyPersonAttachmentFileName2 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:KeyPersonAttachmentFileName2"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named FilenameAtt03 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="138.78787878787878px" hyphenate="true" language="en" keep-together="always" top="381.21212121212125px" height="13.333333333333334px" width="300px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:KeyPersonAttachmentFileName3) or //RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:KeyPersonAttachmentFileName3 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:KeyPersonAttachmentFileName3"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named FilenameAtt04 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="138.78787878787878px" hyphenate="true" language="en" keep-together="always" top="396.3636363636364px" height="13.333333333333334px" width="300px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:KeyPersonAttachmentFileName4) or //RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:KeyPersonAttachmentFileName4 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:KeyPersonAttachmentFileName4"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named single_attachment0 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="138.66666666666669px" hyphenate="true" language="en" keep-together="always" top="441.6666666666667px" height="13.333333333333334px" width="300px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:AdditionalProfilesAttached/RR_KeyPersonExpanded:AdditionalProfileAttached/att:FileName) or //RR_KeyPersonExpanded:AdditionalProfilesAttached/RR_KeyPersonExpanded:AdditionalProfileAttached/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:AdditionalProfilesAttached/RR_KeyPersonExpanded:AdditionalProfileAttached/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="138.66666666666669px" hyphenate="true" language="en" keep-together="always" top="471.6666666666667px" height="13.333333333333334px" width="300px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:AdditionalProfilesAttached/RR_KeyPersonExpanded:AdditionalProfileAttached/att:MimeType) or //RR_KeyPersonExpanded:AdditionalProfilesAttached/RR_KeyPersonExpanded:AdditionalProfileAttached/att:MimeType = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:AdditionalProfilesAttached/RR_KeyPersonExpanded:AdditionalProfileAttached/att:MimeType"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named single_attachment1 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="138.66666666666669px" hyphenate="true" language="en" keep-together="always" top="521.03030303030306px" height="13.333333333333334px" width="300px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:BioSketchsAttached/RR_KeyPersonExpanded:BioSketchAttached/att:FileName) or //RR_KeyPersonExpanded:BioSketchsAttached/RR_KeyPersonExpanded:BioSketchAttached/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:BioSketchsAttached/RR_KeyPersonExpanded:BioSketchAttached/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="138.66666666666669px" hyphenate="true" language="en" keep-together="always" top="550.03030303030306px" height="13.333333333333334px" width="300px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:BioSketchsAttached/RR_KeyPersonExpanded:BioSketchAttached/att:MimeType) or //RR_KeyPersonExpanded:BioSketchsAttached/RR_KeyPersonExpanded:BioSketchAttached/att:MimeType = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//RR_KeyPersonExpanded:RR_KeyPersonExpanded/RR_KeyPersonExpanded:BioSketchsAttached/RR_KeyPersonExpanded:BioSketchAttached/att:MimeType"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named single_attachment2 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="138.66666666666669px" hyphenate="true" language="en" keep-together="always" top="601.39393939393943px" height="13.333333333333334px" width="300px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//RR_KeyPersonExpanded:SupportsAttached/RR_KeyPersonExpanded:SupportAttached/att:FileName) or //RR_KeyPersonExpanded:SupportsAttached/RR_KeyPersonExpanded:SupportAttached/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//RR_KeyPersonExpanded:SupportsAttached/RR_KeyPersonExpanded:SupportAttached/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="138.66666666666669px" hyphenate="true" language="en" keep-together="always" top="631.39393939393943px" height="13.333333333333334px" width="300px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//RR_KeyPersonExpanded:SupportsAttached/RR_KeyPersonExpanded:SupportAttached/att:MimeType) or //RR_KeyPersonExpanded:SupportsAttached/RR_KeyPersonExpanded:SupportAttached/att:MimeType = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//RR_KeyPersonExpanded:SupportsAttached/RR_KeyPersonExpanded:SupportAttached/att:MimeType"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Write labels-->
					<!--Block below is for the label named -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="138.66666666666669px" hyphenate="true" language="en" keep-together="always" top="426.6666666666667px" height="13.333333333333334px" width="185.63636363636365px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">Filename</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="138.66666666666669px" hyphenate="true" language="en" keep-together="always" top="456.6666666666667px" height="13.333333333333334px" width="185.63636363636365px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">MimeType</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL30-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="441.6666666666667px" height="13.333333333333334px" width="185.63636363636365px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">ADDITIONAL SENIOR/KEY</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="451.6666666666667px" height="13.333333333333334px" width="185.63636363636365px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">PERSON PROFILE(S)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="138.66666666666669px" hyphenate="true" language="en" keep-together="always" top="506.6666666666667px" height="13.333333333333334px" width="185.63636363636365px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">Filename</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="138.66666666666669px" hyphenate="true" language="en" keep-together="always" top="536.6666666666667px" height="13.333333333333334px" width="185.63636363636365px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">MimeType</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL31-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="521.03030303030306px" height="13.333333333333334px" width="220px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">Additional Biographical</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="531.03030303030306px" height="13.333333333333334px" width="220px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold"> Sketch(es) (Senior/Key Person)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="138.66666666666669px" hyphenate="true" language="en" keep-together="always" top="586.6666666666667px" height="13.333333333333334px" width="185.63636363636365px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">Filename</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="138.66666666666669px" hyphenate="true" language="en" keep-together="always" top="616.6666666666667px" height="13.333333333333334px" width="185.63636363636365px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">MimeType</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL32-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="601.39393939393943px" height="13.333333333333334px" width="212.72727272727275px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">Additional Current and</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="611.39393939393943px" height="13.333333333333334px" width="212.72727272727275px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">Pending Support(s)</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LabelAtt01-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.212121212121215px" hyphenate="true" language="en" keep-together="always" top="350.90909090909093px" height="13.333333333333334px" width="115.15151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">1) Please attach Attachment 1</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LabelAtt02-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.212121212121215px" hyphenate="true" language="en" keep-together="always" top="366.06060606060606px" height="13.333333333333334px" width="115.15151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">2) Please attach Attachment 2</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LabelAtt03-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.212121212121215px" hyphenate="true" language="en" keep-together="always" top="381.21212121212125px" height="13.333333333333334px" width="115.15151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">3) Please attach Attachment 3</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LabelAtt04-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.212121212121215px" hyphenate="true" language="en" keep-together="always" top="396.3636363636364px" height="13.333333333333334px" width="115.15151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">4) Please attach Attachment 4</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL4-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="22.424242424242426px" hyphenate="true" language="en" keep-together="always" top="69.6969696969697px" height="24.242424242424242px" width="507.87878787878793px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">When submitting senior/key persons in excess of 8 individuals, please attach additional senior/key person forms here.  Each additional form attached here, will provide you with the ability to identify another 8 individuals, up to a maximum of 4 attachments (32 people). </fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL5-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="22.424242424242426px" hyphenate="true" language="en" keep-together="always" top="96.96969696969697px" height="24.242424242424242px" width="507.87878787878793px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">The means to obtain a supplementary  form is provided here on this form, by the button below.  In order to extract, fill, and attach each additional form, simply follow these steps: </fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL20-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="22.424242424242426px" hyphenate="true" language="en" keep-together="always" top="118.7878787878788px" height="25.454545454545457px" width="14.545454545454547px">
						<fo:block background-color="transparent" color="#000000" text-align="start" font-size="16pt" font-style="normal" font-family="Helvetica" font-weight="bold">&#x2022;</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL13-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="22.424242424242426px" hyphenate="true" language="en" keep-together="always" top="134.54545454545456px" height="25.454545454545457px" width="14.545454545454547px">
						<fo:block background-color="transparent" color="#000000" text-align="start" font-size="16pt" font-style="normal" font-family="Helvetica" font-weight="bold">&#x2022;</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL14-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="22.424242424242426px" hyphenate="true" language="en" keep-together="always" top="172.12121212121212px" height="25.454545454545457px" width="14.545454545454547px">
						<fo:block background-color="transparent" color="#000000" text-align="start" font-size="16pt" font-style="normal" font-family="Helvetica" font-weight="bold">&#x2022;</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL15-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="22.424242424242426px" hyphenate="true" language="en" keep-together="always" top="189.0909090909091px" height="25.454545454545457px" width="14.545454545454547px">
						<fo:block background-color="transparent" color="#000000" text-align="start" font-size="16pt" font-style="normal" font-family="Helvetica" font-weight="bold">&#x2022;</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL18-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="22.424242424242426px" hyphenate="true" language="en" keep-together="always" top="215.75757575757578px" height="25.454545454545457px" width="14.545454545454547px">
						<fo:block background-color="transparent" color="#000000" text-align="start" font-size="16pt" font-style="normal" font-family="Helvetica" font-weight="bold">&#x2022;</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL19-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="22.424242424242426px" hyphenate="true" language="en" keep-together="always" top="231.51515151515153px" height="25.454545454545457px" width="14.545454545454547px">
						<fo:block background-color="transparent" color="#000000" text-align="start" font-size="16pt" font-style="normal" font-family="Helvetica" font-weight="bold">&#x2022;</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL21-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="22.424242424242426px" hyphenate="true" language="en" keep-together="always" top="246.66666666666669px" height="25.454545454545457px" width="14.545454545454547px">
						<fo:block background-color="transparent" color="#000000" text-align="start" font-size="16pt" font-style="normal" font-family="Helvetica" font-weight="bold">&#x2022;</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="22.424242424242426px" hyphenate="true" language="en" keep-together="always" top="311.51515151515156px" height="13.333333333333334px" width="45.45454545454546px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">Important:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL6-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="35.75757575757576px" hyphenate="true" language="en" keep-together="always" top="124.24242424242425px" height="13.333333333333334px" width="494.54545454545456px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Select the "Select to Extract the R&amp;R Additional Senior/Key Person Form" button, which appears below.  </fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL7-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="35.75757575757576px" hyphenate="true" language="en" keep-together="always" top="140.60606060606062px" height="33.93939393939394px" width="494.54545454545456px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Save the file using a descriptive name, that will help you remember the content of the supplemental form that you are creating.  When assigning a name to the file, please remember to give it the extension ".xfd" (for example, "My_Senior_Key.xfd").  If you do not name your file with the ".xfd" extension you will be unable to open it later, using your PureEdge viewer software.&#xD;</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL8-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="35.75757575757576px" hyphenate="true" language="en" keep-together="always" top="177.5757575757576px" height="13.333333333333334px" width="494.54545454545456px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Using the "Open Form" tool on your PureEdge viewer, open the new form that you have just saved.  &#xD;
</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL9-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="35.75757575757576px" hyphenate="true" language="en" keep-together="always" top="193.93939393939394px" height="24.84848484848485px" width="494.54545454545456px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Enter your additional Senior/Key Person information in this supplemental form.  It is essentially the same as the Senior/Key person form that you see in the main body of your application.&#xD;</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL10-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="35.75757575757576px" hyphenate="true" language="en" keep-together="always" top="221.81818181818184px" height="13.333333333333334px" width="494.54545454545456px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">When you have completed entering information in the supplemental form, save it and close it.&#xD;</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL11-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="35.75757575757576px" hyphenate="true" language="en" keep-together="always" top="238.1818181818182px" height="13.333333333333334px" width="494.54545454545456px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Return to this "Additional Senior/Key Person Form Attachments" page.&#xD;</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL12-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="35.75757575757576px" hyphenate="true" language="en" keep-together="always" top="254.54545454545456px" height="13.333333333333334px" width="494.54545454545456px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Attach the saved supplemental form, that you just filled in, to one of the blocks provided on this "attachments" form.&#xD;</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="67.27272727272728px" hyphenate="true" language="en" keep-together="always" top="311.51515151515156px" height="33.93939393939394px" width="474.54545454545456px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Please attach additional Senior/Key Person forms, using the blocks below.  Please remember that the files you attach must be Senior/Key Person Pure Edge forms, which were previously extracted using the process outlined above.  Attaching any other type of file may result in the inability to submit your application to Grants.gov.</fo:block>
					</fo:block-container>
					<!--Block below is for the label named label_21-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="80.84848484848486px" hyphenate="true" language="en" keep-together="always" top="18.78787878787879px" height="23.636363636363637px" width="400.51515151515156px">
						<fo:block background-color="transparent" color="#000000" font-size="14pt" font-style="normal" font-family="Helvetica" font-weight="normal">RESEARCH &amp; RELATED Senior/Key Person Profile (Expanded)</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL3-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="178.1818181818182px" hyphenate="true" language="en" keep-together="always" top="42.42424242424243px" height="15.757575757575758px" width="220px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="normal">Additional Senior/Key Person Form Attachments</fo:block>
					</fo:block-container>
					<!--Draw lines-->
					<fo:block-container background-color="black" border-style="none" position="absolute" left="15.757575757575758px" top="415.1515151515152px" width="551.5151515151515px" height="1.8181818181818183px">
                            <fo:block/>
                    </fo:block-container>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	<!--========================================= KP Template ====================================-->
	<xsl:template name="KPTemplate">
		<fo:block font-size="8pt">
			<!--table for Senior/Key person  information-->
			<fo:table width="100%" border-style="solid" border-width="1pt" border-top-width="1.5pt" border-color="black" border-bottom-width="1.5pt">
				<fo:table-column/>
				<fo:table-body>
					<!--row1, header-->
					<fo:table-row>
						<fo:table-cell padding-before="2pt" padding-after="2pt">
							<fo:block text-align="center">
								<fo:inline font-weight="bold">PROFILE - Senior/Key Person</fo:inline>
								<fo:inline text-decoration="underline" font-weight="bold" color="black">&#160;</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<!--row2, name titles-->
					<fo:table-row>
						<fo:table-cell line-height="10pt" border-top-style="solid" border-width="1.5pt" border-top-color="black">
							<fo:block text-align="center">
								<fo:leader leader-pattern="space"/>
								<fo:table width="100%">
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-column column-width="proportional-column-width(25)"/>
									<fo:table-column column-width="proportional-column-width(20)"/>
									<fo:table-column column-width="proportional-column-width(35)"/>
									<fo:table-column column-width="proportional-column-width(10)"/>
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell>
												<fo:block>Prefix</fo:block>
											</fo:table-cell>
											<fo:table-cell>
												<fo:block>* First Name</fo:block>
											</fo:table-cell>
											<fo:table-cell>
												<fo:block>Middle Name</fo:block>
											</fo:table-cell>
											<fo:table-cell>
												<fo:block>* Last Name</fo:block>
											</fo:table-cell>
											<fo:table-cell>
												<fo:block>Suffix</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<!--row3, name values-->
					<xsl:for-each select="RR_KeyPersonExpanded:Name">
						<fo:table-row>
							<fo:table-cell line-height="10pt" padding-before="3pt">
								<fo:block text-align="center">
									<fo:table width="100%">
										<fo:table-column column-width="proportional-column-width(10)"/>
										<fo:table-column column-width="proportional-column-width(25)"/>
										<fo:table-column column-width="proportional-column-width(20)"/>
										<fo:table-column column-width="proportional-column-width(35)"/>
										<fo:table-column column-width="proportional-column-width(10)"/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell hyphenate="true" language="en">
													<fo:block>
														<xsl:value-of select="./globLib:PrefixName"/>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en">
													<fo:block>
														<xsl:value-of select="./globLib:FirstName"/>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en">
													<fo:block>
														<xsl:value-of select="./globLib:MiddleName"/>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en">
													<fo:block>
														<xsl:value-of select="./globLib:LastName"/>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en">
													<fo:block>
														<xsl:value-of select="./globLib:SuffixName"/>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
					</xsl:for-each>
					<!--row4-->
					<fo:table-row>
						<fo:table-cell line-height="10pt">
							<fo:block>
								<fo:leader leader-pattern="space"/>
								<fo:table width="100%">
									<fo:table-column column-width="proportional-column-width(50)"/>
									<fo:table-column column-width="proportional-column-width(50)"/>
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-start="4pt">
												<fo:block>Position/Title:&#160;<xsl:value-of select="RR_KeyPersonExpanded:Title"/>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell hyphenate="true" language="en">
												<fo:block>Department:&#160;<xsl:value-of select="RR_KeyPersonExpanded:DepartmentName"/>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<!--row5-->
					<fo:table-row>
						<fo:table-cell line-height="10pt">
							<fo:block>
								<fo:leader leader-pattern="space"/>
								<fo:table width="100%">
									<fo:table-column column-width="proportional-column-width(50)"/>
									<fo:table-column column-width="proportional-column-width(50)"/>
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-start="4pt">
												<fo:block>Organization Name:&#160;<xsl:value-of select="RR_KeyPersonExpanded:OrganizationName"/>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell hyphenate="true" language="en">
												<fo:block>Division:&#160;<xsl:value-of select="RR_KeyPersonExpanded:DivisionName"/>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<!--address for-each loop-->
					<xsl:for-each select="RR_KeyPersonExpanded:Address">
						<!--row6-->
						<fo:table-row>
							<fo:table-cell line-height="10pt">
								<fo:block>
									<fo:leader leader-pattern="space"/>
									<fo:table width="100%">
										<fo:table-column column-width="proportional-column-width(50)"/>
										<fo:table-column column-width="proportional-column-width(50)"/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell hyphenate="true" language="en" padding-start="4pt">
													<fo:block>* Street1:&#160;<xsl:value-of select="globLib:Street1"/>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en">
													<fo:block>Street2:&#160;<xsl:value-of select="globLib:Street2"/>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<!--row7 (5 columns)-->
						<fo:table-row>
							<fo:table-cell line-height="10pt" padding-after="3pt">
								<fo:block>
									<fo:leader leader-pattern="space"/>
									<fo:table width="100%">
										<fo:table-column column-width="proportional-column-width(28)"/>
										<fo:table-column column-width="proportional-column-width(27)"/>
										<fo:table-column column-width="proportional-column-width(15)"/>
										<fo:table-column column-width="proportional-column-width(15)"/>
										<fo:table-column column-width="proportional-column-width(15)"/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell hyphenate="true" language="en" padding-start="4pt">
													<fo:block>* City:&#160;<xsl:value-of select="globLib:City"/>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en">
													<fo:block>County:&#160;<xsl:value-of select="globLib:County"/>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en">
													<fo:block>* State:&#160;<xsl:value-of select="globLib:State"/>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en">
													<fo:block>Province:&#160;<xsl:value-of select="globLib:Province"/>
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
									<fo:leader leader-pattern="space"/>
									<fo:table width="100%">
										<fo:table-column column-width="proportional-column-width(28)"/>
										<fo:table-column column-width="proportional-column-width(27)"/>
										<fo:table-column column-width="proportional-column-width(15)"/>
										<fo:table-column column-width="proportional-column-width(15)"/>
										<fo:table-column column-width="proportional-column-width(15)"/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell hyphenate="true" language="en" padding-start="4pt">
													<fo:block>* Country:&#160;<xsl:value-of select="globLib:Country"/>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell hyphenate="true" language="en">
													<fo:block>* Zip / Postal Code:&#160;<xsl:value-of select="globLib:ZipPostalCode"/>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>						
						<!--end Address loop-->
					</xsl:for-each>
					<!--row phone, etc. names-->
					<fo:table-row>
						<fo:table-cell line-height="10pt" border-top-style="solid" border-top-color="black">
							<fo:block text-align="center">
								<fo:leader leader-pattern="space"/>
								<fo:table width="100%">
									<fo:table-column column-width="proportional-column-width(34)"/>
									<fo:table-column column-width="proportional-column-width(32)"/>
									<fo:table-column column-width="proportional-column-width(32)"/>
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell>
												<fo:block>*Phone Number</fo:block>
											</fo:table-cell>
											<fo:table-cell>
												<fo:block>Fax Number</fo:block>
											</fo:table-cell>
											<fo:table-cell>
												<fo:block>* E-Mail</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<!--row phone, etc. values-->
					<fo:table-row>
						<fo:table-cell line-height="10pt" padding-before="3pt" padding-after="5pt">
							<fo:block text-align="center">
								<fo:table width="100%">
									<fo:table-column column-width="proportional-column-width(34)"/>
									<fo:table-column column-width="proportional-column-width(32)"/>
									<fo:table-column column-width="proportional-column-width(32)"/>
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en">
												<fo:block>
													<xsl:value-of select="RR_KeyPersonExpanded:Phone"/>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell hyphenate="true" language="en">
												<fo:block>
													<xsl:value-of select="RR_KeyPersonExpanded:Fax"/>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell hyphenate="true" language="en">
												<fo:block>
													<xsl:value-of select="RR_KeyPersonExpanded:Email"/>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<!--row, credentials-->
					<fo:table-row>
						<fo:table-cell padding-start="4pt" padding-before="3pt" padding-after="3pt" line-height="8pt" border-top-style="solid" border-top-color="black" border-top-width="1.5pt">
							<fo:block>Credential, e.g., agency login:&#160;<xsl:value-of select="RR_KeyPersonExpanded:Credential"/>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<!--row, project role, project category-->
					<fo:table-row>
						<fo:table-cell padding-start="4pt" padding-before="3pt" padding-after="3pt" line-height="8pt" border-top-style="solid" border-top-color="black" border-top-width="1.5pt">
							<fo:block font-weight="bold">
								<fo:table width="100%">
									<fo:table-column column-width="proportional-column-width(45)"/>
									<fo:table-column column-width="proportional-column-width(55)"/>
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en" padding-start="4pt">
												<fo:block>* Project Role:&#160;<fo:inline font-weight="100">
														<xsl:value-of select="RR_KeyPersonExpanded:ProjectRole"/>
													</fo:inline>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell hyphenate="true" language="en">
												<fo:block>Other Project Role Category:&#160;<fo:inline font-weight="100">
														<xsl:value-of select="RR_KeyPersonExpanded:OtherProjectRoleCategory"/>
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
						<fo:table-cell padding-start="4pt" padding-before="3pt" padding-after="3pt" line-height="8pt" border-top-style="solid" border-top-color="black" border-top-width="1.5pt">
							<fo:block>
								<fo:table width="100%">
									<fo:table-column column-width="proportional-column-width(45)"/>
									<fo:table-column column-width="proportional-column-width(28)"/>
									<fo:table-column column-width="proportional-column-width(27)"/>
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell padding-after="4pt">
												<fo:block>&#160;</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center">
												<fo:block>File Name</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center">
												<fo:block>Mime Type</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell font-weight="bold" padding-after="6pt">
												<fo:block>*Attach Biographical Sketch</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" hyphenate="true" language="en">
												<fo:block>
													<xsl:value-of select="RR_KeyPersonExpanded:BioSketchsAttached/RR_KeyPersonExpanded:BioSketchAttached/att:FileName"/>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center">
												<fo:block>
													<xsl:value-of select="RR_KeyPersonExpanded:BioSketchsAttached/RR_KeyPersonExpanded:BioSketchAttached/att:MimeType"/>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell font-weight="bold" padding-after="6pt">
												<fo:block>Attach Current &#38; Pending Support</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center" hyphenate="true">
												<fo:block>
													<xsl:value-of select="RR_KeyPersonExpanded:SupportsAttached/RR_KeyPersonExpanded:SupportAttached/att:FileName"/>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="center">
												<fo:block>
													<xsl:value-of select="RR_KeyPersonExpanded:SupportsAttached/RR_KeyPersonExpanded:SupportAttached/att:MimeType"/>
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
			<!--space after tables-->
			<fo:block>&#160;</fo:block>
		</fo:block>
	</xsl:template>
	<!--========= End Key Person Template =============================================================-->
</xsl:stylesheet>
