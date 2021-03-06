package karlkfi.spring.web.method.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Method Argument Resolver for parameters annotated with {@link ResourceParam}.
 * The resource is resolved using the injected {@link BeanFactory}.
 * 
 * Usage:
 * <pre>
 * <mvc:annotation-driven>
 *   <mvc:argument-resolvers>
 *     <bean class="karlkfi.jtyped.context.config.ResourceArgumentResolver"/>
 *   </mvc:argument-resolvers>
 * </mvc:annotation-driven>
 * </pre>
 * 
 * See org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter#getDefaultArgumentResolvers() for a list of the default handlers and ordering.
 */
public class ResourceArgumentResolver implements HandlerMethodArgumentResolver, BeanFactoryAware {

	@Autowired
	private BeanFactory beanFactory;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(ResourceParam.class) != null;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
		
		ResourceParam annotation = parameter.getParameterAnnotation(ResourceParam.class);

		Object bean;
		if (annotation.required()) {
			bean = resolveArgument(parameter, annotation);
		} else {
			try {
				return resolveArgument(parameter, annotation);
			} catch (NoSuchBeanDefinitionException e) {
				bean = null;
			}
		}
		return bean;
	}
	
	protected Object resolveArgument(MethodParameter parameter, ResourceParam annotation) {
		String name = annotation.name();
		if ("".equals(name)) {
			name = parameter.getParameterName();
		}
		
		Class<?> type = annotation.type();
		if (Object.class.equals(type)) {
			type = parameter.getParameterType();
		}
		
		return beanFactory.getBean(name, type);
	}

}
