package com.theyoseph.DemoSupermercado.service;

import com.theyoseph.DemoSupermercado.dto.BranchStatsDTO;
import com.theyoseph.DemoSupermercado.dto.ProductStatsDTO;

public interface IStatsService {

    ProductStatsDTO getTopProduct();
    BranchStatsDTO getTopBranch();

}
