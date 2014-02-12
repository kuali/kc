<?xml version="1.0" encoding="UTF-8"?>
<structure version="2" schemafile="C:\Proposal printing\schemas\phs398schema.xsd" workingxmlfile="C:\Proposal printing\xml files\nsfproposalwithabstracts1.xml" templatexmlfile="">
	<nspair prefix="n1" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
	<nspair prefix="n2" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
	<nspair prefix="n3" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
	<template>
		<styles font-family="Verdana" font-size="8pt"/>
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
																			<text fixtext="(to he completed by DoD Only) "/>
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
																					<choice>
																						<children>
																							<choiceoption>
																								<testexpression>
																									<xpath value="boolean(  n1:ResearchAndRelatedProject/n1:ResearchCoverPage/n1:ProgramDirectorPrincipalInvestigator/Name/MiddleName =  false()  )"/>
																								</testexpression>
																								<children>
																									<text fixtext="  "/>
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
						</children>
					</tablebody>
				</children>
			</table>
		</children>
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="8.5in" papermarginbottom="0.6in" papermarginleft="1.4in" papermarginright="1.4in" papermargintop="2.3in" paperwidth="11in"/>
	</pagelayout>
</structure>
