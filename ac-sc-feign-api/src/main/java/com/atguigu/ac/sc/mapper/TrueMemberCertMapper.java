package com.atguigu.ac.sc.mapper;

import com.atguigu.ac.sc.entity.TrueMemberCert;

import java.util.ArrayList;
import java.util.List;

public interface TrueMemberCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TrueMemberCert record);

    TrueMemberCert selectByPrimaryKey(Integer id);

    List<TrueMemberCert> selectAll();

    int updateByPrimaryKey(TrueMemberCert record);

	void insertTrueMemberCertList(ArrayList<TrueMemberCert> memberCertList);
}