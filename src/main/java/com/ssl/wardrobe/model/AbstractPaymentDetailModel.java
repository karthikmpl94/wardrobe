package com.ssl.wardrobe.model;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractPaymentDetailModel extends BaseModel {
	@Column(name = "sequence")
	private String sequence;

	@Column(name = "tendertype")
	private String tenderType;

	@Column(name = "amount")
	private String amount;

	@Column(name = "code")
	private String code;

	@Column(name = "discount")
	private String discount;

	@Column(name = "unitamount")
	private String unitAmount;
}
