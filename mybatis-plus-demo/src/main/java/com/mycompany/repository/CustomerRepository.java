package com.mycompany.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mycompany.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerRepository extends BaseMapper<Customer> {}
