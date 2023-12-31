package cn.itcast.order.service;


import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserClient userClient;
    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //2.用feign远程调用
        User user = userClient.findById(order.getUserId());
        //3.封装user到Order
        order.setUser(user);
        // 4.返回
        return order;
    }
//    @Autowired
//    private RestTemplate restTemplate;
//
//    public Order queryOrderById(Long orderId) {
//        // 1.查询订单
//        Order order = orderMapper.findById(orderId);
//        //2.利用resttemplate发起http请求，查询用户
//        String url = "http://userserver/user/" + order.getUserId();
//        User user = restTemplate.getForObject(url, User.class);
//        //3.封装user到order
//        order.setUser(user);
//        // 4.返回
//        return order;
//    }
}
