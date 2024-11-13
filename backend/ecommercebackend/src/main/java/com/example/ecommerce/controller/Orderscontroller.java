package com.example.ecommerce.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.data.util.NullableWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.model.Adress;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Cartitem;
import com.example.ecommerce.model.Orderdto;
import com.example.ecommerce.model.Orderitem;
import com.example.ecommerce.model.Orders;
import com.example.ecommerce.model.Users;
import com.example.ecommerce.repository.Adressrepo;
import com.example.ecommerce.repository.Cartitemrepo;
import com.example.ecommerce.repository.Cartrepo;
import com.example.ecommerce.repository.Orderrepo;
import com.example.ecommerce.repository.Userrepo;

@RestController
@RequestMapping("/api/orders")
public class Orderscontroller {
	
	private Cartrepo cartrepo;
	
	private Cartitemrepo cartitemrepo;
	
	
	private Orderrepo orderrepo;

	private Userrepo userrepo;
	
	private Adressrepo adressrepo;
	
	private Orderitemrepo orderitemrepo;
	
	
	@Autowired
	 public Orderscontroller(Cartrepo cartrepo, Cartitemrepo cartitemrepo, Orderrepo orderrepo, Userrepo userrepo,
				Adressrepo adressrepo, Orderitemrepo orderitemrepo) {
			super();
			this.cartrepo = cartrepo;
			this.cartitemrepo = cartitemrepo;
			this.orderrepo = orderrepo;
			this.userrepo = userrepo;
			this.adressrepo = adressrepo;
			this.orderitemrepo = orderitemrepo;
		}
	
     
	@PostMapping("/save/{id}")
	private void placeorder(@PathVariable("id") int id) {

	    Users users = userrepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

	    List<Adress> adresses = adressrepo.findByUsersId(users.getId());
	    Adress adress = adresses.get(0);

	    Cart cart = cartrepo.findByUsers(users);

	    // Remove the duplicate check that prevents placing multiple orders with the same cart and address
	     Orders existingOrder = orderrepo.findByUsersAndCartAndAdress(users, cart, adress);
	     if (existingOrder != null) {
	         throw new RuntimeException("Order already placed with this cart and address");
	     }

	    // Creating a new order
	    Orders orderlist = new Orders();
	    orderlist.setUsers(users);
	    orderlist.setAdress(adress);
	    orderlist.setPaymenttype("Cash on delivery");
	    orderlist.setExpectedDate(new Date());
	    orderlist.setOrderstatus("Placed");
	    orderlist.setCart(cart);

	    orderrepo.save(orderlist);

	    // Copy Cart Items to Order Items
	    List<Cartitem> cartItems = cartitemrepo.findByCart(cart);
	    for (Cartitem cartItem : cartItems) {
	        Orderitem orderitem = new Orderitem();
	        orderitem.setOrder(orderlist);
	        orderitem.setPrice(cartItem.getTotalprice());  // Use the item's total price, not cart total

	        orderitem.setProduct(cartItem.getProduct());
	        orderitem.setQuantity(cartItem.getQuantity());
	        orderitemrepo.save(orderitem); // Save each order item correctly
	    }

	    // Clear the cart after order is placed
	    clearCart(cart);
	    cartrepo.delete(cart);
	}


	private void clearCart(Cart cart) {
	        List<Cartitem> cartItems = cartitemrepo.findByCart(cart);

	        // Delete each cart item
	        for (Cartitem cartItem : cartItems) {
	            cartitemrepo.delete(cartItem);
	        }

	        // Clear cart totals
	        cart.setTotal(0);
	        cart.setTax(0);
	        cartrepo.save(cart);
	    }

	@GetMapping("getall/{userid}")
	private List<Orderdto> getallOrders(@PathVariable("userid") int id){
		
		Users user = userrepo.findById(id).orElseThrow(null);
		
   
		List<Orders> totalorders = orderrepo.findByUsers(user);
		List<Orderdto> responseList = new ArrayList<>();
		for(Orders order:totalorders) {
			
			 Orderdto dto = new Orderdto();
			 
			 dto.setAdress(order.getAdress());
			 dto.setExpectedDate(order.getExpectedDate());
			 dto.setOrderStatus(order.getOrderstatus());
			 dto.setPaymentType(order.getPaymenttype());
			 
			  List<Orderitem> orderItems = orderitemrepo.findByOrder(order);
			
			dto.setOrderitems(orderItems);
			
			responseList.add(dto);
			
			
		}

		
		return responseList;
		
	}
	
	
  

}
