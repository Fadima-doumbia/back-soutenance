package com.bezkoder.springjwt.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
@Entity
@Table(	name = "users", uniqueConstraints = {
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank
	@JoinColumn(unique = true)
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 20)
	@Email
	private String email;

	private String presentation;

	@NotBlank
	@Size(max = 50)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "userId")
	private Set<Project> projects = new HashSet<>();

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "userId")
	private Set<Post> posts = new HashSet<>();


	public User(String username, String email, String presentation, String password) {
		this.username = username;
		this.email = email;
		this.presentation = presentation;
		this.password = password;
	}
}
