<?xml version="1.0" encoding="UTF-8"?>
<structure version="2" schemafile="C:\Proposal printing\schemas\phs398schema.xsd" workingxmlfile="C:\Proposal printing\xml files\nsfproposal.xml" templatexmlfile="">
	<nspair prefix="n1" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
	<nspair prefix="n2" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
	<nspair prefix="n3" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
	<template>
		<styles font-family="Verdana" font-size="8pt" line-height="12pt"/>
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
										<properties colspan="2" width="70%"/>
										<children>
											<text fixtext="determining whether the location has or will have the effect of unnecessarily denying access to any person on the basis of prohibited discrimination; (5) the present or proposed membership by race, color, national origin, sex, age and disability in any planning or advisory body which is an integral part of the program; and (6) any additional written data determined by the Department of Energy to be relevant to the obligation to assure compliance by recipients with laws cited in the first paragraph of this assurance."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties colspan="2" width="70%"/>
										<children>
											<text fixtext="The Applicant agrees to submit requested data to the Department of Energy regarding programs and activities developed by the Applicant from the use of Federal assistance funds extended by the Department of Energy. Facilities of the Applicant (including the physical plants, buildings, or other structures) and all records, books, accounts, and other sources of information pertinent to the Applicant's compliance with the civil rights laws shall be made available for inspection during normal business hours on request of an officer or employee of the Department of Energy specifically authorized to make such inspections. Instructions in this regard will be provided by the Director, Office of Civil Rights, U.S. Department of Energy."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties colspan="2" width="70%"/>
										<children>
											<text fixtext="This assurance is given in consideration of and for the purpose of obtaining any and all Federal grants, loans, contracts (excluding procurement contracts), property, discounts or other Federal assistance extended after the date hereof, to the Applicants by the Department of Energy, including installment payments on account after such data of application for Federal assistance which are approved before such date. The Applicant recognizes and agrees that such Federal assistance will be extended in reliance upon the representations and agreements made in this assurance, and that the United States shall have the right to seek judicial enforcement of this assurance. This assurance is binding on the Applicant, the successors, transferees, and assignees, as well as the person(s) whose signatures appear below and who are authorized to sign this assurance on behalf of the Applicant."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties colspan="2" width="70%"/>
										<children>
											<newline/>
											<text fixtext="Applicant Certification">
												<styles font-weight="bold"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties colspan="2" width="70%"/>
										<children>
											<text fixtext="The Applicant certifies that it has complied, or that, within 90 days of the date of the grant, it will comply with all applicable requirements, of 10 C.F.R. 1040.5 (a copy will be furnished to the Applicant upon written request to DOE)."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties colspan="2" width="70%"/>
										<children>
											<text fixtext="Designated Responsible Employee">
												<styles font-weight="bold"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties width="70%"/>
										<children>
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
																				<match match="OrganizationContactPerson"/>
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
																							<template>
																								<match match="MiddleName"/>
																								<children>
																									<text fixtext="  "/>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																							<template>
																								<match match="LastName"/>
																								<children>
																									<text fixtext=" "/>
																									<xpath allchildren="1"/>
																									<text fixtext=", "/>
																								</children>
																							</template>
																						</children>
																					</template>
																					<template>
																						<match match="PositionTitle"/>
																						<children>
																							<xpath allchildren="1"/>
																						</children>
																					</template>
																					<template>
																						<match match="ContactInformation"/>
																						<children>
																							<template>
																								<match match="PhoneNumber"/>
																								<children>
																									<text fixtext="   "/>
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
													</template>
													<line>
														<properties color="black" size="0.5"/>
													</line>
												</children>
											</paragraph>
											<text fixtext="Name and Title (Printed or Typed) and Telephone Number"/>
										</children>
									</tablecol>
									<tablecol>
										<properties width="30%"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties width="70%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<newline/>
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
																				<match match="OrganizationContactPerson"/>
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
																							<template>
																								<match match="MiddleName"/>
																								<children>
																									<text fixtext="  "/>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																							<template>
																								<match match="LastName"/>
																								<children>
																									<text fixtext=" "/>
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
													</template>
													<line>
														<properties color="black" size="0.5"/>
													</line>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="Signature and Date"/>
												</children>
											</paragraph>
											<text fixtext="(please type in full name if electronically submitted)">
												<styles font-size="6pt"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<properties width="70%"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties colspan="2" width="70%"/>
										<children>
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
										<properties width="70%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<newline/>
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
																					<template>
																						<match match="MiddleName"/>
																						<children>
																							<text fixtext=" "/>
																							<xpath allchildren="1"/>
																						</children>
																					</template>
																					<template>
																						<match match="LastName"/>
																						<children>
																							<text fixtext=" "/>
																							<xpath allchildren="1"/>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext="      "/>
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
													<line>
														<properties color="black" size="0.5"/>
													</line>
												</children>
											</paragraph>
											<text fixtext="Applicant's Name and Telephone Number"/>
										</children>
									</tablecol>
									<tablecol>
										<properties width="70%"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties width="70%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<newline/>
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
																				<match match="ContactInformation"/>
																				<children>
																					<template>
																						<match match="PostalAddress"/>
																						<children>
																							<template>
																								<match match="Street"/>
																								<children>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																							<template>
																								<match match="MailStopCode"/>
																								<children>
																									<text fixtext=" "/>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																							<template>
																								<match match="City"/>
																								<children>
																									<text fixtext=" "/>
																									<xpath allchildren="1"/>
																									<text fixtext=","/>
																								</children>
																							</template>
																							<template>
																								<match match="State"/>
																								<children>
																									<text fixtext=" "/>
																									<xpath allchildren="1"/>
																								</children>
																							</template>
																							<template>
																								<match match="PostalCode"/>
																								<children>
																									<text fixtext=" "/>
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
													</template>
													<line>
														<properties color="black" size="0.5"/>
													</line>
												</children>
											</paragraph>
											<text fixtext="Address"/>
										</children>
									</tablecol>
									<tablecol>
										<properties width="70%"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties width="70%"/>
										<children>
											<newline/>
											<text fixtext=" "/>
											<newline/>
											<line>
												<properties color="black" size="0.5"/>
											</line>
											<text fixtext="Date"/>
										</children>
									</tablecol>
									<tablecol>
										<properties width="70%"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties colspan="2" width="70%"/>
										<children>
											<newline/>
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
										<properties colspan="2" width="70%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="Authorized Official:"/>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="President, Chief Executive Officer"/>
												</children>
											</paragraph>
											<text fixtext="or Authorized Designee"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties width="70%"/>
										<children>
											<paragraph paragraphtag="p">
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
																			<template>
																				<match match="PositionTitle"/>
																				<children>
																					<text fixtext="   "/>
																					<xpath allchildren="1"/>
																				</children>
																			</template>
																			<template>
																				<match match="ContactInformation"/>
																				<children>
																					<template>
																						<match match="PhoneNumber"/>
																						<children>
																							<text fixtext="     "/>
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
													<line>
														<properties color="black" size="0.5"/>
													</line>
												</children>
											</paragraph>
											<text fixtext="Name and Title (Printed or Typed) and Telephone Number"/>
										</children>
									</tablecol>
									<tablecol>
										<properties width="70%"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties width="70%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<newline/>
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
													<line>
														<properties color="black" size="0.5"/>
													</line>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="Signature and Date"/>
												</children>
											</paragraph>
											<text fixtext="(please type in full name if electronically submitted)">
												<styles font-size="6pt"/>
											</text>
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
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.5in" papermarginleft="0.4in" papermarginright="0.4in" papermargintop="0.5in" paperwidth="8.5in"/>
	</pagelayout>
</structure>
