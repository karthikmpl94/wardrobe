package com.ssl.wardrobe.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "LINEITEM")
@Getter
@Setter
@ToString
public class LineItemModel extends AbstractLineItemModel {

	@ManyToOne
	@JoinColumn(name = "header_lineitem")
	private HeaderModel header;

}
