package com.avswasthya.healthapp.hospital.model;

import com.avswasthya.healthapp.doctor.model.Doctor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="hospital")
@Setter
@Getter
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phone;

    @OneToMany(mappedBy = "hospital")
    private List<Doctor> doctors;
}
