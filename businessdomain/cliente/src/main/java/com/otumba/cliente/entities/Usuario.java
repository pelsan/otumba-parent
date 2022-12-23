package com.otumba.cliente.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 *
 * @author pelsa
 */
@Entity
@Table(name = "usuarios")
@Data
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20)
    private String username;

    @Column(length = 60)
    private String password;

    private Boolean enabled;

    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="users_autorities", joinColumns= @JoinColumn(name="user_id"), 
            inverseJoinColumns = @JoinColumn(name="role_id"), 
            uniqueConstraints = { @UniqueConstraint(columnNames={"user_id","role_id"})})
    private List<Role> roles;

    private static final long serialVersionUID = 1L;

}
