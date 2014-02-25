<?xml version="1.0" encoding="UTF-8"?>
<structure version="2" schemafile="C:\Proposal printing\schemas\phs398schema.xsd" workingxmlfile="C:\Proposal printing\xml files\nihproposal.xml" templatexmlfile="">
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
										<styles font-size="12pt"/>
										<properties align="center"/>
										<children>
											<paragraph paragraphtag="p">
												<styles font-size="12pt"/>
												<children>
													<text fixtext="PROTECTION OF PROPRIETARY INFORMATION">
														<styles font-weight="bold"/>
													</text>
												</children>
											</paragraph>
											<text fixtext="DURING EVALUATION AND AFTER AWARD">
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
											<text fixtext="It is the policy of the US Army Research Office to treat all research proposals as privileged information prior to award and to disclose the proposal contents only for evaluation purposes. Technical evaluation ofthese proposals normally are made by highly qualified scientists from the Government and leading scientists and other preeminent experts outside the Government to ascertain their merits. If you wish, you may restrict the evaluation of your proposal to only scientists within the Government. to do so may prevent it from receiving an evaluation by those most qualified to evaluate it. Therefore, we ask permission to send your proposal outside the Government, if necessary, to obtain an unrestricted evaluation."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<text fixtext="All reviewers are made aware that proposals sent to them for evaluation shall not he duplicated, used, or disclosed in whole or in part for any other purpose without the written permission of the offeror."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<text fixtext="You should be aware that despite all precautions, we can protect the confidentiality of proprietary information contained in proposals only to the extent that it is exempt from disclosure under the Freedom of Information Act (FOIA: 5 U.S.C. 552). Generally, Exemption 4 of the FOIA [5 U.S.C. 552(b)(4)] will protect from release information submitted to the Government that constitutes either (1) a trade secret or (2) commercial or financial information which is privileged or confidential. Any such proprietary information contained in your proposal should be marked in accordance with FAR 15.509."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<text fixtext="Please complete the following statement indicating your proposal treatment preference during the evaluation phase and, should a contract be awarded, afterwards."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles font-size="10pt"/>
										<properties align="center"/>
										<children>
											<text fixtext="STATEMENT OF DISCLOSURE PREFERENCE">
												<styles font-weight="bold"/>
											</text>
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
																		<properties align="center" valign="top" width="80%"/>
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
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext=" (Organization)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="20%"/>
																		<children>
																			<text fixtext="in submitting proposal"/>
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
																		<properties height="14" valign="top" width="5%"/>
																		<children>
																			<text fixtext="titled"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties height="14" width="90%"/>
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
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties height="14" valign="top" width="5%"/>
																		<children>
																			<text fixtext="with"/>
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
																		<properties width="35%"/>
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
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext=" (Name)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="65%"/>
																		<children>
																			<text fixtext="Principal Investigator requires the following procedure be used during its evaluation:"/>
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
										<children>
											<newline/>
											<text fixtext="(  ) Permission is hereby granted to the US Army Research Office to evaluate this proposal in accordance with its normal procedures which may include evaluation by reviewers both within and outside the Government."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<newline/>
											<text fixtext="(  ) Restrict the evaluation of the above proposal to Government reviewers only."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<children>
											<newline/>
											<text fixtext="To reduce administrative requirements, you may complete the following. information by checking the appropriate blocks:"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-left="50pt" padding-right="50pt"/>
										<children>
											<newline/>
											<text fixtext="If this proposal results in a contract, the offeror grants ARO the authority to release the following
portion of its proposal in response to requests under the Freedom of information Act, only after such award, without prior contact with the offeror.
"/>
											<newline/>
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
																		<styles padding-left="50pt"/>
																		<properties width="35%"/>
																		<children>
																			<text fixtext="Cover Page"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="30%"/>
																		<children>
																			<text fixtext="__">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext=" Yes   "/>
																			<text fixtext="__">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext=" No"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="20%"/>
																		<children>
																			<text fixtext="Statement of Work"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles padding-right="50pt"/>
																		<properties align="right" width="25%"/>
																		<children>
																			<text fixtext="__">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext=" Yes   "/>
																			<text fixtext="__">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext=" No"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles padding-left="50pt"/>
																		<properties width="35%"/>
																		<children>
																			<text fixtext="Project Abstract"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="30%"/>
																		<children>
																			<text fixtext="__">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext=" Yes   "/>
																			<text fixtext="__">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext=" No"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="20%"/>
																		<children>
																			<text fixtext="Biographical Sketch"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles padding-right="50pt"/>
																		<properties align="right" width="25%"/>
																		<children>
																			<text fixtext="__">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext=" Yes   "/>
																			<text fixtext="__">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext=" No"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles padding-left="50pt"/>
																		<properties width="35%"/>
																		<children>
																			<text fixtext="Technical Proposal"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="30%"/>
																		<children>
																			<text fixtext="__">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext=" Yes   "/>
																			<text fixtext="__">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext=" No"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="20%"/>
																		<children>
																			<text fixtext="Cost Proposal/Budget"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles padding-right="50pt"/>
																		<properties align="right" width="25%"/>
																		<children>
																			<text fixtext="__">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext=" Yes   "/>
																			<text fixtext="__">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext=" No"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<styles padding-left="50pt"/>
																		<properties width="35%"/>
																		<children>
																			<text fixtext="Current &amp; Pending Support"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="30%"/>
																		<children>
																			<text fixtext="__">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext=" Yes   "/>
																			<text fixtext="__">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext=" No"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="20%"/>
																		<children>
																			<text fixtext="Bibliography"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles padding-right="50pt"/>
																		<properties align="right" width="25%"/>
																		<children>
																			<text fixtext="__">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext=" Yes   "/>
																			<text fixtext="__">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext=" No"/>
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
																		<properties align="center" valign="top" width="30%"/>
																		<children>
																			<newline/>
																			<newline/>
																			<newline/>
																			<newline/>
																			<newline/>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext="Date"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="20%"/>
																	</tablecol>
																	<tablecol>
																		<properties align="center" valign="top" width="50%"/>
																		<children>
																			<newline/>
																			<newline/>
																			<newline/>
																			<newline/>
																			<newline/>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext="Signature of person authorized to sign for organization"/>
																		</children>
																	</tablecol>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecol>
																		<properties align="center" valign="top" width="30%"/>
																		<children>
																			<newline/>
																			<newline/>
																			<newline/>
																			<newline/>
																			<newline/>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext="Date"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<properties width="20%"/>
																	</tablecol>
																	<tablecol>
																		<properties align="center" valign="top" width="50%"/>
																		<children>
																			<newline/>
																			<newline/>
																			<newline/>
																			<newline/>
																			<newline/>
																			<line>
																				<properties color="black" size="1"/>
																			</line>
																			<text fixtext="Signature of Principal Investigator"/>
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
										<children>
											<paragraph paragraphtag="p">
												<children>
													<newline/>
													<text fixtext="ARO Form 52A "/>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="(Revised May 97)"/>
												</children>
											</paragraph>
											<text fixtext="USE THIS FORM FOR EDUCATIONAL INSTITUTIONS AND NON-PROFIT ORGANIZATIONS"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties align="center" height="1" valign="bottom"/>
										<children>
											<newline/>
											<newline/>
											<newline/>
											<text fixtext="Page B- I"/>
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
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.5in" papermarginleft="0.6in" papermarginright="0.6in" papermargintop="0.6in" paperwidth="8.5in"/>
	</pagelayout>
</structure>
