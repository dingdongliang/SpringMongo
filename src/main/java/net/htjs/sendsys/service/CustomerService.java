package net.htjs.sendsys.service;

import net.htjs.sendsys.mongo.Customer;

import java.util.List;

/**
 * Description: 示例接口
 * author  dyenigma
 * date 2016/9/12 17:26
 */
public interface CustomerService {

    Customer findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);

    void save(Customer customer);

}
