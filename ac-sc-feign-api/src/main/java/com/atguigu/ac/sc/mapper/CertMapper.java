package com.atguigu.ac.sc.mapper;

import com.atguigu.ac.sc.entity.Cert;
import java.util.List;

public interface CertMapper {
    int insert(Cert record);

    List<Cert> selectAll();

	List<Cert> selectCertList(List<Integer> list);
}