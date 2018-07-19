package com.proyecto.angularjs.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "MOTORBIKE")
public class MotorBike implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8777352052678301854L;

	private @Id @GeneratedValue Long id_Motorbike;

	private String model;

	private String number;

	private Double latitude;

	private Double longitude;

	private Boolean reserved;

	@OneToMany(mappedBy = "Motorbike_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Booking> bookings = new ArrayList<>();

	protected MotorBike() {

	}

	public MotorBike(String model, String number, Double latitude, Double longitude, Boolean reserved) {
		super();
		this.model = model;
		this.number = number;
		this.latitude = latitude;
		this.longitude = longitude;
		this.reserved = reserved;
	}

	/**
	 * 
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * 
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * 
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * 
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * 
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * 
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * 
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * 
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * 
	 * @return the reserved
	 */
	public Boolean getReserved() {
		return reserved;
	}

	/**
	 * 
	 * @param reserved
	 *            the reserved to set
	 */
	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}

	/**
	 * 
	 * @return the id
	 */

	public Long getId_Motorbike() {
		return id_Motorbike;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

//	public void setBookings(List<Booking> bookings) {
//		this.bookings = bookings;
//		for (Booking book : bookings) {
//
//		
//				book.setMotorBike(this);
//			
//		}
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookings == null) ? 0 : bookings.hashCode());
		result = prime * result + ((id_Motorbike == null) ? 0 : id_Motorbike.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((reserved == null) ? 0 : reserved.hashCode());
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
		MotorBike other = (MotorBike) obj;
		if (bookings == null) {
			if (other.bookings != null)
				return false;
		} else if (!bookings.equals(other.bookings))
			return false;
		if (id_Motorbike == null) {
			if (other.id_Motorbike != null)
				return false;
		} else if (!id_Motorbike.equals(other.id_Motorbike))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (reserved == null) {
			if (other.reserved != null)
				return false;
		} else if (!reserved.equals(other.reserved))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MotorBike [id_Motorbike=" + id_Motorbike + ", model=" + model + ", number=" + number + ", latitude="
				+ latitude + ", longitude=" + longitude + ", reserved=" + reserved + ", bookings=" + bookings + "]";
	}

}