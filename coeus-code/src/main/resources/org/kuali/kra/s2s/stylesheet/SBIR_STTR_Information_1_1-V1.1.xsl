<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:SBIR_STTR_Information="http://apply.grants.gov/forms/SBIR_STTR_Information_1_1-V1.1">

	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:header="http://apply.grants.gov/system/Header-V1.0" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0">

			<fo:layout-master-set>
				<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.2in" margin-right="0.2in">
					<fo:region-body margin-top="0.2in" margin-bottom="0.4in"/>
					<fo:region-after extent="0.4in"/>
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="default-page" format="1" initial-page-number="1">
				<fo:flow flow-name="xsl-region-body">
					<!--Data components-->
					<!--Block below is for the field named ProgramTypeRadioRequiredField with FieldID -->
					<fo:block-container position="absolute" left="4.848484848484849px" top="23.636363636363637px" height="12px" width="130.3030303030303px">
						<fo:block font-size="9px" font-family="Helvetica"/>
					</fo:block-container>
					<!--Block below is for the field named SBIRSTTRTypeRadioRequiredField with FieldID -->
					<fo:block-container position="absolute" left="4.848484848484849px" top="73.33333333333334px" height="12px" width="139.3939393939394px">
						<fo:block font-size="9px" font-family="Helvetica"/>
					</fo:block-container>
					
					
     <!-- NEW FORMAT BELOW ======================================================================================================================================= -->               
	 <!--  Note changed the above line to avoid the  org.xml.sax.SAXParseException that was being thrown -->
					<fo:block-container position="absolute" left="0.2in" top="1.0in" height="9in" width="7.9in" border="solid 1pt black">
						<fo:block />
					</fo:block-container>

					<fo:block-container position="absolute" left="0.3in" top="0.6in" height="15px">
						<fo:block text-align="center" font-size="12px" font-family="Helvetica" font-weight="bold">SBIR/STTR Information</fo:block>
					</fo:block-container>

 					<fo:block-container position="absolute" left="0.3in" top="0.5in" height="15px">
						<fo:block text-align="right" font-size="6px" font-family="Helvetica">OMB Number: 4040-0001</fo:block>
                        <fo:block text-align="right" font-size="6px" font-family="Helvetica">Expiration date: 06/30/2011</fo:block>
					</fo:block-container>

 
 
					<fo:block-container position="absolute" left="0.3in" top="1.2in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica" font-weight="bold">Program Type (select only one)*</fo:block>
					</fo:block-container>  

                    <fo:block-container position="absolute" left="0.3in" top="1.4in" height="12px">
                        <fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:ProgramType"/>
										<xsl:with-param name="schemaChoice">SBIR</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="0.45in" top="1.4in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">SBIR</fo:block>
					</fo:block-container>

 
					<fo:block-container position="absolute" left="1.25in" top="1.4in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:ProgramType"/>
										<xsl:with-param name="schemaChoice">STTR</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="1.5in" top="1.4in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">STTR</fo:block>
					</fo:block-container>


					<fo:block-container position="absolute" left="2.25in" top="1.4in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:ProgramType"/>
										<xsl:with-param name="schemaChoice">Both</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="2.5in" top="1.4in" height="12px" width="5.5in">
						<fo:block font-size="9px" font-family="Helvetica">Both&#160; 
                      	<fo:inline font-size="8px" font-family="Helvetica">(See agency-specific instructions to determine whether a particular agency allows a single submission for both SBIR and STTR)</fo:inline>
                        </fo:block>                        
					</fo:block-container>   

                    
                    
					<fo:block-container position="absolute" left="0.3in" top="1.8in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica" font-weight="bold">SBIR/STTR Type (select only one)*</fo:block>
					</fo:block-container>               
                    

					<fo:block-container position="absolute" left="0.3in" top="2.0in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:SBIRSTTRType"/>
										<xsl:with-param name="schemaChoice">Phase I</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="0.45in" top="2.0in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Phase I</fo:block>
					</fo:block-container>
                    
                    
					<fo:block-container position="absolute" left="1.25in" top="2.0in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:SBIRSTTRType"/>
										<xsl:with-param name="schemaChoice">Phase II</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="1.5in" top="2.0in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Phase II</fo:block>
					</fo:block-container>
                    

					<fo:block-container position="absolute" left="2.25in" top="2.0in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:SBIRSTTRType"/>
										<xsl:with-param name="schemaChoice">Fast-Track</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="2.5in" top="2.0in" height="12px" width="5.5in">
						<fo:block font-size="9px" font-family="Helvetica">Fast-Track
						<fo:inline font-size="8px" font-family="Helvetica">(See agency-specific instructions to determine whether a particular agency participates in Fast-Track)</fo:inline>
                        </fo:block>
					</fo:block-container>                  

                    
                    <fo:block-container position="absolute" left="0.2in" top="2.35in" width="7.9in" border="solid 1px black">
						<fo:block />
					</fo:block-container>     
                    
           			<fo:block-container position="absolute" left="0.3in" top="2.5in" height="15px">
						<fo:block font-size="10px" font-family="Helvetica" font-weight="bold">Questions 1-7 must be completed by all SBIR and STTR Applicants:</fo:block>
					</fo:block-container>         
                    
   
   					<fo:block-container position="absolute" left="0.3in" top="2.8in" height="36px" width="5.5in">
						<fo:block font-size="9px" font-family="Helvetica">1a. Do you certify that at the time of award your organization will meet the eligibility criteria for a small business as defined in the funding opportunity announcement?* </fo:block>
					</fo:block-container>
 
					<fo:block-container position="absolute" left="6.0in" top="2.8in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:SmallBusinessEligibility"/>
										<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
                    <fo:block-container position="absolute" left="6.25in" top="2.8in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Yes</fo:block> 
 					</fo:block-container>

					<fo:block-container position="absolute" left="6.75in" top="2.8in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:SmallBusinessEligibility"/>
										<xsl:with-param name="schemaChoice">N: No</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="7.0in" top="2.8in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">No</fo:block>
   					</fo:block-container>
 
					<fo:block-container position="absolute" left="0.3in" top="3.2in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">1b. Anticipated Number of personnel to be employed at your organization at the time of award.*</fo:block>
					</fo:block-container>
   					<fo:block-container position="absolute" left="6.0in" top="3.2in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">
							<xsl:choose>
								<xsl:when test="not(//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:NumberOfEmployees)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:NumberOfEmployees"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
 
                    
                    <fo:block-container position="absolute" left="0.2in" top="3.45in" width="7.9in" border="solid 1px black">
						<fo:block />
					</fo:block-container>       
                    
   					<fo:block-container position="absolute" left="0.3in" top="3.6in" height="24px" width="5.5in">
						<fo:block font-size="9px" font-family="Helvetica">2. Does this application include subcontracts with Federal laboratories or any other Federal Government agencies?*</fo:block>
					</fo:block-container>

   					<fo:block-container position="absolute" left="6.0in" top="3.6in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:SubcontractsIncluded"/>
										<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
 					<fo:block-container position="absolute" left="6.25in" top="3.6in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Yes</fo:block>
   					</fo:block-container>
                      
					<fo:block-container position="absolute" left="6.75in" top="3.6in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:SubcontractsIncluded"/>
										<xsl:with-param name="schemaChoice">N: No</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="7.0in" top="3.6in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">No</fo:block>
   					</fo:block-container>   
                    
   					<fo:block-container position="absolute" left="0.3in" top="4.0in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">If yes, insert the names of the Federal laboratories/agencies:*</fo:block>
					</fo:block-container>   
   					<fo:block-container position="absolute" left="0.3in" top="4.2in" height="48px" width="6.5in">
						<fo:block font-size="9px" font-family="Georgia">
							<xsl:choose>
								<xsl:when test="not(//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:SubcontractorNames) or //SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:SubcontractorNames = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:SubcontractorNames"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
 
                    
                    <fo:block-container position="absolute" left="0.2in" top="4.55in" width="7.9in" border="solid 1px black">
						<fo:block />
					</fo:block-container>       
                     					<fo:block-container position="absolute" left="0.3in" top="4.7in" height="24px" width="5.5in">
						<fo:block font-size="9px" font-family="Helvetica">3. Are you located in a HUBZone? To find out if your business is in a HUBZone, use the mapping utility provided by the Small Business Administration at its web site: http://www.sba.gov *</fo:block>
					</fo:block-container>  
					<fo:block-container position="absolute" left="6.0in" top="4.7in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:LocatedInHUBZone"/>
										<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>                  
                    <fo:block-container position="absolute" left="6.25in" top="4.7in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Yes</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="6.75in" top="4.7in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:LocatedInHUBZone"/>
										<xsl:with-param name="schemaChoice">N: No</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
 					</fo:block-container>	
					<fo:block-container position="absolute" left="7.0in" top="4.7in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">No</fo:block>
					</fo:block-container>               
 
                    
                    <fo:block-container position="absolute" left="0.2in" top="5.15in" width="7.9in" border="solid 1px black">
						<fo:block />
					</fo:block-container>
                    
                    <fo:block-container position="absolute" left="0.3in" top="5.3in" height="12px" width="5.5in">
						<fo:block font-size="9px" font-family="Helvetica">4. Will all research and development on the project be performed in its entirety in the United States?*</fo:block>
					</fo:block-container>                    
                    <fo:block-container position="absolute" left="6.0in" top="5.3in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:DomesticPerformance"/>
										<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
 					<fo:block-container position="absolute" left="6.25in" top="5.3in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Yes</fo:block>
					</fo:block-container>
 
					<fo:block-container position="absolute" left="6.75in" top="5.3in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:DomesticPerformance"/>
										<xsl:with-param name="schemaChoice">N: No</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container  position="absolute" left="7.0in" top="5.3in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">No</fo:block>
					</fo:block-container> 
                    
                   
					<fo:block-container position="absolute" left="0.3in" top="5.7in" height="12px" width="3in">
						<fo:block font-size="9px" font-family="Helvetica">If no, provide an explanation in an attached file.</fo:block>
					</fo:block-container>
 
					<fo:block-container position="absolute" left="3.7in" top="5.7in" height="12px" width="1.0in">
						<fo:block font-size="9px" font-family="Helvetica">Explanation:*</fo:block>
					</fo:block-container> 
                    <fo:block-container position="absolute" left="5.0in" top="5.7in" height="12px">
						<fo:block font-size="9px" font-family="Georgia">
							<xsl:choose>
								<xsl:when test="not(//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:NonDomesticPerformanceExplanation/att:FileName) or //SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:NonDomesticPerformanceExplanation/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:NonDomesticPerformanceExplanation/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
                    
 
                    
                    <fo:block-container position="absolute" left="0.2in" top="5.95in" width="7.9in" border="solid 1px black">
						<fo:block />
					</fo:block-container>
                    
                    
 					<fo:block-container position="absolute" left="0.3in" top="6.1in" height="36px" width="5.5in">
						<fo:block font-size="9px" font-family="Helvetica">5. Has the applicant and/or Program Director/Principal Investigator submitted proposals for essentially equivalent work under other Federal program solicitations or received other Federal awards for essentially equivalent work?*</fo:block>
					</fo:block-container>                   
                    
  					<fo:block-container position="absolute" left="6.0in" top="6.1in" height="12px" >
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:EquivalentProposalsSubmitted"/>
										<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="6.25in" top="6.1in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Yes</fo:block>
					</fo:block-container> 					
                    
                    <fo:block-container position="absolute" left="6.75in" top="6.1in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:EquivalentProposalsSubmitted"/>
										<xsl:with-param name="schemaChoice">N: No</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>                  
					<fo:block-container position="absolute" left="7.0in" top="6.1in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">No</fo:block>
					</fo:block-container> 
                    
                    <fo:block-container position="absolute" left="0.3in" top="6.6in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">If yes, insert the names of the other Federal agencies:*</fo:block>
					</fo:block-container>
                    
                    <fo:block-container position="absolute" left="0.3in" top="6.8in" height="36px" width="6.5in">
						<fo:block font-size="9px" font-family="Helvetica">
							<xsl:choose>
								<xsl:when test="not(//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:EquivalentProposalRecipients) or //SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:EquivalentProposalRecipients = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:EquivalentProposalRecipients"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
 
                    
                    <fo:block-container position="absolute" left="0.2in" top="7.05in" width="7.9in" border="solid 1px black">
						<fo:block />
					</fo:block-container>
                    
					<fo:block-container position="absolute" left="0.3in" top="7.2in" height="36px" width="5.5in">
						<fo:block font-size="9px" font-family="Helvetica">6.  Disclosure Permission Statement:  If this application does not result in an award, is the Government permitted to disclose the title of your proposed project, and the name, address, telephone number and e-mail address of the official signing for the applicant organization, to organizations that may be interested in contacting you for further information (e.g., possible collaborations, investment)?*</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="6.0in" top="7.2in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:DisclosurePermission"/>
										<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
							<fo:block-container position="absolute" left="6.25in" top="7.2in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Yes</fo:block>
					</fo:block-container>
                    
					<fo:block-container position="absolute" left="6.75in" top="7.2in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:DisclosurePermission"/>
										<xsl:with-param name="schemaChoice">N: No</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
				 <fo:block-container position="absolute" left="7.0in" top="7.2in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">No</fo:block>
					</fo:block-container>
 
                    
                    <fo:block-container position="absolute" left="0.2in" top="8.05in" width="7.9in" border="solid 1px black">
						<fo:block />
					</fo:block-container>
                    
					<fo:block-container position="absolute" left="0.3in" top="8.2in" height="24px" width="7.5in">
						<fo:block font-size="9px" font-family="Helvetica">7. Commercialization Plan:  If you are submitting a Phase II or Phase I/Phase II Fast-Track Application, include a Commercialization Plan in accordance with the agency announcement and/or agency-specific instructions.*</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="0.3in" top="8.6in" height="12px" width="1.0in">
						<fo:block font-size="9px" font-family="Helvetica">Attach File:*</fo:block>
					</fo:block-container>					
                    <fo:block-container position="absolute" left="1.5in" top="8.6in" height="12px">
						<fo:block font-size="9px" font-family="Georgia">
							<xsl:choose>
								<xsl:when test="not(//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:CommercializationPlan/att:FileName) or //SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:CommercializationPlan/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:CommercializationPlan/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
				</fo:flow>
			</fo:page-sequence>
		
        
        
        	<fo:page-sequence master-reference="default-page" format="1" initial-page-number="2">
				<fo:flow flow-name="xsl-region-body">
					<fo:block-container position="absolute" left="0.2in" top="1.0in" height="9in" width="7.9in" border="solid 1pt black">
						<fo:block />
					</fo:block-container>

					<fo:block-container position="absolute" left="0.3in" top="0.6in" height="15px">
						<fo:block text-align="center" font-size="12px" font-family="Helvetica" font-weight="bold">SBIR/STTR Information</fo:block>
					</fo:block-container>

 					<fo:block-container position="absolute" left="0.3in" top="0.5in" height="15px">
						<fo:block text-align="right" font-size="6px" font-family="Helvetica">OMB Number: 4040-0001</fo:block>
                        <fo:block text-align="right" font-size="6px" font-family="Helvetica">Expiration date: 06/30/2011</fo:block>
					</fo:block-container>

 
 
					<fo:block-container position="absolute" left="0.3in" top="1.2in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica" font-weight="bold">SBIR-Specific Questions:</fo:block>
					</fo:block-container>
 
					<fo:block-container position="absolute" left="0.3in" top="1.5in" height="24px" width="7.5in">
						<fo:block font-size="9px" font-style="italic" font-family="Helvetica" font-weight="bold">Questions 8 and 9 apply only to SBIR applications.  If you are submitting <fo:inline font-style="underline">ONLY</fo:inline> an STTR application, leave questions 8 and 9 blank and proceed to question 10.</fo:block>
					</fo:block-container>
					
                    <fo:block-container position="absolute" left="0.3in" top="1.9in" height="24px" width="5.5in">						
                    	<fo:block font-size="9px" font-family="Helvetica">8. Have you received SBIR Phase II awards from the Federal Government?  If yes, provide a company commercialization history in accordance with agency-specific instructions using this attachment.*</fo:block>
					</fo:block-container>
     				
                    <fo:block-container position="absolute" left="6.0in" top="1.9in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:SBIR_PhaseIIAwardsReceived"/>
										<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
                    <fo:block-container position="absolute" left="6.25in" top="1.9in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Yes</fo:block>
					</fo:block-container>
 
					<fo:block-container position="absolute" left="6.75in" top="1.9in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:SBIR_PhaseIIAwardsReceived"/>
										<xsl:with-param name="schemaChoice">N: No</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
                 	</fo:block-container>       
					<fo:block-container position="absolute" left="7.0in" top="1.9in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">No</fo:block>
					</fo:block-container> 
     
					<fo:block-container position="absolute" left="0.3in" top="2.4in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Attach File:*</fo:block>
					</fo:block-container>     
         
    			<fo:block-container position="absolute" left="1.5in" top="2.4in" height="12px">
						<fo:block font-size="9px" font-family="Georgia">
							<xsl:choose>
								<xsl:when test="not(//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:SBIR_CommercializationHistory/att:FileName) or //SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:SBIR_CommercializationHistory/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:SBIR_CommercializationHistory/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>	
     
     
					<fo:block-container position="absolute" left="0.3in" top="2.7in" height="24px" width="5.5in">
						<fo:block font-size="9px" font-family="Helvetica">9. Will the Project Director/Principal Investigator have his/her primary employment with the small business at the time of award?*</fo:block>
					</fo:block-container> 
                    
                    <fo:block-container position="absolute" left="6.0in" top="2.7in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:SBIR_PDPIPrimarilyEmployed"/>
										<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
                   <fo:block-container position="absolute" left="6.25in" top="2.7in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Yes</fo:block>
					</fo:block-container> 
 
					<fo:block-container position="absolute" left="6.75in" top="2.7in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:SBIR_PDPIPrimarilyEmployed"/>
										<xsl:with-param name="schemaChoice">N: No</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container position="absolute" left="7.0in" top="2.7in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">No</fo:block>
					</fo:block-container>
  
 
                    
                    <fo:block-container position="absolute" left="0.2in" top="3.25in" width="7.9in" border="solid 1px black">
						<fo:block />
					</fo:block-container>
                    
 
 
					<fo:block-container position="absolute" left="0.3in" top="3.4in" height="15px">
						<fo:block font-size="10px" font-style="normal" font-family="Helvetica" font-weight="bold">STTR-Specific Questions:</fo:block>
					</fo:block-container>
 
					<fo:block-container position="absolute" left="0.3in" top="3.6in" height="24px" width="7.5in">
						<fo:block font-size="9px" font-style="italic" font-family="Helvetica" font-weight="bold">Questions 10 and 11 apply only to STTR applications.  If you are submitting <fo:inline font-style="underline">ONLY</fo:inline> an SBIR application, leave questions 10 and 11 blank.</fo:block>
					</fo:block-container>
                     
 					<fo:block-container position="absolute" left="0.3in" top="4.0in" height="12px" width="5.5in">
						<fo:block font-size="9px" font-family="Helvetica">10. Please indicate whether the answer to BOTH of the following questions is TRUE:*</fo:block>
					</fo:block-container>
 					<fo:block-container position="absolute" left="0.3in" top="4.2in" height="24px" width="7.5in">
						<fo:block font-size="9px" font-family="Helvetica">(1) Does the Project Director/Principal Investigator have a formal appointment or commitment either with the small business directly (as an employee or a contractor) OR as an employee of the Research Institution, which in turn has made a commitment to the small business through the STTR application process; AND</fo:block>
					</fo:block-container>

                    <fo:block-container position="absolute" left="0.3in" top="4.7in" height="24px" width="7.5in">
						<fo:block font-size="9px" font-family="Helvetica">(2) Will the Project Director/Principal Investigator devote at least 10% effort to the proposed project?</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="6.0in" top="4.0in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:STTR_PDPICommitment"/>
										<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
                    <fo:block-container position="absolute" left="6.25in" top="4.0in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Yes</fo:block>
					</fo:block-container>

					<fo:block-container position="absolute" left="6.75in" top="4.0in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:STTR_PDPICommitment"/>
										<xsl:with-param name="schemaChoice">N: No</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
                    <fo:block-container position="absolute" left="7.0in" top="4.0in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">No</fo:block>
					</fo:block-container>
                    
                    
      				<fo:block-container position="absolute" left="0.3in" top="5.1in" height="24px" width="5.45in">
						<fo:block font-size="9px" font-family="Helvetica">11. In the joint research and development proposed in this project, does the small business perform at least 40% of the work and the research institution named in the application perform at least 30% of the work?*</fo:block>
					</fo:block-container>             
                    
					<fo:block-container position="absolute" left="6.0in" top="5.1in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:STTR_JointPerformancePercentage"/>
										<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
                   	<fo:block-container position="absolute" left="6.25in" top="5.1in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">Yes</fo:block>
					</fo:block-container>
 
					<fo:block-container position="absolute" left="6.75in" top="5.1in" height="12px">
						<fo:block font-size="9px" font-family="ZapfDingbats">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:STTR_JointPerformancePercentage"/>
										<xsl:with-param name="schemaChoice">N: No</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
                   	<fo:block-container position="absolute" left="7.0in" top="5.1in" height="12px">
						<fo:block font-size="9px" font-family="Helvetica">No</fo:block>
					</fo:block-container>
                 </fo:flow>
			</fo:page-sequence>
			            
            
            <fo:page-sequence master-reference="default-page" format="1" initial-page-number="3">
				<fo:flow flow-name="xsl-region-body">
					<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-body>
							<!--<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="center" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<fo:block font-size="12px" font-weight="bold">Attachments</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="9px">NonDomesticPerformanceExplanation</fo:block>
								</fo:table-cell>
							</fo:table-row> -->
							<!--<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="9px">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="9px" color="white">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="9px" font-family="Georgia">
										<xsl:value-of select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:NonDomesticPerformanceExplanation/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="9px" color="white">
										<xsl:value-of select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:NonDomesticPerformanceExplanation/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row> -->
							<!--<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="9px">CommercializationPlan</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="9px">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="9px" color="white">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="9px" font-family="Georgia">
										<xsl:value-of select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:CommercializationPlan/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="9px" color="white">
										<xsl:value-of select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:CommercializationPlan/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row> -->
							<!--<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="9px">SBIR</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="9px">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="9px" color="white">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row> -->
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="9px" font-family="Georgia">
										<xsl:value-of select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:SBIR_CommercializationHistory/att:FileName"/>
									</fo:block>
									<fo:block font-size="9px" color="white">
										(Mime-type: <xsl:value-of select="//SBIR_STTR_Information:SBIR_STTR_Information_1_1/SBIR_STTR_Information:SBIR_CommercializationHistory/att:MimeType"/> )
									</fo:block>
								</fo:table-cell>
                                <fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="9px" color="white">
										<xsl:value-of select="/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
								
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>

	<xsl:template name="radioButton">
		<xsl:param name="value"/>
		<xsl:param name="schemaChoice">Y: Yes</xsl:param>
		<xsl:choose>
			<xsl:when test="$value = $schemaChoice">
				<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats">&#x25cf;</fo:inline>
			</xsl:when>
			<xsl:otherwise>
				<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats">&#x274d;</fo:inline>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template name="checkbox">
		<xsl:param name="value"/>
		<xsl:param name="schemaChoice">Yes</xsl:param>
		<xsl:if test="$value = $schemaChoice">
			<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="9px">&#x2714;</fo:inline>
		</xsl:if>
	</xsl:template>
	<xsl:template name="formatDate">
		<xsl:param name="value"/>
		<xsl:if test="$value != ''">
			<xsl:value-of select="format-number(number(substring($value,6,2)), '00')"/>
			<xsl:text>/</xsl:text>
			<xsl:value-of select="format-number(number(substring($value,9,2)), '00')"/>
			<xsl:text>/</xsl:text>
			<xsl:value-of select="format-number(number(substring($value,1,4)), '0000')"/>
		</xsl:if>
	</xsl:template>
	<xsl:template name="addBlankLines">
		<xsl:param name="numLines"/>
		<xsl:if test="string($numLines) != ''">
			<xsl:if test="$numLines &gt; 0">
				<fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0">
					<fo:leader leader-pattern="space"/>
				</fo:block>
				<xsl:call-template name="addBlankLines">
					<xsl:with-param name="numLines" select="$numLines - 1"/>
				</xsl:call-template>
			</xsl:if>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>
