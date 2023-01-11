package study.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.restapi.domain.item.Book;
import study.restapi.domain.item.Item;
import study.restapi.domain.item.Movie;
import study.restapi.exception.NotCorrespondingException;
import study.restapi.factory.Factory;
import study.restapi.factory.ItemFactory;
import study.restapi.form.ItemForm;
import study.restapi.repository.ItemRepository;
import study.restapi.service.dto.ItemDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final Factory itemFactory;

    /**
     * create
     **/
    @Transactional
    public ItemDto save(ItemForm form, String type){
        Item item = (Item) itemFactory.getInstance(type, form);
        itemRepository.save(item);
        return ItemDto.createItemDto(item);
    }

    /**
     * read
     **/
    public List<ItemDto> findAll(){
        return itemRepository.findAll()
                .stream().map(item -> ItemDto.createItemDto(item))
                .collect(Collectors.toList());
    }

    public ItemDto findOne(Long itemId){
        Item item = findById(itemId);
        return ItemDto.createItemDto(item);
    }

    /**
     * update
     **/
    @Transactional
    public ItemDto update(Long itemId, ItemForm form){
        Item item = findById(itemId);
        item.update(form);
        return ItemDto.createItemDto(item);
    }

    /**
     * delete
     **/
    @Transactional
    public void delete(Long memberId){
        itemRepository.deleteById(memberId);
    }

    // find entity
    private Item findById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> new NotCorrespondingException("itemId 에 해당하는 Item 이 존재하지 않습니다."));
    }
}
