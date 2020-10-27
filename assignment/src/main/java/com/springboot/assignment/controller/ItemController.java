package com.springboot.assignment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.assignment.entities.ItemDetails;
import com.springboot.assignment.service.IItemService;
import com.springboot.assignment.utils.ValidationUtils;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	IItemService itemService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	@GetMapping("/listAll")
	public String getAllItems(Model model){
		model.addAttribute("items", itemService.getAllAvailableItems());	
		return "item/items-list";
	}
	
	
	@GetMapping("/save/form")
	public String addItemForm(Model model){
		model.addAttribute("item", new ItemDetails());
		return "item/save-item";
	}
	
	
	@PostMapping("/save")
	public String saveItem(@Valid @ModelAttribute("item") ItemDetails item, BindingResult bindingResult){
		System.out.println(item);
		if(ValidationUtils.checkForErrors(bindingResult)) {
			return "item/save-item";
		}
			
		itemService.saveItem(item);
		return "redirect:/item/listAll";
	}
	
	@GetMapping("/update/form")
	public String updateItemForm(@RequestParam("itemId") int itemId, Model model){
		
		ItemDetails item = itemService.getItemById(itemId);
		model.addAttribute("item", item);
		return "item/save-item";
	}
	
	@GetMapping("/delete")
	public String deleteItem(@RequestParam("itemId") int itemId){
		
		itemService.deleteItemById(itemId);
		return "redirect:/item/listAll";
	}
	
}
