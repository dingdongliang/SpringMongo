package net.htjs.sendsys.service.impl;

import net.htjs.sendsys.mongo.Customer;
import net.htjs.sendsys.service.CustomerService;
import net.htjs.sendsys.utils.MongoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: 接口实现类（示例）
 * author  dyenigma
 * date 2016/9/12 17:29
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    MongoTemplate mongoEventsTemplate;

    @Override
    public Customer findByFirstName(String firstName) {
        return mongoEventsTemplate.findOne(new Query(Criteria.where("firstName").is(firstName)), Customer.class);
    }

    /**
     * Description: 保存数据，已经测试成功
     * methodName:save
     * Time:2016/9/23 10:11
     * param:[customer]
     * return:void
     */
    @Override
    public void save(Customer customer) {

        mongoEventsTemplate.insert(customer, MongoUtil.COLLECTION);
    }


    @Override
    public List<Customer> findByLastName(String lastName) {
        return mongoEventsTemplate.find(new Query(Criteria.where("lastName").is(lastName)), Customer.class);
    }
}
