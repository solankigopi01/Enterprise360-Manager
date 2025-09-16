package com.business.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="orders")
@Data
public class Orders
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int oId;
	private String oName;
	private double oPrice;
	private int oQuantity;
	private Date orderDate;
	private double totalAmmout;
	
	@ManyToOne
	@JoinColumn(name="user_u_id")
	private User user;

}
