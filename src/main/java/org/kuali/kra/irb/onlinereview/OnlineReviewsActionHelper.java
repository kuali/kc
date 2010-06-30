/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.onlinereview;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersService;
import org.kuali.kra.irb.actions.reviewcomments.ReviewerComments;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.service.PersonService;

public class OnlineReviewsActionHelper implements Serializable {

    private static final long serialVersionUID = 1L;
    private ProtocolForm form;
    
    //new reviewer data
    private String newProtocolReviewPersonId;
    private Date newReviewDateRequested;
    private Date newReviewDateDue;
    private String newReviewDocumentDescription;
    private String newReviewExplanation;
    private String newReviewOrganizationDocumentNumber;
    
    private List<ProtocolOnlineReviewDocument> protocolOnlineReviewDocuments;
    private List<ReviewerComments> reviewerComments;
    private List<KcPerson> reviewerPersons;
    private boolean initComplete = false;

    private transient KcPersonService kcPersonService;
    
    
    /**
     * Constructs a OnlineReviewActionHelper.java.
     * @param form
     */
    public OnlineReviewsActionHelper(ProtocolForm form) {
        this.form = form;
        this.newReviewDateRequested = new Date((new java.util.Date()).getTime());
        
        init(false);
    }
    
    public void init( boolean force ) {
        if( (!initComplete || force )  ) {
            ProtocolSubmission currentSubmission = KraServiceLocator.getService(ProtocolAssignReviewersService.class).getCurrentSubmission(form.getProtocolDocument().getProtocol());
            if( currentSubmission != null ) {
                this.newReviewDateRequested = new Date((new java.util.Date()).getTime());
                protocolOnlineReviewDocuments = getProtocolOnlineReviewService()
                .getProtocolReviewDocumentsForCurrentSubmission(form.getProtocolDocument().getProtocol()); 
        
//                protocolOnlineReviewForms = new ArrayList<ProtocolOnlineReviewForm>();
//                for(ProtocolOnlineReviewDocument pReview : protocolOnlineReviewDocuments) {
//                    ProtocolOnlineReviewForm form;
//                    try {
//                        form = new ProtocolOnlineReviewForm();
//                    }
//                    catch (Exception e) {
//                        throw new RuntimeException( String.format( "Exception thrown creating new ProtocolOnlineReviewForm: %s", e.getMessage() ), e );
//                    }
//                    //form.setDocument( pReview );
//                    //protocolOnlineReviewForms.add(form);
//            
//                }
                reviewerComments = new ArrayList<ReviewerComments>();
                reviewerPersons = new ArrayList<KcPerson>();
                for (ProtocolOnlineReviewDocument pDoc : protocolOnlineReviewDocuments) {
                    reviewerComments.add(pDoc.getProtocolOnlineReview().getReviewerComments());
                    KcPerson p = KraServiceLocator.getService(KcPersonService.class).getKcPersonByPersonId(pDoc.getProtocolOnlineReview().getProtocolReviewer().getPersonId());
                    if( p!= null )
                        reviewerPersons.add( p );
                    else 
                        reviewerPersons.add( null );
                }
                initComplete = true;
            }
            
        }
    }
    
    
    
    /**
     * This method...
     * @return
     */
    public List<ProtocolOnlineReview> getCurrentProtocolOnlineReviews() {
        List<ProtocolOnlineReview> reviews = new ArrayList<ProtocolOnlineReview>();
        for( ProtocolOnlineReviewDocument doc : protocolOnlineReviewDocuments ) {
            reviews.add(doc.getProtocolOnlineReview());
        }
        
        return reviews;
    }
    
    /**
     * This method...
     * @return
     */
    public List<CommitteeMembership> getAvailableCommitteeMembersForCurrentSubmission() {
        List<CommitteeMembership> members = getProtocolOnlineReviewService()
                                            .getAvailableCommitteeMembersForCurrentSubmission(form.getProtocolDocument().getProtocol());
        return members;
    }
    
    /**
     * This method...
     * @return
     */
    public List<ProtocolOnlineReviewDocument> getProtocolOnlineReviewsForCurrentSubmission() {
        return protocolOnlineReviewDocuments;
    }
    
    /**
     * This method...
     * @return
     */
//    public List<ProtocolOnlineReviewForm> getProtocolOnlineReviewFormsForCurrentSubmission() throws Exception {
//        return protocolOnlineReviewForms;
//    }
    
    /**
     * Get a list of ReviewerComments.
     * @return
     */
    public List<ReviewerComments> getProtocolOnlineReviewsReviewCommentsList() {
        return reviewerComments;
    }

    /**
     * Gets the newReviewOrganizationDocumentNumber attribute. 
     * @return Returns the newReviewOrganizationDocumentNumber.
     */
    public String getNewReviewOrganizationDocumentNumber() {
        return newReviewOrganizationDocumentNumber;
    }

    /**
     * Sets the newReviewOrganizationDocumentNumber attribute value.
     * @param newReviewOrganizationDocumentNumber The newReviewOrganizationDocumentNumber to set.
     */
    public void setNewReviewOrganizationDocumentNumber(String newReviewOrganizationDocumentNumber) {
        this.newReviewOrganizationDocumentNumber = newReviewOrganizationDocumentNumber;
    }
    

    /**
     * Gets the newReviewDateRequested attribute. 
     * @return Returns the newReviewDateRequested.
     */
    public java.sql.Date getNewReviewDateRequested() {
        return newReviewDateRequested;
    }

    /**
     * Sets the newReviewDateRequested attribute value.
     * @param newReviewDateRequested The newReviewDateRequested to set.
     */
    public void setNewReviewDateRequested(java.sql.Date newReviewDateRequested) {
        this.newReviewDateRequested = newReviewDateRequested;
    }

    /**
     * Gets the newReviewDateDue attribute. 
     * @return Returns the newReviewDateDue.
     */
    public java.sql.Date getNewReviewDateDue() {
        return newReviewDateDue;
    }

    /**
     * Sets the newReviewDateDue attribute value.
     * @param newReviewDateDue The newReviewDateDue to set.
     */
    public void setNewReviewDateDue(java.sql.Date newReviewDateDue) {
        this.newReviewDateDue = newReviewDateDue;
    }

    /**
     * Gets the newReviewDocumentDescription attribute. 
     * @return Returns the newReviewDocumentDescription.
     */
    public String getNewReviewDocumentDescription() {
        return newReviewDocumentDescription;
    }

    /**
     * Sets the newReviewDocumentDescription attribute value.
     * @param newReviewDocumentDescription The newReviewDocumentDescription to set.
     */
    public void setNewReviewDocumentDescription(String newReviewDocumentDescription) {
        this.newReviewDocumentDescription = newReviewDocumentDescription;
    }
    
    /**
     * Gets the newReviewExplanation attribute. 
     * @return Returns the newReviewExplanation.
     */
    public String getNewReviewExplanation() {
        return newReviewExplanation;
    }

    /**
     * Sets the newReviewExplanation attribute value.
     * @param newReviewExplanation The newReviewExplanation to set.
     */
    public void setNewReviewExplanation(String newReviewExplanation) {
        this.newReviewExplanation = newReviewExplanation;
    }

    /**
     * Gets the protocolOnlineReviewDocuments attribute. 
     * @return Returns the protocolOnlineReviewDocuments.
     */
    public List<ProtocolOnlineReviewDocument> getProtocolOnlineReviewDocuments() {
        return protocolOnlineReviewDocuments;
    }

    /**
     * Sets the protocolOnlineReviewDocuments attribute value.
     * @param protocolOnlineReviewDocuments The protocolOnlineReviewDocuments to set.
     */
    public void setProtocolOnlineReviewDocuments(List<ProtocolOnlineReviewDocument> protocolOnlineReviewDocuments) {
        this.protocolOnlineReviewDocuments = protocolOnlineReviewDocuments;
    }

    private static ProtocolOnlineReviewService getProtocolOnlineReviewService() {
        return KraServiceLocator.getService(ProtocolOnlineReviewService.class);
    }

    /**
     * Gets the newProtocolReviewPersonId attribute. 
     * @return Returns the newProtocolReviewPersonId.
     */
    public String getNewProtocolReviewPersonId() {
        return newProtocolReviewPersonId;
    }

    /**
     * Sets the newProtocolReviewPersonId attribute value.
     * @param newProtocolReviewPersonId The newProtocolReviewPersonId to set.
     */
    public void setNewProtocolReviewPersonId(String newProtocolReviewPersonId) {
        this.newProtocolReviewPersonId = newProtocolReviewPersonId;
    }

    /**
     * Gets the reviewerComments attribute. 
     * @return Returns the reviewerComments.
     */
    public List<ReviewerComments> getReviewerComments() {
        return reviewerComments;
    }

    /**
     * Sets the reviewerComments attribute value.
     * @param reviewerComments The reviewerComments to set.
     */
    public void setReviewerComments(List<ReviewerComments> reviewerComments) {
        this.reviewerComments = reviewerComments;
    }

    /**
     * Gets the reviewerPersons attribute. 
     * @return Returns the reviewerPersons.
     */
    public List<KcPerson> getReviewerPersons() {
        return reviewerPersons;
    }

    /**
     * Sets the reviewerPersons attribute value.
     * @param reviewerPersons The reviewerPersons to set.
     */
    public void setReviewerPersons(List<KcPerson> reviewerPersons) {
        this.reviewerPersons = reviewerPersons;
    }
    
}

