package com.biz.grade.persistence.domain;

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
public class ScoreVO {
	private long s_id	;//number
	private String s_std	;//varchar2(5)
	private String st_name	;//nvarchar2(50)
	private int st_grade	;//number(1)
	private String st_dept	;//varchar2(5)
	private String d_name	;//varchar2(30)
	private String d_tel	;//varchar2(20)
	private String s_subject	;//varchar2(4)
	private String sb_name	;//nvarchar2(20)
	private int s_score	;//number(3)
}
