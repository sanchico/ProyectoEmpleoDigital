package com.proyecto.angularjs.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Table
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Booking implements Serializable {

	
	private static final long serialVersionUID = -2944830735091525349L;
	
	
	private @Id @GeneratedValue Long id;
	
	private Long User_id;
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="User_ID")
	//private User user;
	
//	@ManyToOne(fetch=FetchType.LAZY )
//	@JoinColumn(name="Motorbike_ID")
//	private MotorBike motorBike;
	
	
	private Long Motorbike_id;
	
	private Date pickupDate;
	
	private Date dropoffDate;
	
	private Integer reservationDuration;
	
	protected Booking() {
		
	}

	public Booking(Long user_id, Long motorBike_id, Date pickupDate, Date dropoffDate, Integer reservationDuration) {
		super();
		this.User_id = user_id;
		this.Motorbike_id = motorBike_id;
		this.pickupDate = pickupDate;
		this.dropoffDate = dropoffDate;
		this.reservationDuration = reservationDuration;
		
	}
	/**
	 * 
	 * @return the user
 	 */


	/**
	 * 
	 * @return the pickupDate
	 */

	public Date getPickupDate() {
		return pickupDate;
	}
	/**
	 * 
	 * @param pickupDate the pickupDate to set
	 */

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}
	/**
	 * 
	 * @return the dropoffDate
	 */

	public Date getDropoffDate() {
		return dropoffDate;
	}
	/**
	 * 
	 * @param dropoffDate the dropoffDate to set
	 */

	public void setDropoffDate(Date dropoffDate) {
		this.dropoffDate = dropoffDate;
	}
	/**
	 * 
	 * @return the reservationDuration
	 */

	public Integer getReservationDuration() {
		return reservationDuration;
	}
	/**
	 * 
	 * @param reservationDuration the reservationDuration to set
	 */

	public void setReservationDuration(Integer reservationDuration) {
		this.reservationDuration = reservationDuration;
	}
	/**
	 * 
	 * @return the id
	 */

	public Long getId() {
		return id;
	}

	public Long getUser_id() {
		return User_id;
	}

	public void setUser_id(Long user_id) {
		User_id = user_id;
	}

	public Long getMotorbike_id() {
		return Motorbike_id;
	}

	public void setMotorbike_id(Long motorbike_id) {
		Motorbike_id = motorbike_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Motorbike_id == null) ? 0 : Motorbike_id.hashCode());
		result = prime * result + ((User_id == null) ? 0 : User_id.hashCode());
		result = prime * result + ((dropoffDate == null) ? 0 : dropoffDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pickupDate == null) ? 0 : pickupDate.hashCode());
		result = prime * result + ((reservationDuration == null) ? 0 : reservationDuration.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		if (Motorbike_id == null) {
			if (other.Motorbike_id != null)
				return false;
		} else if (!Motorbike_id.equals(other.Motorbike_id))
			return false;
		if (User_id == null) {
			if (other.User_id != null)
				return false;
		} else if (!User_id.equals(other.User_id))
			return false;
		if (dropoffDate == null) {
			if (other.dropoffDate != null)
				return false;
		} else if (!dropoffDate.equals(other.dropoffDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pickupDate == null) {
			if (other.pickupDate != null)
				return false;
		} else if (!pickupDate.equals(other.pickupDate))
			return false;
		if (reservationDuration == null) {
			if (other.reservationDuration != null)
				return false;
		} else if (!reservationDuration.equals(other.reservationDuration))
			return false;
		return true;
	}




	
	

}
