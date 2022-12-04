package com.prokopchuk.xmlparser.persistence.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Entity
@Table(name = "epaper_request")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class EpaperRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "epaper_request_seq")
    @SequenceGenerator(name = "epaper_request_seq", sequenceName = "epaper_request_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private LocalDateTime uploadTime = LocalDateTime.now();

    @OneToOne(mappedBy = "epaperRequestEntity", cascade = CascadeType.ALL)
    private ScreenInfo screenInfo;

    @OneToOne(mappedBy = "epaperRequestEntity", cascade = CascadeType.ALL)
    private AppInfo appInfo;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        EpaperRequestEntity that = (EpaperRequestEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
