<?xml version="1.0" encoding="UTF-8"?>
<structure version="2" schemafile="C:\Proposal printing\schemas\phs398schema.xsd" workingxmlfile="C:\Proposal printing\xml files\nsfproposal.xml" templatexmlfile="">
	<nspair prefix="n1" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
	<nspair prefix="n2" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
	<nspair prefix="n3" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
	<template>
		<styles font-family="Verdana" font-size="8pt" line-height="14pt"/>
		<match overwrittenxslmatch="/"/>
		<children>
			<table>
				<properties border="0" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<styles padding="0"/>
										<children>
											<table>
												<properties border="0" table-layout="fixed" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles line-height="8pt" padding="0"/>
																		<properties valign="top" width="30%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles line-height="8pt" padding="0"/>
																				<children>
																					<text fixtext="DOE F 1600.5">
																						<styles font-size="6pt"/>
																					</text>
																				</children>
																			</paragraph>
																			<paragraph paragraphtag="p">
																				<styles line-height="8pt" margin="0pt" padding="0"/>
																				<children>
																					<text fixtext="(06-94)">
																						<styles font-size="6pt"/>
																					</text>
																				</children>
																			</paragraph>
																			<paragraph paragraphtag="p">
																				<styles line-height="8pt" padding="0"/>
																				<children>
																					<text fixtext="OMB Control No. 1910-0400">
																						<styles font-size="6pt"/>
																					</text>
																				</children>
																			</paragraph>
																			<text fixtext="All Other Editions Are Obsolet">
																				<styles font-size="6pt"/>
																			</text>
																			<text fixtext="e"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="70%"/>
																	</tablecol>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties align="center" valign="top"/>
										<children>
											<paragraph paragraphtag="p">
												<styles padding-top="0"/>
												<children>
													<text fixtext="U.S. Department of Energy">
														<styles font-weight="bold" line-height="5pt"/>
													</text>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="Assurance of Compliance">
														<styles font-weight="bold" line-height="5pt"/>
													</text>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="Nondiscrimination In Federally Assisted Programs">
														<styles font-weight="bold" line-height="5pt"/>
													</text>
												</children>
											</paragraph>
											<text fixtext="0113 Burden Disclosure Statement"/>
											<newline/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-top="0"/>
										<properties valign="top"/>
										<children>
											<text fixtext="Public reporting burden for this collection of information is estimated to average 15 minutes per response, including the time for reviewing instructions, searching existing data sources. gathering and maintaining the data needed. and completing and reviewing the collection of information. Send comments regarding this burden estimate or any other aspect of this collection of information, including suggestions for reducing this burden, to Office of Information Resources Management Policy, Plans, and Oversight, HR-4.3, Paperwork Reduction Project (1910-0400), U.S. Department of Energy, 1000 Independence Avenue, S.W., Washington, DC 20585; and to the Office of Management and Budget (OMB). Paperwork Reduction Project (1910-0400). Washington. DC 20503.">
												<styles font-size="6pt"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties valign="top"/>
										<children>
											<text fixtext="  "/>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="n1:ResearchCoverPage"/>
														<children>
															<template>
																<match match="ApplicantOrganization"/>
																<children>
																	<template>
																		<match match="OrganizationName"/>
																		<children>
																			<xpath allchildren="1">
																				<styles font-weight="bold" text-decoration="underline"/>
																			</xpath>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
											<text fixtext='(Hereinafter called the "Applicant") HEREBY AGREES to comply with Title VI of the Civil Rights Act of 1964 (Pub. L. 88-352). Section 16 of the Federal Energy Administration Act of 1974 (Pub. L. 93-275), Section 401 of the Energy Reorganization Act of 1974 (Pub. L. 93-438). Title IX of the Education Amendments of 1972, as amended. (Pub. L. 92-318. Pub. L. 93-568. and Pub. L. 94-482). Section 504 of the Rehabilitation Act of 1973 (Pub. L. 93-112). the Age Discrimination Act of 1975 (Pub. L,. 94-135), Title VIII of the Civil Rights Act of 1968 (Pub. L.90-284), the Department of Energy Organization Act of 1977 (Pub. L. 95-91), the Energy Conservation and Production Act of 1976. as amended, (Pub. L. 94-385) and Title 10. Code of Federal Regulations. Part 1040. In accordance with the above laws and regulations issued pursuant thereto, the Applicant agrees to assure that no person in the United States shall, on the ground of race, color, national origin, sex, age, or disability, be excluded from participation in. he denied the benefits of, or be otherwise subjected to discrimination under any program or activity in which the Applicant receives Federal assistance from the Department of Energy.'/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<text fixtext="Applicability and Period of Obligation">
												<styles font-weight="bold"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<text fixtext="In the case of any service, financial aid, covered employment, equipment, property, or structure provided, leased, or improved with Federal assistance extended to the Applicant by the Department of Energy, this assurance obligates the Applicant for the period during which Federal assistance is extended. In the ease of any transfer of such service, financial aid, equipment, property, or structure, this assurance obligates the transferee for the period during which Federal assistance is extended. If any personal property is so provided, this assurance obligates the Applicant for the period during which it retains ownership or possession of the property. In all other cases, this assurance obligates the Applicant for the period during which the Federal assistance is extended to the Applicant by the Department of Energy."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<text fixtext="Employment Practices">
												<styles font-weight="bold"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<text fixtext="Where a primary objective of the federal assistance is to provide employment or where the Applicant's employment practices affect the delivery of services in programs or activities resulting from Federal assistance extended by the Department, the Applicant agrees not to discriminate on the ground of race, color, national origin, sex, age, or disability, in its employment practices. Such employment practices may include, but are not limited to, recruitment, advertising, hiring, layoff or termination, promotion, demotion, transfer, rates of pay, training and participation in upward mobility programs; or other forms of compensation and use of facilities."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<text fixtext="Subrecipient Assurance">
												<styles font-weight="bold"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<text fixtext="The Applicant shall require any individual, organization, or other entity with whom it subcontracts, subgrants, or subleases for the purpose of providing any service, financial aid, equipment, property, or structure to comply with laws and regulations cited above. To this end, the subrecipient shall be required to sign a written assurance form; however, the obligation of both recipient and subrecipient to ensure compliance is not relieved by the collection or submission of written assurance forms."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<text fixtext="Data Collection and Access to Records">
												<styles font-weight="bold"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<text fixtext="The Applicant agrees to compile and maintain information pertaining to programs or activities developed as a result of the Applicant's receipt of Federal assistance from the Department of Energy. Such information shall include, but is not limited to the following: (1) the manner in which services are or will be provided and related data necessary for determining whether any persons are or will be denied such services on the basis of prohibited discrimination; (2) the population eligible to be served by race, color, national origin, sex, age, and disability; (3) data regarding covered employment including use or planned use of bilingual public contact employees serving beneficiaries of the program where necessary to permit effective participation by beneficiaries unable to speak or understand English; (4) the location of existing or proposed facilities connected with the program and related information adequate for"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
						</children>
					</tablebody>
				</children>
			</table>
		</children>
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.5in" papermarginleft="0.4in" papermarginright="0.4in" papermargintop="0.5in" paperwidth="8.5in"/>
	</pagelayout>
</structure>
