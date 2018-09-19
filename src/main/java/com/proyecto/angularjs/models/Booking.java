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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Table
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Booking implements Serializable {

	
	
	
	


	private static final long serialVersionUID = -247755875550102105L;

	private @Id @GeneratedValue Long id_booking;
	
	
	
	/*
	 * Intentamos implementar ManyToOne y causaba problemas con JSON, por lo cual 
	 * optamos por Long User_id, Long Motorbike_id y haciendo esto JSON no 
	 * causaba problemas.
	 * */
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="User_ID")
//	@JsonBackReference (value="bookingsUser")
//	private User user;
	
//	@ManyToOne(fetch=FetchType.LAZY )
//	@JoinColumn(name="Motorbike_ID")
//	@JsonBackReference(value="bookingsMotorbike")
//	private MotorBike motorBike;
	private Long User_id;
	
	private Long Motorbike_id;
	
	private Date pickupDate;
	
	private Date dropoffDate;
	private Date devolucionReal;
	
	private Integer reservationDuration;
	
	protected Booking() {
		
	}

	public Booking(Long User_id, Long Motorbike_id, Date pickupDate, Date dropoffDate, Integer reservationDuration,Date devolucionReal) {
		super();
		this.User_id = User_id;
		this.Motorbike_id = Motorbike_id;
		this.pickupDate = pickupDate;
		this.dropoffDate = dropoffDate;
		this.reservationDuration = reservationDuration;
		this.devolucionReal= devolucionReal;
	}
	

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
	public Date getDevolucionReal() {
		return devolucionReal;
	}

	public void setDevolucionReal(Date devolucionReal) {
		this.devolucionReal = devolucionReal;
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

	public Long getId_booking() {
		return id_booking;
	}
	
	
	
	
	
	/**
	 * 
	 * @return the user
 	 */


	public Long getUser_id() {
		return User_id;
	}

	/**
	 * 
	 * @param user_id
	 */
	public void setUser_id(Long user_id) {
		User_id = user_id;
	}

	
	
	/**
	 * 
	 * @return Mototbike_id
	 */
	public Long getMotorbike_id() {
		return Motorbike_id;
	}
	
	
	/**
	 * 
	 * @param motorbike_id
	 */
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
		result = prime * result + ((id_booking == null) ? 0 : id_booking.hashCode());
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
		if (id_booking == null) {
			if (other.id_booking != null)
				return false;
		} else if (!id_booking.equals(other.id_booking))
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

	@Override
	public String toString() {
		return "Booking [id_booking=" + id_booking + ", User_id=" + User_id + ", Motorbike_id=" + Motorbike_id
				+ ", pickupDate=" + pickupDate + ", dropoffDate=" + dropoffDate + ", reservationDuration="
				+ reservationDuration + "]";
	}

	


	
	

}
