package karlkfi.spring.web.method.annotation;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({PARAMETER})
@Retention(RUNTIME)
@Documented
public @interface ResourceParam {
	
	/**
     * The JNDI name of the resource.  For parameter annotations,
     * the default is the field name.
     */
    String name() default "";
    
    /**
	 * Declares whether the annotated dependency is required.
	 * <p>Defaults to {@code true}.
	 */
	boolean required() default true;
	
	/**
     * The Java type of the resource.  For field annotations,
     * the default is the type of the field.  For method annotations,
     * the default is the type of the JavaBeans property.
     * For class annotations, there is no default and this must be
     * specified.
     */
    Class<?> type() default java.lang.Object.class;

}
