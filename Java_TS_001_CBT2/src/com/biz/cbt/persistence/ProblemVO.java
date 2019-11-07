package com.biz.cbt.persistence;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProblemVO {
	private long cb_seq	;
	private String question;
	private List<String> answers;
	private String chosenAns;
	private String correctAns;
	{
		answers=new ArrayList<String>();
	}
}
