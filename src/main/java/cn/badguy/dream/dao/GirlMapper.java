package cn.badguy.dream.dao;

import cn.badguy.dream.pojo.Girl;
import org.springframework.stereotype.Component;

@Component
public interface GirlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Girl record);

    int insertSelective(Girl record);

    Girl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Girl record);

    int updateByPrimaryKey(Girl record);
}