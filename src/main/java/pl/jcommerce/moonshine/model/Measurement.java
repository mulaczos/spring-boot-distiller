package pl.jcommerce.moonshine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity which contains time of measurement and its value. In relation with
 * Thermometer class. Accessor, mutator, and construction data is being auto
 * generated by Lombok library
 * 
 * @author wipo
 * @see Thermometer
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = {
		@Index(columnList = "time", name = "time_description")
})
public class Measurement extends BaseEntity {

	private LocalDateTime time;
	private Double value;
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Thermometer thermometer;

}
