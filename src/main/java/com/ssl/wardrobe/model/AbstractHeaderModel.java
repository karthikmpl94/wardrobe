package com.ssl.wardrobe.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractHeaderModel extends BaseModel {
	@Column(name = "offerid")
	private String offerId;

	@Column(name = "bitdate", nullable = false)
	private Timestamp bitDate;

	@Column(name = "bittype")
	private String bitType;

	@Column(name = "programid")
	private String programId;

	@Column(name = "sponsorid")
	private String sponsorId;

	@Column(name = "bitcategory")
	private String bitCategory;

	@Column(name = "location")
	private String location;

	@Column(name = "bitcurrency")
	private String bitCurrency;

	@Column(name = "bitsourcegeneratedid")
	private String bitSourceGeneratedId;

	@Column(name = "century")
	private String century;

	@Column(name = "posid")
	private String posId;

	@Column(name = "transactionnumber")
	private String transactionNumber;

	@Column(name = "orderconfirmationnumber")
	private String orderConfirmationNumber;

	@Column(name = "bitoriginalsourcegeneratedid")
	private String bitOriginalSourceGeneratedId;

	@Column(name = "rollovernumber")
	private String rollOverNumber;

	@Column(name = "originalbitamount")
	private String originalBitAmount;

	@Column(name = "bitamount")
	private String bitAmount;

	@Column(name = "tendertotal")
	private String tenderTotal;

	@Column(name = "payinamount")
	private String payInAmount;

	@Column(name = "poscashierid")
	private String posCashierId;

	@Column(name = "promotionid")
	private String promotionId;

	@Column(name = "customerid")
	private String customerId;

	@Column(name = "sponsor")
	private String sponsor;

	@Column(name = "locationname")
	private String locationName;
}
