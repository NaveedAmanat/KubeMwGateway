package com.idev4.gateway.repository;

import com.idev4.gateway.domain.MwAdcRgstr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MwAdcRgstrRepository extends JpaRepository<MwAdcRgstr, Long> {

    MwAdcRgstr findOneByAdcNm(String name);
}
