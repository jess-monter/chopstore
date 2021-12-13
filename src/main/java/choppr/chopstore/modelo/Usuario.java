package choppr.chopstore.modelo;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

/**
 * Clase que representa una instancia de entidad de la tabla usuario en la base de datos
 * @author Eric Toporek Coca
 * @author Francisco Alejandro Arganis Ramı́rez
 * @author Jessica Monter Gallardo
 * @version 1.3
 */

@ Data
@ Entity
@NoArgsConstructor
@ Table (name = "usuario")
public class Usuario implements UserDetails {

    @ Id
    @ GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idusuario;

    @ Column (name = "nombre")
    private String nombre;

    @ Column (name = "apellido")
    private String apellido;

    @ Column (name = "correo")
    private String correo;

    @ Column (name = "contrasena")
    private String contrasena;

    @ Column (name = "telefono")
    private String telefono;

    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    private RolUsuario rolUsuario;

    // @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private Set<Producto> productos;
    // @OneToMany(mappedBy = "usuario" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private Set<Compra> compras;
    // @OneToMany(mappedBy = "usuario" ,cascade = CascadeType.ALL)
    // private Set<Resena> resenas;
    private Boolean locked = false;
    private Boolean enabled = false;

    public Usuario(String nombre, String apellido, String correo, String telefono, RolUsuario rolUsuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = generaContrasena(8);
        this.telefono = telefono;
        this.rolUsuario = rolUsuario;
        // if (rolUsuario.equals(RolUsuario.COMPRADOR)) {
        //    this.compras = new HashSet<>();
        //    this.productos = null;
        //    this.resenas = new HashSet<>();
        // } else {
        //    this.compras = null;
        //    this.productos = new HashSet<>();
        // }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+rolUsuario.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    private String generaContrasena(int longitud){
        int left = 48; //ASCII 0
        int right = 122; //ASCII z
        Random r = new Random();

        return r.ints(left,right+1)
                .filter(i->(i<=57 || i >= 65) && (i<= 90 || i >= 97))
                .limit(longitud).collect(StringBuilder::new,StringBuilder::appendCodePoint,StringBuilder::append).toString();
    }
}
