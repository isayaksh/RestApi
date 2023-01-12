# RestApi
REST API 를 이용한 CRUD 구현하기!

#
## ERD
<img width="831" alt="스크린샷 2023-01-11 오후 5 42 40" src="https://user-images.githubusercontent.com/85926257/211760122-ad092b56-02f1-457d-898e-c51662d12e8e.png">

### `Member` 엔티티

- 사용자 이름(`username`), 비밀번호(`password`), 나이(`age`), 장바구니 상품 목록(`cartItems`)으로 구성되어 있다.
- 한 명의 사용자(`Member : 1`)가 장바구니에 여러개의 상품(`CartItem : N`)을 담을 수 있어야 한다.
  - 따라서 사용자(`Member`) 엔티티는 장바구니 상품(`CartItem`) 엔티티와는 일대다 관계(`OneToMany`)를 갖는다.

### `CartItem` 엔티티
- 사용자(`Member`), 상품(`Item`)로 구성되어 있다.
- 사용자의 장바구니에 존재하는 상품의 정보를 관리한다.

### `Item` 엔티티
- 상품 이름(`name`), 상품 유형(`type`), 상품 가격(`price`)으로 구성되어 있다.

### `Book` 엔티티
- 상품(`Item`) 엔티티를 상속받는다.
- 저자(`author`), isbn(`isbn`)으로 구성되어 있다.

### `Movie` 엔티티
- 상품(`Item`) 엔티티를 상속받는다.
- 감독(`director`), 배우(`actor`)로 구성되어 있다.

### `BaseEntity`
- 생성일자(`createdDate`), 마지막 수정일자(`lastModified`)로 구성되어 있다.
- 모든 엔티티의 **공통 속성**으로 사용하기 위한 엔티티이다.

#
##


```
(POST) http://localhost:8080/members
```
