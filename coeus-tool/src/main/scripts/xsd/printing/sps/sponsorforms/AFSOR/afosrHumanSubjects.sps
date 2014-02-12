<?xml version="1.0" encoding="UTF-8"?>
<structure version="2" schemafile="C:\Proposal printing\schemas\phs398schema.xsd" workingxmlfile="C:\Proposal printing\xml files\nsfproposalwithabstracts1.xml" templatexmlfile="">
	<nspair prefix="n1" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
	<nspair prefix="n2" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
	<nspair prefix="n3" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
	<template>
		<styles font-family="Verdana" line-height="12pt"/>
		<match overwrittenxslmatch="/"/>
		<children>
			<table>
				<properties border="1" table-layout="fixed" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<properties align="center" colspan="2" width="52%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="Protection of Human Subjects">
														<styles font-weight="bold"/>
													</text>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="Assurance Identification/Certification/Declaration">
														<styles font-weight="bold"/>
													</text>
												</children>
											</paragraph>
											<text fixtext="(Common Federal Rule)">
												<styles font-weight="bold"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties valign="top" width="261"/>
										<children>
											<text fixtext="Policy: Research activities involving human subjects may not be conducted or supported by the Departments and Agencies adopting the Common Rule (56FR28003, June 18, 1991) unless the activities are exempt from or approved in accordance with the common rule. See section 101(b) the common rule for exemptions. Institutions submitting applications or proposals for support must submit certification or appropriate Institutional Review Board (IRB) review and approval to the Department or Agency in accordance with the common rile.">
												<styles font-size="7pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<properties valign="top" width="52%"/>
										<children>
											<text fixtext="Institutions with an assurance of compliance that covers the research to be conducted on file with the Department: Agency, or the Department of Health and Human Services (HHS) should submit certification of IRB review and approval with each application or proposal unless otherwise advised by the Department or Agency. Institutions which do not have such an assurance must submit an assurance and certification of IRB review and approval within 30 days of a written request from the Department or Agency.
3. Name of Federal Department or Agency and, if known, Application or Proposal Identification No.">
												<styles font-size="7pt"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding="0pt"/>
										<properties colspan="2" height="67" width="261"/>
										<children>
											<table>
												<properties border="1" table-layout="fixed" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-left-style="none" border-top-style="none"/>
																		<properties valign="top" width="20%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles border-left-style="none" border-top-style="none"/>
																				<children>
																					<text fixtext="1. Request Type">
																						<styles font-size="7pt"/>
																					</text>
																				</children>
																			</paragraph>
																			<paragraph paragraphtag="p">
																				<styles border-left-style="none" border-top-style="none"/>
																				<children>
																					<text fixtext="__">
																						<styles font-size="8pt"/>
																					</text>
																					<text fixtext="  ORIGINAL">
																						<styles font-size="8pt"/>
																					</text>
																				</children>
																			</paragraph>
																			<paragraph paragraphtag="p">
																				<styles border-left-style="none" border-top-style="none"/>
																				<children>
																					<text fixtext="__  FOLLOWUP">
																						<styles font-size="8pt"/>
																					</text>
																				</children>
																			</paragraph>
																			<text fixtext="__  EXEMPTION">
																				<styles font-size="8pt"/>
																			</text>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-top-style="none"/>
																		<properties valign="top" width="40%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles border-top-style="none"/>
																				<children>
																					<text fixtext="2. Type of Mechanism">
																						<styles font-size="7pt"/>
																					</text>
																				</children>
																			</paragraph>
																			<paragraph paragraphtag="p">
																				<styles border-top-style="none"/>
																				<children>
																					<text fixtext="__ ">
																						<styles font-size="8pt"/>
																					</text>
																					<text fixtext=" GRANT     ">
																						<styles font-size="8pt"/>
																					</text>
																					<text fixtext="__ ">
																						<styles font-size="8pt"/>
																					</text>
																					<text fixtext=" CONTRACT     __  FELLOWSHIP">
																						<styles font-size="8pt"/>
																					</text>
																				</children>
																			</paragraph>
																			<paragraph paragraphtag="p">
																				<styles border-top-style="none"/>
																				<children>
																					<text fixtext="__  COOPERATIVE AGREEMENT">
																						<styles font-size="8pt"/>
																					</text>
																				</children>
																			</paragraph>
																			<text fixtext="__  OTHER:">
																				<styles font-size="8pt"/>
																			</text>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-top-style="none"/>
																		<properties valign="top" width="40%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles border-right-style="none" border-top-style="none"/>
																				<children>
																					<text fixtext="3. Name of Federal Department or Agency and, if known, Application or Proposal Identification No.">
																						<styles font-size="7pt"/>
																					</text>
																				</children>
																			</paragraph>
																			<paragraph paragraphtag="p">
																				<styles border-top-style="none"/>
																				<children>
																					<template>
																						<match match="n1:ResearchAndRelatedProject"/>
																						<children>
																							<template>
																								<match match="n1:ResearchCoverPage"/>
																								<children>
																									<template>
																										<match match="FederalAgencyReceiptQualifiers"/>
																										<children>
																											<template>
																												<match match="AgencyName"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-size="8pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</paragraph>
																			<template>
																				<match match="n1:ResearchAndRelatedProject"/>
																				<children>
																					<template>
																						<match match="n1:ResearchCoverPage"/>
																						<children>
																							<template>
																								<match match="ApplicantSubmissionQualifiers"/>
																								<children>
																									<template>
																										<match match="ApplicationIdentifier"/>
																										<children>
																											<xpath allchildren="1">
																												<styles font-size="8pt"/>
																											</xpath>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-bottom-style="none" border-left-style="none"/>
																		<properties colspan="2" valign="top" width="20%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles border-bottom-style="none" border-left-style="none"/>
																				<children>
																					<text fixtext="4. Title of Application or Activity">
																						<styles font-size="7pt"/>
																					</text>
																				</children>
																			</paragraph>
																			<template>
																				<match match="n1:ResearchAndRelatedProject"/>
																				<children>
																					<template>
																						<match match="n1:ResearchCoverPage"/>
																						<children>
																							<template>
																								<match match="ProjectTitle"/>
																								<children>
																									<xpath allchildren="1">
																										<styles font-size="8pt"/>
																									</xpath>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-bottom-style="none"/>
																		<properties valign="top" width="40%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles border-bottom-style="none" border-right-style="none"/>
																				<children>
																					<text fixtext="5. Name of Principal Investigator, Program Director, Fellow, or Other">
																						<styles font-size="7pt"/>
																					</text>
																				</children>
																			</paragraph>
																			<template>
																				<match match="n1:ResearchAndRelatedProject"/>
																				<children>
																					<template>
																						<match match="n1:ResearchCoverPage"/>
																						<children>
																							<template>
																								<match match="n1:ProgramDirectorPrincipalInvestigator"/>
																								<children>
																									<template>
																										<match match="Name"/>
																										<children>
																											<template>
																												<match match="FirstName"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="8pt"/>
																													</xpath>
																												</children>
																											</template>
																											<text fixtext=" ">
																												<styles font-family="Verdana" font-size="8pt"/>
																											</text>
																											<template>
																												<match match="MiddleName"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="8pt"/>
																													</xpath>
																													<text fixtext=" "/>
																												</children>
																											</template>
																											<template>
																												<match match="LastName"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="8pt"/>
																													</xpath>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
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
										<styles border-bottom-style="none"/>
										<properties colspan="2" height="8" width="261"/>
										<children>
											<text fixtext="6. Assurance Status of this Project (Respond to one of the following)">
												<styles font-size="7pt"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-style="none" border-top-style="none" font-size="7pt"/>
										<properties colspan="2" width="261"/>
										<children>
											<paragraph paragraphtag="p">
												<styles border-bottom-style="none" border-top-style="none"/>
												<children>
													<newline/>
													<text fixtext="__  This Assurance, on file with Department of Health and Human Services, covers this activity: "/>
												</children>
											</paragraph>
											<text fixtext="Assurance identification no. _______        IRB identification no. ___________________"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-style="none" border-top-style="none" font-size="7pt"/>
										<properties colspan="2" width="261"/>
										<children>
											<paragraph paragraphtag="p">
												<styles border-bottom-style="none" border-top-style="none"/>
												<children>
													<newline/>
													<text fixtext="__  This Assurance, on file with (agency/dept)	____________________, covers this activity."/>
												</children>
											</paragraph>
											<text fixtext="Assurance identification no.	_______       IRB identification no.___________________	(if applicable)"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-style="none" border-top-style="none" font-size="7pt"/>
										<properties colspan="2" width="261"/>
										<children>
											<newline/>
											<text fixtext="__  No assurance has been filed for this project. This institution declares that it will provide an Assurance and Certification of IRB review and approval upon request."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-top-style="none" font-size="7pt"/>
										<properties colspan="2" width="261"/>
										<children>
											<newline/>
											<text fixtext="__  Exemption Status: Human subjects are involved, but this activity qualifies for exemption under Section 101(b), paragraph	"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-width="thick" border-top-width="thick" font-size="7pt"/>
										<properties colspan="2" width="261"/>
										<children>
											<paragraph paragraphtag="p">
												<styles border-bottom-width="thick" border-top-width="thick"/>
												<children>
													<text fixtext="7. Certification of IRB Review (Respond to one of the following IF you have an Assurance on file)"/>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<styles border-bottom-width="thick" border-top-width="thick"/>
												<children>
													<newline/>
													<text fixtext="__  This activity has been reviewed and approved by the IRB in accordance with the common rule and any other governing regulations or subparts on "/>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<styles border-bottom-width="thick" border-top-width="thick"/>
												<children>
													<text fixtext="(date)	_____ by: __  Full IRB Review or __  Expedited Review"/>
												</children>
											</paragraph>
											<newline/>
											<text fixtext="__  This activity contains multiple projects, some of which have not been reviewed. The IRB has granted approval on condition that all projects covered by the common rule will be reviewed and approved before they are initiated and that appropriate further certification will be submitted."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-width="thick" font-size="7pt"/>
										<properties colspan="2" height="48" width="261"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="8. Comments"/>
												</children>
											</paragraph>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding="0"/>
										<properties height="65" width="261"/>
										<children>
											<table>
												<properties border="1" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-left-style="none" border-right-style="none" border-top-style="none" font-size="7pt"/>
																		<properties colspan="2"/>
																		<children>
																			<text fixtext="9 The official signing below certifies that the information provided above is correct and that, as required, future reviews will be performed and certification will be provided."/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-bottom-style="none" border-left-style="none" font-size="7pt"/>
																		<properties align="left"/>
																		<children>
																			<text fixtext="11. Phone No. (with area code)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-bottom-style="none" border-right-style="none" font-size="7pt"/>
																		<children>
																			<text fixtext="12. Fax No. (with area code)"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-bottom-style="none" border-left-style="none" border-top-style="none" font-size="7pt"/>
																		<properties align="center" height="15"/>
																		<children>
																			<template>
																				<match match="n1:ResearchAndRelatedProject"/>
																				<children>
																					<template>
																						<match match="n1:ResearchCoverPage"/>
																						<children>
																							<template>
																								<match match="AuthorizedOrganizationalRepresentative"/>
																								<children>
																									<template>
																										<match match="ContactInformation"/>
																										<children>
																											<template>
																												<match match="PhoneNumber"/>
																												<children>
																													<xpath allchildren="1"/>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-bottom-style="none" border-right-style="none" border-top-style="none" font-size="7pt"/>
																		<properties align="center" height="15"/>
																		<children>
																			<template>
																				<match match="n1:ResearchAndRelatedProject"/>
																				<children>
																					<template>
																						<match match="n1:ResearchCoverPage"/>
																						<children>
																							<template>
																								<match match="AuthorizedOrganizationalRepresentative"/>
																								<children>
																									<template>
																										<match match="ContactInformation"/>
																										<children>
																											<template>
																												<match match="FaxNumber"/>
																												<children>
																													<xpath allchildren="1"/>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
										</children>
									</tablecol>
									<tablecol>
										<styles padding="0"/>
										<properties height="65" width="52%"/>
										<children>
											<table>
												<properties border="1" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-bottom-style="none" border-left-style="none" border-top-style="none" font-size="7pt" line-height="4pt" padding-top="0pt"/>
																		<properties height="10" width="52%"/>
																		<children>
																			<text fixtext="10. Name and Address of Institution">
																				<styles line-height="4pt"/>
																			</text>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-bottom-style="none" border-left-style="none" border-right-style="none" border-top-style="none" font-size="7pt" padding-left="12pt"/>
																		<properties valign="top" width="261"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles padding-top="0pt"/>
																				<children>
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
																													<xpath allchildren="1"/>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</paragraph>
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
																										<match match="OrganizationAddress"/>
																										<children>
																											<paragraph paragraphtag="p">
																												<children>
																													<template>
																														<match match="Street"/>
																														<children>
																															<xpath allchildren="1"/>
																														</children>
																													</template>
																												</children>
																											</paragraph>
																											<paragraph paragraphtag="p">
																												<children>
																													<template>
																														<match match="MailStopCode"/>
																														<children>
																															<xpath allchildren="1"/>
																														</children>
																													</template>
																												</children>
																											</paragraph>
																											<template>
																												<match match="City"/>
																												<children>
																													<xpath allchildren="1"/>
																													<text fixtext=", "/>
																												</children>
																											</template>
																											<template>
																												<match match="State"/>
																												<children>
																													<xpath allchildren="1"/>
																													<text fixtext=" "/>
																												</children>
																											</template>
																											<template>
																												<match match="PostalCode"/>
																												<children>
																													<xpath allchildren="1"/>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
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
										<styles font-size="7pt"/>
										<properties width="261"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="13. Name of official "/>
												</children>
											</paragraph>
											<text fixtext="            "/>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="n1:ResearchCoverPage"/>
														<children>
															<template>
																<match match="AuthorizedOrganizationalRepresentative"/>
																<children>
																	<template>
																		<match match="Name"/>
																		<children>
																			<template>
																				<match match="FirstName"/>
																				<children>
																					<xpath allchildren="1"/>
																					<text fixtext=" "/>
																				</children>
																			</template>
																			<template>
																				<match match="MiddleName"/>
																				<children>
																					<xpath allchildren="1"/>
																					<text fixtext=" "/>
																				</children>
																			</template>
																			<template>
																				<match match="LastName"/>
																				<children>
																					<xpath allchildren="1"/>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles font-size="7pt"/>
										<properties valign="top" width="52%"/>
										<children>
											<paragraph paragraphtag="p">
												<styles font-size="7pt"/>
												<children>
													<text fixtext="14. Title"/>
												</children>
											</paragraph>
											<text fixtext="             "/>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="n1:ResearchCoverPage"/>
														<children>
															<template>
																<match match="AuthorizedOrganizationalRepresentative"/>
																<children>
																	<template>
																		<match match="PositionTitle"/>
																		<children>
																			<xpath allchildren="1"/>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-size="7pt"/>
										<properties height="23" width="261"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="15. Signature"/>
												</children>
											</paragraph>
										</children>
									</tablecol>
									<tablecol>
										<styles font-size="7pt"/>
										<properties height="23" valign="top" width="52%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="16. Date"/>
												</children>
											</paragraph>
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
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.80in" papermarginleft="0.4in" papermarginright="0.4in" papermargintop="0.60in" paperwidth="8.5in"/>
		<footerall>
			<template>
				<styles font-family="Verdana"/>
				<match overwrittenxslmatch="/"/>
				<children>
					<table topdown="0">
						<properties width="100%"/>
						<children>
							<tablebody>
								<children>
									<tablerow>
										<children>
											<tablecol>
												<styles padding="0"/>
												<properties align="left"/>
												<children>
													<text fixtext="Authorized for local Reproduction                            OPTIONAL FORM 310 (Rev. 1-98">
														<styles font-size="7pt"/>
													</text>
												</children>
											</tablecol>
											<tablecol>
												<styles padding="0"/>
												<properties align="right" width="150"/>
												<children>
													<text fixtext="Sponsored by HHS/PHS/NIH">
														<styles font-size="7pt"/>
													</text>
												</children>
											</tablecol>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecol>
												<styles line-height="7pt" padding="0"/>
												<properties align="left" colspan="2" height="55"/>
												<children>
													<text fixtext="Public reporting burden for the collection of information is estimated to average less than an hour per response. An agency may not conduct or sponsor, and a person is not required to respond to, a collection of information unless it displays a valid OMB control number. Send comments regarding this burden estimate or any other aspect of this collection of information, including suggestions for reducing this burden to: MIH, Project Clearance Office, 6701 Rockledge Drive MSC 7730, Bethesda Md. 20892-7730, ATTN: PRA 0925-0418. Do not return the completed form to this address">
														<styles font-size="6pt"/>
													</text>
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
		</footerall>
		<headerall>
			<template>
				<match overwrittenxslmatch="/"/>
				<children>
					<newline/>
					<table topdown="0">
						<properties border="0" width="100%"/>
						<children>
							<tablebody>
								<children>
									<tablerow>
										<children>
											<tablecol>
												<styles line-height="6pt" margin="0" padding="0" padding-bottom="0pt"/>
												<properties align="right" colspan="2" height="38" valign="bottom"/>
												<children>
													<paragraph paragraphtag="p">
														<styles line-height="6pt" padding-bottom="0pt"/>
														<children>
															<newline/>
															<newline/>
															<newline/>
															<newline/>
															<newline/>
															<text fixtext="OMB No. 0925-0418">
																<styles font-family="Verdana" font-size="7pt"/>
															</text>
														</children>
													</paragraph>
													<text fixtext="Approved for use through 01/31/2001">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
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
		</headerall>
	</pagelayout>
</structure>
