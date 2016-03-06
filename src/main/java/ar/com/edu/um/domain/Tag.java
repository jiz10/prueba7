package ar.com.edu.um.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Tag {

    /**
     */
    @NotNull
    private String nombre_tag;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "tags")
    private Set<Negocio> negocios = new HashSet<Negocio>();
}
