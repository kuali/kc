<?xml version="1.0" encoding="UTF-8"?>
<structure version="2" schemafile="C:\Proposal printing\schemas\phs398schema.xsd" workingxmlfile="Y:\lmrobin\printing\xml\Proposal_00000702Sponsor_000500.xml" templatexmlfile="">
	<nspair prefix="n1" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
	<nspair prefix="n2" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
	<nspair prefix="n3" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
	<template>
		<styles font-family="Verdana" font-size="10pt"/>
		<match overwrittenxslmatch="/"/>
		<children>
			<table>
				<properties border="0" table-layout="fixed" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<properties colspan="2" width="65%"/>
										<children>
											<text fixtext="The "/>
											<text fixtext="National Environmental Policy Act of 1969">
												<styles font-style="italic"/>
											</text>
											<text fixtext=" (NEPA) requires Federal agencies to consider potential environmental concerns of major federal undertakings. This includes research projects funded by the Air Force Office of Scientific Research (AFOSR). Under the Air Force Environmental Impact Analysis Process, all projects must have an environmental assessment or environmental impact statement completed UNLESS they qualify for a categorical exclusion from this requirement. In order to qualify for this categorical exclusion, proposed research must be normal and routine basic or applied research confined to the laboratory and in compliance with all safety, environmental, and natural resource conservation laws. The following documentation must be completed in order to assist AFOSR in determining whether the proposed research meets the criteria for such categorical exclusion."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding="0"/>
										<properties height="20" width="65%"/>
										<children>
											<table>
												<properties border="0" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecol>
																		<styles border-style="none"/>
																		<properties align="left" height="17" width="10%"/>
																		<children>
																			<text fixtext="The"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-style="none"/>
																		<properties height="17" width="80%"/>
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
																			<text fixtext="                   (Name of Proposing Institution)"/>
																		</children>
																	</tablecol>
																	<tablecol>
																		<styles border-style="none"/>
																		<properties align="center" height="17" width="10%"/>
																		<children>
																			<text fixtext="and"/>
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
										<properties height="20" width="35%"/>
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
											<line>
												<properties color="black" size="1"/>
											</line>
											<text fixtext="        (Name of Investigator)"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties width="65%"/>
										<children>
											<text fixtext="hereby certify as follows:"/>
										</children>
									</tablecol>
									<tablecol>
										<properties width="35%"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties width="65%"/>
										<children>
											<text fixtext="1. All research to be performed under the proposal for research"/>
										</children>
									</tablecol>
									<tablecol>
										<properties width="65%"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles line-height="12pt" padding-left="12pt" padding-right="12pt"/>
										<properties colspan="2" valign="center" width="65%"/>
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
											<text fixtext="                                                                      (Research Title)"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties width="65%"/>
										<children>
											<text fixtext="will be confined to the laboratory, except as disclosed below:"/>
										</children>
									</tablecol>
									<tablecol>
										<properties width="35%"/>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-bottom="0pt"/>
										<properties colspan="2" height="24" valign="bottom" width="65%"/>
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
										<styles padding-bottom="0pt"/>
										<properties colspan="2" height="24" valign="bottom" width="65%"/>
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
										<properties colspan="2" width="65%"/>
										<children>
											<text fixtext="2.	All research identified in number 1 above, will be conducted in compliance with all safety, environmental, and natural resource conservation laws, except as disclosed below:"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties colspan="2" height="24" width="65%"/>
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
										<properties colspan="2" height="24" width="65%"/>
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
										<properties colspan="2" width="65%"/>
										<children>
											<text fixtext="3.	The proposed research does not involve major construction or remodeling of buildings used as research or test facilities."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties colspan="2" height="24" width="65%"/>
										<children>
											<text fixtext="4. Any additional information that will assist AFOSR in accomplishing the required environmental determination:"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<properties colspan="2" height="24" width="65%"/>
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
										<properties colspan="2" height="24" width="65%"/>
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
										<styles padding-bottom="0pt"/>
										<properties colspan="2" height="24" width="65%"/>
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
										<styles padding="0pt"/>
										<properties colspan="2" width="65%"/>
										<children>
											<text fixtext="The parties signing this certification below understand that the Air Force Office of Scientific Research will rely on the certification in making determinations under the Air Force Environmental Impact Analysis Process and whether the proposed research qualifies for a categorical exclusion."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles line-height="12pt"/>
										<properties height="25" width="65%"/>
										<children>
											<newline/>
											<newline/>
											<newline/>
											<newline/>
											<line>
												<properties color="black" size="1"/>
											</line>
											<text fixtext="    (Signature of Authorized Official of the Institution)"/>
										</children>
									</tablecol>
									<tablecol>
										<styles line-height="12pt"/>
										<properties height="25" width="35%"/>
										<children>
											<newline/>
											<newline/>
											<newline/>
											<newline/>
											<line>
												<properties color="black" size="1"/>
											</line>
											<text fixtext="     (Date)"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles line-height="12pt"/>
										<properties height="25" width="65%"/>
										<children>
											<newline/>
											<newline/>
											<newline/>
											<line>
												<properties color="black" size="1"/>
											</line>
											<text fixtext="   (Signature of Principal Investigator)"/>
										</children>
									</tablecol>
									<tablecol>
										<properties height="25" width="35%"/>
										<children>
											<newline/>
											<newline/>
											<newline/>
											<line>
												<properties color="black" size="1"/>
											</line>
											<text fixtext="     (Date)"/>
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
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.5in" papermarginleft="0.4in" papermarginright="0.4in" papermargintop="1in" paperwidth="8.5in"/>
	</pagelayout>
</structure>
