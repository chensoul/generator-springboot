package com.mycompany.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mycompany.entity.Customer;
import com.mycompany.exception.CustomerNotFoundException;
import com.mycompany.mapper.CustomerMapper;
import com.mycompany.model.query.CustomerQuery;
import com.mycompany.model.request.CustomerRequest;
import com.mycompany.model.response.CustomerResponse;
import com.mycompany.repository.CustomerRepository;
import com.mycompany.util.PageUtils;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CustomerService extends ServiceImpl<CustomerRepository, Customer> {
    @Resource
    private CustomerMapper customerMapper;

    public Page<CustomerResponse> findAllCustomers(CustomerQuery customerQuery) {
        IPage<Customer> customersPage = page(PageUtils.fromPageRequest(customerQuery.pageRequest()));

        List<CustomerResponse> customerResponseList = customerMapper.toResponseList(customersPage.getRecords());

        return new PageImpl(customerResponseList, customerQuery.pageRequest(), customersPage.getTotal());
    }

    public Optional<CustomerResponse> findCustomerById(Long id) {
        return Optional.ofNullable(getById(id)).map(customerMapper::toResponse);
    }

    @Transactional
    public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
        Customer customer = customerMapper.toEntity(customerRequest);
        baseMapper.insert(customer);
        return customerMapper.toResponse(customer);
    }

    @Transactional
    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        Customer customer = Optional.of(baseMapper.selectById(id)).orElseThrow(() -> new CustomerNotFoundException(id));
        customerMapper.mapCustomerWithRequest(customer, customerRequest);
        baseMapper.updateById(customer);

        return customerMapper.toResponse(customer);
    }

    @Transactional
    public void deleteCustomerById(Long id) {
        baseMapper.deleteById(id);
    }
}
