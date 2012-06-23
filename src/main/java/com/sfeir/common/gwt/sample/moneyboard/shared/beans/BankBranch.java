package com.sfeir.common.gwt.sample.moneyboard.shared.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;

import com.beoui.geocell.annotations.Geocells;
import com.beoui.geocell.annotations.Latitude;
import com.beoui.geocell.annotations.Longitude;
import com.google.common.base.Objects;
import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Unindexed;

@Entity
@Cached()
public class BankBranch implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5171934502156113633L;
	@Id
	Long id;
	String codeBank;
	@Unindexed
	String nameBank;
	@Unindexed
	String simpleNameBank;
	String code;
	@Unindexed
	String name;
	@Unindexed
	String address;
	@Latitude
	@Unindexed
	double lat;
	@Longitude
	@Unindexed
	double lng;
	@Geocells
	List<String> cells;
	@Unindexed
	String image;

	public BankBranch() {
	}

	public BankBranch(String codeBank, String simpleNameBank, String nameBank, String code, String name, String address, Double lat, Double lng, String image) {
		super();
		this.codeBank = codeBank;
		this.simpleNameBank = simpleNameBank;
		this.nameBank = nameBank;
		this.code = code;
		this.name = name;
		this.address = address;
		this.lat = lat;
		this.lng = lng;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeBank() {
		return codeBank;
	}

	public void setCodeBank(String codeBank) {
		this.codeBank = codeBank;
	}

	public String getSimpleNameBank() {
		return simpleNameBank;
	}
	
	public void setSimpleNameBank(String simpleNameBank) {
		this.simpleNameBank = simpleNameBank;
	}
	
	public String getNameBank() {
		return nameBank;
	}

	public void setNameBank(String nameBank) {
		this.nameBank = nameBank;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}
	
	public void setCells(List<String> cells) {
		this.cells = cells;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("nameBank",nameBank).add("name",name).toString();
	}
}
