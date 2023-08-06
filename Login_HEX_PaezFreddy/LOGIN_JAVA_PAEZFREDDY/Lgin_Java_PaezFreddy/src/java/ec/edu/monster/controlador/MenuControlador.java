/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.controlador;

import ec.edu.monster.modelo.XeopcOpcio;
import ec.edu.monster.modelo.XeopcOpcioFacade;
import ec.edu.monster.modelo.XeoxpOpcpe;
import ec.edu.monster.modelo.XeoxpOpcpeFacade;
import ec.edu.monster.modelo.XesisSiste;
import ec.edu.monster.modelo.XeusuUsuar;
import ec.edu.monster.modelo.XeuxpUsupe;
import ec.edu.monster.modelo.XesisSisteFacade;
import ec.edu.monster.modelo.XesisVentan;
import ec.edu.monster.modelo.XesisVentanFacade;
import ec.edu.monster.modelo.XeuxpUsupeFacade;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@Named(value = "menuControlador")
@SessionScoped
public class MenuControlador implements Serializable {

    @Inject
    private XesisSisteFacade ejbSistema;

    private List<XesisSiste> lista;
    private List<XeopcOpcio> listaopc;
    private List<XeoxpOpcpe> listaopcpe;
    private List<XesisVentan> listavent;

    private MenuModel model;
    private ArrayList<String> iconos;

    @EJB
    private XeuxpUsupeFacade ejbUsuPerfil;

    @EJB
    private XesisVentanFacade ejbventana;
    @EJB
    private XeopcOpcioFacade ejbOpci;
    @EJB
    private XeoxpOpcpeFacade ejbOpciPer;

    /**
     * Creates a new instance of MenuController
     */
    public MenuControlador() {

    }

    @PostConstruct
    public void init() {
        this.listarMenus();
        model = new DefaultMenuModel();
        this.mostrarMenu();
    }

    public void mostrarMenu() {
        XeuxpUsupe user;
        FacesContext context = FacesContext.getCurrentInstance();
        XeusuUsuar u = (XeusuUsuar) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        user = ejbUsuPerfil.buscarPerfil(u.getXeusuCodigo());
        lista = ejbSistema.getSistemas();

        DefaultMenuItem inicio = DefaultMenuItem.builder().value("Inicio").outcome("/MenuPrinc/menu.xhtml?faces-redirect=true").build();
        
        DefaultSubMenu acciones = DefaultSubMenu.builder().label("Archivo").build();
        DefaultMenuItem con = DefaultMenuItem.builder().value("Conectar").build();
        acciones.getElements().add(con);
        DefaultMenuItem desc = DefaultMenuItem.builder().value("Desconectar").build();
        acciones.getElements().add(desc);
        DefaultMenuItem exp = DefaultMenuItem.builder().value("Exportar").build();
        acciones.getElements().add(exp);
        DefaultMenuItem imp = DefaultMenuItem.builder().value("Importar").build();
        acciones.getElements().add(imp);
        DefaultMenuItem exit = DefaultMenuItem.builder().value("Salir").outcome("/faces/index.xhtml?faces-redirect=true").build();
        acciones.getElements().add(exit);
        
        DefaultSubMenu edicion = DefaultSubMenu.builder().label("Edición").build();
        DefaultMenuItem copiar = DefaultMenuItem.builder().value("Copiar").build();
        edicion.getElements().add(copiar);
        DefaultMenuItem cortar = DefaultMenuItem.builder().value("Cortar").build();
        edicion.getElements().add(cortar);
        DefaultMenuItem pegar = DefaultMenuItem.builder().value("Pegar").build();
        edicion.getElements().add(pegar);
        DefaultMenuItem buscar = DefaultMenuItem.builder().value("Buscar").build();
        edicion.getElements().add(buscar);
        DefaultMenuItem reemp = DefaultMenuItem.builder().value("Reemplazar").build();
        edicion.getElements().add(reemp);
        
        DefaultSubMenu crud = DefaultSubMenu.builder().label("CRUD").build();
        
        DefaultSubMenu procesos = DefaultSubMenu.builder().label("Procesos").build();
        
        DefaultSubMenu reportes = DefaultSubMenu.builder().label("Reportes").build();
        
        model.getElements().add(inicio);
        model.getElements().add(acciones);
        model.getElements().add(edicion);
        
        if (user != null) {
            lista.forEach((m) -> {
                DefaultSubMenu subsistemasCRUD = DefaultSubMenu.builder().label(m.getXesisDescri()).build();
                DefaultSubMenu subsistemasProc = DefaultSubMenu.builder().label(m.getXesisDescri()).build();
                DefaultSubMenu subsistemasRep = DefaultSubMenu.builder().label(m.getXesisDescri()).build();
                listavent = ejbventana.getSistemas(m);

                //for (XesisVentan v : listavent) {
                //listaopc = ejbOpci.getOpciones(v.getXevenCodigo());
                //  for (XeopcOpcio opc : listaopc) {
                listaopcpe = ejbOpciPer.getOpcionesPoPerfil(user.getXeperPerfi().getXeperCodigo());
                int cont = 0;
                List<String> opcioneslista = new ArrayList<>();
                for (XeoxpOpcpe opcpe : listaopcpe) {
                    if (opcpe.getXeopcOpcio().getXesisVentan().getXesisCodigo().getXesisCodigo().equals(m.getXesisCodigo())) {
                        for (String l : opcioneslista) {
                            if (l.equals(opcpe.getXeopcOpcio().getXeopcDescri())) {
                                cont = 1;
                            }

                        }
                        if (opcpe.getXeoxpOpcpePK().getXeperCodigo().equals(user.getXeuxpUsupePK().getXeperCodigo()) && cont == 0) {
                            String url = obtencionUrl(opcpe.getXeopcOpcio().getXeopcUrl());
                            DefaultMenuItem ventana;
                            if (url.startsWith("http")) {
                                ventana = DefaultMenuItem.builder().value(opcpe.getXeopcOpcio().getXeopcDescri()).url(url).build();
                            } else {
                                ventana = DefaultMenuItem.builder().value(opcpe.getXeopcOpcio().getXeopcDescri()).outcome(url).build();
                            }
                            opcioneslista.add(opcpe.getXeopcOpcio().getXeopcDescri());

                            /*if (opcpe.getXeopcOpcio().getXesisVentan().getXevenMensaj().equals("PROCESOS")) {
                                proc.getElements().
                            } else {
                                adm.getElements().add(ventana);
                            }*/
                            if (opcpe.getXeopcOpcio().getXesisVentan().getXevenDescri().equals("CRUD")) {
                                subsistemasCRUD.getElements().add(ventana);
                            } else if (opcpe.getXeopcOpcio().getXesisVentan().getXevenDescri().equals("Procesos")){
                                subsistemasProc.getElements().add(ventana);
                            } else {
                                subsistemasRep.getElements().add(ventana);
                            }
                            
                        }
                        cont = 0;
                    }

                }

                //  }
                // }
                crud.getElements().add(subsistemasCRUD);
                procesos.getElements().add(subsistemasProc);
                reportes.getElements().add(subsistemasRep);
            });
        }
        
        model.getElements().add(crud);        
        model.getElements().add(procesos);
        model.getElements().add(reportes);
        
        /*DefaultMenuItem password = DefaultMenuItem.builder().value("Cambiar Contraseña").outcome("/Password/cambiarPass.xhtml?faces-redirect=true").build();
        model.getElements().add(password);*/
        DefaultMenuItem ayuda = DefaultMenuItem.builder().value("Ayuda").outcome("/Ayuda/ayuda.xhtml?faces-redirect=true").build();
        model.getElements().add(ayuda);

    }

    public void listarMenus() {
        try {
            lista = ejbSistema.getSistemas();
        } catch (Exception e) {

        }

    }

    public String obtencionUrl(String url) {
        String direccion;
        if (!url.isEmpty()) {
            if (url.startsWith("http")) {
                direccion = url;
            } else {
                String text = url.concat(".xhtml?faces-redirect=true");
                direccion = text.replace(" ", "");
            }
        } else {
            direccion = "/Ayuda/PaginaConstruccion.xhtml?faces-redirect=true";
        }
        return direccion;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

    }

}
