package com.thanhdao.course_online.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thanhdao.course_online.entity.common.BaseSoftDeleteEntity;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Transactional
@JsonSerialize
@Entity
@Table
public class Course extends BaseSoftDeleteEntity implements Serializable {

    @Column(length = Integer.MAX_VALUE)
    private String name;
    @Column(length = Integer.MAX_VALUE)
    private String description;

    @Column
    private int tuitionFee;
    @Column(length = Integer.MAX_VALUE)
    private String imageUrl;
    @Column
    private int soldCount;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private User teacher;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course", fetch = FetchType.LAZY)
    private Set<OrderDetail> orderDetails;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "category_course", joinColumns = {@JoinColumn(referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
    private Set<Category> categories;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course", fetch = FetchType.LAZY)
    private Set<Feedback> feedbacks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course", fetch = FetchType.LAZY)
    private Set<Chapter> chapters;

}
