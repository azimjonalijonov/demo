package com.example.demo.rest;

import com.example.demo.Entity.Item;
import com.example.demo.service.ItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ItemResource {
    private final ItemService itemService;

    public ItemResource(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/item")
    public Item post(@RequestBody Item item){
        Item i =itemService.save(item);
        return i;
    }
}
