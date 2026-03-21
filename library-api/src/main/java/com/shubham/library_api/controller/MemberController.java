package com.shubham.library_api.controller;

import com.shubham.library_api.model.Member;
import com.shubham.library_api.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // Get all Members
    @GetMapping
    public List<Member> getAllMember(){
        return memberService.getAllMembers();
    }

    // Get member by id
    @GetMapping("/{id}")
    public Member memberById(@PathVariable Long id){
       return memberService.getMemberById(id);
    }

    // Add member
    @PostMapping
    public ResponseEntity<Member> addMember(@Valid @RequestBody Member member){
        memberService.addMember(member);
        return new ResponseEntity<>(member , HttpStatus.CREATED);
    }

    // Update member
    @PutMapping("{id}")
    public Member updateMember(@PathVariable Long id, @Valid @RequestBody Member member){
        return memberService.updateMember(id,member);
    }

    // Delete Member
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok("Member deleted");
    }
}
