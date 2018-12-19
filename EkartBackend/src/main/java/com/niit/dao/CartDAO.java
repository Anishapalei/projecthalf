package com.niit.dao;

import java.util.List;

import com.niit.model.Cart;

public interface CartDAO {

	public boolean addToCart(Cart cart);
	public boolean deleteItemFromCart(Cart cart);
	public boolean updateCartItem(Cart cart);
	public Cart getCartItem(int cartId);
	public List<Cart> listCartItems(String username);
}
