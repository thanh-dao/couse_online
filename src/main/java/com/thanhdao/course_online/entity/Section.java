package com.thanhdao.course_online.entity;

import com.thanhdao.course_online.entity.common.BaseSoftDeleteEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Section extends BaseSoftDeleteEntity {
    @ManyToOne
    private Chapter chapter;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column
    private String video;
    @Column
    private String attachedFile;
}
