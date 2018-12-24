/*package com.niit.controller;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.dao.CartDAO;
import com.niit.dao.ProductDAO;
import com.niit.model.Cart;
import com.niit.model.Product;

@Entity
@Table

@Controller
public class CartController
{
	@Autowired
	CartDAO cartDAO;
	@Autowired
	ProductDAO productDAO;
	
	@RequestMapping(value="/cart")
	public String showCart(Model m)
	{
		List<Cart>listCartItems=cartDAO.listCartItems("username");
		m.addAttribute("cartItems", listCartItems);
		m.addAttribute("grandTotal", this.calcGrandTotalPrice(listCartItems));
		
		return "Cart";
		
	}
	
	
	
	@RequestMapping(value="/addToCart/{productId}")
	public String addToCart(@PathVariable("productId")int productId,@RequestParam("")int quantity,Model m,HttpSession session)
	{
		Cart cart=new Cart();
		String username=(String)session.getAttribute("username");
		
		Product product=productDAO.getProduct(productId);
		cart.setProductId(productId);
		cart.setPrice(product.getPrice());
		cart.setProductName(product.getProductName());
		cart.setQuantity(quantity);
		cart.setStatus("N");
		cart.setUsername(username);
		
		cartDAO.addToCart(cart);
		List<Cart>listCartItems=cartDAO.listCartItems("Abhi");
		m.addAttribute("cartItems", listCartItems);
		m.addAttribute("grandTotal", this.calcGrandTotalPrice(listCartItems));
		
		
		return "Cart";
		
		
	}
	
	public int calcGrandTotalPrice(List<Cart>listCartItems)
	{
		int grandTotal=0;
		int count=0;
		while(count<listCartItems.size())
		{
			Cart cart=listCartItems.get(count);
			grandTotal=grandTotal+(cart.getQuantity()*cart.getPrice());
			count++;
			
		}
		return grandTotal;
		
	}

@RequestMapping(value="/updateCartItem/{cartId}")	
public String updateCartItem(@PathVariable("cartId")int cartId,@RequestParam("quantity")int quantity,Model m,HttpSession session)
{
	String username="Abhi";
	Cart cart = cartDAO.getCartItem(cartId);
	cart.setQuantity(quantity);
	cartDAO.updateCartItem(cart);
	List<Cart>listCartItems=cartDAO.listCartItems(username);
	m.addAttribute("cartItems", listCartItems);
	m.addAttribute("grandTotal", this.calcGrandTotalPrice(listCartItems));
	
	return "Cart";
			
}
@RequestMapping(value="/deleteCartItem/{cartId}")	
public String deleteCartItem(@PathVariable("cartId")int cartId,@RequestParam("quantity")int quantity,Model m,HttpSession session)
{
	String username="Abhi";
	Cart cart = cartDAO.getCartItem(cartId);
	cart.setQuantity(quantity);
	cartDAO.updateCartItem(cart);
	List<Cart>listCartItems=cartDAO.listCartItems(username);
	m.addAttribute("cartItems", listCartItems);
	m.addAttribute("grandTotal", this.calcGrandTotalPrice(listCartItems));
	
	
	
	
	return "Cart";
	
			
}
@RequestMapping(value="/checkout")
public String showCart(Model m,HttpSession session)
{
	List<Cart>listCartItems=cartDAO.listCartItems("username");
	m.addAttribute("cartItems", listCartItems);
	m.addAttribute("grandTotal", this.calcGrandTotalPrice(listCartItems));
	return "OrderConfirm";
	

	
}


}*/
