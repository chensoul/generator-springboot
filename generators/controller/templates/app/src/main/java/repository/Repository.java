package <%= packageName %>.repository;

import <%= packageName %>.entity.<%= entityName %>;
<%_ if (persistenceType === 'jpa') { _%>
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface <%= entityName %>Repository extends JpaRepository<<%= entityName %>, Long> {}
<%_ } _%>
<%_ if (persistenceType === 'mybatis-plus') { _%>
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import <%= packageName %>.entity.<%= entityName %>;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface <%= entityName %>Repository extends BaseMapper<<%= entityName %>> {}
<%_ } _%>