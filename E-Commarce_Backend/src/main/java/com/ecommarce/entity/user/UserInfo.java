package com.ecommarce.entity.user;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.ecommarce.entity.DeliveryInformation;
import com.ecommarce.entity.OrderDetail;
import com.ecommarce.enums.UserRole;
import com.ecommarce.enums.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String userInfoId;
	
	private String userName;
	
	private int otp;
	
	private Date optGenerationDate;
	
	private int otpAttempt;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String mobile;
	
	private Date createdDate;
	
	private UserRole userRole;
	
	@OneToMany
	private Set<DeliveryInformation> deliveryInformation = new HashSet<>();
	
	@Column(length = 100000000)
	private HashMap<String, Integer> cart = new HashMap<>();
	
	@OneToMany
	private Set<OrderDetail> orders;
	
	private UserStatus status;
	
	public boolean hasDeliveryInformation(String deliveryId) {
        for (DeliveryInformation deliveryInfo: deliveryInformation) {
            if (deliveryInfo.getDeliveryId().equals(deliveryId)) {
                return true;
            }
        }
        return false;
	}
}
