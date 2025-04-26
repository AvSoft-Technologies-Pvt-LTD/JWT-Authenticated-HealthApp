package com.avswasthya.healthapp.patient.model;

import com.avswasthya.healthapp.appointment.model.Appointment;
import com.avswasthya.healthapp.auth.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
@EqualsAndHashCode(exclude = "user")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(length = 40)
    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true)
    private String healthCardId;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false, unique = true)
    private String aadharNumber;

    @Column(length = 100)
    private String address;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "profile_img")
    private byte[] photo;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "pat_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;


}
