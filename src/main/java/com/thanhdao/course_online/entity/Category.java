package com.thanhdao.course_online.entity;

import com.thanhdao.course_online.entity.common.BaseSoftDeleteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Category extends BaseSoftDeleteEntity {

    @Column(length = Integer.MAX_VALUE)
    public String name;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    public Set<Course> courses;
}
