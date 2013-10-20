spring-mvc-params
=================

Spring RequestMapping method parameter injection.

## Description
This project provides two annotations: AutowiredParam & ResourceParam

These can be used to 'inject' Spring context beans into <a href="http://docs.spring.io/spring/docs/3.2.x/javadoc-api/org/springframework/web/bind/annotation/RequestMapping.html">RequestMapping</a> method arguments.

Spring already provides injection for many types of method parameters, but these annotations allows you to add your own without writing additional method argument resolvers.

## Examples

```java
@Controller
public class AutowiredParamController {
	
	/**  
	 * Handles A requests
	 * @param param an example url parameter
	 * @param messageSource the MessageSource from the application context, 
	 * 						injected by type (MessageSource)
	 */
	@RequestMapping("/requestA")
	public void handleRequest(@RequestParam("param") String param, 
							  @AutowiredParam MessageSource messageSource) {
		...
	}
	
	/**  
	 * Handles B requests
	 * @param param an example url parameter
	 * @param messageSource the ExpensiveService from the application context, 
	 * 						injected by name ("namedService")
	 */
	@RequestMapping("/requestB")
	public void handleRequest(@RequestParam("param") String param, 
							  @ResourceParam ExpensiveService namedService) {
		...
	}
	
}
```

## Use Cases
Normally you'll want to just inject shared services like <a href="http://docs.spring.io/spring/docs/3.2.x/javadoc-api/org/springframework/context/MessageSource.html">MessageSource</a> or an "ExpensiveService" into the <a href="http://docs.spring.io/spring/docs/3.2.x/javadoc-api/org/springframework/stereotype/Controller.html">Controller</a>, not the request handling method, but sometimes it makes more sense to inject directly into the method and only into that method.

- An "expensive to construct" service that you want to lazily instantiate only if the request is made
- A more intuitive way to inject scoped beans (ex: session, request, prototype)
- Clean up your controller when a bean is only required by one of the requests
- Inject different named resources into different request handlers

##Limitations
Spring only allows you to add custom <a href="http://docs.spring.io/spring/docs/3.2.x/javadoc-api/org/springframework/web/method/support/HandlerMethodArgumentResolver.html">HandlerMethodArgumentResolver</a>s into one place in the default list of resolvers, near the end. So most default resolvers are tried first. For example, <a href="http://docs.spring.io/spring/docs/3.1.x/javadoc-api/org/springframework/web/method/annotation/MapMethodProcessor.html">MapMethodProcessor</a> populates <a href="http://docs.oracle.com/javase/7/docs/api/java/util/Map.html">Map<a/> arguments with the <a href="http://docs.spring.io/spring/docs/3.1.x/javadoc-api/org/springframework/ui/ModelMap.html">ModelMap</a>. So you can't inject other Maps from the context.

##License
This project is licensed under Apache License, Version 2.0. <br/>
See LICENSE for details or <http://www.apache.org/licenses/LICENSE-2.0.html><br/>
Copyright Karl Isenberg (2013)

Any contributed work must be authorized by the copyright owner and be compatible with the License.