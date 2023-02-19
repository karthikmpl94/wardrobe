package com.ssl.wardrobe.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "LINEITEM_TEMP")
@Getter
@Setter
@ToString
public class TempLineItemModel extends AbstractLineItemModel {

}
