<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://irb.mit.edu/irbnamespace"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="irb.xsd" workingxmlfile="Y:\ele\Correspondence09202004-032207.xml">
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
							<template match="n1:Correspondence" matchtype="schemagraphitem">
								<children>
									<paragraph>
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
																		<properties valign="top" width="24%"/>
																		<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																		<children>
																			<text fixtext="To:   "/>
																			<text fixtext="  ">
																				<styles font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="76%"/>
																		<styles font-family="Arial" font-weight="normal"/>
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
																													<text fixtext=" "/>
																													<template match="n1:LastName" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<styles font-family="Arial" font-size="10pt"/>
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
																		<properties valign="top" width="24%"/>
																		<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																		<children>
																			<text fixtext="From:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="76%"/>
																		<children>
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:Submissions" matchtype="schemagraphitem">
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
																																	<condition>
																																		<children>
																																			<conditionbranch xpath="../../../n1:CurrentSubmissionFlag =&apos;No&apos;">
																																				<children>
																																					<content>
																																						<styles font-family="Arial" font-size="10pt" font-weight="normal"/>
																																						<format datatype="string"/>
																																					</content>
																																				</children>
																																			</conditionbranch>
																																		</children>
																																	</condition>
																																</children>
																															</template>
																															<text fixtext=" ">
																																<styles font-family="Arial" font-size="10pt" font-weight="normal"/>
																															</text>
																															<template match="n1:LastName" matchtype="schemagraphitem">
																																<children>
																																	<condition>
																																		<children>
																																			<conditionbranch xpath="../../../n1:CurrentSubmissionFlag =&apos;No&apos;">
																																				<children>
																																					<content>
																																						<styles font-family="Arial" font-size="10pt" font-weight="normal"/>
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
																				<styles font-family="Arial" font-size="10pt" font-weight="normal"/>
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
																																		<styles font-family="Arial" font-size="10pt" font-weight="normal"/>
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
																		<properties valign="top" width="24%"/>
																		<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Date:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="76%"/>
																		<styles font-family="Arial" font-size="10pt" font-weight="normal"/>
																		<children>
																			<template match="n1:CurrentDate" matchtype="schemagraphitem">
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
																		<properties valign="top" width="24%"/>
																		<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Committee Action:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="76%"/>
																		<children>
																			<text fixtext="Exemption Granted">
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
											<table>
												<properties border="0" width="100%"/>
												<styles table-layout="fixed"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties valign="top" width="24%"/>
																		<children>
																			<text fixtext="Committee Action Date:">
																				<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																			</text>
																			<text fixtext=" ">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																			<text fixtext="    ">
																				<styles font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="76%"/>
																		<styles font-size="10pt"/>
																		<children>
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:Submissions" matchtype="schemagraphitem">
																						<children>
																							<template match="n1:SubmissionDetails" matchtype="schemagraphitem">
																								<children>
																									<template match="n1:ActionType" matchtype="schemagraphitem">
																										<children>
																											<template match="n1:ActionDate" matchtype="schemagraphitem">
																												<children>
																													<condition>
																														<children>
																															<conditionbranch xpath="../../../n1:CurrentSubmissionFlag =&apos;No&apos;">
																																<children>
																																	<content>
																																		<format string="MM/DD/YYYY" datatype="date"/>
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
																			</template>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties valign="top" width="24%"/>
																		<children>
																			<text fixtext="COUHES Protocol #: ">
																				<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																			</text>
																			<text fixtext="  ">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																			<text fixtext="  ">
																				<styles font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="76%"/>
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
																		<properties valign="top" width="24%"/>
																		<children>
																			<text fixtext="S">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																			<text fixtext="tudy Title: ">
																				<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																			</text>
																			<text fixtext="             ">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="76%"/>
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
										</children>
									</paragraph>
									<paragraph>
										<styles font-family="Arial" font-size="10pt" font-weight="normal"/>
										<children>
											<text fixtext="The above-referenced protocol is considered exempt after review by the Committee on the Use of Humans as Experimental Subjects pursuant to Federal regulations, 45 CFR Part 46.101(b)">
												<styles font-family="Arial"/>
											</text>
											<template match="n1:Protocol" matchtype="schemagraphitem">
												<children>
													<template match="n1:Submissions" matchtype="schemagraphitem">
														<children>
															<template match="n1:SubmissionDetails" matchtype="schemagraphitem">
																<children>
																	<template match="n1:SubmissionChecklistInfo" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:ChecklistCodesFormatted" matchtype="schemagraphitem">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="../../../n1:CurrentSubmissionFlag =&apos;No&apos;">
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
													</template>
												</children>
											</template>
											<text fixtext=".">
												<styles font-family="Arial"/>
											</text>
										</children>
									</paragraph>
									<paragraph>
										<styles font-family="Arial" font-size="10pt" font-weight="normal"/>
										<children>
											<newline/>
											<text fixtext="This part of the federal regulations requires that the information be recorded by investigators in such a manner that subjects cannot be identified, directly or through identifiers linked to the subjects.   It is necessary that the information obtained not be such that if disclosed outside the research, it could reasonably place the subjects at risk of criminal or civil liability, or be damaging to the subjects&apos; financial standing, employability, or reputation.">
												<styles font-family="Arial"/>
											</text>
											<newline/>
											<newline/>
											<text fixtext="If the research involves collaboration with another institution then the research cannot commence until COUHES receives written notification of approval from the collaborating institution&apos;s IRB.">
												<styles font-family="Arial"/>
											</text>
											<newline/>
											<newline/>
											<text fixtext="If there are any changes to the protocol that significantly or substantially impact the rights of human subjects you must notify the Committee before those changes are initiated.">
												<styles font-family="Arial"/>
											</text>
											<newline/>
											<text fixtext="">
												<styles font-family="Arial"/>
											</text>
											<newline/>
											<text fixtext="You should retain a copy of this letter for your records. ">
												<styles font-family="Arial"/>
											</text>
										</children>
									</paragraph>
									<table>
										<properties align="left" border="0" width="50%"/>
										<children>
											<tablebody>
												<children>
													<tablerow>
														<children>
															<tablecell>
																<properties width="437"/>
																<children>
																	<table>
																		<properties border="0" width="100"/>
																		<children>
																			<tablebody>
																				<children>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties height="19" width="27"/>
																								<children>
																									<text fixtext="cc:   ">
																										<styles font-family="Arial" font-size="10pt"/>
																									</text>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties height="19" width="452"/>
																								<children>
																									<text fixtext="Tom Duff ">
																										<styles font-family="Arial" font-size="10pt"/>
																									</text>
																								</children>
																							</tablecell>
																						</children>
																					</tablerow>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties width="27"/>
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
																																					<content>
																																						<styles font-family="Arial" font-size="10pt"/>
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
									<newline/>
									<newline/>
									<newline/>
									<text fixtext="   ">
										<styles font-family="Arial" font-size="10pt"/>
									</text>
									<newline/>
									<newline/>
									<newline/>
									<newline/>
									<newline/>
									<text fixtext="">
										<styles font-family="Arial" font-size="10pt"/>
									</text>
								</children>
							</template>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.6in" papermarginright="0.6in" papermargintop="0.79in" paperwidth="8.5in"/>
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
