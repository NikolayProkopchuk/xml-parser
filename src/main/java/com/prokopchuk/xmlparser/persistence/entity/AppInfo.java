package com.prokopchuk.xmlparser.persistence.entity;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Entity
@Getter
@Setter
@ToString(exclude = "epaperRequestEntity")
@RequiredArgsConstructor
public class AppInfo {
    @Id
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private String newspaperName;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    @JsonIgnore
    private EpaperRequestEntity epaperRequestEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        AppInfo appInfo = (AppInfo) o;
        return id != null && Objects.equals(id, appInfo.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
