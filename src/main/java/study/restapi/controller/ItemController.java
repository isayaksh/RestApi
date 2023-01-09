package study.restapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import study.restapi.form.ItemForm;
import study.restapi.service.ItemService;
import study.restapi.service.dto.BookDto;
import study.restapi.service.dto.ItemDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
@Slf4j
public class ItemController {

    private final ItemService itemService;

    // POST : localhost:8080/items/book
    // POST : localhost:8080/items/movie
    @PostMapping("/{type}")
    public ItemDto saveBook(@PathVariable String type,
                            @RequestBody ItemForm form){
        return itemService.save(form, type);
    }

    // GET : localhost:8080/items
    @GetMapping
    public List<ItemDto> findAll(){
        return itemService.findAll();
    }

    // GET : localhost:8080/items/itemId
    @GetMapping("/{itemId}")
    public ItemDto findOne(@PathVariable Long itemId){
        return itemService.findOne(itemId);
    }

    // PATCH : localhost:8080/items/itemId
    @PatchMapping("/{itemId}")
    public ItemDto update(@PathVariable Long memberId,
                          @RequestBody ItemForm form){
        return itemService.update(memberId, form);
    }


    // DELETE : localhost:8080/items/itemId
    @DeleteMapping("/{itemId}")
    public void delete(@PathVariable Long memberId){
        itemService.delete(memberId);
    }
}
