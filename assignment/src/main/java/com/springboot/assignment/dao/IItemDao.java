package com.springboot.assignment.dao;

import java.util.List;

import com.springboot.assignment.entities.ItemDetails;

public interface IItemDao {

	public List<ItemDetails> getAllAvailableItems();
	public ItemDetails getById(int id);
	
	public ItemDetails save(ItemDetails item);
	public void deleteById(int id);
}
