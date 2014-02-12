<?xml version="1.0" encoding="UTF-8"?>
<structure version="2" schemafile="C:\Proposal printing\schemas\phs398schema.xsd" workingxmlfile="C:\proposal printing\xml\Proposal_00000702Sponsor_000500.xml" templatexmlfile="">
	<nspair prefix="n1" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
	<nspair prefix="n2" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
	<nspair prefix="n3" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
	<template>
		<styles font-family="Verdana"/>
		<match overwrittenxslmatch="/"/>
		<children>
			<paragraph paragraphtag="p">
				<children>
					<text fixtext="By signing this Signature Page, the Offeror represents and certifies compliance with the attached Certifications and Representations.">
						<styles font-weight="bold"/>
					</text>
				</children>
			</paragraph>
			<newline/>
			<paragraph paragraphtag="p">
				<children>
					<text fixtext="The full text of a solicitaion provision may be accessed electronically at these: "/>
					<text fixtext="heep://farsite.hill.af.mil">
						<styles font-weight="bold" text-decoration="underline"/>
					</text>
					<text fixtext=". "/>
				</children>
			</paragraph>
			<newline/>
			<table>
				<properties table-layout="fixed" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<properties align="center" height="45" valign="center"/>
										<children>
											<newline/>
											<newline/>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="SIGNATURE PAGE">
														<styles font-size="18pt" font-weight="bold" text-decoration="underline"/>
													</text>
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
			<paragraph paragraphtag="p"/>
			<table>
				<properties border="1" table-layout="fixed" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<properties height="66" width="50%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="NAME OF APPLICANT">
														<styles font-weight="bold"/>
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
									<tablecol>
										<properties height="66" width="50%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="RESEATCH TITLE">
														<styles font-weight="bold"/>
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
										<properties colspan="2" height="45" width="50%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="PRINTED NAME AND TITLE OF AUTHORIZED REPRESENTATIVE">
														<styles font-weight="bold"/>
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
																					<text fixtext=",  "/>
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
										<properties height="45" width="50%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="SIGNATURE">
														<styles font-weight="bold"/>
													</text>
												</children>
											</paragraph>
										</children>
									</tablecol>
									<tablecol>
										<properties height="45" width="50%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="DATE">
														<styles font-weight="bold"/>
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
										<properties height="45" width="50%"/>
										<children>
											<paragraph paragraphtag="p"/>
										</children>
									</tablecol>
									<tablecol>
										<properties height="45" width="50%"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="Principal Investigator">
														<styles font-weight="bold"/>
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
																						<styles font-family="Verdana"/>
																					</xpath>
																				</children>
																			</template>
																			<text fixtext=" ">
																				<styles font-family="Verdana"/>
																			</text>
																			<template>
																				<match match="MiddleName"/>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana"/>
																					</xpath>
																					<text fixtext=" "/>
																				</children>
																			</template>
																			<template>
																				<match match="LastName"/>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana"/>
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
			<newline/>
		</children>
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.6in" papermarginright="0.6in" papermargintop="0.79in" paperwidth="8.5in"/>
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
												<styles padding="0"/>
												<properties colspan="2" height="1"/>
											</tablecol>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecol>
												<properties colspan="2" height="1" valign="bottom"/>
												<children>
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
												<styles font-size="7pt" padding="0"/>
												<properties align="left"/>
												<children>
													<text fixtext="   "/>
												</children>
											</tablecol>
											<tablecol>
												<styles font-size="7pt" padding="0"/>
												<properties align="right" width="150"/>
												<children>
													<text fixtext="PAGE K - 1   "/>
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
					<table topdown="0">
						<properties width="100%"/>
						<children>
							<tablebody>
								<children>
									<tablerow>
										<children>
											<tablecol>
												<styles padding="0"/>
												<properties colspan="2" height="30"/>
											</tablecol>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecol>
												<styles font-size="smaller" padding="0"/>
												<properties align="left"/>
												<children>
													<text fixtext="Certifications, Representations and Provisions (Mar 05)">
														<styles font-weight="bold"/>
													</text>
												</children>
											</tablecol>
											<tablecol>
												<styles font-size="smaller" padding="0"/>
												<properties align="right" width="150"/>
											</tablecol>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecol>
												<styles padding="0"/>
												<properties colspan="2"/>
												<children>
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
			</template>
		</headerall>
	</pagelayout>
</structure>
