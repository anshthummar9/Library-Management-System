package com.library.management.service;

import com.library.management.entity.Member;
import com.library.management.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }
    
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
    }
    
    public Member updateMember(Long id, Member memberDetails) {
        Member member = getMemberById(id);
        member.setName(memberDetails.getName());
        member.setEmail(memberDetails.getEmail());
        member.setPhone(memberDetails.getPhone());
        member.setAddress(memberDetails.getAddress());
        return memberRepository.save(member);
    }
    
    public void deleteMember(Long id) {
        Member member = getMemberById(id);
        memberRepository.delete(member);
    }
}