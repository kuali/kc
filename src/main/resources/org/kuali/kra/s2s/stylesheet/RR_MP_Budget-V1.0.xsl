<?xml version="1.0" encoding="UTF-8"?>
<!-- $Revision: 1583 $ -->
<xsl:stylesheet version="1.0" xmlns:RR_MP_SubawardBudget="http://apply.grants.gov/forms/RR_MP_SubawardBudget-V1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:grant="http://apply.grants.gov/system/MetaGrantApplication" xmlns:RR_MP_Budget="http://apply.grants.gov/forms/RR_MP_Budget-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:header="http://apply.grants.gov/system/Header-V1.0">
   <xsl:template match="RR_MP_Budget:RR_MP_Budget[name(..)!='RR_MP_SubawardBudget:BudgetAttachments']">
      <fo:root>
         <fo:layout-master-set>
            <fo:simple-page-master master-name="AB" page-height="8.5in" page-width="11in" margin-left="0.3in" margin-right="0.3in">
               <fo:region-body margin-top="0.3in" margin-bottom=".4in"/>
               <fo:region-before region-name="region-before-first" extent=".4in" />
               <fo:region-after extent=".3in"/>
            </fo:simple-page-master>
            <fo:simple-page-master master-name="main" page-height="11in" page-width="8.5in" margin-left="0.4in" margin-right="0.4in">
               <fo:region-body margin-top="0.5in" margin-bottom="0.5in"/>
               <fo:region-after extent=".3in"/>
            </fo:simple-page-master>
            <fo:page-sequence-master master-name="primary">
               <fo:single-page-master-reference master-reference="main"/>
            </fo:page-sequence-master>
            <fo:page-sequence-master master-name="summary">
               <fo:single-page-master-reference master-reference="main"/>
            </fo:page-sequence-master>
         </fo:layout-master-set>
         <!--==========================================================================-->
         <xsl:for-each select="RR_MP_Budget:BudgetYear">
         <fo:page-sequence master-reference="AB" format="1">
            <!-- ===================================== -->
            <!-- Header for Page 1                     -->
            <!-- ===================================== -->
            <fo:static-content flow-name="region-before-first">
               <fo:table width="100%"
                  space-before.optimum="0pt"
                  space-after.optimum="0pt"
                  table-layout="fixed">
                  <fo:table-column column-width="proportional-column-width(1)"/>
                  <fo:table-body>
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en"
                         line-height="10pt"
                         padding-start="0pt"
                         padding-end="0pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="right"
                         border-style="solid"
                         border-width="0pt"
                         border-color="white">
                           <fo:block><fo:inline font-size="6px">OMB Number: 4040-0001</fo:inline></fo:block>
                           <fo:block><fo:inline font-size="6px">Expiration Date: 06/30/2016</fo:inline></fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                  </fo:table-body>
               </fo:table>
            </fo:static-content>
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
                           <xsl:if test="/*/*/footer:Grants_govTrackingNumber != ''">
                              <fo:inline font-size="8px">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
                              </fo:inline>
                             </xsl:if>
                           </fo:block>
                        </fo:table-cell>
                        <fo:table-cell hyphenate="true" language="en" line-height="10pt"
                         padding-start="0pt"
                         padding-end="0pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="right"
                         border-style="solid"
                         border-width="0pt"
                         border-color="white">
                           <fo:block>
                           <xsl:if test="/*/*/header:OpportunityID != ''">
                           		<fo:inline font-size="8px">Funding Opportunity Number: <xsl:value-of select="/*/*/header:OpportunityID"/></fo:inline>
                           	</xsl:if>
							<xsl:if test="/*/*/footer:ReceivedDateTime != ''">
                           		<fo:inline font-size="8px">.       Received Date: <xsl:value-of select="/*/*/footer:ReceivedDateTime"/></fo:inline>
                           	</xsl:if>
                           </fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                  </fo:table-body>
               </fo:table>
            </fo:static-content>
            <fo:flow flow-name="xsl-region-body">
               <xsl:call-template name="SingleYearPart1">
                  <xsl:with-param name="year"><xsl:value-of select="position()"></xsl:value-of></xsl:with-param>
               </xsl:call-template>
            </fo:flow>
         </fo:page-sequence>
         <fo:page-sequence master-reference="primary" format="1">
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
                           	  <xsl:if test="/*/*/footer:Grants_govTrackingNumber != ''">
                           	  	<fo:inline font-size="8px">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
                              	</fo:inline>
                           	  </xsl:if>
                           </fo:block>
                        </fo:table-cell>
                        <fo:table-cell hyphenate="true" language="en" line-height="10pt"
                         padding-start="0pt"
                         padding-end="0pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="right"
                         border-style="solid"
                         border-width="0pt"
                         border-color="white">
                           <fo:block>
							<xsl:if test="/*/*/header:OpportunityID != ''">
                           		<fo:inline font-size="8px">Funding Opportunity Number: <xsl:value-of select="/*/*/header:OpportunityID"/></fo:inline>
                           	</xsl:if>
							<xsl:if test="/*/*/footer:ReceivedDateTime != ''">
                           		<fo:inline font-size="8px">.       Received Date: <xsl:value-of select="/*/*/footer:ReceivedDateTime"/></fo:inline>
                           	</xsl:if>
                           </fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                  </fo:table-body>
               </fo:table>
            </fo:static-content>
            <fo:flow flow-name="xsl-region-body">
               <xsl:call-template name="SingleYearPart2">
                  <xsl:with-param name="year"><xsl:value-of select="position()"></xsl:value-of></xsl:with-param>
               </xsl:call-template>
            </fo:flow>
         </fo:page-sequence>
         </xsl:for-each>
         <!-- ====================================== new section SUMMARY ===============================-->
         <fo:page-sequence master-reference="summary" format="1">
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
                       		<xsl:if test="/*/*/footer:Grants_govTrackingNumber != ''">
                           	  	<fo:inline font-size="8px">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
                              	</fo:inline>
                           	  </xsl:if>
                           </fo:block>
                        </fo:table-cell>
                        <fo:table-cell hyphenate="true" language="en" line-height="10pt"
                         padding-start="0pt"
                         padding-end="0pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="right"
                         border-style="solid"
                         border-width="0pt"
                         border-color="white">
                           <fo:block>
                           <xsl:if test="/*/*/header:OpportunityID != ''">
                           		<fo:inline font-size="8px">Funding Opportunity Number: <xsl:value-of select="/*/*/header:OpportunityID"/></fo:inline>
                           	</xsl:if>
							<xsl:if test="/*/*/footer:ReceivedDateTime != ''">
                           		<fo:inline font-size="8px">.       Received Date: <xsl:value-of select="/*/*/footer:ReceivedDateTime"/></fo:inline>
                           	</xsl:if>
                          </fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                  </fo:table-body>
               </fo:table>
            </fo:static-content>
            <fo:flow flow-name="xsl-region-body">
               <!--<xsl:for-each select="XXX">-->
               <fo:table width="100%" space-before.optimum="3pt" space-after.optimum="2pt">
                  <fo:table-column/>
                  <fo:table-body>
                     <fo:table-row>
                        <fo:table-cell text-align="center" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="before">
                           <fo:block>
                              <fo:inline font-weight="bold" font-size="12px">RESEARCH &amp; RELATED BUDGET - Cumulative Budget</fo:inline>
                           </fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                  </fo:table-body>
               </fo:table>
               <fo:block>
                  <fo:leader leader-pattern="space"/>
               </fo:block>
               <fo:block>
                  <fo:leader leader-pattern="space"/>
               </fo:block>
               <fo:block font-size="9px">
                  <fo:table width="90%" space-before.optimum="3pt" space-after.optimum="2pt">
                     <fo:table-column column-width="proportional-column-width(50)"/>
                     <fo:table-column column-width="proportional-column-width(25)"/>
                     <fo:table-column column-width="proportional-column-width(25)"/>
                     <fo:table-body>
                         <fo:table-row>
                             <fo:table-cell>
                                 <fo:block></fo:block>
                             </fo:table-cell>
                         </fo:table-row>
                        <!--============= ROWS Begin ======================-->
                        <xsl:for-each select="RR_MP_Budget:BudgetSummary">
                           <fo:table-row>
                              <fo:table-cell>
                                 <fo:block/>
                              </fo:table-cell>
                              <fo:table-cell number-columns-spanned="2" text-align="center">
                                 <fo:block>
                                    <fo:inline font-family="Helvetica, Arial, sans-serif" font-size="9px" font-weight="bold">Totals ($)</fo:inline>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell number-columns-spanned="3">
                                 <fo:block>&#160;</fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                 <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px" font-weight="bold">Section A, Senior/Key Person</fo:block>
                              </fo:table-cell>
                              <fo:table-cell>
                                 <fo:block/>
                              </fo:table-cell>
                              <fo:table-cell text-align="right">
                                 <fo:block>
                                  <!-- <xsl:for-each select="RR_MP_Budget:CumulativeTotalFundsRequestedSeniorKeyPerson[. != '0.00']">-->
                                       <xsl:for-each select="RR_MP_Budget:CumulativeTotalFundsRequestedSeniorKeyPerson">
                                              <fo:inline font-family="Helvetica, Arial, sans-serif" font-size="9px" font-weight="bold">
                                          <xsl:value-of select="format-number(., '#,##0.00')"/>
                                       </fo:inline>
                                   </xsl:for-each>
                                     </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                 <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px" font-weight="bold">Section B, Other Personnel</fo:block>
                              </fo:table-cell>
                              <fo:table-cell>
                                 <fo:block/>
                              </fo:table-cell>
                              <fo:table-cell text-align="right">
                                 <fo:block>
                                    <xsl:for-each select="RR_MP_Budget:CumulativeTotalFundsRequestedOtherPersonnel">
                                       <fo:inline font-family="Helvetica, Arial, sans-serif" font-size="9px" font-weight="bold">
                                          <xsl:value-of select="format-number(., '#,##0.00')"/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                 <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">Total Number Other Personnel</fo:block>
                              </fo:table-cell>
                              <fo:table-cell text-align="right">
                                 <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                    <xsl:value-of select="RR_MP_Budget:CumulativeTotalNoOtherPersonnel"/>
                                 </fo:block>
                              </fo:table-cell>
                              <fo:table-cell>
                                 <fo:block/>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                 <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px" font-weight="bold">Total Salary, Wages and Fringe Benefits (A+B)</fo:block>
                              </fo:table-cell>
                              <fo:table-cell text-align="right">
                                 <fo:block/>
                              </fo:table-cell>
                              <fo:table-cell text-align="right">
                                 <fo:block>
                                    <!--<xsl:for-each select="RR_MP_Budget:CumulativeTotalFundsRequestedPersonnel[. != '0.00']">-->
                                    <xsl:for-each select="RR_MP_Budget:CumulativeTotalFundsRequestedPersonnel">
                                       <fo:inline font-family="Helvetica, Arial, sans-serif" font-size="9px" font-weight="bold">
                                          <xsl:value-of select="format-number(., '#,##0.00')"/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                 <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px" font-weight="bold">Section C, Equipment</fo:block>
                              </fo:table-cell>
                              <fo:table-cell>
                                 <fo:block/>
                              </fo:table-cell>
                              <fo:table-cell text-align="right">
                                 <fo:block>
                                    <xsl:for-each select="RR_MP_Budget:CumulativeTotalFundsRequestedEquipment">
                                       <fo:inline font-family="Helvetica, Arial, sans-serif" font-size="9px" font-weight="bold">
                                          <xsl:value-of select="format-number(., '#,##0.00')"/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                 <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px" font-weight="bold">Section D, Travel</fo:block>
                              </fo:table-cell>
                              <fo:table-cell text-align="right">
                                 <fo:block/>
                              </fo:table-cell>
                              <fo:table-cell text-align="right">
                                 <fo:block>
                                    <xsl:for-each select="RR_MP_Budget:CumulativeTotalFundsRequestedTravel">
                                       <fo:inline font-family="Helvetica, Arial, sans-serif" font-size="9px" font-weight="bold">
                                          <xsl:value-of select="format-number(., '#,##0.00')"/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                 <fo:block>1. Domestic</fo:block>
                              </fo:table-cell>
                              <fo:table-cell text-align="right">
                                 <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                    <xsl:for-each select="RR_MP_Budget:CumulativeDomesticTravelCosts">
                                       <fo:inline>
                                          <xsl:value-of select="format-number(., '#,##0.00')"/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                              <fo:table-cell>
                                 <fo:block/>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                 <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">2. Foreign</fo:block>
                              </fo:table-cell>
                              <fo:table-cell text-align="right">
                                 <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                    <xsl:for-each select="RR_MP_Budget:CumulativeForeignTravelCosts">
                                       <fo:inline>
                                          <xsl:value-of select="format-number(., '#,##0.00')"/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                              <fo:table-cell>
                                 <fo:block/>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                                 <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px" font-weight="bold">Section E, Participant/Trainee Support Costs</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block/>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right">
                                    <fo:block>
                                       <xsl:for-each select="RR_MP_Budget:CumulativeTotalFundsRequestedTraineeCosts">
                                          <fo:inline font-family="Helvetica, Arial, sans-serif" font-size="9px" font-weight="bold">
                                             <xsl:value-of select="format-number(., '#,##0.00')"/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                              </fo:table-row>
                              <fo:table-row>
                                 <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">1. Tuition/Fees/Health Insurance</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right">
                                    <fo:block>
                                       <xsl:for-each select="RR_MP_Budget:CumulativeTraineeTuitionFeesHealthInsurance">
                                          <fo:inline>
                                             <xsl:value-of select="format-number(., '#,##0.00')"/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block/>
                                 </fo:table-cell>
                              </fo:table-row>
                              <fo:table-row>
                                 <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">2. Stipends</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                       <xsl:for-each select="RR_MP_Budget:CumulativeTraineeStipends">
                                          <fo:inline>
                                             <xsl:value-of select="format-number(., '#,##0.00')"/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block/>
                                 </fo:table-cell>
                              </fo:table-row>
                              <fo:table-row>
                                 <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">3. Travel</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                       <xsl:for-each select="RR_MP_Budget:CumulativeTraineeTravel">
                                          <fo:inline>
                                             <xsl:value-of select="format-number(., '#,##0.00')"/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block/>
                                 </fo:table-cell>
                              </fo:table-row>
                              <fo:table-row>
                                 <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">4. Subsistence</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                       <xsl:for-each select="RR_MP_Budget:CumulativeTraineeSubsistence">
                                          <fo:inline>
                                             <xsl:value-of select="format-number(., '#,##0.00')"/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block/>
                                 </fo:table-cell>
                              </fo:table-row>
                              <fo:table-row>
                                 <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">5. Other</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                       <xsl:for-each select="RR_MP_Budget:CumulativeOtherTraineeCost">
                                          <fo:inline>
                                             <xsl:value-of select="format-number(., '#,##0.00')"/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block/>
                                 </fo:table-cell>
                              </fo:table-row>
                              <fo:table-row>
                                 <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">6. Number of Participants/Trainees</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                       <xsl:value-of select="RR_MP_Budget:CumulativeNoofTrainees"/>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block/>
                                 </fo:table-cell>
                              </fo:table-row>

                              <fo:table-row>
                                 <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px" font-weight="bold">Section F, Other Direct Costs</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block/>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                       <xsl:for-each select="RR_MP_Budget:CumulativeTotalFundsRequestedOtherDirectCosts">
                                          <fo:inline font-weight="bold">
                                             <xsl:value-of select="format-number(., '#,##0.00')"/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                              </fo:table-row>
                              <fo:table-row>
                                 <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">1. Materials and Supplies</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                       <xsl:for-each select="RR_MP_Budget:CumulativeMaterialAndSupplies">
                                          <fo:inline>
                                             <xsl:value-of select="format-number(., '#,##0.00')"/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block/>
                                 </fo:table-cell>
                              </fo:table-row>
                              <fo:table-row>
                                 <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">2. Publication Costs</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                       <xsl:for-each select="RR_MP_Budget:CumulativePublicationCosts">
                                          <fo:inline>
                                             <xsl:value-of select="format-number(., '#,##0.00')"/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block/>
                                 </fo:table-cell>
                              </fo:table-row>
                              <fo:table-row>
                                 <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">3. Consultant Services</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                       <xsl:for-each select="RR_MP_Budget:CumulativeConsultantServices">
                                          <fo:inline>
                                             <xsl:value-of select="format-number(., '#,##0.00')"/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block/>
                                 </fo:table-cell>
                              </fo:table-row>
                              <fo:table-row>
                                 <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">4. ADP/Computer Services</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                       <xsl:for-each select="RR_MP_Budget:CumulativeADPComputerServices">
                                          <fo:inline>
                                             <xsl:value-of select="format-number(., '#,##0.00')"/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block/>
                                 </fo:table-cell>
                              </fo:table-row>
                              <fo:table-row>
                                 <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">5. Subawards/Consortium/Contractual Costs</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                       <xsl:for-each select="RR_MP_Budget:CumulativeSubawardConsortiumContractualCosts">
                                          <fo:inline>
                                             <xsl:value-of select="format-number(., '#,##0.00')"/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block/>
                                 </fo:table-cell>
                              </fo:table-row>
                              <fo:table-row>
                                 <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">6. Equipment or Facility Rental/User Fees</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                       <xsl:for-each select="RR_MP_Budget:CumulativeEquipmentFacilityRentalFees">
                                          <fo:inline>
                                             <xsl:value-of select="format-number(., '#,##0.00')"/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block/>
                                 </fo:table-cell>
                              </fo:table-row>
                              <fo:table-row>
                                 <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">7. Alterations and Renovations</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                       <xsl:for-each select="RR_MP_Budget:CumulativeAlterationsAndRenovations">
                                          <fo:inline>
                                             <xsl:value-of select="format-number(., '#,##0.00')"/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block/>
                                 </fo:table-cell>
                              </fo:table-row>
                              <fo:table-row>
                                 <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">8. Other 1</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                       <xsl:for-each select="RR_MP_Budget:CumulativeOther1DirectCost">
                                          <fo:inline>
                                             <xsl:value-of select="format-number(., '#,##0.00')"/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block/>
                                 </fo:table-cell>
                              </fo:table-row>
                              <fo:table-row>
                                 <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">9. Other 2</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                       <xsl:for-each select="RR_MP_Budget:CumulativeOther2DirectCost">
                                          <fo:inline>
                                             <xsl:value-of select="format-number(., '#,##0.00')"/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block/>
                                 </fo:table-cell>
                              </fo:table-row>
                              <fo:table-row>
                                 <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">10. Other 3</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right">
                                    <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                       <xsl:for-each select="RR_MP_Budget:CumulativeOther3DirectCost">
                                          <fo:inline>
                                             <xsl:value-of select="format-number(., '#,##0.00')"/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block/>
                                 </fo:table-cell>
                              </fo:table-row>

                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                 <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px" font-weight="bold">Section G, Direct Costs</fo:block>
                                  <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px" font-weight="bold">(A thru F)</fo:block>
                              </fo:table-cell>
                              <fo:table-cell>
                                 <fo:block/>
                              </fo:table-cell>
                              <fo:table-cell text-align="right">
                                 <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                    <!--<xsl:for-each select="RR_MP_Budget:CumulativeTotalFundsRequestedDirectCosts[. != '0.00']">-->
                                    <xsl:for-each select="RR_MP_Budget:CumulativeTotalFundsRequestedDirectCosts">
                                       <fo:inline font-weight="bold">
                                          <xsl:value-of select="format-number(., '#,##0.00')"/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                 <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px" font-weight="bold">Section H, Indirect Costs</fo:block>
                              </fo:table-cell>
                              <fo:table-cell>
                                 <fo:block/>
                              </fo:table-cell>
                              <fo:table-cell text-align="right">
                                 <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                    <xsl:for-each select="RR_MP_Budget:CumulativeTotalFundsRequestedIndirectCost">
                                       <fo:inline font-weight="bold">
                                          <xsl:value-of select="format-number(., '#,##0.00')"/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                 <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px" font-weight="bold">Section I, Total Direct and Indirect Costs (G + H)</fo:block>
                              </fo:table-cell>
                              <fo:table-cell>
                                 <fo:block/>
                              </fo:table-cell>
                              <fo:table-cell text-align="right">
                                 <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                   <!-- <xsl:for-each select="RR_MP_Budget:CumulativeTotalFundsRequestedDirectIndirectCosts[. != '0.00']">-->
                                   <xsl:for-each select="RR_MP_Budget:CumulativeTotalFundsRequestedDirectIndirectCosts">
                                       <fo:inline font-weight="bold">
                                          <xsl:value-of select="format-number(., '#,##0.00')"/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" padding-before="3pt" padding-after="3pt">
                                 <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px" font-weight="bold">Section J, Fee</fo:block>
                              </fo:table-cell>
                              <fo:table-cell>
                                 <fo:block/>
                              </fo:table-cell>
                              <fo:table-cell text-align="right">
                                 <fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
                                    <xsl:for-each select="RR_MP_Budget:CumulativeFee">
                                       <fo:inline font-weight="bold">
                                          <xsl:value-of select="format-number(., '#,##0.00')"/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <!--============ ROWS End ================================-->
                        </xsl:for-each>
                     </fo:table-body>
                  </fo:table>
               </fo:block>
               <!--</xsl:for-each>-->
               <!--================== end new section summary ======================= -->
            </fo:flow>
         </fo:page-sequence>
      </fo:root>
   </xsl:template>
   <!--==============   Single Year Template ======================================-->
   <xsl:template name="SingleYearPart1">
      <xsl:param name="year"/>
      <fo:block>
         <fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
            <fo:table-column/>
            <fo:table-body>
               <fo:table-row>
                  <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                     <fo:block>
                        <fo:inline font-family="Helvetica, Arial, sans-serif" font-size="12px" font-weight="bold">RESEARCH &amp; RELATED BUDGET - SECTION A &amp; B, BUDGET PERIOD&#160;<xsl:value-of select="$year"/>
                        </fo:inline>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
            </fo:table-body>
         </fo:table>
                                                     <fo:leader leader-pattern="space"/>
<fo:block font-family="Helvetica, Arial, sans-serif" font-size="9px">
   <fo:inline font-weight="bold">ORGANIZATIONAL DUNS*:&#160;&#160;</fo:inline>
   <fo:inline><xsl:value-of select="../RR_MP_Budget:DUNSID"/></fo:inline>
</fo:block>
         <fo:inline font-size="9px" font-weight="bold">Budget Type*:&#160;&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>
         <xsl:for-each select="../RR_MP_Budget:BudgetType">
            <fo:inline padding-before="-1pt" padding-after="-2pt" color="black">
               <fo:inline font-size="9px">
                  <xsl:choose>
                     <xsl:when test=".='Project'">
                        <fo:inline font-family="ZapfDingbats" font-size="9px">&#x25cf;</fo:inline>
                     </xsl:when>
                     <xsl:otherwise>
                        <fo:inline font-family="ZapfDingbats" font-size="9px">&#x274d;</fo:inline>
                     </xsl:otherwise>
                  </xsl:choose>
               </fo:inline>
            </fo:inline>
         </xsl:for-each>
         <fo:inline font-size="9px"> Project&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>

         <xsl:for-each select="../RR_MP_Budget:BudgetType">
            <fo:inline padding-before="-1pt" padding-after="-2pt" color="black">
               <fo:inline font-size="9px">
                  <xsl:choose>
                     <xsl:when test=".='Subaward/Consortium'">
                        <fo:inline font-family="ZapfDingbats" font-size="9px">&#x25cf;</fo:inline>
                     </xsl:when>
                     <xsl:otherwise>
                        <fo:inline font-family="ZapfDingbats" font-size="9px">&#x274d;</fo:inline>
                     </xsl:otherwise>
                  </xsl:choose>
               </fo:inline>
            </fo:inline>
         </xsl:for-each>
         <fo:inline font-size="9px"> Subaward/Consortium</fo:inline>
         <fo:block>

                     </fo:block>
         <fo:inline font-size="9px" hyphenate="true" language="en" font-weight="bold">Enter name of Organization: </fo:inline>
         <fo:inline font-size="9px">
            <xsl:value-of select="../RR_MP_Budget:OrganizationName"/>
         </fo:inline>
         <fo:block>

                     </fo:block>
         <fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
            <fo:table-column/>
            <fo:table-body>
               <fo:table-row>
                  <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">Start Date*:&#160;&#160;</fo:inline>
                        <fo:inline font-size="9px">
                           <xsl:call-template name="formatDate">
                              <xsl:with-param name="value" select="RR_MP_Budget:BudgetPeriodStartDate"/>
                           </xsl:call-template>
                        </fo:inline>
                        <fo:inline font-size="9px" font-weight="bold">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;End Date*:&#160;</fo:inline>
                        <fo:inline font-size="9px">
                           <xsl:call-template name="formatDate">
                              <xsl:with-param name="value" select="RR_MP_Budget:BudgetPeriodEndDate"/>
                           </xsl:call-template>
                        </fo:inline>
                        <fo:inline font-size="9px" font-weight="bold">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; Budget Period:&#160;<xsl:value-of select="$year"/>
                        </fo:inline>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
            </fo:table-body>
         </fo:table>
         <xsl:for-each select="RR_MP_Budget:KeyPersons">
            <fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
               <fo:table-column/>
               <fo:table-body>
                  <fo:table-row>
                     <fo:table-cell border-style="solid" border-color="black" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                        <fo:block>
                           <fo:inline font-size="9px" font-weight="bold">A. Senior/Key Person</fo:inline>
                              <fo:table width="10.4in" space-before.optimum="0pt" space-after.optimum="0pt">
                                 <fo:table-column column-width="0.2in"/>
                                 <fo:table-column column-width="0.4in"/>
                                 <fo:table-column column-width="0.95in"/>
                                 <fo:table-column column-width="0.75in"/>
                                 <fo:table-column column-width="1.1in"/>
                                 <fo:table-column column-width="0.4in"/>
                                 <fo:table-column column-width="1.0in"/>
                                 <fo:table-column column-width="0.7in"/>
                                 <fo:table-column column-width="0.6in"/>
                                 <fo:table-column column-width="0.6in"/>
                                 <fo:table-column column-width="0.6in"/>
                                 <fo:table-column column-width="0.7in"/>
                                 <fo:table-column column-width="0.9in"/>
                                 <fo:table-column column-width="1.4in"/>
                              <fo:table-body>
                                 <fo:table-row>
                                    <fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline color="white" font-weight="bold" font-size="9px">#</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-weight="bold" font-size="9px">Prefix</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-weight="bold" font-size="9px">First Name*</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-weight="bold" font-size="9px">Middle Name</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt"  padding-before="1pt" padding-after="1pt"  display-align="before">
                                       <fo:block>
                                          <fo:inline font-weight="bold" font-size="9px">Last Name*</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-weight="bold" font-size="9px">Suffix</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-weight="bold" font-size="9px">Project Role*</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-weight="bold" font-size="9px">Base Salary ($)</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-weight="bold" font-size="9px">Calendar Months</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-weight="bold" font-size="9px">Academic Months</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-weight="bold" font-size="9px">Summer Months</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-weight="bold" font-size="9px">Requested Salary ($)*</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-weight="bold" font-size="9px">Fringe Benefits ($)*</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-weight="bold" font-size="9px">Funds Requested ($)*</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                                 <!-- adding next row -->
                                                             </fo:table-body>
                           </fo:table>
                           <xsl:for-each select="RR_MP_Budget:KeyPerson">
                              <fo:table width="10.4in" space-before.optimum="0pt" space-after.optimum="0pt">
                                 <fo:table-column column-width="0.2in"/>
                                 <fo:table-column column-width="0.4in"/>
                                 <fo:table-column column-width="0.95in"/>
                                 <fo:table-column column-width="0.75in"/>
                                 <fo:table-column column-width="1.1in"/>
                                 <fo:table-column column-width="0.4in"/>
                                 <fo:table-column column-width="1.0in"/>
                                 <fo:table-column column-width="0.7in"/>
                                 <fo:table-column column-width="0.6in"/>
                                 <fo:table-column column-width="0.6in"/>
                                 <fo:table-column column-width="0.6in"/>
                                 <fo:table-column column-width="0.7in"/>
                                 <fo:table-column column-width="0.9in"/>
                                 <fo:table-column column-width="1.4in"/>
                                 <fo:table-body>
                                    <fo:table-row border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
                                       <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                             <fo:inline font-size="9px">
                                                <xsl:value-of select="position()"/>.</fo:inline>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                             <xsl:for-each select="RR_MP_Budget:Name">
                                                <xsl:for-each select="globLib:PrefixName">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                             </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                             <xsl:for-each select="RR_MP_Budget:Name">
                                                <xsl:for-each select="globLib:FirstName">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                             </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                             <xsl:for-each select="RR_MP_Budget:Name">
                                                <xsl:for-each select="globLib:MiddleName">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                             </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                             <xsl:for-each select="RR_MP_Budget:Name">
                                                <xsl:for-each select="globLib:LastName">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                             </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                             <xsl:for-each select="RR_MP_Budget:Name">
                                                <xsl:for-each select="globLib:SuffixName">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                             </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                             <xsl:for-each select="RR_MP_Budget:ProjectRole">
                                                <fo:inline font-size="9px">
                                                   <xsl:apply-templates/>
                                                </fo:inline>
                                             </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                                <xsl:for-each select="RR_MP_Budget:BaseSalary">
                                                   <fo:inline font-size="9px">
                                                      <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                                <xsl:for-each select="RR_MP_Budget:CalendarMonths">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                                <xsl:for-each select="RR_MP_Budget:AcademicMonths">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                                <xsl:for-each select="RR_MP_Budget:SummerMonths">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                                <xsl:for-each select="RR_MP_Budget:RequestedSalary">
                                                   <fo:inline font-size="9px">
                                                      <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                                <xsl:for-each select="RR_MP_Budget:FringeBenefits">
                                                   <fo:inline font-size="9px">
                                                      <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                                <xsl:for-each select="RR_MP_Budget:FundsRequested">
                                                   <fo:inline font-size="9px">
                                                      <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                    </fo:table-row>
                                 </fo:table-body>
                              </fo:table>
                           </xsl:for-each>
                           <fo:table width="10.4in" space-before.optimum="0pt" space-after.optimum="0pt">
                              <fo:table-column column-width="0.2in"/>
                                 <fo:table-column column-width="1.35in"/>
                                 <fo:table-column column-width="0.75in"/>
                                 <fo:table-column column-width="1.1in"/>
                                 <fo:table-column column-width="0.4in"/>
                                 <fo:table-column column-width="1.0in"/>
                                 <fo:table-column column-width="0.7in"/>
                                 <fo:table-column column-width="0.6in"/>
                                 <fo:table-column column-width="0.6in"/>
                                 <fo:table-column column-width="0.6in"/>
                              <fo:table-column column-width="1.6in"/>
                              <fo:table-column column-width="1.4in"/>
                              <fo:table-body>
                                 <fo:table-row>
                                    <fo:table-cell number-columns-spanned="11" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-size="9px" font-weight="bold">Total Funds Requested for all Senior Key Persons in the attached file </fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:TotalFundForAttachedKeyPersons">
                                             <fo:inline font-size="9px" font-weight="bold">
                                                <xsl:value-of select="format-number(., '#,##0.00')"/>
                                             </fo:inline>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                                 <fo:table-row>
                                    <fo:table-cell number-columns-spanned="3" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-size="9px" font-weight="bold">Additional Senior Key Persons:</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell number-columns-spanned="4" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-size="9px">File Name: </fo:inline>
                                          <xsl:for-each select="RR_MP_Budget:AttachedKeyPersons">
                                             <xsl:for-each select="att:FileName">
                                                <fo:inline font-size="9px">
                                                   <xsl:apply-templates/>
                                                </fo:inline>
                                             </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>

                                    <fo:table-cell number-columns-spanned="4" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-size="9px" font-weight="bold">Total Senior/Key Person</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" border-top-color="black" border-top-style="solid" border-top-width="1px">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:TotalFundForKeyPersons">
                                             <fo:inline font-size="9px" font-weight="bold">
                                                <xsl:value-of select="format-number(., '#,##0.00')"/>
                                             </fo:inline>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                                 <fo:table-row>
                                 <fo:table-cell number-columns-spanned="3" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>

                                       </fo:block>
                                    </fo:table-cell>
                                  <fo:table-cell number-columns-spanned="9" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block color="white">
                                          <fo:inline font-size="9px">Mime Type: </fo:inline>
                                          <xsl:for-each select="RR_MP_Budget:AttachedKeyPersons">
                                             <xsl:for-each select="att:MimeType">
                                                <fo:inline font-size="9px">
                                                   <xsl:apply-templates/>
                                                </fo:inline>
                                             </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                              </fo:table-body>
                           </fo:table>
                        </fo:block>
                     </fo:table-cell>
                  </fo:table-row>
               </fo:table-body>
            </fo:table>
         </xsl:for-each>
         <fo:block>
            <fo:leader leader-pattern="space"/>
         </fo:block>
         <!--

          -->
         <fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt">
            <fo:table-column/>
            <fo:table-body>
               <fo:table-row>
                  <fo:table-cell border-style="solid" border-color="black" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">B. Other Personnel</fo:inline>
                        <fo:block>
                           <xsl:text>&#xA;</xsl:text>
                        </fo:block>
                        <fo:table width="10.4in" space-before.optimum="0pt" space-after.optimum="0pt">
                           <fo:table-column column-width="0.8in"/>
                           <fo:table-column column-width="3.1in"/>
                           <fo:table-column column-width="1.1in"/>
                           <fo:table-column column-width="1.1in"/>
                           <fo:table-column column-width="1.1in"/>
                           <fo:table-column column-width="1.4in"/>
                           <fo:table-column column-width="1.3in"/>
                           <fo:table-column column-width="1.4in"/>
                           <fo:table-body>
                              <fo:table-row>
                                 <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                    <fo:block>
                                       <fo:inline font-weight="bold" font-size="9px">Number of Personnel* </fo:inline>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="left" width="150pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                    <fo:block>
                                       <fo:inline font-weight="bold" font-size="9px">Project Role*</fo:inline>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                    <fo:block>
                                       <fo:inline font-weight="bold" font-size="9px">Calendar Months</fo:inline>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                    <fo:block>
                                       <fo:inline font-weight="bold" font-size="9px">Academic Months</fo:inline>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                    <fo:block>
                                       <fo:inline font-weight="bold" font-size="9px">Summer Months</fo:inline>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                    <fo:block>
                                       <fo:inline font-weight="bold" font-size="9px">Requested Salary (&#36;)*</fo:inline>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                    <fo:block>
                                       <fo:inline font-weight="bold" font-size="9px">Fringe Benefits*</fo:inline>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                    <fo:block>
                                       <fo:inline font-weight="bold" font-size="9px">Funds Requested (&#36;)*</fo:inline>
                                    </fo:block>
                                 </fo:table-cell>
                              </fo:table-row>
                              <xsl:for-each select="RR_MP_Budget:OtherPersonnel">
                                 <fo:table-row border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
                                    <fo:table-cell line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:PostDocAssociates">
                                             <xsl:for-each select="RR_MP_Budget:NumberOfPersonnel">
                                                <fo:inline font-size="9px">
                                                   <xsl:apply-templates/>
                                                </fo:inline>
                                             </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell line-height="10pt" text-align="left" width="150pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-size="9px">Post Doctoral Associates</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:PostDocAssociates">
                                                <xsl:for-each select="RR_MP_Budget:CalendarMonths">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:PostDocAssociates">
                                                <xsl:for-each select="RR_MP_Budget:AcademicMonths">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:PostDocAssociates">
                                                <xsl:for-each select="RR_MP_Budget:SummerMonths">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:PostDocAssociates">
                                                <xsl:for-each select="RR_MP_Budget:RequestedSalary">
                                                   <fo:inline font-size="9px">
                                                      <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:PostDocAssociates">
                                                <xsl:for-each select="RR_MP_Budget:FringeBenefits">
                                                   <fo:inline font-size="9px">
                                                      <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:PostDocAssociates">
                                                <xsl:for-each select="RR_MP_Budget:FundsRequested">
                                                   <fo:inline font-size="9px">
                                                      <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                                 <fo:table-row border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
                                    <fo:table-cell line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:GraduateStudents">
                                             <xsl:for-each select="RR_MP_Budget:NumberOfPersonnel">
                                                <fo:inline font-size="9px">
                                                   <xsl:apply-templates/>
                                                </fo:inline>
                                             </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell line-height="10pt" text-align="left" width="150pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-size="9px">Graduate Students</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:GraduateStudents">
                                                <xsl:for-each select="RR_MP_Budget:CalendarMonths">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:GraduateStudents">
                                                <xsl:for-each select="RR_MP_Budget:AcademicMonths">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:GraduateStudents">
                                                <xsl:for-each select="RR_MP_Budget:SummerMonths">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:GraduateStudents">
                                                <xsl:for-each select="RR_MP_Budget:RequestedSalary">
                                                   <fo:inline font-size="9px">
                                                      <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:GraduateStudents">
                                                <xsl:for-each select="RR_MP_Budget:FringeBenefits">
                                                   <fo:inline font-size="9px">
                                                      <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:GraduateStudents">
                                                <xsl:for-each select="RR_MP_Budget:FundsRequested">
                                                   <fo:inline font-size="9px">
                                                      <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                                 <fo:table-row border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
                                    <fo:table-cell line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:UndergraduateStudents">
                                             <xsl:for-each select="RR_MP_Budget:NumberOfPersonnel">
                                                <fo:inline font-size="9px">
                                                   <xsl:apply-templates/>
                                                </fo:inline>
                                             </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell line-height="10pt" text-align="left" width="150pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-size="9px">Undergraduate Students</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:UndergraduateStudents">
                                                <xsl:for-each select="RR_MP_Budget:CalendarMonths">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:UndergraduateStudents">
                                                <xsl:for-each select="RR_MP_Budget:AcademicMonths">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:UndergraduateStudents">
                                                <xsl:for-each select="RR_MP_Budget:SummerMonths">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:UndergraduateStudents">
                                                <xsl:for-each select="RR_MP_Budget:RequestedSalary">
                                                   <fo:inline font-size="9px">
                                                      <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:UndergraduateStudents">
                                                <xsl:for-each select="RR_MP_Budget:FringeBenefits">
                                                   <fo:inline font-size="9px">
                                                      <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:UndergraduateStudents">
                                                <xsl:for-each select="RR_MP_Budget:FundsRequested">
                                                   <fo:inline font-size="9px">
                                                      <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                                 <fo:table-row border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
                                    <fo:table-cell line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:SecretarialClerical">
                                             <xsl:for-each select="RR_MP_Budget:NumberOfPersonnel">
                                                <fo:inline font-size="9px">
                                                   <xsl:apply-templates/>
                                                </fo:inline>
                                             </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell line-height="10pt" text-align="left" width="150pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <fo:inline font-size="9px">Secretarial/Clerical</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:SecretarialClerical">
                                                <xsl:for-each select="RR_MP_Budget:CalendarMonths">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:SecretarialClerical">
                                                <xsl:for-each select="RR_MP_Budget:AcademicMonths">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:SecretarialClerical">
                                                <xsl:for-each select="RR_MP_Budget:SummerMonths">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:SecretarialClerical">
                                                <xsl:for-each select="RR_MP_Budget:RequestedSalary">
                                                   <fo:inline font-size="9px">
                                                      <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:SecretarialClerical">
                                                <xsl:for-each select="RR_MP_Budget:FringeBenefits">
                                                   <fo:inline font-size="9px">
                                                      <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                       <fo:block>
                                          <xsl:for-each select="RR_MP_Budget:SecretarialClerical">
                                                <xsl:for-each select="RR_MP_Budget:FundsRequested">
                                                   <fo:inline font-size="9px">
                                                      <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </xsl:for-each>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                              </xsl:for-each>
                           </fo:table-body>
                        </fo:table>
                        <xsl:for-each select="RR_MP_Budget:OtherPersonnel">
                           <xsl:for-each select="RR_MP_Budget:Other">
                        <fo:table width="10.4in" space-before.optimum="0pt" space-after.optimum="0pt">
                           <fo:table-column column-width="0.8in"/>
                           <fo:table-column column-width="3.1in"/>
                           <fo:table-column column-width="1.1in"/>
                           <fo:table-column column-width="1.1in"/>
                           <fo:table-column column-width="1.1in"/>
                           <fo:table-column column-width="1.4in"/>
                           <fo:table-column column-width="1.3in"/>
                           <fo:table-column column-width="1.4in"/>
                                 <fo:table-body>
                                    <fo:table-row border-bottom-color="gray" border-bottom-style="dotted" border-bottom-width="1px">
                                       <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                             <xsl:for-each select="RR_MP_Budget:NumberOfPersonnel">
                                                <fo:inline font-size="9px">
                                                   <xsl:apply-templates/>
                                                </fo:inline>
                                             </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="left" width="150pt" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                             <xsl:for-each select="RR_MP_Budget:ProjectRole">
                                                <fo:inline font-size="9px">
                                                   <xsl:apply-templates/>
                                                </fo:inline>
                                             </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                                <xsl:for-each select="RR_MP_Budget:CalendarMonths">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                                <xsl:for-each select="RR_MP_Budget:AcademicMonths">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                                <xsl:for-each select="RR_MP_Budget:SummerMonths">
                                                   <fo:inline font-size="9px">
                                                      <xsl:apply-templates/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                                <xsl:for-each select="RR_MP_Budget:RequestedSalary">
                                                   <fo:inline font-size="9px">
                                                      <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="right" padding-start="3pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                                <xsl:for-each select="RR_MP_Budget:FringeBenefits">
                                                   <fo:inline font-size="9px">
                                                      <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en" line-height="10pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                          <fo:block>
                                                <xsl:for-each select="RR_MP_Budget:FundsRequested">
                                                   <fo:inline font-size="9px">
                                                      <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                   </fo:inline>
                                                </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                    </fo:table-row>
                                 </fo:table-body>
                              </fo:table>
                           </xsl:for-each>
                        </xsl:for-each>
                        <fo:table width="10.4in" space-before.optimum="0pt" space-after.optimum="0pt">
                           <fo:table-column column-width="0.8in"/>
                           <fo:table-column column-width="4.7in"/>
                           <fo:table-column column-width="0.6in"/>
                           <fo:table-column column-width="0.6in"/>
                           <fo:table-column column-width="0.6in"/>
                           <fo:table-column column-width="0.7in"/>
                           <fo:table-column column-width="0.9in"/>
                           <fo:table-column column-width="1.4in"/>
                           <fo:table-body>
                              <fo:table-row border-top-color="black" border-top-style="solid" border-top-width="1px">
                                 <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                    <fo:block>
                                       <xsl:for-each select="RR_MP_Budget:OtherPersonnel">
                                          <xsl:for-each select="RR_MP_Budget:OtherPersonnelTotalNumber">
                                             <fo:inline font-size="9px" font-weight="bold">
                                                <xsl:apply-templates/>
                                             </fo:inline>
                                          </xsl:for-each>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                    <fo:block>
                                       <fo:inline font-size="9px" font-weight="bold">Total Number Other Personnel</fo:inline>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell number-columns-spanned="5" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                    <fo:block>
                                       <fo:inline font-size="9px" font-weight="bold">Total Other Personnel</fo:inline>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                    <fo:block>
                                       <xsl:for-each select="RR_MP_Budget:OtherPersonnel">
                                          <xsl:if test="RR_MP_Budget:TotalOtherPersonnelFund!=''">
                                             <xsl:for-each select="RR_MP_Budget:TotalOtherPersonnelFund">
                                                <fo:inline font-size="9px" font-weight="bold">
                                                   <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                </fo:inline>
                                             </xsl:for-each>
                                          </xsl:if>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                              </fo:table-row>
                              <fo:table-row>
                                 <fo:table-cell number-columns-spanned="7" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                                    <fo:block>
                                       <fo:inline font-size="9px" font-weight="bold">Total Salary, Wages and Fringe Benefits (A+B)</fo:inline>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" border-top-color="black" border-top-style="solid" border-top-width="1px">
                                    <fo:block>
                                       <xsl:for-each select="RR_MP_Budget:TotalCompensation">
                                          <fo:inline font-size="9px" font-weight="bold">
                                             <xsl:value-of select="format-number(., '#,##0.00')"/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                              </fo:table-row>
                           </fo:table-body>
                        </fo:table>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
            </fo:table-body>
         </fo:table>
         <fo:block font-size="8px" padding-before="1pt">RESEARCH &amp; RELATED Budget {A-B} (Funds Requested)</fo:block>
      </fo:block>
   </xsl:template>
   <xsl:template name="SingleYearPart2">
      <xsl:param name="year"/>
      <fo:block>
         <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
            <fo:table-column/>
            <fo:table-body>
               <fo:table-row>
                  <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <fo:inline font-weight="bold" font-size="12px">RESEARCH &amp; RELATED BUDGET - SECTION C, D, &amp; E, BUDGET PERIOD&#160;<xsl:value-of select="$year"/>
                        </fo:inline>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
            </fo:table-body>
         </fo:table>
                     <fo:leader leader-pattern="space"/>
<fo:block font-size="9px">
   <fo:inline font-weight="bold">ORGANIZATIONAL DUNS*: &#160;&#160;</fo:inline>
   <fo:inline><xsl:value-of select="../RR_MP_Budget:DUNSID"/></fo:inline>
</fo:block>
         <fo:inline font-size="9px" font-weight="bold">Budget Type*: &#160;&#160; </fo:inline>
         <fo:inline font-size="9px">&#160;&#160;&#160; </fo:inline>
         <xsl:for-each select="../RR_MP_Budget:BudgetType">
            <fo:inline padding-before="-1pt" padding-after="-2pt" color="black">
               <fo:inline font-size="9px">
                  <xsl:choose>
                     <xsl:when test=".='Project'">
                        <fo:inline font-family="ZapfDingbats" font-size="9px">&#x25cf;</fo:inline>
                     </xsl:when>
                     <xsl:otherwise>
                        <fo:inline font-family="ZapfDingbats" font-size="9px">&#x274d;</fo:inline>
                     </xsl:otherwise>
                  </xsl:choose>
               </fo:inline>
            </fo:inline>
         </xsl:for-each>
         <fo:inline font-size="9px"> Project&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>

         <xsl:for-each select="../RR_MP_Budget:BudgetType">
            <fo:inline padding-before="-1pt" padding-after="-2pt" color="black">
               <fo:inline font-size="9px">
                  <xsl:choose>
                     <xsl:when test=".='Subaward/Consortium'">
                        <fo:inline font-family="ZapfDingbats" font-size="9px">&#x25cf;</fo:inline>
                     </xsl:when>
                     <xsl:otherwise>
                        <fo:inline font-family="ZapfDingbats" font-size="9px">&#x274d;</fo:inline>
                     </xsl:otherwise>
                  </xsl:choose>
               </fo:inline>
            </fo:inline>
         </xsl:for-each>
         <fo:inline font-size="9px"> Subaward/Consortium</fo:inline>
         <fo:block>

                     </fo:block>
         <fo:inline font-size="9px" font-weight="bold">Enter name of Organization: </fo:inline>
         <xsl:for-each select="../RR_MP_Budget:OrganizationName">
            <fo:inline font-size="9px">
               <xsl:apply-templates/>
            </fo:inline>
         </xsl:for-each>
         <fo:block>

                     </fo:block>
         <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
            <fo:table-column/>
            <fo:table-body>
               <fo:table-row>
                  <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">Start Date*: &#160;</fo:inline>
                        <fo:inline font-size="9px">
                           <xsl:call-template name="formatDate">
                              <xsl:with-param name="value" select="RR_MP_Budget:BudgetPeriodStartDate"/>
                           </xsl:call-template>
                        </fo:inline>
                        <fo:inline font-size="9px" font-weight="bold">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;End Date*: &#160;</fo:inline>
                        <fo:inline font-size="9px">
                           <xsl:call-template name="formatDate">
                              <xsl:with-param name="value" select="RR_MP_Budget:BudgetPeriodEndDate"/>
                           </xsl:call-template>
                        </fo:inline>
                        <fo:inline font-size="9px" font-weight="bold">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; Budget Period:&#160;<xsl:value-of select="$year"/>
                        </fo:inline>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
            </fo:table-body>
         </fo:table>
         <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
            <fo:table-column/>
            <fo:table-body>
               <fo:table-row>
                  <fo:table-cell hyphenate="true" language="en" border-style="solid" border-color="black" padding-start="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">C. Equipment Description</fo:inline>
                        <fo:block font-size="2pt">&#160;</fo:block>
                        <fo:inline font-size="9px">List items and dollar amount for each item exceeding $5,000</fo:inline>
                        <fo:table width="7.6667in">
                           <fo:table-column column-width="6.2667in"/>
                           <fo:table-column column-width="1.4in"/>
                           <fo:table-header>
                              <fo:table-row>
                                 <fo:table-cell border-before-color="white" text-align="left" padding-before="2pt" padding-after="1pt" display-align="before">
                                    <fo:block>
                                       <fo:inline font-weight="bold" font-size="9px">Equipment Item</fo:inline>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell border-before-color="white" border-end-color="white" text-align="right" padding-before="2pt" padding-after="1pt" display-align="before">
                                    <fo:block>
                                       <fo:inline font-weight="bold" font-size="9px">Funds Requested (&#36;)*</fo:inline>
                                    </fo:block>
                                 </fo:table-cell>
                              </fo:table-row>
                           </fo:table-header>
                           <fo:table-body>
                               <fo:table-row>
                                   <fo:table-cell>
                                       <fo:block></fo:block>
                                   </fo:table-cell>
                               </fo:table-row>
                              <xsl:for-each select="RR_MP_Budget:Equipment">
                                 <xsl:for-each select="RR_MP_Budget:EquipmentList">
                                    <fo:table-row>
                                       <fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                                          <fo:block>
                                             <fo:inline font-size="9px">
                                                <xsl:value-of select="position()"/>.&#160;</fo:inline>
                                             <xsl:for-each select="RR_MP_Budget:EquipmentItem">
                                                <fo:inline font-size="9px">
                                                   <xsl:apply-templates/>
                                                </fo:inline>
                                             </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell line-height="9pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="right">
                                          <fo:block>
                                             <xsl:for-each select="RR_MP_Budget:FundsRequested">
                                                <fo:inline font-size="9px">
                                                   <xsl:value-of select="format-number(., '#,##0.00')"/>
                                                </fo:inline>
                                             </xsl:for-each>
                                          </fo:block>
                                       </fo:table-cell>
                                    </fo:table-row>
                                 </xsl:for-each>
                              </xsl:for-each>
                           </fo:table-body>
                        </fo:table>
                        <fo:table width="7.6667in">
                           <fo:table-column column-width="6.2667in"/>
                           <fo:table-column column-width="1.4in"/>
                           <fo:table-header>
                              <fo:table-row>
                                 <fo:table-cell text-align="left" padding-before="2pt" padding-after="2pt" display-align="before">
                                    <fo:block>
                                       <fo:inline font-size="9px" font-weight="bold">Total funds requested for all equipment listed in the attached file</fo:inline>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right" padding-before="2pt" padding-after="2pt" display-align="before">
                                    <fo:block>
                                       <xsl:for-each select="RR_MP_Budget:Equipment">
                                          <xsl:for-each select="RR_MP_Budget:TotalFundForAttachedEquipment">
                                             <fo:inline font-size="9px" font-weight="bold">
                                                <xsl:value-of select="format-number(., '#,##0.00')"/>
                                             </fo:inline>
                                          </xsl:for-each>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                              </fo:table-row>
                           </fo:table-header>
                           <fo:table-body>
                              <fo:table-row>
                                 <fo:table-cell padding-before="2pt" padding-after="2pt" display-align="before" text-align="right">
                                    <fo:block>
                                       <fo:inline font-size="9px" font-weight="bold">Total Equipment</fo:inline>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell text-align="right" padding-before="2pt" padding-after="2pt" display-align="before"  border-top-color="black" border-top-style="solid" border-top-width="1px">
                                    <fo:block>
                                       <xsl:for-each select="RR_MP_Budget:Equipment">
                                          <xsl:for-each select="RR_MP_Budget:TotalFund">
                                             <fo:inline font-size="9px" font-weight="bold">
                                                <xsl:value-of select="format-number(., '#,##0.00')"/>
                                             </fo:inline>
                                          </xsl:for-each>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                              </fo:table-row>
                           </fo:table-body>
                        </fo:table>
                        <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                           <fo:table-column/>
                           <fo:table-column/>
                           <fo:table-column/>
                           <fo:table-body>
                              <fo:table-row>
                                 <fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                                    <fo:block>
                                       <fo:inline font-size="9px" font-weight="bold">Additional Equipment: </fo:inline>

                                        <fo:inline font-size="9px">&#160;&#160;&#160;&#160;&#160;&#160;File Name: </fo:inline>
                                       <fo:inline><xsl:for-each select="RR_MP_Budget:Equipment/RR_MP_Budget:AdditionalEquipmentsAttachment">
                                          <xsl:for-each select="att:FileName">
                                             <fo:inline font-size="9px">
                                                <xsl:apply-templates/>
                                             </fo:inline>
                                          </xsl:for-each>
                                       </xsl:for-each>
                                       </fo:inline>
                                    </fo:block>
                                 </fo:table-cell>
                                 <!-- ele adding row -->
                               </fo:table-row>
                               <fo:table-row>
                                 <fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start"  number-columns-spanned="2">
                                    <fo:block>

                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                                    <fo:block color="white">
                                       <fo:inline font-size="9px">Mime Type: </fo:inline>
                                       <xsl:for-each select="RR_MP_Budget:Equipment/RR_MP_Budget:AdditionalEquipmentsAttachment">
                                          <xsl:for-each select="att:MimeType">
                                             <fo:inline font-size="9px">
                                                <xsl:apply-templates/>
                                             </fo:inline>
                                          </xsl:for-each>
                                       </xsl:for-each>
                                    </fo:block>
                                 </fo:table-cell>
                              </fo:table-row>
                           </fo:table-body>
                        </fo:table>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
            </fo:table-body>
         </fo:table>
         <fo:block>
            <fo:leader leader-pattern="space"/>
         </fo:block>
         <fo:table border-style="solid" border-color="black" width="7.7in" space-before.optimum="1pt" space-after.optimum="2pt">
            <fo:table-column column-width="6.3in"/>
            <fo:table-column column-width="1.4in"/>
            <fo:table-body>
               <fo:table-row>
                  <fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">D. Travel</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <fo:inline font-weight="bold" font-size="9px">Funds Requested (&#36;)*</fo:inline>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <fo:table-row>
                  <fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                     <fo:block>
                        <fo:inline font-size="9px">1. Domestic Travel Costs ( Incl. Canada, Mexico, and U.S. Possessions)</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <xsl:for-each select="RR_MP_Budget:Travel/RR_MP_Budget:DomesticTravelCost">
                           <fo:inline font-size="9px">
                              <xsl:value-of select="format-number(., '#,##0.00')"/>
                           </fo:inline>
                        </xsl:for-each>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <fo:table-row>
                  <fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                     <fo:block>
                        <fo:inline font-size="9px">2. Foreign Travel Costs</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <xsl:for-each select="RR_MP_Budget:Travel/RR_MP_Budget:ForeignTravelCost">
                           <fo:inline font-size="9px">
                              <xsl:value-of select="format-number(., '#,##0.00')"/>
                           </fo:inline>
                        </xsl:for-each>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <fo:table-row>
                  <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold" text-align="right">Total Travel Cost</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before"   border-top-color="black" border-top-style="solid" border-top-width="1px">
                     <fo:block>
                        <xsl:for-each select="RR_MP_Budget:Travel/RR_MP_Budget:TotalTravelCost">
                           <fo:inline font-size="9px" font-weight="bold">
                              <xsl:value-of select="format-number(., '#,##0.00')"/>
                           </fo:inline>
                        </xsl:for-each>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
            </fo:table-body>
         </fo:table>
         <fo:block>
            <fo:leader leader-pattern="space"/>
         </fo:block>
         <fo:table border-style="solid" border-color="black" width="7.7in" space-before.optimum="1pt" space-after.optimum="2pt">
            <fo:table-column column-width="3.8in"/>
            <fo:table-column column-width="2.5in"/>
             <fo:table-column column-width="1.4in"/>
            <fo:table-body>
               <fo:table-row>
                  <fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start" number-columns-spanned="2">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">E. Participant/Trainee Support Costs</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <fo:inline font-weight="bold" font-size="9px">Funds Requested (&#36;)*</fo:inline>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <fo:table-row>
                  <fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start" number-columns-spanned="2">
                     <fo:block>
                        <fo:inline font-size="9px">1. Tuition/Fees/Health Insurance</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <xsl:for-each select="RR_MP_Budget:ParticipantTraineeSupportCosts/RR_MP_Budget:TuitionFeeHealthInsurance">
                           <fo:inline font-size="9px">
                              <xsl:value-of select="format-number(., '#,##0.00')"/>
                           </fo:inline>
                        </xsl:for-each>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <fo:table-row>
                  <fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start" number-columns-spanned="2">
                     <fo:block>
                        <fo:inline font-size="9px">2. Stipends</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <xsl:for-each select="RR_MP_Budget:ParticipantTraineeSupportCosts/RR_MP_Budget:Stipends">
                           <fo:inline font-size="9px">
                              <xsl:value-of select="format-number(., '#,##0.00')"/>
                           </fo:inline>
                        </xsl:for-each>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <fo:table-row>
                  <fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start" number-columns-spanned="2">
                     <fo:block>
                        <fo:inline font-size="9px">3. Travel</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <xsl:for-each select="RR_MP_Budget:ParticipantTraineeSupportCosts/RR_MP_Budget:Travel">
                           <fo:inline font-size="9px">
                              <xsl:value-of select="format-number(., '#,##0.00')"/>
                           </fo:inline>
                        </xsl:for-each>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <fo:table-row>
                  <fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start" number-columns-spanned="2">
                     <fo:block>
                        <fo:inline font-size="9px">4. Subsistence</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <xsl:for-each select="RR_MP_Budget:ParticipantTraineeSupportCosts/RR_MP_Budget:Subsistence">
                           <fo:inline font-size="9px">
                              <xsl:value-of select="format-number(., '#,##0.00')"/>
                           </fo:inline>
                        </xsl:for-each>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <fo:table-row>
                  <fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start" number-columns-spanned="2">
                     <fo:block hyphenate="true">
                        <fo:inline font-size="9px">5. Other:&#160;&#160; </fo:inline>
                        <xsl:for-each select="RR_MP_Budget:ParticipantTraineeSupportCosts/RR_MP_Budget:Other">
                           <xsl:for-each select="RR_MP_Budget:Description">
                              <fo:inline font-size="9px">
                                 <xsl:apply-templates/>
                              </fo:inline>
                           </xsl:for-each>
                        </xsl:for-each>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <xsl:for-each select="RR_MP_Budget:ParticipantTraineeSupportCosts/RR_MP_Budget:Other">
                           <xsl:for-each select="RR_MP_Budget:Cost">
                              <fo:inline font-size="9px">
                                 <xsl:value-of select="format-number(., '#,##0.00')"/>
                              </fo:inline>
                           </xsl:for-each>
                        </xsl:for-each>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <fo:table-row>
                  <fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                     <fo:block>
                        <xsl:for-each select="RR_MP_Budget:ParticipantTraineeSupportCosts/RR_MP_Budget:ParticipantTraineeNumber">
                           <fo:inline font-size="9px" font-weight="bold">
                              <xsl:apply-templates/>
                           </fo:inline>
                        </xsl:for-each>
                        <!--fo:inline font-size="9px" font-weight="bold">&#160;&#160;&#160;Number of Participants/Trainees&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; Total Participant/Trainee Support Costs</fo:inline>-->
                         <fo:inline font-size="9px" font-weight="bold">&#160;&#160;&#160;Number of Participants/Trainees</fo:inline>
                     </fo:block>
                  </fo:table-cell>

                     <!--i am adding this new column -->
                  <fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">Total Participant Trainee Support Costs</fo:inline>
                     </fo:block>
                  </fo:table-cell>

                  <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" border-top-color="black" border-top-style="solid" border-top-width="1px">
                     <fo:block>
                        <xsl:for-each select="RR_MP_Budget:ParticipantTraineeSupportCosts/RR_MP_Budget:TotalCost">
                           <fo:inline font-size="9px" font-weight="bold">
                              <xsl:value-of select="format-number(., '#,##0.00')"/>
                           </fo:inline>
                        </xsl:for-each>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
            </fo:table-body>
         </fo:table>
         <fo:block font-size="8px" padding-before="4pt">RESEARCH &amp; RELATED Budget {C-E} (Funds Requested)</fo:block>
         <fo:block break-after="page">
            <xsl:text>&#xA;</xsl:text>
         </fo:block>
         <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
            <fo:table-column/>
            <fo:table-body>
               <fo:table-row>
                  <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <fo:inline font-weight="bold" font-size="12px">RESEARCH &amp; RELATED BUDGET - SECTIONS F-K, BUDGET PERIOD&#160;<xsl:value-of select="$year"/>
                        </fo:inline>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
            </fo:table-body>
         </fo:table>
                     <fo:leader leader-pattern="space"/>
<fo:block font-size="9px">
   <fo:inline font-weight="bold">ORGANIZATIONAL DUNS*: &#160;&#160;</fo:inline>
   <fo:inline><xsl:value-of select="../RR_MP_Budget:DUNSID"/></fo:inline>
</fo:block>
         <fo:inline font-size="9px" font-weight="bold">Budget Type*: &#160;&#160; </fo:inline>
         <fo:inline font-size="9px">&#160;&#160;&#160; </fo:inline>
         <xsl:for-each select="../RR_MP_Budget:BudgetType">
            <fo:inline padding-before="-1pt" padding-after="-2pt" color="black">
               <fo:inline font-size="9px">
                  <xsl:choose>
                     <xsl:when test=".='Project'">
                        <fo:inline font-family="ZapfDingbats" font-size="9px">&#x25cf;</fo:inline>
                     </xsl:when>
                     <xsl:otherwise>
                        <fo:inline font-family="ZapfDingbats" font-size="9px">&#x274d;</fo:inline>
                     </xsl:otherwise>
                  </xsl:choose>
               </fo:inline>
            </fo:inline>
         </xsl:for-each>
         <fo:inline font-size="9px"> Project&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>

         <xsl:for-each select="../RR_MP_Budget:BudgetType">
            <fo:inline padding-before="-1pt" padding-after="-2pt" color="black">
               <fo:inline font-size="9px">
                  <xsl:choose>
                     <xsl:when test=".='Subaward/Consortium'">
                        <fo:inline font-family="ZapfDingbats" font-size="9px">&#x25cf;</fo:inline>
                     </xsl:when>
                     <xsl:otherwise>
                        <fo:inline font-family="ZapfDingbats" font-size="9px">&#x274d;</fo:inline>
                     </xsl:otherwise>
                  </xsl:choose>
               </fo:inline>
            </fo:inline>
         </xsl:for-each>
         <fo:inline font-size="9px"> Subaward/Consortium</fo:inline>
         <fo:block>

                     </fo:block>
         <fo:inline font-size="9px" font-weight="bold">Enter name of Organization: </fo:inline>
         <xsl:for-each select="../RR_MP_Budget:OrganizationName">
            <fo:inline font-size="9px">
               <xsl:apply-templates/>
            </fo:inline>
         </xsl:for-each>
         <fo:block>

                     </fo:block>
         <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
            <fo:table-column/>
            <fo:table-body>
               <fo:table-row>
                  <fo:table-cell text-align="center" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">Start Date*: &#160;</fo:inline>
                        <fo:inline font-size="9px">
                           <xsl:call-template name="formatDate">
                              <xsl:with-param name="value" select="RR_MP_Budget:BudgetPeriodStartDate"/>
                           </xsl:call-template>
                        </fo:inline>
                        <fo:inline font-size="9px" font-weight="bold">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;End Date*: &#160;</fo:inline>
                        <fo:inline font-size="9px">
                           <xsl:call-template name="formatDate">
                              <xsl:with-param name="value" select="RR_MP_Budget:BudgetPeriodEndDate"/>
                           </xsl:call-template>
                        </fo:inline>
                        <fo:inline font-size="9px" font-weight="bold">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; Budget Period:&#160;<xsl:value-of select="$year"/>
                        </fo:inline>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
            </fo:table-body>
         </fo:table>
         <fo:table border-style="solid" border-color="black" width="7.7in" space-before.optimum="1pt" space-after.optimum="2pt">
            <fo:table-column column-width="6.3in"/>
            <fo:table-column column-width="1.4in"/>
            <fo:table-body>
               <fo:table-row>
                  <fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">F. Other Direct Costs</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <fo:inline font-weight="bold" font-size="9px">Funds Requested (&#36;)*</fo:inline>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <xsl:for-each select="RR_MP_Budget:OtherDirectCosts">
                  <fo:table-row>
                     <fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                        <fo:block>
                           <fo:inline font-size="9px">1. Materials and Supplies</fo:inline>
                        </fo:block>
                     </fo:table-cell>
                     <fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                        <fo:block>
                           <xsl:for-each select="RR_MP_Budget:MaterialsSupplies">
                              <fo:inline font-size="9px">
                                 <xsl:value-of select="format-number(., '#,##0.00')"/>
                              </fo:inline>
                           </xsl:for-each>
                        </fo:block>
                     </fo:table-cell>
                  </fo:table-row>
                  <fo:table-row>
                     <fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                        <fo:block>
                           <fo:inline font-size="9px">2. Publication Costs</fo:inline>
                        </fo:block>
                     </fo:table-cell>
                     <fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                        <fo:block>
                           <xsl:for-each select="RR_MP_Budget:PublicationCosts">
                              <fo:inline font-size="9px">
                                 <xsl:value-of select="format-number(., '#,##0.00')"/>
                              </fo:inline>
                           </xsl:for-each>
                        </fo:block>
                     </fo:table-cell>
                  </fo:table-row>
                  <fo:table-row>
                     <fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                        <fo:block>
                           <fo:inline font-size="9px">3. Consultant Services</fo:inline>
                        </fo:block>
                     </fo:table-cell>
                     <fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                        <fo:block>
                           <xsl:for-each select="RR_MP_Budget:ConsultantServices">
                              <fo:inline font-size="9px">
                                 <xsl:value-of select="format-number(., '#,##0.00')"/>
                              </fo:inline>
                           </xsl:for-each>
                        </fo:block>
                     </fo:table-cell>
                  </fo:table-row>
                  <fo:table-row>
                     <fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                        <fo:block>
                           <fo:inline font-size="9px">4. ADP/Computer Services</fo:inline>
                        </fo:block>
                     </fo:table-cell>
                     <fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                        <fo:block>
                           <xsl:for-each select="RR_MP_Budget:ADPComputerServices">
                              <fo:inline font-size="9px">
                                 <xsl:value-of select="format-number(., '#,##0.00')"/>
                              </fo:inline>
                           </xsl:for-each>
                        </fo:block>
                     </fo:table-cell>
                  </fo:table-row>
                  <fo:table-row>
                     <fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                        <fo:block>
                           <fo:inline font-size="9px">5. Subawards/Consortium/Contractual Costs</fo:inline>
                        </fo:block>
                     </fo:table-cell>
                     <fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                        <fo:block>
                           <xsl:for-each select="RR_MP_Budget:SubawardConsortiumContractualCosts">
                              <fo:inline font-size="9px">
                                 <xsl:value-of select="format-number(., '#,##0.00')"/>
                              </fo:inline>
                           </xsl:for-each>
                        </fo:block>
                     </fo:table-cell>
                  </fo:table-row>
                  <fo:table-row>
                     <fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                        <fo:block>
                           <fo:inline font-size="9px">6. Equipment or Facility Rental/User Fees</fo:inline>
                        </fo:block>
                     </fo:table-cell>
                     <fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                        <fo:block>
                           <xsl:for-each select="RR_MP_Budget:EquipmentRentalFee">
                              <fo:inline font-size="9px">
                                 <xsl:value-of select="format-number(., '#,##0.00')"/>
                              </fo:inline>
                           </xsl:for-each>
                        </fo:block>
                     </fo:table-cell>
                  </fo:table-row>
                  <fo:table-row>
                     <fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                        <fo:block>
                           <fo:inline font-size="9px">7. Alterations and Renovations</fo:inline>
                        </fo:block>
                     </fo:table-cell>
                     <fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                        <fo:block>
                           <xsl:for-each select="RR_MP_Budget:AlterationsRenovations">
                              <fo:inline font-size="9px">
                                 <xsl:value-of select="format-number(., '#,##0.00')"/>
                              </fo:inline>
                           </xsl:for-each>
                        </fo:block>
                     </fo:table-cell>
                  </fo:table-row>
                     <xsl:for-each select="RR_MP_Budget:Other">
                        <fo:table-row>
                           <fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                              <fo:block>
                                 <fo:inline font-size="9px">
                                    <xsl:value-of select="position()+7"/>.&#160;</fo:inline>
                                 <xsl:for-each select="RR_MP_Budget:Description">
                                    <fo:inline font-size="9px">
                                       <xsl:apply-templates/>
                                    </fo:inline>
                                 </xsl:for-each>
                              </fo:block>
                           </fo:table-cell>
                           <fo:table-cell line-height="9pt" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                              <fo:block>
                                 <xsl:for-each select="RR_MP_Budget:Cost">
                                    <fo:inline font-size="9px">
                                       <xsl:value-of select="format-number(., '#,##0.00')"/>
                                    </fo:inline>
                                 </xsl:for-each>
                              </fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                     </xsl:for-each>

               </xsl:for-each>
               <fo:table-row>
                  <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">Total Other Direct Costs</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" border-top-color="black" border-top-style="solid" border-top-width="1px">
                     <fo:block>
                        <xsl:for-each select="RR_MP_Budget:OtherDirectCosts/RR_MP_Budget:TotalOtherDirectCost">
                           <fo:inline font-size="9px" font-weight="bold">
                              <xsl:value-of select="format-number(., '#,##0.00')"/>
                           </fo:inline>
                        </xsl:for-each>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
            </fo:table-body>
         </fo:table>
         <fo:block>
            <fo:leader leader-pattern="space"/>
         </fo:block>
         <fo:table border-style="solid" border-color="black" width="7.7in" space-before.optimum="1pt" space-after.optimum="2pt">
            <fo:table-column column-width="6.3in"/>
            <fo:table-column column-width="1.4in"/>
            <fo:table-body>
               <fo:table-row>
                  <fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">G. Direct Costs</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <fo:inline font-weight="bold" font-size="9px">Funds Requested (&#36;)*</fo:inline>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <fo:table-row>
                  <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">Total Direct Costs (A thru F)</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <xsl:for-each select="RR_MP_Budget:DirectCosts">
                           <fo:inline font-size="9px" font-weight="bold">
                              <xsl:value-of select="format-number(., '#,##0.00')"/>
                           </fo:inline>
                        </xsl:for-each>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
            </fo:table-body>
         </fo:table>
         <fo:block>
            <fo:leader leader-pattern="space"/>
         </fo:block>
         <fo:table border-style="solid" border-color="black" width="7.7in" space-before.optimum="1pt" space-after.optimum="2pt">
            <fo:table-column column-width="3.4in"/>
            <fo:table-column column-width="1.45in"/>
            <fo:table-column column-width="1.45in"/>
            <fo:table-column column-width="1.4in"/>
            <fo:table-header>
               <fo:table-row>
                  <fo:table-cell number-columns-spanned="4" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">H. Indirect Costs</fo:inline>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <fo:table-row>
                  <fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="left">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">Indirect Cost Type</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="right">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">Indirect Cost Rate (%)</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="center">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">Indirect Cost Base ($)</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="right">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">Funds Requested (&#36;)*</fo:inline>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
            </fo:table-header>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block>

                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
               <xsl:for-each select="RR_MP_Budget:IndirectCosts">
                  <xsl:for-each select="RR_MP_Budget:IndirectCost">
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                           <fo:block>
                              <fo:inline font-size="9px">
                                 <xsl:value-of select="position()"/>.&#160;</fo:inline>
                              <xsl:for-each select="RR_MP_Budget:CostType">
                                 <fo:inline font-size="9px">
                                    <xsl:apply-templates/>
                                 </fo:inline>
                              </xsl:for-each>
                           </fo:block>
                        </fo:table-cell>
                        <fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="right">
                           <fo:block>
                              <xsl:for-each select="RR_MP_Budget:Rate">
                                 <fo:inline font-size="9px">
                                    <xsl:apply-templates/>
                                 </fo:inline>
                              </xsl:for-each>
                           </fo:block>
                        </fo:table-cell>
                        <fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="right">
                           <fo:block>
                              <xsl:for-each select="RR_MP_Budget:Base">
                                 <fo:inline font-size="9px">
                                    <xsl:value-of select="format-number(., '#,##0.00')"/>
                                 </fo:inline>
                              </xsl:for-each>
                           </fo:block>
                        </fo:table-cell>
                        <fo:table-cell line-height="9pt" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="right">
                           <fo:block>
                              <xsl:for-each select="RR_MP_Budget:FundRequested">
                                 <fo:inline font-size="9px">
                                    <xsl:value-of select="format-number(., '#,##0.00')"/>
                                 </fo:inline>
                              </xsl:for-each>
                           </fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                  </xsl:for-each>
               </xsl:for-each>
               <fo:table-row>
                  <fo:table-cell number-columns-spanned="3" text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">Total Indirect Costs</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" border-top-color="black" border-top-style="solid" border-top-width="1px">
                     <fo:block>
                        <fo:inline font-weight="bold" font-size="9px">
                           <xsl:choose>
                              <xsl:when test="RR_MP_Budget:IndirectCosts/RR_MP_Budget:TotalIndirectCosts">
                                 <xsl:value-of select="format-number(RR_MP_Budget:IndirectCosts/RR_MP_Budget:TotalIndirectCosts, '#,##0.00')"/>
                              </xsl:when>
                              <!--<xsl:otherwise>0.00</xsl:otherwise>-->
                           </xsl:choose>
                        </fo:inline>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <fo:table-row>
                  <fo:table-cell hyphenate="true" language="en" number-columns-spanned="4" text-align="left" padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="0pt" display-align="before">
                      <fo:block>
                     <fo:table>
                     <fo:table-column column-width="proportional-column-width(40)"/>
                     <fo:table-column column-width="proportional-column-width(60)"/>
                     <fo:table-body>
                     <fo:table-row>
                              <fo:table-cell>
                                 <fo:block>
                                    <fo:block>
                                       <fo:inline font-size="9px" font-weight="bold">Cognizant Federal Agency</fo:inline>
                                       <fo:inline font-size="9px">&#160; </fo:inline>
                                    </fo:block>
                                    <fo:block>
                                       <fo:inline font-size="9px" font-weight="normal">(Agency Name, POC Name, and POC Phone Number)</fo:inline>
                                    </fo:block>
                                 </fo:block>
                              </fo:table-cell>
                              <fo:table-cell>
                                 <fo:block>
                                    <xsl:for-each select="RR_MP_Budget:CognizantFederalAgency">
                                       <fo:inline font-size="9px">
                                          <xsl:apply-templates/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                        </fo:table-body>
                     </fo:table>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
            </fo:table-body>
         </fo:table>
         <fo:block>
            <fo:leader leader-pattern="space"/>
         </fo:block>
         <fo:table border-style="solid" border-color="black" width="7.7in" space-before.optimum="1pt" space-after.optimum="2pt">
            <fo:table-column column-width="6.3in"/>
            <fo:table-column column-width="1.4in"/>
            <fo:table-body>
               <fo:table-row>
                  <fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">I. Total Direct and Indirect Costs</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <fo:inline font-weight="bold" font-size="9px">Funds Requested (&#36;)*</fo:inline>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <fo:table-row>
                  <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">Total Direct and Indirect Institutional Costs (G + H)</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <xsl:for-each select="RR_MP_Budget:TotalCosts">
                           <fo:inline font-size="9px" font-weight="bold">
                              <xsl:value-of select="format-number(., '#,##0.00')"/>
                           </fo:inline>
                        </xsl:for-each>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
            </fo:table-body>
         </fo:table>
         <fo:block>
            <fo:leader leader-pattern="space"/>
         </fo:block>
         <fo:table border-style="solid" border-color="black" width="7.7in" space-before.optimum="1pt" space-after.optimum="2pt">
            <fo:table-column column-width="6.3in"/>
            <fo:table-column column-width="1.4in"/>
            <fo:table-body>
               <fo:table-row>
                  <fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">J. Fee</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <fo:inline font-weight="bold" font-size="9px">Funds Requested (&#36;)*</fo:inline>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <fo:table-row>
                  <fo:table-cell text-align="left" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block>
                        <fo:inline font-size="9px">&#160;                                                </fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right" padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before">
                     <fo:block font-weight="bold">
                        <xsl:for-each select="RR_MP_Budget:Fee">
                           <fo:inline font-size="9px">
                              <xsl:value-of select="format-number(., '#,##0.00')"/>
                           </fo:inline>
                        </xsl:for-each>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
            </fo:table-body>
         </fo:table>
         <fo:block>
            <fo:leader leader-pattern="space"/>
         </fo:block>
         <fo:table border-style="solid" border-color="black" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-body>
               <fo:table-row>
                  <fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                     <fo:block>
                        <fo:inline font-size="9px" font-weight="bold">K. Budget Justification*</fo:inline>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start">
                     <fo:block>
                        <fo:inline font-size="9px">File Name: </fo:inline>
                        <xsl:for-each select="../RR_MP_Budget:BudgetJustificationAttachment">
                           <xsl:for-each select="att:FileName">
                              <fo:inline font-size="9px">
                                 <xsl:apply-templates/>
                              </fo:inline>
                           </xsl:for-each>
                        </xsl:for-each>
                     </fo:block>
                  </fo:table-cell>
                  <fo:table-cell padding-start="1pt" padding-end="1pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="start">
                     <fo:block color="white">
                        <fo:inline font-size="9px">Mime Type: </fo:inline>
                        <xsl:for-each select="../RR_MP_Budget:BudgetJustificationAttachment">
                           <xsl:for-each select="att:MimeType">
                              <fo:inline font-size="9px">
                                 <xsl:apply-templates/>
                              </fo:inline>
                           </xsl:for-each>
                        </xsl:for-each>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <fo:table-row>
                  <fo:table-cell>
                     <fo:block/>
                  </fo:table-cell>
                  <fo:table-cell number-columns-spanned="2">
                     <fo:block>
                        <fo:inline font-size="9px">(Only attach one file.)</fo:inline>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
            </fo:table-body>
         </fo:table>
         <fo:block font-size="8px" padding-before="4pt">RESEARCH &amp; RELATED Budget {F-K} (Funds Requested)</fo:block>
      </fo:block>
   </xsl:template>
   <!--===========================End Single Year Template===========-->
   <!--========================== date template =================-->
   <xsl:template name="formatDate">
      <xsl:param name="value"/>
      <xsl:value-of select="format-number(substring($value,6,2), '00')"/>
      <xsl:text>-</xsl:text>
      <xsl:value-of select="format-number(substring($value,9,2), '00')"/>
      <xsl:text>-</xsl:text>
      <xsl:value-of select="format-number(substring($value,1,4), '0000')"/>
   </xsl:template>
</xsl:stylesheet>
