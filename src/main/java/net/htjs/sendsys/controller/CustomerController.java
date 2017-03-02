package net.htjs.sendsys.controller;

import net.htjs.sendsys.annotation.GetParams;
import net.htjs.sendsys.annotation.LogRecord;
import net.htjs.sendsys.mongo.Customer;
import net.htjs.sendsys.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description: 接口调用控制类（示例）
 * author  dyenigma
 * date 2016/9/12 17:35
 */
@Controller
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @ResponseBody
    @RequestMapping(value = "/getAllCustomer")
    public List<Customer> getAllCustomer() {
        return customerService.findByLastName("Alen");
    }

    /**
     * Description: 正则表达式获取传递过来的参数，注意格式保持一致
     * methodName:insertCustomer
     * Time:2016/9/23 10:09
     * param:[]
     * return:java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = "/insertCustomer", produces = "application/json;charset=utf-8")
    public String insertCustomer() {

        Customer customer = new Customer("firstName", "lastName");
        customerService.save(customer);
        return "success";
    }


    @RequestMapping(value = "/demo")
    @GetParams
    @LogRecord(description = "实验方法")
    public void demo(HttpServletRequest request) {
        logger.info(request.getSession().getAttribute("userInfo").toString());
    }
}
