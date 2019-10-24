package com.biz.dbms.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
/*
 * DTO | VO 필드 변수들은 DB 칼럼 이름과 똑같이 해줘야함
 */
public class BBsDTO {
	private long bs_id	;//number
	private String bs_date	;//varchar2(10 byte)
	private String bs_time	;//varchar2(10 byte)
	private String bs_writer	;//nvarchar2(20 char)
	private String bs_subject	;//nvarchar2(125 char)
	private String bs_text	;//nvarchar2(1000 char)
	private int bs_count	;//number
}
