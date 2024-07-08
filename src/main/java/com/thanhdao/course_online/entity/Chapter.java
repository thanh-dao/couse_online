package com.thanhdao.course_online.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thanhdao.course_online.entity.common.BaseSoftDeleteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@Entity
@Table(name = "chapter")
public class Chapter extends BaseSoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    public Course course;
    @Column
    public int index;
    @Column
    private String name;
}
