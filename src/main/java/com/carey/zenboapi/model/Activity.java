package com.carey.zenboapi.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "activity")
@EntityListeners(AuditingEntityListener.class)
public class Activity {

    private long id;
    private String service;
    private Date time;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Column(name = "service", nullable = false)
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	@Column(name = "time", nullable = false)
    @CreatedDate
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

	

}
