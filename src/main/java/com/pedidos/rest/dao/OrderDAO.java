package com.pedidos.rest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pedidos.rest.model.Order;
import com.pedidos.rest.model.Orders;

@Repository
public class OrderDAO {

	private Orders list = new Orders();

	public Orders getAllOrders() {
		return list;

	}

	public void addOrder(Order order) {
		list.getOrderList().add(order);
	}

	public boolean checkOrderExist(Order order) throws Exception {
		boolean isOk = false;
		List<Order> lista = list.getOrderList();
		for(int i=0;i<lista.size();i++) {
			if(order.getId() == lista.get(i).getId()) {
				isOk = true;
			}

		}
		return isOk;
	}

	public boolean updateOrder(Order order) throws Exception {
		boolean isOk = false;
		List<Order> lista = list.getOrderList();
		for(int i=0;i<lista.size();i++) {
			if(order.getId() == lista.get(i).getId()) {
				lista.remove(i);
				lista.add(order);
				list.setOrderList(lista);
				isOk = true;
				break;
			}
		}
		return isOk;
	}
}
