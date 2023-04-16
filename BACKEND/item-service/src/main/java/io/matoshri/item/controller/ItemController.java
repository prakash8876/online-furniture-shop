package io.matoshri.item.controller;

import io.matoshri.item.dto.ApiRequest;
import io.matoshri.item.dto.ApiResponse;
import io.matoshri.item.service.ItemService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createItem(@RequestBody ApiRequest request) {
        itemService.saveItem(request);
    }

    @GetMapping("/item-id/{item-id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse findItemById(@PathVariable("item-id") final Long itemId) {
        return itemService.findItemById(itemId);
    }

    @GetMapping("/item-name/{item-name}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse findItemByName(@PathVariable("item-name") @NonNull final String itemName) {
        return itemService.findItemByName(itemName);
    }
}
