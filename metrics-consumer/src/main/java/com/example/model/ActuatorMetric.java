package com.example.model;

import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "metrics")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ActuatorMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "metric_seq")
    @SequenceGenerator(name = "metric_seq", sequenceName = "SEQ_METRIC", allocationSize = 10)
    @Column(name = "metrics_id", nullable = false)
    private Long id;

    @Column(name = "metrics_name", nullable = false)
    private String name;

    @Column(name = "metrics_description", nullable = false)
    private String description;

    @Column(name = "metrics_base_unit")
    private String baseUnit;

    @OneToMany(mappedBy = "metric")
    @ToString.Exclude
    private List<Measurement> measurements;

    @OneToMany(mappedBy = "metric")
    @ToString.Exclude
    private List<AvailableTag> availableTags;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActuatorMetric that = (ActuatorMetric) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(baseUnit, that.baseUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, baseUnit);
    }

}