<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="quirks" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<predefinedformats>
		<format string="###,##0" datatype="decimal"/>
	</predefinedformats>
	<predefinedformats>
		<format string="MM / DD / YYYY" datatype="date"/>
	</predefinedformats>
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="approvalRouting" uri="http://v2.xml.utils.coeus.mit.edu/ApprovalRouting"/>
			<nspair prefix="budget" uri="http://v2.xml.utils.coeus.mit.edu/budget"/>
			<nspair prefix="lookuptypes" uri="http://v2.xml.utils.coeus.mit.edu/lookuptypes"/>
			<nspair prefix="modularbudget" uri="http://v2.xml.utils.coeus.mit.edu/modularbudget"/>
			<nspair prefix="organization" uri="http://v2.xml.utils.coeus.mit.edu/organization"/>
			<nspair prefix="propdev" uri="http://v2.xml.utils.coeus.mit.edu/propdev"/>
			<nspair prefix="questionnaire" uri="http://v2.xml.utils.coeus.mit.edu/questionnaire"/>
			<nspair prefix="rateandbase" uri="http://v2.xml.utils.coeus.mit.edu/rateandbase"/>
			<nspair prefix="rolodex" uri="http://v2.xml.utils.coeus.mit.edu/rolodex"/>
			<nspair prefix="sponsor" uri="http://v2.xml.utils.coeus.mit.edu/sponsor"/>
			<nspair prefix="user_unit" uri="http://v2.xml.utils.coeus.mit.edu/user_unit"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="E:\GNCS1\SVN\coeusSource\Branches\4.5\Printing\schemas\v2\DevProposal.xsd">
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
					<newline/>
					<template match="$XML" matchtype="schemasource">
						<editorproperties elementstodisplay="1"/>
						<children>
							<newline/>
							<paragraph paragraphtag="center">
								<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
								<children>
									<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
										<editorproperties elementstodisplay="5"/>
										<children>
											<template match="propdev:SCHOOL_INFO_TYPE" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<template match="propdev:SchoolName" matchtype="schemagraphitem">
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
							<newline/>
							<paragraph paragraphtag="center">
								<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
								<children>
									<text fixtext="Proposal Development Summary">
										<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
									</text>
								</children>
							</paragraph>
							<paragraph paragraphtag="p"/>
							<text fixtext="Proposal Details">
								<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
							</text>
							<text fixtext=" "/>
							<newline/>
							<newline/>
							<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="5"/>
								<children>
									<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
										<editorproperties elementstodisplay="5"/>
										<children>
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<styles font-family="Arial" font-size="9pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties height="18" width="18%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Sponsor:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="3" width="34%"/>
																		<children>
																			<template match="sponsor:SPONSOR" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="sponsor:SPONSOR_CODE" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<content>
																								<format datatype="string"/>
																							</content>
																						</children>
																					</template>
																					<text fixtext=" : "/>
																					<template match="sponsor:SPONSOR_NAME" matchtype="schemagraphitem">
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
																		<properties height="18" width="18%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Prime Sponsor:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="3" width="34%"/>
																		<children>
																			<template match="propdev:PRIME_SPONSOR" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="sponsor:SPONSOR" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="sponsor:SPONSOR_CODE" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																									<text fixtext=" : "/>
																								</children>
																							</template>
																							<template match="sponsor:SPONSOR_NAME" matchtype="schemagraphitem">
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
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties height="18" width="18%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Proposal Title:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="3" width="34%"/>
																		<children>
																			<paragraph paragraphtag="pre-wrap">
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
																			</paragraph>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties height="18" width="18%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Start Date:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="34%"/>
																		<children>
																			<template match="propdev:REQUESTED_START_DATE_INITIAL" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format string="MM/DD/YYYY" datatype="dateTime"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="10%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="End Date:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="38%"/>
																		<children>
																			<template match="propdev:REQUESTED_END_DATE_INITIAL" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format string="MM/DD/YYYY" datatype="dateTime"/>
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
																		<properties height="18" width="18%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Proposal Type:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="3" width="34%"/>
																		<children>
																			<template match="lookuptypes:PROPOSAL_TYPE" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="lookuptypes:DESCRIPTION" matchtype="schemagraphitem">
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
																		<properties height="18" width="18%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Activity Type:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="3" width="34%"/>
																		<children>
																			<template match="lookuptypes:ACTIVITY_TYPE" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="lookuptypes:DESCRIPTION" matchtype="schemagraphitem">
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
																		<properties height="18" width="18%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Anticipate Award Type:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="3" width="34%"/>
																		<children>
																			<template match="lookuptypes:ANTICIPATED_AWARD_TYPE" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="lookuptypes:DESCRIPTION" matchtype="schemagraphitem">
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
																		<properties height="18" width="18%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Proposal Deadline Date:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="3" width="34%"/>
																		<children>
																			<template match="propdev:DEADLINE_DATE" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format string="MM/DD/YYYY" datatype="dateTime"/>
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
							<newline/>
							<newline/>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="count(  //propdev:PROPOSAL/propdev:PROP_INVESTIGATORS  )  &gt; 0">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<newline/>
											<text fixtext="Investigators">
												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
											</text>
											<text fixtext=" ">
												<styles font-family="Arial" font-size="10pt"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
												<styles font-family="Arial" font-size="9pt"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties width="25%"/>
																		<children>
																			<text fixtext="Person Name"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties width="45%"/>
																		<children>
																			<text fixtext="Units"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties width="10%"/>
																		<children>
																			<text fixtext="Faculty"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties width="10%"/>
																		<children>
																			<text fixtext="Multi PI"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties width="10%"/>
																		<children>
																			<text fixtext="%Effort"/>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="propdev:PROP_INVESTIGATORS_BASIC_DETAILS" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<template match="propdev:PERSON_NAME" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																									<condition>
																										<children>
																											<conditionbranch xpath="../propdev:PRINCIPAL_INVESTIGATOR_FLAG = &apos;Y&apos;">
																												<children>
																													<text fixtext="(PI)"/>
																												</children>
																											</conditionbranch>
																										</children>
																									</condition>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="45%"/>
																						<children>
																							<template match="propdev:PROP_UNITS" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<newline/>
																									<table>
																										<properties border="0" cellpadding="1" cellspacing="0" width="100%"/>
																										<children>
																											<tablebody>
																												<children>
																													<template match="user_unit:UNIT" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="15%"/>
																																		<children>
																																			<template match="user_unit:UNIT_NUMBER" matchtype="schemagraphitem">
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
																																		<properties width="85%"/>
																																		<children>
																																			<template match="user_unit:UNIT_NAME" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<content>
																																						<format datatype="string"/>
																																					</content>
																																					<condition>
																																						<children>
																																							<conditionbranch xpath="../../propdev:LEAD_UNIT_FLAG =&apos;Y&apos;">
																																								<children>
																																									<text fixtext="(LU)"/>
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
																														</children>
																													</template>
																												</children>
																											</tablebody>
																										</children>
																									</table>
																									<newline/>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="propdev:FACULTY_FLAG = &apos;Y&apos;">
																										<children>
																											<text fixtext="Yes"/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<condition>
																								<children>
																									<conditionbranch xpath="propdev:FACULTY_FLAG = &apos;N&apos;">
																										<children>
																											<text fixtext="No"/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<newline/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="propdev:MULTI_PI_FLAG = &apos;Y&apos;">
																										<children>
																											<text fixtext="Yes"/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<condition>
																								<children>
																									<conditionbranch xpath="propdev:MULTI_PI_FLAG =&apos;N&apos;">
																										<children>
																											<text fixtext="No"/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</tablecell>
																					<tablecell>
																						<children>
																							<template match="propdev:PERCENTAGE_EFFORT" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format string="###,##0.00" datatype="decimal"/>
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
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="count(  //propdev:PROP_PER_CREDIT_SPLIT  )  &gt; 0">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<text fixtext="Credit Split">
												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
											</text>
											<newline/>
											<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<template match="propdev:CREDIT_SPLIT_COLUMNS" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<newline/>
															<table>
																<properties border="0.5" cellpadding="0" cellspacing="0" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<styles font-weight="bold"/>
																				<children>
																					<tablecell>
																						<properties align="center" width="44%"/>
																					</tablecell>
																					<tablecell>
																						<properties align="center" width="14%"/>
																						<children>
																							<paragraph paragraphtag="pre-wrap">
																								<children>
																									<template match="propdev:ColumnName1" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<content>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</paragraph>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="center" width="14%"/>
																						<children>
																							<paragraph paragraphtag="pre-wrap">
																								<children>
																									<template match="propdev:ColumnName2" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<content>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</paragraph>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="center" width="14%"/>
																						<children>
																							<paragraph paragraphtag="pre-wrap">
																								<children>
																									<template match="propdev:ColumnName3" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<content>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</paragraph>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="center" width="14%"/>
																						<children>
																							<paragraph paragraphtag="pre-wrap">
																								<children>
																									<template match="propdev:ColumnName4" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<content>
																												<format datatype="string"/>
																											</content>
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
													</template>
												</children>
											</template>
											<newline/>
											<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<template match="propdev:PROP_PER_CREDIT_SPLIT" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties valign="top" width="44%"/>
																						<styles font-weight="bold"/>
																						<children>
																							<template match="propdev:PERSON_NAME" matchtype="schemagraphitem">
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
																						<properties align="right" width="14%"/>
																						<children>
																							<template match="propdev:ColumnValue1" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format datatype="decimal"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="14%"/>
																						<children>
																							<template match="propdev:ColumnValue2" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format datatype="decimal"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="14%"/>
																						<children>
																							<template match="propdev:ColumnValue3" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format datatype="decimal"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="right" width="14%"/>
																						<children>
																							<template match="propdev:ColumnValue4" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format datatype="decimal"/>
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
															<condition>
																<children>
																	<conditionbranch xpath="count(  //propdev:PROPOSAL/propdev:PROP_PER_CREDIT_SPLIT/propdev:PROP_UNIT_CREDIT_SPLIT  )  &gt;0">
																		<children>
																			<newline/>
																			<template match="propdev:PROP_UNIT_CREDIT_SPLIT" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<newline/>
																					<table>
																						<properties border="0" width="100%"/>
																						<styles font-family="Arial" font-size="9pt"/>
																						<children>
																							<tablebody>
																								<children>
																									<tablerow>
																										<children>
																											<tablecell>
																												<properties valign="top" width="44%"/>
																												<children>
																													<paragraph paragraphtag="pre-wrap">
																														<children>
																															<template match="propdev:UNIT" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<text fixtext="   "/>
																																	<content>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																															<text fixtext=" :  "/>
																															<template match="propdev:UNIT_NAME" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</paragraph>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties align="right" width="14%"/>
																												<children>
																													<template match="propdev:ColumnValue1" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<content>
																																<format datatype="decimal"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties align="right" width="14%"/>
																												<children>
																													<template match="propdev:ColumnValue2" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<content>
																																<format datatype="decimal"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties align="right" width="14%"/>
																												<children>
																													<template match="propdev:ColumnValue3" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<content>
																																<format datatype="decimal"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties align="right" width="14%"/>
																												<children>
																													<template match="propdev:ColumnValue4" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<content>
																																<format datatype="decimal"/>
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
																			<newline/>
																			<table>
																				<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
																				<styles font-family="Arial" font-size="9pt"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<styles font-weight="bold"/>
																								<children>
																									<tablecell>
																										<properties width="44%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Unit Total"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="right" height="13" width="14%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="string-length(  //propdev:PROPOSAL/propdev:CREDIT_SPLIT_COLUMNS/propdev:ColumnName1  )  &gt; 0">
																														<children>
																															<template match="propdev:TotalUnitColumnValue1" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<format datatype="decimal"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="right" height="13" width="14%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="string-length(  //propdev:PROPOSAL/propdev:CREDIT_SPLIT_COLUMNS/propdev:ColumnName2  )  &gt; 0">
																														<children>
																															<template match="propdev:TotalUnitColumnValue2" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<format datatype="decimal"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="right" height="13" width="14%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="string-length(  //propdev:PROPOSAL/propdev:CREDIT_SPLIT_COLUMNS/propdev:ColumnName3  )  &gt; 0">
																														<children>
																															<template match="propdev:TotalUnitColumnValue3" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<format datatype="decimal"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="right" width="14%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="string-length(  //propdev:PROPOSAL/propdev:CREDIT_SPLIT_COLUMNS/propdev:ColumnName4  )  &gt; 0">
																														<children>
																															<template match="propdev:TotalUnitColumnValue4" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<format datatype="decimal"/>
																																	</content>
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
															<newline/>
														</children>
													</template>
												</children>
											</template>
											<newline/>
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
											<table>
												<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
												<styles font-family="Arial" font-size="9pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<styles font-weight="bold"/>
																<children>
																	<tablecell>
																		<properties width="44%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Investigator Total"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="right" height="13" width="14%"/>
																		<children>
																			<autocalc xpath="sum(  //propdev:PROPOSAL/propdev:PROP_PER_CREDIT_SPLIT/propdev:ColumnValue1  )">
																				<format string="###,##0.00" datatype="decimal"/>
																			</autocalc>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="right" height="13" width="14%"/>
																		<children>
																			<autocalc xpath="sum(  //propdev:PROPOSAL/propdev:PROP_PER_CREDIT_SPLIT/propdev:ColumnValue2  )">
																				<format string="###,##0.00" datatype="decimal"/>
																			</autocalc>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="right" height="13" width="14%"/>
																		<children>
																			<autocalc xpath="sum(  //propdev:PROPOSAL/propdev:PROP_PER_CREDIT_SPLIT/propdev:ColumnValue3  )">
																				<format string="###,##0.00" datatype="decimal"/>
																			</autocalc>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties align="right" height="13" width="14%"/>
																		<children>
																			<autocalc xpath="sum(  //propdev:PROPOSAL/propdev:PROP_PER_CREDIT_SPLIT/propdev:ColumnValue4 )">
																				<format string="###,##0.00" datatype="decimal"/>
																			</autocalc>
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
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="count(  //propdev:PROPOSAL/propdev:PROP_KEY_PERSONS  )  &gt; 0">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<text fixtext="Study Personnel">
												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
												<styles font-family="Arial" font-size="9pt"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties height="1" width="25%"/>
																		<children>
																			<text fixtext="Person Name"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="1" width="45%"/>
																		<children>
																			<text fixtext="Role"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="1" width="15%"/>
																		<children>
																			<text fixtext="Faculty"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="1" width="15%"/>
																		<children>
																			<text fixtext="%Effort"/>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="propdev:PROP_KEY_PERSONS" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<template match="propdev:PERSON_NAME" matchtype="schemagraphitem">
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
																						<properties width="45%"/>
																						<children>
																							<template match="propdev:PROJECT_ROLE" matchtype="schemagraphitem">
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
																						<properties width="15%"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="propdev:FACULTY_FLAG = &apos;Y&apos;">
																										<children>
																											<text fixtext="Yes"/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<condition>
																								<children>
																									<conditionbranch xpath="propdev:FACULTY_FLAG = &apos;N&apos;">
																										<children>
																											<text fixtext="No"/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="propdev:PERCENTAGE_EFFORT" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format string="###,##0.00" datatype="decimal"/>
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
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="count(  //propdev:PROPOSAL/propdev:PROPOSAL_SITES  )  &gt; 0">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<newline/>
											<text fixtext="Organizations">
												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
												<styles font-family="Arial" font-size="9pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<styles font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties width="20%"/>
																		<children>
																			<text fixtext="Type"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties width="30%"/>
																		<children>
																			<text fixtext="Location"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties width="50%"/>
																		<children>
																			<text fixtext="Address"/>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<template match="propdev:PROPOSAL_SITES" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<newline/>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="20%"/>
																						<children>
																							<template match="lookuptypes:LOCATION" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="lookuptypes:DESCRIPTION" matchtype="schemagraphitem">
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
																						<properties width="30%"/>
																						<children>
																							<template match="propdev:OrganizationName" matchtype="schemagraphitem">
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
																						<properties width="50%"/>
																						<children>
																							<template match="propdev:RolodexFullAddess" matchtype="schemagraphitem">
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
																	</tablebody>
																</children>
															</table>
															<condition>
																<children>
																	<conditionbranch xpath="count(  propdev:SiteCOngDistricts  )  &gt; 0">
																		<children>
																			<table>
																				<properties align="center" border="0" width="90%"/>
																				<styles font-family="Arial" font-size="9pt"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<styles font-weight="bold"/>
																								<children>
																									<tablecell>
																										<children>
																											<text fixtext=" "/>
																											<text fixtext=" Congressional District">
																												<styles font-weight="bold"/>
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
																				<properties align="center" border="1" cellpadding="0" cellspacing="0" width="90%"/>
																				<styles font-family="Arial" font-size="9pt"/>
																				<children>
																					<tablebody>
																						<children>
																							<template match="propdev:SiteCOngDistricts" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<tablerow>
																										<children>
																											<tablecell>
																												<children>
																													<template match="propdev:CongDistrict" matchtype="schemagraphitem">
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
																									<tablecell/>
																								</children>
																							</tablerow>
																						</children>
																					</tablebody>
																				</children>
																			</table>
																			<newline/>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
														</children>
													</template>
												</children>
											</template>
											<newline/>
											<newline/>
											<newline/>
											<newline/>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="count(  //propdev:PROPOSAL/propdev:PROP_SPECIAL_REVIEW   ) &gt; 0">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<text fixtext="Special Review ">
												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0"/>
												<styles font-family="Arial" font-size="9pt"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties width="15%"/>
																		<children>
																			<text fixtext="Special Review"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties width="15%"/>
																		<children>
																			<text fixtext="Approval"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties width="10%"/>
																		<children>
																			<text fixtext="Protocol No"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties width="12%"/>
																		<children>
																			<text fixtext="Application Date"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties width="10%"/>
																		<children>
																			<text fixtext="Approval Date"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties width="38%"/>
																		<children>
																			<text fixtext="Comments"/>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="propdev:PROP_SPECIAL_REVIEW" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="lookuptypes:SPECIAL_REVIEW" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="lookuptypes:DESCRIPTION" matchtype="schemagraphitem">
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
																						<properties width="15%"/>
																						<children>
																							<template match="lookuptypes:APPLICABLE_REVIEW_TYPE" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="lookuptypes:DESCRIPTION" matchtype="schemagraphitem">
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
																						<properties width="10%"/>
																						<children>
																							<template match="propdev:PROTOCOL_NUMBER" matchtype="schemagraphitem">
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
																						<properties width="12%"/>
																						<children>
																							<template match="propdev:APPLICATION_DATE" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format string="MM/DD/YYYY" datatype="dateTime"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="10%"/>
																						<children>
																							<template match="propdev:APPROVAL_DATE" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format string="MM/DD/YYYY" datatype="dateTime"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="38%"/>
																						<children>
																							<paragraph paragraphtag="pre-wrap">
																								<children>
																									<template match="propdev:COMMENTS" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<content>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</paragraph>
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
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="count(  //propdev:PROPOSAL/propdev:PROP_YNQ  )  &gt; 0">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<text fixtext="YNQ">
												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
												<styles font-family="Arial" font-size="9pt"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties width="30%"/>
																		<children>
																			<text fixtext="Question"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties width="10%"/>
																		<children>
																			<text fixtext="Answer"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties width="45%"/>
																		<children>
																			<text fixtext="Explanation"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties width="15%"/>
																		<children>
																			<text fixtext="Review Date"/>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="propdev:PROP_YNQ" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="30%"/>
																						<children>
																							<paragraph paragraphtag="pre-wrap">
																								<children>
																									<template match="propdev:QUESTION_DESCRIPTION" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<content>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</paragraph>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="10%"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="propdev:ANSWER = &apos;Y&apos;">
																										<children>
																											<text fixtext="Yes"/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<newline/>
																							<condition>
																								<children>
																									<conditionbranch xpath="propdev:ANSWER = &apos;N&apos;">
																										<children>
																											<text fixtext="No"/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="45%"/>
																						<children>
																							<paragraph paragraphtag="pre-wrap">
																								<children>
																									<template match="propdev:EXPLANATION" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<content>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</paragraph>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="propdev:REVIEW_DATE" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format string="MM/DD/YYYY" datatype="dateTime"/>
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
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<paragraph paragraphtag="p"/>
							<text fixtext="Funding Opportunity Information">
								<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
							</text>
							<newline/>
							<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="5"/>
								<children>
									<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
										<editorproperties elementstodisplay="5"/>
										<children>
											<newline/>
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<styles font-family="Arial" font-size="9pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties height="18" width="18%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Sponsor Proposal No.:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="82%"/>
																		<children>
																			<template match="propdev:SPONSOR_PROPOSAL_NUMBER" matchtype="schemagraphitem">
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
															<tablerow>
																<children>
																	<tablecell>
																		<properties height="18" width="18%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Program Title:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="82%"/>
																		<children>
																			<paragraph paragraphtag="pre-wrap">
																				<children>
																					<template match="propdev:PROGRAM_ANNOUNCEMENT_TITLE" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<content>
																								<format datatype="string"/>
																							</content>
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
																		<properties height="18" width="18%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Proposal in Response:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="82%"/>
																		<children>
																			<template match="lookuptypes:NOTICE_OF_OPPORTUNITY" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="lookuptypes:DESCRIPTION" matchtype="schemagraphitem">
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
																		<properties height="18" width="18%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Funding Opportunity:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="82%"/>
																		<children>
																			<template match="propdev:PROGRAM_ANNOUNCEMENT_NUMBER" matchtype="schemagraphitem">
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
															<tablerow>
																<children>
																	<tablecell>
																		<properties height="18" width="18%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="CFDA Number:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="82%"/>
																		<children>
																			<template match="propdev:CFDA_NUMBER" matchtype="schemagraphitem">
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
															<tablerow>
																<children>
																	<tablecell>
																		<properties height="18" width="18%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Original Proposal No.:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="82%"/>
																		<children>
																			<template match="propdev:CONTINUED_FROM" matchtype="schemagraphitem">
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
															<tablerow>
																<children>
																	<tablecell>
																		<properties height="18" width="18%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Award No.:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="82%"/>
																		<children>
																			<template match="propdev:CURRENT_AWARD_NUMBER" matchtype="schemagraphitem">
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
															<tablerow>
																<children>
																	<tablecell>
																		<properties height="18" width="18%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="NSF Science Code:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="82%"/>
																		<children>
																			<template match="lookuptypes:NSF_SCIENCE_CODE" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="lookuptypes:NSF_CODE" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<content>
																								<format datatype="string"/>
																							</content>
																						</children>
																					</template>
																					<text fixtext=" : "/>
																					<template match="lookuptypes:DESCRIPTION" matchtype="schemagraphitem">
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
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
										</children>
									</template>
								</children>
							</template>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="count(  //budget:BUDGET  )  &gt; 0">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<text fixtext="Budget Details">
												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<children>
																			<table>
																				<properties border="0" width="100%"/>
																				<styles font-family="Arial" font-size="9pt"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="17%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Budget Status:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="3" width="30%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="propdev:PROPOSAL/propdev:PROPOSAL_MASTER/propdev:BUDGET_STATUS = &apos;I&apos;">
																														<children>
																															<text fixtext="InComplete"/>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																											<condition>
																												<children>
																													<conditionbranch xpath="propdev:PROPOSAL/propdev:PROPOSAL_MASTER/propdev:BUDGET_STATUS = &apos;C&apos;">
																														<children>
																															<text fixtext="Complete"/>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																											<newline/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="4" width="15%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Final:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="38%"/>
																										<styles font-weight="normal"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="//propdev:PROPOSAL/budget:BUDGET/budget:BudgetMaster/budget:FINAL_VERSION_FLAG = &apos;Y&apos;">
																														<children>
																															<text fixtext="Yes"/>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																											<condition>
																												<children>
																													<conditionbranch xpath="//propdev:PROPOSAL/budget:BUDGET/budget:BudgetMaster/budget:FINAL_VERSION_FLAG = &apos;N&apos;">
																														<children>
																															<text fixtext="No"/>
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
																										<properties width="17%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Modular Budget:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="3" width="30%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="//propdev:PROPOSAL/budget:BUDGET/budget:BudgetMaster/budget:MODULAR_BUDGET_FLAG = &apos;Y&apos;">
																														<children>
																															<text fixtext="Yes"/>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																											<condition>
																												<children>
																													<conditionbranch xpath="//propdev:PROPOSAL/budget:BUDGET/budget:BudgetMaster/budget:MODULAR_BUDGET_FLAG = &apos;N&apos;">
																														<children>
																															<text fixtext="No"/>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="4" width="15%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Submit Cost Sharing:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="38%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="propdev:PROPOSAL/budget:BUDGET/budget:BudgetMaster/budget:SUBMIT_COST_SHARING_FLAG = &apos;Y&apos;">
																														<children>
																															<text fixtext="Yes"/>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																											<condition>
																												<children>
																													<conditionbranch xpath="propdev:PROPOSAL/budget:BUDGET/budget:BudgetMaster/budget:SUBMIT_COST_SHARING_FLAG = &apos;N&apos;">
																														<children>
																															<text fixtext="No"/>
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
																										<properties width="17%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="on/off Campus:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="3" width="30%"/>
																										<children>
																											<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="budget:BUDGET" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="budget:BudgetMaster" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="budget:ON_OFF_CAMPUS_FLAG" matchtype="schemagraphitem">
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
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="4" width="15%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Total Cost Limit:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="38%"/>
																										<children>
																											<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="budget:BUDGET" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="budget:BudgetMaster" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="budget:TOTAL_COST_LIMIT" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<text fixtext="$"/>
																																			<content>
																																				<format string="###,##0.00" datatype="decimal"/>
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
																										<properties width="17%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Residual Funds:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="3" width="30%"/>
																										<children>
																											<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="budget:BUDGET" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="budget:BudgetMaster" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="budget:RESIDUAL_FUNDS" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<text fixtext="$"/>
																																			<content>
																																				<format string="###,##0.00" datatype="decimal"/>
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
																										<properties colspan="4" width="15%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Total Direct Cost Limit:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="38%"/>
																										<children>
																											<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="budget:BUDGET" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="budget:BudgetMaster" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="budget:TOTAL_DIRECT_COST_LIMIT" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<text fixtext="$"/>
																																			<content>
																																				<format string="###,##0.00" datatype="decimal"/>
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
																										<properties width="17%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Direct Cost:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="3" width="30%"/>
																										<children>
																											<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="budget:BUDGET" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="budget:BudgetMaster" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="budget:TOTAL_DIRECT_COST" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<text fixtext="$"/>
																																			<content>
																																				<format string="###,##0.00" datatype="decimal"/>
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
																										<properties colspan="4" width="15%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Indirect Cost:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="38%"/>
																										<children>
																											<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="budget:BUDGET" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="budget:BudgetMaster" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="budget:TOTAL_INDIRECT_COST" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<text fixtext="$"/>
																																			<content>
																																				<format string="###,##0.00" datatype="decimal"/>
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
																										<properties width="17%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Total Cost:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="3" width="30%"/>
																										<children>
																											<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="budget:BUDGET" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="budget:BudgetMaster" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="budget:TOTAL_COST" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<text fixtext="$"/>
																																			<content>
																																				<format string="###,##0.00" datatype="decimal"/>
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
																										<properties colspan="4" width="15%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Under Recovery:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="38%"/>
																										<children>
																											<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="budget:BUDGET" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="budget:BudgetMaster" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="budget:UNDERRECOVERY_AMOUNT" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<text fixtext="$"/>
																																			<content>
																																				<format string="###,##0.00" datatype="decimal"/>
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
																										<properties width="17%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Cost Share:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="3" width="30%"/>
																										<children>
																											<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="budget:BUDGET" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="budget:BudgetMaster" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="budget:COST_SHARING_AMOUNT" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<text fixtext="$"/>
																																			<content>
																																				<format string="###,##0.00" datatype="decimal"/>
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
																										<properties colspan="4" width="15%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Period:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="38%"/>
																										<children>
																											<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="budget:BUDGET" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="budget:BudgetMaster" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="budget:START_DATE" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<content>
																																				<format string="MM/DD/YYYY" datatype="dateTime"/>
																																			</content>
																																		</children>
																																	</template>
																																	<text fixtext="  - "/>
																																	<template match="budget:END_DATE" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<content>
																																				<format string="MM/DD/YYYY" datatype="dateTime"/>
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
																										<properties width="17%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="OverHead Rate Type:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="3" width="30%"/>
																										<children>
																											<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="budget:BUDGET" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="budget:BudgetMaster" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="budget:OH_RATE_TYPE_DESCRIPTION" matchtype="schemagraphitem">
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
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="4" width="15%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Under Recovery Rate Type:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="38%"/>
																										<children>
																											<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="budget:BUDGET" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="budget:BudgetMaster" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="budget:UR_RATE_TYPE_DESCRIPTION" matchtype="schemagraphitem">
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
																											</template>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="17%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Comments:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties colspan="8" width="28%"/>
																										<children>
																											<paragraph paragraphtag="pre-wrap">
																												<children>
																													<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<template match="budget:BUDGET" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="budget:BudgetMaster" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="budget:COMMENTS" matchtype="schemagraphitem">
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
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="count(  //propdev:PROPOSAL/budget:BUDGET/budget:BudgetMaster/budget:BUDGET_PERIOD  )  &gt; 0">
														<children>
															<newline/>
															<paragraph paragraphtag="p"/>
															<newline/>
															<text fixtext="Budget Periods">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<styles font-weight="bold"/>
																				<children>
																					<tablecell headercell="1">
																						<properties width="5%"/>
																						<children>
																							<text fixtext="Period"/>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties width="10%"/>
																						<children>
																							<text fixtext="Start Date"/>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties width="10%"/>
																						<children>
																							<text fixtext="End Date"/>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties width="15%"/>
																						<children>
																							<text fixtext="No. of Months"/>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties width="12%"/>
																						<children>
																							<text fixtext="Direct Cost"/>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties width="12%"/>
																						<children>
																							<text fixtext="Indirect Cost"/>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties width="12%"/>
																						<children>
																							<text fixtext="Under Recovery"/>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties width="12%"/>
																						<children>
																							<text fixtext="Cost Sharing"/>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties width="12%"/>
																						<children>
																							<text fixtext="Total Cost"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tableheader>
																	<tablebody>
																		<children>
																			<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="budget:BUDGET" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="budget:BudgetMaster" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="budget:BUDGET_PERIOD" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="5%"/>
																														<children>
																															<template match="budget:BUDGET_PERIOD_NUMBER" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<format datatype="int"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="10%"/>
																														<children>
																															<template match="budget:START_DATE" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<format string="MM/DD/YYYY" datatype="dateTime"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="10%"/>
																														<children>
																															<template match="budget:END_DATE" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<format string="MM/DD/YYYY" datatype="dateTime"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="15%"/>
																														<children>
																															<template match="budget:NO_OF_MONTHS" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<format datatype="decimal"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="right" width="12%"/>
																														<children>
																															<template match="budget:TOTAL_DIRECT_COST" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<text fixtext="$"/>
																																	<content>
																																		<format datatype="decimal"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="right" width="12%"/>
																														<children>
																															<template match="budget:TOTAL_INDIRECT_COST" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<text fixtext="$"/>
																																	<content>
																																		<format datatype="decimal"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="right" width="12%"/>
																														<children>
																															<template match="budget:UNDERRECOVERY_AMOUNT" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<text fixtext="$"/>
																																	<content>
																																		<format datatype="decimal"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="right" width="12%"/>
																														<children>
																															<template match="budget:COST_SHARING_AMOUNT" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<text fixtext="$"/>
																																	<content>
																																		<format datatype="decimal"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties align="right" width="12%"/>
																														<children>
																															<template match="budget:TOTAL_COST" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<text fixtext="$"/>
																																	<content>
																																		<format datatype="decimal"/>
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
																			</template>
																		</children>
																	</tablebody>
																</children>
															</table>
															<newline/>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<newline/>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="count(  //propdev:PROP_ABSTRACT  )  &gt; 0">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<text fixtext="Abstract">
												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<styles font-family="Arial" font-size="9pt"/>
												<children>
													<tablebody>
														<children>
															<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="propdev:PROP_ABSTRACT" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<styles font-weight="normal"/>
																				<children>
																					<tablecell>
																						<properties width="30%"/>
																						<styles font-weight="bold"/>
																						<children>
																							<paragraph paragraphtag="pre-wrap">
																								<children>
																									<template match="lookuptypes:ABSTRACT_TYPE" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="lookuptypes:DESCRIPTION" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																													<text fixtext=":"/>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</paragraph>
																						</children>
																					</tablecell>
																					<tablecell>
																						<children>
																							<paragraph paragraphtag="pre-wrap">
																								<children>
																									<template match="propdev:ABSTRACT" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<content>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</paragraph>
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
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="count(  //propdev:PROP_CUSTOM_DATA  )  &gt; 0">
										<children>
											<paragraph paragraphtag="p"/>
											<text fixtext="Others">
												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
											</text>
											<newline/>
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<styles font-family="Arial" font-size="9pt"/>
												<children>
													<tablebody>
														<children>
															<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="propdev:PROP_CUSTOM_DATA" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="40%"/>
																						<styles font-weight="bold"/>
																						<children>
																							<template match="propdev:COLUMN_NAME" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format datatype="string"/>
																									</content>
																									<text fixtext=":"/>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<children>
																							<template match="propdev:COLUMN_VALUE" matchtype="schemagraphitem">
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
													</tablebody>
												</children>
											</table>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="count(  //propdev:PROPOSAL/propdev:ROUTING_DETAILS/approvalRouting:RoutingMaps  )  &gt; 0">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<text fixtext="Routing Details">
												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
											</text>
											<newline/>
											<paragraph paragraphtag="p"/>
											<newline/>
											<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<template match="propdev:ROUTING_DETAILS" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<template match="approvalRouting:RoutingMaps" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<newline/>
																	<template match="approvalRouting:Description" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<content>
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																				<format datatype="string"/>
																			</content>
																		</children>
																	</template>
																	<newline/>
																	<template match="approvalRouting:LevelDetails" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<newline/>
																			<table>
																				<properties width="100%"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																								<children>
																									<tablecell>
																										<children>
																											<text fixtext="Sequential Stop ">
																												<styles font-weight="bold"/>
																											</text>
																											<template match="approvalRouting:LevelNumber" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content>
																														<styles font-weight="bold"/>
																														<format datatype="int"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																								<children>
																									<tablecell>
																										<children>
																											<table>
																												<properties border="1" cellpadding="0" cellspacing="0"/>
																												<styles font-family="Arial" font-size="9pt"/>
																												<children>
																													<tableheader>
																														<children>
																															<tablerow>
																																<styles font-weight="bold"/>
																																<children>
																																	<tablecell headercell="1">
																																		<children>
																																			<text fixtext="User Name(User Id)"/>
																																		</children>
																																	</tablecell>
																																	<tablecell headercell="1">
																																		<children>
																																			<text fixtext="Approval Type"/>
																																		</children>
																																	</tablecell>
																																	<tablecell headercell="1">
																																		<children>
																																			<text fixtext="Approval Status"/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																														</children>
																													</tableheader>
																													<tablebody>
																														<children>
																															<template match="approvalRouting:RoutingDetails" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<tablerow>
																																		<styles font-weight="normal"/>
																																		<children>
																																			<tablecell>
																																				<children>
																																					<template match="approvalRouting:UserName" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="5"/>
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</template>
																																					<template match="approvalRouting:UserId" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="5"/>
																																						<children>
																																							<text fixtext="("/>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																							<text fixtext=")"/>
																																						</children>
																																					</template>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<children>
																																					<condition>
																																						<children>
																																							<conditionbranch xpath="approvalRouting:PrimaryApproverFlag = &apos;Y&apos;">
																																								<children>
																																									<text fixtext="Primary Approver"/>
																																								</children>
																																							</conditionbranch>
																																						</children>
																																					</condition>
																																					<condition>
																																						<children>
																																							<conditionbranch xpath="approvalRouting:PrimaryApproverFlag = &apos;N&apos;">
																																								<children>
																																									<text fixtext="Secondary Approver"/>
																																								</children>
																																							</conditionbranch>
																																						</children>
																																					</condition>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<children>
																																					<template match="approvalRouting:ApprovalStatus" matchtype="schemagraphitem">
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
																													</tablebody>
																												</children>
																											</table>
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
																	</template>
																	<newline/>
																	<newline/>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
											<newline/>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="count(  //propdev:PROPOSAL/propdev:ROUTING_DETAILS/approvalRouting:RoutingMaps )  &gt; 0">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<newline/>
											<text fixtext="Routing Comments">
												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
											</text>
											<newline/>
											<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<template match="propdev:ROUTING_COMMENTS" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<template match="approvalRouting:RoutingMaps" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<newline/>
																	<template match="approvalRouting:RoutingDetails" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<newline/>
																			<table>
																				<properties border="0" width="100%"/>
																				<children>
																					<tablebody>
																						<children>
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
																																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																<children>
																																	<tablecell>
																																		<properties width="40%"/>
																																		<children>
																																			<template match="approvalRouting:UserName" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<content>
																																						<format datatype="string"/>
																																					</content>
																																				</children>
																																			</template>
																																			<text fixtext="("/>
																																			<template match="approvalRouting:UserId" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<content>
																																						<format datatype="string"/>
																																					</content>
																																				</children>
																																			</template>
																																			<text fixtext=")"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="35%"/>
																																		<children>
																																			<template match="approvalRouting:RoutingComments" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<template match="approvalRouting:MapNameDescription" matchtype="schemagraphitem">
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
																																		<properties width="25%"/>
																																		<children>
																																			<template match="approvalRouting:ApprovalStatus" matchtype="schemagraphitem">
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
																													</tablebody>
																												</children>
																											</table>
																											<table>
																												<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
																												<styles font-family="Arial" font-size="9pt"/>
																												<children>
																													<tablebody>
																														<children>
																															<template match="approvalRouting:RoutingComments" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties width="75%"/>
																																				<children>
																																					<template match="approvalRouting:Comments" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="5"/>
																																						<children>
																																							<paragraph paragraphtag="pre-wrap">
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</paragraph>
																																						</children>
																																					</template>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties width="25%"/>
																																				<children>
																																					<template match="approvalRouting:UpdateTimestamp" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="5"/>
																																						<children>
																																							<content>
																																								<format string="MM/DD/YYYY hh:mm:ss" datatype="dateTime"/>
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
																									</tablecell>
																								</children>
																							</tablerow>
																						</children>
																					</tablebody>
																				</children>
																			</table>
																			<newline/>
																		</children>
																	</template>
																	<newline/>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
						</children>
					</template>
					<newline/>
					<newline/>
					<newline/>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" pageorientation="landscape" pageorientationportrait="portrait" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.2in" papermarginright="0.2in" papermargintop="1.4in" paperwidth="8.5in"/>
		<children>
			<globaltemplate match="#headerall" matchtype="named" parttype="headerall">
				<children>
					<template match="$XML" matchtype="schemasource">
						<editorproperties elementstodisplay="1"/>
						<children>
							<table>
								<properties border="0" width="100%"/>
								<styles font-family="Arial" font-size="9pt" table-layout="fixed"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="4" height="18" width="30%"/>
														<styles padding="0" padding-bottom="0"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="4" height="18" width="30%"/>
														<styles padding="0" padding-bottom="0"/>
														<children>
															<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="propdev:SCHOOL_INFO_TYPE" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="propdev:Acronym" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																	<text fixtext=" "/>
																</children>
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" valign="top" width="15%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="0"/>
														<children>
															<text fixtext="Proposal Number: ">
																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="bottom" width="30%"/>
														<styles font-family="Arial" padding-bottom="0" padding-left="0" padding-top="1pt"/>
														<children>
															<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="propdev:PROPOSAL_NUMBER" matchtype="schemagraphitem">
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
													</tablecell>
													<tablecell>
														<properties align="right" valign="top" width="40%"/>
														<styles font-size="9pt" font-weight="bold" padding-bottom="0" padding-right="5pt"/>
														<children>
															<text fixtext="Date Generated: ">
																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="15%"/>
														<styles font-size="9pt" padding-bottom="0"/>
														<children>
															<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="propdev:CUR_DATE" matchtype="schemagraphitem">
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
														<properties align="left" valign="top" width="15%"/>
														<styles font-weight="bold" padding-top="0"/>
														<children>
															<text fixtext="Lead PI:">
																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" colspan="3" valign="top" width="30%"/>
														<styles font-family="Arial" padding-left="0" padding-top="0"/>
														<children>
															<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="propdev:PRINCIPAL_INVESTIGATOR_NAME" matchtype="schemagraphitem">
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
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" valign="top" width="15%"/>
														<styles font-weight="bold" padding-top="0"/>
														<children>
															<text fixtext="Sponsor:">
																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" colspan="3" valign="top" width="30%"/>
														<styles font-family="Arial" padding-left="0" padding-top="0"/>
														<children>
															<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="sponsor:SPONSOR" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="sponsor:SPONSOR_CODE" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<content>
																								<format datatype="string"/>
																							</content>
																							<text fixtext=" : "/>
																						</children>
																					</template>
																					<text fixtext=" "/>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext=" "/>
															<template match="propdev:PROPOSAL" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="propdev:PROPOSAL_MASTER" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="sponsor:SPONSOR" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="sponsor:SPONSOR_NAME" matchtype="schemagraphitem">
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
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" colspan="4" valign="top" width="30%"/>
														<styles font-family="Verdana" font-size="9pt" padding="0" padding-top="0"/>
														<children>
															<line>
																<properties color="black" size="1"/>
															</line>
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
					<newline/>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>
