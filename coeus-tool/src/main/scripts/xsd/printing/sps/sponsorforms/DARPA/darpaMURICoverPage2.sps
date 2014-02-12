<?xml version="1.0" encoding="UTF-8"?>
<structure version="2" schemafile="C:\Proposal printing\schemas\phs398schema.xsd" workingxmlfile="C:\Proposal printing\xml files\nihproposal.xml" templatexmlfile="">
	<nspair prefix="n1" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
	<nspair prefix="n2" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
	<nspair prefix="n3" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
	<template>
		<styles font-family="Verdana" font-size="9pt"/>
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
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="OTHER AGENCIES RECEIVING THIS RESEARCH FUNDING REQUEST"/>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="(e.g., NSF, DOE NASA, NIH). Please identify agency(ies) and give Name(s) and Phone  Number(s) of Point(s) of Contact at those agencies:"/>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<children>
													<newline/>
													<newline/>
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
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<newline/>
											<text fixtext="3.	CERTIFICATIONS: By signing and submitting this proposal, the proposer is providing the certification at Appendix A to 32 CFR Part 25 regarding debarment, suspension. and other matters; the certification at Appendix C to 32 CFR Part 25 regarding drug-free workplace; and the certification at Appendix A to 32 CFR Part 28 regarding lobbying."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<newline/>
											<text fixtext="4.	MINORITY INSTITUTION:	____ Check here if the academic institution named above is qualified to be identified by the Department of Education as a minority institution (i.e.. a historically Black college or university. Hispanic-serving institution, Tribal college or university, or other institution meeting statutorily-defined criteria for serving ethnic groups that are underrepresented in science and engineering). The Department of Education maintains the list of U.S. accredited postsecondary institutions that currently meet the statutory criteria for identification as minority institutions at the following web site: "/>
											<text/>
											<text fixtext="http://www.ed.gov/offices/OCR/minorityinst.html">
												<styles text-decoration="underline"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<newline/>
											<text fixtext="5. THE INSTITUTION: NAME AND ADDRESS OF UNIVERSITY OFFICIAL AUTHORIZED TO OBLIGATE CONTRACTUALLY "/>
											<text fixtext="AND WITH
WHOM BUSINESS NEGOTIATIONS SHOULD BE CONDUCTED:">
												<styles font-size="9pt"/>
											</text>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding="0"/>
										<properties height="48"/>
										<children>
											<table>
												<properties border="0" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles padding-left="0" padding-right="0"/>
																		<properties align="center" valign="top" width="25%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<choice>
																						<children>
																							<choiceoption>
																								<testexpression>
																									<xpath value="string-length(  n1:ResearchAndRelatedProject/n1:ResearchCoverPage/AuthorizedOrganizationalRepresentative/PositionTitle  ) &lt;= 0"/>
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
																			</paragraph>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext=" (Title)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties align="center" valign="top" width="25%"/>
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
																		<properties align="center" valign="top" width="10%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<choice>
																						<children>
																							<choiceoption>
																								<testexpression>
																									<xpath value="string-length(n1:ResearchAndRelatedProject/n1:ResearchCoverPage/AuthorizedOrganizationalRepresentative/Name/MiddleName )&lt;= 0"/>
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
																										<match match="AuthorizedOrganizationalRepresentative"/>
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
																		<properties align="center" valign="top" width="30%"/>
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
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding="0"/>
										<properties height="113"/>
										<children>
											<table>
												<properties border="0" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles padding-right="6pt"/>
																		<properties valign="top" width="25%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles padding-right="6pt"/>
																				<children>
																					<choice>
																						<children>
																							<choiceoption>
																								<testexpression>
																									<xpath value="string-length(   n1:ResearchAndRelatedProject/n1:ResearchCoverPage/AuthorizedOrganizationalRepresentative/ContactInformation/PhoneNumber   ) &lt;= 0"/>
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
																			</paragraph>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext=" (Phone Number)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles padding-right="6pt"/>
																		<properties valign="top" width="25%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles padding-right="6pt"/>
																				<children>
																					<choice>
																						<children>
																							<choiceoption>
																								<testexpression>
																									<xpath value="string-length(   n1:ResearchAndRelatedProject/n1:ResearchCoverPage/AuthorizedOrganizationalRepresentative/ContactInformation/FaxNumber   ) &lt;= 0"/>
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
																			</paragraph>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext=" (Fax Number)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties valign="top" width="50%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<choice>
																						<children>
																							<choiceoption>
																								<testexpression>
																									<xpath value="string-length(   n1:ResearchAndRelatedProject/n1:ResearchCoverPage/AuthorizedOrganizationalRepresentative/ContactInformation/Email   ) &lt;= 0"/>
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
																										<match match="AuthorizedOrganizationalRepresentative"/>
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
																			<text fixtext=" (E-mail address)"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<properties colspan="3" valign="top" width="25%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<newline/>
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
																			<text fixtext=" (Name of Grantee (University)"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<properties colspan="3" valign="top" width="25%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<newline/>
																					<newline/>
																					<choice>
																						<children>
																							<choiceoption>
																								<testexpression>
																									<xpath value="string-length(    n1:ResearchAndRelatedProject/n1:ResearchCoverPage/AuthorizedOrganizationalRepresentative/ContactInformation/PostalAddress/Street    ) &lt;= 0 and  string-length( n1:ResearchAndRelatedProject/n1:ResearchCoverPage/AuthorizedOrganizationalRepresentative/ContactInformation/PostalAddress/MailStopCode ) &lt;= 0"/>
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
																										<match match="AuthorizedOrganizationalRepresentative"/>
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
																			<text fixtext=" Street Address (P.O. Box Numbers Cannot Be Accepted)"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles padding-right="0"/>
																		<properties height="75" valign="top" width="25%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles padding-right="0"/>
																				<children>
																					<newline/>
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
																												<match match="ContactInformation"/>
																												<children>
																													<template>
																														<match match="PostalAddress"/>
																														<children>
																															<template>
																																<match match="City"/>
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
																					</template>
																				</children>
																			</paragraph>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext=" (City)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles padding-left="0" padding-right="0"/>
																		<properties height="75" valign="top"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles padding-left="0" padding-right="0"/>
																				<children>
																					<newline/>
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
																												<match match="ContactInformation"/>
																												<children>
																													<template>
																														<match match="PostalAddress"/>
																														<children>
																															<template>
																																<match match="State"/>
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
																					</template>
																				</children>
																			</paragraph>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext=" (State)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties height="75" valign="top"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<newline/>
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
																												<match match="ContactInformation"/>
																												<children>
																													<template>
																														<match match="PostalAddress"/>
																														<children>
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
																					</template>
																				</children>
																			</paragraph>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext=" (Zip Code)"/>
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
																		<properties height="51" width="27%"/>
																		<children>
																			<text fixtext="Taxpayer Identification No. (TIN)"/>
																			<text fixtext="1">
																				<styles font-size="5pt" vertical-align="top"/>
																			</text>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles padding-left="0pt"/>
																		<properties height="51" valign="top" width="28%"/>
																		<children>
																			<choice>
																				<children>
																					<choiceoption>
																						<testexpression>
																							<xpath value="string-length( n1:ResearchAndRelatedProject/n1:ResearchCoverPage/ApplicantOrganization/OrganizationEIN ) &lt;= 0"/>
																						</testexpression>
																						<children>
																							<text fixtext=" "/>
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
																								<match match="ApplicantOrganization"/>
																								<children>
																									<template>
																										<match match="OrganizationEIN"/>
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
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties align="right" height="51" width="20%"/>
																		<children>
																			<text fixtext="DUNS N0,"/>
																			<text fixtext="2">
																				<styles font-size="5pt" vertical-align="top"/>
																			</text>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties height="51" valign="top" width="25%"/>
																		<children>
																			<choice>
																				<children>
																					<choiceoption>
																						<testexpression>
																							<xpath value="string-length(  n1:ResearchAndRelatedProject/n1:ResearchCoverPage/ApplicantOrganization/OrganizationDUNS ) &lt;= 0"/>
																						</testexpression>
																						<children>
																							<text fixtext=" "/>
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
																								<match match="ApplicantOrganization"/>
																								<children>
																									<template>
																										<match match="OrganizationDUNS"/>
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
										<styles padding="0pt"/>
										<children>
											<table>
												<properties border="0" table-layout="fixed" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<properties valign="top" width="55%"/>
																		<children>
																			<newline/>
																			<newline/>
																			<newline/>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext=" Signature of Authorized University Official"/>
																				</children>
																			</paragraph>
																			<text fixtext="(Please use blue ink)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles padding-left="12pt"/>
																		<properties valign="top" width="35%"/>
																		<children>
																			<newline/>
																			<newline/>
																			<newline/>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext="(Date)"/>
																			<newline/>
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
										<styles font-size="8pt"/>
										<children>
											<paragraph paragraphtag="p">
												<styles font-size="8pt"/>
												<children>
													<text fixtext="1">
														<styles font-size="5pt" vertical-align="top"/>
													</text>
													<text fixtext=" The DoD is required by 31 U.S.C. 7701 to obtain each recipient's TIN (usually the Employer Identification Number) for purposes of collecting and reporting on any delinquent amounts that may arise out of the recipient's relationship with the Government."/>
												</children>
											</paragraph>
											<newline/>
											<text fixtext="2">
												<styles font-size="5pt" vertical-align="top"/>
											</text>
											<text fixtext=" The institution's number in the data universal numbering system (DUNS) is a unique nine digit (all numeric) identification number for organizations. Dun	&amp; Bradstreet Corporation assigns it."/>
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
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.6in" papermarginright="0.6in" papermargintop="0.79in" paperwidth="8.5in"/>
	</pagelayout>
</structure>
