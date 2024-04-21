package com.example.model;

import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "measurements")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "measurement_seq")
    @SequenceGenerator(name = "measurement_seq", sequenceName = "SEQ_MEASUREMENT", allocationSize = 10)
    @Column(name = "measurements_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metrics_id", nullable = false)
    @ToString.Exclude
    private ActuatorMetric metric;

    @Column(name = "measurements_statistic")
    private String statistic;

    @Column(name = "measurements_value")
    private Double value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return Objects.equals(statistic, that.statistic) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statistic, value);
    }

}