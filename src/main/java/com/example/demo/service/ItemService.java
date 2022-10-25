package com.example.demo.service;

import com.example.demo.Entity.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    public Item save(Item item){
        return itemRepository.save(item);
    }
}
