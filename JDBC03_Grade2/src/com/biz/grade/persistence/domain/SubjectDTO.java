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
public class SubjectDTO {
	private String sb_code	;//VARCHAR2(4 BYTE)
	private String sb_name	;//NVARCHAR2(20 CHAR)
	private String sb_pro	;//NVARCHAR2(20 CHAR)
}
