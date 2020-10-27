package com.springboot.assignment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.assignment.dao.IItemDao;
import com.springboot.assignment.entities.ItemDetails;
import com.springboot.assignment.service.IItemService;

@Service
public class ItemService implements IItemService {

	@Autowired
	IItemDao iItemDao;
	
	@Override
	@Transactional
	public List<ItemDetails> getAllAvailableItems() {
		return iItemDao.getAllAvailableItems();
	}

	@Override
	@Transactional
	public ItemDetails getItemById(int itemId) {
		return iItemDao.getById(itemId);
	}

	@Override
	@Transactional
	public ItemDetails saveItem(ItemDetails item) {
		return iItemDao.save(item);
	}

	@Override
	@Transactional
	public void deleteItemById(int itemId) {
		iItemDao.deleteById(itemId);
	}
	
	@Override
	@Transactional
	public void reCalculateStock(int itemId, int stockBought) {
		ItemDetails itemDetails = iItemDao.getById(itemId);
		itemDetails.setStockQuantity( itemDetails.getStockQuantity() - stockBought);
		iItemDao.save(itemDetails);
	}

}
