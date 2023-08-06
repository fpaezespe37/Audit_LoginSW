package ec.edu.monster.controlador;

import ec.edu.monster.modelo.PeempEmple;
import ec.edu.monster.controlador.util.JsfUtil;
import ec.edu.monster.controlador.util.JsfUtil.PersistAction;
import ec.edu.monster.controlador.util.PaginationHelper;
import ec.edu.monster.modelo.Correo;
import ec.edu.monster.modelo.PecanCanton;
import ec.edu.monster.modelo.PedirDirecc;
import ec.edu.monster.modelo.PeempEmpleFacade;
import ec.edu.monster.modelo.PepaiPais;
import ec.edu.monster.modelo.PeparParroq;
import ec.edu.monster.modelo.PeparParroqPK;
import ec.edu.monster.modelo.PeproProvin;
import ec.edu.monster.modelo.XeestEstad;
import ec.edu.monster.modelo.XeperPerfi;
import ec.edu.monster.modelo.XeusuUsuar;
import ec.edu.monster.modelo.XeuxpUsupe;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.Serializable;
import java.sql.Date;
import java.util.Formatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

@Named("peempEmpleController")
@SessionScoped
public class PeempEmpleController implements Serializable {
    
    private DataModel items = null;
    private XeusuUsuar usuario;     
    private UploadedFile foto;
    private String aux;
    private XeperPerfi rol;
    private XeuxpUsupe perfilUser;
    private PedirDirecc direccion;
    private StreamedContent imagepreview;
    private String inLimiteAvaliacao;
    private boolean mostrar = true;
    
    private String codPais;
    private List<PepaiPais> listPaises;
    private String codProvin;
    private List<PeproProvin> listProvin;
    private String codCanton;
    private List<PecanCanton> listCanton;
    private String codParroquia;
    private List<PeparParroq> listParroquias;
    private String opcFiltro;
    private String idGenero;
    private List<PeempEmple> empleadoList;
    private String codDepartamento;
    private java.util.Date fechaFiltro;
    private String nombreFiltro;
    private String pedirCalleprincipal;
    private String pedirCallesecundaria;

    private String valor;
    private int flag = 1;
    private XeestEstad xeestCodigo;
    
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    @EJB
    private ec.edu.monster.modelo.PeempEmpleFacade ejbFacade;
    private PeempEmple selected;
    private PeempEmple current;
    @EJB
    private ec.edu.monster.modelo.PepaiPaisFacade ejbPais;
    @EJB
    private ec.edu.monster.modelo.PeproProvinFacade ejbProvincia;
    @EJB
    private ec.edu.monster.modelo.PecanCantonFacade ejbCanton;
    @EJB
    private ec.edu.monster.modelo.PeparParroqFacade ejbparroquia;
    @EJB
    private ec.edu.monster.modelo.XeusuUsuarFacade ejbUsuario;
    @EJB
    private ec.edu.monster.modelo.PedirDireccFacade ejbDireccion;
  
    public PeempEmpleController() {
    }
    
    public void limiteAvaliacao(){
        if(inLimiteAvaliacao.equals("valor1")){
            mostrar = false;
        } else {
            mostrar = true;
        }
    }
    
    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }
    
    public String getOpcFiltro() {
        return opcFiltro;
    }

    public void setOpcFiltro(String opcFiltro) {
        this.opcFiltro = opcFiltro;
    }
    
    public String getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(String idGenero) {
        this.idGenero = idGenero;
    }
    
    public String getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(String codDepartamento) {
        this.codDepartamento = codDepartamento;
    }
    
    public void obtenerEmpleadosGeneros(){
        empleadoList= this.ejbFacade.obtenerEmpleadosGeneros(idGenero);
    }
    
    public void obtenerEmpleadosDepartamentos(){
        empleadoList= this.ejbFacade.obtenerEmpleadosDepartamentos(codDepartamento);
    }
    
    public void obtenerEmpleadosPais(){
        empleadoList= this.ejbFacade.obtenerEmpleadosPais(codPais);
    }
    
    public void obtenerEmpleadosProvin(){
        empleadoList= this.ejbFacade.obtenerEmpleadosProvin(codProvin);
    }
    
    public void obtenerEmpleadosCanton(){
        empleadoList= this.ejbFacade.obtenerEmpleadosCanton(codCanton);
    }
    
    public void obtenerEmpleadosParroq(){
        empleadoList= this.ejbFacade.obtenerEmpleadosParroq(codParroquia);
    }
    
    public void obtenerEmpleadosFechas(){
        empleadoList= this.ejbFacade.obtenerEmpleadosFechas(fechaFiltro);
    }
    
    public void obtenerEmpleadosNombre(){
        empleadoList= this.ejbFacade.obtenerEmpleadosPorNombre(nombreFiltro);
    }
    
    public java.util.Date getFechaFiltro() {
        return fechaFiltro;
    }

    public void setFechaFiltro(java.util.Date fechaFiltro) {
        this.fechaFiltro = fechaFiltro;
    }
    
    public List<PeempEmple> getEmpleadoList() {
        if(empleadoList==null)
            empleadoList=ejbFacade.obtenerEmpleados();
        return empleadoList;
    }

    public PeempEmple getSelected() {
        if (current == null) {
            current = new PeempEmple();
            selectedItemIndex = -1;
        }
        return current;
    }

    public void setSelected(PeempEmple selected) {
        this.selected = selected;
    }
    
    public String getAux() {
        return aux;
    }

    public void setAux(String aux) {
        this.aux = aux;
    }
    
    public List<PepaiPais> getListPaises() {
        if(listPaises==null)
            listPaises=ejbPais.findAll();
        return listPaises;
    }
    
    public void setListPaises(List<PepaiPais> listPaises) {
        this.listPaises = listPaises;
    }
    
    public String getCodPais() {
        return codPais;
    }

    public void setCodPais(String codPais) {
        this.codPais = codPais;
    }
    
    public String getCodProvin() {
        return codProvin;
    }

    public void setCodProvin(String codProvin) {
        this.codProvin = codProvin;
    }
    
    public List<PeproProvin> getListProvin() {
        return listProvin;
    }

    public void setListProvin(List<PeproProvin> listProvincias) {
        this.listProvin = listProvincias;
    }
    
    public void obtenerProvincias(){
        if(listProvin!=null){
            codCanton="";
            codParroquia="";
            listCanton= null;
            listParroquias = null;
        }
        listProvin=ejbProvincia.obtenerProvinciasPais(codPais);
    }
    
    public void obtenerCantones(){
        if(listCanton!=null){
            listParroquias= null;
            codParroquia="";
        }
        listCanton=ejbCanton.obtenerCantonesProvincia(codProvin);
    }
    
    public String getCodCanton() {
        return codCanton;
    }

    public void setCodCanton(String codCanton) {
        this.codCanton = codCanton;
    }
    
    public List<PecanCanton> getListCanton() {
        return listCanton;
    }

    public void setListCanton(List<PecanCanton> listCanton) {
        this.listCanton = listCanton;
    }
    
    public void obtenerParroquias(){
        listParroquias=ejbparroquia.obtenerParrquiasCantones(codCanton);
    }
    
    public String getCodParroquia() {
        return codParroquia;
    }

    public void setCodParroquia(String codParroquia) {
        this.codParroquia = codParroquia;
    }
    
    public List<PeparParroq> getListParroquias() {
        return listParroquias;
    }

    public void setListParroquias(List<PeparParroq> listParroquias) {
        this.listParroquias = listParroquias;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PeempEmpleFacade getFacade() {
        return ejbFacade;
    }
    
    public UploadedFile getFoto() {
        return foto;
    }

    public void setFoto(UploadedFile foto) {
        this.foto = foto;
    }
    
    public StreamedContent getImagepreview() {
        return imagepreview;
    }

    public void setImagepreview(StreamedContent imagepreview) {
        this.imagepreview = imagepreview;
    }
    
    public void preview(FileUploadEvent event) {
        foto = event.getFile();
        System.out.println("Uploaded");
        System.out.println(event.getFile().getFileName());
        imagepreview = DefaultStreamedContent.builder()
                .contentType("image/*")
                .stream(() -> {
                    try {
                        return new ByteArrayInputStream(event.getFile().getContent());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .build();
        //return Base64.getEncoder().encodeToString(image.getContent());

        //return Base64.getEncoder().encodeToString(image.getContent());
    }

    public String prepareCreate() {
        foto=null;
        current = new PeempEmple();
        selectedItemIndex = -1;
        recreatePagination();
        recreateModel();
        addSuccessMessage("Completado","Usuario Creado correctamente");
        return "List";
    }
    
    public String prepareCreateInvitado() {
        foto=null;
        current = new PeempEmple();
        selectedItemIndex = -1;
        recreatePagination();
        recreateModel();
        addSuccessMessage("Completado","Usuario Creado correctamente, revise su correo para recibir su contraseña");
        return "/index.xhtml";
    }
    
    private void recreatePagination() {
        pagination = null;
    }
    
    private void recreateModel() {
        items = null;
    }
    
    public  void addSuccessMessage(String msg2,String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg2, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public String create() {
        try {
            current.setPeempCurp(ejbFacade.crearCodigo());
            subirArchivo();
            current.setPeempFoto(aux);
            current.setPedirCodigodireccion(ejbFacade.consultarDireccion("5"));
            //current.setCoddepart(codDepartamento);
            if(current.getPeempCedula() == null){
                current.setPeempCedula("NULL");
            }
            PeparParroqPK p = new PeparParroqPK(codPais, codProvin, codCanton, codParroquia);
            PeparParroq peparParroq = ejbparroquia.find(p);
            current.getPedirCodigodireccion().setPeparParroq(peparParroq);
            //java.util.Date fecha = new java.util.Date();
            getFacade().create(current);
            String codigoUser = registrarUsuario(current);
            // perfilUser= new XeuxpUsupe(codigoUser, rol.getXeperCodigo(), "1");
            // ejbPerfilUser.create(perfilUser);
            //JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PeempEmpleCreated"));

            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    public String createInvitado() {
        try {
            current.setPeempCurp(ejbFacade.crearCodigo());
            subirArchivo();
            current.setPeempFoto(aux);
            current.setPedirCodigodireccion(ejbFacade.consultarDireccion("5"));
            //current.setCoddepart(codDepartamento);
            if(current.getPeempCedula() == null){
                current.setPeempCedula("NULL");
            }
            PeparParroqPK p = new PeparParroqPK(codPais, codProvin, codCanton, codParroquia);
            PeparParroq peparParroq = ejbparroquia.find(p);
            current.getPedirCodigodireccion().setPeparParroq(peparParroq);
            //java.util.Date fecha = new java.util.Date();
            getFacade().create(current);
            String codigoUser = registrarUsuario(current);
            // perfilUser= new XeuxpUsupe(codigoUser, rol.getXeperCodigo(), "1");
            // ejbPerfilUser.create(perfilUser);
            //JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PeempEmpleCreated"));

            return prepareCreateInvitado();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    public void subirArchivo() {
        try {
            aux = "resources/imgUsers";
            String dir = JsfUtil.getPath();

            String newDir = dir.replace("\\", "/");
            File archivo = new File(newDir + aux);
            if (!archivo.exists()) {
                archivo.mkdirs();
                
                System.out.println("Direccion inexistente");
            } else {
                System.out.println("Direccion válida");
            }
            System.out.println(getFoto().getInputStream());
            copiarArchivo(getFoto().getFileName(), getFoto().getInputStream(), newDir);
            //return true;
        } catch (Exception e) {
            //return false;
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString(" Error en a imagen"));
        }
    }
    
    public void copiarArchivo(String nombreArchivo, InputStream i, String dir) {
        try {
            /*if (nombreArchivo.endsWith(".png")) {
                if(current.getPeempCedula() != null){
                    nombreArchivo = current.getPeempCedula() + ".png";
                }
                
            } else if (nombreArchivo.endsWith(".jpg")) {
                if(current.getPeempCedula() != null){
                    nombreArchivo = current.getPeempCedula() + ".jpg";
                }
                
            } else if (nombreArchivo.endsWith(".gif")) {
                if(current.getPeempCedula() != null){
                    nombreArchivo = current.getPeempCedula() + ".gif";
                }
            }*/

            aux = aux + "/" + nombreArchivo;
            System.out.println("Ruta Real: " + dir + aux);
            OutputStream out = new FileOutputStream(new File(dir + aux));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = i.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            aux = aux.substring(19);
            i.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("Error"));
        }
    }
    
    public String registrarUsuario(PeempEmple empleado) {
        long miliseconds = System.currentTimeMillis();
        Date fechaAct = new Date(miliseconds);
        Correo c = new Correo();
        String passsword = empleado.generarContraseña();
        String encript = DigestUtils.md5Hex(passsword);
        System.out.println("password:" + passsword);
        c.enviarCorreo("Generación de Contraseña", "Su contraseña es: " + passsword + "\n\nInicie sesión de con esta contraseña para poder cambiarla.", empleado.getPeempEmail());
        int codigo = this.ejbUsuario.crearCodigo();
        Formatter fmt = new Formatter();
        String codigoUser = codigo + "";
        usuario = new XeusuUsuar(codigoUser, encript, fechaAct, fechaAct, "0");
        usuario.setPeempEmple(empleado);
        xeestCodigo = new XeestEstad("1", passsword);
        usuario.setXeestCodigo(xeestCodigo);
        ejbUsuario.create(usuario);
        return codigoUser;
    }
    
    public void verificarSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            XeusuUsuar u = (XeusuUsuar) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (u == null) {
                context.getExternalContext().redirect("index.xhtml");
            } else {
                current = u.getPeempEmple();
            }
        } catch (Exception e) {

        }
    }
    
    public void limpiar(ComponentSystemEvent event) {
            current = null;
    }

    public String update() {
        try {
            if(getFoto() == null){
                current.setPeempFoto(current.getPeempFoto());
            } 
            else {
                System.out.println("entra foto");
                subirArchivo();
                current.setPeempFoto(aux);
            }
            getFacade().edit(current);
            foto=null;
            recreatePagination();
             recreateModel();
        
            addSuccessMessage("Completado","Usuario Editado correctamente");
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    public String updateEmp() {
        try {
            if(getFoto() == null){
                current.setPeempFoto(current.getPeempFoto());
            } 
            else {
                System.out.println("entra foto");
                subirArchivo();
                current.setPeempFoto(aux);
            }
            getFacade().edit(current);
            foto=null;
            recreatePagination();
             recreateModel();
        
            addSuccessMessage("Completado","Usuario Editado correctamente");
            return "";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (PeempEmple) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }
    
    private void performDestroy() {
        try {
            //usuario.setPeempEmple(current);
            //ejbUsuario.remove(usuario);
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PeempEmpleDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
    
    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }
    
    public void inicializarEmpleado() {
            current=null;
    }
    
    public String prepareView() {
        current = (PeempEmple) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    
    public String prepareEdit() {
        flag = 2;
        current = (PeempEmple) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }
    
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }
    
    public String getInLimiteAvaliacao() {
        return inLimiteAvaliacao;
    }

    public void setInLimiteAvaliacao(String inLimiteAvaliacao) {
        this.inLimiteAvaliacao = inLimiteAvaliacao;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public PeempEmple getPeempEmple(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<PeempEmple> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PeempEmple> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PeempEmple.class)
    public static class PeempEmpleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PeempEmpleController controller = (PeempEmpleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "peempEmpleController");
            return controller.getPeempEmple(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PeempEmple) {
                PeempEmple o = (PeempEmple) object;
                return getStringKey(o.getPeempCurp());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PeempEmple.class.getName()});
                return null;
            }
        }

    }

}
