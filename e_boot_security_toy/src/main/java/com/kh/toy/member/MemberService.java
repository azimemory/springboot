package com.kh.toy.member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.kh.toy.common.code.Code;
import com.kh.toy.common.mail.EmailSender;

@Service
public class MemberService implements UserDetailsService{

	private final MemberRepository memberRepository;
	
	@Autowired
	EmailSender mail;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	RestTemplate restTemplate;
	@PersistenceContext
	EntityManager em;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Member member = memberRepository.findByUserIdAndIsLeave(userId,false);
		
		if(member==null) {
            throw new UsernameNotFoundException(userId);
        }
		
		return new MemberAccount(member);
	}
	
	public Member selectMemberById(String userId){	
		return em.find(Member.class, userId);
	}
	
	public void authenticateEmail(Member member, String sessionId) {
		//RestTemplate의 기본 Contents-type은 application/json
		//Application/x-www-form-urlencode 형식으로 보낼 때 파라미터는 MultiValueMap<String, String>에 담는다.
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();		
		body.add("mail-template","temp_join");
		body.add("userId",member.getUserId());
		body.add("sessionId",sessionId);
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body,header);
		ResponseEntity<String> re = restTemplate.exchange(Code.DOMAIN+"/mail", HttpMethod.POST, entity, String.class);
		mail.sendEmail(member.getEmail(), "회원가입을 축하합니다.", re.getBody());
	}
	
	public void saveMember(Member member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberRepository.save(member);
	}
	
	public void updateMember(Member member) {
		memberRepository.save(member);
	}

	public void updateMemberToLeave(String userId) {
		Member member  = memberRepository.findById(userId).get();
		member.setIsLeave(true);
	}
}
