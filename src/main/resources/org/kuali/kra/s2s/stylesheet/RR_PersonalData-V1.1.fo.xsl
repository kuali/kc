<?xml version="1.0" encoding="UTF-8"?> 
<xsl:stylesheet
 version="1.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:fo="http://www.w3.org/1999/XSL/Format"
 xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" 
 xmlns:att="http://apply.grants.gov/system/Attachments-V1.0"
 xmlns:glob="http://apply.grants.gov/system/Global-V1.0"
 xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
 xmlns:xs="http://www.w3.org/2001/XMLSchema"
 xmlns:RR_PersonalData="http://apply.grants.gov/forms/RR_PersonalData-V1.1"
 xmlns:header="http://devapply.row.com/system/Header-V1.0">

   <xsl:template match="RR_PersonalData:RR_PersonalData">
      <fo:root> 
         <fo:layout-master-set> 
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.6in" margin-right="0.6in"> 
               <fo:region-body margin-top="0.6in" margin-bottom="0.6in" /> 
               <fo:region-after extent=".5in"/> 
            </fo:simple-page-master> 
         </fo:layout-master-set>
         <fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
            <!-- ===================================== -->
            <!-- Default Footer                        -->
            <!-- ===================================== -->
            <fo:static-content flow-name="xsl-region-after">
               <fo:table width="100%"
                  space-before.optimum="0pt"
                  space-after.optimum="0pt"
                  table-layout="fixed">
                  <fo:table-column column-width="proportional-column-width(1)"/>
                  <fo:table-column column-width="proportional-column-width(1)"/>
                  <fo:table-body>
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en"
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
                              <fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
                              </fo:inline>
                           </fo:block>
                        </fo:table-cell>

                        <fo:table-cell hyphenate="true" language="en" line-height="9pt"
                         padding-start="0pt"
                         padding-end="0pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="right"
                         border-style="solid"
                         border-width="0pt"
                         border-color="white">
                           <fo:block><fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline></fo:block>
                           <fo:block><fo:inline font-size="6px" font-weight="bold">Expiration Date: 04/30/2008</fo:inline></fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                  </fo:table-body>
               </fo:table>
            </fo:static-content>

            <fo:flow flow-name="xsl-region-body">
               <!-- ===================================== -->
               <!-- Project Director Information          -->
               <!-- ===================================== -->
               <xsl:for-each select="RR_PersonalData:ProjectDirector">
                  <xsl:call-template name="printDirector"/>
               </xsl:for-each>
               <xsl:for-each select="RR_PersonalData:Co-ProjectDirector">
                  <fo:block break-after="page">
                     <xsl:text>&#xA;</xsl:text>
                  </fo:block>
                  <!-- ===================================== -->
                  <!-- Co-Project Director Information       -->
                  <!-- ===================================== -->
                  <xsl:call-template name="printDirector"/>
               </xsl:for-each>
            </fo:flow>
         </fo:page-sequence>
      </fo:root>
   </xsl:template>

   <xsl:template name="printDirector">
      <fo:table width="100%"
         space-before.optimum="0pt"
         space-after.optimum="0pt"
         table-layout="fixed">
         <fo:table-column column-width="proportional-column-width(1)"/>
         <fo:table-body>
            <!-- =================================== -->
            <!-- Title                               -->
            <!-- =================================== -->
            <fo:table-row>
               <fo:table-cell hyphenate="true" language="en"
                padding-start="0pt"
                padding-end="0pt"
                padding-before="1pt"
                padding-after="1pt"
                display-align="before"
                text-align="center"
                border-style="solid"
                border-width="0pt"
                border-color="white">
                  <fo:block font-weight="bold" font-size="10pt">RESEARCH &amp; RELATED PERSONAL DATA</fo:block>
                  <fo:block font-weight="bold" font-size="10pt">Project Director/Principal Investigator and Co-Project Director(s)/Co-Principal Investigator(s)</fo:block>
               </fo:table-cell>
            </fo:table-row>
            <!-- =================================== -->
            <!-- Instructions                        -->
            <!-- =================================== -->
            <fo:table-row>
               <fo:table-cell hyphenate="true" language="en"
                padding-start="0pt"
                padding-end="0pt"
                padding-before="6pt"
                padding-after="1pt"
                display-align="before"
                text-align="justify"
                border-style="solid"
                border-width="0pt"
                border-color="white">
                  <fo:block font-size="8pt">The Federal Government has a continuing commitment to monitor the operation of its review and award processes to identify and address any inequities based on gender, race, ethnicity, or disability of its proposed PDs/PIs and co-PDs/PIs.  To gather information needed for this important task, the applicant should submit the requested information for each identified PD/PI and co-PDs/PIs with each proposal.  Submission of the requested information is voluntary and is not a precondition of award.  However, information not submitted will seriously undermine the statistical validity, and therefore the usefulness, of information received from others.  Any individual not wishing to submit some or all the information should check the box provided for this purpose.  Upon receipt of the application, this form will be separated from the application.  This form will not be duplicated, and it will not be a part of the review process. Data will be confidential.</fo:block>
               </fo:table-cell>
            </fo:table-row>
            <!-- =================================== -->
            <!-- Director Title                      -->
            <!-- =================================== -->
            <fo:table-row>
               <fo:table-cell hyphenate="true" language="en"
                padding-start="0pt"
                padding-end="0pt"
                padding-before="1pt"
                padding-after="1pt"
                display-align="before"
                text-align="center"
                border-style="solid"
                border-width="0pt"
                border-color="white">
                  <fo:block font-size="8pt"><fo:leader leader-pattern="space"/></fo:block>
                  <fo:block font-size="8pt"><fo:leader leader-pattern="rule" leader-length="100%"/></fo:block>
                  <fo:block font-size="8pt"><fo:leader leader-pattern="space"/></fo:block>
                  <fo:block font-size="8pt" font-weight="bold">
                     <xsl:choose>
                        <xsl:when test="local-name(.) = 'ProjectDirector'">Project Director/Principal Investigator</xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="concat('Co-Project Director/co-Principal Investigator ', position())"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
                  <fo:block font-size="8pt"><fo:leader leader-pattern="rule" leader-length="100%"/></fo:block>
               </fo:table-cell>
            </fo:table-row>
            <!-- =================================== -->
            <!-- Full Name                           -->
            <!-- =================================== -->
            <fo:table-row>
               <fo:table-cell hyphenate="true" language="en"
                padding-start="0pt"
                padding-end="0pt"
                padding-before="1pt"
                padding-after="1pt"
                display-align="before"
                text-align="center"
                border-style="solid"
                border-width="0pt"
                border-color="white">
                  <fo:block font-size="10pt">
                     <xsl:call-template name="printNameFormatted">
                        <xsl:with-param name="prefix" select="RR_PersonalData:Name/globLib:PrefixName"/>
                        <xsl:with-param name="first" select="RR_PersonalData:Name/globLib:FirstName"/>
                        <xsl:with-param name="middle" select="RR_PersonalData:Name/globLib:MiddleName"/>
                        <xsl:with-param name="last" select="RR_PersonalData:Name/globLib:LastName"/>
                        <xsl:with-param name="suffix" select="RR_PersonalData:Name/globLib:SuffixName"/>
                     </xsl:call-template>
                  </fo:block>
               </fo:table-cell>
            </fo:table-row>
            <!-- =================================== -->
            <!-- DOB, SSN, Gender                    -->
            <!-- =================================== -->
            <fo:table-row>
               <fo:table-cell hyphenate="true" language="en"
                padding-start="0pt"
                padding-end="0pt"
                padding-before="1pt"
                padding-after="1pt"
                display-align="before"
                text-align="center"
                border-style="solid"
                border-width="0pt"
                border-color="white">
                  <fo:block font-size="10pt">
                     <fo:table width="100%"
                        space-before.optimum="0pt"
                        space-after.optimum="0pt"
                        table-layout="fixed">
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-body>
                           <fo:table-row>
                              <!-- ======================================== -->
                              <!-- Date of Birth                            -->
                              <!-- ======================================== -->
                              <fo:table-cell hyphenate="true" language="en"
                               padding-start="0pt"
                               padding-end="0pt"
                               padding-before="1pt"
                               padding-after="1pt"
                               display-align="before"
                               text-align="left"
                               border-style="solid"
                               border-width="0pt"
                               border-color="white">
                                 <fo:block font-size="8pt">
                                    <fo:inline font-weight="bold">Date of Birth: </fo:inline>
                                    <fo:inline>
                                       <xsl:call-template name="formatDate">
                                          <xsl:with-param name="value" select="RR_PersonalData:DateOfBirth"/>
                                       </xsl:call-template>
                                    </fo:inline>
                                 </fo:block>
                              </fo:table-cell>
                              <!-- ======================================== -->
                              <!-- Social Security Number                   -->
                              <!-- ======================================== -->
                              <fo:table-cell hyphenate="true" language="en"
                               padding-start="0pt"
                               padding-end="0pt"
                               padding-before="1pt"
                               padding-after="1pt"
                               display-align="before"
                               text-align="left"
                               border-style="solid"
                               border-width="0pt"
                               border-color="white">
                                 <fo:block font-size="8pt">
                                    <fo:inline font-weight="bold">Social Security Number: </fo:inline>
                                    <fo:inline><xsl:value-of select="RR_PersonalData:SocialSecurityNumber"/></fo:inline>
                                 </fo:block>
                              </fo:table-cell>
                              <!-- ======================================== -->
                              <!-- Gender                                   -->
                              <!-- ======================================== -->
                              <fo:table-cell hyphenate="true" language="en"
                               padding-start="0pt"
                               padding-end="0pt"
                               padding-before="1pt"
                               padding-after="1pt"
                               display-align="before"
                               text-align="left"
                               border-style="solid"
                               border-width="0pt"
                               border-color="white">
                                 <fo:block font-size="8pt">
                                    <fo:inline font-weight="bold">Gender: </fo:inline>
                                    <fo:inline><xsl:value-of select="RR_PersonalData:Gender"/></fo:inline>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                        </fo:table-body>
                     </fo:table>
                  </fo:block>
                  <fo:block font-size="8pt"><fo:leader leader-pattern="rule" leader-length="100%"/></fo:block>
               </fo:table-cell>
            </fo:table-row>
            <!-- ======================================== -->
            <!-- Race, Ethnicity, Disability, Citizenship -->
            <!-- ======================================== -->
            <fo:table-row>
               <fo:table-cell hyphenate="true" language="en"
                padding-start="0pt"
                padding-end="0pt"
                padding-before="1pt"
                padding-after="1pt"
                display-align="before"
                text-align="center"
                border-style="solid"
                border-width="0pt"
                border-color="white">
                  <fo:block>
                     <fo:table width="100%"
                        space-before.optimum="0pt"
                        space-after.optimum="0pt"
                        table-layout="fixed">
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-body>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en"
                               padding-start="0pt"
                               padding-end="0pt"
                               padding-before="1pt"
                               padding-after="1pt"
                               display-align="before"
                               text-align="left"
                               border-style="solid"
                               border-width="0pt"
                               border-color="white">
                                 <fo:block font-weight="bold" font-size="8pt">Race (check all that apply):</fo:block>
                              </fo:table-cell>
                              <fo:table-cell hyphenate="true" language="en"
                               padding-start="0pt"
                               padding-end="0pt"
                               padding-before="1pt"
                               padding-after="1pt"
                               display-align="before"
                               text-align="left"
                               border-style="solid"
                               border-width="0pt"
                               border-color="white">
                                 <fo:block font-weight="bold" font-size="8pt">Ethnicity:</fo:block>
                              </fo:table-cell>
                              <fo:table-cell hyphenate="true" language="en"
                               padding-start="0pt"
                               padding-end="0pt"
                               padding-before="1pt"
                               padding-after="1pt"
                               display-align="before"
                               text-align="left"
                               border-style="solid"
                               border-width="0pt"
                               border-color="white">
                                 <fo:block font-weight="bold" font-size="8pt">Disability Status (check all that apply):</fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <!-- ======================================== -->
                              <!-- Race                                     -->
                              <!-- ======================================== -->
                              <fo:table-cell hyphenate="true" language="en"
                               padding-start="0pt"
                               padding-end="0pt"
                               padding-before="1pt"
                               padding-after="1pt"
                               display-align="before"
                               text-align="left"
                               border-style="solid"
                               border-width="0pt"
                               border-color="white">
                                 <fo:block/>
                                 <xsl:choose>
                                    <xsl:when test="count(RR_PersonalData:Race) = 6">
                                       <xsl:call-template name="raceCheckbox">
                                          <xsl:with-param name="value" select="concat(RR_PersonalData:Race[1],'-',RR_PersonalData:Race[2],'-',RR_PersonalData:Race[3],'-',RR_PersonalData:Race[4],'-',RR_PersonalData:Race[5],'-',RR_PersonalData:Race[6])"/>
                                       </xsl:call-template>
                                    </xsl:when>
                                    <xsl:when test="count(RR_PersonalData:Race) = 5">
                                       <xsl:call-template name="raceCheckbox">
                                          <xsl:with-param name="value" select="concat(RR_PersonalData:Race[1],'-',RR_PersonalData:Race[2],'-',RR_PersonalData:Race[3],'-',RR_PersonalData:Race[4],'-',RR_PersonalData:Race[5])"/>
                                       </xsl:call-template>
                                    </xsl:when>
                                    <xsl:when test="count(RR_PersonalData:Race) = 4">
                                       <xsl:call-template name="raceCheckbox">
                                          <xsl:with-param name="value" select="concat(RR_PersonalData:Race[1],'-',RR_PersonalData:Race[2],'-',RR_PersonalData:Race[3],'-',RR_PersonalData:Race[4])"/>
                                       </xsl:call-template>
                                    </xsl:when>
                                    <xsl:when test="count(RR_PersonalData:Race) = 3">
                                       <xsl:call-template name="raceCheckbox">
                                          <xsl:with-param name="value" select="concat(RR_PersonalData:Race[1],'-',RR_PersonalData:Race[2],'-',RR_PersonalData:Race[3])"/>
                                       </xsl:call-template>
                                    </xsl:when>
                                    <xsl:when test="count(RR_PersonalData:Race) = 2">
                                       <xsl:call-template name="raceCheckbox">
                                          <xsl:with-param name="value" select="concat(RR_PersonalData:Race[1],'-',RR_PersonalData:Race[2])"/>
                                       </xsl:call-template>
                                    </xsl:when>
                                    <xsl:when test="count(RR_PersonalData:Race) = 1">
                                       <xsl:call-template name="raceCheckbox">
                                          <xsl:with-param name="value" select="RR_PersonalData:Race[1]"/>
                                       </xsl:call-template>
                                    </xsl:when>
                                    <xsl:otherwise>
                                       <xsl:call-template name="raceCheckbox">
                                          <xsl:with-param name="value"></xsl:with-param>
                                       </xsl:call-template>
                                    </xsl:otherwise>
                                 </xsl:choose>
                              </fo:table-cell>
                              <!-- ======================================== -->
                              <!-- Ethnicity                                -->
                              <!-- ======================================== -->
                              <fo:table-cell hyphenate="true" language="en"
                               padding-start="0pt"
                               padding-end="0pt"
                               padding-before="1pt"
                               padding-after="1pt"
                               display-align="before"
                               text-align="left"
                               border-style="solid"
                               border-width="0pt"
                               border-color="white">
                                 <fo:block font-size="8pt">
                                    <xsl:value-of select="RR_PersonalData:Ethnicity"/>
                                 </fo:block>
                              </fo:table-cell>
                              <!-- ======================================== -->
                              <!-- Disability Status                        -->
                              <!-- ======================================== -->
                              <fo:table-cell hyphenate="true" language="en"
                               padding-start="0pt"
                               padding-end="0pt"
                               padding-before="1pt"
                               padding-after="1pt"
                               display-align="before"
                               text-align="left"
                               border-style="solid"
                               border-width="0pt"
                               border-color="white">
                                 <fo:block/>
                                 <xsl:choose>
                                    <xsl:when test="count(RR_PersonalData:DisabilityStatus) = 6">
                                       <xsl:call-template name="disabilityCheckbox">
                                          <xsl:with-param name="value" select="concat(RR_PersonalData:DisabilityStatus[1],'-',RR_PersonalData:DisabilityStatus[2],'-',RR_PersonalData:DisabilityStatus[3],'-',RR_PersonalData:DisabilityStatus[4],'-',RR_PersonalData:DisabilityStatus[5],'-',RR_PersonalData:DisabilityStatus[6])"/>
                                       </xsl:call-template>
                                    </xsl:when>
                                    <xsl:when test="count(RR_PersonalData:DisabilityStatus) = 5">
                                       <xsl:call-template name="disabilityCheckbox">
                                          <xsl:with-param name="value" select="concat(RR_PersonalData:DisabilityStatus[1],'-',RR_PersonalData:DisabilityStatus[2],'-',RR_PersonalData:DisabilityStatus[3],'-',RR_PersonalData:DisabilityStatus[4],'-',RR_PersonalData:DisabilityStatus[5])"/>
                                       </xsl:call-template>
                                    </xsl:when>
                                    <xsl:when test="count(RR_PersonalData:DisabilityStatus) = 4">
                                       <xsl:call-template name="disabilityCheckbox">
                                          <xsl:with-param name="value" select="concat(RR_PersonalData:DisabilityStatus[1],'-',RR_PersonalData:DisabilityStatus[2],'-',RR_PersonalData:DisabilityStatus[3],'-',RR_PersonalData:DisabilityStatus[4])"/>
                                       </xsl:call-template>
                                    </xsl:when>
                                    <xsl:when test="count(RR_PersonalData:DisabilityStatus) = 3">
                                       <xsl:call-template name="disabilityCheckbox">
                                          <xsl:with-param name="value" select="concat(RR_PersonalData:DisabilityStatus[1],'-',RR_PersonalData:DisabilityStatus[2],'-',RR_PersonalData:DisabilityStatus[3])"/>
                                       </xsl:call-template>
                                    </xsl:when>
                                    <xsl:when test="count(RR_PersonalData:DisabilityStatus) = 2">
                                       <xsl:call-template name="disabilityCheckbox">
                                          <xsl:with-param name="value" select="concat(RR_PersonalData:DisabilityStatus[1],'-',RR_PersonalData:DisabilityStatus[2])"/>
                                       </xsl:call-template>
                                    </xsl:when>
                                    <xsl:when test="count(RR_PersonalData:DisabilityStatus) = 1">
                                       <xsl:call-template name="disabilityCheckbox">
                                          <xsl:with-param name="value" select="RR_PersonalData:DisabilityStatus[1]"/>
                                       </xsl:call-template>
                                    </xsl:when>
                                    <xsl:otherwise>
                                       <xsl:call-template name="disabilityCheckbox">
                                          <xsl:with-param name="value"></xsl:with-param>
                                       </xsl:call-template>
                                    </xsl:otherwise>
                                 </xsl:choose>
                              </fo:table-cell>
                           </fo:table-row>
                           <!-- ======================================== -->
                           <!-- Citizenship                              -->
                           <!-- ======================================== -->
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en"
                               number-columns-spanned="3"
                               padding-start="0pt"
                               padding-end="0pt"
                               padding-before="1pt"
                               padding-after="1pt"
                               display-align="before"
                               text-align="left"
                               border-style="solid"
                               border-width="0pt"
                               border-color="white">
                                 <fo:block font-weight="bold" font-size="8pt">Citizenship:</fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en"
                               number-columns-spanned="3"
                               padding-start="0pt"
                               padding-end="0pt"
                               padding-before="1pt"
                               padding-after="1pt"
                               display-align="before"
                               text-align="left"
                               border-style="solid"
                               border-width="0pt"
                               border-color="white">
                                 <fo:block font-size="8pt">
                                    <xsl:value-of select="RR_PersonalData:Citizenship"/>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                        </fo:table-body>
                     </fo:table>
                  </fo:block>
                  <fo:block font-size="8pt"><fo:leader leader-pattern="rule" leader-length="100%"/></fo:block>
               </fo:table-cell>
            </fo:table-row>
         </fo:table-body>
      </fo:table>
   </xsl:template>

   <!-- ============================================= -->
   <!-- PRINT NAME FORMATTED                          -->
   <!-- Prints name in the same format as the XFD     -->
   <!-- ============================================= -->
   <xsl:template name="printNameFormatted">
      <xsl:param name="prefix"/>
      <xsl:param name="first"/>
      <xsl:param name="middle"/>
      <xsl:param name="last"/>
      <xsl:param name="suffix"/>
      <fo:table width="100%"
         space-before.optimum="0pt"
         space-after.optimum="0pt"
         table-layout="fixed">
         <fo:table-column column-width="proportional-column-width(1)"/>
         <fo:table-column column-width="proportional-column-width(3)"/>
         <fo:table-column column-width="proportional-column-width(2)"/>
         <fo:table-column column-width="proportional-column-width(3)"/>
         <fo:table-column column-width="proportional-column-width(1)"/>
         <fo:table-body>
            <fo:table-row>
               <fo:table-cell hyphenate="true" language="en"
                padding-start="1pt"
                padding-end="1pt"
                padding-before="1pt"
                padding-after="1pt"
                display-align="before"
                text-align="left"
                border-style="solid"
                border-width="0pt"
                border-color="white">
                  <fo:block font-size="8pt" font-weight="bold">Prefix:</fo:block>
               </fo:table-cell>
               <fo:table-cell hyphenate="true" language="en"
                padding-start="1pt"
                padding-end="1pt"
                padding-before="1pt"
                padding-after="1pt"
                display-align="before"
                text-align="left"
                border-style="solid"
                border-width="0pt"
                border-color="white">
                  <fo:block font-size="8pt" font-weight="bold">* First Name:</fo:block>
               </fo:table-cell>
               <fo:table-cell hyphenate="true" language="en"
                padding-start="1pt"
                padding-end="1pt"
                padding-before="1pt"
                padding-after="1pt"
                display-align="before"
                text-align="left"
                border-style="solid"
                border-width="0pt"
                border-color="white">
                  <fo:block font-size="8pt" font-weight="bold">Middle Name:</fo:block>
               </fo:table-cell>
               <fo:table-cell hyphenate="true" language="en"
                padding-start="1pt"
                padding-end="1pt"
                padding-before="1pt"
                padding-after="1pt"
                display-align="before"
                text-align="left"
                border-style="solid"
                border-width="0pt"
                border-color="white">
                  <fo:block font-size="8pt" font-weight="bold">* Last Name:</fo:block>
               </fo:table-cell>
               <fo:table-cell hyphenate="true" language="en"
                padding-start="1pt"
                padding-end="1pt"
                padding-before="1pt"
                padding-after="1pt"
                display-align="before"
                text-align="left"
                border-style="solid"
                border-width="0pt"
                border-color="white">
                  <fo:block font-size="8pt" font-weight="bold">Suffix:</fo:block>
               </fo:table-cell>
            </fo:table-row>
            <fo:table-row>
               <fo:table-cell hyphenate="true" language="en"
                padding-start="1pt"
                padding-end="1pt"
                padding-before="1pt"
                padding-after="1pt"
                display-align="before"
                text-align="left"
                border-style="solid"
                border-width="0pt"
                border-color="white">
                  <fo:block font-size="8pt"><xsl:value-of select="$prefix"/></fo:block>
               </fo:table-cell>
               <fo:table-cell hyphenate="true" language="en"
                padding-start="1pt"
                padding-end="1pt"
                padding-before="1pt"
                padding-after="1pt"
                display-align="before"
                text-align="left"
                border-style="solid"
                border-width="0pt"
                border-color="white">
                  <fo:block font-size="8pt"><xsl:value-of select="$first"/></fo:block>
               </fo:table-cell>
               <fo:table-cell hyphenate="true" language="en"
                padding-start="1pt"
                padding-end="1pt"
                padding-before="1pt"
                padding-after="1pt"
                display-align="before"
                text-align="left"
                border-style="solid"
                border-width="0pt"
                border-color="white">
                  <fo:block font-size="8pt"><xsl:value-of select="$middle"/></fo:block>
               </fo:table-cell>
               <fo:table-cell hyphenate="true" language="en"
                padding-start="1pt"
                padding-end="1pt"
                padding-before="1pt"
                padding-after="1pt"
                display-align="before"
                text-align="left"
                border-style="solid"
                border-width="0pt"
                border-color="white">
                  <fo:block font-size="8pt"><xsl:value-of select="$last"/></fo:block>
               </fo:table-cell>
               <fo:table-cell hyphenate="true" language="en"
                padding-start="1pt"
                padding-end="1pt"
                padding-before="1pt"
                padding-after="1pt"
                display-align="before"
                text-align="left"
                border-style="solid"
                border-width="0pt"
                border-color="white">
                  <fo:block font-size="8pt"><xsl:value-of select="$suffix"/></fo:block>
               </fo:table-cell>
            </fo:table-row>
         </fo:table-body>
      </fo:table>
   </xsl:template>

   <!-- ============================================= -->
   <!-- FORMAT DATE                                   -->
   <!-- Writes XSD:date style text into to mm-dd-yyyy -->
   <!-- ============================================= -->
   <xsl:template name="formatDate">
      <xsl:param name="value"/>
      <xsl:if test="$value != ''">
         <xsl:value-of select="format-number(substring($value,6,2), '00')"/>
         <xsl:text>-</xsl:text>
         <xsl:value-of select="format-number(substring($value,9,2), '00')"/>
         <xsl:text>-</xsl:text>
         <xsl:value-of select="format-number(substring($value,1,4), '0000')"/>
      </xsl:if>
   </xsl:template>

   <!-- ============================================= -->
   <!-- CHECKBOX                                      -->
   <!-- Print out a checkbox according to value.      -->
   <!-- ============================================= -->
   <xsl:template name="checkbox">
      <xsl:param name="value"/>
      <xsl:param name="check">Yes</xsl:param>
      <xsl:choose>
         <xsl:when test="$value = $check">
            <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
         </xsl:when>
         <xsl:otherwise>
            <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
         </xsl:otherwise>
      </xsl:choose>
   </xsl:template>

   <!-- ============================================= -->
   <!-- RACE CHECKBOX                                 -->
   <!-- Print out all selected races.                 -->
   <!-- ============================================= -->
   <xsl:template name="raceCheckbox">
      <xsl:param name="value"/>

      <fo:block font-size="8pt">
         <xsl:choose>
            <xsl:when test="contains($value, 'American Indian or Alaska Native')">
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
            </xsl:when>
            <xsl:otherwise>
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
            </xsl:otherwise>
         </xsl:choose>
         <fo:inline> American Indian or Alaska Native</fo:inline>
      </fo:block>

      <fo:block font-size="8pt">
         <xsl:choose>
            <xsl:when test="contains($value, 'Asian')">
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
            </xsl:when>
            <xsl:otherwise>
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
            </xsl:otherwise>
         </xsl:choose>
         <fo:inline> Asian</fo:inline>
      </fo:block>

      <fo:block font-size="8pt">
         <xsl:choose>
            <xsl:when test="contains($value, 'Black or African American')">
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
            </xsl:when>
            <xsl:otherwise>
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
            </xsl:otherwise>
         </xsl:choose>
         <fo:inline> Black or African American</fo:inline>
      </fo:block>

      <fo:block font-size="8pt">
         <xsl:choose>
            <xsl:when test="contains($value, 'Native Hawaiian or Other Pacific Islander')">
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
            </xsl:when>
            <xsl:otherwise>
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
            </xsl:otherwise>
         </xsl:choose>
         <fo:inline> Native Hawaiian or Other Pacific Islander</fo:inline>
      </fo:block>

      <fo:block font-size="8pt">
         <xsl:choose>
            <xsl:when test="contains($value, 'White')">
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
            </xsl:when>
            <xsl:otherwise>
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
            </xsl:otherwise>
         </xsl:choose>
         <fo:inline> White</fo:inline>
      </fo:block>

      <fo:block font-size="8pt">
         <xsl:choose>
            <xsl:when test="contains($value, 'Do Not Wish to Provide')">
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
            </xsl:when>
            <xsl:otherwise>
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
            </xsl:otherwise>
         </xsl:choose>
         <fo:inline> Do Not Wish to Provide</fo:inline>
      </fo:block>
   </xsl:template>

   <!-- ============================================= -->
   <!-- DISABILITY CHECKBOX                           -->
   <!-- Print out all selected races.                 -->
   <!-- ============================================= -->
   <xsl:template name="disabilityCheckbox">
      <xsl:param name="value"/>

      <fo:block font-size="8pt">
         <xsl:choose>
            <xsl:when test="contains($value, 'Hearing')">
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
            </xsl:when>
            <xsl:otherwise>
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
            </xsl:otherwise>
         </xsl:choose>
         <fo:inline> Hearing</fo:inline>
      </fo:block>

      <fo:block font-size="8pt">
         <xsl:choose>
            <xsl:when test="contains($value, 'Visual')">
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
            </xsl:when>
            <xsl:otherwise>
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
            </xsl:otherwise>
         </xsl:choose>
         <fo:inline> Visual</fo:inline>
      </fo:block>

      <fo:block font-size="8pt">
         <xsl:choose>
            <xsl:when test="contains($value, 'Mobility/Orthopedic Impairment')">
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
            </xsl:when>
            <xsl:otherwise>
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
            </xsl:otherwise>
         </xsl:choose>
         <fo:inline> Mobility/Orthopedic Impairment</fo:inline>
      </fo:block>

      <fo:block font-size="8pt">
         <xsl:choose>
            <xsl:when test="contains($value, 'Other')">
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
            </xsl:when>
            <xsl:otherwise>
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
            </xsl:otherwise>
         </xsl:choose>
         <fo:inline> Other</fo:inline>
      </fo:block>

      <fo:block font-size="8pt">
         <xsl:choose>
            <xsl:when test="contains($value, 'None')">
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
            </xsl:when>
            <xsl:otherwise>
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
            </xsl:otherwise>
         </xsl:choose>
         <fo:inline> None</fo:inline>
      </fo:block>

      <fo:block font-size="8pt">
         <xsl:choose>
            <xsl:when test="contains($value, 'Do Not Wish to Provide')">
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
            </xsl:when>
            <xsl:otherwise>
               <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
            </xsl:otherwise>
         </xsl:choose>
         <fo:inline> Do Not Wish to Provide</fo:inline>
      </fo:block>
   </xsl:template>
</xsl:stylesheet>
