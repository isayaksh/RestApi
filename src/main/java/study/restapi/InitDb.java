package study.restapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.restapi.domain.item.Book;
import study.restapi.domain.item.Movie;
import study.restapi.domain.member.Member;
import study.restapi.repository.ItemRepository;
import study.restapi.repository.member.MemberRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.createMembers();
        initService.createItems();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final MemberRepository memberRepository;
        private final ItemRepository itemRepository;

        public void createMembers(){
            Member member1 = Member.createMember("kim", "1234", 27);
            memberRepository.save(member1);
            Member member2 = Member.createMember("lee", "2468", 22);
            memberRepository.save(member2);
            Member member3 = Member.createMember("han", "4321", 23);
            memberRepository.save(member3);
            Member member4 = Member.createMember("um", "9870", 24);
            memberRepository.save(member4);
        }

        public void createItems(){
            Book book = Book.createBook("spring jpa", 33000, "김영한", "2440");
            itemRepository.save(book);
            Movie movie = Movie.createMovie("about time", 12000, "dr", "ac");
            itemRepository.save(movie);
        }
    }
}
