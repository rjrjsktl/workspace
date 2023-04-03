package edu.kh.community.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Lombok 라이브러리
// - VO(Value Object) 또는 DTO(Data Transfer Object)
// 에 작성되는 공통코드(getter/setter/생성자)를 자동 추가해주는 라이브러리

@Getter // getter 자동추가
@Setter // setter 자동추가
@ToString // tostring 자동추가
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
//	
//	// 기본 생성자
//	public Member() {}
//	
//	public Member(int memberNo, String memberEmail, String memberPw, String memberNickname, String memberTel,
//			String memberAddress, String profileImage, String enrollDate, String secessionFlag) {
//		super();
//		this.memberNo = memberNo;
//		this.memberEmail = memberEmail;
//		this.memberPw = memberPw;
//		this.memberNickname = memberNickname;
//		this.memberTel = memberTel;
//		this.memberAddress = memberAddress;
//		this.profileImage = profileImage;
//		this.enrollDate = enrollDate;
//		this.secessionFlag = secessionFlag;
//	}
//	public int getMemberNo() {
//		return memberNo;
//	}
//	public void setMemberNo(int memberNo) {
//		this.memberNo = memberNo;
//	}
//	public String getMemberEmail() {
//		return memberEmail;
//	}
//	public void setMemberEmail(String memberEmail) {
//		this.memberEmail = memberEmail;
//	}
//	public String getMemberPw() {
//		return memberPw;
//	}
//	public void setMemberPw(String memberPw) {
//		this.memberPw = memberPw;
//	}
//	public String getMemberNickname() {
//		return memberNickname;
//	}
//	public void setMemberNickname(String memberNickname) {
//		this.memberNickname = memberNickname;
//	}
//	public String getMemberTel() {
//		return memberTel;
//	}
//	public void setMemberTel(String memberTel) {
//		this.memberTel = memberTel;
//	}
//	public String getMemberAddress() {
//		return memberAddress;
//	}
//	public void setMemberAddress(String memberAddress) {
//		this.memberAddress = memberAddress;
//	}
//	public String getProfileImage() {
//		return profileImage;
//	}
//	public void setProfileImage(String profileImage) {
//		this.profileImage = profileImage;
//	}
//	public String getEnrollDate() {
//		return enrollDate;
//	}
//	public void setEnrollDate(String enrollDate) {
//		this.enrollDate = enrollDate;
//	}
//	public String getSecessionFlag() {
//		return secessionFlag;
//	}
//	public void setSecessionFlag(String secessionFlag) {
//		this.secessionFlag = secessionFlag;
//	}
//	
//	@Override
//	public String toString() {
//		return "Member [memberNo=" + memberNo + ", memberEmail=" + memberEmail + ", memberPw=" + memberPw
//				+ ", memberNickname=" + memberNickname + ", memberTel=" + memberTel + ", memberAddress=" + memberAddress
//				+ ", profileImage=" + profileImage + ", enrollDate=" + enrollDate + ", secessionFlag=" + secessionFlag
//				+ "]";
//	}
//	
//}
