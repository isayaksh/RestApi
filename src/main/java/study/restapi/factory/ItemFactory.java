package study.restapi.factory;

import org.springframework.stereotype.Component;
import study.restapi.domain.item.Book;
import study.restapi.domain.item.Movie;
import study.restapi.exception.NotCorrespondingException;
import study.restapi.form.ItemForm;

@Component
public class ItemFactory implements Factory{
    @Override
    public Object getInstance(String type) {
        switch (type){
            case "book":
                return new Book();
            case "movie":
                return new Movie();
            default:
                throw new NotCorrespondingException("type에 해당하는 객체가 존재하지 않습니다.");
        }
    }
}
