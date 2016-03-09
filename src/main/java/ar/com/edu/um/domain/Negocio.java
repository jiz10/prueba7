package ar.com.edu.um.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findNegociosByNyaEqualsOrDomicilioEqualsOrEmailEqualsOrTelefonoEqualsOrTags", "findNegociosByNyaEquals", "findNegociosByDomicilioEquals", "findNegociosByEmailEquals", "findNegociosByStrtelEquals", "findNegociosByTags" })
public class Negocio {

    /**
     */
    private String nya;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Tag> tags = new HashSet<Tag>();

    /**
     */
    private String domicilio;

    /**
     */
    private String email;

    /**
     */
    private int telefono;

    /**
     */
    private String strtel;
}
