package com.ssl.wardrobe.apientities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberDetails {
	@JsonProperty("memberId")
  private String memberId;
	@JsonProperty("startDate")
  private Date startDate;
	@JsonProperty("endDate")
  private Date enddate;
  
public String getMemberId() {
	return memberId;
}
public void setMemberId(String memberId) {
	this.memberId = memberId;
}
public Date getStartDate() {
	return startDate;
}
public void setStartDate(Date startDate) {
	this.startDate = startDate;
}
public Date getEnddate() {
	return enddate;
}
public void setEnddate(Date enddate) {
	this.enddate = enddate;
}

  
}
