package karlkfi.spring.web.method.annotation;

import java.util.Date;
import java.util.List;
import java.util.Map;

import karlkfi.spring.web.method.annotation.ResourceParam;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.ImmutableList;

/**
 * Example Spring MVC Controller using ResourceParam on RequestMapping methods parameters.
 */
@Controller
public class ResourceParamController {
	
	@RequestMapping("/request")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView handleRequest(@RequestParam("param") String param, @ResourceParam String text1) {
		ModelAndView mv = new ModelAndView("TestView");
		mv.addObject("param", param);
		mv.addObject("text", text1);
		return mv;
	}
	
	@RequestMapping("/requestMissing")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView handleRequestMissingParam(@RequestParam("param") String param, @ResourceParam String text2) {
		ModelAndView mv = new ModelAndView("TestView");
		mv.addObject("param", param);
		mv.addObject("text", text2);
		return mv;
	}
	
	@RequestMapping("/requestOptional")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView handleRequestOptionalParam(@RequestParam("param") String param, @ResourceParam(required=false) String text2) {
		ModelAndView mv = new ModelAndView("TestView");
		mv.addObject("param", param);
		mv.addObject("text", text2);
		return mv;
	}
	
	@RequestMapping("/requestDate")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView handleRequestDate(@RequestParam("param") String param, @ResourceParam Date date1) {
		ModelAndView mv = new ModelAndView("TestView");
		mv.addObject("param", param);
		mv.addObject("date", date1);
		return mv;
	}
	
	@RequestMapping("/requestMap")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView handleRequestMap(@RequestParam("param") String param, @ResourceParam Map<String, String> map1) {
		ModelAndView mv = new ModelAndView("TestView");
		mv.addObject("param", param);
		mv.addObject("map", map1);
		return mv;
	}
	
	@RequestMapping("/requestArrayList")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView handleRequestArrayList(@RequestParam("param") String param, @ResourceParam List<String> list1) {
		ModelAndView mv = new ModelAndView("TestView");
		mv.addObject("param", param);
		mv.addObject("list", list1);
		return mv;
	}
	
	@RequestMapping("/requestImmutableList")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView handleRequestImmutableList(@RequestParam("param") String param, @ResourceParam(type=ImmutableList.class, name="list2") List<String> listX) {
		ModelAndView mv = new ModelAndView("TestView");
		mv.addObject("param", param);
		mv.addObject("list", listX);
		return mv;
	}

}
