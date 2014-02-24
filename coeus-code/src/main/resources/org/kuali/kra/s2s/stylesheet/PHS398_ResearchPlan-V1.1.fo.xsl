<?xml version="1.0" encoding="UTF-8"?>
<!-- $Revision:   1.0  $ -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:PHS398_ResearchPlan="http://apply.grants.gov/forms/PHS398_ResearchPlan-V1.1">
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.2in" margin-right="0.2in">
					<fo:region-body margin-top="0.2in" margin-bottom="0.4in"/>
					<fo:region-after extent="0.4in"/>
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="default-page" format="1" initial-page-number="1">
				<fo:static-content flow-name="xsl-region-after">
					<fo:block>
						<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
						</fo:inline>
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<fo:block-container background-color="black" border-style="none" position="absolute" left="18px" top="55px" width="530px" height="0.60px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="18px" top="79px" width="530px" height="0.60px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="18px" top="195px" width="530px" height="0.60px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="18px" top="715px" width="530px" height="0.60px">
                                        <fo:block/>
                                        </fo:block-container>
					
					<fo:block-container background-color="black" border-style="none" position="absolute" left="18px" top="390px" width="530px" height="0.30px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="18px" top="530px" width="530px" height="0.30px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="18px"  top="685px" width="530px" height="0.20px">
                                            <fo:block/>
                                        </fo:block-container>

					<fo:block-container background-color="black" border-style="none" position="absolute" left="18px"  top="55px" width="0.60px" height="660px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="548px" top="55px" width="0.60px" height="660px">
                                            <fo:block/>
                                        </fo:block-container>

					
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="188.4848484848485px" hyphenate="true" language="en" top="239.39393939393943px" height="13.333333333333334px" width="340.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:IntroductionToApplication/PHS398_ResearchPlan:attFile/att:FileName) or /PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:IntroductionToApplication/PHS398_ResearchPlan:attFile/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:IntroductionToApplication/PHS398_ResearchPlan:attFile/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="188.4848484848485px" hyphenate="true" language="en" top="270.1212121212121px" height="13.333333333333334px" width="340.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:SpecificAims/PHS398_ResearchPlan:attFile/att:FileName) or /PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:SpecificAims/PHS398_ResearchPlan:attFile/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:SpecificAims/PHS398_ResearchPlan:attFile/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="188.4848484848485px" hyphenate="true" language="en" top="290.1212121212121px" height="13.333333333333334px" width="340.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:BackgroundSignificance/PHS398_ResearchPlan:attFile/att:FileName) or /PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:BackgroundSignificance/PHS398_ResearchPlan:attFile/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:BackgroundSignificance/PHS398_ResearchPlan:attFile/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="188.4848484848485px" hyphenate="true" language="en" top="310.90909090909093px" height="13.333333333333334px" width="340.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:ProgressReport/PHS398_ResearchPlan:attFile/att:FileName) or /PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:ProgressReport/PHS398_ResearchPlan:attFile/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:ProgressReport/PHS398_ResearchPlan:attFile/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="188.4848484848485px" hyphenate="true" language="en" top="330.51515151515156px" height="13.333333333333334px" width="340.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:ResearchDesignMethods/PHS398_ResearchPlan:attFile/att:FileName) or /PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:ResearchDesignMethods/PHS398_ResearchPlan:attFile/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:ResearchDesignMethods/PHS398_ResearchPlan:attFile/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="188.4848484848485px" hyphenate="true" language="en" top="350.51515151515156px" height="13.333333333333334px" width="340.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:InclusionEnrollmentReport/PHS398_ResearchPlan:attFile/att:FileName) or /PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:InclusionEnrollmentReport/PHS398_ResearchPlan:attFile/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:InclusionEnrollmentReport/PHS398_ResearchPlan:attFile/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="188.4848484848485px" hyphenate="true" language="en" top="370.51515151515156px" height="13.333333333333334px" width="340.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:ProgressReportPublicationList/PHS398_ResearchPlan:attFile/att:FileName) or /PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:ProgressReportPublicationList/PHS398_ResearchPlan:attFile/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:ProgressReportPublicationList/PHS398_ResearchPlan:attFile/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="188.4848484848485px" hyphenate="true" language="en" top="450px" height="13.333333333333334px" width="340.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:ProtectionOfHumanSubjects/PHS398_ResearchPlan:attFile/att:FileName) or /PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:ProtectionOfHumanSubjects/PHS398_ResearchPlan:attFile/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:ProtectionOfHumanSubjects/PHS398_ResearchPlan:attFile/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="188.4848484848485px" hyphenate="true" language="en" top="470px" height="13.333333333333334px" width="340.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:InclusionOfWomenAndMinorities/PHS398_ResearchPlan:attFile/att:FileName) or /PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:InclusionOfWomenAndMinorities/PHS398_ResearchPlan:attFile/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
								<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:InclusionOfWomenAndMinorities/PHS398_ResearchPlan:attFile/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="189.0909090909091px" hyphenate="true" language="en" top="490px" height="13.333333333333334px" width="340.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:TargetedPlannedEnrollmentTable/PHS398_ResearchPlan:attFile/att:FileName) or /PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:TargetedPlannedEnrollmentTable/PHS398_ResearchPlan:attFile/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:TargetedPlannedEnrollmentTable/PHS398_ResearchPlan:attFile/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="189.0909090909091px" hyphenate="true" language="en" top="510px" height="13.333333333333334px" width="340.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:InclusionOfChildren/PHS398_ResearchPlan:attFile/att:FileName) or /PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:InclusionOfChildren/PHS398_ResearchPlan:attFile/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:InclusionOfChildren/PHS398_ResearchPlan:attFile/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="189.0909090909091px" hyphenate="true" language="en" top="565px" height="13.333333333333334px" width="340.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:VertebrateAnimals/PHS398_ResearchPlan:attFile/att:FileName) or /PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:VertebrateAnimals/PHS398_ResearchPlan:attFile/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:VertebrateAnimals/PHS398_ResearchPlan:attFile/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="189.0909090909091px" hyphenate="true" language="en" top="585px" height="13.333333333333334px" width="340.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:SelectAgentResearch/PHS398_ResearchPlan:attFile/att:FileName) or /PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:SelectAgentResearch/PHS398_ResearchPlan:attFile/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:SelectAgentResearch/PHS398_ResearchPlan:attFile/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="189.0909090909091px" hyphenate="true" language="en" top="605px" height="13.333333333333334px" width="340.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:MultiplePILeadershipPlan/PHS398_ResearchPlan:attFile/att:FileName) or /PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:MultiplePILeadershipPlan/PHS398_ResearchPlan:attFile/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:MultiplePILeadershipPlan/PHS398_ResearchPlan:attFile/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="189.0909090909091px" hyphenate="true" language="en" top="625px" height="13.333333333333334px" width="340.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:ConsortiumContractualArrangements/PHS398_ResearchPlan:attFile/att:FileName) or /PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:ConsortiumContractualArrangements/PHS398_ResearchPlan:attFile/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:ConsortiumContractualArrangements/PHS398_ResearchPlan:attFile/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="189.0909090909091px" hyphenate="true" language="en" top="645px" height="13.333333333333334px" width="340.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:LettersOfSupport/PHS398_ResearchPlan:attFile/att:FileName) or /PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:LettersOfSupport/PHS398_ResearchPlan:attFile/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:LettersOfSupport/PHS398_ResearchPlan:attFile/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="189.0909090909091px" hyphenate="true" language="en" top="665px" height="13.333333333333334px" width="340.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:ResourceSharingPlans/PHS398_ResearchPlan:attFile/att:FileName) or /PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:ResourceSharingPlans/PHS398_ResearchPlan:attFile/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:ResourceSharingPlans/PHS398_ResearchPlan:attFile/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="190.75757575757578px" hyphenate="true" language="en" top="60.666666666666667px" height="18.78787878787879px" width="200.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="14pt" font-style="normal" font-family="Helvetica" font-weight="bold">PHS 398 Research Plan</fo:block>
					</fo:block-container>
					
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="212.12121212121212px" hyphenate="true" language="en" top="25.454545454545457px" height="12.121212121212121px" width="71.51515151515152px">
						<fo:block background-color="transparent" color="#000000" font-size="12pt" font-style="normal" font-family="Helvetica" font-weight="bold"> Research Plan</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="22.424242424242426px" hyphenate="true" language="en" top="93.33333333333334px" height="12.121212121212121px" width="100.60606060606061px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">1. Application Type:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="22.424242424242426px" hyphenate="true" language="en" top="107.87878787878789px" height="23.03030303030303px" width="478.7878787878788px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">From SF 424 (R&amp;R) Cover Page and PHS398 Checklist.  The responses provided on these pages, regarding the type of application being submitted, are repeated for your reference, as you attach the appropriate sections of the research plan.&#xD;
</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="149.69696969696972px" height="12.121212121212121px" width="105.45454545454545px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">*Type of Application:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="93.93939393939394px" hyphenate="true" language="en" top="169.0909090909091px" height="13.333333333333334px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Resubmission</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="50.303030303030305px" hyphenate="true" language="en" top="169.0909090909091px" height="13.333333333333334px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">New</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="176.36363636363637px" hyphenate="true" language="en" top="169.0909090909091px" height="13.333333333333334px" width="36.96969696969697px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Renewal</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="234.54545454545456px" hyphenate="true" language="en" top="169.0909090909091px" height="13.333333333333334px" width="51.515151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Continuation</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="309.0909090909091px" hyphenate="true" language="en" top="169.0909090909091px" height="12.121212121212121px" width="42.42424242424243px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Revision</fo:block>
					</fo:block-container>
					
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="480.7575757575758px" hyphenate="true" language="en" top="35.8484848484849px" height="12.121212121212121px" width="105.45454545454545px">
						<fo:block background-color="transparent" color="#000000" font-size="6pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 0925-0001</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="478.51515151515156px" hyphenate="true" language="en" top="45.7878787878789px" height="12.121212121212121px" width="129.69696969696972px">
						<fo:block background-color="transparent" color="#000000" font-size="6pt" font-style="normal" font-family="Helvetica" font-weight="normal">Expiration Date: 9/30/2007</fo:block>
					</fo:block-container>
					
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="270.1212121212121px" height="13.333333333333334px" width="68.48484848484848px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">2. Specific Aims</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="290.1212121212121px" height="13.333333333333334px" width="117.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">3. Background and Significance</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="310.90909090909093px" height="13.333333333333334px" width="148.4848484848485px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">4. Preliminary Studies / Progress Report</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="330.51515151515156px" height="13.333333333333334px" width="124.24242424242425px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">5. Research Design and Methods</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="350.51515151515156px" height="13.333333333333334px" width="124.24242424242425px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">6. Inclusion Enrollment Report</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="370.51515151515156px" height="13.333333333333334px" width="124.24242424242425px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">7. Progress Report Publication List</fo:block>
					</fo:block-container>										
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="450px" height="13.333333333333334px" width="123.63636363636364px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">8. Protection of Human Subjects</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="470px" height="13.333333333333334px" width="135.75757575757575px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">9. Inclusion of Women and Minorities</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="490px" height="13.333333333333334px" width="139.75757575757575px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">10. Targeted/Planned Enrollment Table</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="510px" height="13.333333333333334px" width="90.45454545454545px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">11. Inclusion of Children</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="565px" height="13.333333333333334px" width="87.87878787878789px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">12. Vertebrate Animals</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="585px" height="13.333333333333334px" width="100.87878787878789px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">13. Select Agent Research</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="605px" height="13.333333333333334px" width="100.87878787878789px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">14. Multiple PI Leadership Plan</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="625px" height="13.333333333333334px" width="152.12121212121212px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">15. Consortium/Contractual Arrangements</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="645px" height="13.333333333333334px" width="80.60606060606061px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">16. Letters of Support</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="665px" height="13.333333333333334px" width="113.93939393939395px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">17. Resource Sharing Plan(s)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" top="695px" height="13.333333333333334px" width="100.60606060606061px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">18. Appendix</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="239.39393939393943px" height="13.333333333333334px" width="106.66666666666667px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">1. Introduction to Application </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="250.72727272727275px" height="13.333333333333334px" width="148.4848484848485px">
						<fo:block background-color="transparent" color="#000000" font-size="6pt" font-style="normal" font-family="Helvetica" font-weight="normal">(for RESUBMISSION or REVISION only)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="414px" height="50.303030303030305px" width="513.939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Attachments 8-11 apply only when you have answered "yes" to the question "are human subjects involved" on the R&amp;R Other Project Information
Form. In this case, attachments 8-11 may be required, and you are encouraged to consult the Application guide instructions and/or the
specific Funding Opportunity Announcement to determine which sections must be submitted with this application.</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="398.1818181818182px" height="12.121212121212121px" width="125.15151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" text-decoration="underline">Human Subjects Sections</fo:block>
					</fo:block-container>
					
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="540px" height="12.121212121212121px" width="125.15151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" text-decoration="underline">Other Research Plan Sections</fo:block>
					</fo:block-container>					
					
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="208.4848484848485px" height="12.121212121212121px" width="164.24242424242425px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">2. Research Plan Attachments:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" top="224.24242424242425px" height="12.121212121212121px" width="304.24242424242425px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Please attach applicable sections of the research plan, below.</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="41.21212121212121px" hyphenate="true" language="en" top="169.0909090909091px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//PHS398_ResearchPlan:PHS398_ResearchPlan[1]/PHS398_ResearchPlan:ApplicationType[1]/PHS398_ResearchPlan:TypeOfApplication[1]"/>
										<xsl:with-param name="schemaChoice">New</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.03030303030303px" hyphenate="true" language="en" top="169.0909090909091px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//PHS398_ResearchPlan:PHS398_ResearchPlan[1]/PHS398_ResearchPlan:ApplicationType[1]/PHS398_ResearchPlan:TypeOfApplication[1]"/>
										<xsl:with-param name="schemaChoice">Resubmission</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="165.45454545454547px" hyphenate="true" language="en" top="169.0909090909091px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//PHS398_ResearchPlan:PHS398_ResearchPlan[1]/PHS398_ResearchPlan:ApplicationType[1]/PHS398_ResearchPlan:TypeOfApplication[1]"/>
										<xsl:with-param name="schemaChoice">Renewal</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="223.63636363636365px" hyphenate="true" language="en" top="169.0909090909091px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//PHS398_ResearchPlan:PHS398_ResearchPlan[1]/PHS398_ResearchPlan:ApplicationType[1]/PHS398_ResearchPlan:TypeOfApplication[1]"/>
										<xsl:with-param name="schemaChoice">Continuation</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="298.1818181818182px" hyphenate="true" language="en" top="169.0909090909091px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//PHS398_ResearchPlan:PHS398_ResearchPlan[1]/PHS398_ResearchPlan:ApplicationType[1]/PHS398_ResearchPlan:TypeOfApplication[1]"/>
										<xsl:with-param name="schemaChoice">Revision</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="406.06060606060606px" hyphenate="true" language="en" top="169.0909090909091px" height="12.121212121212121px" width="13.333333333333334px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal"/>
					</fo:block-container>
				</fo:flow>
			</fo:page-sequence>
			<fo:page-sequence master-reference="default-page" format="1" initial-page-number="2">
				<fo:static-content flow-name="xsl-region-after">
					<fo:block>
						<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
						</fo:inline>
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="center" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<fo:block font-size="14pt" text-decoration="underline">Attachments</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">IntroductionToApplication_attDataGroup0</fo:block>
      `                  </fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:IntroductionToApplication/PHS398_ResearchPlan:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:IntroductionToApplication/PHS398_ResearchPlan:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">SpecificAims_attDataGroup0</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:SpecificAims/PHS398_ResearchPlan:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:SpecificAims/PHS398_ResearchPlan:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">BackgroundSignificance_attDataGroup0</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:BackgroundSignificance/PHS398_ResearchPlan:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:BackgroundSignificance/PHS398_ResearchPlan:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">ProgressReport_attDataGroup0</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:ProgressReport/PHS398_ResearchPlan:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:ProgressReport/PHS398_ResearchPlan:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">ResearchDesignMethods_attDataGroup0</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:ResearchDesignMethods/PHS398_ResearchPlan:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:ResearchDesignMethods/PHS398_ResearchPlan:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">InclusionEnrollmentReport_attDataGroup0</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:InclusionEnrollmentReport/PHS398_ResearchPlan:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:InclusionEnrollmentReport/PHS398_ResearchPlan:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">ProgressReportPublicationList_attDataGroup0</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:ProgressReportPublicationList/PHS398_ResearchPlan:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:ProgressReportPublicationList/PHS398_ResearchPlan:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>							
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">ProtectionOfHumanSubjects_attDataGroup0</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:ProtectionOfHumanSubjects/PHS398_ResearchPlan:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:ProtectionOfHumanSubjects/PHS398_ResearchPlan:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">InclusionOfWomenAndMinorities_attDataGroup0</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:InclusionOfWomenAndMinorities/PHS398_ResearchPlan:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:InclusionOfWomenAndMinorities/PHS398_ResearchPlan:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">TargetedPlannedEnrollmentTable_attDataGroup0</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:TargetedPlannedEnrollmentTable/PHS398_ResearchPlan:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:TargetedPlannedEnrollmentTable/PHS398_ResearchPlan:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">InclusionOfChildren_attDataGroup0</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:InclusionOfChildren/PHS398_ResearchPlan:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:HumanSubjectSection/PHS398_ResearchPlan:InclusionOfChildren/PHS398_ResearchPlan:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">VertebrateAnimals_attDataGroup0</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:VertebrateAnimals/PHS398_ResearchPlan:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:VertebrateAnimals/PHS398_ResearchPlan:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">SelectAgentResearch_attDataGroup0</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:SelectAgentResearch/PHS398_ResearchPlan:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:SelectAgentResearch/PHS398_ResearchPlan:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">MultiplePILeadershipPlan_attDataGroup0</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:MultiplePILeadershipPlan/PHS398_ResearchPlan:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:MultiplePILeadershipPlan/PHS398_ResearchPlan:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">ConsortiumContractualArrangements_attDataGroup0</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:ConsortiumContractualArrangements/PHS398_ResearchPlan:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:ConsortiumContractualArrangements/PHS398_ResearchPlan:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">LettersOfSupport_attDataGroup0</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:LettersOfSupport/PHS398_ResearchPlan:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:LettersOfSupport/PHS398_ResearchPlan:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">ResourceSharingPlans_attDataGroup0</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:ResourceSharingPlans/PHS398_ResearchPlan:attFile/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:OtherResearchPlanSections/PHS398_ResearchPlan:ResourceSharingPlans/PHS398_ResearchPlan:attFile/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>

					<!--  multiple attachmets -->
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">Appendix</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							
							<xsl:for-each select="//PHS398_ResearchPlan:PHS398_ResearchPlan/PHS398_ResearchPlan:ResearchPlanAttachments/PHS398_ResearchPlan:Appendix/att:AttachedFile">
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							</xsl:for-each>
							
						</fo:table-body>
					</fo:table>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	
	
	<xsl:template name="radioButton">
		<xsl:param name="value"/>
		<xsl:param name="schemaChoice">Yes</xsl:param>
		<xsl:choose>
			<xsl:when test="$value = $schemaChoice">
				<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
			</xsl:when>
			<xsl:otherwise>
				<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="checkbox">
		<xsl:param name="value"/>
		<xsl:param name="schemaChoice">Yes</xsl:param>
		<xsl:if test="$value = $schemaChoice">
			<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="11pt">&#x2714;</fo:inline>
		</xsl:if>
	</xsl:template>
	<xsl:template name="formatDate">
		<xsl:param name="value"/>
		<xsl:if test="$value != ''">
			<xsl:value-of select="format-number(substring($value,6,2), '00')"/>
			<xsl:text>/</xsl:text>
			<xsl:value-of select="format-number(substring($value,9,2), '00')"/>
			<xsl:text>/</xsl:text>
			<xsl:value-of select="format-number(substring($value,1,4), '0000')"/>
		</xsl:if>
	</xsl:template>
	<xsl:template name="addBlankLines">
		<xsl:param name="numLines"/>
		<xsl:if test="string($numLines) != ''">
			<xsl:if test="$numLines &gt; 0">
				<fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0">
					<fo:leader leader-pattern="space"/>
				</fo:block>
				<xsl:call-template name="addBlankLines">
					<xsl:with-param name="numLines" select="$numLines - 1"/>
				</xsl:call-template>
			</xsl:if>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>
