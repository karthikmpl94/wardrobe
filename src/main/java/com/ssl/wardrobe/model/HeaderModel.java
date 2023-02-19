package com.ssl.wardrobe.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "HEADER", uniqueConstraints = { @UniqueConstraint(columnNames = { "bitreference" }) })
@Getter
@Setter
@ToString
public class HeaderModel extends AbstractHeaderModel {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "temp_header_lineitem")
	private List<LineItemModel> lineItemList;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "temp_header_paymentDetails")
	private List<PaymentDetailModel> paymentDetailList;

}
