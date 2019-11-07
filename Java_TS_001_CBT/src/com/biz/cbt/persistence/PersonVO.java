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
public class PersonVO {
	List<ProblemVO> problemList;
	int point=0;
	int solvedCount=0;
	{
		problemList=new ArrayList<ProblemVO>();
	}
}
