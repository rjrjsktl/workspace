package edu.kh.community.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// lombok 라이브러리
// - VO(value object) 또는 DTO ( Data Transfer Object )
// 에 작성되는 공통코드 ( getter/setter/생성자 ) 를 자동으로 추가해주는 라이브러리
// 롬복설치 + lib에도 롬복파일도 필요함.


// 필드는 내가 직접 작성해야함.

@Getter // getter 자동추가
@Setter // setter 자동추가
@ToString // toString 자동추가
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드 초기화하는 매개변수 생성자

public class Member {

	private int memberNo;
	private String memberEmail;
	private String memberPw;
	private String memberNickname;
	private String memberTel;
	private String memberAddress;
	private String profileImage;
	private String enrollDate;
	private String secessionFlag;

}



