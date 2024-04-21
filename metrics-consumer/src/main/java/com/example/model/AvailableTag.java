package com.example.model;

import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
@Table(name = "tags")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AvailableTag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_seq")
    @SequenceGenerator(name = "tag_seq", sequenceName = "SEQ_TAG", allocationSize = 10)
    @Column(name = "tags_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metrics_id", nullable = false)
    @ToString.Exclude
    private ActuatorMetric metric;

    @Column(name = "tags_statistic")
    private String tag;

    @ElementCollection
    @CollectionTable(name = "tag_values", joinColumns = @JoinColumn(name = "tags_id"))
    @ToString.Exclude
    private List<String> values;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableTag that = (AvailableTag) o;
        return Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag);
    }

}