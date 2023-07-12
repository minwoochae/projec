package com.example.projec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projec.dao.ProjecDao;
import com.example.projec.dto.Projec;

@Service
public class ProjecServiceImpl implements ProjecService {
	@Autowired
	private ProjecDao projecMapper;

	@Override
	public int maxNum() throws Exception {
		return projecMapper.maxNum();
	}

	@Override
	public void insertData(Projec Projec) throws Exception {
		
		projecMapper.insertData(Projec);
	}

	@Override
	public int getDataCount(String searchKey, String searchValue) throws Exception {
		return projecMapper.getDataCount(searchKey, searchValue);
	}

	@Override
	public List<Projec> getLists(String searchKey, String searchValue, int start, int end) throws Exception {
		return projecMapper.getLists(searchKey, searchValue, start, end);
	}

	@Override
	public void updateHitCount(int num) throws Exception {
		projecMapper.updateHitCount(num);		
	}

	@Override
	public Projec getReadData(int num) throws Exception {
		return  projecMapper.getReadData(num);
	}

	@Override
	public void updateData(Projec projec) throws Exception {
		projecMapper.updateData(projec);		
	}

	@Override
	public void deleteData(int num) throws Exception {
		projecMapper.deleteData(num);			
	}
	
}
