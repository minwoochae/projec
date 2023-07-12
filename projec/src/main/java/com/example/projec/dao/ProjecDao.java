package com.example.projec.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.example.projec.dto.Projec;

public interface ProjecDao {
	@Mapper
		public int maxNum() throws Exception;

		public void insertData(Projec projec) throws Exception;

		public int getDataCount(String searchKey, String searchValue) throws Exception;

		public List<Projec> getLists(String searchKey, String searchValue, int start, int end) throws Exception;

		public void updateHitCount(int num) throws Exception;

		public Projec getReadData(int num) throws Exception;

		public void updateData(Projec projec) throws Exception;

		public void deleteData(int num) throws Exception;
}
