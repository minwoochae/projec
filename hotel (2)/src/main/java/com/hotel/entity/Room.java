package com.hotel.entity;

import java.time.LocalDateTime;

import com.hotel.constant.Availability;
import com.hotel.constant.ReservationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity //엔티티 클래스로 정의
@Table(name="room") //테이블 이름 지정
@Setter
@Getter
@ToString
public class Room {
	@Id
	@Column(name="room_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int roomNum;
	
	private String roomName;
	
	private int capacity;
	
	private int price;
	
	@Enumerated(EnumType.STRING)
	private Availability availability;
}
