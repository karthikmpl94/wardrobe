package com.ssl.wardrobe.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "HEADER_TEMP")
@Getter
@Setter
@ToString
public class TempHeaderModel extends AbstractHeaderModel {

}
