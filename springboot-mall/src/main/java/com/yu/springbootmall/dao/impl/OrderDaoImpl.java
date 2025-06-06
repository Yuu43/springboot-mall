package com.yu.springbootmall.dao.impl;

import com.yu.springbootmall.dao.OrderDao;
import com.yu.springbootmall.dao.UserDao;
import com.yu.springbootmall.dto.OrderQueryParams;
import com.yu.springbootmall.model.Order;
import com.yu.springbootmall.model.OrderItem;
import com.yu.springbootmall.rowmapper.OrderItemRowMapper;
import com.yu.springbootmall.rowmapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate ;

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        String sql = "select count(*) from `order` where 1=1";

        Map<String, Object> map = new HashMap<>();

        sql = addFilteringSql(sql, map, orderQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        String sql = "select order_id, user_id, total_amount, created_date, last_modified_date" +
                " from `order` where 1=1";

        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        sql = addFilteringSql(sql, map, orderQueryParams);

        // 排序
        sql = sql + " order by order_id desc";

        // 分頁
        sql = sql + " limit :limit offset :offset";
        map.put("limit", orderQueryParams.getLimit());
        map.put("offset", orderQueryParams.getOffset());

        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());
        return orderList;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "select order_id, user_id, total_amount, created_date, last_modified_date " +
                "from `order` where order_id = :orderId";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());

        if (orderList.size() > 0) {
            return orderList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
        String sql = "SELECT oi.order_item_id, oi.order_id, oi.product_id, oi.quantity, oi.amount, p.product_name, p.image_url " +
                "FROM order_item as oi " +
                "LEFT JOIN product as p ON oi.product_id = p.product_id " +
                "WHERE oi.order_id = :orderId";


        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql, map, new OrderItemRowMapper());

        return orderItemList;
    }

    @Override
    public Integer createOrder(Integer userId, Integer totalAmount) {
        String sql = "INSERT INTO `order`(user_id, total_amount, created_date, last_modified_date)" +
                "VALUES(:userId, :totalAmount, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int orderId = keyHolder.getKey().intValue();

        return orderId;
    }

    @Override
    public void createOrderItems(Integer orderId, List<OrderItem> orderItemsList) {

          // 使用 for  loop 一條一條 sql 加入數據，效率較低
//          for (OrderItem orderItem : orderItemsList) {
//
//              String sql = "INSERT INTO order_item(order_id, product_id, quantity, amount)"
//                      + "VALUES(:orderId, :productId, :quantity, :amount)";
//
//              Map<String, Object> map = new HashMap<>();
//              map.put("orderId", orderId);
//              map.put("productId", orderItem.getProductId());
//              map.put("quantity", orderItem.getQuantity());
//              map.put("amount", orderItem.getAmount());
//
//              KeyHolder keyHolder = new GeneratedKeyHolder();
//
//              namedParameterJdbcTemplate.update(sql, map);
//          }


          // 使用 batchUpdate 一次性加入數據，效率更高
            String sql = "INSERT INTO order_item(order_id, product_id, quantity, amount)"
                    + "VALUES(:orderId, :productId, :quantity, :amount)";

            MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderItemsList.size()];

            for (int i = 0; i < orderItemsList.size(); i++) {
                OrderItem orderItem = orderItemsList.get(i);

                parameterSources[i] = new MapSqlParameterSource();
                parameterSources[i].addValue("orderId", orderId);
                parameterSources[i].addValue("productId", orderItem.getProductId());
                parameterSources[i].addValue("quantity", orderItem.getQuantity());
                parameterSources[i].addValue("amount", orderItem.getAmount());

            }

            namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
    }

    private String addFilteringSql(String sql, Map<String, Object> map, OrderQueryParams orderQueryParams) {
        if(orderQueryParams.getUserId() != null) {
            sql = sql + " and user_id = :userId";
            map.put("userId", orderQueryParams.getUserId());
        }
        return sql;
    }
}
