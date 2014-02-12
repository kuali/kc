<?xml version="1.0" encoding="UTF-8"?>
<structure version="2" schemafile="C:\Proposal printing\schemas\phs398schema.xsd" workingxmlfile="C:\Proposal printing\xml files\nsfproposalwithabstracts1.xml" templatexmlfile="">
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
										<properties align="left" colspan="2" height="29" width="40%"/>
										<children>
											<text fixtext="  Submitted in response to FY 2003 DoD Multidisciplinary Research Program of the University Research Initiative BAA">
												<styles font-size="10pt"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties align="center" width="60%"/>
										<children>
											<text fixtext="APPENDIX A: PROPOSAL COVER"/>
										</children>
									</tablecol>
									<tablecol>
										<properties width="40%"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties align="center" width="60%"/>
										<children>
											<text fixtext="(This form must be completed and submitted as the cover of the proposal)"/>
										</children>
									</tablecol>
									<tablecol>
										<properties align="left" width="40%"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties width="60%"/>
									</tablecol>
									<tablecol>
										<styles padding="0"/>
										<properties width="40%"/>
										<children>
											<table>
												<properties border="0" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<properties align="right"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="PROPOSAL NUMBER:"/>
																				</children>
																			</paragraph>
																			<text fixtext="(to he completed by DoD Only)">
																				<styles font-size="7pt"/>
																			</text>
																			<text fixtext=" "/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties valign="top"/>
																		<children>
																			<newline/>
																			<newline/>
																			<newline/>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
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
										<properties valign="top" width="60%"/>
										<children>
											<text fixtext="1. THE PRINCIPAL INVESTIGATOR (One name only)"/>
										</children>
									</tablecol>
									<tablecol>
										<properties width="40%"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties height="40" width="60%"/>
										<children>
											<table>
												<properties border="0" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles line-height="12pt" padding-left="0pt" padding-right="0"/>
																		<properties align="center" height="28" valign="top" width="30%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles line-height="12pt" padding-left="0pt" padding-right="0"/>
																				<properties align="center"/>
																				<children>
																					<template>
																						<match match="n1:ResearchAndRelatedProject"/>
																						<children>
																							<template>
																								<match match="KeyPerson"/>
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
																			</paragraph>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext="(Title)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles line-height="12pt" padding-left="0" padding-right="0"/>
																		<properties align="center" height="28" valign="top" width="30%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles line-height="12pt" padding-left="0" padding-right="0"/>
																				<children>
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
																			<text fixtext="(First Name)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles line-height="12pt" padding-left="0" padding-right="0"/>
																		<properties align="center" height="28" valign="top" width="10%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles line-height="12pt" padding-left="0" padding-right="0"/>
																				<properties align="center"/>
																				<children>
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
																														<match match="MiddleName"/>
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
																			<text fixtext="(MI)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles line-height="12pt" padding-left="0" padding-right="0"/>
																		<properties align="center" height="28" valign="top" width="30%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles line-height="12pt" padding-left="0" padding-right="0"/>
																				<properties align="center"/>
																				<children>
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
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext="(Last Name)"/>
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
										<properties height="40" valign="top" width="40%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<newline/>
													<text fixtext=" "/>
												</children>
											</paragraph>
											<line>
												<properties color="black" size="1"/>
											</line>
											<text fixtext="PI Signature (please use blue ink)"/>
											<newline/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding="0"/>
										<properties width="60%"/>
										<children>
											<table>
												<properties border="0" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles padding-top="4pt"/>
																		<properties valign="top" width="60%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles padding-top="4pt"/>
																				<children>
																					<text fixtext="  "/>
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
																			</paragraph>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext="  (Phone Number)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles padding-top="4pt"/>
																		<properties valign="top" width="60%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles padding-top="4pt"/>
																				<children>
																					<text fixtext="  "/>
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
																			</paragraph>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext="      (FAX Number)"/>
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
										<properties valign="top" width="60%"/>
										<children>
											<paragraph paragraphtag="p">
												<styles padding-top="6pt"/>
												<children>
													<text fixtext="  "/>
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
																						<match match="Email"/>
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
											<text fixtext="      (E-mail address)"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties colspan="2" height="30" valign="top" width="60%"/>
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
											<text fixtext="  (Institution)"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties colspan="2" valign="top" width="60%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="  "/>
												</children>
											</paragraph>
											<line>
												<properties color="black" size="1"/>
											</line>
											<text fixtext="  (Department/Division )"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties colspan="4" valign="top" width="60%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<choice>
														<children>
															<choiceoption>
																<testexpression>
																	<xpath value="string-length( n1:ResearchAndRelatedProject/n1:ResearchCoverPage/n1:ProgramDirectorPrincipalInvestigator/ContactInformation/PostalAddress/Street  ) &lt;=0 and  string-length(  n1:ResearchAndRelatedProject/n1:ResearchCoverPage/n1:ProgramDirectorPrincipalInvestigator/ContactInformation/PostalAddress/MailStopCode &lt;= 0 )"/>
																</testexpression>
																<children>
																	<newline/>
																</children>
															</choiceoption>
														</children>
													</choice>
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
												</children>
											</paragraph>
											<line>
												<properties color="black" size="1"/>
											</line>
											<text fixtext="  (Street/PO Box/Building )"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties colspan="2" valign="top" width="60%"/>
										<children>
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
																						<match match="City"/>
																						<children>
																							<xpath allchildren="1"/>
																							<text fixtext=", "/>
																						</children>
																					</template>
																					<text fixtext=" "/>
																					<template>
																						<match match="State"/>
																						<children>
																							<xpath allchildren="1"/>
																						</children>
																					</template>
																					<template>
																						<match match="PostalCode"/>
																						<children>
																							<text fixtext="  "/>
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
												<properties color="black" size="1"/>
											</line>
											<text fixtext="  (City)         (State)        (Zip Code)"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties colspan="2" valign="top" width="60%"/>
										<children>
											<text fixtext="CURRENT DoD) CONTRACTOR OR GRANTEE: YES"/>
											<text fixtext="__">
												<styles font-weight="bold"/>
											</text>
											<text fixtext="  No"/>
											<text fixtext="__">
												<styles font-weight="bold"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding="0pt"/>
										<properties colspan="2" valign="top" width="60%"/>
										<children>
											<table>
												<properties border="0" table-layout="fixed" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles font-size="6pt"/>
																		<properties align="right" width="28%"/>
																		<children>
																			<text fixtext="If yes, give Agency. Point of Contact,. Phone Number:"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles padding-left="0pt"/>
																		<properties valign="top" width="72%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles padding-left="0pt"/>
																				<children>
																					<text fixtext=" "/>
																				</children>
																			</paragraph>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
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
										<properties colspan="4" valign="top" width="60%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="2. THE PROPOSAL:"/>
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
											</paragraph>
											<line>
												<properties color="black" size="1"/>
											</line>
											<text fixtext=" (Title: be brief and descriptive: do not use acronyms or mathematical or scientific notation)"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding="0pt"/>
										<properties colspan="2" valign="top" width="60%"/>
										<children>
											<table>
												<properties border="0" table-layout="fixed" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<properties valign="top" width="30%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<newline/>
																					<text fixtext=" "/>
																				</children>
																			</paragraph>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext="Proposed Base Period"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties valign="top" width="30%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<newline/>
																					<text fixtext=" "/>
																					<newline/>
																				</children>
																			</paragraph>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext="Proposed Option Period"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties valign="top" width="40%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<newline/>
																					<text fixtext=" "/>
																					<newline/>
																				</children>
																			</paragraph>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext="Your Institution's Proposal Number "/>
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
										<styles padding="0pt"/>
										<properties colspan="2" valign="top" width="60%"/>
										<children>
											<table>
												<properties border="0" table-layout="fixed" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles padding-left="0pt" padding-right="0pt"/>
																		<properties width="10%"/>
																		<children>
																			<text fixtext="Submitted to:"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles padding-right="0pt"/>
																		<properties valign="top" width="90%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles padding-left="0pt" padding-right="0pt"/>
																				<children>
																					<text fixtext=" "/>
																				</children>
																			</paragraph>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext="      DOD Agency/ Topic #/Topic Title "/>
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
						</children>
					</tablebody>
				</children>
			</table>
			<table>
				<properties border="0" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<text fixtext="Total funds requested from DOD:"/>
											<newline/>
										</children>
									</tablecol>
								</children>
							</tablerow>
						</children>
					</tablebody>
				</children>
			</table>
			<table>
				<properties border="0" table-layout="fixed" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<properties valign="top" width="25%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="$"/>
												</children>
											</paragraph>
											<line>
												<properties color="black" size="1"/>
											</line>
											<text fixtext="3-year base total"/>
										</children>
									</tablecol>
									<tablecol>
										<properties align="center" width="5%"/>
										<children>
											<text fixtext="+"/>
										</children>
									</tablecol>
									<tablecol>
										<properties valign="top" width="25%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="$"/>
												</children>
											</paragraph>
											<line>
												<properties color="black" size="1"/>
											</line>
											<text fixtext="2-year option total"/>
										</children>
									</tablecol>
									<tablecol>
										<properties align="center" width="5%"/>
										<children>
											<text fixtext="="/>
										</children>
									</tablecol>
									<tablecol>
										<properties valign="top" width="25%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="$"/>
												</children>
											</paragraph>
											<line>
												<properties color="black" size="1"/>
											</line>
											<text fixtext="5-year total"/>
										</children>
									</tablecol>
									<tablecol>
										<properties width="15%"/>
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
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.6in" papermarginleft="0.4in" papermarginright="0.4in" papermargintop="0.6in" paperwidth="8.5in"/>
	</pagelayout>
</structure>
