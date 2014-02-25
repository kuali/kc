<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces/>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="E:\GNCS1\SVN\coeusSource\Branches\4.5\Printing\schemas\instituteProposal.xsd" workingxmlfile="institutePropSample.xml">
				<xmltablesupport/>
				<textstateicons/>
			</xsdschemasource>
		</schemasources>
	</schemasources>
	<modules/>
	<flags>
		<scripts/>
		<globalparts/>
		<designfragments/>
		<pagelayouts/>
	</flags>
	<scripts>
		<script language="javascript"/>
	</scripts>
	<globalstyles/>
	<mainparts>
		<children>
			<globaltemplate match="/" matchtype="named" parttype="main">
				<children>
					<template match="$XML" matchtype="schemasource">
						<children>
							<condition>
								<children>
									<conditionbranch xpath="count(   InstituteProposal/DisclosureItem  ) &gt; 0">
										<children>
											<template match="InstituteProposal" matchtype="schemagraphitem">
												<children>
													<template match="SchoolInfo" matchtype="schemagraphitem">
														<children>
															<paragraph paragraphtag="center">
																<styles font-family="Times New Roman" font-size="9pt"/>
																<children>
																	<template match="SchoolName" matchtype="schemagraphitem">
																		<children>
																			<content>
																				<styles font-size="16pt" font-weight="bold"/>
																				<format datatype="string"/>
																			</content>
																		</children>
																	</template>
																	<newline/>
																	<newline/>
																</children>
															</paragraph>
															<newline/>
															<paragraph paragraphtag="center">
																<styles font-family="Times New Roman" font-size="9pt"/>
																<children>
																	<text fixtext="Proposal Summary - Disclosure Status">
																		<styles font-size="14pt" font-weight="bold"/>
																	</text>
																</children>
															</paragraph>
															<newline/>
														</children>
													</template>
												</children>
											</template>
											<newline/>
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<styles font-family="Times New Roman" font-size="9pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties align="right" width="20%"/>
																		<children>
																			<text fixtext="Proposal Number:">
																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles padding="0"/>
																		<children>
																			<table>
																				<properties border="0" width="100%"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="12%"/>
																										<styles padding-left="0" padding-right="0"/>
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<template match="instProposalMaster" matchtype="schemagraphitem">
																														<children>
																															<template match="proposalNumber" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="right" width="28%"/>
																										<styles padding-right="0"/>
																										<children>
																											<text fixtext="Sequence:">
																												<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="15%"/>
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<template match="instProposalMaster" matchtype="schemagraphitem">
																														<children>
																															<template match="SequenceNumber" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format datatype="int"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="45%"/>
																										<children>
																											<text fixtext="Proposal Type: ">
																												<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																											</text>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<template match="instProposalMaster" matchtype="schemagraphitem">
																														<children>
																															<template match="proposalType" matchtype="schemagraphitem">
																																<children>
																																	<template match="proposalTypeDesc" matchtype="schemagraphitem">
																																		<children>
																																			<content>
																																				<format datatype="string"/>
																																			</content>
																																		</children>
																																	</template>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																						</children>
																					</tablebody>
																				</children>
																			</table>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties align="right" width="20%"/>
																		<children>
																			<text fixtext="Account Number:">
																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles padding="0"/>
																		<children>
																			<table>
																				<properties border="0" width="100%"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="12%"/>
																										<styles padding-left="0" padding-right="0"/>
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<template match="instProposalMaster" matchtype="schemagraphitem">
																														<children>
																															<template match="accountNumber" matchtype="schemagraphitem">
																																<children>
																																	<condition>
																																		<children>
																																			<conditionbranch xpath=". != 0">
																																				<children>
																																					<content>
																																						<format datatype="string"/>
																																					</content>
																																				</children>
																																			</conditionbranch>
																																		</children>
																																	</condition>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="right" width="28%"/>
																										<styles padding-right="0"/>
																										<children>
																											<text fixtext="Sponsor Proposal Number:">
																												<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="60%"/>
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<template match="instProposalMaster" matchtype="schemagraphitem">
																														<children>
																															<template match="sponsorProposalNumber" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																						</children>
																					</tablebody>
																				</children>
																			</table>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties align="right" width="20%"/>
																		<children>
																			<text fixtext="Activity Type:">
																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles padding-left="0"/>
																		<children>
																			<template match="InstituteProposal" matchtype="schemagraphitem">
																				<children>
																					<template match="instProposalMaster" matchtype="schemagraphitem">
																						<children>
																							<template match="activityType" matchtype="schemagraphitem">
																								<children>
																									<template match="activityTypeDesc" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties align="right" width="20%"/>
																		<children>
																			<text fixtext="Sponsor:">
																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles padding-left="0"/>
																		<children>
																			<template match="InstituteProposal" matchtype="schemagraphitem">
																				<children>
																					<template match="instProposalMaster" matchtype="schemagraphitem">
																						<children>
																							<template match="sponsor" matchtype="schemagraphitem">
																								<children>
																									<template match="sponsorCode" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</template>
																									<text fixtext="   "/>
																									<template match="sponsorName" matchtype="schemagraphitem">
																										<children>
																											<content>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties align="right" width="20%"/>
																		<children>
																			<text fixtext="Title:">
																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles padding-left="0"/>
																		<children>
																			<template match="InstituteProposal" matchtype="schemagraphitem">
																				<children>
																					<template match="instProposalMaster" matchtype="schemagraphitem">
																						<children>
																							<template match="title" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="2" width="20%"/>
																		<children>
																			<newline/>
																			<newline/>
																			<text fixtext="     This proposal has financial interest disclosures associated with it which are in Pending or Incomplete status.">
																				<styles font-family="Times New Roman" font-size="9pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="2" width="20%"/>
																		<children>
																			<paragraph paragraphtag="p">
																				<styles padding="0" padding-top="8pt"/>
																				<children>
																					<text fixtext="    Disclosures for this award">
																						<styles font-size="12pt" font-weight="bold"/>
																					</text>
																				</children>
																			</paragraph>
																			<template match="InstituteProposal" matchtype="schemagraphitem">
																				<children>
																					<table>
																						<properties border="0"/>
																						<children>
																							<tableheader>
																								<children>
																									<tablerow>
																										<children>
																											<tablecell>
																												<properties width="25%"/>
																												<styles padding-left="20pt"/>
																												<children>
																													<text fixtext="Disclosure Number">
																														<styles font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties width="40%"/>
																												<children>
																													<text fixtext="Investigator">
																														<styles font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties width="15%"/>
																												<children>
																													<text fixtext="Type">
																														<styles font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties width="20%"/>
																												<children>
																													<text fixtext="Status">
																														<styles font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																										</children>
																									</tablerow>
																								</children>
																							</tableheader>
																							<tablebody>
																								<children>
																									<template match="DisclosureItem" matchtype="schemagraphitem">
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="25%"/>
																														<styles padding-left="20pt"/>
																														<children>
																															<template match="DisclosureNumber" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="40%"/>
																														<children>
																															<template match="PersonName" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="15%"/>
																														<children>
																															<template match="DisclosureTypeDesc" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="20%"/>
																														<children>
																															<template match="DisclosureStatusDesc" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																												</children>
																											</tablerow>
																										</children>
																									</template>
																								</children>
																							</tablebody>
																						</children>
																					</table>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="2" width="20%"/>
																		<styles padding="0" padding-left="0"/>
																		<children>
																			<table>
																				<properties border="0" width="100%"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties align="right" width="15%"/>
																										<children>
																											<text fixtext="Okay for release">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="center" width="50%"/>
																										<styles padding-right="30pt"/>
																										<children>
																											<newline/>
																											<newline/>
																											<newline/>
																											<newline/>
																											<line>
																												<properties color="black" size="1"/>
																											</line>
																											<text fixtext="Signed">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="center" width="35%"/>
																										<styles padding-right="45pt"/>
																										<children>
																											<newline/>
																											<newline/>
																											<newline/>
																											<newline/>
																											<line>
																												<properties color="black" size="1"/>
																											</line>
																											<text fixtext="Date">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties align="right" width="15%"/>
																										<children>
																											<text fixtext="Follow-up date:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="center" valign="bottom" width="50%"/>
																										<styles padding-right="30pt"/>
																										<children>
																											<newline/>
																											<newline/>
																											<newline/>
																											<line>
																												<properties color="black" size="1"/>
																											</line>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="35%"/>
																									</tablecell>
																								</children>
																							</tablerow>
																						</children>
																					</tablebody>
																				</children>
																			</table>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<newline break="page"/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<table>
								<properties width="100%"/>
								<styles font-family="Times New Roman" font-size="9pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties align="center"/>
														<children>
															<template match="InstituteProposal" matchtype="schemagraphitem">
																<children>
																	<template match="SchoolInfo" matchtype="schemagraphitem">
																		<children>
																			<paragraph paragraphtag="center">
																				<children>
																					<template match="SchoolName" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<styles font-size="16pt" font-weight="bold"/>
																								<format datatype="string"/>
																							</content>
																						</children>
																					</template>
																					<newline/>
																					<newline/>
																				</children>
																			</paragraph>
																			<newline/>
																			<paragraph paragraphtag="center">
																				<children>
																					<text fixtext="Proposal Summary ">
																						<styles font-size="14pt" font-weight="bold"/>
																					</text>
																				</children>
																			</paragraph>
																			<newline/>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell/>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<styles padding="0"/>
														<children>
															<paragraph paragraphtag="p">
																<children>
																	<table>
																		<properties width="100%"/>
																		<styles table-layout="fixed"/>
																		<children>
																			<tablebody>
																				<children>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties width="15%"/>
																								<children>
																									<text fixtext="Proposal Number:">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="35%"/>
																								<children>
																									<template match="InstituteProposal" matchtype="schemagraphitem">
																										<children>
																											<template match="instProposalMaster" matchtype="schemagraphitem">
																												<children>
																													<template match="proposalNumber" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="18%"/>
																								<children>
																									<text fixtext="Proposal Type:">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="32%"/>
																								<children>
																									<template match="InstituteProposal" matchtype="schemagraphitem">
																										<children>
																											<template match="instProposalMaster" matchtype="schemagraphitem">
																												<children>
																													<template match="proposalType" matchtype="schemagraphitem">
																														<children>
																															<template match="proposalTypeDesc" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties width="15%"/>
																								<children>
																									<text fixtext="Merged With:">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties colspan="3" width="35%"/>
																								<children>
																									<paragraph paragraphtag="pre-wrap">
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="instProposalMaster" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="mergedWith" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</paragraph>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties width="15%"/>
																								<children>
																									<text fixtext="Account Number:">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="35%"/>
																								<children>
																									<template match="InstituteProposal" matchtype="schemagraphitem">
																										<children>
																											<template match="instProposalMaster" matchtype="schemagraphitem">
																												<children>
																													<template match="accountNumber" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="18%"/>
																								<children>
																									<text fixtext="Sponsor Proposal No.:">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="32%"/>
																								<children>
																									<template match="InstituteProposal" matchtype="schemagraphitem">
																										<children>
																											<template match="instProposalMaster" matchtype="schemagraphitem">
																												<children>
																													<template match="sponsorProposalNumber" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties width="15%"/>
																								<children>
																									<text fixtext="Type of Activity:">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="35%"/>
																								<children>
																									<template match="InstituteProposal" matchtype="schemagraphitem">
																										<children>
																											<template match="instProposalMaster" matchtype="schemagraphitem">
																												<children>
																													<template match="activityType" matchtype="schemagraphitem">
																														<children>
																															<template match="activityTypeDesc" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="18%"/>
																								<children>
																									<text fixtext="SubContracts:">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="32%"/>
																								<children>
																									<template match="InstituteProposal" matchtype="schemagraphitem">
																										<children>
																											<template match="instProposalMaster" matchtype="schemagraphitem">
																												<children>
																													<template match="hasSubcontracts" matchtype="schemagraphitem">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath=". = &apos;true&apos;">
																																		<children>
																																			<text fixtext="Yes"/>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch>
																																		<children>
																																			<text fixtext="No"/>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties width="15%"/>
																								<children>
																									<text fixtext="Opportunity ID:">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="35%"/>
																								<children>
																									<template match="InstituteProposal" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="opportunityID" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="18%"/>
																								<children>
																									<text fixtext="CFDA  No:">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="32%"/>
																								<children>
																									<template match="InstituteProposal" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="CFDANum" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<autocalc xpath="concat(  substring( .,1 , 2 )   ,  &quot;.&quot; ,substring-after( . , substring( .,1 , 2 ) )  )"/>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties width="15%"/>
																								<children>
																									<text fixtext="Title of Project:">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties colspan="3" width="85%"/>
																								<children>
																									<template match="InstituteProposal" matchtype="schemagraphitem">
																										<children>
																											<template match="instProposalMaster" matchtype="schemagraphitem">
																												<children>
																													<template match="title" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																				</children>
																			</tablebody>
																		</children>
																	</table>
																</children>
															</paragraph>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<children>
															<table>
																<properties border="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell/>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<text fixtext="Investigators:">
																<styles font-size="12pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<styles padding="0"/>
														<children>
															<template match="InstituteProposal" matchtype="schemagraphitem">
																<children>
																	<template match="investigators" matchtype="schemagraphitem">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="principalInvFlag = &apos;true&apos;">
																						<children>
																							<table>
																								<properties width="100%"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="50%"/>
																														<styles line-height="9pt" padding="0" padding-left="30pt"/>
																														<children>
																															<template match="PIName" matchtype="schemagraphitem">
																																<children>
																																	<template match="fullName" matchtype="schemagraphitem">
																																		<children>
																																			<content>
																																				<styles font-weight="bold"/>
																																				<format datatype="string"/>
																																			</content>
																																		</children>
																																	</template>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties colspan="2" width="50%"/>
																														<children>
																															<text fixtext="Faculty:">
																																<styles font-weight="bold"/>
																															</text>
																															<text fixtext=" "/>
																															<template match="facultyFlag" matchtype="schemagraphitem">
																																<children>
																																	<condition>
																																		<children>
																																			<conditionbranch xpath=". = &apos;Y&apos; or .= &apos;true&apos;">
																																				<children>
																																					<text fixtext="Yes"/>
																																				</children>
																																			</conditionbranch>
																																			<conditionbranch>
																																				<children>
																																					<text fixtext="No"/>
																																				</children>
																																			</conditionbranch>
																																		</children>
																																	</condition>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																												</children>
																											</tablerow>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties colspan="3" valign="top" width="50%"/>
																														<styles padding-left="60pt"/>
																														<children>
																															<paragraph paragraphtag="pre-wrap">
																																<children>
																																	<template match="unit" matchtype="schemagraphitem">
																																		<children>
																																			<condition>
																																				<children>
																																					<conditionbranch xpath="leadUnitFlag = &apos;true&apos;">
																																						<children>
																																							<template match="unitNumber" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<styles font-weight="bold"/>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																							<text fixtext=" : ">
																																								<styles font-weight="bold"/>
																																							</text>
																																							<template match="unitName" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<styles font-weight="bold"/>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</conditionbranch>
																																				</children>
																																			</condition>
																																		</children>
																																	</template>
																																	<template match="unit" matchtype="schemagraphitem">
																																		<children>
																																			<condition>
																																				<children>
																																					<conditionbranch xpath="leadUnitFlag != &apos;true&apos;">
																																						<children>
																																							<template match="unitNumber" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																							<text fixtext=" : ">
																																								<styles font-weight="bold"/>
																																							</text>
																																							<template match="unitName" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</conditionbranch>
																																				</children>
																																			</condition>
																																		</children>
																																	</template>
																																</children>
																															</paragraph>
																														</children>
																													</tablecell>
																												</children>
																											</tablerow>
																										</children>
																									</tablebody>
																								</children>
																							</table>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</template>
																</children>
															</template>
															<newline/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<styles padding="0"/>
														<children>
															<template match="InstituteProposal" matchtype="schemagraphitem">
																<children>
																	<template match="investigators" matchtype="schemagraphitem">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="principalInvFlag !=&apos;true&apos;">
																						<children>
																							<newline/>
																							<table>
																								<properties width="100%"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="50%"/>
																														<styles padding="0" padding-left="30pt"/>
																														<children>
																															<template match="PIName" matchtype="schemagraphitem">
																																<children>
																																	<template match="fullName" matchtype="schemagraphitem">
																																		<children>
																																			<content>
																																				<format datatype="string"/>
																																			</content>
																																		</children>
																																	</template>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties colspan="2" width="50%"/>
																														<children>
																															<text fixtext="Faculty:">
																																<styles font-weight="bold"/>
																															</text>
																															<text fixtext=" "/>
																															<template match="facultyFlag" matchtype="schemagraphitem">
																																<children>
																																	<condition>
																																		<children>
																																			<conditionbranch xpath=". = &apos;Y&apos; or .= &apos;true&apos;">
																																				<children>
																																					<text fixtext="Yes"/>
																																				</children>
																																			</conditionbranch>
																																			<conditionbranch>
																																				<children>
																																					<text fixtext="No"/>
																																				</children>
																																			</conditionbranch>
																																		</children>
																																	</condition>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																												</children>
																											</tablerow>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties colspan="3" valign="top" width="50%"/>
																														<styles padding-left="60pt"/>
																														<children>
																															<paragraph paragraphtag="pre-wrap">
																																<children>
																																	<template match="unit" matchtype="schemagraphitem">
																																		<children>
																																			<condition>
																																				<children>
																																					<conditionbranch xpath="leadUnitFlag = &apos;true&apos;">
																																						<children>
																																							<template match="unitNumber" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<styles font-weight="bold"/>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																							<text fixtext=" : ">
																																								<styles font-weight="bold"/>
																																							</text>
																																							<template match="unitName" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<styles font-weight="bold"/>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</conditionbranch>
																																				</children>
																																			</condition>
																																		</children>
																																	</template>
																																	<template match="unit" matchtype="schemagraphitem">
																																		<children>
																																			<condition>
																																				<children>
																																					<conditionbranch xpath="leadUnitFlag != &apos;true&apos;">
																																						<children>
																																							<template match="unitNumber" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																							<text fixtext=" : ">
																																								<styles font-weight="bold"/>
																																							</text>
																																							<template match="unitName" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</conditionbranch>
																																				</children>
																																			</condition>
																																		</children>
																																	</template>
																																</children>
																															</paragraph>
																														</children>
																													</tablecell>
																												</children>
																											</tablerow>
																										</children>
																									</tablebody>
																								</children>
																							</table>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<children>
															<table>
																<properties border="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell/>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<text fixtext="Budget Data:">
																<styles font-size="12pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<styles padding="0" padding-left="30pt"/>
														<children>
															<table>
																<properties border="0" width="100%"/>
																<styles table-layout="fixed"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<text fixtext="Type of Account:">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="75%"/>
																						<children>
																							<template match="InstituteProposal" matchtype="schemagraphitem">
																								<children>
																									<template match="instProposalMaster" matchtype="schemagraphitem">
																										<children>
																											<template match="accountType" matchtype="schemagraphitem">
																												<children>
																													<condition>
																														<children>
																															<conditionbranch xpath=". = &apos;R&apos;">
																																<children>
																																	<text fixtext="Research"/>
																																</children>
																															</conditionbranch>
																															<conditionbranch>
																																<children>
																																	<text fixtext="Fund"/>
																																</children>
																															</conditionbranch>
																														</children>
																													</condition>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<styles padding="0" padding-left="30pt"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="boolean(  InstituteProposal/budgetData/requestedStartDateInitial or  InstituteProposal/budgetData/requestedEndDateInitial or  InstituteProposal/budgetData/requestedStartDateTotal or  InstituteProposal/budgetData/requestedEndDateTotal  )   or  number(   InstituteProposal/budgetData/totalCostInitial  ) &gt; 0 or  number(  InstituteProposal/budgetData/totalCostTotal  ) &gt; 0">
																		<children>
																			<table>
																				<properties border="0" width="100%"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell/>
																									<tablecell>
																										<children>
																											<text fixtext="Initial Period">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<children>
																											<text fixtext="Total Period">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="25%"/>
																										<children>
																											<text fixtext="Requested Start Date:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="15%"/>
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<template match="budgetData" matchtype="schemagraphitem">
																														<children>
																															<template match="requestedStartDateInitial" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format string="MM/DD/YYYY" datatype="date"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<template match="budgetData" matchtype="schemagraphitem">
																														<children>
																															<template match="requestedStartDateTotal" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format string="MM/DD/YYYY" datatype="date"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="25%"/>
																										<children>
																											<text fixtext="Requested End Date:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="15%"/>
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<template match="budgetData" matchtype="schemagraphitem">
																														<children>
																															<template match="requestedEndDateInitial" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format string="MM/DD/YYYY" datatype="date"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<template match="budgetData" matchtype="schemagraphitem">
																														<children>
																															<template match="requestedEndDateTotal" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format string="MM/DD/YYYY" datatype="date"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="25%"/>
																										<children>
																											<text fixtext="Total Direct Cost:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="15%"/>
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<template match="budgetData" matchtype="schemagraphitem">
																														<children>
																															<template match="totalDirectCostInitial" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format string="#,###,###,##0.00" datatype="decimal"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<template match="budgetData" matchtype="schemagraphitem">
																														<children>
																															<template match="totalDirectCostTotal" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format string="#,###,###,##0.00" datatype="decimal"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="25%"/>
																										<children>
																											<text fixtext="Total Indirect Cost:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="15%"/>
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<template match="budgetData" matchtype="schemagraphitem">
																														<children>
																															<template match="totalIndirectCostInitial" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format string="#,###,###,##0.00" datatype="decimal"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<template match="budgetData" matchtype="schemagraphitem">
																														<children>
																															<template match="totalIndirectCostTotal" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format string="#,###,###,##0.00" datatype="decimal"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="25%"/>
																										<children>
																											<text fixtext="Total All Cost:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="15%"/>
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<template match="budgetData" matchtype="schemagraphitem">
																														<children>
																															<template match="totalCostInitial" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format string="#,###,###,##0.00" datatype="decimal"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<template match="budgetData" matchtype="schemagraphitem">
																														<children>
																															<template match="totalCostTotal" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format string="#,###,###,##0.00" datatype="decimal"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																						</children>
																					</tablebody>
																				</children>
																			</table>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<styles padding="0" padding-left="30pt"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="(boolean( InstituteProposal/instProposalMaster/gradStudentCount ) and number (InstituteProposal/instProposalMaster/gradStudentCount) &gt;0) or  (boolean(  InstituteProposal/instProposalMaster/gradStudentmonths  )and number (InstituteProposal/instProposalMaster/gradStudentmonths) &gt;0)">
																		<children>
																			<table>
																				<properties border="0" width="100%"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties colspan="2" width="25%"/>
																										<children>
																											<text fixtext="Graduate Students">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="25%"/>
																										<styles padding-left="30pt"/>
																										<children>
																											<text fixtext="HeadCount:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="75%"/>
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<template match="instProposalMaster" matchtype="schemagraphitem">
																														<children>
																															<template match="gradStudentCount" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format string="##0" datatype="int"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="25%"/>
																										<styles padding-left="30pt"/>
																										<children>
																											<text fixtext="Person Months:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="75%"/>
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<template match="instProposalMaster" matchtype="schemagraphitem">
																														<children>
																															<template match="gradStudentmonths" matchtype="schemagraphitem">
																																<children>
																																	<content>
																																		<format string="##,##0.00" datatype="double"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																						</children>
																					</tablebody>
																				</children>
																			</table>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<children>
															<table>
																<properties border="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell/>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<text fixtext="Agency Transmittal Data:">
																<styles font-size="12pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<styles padding="0" padding-left="30pt"/>
														<children>
															<table>
																<properties border="0" width="100%"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<text fixtext="Sponsor:">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="75%"/>
																						<children>
																							<template match="InstituteProposal" matchtype="schemagraphitem">
																								<children>
																									<template match="instProposalMaster" matchtype="schemagraphitem">
																										<children>
																											<template match="sponsor" matchtype="schemagraphitem">
																												<children>
																													<template match="sponsorCode" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																													<template match="sponsorName" matchtype="schemagraphitem">
																														<children>
																															<text fixtext=" : "/>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<text fixtext="NSF Code:">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="75%"/>
																						<children>
																							<template match="InstituteProposal" matchtype="schemagraphitem">
																								<children>
																									<template match="instProposalMaster" matchtype="schemagraphitem">
																										<children>
																											<template match="NSFcode" matchtype="schemagraphitem">
																												<children>
																													<content/>
																												</children>
																											</template>
																											<template match="NSFcode" matchtype="schemagraphitem">
																												<children>
																													<template match="NSFcodeDesc" matchtype="schemagraphitem">
																														<children>
																															<text fixtext=" : "/>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<text fixtext="Prime Sponsor:">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="75%"/>
																						<children>
																							<template match="InstituteProposal" matchtype="schemagraphitem">
																								<children>
																									<template match="instProposalMaster" matchtype="schemagraphitem">
																										<children>
																											<template match="primeSponsor" matchtype="schemagraphitem">
																												<children>
																													<template match="sponsorCode" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																													<template match="sponsorName" matchtype="schemagraphitem">
																														<children>
																															<text fixtext=" : "/>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="2" width="25%"/>
																						<styles padding="0"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="boolean( InstituteProposal/mailingInfo/mailToPerson )">
																										<children>
																											<table>
																												<properties border="0" width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="25%"/>
																																		<children>
																																			<text fixtext="Attention:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="75%"/>
																																		<children>
																																			<template match="InstituteProposal" matchtype="schemagraphitem">
																																				<children>
																																					<template match="mailingInfo" matchtype="schemagraphitem">
																																						<children>
																																							<template match="mailToPerson" matchtype="schemagraphitem">
																																								<children>
																																									<template match="fullName" matchtype="schemagraphitem">
																																										<children>
																																											<content>
																																												<format datatype="string"/>
																																											</content>
																																										</children>
																																									</template>
																																								</children>
																																							</template>
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="25%"/>
																																		<children>
																																			<text fixtext="Address:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="75%"/>
																																		<children>
																																			<template match="InstituteProposal" matchtype="schemagraphitem">
																																				<children>
																																					<template match="mailingInfo" matchtype="schemagraphitem">
																																						<children>
																																							<template match="mailToPerson" matchtype="schemagraphitem">
																																								<children>
																																									<template match="address" matchtype="schemagraphitem">
																																										<children>
																																											<content>
																																												<format datatype="string"/>
																																											</content>
																																										</children>
																																									</template>
																																								</children>
																																							</template>
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties colspan="2" width="25%"/>
																																		<styles padding="0"/>
																																		<children>
																																			<table>
																																				<properties border="0" width="100%"/>
																																				<children>
																																					<tablebody>
																																						<children>
																																							<tablerow>
																																								<children>
																																									<tablecell>
																																										<properties width="25%"/>
																																										<children>
																																											<text fixtext="City:">
																																												<styles font-weight="bold"/>
																																											</text>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<properties width="25%"/>
																																										<children>
																																											<template match="InstituteProposal" matchtype="schemagraphitem">
																																												<children>
																																													<template match="mailingInfo" matchtype="schemagraphitem">
																																														<children>
																																															<template match="mailToPerson" matchtype="schemagraphitem">
																																																<children>
																																																	<template match="City" matchtype="schemagraphitem">
																																																		<children>
																																																			<content>
																																																				<format datatype="string"/>
																																																			</content>
																																																		</children>
																																																	</template>
																																																</children>
																																															</template>
																																														</children>
																																													</template>
																																												</children>
																																											</template>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<properties width="8%"/>
																																										<children>
																																											<text fixtext="State:">
																																												<styles font-weight="bold"/>
																																											</text>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<properties width="17%"/>
																																										<children>
																																											<template match="InstituteProposal" matchtype="schemagraphitem">
																																												<children>
																																													<template match="mailingInfo" matchtype="schemagraphitem">
																																														<children>
																																															<template match="mailToPerson" matchtype="schemagraphitem">
																																																<children>
																																																	<template match="state" matchtype="schemagraphitem">
																																																		<children>
																																																			<content>
																																																				<format datatype="string"/>
																																																			</content>
																																																		</children>
																																																	</template>
																																																</children>
																																															</template>
																																														</children>
																																													</template>
																																												</children>
																																											</template>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<properties width="5%"/>
																																										<children>
																																											<text fixtext="Zip:">
																																												<styles font-weight="bold"/>
																																											</text>
																																										</children>
																																									</tablecell>
																																									<tablecell>
																																										<properties width="20%"/>
																																										<children>
																																											<template match="InstituteProposal" matchtype="schemagraphitem">
																																												<children>
																																													<template match="mailingInfo" matchtype="schemagraphitem">
																																														<children>
																																															<template match="mailToPerson" matchtype="schemagraphitem">
																																																<children>
																																																	<template match="zip" matchtype="schemagraphitem">
																																																		<children>
																																																			<content>
																																																				<format datatype="string"/>
																																																			</content>
																																																		</children>
																																																	</template>
																																																</children>
																																															</template>
																																														</children>
																																													</template>
																																												</children>
																																											</template>
																																										</children>
																																									</tablecell>
																																								</children>
																																							</tablerow>
																																						</children>
																																					</tablebody>
																																				</children>
																																			</table>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="25%"/>
																																		<children>
																																			<text fixtext="Phone Number:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="75%"/>
																																		<children>
																																			<template match="InstituteProposal" matchtype="schemagraphitem">
																																				<children>
																																					<template match="mailingInfo" matchtype="schemagraphitem">
																																						<children>
																																							<template match="mailToPerson" matchtype="schemagraphitem">
																																								<children>
																																									<template match="phone" matchtype="schemagraphitem">
																																										<children>
																																											<content>
																																												<format datatype="string"/>
																																											</content>
																																										</children>
																																									</template>
																																								</children>
																																							</template>
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																														</children>
																													</tablebody>
																												</children>
																											</table>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="boolean(  InstituteProposal/instProposalMaster/NoticeOfOpportunity  )">
																										<children>
																											<text fixtext="Notice of Opportunity:">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="75%"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="boolean(  InstituteProposal/instProposalMaster/NoticeOfOpportunity  )">
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<template match="instProposalMaster" matchtype="schemagraphitem">
																														<children>
																															<template match="NoticeOfOpportunity" matchtype="schemagraphitem">
																																<children>
																																	<template match="NoticeOfOppDesc" matchtype="schemagraphitem">
																																		<children>
																																			<content>
																																				<format datatype="string"/>
																																			</content>
																																		</children>
																																	</template>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
														</children>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<condition>
								<children>
									<conditionbranch xpath="boolean( InstituteProposal/mailingInfo/deadlineType ) or  boolean(  InstituteProposal/mailingInfo/mailByOSP  ) or  boolean(  InstituteProposal/mailingInfo/mailType  ) or  string-length(  InstituteProposal/mailingInfo/comments  ) &gt; 0">
										<children>
											<newline/>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell/>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Mailing Information:">
																				<styles font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<styles font-family="Arial" font-size="9pt" padding="0" padding-left="30pt"/>
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="boolean( InstituteProposal/mailingInfo/deadlineType ) or  boolean(  InstituteProposal/mailingInfo/mailByOSP  ) or  boolean(  InstituteProposal/mailingInfo/mailType  ) or  string-length(  InstituteProposal/mailingInfo/comments  ) &gt; 0">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="boolean( InstituteProposal/mailingInfo/deadlineType ) or  boolean(  InstituteProposal/mailingInfo/mailByOSP  ) or  boolean(  InstituteProposal/mailingInfo/mailType  )">
																										<children>
																											<table>
																												<properties width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="25%"/>
																																		<children>
																																			<text fixtext="Deadline Date:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties colspan="2" width="75%"/>
																																		<children>
																																			<template match="InstituteProposal" matchtype="schemagraphitem">
																																				<children>
																																					<template match="mailingInfo" matchtype="schemagraphitem">
																																						<children>
																																							<template match="deadlineDate" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format string="MM/DD/YYYY" datatype="date"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="25%"/>
																																		<children>
																																			<text fixtext="Deadline Type:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties colspan="2" width="75%"/>
																																		<children>
																																			<template match="InstituteProposal" matchtype="schemagraphitem">
																																				<children>
																																					<template match="mailingInfo" matchtype="schemagraphitem">
																																						<children>
																																							<template match="deadlineType" matchtype="schemagraphitem">
																																								<children>
																																									<condition>
																																										<children>
																																											<conditionbranch xpath=". = &apos;R&apos;">
																																												<children>
																																													<text fixtext="Receipt"/>
																																												</children>
																																											</conditionbranch>
																																											<conditionbranch xpath=". = &apos;P&apos;">
																																												<children>
																																													<text fixtext="Postmark"/>
																																												</children>
																																											</conditionbranch>
																																											<conditionbranch/>
																																										</children>
																																									</condition>
																																								</children>
																																							</template>
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="25%"/>
																																		<children>
																																			<text fixtext="Mail By:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties colspan="2" width="75%"/>
																																		<children>
																																			<template match="InstituteProposal" matchtype="schemagraphitem">
																																				<children>
																																					<template match="mailingInfo" matchtype="schemagraphitem">
																																						<children>
																																							<template match="mailByOSP" matchtype="schemagraphitem">
																																								<children>
																																									<condition>
																																										<children>
																																											<conditionbranch xpath=". = &apos;O&apos;">
																																												<children>
																																													<text fixtext="OSP"/>
																																												</children>
																																											</conditionbranch>
																																											<conditionbranch xpath=". = &apos;D&apos;">
																																												<children>
																																													<text fixtext="Department"/>
																																												</children>
																																											</conditionbranch>
																																											<conditionbranch/>
																																										</children>
																																									</condition>
																																								</children>
																																							</template>
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="25%"/>
																																		<children>
																																			<text fixtext="Mail Type:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="25%"/>
																																		<children>
																																			<template match="InstituteProposal" matchtype="schemagraphitem">
																																				<children>
																																					<template match="mailingInfo" matchtype="schemagraphitem">
																																						<children>
																																							<template match="mailType" matchtype="schemagraphitem">
																																								<children>
																																									<condition>
																																										<children>
																																											<conditionbranch xpath=". = &apos;R&apos;">
																																												<children>
																																													<text fixtext="Regular"/>
																																												</children>
																																											</conditionbranch>
																																											<conditionbranch xpath=". = &apos;D&apos;">
																																												<children>
																																													<text fixtext="Express Courier"/>
																																												</children>
																																											</conditionbranch>
																																											<conditionbranch xpath=". = &apos;E&apos;">
																																												<children>
																																													<text fixtext="Electronic"/>
																																												</children>
																																											</conditionbranch>
																																											<conditionbranch/>
																																										</children>
																																									</condition>
																																								</children>
																																							</template>
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="50%"/>
																																		<children>
																																			<condition>
																																				<children>
																																					<conditionbranch xpath="InstituteProposal/mailingInfo/mailByOSP = &apos;O&apos; and  InstituteProposal/mailingInfo/mailType = &apos;D&apos;">
																																						<children>
																																							<text fixtext="Mail Account No:">
																																								<styles font-weight="bold"/>
																																							</text>
																																							<text fixtext="          "/>
																																							<template match="InstituteProposal" matchtype="schemagraphitem">
																																								<children>
																																									<template match="mailingInfo" matchtype="schemagraphitem">
																																										<children>
																																											<template match="mailAccount" matchtype="schemagraphitem">
																																												<children>
																																													<content>
																																														<format datatype="string"/>
																																													</content>
																																												</children>
																																											</template>
																																										</children>
																																									</template>
																																								</children>
																																							</template>
																																							<text fixtext=" "/>
																																						</children>
																																					</conditionbranch>
																																				</children>
																																			</condition>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																														</children>
																													</tablebody>
																												</children>
																											</table>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<condition>
																								<children>
																									<conditionbranch xpath="string-length(  InstituteProposal/mailingInfo/comments  ) &gt; 0">
																										<children>
																											<table>
																												<properties border="0" width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="25%"/>
																																		<styles padding-top="0"/>
																																		<children>
																																			<text fixtext="Comments:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties valign="top" width="75%"/>
																																		<styles padding-top="0"/>
																																		<children>
																																			<template match="InstituteProposal" matchtype="schemagraphitem">
																																				<children>
																																					<template match="mailingInfo" matchtype="schemagraphitem">
																																						<children>
																																							<template match="comments" matchtype="schemagraphitem">
																																								<children>
																																									<paragraph paragraphtag="pre-wrap">
																																										<children>
																																											<content>
																																												<styles line-height="8pt"/>
																																												<format datatype="string"/>
																																											</content>
																																										</children>
																																									</paragraph>
																																								</children>
																																							</template>
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																														</children>
																													</tablebody>
																												</children>
																											</table>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="InstituteProposal/scienceCode">
										<children>
											<newline/>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell/>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Science Code:">
																				<styles font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<condition>
												<children>
													<conditionbranch xpath="InstituteProposal/scienceCode">
														<children>
															<template match="InstituteProposal" matchtype="schemagraphitem">
																<children>
																	<table>
																		<children>
																			<tableheader>
																				<children>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties width="17%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<text fixtext="Code">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="83%"/>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<text fixtext="Description">
																										<styles font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																				</children>
																			</tableheader>
																			<tablebody>
																				<children>
																					<template match="scienceCode" matchtype="schemagraphitem">
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="17%"/>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<template match="scienceCode" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="83%"/>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<template match="scienceCodeDesc" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																						</children>
																					</template>
																				</children>
																			</tablebody>
																		</children>
																	</table>
																</children>
															</template>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="InstituteProposal/IDCRates or  string-length(  InstituteProposal/IDCRatesComments  )  &gt; 0">
										<children>
											<newline/>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell/>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																		<children>
																			<text fixtext="IDC Rates:">
																				<styles font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<styles font-family="Arial" font-size="9pt" padding="0" padding-left="30pt"/>
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="InstituteProposal/IDCRates or  string-length(  InstituteProposal/IDCRatesComments  )  &gt; 0">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="InstituteProposal/IDCRates">
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<table>
																														<children>
																															<tableheader>
																																<children>
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<styles font-family="Arial" font-size="9pt"/>
																																				<children>
																																					<text fixtext="Rate">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" font-size="9pt"/>
																																				<children>
																																					<text fixtext="Type">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" font-size="9pt"/>
																																				<children>
																																					<text fixtext="Year">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" font-size="9pt"/>
																																				<children>
																																					<text fixtext="On Campus">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" font-size="9pt"/>
																																				<children>
																																					<text fixtext="UnderRecovery">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" font-size="9pt"/>
																																				<children>
																																					<text fixtext="Source">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																		</children>
																																	</tablerow>
																																</children>
																															</tableheader>
																															<tablebody>
																																<children>
																																	<template match="IDCRates" matchtype="schemagraphitem">
																																		<children>
																																			<tablerow>
																																				<children>
																																					<tablecell>
																																						<styles font-family="Arial" font-size="9pt"/>
																																						<children>
																																							<template match="rate" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format string="##0.00" datatype="double"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<styles font-family="Arial" font-size="9pt"/>
																																						<children>
																																							<template match="rateType" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<styles font-family="Arial" font-size="9pt"/>
																																						<children>
																																							<template match="FY" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<styles font-family="Arial" font-size="9pt"/>
																																						<children>
																																							<template match="onCampus" matchtype="schemagraphitem">
																																								<children>
																																									<condition>
																																										<children>
																																											<conditionbranch xpath=". = &apos;true&apos;">
																																												<children>
																																													<text fixtext="Yes"/>
																																												</children>
																																											</conditionbranch>
																																											<conditionbranch>
																																												<children>
																																													<text fixtext="No"/>
																																												</children>
																																											</conditionbranch>
																																										</children>
																																									</condition>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<styles font-family="Arial" font-size="9pt"/>
																																						<children>
																																							<template match="underRecovery" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format string="#,###,###,##0.00" datatype="double"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<styles font-family="Arial" font-size="9pt"/>
																																						<children>
																																							<template match="sourceAccount" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																				</children>
																																			</tablerow>
																																		</children>
																																	</template>
																																</children>
																															</tablebody>
																														</children>
																													</table>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<newline/>
																							<condition>
																								<children>
																									<conditionbranch xpath="string-length(  InstituteProposal/IDCRatesComments  )  &gt; 0">
																										<children>
																											<text fixtext=" "/>
																											<table>
																												<properties border="0" width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="17%"/>
																																		<styles font-family="Arial" font-size="9pt"/>
																																		<children>
																																			<text fixtext="Comments:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties valign="top" width="3%"/>
																																		<styles font-family="Arial" font-size="9pt" padding-left="1pt"/>
																																	</tablecell>
																																	<tablecell>
																																		<properties valign="top" width="80%"/>
																																		<styles font-family="Arial" font-size="9pt" padding-left="1pt"/>
																																		<children>
																																			<template match="InstituteProposal" matchtype="schemagraphitem">
																																				<children>
																																					<template match="IDCRatesComments" matchtype="schemagraphitem">
																																						<children>
																																							<paragraph paragraphtag="pre-wrap">
																																								<children>
																																									<content>
																																										<styles line-height="8pt"/>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</paragraph>
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																														</children>
																													</tablebody>
																												</children>
																											</table>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="InstituteProposal/specialReviews">
										<children>
											<newline/>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell/>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Special Review:">
																				<styles font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<styles font-family="Arial" font-size="9pt" padding="0" padding-left="30pt"/>
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="InstituteProposal/specialReviews">
																						<children>
																							<template match="InstituteProposal" matchtype="schemagraphitem">
																								<children>
																									<table>
																										<properties border="0"/>
																										<children>
																											<tableheader>
																												<children>
																													<tablerow>
																														<children>
																															<tablecell>
																																<styles font-family="Arial" font-size="9pt"/>
																																<children>
																																	<text fixtext="Type">
																																		<styles font-weight="bold"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<styles font-family="Arial" font-size="9pt"/>
																																<children>
																																	<text fixtext="Approval">
																																		<styles font-weight="bold"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<styles font-family="Arial" font-size="9pt"/>
																																<children>
																																	<text fixtext="Protocol No">
																																		<styles font-weight="bold"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<styles font-family="Arial" font-size="9pt"/>
																																<children>
																																	<text fixtext="Application Date">
																																		<styles font-weight="bold"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<styles font-family="Arial" font-size="9pt"/>
																																<children>
																																	<text fixtext="Approval Date">
																																		<styles font-weight="bold"/>
																																	</text>
																																</children>
																															</tablecell>
																														</children>
																													</tablerow>
																												</children>
																											</tableheader>
																											<tablebody>
																												<children>
																													<template match="specialReviews" matchtype="schemagraphitem">
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<styles font-family="Arial" font-size="9pt"/>
																																		<children>
																																			<template match="specialReviewType" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<format datatype="string"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<styles font-family="Arial" font-size="9pt"/>
																																		<children>
																																			<template match="specialReviewStatus" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<format datatype="string"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<styles font-family="Arial" font-size="9pt"/>
																																		<children>
																																			<template match="protocolNumber" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<format datatype="string"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<styles font-family="Arial" font-size="9pt"/>
																																		<children>
																																			<template match="applicationDate" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<format string="MM/DD/YYYY" datatype="date"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<styles font-family="Arial" font-size="9pt"/>
																																		<children>
																																			<template match="approvalDate" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<format string="MM/DD/YYYY" datatype="date"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="17%"/>
																																		<styles font-family="Arial" font-size="9pt" padding-left="30pt" padding-top="0"/>
																																		<children>
																																			<condition>
																																				<children>
																																					<conditionbranch xpath="string-length(  comments  ) &gt;0">
																																						<children>
																																							<text fixtext="Comments:">
																																								<styles font-weight="bold"/>
																																							</text>
																																						</children>
																																					</conditionbranch>
																																				</children>
																																			</condition>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties colspan="4" valign="top" width="83%"/>
																																		<styles font-family="Arial" font-size="9pt" padding-top="0"/>
																																		<children>
																																			<condition>
																																				<children>
																																					<conditionbranch xpath="string-length(  comments  ) &gt;0">
																																						<children>
																																							<template match="comments" matchtype="schemagraphitem">
																																								<children>
																																									<paragraph paragraphtag="pre-wrap">
																																										<children>
																																											<content>
																																												<styles line-height="8pt"/>
																																												<format datatype="string"/>
																																											</content>
																																										</children>
																																									</paragraph>
																																								</children>
																																							</template>
																																						</children>
																																					</conditionbranch>
																																				</children>
																																			</condition>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																														</children>
																													</template>
																												</children>
																											</tablebody>
																										</children>
																									</table>
																								</children>
																							</template>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="InstituteProposal/costSharingInfo or  string-length(  InstituteProposal/costSharingComments  ) &gt; 0">
										<children>
											<newline/>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell/>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Cost Sharing:">
																				<styles font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<styles font-family="Arial" font-size="9pt" padding="0" padding-left="30pt"/>
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="InstituteProposal/costSharingInfo or  string-length(  InstituteProposal/costSharingComments  ) &gt; 0">
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="InstituteProposal/costSharingInfo">
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<children>
																													<table>
																														<properties border="0"/>
																														<children>
																															<tableheader>
																																<children>
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<styles font-family="Arial" font-size="9pt"/>
																																				<children>
																																					<text fixtext="Percentage">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" font-size="9pt"/>
																																				<children>
																																					<text fixtext="Type">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" font-size="9pt"/>
																																				<children>
																																					<text fixtext="Project Year">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" font-size="9pt"/>
																																				<children>
																																					<text fixtext="Source Account">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<styles font-family="Arial" font-size="9pt"/>
																																				<children>
																																					<text fixtext="Amount">
																																						<styles font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																		</children>
																																	</tablerow>
																																</children>
																															</tableheader>
																															<tablebody>
																																<children>
																																	<template match="costSharingInfo" matchtype="schemagraphitem">
																																		<children>
																																			<tablerow>
																																				<children>
																																					<tablecell>
																																						<styles font-family="Arial" font-size="9pt"/>
																																						<children>
																																							<template match="percentage" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format string="##0.00" datatype="double"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<styles font-family="Arial" font-size="9pt"/>
																																						<children>
																																							<template match="costSharingType" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<styles font-family="Arial" font-size="9pt"/>
																																						<children>
																																							<template match="FY" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<styles font-family="Arial" font-size="9pt"/>
																																						<children>
																																							<template match="sourceAccount" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<styles font-family="Arial" font-size="9pt"/>
																																						<children>
																																							<template match="amount" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<format string="#,###,###,##0.00" datatype="double"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																				</children>
																																			</tablerow>
																																		</children>
																																	</template>
																																</children>
																															</tablebody>
																														</children>
																													</table>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<condition>
																								<children>
																									<conditionbranch xpath="string-length(  InstituteProposal/costSharingComments  ) &gt;0">
																										<children>
																											<table>
																												<properties border="0" width="100%"/>
																												<children>
																													<tablebody>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties valign="top" width="20%"/>
																																		<styles font-family="Arial" font-size="9pt"/>
																																		<children>
																																			<text fixtext="Comments:">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties valign="top" width="80%"/>
																																		<styles font-family="Arial" font-size="9pt"/>
																																		<children>
																																			<template match="InstituteProposal" matchtype="schemagraphitem">
																																				<children>
																																					<template match="costSharingComments" matchtype="schemagraphitem">
																																						<children>
																																							<paragraph paragraphtag="pre-wrap">
																																								<children>
																																									<content>
																																										<styles line-height="8pt"/>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</paragraph>
																																						</children>
																																					</template>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																														</children>
																													</tablebody>
																												</children>
																											</table>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="string-length( InstituteProposal/instProposalMaster/comments ) &gt; 0">
										<children>
											<newline/>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell/>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Remarks/Summary:">
																				<styles font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<styles font-family="Arial" font-size="9pt" padding-left="30pt"/>
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="string-length( InstituteProposal/instProposalMaster/comments ) &gt; 0">
																						<children>
																							<template match="InstituteProposal" matchtype="schemagraphitem">
																								<children>
																									<template match="instProposalMaster" matchtype="schemagraphitem">
																										<children>
																											<template match="comments" matchtype="schemagraphitem">
																												<children>
																													<paragraph paragraphtag="pre-wrap">
																														<children>
																															<content>
																																<styles line-height="8pt"/>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</paragraph>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</template>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="InstituteProposal/otherData">
										<children>
											<newline/>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell/>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Other Data:">
																				<styles font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<table>
												<properties width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<styles font-family="Arial" font-size="9pt" padding-left="30pt"/>
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="InstituteProposal/otherData">
																						<children>
																							<table>
																								<properties border="0" width="100%"/>
																								<children>
																									<tablebody>
																										<children>
																											<template match="InstituteProposal" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="otherData" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="OtherGroupDetails" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties width="25%"/>
																																				<styles font-family="Arial" font-size="9pt"/>
																																				<children>
																																					<template match="ColumnName" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="5"/>
																																						<children>
																																							<content>
																																								<styles font-weight="bold"/>
																																								<format datatype="string"/>
																																							</content>
																																							<text fixtext=":"/>
																																						</children>
																																					</template>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties width="11%"/>
																																				<styles font-family="Arial" font-size="9pt" padding-left="20pt"/>
																																				<children>
																																					<template match="ColumnValue" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="5"/>
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</template>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties colspan="2" width="39%"/>
																																				<styles font-family="Arial" font-size="9pt"/>
																																				<children>
																																					<template match="ColumnDesc" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="5"/>
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</template>
																																				</children>
																																			</tablecell>
																																		</children>
																																	</tablerow>
																																</children>
																															</template>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablebody>
																								</children>
																							</table>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts>
		<children>
			<globaltemplate match="InstituteProposal" matchtype="schemagraphitem">
				<children>
					<template match="InstituteProposal" matchtype="schemagraphitem">
						<children>
							<content/>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</globalparts>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.65in" papermarginleft="0.4in" papermarginright="0.4in" papermargintop="0.65in" paperwidth="8.5in"/>
		<children>
			<globaltemplate match="#footerall" matchtype="named" parttype="footerall">
				<children>
					<template match="$XML" matchtype="schemasource">
						<children>
							<table>
								<properties border="0" width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="2" height="1"/>
														<styles padding="0"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="2" height="1"/>
														<styles padding="0"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left"/>
														<styles padding="0"/>
														<children>
															<text fixtext="Proposal Number:          ">
																<styles font-family="Times New Roman" font-size="8pt"/>
															</text>
															<template match="InstituteProposal" matchtype="schemagraphitem">
																<children>
																	<template match="instProposalMaster" matchtype="schemagraphitem">
																		<children>
																			<template match="proposalNumber" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<styles font-family="Times New Roman" font-size="8pt"/>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" width="150"/>
														<styles padding="0"/>
														<children>
															<text fixtext="Page: ">
																<styles font-family="Times New Roman" font-size="8pt"/>
															</text>
															<field>
																<styles font-family="Times New Roman" font-size="8pt"/>
															</field>
														</children>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>
