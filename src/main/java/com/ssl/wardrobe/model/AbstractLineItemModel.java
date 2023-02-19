package com.ssl.wardrobe.model;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractLineItemModel extends BaseModel {
	@Column(name = "sequence")
	private String sequence;

	@Column(name = "status")
	private String status;

	@Column(name = "type")
	private String type;

	@Column(name = "externalproductid")
	private String externalProductId;

	@Column(name = "quantity")
	private String quantity;

	@Column(name = "regularunitprice")
	private String regularUnitPrice;

	@Column(name = "actualunitprice")
	private String actualUnitPrice;

	@Column(name = "extendedamount")
	private String extendedAmount;

	@Column(name = "linetax")
	private String lineTax;

	@Column(name = "departmentcode")
	private String departmentCode;

	@Column(name = "subdepartmentcodeclasscode")
	private String subDepartmentCodeClassCode;

	@Column(name = "subclasscode")
	private String subClassCode;

	@Column(name = "promotionid")
	private String promotionId;

	@Column(name = "promotioninfo")
	private String promotionInfo;

	@Column(name = "promotionamount")
	private String promotionAmount;

	@Column(name = "promotionreasoncode")
	private String promotionReasonCode;

	@Column(name = "linediscount")
	private String lineDiscount;

	@Column(name = "discountcode")
	private String discountCode;

	@Column(name = "discountreasontype")
	private String discountReasonType;

	@Column(name = "disountreason")
	private String disountReason;
}
