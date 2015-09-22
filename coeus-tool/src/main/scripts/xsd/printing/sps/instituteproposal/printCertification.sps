<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces/>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="printCertification.xsd" workingxmlfile="printCertificationSample.xml">
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
							<newline/>
							<table>
								<properties border="0" width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" valign="top" width="35%"/>
														<styles border-bottom-style="none" border-bottom-width="0" font-family="Verdana" font-size="9pt" margin-bottom="0" padding-bottom="0" vertical-align="0"/>
														<children>
															<paragraph paragraphtag="p">
																<styles border-bottom-style="none" border-bottom-width="0" font-family="Verdana" font-size="9pt" margin-bottom="0" padding-bottom="0" vertical-align="0"/>
																<children>
																	<template match="printCertification" matchtype="schemagraphitem">
																		<children>
																			<template match="OfficeName" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<styles color="#800040" font-weight="bold"/>
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
													<tablecell>
														<properties align="left" width="20%"/>
														<styles border-bottom-style="none" border-bottom-width="0" font-size="xx-small" margin-bottom="0" padding-bottom="0" vertical-align="0"/>
														<children>
															<image>
																<properties border="0"/>
																<target>
																	<xpath value="printCertification/LogoPath"/>
																	<fixtext value="printCertificationLogo.gif"/>
																</target>
																<imagesource>
																	<xpath value="printCertification/LogoPath"/>
																	<fixtext value="printCertificationLogo.gif"/>
																</imagesource>
															</image>
														</children>
													</tablecell>
													<tablecell>
														<properties align="justify" valign="top" width="45%"/>
														<styles border-bottom-style="none" border-bottom-width="0" font-family="Verdana" font-size="9pt" margin-bottom="0" padding-bottom="0" vertical-align="0"/>
														<children>
															<paragraph paragraphtag="p">
																<styles border-bottom-style="none" border-bottom-width="0" font-family="Verdana" font-size="9pt" margin-bottom="0" vertical-align="0"/>
																<children>
																	<template match="printCertification" matchtype="schemagraphitem">
																		<children>
																			<template match="organizationInfo" matchtype="schemagraphitem">
																				<children>
																					<template match="OrganizationName" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<styles color="#800040" font-family="Verdana" font-size="9pt" font-weight="bold"/>
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
															<paragraph paragraphtag="p">
																<styles border-bottom-style="none" border-bottom-width="0" font-family="Verdana" font-size="9pt" margin-bottom="0" vertical-align="0"/>
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="boolean(  printCertification/organizationInfo/ContactName  )  and  boolean(  printCertification/organizationInfo/Address1 )  and  not(boolean(  printCertification/organizationInfo/Address2  ) ) and  not(boolean(  printCertification/organizationInfo/Address3  ))">
																				<children>
																					<template match="printCertification" matchtype="schemagraphitem">
																						<children>
																							<template match="organizationInfo" matchtype="schemagraphitem">
																								<children>
																									<paragraph paragraphtag="p">
																										<children>
																											<template match="ContactName" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</paragraph>
																									<template match="Address1" matchtype="schemagraphitem">
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
																			</conditionbranch>
																			<conditionbranch xpath="boolean(  printCertification/organizationInfo/ContactName  )  and  boolean(  printCertification/organizationInfo/Address1 )  and  boolean(  printCertification/organizationInfo/Address2  ) and  not(boolean(  printCertification/organizationInfo/Address3  ))">
																				<children>
																					<template match="printCertification" matchtype="schemagraphitem">
																						<children>
																							<template match="organizationInfo" matchtype="schemagraphitem">
																								<children>
																									<paragraph paragraphtag="p">
																										<children>
																											<template match="ContactName" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</paragraph>
																									<paragraph paragraphtag="p">
																										<children>
																											<template match="Address1" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</paragraph>
																									<template match="Address2" matchtype="schemagraphitem">
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
																			</conditionbranch>
																			<conditionbranch xpath="boolean(  printCertification/organizationInfo/ContactName  )  and  boolean(  printCertification/organizationInfo/Address1 )  and  not(boolean(  printCertification/organizationInfo/Address2  )) and  boolean(  printCertification/organizationInfo/Address3  )">
																				<children>
																					<template match="printCertification" matchtype="schemagraphitem">
																						<children>
																							<template match="organizationInfo" matchtype="schemagraphitem">
																								<children>
																									<paragraph paragraphtag="p">
																										<children>
																											<template match="ContactName" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</paragraph>
																									<paragraph paragraphtag="p">
																										<children>
																											<template match="Address1" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</paragraph>
																									<template match="Address3" matchtype="schemagraphitem">
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
																			</conditionbranch>
																			<conditionbranch xpath="boolean(  printCertification/organizationInfo/ContactName  )  and  not(boolean(  printCertification/organizationInfo/Address1 ))  and  boolean(  printCertification/organizationInfo/Address2 ) and  boolean(  printCertification/organizationInfo/Address3  )">
																				<children>
																					<template match="printCertification" matchtype="schemagraphitem">
																						<children>
																							<template match="organizationInfo" matchtype="schemagraphitem">
																								<children>
																									<paragraph paragraphtag="p">
																										<children>
																											<template match="ContactName" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</paragraph>
																									<paragraph paragraphtag="p">
																										<children>
																											<template match="Address2" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</paragraph>
																									<template match="Address3" matchtype="schemagraphitem">
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
																			</conditionbranch>
																			<conditionbranch xpath="boolean(  printCertification/organizationInfo/ContactName  )  and  not(boolean(  printCertification/organizationInfo/Address1 ))  and  not(boolean(  printCertification/organizationInfo/Address2 )) and  boolean(  printCertification/organizationInfo/Address3  )">
																				<children>
																					<template match="printCertification" matchtype="schemagraphitem">
																						<children>
																							<template match="organizationInfo" matchtype="schemagraphitem">
																								<children>
																									<paragraph paragraphtag="p">
																										<children>
																											<template match="ContactName" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</paragraph>
																									<template match="Address3" matchtype="schemagraphitem">
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
																			</conditionbranch>
																			<conditionbranch xpath="boolean(  printCertification/organizationInfo/ContactName  )  and  not(boolean(  printCertification/organizationInfo/Address1 ))  and  boolean(  printCertification/organizationInfo/Address2 ) and  not(boolean(  printCertification/organizationInfo/Address3 ) )">
																				<children>
																					<template match="printCertification" matchtype="schemagraphitem">
																						<children>
																							<template match="organizationInfo" matchtype="schemagraphitem">
																								<children>
																									<paragraph paragraphtag="p">
																										<children>
																											<template match="ContactName" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</paragraph>
																									<template match="Address2" matchtype="schemagraphitem">
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
																			</conditionbranch>
																			<conditionbranch xpath="boolean(  printCertification/organizationInfo/ContactName  )  and  not(boolean(  printCertification/organizationInfo/Address1 ))  and  not(boolean(  printCertification/organizationInfo/Address2 )) and  not(boolean(  printCertification/organizationInfo/Address3 ) )">
																				<children>
																					<template match="printCertification" matchtype="schemagraphitem">
																						<children>
																							<template match="organizationInfo" matchtype="schemagraphitem">
																								<children>
																									<paragraph paragraphtag="p">
																										<children>
																											<template match="ContactName" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</paragraph>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</conditionbranch>
																			<conditionbranch>
																				<children>
																					<template match="printCertification" matchtype="schemagraphitem">
																						<children>
																							<template match="organizationInfo" matchtype="schemagraphitem">
																								<children>
																									<paragraph paragraphtag="p">
																										<children>
																											<template match="ContactName" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</paragraph>
																									<paragraph paragraphtag="p">
																										<children>
																											<template match="Address1" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</paragraph>
																									<paragraph paragraphtag="p">
																										<children>
																											<template match="Address2" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</paragraph>
																									<template match="Address3" matchtype="schemagraphitem">
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
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</paragraph>
															<template match="printCertification" matchtype="schemagraphitem">
																<children>
																	<template match="organizationInfo" matchtype="schemagraphitem">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="boolean(  City  )">
																						<children>
																							<template match="City" matchtype="schemagraphitem">
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
																			<condition>
																				<children>
																					<conditionbranch xpath="boolean(  City  )  and boolean(  State  )">
																						<children>
																							<template match="State" matchtype="schemagraphitem">
																								<children>
																									<text fixtext=", "/>
																									<content>
																										<format datatype="string"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</conditionbranch>
																					<conditionbranch xpath="not(boolean(  City  ) ) and boolean(  State  )  and  State != null">
																						<children>
																							<template match="State" matchtype="schemagraphitem">
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
																			<template match="PostCode" matchtype="schemagraphitem">
																				<children>
																					<text fixtext=" "/>
																					<content>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																			<condition>
																				<children>
																					<conditionbranch xpath="not(  boolean(  State  )  )">
																						<children>
																							<template match="Country" matchtype="schemagraphitem">
																								<children>
																									<text fixtext=" , "/>
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
								<properties border="0" width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" height="24" valign="top"/>
														<styles border-top-style="none" border-top-width="0" color="#800040" font-size="larger" font-weight="bolder" line-height="0" margin="0" margin-bottom="0" padding="0" padding-bottom="0" padding-top="0" top="0" vertical-align="0"/>
														<children>
															<line/>
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
									<conditionbranch xpath="printCertification/certifications/questionID = &quot;P1&quot;">
										<children>
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<styles font-size="9pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties height="19" width="20%"/>
																		<styles font-size="9pt" font-weight="bold" padding-bottom="0" padding-top="0"/>
																		<children>
																			<text fixtext="Date:">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="19" width="80%"/>
																		<styles font-size="9pt" padding-bottom="0" padding-top="0"/>
																		<children>
																			<template match="printCertification" matchtype="schemagraphitem">
																				<children>
																					<template match="CurrentDate" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<format string="MM/DD/YYYY" datatype="date"/>
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
																		<properties nowrap="set" width="20%"/>
																		<styles font-size="9pt" font-weight="bold" padding-bottom="0" padding-top="0"/>
																		<children>
																			<text fixtext="Investigator:">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-size="9pt" padding-bottom="0" padding-top="0"/>
																		<children>
																			<template match="printCertification" matchtype="schemagraphitem">
																				<children>
																					<template match="investigator" matchtype="schemagraphitem">
																						<children>
																							<template match="PersonName" matchtype="schemagraphitem">
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
																		<properties nowrap="set" width="20%"/>
																		<styles font-size="9pt" font-weight="bold" padding-bottom="0" padding-top="0"/>
																		<children>
																			<text fixtext="Proposal Number:">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-size="9pt" padding-bottom="0" padding-top="0"/>
																		<children>
																			<template match="printCertification" matchtype="schemagraphitem">
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
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties width="20%"/>
																		<styles font-size="9pt" font-weight="bold" padding-bottom="0" padding-top="0"/>
																		<children>
																			<text fixtext="Agency:">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-size="9pt" padding-bottom="0" padding-top="0"/>
																		<children>
																			<template match="printCertification" matchtype="schemagraphitem">
																				<children>
																					<template match="sponsor" matchtype="schemagraphitem">
																						<children>
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
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties width="20%"/>
																		<styles font-size="9pt" font-weight="bold" padding-bottom="0" padding-top="0"/>
																		<children>
																			<text fixtext="Subject:">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-size="9pt" padding-bottom="0" padding-top="0"/>
																		<children>
																			<template match="printCertification" matchtype="schemagraphitem">
																				<children>
																					<template match="proposalTitle" matchtype="schemagraphitem">
																						<children>
																							<paragraph paragraphtag="pre-wrap">
																								<styles padding-bottom="0" padding-top="0"/>
																								<children>
																									<text fixtext="Certification for proposal entitled &quot;"/>
																									<content>
																										<format datatype="string"/>
																									</content>
																									<text fixtext="&quot;"/>
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
															<tablerow>
																<children>
																	<tablecell>
																		<properties width="20%"/>
																		<styles padding-bottom="0" padding-top="0"/>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles padding-bottom="0" padding-top="0"/>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<styles font-family="Verdana" font-size="10pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="17" width="30%"/>
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
																										<properties width="20%"/>
																										<styles padding-bottom="0" padding-top="0"/>
																										<children>
																											<newline/>
																											<newline/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="80%"/>
																										<styles padding-bottom="0" padding-top="0"/>
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
																		<properties colspan="17" width="30%"/>
																		<children>
																			<newline/>
																			<text fixtext="My signature below certifies that I hereby authorize MIT to submit the above referenced proposal as an institutionally authorized proposal."/>
																			<newline/>
																			<newline/>
																			<text fixtext="Further, as a Principal Investigator/Co-Principal Investigator/Co-Investigator I certify that:"/>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="17" width="30%"/>
																		<styles padding="0"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="17" width="30%"/>
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
																										<properties valign="top" width="5%"/>
																										<children>
																											<text fixtext=".">
																												<styles font-size="xx-large" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="95%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="printCertification/certifications[ questionID = &quot;P1&quot; and   answer = &apos;Y&apos; ]">
																														<children>
																															<text fixtext="The information submitted within the application is true, complete and accurate to the best of my knowledge.  Any false, fictitious, or fraudulent statements or claims may subject me, as the PI/Co-PI/Co-I to criminal, civil or administrative penalties.  I agree to accept responsibility for the scientific conduct of the project and to provide the required progress reports if an award is made as a result of this application.  "/>
																															<text fixtext="This certification is being added at this time to meet a specific NIH requirement.  It also reflects current federal regulations.">
																																<styles font-size="9pt" font-style="italic"/>
																															</text>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="printCertification/certifications[ questionID = &quot;P1&quot; and   answer = &apos;N&apos; ]">
																														<children>
																															<text fixtext="Please contact your department head, center or laboratory director, or Vice-President for research if you cannot certify the following:"/>
																															<newline/>
																															<newline/>
																															<text fixtext="I cannot certify that this application is true, complete and accurate to the best of my knowledge; that I understand that any false, fictitious, or fraudulent statements or claims may subject me, as the PI/Co-PI/Co-I to criminal, civil or administrative penalties or that I agree to accept responsibility for the scientific conduct of the project and to provide the required progress reports if an award is made as a result of this application.  "/>
																															<text fixtext="This certification is being added at this time to meet a specific NIH requirement, and it also reflects current federal regulations">
																																<styles font-size="9pt" font-style="italic"/>
																															</text>
																															<text fixtext=". "/>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																											<newline/>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties valign="top" width="5%"/>
																										<children>
																											<text fixtext=".">
																												<styles font-size="xx-large" font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="95%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="printCertification/certifications[questionID = &quot;P2&quot; and   answer = &quot;N&quot;]">
																														<children>
																															<text fixtext="I do not have a perceived, potential or real conflict of interest as defined in MIT policies and procedures with regard to the proposed activity."/>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="printCertification/certifications[questionID = &quot;P2&quot; and   answer = &quot;Y&quot;]">
																														<children>
																															<text fixtext="I have identified a perceived, potential or real financial conflict of interest with regard to the proposed research of this proposal.  I have (or will) seek advice and guidance to eliminate or to manage the conflict, by reviewing the circumstances with my department head, center or laboratory director, or with the Director of the Office of Sponsored Programs."/>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																											<newline/>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties valign="top" width="5%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="printCertification/certifications[questionID = &quot;P7&quot; and   answer = &quot;N&quot; ]">
																														<children>
																															<text fixtext=".">
																																<styles font-size="xx-large" font-weight="bold"/>
																															</text>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="printCertification/certifications[questionID = &quot;P7&quot; and   answer = &quot;Y&quot;]">
																														<children>
																															<text fixtext=".">
																																<styles font-size="xx-large" font-weight="bold"/>
																															</text>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="95%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="printCertification/certifications[questionID = &quot;P7&quot; and   answer = &quot;Y&quot;]">
																														<children>
																															<text fixtext="As the PI, I confirm that I have reviewed all subawards included in this proposal. The subaward direct costs have been reviewed and appear to be reasonable given the proposed statement of work."/>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="printCertification/certifications[questionID = &quot;P7&quot; and   answer = &quot;N&quot;]">
																														<children>
																															<text fixtext="As the PI, either I have "/>
																															<text fixtext="not">
																																<styles font-weight="bold"/>
																															</text>
																															<text fixtext=" reviewed all subawards included in this proposal or I am "/>
																															<text fixtext="unable to confirm">
																																<styles font-weight="bold"/>
																															</text>
																															<text fixtext=" that the direct costs appear reasonable given the proposed statement of work at this time."/>
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
																										<properties valign="top" width="5%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="printCertification/sponsor/sponsorType  = &quot;FED&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="printCertification/certifications[questionID = &quot;P3&quot; and   answer = &quot;Y&quot;]">
																																		<children>
																																			<text fixtext=".">
																																				<styles font-size="xx-large" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="printCertification/certifications[questionID = &quot;P3&quot; and   answer = &quot;N&quot;]">
																																		<children>
																																			<text fixtext=".">
																																				<styles font-size="xx-large" font-weight="bold"/>
																																			</text>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="printCertification/certifications[questionID = &quot;P3&quot; and   answer = &quot;F&quot;]">
																																		<children>
																																			<text fixtext=".">
																																				<styles font-size="xx-large" font-weight="bold"/>
																																			</text>
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
																									<tablecell>
																										<properties valign="top" width="95%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="printCertification/sponsor/sponsorType  = &quot;FED&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="printCertification/certifications[questionID = &quot;P3&quot; and   answer = &quot;Y&quot;]">
																																		<children>
																																			<text fixtext="I submitted the required Financial Disclosure for this NIH/NSF proposal via the web based COEUS Conflict of Interest module on   _____________________."/>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="printCertification/certifications[questionID = &quot;P3&quot; and   answer = &quot;N&quot;]">
																																		<children>
																																			<text fixtext="I have not submitted the required Financial Disclosure for this NIH/NSF proposal in COEUS and I understand that this proposal cannot be submitted until I have completed this disclosure."/>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="printCertification/certifications[questionID = &quot;P3&quot; and   answer = &quot;F&quot;]">
																																		<children>
																																			<text fixtext="I have not submitted a Financial Disclosure for this NIH/NSF Fellowship application. My Sponsor, however, will submit one as required for this application."/>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																											<newline/>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties valign="top" width="5%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="printCertification/sponsor/sponsorType  = &quot;FED&quot;">
																														<children>
																															<text fixtext=".">
																																<styles font-size="xx-large" font-weight="bold"/>
																															</text>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="95%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="printCertification/sponsor/sponsorType  = &quot;FED&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="printCertification/certifications[(questionID = &quot;H4&quot;  or  questionID = &quot;P4&quot; ) and   answer = &quot;N&quot;]">
																																		<children>
																																			<text fixtext="I have not and will not lobby any federal agency on behalf of this proposal nor do I have any knowledge of anyone else doing so. "/>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="printCertification/certifications[(questionID = &quot;H4&quot;  or  questionID = &quot;P4&quot;)  and   answer = &quot;Y&quot;]">
																																		<children>
																																			<text fixtext="I have and/or will lobby a federal agency on behalf of this proposal or I plan to have others do so.  "/>
																																			<text fixtext="I have completed the Report on Lobbying Activity and have submitted it to the Office of Sponsored Programs, E19-750.">
																																				<styles font-size="9pt" font-style="italic"/>
																																			</text>
																																			<text fixtext="  ">
																																				<styles font-style="italic"/>
																																			</text>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																											<newline/>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties valign="top" width="5%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="printCertification/sponsor/sponsorType  = &quot;FED&quot;">
																														<children>
																															<text fixtext=".">
																																<styles font-size="xx-large" font-weight="bold"/>
																															</text>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="95%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="printCertification/sponsor/sponsorType  = &quot;FED&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="printCertification/certifications[questionID = &quot;P5&quot; and   answer = &quot;N&quot;]">
																																		<children>
																																			<text fixtext="I am not debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from the current transaction by a federal department or agency."/>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="printCertification/certifications[questionID = &quot;P5&quot; and   answer = &quot;Y&quot;]">
																																		<children>
																																			<text fixtext="I am currently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from the current transaction by a federal department or agency.  "/>
																																			<text fixtext="I have notified the Office of Sponsored Programs and understand that I am precluded from receiving federally funded grant or contract awards or from being paid with federal funds.">
																																				<styles font-size="9pt" font-style="italic"/>
																																			</text>
																																			<text fixtext=" ">
																																				<styles font-size="9pt"/>
																																			</text>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																											<newline/>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties valign="top" width="5%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="printCertification/sponsor/sponsorType = &quot;FED&quot;">
																														<children>
																															<text fixtext=".">
																																<styles font-size="xx-large" font-weight="bold"/>
																															</text>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top" width="95%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="printCertification/sponsor/sponsorType = &quot;FED&quot;">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="printCertification/certifications[questionID = &quot;P6&quot; and   answer = &quot;Y&quot;]">
																																		<children>
																																			<text fixtext="I am familiar with the requirements of the Procurement Integrity Act [(OFPP, Section 27 b103)] ("/>
																																			<text fixtext="http://web.mit.edu/osp/www/Procuint.htm">
																																				<styles text-decoration="underline"/>
																																			</text>
																																			<text fixtext=") and will report any violations to the Office of Sponsored Programs."/>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch xpath="printCertification/certifications[questionID = &quot;P6&quot; and   answer = &quot;N&quot;]">
																																		<children>
																																			<text fixtext="I am not familiar with the requirements of the Procurement Integrity Act [(OFPP, Section 27 b103)] ("/>
																																			<text fixtext="http://web.mit.edu/osp/www/Procuint.htm">
																																				<styles text-decoration="underline"/>
																																			</text>
																																			<text fixtext=") and will not be able report any violations to the Office of Sponsored Programs."/>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
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
																		<properties colspan="17" width="30%"/>
																		<children>
																			<text fixtext=" "/>
																			<newline/>
																			<text fixtext=" "/>
																			<newline/>
																			<text fixtext=" "/>
																			<newline/>
																			<text fixtext=" "/>
																			<newline/>
																			<text fixtext=" "/>
																			<newline/>
																			<text fixtext=" "/>
																			<newline/>
																			<text fixtext=" "/>
																			<newline/>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="17" width="30%"/>
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
																										<properties valign="center" width="65%"/>
																										<styles padding-left="30pt" padding-right="18pt"/>
																										<children>
																											<newline/>
																											<line>
																												<properties color="black" size="1"/>
																											</line>
																											<text fixtext="Signature">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="bottom" width="35%"/>
																										<styles margin-bottom="0" padding-left="18pt" padding-right="38pt"/>
																										<children>
																											<newline/>
																											<line>
																												<properties color="black" size="1"/>
																											</line>
																											<text fixtext="         Date">
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
									<conditionbranch>
										<children>
											<condition>
												<children>
													<conditionbranch xpath="printCertification/certifications/questionID !=  &quot;P1&quot;   and  boolean( printCertification/certifications/statement )">
														<children>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Verdana" font-size="10pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties align="center"/>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties align="center"/>
																						<children>
																							<newline/>
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
																						<properties align="center"/>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties align="center"/>
																						<styles padding="0"/>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties align="center"/>
																						<styles padding="0"/>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties align="center"/>
																						<styles padding="0"/>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<styles padding="0"/>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties align="center"/>
																						<styles padding="0"/>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<styles padding="0"/>
																						<children>
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
																							<table>
																								<properties border="0" width="100%"/>
																								<children>
																									<tablebody>
																										<children>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="20%"/>
																														<children>
																															<text fixtext="Investigator">
																																<styles font-weight="bold"/>
																															</text>
																															<condition>
																																<children>
																																	<conditionbranch xpath="printCertification/investigator/principalInvFlag  = &apos;true&apos;">
																																		<children>
																																			<text fixtext="(PI):">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</conditionbranch>
																																	<conditionbranch>
																																		<children>
																																			<text fixtext=":">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="80%"/>
																														<children>
																															<template match="printCertification" matchtype="schemagraphitem">
																																<children>
																																	<template match="investigator" matchtype="schemagraphitem">
																																		<children>
																																			<template match="PersonName" matchtype="schemagraphitem">
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
																														<properties width="20%"/>
																														<children>
																															<text fixtext="Proposal Number:">
																																<styles font-weight="bold"/>
																															</text>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="80%"/>
																														<children>
																															<template match="printCertification" matchtype="schemagraphitem">
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
																													</tablecell>
																												</children>
																											</tablerow>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="20%"/>
																														<children>
																															<text fixtext="Agency:">
																																<styles font-weight="bold"/>
																															</text>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="80%"/>
																														<children>
																															<template match="printCertification" matchtype="schemagraphitem">
																																<children>
																																	<template match="sponsor" matchtype="schemagraphitem">
																																		<children>
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
																													</tablecell>
																												</children>
																											</tablerow>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties width="20%"/>
																														<children>
																															<text fixtext="Proposal Title:">
																																<styles font-weight="bold"/>
																															</text>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="80%"/>
																														<children>
																															<template match="printCertification" matchtype="schemagraphitem">
																																<children>
																																	<template match="proposalTitle" matchtype="schemagraphitem">
																																		<children>
																																			<paragraph paragraphtag="pre">
																																				<children>
																																					<content>
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
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<children>
																							<newline/>
																							<newline/>
																							<text fixtext="As a principal or co-principal investigator on the above referenced proposal, I certify that:"/>
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
																							<template match="printCertification" matchtype="schemagraphitem">
																								<children>
																									<table>
																										<properties border="0"/>
																										<children>
																											<tablebody>
																												<children>
																													<template match="certifications" matchtype="schemagraphitem">
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell>
																																		<properties width="5%"/>
																																		<children>
																																			<template match="stmt_number" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<format datatype="int"/>
																																					</content>
																																					<text fixtext=" )"/>
																																				</children>
																																			</template>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="95%"/>
																																		<children>
																																			<template match="statement" matchtype="schemagraphitem">
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
																					<tablecell/>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
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
																														<properties valign="center" width="10%"/>
																														<children>
																															<newline/>
																															<newline/>
																															<text fixtext="Signature">
																																<styles font-weight="bold"/>
																															</text>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties valign="bottom" width="90%"/>
																														<styles margin-bottom="0" padding-left="8pt"/>
																														<children>
																															<newline/>
																															<newline/>
																															<newline/>
																															<line>
																																<properties color="black" size="1"/>
																															</line>
																														</children>
																													</tablecell>
																												</children>
																											</tablerow>
																											<tablerow>
																												<children>
																													<tablecell>
																														<properties valign="center" width="10%"/>
																														<children>
																															<newline/>
																															<newline/>
																															<text fixtext="Date">
																																<styles font-weight="bold"/>
																															</text>
																														</children>
																													</tablecell>
																													<tablecell>
																														<properties width="90%"/>
																														<styles margin-bottom="0" padding-left="8pt"/>
																														<children>
																															<newline/>
																															<newline/>
																															<newline/>
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
							<newline/>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" pageorientationportrait="0" paperheight="11in" papermarginbottom="0.15in" papermarginleft="0.65in" papermarginright="0.65in" papermargintop="0.3in" paperwidth="8.5in"/>
	</pagelayout>
	<designfragments/>
</structure>
