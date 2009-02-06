/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra;

import java.util.List;

import org.kuali.kra.service.impl.versioningartifacts.SequenceOwnerImpl;

/**
 * This interface defines callback methods for assigning newly versioned 
 * SeparatelySequenceableAssociate reference(s) back to the owner
 * 
 * This is needed in an OJB environment because there's reflection offers 
 * too little information (without a lot of work) to know how the new
 * SeparatelySequenceableAssociate(s) should be assigned back to the
 * SequenceOwner.
 * 
 * For instance, suppose the following classes are defined like this:
 * 
  <pre>
  public class Foo implements SequenceOwner {
      private List<Bar> federalBars;
      private List<Bar> institutionalBars;
      //...
      public void addFederalBar(bar) {
          if(bar != null) {
              federalBars.add(bar);
              bar.add(this);
          }
      }
      public void addInstitutionalBar(bar) {
          if(bar != null) {
              institutionalBars.add(bar);
              bar.add(this);
          }
      }
  }
  
  public class Bar implements SeparatelySequenceableAssociate {
      private List<Foo> owners;
      //...
      public void add(Foo owner) {
          if(owner != null) {
              owners.add(owner);
          }
      }
  }
  </pre>
  
  Now suppose a Foo Bar needs to be versioned because its being updated.
  
  The VersioningService with this method:
  
  <pre> 
  SequenceOwner createNewVersion(SequenceOwner oldVersion, 
                                  SeparatelySequenceableAssociate oldAssociate) 
                                  throws VersionException;
  </pre>
  
  has no idea which collection of Bars the oldAssociate came from, so doesn't know 
  where to assign the newly sequence SeparatelySequenceableAssociate.
  
  Likewise, if a collection of SeparatelySequenceableAssociates needs to be versioned
  using this method:
  
  <pre> 
  SequenceOwner createNewVersion(SequenceOwner oldVersion, 
                                  List<SeparatelySequenceableAssociate> oldAssociate) 
                                  throws VersionException;
  </pre>
  
  the VersioningService has no idea whether the SeparatelySequenceableAssociates came from 
  one collection or the other, or a combination of both.
  
   Only the caller knows how the newly versioned (SeparatelySequenceableAssociate(s) should 
   be associated with the SequenceOwner.
   
   By defining the versioning service methods with a SeparatelySequenceableAssociateAssignmentCallback 
   method, and using the SequenceAssociateCallbackAdapter, 
   the caller can do something like this:
   
   <pre>
   public ActionForward versionAFederalBar(ActionMapping, ActionForm form, HttpServletRequest request, 
                                           HttpServletResponseresponse) {
       Foo foo = findSequenceOwner(form, request);
       Bar bar = findSelectedBarToVersion(form, request);
       Foo newFoo = getVersionService().createNewVersion(foo);
       getVersionService().createNewVersion(newFoo, bar, 
                                new SequenceAssociateCallbackAdapter() {
                                    @Override
                                    public void assign(SeparatelySequenceableAssociate newAssociate) {
                                        SequenceOwnerImpl owner = (SequenceOwnerImpl) newAssociate.getLatestOwner();
                                        owner.addFederalBar(bar);
                                    }
                                });
   }
   </pre>
   
   Versioning a list of SeparatelySequenceableAssociates is equally straightforward:
   
   <pre>
   public ActionForward versionAFederalBar(ActionMapping, ActionForm form, HttpServletRequest request, 
                                           HttpServletResponseresponse) {
       final Foo foo = findSequenceOwner(form, request);
       Foo newFoo = getVersionService().createNewVersion(foo);
       final List<Bar> bars = findSelectedBarsToVersion(form, request);
       getVersionService().createNewVersion(newFoo, bars, 
                              new SequenceAssociateCallbackAdapter() {
                                  @Override
                                  public void assign(List<SeparatelySequenceableAssociate> newAssociates) {
                                      SequenceOwnerImpl owner = (SequenceOwnerImpl) newAssociate.getLatestOwner();
                                      for(SeparatelySequenceableAssociate newBar : newAssociates) {
                                          owner.addInstitutionalBar((Bar)newBar);
                                      }
                                  }
                              });
   }
   </pre>
 */
public interface SeparatelySequenceableAssociateAssignmentCallback {
    /**
     * This method assigns a newly versioned SeparatelySequenceableAssociate
     * back to the new SequenceOwner
     * 
     * @param newOwner
     * @param newAssociate
     */
    void assign(SequenceOwner newOwner, SeparatelySequenceableAssociate newAssociate);
    
    /**
     * This method assigns a newly versioned list of SeparatelySequenceableAssociates
     * back to the new SequenceOwner
     * 
     * @param newOwner
     * @param newAssociate
     */
    void assign(SequenceOwner newOwner, List<SeparatelySequenceableAssociate> newAssociate);
}
