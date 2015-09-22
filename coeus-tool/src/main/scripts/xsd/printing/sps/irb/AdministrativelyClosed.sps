<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://irb.mit.edu/irbnamespace"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="irb.xsd" workingxmlfile="Correspondence.xml">
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
							<image>
								<properties border="0"/>
								<target>
									<fixtext value="/export/home/www/https/tomcat5.0.25/webapps/coeus/images/couhes_byline2.gif"/>
								</target>
								<imagesource>
									<fixtext value="/export/home/www/https/tomcat5.0.25/webapps/coeus/images/couhes_byline2.gif"/>
								</imagesource>
							</image>
							<line>
								<properties color="black" size="1"/>
							</line>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Correspondence/n1:Protocol/n1:ProtocolMasterData/n1:ProtocolStatusCode =300">
										<children>
											<template match="n1:Correspondence" matchtype="schemagraphitem">
												<children>
													<paragraph>
														<children>
															<table>
																<properties border="0"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties valign="top" width="127"/>
																						<styles font-family="Arial" font-size="10pt"/>
																						<children>
																							<text fixtext="To:">
																								<styles font-size="10pt" font-weight="bold"/>
																							</text>
																							<text fixtext="     ">
																								<styles font-size="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="451"/>
																						<styles font-family="Arial" font-size="10pt"/>
																						<children>
																							<template match="n1:Protocol" matchtype="schemagraphitem">
																								<children>
																									<template match="n1:Investigator" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="n1:PI_flag = &apos;true&apos;">
																														<children>
																															<template match="n1:Person" matchtype="schemagraphitem">
																																<children>
																																	<template match="n1:Firstname" matchtype="schemagraphitem">
																																		<children>
																																			<content>
																																				<styles font-family="Arial" font-size="10pt"/>
																																				<format datatype="string"/>
																																			</content>
																																		</children>
																																	</template>
																																	<text fixtext=" ">
																																		<styles font-size="10pt"/>
																																	</text>
																																	<template match="n1:LastName" matchtype="schemagraphitem">
																																		<children>
																																			<content>
																																				<styles font-size="10pt"/>
																																				<format datatype="string"/>
																																			</content>
																																		</children>
																																	</template>
																																	<paragraph paragraphtag="pre">
																																		<children>
																																			<template match="n1:OfficeLocation" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<styles font-family="Arial" font-size="10pt"/>
																																						<format datatype="string"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</paragraph>
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
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties valign="top" width="127"/>
																						<styles font-family="Arial" font-size="10pt"/>
																						<children>
																							<text fixtext="From:">
																								<styles font-size="10pt" font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="451"/>
																						<styles font-family="Arial" font-size="10pt"/>
																						<children>
																							<template match="n1:Protocol" matchtype="schemagraphitem">
																								<children>
																									<template match="n1:Submissions" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="n1:CurrentSubmissionFlag =&apos;No&apos;">
																														<children>
																															<template match="n1:CommitteeMember" matchtype="schemagraphitem">
																																<children>
																																	<condition>
																																		<children>
																																			<conditionbranch xpath="n1:CommitteeMemberRole/n1:MemberRoleDesc = &apos;Chair&apos;">
																																				<children>
																																					<template match="n1:Person" matchtype="schemagraphitem">
																																						<children>
																																							<template match="n1:Firstname" matchtype="schemagraphitem">
																																								<children>
																																									<content>
																																										<styles font-size="10pt"/>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																							<text fixtext=" ">
																																								<styles font-size="10pt"/>
																																							</text>
																																							<template match="n1:LastName" matchtype="schemagraphitem">
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
																															</template>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</template>
																								</children>
																							</template>
																							<text fixtext=", Chair ">
																								<styles font-size="10pt"/>
																							</text>
																							<paragraph paragraphtag="pre">
																								<children>
																									<template match="n1:Protocol" matchtype="schemagraphitem">
																										<children>
																											<template match="n1:Submissions" matchtype="schemagraphitem">
																												<children>
																													<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
																														<children>
																															<template match="n1:CommitteeName" matchtype="schemagraphitem">
																																<children>
																																	<condition>
																																		<children>
																																			<conditionbranch xpath="../../n1:CurrentSubmissionFlag =&apos;No&apos;">
																																				<children>
																																					<content>
																																						<styles font-size="10pt"/>
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
																						<properties valign="top" width="127"/>
																						<styles font-family="Arial" font-size="10pt"/>
																						<children>
																							<text fixtext="Date:">
																								<styles font-size="10pt" font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="451"/>
																						<styles font-family="Arial" font-size="10pt"/>
																						<children>
																							<template match="n1:CurrentDate" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles font-family="Arial" font-size="10pt"/>
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
																						<properties valign="top" width="127"/>
																						<styles font-family="Arial" font-size="10pt"/>
																						<children>
																							<text fixtext="Committee Action:">
																								<styles font-size="10pt" font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="451"/>
																						<styles font-family="Arial" font-size="10pt"/>
																						<children>
																							<text fixtext="Protocol Administratively Closed">
																								<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<paragraph>
																<properties align="justify"/>
																<children>
																	<newline/>
																	<table>
																		<properties border="0"/>
																		<children>
																			<tablebody>
																				<children>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties valign="top" width="127"/>
																								<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																								<children>
																									<text fixtext="Expiration Date: ">
																										<styles font-size="10pt" font-weight="bold"/>
																									</text>
																									<text fixtext="     ">
																										<styles font-size="10pt"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties valign="top" width="451"/>
																								<styles font-family="Arial" font-size="10pt"/>
																								<children>
																									<template match="n1:Protocol" matchtype="schemagraphitem">
																										<children>
																											<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																												<children>
																													<template match="n1:ExpirationDate" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<styles font-size="10pt"/>
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
																								<properties width="127"/>
																								<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																								<children>
																									<text fixtext="IRB Protocol #: ">
																										<styles font-size="10pt" font-weight="bold"/>
																									</text>
																									<text fixtext="    ">
																										<styles font-size="10pt"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties valign="top" width="451"/>
																								<styles font-family="Arial" font-size="10pt"/>
																								<children>
																									<template match="n1:Protocol" matchtype="schemagraphitem">
																										<children>
																											<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																												<children>
																													<template match="n1:ProtocolNumber" matchtype="schemagraphitem">
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
																							</tablecell>
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties width="127"/>
																								<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																								<children>
																									<text fixtext="Study Title: ">
																										<styles font-size="10pt" font-weight="bold"/>
																									</text>
																									<text fixtext="             ">
																										<styles font-size="10pt"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties valign="top" width="451"/>
																								<styles font-family="Arial" font-size="10pt"/>
																								<children>
																									<template match="n1:Protocol" matchtype="schemagraphitem">
																										<children>
																											<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																												<children>
																													<template match="n1:ProtocolTitle" matchtype="schemagraphitem">
																														<children>
																															<paragraph paragraphtag="pre-wrap">
																																<children>
																																	<content>
																																		<styles font-size="10pt"/>
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
																	<newline/>
																</children>
															</paragraph>
														</children>
													</paragraph>
													<paragraph>
														<children>
															<text fixtext="This is to notify you that the above protocol has been ADMINISTRATIVELY CLOSED effective ">
																<styles font-family="Book Antiqua" font-size="10pt"/>
															</text>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<children>
																	<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:ExpirationDate" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<styles font-family="Book Antiqua" font-size="10pt"/>
																						<format string="MM/DD/YYYY" datatype="date"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext=" due to your failure to return the required Continuing Review Questionnaire.    Data collected after the expiration date is considered unapproved research and cannot be included with data collected during an approved period.  Furthermore, data collected after termination of approval cannot be reported or published as research data.">
																<styles font-family="Book Antiqua" font-size="10pt"/>
															</text>
															<newline/>
															<newline/>
															<text fixtext="In accordance with MIT Policy and Federal Regulations, you may NOT continue any further research efforts covered by this protocol.  If you wish to gather further research information, you must submit a new proposal for review by the Committee on the Use of Humans as Experimental Subjects.">
																<styles font-family="Book Antiqua" font-size="10pt"/>
															</text>
															<newline/>
															<paragraph paragraphtag="p">
																<children>
																	<text fixtext="You should retain a copy of this letter for your records.">
																		<styles font-family="Book Antiqua" font-size="10pt"/>
																	</text>
																</children>
															</paragraph>
														</children>
													</paragraph>
													<newline/>
													<newline/>
													<table>
														<properties border="0" width="100%"/>
														<children>
															<tablebody>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties valign="top" width="3"/>
																			</tablecell>
																			<tablecell>
																				<properties align="left"/>
																				<children>
																					<table>
																						<properties border="0" width="100%"/>
																						<children>
																							<tablebody>
																								<children>
																									<tablerow>
																										<children>
																											<tablecell>
																												<properties width="46"/>
																												<children>
																													<text fixtext="cc">
																														<styles font-family="Book Antiqua" font-size="10pt"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<children>
																													<text fixtext="Tom Duff">
																														<styles font-family="Book Antiqua" font-size="10pt"/>
																													</text>
																												</children>
																											</tablecell>
																										</children>
																									</tablerow>
																									<tablerow>
																										<children>
																											<tablecell>
																												<properties width="46"/>
																											</tablecell>
																											<tablecell>
																												<properties valign="top" width="452"/>
																												<children>
																													<template match="n1:Protocol" matchtype="schemagraphitem">
																														<children>
																															<template match="n1:Correspondent" matchtype="schemagraphitem">
																																<children>
																																	<template match="n1:Person" matchtype="schemagraphitem">
																																		<children>
																																			<template match="n1:Fullname" matchtype="schemagraphitem">
																																				<children>
																																					<condition>
																																						<children>
																																							<conditionbranch xpath="../../n1:TypeOfCorrespondent = &apos;CRC&apos;">
																																								<children>
																																									<paragraph paragraphtag="p">
																																										<children>
																																											<content>
																																												<styles font-family="Book Antiqua" font-size="10pt"/>
																																												<format datatype="string"/>
																																											</content>
																																										</children>
																																									</paragraph>
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
																</children>
															</tablebody>
														</children>
													</table>
												</children>
											</template>
										</children>
									</conditionbranch>
									<conditionbranch>
										<children>
											<newline/>
											<template match="n1:Correspondence" matchtype="schemagraphitem">
												<children>
													<paragraph>
														<children>
															<table>
																<properties border="0"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties valign="top" width="127"/>
																						<children>
																							<text fixtext="To:">
																								<styles font-size="10pt" font-weight="bold"/>
																							</text>
																							<text fixtext="      ">
																								<styles font-size="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="451"/>
																						<children>
																							<template match="n1:Protocol" matchtype="schemagraphitem">
																								<children>
																									<template match="n1:Investigator" matchtype="schemagraphitem">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="n1:PI_flag = &apos;true&apos;">
																														<children>
																															<template match="n1:Person" matchtype="schemagraphitem">
																																<children>
																																	<template match="n1:Firstname" matchtype="schemagraphitem">
																																		<children>
																																			<content>
																																				<styles font-family="Book Antiqua" font-size="10pt"/>
																																				<format datatype="string"/>
																																			</content>
																																		</children>
																																	</template>
																																	<text fixtext=" ">
																																		<styles font-size="10pt"/>
																																	</text>
																																	<template match="n1:LastName" matchtype="schemagraphitem">
																																		<children>
																																			<content>
																																				<styles font-size="10pt"/>
																																				<format datatype="string"/>
																																			</content>
																																		</children>
																																	</template>
																																	<paragraph paragraphtag="pre">
																																		<children>
																																			<template match="n1:OfficeLocation" matchtype="schemagraphitem">
																																				<children>
																																					<content>
																																						<styles font-size="10pt"/>
																																						<format datatype="string"/>
																																					</content>
																																				</children>
																																			</template>
																																		</children>
																																	</paragraph>
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
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties valign="top" width="127"/>
																						<children>
																							<text fixtext="From:">
																								<styles font-size="10pt" font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="451"/>
																						<children>
																							<template match="n1:Protocol" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="n1:Submissions" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="n1:CommitteeMember" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<condition>
																														<children>
																															<conditionbranch xpath="n1:CommitteeMemberRole/n1:MemberRoleCode =1">
																																<children>
																																	<template match="n1:Person" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<template match="n1:Firstname" matchtype="schemagraphitem">
																																				<editorproperties elementstodisplay="5"/>
																																				<children>
																																					<content>
																																						<format datatype="string"/>
																																					</content>
																																				</children>
																																			</template>
																																			<text fixtext=" "/>
																																			<template match="n1:LastName" matchtype="schemagraphitem">
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
																															</conditionbranch>
																														</children>
																													</condition>
																												</children>
																											</template>
																										</children>
																									</template>
																								</children>
																							</template>
																							<text fixtext=", Chair ">
																								<styles font-size="10pt"/>
																							</text>
																							<paragraph paragraphtag="p">
																								<children>
																									<text fixtext="COUHES"/>
																								</children>
																							</paragraph>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties height="19" valign="top" width="127"/>
																						<children>
																							<text fixtext="Date:">
																								<styles font-size="10pt" font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties height="19" width="451"/>
																						<children>
																							<template match="n1:CurrentDate" matchtype="schemagraphitem">
																								<children>
																									<content>
																										<styles font-size="10pt"/>
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
																						<properties valign="top" width="127"/>
																						<children>
																							<text fixtext="Committee Action:">
																								<styles font-size="10pt" font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="451"/>
																						<children>
																							<text fixtext="Protocol Administratively Closed">
																								<styles font-size="10pt" font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<paragraph>
																<properties align="justify"/>
																<children>
																	<newline/>
																	<table>
																		<properties border="0"/>
																		<children>
																			<tablebody>
																				<children>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties valign="top" width="127"/>
																							</tablecell>
																							<tablecell>
																								<properties valign="top" width="461"/>
																							</tablecell>
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties width="127"/>
																								<children>
																									<text fixtext="IRB Protocol #: ">
																										<styles font-size="10pt" font-weight="bold"/>
																									</text>
																									<text fixtext="    ">
																										<styles font-size="10pt"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties valign="top" width="461"/>
																								<children>
																									<template match="n1:Protocol" matchtype="schemagraphitem">
																										<children>
																											<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																												<children>
																													<template match="n1:ProtocolNumber" matchtype="schemagraphitem">
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
																							</tablecell>
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties width="127"/>
																								<children>
																									<text fixtext="Study Title: ">
																										<styles font-size="10pt" font-weight="bold"/>
																									</text>
																									<text fixtext="             ">
																										<styles font-size="10pt"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties valign="top" width="461"/>
																								<children>
																									<template match="n1:Protocol" matchtype="schemagraphitem">
																										<children>
																											<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																												<children>
																													<template match="n1:ProtocolTitle" matchtype="schemagraphitem">
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
																							</tablecell>
																						</children>
																					</tablerow>
																				</children>
																			</tablebody>
																		</children>
																	</table>
																	<newline/>
																</children>
															</paragraph>
														</children>
													</paragraph>
													<paragraph paragraphtag="p">
														<children>
															<paragraph paragraphtag="p">
																<children>
																	<paragraph>
																		<children>
																			<text fixtext="This is to notify you that the above protocol has been ADMINISTRATIVELY CLOSED effective ">
																				<styles font-family="Book Antiqua" font-size="10pt"/>
																			</text>
																			<template match="n1:CurrentDate" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<styles font-family="Book Antiqua" font-size="10pt"/>
																						<format string="MM/DD/YYYY" datatype="date"/>
																					</content>
																				</children>
																			</template>
																			<text fixtext="  as per your request.   Data collected after the closed date is considered unapproved research and cannot be included with data collected during an approved period.  Furthermore, data collected after termination of approval cannot be reported or published as research data.">
																				<styles font-family="Book Antiqua" font-size="10pt"/>
																			</text>
																			<newline/>
																			<newline/>
																			<text fixtext="In accordance with MIT Policy and Federal Regulations, you may NOT continue any further research efforts covered by this protocol. If you wish to gather further research information, you must submit a new proposal for review by the Committee on the Use of Humans as Experimental Subjects.">
																				<styles font-family="Book Antiqua" font-size="10pt"/>
																			</text>
																			<newline/>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="You should retain a copy of this letter for your records.">
																						<styles font-family="Book Antiqua" font-size="10pt"/>
																					</text>
																					<newline/>
																					<newline/>
																					<newline/>
																					<newline/>
																					<newline/>
																					<newline/>
																					<newline/>
																					<newline/>
																					<newline/>
																					<newline/>
																					<newline/>
																					<table>
																						<properties border="0" width="100%"/>
																						<children>
																							<tablebody>
																								<children>
																									<tablerow>
																										<children>
																											<tablecell>
																												<properties valign="top" width="3"/>
																											</tablecell>
																											<tablecell>
																												<properties align="left"/>
																												<children>
																													<table>
																														<properties border="0" width="100%"/>
																														<children>
																															<tablebody>
																																<children>
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties width="46"/>
																																				<children>
																																					<text fixtext="cc">
																																						<styles font-family="Book Antiqua" font-size="10pt"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<children>
																																					<text fixtext="Tom Duff">
																																						<styles font-family="Book Antiqua" font-size="10pt"/>
																																					</text>
																																				</children>
																																			</tablecell>
																																		</children>
																																	</tablerow>
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties width="46"/>
																																			</tablecell>
																																			<tablecell>
																																				<properties valign="top" width="452"/>
																																				<children>
																																					<template match="n1:Protocol" matchtype="schemagraphitem">
																																						<children>
																																							<template match="n1:Correspondent" matchtype="schemagraphitem">
																																								<children>
																																									<template match="n1:Person" matchtype="schemagraphitem">
																																										<children>
																																											<template match="n1:Fullname" matchtype="schemagraphitem">
																																												<children>
																																													<condition>
																																														<children>
																																															<conditionbranch xpath="../../n1:TypeOfCorrespondent = &apos;CRC&apos;">
																																																<children>
																																																	<paragraph paragraphtag="p">
																																																		<children>
																																																			<content>
																																																				<styles font-family="Book Antiqua" font-size="10pt"/>
																																																				<format datatype="string"/>
																																																			</content>
																																																		</children>
																																																	</paragraph>
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
																								</children>
																							</tablebody>
																						</children>
																					</table>
																				</children>
																			</paragraph>
																		</children>
																	</paragraph>
																</children>
															</paragraph>
														</children>
													</paragraph>
												</children>
											</template>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.6in" papermarginright="1.0in" papermargintop="0.79in" paperwidth="8.5in"/>
		<children>
			<globaltemplate match="#headerall" matchtype="named" parttype="headerall">
				<children>
					<template match="$XML" matchtype="schemasource">
						<children>
							<table>
								<properties width="100%"/>
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
														<properties align="left"/>
														<styles font-size="smaller" padding="0"/>
													</tablecell>
													<tablecell>
														<properties align="right" width="150"/>
														<styles font-size="smaller" padding="0"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="2"/>
														<styles padding="0"/>
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
