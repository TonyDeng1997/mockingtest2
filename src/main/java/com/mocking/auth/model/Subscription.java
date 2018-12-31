package com.mocking.auth.model;

import javax.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "subscription")
public class Subscription {
	private long id;
    private Date subscription_date;
    private Date expiration_date;
    private String status;
    private long product_id;
    private Client client;
 
    public Subscription() {
    }
 
    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
 
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    public Client getClient() {
        return client;
    }
 
    public void setClient(Client client) {
        this.client = client;
    }

	public Date getSubscription_date() {
		return subscription_date;
	}

	public void setSubscription_date(Date subscription_date) {
		this.subscription_date = subscription_date;
	}

	public Date getExpiration_date() {
		return expiration_date;
	}

	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
}
