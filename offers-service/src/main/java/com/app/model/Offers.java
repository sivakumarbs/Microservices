package com.app.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Offers_tab")
public class Offers {
	
	@Transient
	public static final String SEQUENCE_NAME = "offer_sequence";
	
	@Id
	private Integer offerId;
	private String offerCode;
	private Double amount;
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date expDate;
}
