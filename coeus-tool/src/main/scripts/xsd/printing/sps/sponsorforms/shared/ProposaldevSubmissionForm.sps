<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="quirks" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="budget" uri="http://v2.xml.utils.coeus.mit.edu/budget"/>
			<nspair prefix="lookuptypes" uri="http://v2.xml.utils.coeus.mit.edu/lookuptypes"/>
			<nspair prefix="organization" uri="http://v2.xml.utils.coeus.mit.edu/organization"/>
			<nspair prefix="propdev" uri="http://v2.xml.utils.coeus.mit.edu/propdev"/>
			<nspair prefix="rolodex" uri="http://v2.xml.utils.coeus.mit.edu/rolodex"/>
			<nspair prefix="sponsor" uri="http://v2.xml.utils.coeus.mit.edu/sponsor"/>
			<nspair prefix="user_unit" uri="http://v2.xml.utils.coeus.mit.edu/user_unit"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="DevProposal.xsd" workingxmlfile="C:\Coeus\Proposal printing\xml files\propdev\Proposal_Actual_Data.xml">
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
						<editorproperties elementstodisplay="1"/>
						<children>
							<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="1"/>
								<children>
									<table>
										<properties border="0" cellpadding="0" cellspacing="0" width="100%"/>
										<children>
											<tablebody>
												<children>
													<tablerow>
														<children>
															<tablecell>
																<properties colspan="3" height="20"/>
																<children>
																	<table>
																		<properties border="0" cellpadding="0" cellspacing="0" width="100%"/>
																		<children>
																			<tablebody>
																				<children>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties width="55%"/>
																								<children>
																									<image>
																										<target>
																											<xpath value="propdev:LOGO_PATH"/>
																											<fixtext value="PurdueSubmissionFormLogo.jpg"/>
																										</target>
																										<imagesource>
																											<xpath value="propdev:LOGO_PATH"/>
																											<fixtext value="PurdueSubmissionFormLogo.jpg"/>
																										</imagesource>
																									</image>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties rowspan="2" valign="top"/>
																								<children>
																									<table>
																										<properties bgcolor="#B1B1B1" border="1" cellpadding="0" cellspacing="0" width="100%"/>
																										<styles background-color="#d2d2d2" font-size="10pt" font-weight="bold"/>
																										<children>
																											<tablebody>
																												<children>
																													<tablerow>
																														<children>
																															<tablecell>
																																<properties colspan="2"/>
																																<children>
																																	<text fixtext="SPS Use Only">
																																		<styles font-size="10pt" font-weight="bold"/>
																																	</text>
																																</children>
																															</tablecell>
																														</children>
																													</tablerow>
																													<tablerow>
																														<children>
																															<tablecell>
																																<properties align="left" height="17"/>
																																<children>
																																	<text fixtext="Date/Time Received: ">
																																		<styles font-size="10pt" font-weight="bold"/>
																																	</text>
																																	<text fixtext=" ">
																																		<styles font-size="10pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties bgcolor="#FFFFFF" height="17"/>
																															</tablecell>
																														</children>
																													</tablerow>
																													<tablerow>
																														<children>
																															<tablecell>
																																<properties align="left"/>
																																<children>
																																	<text fixtext="Coeus Institute Prop. #: ">
																																		<styles font-size="10pt" font-weight="bold"/>
																																	</text>
																																	<text fixtext=" ">
																																		<styles font-size="10pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties bgcolor="#FFFFFF"/>
																															</tablecell>
																														</children>
																													</tablerow>
																													<tablerow>
																														<children>
																															<tablecell>
																																<properties align="left"/>
																																<children>
																																	<text fixtext="Submission Deadline:">
																																		<styles font-size="10pt" font-weight="bold"/>
																																	</text>
																																	<text fixtext="  ">
																																		<styles font-size="10pt"/>
																																	</text>
																																</children>
																															</tablecell>
																															<tablecell>
																																<properties bgcolor="#FFFFFF"/>
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
																								<properties width="55%"/>
																								<children>
																									<paragraph paragraphtag="h1">
																										<children>
																											<text fixtext="Proposal Submission Form">
																												<styles font-size="20pt"/>
																											</text>
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
															</tablecell>
														</children>
													</tablerow>
													<tablerow>
														<children>
															<tablecell>
																<properties colspan="3" height="20"/>
																<children>
																	<table>
																		<properties border="0" cellpadding="0" cellspacing="0" width="100%"/>
																		<styles background-color="white" border-spacing="inherit" empty-cells="hide" font-size="10pt" font-weight="normal" margin-right="inherit" text-ident="inherit"/>
																		<children>
																			<tablebody>
																				<children>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties colspan="4" height="20" valign="middle"/>
																								<styles background-color="#d2d2d2"/>
																								<children>
																									<text fixtext="Proposal Information">
																										<styles font-size="12pt" font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties height="15" width="25%"/>
																								<children>
																									<text fixtext="Coeus proposal # :">
																										<styles font-size="10pt" font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties colspan="3"/>
																								<children>
																									<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="propdev:PROPOSAL_NUMBER" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<content>
																														<styles font-size="10pt"/>
																														<format datatype="string"/>
																													</content>
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
																								<properties height="15" width="25%"/>
																								<children>
																									<text fixtext="Lead unit:  ">
																										<styles font-size="10pt" font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties colspan="3"/>
																								<children>
																									<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="propdev:LEAD_UNIT" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
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
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties height="15" width="25%"/>
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<text fixtext="Title:">
																												<styles font-size="10pt" font-weight="bold"/>
																											</text>
																										</children>
																									</paragraph>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties colspan="3"/>
																								<children>
																									<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="propdev:TITLE" matchtype="schemagraphitem">
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
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties height="15" width="25%"/>
																								<children>
																									<text fixtext="Sponsor Name:">
																										<styles font-size="10pt" font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties colspan="3"/>
																								<children>
																									<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="sponsor:SPONSOR" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="sponsor:SPONSOR_NAME" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
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
																								<properties height="15" width="25%"/>
																								<children>
																									<text fixtext="Prime Sponsor:">
																										<styles font-size="10pt" font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties colspan="3"/>
																								<children>
																									<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="propdev:PRIME_SPONSOR" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="sponsor:SPONSOR" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<template match="sponsor:SPONSOR_NAME" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
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
																								<properties height="15" width="25%"/>
																								<children>
																									<text fixtext="Principal Investigator:">
																										<styles font-size="10pt" font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties colspan="3"/>
																								<children>
																									<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="propdev:PROP_INVESTIGATORS" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="propdev:PERSON_NAME" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
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
																								<properties height="15" width="25%"/>
																								<children>
																									<text fixtext="Project Period:">
																										<styles font-size="10pt" font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties colspan="3"/>
																								<children>
																									<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="propdev:PERIOD" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
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
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties height="15" width="25%"/>
																								<children>
																									<text fixtext="Proposal Type: ">
																										<styles font-size="10pt" font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="25%"/>
																								<children>
																									<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="lookuptypes:PROPOSAL_TYPE" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="lookuptypes:DESCRIPTION" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
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
																								<properties width="25%"/>
																								<children>
																									<text fixtext="Activity Type:">
																										<styles font-size="10pt" font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="25%"/>
																								<children>
																									<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="lookuptypes:ACTIVITY_TYPE" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="lookuptypes:DESCRIPTION" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
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
																								<properties height="15" width="25%"/>
																								<children>
																									<text fixtext="Notice of Opportunity:">
																										<styles font-size="10pt" font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="25%"/>
																								<children>
																									<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="lookuptypes:NOTICE_OF_OPPORTUNITY" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="lookuptypes:DESCRIPTION" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
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
																								<properties width="25%"/>
																								<children>
																									<text fixtext="Program #:">
																										<styles font-size="10pt" font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="25%"/>
																								<children>
																									<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="propdev:PROGRAM_ANNOUNCEMENT_NUMBER" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
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
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties height="15" width="25%"/>
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<text fixtext="Program Name:">
																												<styles font-size="10pt" font-weight="bold"/>
																											</text>
																										</children>
																									</paragraph>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties colspan="3"/>
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="propdev:PROGRAM_ANNOUNCEMENT_TITLE" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
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
																								<properties height="15" width="25%"/>
																								<children>
																									<text fixtext="Sponsor Cost:">
																										<styles font-size="10pt" font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="25%"/>
																								<children>
																									<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="propdev:SPONSOR_COST" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
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
																								<properties width="25%"/>
																								<children>
																									<text fixtext="Cost Sharing:">
																										<styles font-size="10pt" font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="25%"/>
																								<children>
																									<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<template match="propdev:COST_SHARING" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
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
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties height="25" valign="top" width="25%"/>
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<text fixtext="Business Contact:">
																												<styles font-size="10pt" font-weight="bold"/>
																											</text>
																										</children>
																									</paragraph>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties colspan="3" valign="top"/>
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="organization:ORGANIZATION" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<template match="rolodex:ROLODEX" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<template match="rolodex:NAME" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="1"/>
																																		<children>
																																			<template match="rolodex:FIRST_NAME" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="1"/>
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
																											</template>
																										</children>
																									</paragraph>
																									<newline/>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties height="15" width="25%"/>
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<text fixtext="Comments:">
																												<styles font-size="10pt" font-weight="bold"/>
																											</text>
																										</children>
																									</paragraph>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties colspan="3"/>
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="propdev:OTHER_COMMENTS" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
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
																<properties colspan="3" height="20"/>
																<children>
																	<table>
																		<properties border="0" cellpadding="0" cellspacing="0" width="100%"/>
																		<children>
																			<tablebody>
																				<children>
																					<tablerow>
																						<styles background-color="#b3b3b3"/>
																						<children>
																							<tablecell>
																								<properties colspan="2" height="20" valign="middle"/>
																								<styles background-color="#d2d2d2"/>
																								<children>
																									<text fixtext="Mailing Information">
																										<styles font-size="12pt" font-weight="bold"/>
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
																									<paragraph paragraphtag="pre">
																										<children>
																											<text fixtext="Sponsor Deadline:">
																												<styles font-size="10pt" font-weight="bold"/>
																											</text>
																										</children>
																									</paragraph>
																								</children>
																							</tablecell>
																							<tablecell>
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="propdev:DEADLINE" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<content>
																																<styles font-size="10pt"/>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</paragraph>
																								</children>
																							</tablecell>
																							<tablecell/>
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<text fixtext="Sponsor Address:">
																												<styles font-size="10pt" font-weight="bold"/>
																											</text>
																										</children>
																									</paragraph>
																								</children>
																							</tablecell>
																							<tablecell>
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="propdev:MAIL_ADDRESS" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<template match="rolodex:ROLODEX" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<template match="rolodex:ADDRESS" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="1"/>
																																		<children>
																																			<template match="rolodex:ADDRESS_LINE_1" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="1"/>
																																				<children>
																																					<content>
																																						<styles font-size="10pt"/>
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
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<text fixtext="Mailing Description:">
																												<styles font-size="10pt" font-weight="bold"/>
																											</text>
																											<text fixtext=" ">
																												<styles font-size="10pt"/>
																											</text>
																										</children>
																									</paragraph>
																								</children>
																							</tablecell>
																							<tablecell>
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="propdev:MAIL_DESCRIPTION" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<content>
																																<styles font-size="10pt"/>
																																<format datatype="string"/>
																															</content>
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
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<text fixtext="Add&apos;l mailing info">
																												<styles font-size="10pt" font-weight="bold"/>
																											</text>
																										</children>
																									</paragraph>
																								</children>
																							</tablecell>
																							<tablecell>
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="propdev:ADDL_MAILING_INSTR" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<content>
																																<styles font-size="10pt"/>
																																<format datatype="string"/>
																															</content>
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
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<text fixtext="Submission Method:">
																												<styles font-size="10pt" font-weight="bold"/>
																											</text>
																										</children>
																									</paragraph>
																								</children>
																							</tablecell>
																							<tablecell>
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="propdev:MAIL_TYPE" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<content>
																																<styles font-size="10pt"/>
																																<format datatype="string"/>
																															</content>
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
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<text fixtext="# of copies to sponsor:">
																												<styles font-size="10pt" font-weight="bold"/>
																											</text>
																											<text fixtext=" ">
																												<styles font-size="10pt"/>
																											</text>
																										</children>
																									</paragraph>
																								</children>
																							</tablecell>
																							<tablecell>
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="propdev:NUMBER_OF_COPIES" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<content>
																																<styles font-size="10pt"/>
																																<format datatype="string"/>
																															</content>
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
																<properties colspan="3" height="20"/>
																<children>
																	<table>
																		<properties border="0" cellpadding="0" cellspacing="0" width="100%"/>
																		<styles border="0" clip="auto"/>
																		<children>
																			<tablebody>
																				<children>
																					<tablerow>
																						<styles background-color="#d2d2d2" border-color="#d2d2d2"/>
																						<children>
																							<tablecell>
																								<properties valign="top"/>
																								<children>
																									<text fixtext="Project Information">
																										<styles font-size="12pt" font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties colspan="2" width="463"/>
																								<styles text-align="right"/>
																								<children>
																									<text fixtext="PI Reviewed(initial here):">
																										<styles font-size="10pt"/>
																									</text>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties valign="top"/>
																								<children>
																									<text fixtext="This proposal contains confidential information:  ">
																										<styles font-size="10pt" font-weight="bold"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties colspan="2" width="463"/>
																								<children>
																									<table>
																										<properties border="0" cellpadding="0" cellspacing="0"/>
																										<styles empty-cells="hide"/>
																										<children>
																											<tablebody>
																												<children>
																													<template match="propdev:PROP_YNQ" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<tablerow>
																																<styles empty-cells="hide"/>
																																<children>
																																	<tablecell>
																																		<properties colspan="2" valign="top"/>
																																		<styles empty-cells="hide"/>
																																		<children>
																																			<condition>
																																				<children>
																																					<conditionbranch xpath="propdev:QUESTION_ID  =  &apos;G8&apos;">
																																						<children>
																																							<template match="propdev:ANSWER" matchtype="schemagraphitem">
																																								<editorproperties elementstodisplay="1"/>
																																								<children>
																																									<content>
																																										<styles font-size="10pt"/>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																							<text fixtext=" - ">
																																								<styles font-size="10pt"/>
																																							</text>
																																							<template match="propdev:EXPLANATION" matchtype="schemagraphitem">
																																								<editorproperties elementstodisplay="1"/>
																																								<children>
																																									<content>
																																										<styles font-size="10pt"/>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</conditionbranch>
																																				</children>
																																			</condition>
																																			<newline/>
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
																							</tablecell>
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<text fixtext="Center/Institute affiliation: ">
																												<styles font-size="10pt" font-weight="bold"/>
																											</text>
																										</children>
																									</paragraph>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties colspan="2" width="463"/>
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="propdev:CENTER_INST" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<content>
																																<styles font-size="10pt"/>
																																<format datatype="string"/>
																															</content>
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
																						<properties bgcolor="#d2d2d2"/>
																						<children>
																							<tablecell>
																								<styles font-size="12pt" font-weight="bold"/>
																								<children>
																									<text fixtext="Resource Information"/>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties colspan="2" width="463"/>
																								<styles font-size="10pt" text-align="right"/>
																								<children>
																									<text fixtext="PI Reviewed (initial here):"/>
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
																<properties colspan="3" height="20"/>
																<children>
																	<table>
																		<properties border="0" cellpadding="0" cellspacing="0"/>
																		<children>
																			<tablebody>
																				<children>
																					<template match="propdev:PROP_YNQ" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties height="0"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="propdev:QUESTION_ID  = &apos;A&apos;">
																														<children>
																															<text fixtext="Space available:">
																																<styles font-size="10pt" font-weight="bold"/>
																															</text>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="propdev:QUESTION_ID =&apos;B&apos;">
																														<children>
																															<text fixtext="Equipment available:">
																																<styles font-size="10pt" font-weight="bold"/>
																															</text>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="propdev:QUESTION_ID =&apos;C&apos;">
																														<children>
																															<text fixtext="Computation Time Needed:">
																																<styles font-size="10pt" font-weight="bold"/>
																															</text>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="propdev:QUESTION_ID =&apos;D&apos;">
																														<children>
																															<text fixtext="Data Storage Needed:">
																																<styles font-size="10pt" font-weight="bold"/>
																															</text>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="propdev:QUESTION_ID =&apos;E&apos;">
																														<children>
																															<text fixtext="Technology Funds Available in Proposal:">
																																<styles font-size="10pt" font-weight="bold"/>
																															</text>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="2"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="propdev:QUESTION_ID  = &apos;A&apos;">
																														<children>
																															<template match="propdev:ANSWER" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<styles font-size="10pt"/>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																															<text fixtext=" - ">
																																<styles font-size="10pt"/>
																															</text>
																															<template match="propdev:EXPLANATION" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<styles font-size="10pt"/>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="propdev:QUESTION_ID  = &apos;B&apos;">
																														<children>
																															<template match="propdev:ANSWER" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<styles font-size="10pt"/>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																															<text fixtext=" - ">
																																<styles font-size="10pt"/>
																															</text>
																															<template match="propdev:EXPLANATION" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<styles font-size="10pt"/>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="propdev:QUESTION_ID  = &apos;C&apos;">
																														<children>
																															<template match="propdev:ANSWER" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<styles font-size="10pt"/>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																															<text fixtext=" - ">
																																<styles font-size="10pt"/>
																															</text>
																															<template match="propdev:EXPLANATION" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<styles font-size="10pt"/>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="propdev:QUESTION_ID  = &apos;D&apos;">
																														<children>
																															<template match="propdev:ANSWER" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<styles font-size="10pt"/>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																															<text fixtext=" - ">
																																<styles font-size="10pt"/>
																															</text>
																															<template match="propdev:EXPLANATION" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<styles font-size="10pt"/>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="propdev:QUESTION_ID  = &apos;E&apos;">
																														<children>
																															<template match="propdev:ANSWER" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<styles font-size="10pt"/>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																															<text fixtext=" - ">
																																<styles font-size="10pt"/>
																															</text>
																															<template match="propdev:EXPLANATION" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<content>
																																		<styles font-size="10pt"/>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																											<text fixtext=" ">
																												<styles font-size="10pt"/>
																											</text>
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
															</tablecell>
														</children>
													</tablerow>
													<tablerow>
														<styles font-size="10pt"/>
														<children>
															<tablecell>
																<properties bgcolor="#d2d2d2" colspan="2"/>
																<styles border-bottom-color="#d2d2d2" border-left-color="#d2d2d2" border-right-color="#d2d2d2" border-top-color="#d2d2d2" font-size="12pt" font-weight="bold"/>
																<children>
																	<text fixtext="Regulatory Assurance"/>
																</children>
															</tablecell>
															<tablecell>
																<properties align="right" bgcolor="#d2d2d2"/>
																<styles border-bottom-color="#d2d2d2" border-left-color="#d2d2d2" border-right-color="#d2d2d2" border-top-color="#d2d2d2"/>
																<children>
																	<text fixtext="PI Reviewed (initial here):">
																		<styles font-size="10pt" font-weight="normal"/>
																	</text>
																</children>
															</tablecell>
														</children>
													</tablerow>
													<tablerow>
														<children>
															<tablecell>
																<properties colspan="3" height="20"/>
																<children>
																	<table>
																		<properties border="0" width="100%"/>
																		<children>
																			<tablebody>
																				<children>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties colspan="2"/>
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="count(  propdev:PROP_SPECIAL_REVIEW  ) &gt;0">
																												<children>
																													<table>
																														<properties border="0" cellpadding="0" cellspacing="0"/>
																														<children>
																															<tableheader>
																																<children>
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties height="0"/>
																																				<children>
																																					<text fixtext="Special Review">
																																						<styles font-size="10pt" font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<children>
																																					<text fixtext="Approval Status">
																																						<styles font-size="10pt" font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<children>
																																					<text fixtext="Protocol #">
																																						<styles font-size="10pt" font-weight="bold"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																		</children>
																																	</tablerow>
																																</children>
																															</tableheader>
																															<tablebody>
																																<children>
																																	<template match="propdev:PROP_SPECIAL_REVIEW" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="1"/>
																																		<children>
																																			<tablerow>
																																				<children>
																																					<tablecell>
																																						<properties height="0"/>
																																						<children>
																																							<template match="lookuptypes:SPECIAL_REVIEW" matchtype="schemagraphitem">
																																								<editorproperties elementstodisplay="1"/>
																																								<children>
																																									<template match="lookuptypes:DESCRIPTION" matchtype="schemagraphitem">
																																										<editorproperties elementstodisplay="1"/>
																																										<children>
																																											<content>
																																												<styles font-size="10pt"/>
																																												<format datatype="string"/>
																																											</content>
																																										</children>
																																									</template>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<children>
																																							<template match="lookuptypes:APPLICABLE_REVIEW_TYPE" matchtype="schemagraphitem">
																																								<editorproperties elementstodisplay="1"/>
																																								<children>
																																									<template match="lookuptypes:DESCRIPTION" matchtype="schemagraphitem">
																																										<editorproperties elementstodisplay="1"/>
																																										<children>
																																											<content>
																																												<styles font-size="10pt"/>
																																												<format datatype="string"/>
																																											</content>
																																										</children>
																																									</template>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<children>
																																							<template match="propdev:PROTOCOL_NUMBER" matchtype="schemagraphitem">
																																								<editorproperties elementstodisplay="1"/>
																																								<children>
																																									<content>
																																										<styles font-size="10pt"/>
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
																											</conditionbranch>
																											<conditionbranch xpath="count(  propdev:PROP_SPECIAL_REVIEW  )  &lt;= 0">
																												<children>
																													<text fixtext="None">
																														<styles font-size="10pt"/>
																													</text>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
																								</children>
																							</tablecell>
																							<tablecell/>
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
																<properties colspan="3" height="20"/>
																<children>
																	<newline break="page"/>
																</children>
															</tablecell>
														</children>
													</tablerow>
													<tablerow>
														<children>
															<tablecell>
																<properties colspan="3" height="20"/>
																<children>
																	<table>
																		<properties border="0" cellpadding="0" cellspacing="0"/>
																		<children>
																			<tableheader>
																				<children>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties colspan="4"/>
																							</tablecell>
																						</children>
																					</tablerow>
																				</children>
																			</tableheader>
																			<tablebody>
																				<children>
																					<template match="propdev:PROP_INVESTIGATORS" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties colspan="4"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="propdev:PRINCIPAL_INVESTIGATOR_FLAG  != &apos;Y&apos; and  position()  = 2">
																														<children>
																															<table>
																																<properties border="0" cellpadding="0" cellspacing="0" width="100%"/>
																																<children>
																																	<tablebody>
																																		<children>
																																			<tablerow>
																																				<styles background-color="#d2d2d2"/>
																																				<children>
																																					<tablecell>
																																						<properties height="20" valign="middle"/>
																																						<children>
																																							<text fixtext="Co-Investigator Details">
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
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="propdev:PRINCIPAL_INVESTIGATOR_FLAG  = &apos;Y&apos; and  position()  = 1">
																														<children>
																															<table>
																																<properties border="0" cellpadding="0" cellspacing="0" width="100%"/>
																																<children>
																																	<tablebody>
																																		<children>
																																			<tablerow>
																																				<styles background-color="#d2d2d2"/>
																																				<children>
																																					<tablecell>
																																						<children>
																																							<text fixtext="Principal Investigator Details">
																																								<styles background-color="#d2d2d2" font-size="12pt" font-weight="bold"/>
																																							</text>
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
																										<properties colspan="2" valign="top"/>
																										<children>
																											<template match="propdev:PERSON_NAME" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<content>
																														<styles font-size="9pt" font-style="italic" font-weight="bold"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="right" valign="top" width="30%"/>
																										<children>
																											<text fixtext="Total Percent Credit:">
																												<styles font-size="9pt" font-style="italic" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="10%"/>
																										<children>
																											<template match="propdev:PROP_PER_CREDIT_SPLIT" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="propdev:CREDIT" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<content>
																																<styles font-size="10pt"/>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																													<text fixtext="%">
																														<styles font-size="10pt"/>
																													</text>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties colspan="4"/>
																										<children>
																											<table>
																												<properties border="0" cellpadding="0" cellspacing="0"/>
																												<children>
																													<tableheader>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="244"/>
																																		<children>
																																			<condition>
																																				<children>
																																					<conditionbranch xpath="position()  &lt;=2">
																																						<children>
																																							<text fixtext="Collaborating Department(s)">
																																								<styles font-size="10pt" font-weight="bold"/>
																																							</text>
																																						</children>
																																					</conditionbranch>
																																				</children>
																																			</condition>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center"/>
																																		<children>
																																			<condition>
																																				<children>
																																					<conditionbranch xpath="position()   &lt;=  2">
																																						<children>
																																							<text fixtext="Primary Credit">
																																								<styles font-size="10pt" font-weight="bold"/>
																																							</text>
																																						</children>
																																					</conditionbranch>
																																				</children>
																																			</condition>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties align="center"/>
																																		<children>
																																			<condition>
																																				<children>
																																					<conditionbranch xpath="position()   &lt;=  2">
																																						<children>
																																							<text fixtext="Center/Institute Credit">
																																								<styles font-size="10pt" font-weight="bold"/>
																																							</text>
																																						</children>
																																					</conditionbranch>
																																				</children>
																																			</condition>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																														</children>
																													</tableheader>
																													<tablebody>
																														<children>
																															<template match="propdev:PROP_PER_CREDIT_SPLIT" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<template match="propdev:PROP_UNIT_CREDIT_SPLIT" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="1"/>
																																		<children>
																																			<tablerow>
																																				<children>
																																					<tablecell>
																																						<properties width="244"/>
																																						<children>
																																							<template match="propdev:UNIT" matchtype="schemagraphitem">
																																								<editorproperties elementstodisplay="1"/>
																																								<children>
																																									<content>
																																										<styles font-size="10pt"/>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<properties align="center"/>
																																						<children>
																																							<template match="propdev:PRIMARY" matchtype="schemagraphitem">
																																								<editorproperties elementstodisplay="1"/>
																																								<children>
																																									<content>
																																										<styles font-size="10pt"/>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<properties align="center"/>
																																						<children>
																																							<template match="propdev:SECONDARY" matchtype="schemagraphitem">
																																								<editorproperties elementstodisplay="1"/>
																																								<children>
																																									<content>
																																										<styles font-size="10pt"/>
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
																													</tablebody>
																												</children>
																											</table>
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
															</tablecell>
														</children>
													</tablerow>
													<tablerow>
														<children>
															<tablecell>
																<properties colspan="3" height="20"/>
																<children>
																	<newline break="page"/>
																</children>
															</tablecell>
														</children>
													</tablerow>
													<tablerow>
														<styles background-color="#d2d2d2"/>
														<children>
															<tablecell>
																<properties colspan="3" height="20" valign="middle"/>
																<children>
																	<text fixtext="Disclosures and Assurances">
																		<styles font-size="12pt" font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
														</children>
													</tablerow>
													<tablerow>
														<children>
															<tablecell>
																<properties colspan="3" height="20"/>
																<children>
																	<text fixtext="CONFLICT OF INTEREST:  The proposed project or relationship with the sponsor ">
																		<styles font-size="10pt"/>
																	</text>
																	<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="propdev:COI" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<content>
																						<styles font-size="10pt"/>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																	<text fixtext=" the disclosure of significant financial interests that present an actual or potential conflict of interest for investigators involved in this project.  If answered in the affirmative, then all investigators so involved have provided a complete disclosure of this matter (SPS Form 2, President’s Form 32A and 35), as instructed by current University policy.  By signing this form, all investigators certify that they have read and understand Purdue’s Conflict of Interest policies (executive memorandum C-1 and the Conflict of Interest and Commitment) and made all disclosures required by them (see Investigator Significant Interest Financial Disclosure policy for additional information and guidance.)">
																		<styles font-size="10pt"/>
																	</text>
																	<newline/>
																	<newline/>
																	<text fixtext="CERTIFICATION FOR PRINCIPAL INVESTIGATORS AND CO-PRINCIPAL INVESTIGATORS:  I certify to the best of my knowledge that:  (1)  the statements included within the subject proposal (excluding scientific hypothesis and scientific opinions) are true and complete.  (2)  The text and graphics included within the subject proposal as well as any accompanying publications or other documents, unless otherwise indicated, are the original work of the signatories or individuals working under their supervision.  (3)  I agree to accept responsibility for the scientific conduct of the project and to provide the required progress reports if an award is made as a result of this proposal.  I understand that the willful provision of false information or concealing a material fact in this proposal or any other communication submitted is a criminal offense (U. S. Code, Title 18, Section 1001).">
																		<styles font-size="10pt"/>
																	</text>
																	<newline/>
																</children>
															</tablecell>
														</children>
													</tablerow>
													<tablerow>
														<styles background-color="#d2d2d2"/>
														<children>
															<tablecell>
																<properties colspan="3" height="20" valign="middle"/>
																<children>
																	<text fixtext="Investigator Approvals and Signatures">
																		<styles font-size="12pt" font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
														</children>
													</tablerow>
													<tablerow>
														<children>
															<tablecell>
																<properties colspan="3" height="20"/>
																<children>
																	<text fixtext="To the best of my knowledge the above statements are correct:">
																		<styles font-size="10pt"/>
																	</text>
																	<text fixtext=" "/>
																</children>
															</tablecell>
														</children>
													</tablerow>
													<tablerow>
														<children>
															<tablecell>
																<properties colspan="3" height="20"/>
																<children>
																	<table>
																		<properties border="0" cellpadding="0"/>
																		<children>
																			<tablebody>
																				<children>
																					<template match="propdev:PROP_INVESTIGATORS" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="80%"/>
																										<children>
																											<newline/>
																											<newline/>
																											<newline/>
																											<line>
																												<properties size="1"/>
																											</line>
																											<template match="propdev:PERSON_NAME" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<content>
																														<styles font-size="10pt"/>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																											<condition>
																												<children>
																													<conditionbranch xpath="../propdev:PRINCIPAL_INVESTIGATOR_FLAG =&apos;Y&apos;">
																														<children>
																															<text fixtext="(PI)">
																																<styles font-size="10pt"/>
																															</text>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="../propdev:PRINCIPAL_INVESTIGATOR_FLAG  != &apos;Y&apos;">
																														<children>
																															<text fixtext="(Co-Investigator)">
																																<styles font-size="10pt"/>
																															</text>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top"/>
																										<children>
																											<newline/>
																											<newline/>
																											<newline/>
																											<line>
																												<properties size="1"/>
																											</line>
																											<text fixtext="Date">
																												<styles font-size="10pt"/>
																											</text>
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
															</tablecell>
														</children>
													</tablerow>
													<tablerow>
														<children>
															<tablecell>
																<properties colspan="3" height="20"/>
																<children>
																	<newline break="page"/>
																</children>
															</tablecell>
														</children>
													</tablerow>
													<tablerow>
														<styles background-color="#d2d2d2"/>
														<children>
															<tablecell>
																<properties colspan="3" height="20" valign="middle"/>
																<children>
																	<text fixtext="Unit Approvals and Signatures ">
																		<styles font-size="12pt" font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
														</children>
													</tablerow>
													<tablerow>
														<children>
															<tablecell>
																<properties colspan="3" height="20"/>
																<children>
																	<text fixtext="I approve the proposal for transmission to agency indicated:">
																		<styles font-size="10pt"/>
																	</text>
																</children>
															</tablecell>
														</children>
													</tablerow>
													<tablerow>
														<children>
															<tablecell>
																<properties colspan="3" height="20"/>
																<children>
																	<table>
																		<properties border="0" cellpadding="0" width="100%"/>
																		<children>
																			<tablebody>
																				<children>
																					<template match="propdev:PROP_UNITS" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties valign="bottom" width="80%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="propdev:LEAD_UNIT_FLAG  = &apos;Y&apos;">
																														<children>
																															<newline/>
																															<line>
																																<properties size="1"/>
																															</line>
																															<text fixtext="Department Head administratively responsible for the project">
																																<styles font-size="10pt"/>
																															</text>
																															<newline/>
																															<template match="user_unit:UNIT" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<template match="user_unit:UNIT_NUMBER" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="1"/>
																																		<children>
																																			<content>
																																				<styles font-size="10pt"/>
																																				<format datatype="string"/>
																																			</content>
																																		</children>
																																	</template>
																																	<text fixtext=" - ">
																																		<styles font-size="10pt"/>
																																	</text>
																																	<template match="user_unit:UNIT_NAME" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="1"/>
																																		<children>
																																			<content>
																																				<styles font-size="10pt"/>
																																				<format datatype="string"/>
																																			</content>
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
																									<tablecell>
																										<properties valign="top"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="propdev:LEAD_UNIT_FLAG  = &apos;Y&apos;">
																														<children>
																															<newline/>
																															<newline/>
																															<line>
																																<properties size="1"/>
																															</line>
																															<text fixtext="Date">
																																<styles font-size="10pt"/>
																															</text>
																															<newline/>
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
															</tablecell>
														</children>
													</tablerow>
													<tablerow>
														<children>
															<tablecell>
																<properties colspan="3" height="20"/>
																<children>
																	<table>
																		<properties border="0" cellpadding="0" cellspacing="1" width="100%"/>
																		<children>
																			<tablebody>
																				<children>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties width="80%"/>
																								<children>
																									<newline/>
																									<line>
																										<properties size="1"/>
																									</line>
																									<text fixtext="Dean of School or Director of Institute administratively responsible for this project">
																										<styles font-size="10pt"/>
																									</text>
																									<newline/>
																								</children>
																							</tablecell>
																							<tablecell>
																								<children>
																									<newline/>
																									<line>
																										<properties size="1"/>
																									</line>
																									<text fixtext="Date">
																										<styles font-size="10pt"/>
																									</text>
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
																<properties colspan="3" height="20"/>
																<children>
																	<table>
																		<properties border="0" cellpadding="0" width="100%"/>
																		<children>
																			<tablebody>
																				<children>
																					<template match="propdev:PROP_UNITS" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="80%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="propdev:LEAD_UNIT_FLAG  != &apos;Y&apos;">
																														<children>
																															<newline/>
																															<newline/>
																															<line>
																																<properties size="1"/>
																															</line>
																															<text fixtext="Department Head">
																																<styles font-size="10pt"/>
																															</text>
																															<newline/>
																															<template match="user_unit:UNIT" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="1"/>
																																<children>
																																	<template match="user_unit:UNIT_NUMBER" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="1"/>
																																		<children>
																																			<content>
																																				<styles font-size="10pt"/>
																																				<format datatype="string"/>
																																			</content>
																																		</children>
																																	</template>
																																	<text fixtext=" - ">
																																		<styles font-size="10pt"/>
																																	</text>
																																	<template match="user_unit:UNIT_NAME" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="1"/>
																																		<children>
																																			<content>
																																				<styles font-size="10pt"/>
																																				<format datatype="string"/>
																																			</content>
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
																									<tablecell>
																										<properties valign="top"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="propdev:LEAD_UNIT_FLAG  != &apos;Y&apos;">
																														<children>
																															<newline/>
																															<newline/>
																															<line>
																																<properties size="1"/>
																															</line>
																															<text fixtext="Date">
																																<styles font-size="10pt"/>
																															</text>
																															<newline/>
																															<newline/>
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
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts>
		<children>
			<globaltemplate match="rolodex:ADDRESS" matchtype="schemagraphitem">
				<children>
					<template match="rolodex:ADDRESS" matchtype="schemagraphitem">
						<editorproperties elementstodisplay="1"/>
						<children>
							<template match="rolodex:ORGANIZATION" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="1"/>
								<children>
									<content>
										<format datatype="string"/>
									</content>
								</children>
							</template>
							<newline/>
							<template match="rolodex:ADDRESS_LINE_1" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="1"/>
								<children>
									<content>
										<format datatype="string"/>
									</content>
								</children>
							</template>
							<newline/>
							<template match="rolodex:ADDRESS_LINE_2" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="1"/>
								<children>
									<content>
										<format datatype="string"/>
									</content>
								</children>
							</template>
							<newline/>
							<template match="rolodex:ADDRESS_LINE_3" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="1"/>
								<children>
									<content>
										<format datatype="string"/>
									</content>
								</children>
							</template>
							<newline/>
							<template match="rolodex:CITY" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="1"/>
								<children>
									<content>
										<format datatype="string"/>
									</content>
								</children>
							</template>
							<newline/>
							<template match="lookuptypes:STATE_CODE" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="1"/>
								<children>
									<template match="lookuptypes:DESCRIPTION" matchtype="schemagraphitem">
										<editorproperties elementstodisplay="1"/>
										<children>
											<content>
												<format datatype="string"/>
											</content>
										</children>
									</template>
								</children>
							</template>
							<text fixtext=" ">
								<styles font-size="10pt"/>
							</text>
							<template match="rolodex:POSTAL_CODE" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="1"/>
								<children>
									<content>
										<format datatype="string"/>
									</content>
								</children>
							</template>
							<text fixtext=", ">
								<styles font-size="10pt"/>
							</text>
							<template match="lookuptypes:COUNTRY_CODE" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="1"/>
								<children>
									<template match="lookuptypes:COUNTRY_NAME" matchtype="schemagraphitem">
										<editorproperties elementstodisplay="1"/>
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
					<newline/>
				</children>
			</globaltemplate>
			<globaltemplate match="rolodex:NAME" matchtype="schemagraphitem">
				<children>
					<template match="rolodex:NAME" matchtype="schemagraphitem">
						<editorproperties elementstodisplay="1"/>
						<children>
							<template match="rolodex:LAST_NAME" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="1"/>
								<children>
									<content>
										<format datatype="string"/>
									</content>
								</children>
							</template>
							<text fixtext=" ">
								<styles font-size="10pt"/>
							</text>
							<template match="rolodex:SUFFIX" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="1"/>
								<children>
									<content>
										<format datatype="string"/>
									</content>
								</children>
							</template>
							<text fixtext=", ">
								<styles font-size="10pt"/>
							</text>
							<template match="rolodex:PREFIX" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="1"/>
								<children>
									<content>
										<format datatype="string"/>
									</content>
								</children>
							</template>
							<text fixtext=" ">
								<styles font-size="10pt"/>
							</text>
							<template match="rolodex:FIRST_NAME" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="1"/>
								<children>
									<content>
										<format datatype="string"/>
									</content>
								</children>
							</template>
							<text fixtext=" ">
								<styles font-size="10pt"/>
							</text>
							<template match="rolodex:MIDDLE_NAME" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="1"/>
								<children>
									<content>
										<format datatype="string"/>
									</content>
								</children>
							</template>
							<text fixtext=" ">
								<styles font-size="10pt"/>
							</text>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</globalparts>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="1.6in" papermarginleft="0.6in" papermarginright="0.6in" papermargintop="0.79in" paperwidth="8.5in"/>
		<children>
			<globaltemplate match="#footerall" matchtype="named" parttype="footerall">
				<children>
					<table>
						<properties cellpadding="0" cellspacing="0" width="100%"/>
						<children>
							<tablebody>
								<children>
									<tablerow>
										<children>
											<tablecell>
												<properties colspan="2" height="30"/>
												<styles padding="0"/>
											</tablecell>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecell>
												<properties colspan="2" valign="bottom"/>
												<styles padding="0"/>
												<children>
													<line>
														<properties color="black" size="1"/>
														<styles top="-37pt"/>
													</line>
												</children>
											</tablecell>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecell>
												<properties align="left" valign="bottom"/>
												<styles font-size="smaller" padding="0"/>
												<children>
													<template match="$XML" matchtype="schemasource">
														<editorproperties elementstodisplay="1"/>
														<children>
															<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<table>
																				<properties border="0" cellpadding="0" cellspacing="0" width="100%"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties height="15"/>
																										<children>
																											<text fixtext="Proposal #">
																												<styles font-size="8pt" font-weight="bolder"/>
																											</text>
																											<template match="propdev:PROPOSAL_NUMBER" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<content>
																														<styles font-size="8pt" font-weight="bolder"/>
																														<format datatype="string"/>
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
																										<properties height="15"/>
																										<children>
																											<template match="sponsor:SPONSOR" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="sponsor:SPONSOR_NAME" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<content>
																																<styles font-size="8pt" font-weight="lighter"/>
																																<format datatype="string"/>
																															</content>
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
																										<properties height="15"/>
																										<children>
																											<template match="propdev:PROP_INVESTIGATORS" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<template match="propdev:PERSON_NAME" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="1"/>
																														<children>
																															<content>
																																<styles font-size="8pt" font-weight="lighter"/>
																																<format datatype="string"/>
																															</content>
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
																										<properties height="21"/>
																										<children>
																											<template match="propdev:TITLE" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="1"/>
																												<children>
																													<content>
																														<styles font-size="8pt" font-weight="lighter"/>
																														<format datatype="string"/>
																													</content>
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
																	</template>
																</children>
															</template>
														</children>
													</template>
												</children>
											</tablecell>
											<tablecell>
												<properties align="right" valign="bottom" width="150"/>
												<styles font-size="smaller" padding="0"/>
												<children>
													<text fixtext="Page: ">
														<styles font-size="8pt" font-weight="bolder"/>
													</text>
													<field>
														<styles font-size="8pt" font-weight="bolder"/>
													</field>
													<newline/>
													<template match="$XML" matchtype="schemasource">
														<editorproperties elementstodisplay="1"/>
														<children>
															<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="propdev:CUR_DATE" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<content>
																				<styles font-size="8pt" font-weight="lighter"/>
																				<format datatype="string"/>
																			</content>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</template>
													<newline/>
												</children>
											</tablecell>
										</children>
									</tablerow>
								</children>
							</tablebody>
						</children>
					</table>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>
