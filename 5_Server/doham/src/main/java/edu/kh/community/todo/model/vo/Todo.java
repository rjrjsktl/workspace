package edu.kh.community.todo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter // getter 자동추가
@Setter // setter 자동추가
@ToString // toString 자동추가
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드 초기화하는 매개변수 생성자

public class Todo {

	private int todonum;
	private String content;
	private int memberNo;
	private String flag;

}



