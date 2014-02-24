<?xml version="1.0" encoding="UTF-8"?>
<structure version="2" schemafile="c:\proposal printing\schemas\phs398schema.xsd" workingxmlfile="y:\printing\xmls\nsfJune8_03.xml" templatexmlfile="">
	<nspair prefix="n1" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
	<nspair prefix="n2" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
	<nspair prefix="n3" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
	<template>
		<styles font-family="Verdana" font-size="8pt" line-height="9pt"/>
		<match overwrittenxslmatch="/"/>
		<children>
			<table>
				<properties border="0" cellpadding="5" cellspacing="5" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<properties align="center"/>
										<children>
											<text fixtext="COVER SHEET FOR PROPOSAL TO THE NATIONAL SCIENCE FOUNDATION">
												<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
						</children>
					</tablebody>
				</children>
			</table>
			<newline/>
			<table>
				<properties border="0" cellpadding="0" cellspacing="0" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<table>
												<properties border="1" cellpadding="5" cellspacing="5" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<properties height=".2in"/>
																<children>
																	<tablecol>
																		<properties colspan="4" rowspan="2" valign="top" width="1.1in"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="PROGRAM ANNOUNCEMENT/SOLICITATION NO/CLOSING DATE/if not in response to a program announcement/solicitation enter NSF 99-2">
																						<styles font-family="Verdana" font-size="7pt"/>
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
																								<match match="FundingOpportunityDetails"/>
																								<children>
																									<template>
																										<match match="FundingOpportunityNumber"/>
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
																	</tablecol>
																	<tablecol>
																		<styles background-color="gray"/>
																		<properties align="center" colspan="2" valign="top" width="1.75in"/>
																		<children>
																			<text fixtext="FOR NSF USE ONLY">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles background-color="gray"/>
																		<properties align="center" colspan="2" rowspan="2" valign="top" width="1.75in"/>
																		<children>
																			<text fixtext="NSF PROPOSAL NUMBER">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<properties height=".2in"/>
																<children>
																	<tablecol>
																		<properties colspan="4" valign="top" width="1.1in"/>
																		<children>
																			<text fixtext="FOR CONSIDERATION BY NSF ORGANIZATIONAL UNIT">
																				<styles font-family="Verdana" font-size="7pt"/>
																			</text>
																			<text fixtext="(S) (Indicate the most specific unit known, i.e. program, division etc.)    ">
																				<styles font-family="Verdana" font-size="7pt"/>
																			</text>
																			<template>
																				<match match="n1:ResearchAndRelatedProject"/>
																				<children>
																					<template>
																						<match match="n1:ResearchCoverPage"/>
																						<children>
																							<template>
																								<match match="FundingOpportunityDetails"/>
																								<children>
																									<template>
																										<match match="FundingOpportunityTitle"/>
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
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<properties width="1.1in"/>
																		<children>
																			<text fixtext="DATE RECEIVED">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="1.15in"/>
																		<children>
																			<text fixtext="NUMBER OF COPIES">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="1.1in"/>
																		<children>
																			<text fixtext="DIVISION ASSIGNED">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="1.15in"/>
																		<children>
																			<text fixtext="FUND CODE">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties valign="top" width="1.75in"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="DUNS # (Data Universal Numbering System)">
																						<styles font-family="Verdana" font-size="8pt"/>
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
																								<match match="ApplicantOrganization"/>
																								<children>
																									<template>
																										<match match="OrganizationDUNS"/>
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
																	</tablecol>
																	<tablecol>
																		<properties width="1.75in"/>
																		<children>
																			<text fixtext="FILE LOCATION">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<table>
												<properties border="1" cellpadding="5" cellspacing="5" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<properties width="2.5in"/>
																		<children>
																			<text fixtext="EMPLOYER IDENTIFICATION NUMBER (EIN) OR ">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="TAXPAYER ">
																						<styles font-family="Verdana" font-size="8pt"/>
																					</text>
																					<text fixtext="IDENTIFICATION NUMBER (TIN)">
																						<styles font-family="Verdana" font-size="8pt"/>
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
																								<match match="ApplicantOrganization"/>
																								<children>
																									<template>
																										<match match="OrganizationEIN"/>
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
																	</tablecol>
																	<tablecol>
																		<properties valign="top" width="2.25in"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="SHOW PREVIOUS AWARD NO. IF">
																						<styles font-family="Verdana" font-size="8pt"/>
																					</text>
																					<text fixtext=" THIS IS">
																						<styles font-family="Verdana" font-size="8pt"/>
																					</text>
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
																										<match match="ApplicationCategory"/>
																										<children>
																											<template>
																												<match match="CategoryIdentifier"/>
																												<children>
																													<checkbox ownvalue="1" checkedvalue="Renewal" checkedvalue1="1">
																														<styles font-family="Verdana" font-size="8pt"/>
																														<properties type="checkbox"/>
																													</checkbox>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																					<text fixtext=" A Renewal">
																						<styles font-family="Verdana" font-size="8pt"/>
																					</text>
																				</children>
																			</paragraph>
																			<template>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<match match="n1:ResearchAndRelatedProject"/>
																				<children>
																					<template>
																						<match match="n1:ResearchCoverPage"/>
																						<children>
																							<template>
																								<match match="ApplicationCategory"/>
																								<children>
																									<template>
																										<match match="CategoryIdentifier"/>
																										<children>
																											<checkbox ownvalue="1" checkedvalue="Accomplishment-based Renewal" checkedvalue1="1">
																												<properties type="checkbox"/>
																											</checkbox>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext="An Accomplishment Based Renewal">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<newline/>
																			<newline/>
																			<template>
																				<match match="n1:ResearchAndRelatedProject"/>
																				<children>
																					<template>
																						<match match="NSFPreviousAwardNumber"/>
																						<children>
																							<xpath allchildren="1">
																								<styles font-family="Verdana" font-size="8pt"/>
																							</xpath>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles font-family="Verdana" font-size="8pt"/>
																		<properties width="3.25in"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="IS THIS PROPOSAL BEING SUBMITTED TO ANOTHER AGENCY?   YES">
																						<styles font-family="Verdana" font-size="8pt"/>
																					</text>
																					<template>
																						<match match="n1:ResearchAndRelatedProject"/>
																						<children>
																							<template>
																								<match match="n1:ResearchCoverPage"/>
																								<children>
																									<template>
																										<match match="OtherAgencyQuestions"/>
																										<children>
																											<template>
																												<match match="OtherAgencyIndicator"/>
																												<children>
																													<checkbox ownvalue="1">
																														<styles font-family="Verdana" font-size="8pt"/>
																														<properties type="checkbox"/>
																													</checkbox>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																					<text fixtext="   NO">
																						<styles font-family="Verdana" font-size="8pt"/>
																					</text>
																					<template>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<match match="n1:ResearchAndRelatedProject"/>
																						<children>
																							<template>
																								<match match="n1:ResearchCoverPage"/>
																								<children>
																									<template>
																										<match match="OtherAgencyQuestions"/>
																										<children>
																											<template>
																												<match match="OtherAgencyIndicator"/>
																												<children>
																													<checkbox ownvalue="1" checkedvalue="false" checkedvalue1="" uncheckedvalue="true">
																														<properties type="checkbox"/>
																													</checkbox>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																					<text fixtext=" IF YES, LIST ACRONYM(S)"/>
																				</children>
																			</paragraph>
																			<template>
																				<match match="n1:ResearchAndRelatedProject"/>
																				<children>
																					<template>
																						<match match="n1:ResearchCoverPage"/>
																						<children>
																							<template>
																								<match match="OtherAgencyQuestions"/>
																								<children>
																									<template>
																										<match match="OtherAgencyNames"/>
																										<children>
																											<paragraph paragraphtag="p">
																												<children>
																													<xpath allchildren="1"/>
																												</children>
																											</paragraph>
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
											<table>
												<properties border="1" cellpadding="5" cellspacing="5" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<properties width="4in"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="NAME OF ORGANIZATION TO WHICH AWARD SHOULD BE MADE">
																						<styles font-family="Verdana" font-size="8pt"/>
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
																								<match match="ApplicantOrganization"/>
																								<children>
																									<template>
																										<match match="OrganizationName"/>
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
																	</tablecol>
																	<tablecol>
																		<properties rowspan="2" width="4in"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="ADDRESS OF AWARDEE ORGANIZATION, INCLUDING 9 DIGIT ZIP CODE">
																						<styles font-family="Verdana" font-size="8pt"/>
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
																															<xpath allchildren="1">
																																<styles font-family="Verdana" font-size="8pt"/>
																															</xpath>
																														</children>
																													</template>
																												</children>
																											</paragraph>
																											<template>
																												<match match="MailStopCode"/>
																												<children>
																													<xpath allchildren="1">
																														<styles font-family="Verdana" font-size="8pt"/>
																													</xpath>
																												</children>
																											</template>
																											<paragraph paragraphtag="p">
																												<children>
																													<template>
																														<match match="City"/>
																														<children>
																															<xpath allchildren="1">
																																<styles font-family="Verdana" font-size="8pt"/>
																															</xpath>
																														</children>
																													</template>
																													<text fixtext=", ">
																														<styles font-family="Verdana" font-size="8pt"/>
																													</text>
																													<template>
																														<match match="State"/>
																														<children>
																															<xpath allchildren="1">
																																<styles font-family="Verdana" font-size="8pt"/>
																															</xpath>
																														</children>
																													</template>
																													<text fixtext="  ">
																														<styles font-family="Verdana" font-size="8pt"/>
																													</text>
																													<template>
																														<match match="PostalCode"/>
																														<children>
																															<xpath allchildren="1">
																																<styles font-family="Verdana" font-size="8pt"/>
																															</xpath>
																														</children>
																													</template>
																												</children>
																											</paragraph>
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
																		<properties width="4in"/>
																		<children>
																			<text fixtext="AWARDEE ORGANIZATION CODE (IF KNOWN)   ">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles font-family="Verdana" font-size="8pt"/>
																		<properties width="4in"/>
																		<children>
																			<text fixtext="NAME OF PERFORMING ORGANIZATION, IF DIFFERENT FROM ABOVE">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<template>
																				<match match="n1:ResearchAndRelatedProject"/>
																				<children>
																					<template>
																						<match match="n1:ResearchCoverPage"/>
																						<children>
																							<template>
																								<match match="PrimaryProjectSite"/>
																								<children>
																									<choice>
																										<children>
																											<choiceoption>
																												<testexpression>
																													<xpath value="OrganizationName !=  ../ApplicantOrganization/OrganizationName"/>
																												</testexpression>
																												<children>
																													<template>
																														<match match="OrganizationName"/>
																														<children>
																															<paragraph paragraphtag="p">
																																<children>
																																	<xpath allchildren="1"/>
																																</children>
																															</paragraph>
																														</children>
																													</template>
																												</children>
																											</choiceoption>
																										</children>
																									</choice>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles font-family="Verdana" font-size="8pt"/>
																		<properties rowspan="2" width="4in"/>
																		<children>
																			<text fixtext="ADDRESS OF PERFORMING ORGANIZATION, IF DIFFERENT, INCLUDING 9 DIGIT ZIP CODE">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<template>
																				<match match="n1:ResearchAndRelatedProject"/>
																				<children>
																					<template>
																						<match match="n1:ResearchCoverPage"/>
																						<children>
																							<template>
																								<match match="PrimaryProjectSite"/>
																								<children>
																									<choice>
																										<children>
																											<choiceoption>
																												<testexpression>
																													<xpath value="PostalAddress/Street !=  ../ApplicantOrganization/OrganizationAddress/Street or  PostalAddress/MailStopCode !=  ../ApplicantOrganization/OrganizationAddress/MailStopCode or   PostalAddress/PostalCode != ../ApplicantOrganization/OrganizationAddress/PostalCode"/>
																												</testexpression>
																												<children>
																													<template>
																														<match match="PostalAddress"/>
																														<children>
																															<template>
																																<match match="Street"/>
																																<children>
																																	<paragraph paragraphtag="p">
																																		<children>
																																			<xpath allchildren="1"/>
																																		</children>
																																	</paragraph>
																																</children>
																															</template>
																															<template>
																																<match match="City"/>
																																<children>
																																	<xpath allchildren="1"/>
																																</children>
																															</template>
																															<text fixtext=","/>
																															<template>
																																<match match="State"/>
																																<children>
																																	<xpath allchildren="1"/>
																																</children>
																															</template>
																															<text fixtext=" "/>
																															<template>
																																<match match="PostalCode"/>
																																<children>
																																	<xpath allchildren="1"/>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</choiceoption>
																										</children>
																									</choice>
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
																		<properties width="4in"/>
																		<children>
																			<text fixtext="PERFORMING ORGANIZATION CODE (IF KNOWN)">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<table>
												<properties border="1" cellpadding="5" cellspacing="5" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<properties colspan="4" valign="top" width="2in"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="IS AWARDEE ORGANIZATION (Check all that apply)">
																						<styles font-family="Verdana" font-size="8pt"/>
																					</text>
																				</children>
																			</paragraph>
																			<text fixtext="(See GPG II.D.1 for Definitions)  __FOR-PROFIT ORGANIZATION   ">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<template>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<match match="n1:ResearchAndRelatedProject"/>
																				<children>
																					<template>
																						<match match="n3:OrgAssurances"/>
																						<children>
																							<template>
																								<match match="n3:SBIRSurvey"/>
																								<children>
																									<template>
																										<match match="SBCertificationQuestion"/>
																										<children>
																											<checkbox ownvalue="1">
																												<styles font-family="Verdana"/>
																												<properties type="checkbox"/>
																											</checkbox>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext="  __SMALL BUSINESS     __MINORITY BUSINESS    ">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<template>
																				<match match="n3:OrgAssurances"/>
																				<children>
																					<template>
																						<match match="n3:SBIRSurvey"/>
																						<children>
																							<template>
																								<match match="WomenOwnedQuestion"/>
																								<children>
																									<checkbox ownvalue="1">
																										<styles font-family="Verdana"/>
																										<properties type="checkbox"/>
																									</checkbox>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext="__WOMAN-OWNED BUSINESS">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<properties colspan="4" valign="top" width="2in"/>
																		<children>
																			<text fixtext="TITLE OF PROPOSED PROJECT   ">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
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
																										<styles font-family="Verdana" font-size="8pt"/>
																									</xpath>
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
																		<properties valign="top" width="2in"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="REQUESTED AMOUNT">
																						<styles font-family="Verdana" font-size="8pt"/>
																					</text>
																				</children>
																			</paragraph>
																			<template>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<match match="n1:ResearchAndRelatedProject"/>
																				<children>
																					<template>
																						<match match="BudgetSummary"/>
																						<children>
																							<template>
																								<match match="BudgetCostsTotal"/>
																								<children>
																									<text fixtext="$"/>
																									<xpath allchildren="1">
																										<format string="#,###,###,##0" xslt="1"/>
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
																		<styles font-family="Verdana" font-size="8pt"/>
																		<properties valign="top" width="2in"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles font-family="Verdana" font-size="8pt"/>
																				<children>
																					<text fixtext="PROPOSED DURATION (1-60 MONTHS)">
																						<styles font-family="Verdana" font-size="8pt"/>
																					</text>
																				</children>
																			</paragraph>
																			<template>
																				<match match="n1:ResearchAndRelatedProject"/>
																				<children>
																					<template>
																						<match match="NSFProjectDuration"/>
																						<children>
																							<xpath allchildren="1">
																								<styles font-family="Verdana" font-size="8pt"/>
																							</xpath>
																						</children>
																					</template>
																				</children>
																			</template>
																			<text fixtext=" months"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties valign="top" width="2in"/>
																		<children>
																			<text fixtext="RE">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<text fixtext="QUESTED STARTING DA">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<text fixtext="TE">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<paragraph paragraphtag="p">
																				<children>
																					<template>
																						<styles font-family="Verdana" font-size="8pt"/>
																						<match match="n1:ResearchAndRelatedProject"/>
																						<children>
																							<template>
																								<match match="n1:ResearchCoverPage"/>
																								<children>
																									<template>
																										<match match="ProjectDates"/>
																										<children>
																											<template>
																												<match match="ProjectStartDate"/>
																												<children>
																													<xpath allchildren="1">
																														<format string="MM/DD/YYYY" xslt="1"/>
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
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties valign="top" width="2in"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="SHOW RELATED PROPOSAL NO., IF APPLICABLE">
																						<styles font-family="Verdana" font-size="8pt"/>
																					</text>
																				</children>
																			</paragraph>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<properties colspan="4" width="2in"/>
																		<children>
																			<table>
																				<properties border="0" cellpadding="5" cellspacing="5" width="100%"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecol>
																										<properties colspan="2" valign="top"/>
																										<children>
																											<text fixtext="CHECK APPROPRIATE BOX(ES) IF THIS PROPOSAL INCLUDES ANY OF THE ITEMS LISTED BELOW">
																												<styles font-family="Verdana" font-size="8pt"/>
																											</text>
																										</children>
																									</tablecol>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecol>
																										<properties valign="top"/>
																									</tablecol>
																									<tablecol>
																										<properties valign="top" width="4in"/>
																									</tablecol>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecol>
																										<properties valign="top"/>
																										<children>
																											<paragraph paragraphtag="p">
																												<children>
																													<template>
																														<styles font-family="Verdana" font-size="8pt"/>
																														<match match="n1:ResearchAndRelatedProject"/>
																														<children>
																															<template>
																																<match match="n1:ProjectDescription"/>
																																<children>
																																	<template>
																																		<match match="n3:ProjectSurvey"/>
																																		<children>
																																			<template>
																																				<match match="NSFbeginningInvestQuestion"/>
																																				<children>
																																					<checkbox ownvalue="1">
																																						<properties type="checkbox"/>
																																					</checkbox>
																																				</children>
																																			</template>
																																		</children>
																																	</template>
																																</children>
																															</template>
																														</children>
																													</template>
																													<text fixtext="BEGINNING INVESTIGATOR (GPG I.A)">
																														<styles font-family="Verdana" font-size="8pt"/>
																													</text>
																												</children>
																											</paragraph>
																											<paragraph paragraphtag="p">
																												<children>
																													<template>
																														<match match="n1:ResearchAndRelatedProject"/>
																														<children>
																															<template>
																																<match match="n1:ProjectDescription"/>
																																<children>
																																	<template>
																																		<match match="n3:ProjectSurvey"/>
																																		<children>
																																			<template>
																																				<match match="H4Question"/>
																																				<children>
																																					<checkbox ownvalue="1">
																																						<properties type="checkbox"/>
																																					</checkbox>
																																				</children>
																																			</template>
																																		</children>
																																	</template>
																																</children>
																															</template>
																														</children>
																													</template>
																													<text fixtext="DISCLOSURE OF LOBBYING ACTIVITIES (GPG I.A)">
																														<styles font-family="Verdana" font-size="8pt"/>
																													</text>
																												</children>
																											</paragraph>
																											<paragraph paragraphtag="p">
																												<children>
																													<template>
																														<styles font-family="Verdana" font-size="8pt"/>
																														<match match="n1:ResearchAndRelatedProject"/>
																														<children>
																															<template>
																																<match match="n1:ProjectDescription"/>
																																<children>
																																	<template>
																																		<match match="n3:ProjectSurvey"/>
																																		<children>
																																			<template>
																																				<match match="G8Question"/>
																																				<children>
																																					<checkbox ownvalue="1">
																																						<properties type="checkbox"/>
																																					</checkbox>
																																				</children>
																																			</template>
																																		</children>
																																	</template>
																																</children>
																															</template>
																														</children>
																													</template>
																													<text fixtext="PROPRIETARY AND PRIVILEGED INFORMATION (PGP II.C)">
																														<styles font-family="Verdana" font-size="8pt"/>
																													</text>
																												</children>
																											</paragraph>
																											<paragraph paragraphtag="p">
																												<children>
																													<template>
																														<styles font-family="Verdana" font-size="8pt"/>
																														<match match="n1:ResearchAndRelatedProject"/>
																														<children>
																															<template>
																																<match match="n1:ProjectDescription"/>
																																<children>
																																	<template>
																																		<match match="n3:ProjectSurvey"/>
																																		<children>
																																			<template>
																																				<match match="G6Question"/>
																																				<children>
																																					<checkbox ownvalue="1">
																																						<properties type="checkbox"/>
																																					</checkbox>
																																				</children>
																																			</template>
																																		</children>
																																	</template>
																																</children>
																															</template>
																														</children>
																													</template>
																													<text fixtext="HISTORIC PLACES (GPG II.C.9)">
																														<styles font-family="Verdana" font-size="8pt"/>
																													</text>
																												</children>
																											</paragraph>
																											<paragraph paragraphtag="p">
																												<children>
																													<template>
																														<styles font-family="Verdana" font-size="8pt"/>
																														<match match="n1:ResearchAndRelatedProject"/>
																														<children>
																															<template>
																																<match match="n1:ProjectDescription"/>
																																<children>
																																	<template>
																																		<match match="n3:ProjectSurvey"/>
																																		<children>
																																			<template>
																																				<match match="SmallGrantQuestion"/>
																																				<children>
																																					<checkbox ownvalue="1">
																																						<properties type="checkbox"/>
																																					</checkbox>
																																				</children>
																																			</template>
																																		</children>
																																	</template>
																																</children>
																															</template>
																														</children>
																													</template>
																													<text fixtext="SMALL GRANT FOR EXPLOR. RESEARCH (SGER) (GPG II.C.11)">
																														<styles font-family="Verdana" font-size="8pt"/>
																													</text>
																												</children>
																											</paragraph>
																											<template>
																												<styles font-family="Verdana" font-size="8pt"/>
																												<match match="n1:ResearchAndRelatedProject"/>
																												<children>
																													<template>
																														<match match="n1:ProjectDescription"/>
																														<children>
																															<template>
																																<match match="n3:AnimalSubject"/>
																																<children>
																																	<template>
																																		<match match="VertebrateAnimalsUsedQuestion"/>
																																		<children>
																																			<checkbox ownvalue="1">
																																				<styles font-family="Verdana"/>
																																				<properties type="checkbox"/>
																																			</checkbox>
																																		</children>
																																	</template>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																											<text fixtext="VERTEBRATE ANIMALS (GPG II.C.11) IACUC App. Date ">
																												<styles font-family="Verdana" font-size="8pt"/>
																											</text>
																											<newline/>
																											<template>
																												<match match="n1:ResearchAndRelatedProject"/>
																												<children>
																													<template>
																														<match match="n1:ProjectDescription"/>
																														<children>
																															<template>
																																<match match="n3:AnimalSubject"/>
																																<children>
																																	<template>
																																		<match match="IACUCApprovalDate"/>
																																		<children>
																																			<xpath allchildren="1">
																																				<styles font-family="Verdana" font-size="8pt"/>
																																				<format string="MM/DD/YYYY" xslt="1"/>
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
																									<tablecol>
																										<properties valign="top" width="4in"/>
																										<children>
																											<paragraph paragraphtag="p">
																												<children>
																													<template>
																														<styles font-family="Verdana" font-size="8pt"/>
																														<match match="n1:ResearchAndRelatedProject"/>
																														<children>
																															<template>
																																<match match="n1:ProjectDescription"/>
																																<children>
																																	<template>
																																		<match match="n1:HumanSubject"/>
																																		<children>
																																			<template>
																																				<match match="HumanSubjectsUsedQuestion"/>
																																				<children>
																																					<checkbox ownvalue="1">
																																						<styles font-family="Verdana"/>
																																						<properties type="checkbox"/>
																																					</checkbox>
																																				</children>
																																			</template>
																																		</children>
																																	</template>
																																</children>
																															</template>
																														</children>
																													</template>
																													<text fixtext="HUMAN SUBJECTS (GPG II.C.11)">
																														<styles font-family="Verdana" font-size="8pt"/>
																													</text>
																												</children>
																											</paragraph>
																											<paragraph paragraphtag="p">
																												<children>
																													<text fixtext="Exemption Subsection   or IRB App.Date:  ">
																														<styles font-family="Verdana" font-size="8pt"/>
																													</text>
																													<template>
																														<match match="n1:ResearchAndRelatedProject"/>
																														<children>
																															<template>
																																<match match="n1:ProjectDescription"/>
																																<children>
																																	<template>
																																		<match match="n1:HumanSubject"/>
																																		<children>
																																			<template>
																																				<match match="IRBApprovalDate"/>
																																				<children>
																																					<xpath allchildren="1">
																																						<format string="MM/DD/YYYY" xslt="1"/>
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
																												<styles font-family="Verdana" font-size="7pt"/>
																												<match match="n1:ResearchAndRelatedProject"/>
																												<children>
																													<template>
																														<match match="n1:ProjectDescription"/>
																														<children>
																															<template>
																																<match match="n3:ProjectSurvey"/>
																																<children>
																																	<template>
																																		<match match="H1Question"/>
																																		<children>
																																			<checkbox ownvalue="1">
																																				<properties type="checkbox"/>
																																			</checkbox>
																																		</children>
																																	</template>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																											<text fixtext="INTERNATIONAL COOPERATIVE ACTIVITIES: COUNTRY/COUNTRIES   ">
																												<styles font-family="Verdana" font-size="8pt"/>
																											</text>
																											<template>
																												<styles font-family="Verdana" font-size="7pt"/>
																												<match match="n1:ResearchAndRelatedProject"/>
																												<children>
																													<template>
																														<match match="n1:ProjectDescription"/>
																														<children>
																															<template>
																																<match match="n3:ProjectSurvey"/>
																																<children>
																																	<template>
																																		<match match="H1Text"/>
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
												<properties border="1" cellpadding="5" cellspacing="5" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<properties valign="top" width="2.75in"/>
																		<children>
																			<text fixtext="PI/PD DEPARTMENT  ">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<template>
																				<styles font-family="Verdana" font-size="8pt"/>
																				<match match="n1:ResearchAndRelatedProject"/>
																				<children>
																					<template>
																						<match match="KeyPerson"/>
																						<children>
																							<choice>
																								<children>
																									<choiceoption>
																										<testexpression>
																											<xpath value="n1:AccountIdentifier =  ../n1:ResearchCoverPage/n1:ProgramDirectorPrincipalInvestigator/n1:AccountIdentifier"/>
																										</testexpression>
																										<children>
																											<template>
																												<match match="OrganizationDepartment"/>
																												<children>
																													<xpath allchildren="1"/>
																												</children>
																											</template>
																										</children>
																									</choiceoption>
																								</children>
																							</choice>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties colspan="4" rowspan="2" valign="top" width="1in"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="PI/PD POSTAL ADDRESS">
																						<styles font-family="Verdana" font-size="8pt"/>
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
																										<match match="ContactInformation"/>
																										<children>
																											<template>
																												<match match="PostalAddress"/>
																												<children>
																													<paragraph paragraphtag="p">
																														<children>
																															<template>
																																<match match="Street"/>
																																<children>
																																	<xpath allchildren="1">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</xpath>
																																</children>
																															</template>
																														</children>
																													</paragraph>
																													<paragraph paragraphtag="p">
																														<children>
																															<template>
																																<match match="MailStopCode"/>
																																<children>
																																	<xpath allchildren="1">
																																		<styles font-family="Verdana" font-size="8pt"/>
																																	</xpath>
																																</children>
																															</template>
																														</children>
																													</paragraph>
																													<template>
																														<match match="City"/>
																														<children>
																															<xpath allchildren="1">
																																<styles font-family="Verdana" font-size="8pt"/>
																															</xpath>
																														</children>
																													</template>
																													<text fixtext=", ">
																														<styles font-family="Verdana" font-size="8pt"/>
																													</text>
																													<template>
																														<match match="State"/>
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
																			</template>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<properties width="2.75in"/>
																		<children>
																			<text fixtext="PI/PD FAX NUMBER   ">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																			<template>
																				<styles font-family="Verdana" font-size="8pt"/>
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
																			<text fixtext="   ">
																				<styles font-family="Verdana" font-size="8pt"/>
																			</text>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles font-family="Verdana" font-size="8pt" padding-bottom="0pt" padding-left="0pt" padding-right="0pt" padding-top="0pt"/>
																		<properties colspan="5" valign="top" width="2.75in"/>
																		<children>
																			<template>
																				<match match="n1:ResearchAndRelatedProject"/>
																				<children>
																					<template>
																						<match match="ProposalPerson"/>
																						<children>
																							<table dynamic="1">
																								<properties border="1" cellpadding="5" cellspacing="5" table-layout="fixed"/>
																								<children>
																									<tableheader>
																										<children>
																											<tablerow>
																												<children>
																													<tablecol>
																														<styles border-left-style="none" border-top-style="none" font-family="Verdana" font-size="8pt"/>
																														<properties width="2.75in"/>
																														<children>
																															<text fixtext="Name"/>
																														</children>
																													</tablecol>
																													<tablecol>
																														<styles border-top-style="none" font-family="Verdana" font-size="8pt"/>
																														<properties width="1in"/>
																														<children>
																															<text fixtext="High Degree"/>
																														</children>
																													</tablecol>
																													<tablecol>
																														<styles border-top-style="none" font-family="Verdana" font-size="8pt"/>
																														<properties width="1in"/>
																														<children>
																															<text fixtext="Yr of Degree"/>
																														</children>
																													</tablecol>
																													<tablecol>
																														<styles border-top-style="none" font-family="Verdana" font-size="8pt"/>
																														<properties width="1.25in"/>
																														<children>
																															<text fixtext="Telephone Number"/>
																														</children>
																													</tablecol>
																													<tablecol>
																														<styles border-right-style="none" border-top-style="none" font-family="Verdana" font-size="8pt"/>
																														<properties width="2in"/>
																														<children>
																															<text fixtext="Electronic Mail Address"/>
																														</children>
																													</tablecol>
																												</children>
																											</tablerow>
																										</children>
																									</tableheader>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecol>
																														<styles border-bottom-style="none" border-left-style="none" font-family="Verdana" font-size="8pt" padding-left="0pt"/>
																														<properties width="2.75in"/>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value="ProjectRole =&apos;Principal Investigator&apos;"/>
																																		</testexpression>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<styles border-bottom-style="none" border-left-style="none" padding-left="0pt"/>
																																				<children>
																																					<text fixtext="PI/PD NAME"/>
																																				</children>
																																			</paragraph>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value="ProjectRole =&apos;Co-PI&apos;"/>
																																		</testexpression>
																																		<children>
																																			<paragraph paragraphtag="p">
																																				<styles border-bottom-style="none"/>
																																				<children>
																																					<text fixtext="CO-PI/PD NAME"/>
																																				</children>
																																			</paragraph>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value="ProjectRole =&apos;Principal Investigator&apos; or ProjectRole=&apos;Co-PI&apos;"/>
																																		</testexpression>
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
																																					<text fixtext=", "/>
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
																																						</children>
																																					</template>
																																					<text fixtext=" "/>
																																					<template>
																																						<match match="NameSuffix"/>
																																						<children>
																																							<xpath allchildren="1"/>
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
																														</children>
																													</tablecol>
																													<tablecol>
																														<styles border-bottom-style="none" font-family="Verdana" font-size="8pt"/>
																														<properties width="1in"/>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value="ProjectRole=&apos;Principal Investigator&apos; or ProjectRole=&apos;Co-PI&apos;"/>
																																		</testexpression>
																																		<children>
																																			<template>
																																				<match match="Degree"/>
																																				<children>
																																					<xpath allchildren="1"/>
																																				</children>
																																			</template>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
																														</children>
																													</tablecol>
																													<tablecol>
																														<styles border-bottom-style="none" font-family="Verdana" font-size="8pt"/>
																														<properties width="1in"/>
																													</tablecol>
																													<tablecol>
																														<styles border-bottom-style="none" font-family="Verdana" font-size="8pt"/>
																														<properties width="1.25in"/>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value="ProjectRole =&apos;Principal Investigator&apos; or ProjectRole=&apos;Co-PI&apos;"/>
																																		</testexpression>
																																		<children>
																																			<template>
																																				<match match="Phone"/>
																																				<children>
																																					<xpath allchildren="1"/>
																																				</children>
																																			</template>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
																														</children>
																													</tablecol>
																													<tablecol>
																														<styles border-bottom-style="none" border-right-style="none" font-family="Verdana" font-size="8pt"/>
																														<properties width="2in"/>
																														<children>
																															<choice>
																																<children>
																																	<choiceoption>
																																		<testexpression>
																																			<xpath value="ProjectRole=&apos;Principal Investigator&apos; or ProjectRole=&apos;Co-PI&apos;"/>
																																		</testexpression>
																																		<children>
																																			<template>
																																				<match match="Email"/>
																																				<children>
																																					<xpath allchildren="1"/>
																																				</children>
																																			</template>
																																		</children>
																																	</choiceoption>
																																</children>
																															</choice>
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
																				</children>
																			</template>
																			<newline/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<properties height="1pt"/>
																<children>
																	<tablecol>
																		<styles border-bottom-style="none" border-left-style="none" border-right-style="none" font-family="Verdana" font-size="8pt" height="1pt"/>
																		<properties height="1" valign="top" width="2.75in"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles border-bottom-style="none" border-left-style="none" border-right-style="none" font-family="Verdana" font-size="8pt" height="1pt"/>
																			</paragraph>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-style="none" border-top-style="none" font-family="Verdana" font-size="8pt"/>
																		<properties height="1" width="1in"/>
																	</tablecol>
																	<tablecol>
																		<styles border-style="none" border-top-style="none" font-family="Verdana" font-size="8pt"/>
																		<properties height="1" width="1in"/>
																	</tablecol>
																	<tablecol>
																		<styles border-style="none" border-top-style="none" font-family="Verdana" font-size="8pt"/>
																		<properties height="1" width="1.25in"/>
																	</tablecol>
																	<tablecol>
																		<styles border-style="none" border-top-style="none" font-family="Verdana" font-size="8pt"/>
																		<properties height="1" width="2in"/>
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
			<newline/>
		</children>
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.2in" papermarginleft="0.25in" papermarginright="0.3in" papermargintop="0.3in" paperwidth="8.5in"/>
		<footerall>
			<template>
				<match overwrittenxslmatch="/"/>
				<children>
					<table topdown="0">
						<properties cellpadding="5" cellspacing="5" width="100%"/>
						<children>
							<tablebody>
								<children>
									<tablerow>
										<children>
											<tablecol>
												<styles padding="0"/>
												<properties colspan="2" height="7"/>
											</tablecol>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecol>
												<styles font-size="smaller" padding="0"/>
												<properties align="left"/>
												<children>
													<text fixtext="NSF Form 1207 (10/89)">
														<styles font-family="Verdana" font-size="8pt"/>
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
