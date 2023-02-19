package com.ssl.wardrobe.apientities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("audit_info")
public class AuditInfo {

	@JsonProperty("user")
	private String user;
	@JsonProperty("attributes")
	private List<Attributes> attributes;
}
