package com.kh.bookmanager.rent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.kh.bookmanager.book.Book;
import com.kh.bookmanager.jpa.EntityTemplate;
import com.kh.bookmanager.member.Member;

public class RentService {
	
	private RentRepository rentRepository = new RentRepository();

	public boolean persistRentInfo(String userId, List<Long> bkIdxs) {
		
		EntityManager em = EntityTemplate.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Rent rent = new Rent();
			
			//대출건 제목
			List<Book> books = new ArrayList<>();
			for (Long bkIdx : bkIdxs) {
				books.add(em.find(Book.class, bkIdx));
			}
			
			String title = books.size() > 1 ? books.get(0).getTitle() + " 외 " + (books.size()-1) + "권"
							: books.get(0).getTitle();
			
			//대출자정보
			Member member = em.find(Member.class, userId);
			
			List<RentBook> rentBooks = new ArrayList<RentBook>();
			for (Book book : books) {
				RentBook rentBook = new RentBook();
				rentBook.setBook(book);
				rentBook.setState("대출");
				rentBooks.add(rentBook);
			}
			
			rent.setMember(member);
			rent.changeRentBooks(rentBooks);
			rent.setTitle(title);
			rent.setRentBookCnt(rentBooks.size());
			em.persist(rent);
			
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}

		return false;
	}

	public boolean returnBook(Long rbIdx) {
		EntityManager em = EntityTemplate.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		try {
			RentBook rentBook = em.find(RentBook.class, rbIdx);
			rentBook.setState("반납");
			rentBook.setReturnDate(LocalDateTime.now());
			
			if(rentBook.getRent().getRentBooks().stream().allMatch(e -> e.getState().equals("반납"))) {
				rentBook.getRent().setIsReturn(true);
			}
			
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		
		return false;
		
	}

	public List<Rent> findRentByUserId(String userId) {
		
		EntityManager em = EntityTemplate.getEntityManager();
		List<Rent> rents = new ArrayList<Rent>();
		try {
			rents = rentRepository.findRentByUserId(em, userId);
		} finally {
			em.close();
		}
		
		return rents;
	}

	public boolean extendsRent(long rbIdx) {
		
		EntityManager em = EntityTemplate.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			RentBook rentBook = em.find(RentBook.class, rbIdx);
			rentBook.setReturnDate(rentBook.getReturnDate().plusDays(7));
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}

		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
