package com.shop.base.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shop.base.entity.JokeImgModel;

@Controller
@RequestMapping("/")
public class LoginAction {
	@RequestMapping("/login")
	@ResponseBody
	public ModelAndView index(JokeImgModel jokeImg) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("base/login");
		return null;
	}
}
