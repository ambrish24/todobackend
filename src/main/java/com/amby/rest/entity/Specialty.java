package com.amby.rest.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Models a {@link Vet Vet's} specialty (for example, dentistry).
 */
@Entity
@Table(name = "specialties")
public class Specialty extends NamedEntity {

}
