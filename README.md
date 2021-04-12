# SpringBoot 수업 진행
# 1. JPA
0. JPA의 사용 이유, PersistenceContext, EntityFactory, EntityManager
1. @JoinColumn없이 객체 설계 - 이후 테이블 erd 보여주기
2. @JoinColumn 설명 이후 다시 객체 설계 - 테이블 erd 보여주기
3. Member CRUD 진행
4. 트랜잭션 & 1차캐시 설명 , Lazy initialize & Proxy 설명 
5. 회원, 도서, 대출 더미데이터 입력 
6. 전체 회원 조회 -> N+1 설명 -> fetch join 설명
7. 결과 확인(inner join의 결과로 동일한 회원이 여러개 반환 된 것 확인) -> JPQL에 distinct 추가
8. 새로운 회원 추가 + 전체 회원 조회 / inner join이기 때문에 새로운 회원은 나오지 않음 -> left join으로 변경
9. mappedBy 설명 (외래키를 한 곳에서만)
10. 양방향 매핑 주의사항
     - 연관관계 편의 메서드
     - 엔티티 무한 참조 문제(toString, Json)
12. 2차캐시 설정 및 설명

# 2. SpringBoot
13. springboot 프로젝트 생성 + thymeleaf
14. spring Data + queryDSL
15. spring toy project
16. spring security toy 





