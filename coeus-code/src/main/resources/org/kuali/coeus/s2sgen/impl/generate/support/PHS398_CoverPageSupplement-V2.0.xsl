<?xml version="1.0" encoding="UTF-8"?><!-- $Revision:   1.4  $ -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xmlns:PHS398_CoverPageSupplement_2_0="http://apply.grants.gov/forms/PHS398_CoverPageSupplement_2_0-V2.0" 
xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" 
xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" 
xmlns:glob="http://apply.grants.gov/system/Global-V1.0" 
xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" 
xmlns:header="http://apply.grants.gov/system/Header-V1.0" 
xmlns:xs="http://www.w3.org/2001/XMLSchema">
   <xsl:output method="xml" indent="yes"/>
   <xsl:template match="PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0">
      <fo:root>
         <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.2in" margin-right="0.2in">
               <fo:region-body margin-top="0.2in" margin-bottom="0.4in"/>
              <fo:region-after region-name="region-after-all" extent=".3in" />
            </fo:simple-page-master>
         </fo:layout-master-set>
         <fo:page-sequence master-reference="default-page" format="1" initial-page-number="1">
            <fo:static-content flow-name="region-after-all">
               <fo:table width="100%"
                  space-before.optimum="0pt"
                  space-after.optimum="0pt"
                  table-layout="fixed">
                  <fo:table-column column-width="proportional-column-width(1)"/>
                  <fo:table-column column-width="proportional-column-width(1)"/>
                  <fo:table-body>
                     <fo:table-row>
                        <fo:table-cell
                         padding-start="0pt"
                         padding-end="0pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="0pt"
                         border-color="white">
                           <fo:block>
                              <fo:inline font-size="8px">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
                              </fo:inline>
                           </fo:block>
                        </fo:table-cell>
                        <fo:table-cell line-height="9pt"
                         padding-start="0pt"
                         padding-end="0pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="right"
                         border-style="solid"
                         border-width="0pt"
                         border-color="white">
                           <fo:block><fo:inline font-size="8px">Funding Opportunity Number: <xsl:value-of select="/*/*/header:OpportunityID"/></fo:inline>
                                     <fo:inline font-size="8px">.       Received Date: <xsl:value-of select="/*/*/footer:ReceivedDateTime"/></fo:inline></fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                  </fo:table-body>
               </fo:table>
            </fo:static-content>
            
            <fo:flow flow-name="xsl-region-body">
              
   				<fo:block-container position="absolute" left="0.2in" top="1.0in" height="15px">
                  <fo:block text-align="center" font-size="12px" font-family="Helvetica" font-weight="bold">PHS 398 Cover Page Supplement</fo:block>
               </fo:block-container>

				<fo:block-container position="absolute" left="0.2in" top="1.2in" height="12px">
                  <fo:block text-align="end" font-size="6px" font-family="Helvetica">OMB Number: 0925-0001</fo:block>
               </fo:block-container>
             
             
             <fo:block-container position="absolute" left="0.1in" top="1.4in" height="1.6in" width="8.1in" border-color="black" border-style="solid" border-top-width="1px" border-bottom-width="0.5px" border-left-width="1px" border-right-width="1px">
             	<fo:block />
              </fo:block-container>  
				
				<fo:block-container position="absolute" left="0.2in" top="1.5in" height="15px">
                  	<fo:block font-size="10px" font-family="Helvetica" font-weight="bold">1. Project Director / Principal Investigator (PD/PI)</fo:block>
               </fo:block-container> 
              
              
               <fo:block-container position="absolute" left="0.2in" top="1.8in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Prefix:</fo:block>
               </fo:block-container>
         	<fo:block-container position="absolute" left="1.5in" top="1.8in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Georgia">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:PDPI/PHS398_CoverPageSupplement_2_0:PDPIName/globLib:PrefixName) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:PDPI/PHS398_CoverPageSupplement_2_0:PDPIName/globLib:PrefixName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:PDPI/PHS398_CoverPageSupplement_2_0:PDPIName/globLib:PrefixName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
              
              
      		<fo:block-container position="absolute" left="0.2in" top="2.0in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">First Name*:</fo:block>
               </fo:block-container>   
                <fo:block-container position="absolute" left="1.5in" top="2.0in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Georgia">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:PDPI/PHS398_CoverPageSupplement_2_0:PDPIName/globLib:FirstName) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:PDPI/PHS398_CoverPageSupplement_2_0:PDPIName/globLib:FirstName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:PDPI/PHS398_CoverPageSupplement_2_0:PDPIName/globLib:FirstName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

         		<fo:block-container position="absolute" left="0.2in" top="2.2in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Middle Name:</fo:block>
               </fo:block-container>
               <fo:block-container position="absolute" left="1.5in" top="2.2in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Georgia">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:PDPI/PHS398_CoverPageSupplement_2_0:PDPIName/globLib:MiddleName) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:PDPI/PHS398_CoverPageSupplement_2_0:PDPIName/globLib:MiddleName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:PDPI/PHS398_CoverPageSupplement_2_0:PDPIName/globLib:MiddleName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               
               <fo:block-container position="absolute" left="0.2in" top="2.4in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Last Name*:</fo:block>
               </fo:block-container>
        
               <fo:block-container position="absolute" left="1.5in" top="2.4in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Georgia">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:PDPI/PHS398_CoverPageSupplement_2_0:PDPIName/globLib:LastName) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:PDPI/PHS398_CoverPageSupplement_2_0:PDPIName/globLib:LastName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:PDPI/PHS398_CoverPageSupplement_2_0:PDPIName/globLib:LastName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
                      <fo:block-container position="absolute" left="0.2in" top="2.6in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Suffix:</fo:block>
               </fo:block-container>
               <fo:block-container position="absolute" left="1.5in" top="2.6in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Georgia">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:PDPI/PHS398_CoverPageSupplement_2_0:PDPIName/globLib:SuffixName) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:PDPI/PHS398_CoverPageSupplement_2_0:PDPIName/globLib:SuffixName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:PDPI/PHS398_CoverPageSupplement_2_0:PDPIName/globLib:SuffixName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>


             <fo:block-container position="absolute" left="0.1in" top="3.0in" height="0.9in" width="8.1in" border-color="black" border-style="solid" border-top-width="0.5px" border-bottom-width="0.5px" border-left-width="1px" border-right-width="1px">
             	<fo:block />
              </fo:block-container>   

               <fo:block-container position="absolute" left="0.2in" top="3.1in" height="15px" keep-together="always">
                  <fo:block font-size="10px" font-family="Helvetica" font-weight="bold">2. Human Subjects</fo:block>
               </fo:block-container>
               
               <fo:block-container position="absolute" left="0.2in" top="3.4in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Clinical Trial?</fo:block>             
               </fo:block-container>
               
               <fo:block-container position="absolute" left="3.0in" top="3.4in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="ZapfDingbats">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:ClinicalTrial/PHS398_CoverPageSupplement_2_0:isClinicalTrial) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:ClinicalTrial/PHS398_CoverPageSupplement_2_0:isClinicalTrial = ''">
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value">N: No</xsl:with-param>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:ClinicalTrial/PHS398_CoverPageSupplement_2_0:isClinicalTrial"/>
                              <xsl:with-param name="schemaChoice">N: No</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               	</fo:block-container>            
            	<fo:block-container position="absolute" left="3.25in" top="3.4in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">No</fo:block>
               </fo:block-container>
               
               <fo:block-container position="absolute" left="3.75in" top="3.4in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="ZapfDingbats">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:ClinicalTrial/PHS398_CoverPageSupplement_2_0:isClinicalTrial) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:ClinicalTrial/PHS398_CoverPageSupplement_2_0:isClinicalTrial = ''">
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value">N: No</xsl:with-param>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:ClinicalTrial/PHS398_CoverPageSupplement_2_0:isClinicalTrial"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container position="absolute" left="4.0in" top="3.4in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Yes</fo:block>
               </fo:block-container>               
               
               
               
				<fo:block-container position="absolute" left="0.2in" top="3.6in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Agency-Defined Phase III Clinical Trial?*</fo:block>
               </fo:block-container>
               
               <fo:block-container position="absolute" left="3.0in" top="3.6in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="ZapfDingbats">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:ClinicalTrial/PHS398_CoverPageSupplement_2_0:isPhaseIIIClinicalTrial) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:ClinicalTrial/PHS398_CoverPageSupplement_2_0:isPhaseIIIClinicalTrial = ''">
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value">N: No</xsl:with-param>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:ClinicalTrial/PHS398_CoverPageSupplement_2_0:isPhaseIIIClinicalTrial"/>
                              <xsl:with-param name="schemaChoice">N: No</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
              <fo:block-container position="absolute" left="3.25in" top="3.6in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">No</fo:block>
               </fo:block-container>
               
               
             <fo:block-container position="absolute" left="3.75in" top="3.6in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="ZapfDingbats">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:ClinicalTrial/PHS398_CoverPageSupplement_2_0:isPhaseIIIClinicalTrial) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:ClinicalTrial/PHS398_CoverPageSupplement_2_0:isPhaseIIIClinicalTrial = ''">
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value">N: No</xsl:with-param>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:ClinicalTrial/PHS398_CoverPageSupplement_2_0:isPhaseIIIClinicalTrial"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>  
                 <fo:block-container position="absolute" left="4.0in" top="3.6in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Yes</fo:block>
               </fo:block-container>
               
               
             <fo:block-container position="absolute" left="0.1in" top="3.9in" height="1.4in" width="8.1in" border-color="black" border-style="solid"  border-top-width="0.5px" border-bottom-width="0.5px" border-left-width="1px" border-right-width="1px">
             	<fo:block />
              </fo:block-container>   				
              
              <fo:block-container position="absolute" left="0.2in" top="4.1in" height="15px" keep-together="always">
                  	<fo:block font-size="10px" font-family="Helvetica" font-weight="bold">3.  Permission Statement*</fo:block>
               	</fo:block-container>            
               
     			<fo:block-container position="absolute" left="0.2in" top="4.4in" height="36px" width="7.6in">
                  <fo:block font-size="9px" font-family="Helvetica">
                  	If this application does not result in an award, is the Government permitted to disclose the title of your proposed project, and the name,
				  	address, telephone number and e-mail address of the official signing for the applicant organization, to organizations that may be
				  	interested in contacting you for further information (e.g., possible collaborations, investment)?
                  </fo:block>
               </fo:block-container>          
      
               <fo:block-container position="absolute" left="0.2in" top="5.0in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="ZapfDingbats">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="PHS398_CoverPageSupplement_2_0:Permission[1]"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container> 
               <fo:block-container position="absolute" left="0.45in" top="5.0in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Yes</fo:block>
               </fo:block-container>
               
               <fo:block-container position="absolute" left="0.95in" top="5.0in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="ZapfDingbats">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="PHS398_CoverPageSupplement_2_0:Permission[1]"/>
                              <xsl:with-param name="schemaChoice">N: No</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
                <fo:block-container position="absolute" left="1.2in" top="5.0in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">No</fo:block>
               </fo:block-container>        
               

<!-- program income -->
               
               
             <fo:block-container position="absolute" left="0.1in" top="5.3in" height="4.2in" width="8.1in" border-color="black" border-style="solid"  border-top-width="0.5px" border-bottom-width="1px" border-left-width="1px" border-right-width="1px">
             	<fo:block />
              </fo:block-container>   				
              
                 <fo:block-container position="absolute" left="0.2in" top="5.5in" height="12px" keep-together="always">
                  <fo:block font-size="10px" font-family="Helvetica" font-weight="bold">4. Program Income*</fo:block>
               </fo:block-container>
               
               <fo:block-container position="absolute" left="0.2in" top="5.7in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Is program income anticipated during the periods for which the grant support is requested?</fo:block>
               </fo:block-container>            
                
         	<fo:block-container position="absolute" left="5.75in" top="5.7in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="ZapfDingbats">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="PHS398_CoverPageSupplement_2_0:ProgramIncome[1]"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container position="absolute" left="6.0in" top="5.7in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Yes</fo:block>
               </fo:block-container>
                             
    			<fo:block-container position="absolute" left="6.5in" top="5.7in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-style="normal" font-family="ZapfDingbats">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="PHS398_CoverPageSupplement_2_0:ProgramIncome[1]"/>
                              <xsl:with-param name="schemaChoice">N: No</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
                 <fo:block-container position="absolute" left="6.75in" top="5.7in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">No</fo:block>
               </fo:block-container>            
              
                <fo:block-container position="absolute" left="0.2in" top="5.9in" height="24px" width="8.0in">
                  <fo:block font-size="9px" font-family="Helvetica">
                  If you checked "yes" above (indicating that program income is anticipated), then use the format below to reflect the amount and
                  source(s).  Otherwise, leave this section blank.</fo:block>
               </fo:block-container>             
              
    
               <fo:block-container position="absolute" left="0.2in" top="6.4in" height="12px" keep-together="always" width="1.0in">
                  <fo:block font-size="9px" font-family="Helvetica">Budget Period*</fo:block>
               </fo:block-container>    
              
               <fo:block-container position="absolute" left="1.6in" top="6.4in" height="12px" keep-together="always" width="2.0in">
                  <fo:block font-size="9px" font-family="Helvetica">Anticipated Amount ($)*</fo:block>
               </fo:block-container>              
              
        		<fo:block-container position="absolute" left="3.7in" top="6.4in" height="12px" keep-together="always" width="4.0in">
                  <fo:block font-size="9px" font-family="Helvetica">Source(s)*</fo:block>
               </fo:block-container>


				<fo:block-container position="absolute" left="0.2in" top="6.7in" height="auto" keep-together="always" width="1.0in"
				                   border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1pt">
                  <fo:block text-align="end" font-size="9px" font-family="Helvetica">
                     <xsl:choose>
                        <xsl:when test="not(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[1]/PHS398_CoverPageSupplement_2_0:BudgetPeriod[1]) or PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[1]/PHS398_CoverPageSupplement_2_0:BudgetPeriod[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[1]/PHS398_CoverPageSupplement_2_0:BudgetPeriod[1], '#,##0')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               	</fo:block-container>
	 			<fo:block-container position="absolute" left="1.6in" top="6.7in" height="auto" keep-together="always" width="2.0in"
                              border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1pt">
                  <fo:block text-align="end" font-size="9px" font-family="Helvetica">
                     <xsl:choose>
                        <xsl:when test="not(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[1]/PHS398_CoverPageSupplement_2_0:AnticipatedAmount[1]) or PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[1]/PHS398_CoverPageSupplement_2_0:AnticipatedAmount[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[1]/PHS398_CoverPageSupplement_2_0:AnticipatedAmount[1], '#,##0.00')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>         
              
         		<fo:block-container position="absolute" left="3.7in" top="6.7in" height="auto" keep-together="always" width="4.0in" 
         				border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1pt">
                  <fo:block text-align="start" font-size="9px" font-family="Georgia">
                     <xsl:choose>
                        <xsl:when test="not(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[1]/PHS398_CoverPageSupplement_2_0:Source[1]) or PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[1]/PHS398_CoverPageSupplement_2_0:Source[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[1]/PHS398_CoverPageSupplement_2_0:Source[1]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>             
              

               	<fo:block-container position="absolute" left="0.2in" top="7.3in" height="auto" keep-together="always" width="1.0in"
				                   border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1pt">
   					<fo:block text-align="end" font-size="9px" font-family="Helvetica">
                     <xsl:choose>
                        <xsl:when test="not(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[2]/PHS398_CoverPageSupplement_2_0:BudgetPeriod[1]) or PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[2]/PHS398_CoverPageSupplement_2_0:BudgetPeriod[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[2]/PHS398_CoverPageSupplement_2_0:BudgetPeriod[1], '#,##0')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

               	<fo:block-container position="absolute" left="1.6in" top="7.3in" height="auto" keep-together="always" width="2.0in"
                      	border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1pt">
                  <fo:block text-align="end" font-size="9px" font-family="Helvetica">
                  	<xsl:choose>
                        <xsl:when test="not(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[2]/PHS398_CoverPageSupplement_2_0:AnticipatedAmount[1]) or PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[2]/PHS398_CoverPageSupplement_2_0:AnticipatedAmount[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[2]/PHS398_CoverPageSupplement_2_0:AnticipatedAmount[1], '#,##0.00')"/>
                        </xsl:otherwise>
                     </xsl:choose>

                  </fo:block>
               </fo:block-container>
                    <fo:block-container position="absolute" left="3.7in" top="7.3in" height="auto" keep-together="always" width="4.0in" 
         			border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1pt">
                  <fo:block text-align="start" font-size="9px" font-family="Georgia">
                  	<xsl:choose>
                        <xsl:when test="not(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[2]/PHS398_CoverPageSupplement_2_0:Source[1]) or PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[2]/PHS398_CoverPageSupplement_2_0:Source[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[2]/PHS398_CoverPageSupplement_2_0:Source[1]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
                           
               	<fo:block-container position="absolute" left="0.2in" top="7.8in" height="auto" keep-together="always" width="1.0in"
				                   border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1pt">
   					<fo:block text-align="end" font-size="9px" font-family="Helvetica">
   						<xsl:choose>
                        <xsl:when test="not(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[3]/PHS398_CoverPageSupplement_2_0:BudgetPeriod[1]) or PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[3]/PHS398_CoverPageSupplement_2_0:BudgetPeriod[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[3]/PHS398_CoverPageSupplement_2_0:BudgetPeriod[1], '#,##0')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               	<fo:block-container position="absolute" left="1.6in" top="7.8in" height="auto" keep-together="always" width="2.0in"
                      	border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1pt">
                  <fo:block text-align="end" font-size="9px" font-family="Helvetica">
                  	<xsl:choose>
                        <xsl:when test="not(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[3]/PHS398_CoverPageSupplement_2_0:AnticipatedAmount[1]) or PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[3]/PHS398_CoverPageSupplement_2_0:AnticipatedAmount[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[3]/PHS398_CoverPageSupplement_2_0:AnticipatedAmount[1], '#,##0.00')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>             
                    <fo:block-container position="absolute" left="3.7in" top="7.8in" height="auto" keep-together="always" width="4.0in" 
         			border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1pt">
                  <fo:block text-align="start" font-size="9px" font-family="Georgia">
                  	<xsl:choose>
                        <xsl:when test="not(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[3]/PHS398_CoverPageSupplement_2_0:Source[1]) or PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[1]/PHS398_CoverPageSupplement_2_0:Source[3] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[3]/PHS398_CoverPageSupplement_2_0:Source[1]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>               

               
               	<fo:block-container position="absolute" left="0.2in" top="8.3in" height="auto" keep-together="always" width="1.0in"
				                   border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1pt">
   					<fo:block text-align="end" font-size="9px" font-family="Helvetica">
   						<xsl:choose>
                        <xsl:when test="not(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[4]/PHS398_CoverPageSupplement_2_0:BudgetPeriod[1]) or PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[4]/PHS398_CoverPageSupplement_2_0:BudgetPeriod[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[4]/PHS398_CoverPageSupplement_2_0:BudgetPeriod[1], '#,##0')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               	<fo:block-container position="absolute" left="1.6in" top="8.3in" height="auto" keep-together="always" width="2.0in"
                      	border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1pt">
                  <fo:block text-align="end" font-size="9px" font-family="Helvetica">
                  	<xsl:choose>
                        <xsl:when test="not(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[4]/PHS398_CoverPageSupplement_2_0:AnticipatedAmount[1]) or PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[4]/PHS398_CoverPageSupplement_2_0:AnticipatedAmount[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[4]/PHS398_CoverPageSupplement_2_0:AnticipatedAmount[1], '#,##0.00')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
                     <fo:block-container position="absolute" left="3.7in" top="8.3in" height="auto" keep-together="always" width="4.0in" 
         			border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1pt">
                  <fo:block text-align="start" font-size="9px" font-family="Georgia">
                  <xsl:choose>
                        <xsl:when test="not(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[4]/PHS398_CoverPageSupplement_2_0:Source[1]) or PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[4]/PHS398_CoverPageSupplement_2_0:Source[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[4]/PHS398_CoverPageSupplement_2_0:Source[1]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>                        
               

               	<fo:block-container position="absolute" left="0.2in" top="8.8in" height="auto" keep-together="always" width="1.0in" border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1pt">
   					<fo:block text-align="end" font-size="9px" font-family="Helvetica">
   					<xsl:choose>
                        <xsl:when test="not(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[5]/PHS398_CoverPageSupplement_2_0:BudgetPeriod[1]) or PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[5]/PHS398_CoverPageSupplement_2_0:BudgetPeriod[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[5]/PHS398_CoverPageSupplement_2_0:BudgetPeriod[1], '#,##0')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               	<fo:block-container position="absolute" left="1.6in" top="8.8in" height="auto" keep-together="always" width="2.0in"
                      	border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1pt">
                  <fo:block text-align="end" font-size="9px" font-family="Helvetica">
                  <xsl:choose>
                        <xsl:when test="not(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[5]/PHS398_CoverPageSupplement_2_0:AnticipatedAmount[1]) or PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[5]/PHS398_CoverPageSupplement_2_0:AnticipatedAmount[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[5]/PHS398_CoverPageSupplement_2_0:AnticipatedAmount[1], '#,##0.00')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

                     <fo:block-container position="absolute" left="3.7in" top="8.8in" height="auto" keep-together="always" width="4.0in" 
         			border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1pt">
                  <fo:block text-align="start" font-size="9px" font-family="Georgia"><xsl:choose>
                        <xsl:when test="not(PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[5]/PHS398_CoverPageSupplement_2_0:Source[1]) or PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[5]/PHS398_CoverPageSupplement_2_0:Source[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="PHS398_CoverPageSupplement_2_0:IncomeBudgetPeriod[5]/PHS398_CoverPageSupplement_2_0:Source[1]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               
               
               
				<fo:block break-after="page">
                 	<xsl:text>&#xA;</xsl:text>
               	</fo:block>
               
                  <fo:block-container position="absolute" left="0.2in" top="1.0in" height="15px" keep-together="always">
                  	<fo:block text-align="center" font-size="12px" font-family="Helvetica" font-weight="bold">PHS 398 Cover Page Supplement</fo:block> 
                  </fo:block-container>
  
             <fo:block-container position="absolute" left="0.1in" top="1.2in" height="4.0in" width="8.1in" border-color="black" border-style="solid"  border-top-width="0.5px" border-bottom-width="0.5px" border-left-width="1px" border-right-width="1px">
             	<fo:block />
              </fo:block-container>
                 	               
               <fo:block-container position="absolute" left="0.2in" top="1.3in" height="15px" keep-together="always" >
                  <fo:block font-size="10px" font-family="Helvetica" font-weight="bold">5. Human Embryonic Stem Cells</fo:block>
               </fo:block-container>
               <fo:block-container position="absolute" left="0.2in" top="1.6in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Does the proposed project involve human embryonic stem cells?*</fo:block>
               </fo:block-container>
  
       	`	<fo:block-container position="absolute" left="4.5in" top="1.6in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="ZapfDingbats">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:isHumanStemCellsInvolved) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:isHumanStemCellsInvolved = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:isHumanStemCellsInvolved"/>
                              <xsl:with-param name="schemaChoice">N: No</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container position="absolute" left="4.75in" top="1.6in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">No</fo:block>
               </fo:block-container>
               
               
               <fo:block-container position="absolute" left="5.25in" top="1.6in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="ZapfDingbats">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:isHumanStemCellsInvolved) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:isHumanStemCellsInvolved = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:isHumanStemCellsInvolved"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container position="absolute" left="5.5in" top="1.6in" height="12px" keep-together="always" >
                  <fo:block font-size="9px" font-family="Helvetica">Yes</fo:block>
               </fo:block-container>
               
               
               <fo:block-container position="absolute" left="421.8181818181818px" top="106.06060606060606px" height="12px" width="13.333333333333334px">
                  <fo:block font-size="9px" font-family="ZapfDingbats" />
               </fo:block-container>       
                          
             
             
               <fo:block-container position="absolute" left="0.2in" top="1.8in" height="36px">
                  <fo:block font-size="9px" font-family="Helvetica">
					If the proposed project involves human embryonic stem cells, list below the registration number of the specific cell line(s) from the
					following list: http://grants.nih.gov/stem_cells/registry/current.htm.  Or, if a specific stem cell line cannot be referenced at this time,
					please check the box indicating that one from the registry will be used:</fo:block>
               </fo:block-container>
               <!--
               <fo:block-container position="absolute" left="350.6060606060606px" top="150.9090909090909px" height="12px" width="105.45454545454545px">
                  <fo:block font-size="9px" font-family="Helvetica">.  Or, if a specific </fo:block>
               </fo:block-container>-->
              
               <fo:block-container position="absolute" left="0.2in" top="2.3in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica" font-weight="bold">Cell Line(s):</fo:block>
               </fo:block-container>
               
               <fo:block-container position="absolute" left="1.5in" top="2.3in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="ZapfDingbats">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:StemCellsIndicator) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:StemCellsIndicator = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                          <fo:inline font-family="ZapfDingbats" font-size="9px">&#x274F;</fo:inline>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container position="absolute" left="1.5in" top="2.25in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="ZapfDingbats">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:StemCellsIndicator) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:StemCellsIndicator = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="checkbox">
                              <xsl:with-param name="value" select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:StemCellsIndicator"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
                  
               </fo:block-container>              
                       <fo:block-container position="absolute" left="1.75in" top="2.3in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Specific stem cell line cannot be referenced at this time.  One from the registry will be used.</fo:block>
               </fo:block-container>
                              
 <!--Row 1; Column 1-->              
               <fo:block-container position="absolute" left="0.2in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[1]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[1] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[1]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 2; Column 1-->              
               <fo:block-container position="absolute" left="0.2in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[2]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[2] = ''">
                           <fo:inline color="#000000">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[2]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
 
<!--Row 3; Column 1-->                
               <fo:block-container position="absolute" left="0.2in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[3]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[3] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[3]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 1-->                 
               <fo:block-container position="absolute" left="0.2in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[4]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[4] = ''">
                           <fo:inline color="#FFFFFF">&#160; </fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[4]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 5; Column 1-->                 
               <fo:block-container position="absolute" left="0.2in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[5]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[5] = ''">
						   <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[5]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 1-->                 
               <fo:block-container position="absolute" left="0.2in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[6]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[6] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[6]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 1-->                  
               <fo:block-container position="absolute" left="0.2in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[7]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[7] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[7]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 1-->                  
               <fo:block-container position="absolute" left="0.2in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[8]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[8] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[8]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 1-->   
               <fo:block-container position="absolute" left="0.2in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[9]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[9] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[9]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 10; Column 1-->                  
               <fo:block-container position="absolute" left="0.2in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[10]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[10] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[10]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 1; Column 2-->                  
               <fo:block-container position="absolute" left="0.6in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[11]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[11] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[11]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               

<!--Row 2; Column 2-->  
               <fo:block-container position="absolute" left="0.6in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[12]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[12] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[12]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 3; Column 2-->                 
               <fo:block-container position="absolute" left="0.6in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[13]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[13] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[13]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 2-->                 
               <fo:block-container position="absolute" left="0.6in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[14]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[14] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[14]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
 
<!--Row 5; Column 2-->                
               <fo:block-container position="absolute" left="0.6in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[15]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[15] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[15]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 2-->                 
               <fo:block-container position="absolute" left="0.6in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[16]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[16] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[16]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 2-->  
               <fo:block-container position="absolute" left="0.6in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[17]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[17] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[17]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 2-->                 
               <fo:block-container position="absolute" left="0.6in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[18]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[18] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[18]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 2-->                 
               <fo:block-container position="absolute" left="0.6in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[19]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[19] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[19]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
 
<!--Row 10; Column 2-->                
               <fo:block-container position="absolute" left="0.6in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[20]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[20] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[20]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>


<!-- new cell lines -->
 
<!--Row 1; Column 3; Cell Line 21 -->
               <fo:block-container position="absolute" left="1.0in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[21]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[21] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[21]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 2; Column 3; Cell Line 22 -->
               <fo:block-container position="absolute" left="1.0in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[22]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[22] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[22]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 3; Column 3;  Cell Line 23 -->
               <fo:block-container position="absolute" left="1.0in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[23]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[23] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[23]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 3; Cell Line 24 -->
               <fo:block-container position="absolute" left="1.0in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[24]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[24] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[24]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 5; Column 3; Cell Line 25 -->
               <fo:block-container position="absolute" left="1.0in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[25]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[25] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[25]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 3;  Cell Line 26 -->
               <fo:block-container position="absolute" left="1.0in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[26]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[26] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[26]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 3; Cell Line 27 -->
               <fo:block-container position="absolute" left="1.0in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[27]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[27] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[27]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 3; Cell Line 28 -->
               <fo:block-container position="absolute" left="1.0in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[28]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[28] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[28]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 3; Cell Line 29 -->
               <fo:block-container position="absolute" left="1.0in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[29]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[29] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[29]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 10; Column 3; Cell Line 30 -->
               <fo:block-container position="absolute" left="1.0in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[30]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[30] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[30]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 1; Column 4; Cell Line 31 -->
               <fo:block-container position="absolute" left="1.4in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[31]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[31] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[31]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 2; Column 4; Cell Line 32 -->
               <fo:block-container position="absolute" left="1.4in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[32]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[32] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[32]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 3; Column 4; Cell Line 33 -->
               <fo:block-container position="absolute" left="1.4in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[33]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[33] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[33]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 4; Cell Line 34 -->
               <fo:block-container position="absolute" left="1.4in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[34]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[34] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[34]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 5; Column 4;  Cell Line 35 -->
               <fo:block-container position="absolute" left="1.4in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[35]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[35] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[35]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 4; Cell Line 36 -->
               <fo:block-container position="absolute" left="1.4in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[36]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[36] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[36]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 4; Cell Line 37 -->
               <fo:block-container position="absolute" left="1.4in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[37]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[37] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[37]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 4; Cell Line 38 -->
               <fo:block-container position="absolute" left="1.4in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[38]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[38] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[38]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 4; Cell Line 39 -->
               <fo:block-container position="absolute" left="1.4in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[39]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[39] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[39]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 10; Column 4; Cell Line 40 -->
               <fo:block-container position="absolute" left="1.4in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[40]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[40] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[40]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 1; Column 5; Cell Line 41 -->
               <fo:block-container position="absolute" left="1.8in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[41]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[41] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[41]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 2; Column 5; Cell Line 42 -->
               <fo:block-container position="absolute" left="1.8in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[42]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[42] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[42]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 3; Column 5; Cell Line 43 -->
               <fo:block-container position="absolute" left="1.8in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[43]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[43] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[43]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 5; Cell Line 44 -->
               <fo:block-container position="absolute" left="1.8in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[44]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[44] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[44]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 5; Column 5; Cell Line 45 -->
               <fo:block-container position="absolute" left="1.8in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[45]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[45] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[45]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 5; Cell Line 46 -->
               <fo:block-container position="absolute" left="1.8in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[46]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[46] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[46]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 5; Cell Line 47 -->
               <fo:block-container position="absolute" left="1.8in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[47]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[47] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[47]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 5; Cell Line 48 -->
               <fo:block-container position="absolute" left="1.8in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[48]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[48] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[48]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 5; Cell Line 49 -->
               <fo:block-container position="absolute" left="1.8in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[49]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[49] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[49]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 10; Column 5; Cell Line 50 -->
               <fo:block-container position="absolute" left="1.8in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[50]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[50] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[50]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 1; Column 6; Cell Line 51 -->
               <fo:block-container position="absolute" left="2.2in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[51]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[51] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[51]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 2; Column 6; Cell Line 52 -->
               <fo:block-container position="absolute" left="2.2in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[52]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[52] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[52]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 3; Column 6; Cell Line 53 -->
               <fo:block-container position="absolute" left="2.2in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[53]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[53] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[53]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 6; Cell Line 54 -->
               <fo:block-container position="absolute" left="2.2in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[54]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[54] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[54]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 5; Column 6; Cell Line 55 -->
               <fo:block-container position="absolute" left="2.2in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[55]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[55] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[55]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 6; Cell Line 56 -->
               <fo:block-container position="absolute" left="2.2in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[56]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[56] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[56]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 6; Cell Line 57 -->
               <fo:block-container position="absolute" left="2.2in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[57]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[57] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[57]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 6; Cell Line 58 -->
               <fo:block-container position="absolute" left="2.2in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[58]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[58] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[58]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 6; Cell Line 59 -->
               <fo:block-container position="absolute" left="2.2in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[59]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[59] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[59]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 10; Column 6; Cell Line 60 -->
               <fo:block-container position="absolute" left="2.2in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[60]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[60] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[60]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 1; Column 7; Cell Line 61 -->
               <fo:block-container position="absolute" left="2.6in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[61]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[61] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[61]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 2; Column 7; Cell Line 62 -->
               <fo:block-container position="absolute" left="2.6in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[62]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[62] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[62]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 3; Column 7; Cell Line 63 -->
               <fo:block-container position="absolute" left="2.6in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[63]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[63] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[63]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 7; Cell Line 64 -->
               <fo:block-container position="absolute" left="2.6in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[64]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[64] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[64]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 5; Column 7; Cell Line 65 -->
               <fo:block-container position="absolute" left="2.6in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[65]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[65] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[65]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 7; Cell Line 66 -->
               <fo:block-container position="absolute" left="2.6in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[66]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[66] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[66]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 7; Cell Line 67 -->
               <fo:block-container position="absolute" left="2.6in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[67]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[67] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[67]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 7; Cell Line 68 -->
               <fo:block-container position="absolute" left="2.6in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[68]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[68] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[68]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 7; Cell Line 69 -->
               <fo:block-container position="absolute" left="2.6in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[69]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[69] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[69]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 10; Column 7; Cell Line 70 -->
               <fo:block-container position="absolute" left="2.6in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[70]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[70] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[70]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 1; Column 8; Cell Line 71 -->
               <fo:block-container position="absolute" left="3.0in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[71]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[71] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[71]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 2; Column 8; Cell Line 72 -->
               <fo:block-container position="absolute" left="3.0in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[72]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[72] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[72]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 3; Column 8; Cell Line 73 -->
               <fo:block-container position="absolute" left="3.0in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[73]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[73] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[73]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 8; Cell Line 74 -->
               <fo:block-container position="absolute" left="3.0in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[74]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[74] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[74]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 5; Column 8; Cell Line 75 -->
               <fo:block-container position="absolute" left="3.0in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[75]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[75] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[75]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 8; Cell Line 76 -->
               <fo:block-container position="absolute" left="3.0in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[76]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[76] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[76]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 8; Cell Line 77 -->
               <fo:block-container position="absolute" left="3.0in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[77]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[77] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[77]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 8; Cell Line 78 -->
               <fo:block-container position="absolute" left="3.0in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[78]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[78] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[78]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 8; Cell Line 79 -->
               <fo:block-container position="absolute" left="3.0in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[79]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[79] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[79]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 10; Column 8; Cell Line 80 -->
               <fo:block-container position="absolute" left="3.0in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[80]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[80] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[80]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 1; Column 9; Cell Line 81 -->
               <fo:block-container position="absolute" left="3.4in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[81]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[81] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[81]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 2; Column 9; Cell Line 82 -->
               <fo:block-container position="absolute" left="3.4in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[82]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[82] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[82]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 3; Column 9; Cell Line 83 -->
               <fo:block-container position="absolute" left="3.4in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[83]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[83] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[83]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 9; Cell Line 84 -->
               <fo:block-container position="absolute" left="3.4in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[84]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[84] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[84]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 5; Column 9; Cell Line 85 -->
               <fo:block-container position="absolute" left="3.4in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[85]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[85] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[85]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 9; Cell Line 86 -->
               <fo:block-container position="absolute" left="3.4in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[86]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[86] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[86]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 9; Cell Line 87 -->
               <fo:block-container position="absolute" left="3.4in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[87]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[87] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[87]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 9; Cell Line 88 -->
               <fo:block-container position="absolute" left="3.4in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[88]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[88] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[88]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 9; Cell Line 89 -->
               <fo:block-container position="absolute" left="3.4in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[89]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[89] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[89]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 10; Column 9; Cell Line 90 -->
               <fo:block-container position="absolute" left="3.4in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[90]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[90] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[90]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 1; Column 10; Cell Line 91 -->
               <fo:block-container position="absolute" left="3.8in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[91]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[91] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[91]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 2; Column 10; Cell Line 92 -->
               <fo:block-container position="absolute" left="3.8in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[92]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[92] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[92]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 3; Column 10; Cell Line 93 -->
               <fo:block-container position="absolute" left="3.8in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[93]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[93] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[93]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 10; Cell Line 94 -->
               <fo:block-container position="absolute" left="3.8in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[94]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[94] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[94]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 5; Column 10; Cell Line 95 -->
               <fo:block-container position="absolute" left="3.8in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[95]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[95] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[95]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 10; Cell Line 96 -->
               <fo:block-container position="absolute" left="3.8in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[96]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[96] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[96]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 10; Cell Line 97 -->
               <fo:block-container position="absolute" left="3.8in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[97]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[97] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[97]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 10; Cell Line 98 -->
               <fo:block-container position="absolute" left="3.8in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[98]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[98] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[98]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 10; Cell Line 99 -->
               <fo:block-container position="absolute" left="3.8in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[99]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[99] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[99]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 10; Column 10; Cell Line 100 -->
               <fo:block-container position="absolute" left="3.8in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[100]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[100] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[100]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 1; Column 11; Cell Line 101 -->
               <fo:block-container position="absolute" left="4.2in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[101]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[101] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[101]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 2; Column 11; Cell Line 102 -->
               <fo:block-container position="absolute" left="4.2in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[102]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[102] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[102]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 3; Column 11; Cell Line 103 -->
               <fo:block-container position="absolute" left="4.2in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[103]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[103] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[103]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 11; Cell Line 104 -->
               <fo:block-container position="absolute" left="4.2in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[104]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[104] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[104]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 5; Column 11; Cell Line 105 -->
               <fo:block-container position="absolute" left="4.2in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[105]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[105] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[105]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 11; Cell Line 106 -->
               <fo:block-container position="absolute" left="4.2in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[106]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[106] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[106]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 11; Cell Line 107 -->
               <fo:block-container position="absolute" left="4.2in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[107]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[107] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[107]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 11; Cell Line 108 -->
               <fo:block-container position="absolute" left="4.2in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[108]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[108] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[108]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 11; Cell Line 109 -->
               <fo:block-container position="absolute" left="4.2in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[109]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[109] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[109]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 10; Column 11; Cell Line 110 -->
               <fo:block-container position="absolute" left="4.2in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[110]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[110] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[110]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 1; Column 12; Cell Line 111 -->
               <fo:block-container position="absolute" left="4.6in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[111]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[111] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[111]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 2; Column 12; Cell Line 112 -->
               <fo:block-container position="absolute" left="4.6in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[112]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[112] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[112]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 3; Column 12; Cell Line 113 -->
               <fo:block-container position="absolute" left="4.6in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[113]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[113] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[113]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 12; Cell Line 114 -->
               <fo:block-container position="absolute" left="4.6in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[114]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[114] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[114]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 5; Column 12; Cell Line 115 -->
               <fo:block-container position="absolute" left="4.6in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[115]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[115] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[115]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 12; Cell Line 116 -->
               <fo:block-container position="absolute" left="4.6in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[116]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[116] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[116]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 12; Cell Line 117 -->
               <fo:block-container position="absolute" left="4.6in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[117]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[117] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[117]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 12; Cell Line 118 -->
               <fo:block-container position="absolute" left="4.6in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[118]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[118] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[118]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 12; Cell Line 119 -->
               <fo:block-container position="absolute" left="4.6in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[119]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[119] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[119]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 10; Column 12; Cell Line 120 -->
               <fo:block-container position="absolute" left="4.6in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[120]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[120] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[120]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 1; Column 13; Cell Line 121 -->
               <fo:block-container position="absolute" left="5.0in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[121]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[121] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[121]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 2; Column 13; Cell Line 122 -->
               <fo:block-container position="absolute" left="5.0in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[122]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[122] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[122]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 3; Column 13; Cell Line 123 -->
               <fo:block-container position="absolute" left="5.0in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[123]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[123] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[123]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 13; Cell Line 124 -->
               <fo:block-container position="absolute" left="5.0in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[124]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[124] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[124]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 5; Column 13; Cell Line 125 -->
               <fo:block-container position="absolute" left="5.0in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[125]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[125] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[125]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 13; Cell Line 126 -->
               <fo:block-container position="absolute" left="5.0in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[126]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[126] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[126]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 13; Cell Line 127 -->
               <fo:block-container position="absolute" left="5.0in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[127]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[127] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[127]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 13; Cell Line 128 -->
               <fo:block-container position="absolute" left="5.0in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[128]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[128] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[128]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 13; Cell Line 129 -->
               <fo:block-container position="absolute" left="5.0in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[129]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[129] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[129]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 10; Column 13; Cell Line 130 -->
               <fo:block-container position="absolute" left="5.0in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[130]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[130] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[130]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 1; Column 14; Cell Line 131 -->
               <fo:block-container position="absolute" left="5.4in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[131]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[131] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[131]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 2; Column 14; Cell Line 132 -->
               <fo:block-container position="absolute" left="5.4in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[132]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[132] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[132]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 3; Column 14; Cell Line 133 -->
               <fo:block-container position="absolute" left="5.4in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[133]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[133] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[133]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 14; Cell Line 134 -->
               <fo:block-container position="absolute" left="5.4in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[134]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[134] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[134]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 5; Column 14; Cell Line 135 -->
               <fo:block-container position="absolute" left="5.4in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[135]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[135] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[135]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 14; Cell Line 136 -->
               <fo:block-container position="absolute" left="5.4in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[136]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[136] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[136]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 14; Cell Line 137 -->
               <fo:block-container position="absolute" left="5.4in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[137]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[137] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[137]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 14; Cell Line 138 -->
               <fo:block-container position="absolute" left="5.4in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[138]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[138] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[138]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 14; Cell Line 139 -->
               <fo:block-container position="absolute" left="5.4in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[139]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[139] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[139]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 10; Column 14; Cell Line 140 -->
               <fo:block-container position="absolute" left="5.4in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[140]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[140] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[140]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 1; Column 15; Cell Line 141 -->
               <fo:block-container position="absolute" left="5.8in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[141]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[141] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[141]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 2; Column 15; Cell Line 142 -->
               <fo:block-container position="absolute" left="5.8in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[142]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[142] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[142]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 3; Column 15; Cell Line 143 -->
               <fo:block-container position="absolute" left="5.8in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[143]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[143] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[143]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 15; Cell Line 144 -->
               <fo:block-container position="absolute" left="5.8in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[144]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[144] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[144]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 5; Column 15; Cell Line 145 -->
               <fo:block-container position="absolute" left="5.8in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[145]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[145] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[145]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 15; Cell Line 146 -->
               <fo:block-container position="absolute" left="5.8in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[146]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[146] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[146]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 15; Cell Line 147 -->
               <fo:block-container position="absolute" left="5.8in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[147]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[147] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[147]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 15; Cell Line 148 -->
               <fo:block-container position="absolute" left="5.8in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[148]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[148] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[148]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 15; Cell Line 149 -->
               <fo:block-container position="absolute" left="5.8in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[149]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[149] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[149]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 10; Column 15; Cell Line 150 -->
               <fo:block-container position="absolute" left="5.8in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[150]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[150] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[150]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 1; Column 16; Cell Line 151 -->
               <fo:block-container position="absolute" left="6.2in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[151]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[151] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[151]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 2; Column 16; Cell Line 152 -->
               <fo:block-container position="absolute" left="6.2in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[152]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[152] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[152]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 3; Column 16; Cell Line 153 -->
               <fo:block-container position="absolute" left="6.2in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[153]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[153] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[153]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 16; Cell Line 154 -->
               <fo:block-container position="absolute" left="6.2in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[154]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[154] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[154]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 5; Column 16; Cell Line 155 -->
               <fo:block-container position="absolute" left="6.2in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[155]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[155] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[155]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 16; Cell Line 156 -->
               <fo:block-container position="absolute" left="6.2in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[156]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[156] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[156]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 16; Cell Line 157 -->
               <fo:block-container position="absolute" left="6.2in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[157]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[157] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[157]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 16; Cell Line 158 -->
               <fo:block-container position="absolute" left="6.2in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[158]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[158] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[158]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 16; Cell Line 159 -->
               <fo:block-container position="absolute" left="6.2in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[159]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[159] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[159]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 10; Column 16; Cell Line 160 -->
               <fo:block-container position="absolute" left="6.2in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[160]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[160] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[160]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 1; Column 17; Cell Line 161 -->
               <fo:block-container position="absolute" left="6.6in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[161]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[161] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[161]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 2; Column 17; Cell Line 162 -->
               <fo:block-container position="absolute" left="6.6in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[162]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[162] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[162]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 3; Column 17; Cell Line 163 -->
               <fo:block-container position="absolute" left="6.6in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[163]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[163] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[163]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 17; Cell Line 164 -->
               <fo:block-container position="absolute" left="6.6in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[164]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[164] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[164]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 5; Column 17; Cell Line 165 -->
               <fo:block-container position="absolute" left="6.6in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[165]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[165] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[165]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 17; Cell Line 166 -->
               <fo:block-container position="absolute" left="6.6in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[166]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[166] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[166]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 17; Cell Line 167 -->
               <fo:block-container position="absolute" left="6.6in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[167]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[167] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[167]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 17; Cell Line 168 -->
               <fo:block-container position="absolute" left="6.6in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[168]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[168] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[168]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 17; Cell Line 169 -->
               <fo:block-container position="absolute" left="6.6in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[169]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[169] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[169]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 10; Column 17; Cell Line 170 -->
               <fo:block-container position="absolute" left="6.6in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[170]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[170] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[170]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 1; Column 18; Cell Line 171 -->
               <fo:block-container position="absolute" left="7.0in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[171]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[171] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[171]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 2; Column 18; Cell Line 172 -->
               <fo:block-container position="absolute" left="7.0in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[172]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[172] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[172]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 3; Column 18; Cell Line 173 -->
               <fo:block-container position="absolute" left="7.0in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[173]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[173] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[173]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 18; Cell Line 174 -->
               <fo:block-container position="absolute" left="7.0in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[174]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[174] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[174]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 5; Column 18; Cell Line 175 -->
               <fo:block-container position="absolute" left="7.0in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[175]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[175] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[175]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 18; Cell Line 176 -->
               <fo:block-container position="absolute" left="7.0in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[176]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[176] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[176]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 18; Cell Line 177 -->
               <fo:block-container position="absolute" left="7.0in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[177]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[177] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[177]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 18; Cell Line 178 -->
               <fo:block-container position="absolute" left="7.0in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[178]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[178] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[178]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 18; Cell Line 179 -->
               <fo:block-container position="absolute" left="7.0in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[179]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[179] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[179]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 10; Column 18; Cell Line 180 -->
               <fo:block-container position="absolute" left="7.0in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[180]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[180] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[180]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 1; Column 19; Cell Line 181 -->
               <fo:block-container position="absolute" left="7.4in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[181]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[181] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[181]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 2; Column 19; Cell Line 182 -->
               <fo:block-container position="absolute" left="7.4in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[182]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[182] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[182]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 3; Column 19; Cell Line 183 -->
               <fo:block-container position="absolute" left="7.4in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[183]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[183] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[183]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 19; Cell Line 184 -->
               <fo:block-container position="absolute" left="7.4in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[184]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[184] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[184]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 5; Column 19; Cell Line 185 -->
               <fo:block-container position="absolute" left="7.4in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[185]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[185] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[185]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 19; Cell Line 186 -->
               <fo:block-container position="absolute" left="7.4in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[186]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[186] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[186]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 19; Cell Line 187 -->
               <fo:block-container position="absolute" left="7.4in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[187]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[187] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[187]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 19; Cell Line 188 -->
               <fo:block-container position="absolute" left="7.4in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[188]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[188] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[188]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 19; Cell Line 189 -->
               <fo:block-container position="absolute" left="7.4in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[189]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[189] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[189]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 10; Column 19; Cell Line 190 -->
               <fo:block-container position="absolute" left="7.4in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[190]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[190] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[190]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 1; Column 20; Cell Line 191 -->
               <fo:block-container position="absolute" left="7.8in" top="2.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[191]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[191] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[191]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 2; Column 20; Cell Line 192 -->
               <fo:block-container position="absolute" left="7.8in" top="2.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[192]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[192] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[192]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 3; Column 20; Cell Line 193 -->
               <fo:block-container position="absolute" left="7.8in" top="3.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[193]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[193] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[193]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 4; Column 20;  Cell Line 194 -->
               <fo:block-container position="absolute" left="7.8in" top="3.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[194]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[194] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[194]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 5; Column 20; Cell Line 195 -->
               <fo:block-container position="absolute" left="7.8in" top="3.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[195]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[195] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[195]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 6; Column 20; Cell Line 196 -->
               <fo:block-container position="absolute" left="7.8in" top="3.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[196]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[196] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[196]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 7; Column 20; Cell Line 197 -->
               <fo:block-container position="absolute" left="7.8in" top="4.1in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[197]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[197] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[197]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 8; Column 20; Cell Line 198 -->
               <fo:block-container position="absolute" left="7.8in" top="4.35in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[198]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[198] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[198]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 9; Column 20; Cell Line 199 -->
               <fo:block-container position="absolute" left="7.8in" top="4.6in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[199]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[199] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[199]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

<!--Row 10; Column 20; Cell Line 200 -->
               <fo:block-container position="absolute" left="7.8in" top="4.85in" height="12px" width="0.35in" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                  <fo:block font-size="9px" font-family="Helvetica" display-align="center" text-align="center">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[200]) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[200] = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:StemCells/PHS398_CoverPageSupplement_2_0:CellLines[200]"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>


<!-- end new cell lines -->
 
               <fo:block-container position="absolute" left="0.1in" top="5.2in" height="1.35in" width="8.1in" border-color="black" border-style="solid"  border-top-width="0.5px" border-bottom-width="0.5px" border-left-width="1px" border-right-width="1px">
             	<fo:block />
              </fo:block-container>   	
                           
               <fo:block-container position="absolute" left="0.2in" top="5.4in" height="15px"  keep-together="always">
                  <fo:block font-size="10px" font-family="Helvetica" font-weight="bold">6. Inventions and Patents (For renewal applications only)</fo:block>
               </fo:block-container>
 
               <fo:block-container position="absolute" left="0.2in" top="5.7in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Inventions and Patents*: </fo:block>
               </fo:block-container>
               
               <fo:block-container position="absolute" left="2.0in" top="5.7in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="ZapfDingbats">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:IsInventionsAndPatents"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               	</fo:block-container>
				<fo:block-container position="absolute" left="2.25in" top="5.7in" height="12px" keep-together="always">
                  	<fo:block font-size="9px" font-family="Helvetica">Yes</fo:block>
               	</fo:block-container>
               
               <fo:block-container position="absolute" left="2.75in" top="5.7in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="ZapfDingbats">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:IsInventionsAndPatents"/>
                              <xsl:with-param name="schemaChoice">N: No</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container position="absolute" left="3.0in" top="5.7in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">No</fo:block>
               </fo:block-container>

               <fo:block-container position="absolute" left="0.2in" top="6.0in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">If the answer is "Yes" then please answer the following:</fo:block>
               </fo:block-container>

                <fo:block-container position="absolute" left="0.2in" top="6.3in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Previously Reported*:  </fo:block>
               </fo:block-container>
            
               <fo:block-container position="absolute" left="2.0in" top="6.3in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="ZapfDingbats">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:IsPreviouslyReported"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               	</fo:block-container>
				<fo:block-container position="absolute" left="2.25in" top="6.3in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Yes</fo:block>
               </fo:block-container>
               
               <fo:block-container position="absolute" left="2.75in" top="6.3in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="ZapfDingbats">                     
                  	<xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:IsPreviouslyReported"/>
                              <xsl:with-param name="schemaChoice">N: No</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
				<fo:block-container position="absolute" left="3.0in" top="6.3in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">No</fo:block>
               </fo:block-container>
               
             <fo:block-container position="absolute" left="0.1in" top="6.55in" height="3.0in" width="8.1in" border-color="black" border-style="solid"  border-top-width="0.5px" border-bottom-width="1px" border-left-width="1px" border-right-width="1px">
             	<fo:block />
              </fo:block-container>   	
                             
            	<fo:block-container position="absolute" left="0.2in" top="6.7in" height="15px" keep-together="always">
                  	<fo:block font-size="10px" font-family="Helvetica" font-weight="bold">7. Change of Investigator / Change of Institution Questions</fo:block>
               	</fo:block-container>              	
                 
                 <fo:block-container  position="absolute" left="0.2in" top="7.1in" height="12px" keep-together="always">
                  <fo:block font-size="10px" font-family="ZapfDingbats">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <fo:inline font-family="ZapfDingbats" font-size="10px">&#x274F;</fo:inline>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               <fo:block-container  position="absolute" left="0.2in" top="7.05in" height="12px" keep-together="always">
                  <fo:block font-size="10px" font-family="ZapfDingbats">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:IsChangeOfPDPI) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:IsChangeOfPDPI = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="checkbox">
                              <xsl:with-param name="value" select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:IsChangeOfPDPI"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container> 
               <fo:block-container  position="absolute" left="0.75in" top="7.1in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Change of principal investigator / program director</fo:block>
               </fo:block-container>             	
 				
 				<fo:block-container position="absolute" left="0.2in" top="7.3in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Name of former principal investigator / program director:</fo:block>
               </fo:block-container> 
                
                <fo:block-container position="absolute" left="0.2in" top="7.5in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Prefix:</fo:block>
               </fo:block-container>              	
               	  	<fo:block-container position="absolute" left="1.5in" top="7.5in" height="12px" keep-together="always">
                  <fo:block text-align="start" font-size="9px" font-family="Georgia">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:FormerPD_Name/globLib:PrefixName) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:FormerPD_Name/globLib:PrefixName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:FormerPD_Name/globLib:PrefixName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

               <fo:block-container position="absolute" left="0.2in" top="7.7in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">First Name*:</fo:block>
               </fo:block-container>               	             
 				<fo:block-container position="absolute" left="1.5in" top="7.7in" height="12px" keep-together="always">
                  <fo:block text-align="start" font-size="9px" font-family="Georgia">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:FormerPD_Name/globLib:FirstName) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:FormerPD_Name/globLib:FirstName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:FormerPD_Name/globLib:FirstName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>

				<fo:block-container position="absolute" left="0.2in" top="7.9in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Middle Name:</fo:block>
               </fo:block-container>
               <fo:block-container position="absolute" left="1.5in" top="7.9in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Georgia">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:FormerPD_Name/globLib:MiddleName) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:FormerPD_Name/globLib:MiddleName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:FormerPD_Name/globLib:MiddleName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               
      			<fo:block-container  position="absolute" left="0.2in" top="8.1in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Last Name*:</fo:block>
               </fo:block-container>
               
               <fo:block-container  position="absolute" left="1.5in" top="8.1in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Georgia">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:FormerPD_Name/globLib:LastName) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:FormerPD_Name/globLib:LastName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:FormerPD_Name/globLib:LastName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               
             <fo:block-container  position="absolute" left="0.2in" top="8.3in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Suffix:</fo:block>
               </fo:block-container>  
               <fo:block-container  position="absolute" left="1.5in" top="8.3in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Georgia">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:FormerPD_Name/globLib:SuffixName) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:FormerPD_Name/globLib:SuffixName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:FormerPD_Name/globLib:SuffixName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container> 
 
				<fo:block-container  position="absolute" left="0.2in" top="8.6in" height="12px" keep-together="always">
                  <fo:block font-size="10px" font-family="ZapfDingbats">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <fo:inline font-family="ZapfDingbats" font-size="10px">&#x274F;</fo:inline>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               
               <fo:block-container  position="absolute" left="0.2in" top="8.55in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="ZapfDingbats">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:IsChangeOfInstitution) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:IsChangeOfInstitution = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="checkbox">
                              <xsl:with-param name="value" select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:IsChangeOfInstitution"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>            
               <fo:block-container position="absolute" left="0.75in" top="8.6in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Change of Grantee Institution</fo:block>
               </fo:block-container> 
                            
                <fo:block-container  position="absolute" left="0.2in" top="8.9in" height="12px" keep-together="always">
                  <fo:block font-size="9px" font-family="Helvetica">Name of former institution*:</fo:block>
               </fo:block-container>     
               <fo:block-container  position="absolute" left="0.2in" top="9.1in" height="24px" keep-together="always">
                  <fo:block font-size="9px" font-family="Georgia">
                     <xsl:choose>
                        <xsl:when test="not(//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:FormerInstitutionName) or //PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:FormerInstitutionName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//PHS398_CoverPageSupplement_2_0:PHS398_CoverPageSupplement_2_0/PHS398_CoverPageSupplement_2_0:FormerInstitutionName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
               
            </fo:flow>
         </fo:page-sequence>        
      </fo:root>
   </xsl:template>
   
   
   
   
   
   
   
   <xsl:template name="radioButton">
      <xsl:param name="value"/>
      <xsl:param name="schemaChoice">Y: Yes</xsl:param>
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
      <xsl:param name="schemaChoice">Y: Yes</xsl:param>
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
