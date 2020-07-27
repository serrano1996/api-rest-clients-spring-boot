/**
 * 
 */
package com.rssoft.example.apirest.app.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rafas
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="clients")
public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="El campo nombre es requerido")
	@Size(min=4, max=50, message="El número de caracteres del nombre de estar entre 4 y 50")
	@Column(nullable=false)
	private String name;
	
	@NotEmpty(message="El campo apellido es requerido")
	private String lastname;
	
	@NotEmpty(message="El campo email es requerido")
	@Email(message="El campo correo no tiene un formato válido")
	@Column(nullable=false, unique=true)
	private String email;
	
	@NotNull(message="El campo fecha es requerido")
	@Column(name="create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	private String image;
	
	@NotNull(message="El campo region es requerido")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="region_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Region region;
	
	//@PrePersist
	//public void prePersist() {
	//	this.createdAt = new Date();
	//}

}
