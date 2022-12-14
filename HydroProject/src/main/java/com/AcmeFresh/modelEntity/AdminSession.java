package com.AcmeFresh.modelEntity;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.Data;
@Data
@Entity
public class AdminSession {
	
	@Id
	@SequenceGenerator(name="admin_Session_generator", sequenceName = "admin_Session_seq", allocationSize=50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_Session_generator")
	private Integer sessionId;
	private Integer adminId;
	private String uuid;
	private LocalDateTime sessionStartTime;

}
