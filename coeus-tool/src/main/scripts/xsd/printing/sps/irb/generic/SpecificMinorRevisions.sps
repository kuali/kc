<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://irb.mit.edu/irbnamespace"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="D:\StyleVisionFiles\Correspondent templates\xmlpyproject\irb.xsd" workingxmlfile="C:\correspondenceTemplates\xml files\actionCorrespondence.xml">
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
									<fixtext value="http://localhost:8080/CoeusApplet/images/couhes_byline2.gif"/>
								</target>
								<imagesource>
									<fixtext value="http://localhost:8080/CoeusApplet/images/couhes_byline2.gif"/>
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
																		<properties width="551"/>
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
																		<properties valign="top" width="127"/>
																		<children>
																			<text fixtext="From:">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="551"/>
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
																			<paragraph paragraphtag="p">
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
																		<children>
																			<text fixtext="Date:">
																				<styles font-size="10pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="551"/>
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
																		<properties width="551"/>
																		<children>
																			<text fixtext="Specific Minor Revisions Required">
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
																				<children>
																					<text fixtext="IRB Action Date ">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																					<text fixtext="     ">
																						<styles font-size="10pt"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties valign="top" width="551"/>
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
																				<properties width="127"/>
																				<children>
																					<text fixtext="IRB Protocol # ">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																					<text fixtext="    ">
																						<styles font-size="10pt"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties valign="top" width="551"/>
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
																					<text fixtext="Study Title ">
																						<styles font-size="10pt" font-weight="bold"/>
																					</text>
																					<text fixtext="             ">
																						<styles font-size="10pt"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties valign="top" width="551"/>
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
													<paragraph/>
													<paragraph/>
													<paragraph>
														<children>
															<paragraph>
																<children>
																	<text fixtext="At its meeting of  ">
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
																											<conditionbranch xpath="../../n1:CurrentSubmissionFlag =&apos;No&apos;">
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
																	<text fixtext=", the ">
																		<styles font-size="10pt"/>
																	</text>
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
																	<text fixtext=" reviewed the above mentioned protocol and determined that specific minor revisions are required. These revisions are noted  below.  If you agree with all of the committee’s revisions, incorporate them in a revised protocol and submit it to the ">
																		<styles font-size="10pt"/>
																	</text>
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
																	<text fixtext=" for expeditious review.">
																		<styles font-size="10pt"/>
																	</text>
																</children>
															</paragraph>
															<paragraph>
																<children>
																	<paragraph paragraphtag="p">
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<paragraph>
																						<children>
																							<paragraph paragraphtag="p">
																								<children>
																									<text fixtext="If you disagree with the committee’s recommendations, you may do the following:  Please justify to the  ">
																										<styles font-size="10pt"/>
																									</text>
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
																									<text fixtext=" why the revisions should not be incorporated.  You are also invited to meet with the committee to discuss these revisions if you feel it is necessary.">
																										<styles font-size="10pt"/>
																									</text>
																									<newline/>
																									<newline/>
																									<paragraph>
																										<children>
																											<line>
																												<properties color="black" size="1"/>
																											</line>
																											<text fixtext="Requested Revisions:">
																												<styles font-size="10pt" font-weight="bold"/>
																											</text>
																										</children>
																									</paragraph>
																								</children>
																							</paragraph>
																						</children>
																					</paragraph>
																				</children>
																			</paragraph>
																		</children>
																	</paragraph>
																</children>
															</paragraph>
															<paragraph paragraphtag="p">
																<children>
																	<template match="n1:Protocol" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:Submissions" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:Minutes" matchtype="schemagraphitem">
																						<children>
																							<template match="n1:MinuteEntry" matchtype="schemagraphitem">
																								<children>
																									<paragraph paragraphtag="pre">
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="../n1:PrivateCommentFlag = &quot;false&quot; and  ../../n1:CurrentSubmissionFlag =&apos;Yes&apos;">
																														<children>
																															<list>
																																<styles margin-bottom="0" margin-top="0"/>
																																<children>
																																	<listrow>
																																		<children>
																																			<content>
																																				<styles font-family="Book Antiqua" font-size="10pt"/>
																																				<format datatype="string"/>
																																			</content>
																																		</children>
																																	</listrow>
																																</children>
																															</list>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</paragraph>
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
													</paragraph>
												</children>
											</paragraph>
										</children>
									</paragraph>
									<paragraph paragraphtag="p">
										<children>
											<paragraph paragraphtag="p">
												<children>
													<paragraph paragraphtag="p">
														<children>
															<paragraph paragraphtag="p">
																<children>
																	<paragraph paragraphtag="p">
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<paragraph paragraphtag="p">
																						<children>
																							<paragraph paragraphtag="p">
																								<children>
																									<paragraph paragraphtag="p">
																										<children>
																											<paragraph paragraphtag="p">
																												<children>
																													<paragraph>
																														<children>
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
																																						<properties valign="top" width="31"/>
																																						<children>
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="(n1:Protocol/n1:KeyStudyPerson/n1:Person/n1:PersonID != &apos;null&apos;)  and  (n1:Protocol/n1:Correspondent/n1:Person/n1:PersonID != &apos;null&apos;)">
																																										<children>
																																											<text fixtext="cc:">
																																												<styles font-size="10pt"/>
																																											</text>
																																										</children>
																																									</conditionbranch>
																																								</children>
																																							</condition>
																																						</children>
																																					</tablecell>
																																					<tablecell>
																																						<children>
																																							<paragraph paragraphtag="p">
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
																																											<paragraph paragraphtag="p">
																																												<children>
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
																																											</paragraph>
																																											<newline/>
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
																															<newline/>
																														</children>
																													</paragraph>
																													<paragraph paragraphtag="p">
																														<children>
																															<paragraph paragraphtag="p">
																																<children>
																																	<text fixtext="">
																																		<styles font-size="10pt"/>
																																	</text>
																																</children>
																															</paragraph>
																														</children>
																													</paragraph>
																												</children>
																											</paragraph>
																										</children>
																									</paragraph>
																								</children>
																							</paragraph>
																						</children>
																					</paragraph>
																				</children>
																			</paragraph>
																		</children>
																	</paragraph>
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
							<paragraph paragraphtag="p">
								<children>
									<paragraph paragraphtag="p">
										<children>
											<paragraph paragraphtag="p">
												<children>
													<paragraph paragraphtag="p">
														<children>
															<paragraph paragraphtag="p">
																<children>
																	<paragraph paragraphtag="p">
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<paragraph paragraphtag="p">
																						<children>
																							<paragraph paragraphtag="p">
																								<children>
																									<paragraph paragraphtag="p">
																										<children>
																											<paragraph paragraphtag="p"/>
																										</children>
																									</paragraph>
																								</children>
																							</paragraph>
																						</children>
																					</paragraph>
																				</children>
																			</paragraph>
																		</children>
																	</paragraph>
																</children>
															</paragraph>
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
