<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://irb.mit.edu/irbnamespace"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="irb.xsd" workingxmlfile="C:\correspondenceTemplates\xml files\Correspondence.xml">
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
																		<properties valign="top" width="25%"/>
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
																		<properties valign="top" width="75%"/>
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
																																<styles font-size="10pt"/>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																													<text fixtext="  ">
																														<styles font-family="Arial" font-size="10pt"/>
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
																																		<styles font-family="Book Antiqua" font-size="10pt"/>
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
																		<properties valign="top" width="25%"/>
																		<styles font-family="Arial" font-size="10pt"/>
																		<children>
																			<text fixtext="From:">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="75%"/>
																		<styles font-family="Arial" font-size="10pt"/>
																		<children>
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:Submissions" matchtype="schemagraphitem">
																						<children>
																							<template match="n1:CommitteeMember" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="n1:CommitteeMemberRole/n1:MemberRoleDesc = &apos;Chair&apos; and ../n1:CurrentSubmissionFlag  = &apos;No&apos;">
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
																																						<styles font-size="10pt"/>
																																						<format datatype="string"/>
																																					</content>
																																				</children>
																																			</conditionbranch>
																																		</children>
																																	</condition>
																																</children>
																															</template>
																															<text fixtext=" ">
																																<styles font-size="10pt"/>
																															</text>
																															<template match="n1:LastName" matchtype="schemagraphitem">
																																<children>
																																	<condition>
																																		<children>
																																			<conditionbranch xpath="../../../n1:CurrentSubmissionFlag =&apos;No&apos;">
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
																		<properties valign="top" width="25%"/>
																		<styles font-family="Arial" font-size="10pt"/>
																		<children>
																			<text fixtext="Date:">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="75%"/>
																		<styles font-family="Arial" font-size="10pt"/>
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
																		<properties valign="top" width="25%"/>
																		<styles font-family="Arial" font-size="10pt"/>
																		<children>
																			<text fixtext="Committee Action:">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="75%"/>
																		<styles font-family="Arial" font-size="10pt"/>
																		<children>
																			<text fixtext="Protocol Suspension">
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
											<table>
												<properties border="0" width="100%"/>
												<styles font-family="Arial" font-size="10pt" table-layout="fixed"/>
												<children>
													<tablebody>
														<styles font-family="Arial" font-size="10pt"/>
														<children>
															<tablerow>
																<styles font-family="Arial" font-size="10pt"/>
																<children>
																	<tablecell>
																		<properties width="25%"/>
																		<styles font-family="Arial" font-size="10pt"/>
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
																		<properties valign="top" width="75%"/>
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
																		<properties width="25%"/>
																		<styles font-family="Arial" font-size="10pt"/>
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
																		<properties valign="top" width="75%"/>
																		<styles font-family="Arial" font-size="10pt"/>
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
									<paragraph>
										<children>
											<text fixtext="This message constitutes notice that the participant accrual to this protocol is suspended effective ">
												<styles font-family="Arial" font-size="10pt"/>
											</text>
											<template match="n1:CurrentDate" matchtype="schemagraphitem">
												<children>
													<content>
														<styles font-size="10pt"/>
														<format string="MM/DD/YYYY" datatype="date"/>
													</content>
												</children>
											</template>
											<text fixtext="  .  No additional participants may be enrolled until the Institutional Review Board has reinstated this protocol.  Additionally, unless there are safety issues to the contrary, research cannot be conducted on subjects who are currently enrolled.  However, analysis of data that has already been collected can continue.">
												<styles font-family="Arial" font-size="10pt"/>
											</text>
											<newline/>
											<newline/>
											<text fixtext="IF ADDITIONAL PARTICIPANTS HAVE BEEN ENROLLED SINCE THE SUSPENSION DATE, PLEASE CONTACT THE COUHES OFFICE IMMEDIATELY.   ">
												<styles font-family="Arial" font-size="10pt"/>
											</text>
										</children>
									</paragraph>
									<paragraph>
										<children>
											<text fixtext="If the study is TERMINATED, please contact COUHES so that we can close the file.">
												<styles font-family="Arial" font-size="10pt"/>
											</text>
											<newline/>
											<newline/>
											<text fixtext="Thank you.">
												<styles font-family="Arial" font-size="10pt"/>
											</text>
										</children>
									</paragraph>
									<table>
										<properties border="0" width="100%"/>
										<children>
											<tablebody>
												<children>
													<tablerow>
														<children>
															<tablecell>
																<properties valign="top" width="31"/>
																<children>
																	<condition>
																		<children>
																			<conditionbranch xpath="(n1:Protocol/n1:KeyStudyPerson/n1:Person/n1:PersonID != &apos;null&apos;)  and  (n1:Protocol/n1:Correspondent/n1:Person/n1:PersonID != &apos;null&apos;)">
																				<children>
																					<text fixtext="cc:">
																						<styles font-family="Arial" font-size="10pt"/>
																					</text>
																				</children>
																			</conditionbranch>
																		</children>
																	</condition>
																</children>
															</tablecell>
															<tablecell>
																<children>
																	<template match="n1:Protocol" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:KeyStudyPerson" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:Person" matchtype="schemagraphitem">
																						<children>
																							<template match="n1:Fullname" matchtype="schemagraphitem">
																								<children>
																									<paragraph paragraphtag="p">
																										<styles font-family="Arial"/>
																										<children>
																											<content>
																												<styles font-family="Arial" font-size="10pt"/>
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
																			<template match="n1:Correspondent" matchtype="schemagraphitem">
																				<children>
																					<condition>
																						<children>
																							<conditionbranch xpath="n1:TypeOfCorrespondent  = &apos;Protocol level&apos;">
																								<children>
																									<template match="n1:Person" matchtype="schemagraphitem">
																										<children>
																											<template match="n1:Fullname" matchtype="schemagraphitem">
																												<children>
																													<content>
																														<styles font-family="Arial" font-size="10pt"/>
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
															</tablecell>
														</children>
													</tablerow>
												</children>
											</tablebody>
										</children>
									</table>
									<newline/>
									<text fixtext="">
										<styles font-size="10pt"/>
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
