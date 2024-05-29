package com.upc.api_examen_parcial_202120796.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")

/*
Para la implementacion del security es imporpotante tener en cuenta el apartado de User , ya que este nos permitira
mantener un identificador unico para nuestros usuarios , como lo podemos ver el gcUsername con el unique=true , validamos que
los usuarios ingresados sean unicos.
Además, hacemos uso de un enabled que nos permite manejar la cuenta aun existente, es decir si es que el usuario elimina su cuenta
simplemente ponemos el enabled el false , para que si en algun futuro el usuario desea volver a tener una cuenta en nuestro programa
con el mismo user solo tendriamos que colocar el enable el true nuevamente.

Al realizar un manytomany con la tabla roles, creamos una tabla intermedia , ya que muchos usuarios pueden tener muchos roles, esto nos permitira
asignarle roles a nuestros usuarios lo cual teniendo ya creado en la base de datos los roles y ya habiendo creado los usuarios, simplemente
hacemos la asignacion de los ID, esto no va a permitir que los usuarios tengan un rol en nuestro programa y hagan solo las acciones que estan
permitidas , esto en un entorno empresarial es muy importante, ya que el manejo de roles ayuda a que algun usuario no realice cambios o accede a informacion
que no esta permitida.

 */
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long gcId;
    @Column(nullable = false , unique = true)
    private String gcUsername;
    @Column(nullable = false)
    private String gcPassword;
    private Boolean gcEnabled;
    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns= @JoinColumn(name = "role_id"))
    private Set<Role> gcRoles;
}