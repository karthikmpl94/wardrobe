package com.ssl.wardrobe.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PAYMENTDETAIL_TEMP")
@Getter
@Setter
@ToString
public class TempPaymentDetailModel extends AbstractPaymentDetailModel {

}
