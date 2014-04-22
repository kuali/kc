<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:RR_SF424_Multi_Project_Cover_1_0="http://apply.grants.gov/forms/RR_SF424_Multi_Project_Cover_1_0-V1.0"
	xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0"
	xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
	xmlns:header="http://apply.grants.gov/system/Header-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"> 
 
	<xsl:template match="RR_SF424_Multi_Project_Cover_1_0:RR_SF424_Multi_Project_Cover_1_0"> 
		<fo:root> 
 
			<fo:layout-master-set> 
				<fo:simple-page-master master-name="first" 
					page-height="11in" page-width="8.5in" margin-left="0.2in" 
					margin-right="0.2in"> 
					<fo:region-body margin-top="0.4in" margin-bottom="0.6in" /> 
					<fo:region-before region-name="region-before-first" 
						extent="0.1in" /> 
					<fo:region-after region-name="region-after-all" 
						extent="0.6in" />
				</fo:simple-page-master>
				<fo:simple-page-master master-name="rest"
					page-height="11in" page-width="8.5in" margin-left="0.2in"
					margin-right="0.2in">
					<fo:region-body margin-top="0.4in" margin-bottom="0.6in" />
					<fo:region-after region-name="region-after-all"
						extent="0.6in" />
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
 
			<fo:page-sequence master-reference="all-pages" 
				initial-page-number="1" format="1">
				<!-- ===================================== -->
				<!-- Header for Page 1 -->
				<!-- ===================================== -->
				<fo:static-content flow-name="region-before-first">
					<fo:table width="100%" space-before.optimum="0pt"
						space-after.optimum="0pt" table-layout="fixed">
						<fo:table-column column-width="proportional-column-width(1)" />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en"
									line-height="9pt" padding-start="0pt" padding-end="0pt"
									padding-before="1pt" padding-after="1pt" display-align="before"
									text-align="right" border-style="solid" border-width="0pt"
									border-color="white">
									<fo:block>
										<fo:inline font-size="6px">OMB Number:
											4040-0001</fo:inline>
									</fo:block>
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Expiration
											Date: 06/30/2016</fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>
				<fo:static-content flow-name="region-after-all">
					<fo:table width="100%" space-before.optimum="0pt"
						space-after.optimum="0pt" table-layout="fixed">
						<fo:table-column column-width="proportional-column-width(1)" />
						<fo:table-column column-width="proportional-column-width(1)" />
						<fo:table-body> 
						<!--  -->
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
											<xsl:call-template name="formatDate"> 
											<xsl:with-param name="value" 
												select="/*/*/footer:ReceivedDateTime" /> 
											</xsl:call-template>  
										</fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>

				<fo:flow flow-name="xsl-region-body"> 
               <!-- ===================================== --> 
					<!-- First Page --> 
					<!-- ===================================== --> 
					<fo:table width="100%" space-before.optimum="1pt" 
						space-after.optimum="2pt" table-layout="fixed"> 
						<fo:table-column column-width="proportional-column-width(1)" /> 
						<fo:table-column column-width="proportional-column-width(1)" /> 
						<fo:table-column column-width="proportional-column-width(1)" /> 
						<fo:table-column column-width="proportional-column-width(1)" /> 
						<fo:table-body> 
							<fo:table-row> 
								<!-- ============================ --> 
								<!-- Title --> 
								<!-- ============================ --> 
								<fo:table-cell hyphenate="true" language="en" 
									number-rows-spanned="1" number-columns-spanned="2" text-align="left" 
									padding-start="0pt" padding-end="3pt" padding-before="0pt" 
									padding-after="1pt" display-align="before" border-style="solid" 
									border-width="0pt" border-color="white"> 
									<fo:block font-size="9px">APPLICATION FOR FEDERAL ASSISTANCE 
									</fo:block> 
									<fo:block font-size="12px" font-weight="bold">SF 424 
										(R&amp;R)</fo:block> 
								</fo:table-cell> 
                                
								<fo:table-cell hyphenate="true" language="en" 
									text-align="left" padding-start="3pt" padding-end="3pt" 
									padding-before="3pt" padding-after="1pt" display-align="before" 
									border-bottom-style="solid" border-bottom-width="1pt" border-bottom-color="black"> 
									<fo:block font-size="9px" font-weight="bold">&#160;</fo:block> 
								</fo:table-cell> 
								
                                <fo:table-cell hyphenate="true" language="en" 
									text-align="left" padding-start="3pt" padding-end="3pt" 
									padding-before="3pt" padding-after="1pt" display-align="before" 
									border-bottom-style="solid" border-bottom-width="1pt" border-bottom-color="black"> 
									<fo:block font-size="9px" font-weight="bold">&#160;</fo:block> 
								</fo:table-cell> 
							</fo:table-row> 
						
							<fo:table-row> 
								<!-- ============================ --> 
								<!-- Applicant Information --> 
								<!-- ============================ --> 
								<fo:table-cell hyphenate="true" language="en" 
									number-columns-spanned="4" text-align="left" padding-start="3pt" 
									padding-end="3pt" padding-before="3pt" padding-after="1pt" 
									display-align="before" border-style="solid" border-width="1pt" 
									border-color="black"> 
									<fo:table width="100%" space-before.optimum="0pt" 
										space-after.optimum="0pt" table-layout="fixed"> 
										<fo:table-column column-width="proportional-column-width(1)" /> 
										<fo:table-column column-width="proportional-column-width(2)" /> 
										<fo:table-body> 
											<fo:table-row> 
												<fo:table-cell hyphenate="true" language="en" 
													padding-start="0pt" padding-end="0pt" padding-before="1pt" 
													padding-after="1pt" display-align="before" text-align="left" 
													border-style="solid" border-width="0pt" border-color="white"> 
													<fo:block font-weight="bold" font-size="9px">5. 
														APPLICANT INFORMATION</fo:block> 
												</fo:table-cell> 
												<fo:table-cell hyphenate="true" language="en" 
													padding-start="0pt" padding-end="0pt" padding-before="1pt" 
													padding-after="1pt" display-align="before" text-align="right" 
													border-style="solid" border-width="0pt" border-color="white"> 
													<fo:block font-size="9px"> 
														<fo:inline font-weight="bold">Organizational DUNS*: 
														</fo:inline> 
														<fo:inline font-family="Georgia" text-indent="10pt"> 
															<xsl:value-of 
																select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:OrganizationInfo/RR_SF424_Multi_Project_Cover_1_0:DUNSID" /> 
														</fo:inline> 
													</fo:block> 
												</fo:table-cell> 
											</fo:table-row> 
											<fo:table-row> 
												<fo:table-cell hyphenate="true" language="en" 
													number-columns-spanned="2" padding-start="0pt" padding-end="0pt" 
													padding-before="1pt" padding-after="1pt" display-align="before" 
													text-align="start" border-style="solid" border-width="0pt" 
													border-color="white"> 
													<fo:table>						
														<fo:table-column column-width="1.3in"  />
														<fo:table-column column-width="7.6in"  />
															<fo:table-body>
																<fo:table-row>
																	<fo:table-cell>
																		<fo:block font-size="9px">Legal Name*:</fo:block>
																	</fo:table-cell>
																	<fo:table-cell> 
																		<fo:block font-family="Georgia" font-size="9px">
																			<xsl:value-of select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:OrganizationInfo/RR_SF424_Multi_Project_Cover_1_0:OrganizationName" /> 
																		</fo:block> 
																	</fo:table-cell>
																</fo:table-row>
															</fo:table-body>
													</fo:table>	
												</fo:table-cell> 
											</fo:table-row> 
											<xsl:call-template name="printAddressFormatted"> 
												<xsl:with-param name="department"> 
													<xsl:value-of 
														select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:OrganizationInfo/RR_SF424_Multi_Project_Cover_1_0:DepartmentName" /> 
												</xsl:with-param> 
												<xsl:with-param name="division"> 
													<xsl:value-of 
														select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:OrganizationInfo/RR_SF424_Multi_Project_Cover_1_0:DivisionName" /> 
												</xsl:with-param> 
												<xsl:with-param name="street1"> 
													<xsl:value-of 
														select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:OrganizationInfo/RR_SF424_Multi_Project_Cover_1_0:Address/RR_SF424_Multi_Project_Cover_1_0:Street1" /> 
												</xsl:with-param> 
												<xsl:with-param name="street2"> 
													<xsl:value-of 
														select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:OrganizationInfo/RR_SF424_Multi_Project_Cover_1_0:Address/RR_SF424_Multi_Project_Cover_1_0:Street2" /> 
												</xsl:with-param> 
												<xsl:with-param name="city"> 
													<xsl:value-of 
														select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:OrganizationInfo/RR_SF424_Multi_Project_Cover_1_0:Address/RR_SF424_Multi_Project_Cover_1_0:City" /> 
												</xsl:with-param> 
												<xsl:with-param name="county"> 
													<xsl:value-of 
														select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:OrganizationInfo/RR_SF424_Multi_Project_Cover_1_0:Address/RR_SF424_Multi_Project_Cover_1_0:County" /> 
												</xsl:with-param> 
												<xsl:with-param name="state"> 
													<xsl:value-of 
														select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:OrganizationInfo/RR_SF424_Multi_Project_Cover_1_0:Address/RR_SF424_Multi_Project_Cover_1_0:State" /> 
												</xsl:with-param> 
												<xsl:with-param name="province"> 
													<xsl:value-of 
														select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:OrganizationInfo/RR_SF424_Multi_Project_Cover_1_0:Address/RR_SF424_Multi_Project_Cover_1_0:Province" /> 
												</xsl:with-param> 
												<xsl:with-param name="country"> 
													<xsl:value-of 
														select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:OrganizationInfo/RR_SF424_Multi_Project_Cover_1_0:Address/RR_SF424_Multi_Project_Cover_1_0:Country" /> 
												</xsl:with-param> 
												<xsl:with-param name="zip"> 
													<xsl:value-of 
														select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:OrganizationInfo/RR_SF424_Multi_Project_Cover_1_0:Address/RR_SF424_Multi_Project_Cover_1_0:ZipPostalCode" /> 
												</xsl:with-param> 
											</xsl:call-template> 
										</fo:table-body> 
									</fo:table> 
								</fo:table-cell> 
							</fo:table-row> 
							<fo:table-row> 
								<fo:table-cell hyphenate="true" language="en" 
									number-columns-spanned="4" text-align="left" padding-start="3pt" 
									padding-end="3pt" padding-before="3pt" padding-after="1pt" 
									display-align="before" border-style="solid" border-width="1pt" 
									border-color="black"> 
									<fo:block font-size="9px">Person to be contacted on matters 
										involving this application</fo:block> 
									<xsl:call-template name="printNameFormatted"> 
										<xsl:with-param name="prefix"> 
											<xsl:value-of 
												select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:ContactPersonInfo/RR_SF424_Multi_Project_Cover_1_0:Name/RR_SF424_Multi_Project_Cover_1_0:PrefixName" /> 
										</xsl:with-param> 
										<xsl:with-param name="first"> 
											<xsl:value-of 
												select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:ContactPersonInfo/RR_SF424_Multi_Project_Cover_1_0:Name/RR_SF424_Multi_Project_Cover_1_0:FirstName" /> 
										</xsl:with-param> 
										<xsl:with-param name="middle"> 
											<xsl:value-of 
												select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:ContactPersonInfo/RR_SF424_Multi_Project_Cover_1_0:Name/RR_SF424_Multi_Project_Cover_1_0:MiddleName" /> 
										</xsl:with-param> 
										<xsl:with-param name="last"> 
											<xsl:value-of 
												select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:ContactPersonInfo/RR_SF424_Multi_Project_Cover_1_0:Name/RR_SF424_Multi_Project_Cover_1_0:LastName" /> 
										</xsl:with-param> 
										<xsl:with-param name="suffix"> 
											<xsl:value-of 
												select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:ContactPersonInfo/RR_SF424_Multi_Project_Cover_1_0:Name/RR_SF424_Multi_Project_Cover_1_0:SuffixName" /> 
										</xsl:with-param> 
									</xsl:call-template> 

									<fo:table width="100%" space-before.optimum="0pt" 
										space-after.optimum="0pt" table-layout="fixed"> 
										<fo:table-column column-width="proportional-column-width(1)" /> 
										<fo:table-column column-width="proportional-column-width(2)" /> 
										<fo:table-body> 
											<fo:table-row> 
												<fo:table-cell hyphenate="true" language="en" 
													padding-start="0pt" padding-end="0pt" padding-before="1pt" 
													padding-after="1pt" display-align="before" text-align="start" 
													border-style="solid" border-width="0pt" border-color="white"> 
													<fo:block font-size="9px"> 
														
													</fo:block> 
												</fo:table-cell> 
												<fo:table-cell hyphenate="true" language="en" 
													padding-start="0pt" padding-end="0pt" padding-before="1pt" 
													padding-after="1pt" display-align="before" text-align="start" 
													border-style="solid" border-width="0pt" border-color="white"> 
													<fo:block font-size="9px"> 
														
													</fo:block> 
												</fo:table-cell> 
											</fo:table-row> 
										
                                        <xsl:call-template name="printAddressFormattedForContact"> 
												<xsl:with-param name="position"> 
													<xsl:value-of 
														select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:ContactPersonInfo/RR_SF424_Multi_Project_Cover_1_0:Title" /> 
												</xsl:with-param> 
												<xsl:with-param name="street1"> 
													<xsl:value-of 
														select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:ContactPersonInfo/RR_SF424_Multi_Project_Cover_1_0:Address/RR_SF424_Multi_Project_Cover_1_0:Street1" /> 
												</xsl:with-param> 
												<xsl:with-param name="city"> 
													<xsl:value-of 
														select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:ContactPersonInfo/RR_SF424_Multi_Project_Cover_1_0:Address/RR_SF424_Multi_Project_Cover_1_0:City" /> 
												</xsl:with-param> 
												<xsl:with-param name="street2"> 
													<xsl:value-of 
														select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:ContactPersonInfo/RR_SF424_Multi_Project_Cover_1_0:Address/RR_SF424_Multi_Project_Cover_1_0:Street2" /> 
												</xsl:with-param> 
												<xsl:with-param name="county"> 
													<xsl:value-of 
														select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:ContactPersonInfo/RR_SF424_Multi_Project_Cover_1_0:Address/RR_SF424_Multi_Project_Cover_1_0:County" /> 
												</xsl:with-param> 
												<xsl:with-param name="state"> 
													<xsl:value-of 
														select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:ContactPersonInfo/RR_SF424_Multi_Project_Cover_1_0:Address/RR_SF424_Multi_Project_Cover_1_0:State" /> 
												</xsl:with-param> 
												<xsl:with-param name="province"> 
													<xsl:value-of 
														select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:ContactPersonInfo/RR_SF424_Multi_Project_Cover_1_0:Address/RR_SF424_Multi_Project_Cover_1_0:Province" /> 
												</xsl:with-param> 
												<xsl:with-param name="country"> 
													<xsl:value-of 
														select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:ContactPersonInfo/RR_SF424_Multi_Project_Cover_1_0:Address/RR_SF424_Multi_Project_Cover_1_0:Country" /> 
												</xsl:with-param> 
												<xsl:with-param name="zip"> 
													<xsl:value-of 
														select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:ContactPersonInfo/RR_SF424_Multi_Project_Cover_1_0:Address/RR_SF424_Multi_Project_Cover_1_0:ZipPostalCode" /> 
												</xsl:with-param> 
									</xsl:call-template> 
										</fo:table-body> 
									</fo:table> 

									<xsl:call-template name="printContactInfo2"> 
										<xsl:with-param name="phone"> 
											<xsl:value-of 
												select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:ContactPersonInfo/RR_SF424_Multi_Project_Cover_1_0:Phone" /> 
										</xsl:with-param> 
										<xsl:with-param name="fax"> 
											<xsl:value-of 
												select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:ContactPersonInfo/RR_SF424_Multi_Project_Cover_1_0:Fax" /> 
										</xsl:with-param> 
										<xsl:with-param name="email"> 
											<xsl:value-of 
												select="RR_SF424_Multi_Project_Cover_1_0:ApplicantInfo/RR_SF424_Multi_Project_Cover_1_0:ContactPersonInfo/RR_SF424_Multi_Project_Cover_1_0:Email" /> 
										</xsl:with-param> 
									</xsl:call-template> 
								</fo:table-cell> 
							</fo:table-row> 

							<fo:table-row> 
								<!-- ============================ --> 
								<!-- Applicant Type --> 
								<!-- ============================ --> 
								<fo:table-cell hyphenate="true" language="en" 
									number-columns-spanned="2" text-align="left" padding-start="3pt" 
									padding-end="3pt" padding-before="3pt" padding-after="1pt" 
									display-align="before"
                                    border-top-style="solid" border-top-width="1pt" border-top-color="black"
                                    border-left-style="solid" border-left-width="1pt" border-left-color="black"
                                    border-bottom-style="solid" border-bottom-width="1pt" border-bottom-color="black"> 
									<fo:block font-size="9px" font-weight="bold">7.&#160;&#160;TYPE OF 
										APPLICANT*</fo:block> 
								</fo:table-cell> 
								<fo:table-cell hyphenate="true" language="en" 
									number-columns-spanned="2" text-align="left" padding-start="3pt" 
									padding-end="3pt" padding-before="3pt" padding-after="1pt" 
									display-align="before" 
                                    border-top-style="solid" border-top-width="1pt" border-top-color="black"
                                    border-right-style="solid" border-right-width="1pt" border-right-color="black"
                                    border-bottom-style="solid" border-bottom-width="1pt" border-bottom-color="black"> 
									<fo:block font-family="Georgia" font-size="9px" text-indent="10pt"> 
										<xsl:value-of 
											select="RR_SF424_Multi_Project_Cover_1_0:ApplicantType/RR_SF424_Multi_Project_Cover_1_0:ApplicantTypeCode" /> 
									</fo:block> 
								</fo:table-cell> 
							</fo:table-row> 
							<fo:table-row> 
								<!-- ==================================== --> 
								<!-- Applicant Type (Other and Small Biz) --> 
								<!-- ==================================== --> 
								<fo:table-cell hyphenate="true" language="en" 
									number-columns-spanned="4" padding-start="3pt" padding-end="3pt" 
									padding-before="1pt" padding-after="1pt" display-align="before" 
									text-align="left" border-style="solid" border-width="1pt" 
									border-color="black"> 
									<fo:block font-size="9px"> 
										Other (Specify): 
										<xsl:value-of 
											select="RR_SF424_Multi_Project_Cover_1_0:ApplicantType/RR_SF424_Multi_Project_Cover_1_0:ApplicantTypeCodeOtherExplanation" /> 
									</fo:block> 
									<fo:table width="100%" space-before.optimum="0pt" 
										space-after.optimum="0pt" table-layout="fixed"> 
										<fo:table-column column-width="proportional-column-width(2)" /> 
										<fo:table-column column-width="proportional-column-width(1)" /> 
										<fo:table-column column-width="proportional-column-width(2)" /> 
										<fo:table-body> 
											<fo:table-row> 
												<fo:table-cell number-columns-spanned="1" 
													padding-start="0pt" padding-end="0pt" padding-before="1pt" 
													padding-after="1pt" display-align="before" text-align="center" 
													border-style="solid" border-width="0pt" border-color="white"> 
													<fo:block font-size="9px" font-weight="bold" 
														text-align="center">Small Business Organization Type</fo:block> 
												</fo:table-cell> 
												<fo:table-cell padding-start="3pt" 
													padding-end="3pt" padding-before="1pt" padding-after="1pt" 
													display-align="before" text-align="left" border-style="solid" 
													border-width="0pt" border-color="white"> 
													<fo:block font-size="9px"> 
														<xsl:call-template name="checkbox"> 
															<xsl:with-param name="value" 
																select="RR_SF424_Multi_Project_Cover_1_0:ApplicantType/RR_SF424_Multi_Project_Cover_1_0:SmallBusinessOrganizationType/RR_SF424_Multi_Project_Cover_1_0:isWomenOwned" /> 
														</xsl:call-template> 
														<fo:inline> Women Owned</fo:inline> 
													</fo:block> 
												</fo:table-cell> 
												<fo:table-cell padding-start="3pt" 
													padding-end="3pt" padding-before="1pt" padding-after="1pt" 
													display-align="before" text-align="left" border-style="solid" 
													border-width="0pt" border-color="white"> 
													<fo:block font-size="9px"> 
														<xsl:call-template name="checkbox"> 
															<xsl:with-param name="value" 
																select="RR_SF424_Multi_Project_Cover_1_0:ApplicantType/RR_SF424_Multi_Project_Cover_1_0:SmallBusinessOrganizationType/RR_SF424_Multi_Project_Cover_1_0:isSociallyEconomicallyDisadvantaged" /> 
														</xsl:call-template> 
														<fo:inline> Socially and Economically Disadvantaged 
														</fo:inline> 
													</fo:block> 
												</fo:table-cell> 
											</fo:table-row> 
										</fo:table-body> 
									</fo:table> 
								</fo:table-cell> 
							</fo:table-row> 
							
                            <fo:table-row> 
								<!-- ============================ --> 
								<!-- Project Title --> 
								<!-- ============================ --> 
								<fo:table-cell hyphenate="true" language="en" 
									number-columns-spanned="4" padding-start="3pt" padding-end="3pt" 
									padding-before="1pt" padding-after="1pt" display-align="before" 
									text-align="left" border-style="solid" border-width="1pt" 
									border-color="black"> 
									<fo:block font-size="9px" font-weight="bold">11.&#160;DESCRIPTIVE TITLE OF APPLICANT'S PROJECT*</fo:block> 
									<fo:block font-family="Georgia" font-size="9px"> 
										<xsl:value-of select="RR_SF424_Multi_Project_Cover_1_0:ProjectTitle" /> 
									</fo:block> 
								</fo:table-cell> 
							</fo:table-row> 
							
                            <fo:table-row> 
								<!-- ============================ --> 
								<!-- Project Dates --> 
								<!-- ============================ --> 
								<fo:table-cell hyphenate="true" language="en" 
									number-columns-spanned="2" padding-start="3pt" padding-end="3pt" 
									padding-before="1pt" padding-after="1pt" display-align="before" 
									text-align="left" border-style="solid" border-width="1pt" 
									border-color="black"> 
									<fo:block font-size="9px" font-weight="bold">12. PROPOSED 
										PROJECT</fo:block> 
									<fo:block font-size="9px"> 
										<fo:table width="90%" space-before.optimum="0pt" 
											space-after.optimum="0pt" table-layout="fixed"> 
											<fo:table-column column-width="proportional-column-width(1)" /> 
											<fo:table-column column-width="proportional-column-width(1)" /> 
											<fo:table-body> 
												<fo:table-row> 
													<fo:table-cell padding-start="0pt" 
														padding-end="0pt" padding-before="1pt" padding-after="1pt" 
														display-align="before" text-align="left" border-style="solid" 
														border-width="0pt" border-color="white"> 
														<fo:block font-size="9px">Start Date*</fo:block> 
													</fo:table-cell> 
													<fo:table-cell padding-start="0pt" 
														padding-end="0pt" padding-before="1pt" padding-after="1pt" 
														display-align="before" text-align="left" border-style="solid" 
														border-width="0pt" border-color="white"> 
														<fo:block font-size="9px">Ending Date*</fo:block> 
													</fo:table-cell> 
												</fo:table-row> 
												<fo:table-row> 
													<fo:table-cell padding-start="0pt" 
														padding-end="0pt" padding-before="1pt" padding-after="1pt" 
														display-align="before" text-align="left" border-style="solid" 
														border-width="0pt" border-color="white"> 
														<fo:block font-family="Georgia" font-size="9px"> 
															<xsl:call-template name="formatDate"> 
																<xsl:with-param name="value" 
																	select="RR_SF424_Multi_Project_Cover_1_0:ProposedProjectPeriod/RR_SF424_Multi_Project_Cover_1_0:ProposedStartDate" /> 
															</xsl:call-template> 
														</fo:block> 
													</fo:table-cell> 
													<fo:table-cell padding-start="0pt" 
														padding-end="0pt" padding-before="1pt" padding-after="1pt" 
														display-align="before" text-align="left" border-style="solid" 
														border-width="0pt" border-color="white"> 
														<fo:block font-family="Georgia" font-size="9px"> 
															<xsl:call-template name="formatDate"> 
																<xsl:with-param name="value" 
																	select="RR_SF424_Multi_Project_Cover_1_0:ProposedProjectPeriod/RR_SF424_Multi_Project_Cover_1_0:ProposedEndDate" /> 
															</xsl:call-template> 
														</fo:block> 
													</fo:table-cell> 
												</fo:table-row> 
											</fo:table-body> 
										</fo:table> 
									</fo:block> 
								</fo:table-cell> 
 
								<fo:table-cell hyphenate="true" language="en" 
									number-columns-spanned="2" padding-start="3pt" padding-end="3pt" 
									padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" 
                                    border-top-style="solid" border-top-width="1pt" border-top-color="black"
                                    border-bottom-style="solid" border-bottom-width="1pt" border-bottom-color="black"
                                    border-right-style="solid" border-right-width="1pt" border-right-color="black"> 
									<fo:block font-size="9px" font-weight="bold">&#160;</fo:block> 
								</fo:table-cell> 
							</fo:table-row> 
 						</fo:table-body>                          
  						</fo:table>
				</fo:flow> 
			</fo:page-sequence> 
		</fo:root> 
	</xsl:template> 
 
 
	<!-- ============================================= --> 
	<!-- FORMAT DATE --> 
	<!-- Writes XSD:date style text into to mm-dd-yyyy --> 
	<!-- ============================================= --> 
	<xsl:template name="formatDate"> 
		<xsl:param name="value" /> 
		<xsl:if test="$value != ''"> 
			<xsl:value-of select="format-number(substring($value,6,2), '00')" /> 
			<xsl:text>/</xsl:text> 
			<xsl:value-of select="format-number(substring($value,9,2), '00')" /> 
			<xsl:text>/</xsl:text> 
			<xsl:value-of select="format-number(substring($value,1,4), '0000')" /> 
		</xsl:if> 
	</xsl:template> 
 
	<!-- ============================================= --> 
	<!-- PRINT ADDRESS --> 
	<!-- Prints address in the usual format --> 
	<!-- ============================================= --> 
	<xsl:template name="printAddress"> 
		<xsl:param name="street1" /> 
		<xsl:param name="street2" /> 
		<xsl:param name="city" /> 
		<xsl:param name="state" /> 
		<xsl:param name="province" /> 
		<xsl:param name="country" /> 
		<xsl:param name="zip" /> 
		<fo:block line-height="9pt"> 
			<fo:block font-size="9px"> 
				<xsl:value-of select="$street1" /> 
			</fo:block> 
			<fo:block font-size="9px"> 
				<xsl:value-of select="$street2" /> 
			</fo:block> 
			<fo:block font-size="9px"> 
				<xsl:value-of select="$city" /> 
				,&#160; 
				<xsl:value-of select="$state" /> 
				&#160; 
				<xsl:value-of select="$province" /> 
				&#160; 
				<xsl:value-of select="$country" /> 
				<xsl:value-of select="$zip" /> 
				&#160; 
			</fo:block> 
		</fo:block> 
	</xsl:template> 
 
	<!-- ============================================= --> 
	<!-- PRINT ADDRESS FORMATTED For Contact --> 
	<!-- Prints address in the format used in XFD --> 
	<!-- ============================================= --> 
	<xsl:template name="printAddressFormattedForContact"> 
		<xsl:param name="position" /> 
		<xsl:param name="street1" /> 
		<xsl:param name="street2" /> 
		<xsl:param name="city" /> 
		<xsl:param name="county" /> 
		<xsl:param name="state" /> 
		<xsl:param name="province" /> 
		<xsl:param name="country" /> 
		<xsl:param name="zip" /> 
		<fo:table-row> 
			<fo:table-cell hyphenate="true" language="en" 
				padding-start="0pt" padding-end="0pt" padding-before="1pt" 
				padding-after="1pt" display-align="before" text-align="start" 
				border-style="solid" border-width="0pt" border-color="white"> 
				<fo:table>						
					<fo:table-column column-width="1.3in"  />
					<fo:table-column column-width="7.6in"  />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell>
									<fo:block font-size="9px">Position/Title:</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block font-family="Georgia" font-size="9px"><xsl:value-of select="$position" /></fo:block> 
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
				</fo:table>	
			</fo:table-cell> 
		</fo:table-row> 
		<fo:table-row> 
			<fo:table-cell hyphenate="true" language="en" 
				padding-start="0pt" padding-end="0pt" padding-before="1pt" 
				padding-after="1pt" display-align="before" text-align="start" 
				border-style="solid" border-width="0pt" border-color="white"> 
				<fo:table>						
					<fo:table-column column-width="1.3in"  />
					<fo:table-column column-width="7.6in"  />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell>
									<fo:block font-size="9px">Street1*:</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block font-family="Georgia" font-size="9px"><xsl:value-of select="$street1" /></fo:block> 
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
				</fo:table>	
			</fo:table-cell> 
		</fo:table-row> 
		
		<fo:table-row> 
        		<fo:table-cell hyphenate="true" language="en" 
				padding-start="0pt" padding-end="0pt" padding-before="1pt" 
				padding-after="1pt" display-align="before" text-align="start" 
				border-style="solid" border-width="0pt" border-color="white"> 
				<fo:table>						
					<fo:table-column column-width="1.3in"  />
					<fo:table-column column-width="7.6in"  />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell>
									<fo:block font-size="9px">Street2:</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block font-family="Georgia" font-size="9px"><xsl:value-of select="$street2" /></fo:block> 
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
				</fo:table>	
			</fo:table-cell> 
		</fo:table-row> 
		
		<fo:table-row> 
			<fo:table-cell hyphenate="true" language="en" 
				number-columns-spanned="2" padding-start="0pt" padding-end="0pt" 
				padding-before="1pt" padding-after="1pt" display-align="before" 
				text-align="start" border-style="solid" border-width="0pt" 
				border-color="white"> 
				<fo:table width="100%" space-before.optimum="0pt" 
					space-after.optimum="0pt" table-layout="fixed"> 
					<fo:table-column column-width="proportional-column-width(2)" /> 
					<fo:table-column column-width="proportional-column-width(2)" /> 
					<fo:table-column column-width="proportional-column-width(1)" /> 
					<fo:table-column column-width="proportional-column-width(1)" /> 
					<fo:table-body> 
						<fo:table-row> 
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:table>					
									<fo:table-column column-width="1.3in"  />
									<fo:table-column column-width="7.6in"  />
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell>
													<fo:block font-size="9px">City*:</fo:block>
												</fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Georgia" font-size="9px"><xsl:value-of select="$city" /></fo:block> 
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
								</fo:table>	
							</fo:table-cell> 
						</fo:table-row> 
						
						<fo:table-row> 							
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:table>					
									<fo:table-column column-width="1.3in"  />
									<fo:table-column column-width="7.6in"  />
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell>
													<fo:block font-size="9px">County:</fo:block>
												</fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Georgia" font-size="9px"><xsl:value-of select="$county" /></fo:block> 
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
								</fo:table>	
							</fo:table-cell> 
						</fo:table-row> 
						
						<fo:table-row> 							
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:table>					
									<fo:table-column column-width="1.3in"  />
									<fo:table-column column-width="7.6in"  />
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell>
													<fo:block font-size="9px">State*:</fo:block>
												</fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Georgia" font-size="9px"><xsl:value-of select="$state" /></fo:block> 
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
								</fo:table>	
							</fo:table-cell> 
						</fo:table-row> 					
					</fo:table-body> 
				</fo:table> 
			</fo:table-cell> 
		</fo:table-row> 
		<fo:table-row> 
			<fo:table-cell hyphenate="true" language="en" 
				number-columns-spanned="2" padding-start="0pt" padding-end="0pt" 
				padding-before="1pt" padding-after="1pt" display-align="before" 
				text-align="start" border-style="solid" border-width="0pt" 
				border-color="white"> 
				<fo:table width="100%" space-before.optimum="0pt" 
					space-after.optimum="0pt" table-layout="fixed"> 
					<fo:table-column column-width="proportional-column-width(2)" /> 
					<fo:table-column column-width="proportional-column-width(2)" /> 
					<fo:table-column column-width="proportional-column-width(1)" /> 
					<fo:table-column column-width="proportional-column-width(1)" /> 
					<fo:table-body> 
						<fo:table-row> 
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:table>					
									<fo:table-column column-width="1.3in"  />
									<fo:table-column column-width="7.6in"  />
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell>
													<fo:block font-size="9px">Province:</fo:block>
												</fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Georgia" font-size="9px"><xsl:value-of select="$province" /></fo:block> 
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
								</fo:table>	
							</fo:table-cell> 
						</fo:table-row> 
						
						<fo:table-row> 										
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:table>					
									<fo:table-column column-width="1.3in"  />
									<fo:table-column column-width="7.6in"  />
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell>
													<fo:block font-size="9px">Country*:</fo:block>
												</fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Georgia" font-size="9px"><xsl:value-of select="$country" /></fo:block> 
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
								</fo:table>	
							</fo:table-cell> 
						</fo:table-row> 
						
						<fo:table-row> 							
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:table>					
									<fo:table-column column-width="1.3in"  />
									<fo:table-column column-width="7.6in"  />
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell>
													<fo:block font-size="9px">ZIP / Postal Code*:</fo:block>
												</fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Georgia" font-size="9px"><xsl:value-of select="$zip" /></fo:block> 
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
								</fo:table>	
							</fo:table-cell> 
						</fo:table-row> 
					</fo:table-body> 
				</fo:table> 
			</fo:table-cell> 
		</fo:table-row> 
	</xsl:template> 
 
	<!-- ============================================= --> 
	<!-- PRINT ADDRESS FORMATTED --> 
	<!-- Prints address in the format used in XFD --> 
	<!-- ============================================= --> 
	<xsl:template name="printAddressFormatted"> 
		<xsl:param name="department" /> 
		<xsl:param name="division" /> 
		<xsl:param name="street1" /> 
		<xsl:param name="street2" /> 
		<xsl:param name="city" /> 
		<xsl:param name="county" /> 
		<xsl:param name="state" /> 
		<xsl:param name="province" /> 
		<xsl:param name="country" /> 
		<xsl:param name="zip" /> 
		<fo:table-row> 
			<fo:table-cell hyphenate="true" language="en" 
				padding-start="0pt" padding-end="0pt" padding-before="1pt" 
				padding-after="1pt" display-align="before" text-align="start" 
				border-style="solid" border-width="0pt" border-color="white"> 
				<fo:table>						
					<fo:table-column column-width="1.3in"  />
					<fo:table-column column-width="7.6in"  />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell>
									<fo:block font-size="9px">Department:</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block font-family="Georgia" font-size="9px"><xsl:value-of select="$department" /></fo:block> 
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
				</fo:table>	
			</fo:table-cell> 
		</fo:table-row> 
		
		<fo:table-row> 
			<fo:table-cell hyphenate="true" language="en" 
				padding-start="0pt" padding-end="0pt" padding-before="1pt" 
				padding-after="1pt" display-align="before" text-align="start" 
				border-style="solid" border-width="0pt" border-color="white"> 
				<fo:table>						
					<fo:table-column column-width="1.3in"  />
					<fo:table-column column-width="7.6in"  />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell>
									<fo:block font-size="9px">Division:</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block font-family="Georgia" font-size="9px"><xsl:value-of select="$division" /></fo:block> 
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
				</fo:table>	
			</fo:table-cell> 
		</fo:table-row> 
		
		<fo:table-row> 
			<fo:table-cell hyphenate="true" language="en" 
				padding-start="0pt" padding-end="0pt" padding-before="1pt" 
				padding-after="1pt" display-align="before" text-align="start" 
				border-style="solid" border-width="0pt" border-color="white"> 
				<fo:table>						
					<fo:table-column column-width="1.3in"  />
					<fo:table-column column-width="7.6in"  />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell>
									<fo:block font-size="9px">Street1*:</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block font-family="Georgia" font-size="9px"><xsl:value-of select="$street1" /></fo:block> 
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
				</fo:table>	
			</fo:table-cell> 
		</fo:table-row> 
		
		<fo:table-row> 
			<fo:table-cell hyphenate="true" language="en" 
				padding-start="0pt" padding-end="0pt" padding-before="1pt" 
				padding-after="1pt" display-align="before" text-align="start" 
				border-style="solid" border-width="0pt" border-color="white"> 
				<fo:table>						
					<fo:table-column column-width="1.3in"  />
					<fo:table-column column-width="7.6in"  />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell>
									<fo:block font-size="9px">Street2:</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block font-family="Georgia" font-size="9px"><xsl:value-of select="$street2" /></fo:block> 
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
				</fo:table>	
			</fo:table-cell> 
		</fo:table-row> 
		
		<fo:table-row> 
			<fo:table-cell hyphenate="true" language="en" 
				number-columns-spanned="2" padding-start="0pt" padding-end="0pt" 
				padding-before="1pt" padding-after="1pt" display-align="before" 
				text-align="start" border-style="solid" border-width="0pt" 
				border-color="white"> 
				<fo:table width="100%" space-before.optimum="0pt" 
					space-after.optimum="0pt" table-layout="fixed"> 
					<fo:table-column column-width="proportional-column-width(2)" /> 
					<fo:table-column column-width="proportional-column-width(2)" /> 
					<fo:table-column column-width="proportional-column-width(1)" /> 
					<fo:table-column column-width="proportional-column-width(1)" /> 
					<fo:table-body> 
						<fo:table-row> 
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:table>					
									<fo:table-column column-width="1.3in"  />
									<fo:table-column column-width="7.6in"  />
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell>
													<fo:block font-size="9px">City*:</fo:block>
												</fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Georgia" font-size="9px"><xsl:value-of select="$city" /></fo:block> 
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
								</fo:table>	
							</fo:table-cell> 
						</fo:table-row> 
						
						<fo:table-row> 	
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:table>					
									<fo:table-column column-width="1.3in"  />
									<fo:table-column column-width="7.6in"  />
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell>
													<fo:block font-size="9px">County:</fo:block>
												</fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Georgia" font-size="9px"><xsl:value-of select="$county" /></fo:block> 
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
								</fo:table>	
							</fo:table-cell> 
						</fo:table-row> 
						
						<fo:table-row> 	
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:table>					
									<fo:table-column column-width="1.3in"  />
									<fo:table-column column-width="7.6in"  />
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell>
													<fo:block font-size="9px">State*:</fo:block>
												</fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Georgia" font-size="9px"><xsl:value-of select="$state" /></fo:block> 
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
								</fo:table>	
							</fo:table-cell> 
						</fo:table-row> 
					</fo:table-body> 
				</fo:table> 
			</fo:table-cell> 
		</fo:table-row> 
		<fo:table-row> 
			<fo:table-cell hyphenate="true" language="en" 
				number-columns-spanned="2" padding-start="0pt" padding-end="0pt" 
				padding-before="1pt" padding-after="1pt" display-align="before" 
				text-align="start" border-style="solid" border-width="0pt" 
				border-color="white"> 
				<fo:table width="100%" space-before.optimum="0pt" 
					space-after.optimum="0pt" table-layout="fixed"> 
					<fo:table-column column-width="proportional-column-width(2)" /> 
					<fo:table-column column-width="proportional-column-width(2)" /> 
					<fo:table-column column-width="proportional-column-width(1)" /> 
					<fo:table-column column-width="proportional-column-width(1)" /> 
					<fo:table-body> 
						<fo:table-row> 
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:table>					
									<fo:table-column column-width="1.3in"  />
									<fo:table-column column-width="7.6in"  />
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell>
													<fo:block font-size="9px">Province:</fo:block>
												</fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Georgia" font-size="9px"><xsl:value-of select="$province" /></fo:block> 
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
								</fo:table>	
							</fo:table-cell> 
						</fo:table-row> 
						
						<fo:table-row> 	
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:table>					
									<fo:table-column column-width="1.3in"  />
									<fo:table-column column-width="7.6in"  />
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell>
													<fo:block font-size="9px">Country*:</fo:block>
												</fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Georgia" font-size="9px"><xsl:value-of select="$country" /></fo:block> 
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
								</fo:table>	
							</fo:table-cell> 
						</fo:table-row> 
		
						<fo:table-row> 	
							<fo:table-cell hyphenate="true" language="en" 
								padding-start="0pt" padding-end="0pt" padding-before="1pt" 
								padding-after="1pt" display-align="before" text-align="left" 
								border-style="solid" border-width="0pt" border-color="white"> 
								<fo:table>					
									<fo:table-column column-width="1.3in"  />
									<fo:table-column column-width="7.6in"  />
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell>
													<fo:block font-size="9px">ZIP / Postal Code*:</fo:block>
												</fo:table-cell>
												<fo:table-cell>
													<fo:block font-family="Georgia" font-size="9px"><xsl:value-of select="$zip" /></fo:block> 
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
								</fo:table>	
							</fo:table-cell> 
						</fo:table-row> 
					</fo:table-body> 
				</fo:table> 
			</fo:table-cell> 
		</fo:table-row> 
	</xsl:template> 
 
	<!-- ============================================= --> 
	<!-- PRINT NAME --> 
	<!-- Prints name in the usual format --> 
	<!-- ============================================= --> 
	<xsl:template name="printName"> 
		<xsl:param name="prefix" /> 
		<xsl:param name="first" /> 
		<xsl:param name="middle" /> 
		<xsl:param name="last" /> 
		<xsl:param name="suffix" /> 
		<xsl:if test="$prefix != ''"> 
			<xsl:value-of select="$prefix" /> 
			&#160; 
		</xsl:if> 
		<xsl:value-of select="$first" /> 
		&#160; 
		<xsl:if test="$middle != ''"> 
			<xsl:value-of select="$middle" /> 
			&#160; 
		</xsl:if> 
		<xsl:value-of select="$last" /> 
		&#160; 
		<xsl:if test="$suffix != ''"> 
			<xsl:value-of select="$suffix" /> 
			&#160; 
		</xsl:if> 
	</xsl:template> 
 
	<!-- ============================================= --> 
	<!-- PRINT NAME FORMATTED --> 
	<!-- Prints name in the same format as the XFD --> 
	<!-- ============================================= --> 
	<xsl:template name="printNameFormatted"> 
		<xsl:param name="prefix" /> 
		<xsl:param name="first" /> 
		<xsl:param name="middle" /> 
		<xsl:param name="last" /> 
		<xsl:param name="suffix" /> 
		<fo:table width="100%" space-before.optimum="0pt" 
			space-after.optimum="0pt" table-layout="fixed"> 
			<fo:table-column column-width="proportional-column-width(1)" /> 
			<fo:table-column column-width="proportional-column-width(3)" /> 
			<fo:table-column column-width="proportional-column-width(2)" /> 
			<fo:table-column column-width="proportional-column-width(3)" /> 
			<fo:table-column column-width="proportional-column-width(1)" /> 
			<fo:table-body> 
				<fo:table-row> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px">Prefix:</fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px">First Name*:</fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px">Middle Name:</fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px">Last Name*:</fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px">Suffix:</fo:block> 
					</fo:table-cell> 
				</fo:table-row> 
				<fo:table-row> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px" font-family="Georgia"> 
							<xsl:value-of select="$prefix" /> 
						</fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px" font-family="Georgia"> 
							<xsl:value-of select="$first" /> 
						</fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px" font-family="Georgia"> 
							<xsl:value-of select="$middle" /> 
						</fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px" font-family="Georgia"> 
							<xsl:value-of select="$last" /> 
						</fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px" font-family="Georgia"> 
							<xsl:value-of select="$suffix" /> 
						</fo:block> 
					</fo:table-cell> 
				</fo:table-row> 
			</fo:table-body> 
		</fo:table> 
	</xsl:template> 
 
	<!-- ============================================= --> 
	<!-- PRINT CONTACT INFO --> 
	<!-- Prints phone, fax, and email information. --> 
	<!-- ============================================= --> 
	<xsl:template name="printContactInfo"> 
		<xsl:param name="phone" /> 
		<xsl:param name="fax" /> 
		<xsl:param name="email" /> 
		<fo:table width="100%" space-before.optimum="0pt" 
			space-after.optimum="0pt" table-layout="fixed"> 
			<fo:table-column column-width="proportional-column-width(1)" /> 
			<fo:table-column column-width="proportional-column-width(1)" /> 
			<fo:table-column column-width="proportional-column-width(1)" /> 
			<fo:table-body> 
				<fo:table-row> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px"> 
							Phone Number*: 
							<fo:inline font-family="Georgia"><xsl:value-of select="$phone" /></fo:inline> 
						</fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px"> 
							Fax Number: 
							<fo:inline font-family="Georgia"><xsl:value-of select="$fax" /></fo:inline> 
						</fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px"> 
							Email*: 
							<fo:inline font-family="Georgia"><xsl:value-of select="$email" /></fo:inline> 
						</fo:block> 
					</fo:table-cell> 
				</fo:table-row> 
			</fo:table-body> 
		</fo:table> 
	</xsl:template> 
	<!-- ============================================= --> 
	<!-- PRINT CONTACT INFO WITHOUT * --> 
	<!-- Prints phone, fax, and email information. --> 
	<!-- ============================================= --> 
	<xsl:template name="printContactInfo2"> 
		<xsl:param name="phone" /> 
		<xsl:param name="fax" /> 
		<xsl:param name="email" /> 
		<fo:table width="100%" space-before.optimum="0pt" 
			space-after.optimum="0pt" table-layout="fixed"> 
			<fo:table-column column-width="proportional-column-width(1)" /> 
			<fo:table-column column-width="proportional-column-width(1)" /> 
			<fo:table-column column-width="proportional-column-width(1)" /> 
			<fo:table-body> 
				<fo:table-row> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px"> 
							Phone Number*: 
							<fo:inline font-family="Georgia"><xsl:value-of select="$phone" /></fo:inline> 
						</fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px"> 
							Fax Number: 
							<fo:inline font-family="Georgia"><xsl:value-of select="$fax" /></fo:inline> 
						</fo:block> 
					</fo:table-cell> 
					<fo:table-cell hyphenate="true" language="en" 
						padding-start="0pt" padding-end="0pt" padding-before="1pt" 
						padding-after="1pt" display-align="before" text-align="left" 
						border-style="solid" border-width="0pt" border-color="white"> 
						<fo:block font-size="9px"> 
							Email: 
							<fo:inline font-family="Georgia"><xsl:value-of select="$email" /></fo:inline> 
						</fo:block> 
					</fo:table-cell> 
				</fo:table-row> 
			</fo:table-body> 
		</fo:table> 
	</xsl:template> 
 
 
	<!-- ============================================= --> 
	<!-- ADD BLANK LINES --> 
	<!-- Adds blank lines to fill up page space. --> 
	<!-- ============================================= --> 
	<xsl:template name="addBlankLines"> 
		<xsl:param name="numLines" /> 
		<xsl:if test="$numLines &gt; 0"> 
			<fo:block> 
				<fo:leader leader-pattern="space" /> 
			</fo:block> 
			<xsl:call-template name="addBlankLines"> 
				<xsl:with-param name="numLines" select="$numLines - 1" /> 
			</xsl:call-template> 
		</xsl:if> 
	</xsl:template> 
 
	<!-- ============================================= --> 
	<!-- CHECKBOX --> 
	<!-- Print out a checkbox according to value. --> 
	<!-- ============================================= --> 
	<xsl:template name="checkbox"> 
		<xsl:param name="value" /> 
		<xsl:param name="check">Y: Yes</xsl:param> 
		<xsl:choose> 
			<xsl:when test="$value = $check"> 
				<fo:inline font-family="ZapfDingbats" font-size="9px">&#x25cf; 
				</fo:inline> 
			</xsl:when> 
			<xsl:otherwise> 
				<fo:inline font-family="ZapfDingbats" font-size="9px">&#x274d; 
				</fo:inline> 
			</xsl:otherwise> 
		</xsl:choose> 
	</xsl:template> 
 
	<!-- ============================================= --> 
	<!-- YES NO RADIO --> 
	<!-- Print out a radio button according to value. --> 
	<!-- ============================================= --> 
	<xsl:template name="yes_no_radio"> 
		<xsl:param name="value" /> 
		<xsl:choose> 
			<xsl:when test="$value = 'N: No'"> 
				<fo:inline font-family="ZapfDingbats" font-size="9px">&#x274d; 
				</fo:inline> 
				<fo:inline font-size="9px">&#160;Yes&#160;&#160;</fo:inline> 
				<fo:inline font-family="ZapfDingbats" font-size="9px">&#x25cf; 
				</fo:inline> 
				<fo:inline font-size="9px">&#160;No&#160;&#160;</fo:inline> 
			</xsl:when> 
			<xsl:when test="$value = 'Y: Yes'"> 
				<fo:inline font-family="ZapfDingbats" font-size="9px">&#x25cf; 
				</fo:inline> 
				<fo:inline font-size="9px">&#160;Yes&#160;&#160;</fo:inline> 
				<fo:inline font-family="ZapfDingbats" font-size="9px">&#x274d; 
				</fo:inline> 
				<fo:inline font-size="9px">&#160;No&#160;&#160;</fo:inline> 
			</xsl:when> 
			<!-- Use this if production box doesn't have ZapfDingbats font. <xsl:when  
				test="$value = 'No'"> <fo:inline font-family="Courier" font-size="10pt">&#x2022;</fo:inline>  
				<fo:inline font-size="9px">&#160;No&#160;&#160;</fo:inline> <fo:inline font-family="Courier"  
				font-size="10pt">&#160;</fo:inline> <fo:inline font-size="9px">&#160;Yes</fo:inline>  
				</xsl:when> <xsl:when test="$value = 'Yes'"> <fo:inline font-family="Courier"  
				font-size="10pt">&#160;</fo:inline> <fo:inline font-size="9px">&#160;No&#160;&#160;</fo:inline>  
				<fo:inline font-family="Courier" font-size="10pt">&#x2022;</fo:inline> <fo:inline  
				font-size="8pt">&#160;Yes</fo:inline> </xsl:when> --> 
		</xsl:choose> 
	</xsl:template> 
 
</xsl:stylesheet> 

