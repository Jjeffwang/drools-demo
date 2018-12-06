package com.learn.droolsdemo.controller;

import com.learn.droolsdemo.domain.Message;
import com.learn.droolsdemo.domain.Order;
import com.learn.droolsdemo.domain.Result;
import com.learn.droolsdemo.domain.User;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chengyong.wang
 * @Date: 2018/9/30 上午10:43
 * @Description:
 */
@RestController
public class TestController {

    @Resource
    private KieContainer kieContainer;

    @ResponseBody
    @RequestMapping("/address")
    public void test() {
        Message message = new Message();
        message.setMessage("Hello World");
        message.setStatus(Message.HELLO);

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(message);
        kieSession.getAgenda().getAgendaGroup("message-group").setFocus();
        int ruleFiredCount = kieSession.fireAllRules();
        kieSession.destroy();
        System.out.println("触发了" + ruleFiredCount + "条规则");

    }

    @ResponseBody
    @RequestMapping("/order")
    public void person() {
        List list =new ArrayList();
        Order order = new Order();
        order.setAmount(1000);
        User user = new User(2018, "vip", 0);
        order.setUser(user);
        Result result = new Result();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("myGlobalList", list);
        kieSession.insert(order);
        kieSession.insert(result);
        int ruleFiredCount = kieSession.fireAllRules();
        //从global获取数据
        list= (List) kieSession.getGlobal("myGlobalList");
        System.out.println(list.get(0));
        kieSession.destroy();
        if(result.isCheckResult()){
            System.out.println("user score:"+user.getUserScore());
        }
        System.out.println("触发了" + ruleFiredCount + "条规则");

    }

    /**
     * 生成随机数
     *
     * @param num
     * @return
     */
    public String generateRandom(int num) {
        String chars = "0123456789";
        StringBuffer number = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int rand = (int) (Math.random() * 10);
            number = number.append(chars.charAt(rand));
        }
        return number.toString();
    }
}
