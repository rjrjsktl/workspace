package toypjt1.yun.note.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Note {
	private int noteNo;
	private String noteTitle;
	private String noteMemo;
	private String noteDate;
	private String noteSession;
}
