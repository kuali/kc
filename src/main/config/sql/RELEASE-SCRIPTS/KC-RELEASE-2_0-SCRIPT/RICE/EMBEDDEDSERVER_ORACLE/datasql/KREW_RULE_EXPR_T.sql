TRUNCATE TABLE KREW_RULE_EXPR_T DROP STORAGE
/
INSERT INTO KREW_RULE_EXPR_T (OBJ_ID,RULE_EXPR,RULE_EXPR_ID,TYP,VER_NBR)
  VALUES ('616A0754-3BA6-39DF-9A1B-11432849DC6B','import edu.sampleu.recipe.util.RecipeUtils' || chr(13) || chr(10) ||
'		        import org.apache.commons.lang.StringUtils' || chr(13) || chr(10) ||
'		        import org.kuali.rice.kew.engine.RouteContext;' || chr(13) || chr(10) ||
'		        import org.kuali.rice.kew.rule.Rule;' || chr(13) || chr(10) ||
'		        import org.kuali.rice.kew.rule.RuleExpressionResult;' || chr(13) || chr(10) ||
'		        ' || chr(13) || chr(10) ||
'		        String ingredients = RecipeUtils.getRecipeIngredientsFromRecipeDocumentContent(routeContext)' || chr(13) || chr(10) ||
'		        ' || chr(13) || chr(10) ||
'		        if(StringUtils.containsIgnoreCase(ingredients, "chicken")) {' || chr(13) || chr(10) ||
'		        	return RecipeUtils.constructGroupApprovalRequest("KR-WKFLW:ChickenRecipeMasters", rule)' || chr(13) || chr(10) ||
'		        } else {' || chr(13) || chr(10) ||
'		        	/* Return an empty route request */' || chr(13) || chr(10) ||
'		        	return RecipeUtils.constructEmptyApprovalRequest(rule);' || chr(13) || chr(10) ||
'		        }',2000,'BSF:groovy',1)
/
INSERT INTO KREW_RULE_EXPR_T (OBJ_ID,RULE_EXPR,RULE_EXPR_ID,TYP,VER_NBR)
  VALUES ('3364EB47-6407-9C45-8AAD-7A8310266208','import javax.xml.namespace.QName' || chr(13) || chr(10) ||
'		        import org.kuali.rice.resourceloader.GlobalResourceLoader' || chr(13) || chr(10) ||
'		        import edu.sampleu.magazine.service.MagazineService' || chr(13) || chr(10) ||
'		        import edu.sampleu.recipe.util.RecipeUtils' || chr(13) || chr(10) ||
'		        import org.apache.commons.lang.StringUtils' || chr(13) || chr(10) ||
'		        import org.kuali.rice.kew.engine.RouteContext' || chr(13) || chr(10) ||
'		        import org.kuali.rice.kew.rule.Rule' || chr(13) || chr(10) ||
'		        import org.kuali.rice.kew.rule.RuleExpressionResult' || chr(13) || chr(10) ||
'		        ' || chr(13) || chr(10) ||
'		        String origin = RecipeUtils.getRecipeOriginFromRecipeDocumentContent(routeContext)' || chr(13) || chr(10) ||
'		        ' || chr(13) || chr(10) ||
'		        QName serviceName = new QName("magazineNamespace", "magazineSoapService")' || chr(13) || chr(10) ||
'		        MagazineService magazineSoapService = (MagazineService) GlobalResourceLoader.getService(serviceName)' || chr(13) || chr(10) ||
'		        String managedMagazines[] = magazineSoapService.getAllManagedMagazines()' || chr(13) || chr(10) ||
'		        ' || chr(13) || chr(10) ||
'		        if(ArrayUtils.contains(managedMagazines, origin)) {' || chr(13) || chr(10) ||
'		        	return RecipeUtils.constructGroupApprovalRequest("KR-WKFLW:MagazineManagers", rule)' || chr(13) || chr(10) ||
'		        } else {' || chr(13) || chr(10) ||
'		        	/* Return an empty route request */' || chr(13) || chr(10) ||
'		        	return RecipeUtils.constructEmptyApprovalRequest(rule);' || chr(13) || chr(10) ||
'		        }',2001,'BSF:groovy',1)
/
