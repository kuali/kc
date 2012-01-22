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
package org.kuali.kra.award;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.home.AwardSponsorTerm;
import org.kuali.kra.award.home.AwardSyncable;
import org.kuali.kra.award.home.AwardSyncableList;
import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.kra.award.home.AwardTemplateComment;
import org.kuali.kra.award.home.AwardTemplateReportTermRecipient;
import org.kuali.kra.award.home.AwardTemplateTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * This class is the implementation of AwardTemplateSyncService.
 */
public class AwardTemplateSyncServiceImpl implements AwardTemplateSyncService {

    private BusinessObjectService businessObjectService;
    private KualiRuleService kualiRuleService;
    
    private static final Log LOG = LogFactory.getLog(AwardTemplateSyncService.class);
    
    /**
     * @see org.kuali.kra.award.AwardTemplateSyncService#syncAwardToTemplate(org.kuali.kra.award.document.AwardDocument, org.kuali.kra.award.AwardTemplateSyncScope[])
     */
    public boolean syncAwardToTemplate(AwardDocument awardDocument, AwardTemplateSyncScope[] scopes ) {
        boolean success;
        Award award = awardDocument.getAward();
        AwardTemplateSyncEvent awardTemplateSyncEvent = 
            new AwardTemplateSyncEvent("Award Sync","document.award.awardTemplate",awardDocument);
        if(!getKualiRuleService().applyRules(awardTemplateSyncEvent)){
            return false;
        }
        
        try {
            AwardTemplate awardTemplate = fetchAwardTemplate(award);
            sync(awardTemplate, award, scopes, createScopeStack(),award,awardTemplate);
            success=true;
        }catch (Exception e) {
            throw new RuntimeException( String
                    .format( "Exception thrown executing syncAwardToTemplate on document %s with scopes {%s}"
                            , awardDocument.getDocumentNumber(), scopesArrayToDelimitedList( scopes ) )
                    , e );  
        }
        return success;
    }

    
    
    /**
     * @see org.kuali.kra.award.AwardTemplateSyncService#syncWillAlterData(org.kuali.kra.award.document.AwardDocument, org.kuali.kra.award.AwardTemplateSyncScope)
     */
    public boolean syncWillAlterData(AwardDocument awardDocument, AwardTemplateSyncScope scope) {
        boolean result = false;
        AwardTemplateSyncScope[] scopes = getScopeArray( scope );
        Award award = awardDocument.getAward();
        
        try {
            result =  syncTargetCheck( fetchAwardTemplate(award), award, scopes, createScopeStack(), award, fetchAwardTemplate(award) );
        }
        catch (Exception e) {
            throw new RuntimeException( String
                    .format( "Exception thrown executing syncWillAlterData on document %s with scopes {%s}"
                            , awardDocument.getDocumentNumber(), scopesArrayToDelimitedList( scopes ) )
                    , e );  
        }
        return result;
    }
    
    /**
     * @see org.kuali.kra.award.AwardTemplateSyncService#templateContainsScopedData(org.kuali.kra.award.document.AwardDocument, org.kuali.kra.award.AwardTemplateSyncScope)
     */
    public boolean templateContainsScopedData( AwardDocument awardDocument, AwardTemplateSyncScope scope ) {
        boolean result = false;       
        AwardTemplateSyncScope[] scopes = getScopeArray( scope );
        Award award = awardDocument.getAward();
        try {
            result = syncSourceCheck( fetchAwardTemplate( award), award, scopes, createScopeStack(), award, fetchAwardTemplate(award));
        }  catch (Exception e) {
            throw new RuntimeException( String
                    .format( "Exception thrown executing syncSourceCheck on document %s with scopes {%s}"
                            , awardDocument.getDocumentNumber(), scopesArrayToDelimitedList( scopes ) )
                    , e );  
        }
        return result;
    }
    

    
    /**
     * Get an array of AwardTemplateSyncScopes that should be used.  The rule is:
     * if scope = FULL, then all values of AwardTemplateSyncScope.values() is used, otherwise
     * an array with one element is returned.
     *  
     * @param scope The scope
     * @return The array of scopes that will be synchronized.
     */
    protected AwardTemplateSyncScope[] getScopeArray( AwardTemplateSyncScope scope ) {
        AwardTemplateSyncScope[] scopes =  {scope};
        if( scope.equals(AwardTemplateSyncScope.FULL) ) scopes = AwardTemplateSyncScope.values();
        return scopes;
    }
    
    /**
     * This method returns an empty scope stack.  This stack is used to keep track of the sync scopes of each object 
     * as we traverse the object graph.  The allows for the scope CONTAININIG_CLASS_INHERIT which for an element or list 
     * that will match the scopes of the containing class.
     * 
     * @return a new Stack of AwardTemplateSyncScope 
     */
    protected Stack<AwardTemplateSyncScope[]> createScopeStack() {
        return new Stack<AwardTemplateSyncScope[]>();
    }
    
    /**
     * This method 
     * 
     * @param awardTemplateObject
     * @param awardObject
     */
    protected boolean syncTargetCheck(Object awardTemplateObject, Object awardObject,AwardTemplateSyncScope[] scopes, java.util.Stack<AwardTemplateSyncScope[]> scopeStack, Award award, AwardTemplate awardTemplate) 
        throws Exception {
        List<Field> allFields = new ArrayList<Field>();
        findAllFields(awardObject.getClass(), allFields);
        boolean result = false;
        AwardTemplateSyncScope[] effectiveScopes;
        for (Field field: allFields) {
            try {
                if (field.isAnnotationPresent(AwardSyncable.class)){
                    scopeStack.push(field.getAnnotation(AwardSyncable.class).scopes());
                    effectiveScopes = getEffectiveScope(scopeStack);
                    if (AwardTemplateSyncScope.isInScope(effectiveScopes, scopes)) {
                        result = checkTargetField(awardTemplateObject, awardObject, field, scopeStack,award,awardTemplate);
                    } else {
                        //not in scope.
                    }
                    scopeStack.pop();
                } else if(field.isAnnotationPresent(AwardSyncableList.class)) {
                    scopeStack.push(field.getAnnotation(AwardSyncableList.class).scopes());
                    effectiveScopes = getEffectiveScope(scopeStack);
                    if( AwardTemplateSyncScope.isInScope(effectiveScopes, scopes)) {
                        result = checkTargetList(awardTemplateObject, awardObject, field, scopes, scopeStack, award, awardTemplate);
                    } else {
                        if( LOG.isDebugEnabled() )
                            LOG.debug(String.format( "Skipped (not in scope(s) %s) list:%s.%s", ArrayUtils.toString(scopes),awardObject.getClass().toString(),field.getName() ));
                    }
                    scopeStack.pop();
                } else {
                    if ( LOG.isTraceEnabled() ) {
                        LOG.trace( String.format( "Skipped (No Annotation):%s.%s", awardObject.getClass().toString(),field.getName() )  );
                    }
                }
                if (result) break;
            } catch( IllegalStateException ise ) {
                throw new RuntimeException( String.format( "IllegalStateException while processing %s.%s",field.getDeclaringClass(), field.getName() ), ise );
            }
        }
        
        return result;
    }
  
    
    /**
     * This checks to see if a target field is null or otherwise empty.  
     * @param awardTemplateObject
     * @param awardObject
     * @param field
     * @throws Exception
     */
    protected boolean checkTargetField(Object awardTemplateObject, Object awardObject, Field field, java.util.Stack<AwardTemplateSyncScope[]> scopeStack, Award award, AwardTemplate awardTemplate) throws Exception {
        boolean result = false;
        if (field.isAnnotationPresent(AwardSyncable.class)){
            if( ObjectUtils.getPropertyValue(awardObject, field.getName()) == null ) result =  false;
            else result = true;
        }
        return result;
    }
    
    
    
    
    /**
     * This method 
     * 
     * @param awardTemplateObject
     * @param awardObject
     */
    protected boolean syncSourceCheck(Object awardTemplateObject, Object awardObject,AwardTemplateSyncScope[] scopes, java.util.Stack<AwardTemplateSyncScope[]> scopeStack, Award award, AwardTemplate awardTemplate) 
        throws Exception {
        List<Field> allFields = new ArrayList<Field>();
        findAllFields(awardObject.getClass(), allFields);
        boolean result = false;
        AwardTemplateSyncScope[] effectiveScopes;
        for (Field field: allFields) {
            try {
                if (field.isAnnotationPresent(AwardSyncable.class)){
                    scopeStack.push(field.getAnnotation(AwardSyncable.class).scopes());
                    effectiveScopes = getEffectiveScope(scopeStack);
                    if (AwardTemplateSyncScope.isInScope(effectiveScopes, scopes)) {
                        result = checkSourceField(awardTemplateObject, awardObject, field, scopeStack,award,awardTemplate);
                    } else {
                        //not in scope.
                    }
                    scopeStack.pop();
                } else if(field.isAnnotationPresent(AwardSyncableList.class)) {
                    scopeStack.push(field.getAnnotation(AwardSyncableList.class).scopes());
                    effectiveScopes = getEffectiveScope(scopeStack);
                    if( AwardTemplateSyncScope.isInScope(effectiveScopes, scopes)) {
                        result = checkSourceList(awardTemplateObject, awardObject, field, scopes, scopeStack, award, awardTemplate);
                    } else {
                        if( LOG.isDebugEnabled() )
                            LOG.debug(String.format( "Skipped (not in scope(s) %s) list:%s.%s", ArrayUtils.toString(scopes),awardObject.getClass().toString(),field.getName() ));
                    }
                    scopeStack.pop();
                } else {
                    if ( LOG.isTraceEnabled() ) {
                        LOG.trace( String.format( "Skipped (No Annotation):%s.%s", awardObject.getClass().toString(),field.getName() )  );
                    }
                }
                if (result) break;
            } catch( IllegalStateException ise ) {
                throw new RuntimeException( String.format( "IllegalStateException while processing %s.%s",field.getDeclaringClass(), field.getName() ), ise );
            }
        }
        
        return result;
    }
    
    protected boolean checkSourceField(Object awardTemplateObject, Object awardObject, Field field,
            Stack<AwardTemplateSyncScope[]> scopeStack, Award award, AwardTemplate awardTemplate) {
            boolean result = false;
            if( ObjectUtils.isNotNull( ObjectUtils.getPropertyValue( awardTemplateObject, field.getName() ) ) && field.getAnnotation(AwardSyncable.class).impactSourceScopeEmpty()) result = true;
            return result;
    }



    protected boolean checkSourceList(Object awardTemplateObject, Object awardObject, Field field, AwardTemplateSyncScope[] scopes,
            Stack<AwardTemplateSyncScope[]> scopeStack, Award award, AwardTemplate awardTemplate) throws Exception {
        field.setAccessible(true);
        boolean result = false;
        List<Object> awardTemplateObjectList = (List)ObjectUtils.getPropertyValue(awardTemplateObject, field.getName());
        if( !( awardTemplateObjectList == null || awardTemplateObjectList.size() == 0) ) { 
            //need to check each object for in scope because of shared Lists.
            Method templateIsInScopeMethod = findIsInScopeMethodForClass( field.getAnnotation(AwardSyncableList.class).syncSourceClass() );
            for( Object awardListObject : awardTemplateObjectList ) {
                if( (Boolean)templateIsInScopeMethod.invoke(null,awardListObject, scopes)) { 
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * This method is for extracting the appropriate list from award by using property name and sync the list
     * @param awardTemplateObject
     * @param awardObject
     * @param propertyName
     * @param awardSyncableList 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    protected void extractListFromParentAndSync(Object awardTemplateObject, Object awardObject, Field field, AwardTemplateSyncScope[] scopes, java.util.Stack<AwardTemplateSyncScope[]> scopeStack, Award award, AwardTemplate awardTemplate ) throws Exception {
        field.setAccessible(true);
        List<Object> awardTemplateObjectList = (List)ObjectUtils.getPropertyValue(awardTemplateObject, field.getName());
        AwardSyncableList awardSyncableList = field.getAnnotation(AwardSyncableList.class);
        scopeStack.push(awardSyncableList.scopes());
        AwardTemplateSyncScope[] effectiveScopes = getEffectiveScope( scopeStack );
        if(awardTemplateObjectList!=null && AwardTemplateSyncScope.isInScope( scopes, effectiveScopes )){
            if(awardSyncableList.syncMethodName().equalsIgnoreCase(AwardSyncableList.DEFAULT_METHOD)){
                syncListObjects(awardObject,awardTemplateObjectList,field, scopes, scopeStack, award, awardTemplate);
            }else{
                invokeMethodToSync((Award)awardObject,awardTemplateObjectList,awardSyncableList.syncMethodName(), scopes, scopeStack, award, awardTemplate);
            }
        }
        scopeStack.pop();
    }
    
    /**
     * This method calculates the scope that should be used for an element given a scope stack.  
     * The rule is simple:  Move down the stack until you reach an array that contains
     * something other that CONTAINING_CLASS_INHERIT.  If no such element exists in the stack we throw an exception.
     * @param scopeStack The scope stack you want to get the effective scope for.
     * @return An array representing the effective scope to be used.
     * @throws IllegalStateException if there is no array in the stack that contains a scope other than CONTAINING_CLASS_INHERIT.
     */
    protected AwardTemplateSyncScope[] getEffectiveScope( Stack<AwardTemplateSyncScope[]> scopeStack )
        throws IllegalStateException {
        AwardTemplateSyncScope[] effectiveScope = null;
        
        for( int i = scopeStack.size()-1;i>=0;i--) {
            if( ArrayUtils.contains( scopeStack.get(i), AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT ) ) continue;
            effectiveScope = scopeStack.get(i);
        }
     
        if( effectiveScope == null ) {
            StringBuffer scopeStackString = new StringBuffer();
            for( int i = scopeStack.size()-1;i>=0;i--) {
                scopeStackString.append( String.format( "{%s}", scopeStackString.append( scopesArrayToDelimitedList( scopeStack.get(i) ))));
                if( i > 0 ) scopeStackString.append("|");
            }
            throw new RuntimeException( String.format( "No Effective Scope could  be calculated for the scopeStack [%s]", scopeStackString ));
        }
        return effectiveScope;
    }
    /**
     * 
     * This method is to invoke individual method if there is a method mentioned in the AwardSyncableList annotation
     * @param awardObject
     * @param awardTemplateObjectList
     * @param syncMethodName
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    protected void invokeMethodToSync(Award awardObject, List awardTemplateObjectList,String syncMethodName,AwardTemplateSyncScope[] scopes, Stack<AwardTemplateSyncScope[]> scopeStack, Award award, AwardTemplate awardTemplate) throws Exception{
        Method syncMethod = getClass().getMethod(syncMethodName, new Class[]{Award.class,List.class,Stack.class, Award.class, AwardTemplate.class});
        syncMethod.invoke(this, new Object[]{awardObject,awardTemplateObjectList,scopeStack, award, awardTemplate});
    }


    
    /**
     * This method is used to sync member properties of an award template object to an award object
     * 
     * @param awardTemplateObject
     * @param awardObject
     */
    protected void sync(Object awardTemplateObject, Object awardObject,AwardTemplateSyncScope[] scopes, java.util.Stack<AwardTemplateSyncScope[]> scopeStack, Award award, AwardTemplate awardTemplate) throws Exception{
        List<Field> allFields = new ArrayList<Field>();
        findAllFields(awardObject.getClass(), allFields);
        
        AwardTemplateSyncScope[] effectiveScopes;
        for (Field field: allFields) {
            if (field.isAnnotationPresent(AwardSyncable.class)){
                scopeStack.push(field.getAnnotation(AwardSyncable.class).scopes());
                effectiveScopes = getEffectiveScope(scopeStack);
                
                if (AwardTemplateSyncScope.isInScope(effectiveScopes, scopes)) {
                    if( LOG.isDebugEnabled() )
                        LOG.debug(String.format( "Copying field:%s.%s", awardObject.getClass().toString(),field.getName() ));
                    copyField(awardTemplateObject, awardObject, field, scopeStack,award,awardTemplate);
                } else {
                    if( LOG.isDebugEnabled() )
                        LOG.debug(String.format( "Skiped (not in scope(s) %s):%s.%s", ArrayUtils.toString(scopes), awardObject.getClass().toString(),field.getName() ));
                }
                scopeStack.pop();
            }
            else if(field.isAnnotationPresent(AwardSyncableList.class)){
                scopeStack.push(field.getAnnotation(AwardSyncableList.class).scopes());
                effectiveScopes = getEffectiveScope(scopeStack);
                if( AwardTemplateSyncScope.isInScope(effectiveScopes, scopes)) {
                    if( LOG.isDebugEnabled() )
                        LOG.debug(String.format( "Sync list:%s.%s", awardObject.getClass().toString(),field.getName() ));
                    extractListFromParentAndSync(awardTemplateObject, awardObject, field, scopes, scopeStack, award, awardTemplate);
                } else {
                    if( LOG.isDebugEnabled() )
                        LOG.debug(String.format( "Skipped (not in scope(s) %s) list:%s.%s", ArrayUtils.toString(scopes),awardObject.getClass().toString(),field.getName() ));
                }
                scopeStack.pop();
            } else {
                if ( LOG.isTraceEnabled() ) {
                        LOG.trace( String.format( "Skipped (No Annotation):%s.%s", awardObject.getClass().toString(),field.getName() )  );
                }
            }
        }
    }
    
    /**
     * This method uses recursion to find all declared fields for a class hierierachy
     * @param klass
     * @param allFields
     */
    @SuppressWarnings("unchecked")
    protected void findAllFields(Class klass, List<Field> allFields) {
        Field[] fields = klass.getDeclaredFields();
        allFields.addAll(Arrays.asList(fields));
        klass = klass.getSuperclass();
        if(klass != null) {
            findAllFields(klass, allFields);
        }
    }
    
    
    
    
    
    /**
     * This copies value from Award Template object to Award object
     * @param awardTemplateObject
     * @param awardObject
     * @param field
     * @throws Exception
     */
    protected void copyField(Object awardTemplateObject, Object awardObject, Field field, java.util.Stack<AwardTemplateSyncScope[]> scopeStack, Award award, AwardTemplate awardTemplate) throws Exception {
        if (field.isAnnotationPresent(AwardSyncable.class)){
            Object value = ObjectUtils.getPropertyValue(awardTemplateObject, field.getName());
            ObjectUtils.setObjectProperty(awardObject, field.getName(), value);
        }
    }
    
    /**
     * Check the target list, returns true if there is an element in the collection that is in scope.  
     * 
     * @param awardTemplateObject
     * @param awardObject
     * @param propertyName
     * @param awardSyncableList 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    protected boolean checkTargetList(Object awardTemplateObject, Object awardObject, Field field, AwardTemplateSyncScope[] scopes, java.util.Stack<AwardTemplateSyncScope[]> scopeStack, Award award, AwardTemplate awardTemplate ) throws Exception {
        field.setAccessible(true);
        boolean result = false;
        List<Object> awardObjectList = (List)ObjectUtils.getPropertyValue(awardObject, field.getName());
        if( !( awardObjectList == null || awardObjectList.size() == 0) ) { 
            //need to check each object for in scope because of shared Lists.
            Method templateIsInScopeMethod = findIsInScopeMethodForClass( field.getAnnotation(AwardSyncableList.class).syncClass() );
            for( Object awardListObject : awardObjectList ) {
                if( (Boolean)templateIsInScopeMethod.invoke(null,awardListObject, scopes)){ 
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    
    /**
     * This is the default method use to sync objects. This method is implemented to work on row Object.
     * If we need to change the implementation of sync functionality for a specific list, 
     * we have to overload this method with specific type.
     * @param awardObject
     * @param listObject
     * @param objectInList
     * @param field
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    protected void syncListObjects(Object awardObject, List<Object> listObject, Field field, AwardTemplateSyncScope[] scopes, java.util.Stack<AwardTemplateSyncScope[]> scopeStack, Award award, AwardTemplate awardTemplate  ) 
    throws Exception{
        AwardSyncableList awardSyncableList = field.getAnnotation(AwardSyncableList.class);
        String parentPropertyName = awardSyncableList.parentPropertyName();
        
        Method templateIsInScopeMethod = findIsInScopeMethodForClass( awardSyncableList.syncSourceClass() );
        Method awardIsInScopeMethod = findIsInScopeMethodForClass( awardSyncableList.syncClass() );
        
        
        List<Object> newObjectList = new ArrayList<Object>(listObject.size());
        Method createNewListElementMethod = getCreateNewListElementMethod( awardSyncableList.syncSourceClass() );
        Map<Object,Object> syncdMap = new HashMap<Object,Object>();
        
        for (Object awardTemplateObject : listObject) {
            if( (Boolean) templateIsInScopeMethod.invoke(null, awardTemplateObject, scopes )) {
                Object newObjectToSync = createNewListElementMethod.invoke(this, awardTemplateObject, awardSyncableList.syncClass(), award, awardTemplate,true );
                sync(awardTemplateObject, newObjectToSync, scopes, scopeStack, award, awardTemplate);
                ObjectUtils.setObjectProperty(newObjectToSync, parentPropertyName, awardObject);
                newObjectList.add(newObjectToSync);
                syncdMap.put( awardTemplateObject, newObjectToSync );
            } else {
               //nothing to do here.
            }
        }
        
        //now we need to loop through the list in the award and save the object that are not in scope, otherwise we will lose them...
        
        List<Object> newAwardList = new ArrayList<Object>();
        List<Object> awardList = (List<Object>) ObjectUtils.getPropertyValue(awardObject, field.getName());
        Method clearExistingElementMethod = getClearExistingElementMethod( awardSyncableList.syncClass() );
        Method findTargetSourceMethod = getFindSourceListElementFromTargetMethod( awardSyncableList.syncClass() );
        
        if( !awardSyncableList.removeMissingListElementsFromTarget() && ( clearExistingElementMethod==null || findTargetSourceMethod==null) ) {
            //in this case clearExistingElementMethod and findTargetSourceMethod must be defined for the
            //object.
            throw new RuntimeException( String.format( "Cannot process sync of %s since it is configured to not remove missing list elements from the target, but the necessary methods have not been defined.", awardSyncableList.syncClass() ));
        }
        
        for( Object aObject : awardList ) {
            Boolean isInScope = (Boolean)awardIsInScopeMethod.invoke( null, aObject, scopes );
            if( !isInScope) {
                newAwardList.add(aObject);
            } else if ( isInScope && !awardSyncableList.removeMissingListElementsFromTarget()  ) {
                //this means that we have the object in the target but is may not be in the source.  The annotation has been set so that
                //we should only call the clear fields on the existing instead of leaving it out of the final sync'd list.  
                //clearExistingElementMethod is a function that know how to reset the element to it's default state.
                //findTargetSourceMethod is a method that can determine if the target object has been sync'd to already by
                //checking the sync'd map.  We cannot simply do syncdMap.values().contains( thisThing ) since if a new record
                //was created as a result of the sync then it will not have non-null pks so equals() fails.
                if( findTargetSourceMethod.invoke(this,aObject, syncdMap, award, awardTemplate ) == null ) {
                    newAwardList.add(clearExistingElementMethod.invoke(this, aObject, awardSyncableList.syncClass(), award, awardTemplate ));
                }
               
            }
        }
        newObjectList.addAll(newAwardList);
        ObjectUtils.setObjectProperty(awardObject, field.getName(), newObjectList);
    }

    
    
    /**
     * This method finds the appropriate getOrCreateNewListElementObject for a class.  It does this by first trying to return 
     * the method within this implementation with the signature getOrCreateNewListElementObject( syncSourceClass, Class.class, Award.class, AwardTemplate.class ).
     * If it does not find one, it returns the generic method getOrCreateNewListElementObject( Object.class, Class.class, Award.class, AwardTemplate.class ).
     * @param syncSourceClass - The class you wish to find the method for.  If a specific method for this class does not exist, the generic method is returned.
     * @return The best match for the method.
     */
    protected Method getCreateNewListElementMethod( Class syncSourceClass ) {
        
        //Try to get the method that is specific for the source class.
        try {
            Method m = AwardTemplateSyncServiceImpl.class.getDeclaredMethod("getOrCreateNewListElementObject", syncSourceClass, Class.class, Award.class, AwardTemplate.class, boolean.class  );
            m.setAccessible(true);
            return m;
        }
        catch (Exception e) {
            if( LOG.isDebugEnabled() )
                LOG.debug( String.format( "Could not find specific getOrCreateNewListElementObject for class %s, using generic method.", syncSourceClass ) );
        }
        //well we failed to get the specific method, so we are going to return the generic method.
        try {
            Method m = AwardTemplateSyncServiceImpl.class.getDeclaredMethod( "getOrCreateNewListElementObject", Object.class, Class.class, Award.class, AwardTemplate.class, boolean.class );
            m.setAccessible(true);
            return m;
        }
        catch (Exception e) {
            throw new IllegalStateException( "Could not find generic getOrCreateNewListElementObject, this should never happen." );
        }
    }
    
    protected Method getClearExistingElementMethod( Class targetClass ) {
        Method m = null;
        try {
            m = AwardTemplateSyncServiceImpl.class.getDeclaredMethod("clearListElement", targetClass, Class.class, Award.class, AwardTemplate.class );
            m.setAccessible(true);
            return m;
        } catch ( Exception e ) {
            //could not find one, return null
            LOG.error(e);
        }
        return m;
    }
    
    protected Method getFindSourceListElementFromTargetMethod( Class targetClass ) {
        Method m = null;
        try {
            m = AwardTemplateSyncServiceImpl.class.getDeclaredMethod("findSourceListElementFromTarget", targetClass, Map.class, Award.class, AwardTemplate.class );
            m.setAccessible(true);
            return m;
        } catch ( Exception e ) {
            //could not find one, return null
            LOG.error(e);
        }
        return m;
    }
    
    
    @SuppressWarnings({ "unused", "unchecked" })
    protected Object getOrCreateNewListElementObject( Object sourceObject, java.lang.Class syncClass, Award award, AwardTemplate awardTemplate, boolean createNew ) {
        return createNew?ObjectUtils.createNewObjectFromClass(syncClass):null; 
    }
    
    @SuppressWarnings({ "unused", "unchecked" })
    protected AwardComment getOrCreateNewListElementObject( AwardTemplateComment sourceComment, java.lang.Class syncClass, Award award, AwardTemplate awardTemplate, boolean createNew ) {
        AwardComment comment = award.getAwardCommentByType(sourceComment.getCommentTypeCode(), sourceComment.getChecklistPrintFlag(), createNew );
        return comment;
    }
    
    @SuppressWarnings({ "unused", "unchecked" })
    protected AwardComment clearListElement( AwardComment comment, java.lang.Class syncClass, Award award, AwardTemplate awardTemplate) {
        comment.setComments(null);
        return comment;
    }
    
    @SuppressWarnings({ "unused", "unchecked" })
    protected AwardTemplateComment findSourceListElementFromTarget( AwardComment comment, Map<Object,Object> sourceToTargetMap, Award award, AwardTemplate awardTemplate) {
        AwardTemplateComment result = null;
        if( sourceToTargetMap != null && sourceToTargetMap.values() != null ) {
            for( Object tc : (sourceToTargetMap.keySet()) ) {
                AwardTemplateComment templateComment = (AwardTemplateComment)tc;
                if( StringUtils.equals( templateComment.getCommentTypeCode(), comment.getCommentTypeCode() ) && templateComment.getCommentTypeCode()!=null) {
                    result = templateComment;
                    break;
                }
            }
        }
        return result;
    }
    
    @SuppressWarnings({ "unused", "unchecked" })
    protected AwardSponsorTerm getOrCreateNewListElementObject( AwardTemplateTerm sponsorTerm, java.lang.Class syncClass, Award award, AwardTemplate awardTemplate, boolean createNew ) {
        AwardSponsorTerm term = award.getAwardSponsorTermByTemplateTerm( sponsorTerm, createNew );
        return term;
    }
    
    @SuppressWarnings({ "unused", "unchecked" })
    protected AwardReportTermRecipient getOrCreateNewListElementObject( AwardTemplateReportTermRecipient recipient, java.lang.Class syncClass, Award award, AwardTemplate awardTemplate, boolean createNew ) {
        AwardReportTermRecipient newRecipient = new AwardReportTermRecipient();
        recipient.refreshReferenceObject("rolodex");
        recipient.refreshReferenceObject("contactType");
        newRecipient.setContactType(recipient.getContactType());
        newRecipient.setNumberOfCopies(recipient.getNumberOfCopies());
        newRecipient.setRolodex(recipient.getRolodex());
        newRecipient.setRolodexId(recipient.getRolodexId());
        newRecipient.setContactTypeCode(recipient.getContactTypeCode());
        return newRecipient;
    }
    
    @SuppressWarnings("all")
    protected Method findIsInScopeMethodForClass( Class clazz ) {
        Class klass = AwardTemplateSyncScope.class;
        Method result = null;
        try {
            result = klass.getMethod("isInScope", Object.class,AwardTemplateSyncScope[].class );
        } catch (Exception e1) {
            throw new IllegalStateException( "FATAL: Cannot find method isInScope( Object,AwardTemplateSyncScope[]) on the AwardTemplateSyncScope class.  This should never happen." );
        }
        try {
            result = klass.getMethod("isInScope", clazz, AwardTemplateSyncScope[].class );
        } catch (Exception e) {
            
        }
        
        return result;
    }

    /**
     * This method is to fetch the award template by using template code from Award object.
     * 
     * @param Award object
     * @return AwardTemplate object
     */
    protected AwardTemplate fetchAwardTemplate(Award award) {
        award.refreshReferenceObject("awardTemplate");
        AwardTemplate awardTemplate = award.getAwardTemplate();
        if (awardTemplate == null && award.getTemplateCode() != null) {
            Map<String, Integer> primaryKeys = new HashMap<String, Integer>();
            primaryKeys.put("templateCode", award.getTemplateCode());
            awardTemplate = (AwardTemplate) businessObjectService.findByPrimaryKey(AwardTemplate.class, primaryKeys);
        }
        return awardTemplate;
    }

    /**
     * Gets the businessObjectService attribute.
     * 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * 
     * This is an overloaded method for syncing only AwardComments.
     * @param awardObject
     * @param templateComments
     * @param objectInList
     * @param propertyName
     */
    public void syncAwardComments(Award awardObject, List<AwardTemplateComment> awardTemplateComments,Stack<AwardTemplateSyncScope[]> scopeStack){
        awardObject.addTemplateComments(awardTemplateComments);
    }
    

    /**
     * 
     * This is an overloaded method for syncing only AwardSponsorTerms.
     * @param awardObject
     * @param templateTerms
     * @param objectInList
     * @param propertyName
     */
    public void syncAwardSponsorTerms(Award awardObject, List<AwardTemplateTerm> awardTemplateTerms, Stack<AwardTemplateSyncScope[]> scopeStack ){
        awardObject.addTemplateTerms(awardTemplateTerms);
    }
    
    
    /**
     * Gets the kualiRuleService attribute. 
     * @return Returns the kualiRuleService.
     */
    public KualiRuleService getKualiRuleService() {
        return kualiRuleService;
    }
    /**
     * Sets the kualiRuleService attribute value.
     * @param kualiRuleService The kualiRuleService to set.
     */
    public void setKualiRuleService(KualiRuleService kualiRuleService) {
        this.kualiRuleService = kualiRuleService;
    }


    /**
     * Utility method to generate a comma delimited list of scopes contained in the array passed in.
     * @param scopes 
     * @return Comma delimited list of scope name contained in the passed in scopes array.
     */
    public static final String scopesArrayToDelimitedList( AwardTemplateSyncScope[] scopes ) {
        StringBuffer result = new StringBuffer();
        for( int i = 0; i<scopes.length;i++) {
            result.append(scopes[i].toString());
            if( i < scopes.length-1 )result.append(",");
        }
        return result.toString();
    }
    
    
}
