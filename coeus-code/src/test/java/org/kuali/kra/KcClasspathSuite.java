package org.kuali.kra;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.filter.AbstractTypeHierarchyTraversingFilter;

public class KcClasspathSuite extends Suite {

	public KcClasspathSuite(Class<?> suiteClass, RunnerBuilder builder) throws InitializationError, ClassNotFoundException {
		super(builder, getTestClasses(suiteClass));
	}
	
	protected static Class<?>[] getTestClasses(Class<?> suiteClass) throws ClassNotFoundException {
		ClassPathScanningCandidateComponentProvider scanner = 
				new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new MethodAnnotationTypeFilter(Test.class));
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (BeanDefinition bd : scanner.findCandidateComponents(suiteClass.getPackage().getName())) {
			System.out.println("Found test - " + bd.getBeanClassName());
			classes.add(Class.forName(bd.getBeanClassName()));
		}
		Class<?>[] classArray = new Class<?>[classes.size()];
		return (Class<?>[]) classes.toArray(classArray);
	}
	
	/**
	 * Based on AnnotationTypeFilter with changes. Major one is we cannot use 
	 * AbstractTypeHierarchyTraversingFilter superClass features due to the real possiblity our
	 * parent classes exist in jar files and the metadata readers have troubles processing those files
	 * so instead once we have a class we do simple reflection based getMethod.hasAnnotation scanning of parent
	 * classes.
	 * @author dpace
	 *
	 */
	protected static class MethodAnnotationTypeFilter extends AbstractTypeHierarchyTraversingFilter {
		
		private Class<? extends Annotation> methodAnnotation;
		public MethodAnnotationTypeFilter(Class<? extends Annotation> annotationType) {
			super(false, true);
			this.methodAnnotation = annotationType;
		}
		
		@Override
		protected boolean matchSelf(MetadataReader metadataReader) {
			AnnotationMetadata metadata = metadataReader.getAnnotationMetadata();
			if (metadata.hasAnnotatedMethods(methodAnnotation.getName())) {
				return true;
			} else if (metadata.hasSuperClass()) {
				return matchSuperClass(metadata.getSuperClassName());
			} else {
				return false;
			}
		}

		@Override
		protected Boolean matchSuperClass(String superClassName) {
			if (Object.class.getName().equals(superClassName)) {
				return Boolean.FALSE;
			}
			else {
				try {
					Class<?> clazz = getClass().getClassLoader().loadClass(superClassName);
					for (Method method : clazz.getMethods()) {
						if (method.getAnnotation(methodAnnotation) != null) {
							return Boolean.TRUE;
						}
					}
					return matchSuperClass(clazz.getSuperclass().getCanonicalName());
				} catch (ClassNotFoundException ex) {
					// Class not found - can't determine a match that way.
				}
			}
			return Boolean.FALSE;
		}

	}

}
