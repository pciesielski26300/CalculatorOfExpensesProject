package pl.pawelciesielski.persistence;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ROLE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id

    @Column(name = "ROLE_ID")
    private int roleId;
    @Column(name = "ROLE_NAME")
    private String roleName;
}
