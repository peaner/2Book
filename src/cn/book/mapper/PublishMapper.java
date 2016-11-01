package cn.book.mapper;

import cn.book.po.Publish;
import cn.book.po.PublishExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PublishMapper {
    int countByExample(PublishExample example);

    int deleteByExample(PublishExample example);

    int deleteByPrimaryKey(Integer publishId);

    int insert(Publish record);

    int insertSelective(Publish record);

    List<Publish> selectByExample(PublishExample example);

    Publish selectByPrimaryKey(Integer publishId);

    int updateByExampleSelective(@Param("record") Publish record, @Param("example") PublishExample example);

    int updateByExample(@Param("record") Publish record, @Param("example") PublishExample example);

    int updateByPrimaryKeySelective(Publish record);

    int updateByPrimaryKey(Publish record);
}