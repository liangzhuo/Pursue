package com.liang.dubbo.model;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import com.sun.istack.internal.NotNull;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable{
	
	private static final long serialVersionUID = -1508175718167509483L;

	@NotNull
	@Min(1L)
	private Long id;
	
	@JsonProperty("username")
	@XmlElement(name = "username")
	@javax.validation.constraints.NotNull
	@Size(min = 6, max = 50)
	private String name;

	public User() {
		
	}

	public User(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
}
