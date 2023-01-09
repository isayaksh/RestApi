package study.restapi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.restapi.domain.item.Movie;

import static lombok.AccessLevel.PROTECTED;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class MovieDto {
    private String name;
    private int price;

    private String director;
    private String actor;

}
