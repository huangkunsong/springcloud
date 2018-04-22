package com.hks.app.controller;

import com.hks.app.dao.ITestDao;
import com.hks.app.entity.User;
import com.netflix.appinfo.InstanceInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(value = "用户账户操作API", description = "用户账户操作API")
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    ITestDao iTestDao;

    @Autowired
    DiscoveryClient client;

	@RequestMapping(method=RequestMethod.GET)
	public String home(Map<String,Object> model) {
        return "home";
	}

	@ApiOperation(value = "获取所有用户", notes = "根据用户获取所有")
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public List<User> home(User user){
        System.out.println("-----------------------------------------------");
        List<ServiceInstance> list = client.getInstances("springboot");
        return iTestDao.findAllUser(user);
    }
}