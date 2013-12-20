<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xmlns:footer="http://apply.grants.gov/system/Footer-V1.0"
xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:SF424A="http://apply.grants.gov/forms/SF424A-V1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <xsl:variable name="fo:layout-master-set">
        <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="8.5in" page-width="11in" margin-left="0.5in" margin-right="0.5in">
                <fo:region-body margin-top="0.4in" margin-bottom="0.4in" font-family="Helvetica,Times,Courier" font-size="14pt" />
                <fo:region-after extent=".5in"/>
            </fo:simple-page-master>
        </fo:layout-master-set>
    </xsl:variable>


 <xsl:template match="SF424A:BudgetInformation">
        <fo:root>
            <xsl:copy-of select="$fo:layout-master-set" />
            <fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
                <fo:static-content flow-name="xsl-region-after">
               
                <fo:block>
																						<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
																						</fo:inline>
																					</fo:block>
                </fo:static-content>

                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
<fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
   <fo:table-column column-width="proportional-column-width(2)"/>
   <fo:table-column column-width="proportional-column-width(6)"/>
   <fo:table-column column-width="proportional-column-width(2)"/>
   <fo:table-body>
      <fo:table-row>
         <fo:table-cell font-size="8.5pt" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="1pt" display-align="center" border-style="solid" border-width="0pt" border-color="white">
             <fo:block/>
         </fo:table-cell>
         <fo:table-cell font-size="8.5pt" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="1pt" display-align="center" border-style="solid" border-width="0pt" border-color="white">
            <fo:block font-size="8.5pt">
               <fo:inline font-size="10pt" font-weight="bold">BUDGET INFORMATION - Non-Construction Programs</fo:inline>
            </fo:block>
         </fo:table-cell>
         <fo:table-cell font-size="8.5pt" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="1pt" display-align="center" border-style="solid" border-width="0pt" border-color="white">
            <fo:block text-align="right" font-size="7.0pt">OMB Approval No. 4040-0006</fo:block>
            <fo:block text-align="right" font-size="7.0pt">Expiration Date 04/30/2008</fo:block>
         </fo:table-cell>
      </fo:table-row>
   </fo:table-body>
</fo:table>

                            <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell background-color="silver" font-size="8.5pt" number-columns-spanned="7" text-align="center" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="0pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:inline font-size="8.5pt" font-weight="bold">SECTION A - BUDGET SUMMARY</fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" text-align="center" number-rows-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:block font-size="8.5pt">
                                                        <fo:inline font-size="8.5pt">Grant Program Function or Activity (a)</fo:inline>
                                                    </fo:block>
                                                </fo:block>
                                             </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="center" number-rows-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:block font-size="8.5pt">Catalog of Federal Domestic Assistance Number (b)</fo:block>
                                                </fo:block>
                                                </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="center" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>Estimated Unobligated Funds</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="center" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>New or Revised Budget</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:block font-size="8.5pt" text-align="center">Federal (c)</fo:block>
                                                </fo:block>
                                                </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:block font-size="8.5pt" text-align="center">Non-Federal (d)</fo:block>
                                                </fo:block>
                                                </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:block font-size="8.5pt" text-align="center">Federal (e)</fo:block>
                                                </fo:block>
                                                </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:block>Non-Federal (f)</fo:block>
                                                </fo:block>
                                                </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:block font-size="8.5pt" text-align="center">Total (g)</fo:block>
                                                </fo:block>
                                                </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
    <xsl:for-each select="SF424A:BudgetSummary">
        <xsl:call-template name="printSummaryLine">
           <xsl:with-param name="number">1</xsl:with-param>
        </xsl:call-template>
    <xsl:call-template name="printSummaryLine">
       <xsl:with-param name="number">2</xsl:with-param>
    </xsl:call-template>
    <xsl:call-template name="printSummaryLine">
       <xsl:with-param name="number">3</xsl:with-param>
    </xsl:call-template>
    <xsl:call-template name="printSummaryLine">
       <xsl:with-param name="number">4</xsl:with-param>
    </xsl:call-template>
 </xsl:for-each>
                                     <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>5.&#160;&#160;&#160;&#160; Totals</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block/>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetSummary/SF424A:SummaryTotals/SF424A:BudgetFederalEstimatedUnobligatedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetSummary/SF424A:SummaryTotals/SF424A:BudgetNonFederalEstimatedUnobligatedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetSummary/SF424A:SummaryTotals/SF424A:BudgetFederalNewOrRevisedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetSummary/SF424A:SummaryTotals/SF424A:BudgetNonFederalNewOrRevisedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetSummary/SF424A:SummaryTotals/SF424A:BudgetTotalNewOrRevisedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell background-color="silver" font-size="8.5pt" font-weight="bold" text-align="center" number-columns-spanned="7" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>SECTION B - BUDGET CATEGORIES</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="2" number-rows-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>6. Object Class Categories</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" font-weight="bold" text-align="center" number-columns-spanned="4" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>GRANT PROGRAM, FUNCTION OR ACTIVITY</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" font-weight="bold" text-align="center" number-rows-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:block font-size="8.5pt" font-weight="bold" text-align="center">Total</fo:block>
                                                </fo:block>(5)</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="2pt" number-columns-spanned="4" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white" hyphenate="true">
                                                                <fo:block>
                                                                    <fo:inline font-size="8.5pt">(1) <xsl:value-of select="SF424A:BudgetSummary/SF424A:SummaryLineItem[1]/@SF424A:activityTitle"/></fo:inline>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white" hyphenate="true">
                                                                <fo:block>
                                                                    <fo:inline font-size="8.5pt">(2) <xsl:value-of select="SF424A:BudgetSummary/SF424A:SummaryLineItem[2]/@SF424A:activityTitle"/></fo:inline>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white" hyphenate="true">
                                                                <fo:block>
                                                                    <fo:inline font-size="8.5pt">(3) <xsl:value-of select="SF424A:BudgetSummary/SF424A:SummaryLineItem[3]/@SF424A:activityTitle"/></fo:inline>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white" hyphenate="true">
                                                                <fo:block>
                                                                    <fo:inline font-size="8.5pt">(4) <xsl:value-of select="SF424A:BudgetSummary/SF424A:SummaryLineItem[4]/@SF424A:activityTitle"/></fo:inline>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    
























































































                                    
                                                      <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>&#160;&#160;&#160;&#160; a. Personnel</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="0pt" number-columns-spanned="4" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black" hyphenate="true">
                                            <fo:block>
                                                <xsl:for-each select="SF424A:BudgetCategories">
                                                    
                                                        <xsl:if test="position()=1">
                                                            <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                
                                                                    <fo:table-column />
                                                             <fo:table-column />
                                                             <fo:table-column />
                                                             <fo:table-column />

                                                                <fo:table-body>
                                                                    <fo:table-row>
                                                                        
                                                                            <fo:table-cell border-start-color="black" border-end-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[1]/SF424A:BudgetPersonnelRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                            <fo:table-cell border-start-color="black" border-end-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[2]/SF424A:BudgetPersonnelRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
<fo:table-cell border-start-color="black" border-end-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                       <xsl:for-each select="SF424A:CategorySet[3]/SF424A:BudgetPersonnelRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />

                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
<fo:table-cell border-start-color="black" border-end-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                     <xsl:for-each select="SF424A:CategorySet[4]/SF424A:BudgetPersonnelRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
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
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                
                                            </fo:block>
                                        </fo:table-cell>
                                        
                                        
                                        
                                        
                                        
                                        
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetCategories/SF424A:CategoryTotals/SF424A:BudgetPersonnelRequestedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                     			 </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                 
                                 
                                 <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>&#160;&#160;&#160;&#160; b. Fringe Benefits</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="0pt" number-columns-spanned="4" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <xsl:for-each select="SF424A:BudgetCategories">
                                                    
                                                        <xsl:if test="position()=1">
                                                            <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                
                                                                    <fo:table-column />
                                                              <fo:table-column /> <fo:table-column /> <fo:table-column />
                                                                <fo:table-body>
                                                                    <fo:table-row>
                                                                        
                                                                            <fo:table-cell border-start-color="black" border-end-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                <xsl:for-each select="SF424A:CategorySet[1]/SF424A:BudgetFringeBenefitsRequestedAmount">
                                                                                                           <xsl:value-of select="format-number(., '$#,##0.00')"></xsl:value-of>                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                        
                                                                        <fo:table-cell border-start-color="black" border-end-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                <xsl:for-each select="SF424A:CategorySet[2]/SF424A:BudgetFringeBenefitsRequestedAmount">
                                                                                                           <xsl:value-of select="format-number(., '$#,##0.00')"></xsl:value-of>                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>


<fo:table-cell border-start-color="black" border-end-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                <xsl:for-each select="SF424A:CategorySet[3]/SF424A:BudgetFringeBenefitsRequestedAmount">
                                                                                                           <xsl:value-of select="format-number(., '$#,##0.00')"></xsl:value-of>                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
<fo:table-cell border-start-color="black" border-end-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                <xsl:for-each select="SF424A:CategorySet[4]/SF424A:BudgetFringeBenefitsRequestedAmount">
                                                                                                           <xsl:value-of select="format-number(., '$#,##0.00')"></xsl:value-of>                                                                                                        </xsl:for-each>
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
                                                        </xsl:if>
                                                    </xsl:for-each>
                                          
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
          <xsl:for-each select="SF424A:BudgetCategories/SF424A:CategoryTotals/SF424A:BudgetFringeBenefitsRequestedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                     			 </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    
                                    
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>&#160;&#160;&#160;&#160; c. Travel</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="0pt" number-columns-spanned="4" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <xsl:for-each select="SF424A:BudgetCategories">
                                                    
                                                        <xsl:if test="position()=1">
                                                            <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                
                                                                    <fo:table-column /><fo:table-column /><fo:table-column /><fo:table-column />
                                                               
                                                                <fo:table-body>
                                                                    <fo:table-row>
                                                                        
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[1]/SF424A:BudgetTravelRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[2]/SF424A:BudgetTravelRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
<fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[3]/SF424A:BudgetTravelRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
<fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[4]/SF424A:BudgetTravelRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
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
                                                        </xsl:if>
                                                    </xsl:for-each>
                                             
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetCategories/SF424A:CategoryTotals/SF424A:BudgetTravelRequestedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                     			 </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                   
                                   
                                   <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>&#160;&#160;&#160;&#160; d. Equipment</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="0pt" number-columns-spanned="4" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <xsl:for-each select="SF424A:BudgetCategories">
                                                    
                                                        <xsl:if test="position()=1">
                                                            <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                
                                                                    <fo:table-column /><fo:table-column /><fo:table-column /><fo:table-column />
                                                                
                                                                <fo:table-body>
                                                                    <fo:table-row>
                                                                        
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[1]/SF424A:BudgetEquipmentRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[2]/SF424A:BudgetEquipmentRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
<fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[3]/SF424A:BudgetEquipmentRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
<fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[4]/SF424A:BudgetEquipmentRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
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
                                                        </xsl:if>
                                                    </xsl:for-each>
                                              
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetCategories/SF424A:CategoryTotals/SF424A:BudgetEquipmentRequestedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                     			 </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                   
                                    
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>&#160;&#160;&#160;&#160; e. Supplies</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="0pt" number-columns-spanned="4" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <xsl:for-each select="SF424A:BudgetCategories">
                                                    
                                                        <xsl:if test="position()=1">
                                                            <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                
                                                                    <fo:table-column /> <fo:table-column /> <fo:table-column /> <fo:table-column />
                                                               
                                                                <fo:table-body>
                                                                    <fo:table-row>
                                                                        
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[1]/SF424A:BudgetSuppliesRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                        <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[2]/SF424A:BudgetSuppliesRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                            
                                                                         <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[3]/SF424A:BudgetSuppliesRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
     <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[4]/SF424A:BudgetSuppliesRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
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
                                                        </xsl:if>
                                                    </xsl:for-each>
                                               
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetCategories/SF424A:CategoryTotals/SF424A:BudgetSuppliesRequestedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                     			 </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                  
                                  
                                  <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>&#160;&#160;&#160;&#160; f. Contractual</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="0pt" number-columns-spanned="4" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <xsl:for-each select="SF424A:BudgetCategories">
                                                    
                                                        <xsl:if test="position()=1">
                                                            <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                
                                                                    <fo:table-column /><fo:table-column /><fo:table-column /><fo:table-column />
                                                        
                                                                <fo:table-body>
                                                                    <fo:table-row>
                                                                        
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[1]/SF424A:BudgetContractualRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                            
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[2]/SF424A:BudgetContractualRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[3]/SF424A:BudgetContractualRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
<fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[4]/SF424A:BudgetContractualRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
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
                                                        </xsl:if>
                                                    </xsl:for-each>
                                             
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetCategories/SF424A:CategoryTotals/SF424A:BudgetContractualRequestedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                     			 </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


<fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>&#160;&#160;&#160;&#160; g. Construction</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="0pt" number-columns-spanned="4" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <xsl:for-each select="SF424A:BudgetCategories">
                                                    
                                                        <xsl:if test="position()=1">
                                                            <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                
                                                                    <fo:table-column /><fo:table-column /><fo:table-column /><fo:table-column />
                                                       
                                                                <fo:table-body>
                                                                    <fo:table-row>
                                                                        
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[1]/SF424A:BudgetConstructionRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[2]/SF424A:BudgetConstructionRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
<fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[3]/SF424A:BudgetConstructionRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
<fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[4]/SF424A:BudgetConstructionRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
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
                                                        </xsl:if>
                                                    </xsl:for-each>
                           
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetCategories/SF424A:CategoryTotals/SF424A:BudgetConstructionRequestedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                     			 </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    
                                    
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>&#160;&#160;&#160;&#160; h. Other</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="0pt" number-columns-spanned="4" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <xsl:for-each select="SF424A:BudgetCategories">
                                                    
                                                        <xsl:if test="position()=1">
                                                            <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                
                                                                    <fo:table-column /><fo:table-column /><fo:table-column /><fo:table-column />
                                                               
                                                                <fo:table-body>
                                                                    <fo:table-row>
                                                                        
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[1]/SF424A:BudgetOtherRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                           <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[2]/SF424A:BudgetOtherRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[3]/SF424A:BudgetOtherRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
<fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[4]/SF424A:BudgetOtherRequestedAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
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
                                                        </xsl:if>
                                                    </xsl:for-each>
                                     
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetCategories/SF424A:CategoryTotals/SF424A:BudgetOtherRequestedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                     			 </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    
                                    
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>&#160;&#160;&#160;&#160; i. Total Direct Charges (<fo:inline font-style="italic">sum of 6a-6h</fo:inline>)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="0pt" number-columns-spanned="4" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <xsl:for-each select="SF424A:BudgetCategories">
                                                    
                                                        <xsl:if test="position()=1">
                                                            <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                
                                                                    <fo:table-column /><fo:table-column /><fo:table-column /><fo:table-column />
                                                             
                                                                <fo:table-body>
                                                                    <fo:table-row>
                                                                        
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[1]/SF424A:BudgetTotalDirectChargesAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[2]/SF424A:BudgetTotalDirectChargesAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
<fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[3]/SF424A:BudgetTotalDirectChargesAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
<fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[4]/SF424A:BudgetTotalDirectChargesAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
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
                                                        </xsl:if>
                                                    </xsl:for-each>
                                             
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetCategories/SF424A:CategoryTotals/SF424A:BudgetTotalDirectChargesAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                     			 </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    
                                    
               
<fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>&#160;&#160;&#160;&#160; j. Indirect Charges</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="0pt" number-columns-spanned="4" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <xsl:for-each select="SF424A:BudgetCategories">
                                                    
                                                        <xsl:if test="position()=1">
                                                            <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                
                                                                    <fo:table-column /><fo:table-column /><fo:table-column /><fo:table-column />
                                                              
                                                                <fo:table-body>
                                                                    <fo:table-row>
                                                                        
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[1]/SF424A:BudgetIndirectChargesAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[2]/SF424A:BudgetIndirectChargesAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[3]/SF424A:BudgetIndirectChargesAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[4]/SF424A:BudgetIndirectChargesAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
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
                                                        </xsl:if>
                                                    </xsl:for-each>
                                               
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetCategories/SF424A:CategoryTotals/SF424A:BudgetIndirectChargesAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                     			 </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    
                                    
                                    
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>&#160;&#160;&#160;&#160; k. TOTALS (<fo:inline font-style="italic">sum of 6i and 6j</fo:inline>)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="0pt" number-columns-spanned="4" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <xsl:for-each select="SF424A:BudgetCategories">
                                                    
                                                        <xsl:if test="position()=1">
                                                            <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                
                                                                    <fo:table-column /> <fo:table-column /> <fo:table-column /> <fo:table-column />
                                                                
                                                                <fo:table-body>
                                                                    <fo:table-row>
                                                                        
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[1]/SF424A:BudgetTotalAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[2]/SF424A:BudgetTotalAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[3]/SF424A:BudgetTotalAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[4]/SF424A:BudgetTotalAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
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
                                                        </xsl:if>
                                                    </xsl:for-each>
                                              
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetCategories/SF424A:CategoryTotals/SF424A:BudgetTotalAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                     			 </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    
                                    
                                    
                                    <fo:table-row>
                                        <fo:table-cell background-color="silver" font-size="8.5pt" number-columns-spanned="7" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block />
                                        </fo:table-cell>
                                    </fo:table-row>


<fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>7. Program Income</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="0pt" number-columns-spanned="4" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <xsl:for-each select="SF424A:BudgetCategories">
                                                    
                                                        <xsl:if test="position()=1">
                                                            <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                
                                                                    <fo:table-column /><fo:table-column /><fo:table-column /><fo:table-column />
                                                              
                                                                <fo:table-body>
                                                                    <fo:table-row>
                                                                        
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[1]/SF424A:ProgramIncomeAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[2]/SF424A:ProgramIncomeAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[3]/SF424A:ProgramIncomeAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                                                        </xsl:for-each>
                                                                                                    </fo:block>
                                                                                                </fo:table-cell>
                                                                                            </fo:table-row>
                                                                                        </fo:table-body>
                                                                                    </fo:table>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                            <fo:table-cell border-start-color="black" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:table-column />
                                                                                        <fo:table-column />
                                                                                        <fo:table-body>
                                                                                            <fo:table-row>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block></fo:block>
                                                                                                </fo:table-cell>
                                                                                                <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="1pt" padding-end="2pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                                                                        <xsl:for-each select="SF424A:CategorySet[4]/SF424A:ProgramIncomeAmount">
                                                                                                            <xsl:value-of select="format-number(., '$#,##0.00')" />
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
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetCategories">
                                                                        <xsl:for-each select="SF424A:CategoryTotals">
                                                                            <xsl:for-each select="SF424A:ProgramIncomeAmount">
                                                                                <xsl:value-of select="format-number(., '$#,##0.00')" />
                                                                            </xsl:for-each>
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
                            <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                <fo:table-column />
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell font-size="7.0pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block>Standard From 424A (Rev. 7-97)</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="7.0pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block>Prescribed by OMB Circular A-102</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                            <fo:block break-after="page">
                                <xsl:text>&#xA;</xsl:text>
                            </fo:block>
                            <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell background-color="silver" font-size="8.5pt" font-weight="bold" text-align="center" number-columns-spanned="6" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>SECTION C - NON-FEDERAL RESOURCES</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" text-align="center" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>(a) Grant Program</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>(b) Applicant</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>(c) State</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>(d) Other Sources</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>(e) TOTALS</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                               
                               
                                    
                                    
<xsl:for-each select="SF424A:NonFederalResources" >                                 
   <xsl:choose>
      <xsl:when test="SF424A:ResourceLineItem[1]">
         <xsl:for-each select="SF424A:ResourceLineItem[1]">
            <xsl:call-template name="printNonFedLineSummary">
               <xsl:with-param name="number">1</xsl:with-param>
            </xsl:call-template>
         </xsl:for-each>
      </xsl:when>
      <xsl:otherwise>
         <xsl:call-template name="printNonFedLineSummary">
            <xsl:with-param name="number">1</xsl:with-param>
         </xsl:call-template>
      </xsl:otherwise>
   </xsl:choose>
   <xsl:choose>
      <xsl:when test="SF424A:ResourceLineItem[2]">
         <xsl:for-each select="SF424A:ResourceLineItem[2]">
            <xsl:call-template name="printNonFedLineSummary">
               <xsl:with-param name="number">2</xsl:with-param>
            </xsl:call-template>
         </xsl:for-each>
      </xsl:when>
      <xsl:otherwise>
         <xsl:call-template name="printNonFedLineSummary">
            <xsl:with-param name="number">2</xsl:with-param>
         </xsl:call-template>
      </xsl:otherwise>
   </xsl:choose>
   <xsl:choose>
      <xsl:when test="SF424A:ResourceLineItem[3]">
         <xsl:for-each select="SF424A:ResourceLineItem[3]">
            <xsl:call-template name="printNonFedLineSummary">
               <xsl:with-param name="number">3</xsl:with-param>
            </xsl:call-template>
         </xsl:for-each>
      </xsl:when>
      <xsl:otherwise>
         <xsl:call-template name="printNonFedLineSummary">
            <xsl:with-param name="number">3</xsl:with-param>
         </xsl:call-template>
      </xsl:otherwise>
   </xsl:choose>
   <xsl:choose>
      <xsl:when test="SF424A:ResourceLineItem[4]">
         <xsl:for-each select="SF424A:ResourceLineItem[4]">
            <xsl:call-template name="printNonFedLineSummary">
               <xsl:with-param name="number">4</xsl:with-param>
            </xsl:call-template>
         </xsl:for-each>
      </xsl:when>
      <xsl:otherwise>
         <xsl:call-template name="printNonFedLineSummary">
            <xsl:with-param name="number">4</xsl:with-param>
         </xsl:call-template>
      </xsl:otherwise>
   </xsl:choose>
</xsl:for-each>   

                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>12. TOTAL (sum of lines 8-11)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:NonFederalResources/SF424A:ResourceTotals/SF424A:BudgetApplicantContributionAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:NonFederalResources/SF424A:ResourceTotals/SF424A:BudgetStateContributionAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:NonFederalResources/SF424A:ResourceTotals/SF424A:BudgetOtherContributionAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:NonFederalResources/SF424A:ResourceTotals/SF424A:BudgetTotalContributionAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell background-color="silver" font-size="8.5pt" font-weight="bold" text-align="center" number-columns-spanned="6" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>SECTION D - FORECASTED CASH NEEDS</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-rows-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>13. Federal</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" font-weight="bold" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>Total for 1st Year</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" font-weight="bold" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>1st Quarter</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" font-weight="bold" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>2nd Quarter</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" font-weight="bold" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>3rd Quarter</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" font-weight="bold" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>4th Quarter</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetForecastedCashNeeds/SF424A:BudgetFirstYearAmounts/SF424A:BudgetFederalForecastedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetForecastedCashNeeds/SF424A:BudgetFirstQuarterAmounts/SF424A:BudgetFederalForecastedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetForecastedCashNeeds/SF424A:BudgetSecondQuarterAmounts/SF424A:BudgetFederalForecastedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetForecastedCashNeeds/SF424A:BudgetThirdQuarterAmounts/SF424A:BudgetFederalForecastedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetForecastedCashNeeds/SF424A:BudgetFourthQuarterAmounts/SF424A:BudgetFederalForecastedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>14. Non-Federal</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <xsl:for-each select="SF424A:BudgetForecastedCashNeeds/SF424A:BudgetFirstYearAmounts/SF424A:BudgetNonFederalForecastedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <xsl:for-each select="SF424A:BudgetForecastedCashNeeds/SF424A:BudgetFirstQuarterAmounts/SF424A:BudgetNonFederalForecastedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <xsl:for-each select="SF424A:BudgetForecastedCashNeeds/SF424A:BudgetSecondQuarterAmounts/SF424A:BudgetNonFederalForecastedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <xsl:for-each select="SF424A:BudgetForecastedCashNeeds/SF424A:BudgetThirdQuarterAmounts/SF424A:BudgetNonFederalForecastedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <xsl:for-each select="SF424A:BudgetForecastedCashNeeds/SF424A:BudgetFourthQuarterAmounts/SF424A:BudgetNonFederalForecastedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>15. TOTAL (<fo:inline font-style="italic">sum of lines 13 and 14</fo:inline>)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetForecastedCashNeeds/SF424A:BudgetFirstYearAmounts/SF424A:BudgetTotalForecastedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetForecastedCashNeeds/SF424A:BudgetFirstQuarterAmounts/SF424A:BudgetTotalForecastedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetForecastedCashNeeds/SF424A:BudgetSecondQuarterAmounts/SF424A:BudgetTotalForecastedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetForecastedCashNeeds/SF424A:BudgetThirdQuarterAmounts/SF424A:BudgetTotalForecastedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:BudgetForecastedCashNeeds/SF424A:BudgetFourthQuarterAmounts/SF424A:BudgetTotalForecastedAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell background-color="silver" font-size="8.5pt" font-weight="bold" text-align="center" number-columns-spanned="6" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>SECTION E - BUDGET ESTIMATES OF FEDERAL FUNDS NEEDED FOR BALANCE OF THE PROJECT</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" text-align="center" number-columns-spanned="2" number-rows-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>(a) Grant Program</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="center" number-columns-spanned="4" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>FUTURE FUNDING PERIODS (Years)</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>(b) First</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>(c) Second</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>(d) Third</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>(e) Fourth</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>



<xsl:for-each select="SF424A:FederalFundsNeeded" >                                 
     <xsl:for-each select="SF424A:FundsLineItem" >                               
           </xsl:for-each>   

   <xsl:choose>
      <xsl:when test="SF424A:FundsLineItem[1]">
         <xsl:for-each select="SF424A:FundsLineItem[1]">
            <xsl:call-template name="printFundLineItem">
               <xsl:with-param name="number">1</xsl:with-param>
            </xsl:call-template>
         </xsl:for-each>
      </xsl:when>
      <xsl:otherwise>
         <xsl:call-template name="printFundLineItem">
            <xsl:with-param name="number">1</xsl:with-param>
         </xsl:call-template>
      </xsl:otherwise>
   </xsl:choose>
   <xsl:choose>
      <xsl:when test="SF424A:FundsLineItem[2]">
         <xsl:for-each select="SF424A:FundsLineItem[2]">
            <xsl:call-template name="printFundLineItem">
               <xsl:with-param name="number">2</xsl:with-param>
            </xsl:call-template>
         </xsl:for-each>
      </xsl:when>
      <xsl:otherwise>
         <xsl:call-template name="printFundLineItem">
            <xsl:with-param name="number">2</xsl:with-param>
         </xsl:call-template>
      </xsl:otherwise>
   </xsl:choose>
   <xsl:choose>
      <xsl:when test="SF424A:FundsLineItem[3]">
         <xsl:for-each select="SF424A:FundsLineItem[3]">
            <xsl:call-template name="printFundLineItem">
               <xsl:with-param name="number">3</xsl:with-param>
            </xsl:call-template>
         </xsl:for-each>
      </xsl:when>
      <xsl:otherwise>
         <xsl:call-template name="printFundLineItem">
            <xsl:with-param name="number">3</xsl:with-param>
         </xsl:call-template>
      </xsl:otherwise>
   </xsl:choose>
   <xsl:choose>
      <xsl:when test="SF424A:FundsLineItem[4]">
         <xsl:for-each select="SF424A:FundsLineItem[4]">
            <xsl:call-template name="printFundLineItem">
               <xsl:with-param name="number">4</xsl:with-param>
            </xsl:call-template>
         </xsl:for-each>
      </xsl:when>
      <xsl:otherwise>
         <xsl:call-template name="printFundLineItem">
            <xsl:with-param name="number">4</xsl:with-param>
         </xsl:call-template>
      </xsl:otherwise>
   </xsl:choose>
</xsl:for-each>  


                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>20. TOTAL (<fo:inline font-style="italic">sum of lines 16-19</fo:inline>)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:FederalFundsNeeded/SF424A:FundsTotals/SF424A:BudgetFirstYearAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:FederalFundsNeeded/SF424A:FundsTotals/SF424A:BudgetSecondYearAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:FederalFundsNeeded/SF424A:FundsTotals/SF424A:BudgetThirdYearAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block></fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="SF424A:FederalFundsNeeded/SF424A:FundsTotals/SF424A:BudgetFourthYearAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> 
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell background-color="silver" font-size="8.5pt" font-weight="bold" text-align="center" number-columns-spanned="6" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>SECTION F - OTHER BUDGET INFORMATION</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>21. Direct Charges:&#160; <xsl:value-of select="SF424A:OtherInformation/SF424A:OtherDirectChargesExplanation"/>

                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>22. Indirect Charges:&#160; <xsl:value-of select="SF424A:OtherInformation/SF424A:OtherIndirectChargesExplanation"/>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="6" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black" hyphenate="true">
                                            <fo:block>23. Remarks:&#160; <xsl:value-of select="SF424A:OtherInformation/SF424A:Remarks"/>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                <fo:block text-align="right">
                                    <fo:inline font-size="8.5pt" font-weight="bold">Standard Form 424A (rev. 7-97) Page </fo:inline>
                                    <fo:page-number font-size="8.5pt" font-weight="bold" />
                                </fo:block>
                            </fo:block>
                            <fo:block text-align="center">
                             <fo:block><fo:inline font-size="7.0pt" font-weight="bold">Authorized for Local Reproduction </fo:inline></fo:block>
                            </fo:block>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

   <xsl:template name="printSummaryLine">
      <xsl:param name="number"/>
      <xsl:choose>
         <xsl:when test="$number = 1 and SF424A:SummaryLineItem[1]">
            <xsl:for-each select="SF424A:SummaryLineItem[1]">
               <xsl:call-template name="printSummaryLine2">
                  <xsl:with-param name="number" select="$number"/>
               </xsl:call-template>
            </xsl:for-each>
         </xsl:when>
         <xsl:when test="$number = 2 and SF424A:SummaryLineItem[2]">
            <xsl:for-each select="SF424A:SummaryLineItem[2]">
               <xsl:call-template name="printSummaryLine2">
                  <xsl:with-param name="number" select="$number"/>
               </xsl:call-template>
            </xsl:for-each>
         </xsl:when>
         <xsl:when test="$number = 3 and SF424A:SummaryLineItem[3]">
            <xsl:for-each select="SF424A:SummaryLineItem[3]">
               <xsl:call-template name="printSummaryLine2">
                  <xsl:with-param name="number" select="$number"/>
               </xsl:call-template>
            </xsl:for-each>
         </xsl:when>
         <xsl:when test="$number = 4 and SF424A:SummaryLineItem[4]">
            <xsl:for-each select="SF424A:SummaryLineItem[4]">
               <xsl:call-template name="printSummaryLine2">
                  <xsl:with-param name="number" select="$number"/>
               </xsl:call-template>
            </xsl:for-each>
         </xsl:when>
         <xsl:otherwise>
            <xsl:call-template name="printSummaryLine2">
               <xsl:with-param name="number" select="$number"/>
            </xsl:call-template>
         </xsl:otherwise>
      </xsl:choose>
   </xsl:template>

   <xsl:template name="printSummaryLine2">
      <xsl:param name="number"/>
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black" hyphenate="true">
                                            <fo:block>
                                                <fo:inline font-size="8.5pt"><xsl:value-of select="$number"/>.&#160;&#160;&#160;&#160;<xsl:value-of select="@SF424A:activityTitle"/></fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="center" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                         <xsl:value-of select="SF424A:CFDANumber"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                            <fo:block/>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
<xsl:if test="SF424A:BudgetFederalEstimatedUnobligatedAmount">
                                                                           <xsl:value-of  select="format-number(SF424A:BudgetFederalEstimatedUnobligatedAmount, '$#,##0.00')" /> 
</xsl:if>
                                                                    
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                            <fo:block/>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
<xsl:if test="SF424A:BudgetNonFederalEstimatedUnobligatedAmount">
                                                                             <xsl:value-of  select="format-number(SF424A:BudgetNonFederalEstimatedUnobligatedAmount, '$#,##0.00')" />
</xsl:if>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                            <fo:block/>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
<xsl:if test="SF424A:BudgetFederalNewOrRevisedAmount">
                                                               				 <xsl:value-of  select="format-number(SF424A:BudgetFederalNewOrRevisedAmount, '$#,##0.00')" />
</xsl:if>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                            <fo:block/>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
<xsl:if test="SF424A:BudgetNonFederalNewOrRevisedAmount">
                                                         				       <xsl:value-of  select="format-number(SF424A:BudgetNonFederalNewOrRevisedAmount, '$#,##0.00')" />
</xsl:if>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                            <fo:block/>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
<xsl:choose>
   <xsl:when test="SF424A:BudgetTotalNewOrRevisedAmount">
      <xsl:value-of  select="format-number(SF424A:BudgetTotalNewOrRevisedAmount, '$#,##0.00')" />
   </xsl:when>
   <xsl:otherwise>$0.00</xsl:otherwise>
</xsl:choose>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
   </xsl:template>

   <xsl:template name="printNonFedLineSummary">
      <xsl:param name="number"/>
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black" hyphenate="true">
                                            <fo:block><xsl:value-of select="$number+7"/>.&#160;&#160;&#160;&#160;<xsl:value-of select="@SF424A:activityTitle"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block/>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block><xsl:for-each select="SF424A:BudgetApplicantContributionAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                            <fo:block/>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block><xsl:for-each select="SF424A:BudgetStateContributionAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block/>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block><xsl:for-each select="SF424A:BudgetOtherContributionAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                             <fo:block/>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
<xsl:choose>
   <xsl:when test="SF424A:BudgetTotalContributionAmount">
      <xsl:value-of  select="format-number(SF424A:BudgetTotalContributionAmount, '$#,##0.00')" />
   </xsl:when>
   <xsl:otherwise>$0.00</xsl:otherwise>
</xsl:choose>
</fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
   </xsl:template>

   <xsl:template name="printFundLineItem">
      <xsl:param name="number"/>
                                    <fo:table-row>
                                        <fo:table-cell font-size="8.5pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block><xsl:value-of select="$number+15"/>.&#160;&#160;&#160;&#160;<xsl:value-of select="@SF424A:activityTitle"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                            <fo:block/>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block><xsl:for-each select="SF424A:BudgetFirstYearAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                            <fo:block/>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block><xsl:for-each select="SF424A:BudgetSecondYearAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                            <fo:block/>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block><xsl:for-each select="SF424A:BudgetThirdYearAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="8.5pt" padding-start="3pt" padding-end="3pt" padding-before="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                            <fo:block/>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8.5pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block><xsl:for-each select="SF424A:BudgetFourthYearAmount"><xsl:value-of select="format-number(., '$#,##0.00')" /></xsl:for-each> </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
   </xsl:template>

</xsl:stylesheet>
