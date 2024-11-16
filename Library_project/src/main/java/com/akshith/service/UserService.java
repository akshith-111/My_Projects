package com.akshith.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.akshith.entity.Books;
import com.akshith.entity.Users;
import com.akshith.repositry.LibraryRepo;
import com.akshith.repositry.RequestsRepo;
import com.akshith.repositry.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	RequestsRepo requestsRepo;
	
	@Autowired
	UserRepository repo;

	@Autowired
	LibraryRepo libraryRepo;
	public List<Object> checkUser(Users userdetails) {
		
		List<Books> books=libraryRepo.findAll();
		List<Users> details=repo.findByUsernameAndPassword(userdetails.getUsername(),userdetails.getPassword());
		ArrayList<Object> al=new ArrayList<>();
		al.add(details);
		al.add(books);
		if(details.isEmpty())
		return null;
		
		else
			return al;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetails details= repo.findByUsername(username);
		return details;
	}
	public List<Books> getBooks() {
		
		return libraryRepo.findAll();
		
		
	}
	
	public Users getUser(Integer id) {
		Optional<Users> opt= repo.findById(id);
		return opt.get();
	}
	
	public Users addUser(Users user) {
		return repo.save(user);
	}
	
	public Set<Books> getUserBooks(Integer id){
		
		 Optional<Users> opt=repo.findById(id);
		 Users u=opt.get();
		
		 return u.getBooks();
		
	}
	
}
