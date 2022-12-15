package com.example.demo.rest;

import com.example.demo.Entity.Item;
import com.example.demo.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("get")
    public ResponseEntity find(@PathVariable int id){
        Item res = itemService.find(id);
        return ResponseEntity.ok(res);
    }


}
