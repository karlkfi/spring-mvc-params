package karlkfi.spring.web.method.annotation;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.ImmutableList;

/**
 * Example Spring MVC Controller using AutowiredParam on RequestMapping methods parameters.
 */
@Controller
public class AutowiredParamController {
	
	@RequestMapping("/request")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView handleRequest(@RequestParam("param") String param, @AutowiredParam String textX) {
		ModelAndView mv = new ModelAndView("TestView");
		mv.addObject("param", param);
		mv.addObject("text", textX);
		return mv;
	}
	
	@RequestMapping("/requestMissing")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView handleRequestMissingParam(@RequestParam("param") String param, @AutowiredParam String textX) {
		ModelAndView mv = new ModelAndView("TestView");
		mv.addObject("param", param);
		mv.addObject("text", textX);
		return mv;
	}
	
	@RequestMapping("/requestOptional")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView handleRequestOptionalParam(@RequestParam("param") String param, @AutowiredParam(required=false) Set<String> setX) {
		ModelAndView mv = new ModelAndView("TestView");
		mv.addObject("param", param);
		mv.addObject("set", setX);
		return mv;
	}
	
	@RequestMapping("/requestDate")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView handleRequestDate(@RequestParam("param") String param, @AutowiredParam Date dateX) {
		ModelAndView mv = new ModelAndView("TestView");
		mv.addObject("param", param);
		mv.addObject("date", dateX);
		return mv;
	}
	
	@RequestMapping("/requestMap")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView handleRequestMap(@RequestParam("param") String param, @AutowiredParam Map<String, String> mapX) {
		ModelAndView mv = new ModelAndView("TestView");
		mv.addObject("param", param);
		mv.addObject("map", mapX);
		return mv;
	}
	
	@RequestMapping("/requestArrayList")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView handleRequestArrayList(@RequestParam("param") String param, @AutowiredParam List<String> listX) {
		ModelAndView mv = new ModelAndView("TestView");
		mv.addObject("param", param);
		mv.addObject("list", listX);
		return mv;
	}
	
	@RequestMapping("/requestImmutableList")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView handleRequestImmutableList(@RequestParam("param") String param, @AutowiredParam(type=ImmutableList.class) List<String> listX) {
		ModelAndView mv = new ModelAndView("TestView");
		mv.addObject("param", param);
		mv.addObject("list", listX);
		return mv;
	}
	
	@RequestMapping("/requestChild")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView handleRequestImmutableList(@RequestParam("param") String param, @AutowiredParam Child childX) {
		ModelAndView mv = new ModelAndView("TestView");
		mv.addObject("param", param);
		mv.addObject("child", childX);
		return mv;
	}
	
	@RequestMapping("/requestParent")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView handleRequestImmutableList(@RequestParam("param") String param, @AutowiredParam Parent parentX) {
		ModelAndView mv = new ModelAndView("TestView");
		mv.addObject("param", param);
		mv.addObject("parent", parentX);
		return mv;
	}

}
