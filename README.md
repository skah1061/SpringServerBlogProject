# SpringServerBlogProject
스프링 서버 공부용 블로그 프로젝트 제작 깃 입니다.

<h2>ERD</h2>
<img src="https://github.com/skah1061/SpringServerBlogProject/assets/81159848/aaaf2618-a4ed-4d5e-9e20-1859d41e4956">
<h2>usecase</h2>

<img src ="https://github.com/skah1061/SpringServerBlogProject/assets/81159848/184368a1-93c8-4d03-8af4-b23e57309d50">

<h3>API명세</h3>
[https://docs.google.com/spreadsheets/d/1cE1zbtSOKVx5E4zYig8rTs3YSXXGU288LMeJ0ms31Uc/edit?usp=sharing](https://www.notion.so/acce043ca1ed44148fc863c0cdbd9f8e?v=fafa508ff33146e6a8d798573d1260fe&pvs=4)

<h3>#전체 조회 GET요청</h3>
<img src="https://github.com/skah1061/SpringServerBlogProject/assets/81159848/4201038b-59b5-4de5-9d2e-10defbedbad1">

<h3>#게시글 추가 POST요청</h3>
<img src="https://github.com/skah1061/SpringServerBlogProject/assets/81159848/a5804f4e-1557-4f4e-adc3-a1c8077d768b">

<h3>#선택게시글 조회 GET요청</h3>
<img src="https://github.com/skah1061/SpringServerBlogProject/assets/81159848/00a11db2-771a-4e99-9def-56f1a1c031f9">

<h3>#게시글 수정 PUT요청</h3>
<h5>Params를 이용해 데이터객체를 받아오고 body로 맞는 암호를 입력해야 수정</h5>
<img src="https://github.com/skah1061/SpringServerBlogProject/assets/81159848/543220db-38b6-48fc-a7dc-30380a56afb8">

<h3>#게시글 삭제 DELETE요청</h3>
<h5>body를 이용해 맞는 암호를 입력해야 삭제</h5>
<h5>암호가 맞다면 삭제하고 삭제완료 반환</h5>
<h5>암호가 아니라면 삭제하지않고 암호가 틀립니다. 반환</h5>
<img src="https://github.com/skah1061/SpringServerBlogProject/assets/81159848/2677b01e-367e-496a-bad0-7c2ec734f869">

#############
