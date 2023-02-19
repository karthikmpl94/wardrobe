package com.ssl.wardrobe.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PAYMENTDETAILS")
@Getter
@Setter
@ToString
public class PaymentDetailModel extends AbstractPaymentDetailModel {

	@ManyToOne
	@JoinColumn(name = "header_paymentDetails")
	private HeaderModel header;
}
