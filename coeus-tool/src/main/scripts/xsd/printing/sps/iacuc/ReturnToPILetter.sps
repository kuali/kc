<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://xml.coeus.mit.edu/iacuc"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="D:\Coeus\4.4\Printing\iacuc\xmlspy project\iacuc.xsd">
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
																		<properties valign="top" width="20%"/>
																		<styles font-family="Arial"/>
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
																		<properties width="80%"/>
																		<styles font-family="Arial"/>
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
																		<properties valign="top" width="20%"/>
																		<styles font-family="Arial"/>
																		<children>
																			<text fixtext="From:">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-family="Arial"/>
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
																		<properties valign="top" width="20%"/>
																		<styles font-family="Arial"/>
																		<children>
																			<text fixtext="Date:">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-family="Arial"/>
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
																		<properties valign="top" width="20%"/>
																		<styles font-family="Arial"/>
																		<children>
																			<text fixtext="Committee Action:">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-family="Arial"/>
																		<children>
																			<text fixtext="Return to PI">
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
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<styles table-layout="fixed"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties valign="top" width="20%"/>
																		<styles font-family="Arial"/>
																		<children>
																			<text fixtext="IACUC Action Date: ">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																			<text fixtext="     ">
																				<styles font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="80%"/>
																		<styles font-family="Arial"/>
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
																															<conditionbranch xpath="../../../n1:CurrentSubmissionFlag =&apos;Yes&apos;">
																																<children>
																																	<content>
																																		<styles font-size="10pt"/>
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
																		<properties width="20%"/>
																		<styles font-family="Arial"/>
																		<children>
																			<text fixtext="IACUC Protocol #:">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																			<text fixtext=" ">
																				<styles font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties valign="top" width="80%"/>
																		<styles font-family="Arial"/>
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
																		<properties width="20%"/>
																		<styles font-family="Arial"/>
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
																		<properties valign="top" width="80%"/>
																		<styles font-family="Arial"/>
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
										<styles font-family="Arial" font-size="10pt"/>
										<children>
											<text fixtext="At its meeting on  ">
												<styles font-size="10pt"/>
											</text>
											<template match="n1:Protocol" matchtype="schemagraphitem">
												<children>
													<template match="n1:Submissions" matchtype="schemagraphitem">
														<children>
															<template match="n1:ScheduleMasterData" matchtype="schemagraphitem">
																<children>
																	<template match="n1:MeetingDate" matchtype="schemagraphitem">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="../../n1:CurrentSubmissionFlag =&apos;Yes&apos;">
																						<children>
																							<content>
																								<styles font-size="10pt"/>
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
											<text fixtext=", the Committee reviewed the above mentioned protocol and decided to return back to Principal Investigator">
												<styles font-size="10pt"/>
											</text>
										</children>
									</paragraph>
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
																<properties width="27"/>
																<styles font-family="Arial" font-size="10pt"/>
																<children>
																	<text fixtext="cc:">
																		<styles font-family="Arial" font-size="10pt"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties valign="top" width="452"/>
																<styles font-family="Arial" font-size="10pt"/>
																<children>
																	<text fixtext="Tom Duff">
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
																<styles font-family="Arial" font-size="10pt"/>
															</tablecell>
															<tablecell>
																<properties valign="top" width="452"/>
																<styles font-family="Arial" font-size="10pt"/>
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
																																<styles font-family="Arial" font-size="10pt"/>
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
