package com.truckplast.analyzer.repository;

import com.truckplast.analyzer.entity.MailInfo;

import java.util.List;

public interface MailInfoRepository {

    int[] saveAll(List<MailInfo> mailInfoList);
}
