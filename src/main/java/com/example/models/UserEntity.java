package com.example.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

@Builder
@Data
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name="tbusers")
public class UserEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Email
	@NotBlank
	@Size(max=80)
	private String email;
	
	@NotBlank
	@Size(max=30)
	private String username;
	
	@NotBlank
	private String password;
	
	//many to many with roles
	
	//using set is recomended it has uniques values
	@ManyToMany(fetch=FetchType.EAGER,targetEntity=RoleEntity.class,cascade=CascadeType.PERSIST)
	@JoinTable(name="user_roles",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<RoleEntity> roles;





}


