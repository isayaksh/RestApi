package study.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.restapi.domain.item.Item;
import study.restapi.form.ItemForm;
import study.restapi.repository.ItemRepository;
import study.restapi.service.dto.ItemDto;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * create
     **/
    public ItemDto save(ItemForm form){


    }

    // save

    // findAll

    // findOne

    // update

    // delete
}
