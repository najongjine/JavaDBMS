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
public class CbtDTO {
	private long cb_seq	;//NUMBER
	private String cb_question	;//NVARCHAR2(125 CHAR)
	private String cb_answer1	;//NVARCHAR2(50 CHAR)
	private String cb_answer2	;//NVARCHAR2(50 CHAR)
	private String cb_answer3	;//NVARCHAR2(50 CHAR)
	private String cb_answer4	;//NVARCHAR2(50 CHAR)
	private List<String> ansList;
	private String chosenAns;
	private String cb_correct_answer	;//NVARCHAR2(50 CHAR)
	{
		ansList=new ArrayList<String>();
	}
}
