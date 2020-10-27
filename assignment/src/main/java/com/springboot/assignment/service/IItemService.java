package com.springboot.assignment.service;

import java.util.List;

import com.springboot.assignment.entities.ItemDetails;

public interface IItemService {
	
	public List<ItemDetails> getAllAvailableItems();
	public ItemDetails getItemById(int itemId);
	
	public ItemDetails saveItem(ItemDetails item);
	public void deleteItemById(int itemId);
	
	
	public void reCalculateStock(int itemId, int stockBought);

}
