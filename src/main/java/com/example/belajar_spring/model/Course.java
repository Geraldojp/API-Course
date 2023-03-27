package com.example.belajar_spring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "course")
@Data
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "link")
    private String link;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "course_info_id")
    private CourseInfo courseInfo;

    @ManyToOne
    @JoinColumn(name = "course_type_id")
    private CourseType courseType;

}
