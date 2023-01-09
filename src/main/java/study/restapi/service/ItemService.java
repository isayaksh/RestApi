package study.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.restapi.domain.item.Book;
import study.restapi.domain.item.Item;
import study.restapi.domain.item.Movie;
import study.restapi.exception.NotCorrespondingException;
import study.restapi.form.ItemForm;
import study.restapi.repository.ItemRepository;
import study.restapi.service.dto.BookDto;
import study.restapi.service.dto.ItemDto;
import study.restapi.service.dto.MovieDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * create
     **/
    @Transactional
    public ItemDto save(ItemForm form, String type){
        if(type.equals("book")){
            Book book = Book.createBook(form.getName(), form.getPrice(), form.getAuthor(), form.getIsbn());
            itemRepository.save(book);
            return ItemDto.createItemDto(book);
        } else if (type.equals("movie")) {
            Movie movie = Movie.createMovie(form.getName(), form.getPrice(), form.getDirector(), form.getActor());
            itemRepository.save(movie);
            return ItemDto.createItemDto(movie);
        }
        throw new NotCorrespondingException("url 의 정보가 통신규약과 맞지 않습니다.");
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
    public ItemDto update(Long memberId, ItemForm form){
        Item item = findById(memberId);
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

    private Item findById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> new NotCorrespondingException("itemId 에 해당하는 Item 이 존재하지 않습니다."));
    }
}
