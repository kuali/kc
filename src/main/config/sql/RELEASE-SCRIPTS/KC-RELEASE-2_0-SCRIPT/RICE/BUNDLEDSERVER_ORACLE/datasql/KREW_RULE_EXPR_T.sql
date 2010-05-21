TRUNCATE TABLE KREW_RULE_EXPR_T DROP STORAGE
/
INSERT INTO KREW_RULE_EXPR_T (OBJ_ID,RULE_EXPR,RULE_EXPR_ID,TYP,VER_NBR)
  VALUES ('616A0754-3BA6-39DF-9A1B-11432849DC6B','import edu.sampleu.recipe.util.RecipeUtils
		        import org.apache.commons.lang.StringUtils
				import org.kuali.rice.kew.engine.RouteContext;
				import org.kuali.rice.kew.rule.Rule;
				import org.kuali.rice.kew.rule.RuleExpressionResult;
		        
				String ingredients = RecipeUtils.getRecipeIngredientsFromRecipeDocumentContent(routeContext)
		
				if(StringUtils.containsIgnoreCase(ingredients, "chicken")) {
					return RecipeUtils.constructGroupApprovalRequest("KR-WKFLW:ChickenRecipeMasters", rule)
		        } else {
		        	/* Return an empty route request */
		        	return RecipeUtils.constructEmptyApprovalRequest(rule);
		        }',2000,'BSF:groovy',1)
/
INSERT INTO KREW_RULE_EXPR_T (OBJ_ID,RULE_EXPR,RULE_EXPR_ID,TYP,VER_NBR)
  VALUES ('3364EB47-6407-9C45-8AAD-7A8310266208','import javax.xml.namespace.QName
				import org.kuali.rice.resourceloader.GlobalResourceLoader
				import edu.sampleu.magazine.service.MagazineService
		        import edu.sampleu.recipe.util.RecipeUtils
		        import org.apache.commons.lang.StringUtils
				import org.kuali.rice.kew.engine.RouteContext
				import org.kuali.rice.kew.rule.Rule
				import org.kuali.rice.kew.rule.RuleExpressionResult
		        
				String origin = RecipeUtils.getRecipeOriginFromRecipeDocumentContent(routeContext)
				
				QName serviceName = new QName("magazineNamespace", "magazineSoapService")
				MagazineService magazineSoapService = (MagazineService) GlobalResourceLoader.getService(serviceName)
				String managedMagazines[] = magazineSoapService.getAllManagedMagazines()
		
				if(ArrayUtils.contains(managedMagazines, origin)) {
					return RecipeUtils.constructGroupApprovalRequest("KR-WKFLW:MagazineManagers", rule)
		        } else {
		        	/* Return an empty route request */
		        	return RecipeUtils.constructEmptyApprovalRequest(rule);
		        }',2001,'BSF:groovy',1)
/
