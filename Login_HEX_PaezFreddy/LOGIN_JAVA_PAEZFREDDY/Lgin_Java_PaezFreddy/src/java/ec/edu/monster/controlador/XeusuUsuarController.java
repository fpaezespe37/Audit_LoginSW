package ec.edu.monster.controlador;

import com.google.gson.JsonObject;
import ec.edu.monster.modelo.XeusuUsuar;
import ec.edu.monster.controlador.util.JsfUtil;
import ec.edu.monster.controlador.util.JsfUtil.PersistAction;
import ec.edu.monster.controlador.util.PaginationHelper;
import ec.edu.monster.modelo.Correo;
import ec.edu.monster.modelo.XeusuUsuarFacade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import com.google.gson.JsonParser;
import org.apache.commons.codec.digest.DigestUtils;

@Named("xeusuUsuarController")
@SessionScoped
public class XeusuUsuarController implements Serializable {

    private XeusuUsuar current;
    private DataModel items = null;
    private String newPassword;
    private String repPassword;
    @EJB
    private ec.edu.monster.modelo.XeusuUsuarFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public String getRepPassword() {
        return repPassword;
    }

    public void setRepPassword(String repPassword) {
        this.repPassword = repPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public XeusuUsuarController() {
    }

    public XeusuUsuar getSelected() {
        if (current == null) {
            current = new XeusuUsuar();
            selectedItemIndex = -1;
        }
        return current;
    }

    private XeusuUsuarFacade getFacade() {
        return ejbFacade;
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

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (XeusuUsuar) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new XeusuUsuar();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("XeusuUsuarCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (XeusuUsuar) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("XeusuUsuarUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (XeusuUsuar) getItems().getRowData();
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
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("XeusuUsuarDeleted"));
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

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public XeusuUsuar getXeusuUsuar(java.lang.String id) {
        return ejbFacade.find(id);
    }

    public String validar() {
        String ruta = "";
        XeusuUsuar u;
        try {
            String password = DigestUtils.md5Hex(this.current.getXeusuPaswd());
            //String password = this.current.getXeusuPaswd();
            this.current.setXeusuPaswd(password);
            u = this.ejbFacade.acceder(this.current);
            if (validarCaptcha()) {
                if (u != null) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", u);
                    this.current = u;
                    if (u.getXeusuPiefir().equals("0")) {
                        ruta = "cambiarPassword";
                    } else {
                        ruta = "menu";//?faces-redirect=true
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Email o Contraseña Incorrecta!!", "Email o Password Incorrectos!!"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Captcha Inválido!", "Valide correctamente el captcha para iniciar sesión"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("formLogin", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso" + e.toString(), "Error"));
        }
        return ruta;
    }

    public boolean validarCaptcha() {
        String captchaResponse = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap().get("g-recaptcha-response");
        boolean valid = false;
        try {
            String secretKey = "6LcwYHokAAAAADy1zQ0hkTzwROB2iV8ZnGLbF7hx";
            String url = "https://www.google.com/recaptcha/api/siteverify?secret=" + secretKey + "&response=" + captchaResponse;
            String response = readUrlContent(url);
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(response).getAsJsonObject();
            valid = jsonObject.get("success").getAsBoolean();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valid;
    }

    private String readUrlContent(String urlString) throws IOException {
        URL url = new URL(urlString);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        return sb.toString();
    }

    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public String obtenerNameFoto() {
        XeusuUsuar u = (XeusuUsuar) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if (u.getPeempEmple().getPeempFoto().equals(null) || u.getPeempEmple().getPeempFoto().equals("")) {
            return "user.png";
        } else {
            return u.getPeempEmple().getPeempFoto();
        }
    }

    public String cambiarPassword() {
        String ruta = "";
        XeusuUsuar u = (XeusuUsuar) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        try {
            if (newPassword.equals(repPassword)) {
                String password = DigestUtils.md5Hex(newPassword);

                u.setXeusuPiefir("1");
                //u.setXeusuPaswd(newPassword);
                u.setXeusuPaswd(password);
                getFacade().edit(u);
                ruta = "index";
            } else {
                System.out.println("llego");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Las contraseñas deben ser iguales"));
            }
        } catch (Exception e) {
            throw e;
        }
        return ruta;
    }

    public String resetearPassword() {
        String ruta = "";
        int resp;
        XeusuUsuar u;
        try {
            u = this.ejbFacade.accederMail(this.current);
            if (u != null) {
                String passsword = this.current.getPeempEmple().generarContraseña();
                String encript = DigestUtils.md5Hex(passsword);
                Correo c = new Correo();
                resp = c.enviarCorreo("Reseteo de Contraseña", "Su contraseña reseteada es: " + passsword + "\n\nInicie sesión de con esta contraseña para poder cambiarla.", this.current.getPeempEmple().getPeempEmail());
                ruta = "index";//?faces-redirect=true
                u.setXeusuPaswd(encript);
                u.setXeusuPiefir("0");
                getFacade().edit(u);
                if (resp == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Contraseña restablecida correctamente, revise su correo"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Email No Registrado!!"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }
        return ruta;
    }

    public List<XeusuUsuar> getUsuariosNotRelated() {

        System.out.println(getFacade().getUsuariosNotRelated());
        return getFacade().getUsuariosNotRelated();
    }

    @FacesConverter(forClass = XeusuUsuar.class)
    public static class XeusuUsuarControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            XeusuUsuarController controller = (XeusuUsuarController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "xeusuUsuarController");
            return controller.getXeusuUsuar(getKey(value));
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
            if (object instanceof XeusuUsuar) {
                XeusuUsuar o = (XeusuUsuar) object;
                return getStringKey(o.getXeusuCodigo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + XeusuUsuar.class.getName());
            }
        }
    }

}
