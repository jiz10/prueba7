package ar.com.edu.um.web;
import ar.com.edu.um.domain.Negocio;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.gvnix.addon.web.mvc.annotations.jquery.GvNIXWebJQuery;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;

@RequestMapping("/negocios")
@Controller
@RooWebScaffold(path = "negocios", formBackingObject = Negocio.class)
@GvNIXWebJQuery
@RooWebFinder
public class NegocioController {
}
