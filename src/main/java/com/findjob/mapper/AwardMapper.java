package com.findjob.mapper;

import com.findjob.pojo.Award;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AwardMapper {
    int insertAward(Award award);
    int deleteAwards(List<Integer> list);
    int updateAward(Award award);
    List<Award> getAward(Integer resumeId);
}
