<?xml version="1.0" encoding="UTF-8"?>
<structure version="2" schemafile="C:\Proposal printing\schemas\phs398schema.xsd" workingxmlfile="C:\Proposal printing\xml files\nihproposal.xml" templatexmlfile="">
	<nspair prefix="n1" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
	<nspair prefix="n2" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
	<nspair prefix="n3" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
	<template>
		<styles font-family="Verdana" font-size="8pt" line-height="13pt"/>
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
										<children>
											<text fixtext="   (e) Notifying the agency in writing, within ten calendar days after receiving notice under subparagraph (d)(2) from an employee or otherwise receiving actual notice of such conviction. Employers of convicted employees must provide notice, including position title, to every grant officer or other designee on whose grant activity the convicted employee was working, unless the Federal agency has designated a. central point for receipt of such notices. Notice shall include the identification number(s) of each affected Grant;"/>
										</children>
									</tablecol>
									<tablecol>
										<properties valign="top"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="Place of Performance	(Street address, city, county, state, zip code) "/>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<styles padding-top="0pt"/>
												<children>
													<newline/>
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
											<line>
												<properties color="black" size="1"/>
											</line>
											<paragraph paragraphtag="p">
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
																				<match match="OrganizationAddress"/>
																				<children>
																					<paragraph paragraphtag="p">
																						<children>
																							<text fixtext="  "/>
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
																							<text fixtext="  "/>
																							<template>
																								<match match="MailStopCode"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																						</children>
																					</paragraph>
																					<text fixtext="  "/>
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
											</paragraph>
											<line>
												<properties color="black" size="1"/>
											</line>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<text fixtext="   (f) Taking one of the following actions, within 30 calendar days of receiving notice under subparagraph (d)(2), with respect to any employee who is so convicted -"/>
										</children>
									</tablecol>
									<tablecol>
										<properties valign="top"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="Check  __  if there are workplaces on file that are not identified here."/>
													<newline/>
													<newline/>
												</children>
											</paragraph>
											<newline/>
											<line>
												<properties color="black" size="1"/>
											</line>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<text fixtext="     (1) Taking appropriate personnel action against such an employee, up to and including termination, consistent with the requirements of the Rehabilitation Act of 1973, as amended; or"/>
										</children>
									</tablecol>
									<tablecol>
										<properties rowspan="3" valign="top"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="Alternate I. (Grantees Who Are individuals)"/>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="1.	 The grantee certified that, as a condition of the grant, he or she will not engage in the unlawful manufacture, distribution, dispensing, possession, use of a controlled substance in conducting any activity with the grant;"/>
												</children>
											</paragraph>
											<text fixtext="2.	 If convicted of a criminal drug offense resulting from a violation occurring during the conduct of any grant activity, he or she will report the conviction in writing, within If) calendar days of the conviction, to every grant office or other designee, unless the Federal agency designates a. central point for the receipt of such notices. When notice is made to such a central point, it shall include the identification number(s) of each affected grant."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<text fixtext="     (2) Requiring such employee to participate satisfactorily in a drug abuse assistance or rehabilitation program approved for such purposes by a Federal, State, or local health, law enforcement, or other appropriate agency;"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<text fixtext="   (g) Making a good faith effort to continue to maintain a drug-free workplace through implementation of paragraphs (a.), (b), (c), (d), (e) and (f)."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<text fixtext="B. The grantee may insert in the space provided below the site(s) for the performance of work done in connection with the specific grant:"/>
										</children>
									</tablecol>
									<tablecol/>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties colspan="2"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<newline/>
													<newline/>
													<line>
														<properties color="black" size="1"/>
													</line>
												</children>
											</paragraph>
											<text fixtext="As the duly authorized representative of the applicant, I hereby make the above certifications on behalf of the applicant."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding="0pt"/>
										<properties colspan="2" height="260"/>
										<children>
											<table>
												<properties border="1" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<properties height="87" valign="top" width="55%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="NAME OF APPLICANT, TAXPAYER IDENTIFICATION NUMBER (TIN) &amp; CAGE CODE"/>
																				</children>
																			</paragraph>
																			<paragraph paragraphtag="p">
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
																			<paragraph paragraphtag="p">
																				<children>
																					<newline/>
																					<newline/>
																					<text fixtext="TIN">
																						<styles font-weight="bold"/>
																					</text>
																					<text fixtext=":  "/>
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
																												<match match="OrganizationTIN"/>
																												<children>
																													<xpath allchildren="1">
																														<styles text-decoration="underline"/>
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
																			<newline/>
																			<newline/>
																			<text fixtext="CAGE">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext=":"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties colspan="2" height="87" valign="top" width="20%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="RESEARCH TITLE"/>
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
																									<xpath allchildren="1"/>
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
																		<properties colspan="2" width="55%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="PRINTED NAME, TITLE AND SIGNATURE OF AUTHORIZED REPRESENTATIVE"/>
																				</children>
																			</paragraph>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="  "/>
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
																			</paragraph>
																			<text fixtext="  "/>
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
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="  "/>
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
																												<match match="OrganizationalOfficialSignature"/>
																												<children>
																													<template>
																														<match match="SignatureAuthentication"/>
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
																			</paragraph>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties valign="top" width="25%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="DATE"/>
																				</children>
																			</paragraph>
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
																										<match match="OrganizationalOfficialSignature"/>
																										<children>
																											<template>
																												<match match="SignatureDate"/>
																												<children>
																													<xpath allchildren="1">
																														<format string="MM/DD/YYYY" xslt="1"/>
																													</xpath>
																													<datepicker ownvalue="1"/>
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
															<tablerow>
																<children>
																	<tablecol>
																		<properties colspan="3" height="82" valign="top" width="55%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="PROPOSAL NUMBER:"/>
																					<newline/>
																					<newline/>
																				</children>
																			</paragraph>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="PRINCIPAL INVESTIGATOR:    "/>
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
																															<xpath allchildren="1"/>
																														</children>
																													</template>
																													<text fixtext=" "/>
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
																			</paragraph>
																			<newline/>
																			<text fixtext="NEGOTIATOR:"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-style="none"/>
																		<properties height="1" width="55%"/>
																	</tablecol>
																	<tablecol>
																		<styles border-style="none"/>
																		<properties height="1" width="20%"/>
																	</tablecol>
																	<tablecol>
																		<styles border-style="none"/>
																		<properties height="1" width="25%"/>
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
						</children>
					</tablebody>
				</children>
			</table>
		</children>
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.5in" papermarginleft="0.4in" papermarginright="0.4in" papermargintop="0.5in" paperwidth="8.5in"/>
		<footerall>
			<template>
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
												<styles font-size="smaller" padding="0"/>
												<properties align="left"/>
												<children>
													<text fixtext="March 2000">
														<styles font-family="Verdana" font-size="7pt"/>
													</text>
												</children>
											</tablecol>
											<tablecol>
												<styles font-size="smaller" padding="0"/>
												<properties align="right" width="150"/>
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
	</pagelayout>
</structure>
