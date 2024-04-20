package com.tcs.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "media_content")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Media {


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Lob
	    @Column(columnDefinition = "LONGBLOB")
	    private byte[] data;

	    private String contentType;  // "text", "image", "video"


}
