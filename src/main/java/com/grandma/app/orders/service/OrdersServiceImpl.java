package com.grandma.app.orders.service;

import com.grandma.app.clients.exception.ClientNotFoundException;
import com.grandma.app.clients.repository.ClientsRepository;
import com.grandma.app.orders.dto.OrderDto;
import com.grandma.app.orders.exception.OrderNotFoundException;
import com.grandma.app.orders.mapper.OrderMapper;
import com.grandma.app.orders.entity.OrderEntity;
import com.grandma.app.orders.repository.OrdersRepository;
import com.grandma.app.products.exception.ProductNotFoundException;
import com.grandma.app.products.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final OrderMapper orderMapper;
    private final ClientsRepository clientsRepository;
    private final ProductsRepository productsRepository;

    public OrdersServiceImpl(OrdersRepository ordersRepository, OrderMapper orderMapper,
            ClientsRepository clientsRepository,
            ProductsRepository productsRepository) {
        this.ordersRepository = ordersRepository;
        this.orderMapper = orderMapper;
        this.clientsRepository = clientsRepository;
        this.productsRepository = productsRepository;
    }

    public OrderEntity createOrder(OrderDto orderDto) {
        var foundClient = clientsRepository.findByDocument(orderDto.getClientDocument())
                .orElseThrow(() -> new ClientNotFoundException("ClientNotFoundException"));

        var existsProduct = productsRepository.findByUuid(orderDto.getProductUuid())
                .orElseThrow(() -> new ProductNotFoundException("ProductNotFoundException"));

        OrderEntity orderEntity = orderMapper.orderDtoToOrderEntity(orderDto);

        orderEntity.setClientUuid(foundClient.getUuid());

        var price = existsProduct.getPrice();

        // Calculate subtotal: convertir a privado
        BigDecimal subTotal = price.multiply(BigDecimal.valueOf(orderEntity.getQuantity()));
        subTotal = subTotal.setScale(2, RoundingMode.HALF_UP);

        // Calculate tax (19% IVA): convertir a privado
        BigDecimal tax = subTotal.multiply(new BigDecimal("0.19")).setScale(2, RoundingMode.HALF_UP);

        // Calculate grand total: convertir a privado
        BigDecimal grandTotal = subTotal.add(tax).setScale(2, RoundingMode.HALF_UP);

        // mapper to setData
        orderEntity.setCreationDateTime(LocalDateTime.now());
        orderEntity.setSubTotal(subTotal);
        orderEntity.setTax(tax);
        orderEntity.setGrandTotal(grandTotal);
        orderEntity.setDelivered(false);
        orderEntity.setDeliveredDate(null);

        return ordersRepository.save(orderEntity);
    }

    public OrderEntity updateOrder(String uuid, LocalDateTime timestamp) {
        if (!ordersRepository.existsByUuid(uuid)) {
            throw new OrderNotFoundException("Order with String " + uuid + " does not exist");
        }

        OrderEntity order = ordersRepository.findById(uuid)
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order with String %s not found", uuid)));

        order.setDelivered(true);
        order.setDeliveredDate(timestamp);

        return ordersRepository.save(order);
    }
}
