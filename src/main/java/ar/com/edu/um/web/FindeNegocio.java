package ar.com.edu.um.web;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ar.com.edu.um.domain.Tag;
import ar.com.edu.um.domain.Negocio;
import ar.com.edu.um.domain.Tag;

@RequestMapping("/findnegocio/")
@Controller
public class FindeNegocio {
	
	@RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index() {
        return "findnegocio/index";
    }
    
   @RequestMapping(value = "/find", method = RequestMethod.GET, produces = "text/html")
    public String find(Model uiModel, HttpServletRequest request, HttpServletResponse response) {
    	    	
    	String saludo="saludo"; 	
    	
    	uiModel.addAttribute("tags",Tag.findTagsByHabilitado(1).getResultList());
    	

    	uiModel.addAttribute("saludo", saludo);
        return "findnegocio/find";	
    }
    
   
    
    @RequestMapping(value = "/listado", method = RequestMethod.POST)
        public String listado(Model uiModel, HttpServletRequest request, HttpServletResponse response){
    	
    	//listas auxiliares
    	List<Negocio> todos2 = new ArrayList<Negocio>();
    	List<Negocio> todos3 = new ArrayList<Negocio>();
    	List<Tag> tags2 = new ArrayList<Tag>();
    	
    	
    	//obtengo los valores del formulario
    	String nya = request.getParameter("nya");
    	String domicilio = request.getParameter("domicilio");
    	String email = request.getParameter("email");
    	String strtel  = request.getParameter("strtel");
    	String[] tags = request.getParameterValues("tags");
    	
   
    	//compruebo si los campos son nulos, sino los agrego a la lista de negocios
    	if (nya!=""){
    		List<Negocio> negociosnya = Negocio.findNegociosByNyaEquals(nya).getResultList();
    		
    		todos2.addAll(negociosnya);
    		
    		
    	} 
        if (domicilio != ""){
        	List<Negocio> negociosdom = Negocio.findNegociosByDomicilioEquals(domicilio).getResultList();
        	todos2.addAll(negociosdom);
        	
        }
        if (email !=""){
        	List<Negocio> negociosemail = Negocio.findNegociosByEmailEquals(email).getResultList();
        	todos2.addAll(negociosemail);
        	
        } 
        
        if (strtel !=""){
        	List<Negocio> negociostel = Negocio.findNegociosByStrtelEquals(strtel).getResultList();
        	todos2.addAll(negociostel);
        	
        }
        
        if (tags != null){
        	
        	for(int i = 0; i< tags.length; i++){
        		
                tags2.add(Tag.findTagsByNombre_tagEquals(tags[i]).getSingleResult());
            }
      
        	//elimino los repetidos
        	Set<Tag> tags3 = new HashSet<Tag>(tags2);
        	List<Negocio> negociostag = Negocio.findNegociosByTags(tags3).getResultList();
    		todos2.addAll(negociostag);
      
        }
    	
        //elimino negocios repetidos
        todos3 = eliminarDuplicados(todos2);
        
        //agrego a la vistas
        uiModel.addAttribute("negocios",todos3);
        uiModel.addAttribute("size2",todos2.size());
        uiModel.addAttribute("size3",todos3.size());
        
        return "findnegocio/listado";
        
	}
    
    	//funcion para eliminar duplicados con hashset
    	public List<Negocio> eliminarDuplicados(List<Negocio> list){
	        List<Negocio> listSinDuplicados = new ArrayList<Negocio>();
	        Set<Negocio> set = new LinkedHashSet<Negocio>(list);
	        listSinDuplicados.addAll(set);
	        return listSinDuplicados;
    	} 
    
    
/*  
		public void agregarNeg(List<Negocio> aux){
		    	
		    	Negocio negocioaux = new Negocio();
		    	Negocio negocioaux2 = new Negocio();
		    	
		    	if((todos.size())== 0){
		    		todos.addAll(aux);
		    		
		    	}else{
		    	
			    	for(int i =0; i < aux.size() ; i++ ){
						negocioaux = aux.get(i);
						
						for(int j =0; j < todos.size() ; i++ ){
							negocioaux2 = todos.get(j);
							
							if((negocioaux.getId())!= (negocioaux2.getId())){
								todos.add(negocioaux);
							}
						
						
						}
					}
		    	
		    	}
		    
		    
		}

*/
    
/*

		public List<Negocio> eliminarRep(List<Negocio> aux){
			
			Negocio negocioaux = new Negocio();
			Negocio negocioaux2 = new Negocio();
			
			if((aux.size())== 0){
				aux.addAll(aux);
				
			}else{
			
		    	for(int i =0; i < aux.size() ; i++ ){
					negocioaux = aux.get(i);
					
					for(int j =0; j < aux.size() ; i++ ){
						negocioaux2 = (Negocio) aux.get(j);
						
						
						if(i!=j){
							if(negocioaux.getNya().equals(negocioaux2.getNya())){
								aux.remove(j);
							}
							
						}
						
					
					
					}
				}
			
			}
			
			
			
			
			return aux;
			
			
			
			
		}
*/



}