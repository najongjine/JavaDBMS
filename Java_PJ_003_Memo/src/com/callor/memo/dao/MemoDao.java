package com.callor.memo.dao;

import java.util.List;

import com.callor.memo.persistence.MemoDTO;

public interface MemoDao {
	public List<MemoDTO> selectAll();
	public MemoDTO findById(long id);
	public List<MemoDTO> findBySubj(String m_subject);
	public List<MemoDTO> findByText(String m_text);
	public int insert(MemoDTO memoDTO);
	public int update(MemoDTO memoDTO);
	public int delete(long id);
}
