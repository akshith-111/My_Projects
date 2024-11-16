package com.akshith.entity;


import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users implements UserDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String fname;
	private String lname;
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;
	private String mailid;
	private Long phno;
	private String role;
	@ManyToMany
	@JoinTable(name="users_books",
	joinColumns = @JoinColumn(name="users_id"),inverseJoinColumns = @JoinColumn(name="books_id"))
	private Set<Books> books;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_"+role));
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		return Objects.equals(fname, other.fname) && Objects.equals(id, other.id) && Objects.equals(lname, other.lname)
				&& Objects.equals(mailid, other.mailid) && Objects.equals(password, other.password)
				&& Objects.equals(phno, other.phno) && Objects.equals(role, other.role)
				&& Objects.equals(username, other.username);
	}
	@Override
	public int hashCode() {
		return Objects.hash(fname, id, lname, mailid, password, phno, role, username);
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", fname=" + fname + ", lname=" + lname + ", username=" + username + ", password="
				+ password + ", mailid=" + mailid + ", phno=" + phno + ", role=" + role + "]";
	}
	


}
