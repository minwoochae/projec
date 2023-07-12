package com.hotel.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hotel.dto.MemberFormDto;
import com.hotel.entity.Member;
import com.hotel.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	//문의
	@GetMapping(value="/member/contact")
	public String contact() {
		return "member/contact";
	}
	
	//로그인
	@GetMapping(value="/member/login")
	public String login() {
		return "member/loginForm";
	}
	
	//회원가입 화면
	@GetMapping(value = "/member/signUp")
	public String signUp(Model model) {
		model.addAttribute("memberFormDto" , new MemberFormDto());
		return "member/signUpForm";
	}
	
	//회원가입
	@PostMapping(value="/member/signUp")
	public String signUp(@Valid MemberFormDto memberFormDto,BindingResult bindingResult, Model model ) {
		//@Valid: 유효성을 검증하려는 객체 앞에 붙인다.
		//BindingResult: 유효성 검증 후의 결과가 들어있다.
		
		if(bindingResult.hasErrors()) {
			//에러가 있다면 회원가입 페이지로 이동
			return "member/signUpForm";			
		}
		
		
		try {
			//MemberFormDto -> Member Entity, 비밀번호 암호화			
		Member member = Member.createMember(memberFormDto, passwordEncoder);
		memberService.saveMember(member);	
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/signUpForm";
		}
		
		return "redirect:/";		

	}
	
	//로그인 실패했을때
	@GetMapping(value = "/member/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요.");
		return "member/loginForm";
	}
}
