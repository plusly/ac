package com.atguigu.ac.sc.mapper;

import com.atguigu.ac.sc.entity.MemberCert;
import java.util.List;

public interface MemberCertMapper {
    int insert(MemberCert record);

    List<MemberCert> selectAll();

	List<Integer> selectListCertIdByAccType(Byte accType);
}