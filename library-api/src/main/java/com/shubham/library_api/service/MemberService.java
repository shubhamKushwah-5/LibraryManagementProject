package com.shubham.library_api.service;

import com.shubham.library_api.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.shubham.library_api.model.Member;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // Get all Members
    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

    //Get member by Id
    public Member getMemberById(Long id){
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));

    }

    // Add new member
    public Member addMember(Member member){
       return  memberRepository.save(member);
    }

    //update member
    public Member updateMember(Long id, Member memberDetails){
        Member old = getMemberById(id);

        old.setEmail(memberDetails.getEmail());
        old.setName(memberDetails.getName());
        old.setPhone(memberDetails.getPhone());

        return memberRepository.save(old);
    }

    // Delete member
    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }
}
