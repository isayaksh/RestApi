package study.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.restapi.form.CartItemForm;
import study.restapi.service.CartItemService;
import study.restapi.service.dto.CartItemDto;

import java.util.List;


@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    // POST : localhost:8080/carts
    @PostMapping
    public CartItemDto save(@RequestBody CartItemForm form){
        return cartItemService.save(form);
    }

    // GET : localhost:8080/carts/memberId
    @GetMapping("/{memberId}")
    public List<CartItemDto> findAll(@PathVariable Long memberId){
        return cartItemService.findAll(memberId);
    }

    // DELETE : localhost:8080/carts/cartItemId
    @DeleteMapping("/{cartItemId}")
    public void delete(@PathVariable Long cartItemId){
        cartItemService.delete(cartItemId);
    }
}
