package ec.edu.monster.controlador;

import ec.edu.monster.modelo.PeproProvin;
import ec.edu.monster.controlador.util.JsfUtil;
import ec.edu.monster.controlador.util.JsfUtil.PersistAction;
import ec.edu.monster.modelo.PeproProvinFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("peproProvinController")
@SessionScoped
public class PeproProvinController implements Serializable {

    @EJB
    private ec.edu.monster.modelo.PeproProvinFacade ejbFacade;
    private List<PeproProvin> items = null;
    private PeproProvin selected;

    public PeproProvinController() {
    }

    public PeproProvin getSelected() {
        return selected;
    }

    public void setSelected(PeproProvin selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getPeproProvinPK().setPepaiCodigo(selected.getPepaiPais().getPepaiCodigo());
    }

    protected void initializeEmbeddableKey() {
        selected.setPeproProvinPK(new ec.edu.monster.modelo.PeproProvinPK());
    }

    private PeproProvinFacade getFacade() {
        return ejbFacade;
    }

    public PeproProvin prepareCreate() {
        selected = new PeproProvin();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PeproProvinCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PeproProvinUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PeproProvinDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PeproProvin> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
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

    public PeproProvin getPeproProvin(ec.edu.monster.modelo.PeproProvinPK id) {
        return getFacade().find(id);
    }

    public List<PeproProvin> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PeproProvin> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PeproProvin.class)
    public static class PeproProvinControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PeproProvinController controller = (PeproProvinController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "peproProvinController");
            return controller.getPeproProvin(getKey(value));
        }

        ec.edu.monster.modelo.PeproProvinPK getKey(String value) {
            ec.edu.monster.modelo.PeproProvinPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new ec.edu.monster.modelo.PeproProvinPK();
            key.setPepaiCodigo(values[0]);
            key.setPeproCodigo(values[1]);
            return key;
        }

        String getStringKey(ec.edu.monster.modelo.PeproProvinPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getPepaiCodigo());
            sb.append(SEPARATOR);
            sb.append(value.getPeproCodigo());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PeproProvin) {
                PeproProvin o = (PeproProvin) object;
                return getStringKey(o.getPeproProvinPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PeproProvin.class.getName()});
                return null;
            }
        }

    }

}
