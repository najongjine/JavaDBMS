package com.biz.grade.persistence;

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
/*
 * VO(Value Object), DTO(Data Transfer Object)
 * -공통기능: crud 수행시 데이터를 담아서 method간에 이동할때 사용.
 * -DTO: 물리적 table과 연관(매핑)되어 완전한 CRUD를 수행할때
 * -VO: View table|join된 sql과 연관되어 주로 READ.
 */
public class ScoreDTO {
	private long s_id;
	private String s_std;//학번
	private String s_subject;//과목코드
	private int s_score;//과목점수
	private String s_rem; //비고
}
