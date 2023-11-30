package io.toolisticon.jmolecules.archunit

import com.tngtech.archunit.base.DescribedPredicate
import com.tngtech.archunit.core.domain.JavaClass
import com.tngtech.archunit.core.domain.JavaPackage


/**
 * This JavaClass predicate is used for checking if a given type belongs to a certain layer.
 * The layer is characterized by an annotation. The predicate is true if one of the following conditions is true:
 * - the type is annotated directly
 * - the supertype of the is annotated
 * - the package contains a marker type (FQN is package.<packageMarkerSimpleName>) that is annotated
 * - the type belongs to a package with a parent package matching the condition above
 * - the (Java) package is annotated (using package-info.java)
 * - the type belongs to a package with a parent package matching the condition above
 * All these conditions hold if and only if the type is not annotated by annotation listed in excludingAnnotations.
 */
class IsLayerType(
  private val definedByAnnotation: Class<out Annotation>,
  private val excludingAnnotations: Set<Class<out Annotation>> = emptySet(),
  private val packageMarkerSimpleClassNames: Set<String> = emptySet(),
  private val annotationType: AnnotationTarget = AnnotationTarget.CLASS
) : DescribedPredicate<JavaClass>(
  "(meta-)annotated with %s or residing in package (meta-)annotated or marked with %s",  //
  definedByAnnotation.typeName, definedByAnnotation.typeName
) {

  companion object {
    /**
     * Constructs new layer type grouping classes by annotation used on class level.
     */
    @JvmStatic
    fun layerType(definedByAnnotation: Class<out Annotation>, packageMarkerSimpleClassNames: Set<String> = emptySet()): IsLayerType {
      return IsLayerType(definedByAnnotation = definedByAnnotation, packageMarkerSimpleClassNames = packageMarkerSimpleClassNames)
    }

    /**
     * Constructs new layer type grouping classes by annotation used on method of level.
     */
    @JvmStatic
    fun layerTypeByClassMethod(definedByAnnotation: Class<out Annotation>, packageMarkerSimpleClassNames: Set<String> = emptySet()): IsLayerType {
      return IsLayerType(
        definedByAnnotation = definedByAnnotation,
        packageMarkerSimpleClassNames = packageMarkerSimpleClassNames,
        annotationType = AnnotationTarget.FUNCTION
      )
    }

  }


  /**
   * Creates a new layer type from current adding exclusions.
   * @param exclusions annotations to be used to exclude types from being in this layer.
   */
  @SafeVarargs
  fun withExclusions(vararg exclusions: Class<out Annotation>): IsLayerType {
    return IsLayerType(definedByAnnotation = definedByAnnotation, excludingAnnotations = this.excludingAnnotations + setOf(*exclusions))
  }

  override fun test(type: JavaClass): Boolean {
    return if (excludingAnnotations.any { type.hasDirectOrMetaAnnotation(it) }) {
      false
    } else {
      when (this.annotationType) {
        AnnotationTarget.CLASS ->
          type.hasDirectOrMetaAnnotation(definedByAnnotation)
            || type.getPackage().hasAnnotationOnPackageOrParent(definedByAnnotation)
            || type.getPackage().hasAnnotatedMarkerTypeInPackageOrParent(definedByAnnotation)

        AnnotationTarget.FUNCTION ->
          type.hasMethodAnnotatedWith(definedByAnnotation)
        else -> false
      }
    }
  }

  private fun JavaPackage.hasAnnotatedMarkerTypeInPackageOrParent(annotation: Class<out Annotation>): Boolean {
    return if (packageMarkerSimpleClassNames
        .filter { simpleName -> this.containsClassWithSimpleName(simpleName) }
        .map { simpleName -> this.getClassWithSimpleName(simpleName) }
        .any { clazz -> clazz.hasDirectOrMetaAnnotation(annotation) }
    ) {
      true
    } else {
      this.parent
        .map { it.hasAnnotatedMarkerTypeInPackageOrParent(annotation) }
        .orElse(false)
    }
  }

  private fun JavaPackage.hasAnnotationOnPackageOrParent(annotation: Class<out Annotation>): Boolean {
    return if (excludingAnnotations.any { this.isAnnotatedWith(it) || this.isMetaAnnotatedWith(it) }) {
      false
    } else {
      if (this.isAnnotatedWith(annotation) || this.isMetaAnnotatedWith(annotation)) {
        true
      } else {
        this.parent
          .map { it.hasAnnotationOnPackageOrParent(annotation) }
          .orElse(false)
      }
    }
  }

  private fun JavaClass.hasDirectOrMetaAnnotation(annotation: Class<out Annotation>): Boolean {
    return this.isAnnotatedWith(annotation) || this.isMetaAnnotatedWith(annotation)
  }

  private fun JavaClass.hasMethodAnnotatedWith(annotation: Class<out Annotation>): Boolean {
    return this.allMethods.any { m -> m.isAnnotatedWith(annotation) || m.isMetaAnnotatedWith(annotation) }
  }
}
