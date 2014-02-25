<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://irb.mit.edu/irbnamespace"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="irb.xsd" workingxmlfile="C:\correspondenceTemplates\xml files\renew.xml">
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
							<template match="n1:RenewalReminder" matchtype="schemagraphitem">
								<children>
									<paragraph paragraphtag="h2">
										<properties align="center"/>
										<children>
											<text fixtext="Massachusetts Institute of Technology">
												<styles font-weight="bold"/>
											</text>
										</children>
									</paragraph>
									<paragraph paragraphtag="h2">
										<properties align="center"/>
										<children>
											<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
												<children>
													<template match="n1:CommitteeName" matchtype="schemagraphitem">
														<children>
															<paragraph paragraphtag="h3">
																<children>
																	<content>
																		<styles font-weight="bold"/>
																	</content>
																</children>
															</paragraph>
														</children>
													</template>
												</children>
											</template>
										</children>
									</paragraph>
									<newline/>
									<table>
										<properties border="0"/>
										<children>
											<tablebody>
												<children>
													<tablerow>
														<children>
															<tablecell>
																<properties align="right" height="15" valign="top" width="43"/>
																<children>
																	<text fixtext="To:">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties height="15" valign="top" width="281"/>
																<children>
																	<template match="n1:Protocol" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:Investigator" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:Person" matchtype="schemagraphitem">
																						<children>
																							<template match="n1:Fullname" matchtype="schemagraphitem">
																								<children>
																									<condition>
																										<children>
																											<conditionbranch xpath="../../n1:PI_flag =&apos;true&apos;">
																												<children>
																													<content/>
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
																<properties align="right" height="15" valign="top" width="100"/>
																<children>
																	<text fixtext="Date:">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties height="15" width="99"/>
																<children>
																	<template match="n1:CurrentDate" matchtype="schemagraphitem">
																		<children>
																			<content>
																				<format string="MM/DD/YYYY"/>
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
																<properties align="right" height="15" valign="top" width="43"/>
																<children>
																	<text fixtext="From:">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties height="15" valign="top" width="281"/>
																<children>
																	<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:CommitteeName" matchtype="schemagraphitem">
																				<children>
																					<content/>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</tablecell>
															<tablecell>
																<properties align="right" height="15" valign="top" width="100"/>
																<children>
																	<text fixtext="Expiration Date:">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties height="15" valign="top" width="99"/>
																<children>
																	<template match="n1:Protocol" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:ExpirationDate" matchtype="schemagraphitem">
																						<children>
																							<content>
																								<format string="MM/DD/YYYY"/>
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
																<properties align="right" height="15" valign="top" width="43"/>
																<children>
																	<text fixtext="Re:">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<properties colspan="3" height="15" valign="top" width="292"/>
																<children>
																	<text fixtext="Protocol #  ">
																		<styles font-weight="bold"/>
																	</text>
																	<template match="n1:Protocol" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:ProtocolNumber" matchtype="schemagraphitem">
																						<children>
																							<content/>
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
									<text fixtext="This is a reminder that the "/>
									<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
										<children>
											<template match="n1:CommitteeName" matchtype="schemagraphitem">
												<children>
													<content/>
												</children>
											</template>
										</children>
									</template>
									<text fixtext=" approval for the above-mentioned protocol will expire on "/>
									<template match="n1:Protocol" matchtype="schemagraphitem">
										<children>
											<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
												<children>
													<template match="n1:ExpirationDate" matchtype="schemagraphitem">
														<children>
															<content>
																<format string="MM/DD/YYYY"/>
															</content>
														</children>
													</template>
												</children>
											</template>
										</children>
									</template>
									<text fixtext=".   "/>
									<newline/>
									<newline/>
									<text fixtext="Please return the Continuing Review Questionnaire (web.mit.edu/committees/couhes/forms.shtml) before the next Committee meeting scheduled for "/>
									<autocalc xpath="n1:NextScheduleDate[  number(concat( substring ( string( n1:ScheduleDate ), 1,4) , substring ( string(n1:ScheduleDate ), 6,2)  , substring ( string(n1:ScheduleDate ), 9,2)    )) -  
number(concat(substring (string(../ n1:CurrentDate )  , 1,4) , 
                         substring (string( ../ n1:CurrentDate  ) ,6,2 ), 
                          substring (string( ../n1:CurrentDate),9,2))) &gt; 15      ]/n1:ScheduleDate">
										<format string="MM/DD/YYYY" datatype="date"/>
									</autocalc>
									<text fixtext=", or it will be necessary for you to submit a new appliation to the Committee if you want to contine this project.  Please provide COUHES with the OSP number assigned to this project whether or not you intend to continue this research.  Please note that no human use research may be continued under this protocol without COUHES approval. "/>
									<newline/>
									<newline/>
									<newline/>
									<newline/>
									<newline/>
									<newline/>
									<newline/>
									<text fixtext="cc:  Tom Duff"/>
									<newline/>
									<newline/>
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
	</pagelayout>
	<designfragments/>
</structure>
