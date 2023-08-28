package com.spottag.app.domain.mapper;

import com.spottag.app.domain.vo.TagInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface TagMapper {
    /**
     * 위도 경도를 기준으로 between (longitude - distance) and (longitude + distance) 위치에 있는 Tag 정보 조회
     *
     * @param distance  기준 거리
     * @param latitude  위도
     * @param longitude 경도
     * @return
     */
    List<TagInfoVo> findAllTagInfo(
            @Param(value = "distance") Double distance,
            @Param(value = "latitude") String latitude,
            @Param(value = "longitude") final String longitude
    );
}
