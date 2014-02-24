<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<predefinedformats>
		<format string="Mon DD YYYY" datatype="date"/>
	</predefinedformats>
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://irb.mit.edu/irbnamespace"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="irb.xsd" workingxmlfile="Y:\ele\AgendaReportnewer.xml">
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
							<paragraph paragraphtag="center">
								<children>
									<text fixtext="Massachusetts Institute of Technology">
										<styles font-size="large"/>
									</text>
								</children>
							</paragraph>
							<paragraph paragraphtag="center">
								<children>
									<text fixtext="Agenda">
										<styles font-size="large" font-weight="bold"/>
									</text>
									<newline/>
								</children>
							</paragraph>
							<template match="n1:Schedule" matchtype="schemagraphitem">
								<children>
									<template match="n1:ScheduleMasterData" matchtype="schemagraphitem">
										<children>
											<template match="n1:CommitteeName" matchtype="schemagraphitem">
												<children>
													<paragraph paragraphtag="center">
														<children>
															<content>
																<styles padding-bottom="2px" padding-top="2px"/>
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
							<template match="n1:Schedule" matchtype="schemagraphitem">
								<children>
									<template match="n1:ScheduleMasterData" matchtype="schemagraphitem">
										<children>
											<template match="n1:ScheduledTime" matchtype="schemagraphitem">
												<children>
													<paragraph paragraphtag="center">
														<children>
															<content>
																<styles margin-bottom=" "/>
																<format string="hh:mm" datatype="time"/>
															</content>
														</children>
													</paragraph>
												</children>
											</template>
										</children>
									</template>
								</children>
							</template>
							<template match="n1:Schedule" matchtype="schemagraphitem">
								<children>
									<template match="n1:ScheduleMasterData" matchtype="schemagraphitem">
										<children>
											<template match="n1:Place" matchtype="schemagraphitem">
												<children>
													<paragraph paragraphtag="center">
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
							</template>
							<newline/>
							<newline/>
							<newline/>
							<text fixtext="AGENDA ITEMS">
								<styles font-weight="bold" text-decoration="underline"/>
							</text>
							<newline/>
							<newline/>
							<list>
								<properties start="1" type="disc"/>
								<styles margin-bottom="0" margin-top="0"/>
								<children>
									<listrow>
										<children>
											<condition>
												<children>
													<conditionbranch xpath="string-length(  n1:Schedule/n1:PreviousSchedule/n1:ScheduleMasterData/n1:MeetingDate ) &gt; 0">
														<children>
															<text fixtext="Review the minutes of the meeting held on"/>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<text fixtext=" "/>
											<template match="n1:Schedule" matchtype="schemagraphitem">
												<children>
													<template match="n1:PreviousSchedule" matchtype="schemagraphitem">
														<children>
															<template match="n1:ScheduleMasterData" matchtype="schemagraphitem">
																<children>
																	<template match="n1:MeetingDate" matchtype="schemagraphitem">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="string-length(.) &gt; 0">
																						<children>
																							<content>
																								<format string="MM/DD/YYYY" datatype="date"/>
																							</content>
																							<text fixtext="."/>
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
									</listrow>
									<listrow>
										<children>
											<condition>
												<children>
													<conditionbranch xpath="string-length(n1:Schedule/n1:NextSchedule/n1:ScheduleMasterData/n1:ScheduledDate) &gt; 0">
														<children>
															<text fixtext="The next meeting will be held on"/>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<text fixtext=" "/>
											<template match="n1:Schedule" matchtype="schemagraphitem">
												<children>
													<template match="n1:NextSchedule" matchtype="schemagraphitem">
														<children>
															<template match="n1:ScheduleMasterData" matchtype="schemagraphitem">
																<children>
																	<template match="n1:ScheduledDate" matchtype="schemagraphitem">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="string-length( . ) &gt;0">
																						<children>
																							<content>
																								<format string="MM/DD/YYYY" datatype="date"/>
																							</content>
																							<text fixtext="."/>
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
											<text fixtext="  "/>
											<condition>
												<children>
													<conditionbranch xpath="string-length( n1:Schedule/n1:NextSchedule/n1:ScheduleMasterData/n1:Place  ) &gt; 0">
														<children>
															<text fixtext="It is scheduled to be held in  at"/>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<text fixtext="  "/>
											<template match="n1:Schedule" matchtype="schemagraphitem">
												<children>
													<template match="n1:NextSchedule" matchtype="schemagraphitem">
														<children>
															<template match="n1:ScheduleMasterData" matchtype="schemagraphitem">
																<children>
																	<template match="n1:Place" matchtype="schemagraphitem">
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="string-length( . )  &gt;0">
																						<children>
																							<content>
																								<format datatype="string"/>
																							</content>
																							<text fixtext="."/>
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
									</listrow>
								</children>
							</list>
							<newline/>
							<text fixtext="PROTOCOLS SUBMITTED FOR REVIEW">
								<styles font-weight="bold" text-decoration="underline"/>
							</text>
							<newline/>
							<paragraph>
								<children>
									<template match="n1:Schedule" matchtype="schemagraphitem">
										<children>
											<template match="n1:ProtocolSubmission" matchtype="schemagraphitem">
												<children>
													<template match="n1:SubmissionDetails" matchtype="schemagraphitem">
														<children>
															<newline/>
															<template match="n1:SubmissionTypeDesc" matchtype="schemagraphitem">
																<children>
																	<content>
																		<styles font-weight="bold"/>
																		<format datatype="string"/>
																	</content>
																</children>
															</template>
														</children>
													</template>
													<newline/>
													<paragraph>
														<children>
															<paragraph>
																<children>
																	<template match="n1:ProtocolSummary" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																				<children>
																					<text fixtext=" "/>
																					<table>
																						<properties border="0"/>
																						<children>
																							<tablebody>
																								<children>
																									<tablerow>
																										<children>
																											<tablecell>
																												<properties valign="top" width="70"/>
																												<children>
																													<text fixtext="Protocol #: ">
																														<styles font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties valign="top" width="110"/>
																												<children>
																													<template match="n1:ProtocolNumber" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties valign="top" width="111"/>
																												<children>
																													<text fixtext="Expiration Date:">
																														<styles font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties align="left" valign="top" width="109"/>
																												<children>
																													<template match="n1:ExpirationDate" matchtype="schemagraphitem">
																														<children>
																															<content>
																																<format string="MM/DD/YYYY" datatype="date"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties align="left" valign="top" width="109"/>
																											</tablecell>
																										</children>
																									</tablerow>
																									<tablerow>
																										<children>
																											<tablecell>
																												<properties valign="top" width="70"/>
																												<children>
																													<text fixtext="Title:">
																														<styles font-weight="bold"/>
																													</text>
																												</children>
																											</tablecell>
																											<tablecell>
																												<properties align="left" colspan="4" valign="top" width="330"/>
																												<children>
																													<template match="n1:ProtocolTitle" matchtype="schemagraphitem">
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
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</paragraph>
															<template match="n1:ProtocolSummary" matchtype="schemagraphitem">
																<children>
																	<template match="n1:ProtocolMasterData" matchtype="schemagraphitem"/>
																</children>
															</template>
															<table>
																<properties border="0"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties valign="top" width="70"/>
																						<children>
																							<text fixtext="PI:">
																								<styles font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties valign="top" width="187"/>
																						<children>
																							<template match="n1:ProtocolSummary" matchtype="schemagraphitem">
																								<children>
																									<template match="n1:Investigator" matchtype="schemagraphitem">
																										<children>
																											<template match="n1:Person" matchtype="schemagraphitem">
																												<children>
																													<template match="n1:Fullname" matchtype="schemagraphitem">
																														<children>
																															<condition>
																																<children>
																																	<conditionbranch xpath="../../n1:PI_flag  = &apos;true&apos;">
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
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
														</children>
													</paragraph>
													<table>
														<properties border="0" cellpadding="0" cellspacing="0"/>
														<children>
															<tablebody>
																<children>
																	<tablerow>
																		<children>
																			<tablecell>
																				<properties valign="top" width="70"/>
																				<children>
																					<text fixtext="Primary Reviewers:">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties valign="top" width="141"/>
																				<children>
																					<template match="n1:SubmissionDetails" matchtype="schemagraphitem">
																						<children>
																							<template match="n1:ProtocolReviewer" matchtype="schemagraphitem">
																								<children>
																									<template match="n1:Person" matchtype="schemagraphitem">
																										<children>
																											<template match="n1:Fullname" matchtype="schemagraphitem">
																												<children>
																													<condition>
																														<children>
																															<conditionbranch xpath="../../n1:ReviewerTypeCode = 1">
																																<children>
																																	<paragraph paragraphtag="p">
																																		<children>
																																			<content>
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
																			<tablecell>
																				<properties valign="top" width="113"/>
																				<children>
																					<text fixtext="Secondary Reviewers:">
																						<styles font-weight="bold"/>
																					</text>
																				</children>
																			</tablecell>
																			<tablecell>
																				<properties valign="top" width="100"/>
																				<children>
																					<template match="n1:SubmissionDetails" matchtype="schemagraphitem">
																						<children>
																							<template match="n1:ProtocolReviewer" matchtype="schemagraphitem">
																								<children>
																									<template match="n1:Person" matchtype="schemagraphitem">
																										<children>
																											<template match="n1:Fullname" matchtype="schemagraphitem">
																												<children>
																													<condition>
																														<children>
																															<conditionbranch xpath="../../n1:ReviewerTypeCode =2">
																																<children>
																																	<paragraph paragraphtag="p">
																																		<children>
																																			<content>
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
													<newline/>
												</children>
											</template>
										</children>
									</template>
								</children>
							</paragraph>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts>
		<children>
			<globaltemplate match="n1:ProtocolSubmission" matchtype="schemagraphitem">
				<children>
					<template match="n1:ProtocolSubmission" matchtype="schemagraphitem">
						<children>
							<newline/>
							<newline/>
							<newline/>
							<newline/>
						</children>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate match="n1:ProtocolSummary" matchtype="schemagraphitem">
				<children>
					<template match="n1:ProtocolSummary" matchtype="schemagraphitem">
						<children>
							<newline/>
							<newline/>
							<newline/>
						</children>
					</template>
				</children>
			</globaltemplate>
			<globaltemplate match="n1:ResearchArea" matchtype="schemagraphitem">
				<children>
					<template match="n1:ResearchArea" matchtype="schemagraphitem">
						<children>
							<content/>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</globalparts>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.6in" papermarginright="0.6in" papermargintop="0.79in" paperwidth="8.5in"/>
	</pagelayout>
	<designfragments/>
</structure>
