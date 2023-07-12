package com.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoomController {
	
	//객실리스트
	@GetMapping(value="/rooms/roomList")
	public String roomList() {
		return "rooms/roomList";
	}
	
	//객실상세정보
	@GetMapping(value="/rooms/roomDetails")
	public String roomDetails() {
		
		return "rooms/roomDetails";
	}
	
	//프로모션
	@GetMapping(value="/rooms/promotions")
	public String promotions() {
		
		return "rooms/promotions";
	}

}
