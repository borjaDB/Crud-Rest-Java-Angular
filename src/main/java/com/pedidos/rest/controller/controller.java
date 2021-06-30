package com.pedidos.rest.controller;

import java.net.URI;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pedidos.rest.dao.OrderDAO;
import com.pedidos.rest.model.Order;
import com.pedidos.rest.model.Orders;

@RestController
@RequestMapping(path = "/orders")
public class controller {

	@Autowired
	private OrderDAO orderDao;

	private final static Logger LOGGER = Logger.getLogger(controller.class);

	//get
	@GetMapping(path="/", produces = "application/json")
	public Orders getOrders() {
		LOGGER.info("Devolviendo lista de pedidos");
		return orderDao.getAllOrders();

	}

	//add
	@PostMapping(path= "/add", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addOrder(@RequestBody Order order) throws Exception	{
		LOGGER.info("Procesando peticion post");
	
		orderDao.addOrder(order);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().userInfo("Datos procesados correctamente")
				.path("/{orderId}")
				.buildAndExpand(order.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

}

